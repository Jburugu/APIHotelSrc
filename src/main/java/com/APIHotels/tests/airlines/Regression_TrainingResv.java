package com.APIHotels.tests.airlines;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class Regression_TrainingResv extends LocalDriverManager {

	public PgWrapper pgWrapper;
	String reservId ;
	String faxNumber;
	String saveButtonType;
	String expected ;
	String windowHandler;
	//String airlinesUrl = "http://10.10.103.152:3080/ACESAIR/";

	// @Test(description="TC:01 - Verify the tab index of Training Reservation for Envoy Airlines", dataProvider = "TestDataFile", groups = "Regression")
	public void verifyTabIndex(String envoyUsername, String envoyPassword) {
		//logger.info("**** Create a reservation in request reservation page ");
		pgWrapper.genericClass.airlinesLoginAndNavigateToTrainingReservation(envoyUsername, envoyPassword);
		pgWrapper.requestReservationPage.verifyFunctionalityWithTab();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	@Test(description = "TC:02 - Verify the Envoy Airlines user able to block training room", dataProvider = "TestDataFile", groups = "Regression")
	public void blockTrainingRoom(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String userName, String password, String tenantName,
			String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode,
			String hotelNotesValue, String vendorNotesValue, String billingValue, String serviceType,String expectedValue)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//saveButtonType = "SendToAPI";
		saveButtonType = "SendToVendorWebsite";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		
		expected = "Pending Assignment";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Verifying the created reservation in ACES Application");
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(userName, password, tenantName, bidMonthIndex);
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode, hotelNotesValue, vendorNotesValue,
				billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();
		//logger.info("Verification of reservation under aces application is done");

		//logger.info("*** Verifying the Booked reservation in ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToTrainingReservation(envoyUsername, envoyPassword);
		pgWrapper.genericClass.findReservation(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	//@Test(description = "TC:03 - Verify the Envoy Airlines user able to block the training room and 'Generate Fax' to the Supplier", priority=2,dataProvider = "TestDataFile", groups = "Regression")
	public void blockTrainingRoomGenerateFax(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String userName, String password, String tenantName,
			String bidMonthIndex,String expectedValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a reservation in request reservation page ");
		saveButtonType = "GenerateFax";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		// Check the Fax pdf is displaying successfully - ADD 
		
		//logger.info("**** find reservation with startdate,enddate and confirmation number");
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		faxNumber = pgWrapper.findReservationBTPage.clickOnNotesLinkAndValidateFaxNumber();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verifying the created reservation in ACES Application");
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(userName, password, tenantName, bidMonthIndex);
		pgWrapper.genericClass.clickOnPendingConfirmationLinkUnderOPSDesk();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.clickOnNotesLink(faxNumber);
		// add code for - Aces user should successfully view the fax in PDF.
		pgWrapper.pageHome.clickLogoutLink();
		// Need inputs for Supplier site link - Login into Supplier website Add steps
		
		//logger.info("*** Verifying the Booked reservation in ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToTrainingReservation(envoyUsername, envoyPassword);
		pgWrapper.genericClass.findReservation(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}
	
	//@Test(description = "TC:04 - Verify the Envoy Airlines user able to confirm the training room which was created through 'Generate Fax' option.",priority=3, dataProvider = "TestDataFile", groups = "Regression")
	public void confirmTrainingRoom(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType,String cancellationValue, String confirmationValue, String notesValue,String expectedValue) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a reservation in request reservation page ");
		saveButtonType = "GenerateFax";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		// Check the Fax pdf is displaying successfully - ADD

		//logger.info("**** find reservation with startdate,enddate and confirmation number");
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.findReservationBTPage.confirmLinkCancellationValue(cancellationValue,confirmationValue, notesValue);
		try{Thread.sleep(7000);}catch(Exception e){}
		pgWrapper.findReservationBTPage.verifyReservationExists(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	@Test(description = "TC:05 - Verify the OPS Team able to confirm the training room which was created through 'Generate Fax' option.",priority=4, dataProvider = "TestDataFile", groups = "Regression")
	public void confirmTrainingRoomThroughOps(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String userName, String password, String tenantName, String bidMonthIndex,
			String confirmationCode, String expectedValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a reservation in request reservation page ");
		saveButtonType = "GenerateFax";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		
		//logger.info("**** find reservation with startdate, enddate and confirmation number");
		String expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verifying the created reservation in ACES Application");
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(userName, password, tenantName, bidMonthIndex);
		pgWrapper.genericClass.clickOnPendingConfirmationLinkUnderOPSDesk();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.clickOnFaxImageLink();
		pgWrapper.pagePendingConfirmations.clickTripLinkAndEnterconfirmationDetails(confirmationCode);
		// Click on the Fax Number to view the Fax PDF - This step is not according to the flow
		// Unable to find fax number after booking a reservation
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** find reservation with startdate,enddate and confirmation number");
		pgWrapper.genericClass.airlinesLoginAndNavigateToTrainingReservation(envoyUsername, envoyPassword);
		pgWrapper.genericClass.findReservation(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	//@Test(description = "TC:06 - Verify the Airline user able to confirm the training room which was created through 'Send to Hotel by email' option.",priority=5, dataProvider = "TestDataFile", groups = "Regression")
	public void confirmTrainingRoomViaEmail(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType,String cancellationValue, String confirmationValue, String notesValue,String expectedValue) throws Exception{

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a reservation in request reservation page ");
		//Check the Email pdf is displaying successfully
		saveButtonType = "";
		expected = "Pending Assignment Confirmation";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		
		//logger.info("**** find reservation with startdate,enddate and confirmation number");
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.findReservationBTPage.confirmLinkCancellationValue(cancellationValue, confirmationValue, notesValue);
		try{Thread.sleep(7000);}catch(Exception e){}
		pgWrapper.findReservationBTPage.verifyReservationExists(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}

	//@Test(description = "TC:07 - Verify the Supplier able to confirm the training room which was created through 'Send to vendor website' option.",priority=6, dataProvider = "TestDataFile", groups = "Regression")
	public void confirmTrainingViaVendorWebsite(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType,String expectedValue) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a reservation in request reservation page ");
		
		// Unable to click on vendor website Manual in QA and dev env
		saveButtonType = "SendToVendorWebsite";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,saveButtonType);
		
		//logger.info("**** find reservation with startdate,enddate and confirmation number");
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		// Need inputs for Supplier site link - Add steps to login to supplier website
		
		//logger.info("**** find reservation with startdate,enddate and confirmation number");
		pgWrapper.genericClass.airlinesLoginAndNavigateToTrainingReservation(envoyUsername, envoyPassword);
		pgWrapper.genericClass.findReservation(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "TC:08 - Verify the OPS Team able to confirm the training room which was created through 'Send to vendor website' option.", dataProvider = "TestDataFile", groups = "Regression")
	public void confirmTrainingViaSendToVendor(String destinationValue, String timeFormatValue, String arrivaltimeValue, String departuretimeValue, 
			String arrivalFlightCodeValue, String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String tenantName, String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode,
			String expectedValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("username"), readPropValue("password"));

		// Unable to click on vendor website Manual in QA and dev env
		saveButtonType = "SendToVendorWebsite";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Verifying the created reservation in ACES Application");
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(readPropValue("username"), readPropValue("password"), tenantName, readPropValue("bidMonthIndex"));
		pgWrapper.genericClass.clickOnPendingConfirmationLinkUnderOPSDesk();
		pgWrapper.pagePendingConfirmations.bookPendingConfirmation(reservId, confirmationCode);
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("*** Verifying the Booked reservation in ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToTrainingReservation(readPropValue("username"), readPropValue("password"));
		pgWrapper.genericClass.findReservation(reservId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}

	@Test(description = "TC:09 - Verify the Airline user able to cancel the training room and checked the status as Cancelled", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelTrainingRoom(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String userName, String password, String tenantName,
			String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode,
			String expectedValue, String notesValue, String cancelValue,String confirmationValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		/*pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
	
		saveButtonType = "GenerateFax";
		reservId = pgWrapper.genericClass.createTrainingReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType,
				saveButtonType);
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Verifying the created reservation in ACES Application");
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(userName, password, tenantName, bidMonthIndex);
		pgWrapper.genericClass.clickOnPendingConfirmationLinkUnderOPSDesk();
		pgWrapper.pagePendingConfirmations.bookPendingConfirmation("936682", confirmationCode);
		pgWrapper.pageHome.clickLogoutLink();*/

		//logger.info("*** Verifying the Booked reservation in ACESAIR Application");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnTrainingReservations();
		pgWrapper.genericClass.findReservation("936682", expectedValue);
		pgWrapper.findReservationBTPage.cancelLinkGenerateFax(notesValue);

		//logger.info("*** Verifying the Pending cancellation reservation in ACESAIR Application");
		expected = "Pending Cancellation Confirmation";
		pgWrapper.findReservationBTPage.verifyReservationExists("936682", expected);
		pgWrapper.findReservationBTPage.confirmLinkCancellationValue(cancelValue,confirmationValue, notesValue);

		//logger.info("**** Verifying the cancelled status");
		expected = "Cancelled";
		pgWrapper.findReservationBTPage.verifyReservationExists("936682", expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}
	
	
}
