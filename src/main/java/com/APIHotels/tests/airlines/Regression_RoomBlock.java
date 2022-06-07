package com.APIHotels.tests.airlines;

import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class Regression_RoomBlock extends LocalDriverManager {

	public PgWrapper pgWrapper;
	String roomsToCancel;
	int rowCount;
	String adhocReservation;
	String hotelName ;
	String reservId ;
	String reservationHotelProviderName;
	int availableReservationRowCount;
	int assignmentAlertRowCount;
	int availRoomCountValue;
	int statusPC;
	int cancellationAlertRowCount;
	int reservationRowCount;
	int triprowCount;
	String expected;
	int expectedRowSize;
	String noOfRooms;
	
	////@Test(description = "TC:ADHOC_Booking_001 - Verify the tab index of Room Block page for Endeavor Airlines", dataProvider = "TestDataFile", groups = "Regression")
	public void verifyTabForRoomBlock(String endeavorUsername, String endeavorPassword) {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("**** Verify Tab functionality for Request room block under Operations");
		pgWrapper.operationsTab.clickOnRequestRoomBlockUnderBT();
		pgWrapper.requestRoomBlockPage.verifyTabFunctionality();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}

	////@Test(description = "TC:ADHOC_Booking_002 && TC:ADHOC_Booking_003 - Verify the Endeavor Airlines user able to block a room with ADHOC type as 'Hard' And 'Soft'", dataProvider = "TestDataFile", groups = "Regression")
	public void bookHardAndSoftRoomBlock(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue,String testScenario) {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("**** Book Room Block With Adhoc Hard Type");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "TC:ADHOC_Booking_004 && TC:ADHOC_Booking_005 - Verify the Endeavor Airlines user able to do partial & All room cancelling before booking", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelRoomBeforeBooking(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue,String cancelRoomValue, String cancelNotesValue,String testScenario) {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("**** Book Room Block With Adhoc Soft Type");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		
		//logger.info("Find the room block by entering hotel location , Startdate and enddate under FindOrEdit Room block page");
		rowCount=pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.genericClass.clickOnCancelLinkAndPerformActions(rowCount, roomCountValue, cancelRoomValue, cancelNotesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	//@Test(description = "TC:ADHOC_Booking_006 && TC:ADHOC_Booking_007 - Verify the Endeavor Airlines user able to do partial & All room cancelling after booking", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelRoom(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String hotelNotesValue,
			String rateValue, String isTaxIncluded, String billingMethodValue, String cancelRoomValue,
			String cancelNotesValue, String testScenario) throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("**** Book Room Block With Adhoc Soft Type");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		//logger.info("Find the room block by entering hotel location , Startdate and enddate under Find/edit room block requests");
		rowCount=pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("Click on room block request reservation link ,select hotel, payment method and confirmaton code ");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		pgWrapper.genericClass.clickAdhocReservationLinkAndBookTripWithConfirmationNumber(adhocType, roomCountValue, locationValue,
				checkInDateValue, confirmationCode, hotelNotesValue, rateValue, isTaxIncluded, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("Login to ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		//logger.info("Click on find or edit room block requests page and partially cancel room ");
		pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.genericClass.clickOnCancelLinkAndPerformActions(rowCount, roomCountValue, cancelRoomValue, cancelNotesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "TC:ADHOC_Booking_008 && TC:ADHOC_Booking_009 - Verify the Endeavor Airlines user able to view all room booking related to the Hotel Location or in the given date range", dataProvider = "TestDataFile", groups = "Regression")
	public void viewAllRoomsViaHotelOrDateRange(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue,String hotelLocation, String testScenario) {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);
		
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		//logger.info("Click on find or edit room block requests page and partially cancel room ");
		pgWrapper.genericClass.findOrEditRoomBlockRequest(hotelLocation, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "TC:ADHOC_Booking_010 - Verify the Endeavor Airlines user able to view the Supplier detail for the rooms they have blocked.", dataProvider = "TestDataFile", groups = "Regression")
	public void viewSupplierDetail(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String hotelNotesValue,
			String rateValue, String isTaxIncluded, String billingMethodValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("**** Book Room Block With Adhoc Soft Type");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		//logger.info("**** Click on Find/Edit room block requests");
		pgWrapper.operationsTab.clickOnFindOrEditRoomBlockRequestsLink();

		//logger.info("Find the room block by entering hotel location , Startdate and enddate");
		pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("Click on room block request reservation link ,select hotel, payment method and confirmaton code ");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.genericClass.clickAdhocReservationLinkAndBookTripWithConfirmationNumber(adhocType, roomCountValue, locationValue,
				checkInDateValue, confirmationCode, hotelNotesValue, rateValue, isTaxIncluded, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("Login to ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);

		//logger.info("Click on find or edit room block requests page and partially cancel room ");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnSupplierNameAndValidate(rowCount);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "TC:ADHOC_Booking_011 - Verify the Endeavor Airlines user able to view all reservation notes for the rooms they have blocked.", dataProvider = "TestDataFile", groups = "Regression")
	public void viewNotes(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String hotelNotesValue,
			String rateValue, String isTaxIncluded, String billingMethodValue) throws Exception {
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("**** Book Room Block With Adhoc Soft Type");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		//logger.info("Find the room block by entering hotel location , Startdate and enddate under Find/Edit room block requests");
		pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("Click on room block request reservation link ,select hotel, payment method and confirmaton code ");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.genericClass.clickAdhocReservationLinkAndBookTripWithConfirmationNumber(adhocType, roomCountValue, locationValue,
				checkInDateValue, confirmationCode, hotelNotesValue, rateValue, isTaxIncluded, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();

		//logger.info("Login to ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		//logger.info("Click on find or edit room block requests page and partially cancel room ");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
		// Notes Validation - needs input what data to validate
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnNotesLink(rowCount, notesValue, roomCountValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "TC:ADHOC_Booking_012 - Verify the OPS Team able to use the blocked room  for the BT Reservation created by Endeavor airlines.", dataProvider = "TestDataFile", groups = "Regression")
	public void createBTForBlockedRooms(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String hotelNotesValue,
			String rateValue, String isTaxIncluded, String billingMethodValue, String destinationValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,
			String reasonCodeValue, String roomsUsedValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("****Precondition: Book Room Block With Adhoc Soft/Hard Type and block supplier");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		
		// logger.info("Find the room block by entering hotel location , Startdate and enddate under Find/Edit room block requests");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("**** Login to ACES application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		hotelName = pgWrapper.genericClass.clickAdhocReservationLinkAndBookTripWithConfirmationNumber(adhocType, roomCountValue, locationValue,
				checkInDateValue, confirmationCode, hotelNotesValue, rateValue, isTaxIncluded, billingMethodValue);
		System.out.println("hotelName : "+hotelName);
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("Login to ACESAIR Application");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		
		//logger.info("**** Create a request reservation under BT");
		reservId = pgWrapper.genericClass.createRequestReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, singleRoomType, doubleRoomType, reasonCodeValue);
		System.out.println("reservId: "+reservId);
		
		//logger.info("**** Click on find reservation link , Enter startdate , enddate , reservation id and click on retrieve button");
		expected = "Pending Assignment";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("**** login to ACES application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		reservationHotelProviderName = pgWrapper.genericClass.clickOnAssignmentLinkAndGetHotelName(reservId);
		pgWrapper.genericClass.enterConfirmationDetailsAndBookReservation(checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, hotelName, "", confirmationCode, hotelNotesValue,
				billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** Click on find reservation link , Enter startdate , enddate , reservation id and click on retrieve button");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		expected = "Booked";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.findReservationBTPage.verifySupplierNameIncluded(hotelName);

		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		System.out.println("rowCount :"+rowCount);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyRoomBlocks(rowCount, roomCountValue, roomsUsedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "TC:ADHOC_Booking_013 - Verify that the OPS Team able to ignore the blocked room before booking.", dataProvider = "TestDataFile", groups = "Regression")
	public void ignoreBlockedRoomsBeforeBooking(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue,String reason,String expectedValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		// logger.info("****Precondition: Book Room Block With Adhoc Soft/Hard Type and block supplier");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		// logger.info("**** Click on Find/Edit room block requests and Find the  room block by entering hotel location , Startdate and enddate");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
		System.out.println("rowCount:" + rowCount);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// logger.info("**** Login to ACES application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(adhocReservation);
		noOfRooms =  "S" + roomCountValue;
		assignmentAlertRowCount  = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(noOfRooms, locationValue, checkInDateValue);
		pgWrapper.pageDashBoard.clickOnIgnoreLink(assignmentAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyStatus(rowCount, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	@Test(description = "TC:ADHOC_Booking_014 && TC:ADHOC_Booking_015 - Verify the OPS Team able to use the blocked multiple rooms with cancellation of Room Request && Cancellation of Room request before Booking.", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelMultipleRooms(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String expectedValue, String userName, String password, String tenantName,
			String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode,
			String hotelNotesValue, String rateValue, String isTaxIncluded, String billingMethodValue,
			String cancelRoomValue, String cancelNotesValue, String roomsUsedValue, String destinationValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,
			String reasonCodeValue,String availableRooms,String testScenario) throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		// logger.info("****Precondition: Book Room Block With Adhoc Soft/Hard Type ");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		// logger.info("**** Click on Find/Edit room block requests and Find the room block by entering hotel location , Startdate and enddate");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyStatus(rowCount, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("**** Login to ACES application and confirm room block requests");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		hotelName = pgWrapper.genericClass.clickAdhocReservationLinkAndBookTripWithConfirmationNumber(adhocType, roomCountValue, locationValue,
				checkInDateValue, confirmationCode, hotelNotesValue, rateValue, isTaxIncluded, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** login to ACESAIR application and click on find/edit room block requests");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		
		//logger.info("Find the room block by entering hotel location , Startdate ,enddate under find/edit room block requests");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnCancelLink(rowCount);
		roomsToCancel=pgWrapper.findOrEditRoomBlockRequestsPage.cancelRoomBlock(roomCountValue,cancelRoomValue,cancelNotesValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnOkBtn();
		availRoomCountValue =pgWrapper.findOrEditRoomBlockRequestsPage.verifyCancelledRowStatus(rowCount, roomCountValue, roomsToCancel);
		
		//logger.info("**** Create a request reservation under BT with 8 single room types");
		pgWrapper.operationsTab.clickOnRequestReservationLink();
		reservId = pgWrapper.genericClass.createRequestReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, singleRoomType, doubleRoomType, reasonCodeValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("**** login to ACES application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		reservationHotelProviderName = pgWrapper.genericClass.clickOnAssignmentLinkAndGetHotelName(reservId);
		System.out.println("reservationHotelProviderName :" + reservationHotelProviderName);
		//logger.info("2 entries are created with PC and Booked status accordingly");
		statusPC =  pgWrapper.pagePendingConfirmations.getHotelProviderRow(checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomsToCancel, hotelName);
		expected = "PC";
		pgWrapper.pagePendingConfirmations.verifyStatus(statusPC, expected);
		
		expected = "Booked";
		pgWrapper.genericClass.enterConfirmationDetailsAndBookReservation(checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue,  Integer.toString(availRoomCountValue), hotelName, expected, confirmationCode, hotelNotesValue,
				billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** Click on find reservation link , Enter startdate , enddate , reservation id and click on retrieve button");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		System.out.println("rowCount :" + rowCount);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyUsedRoomBlock(rowCount, roomCountValue, roomsToCancel, availableRooms);
		System.out.println("roomsToCancel : " +roomsToCancel);
		System.out.println("availableRooms : " +availableRooms);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description="TC:ADHOC_Booking_016 - Ignoring Pending cancellation Room requests", dataProvider = "TestDataFile", groups = "Regression")
	public void ignorePendingCancellation(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String expectedValue, String userName, String password, String tenantName,
			String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode,
			String hotelNotesValue, String rateValue, String isTaxIncluded, String billingMethodValue,
			String cancelRoomValue, String cancelNotesValue, String reason, String serviceType, String expectedResult)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		// logger.info("****Precondition: Book Room Block With Adhoc Soft/Hard Type ");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		// logger.info("**** Click on Find/Edit room block requests and Find the room block by entering hotel location , Startdate and enddate");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		System.out.println("rowCount:" + rowCount);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyStatus(rowCount, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// logger.info("**** Login to ACES application and confirm room block requests");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		hotelName = pgWrapper.genericClass.clickAdhocReservationLinkAndBookTripWithConfirmationNumber(adhocType, roomCountValue, locationValue,
				checkInDateValue, confirmationCode, hotelNotesValue, rateValue, isTaxIncluded, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();

		// logger.info("Find the room block by entering hotel location , Startdate ,enddate and cancel room under findoredit room block page");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		roomsToCancel = pgWrapper.genericClass.clickOnCancelLinkAndPerformActions(rowCount, roomCountValue, cancelRoomValue, cancelNotesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// logger.info("**** Login to ACES application and confirm room block requests");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(adhocReservation);
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts(roomCountValue,
				locationValue, checkInDateValue);
		pgWrapper.pageDashBoard.clickOnCancellationIgnoreLink(cancellationAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		expectedRowSize = 0;
		pgWrapper.pageDashBoard.verifyCancellationAlert(expectedRowSize);

		pgWrapper.genericClass.findReservationUnderReservationsAndVerifyStatus(serviceType, locationValue, checkInDateValue, adhocType,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, hotelName, expectedResult);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		// logger.info("Find the room block by entering hotel location ,Startdate ,enddate and cancel room");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyCancelledRowStatus(rowCount, roomCountValue, roomsToCancel);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}

	//@Test(description = "TC:ADHOC_Booking_017 - Room Book by OPS Reservation - Endeavor Airline", dataProvider = "TestDataFile", groups = "Regression")
	public void bookRoomWithOpsReservation(String userName, String password, String tenantName, String bidMonthIndex,
			String cityValue, String supplierValue, String adhocType, String startDateValue, String endDateValue,
			String roomsValue, String startTime, String endTime, String rateValue, String reasonCodeValue,
			String confirmationNumber, String notesValue, String billingValue, String vendorComments,
			String endeavorUsername, String endeavorPassword, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,String reason,
			String timeFrameFilterValue, String refreshIntervalValue,String roomsUsed) throws Exception {

		// logger.info("**** Login to ACES application ");
		getDriver().get("http://10.10.103.152:3005/ACES/");
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));

		//logger.info("**** click on add hotel reservation link and add a hotel reservation from OPS desk");
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageAddHotelReservation.clickAddHotelReservationLink();
		pgWrapper.pageAddHotelReservation.addHotelReservation(cityValue, supplierValue, adhocType, startDateValue,
				endDateValue, roomsValue, startTime, endTime, rateValue, reasonCodeValue);// HARD ,IROP
		pgWrapper.pageAddHotelReservationInformation.addHotelReservationInformation(confirmationNumber, notesValue,
				billingValue, vendorComments);
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** Create a request reservation under BT");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		reservId = pgWrapper.genericClass.createRequestReservation(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, singleRoomType, doubleRoomType, reason);
		expected = "Pending Assignment";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("**** login to ACES application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		reservationHotelProviderName = pgWrapper.genericClass.clickOnAssignmentLinkAndGetHotelName(reservId);
		System.out.println("reservationHotelProviderName :" + reservationHotelProviderName);
		
		pgWrapper.genericClass.enterConfirmationDetailsAndBookReservation(startDateValue, startTime, endDateValue, endTime, roomsValue,
				supplierValue, "", confirmationNumber, notesValue, billingValue);
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** Click on find reservation link , Enter startdate , enddate , reservation id and click on retrieve button");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		expected = "Booked";
		pgWrapper.genericClass.findReservation(reservId, expected);
		pgWrapper.findReservationBTPage.verifySupplierNameIncluded(supplierValue);
		
		// logger.info("Find the room block by entering hotel location ,Startdate and enddate under Find/Edit room block requests");
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(cityValue, adhocType, startDateValue, startTime, endDateValue, endTime, roomsValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyRoomBlocks(rowCount, roomsValue, roomsUsed);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	//@Test(description = "TC:ADHOC_Booking_018 - Pending Assignment Confirmation - Endeavor Airlines", dataProvider = "TestDataFile", groups = "Regression")
	public void pendingAssignmentConfirmation(String endeavorUsername, String endeavorPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue,
			String rateValue, String isTaxIncluded, String billingMethodValue,String serviceType,String confirmationCode) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("****Precondition: Book Room Block With Adhoc Soft/Hard Type and block supplier");
		pgWrapper.genericClass.createRoomBlock(locationValue, timeFormatValue, checkInDateValue, checkInTimeValue, checkOutDateValue,
				checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);

		//logger.info("**** Login to ACES application");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue,
				timeFormatValue, refreshIntervalValue);
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(adhocReservation);
		noOfRooms =  "S" + roomCountValue;
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(noOfRooms, locationValue,
				checkInDateValue);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		pgWrapper.pageDashBoard.clickYesAlertBtn();
		pgWrapper.pagePendingConfirmations.hotelProvider();
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.selectBillingMethod(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickOnFinishButtonAndReservationALert();

		expected = "Pending Assignment Confirmation";
		pgWrapper.genericClass.findReservationUnderReservationsAndVerifyStatus(serviceType, locationValue, checkInDateValue, adhocType,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, hotelName, expected);

		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		String rooms = "S"+roomCountValue;
		triprowCount = pgWrapper.pagePendingConfirmations.bookPendingConfirmation(adhocReservation, checkInDateValue,
				checkInTimeValue, locationValue, rooms);
		pgWrapper.pagePendingConfirmations.clickOnTripAndEnterConfirmationCode(triprowCount, confirmationCode);
		expected = "Booked";
		pgWrapper.genericClass.findReservationUnderReservationsAndVerifyStatus(serviceType, locationValue, checkInDateValue, adhocType,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, hotelName, expected);
		pgWrapper.pageHome.clickLogoutLink();
		
	}

}
