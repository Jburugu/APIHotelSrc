package com.APIHotels.tests.airlines;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class Regression_ConferenceRoom extends LocalDriverManager {

	public PgWrapper pgWrapper;
	String conferenceReservationId ;
	String expected;
	String currentWindowHandle;

	@Test(description = "TC:ConferenceRoom_001 && TC:ConferenceRoom_002 && TC:ConferenceRoom_003 - Verify that the user is able to make a Conference room request , find and modify and cancel the conference room request and find, confirm and book the conference room request with the status 'Pending Assignment'", dataProvider = "TestDataFile", groups = "Regression")
	public void bookConferenceRoom(String mesaUsername, String mesaPassword, String destinationValue,
			String startDateTimeValue, String endDateTimeValue, String confRoomsNeededValue, String seatingStyleValue,
			String NbrOfAttendeesValue, String hotelDescriptionValue, String notesValue, String userName,
			String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String confirmationCode, String billingValue,
			String conferenceRoomNameValue, String rate, String serviceType, String expectedValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(mesaUsername, mesaPassword);

		conferenceReservationId = pgWrapper.genericClass.createConferenceRoom(destinationValue, startDateTimeValue, endDateTimeValue,
				confRoomsNeededValue, seatingStyleValue, NbrOfAttendeesValue, hotelDescriptionValue, notesValue);

		// logger.info("*** find reservation");
		expected = "Pending Assignment";
		pgWrapper.genericClass.findReservation(conferenceReservationId, expected);
		pgWrapper.findReservationBTPage.verifyCancelAndNotesLink();
		currentWindowHandle = pgWrapper.findReservationBTPage.clickOnNotesLink();
		pgWrapper.findReservationBTPage.verifyConferenceNotes(currentWindowHandle, notesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("**** login to Aces application and select tenant and bidperiod and navigate to OPS dashboard");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.genericClass.clickOnAssignmentLinkAndConfirmWithConfirmationNo(conferenceReservationId, confirmationCode, notesValue, billingValue, conferenceRoomNameValue, rate);
		//logger.info("Find created conference room");
		pgWrapper.genericClass.findReservation_OPSDesk(serviceType, conferenceReservationId, expectedValue);
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("**** Find a reservation in find reservation page ");
		pgWrapper.genericClass.airlinesLoginAndClickOnManualBookingLink(mesaUsername, mesaPassword);
		pgWrapper.genericClass.findReservation(conferenceReservationId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	
	//@Test(description = "TC:ConferenceRoom_004 - Verify that the OPS team is able to ignore the conference room request", dataProvider = "TestDataFile", groups = "Regression")
	public void ignoreConferenceRoom(String mesaUsername, String mesaPassword, String destinationValue,
			String startDateTimeValue, String endDateTimeValue, String confRoomsNeededValue, String seatingStyleValue,
			String NbrOfAttendeesValue, String hotelDescriptionValue, String notesValue, String userName,
			String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String reason, String expectedValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(mesaUsername, mesaPassword);

		// logger.info("**** create a request conference room");
		conferenceReservationId = pgWrapper.genericClass.createConferenceRoom(destinationValue, startDateTimeValue, endDateTimeValue,
				confRoomsNeededValue, seatingStyleValue, NbrOfAttendeesValue, hotelDescriptionValue, notesValue);

		//logger.info("*** find a conference room reservation");
		expected = "Pending Assignment";
		pgWrapper.genericClass.findReservation(conferenceReservationId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Verifying the created conference room in ACES Application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(conferenceReservationId);
		pgWrapper.pageDashBoard.clickOnIgnoreLink();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("**** Find a reservation in find reservation page ");
		pgWrapper.genericClass.airlinesLoginAndClickOnManualBookingLink(mesaUsername, mesaPassword);
		pgWrapper.genericClass.findReservation(conferenceReservationId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}
	
	//@Test(description = "TC:ConferenceRoom_005 - Verify that the OPS team is able to confirm the conference room request with the status 'Pending Assignment Confirmation' ", dataProvider = "TestDataFile", groups = "Regression")
	public void confirmConferenceRoomViaPAC(String mesaUsername, String mesaPassword, String destinationValue,
			String startDateTimeValue, String endDateTimeValue, String confRoomsNeededValue, String seatingStyleValue,
			String NbrOfAttendeesValue, String hotelDescriptionValue, String notesValue, String userName,
			String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String billingValue, String conferenceRoomNameValue,
			String rate, String serviceType, String confirmationCode, String expectedValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(mesaUsername, mesaPassword);

		// logger.info("Precondition - Create a Conference Room Reservation and confirm the reservation request without confirmation number");
		conferenceReservationId = pgWrapper.genericClass.createConferenceRoom(destinationValue, startDateTimeValue, endDateTimeValue,
				confRoomsNeededValue, seatingStyleValue, NbrOfAttendeesValue, hotelDescriptionValue, notesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("**** Login to Aces application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(conferenceReservationId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pageDashBoard.clickYesAlertBtn();
		pgWrapper.pagePendingConfirmations.hotelProvider();
		pgWrapper.pagePendingConfirmations.enterBillingMethod(billingValue);
		pgWrapper.pagePendingConfirmations.enterConferenceRoomName(conferenceRoomNameValue);
		pgWrapper.pagePendingConfirmations.enterOverrideRate(rate);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickOkBtn();
		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservation_OPSDesk(serviceType, conferenceReservationId, expected);

		//logger.info("Click on OPS desk pending confirmation");
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.bookPendingConfirmation(conferenceReservationId, confirmationCode);
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("Find reservation under manual booking ACESAIR");
		pgWrapper.genericClass.airlinesLoginAndClickOnManualBookingLink(mesaUsername, mesaPassword);
		pgWrapper.genericClass.findReservation(conferenceReservationId, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
}
