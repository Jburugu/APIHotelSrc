package com.APIHotels.tests.ACESIntegration;

import java.io.IOException;

import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_Integrations_Regression_Suite extends LocalDriverManager {
	public PgWrapper pgWrapper;

	@Test(description = "Manual Booking Tab - Request Reservation page verification", dataProvider = "TestDataFile", groups = {
			"Regression" })
	public void RequestReservationManualBooking(String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String empID, String empName, String tenantName,
			String timeFrameFilterValue, String refreshIntervalValue) throws Exception {
		//
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AirFranceUsername"),
				readPropValue("AirFrancePassword"));
		String reservationId = createBTJB(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, empID, empName);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//// Go to ACES2 main site and verify that created reservation is shown in Ops
		//// Dashboard
		getDriver().get(aces2Url);
//				String reservationId ="1178692";
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Started processing the following : " + reservationId + " reservation.");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation("Hotel", reservationId);// "Pending Assignment"
		pgWrapper.pageFindReservation.verifyReservationStatus("Pending Assignment");
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		// Process the PA
		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pageDashBoard.selectTheSupplier();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode("confirm");
		pgWrapper.pageDashBoard.selectPaymentMethodManualBooking("Direct Billing");
		pgWrapper.pageFindReservation.clickOnFinishButton();
		pgWrapper.pageDashBoard.clickYesAlertBtnMB();

		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation("Hotel", reservationId);// "Pending Assignment"
		pgWrapper.pageFindReservation.verifyReservationStatus("Booked");
	}

	public String createBTJB(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotel, String empID, String EmpName) throws IOException {
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsForRoom(empID, EmpName);// 11790
		pgWrapper.requestReservationPage.hotelDescription("test");
//				pgWrapper.requestReservationPage.selectReason("1");
//				pgWrapper.requestReservationPage.reason("1");
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservId;
		return reservId = pgWrapper.requestReservationPage.processingRequest();

	}

	@Test(description = "Manual Booking Tab - Create Request Reservation and Find it in Airlines site", dataProvider = "TestDataFile", groups = {
			"Regression" })
	public void createReqReservationAndFind(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotel, String empID, String empName, String statusPA) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AirFranceUsername"),
				readPropValue("AirFrancePassword"));
		String reservationId = createBTJB(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, empID, empName);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.findReservationAF(reservationId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		pgWrapper.findReservationBTPage.verifyReservationExists(reservationId, statusPA);

	}

	@Test(description = "Manual Booking Tab - Create Request Limo and process it in Aces site", dataProvider = "TestDataFile", groups = {
			"Regression" })
	public void createReqLimo(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String empNameValue, String noOfPassengersValue,
			String notesValue, String pickupPlaceValue, String dropOffPlaceValue, String tenantName,
			String reservationType, String statusPA, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String confirmationCode, String directBilling, String statusBooked)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AirFranceUsername"),
				readPropValue("AirFrancePassword"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
		String getOpertionDate = pgWrapper.requestLimoPage.requestLimoPageUnderBTAF(locationValue, pickUpTimeValue,
				pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue,
				empIdValue, empNameValue, noOfPassengersValue, notesValue, pickupPlaceValue, dropOffPlaceValue);
		String tripId = pgWrapper.requestLimoPage.processingRequestBT();
		System.out.println("Created Reservation Id is : " + tripId);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		getDriver().get(aces2Url);
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Started processing the following : " + tripId + " reservation.");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation(reservationType, tripId);
		pgWrapper.pageFindReservation.verifyReservationStatus(statusPA);
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		// Process the PA
		pgWrapper.pageDashBoard.searchWithTripNumber(tripId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pageDashBoard.selectTheSupplier();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pageDashBoard.selectPaymentMethodManualBooking(directBilling);
		pgWrapper.pageFindReservation.clickOnFinishButton();
		pgWrapper.pageDashBoard.clickYesAlertBtnMB();

		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation(reservationType, tripId);// "Pending Assignment"
		pgWrapper.pageFindReservation.verifyReservationStatus(statusBooked);
	}
	
	@Test(description = "Manual Booking Tab - Create Request Limo and process it in Aces site", dataProvider = "TestDataFile", groups = {
	"Regression" })
	public void createRoomBlockRequestMB(String locationValue, String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String roomCountValue, String reasonCountValue,
			String adhocType, String notesValue, String tenantName, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue) throws NumberFormatException, Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AirFranceUsername"),
				readPropValue("AirFrancePassword"));
		pgWrapper.operationsTab.clickOnAdhocHotelRoomBlockRequests();
		pgWrapper.requestRoomBlockPage.createRoomblock(locationValue, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		pgWrapper.requestRoomBlockPage.clickSaveBtn();
		pgWrapper.requestRoomBlockPage.processingRequest();
		
		pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		getDriver().get(aces2Url);
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Started processing the  reservation.");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndPickupDate("Adhoc-Hard","14-Jan-2021");
		
	}
}
