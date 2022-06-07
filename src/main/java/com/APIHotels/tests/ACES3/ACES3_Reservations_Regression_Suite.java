package com.APIHotels.tests.ACES3;

import java.util.List;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.APIHotels.tests.ACESII.ACESII_Operations_Regression_Suite;

public class ACES3_Reservations_Regression_Suite extends LocalDriverManager{
	
	public PgWrapper pgWrapper;
	ACESII_Operations_Regression_Suite oRS;
		
	@Test(description = "JIRA# ATA-80 - Verify that the BT reservation created in Airline website are displayed in Find reservation screen for the selected date range", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void verifyFindReservationsInSS_BT(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotelValue, String singleRoomType, String doubleRoomType, String reasonCodeValue, String tenantName,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String aces3ConfirmationCode) throws Exception{
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		oRS = new ACESII_Operations_Regression_Suite();
		
		//Create BTReservation for Endeavor from Airlines and book without confirmation from ACES2 application
		oRS.processBTReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, singleRoomType, doubleRoomType, reasonCodeValue, tenantName,
				timeFrameFilterValue, refreshIntervalValue, confirmationCode);
		String reservId = oRS.getReservationId();
		/*Login to Aces3 supplier site, select the supplier and navigate to Reservations Page
		 * Navigate to FindReservations tab and enter date range to fetch Reservations created in Airlines are displaying in ACES3 application
		*/
		verifyReservations(tenantName, destinationValue, hotelValue, reservId);
		confirmReservations(reservId, aces3ConfirmationCode, 1);
	}
	
	

	@Test(description = "JIRA# ATA-80 - Verify that the Training reservation created in Airline website are displayed in Find reservation screen for the selected date range", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void createTrainingReservInAirline(String runMode, String destinationValue, String timeFormatValue, String arrivaltimeValue, 
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String tenantName, String aces3ConfirmationCode, String acceptDeclineIndicator, String declineNotes) throws Exception{
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			// logger.info("**** Create a reservation in request reservation
			// page ");
			String reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue,
					arrivaltimeValue, departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue,
					departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue,
					accountTypeValue, singleRoomType, doubleRoomType, "SendToVendorWebsite");
			String expected = "Pending Assignment Confirmation";
			pgWrapper.genericClass.findReservation(reservId, expected);
			hotelValue = pgWrapper.findReservationBTPage.getSupplierName();
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();

			/*
			 * hotelValue = "Best Western Tlc Hote"; String reservId =
			 * "1100682";
			 */
			/*
			 * Login to Aces3 supplier site, select the supplier and navigate to
			 * Reservations Page Navigate to FindReservations tab and enter date
			 * range to fetch Reservations created in Airlines are displaying in
			 * ACES3 application
			 */

			verifyReservations(tenantName, destinationValue, hotelValue, reservId);

			if (acceptDeclineIndicator.equals("Accept"))
				confirmReservations(reservId, aces3ConfirmationCode, 1);
			else if (acceptDeclineIndicator.equals("Decline"))
				declineReservations(reservId, declineNotes);
		}

	}
	
	@Test(description = "JIRA# ATA-80 - Verify that the Aces Direct reservation created in Airline website are displayed in Find reservation screen for the selected date range", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void createAcesDirectReservInAirline(String departmentValue, String pairingNumber, String locationValue, String arrivalFlightNumberValue,
			String arrivalTimeValue, String departureFlightNumberValue, String departureTimeValue,
			String reportTimeValue, String individualEmpId, String empName, String noOfRooms, String tenantName, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String roomCountValue, String confirmationCode,
			String toHotelProviderValue, String fromHotelGTProviderValue, String rateValue, String isTaxIncluded,
			String billingMethodValue, String aces3ConfirmationCode) throws Exception{
        
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"), readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnAcesDirectLink();
		List<String>list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		String arrivalDate = list.get(0);
		String departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(readPropValue("username"), readPropValue("password"),
				tenantName, readPropValue("bidMonthIndex"), timeFrameFilterValue, timeFormatValue,
				refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S"+roomCountValue, locationValue,
				arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(1);
		System.out.println("assignmentAlertRowCount:" + 1);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue,fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		//pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		String hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();
		
		/*Login to Aces3 supplier site, select the supplier and navigate to Reservations Page
		 * Navigate to FindReservations tab and enter date range to fetch Reservations created in Airlines are displaying in ACES3 application
		*/
		
		verifyReservations(tenantName, locationValue, hotelName, pairingNumber);
		confirmReservations(pairingNumber, aces3ConfirmationCode, Integer.parseInt(roomCountValue));
	}
	
	private void verifyReservations(String tenantName, String city, String supplier, String reservationId) throws Exception{
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"), readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplier);
		pgWrapper.aces3SupplierHomePage.clickOnReservationsLink();
		pgWrapper.aces3SupplierReservationsPage.clickOnFindReservationTab();
		pgWrapper.aces3SupplierReservationsPage.findReservationForDateRange();
		pgWrapper.aces3SupplierReservationsPage.verifyReservationDetails(reservationId);
		pgWrapper.aces3SupplierReservationsPage.verifyStatus(reservationId, "Pending Assignment Confirmation");
	}
	
	private void confirmReservations(String reservId, String confirmationNumber, int count) throws InterruptedException {
		pgWrapper.aces3SupplierReservationsPage.clickOnPendingConfirmationTab();
		for(int i = 0; i < count; i++)
			pgWrapper.aces3SupplierReservationsPage.enterConfirmationNumberForReserv(reservId, confirmationNumber);	
		pgWrapper.aces3SupplierReservationsPage.clickOnFindReservationTab();
		pgWrapper.aces3SupplierReservationsPage.findReservationForDateRange();
		pgWrapper.aces3SupplierReservationsPage.verifyStatus(reservId, "Booked");
	}
	
	private void declineReservations(String reservId, String declineNotes) {
		pgWrapper.aces3SupplierReservationsPage.clickOnPendingConfirmationTab();
		pgWrapper.aces3SupplierReservationsPage.declineReservation(reservId, declineNotes);
		pgWrapper.aces3SupplierReservationsPage.clickOnFindReservationTab();
		pgWrapper.aces3SupplierReservationsPage.findReservationForDateRange();
		pgWrapper.aces3SupplierReservationsPage.verifyStatus(reservId, "Cancelled");
	}

}
