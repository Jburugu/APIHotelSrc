package com.APIHotels.tests.ACESII;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_EmployeeFileValidation_Regression_Suite extends LocalDriverManager {
	public PgWrapper pgWrapper;

	// Return first three employees from EMPLOYEE TENANT table for different tenants
	public List<List<Integer>> requiredEmployeeNumbers(String runMode, String tenantName, String dbCreds)
			throws Exception {
		List<Integer> employeenbr = new ArrayList<>();
		List<Integer> deptCode = new ArrayList<>();
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Checking For Migrating Records In DB");
			pgWrapper = LocalDriverManager.getPageWrapper();

			String url = "jdbc:oracle:thin:@" + dbIP + ":" + dbPort + ":aces";
			Connection con = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, dbCreds, dbCreds);
			System.out.println("connection established!");
			Statement st = con.createStatement();
			String tenantIdQuery = "Select tenantid from Tenant where tenantname = '" + tenantName + "'";
			ResultSet rs = st.executeQuery(tenantIdQuery);
			rs.next();
			String tenantId = rs.getString(1);
			String getThreeEmployeeDetailsOfLatestDate = "select * from (select EMPLOYEENBR,DEPARTMENTID from TENANTEMPLOYEE where TENANTID="
					+ tenantId
					+ " and STATUS =1 order by LASTMODDTTMZ desc) TENANTEMPLOYEE1 WHERE rownum <= 3 ORDER BY rownum";
			ResultSet rs1 = st.executeQuery(getThreeEmployeeDetailsOfLatestDate);
			int i = 0;
			int j = 0;
			while (rs1.next()) {
				int EmployeeNBR = rs1.getInt("EMPLOYEENBR");
				System.out.println(EmployeeNBR);
				employeenbr.add(i, EmployeeNBR);
				i++;
				int departmentCode = rs1.getInt("DEPARTMENTID");
				System.out.println(departmentCode);
				deptCode.add(j, departmentCode);
				j++;

			}
		}
		return Arrays.asList(employeenbr, deptCode);
	}

	@Test(description = "Process Employee Files of All Tenants", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void processEmployeeFiles_Tenants(String runMode, String tenantName, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Processing Employee File of " + tenantName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.genericClass.uploadFileToSFTP(sftpFolderPath, scenarioFolderName);
			pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);

		}
	}

	@Test(description = "ATA-, Employee File validation for Mesa", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void employeeFileValidationMesa(String runMode, String tenantName, String dbCreds, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,
			String companyValue, String expenseCodeValue, String reasonCodeValue, String statusPA,
			String confirmationCode, String paymentMethod, String statusBooked) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		// Go to Airlines and create Reservation
		getDriver().get(airlinesUrl);
		List<List<Integer>> empDetails = requiredEmployeeNumbers(runMode, tenantName, dbCreds);
		System.out.println(empDetails);
		List<Integer> EmployeeNumbers = empDetails.get(0);
		System.out.println(EmployeeNumbers);
		String EmployeeNumber1 = EmployeeNumbers.get(0).toString();
		System.out.println(EmployeeNumber1);
		String EmployeeNumber2 = EmployeeNumbers.get(1).toString();
		System.out.println(EmployeeNumber2);
		String EmployeeNumber3 = EmployeeNumbers.get(2).toString();
		System.out.println(EmployeeNumber3);

		List<Integer> deptCodes = empDetails.get(1);
		System.out.println(deptCodes);
		String deptCode1 = deptCodes.get(0).toString();
		System.out.println(deptCode1);
		String deptCode2 = deptCodes.get(1).toString();
		System.out.println(deptCode2);
		String deptCode3 = deptCodes.get(2).toString();
		System.out.println(deptCode3);

		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsForMesa(singleRoomType, EmployeeNumber1, deptCode1,
				doubleRoomType, EmployeeNumber2, deptCode2, EmployeeNumber3, deptCode3);
		pgWrapper.requestReservationPage.additionalInformationMesa(companyValue, expenseCodeValue, reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservationId = pgWrapper.requestReservationPage.processingRequestMesa();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);
		/* Verify Status of the Reservation */
		verifyStatusOfBT(reservationId, tenantName, statusPA);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		/*
		 * Go to ACES2 main site and verify that created reservation is shown in Ops
		 * Dashboard.
		 */
		bookPAAndVerifyStatusInAirlines(tenantName, reservationId, timeFormatValue, confirmationCode, paymentMethod,
				statusBooked);
	}

	@Test(description = "ATA-, Employee File validation for Endevour", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void employeeFileValidationEndeavor(String runMode, String tenantName, String dbCreds, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,
			String reasonCodeValue, String statusPA, String confirmationCode, String paymentMethod, String statusBooked)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		// Pull the last modified 3 employees with their departments from database
		getDriver().get(airlinesUrl);
		List<List<Integer>> empDetails = requiredEmployeeNumbers(runMode, tenantName, dbCreds);
		System.out.println(empDetails);
		List<Integer> EmployeeNumbers = empDetails.get(0);
		System.out.println(EmployeeNumbers);
		String EmployeeNumber1 = EmployeeNumbers.get(0).toString();
		System.out.println(EmployeeNumber1);
		String EmployeeNumber2 = EmployeeNumbers.get(1).toString();
		System.out.println(EmployeeNumber2);
		String EmployeeNumber3 = EmployeeNumbers.get(2).toString();
		System.out.println(EmployeeNumber3);
		// Go to Airlines and create Reservation
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsFor9E(singleRoomType, EmployeeNumber1, doubleRoomType,
				EmployeeNumber2, EmployeeNumber3);
		pgWrapper.requestReservationPage.selectReason9E(reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservationId = pgWrapper.requestReservationPage.processingRequestMesa();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);
		/* Verify Status of the Reservation */
		verifyStatusOfBT(reservationId, tenantName, statusPA);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		/*
		 * Go to ACES2 main site and verify that created reservation is shown in Ops
		 * Dashboard.
		 */
		bookPAAndVerifyStatusInAirlines(tenantName, reservationId, timeFormatValue, confirmationCode, paymentMethod,
				statusBooked);
	}

	@Test(description = "ATA-, Employee File validation for Mesa", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void employeeFileValidationEnvoy(String runMode, String tenantName, String dbCreds, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,
			String reasonCodeValue, String statusPA, String confirmationCode, String paymentMethod, String statusBooked)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		// Pull the last modified 3 employees with their departments from database
		List<List<Integer>> empDetails = requiredEmployeeNumbers(runMode, tenantName, dbCreds);
		System.out.println(empDetails);
		List<Integer> EmployeeNumbers = empDetails.get(0);
		System.out.println(EmployeeNumbers);
		String EmployeeNumber1 = EmployeeNumbers.get(0).toString();
		System.out.println(EmployeeNumber1);
		String EmployeeNumber2 = EmployeeNumbers.get(1).toString();
		System.out.println(EmployeeNumber2);
		String EmployeeNumber3 = EmployeeNumbers.get(2).toString();
		System.out.println(EmployeeNumber3);
		// Go to Airlines and create Reservation
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsFor9E(singleRoomType, EmployeeNumber1, doubleRoomType,
				EmployeeNumber2, EmployeeNumber3);
		pgWrapper.requestReservationPage.selectReasonEnvoy(reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservationId = pgWrapper.requestReservationPage.processingRequestMesa();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);
		/* Verify Status of the Reservation */
		verifyStatusOfBT(reservationId, tenantName, statusPA);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		/*
		 * Go to ACES2 main site and verify that created reservation is shown in Ops
		 * Dashboard.
		 */
		bookPAAndVerifyStatusInAirlines(tenantName, reservationId, timeFormatValue, confirmationCode, paymentMethod,
				statusBooked);
	}

	@Test(description = "ATA-, Employee File validation for Mesa", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void employeeFileValidationVA(String runMode, String tenantName, String dbCreds, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String statusPA,
			String confirmationCode, String paymentMethod, String statusBooked) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		/* Pull the last modified 3 employees with their departments from database */
		List<List<Integer>> empDetails = requiredEmployeeNumbers(runMode, tenantName, dbCreds);
		System.out.println(empDetails);
		List<Integer> EmployeeNumbers = empDetails.get(0);
		System.out.println(EmployeeNumbers);
		String EmployeeNumber1 = EmployeeNumbers.get(0).toString();
		System.out.println(EmployeeNumber1);
		String EmployeeNumber2 = EmployeeNumbers.get(1).toString();
		System.out.println(EmployeeNumber2);
		String EmployeeNumber3 = EmployeeNumbers.get(2).toString();
		System.out.println(EmployeeNumber3);
		/* Go to Airlines and create Reservation */
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsForVA(singleRoomType, EmployeeNumber1, singleRoomType,
				EmployeeNumber2);
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservationId = pgWrapper.requestReservationPage.processingRequestMesa();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);
		/* Verify Status of the Reservation */
		verifyStatusOfBT(reservationId, tenantName, statusPA);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		/*
		 * Go to ACES2 main site and verify that created reservation is shown in Ops
		 * Dashboard.
		 */
		bookPAAndVerifyStatusInAirlines(tenantName, reservationId, timeFormatValue, confirmationCode, paymentMethod,
				statusBooked);

	}

	@Test(description = "ATA-, Employee File validation for ExpressJet ERJ", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void employeeFileValidationERJ(String runMode, String tenantName, String dbCreds, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String singleRoomType, String doubleRoomType,String reasonCodeValue, String statusPA,
			String confirmationCode, String paymentMethod, String statusBooked) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		/* Pull the last modified 3 employees with their departments from database */
		List<List<Integer>> empDetails = requiredEmployeeNumbers(runMode, tenantName, dbCreds);
		System.out.println(empDetails);
		List<Integer> EmployeeNumbers = empDetails.get(0);
		System.out.println(EmployeeNumbers);
		String EmployeeNumber1 = EmployeeNumbers.get(0).toString();
		System.out.println(EmployeeNumber1);
		String EmployeeNumber2 = EmployeeNumbers.get(1).toString();
		System.out.println(EmployeeNumber2);
		String EmployeeNumber3 = EmployeeNumbers.get(2).toString();
		System.out.println(EmployeeNumber3);
		/* Go to Airlines and create Reservation */
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsFor9E(singleRoomType, EmployeeNumber1, doubleRoomType,
				EmployeeNumber2, EmployeeNumber3);
		pgWrapper.requestReservationPage.selectReason9E(reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservationId = pgWrapper.requestReservationPage.processingRequestMesa();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);
		/* Verify Status of the Reservation */
		verifyStatusOfBT(reservationId, tenantName, statusPA);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		/*
		 * Go to ACES2 main site and verify that created reservation is shown in Ops
		 * Dashboard.
		 */
		bookPAAndVerifyStatusInAirlines(tenantName, reservationId, timeFormatValue, confirmationCode, paymentMethod,
				statusBooked);

	}

	public void bookPAAndVerifyStatusInAirlines(String tenantName, String reservationId, String timeFormatValue,
			String confirmationCode, String paymentMethod, String statusBooked) throws Exception {
		getDriver().get(aces2Url);
		// String reservationId ="1103684";
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Started processing the following : " + reservationId + " reservation.");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, "All", timeFormatValue, "0");
		// Process the PA
		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pageDashBoard.enterBTConfirmationCode(confirmationCode);
		pgWrapper.pageDashBoard.selectPaymentDetails_BT(paymentMethod);
		pgWrapper.pageDashBoard.clickOnFinishButton();
		/* Verify Status of the Reservation */
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		verifyStatusOfBT(reservationId, tenantName, statusBooked);
	}

	public void verifyStatusOfBT(String reservationId, String tenantName, String statusPA) {
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.findReservationMesa(reservationId, tenantName);
		pgWrapper.findReservationBTPage.verifyBTStatusAirlines(tenantName,statusPA);
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Pending Assignment status found for the following reservation id : " + reservationId);
	}

	
	@Test(description = "", groups = {
	"Regression" }, dataProvider = "TestDataFile")
	public void editSupplierDetails(String tenantName,String city,String serviceId, String status, String supplierName, String NewpairingFileName, String NewcontractEndDate ) throws Exception {
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Started processing the following :  ");
		
		/* modify Supplier details  */ 
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
//		pgWrapper.pageHome.clickSupplierProfileLink();
//		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
//		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
//		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
//		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(supplierName);
//		pgWrapper.pageAirlineHotelSupplier.setGeneralHotelSupplierDetails(NewpairingFileName);
//		pgWrapper.pageAirlineHotelSupplier.setOnlyContractEndDate(NewcontractEndDate);
//		pgWrapper.pageAirlineHotelSupplier.clickHotelContractDetailsBtn();
//		pgWrapper.pageHotelSupplierContractDetails.selectHotelProvidesTransportation();
//		pgWrapper.pageHotelSupplierContractDetails.clickSaveDetails();
//		pgWrapper.pageAirlineHotelSupplier.saveDetails();
		
		
		
		/* Create Schedules */
//		pgWrapper.pageHome.clickSchedulesLink();
//		pgWrapper.schedulesMenu.clickCreateSchedulesLink();
//		pgWrapper.createSchedulesPage.createSchedules("27 Mar 17-23 Apr 17", "HOTEL", city, supplierName);
//		pgWrapper.createSchedulesPage.clickOnCreateSchedules();
//		pgWrapper.createSchedulesPage.waitForProcessingToComplete();
//		pgWrapper.createSchedulesPage.verifySchResults(city, "BOTH", supplierName);
		
		/*View Schedules */
//		pgWrapper.schedulesMenu.clickViewSchedulesLink();
//		pgWrapper.viewSchedulesPage.setSchedulesCriteria("27 Mar 17-23 Apr 17","HOTEL", city, supplierName, "", "",
//				"", "", "", "", "", "");
//		pgWrapper.viewSchedulesPage.clickFindBtn();
//		pgWrapper.viewSchedulesPage.clickSupplierLinkAndVerifyNotesPDF("Special Notes", "BNE", "The Point Brisbane",supplierName);
		
//		getDriver().get(airlinesUrl);
//		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
//				readPropValue(tenantName.replace(" ", "") + "Password"));
//		pgWrapper.operationsTab.ClickOnGTOPSSchedule();
//		pgWrapper.gtOpsSchedulePage.gtOpsSchedule("", "BNE", "", "", "", "","05-Nov-2020","05-Nov-2020");
//		pgWrapper.gtOpsSchedulePage.verifyRiders("14");
//		
		
		//verifyOpsGTSchedule("BNE", "The Point Brisbane", "07-Nov-2020", "07-NOV-2020", "7777711", "The Point Brisbane");
		
		
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickManageVenuesLink();
		pgWrapper.pageManageVenues.searchVenues("BNE");
		pgWrapper.pageManageVenues.editVenueGroups(1,"BNE FROM G22", "Test");
		pgWrapper.pageManageVenues.editVenues("BNE VA 1", "Test", "Group 4","Test","Test");
		pgWrapper.pageManageVenues.editVenueGroupMergeDetails("Both Direction", "1,4,6,12");
		
		
	}
	
	
	
	
	private void verifyOpsGTSchedule(String city, String gtSupplier, String startDate, String endDate,
			String verifyTrips, String textToVerifyInGTPDF) throws Exception {
		pgWrapper.opsDeskMenu.clickSchedulesLink();
		pgWrapper.pageGTSchedules.clickGTSchedulesLink();
		pgWrapper.pageGTSchedules.verifyOpsGTSchedulesAndVerifyPDF(city, gtSupplier, startDate, endDate, verifyTrips, textToVerifyInGTPDF);
	}

}
