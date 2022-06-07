package com.APIHotels.tests.airlines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class AirlinesTest extends LocalDriverManager {

	public PgWrapper pgWrapper;
	List<String> list;
	String arrivalDate;
	String departureDate;
	int rowCount;
	String expected;
	String hotelName;

	@Test(description = "create reservation", dataProvider = "TestDataFile", groups = "createReservation1")
	public void createReservation(String aircanadaUsername, String aircanadaPassword, String departmentValue,
			String pairingNumber, String locationValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String departureFlightNumberValue, String departureTimeValue, String reportTimeValue,
			String individualEmpId, String empName, String noOfRooms) {
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
	}

	//@Test(description = "cancel crew members", dataProvider = "TestDataFile", groups = "cancelCrew", dependsOnGroups = "com.APIHotels.tests.airlines.AcesTest.bookReservation" )
	public void cancelCrew(String aircanadaUsername, String aircanadaPassword, String serviceType,
			String departmentValue, String pairingNumber, String locationValue, String arrivalTimeValue,
			String departureTimeValue, String expectedResult, String individualEmpId, String empName) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		rowCount = pgWrapper.acesDirectFindReservationPage.verifyReservation(arrivalDate, arrivalTimeValue,
				departureDate, departureTimeValue, hotelName);
		pgWrapper.acesDirectFindReservationPage.verifyStatus(rowCount, expectedResult);
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.cancelCrewMembers(rowCount, employeeIds.get(0), empNames.get(0));
		expected = "Pending Cancellation";
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0),
				employeeIds.get(0), expected);
		System.out.println(employeeIds.get(1));
		System.out.println(empNames.get(1));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(1),
				employeeIds.get(1), expectedResult);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "verify cancelled status", dataProvider = "TestDataFile", groups = "cancelledStatus", dependsOnGroups = {
	//		"cancelAlerts" })
	public void cancelledStatus(String aircanadaUsername, String aircanadaPassword, String serviceType,
			String departmentValue, String pairingNumber, String locationValue, String individualEmpId,
			String empName) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		expected = "Pending Cancellation Confirmation";
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0),
				employeeIds.get(0), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "find reservation", dataProvider = "TestDataFile", groups = "findReservation", dependsOnGroups = {
	//		"pendingConfirmation" })
	public void findReservation(String aircanadaUsername, String aircanadaPassword, String serviceType,
			String departmentValue, String pairingNumber, String locationValue, String individualEmpId,
			String empName) {
		pgWrapper.genericClass.airlinesLoginAndNavigateToAcesDirect(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, departmentValue, pairingNumber,
				locationValue, arrivalDate, departureDate);
		expected = "Cancelled";
		List<String> employeeIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
		List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0),
				employeeIds.get(1), expected);

		pgWrapper.acesDirectFindReservationPage.findReservation("Ground", departmentValue, pairingNumber, locationValue,
				arrivalDate, departureDate);
		pgWrapper.acesDirectFindReservationPage.verifyCancelledRooms(departmentValue, empNames.get(0),
				employeeIds.get(1), expected);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

}
