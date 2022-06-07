package com.APIHotels.tests.airlines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class Regression_AcesDirect extends LocalDriverManager {

	public PgWrapper pgWrapper;
	String adhocReservation;
	int assignmentAlertRowCount;
	int rowCount;
	int expectedRowSize;
	int reservationRowCount;
	String hotelName ;
	String roomsToCancel;
	int cancellationAlertRowCount;
	String tripReservation;
	String noOfRooms;
	String trip;
	String expected;
	int roomCount;
	String alertMsg;
	String hotelProviderName;
	String currentWindowHandle;
	List<String> list;
	String arrivalDate ;
	String departureDate ;
	String reservId;

	//@Test(description = "Book a ACES DIRECT request with a confirmation number/Individual booking and then cancel.", dataProvider = "TestDataFile", groups = "Regression")
	public void bookRequestWithConfirmationNo(String aircanadaUsername, String aircanadaPassword,
			String departmentValue, String pairingNumber, String locationValue, String arrivalFlightNumberValue,
			String arrivalTimeValue, String departureFlightNumberValue, String departureTimeValue,
			String reportTimeValue, String individualEmpId, String empName, String noOfRooms, String userName,
			String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String roomCountValue, String confirmationCode,
			String toHotelProviderValue, String fromHotelGTProviderValue, String rateValue, String isTaxIncluded,
			String billingMethodValue, String serviceType, String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S"+roomCountValue, locationValue,
				arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue,fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, departmentValue, pairingNumber, locationValue, arrivalDate,
				departureDate, arrivalTimeValue, departureTimeValue, hotelName, expectedResult);
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.cancelCrewMembers(rowCount, employeeIds.get(0), empNames.get(0));
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0), employeeIds.get(0), expected);
		System.out.println(employeeIds.get(1));
		System.out.println(empNames.get(1));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(1), employeeIds.get(1), expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		List<String> rooms = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S"+rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		expected = "Pending Cancellation Confirmation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0), employeeIds.get(0), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber,serviceType.toUpperCase(), locationValue,
				"S" + rooms.get(0), hotelName);
		pgWrapper.pagePendingConfirmations.clickOnTripAndEnterConfirmationCode(rowCount, confirmationCode);
		
		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber,"GT", locationValue,
				rooms.get(0), fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.bookPendingConfirmationCancelledStatus(rowCount,confirmationCode);
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		expected = "Cancelled";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0), employeeIds.get(1), expected);
		
		pgWrapper.acesDirectFindReservationPage.findReservation("Ground", departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue,empNames.get(0), employeeIds.get(1), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}
	
	//@Test(description = "Unconfirmed BT can be cancelled by the airline user .", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelUnConfirmedBT(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValue, String toHotelProviderValue,
			String fromHotelGTProviderValue, String rateValue, String isTaxIncluded, String billingMethodValue,
			String serviceType, String confirmationCode, String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();

		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM
		// COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Pending Assignment Confirmation";
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, departmentValue, pairingNumber, locationValue, arrivalDate,
				departureDate, arrivalTimeValue, departureTimeValue, hotelName, expected);
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.cancelCrewMembers(rowCount, employeeIds.get(0), empNames.get(0));
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(1),
				employeeIds.get(1), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		List<String> rooms = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.enterConfirmationNumber(confirmationCode);
		alertMsg = "Reservation not confirmed by the Supplier";
		pgWrapper.pageCancelReservation.clickFinishBtn(alertMsg);

		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber, serviceType.toUpperCase(),
				locationValue, "S" + roomCount, hotelName);
		pgWrapper.pagePendingConfirmations.clickOnTripAndEnterConfirmationCode(rowCount, confirmationCode);

		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.enterConfirmationNumber(confirmationCode);
		pgWrapper.pageCancelReservation.clickFinishBtn();

		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation(serviceType, pairingNumber);
		reservationRowCount = pgWrapper.pageFindReservation.verifyReservation(departmentValue, empNames.get(0),
				employeeIds.get(0), hotelName, arrivalDate, arrivalTimeValue);
		pgWrapper.pageFindReservation.verifyReservationStatus(reservationRowCount, expectedResult);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0),
				employeeIds.get(0), expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "Unconfirmed ACES Direct can be ‘ignore’ by ACES after it was cancel by the airline user", dataProvider = "TestDataFile", groups = "Regression")
	public void ignoreUnconfirmedBT(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValue, String toHotelProviderValue,
			String fromHotelGTProviderValue, String rateValue, String isTaxIncluded, String billingMethodValue,
			String serviceType, String confirmationCode, String reason, String expectedResult) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM
		// COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Pending Assignment Confirmation";
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, departmentValue, pairingNumber, locationValue, arrivalDate,
				departureDate, arrivalTimeValue, departureTimeValue, hotelName, expected);
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.cancelCrewMembers(rowCount, employeeIds.get(0), empNames.get(0));
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(1),
				employeeIds.get(1), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		List<String> rooms = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.enterConfirmationNumber(confirmationCode);
		alertMsg = "Reservation not confirmed by the Supplier";
		pgWrapper.pageCancelReservation.clickFinishBtn(alertMsg);

		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber, serviceType.toUpperCase(),
				locationValue, "S" + roomCount, hotelName);
		pgWrapper.pagePendingConfirmations.clickOnTripAndEnterConfirmationCode(rowCount, confirmationCode);

		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationIgnoreLink(cancellationAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();

		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation(serviceType, pairingNumber);
		reservationRowCount = pgWrapper.pageFindReservation.verifyReservation(departmentValue, empNames.get(0),
				employeeIds.get(0), hotelName, arrivalDate, arrivalTimeValue);
		pgWrapper.pageFindReservation.verifyReservationStatus(reservationRowCount, expectedResult);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0),
				employeeIds.get(0), expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "Unconfirmed ACES DIRECT can be edited by the airline user.", dataProvider = "TestDataFile", groups = "Regression")
	public void editUnconfirmedBT(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValue, String toHotelProviderValue,
			String fromHotelGTProviderValue, String rateValue, String isTaxIncluded, String billingMethodValue,
			String serviceType, String empId, String employeeName, String confirmationCode) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM
		// COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Pending Assignment Confirmation";
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, departmentValue, pairingNumber, locationValue, arrivalDate,
				departureDate, arrivalTimeValue, departureTimeValue, hotelName, expected);
		pgWrapper.acesDirectFindReservationPage.editReservation(rowCount, empId, employeeName);
		expected = "Pending Assignment";
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, employeeName, empId,
				expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM
		// COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();

		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber, serviceType.toUpperCase(),
				locationValue, "S" + roomCountValue, hotelName);
		pgWrapper.pagePendingConfirmations.clickOnTripAndEnterConfirmationCode(rowCount, confirmationCode);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		List<String> individualEmpid = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> individualEmpName = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, individualEmpName.get(0),
				individualEmpid.get(0), expected);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, employeeName, empId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	@Test(description = "Confirmed ACES DIRECT can be edited by the airline user.", dataProvider = "TestDataFile", groups = "Regression")
	public void editConfirmedBT(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValue, String toHotelProviderValue,
			String fromHotelGTProviderValue, String confirmationCode, String rateValue, String isTaxIncluded,
			String billingMethodValue, String serviceType, String empId, String employeeName) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, departmentValue, pairingNumber, locationValue, arrivalDate,
				departureDate, arrivalTimeValue, departureTimeValue, hotelName, expected);
		pgWrapper.acesDirectFindReservationPage.editReservation(rowCount, empId, employeeName);
		expected = "Pending Assignment";
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, employeeName, empId,
				expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM
		// COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		List<String> individualEmpid = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> individualEmpName = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, individualEmpName.get(0),
				individualEmpid.get(0), expected);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, employeeName, empId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "If the PA is booked with a different supplier, a PC is created for the original reservation and a new booking fax sent to the supplier.", dataProvider = "TestDataFile", groups = "Regression")
	public void reservationDifferentSuppliers(String aircanadaUsername, String aircanadaPassword,
			String departmentValue, String pairingNumber, String locationValue, String arrivalFlightNumberValue,
			String arrivalTimeValue, String departureFlightNumberValue, String departureTimeValue,
			String reportTimeValue, String individualEmpId, String empName, String noOfRooms, String userName,
			String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String roomCountValue, String toHotelProviderValue,
			String fromHotelGTProviderValue, String confirmationCode, String rateValue, String isTaxIncluded,
			String billingMethodValue, String serviceType, String empId, String employeeName, String roomCount)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage.selectHotel();
		System.out.println("hotelName:" + hotelName);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.selectHotelProvider();
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.clickOnLayoverLengthAlert();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, departmentValue, pairingNumber, locationValue, arrivalDate,
				departureDate, arrivalTimeValue, departureTimeValue, hotelName, expected);
		pgWrapper.acesDirectFindReservationPage.editReservation(rowCount, empId, employeeName);
		expected = "Pending Assignment";
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, employeeName, empId,
				expected);
		System.out.println(rowCount);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCount,
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelProvider();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.clickOkBtn();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelProviderName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelProviderName :" + hotelProviderName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, employeeName, empId,
				expected);
		List<String> individualEmpid = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> individualEmpName = new ArrayList<>(Arrays.asList(empName.split(",")));
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue,
				individualEmpName.get(0), individualEmpid.get(0), expected);
		System.out.println(rowCount);
		currentWindowHandle = pgWrapper.acesDirectFindReservationPage.clickOnEditLink(rowCount);
		pgWrapper.acesDirectFindReservationPage.cancelEmployee(currentWindowHandle, individualEmpid.get(0),
				individualEmpName.get(0));
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, individualEmpName.get(0),
				individualEmpid.get(0), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// TWO RECORDS ARE GETTING CREATED , PC AND PA FOR THE SAME CREW-MEMBER
		// WHICH WAS SELECTED UNDER EDIT LINK
		// UNABLE TO VIEW THE PAIRING UNDER PC
		// NEED TO VERIFY THE BELOW CODE
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		List<String> rooms = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.enterConfirmationNumber(confirmationCode);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, individualEmpName.get(0),
				individualEmpid.get(0), expected);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, individualEmpName.get(1),
				individualEmpid.get(1), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "If the PA or PC is ignored. ", dataProvider = "TestDataFile", groups = "Regression")
	public void createReservationIgnorePAPC(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValue, String toHotelProviderValue,
			String fromHotelGTProviderValue, String confirmationCode, String rateValue, String isTaxIncluded,
			String billingMethodValue,String serviceType,String roomCount,String reason) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		// roomcount value is dependent on no of passengers count / NO OF ROOM COUNT
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empName, individualEmpId,
				expected);
		pgWrapper.acesDirectFindReservationPage.cancelSingleCrew(rowCount);
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empName, individualEmpId, expected);
		expected = "Booked";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empName, individualEmpId, expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + roomCount,
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationIgnoreLink(cancellationAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);		// When the Pending cancellation is ignored from the ACES application the status is still pending cancellation , but the expected status should be booked
		// Need to add more steps below for the status verification
		expected = "Booked";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empName, individualEmpId,
				expected);		
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "Booking request can be ignored by ACES", dataProvider = "TestDataFile", groups = "Regression")
	public void editOrCancelBookedReservation(String aircanadaUsername, String aircanadaPassword,
			String departmentValue, String pairingNumber, String locationValue, String arrivalFlightNumberValue,
			String arrivalTimeValue, String departureFlightNumberValue, String departureTimeValue,
			String reportTimeValue, String individualEmpId, String empName, String noOfRooms, String userName,
			String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String roomCountValue, String reason,
			String serviceType) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnIgnoreLink(assignmentAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();

		// NEED MORE INPUTS ON THE STEPS
		// When a PA is ignored then the status wil be changed to IGNORED , but according to the steps the reservation shouldn't exist.
		//While  creating a request reservation under ACES Direct , there will be no SINGLE or DOUBLE room type. It has Individual and group booking -- Want to confirm whether the individual and group bookings are same as the single and the double room types.

		// INCOMPLETE
	}
	
	//@Test(description = "One of the multiple room unconfirmed booking can be edited or cancelled by airline user", dataProvider = "TestDataFile", groups = "Regression")
	public void unconfirmedBTEdited(String endeavorUsername, String endeavorPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,
			String reasonCodeValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String overrideRate, String billingValue,
			String statusAfterReservConfirmation,String empValue,String statusAfterRoomEdit,String reason,String statusAfterIgnore)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);

		//logger.info("*** Create a multiple crew member reservation request from the Airline application ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.genericClass.createRequestReservation(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue, hotel, singleRoomType, doubleRoomType,
				reasonCodeValue);
		System.out.println("reservId: " + reservId);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Process the reservation from ACES without a confirmation by changing the supplier ***");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		hotelName = pgWrapper.pagePendingConfirmations.hotelProviderName();
		pgWrapper.pagePendingConfirmations.changeHotelProvider(hotelName,overrideRate, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickOkBtn();
		pgWrapper.pagePendingConfirmations.clickOkBtn();
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("*** On the Airline application, the status should change to ‘PAC’ for both single and double rooms. ***");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterReservConfirmation);
		
		//logger.info("*** Edit the double room on the airline application by changing to a ‘single’ room. ***");
		pgWrapper.findReservationBTPage.processEdit_RoomTypeChange(empValue);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		
		//logger.info("*** The status should now change to (Pending-Revision) PA for the part of the reservation that changed. ***");
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterRoomEdit);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("****  In ACES, the reservation should be in PA. ***");
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		
		//logger.info("**** ‘Ignore’ the part of the reservation from ACES which was modified by the Airline application. ***");
		pgWrapper.pageDashBoard.clickOnIgnoreLink();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();
		
		//logger.info("**** The part of the reservation which was modified should now show as “(Original)PAC.");
		pgWrapper.genericClass.airlinesLoginAndNavigateToBT(endeavorUsername, endeavorPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterIgnore);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "ACES DIRECT request for single, double or multiple rooms", dataProvider = "TestDataFile", groups = "Regression")
	public void createGroupBooking(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms,String serviceType,String expectedResult) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		arrivalDate = list.get(0);
		departureDate = list.get(1);
		System.out.println("arrivalDate ::::::" + arrivalDate);
		System.out.println("departureDate ::::::" + departureDate);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();
		
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empName, individualEmpId,
				expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	@Test(description = "Airline can cancel ACES DIRECT for multiple crewmembers, on creation && Airline can create and cancel ACES DIRECT for multiple crewmembers, on creation ", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelBooking(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms, String serviceType, String expectedResult,
			String userName, String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.operationsTab.clickOnAcesDirectLink();
		list = pgWrapper.acesDirectRequestReservationPage.requestReservation(departmentValue, pairingNumber,
				locationValue, arrivalFlightNumberValue, arrivalTimeValue, departureFlightNumberValue,
				departureTimeValue, reportTimeValue);
		hotelName = pgWrapper.acesDirectRequestReservationPage.selectHotel();
		System.out.println("hotelName:" + hotelName);
		pgWrapper.acesDirectRequestReservationPage
				.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(individualEmpId,
						departmentValue, empName, noOfRooms);
		pgWrapper.acesDirectRequestReservationPage.clickOnSaveReservation();
		pgWrapper.acesDirectRequestReservationPage.processingRequest();

		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empName,
				individualEmpId, expectedResult);
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.cancelCrewMembers(rowCount, employeeIds.get(0), empNames.get(0));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(1),
				employeeIds.get(1), expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation(serviceType, pairingNumber);
		reservationRowCount = pgWrapper.pageFindReservation.verifyReservation(departmentValue, empNames.get(1),
				employeeIds.get(1), hotelName, arrivalDate, arrivalTimeValue);
		pgWrapper.pageFindReservation.verifyReservationStatus(reservationRowCount, expectedResult);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(1),
				employeeIds.get(1), expectedResult);
		pgWrapper.acesDirectFindReservationPage.cancelCrewMembers(rowCount, employeeIds.get(1), empNames.get(1));
		pgWrapper.acesDirectFindReservationPage.verifyReservationDoesntExist();
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

	}
	
	//@Test(description = "Multiple rooms(Soft AdHoc Type) can be ‘ignore’ from ACES", dataProvider = "TestDataFile", groups = "Regression")
	public void ignoreRoomBlock(String aircanadaUsername, String aircanadaPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String reason, String expectedValue,
			String serviceType) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.genericClass.createRoomBlockUnderAcesDirect(locationValue, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(adhocReservation);
		noOfRooms =  "S" + roomCountValue;
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(noOfRooms, locationValue,
				checkInDateValue);
		pgWrapper.pageDashBoard.clickOnIgnoreLink(assignmentAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		expectedRowSize = 0;
		pgWrapper.pageDashBoard.verifyAssignmentAlertRow(expectedRowSize);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get("http://10.10.103.152:3080/ACESAIR/");
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnAcesDirectLink();
		pgWrapper.operationsTab.clickOnFindOrEditRoomBlockRequestsLink();
		pgWrapper.findOrEditRoomBlockRequestsPage.findOrEditRoomBlockRequest(locationValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickRetrieveBtn();
		rowCount = pgWrapper.findOrEditRoomBlockRequestsPage.verifyAdhocBooking(adhocType, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		System.out.println("rowCount :" + rowCount);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyStatus(rowCount, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get("http://10.10.103.152:3005/ACES/");
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.enterLocationStartDateToFindReservation(serviceType, locationValue,
				checkInDateValue);
		adhocReservation = "Adhoc-" + adhocType;
		hotelName = "Ignored";
		reservationRowCount = pgWrapper.pageFindReservation.verifyReservationExists(adhocReservation, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, hotelName);
		pgWrapper.pageFindReservation.verifyReservationStatus(reservationRowCount, expectedValue);
		pgWrapper.pageHome.clickLogoutLink();

	}

	//@Test(description = " Airline user can request multiple rooms to be booked and it can be ‘ignore’ from ACES.", dataProvider = "TestDataFile", groups = "Regression")
	public void bookedRoomsToBeIgnored(String aircanadaUsername, String aircanadaPassword, String locationValue,
			String timeFormatValue, String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String reasonCountValue, String adhocType,
			String notesValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String rateValue,
			String isTaxIncluded, String billingMethodValue, String cancelRoomValue, String cancelNotesValue,
			String reason, String expectedValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.genericClass.createRoomBlockUnderAcesDirect(locationValue, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(adhocReservation);
		noOfRooms =  "S" + roomCountValue;
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(noOfRooms, locationValue,
				checkInDateValue);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		pgWrapper.pageDashBoard.clickYesAlertBtn();
		pgWrapper.pagePendingConfirmations.hotelProvider();
		pgWrapper.pagePendingConfirmations.enterCancellationCode(confirmationCode, notesValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		// ADD LAYOVER HANDLER
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.selectBillingMethod(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnCancelLink(rowCount);
		roomsToCancel = pgWrapper.findOrEditRoomBlockRequestsPage.cancelRoomBlock(roomCountValue, cancelRoomValue,
				cancelNotesValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickCancelOkBtnAircanada();
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyCancelledRowStatus(rowCount, roomCountValue, roomsToCancel);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(adhocReservation);
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts(cancelRoomValue,
				locationValue, checkInDateValue);
		pgWrapper.pageDashBoard.clickOnCancellationIgnoreLink(cancellationAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();
		
		// ADD STEPS FOR - Under the ‘Airline’ application, 'Supplier' is shown as 'Ignored'.'Action' tab is seen without any Actions.
	}
	
	//@Test(description = "Booking a ACES DIRECT (Request Limo)", dataProvider = "TestDataFile", groups = "Regression")
	public void createRequestLimoAndCancel(String aircanadaUsername, String aircanadaPassword, String pairingNumber,
			String pickUpLocationValue, String pickUpDetailValue, String pickUpDateValue, String pickUpFlightValue,
			String pickUpTimeValue, String dropOffLocationValue, String dropOffDetailValue, String dropOffFlightValue,
			String dropOffDateValue, String dropOffTimeValue, String individualEmpId, String deptValue, String empName,String passengers,
			String userName, String password, String tenantName, String bidMonthIndex, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String roomCountValue, String gtProviderValue,
			String confirmationCode,String rateValue,String billingMethodValue,String serviceType,String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);
		pgWrapper.genericClass.createRequestLimo(pairingNumber, pickUpLocationValue, pickUpDetailValue, pickUpDateValue, pickUpFlightValue,
				pickUpTimeValue, dropOffLocationValue, dropOffDetailValue, dropOffFlightValue, dropOffDateValue,
				dropOffTimeValue, individualEmpId, deptValue, empName, passengers);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		
		// roomcount value depends on employees 
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		hotelName= pgWrapper.genericClass.verifyAssignmentAndBookReservation(pairingNumber, roomCountValue, pickUpLocationValue, pickUpDateValue,
				gtProviderValue, confirmationCode, rateValue, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		rowCount=pgWrapper.genericClass.findReservationVerifyStatus(serviceType, deptValue, pairingNumber, pickUpLocationValue, pickUpDateValue, dropOffDateValue,
				pickUpTimeValue, dropOffTimeValue, hotelName, expectedResult);
		pgWrapper.acesDirectFindReservationPage.clickOnCancelLink(rowCount);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "Cancelling a ACES DIRECT(Request Limo) ", dataProvider = "TestDataFile", groups = "Regression")
	public void cancelWithOutConfirmation(String aircanadaUsername, String aircanadaPassword, String pairingNumber,
			String pickUpLocationValue, String pickUpDetailValue, String pickUpDateValue, String pickUpFlightValue,
			String pickUpTimeValue, String dropOffLocationValue, String dropOffDetailValue, String dropOffFlightValue,
			String dropOffDateValue, String dropOffTimeValue,String individualEmpId, String deptValue,
			String empName, String passengers,String userName, String password, String tenantName,
			String bidMonthIndex,String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue,String serviceType,String expectedResult) throws Exception{

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.genericClass.createRequestLimo(pairingNumber, pickUpLocationValue, pickUpDetailValue, pickUpDateValue, pickUpFlightValue,
				pickUpTimeValue, dropOffLocationValue, dropOffDetailValue, dropOffFlightValue, dropOffDateValue,
				dropOffTimeValue, individualEmpId, deptValue, empName, passengers);

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		// roomcount value depends on employees
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(passengers,
				pickUpLocationValue, pickUpDateValue);
		if(assignmentAlertRowCount == 1){
			trip = pgWrapper.pageDashBoard.getAssignmentReservationLinkText(assignmentAlertRowCount);
			Assert.assertTrue(trip.contains(pairingNumber), "Trip is not visible under pending assignments");
		}
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		hotelName = "Unassigned";
		rowCount=pgWrapper.genericClass.findReservationVerifyStatus(serviceType, deptValue, pairingNumber, pickUpLocationValue, pickUpDateValue, dropOffDateValue,
				pickUpTimeValue, dropOffTimeValue, hotelName, expectedResult);
		pgWrapper.acesDirectFindReservationPage.clickOnCancelLink(rowCount);
		pgWrapper.acesDirectFindReservationPage.verifyReservation(pickUpDateValue,
				pickUpTimeValue, dropOffDateValue, dropOffTimeValue, hotelName);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}
	
	//@Test(description = "Ignoring a ACES DIRECT(Request Limo) ", dataProvider = "TestDataFile", groups = "Regression")
	public void createRequestLimoCancelIgnore(String aircanadaUsername, String aircanadaPassword,
			String pairingNumber, String pickUpLocationValue, String pickUpDetailValue, String pickUpDateValue,
			String pickUpFlightValue, String pickUpTimeValue, String dropOffLocationValue, String dropOffDetailValue,
			String dropOffFlightValue, String dropOffDateValue, String dropOffTimeValue, String individualEmpId,
			String deptValue, String empName, String passengers, String userName, String password, String tenantName,
			String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String gtProviderValue, String confirmationCode, String rateValue, String billingMethodValue,
			String serviceType, String reason, String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);

		pgWrapper.genericClass.createRequestLimo(pairingNumber, pickUpLocationValue, pickUpDetailValue, pickUpDateValue, pickUpFlightValue,
				pickUpTimeValue, dropOffLocationValue, dropOffDetailValue, dropOffFlightValue, dropOffDateValue,
				dropOffTimeValue, individualEmpId, deptValue, empName, passengers);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		hotelName = pgWrapper.genericClass.verifyAssignmentAndBookReservation(pairingNumber, passengers, pickUpLocationValue, pickUpDateValue,
				gtProviderValue, confirmationCode, rateValue, billingMethodValue);
		pgWrapper.pageHome.clickLogoutLink();

		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		expected = "Booked";
		rowCount = pgWrapper.genericClass.findReservationVerifyStatus(serviceType, deptValue, pairingNumber, pickUpLocationValue,
				pickUpDateValue, dropOffDateValue, pickUpTimeValue, dropOffTimeValue, hotelName, expected);
		pgWrapper.acesDirectFindReservationPage.clickOnCancelLink(rowCount);
		pgWrapper.acesDirectFindReservationPage.verifyReservation(pickUpDateValue, pickUpTimeValue, dropOffDateValue,
				dropOffTimeValue, hotelName);
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyStatus(rowCount, expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts(passengers,
				pickUpLocationValue, pickUpDateValue);
		pgWrapper.pageDashBoard.clickOnCancellationIgnoreLink(cancellationAlertRowCount);
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageHome.clickLogoutLink();
		
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.genericClass.findReservationVerifyStatus(serviceType, deptValue, pairingNumber, pickUpLocationValue,
				pickUpDateValue, dropOffDateValue, pickUpTimeValue, dropOffTimeValue, hotelName, expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		
	}
	
	
}