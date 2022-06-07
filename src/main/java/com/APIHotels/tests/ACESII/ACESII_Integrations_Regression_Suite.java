package com.APIHotels.tests.ACESII;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
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
			String adhocType, String notesValue, String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue) throws NumberFormatException, Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AirFranceUsername"),
				readPropValue("AirFrancePassword"));
		pgWrapper.operationsTab.clickOnAdhocHotelRoomBlockRequests();
		pgWrapper.requestRoomBlockPage.createRoomblock(locationValue, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
		pgWrapper.requestRoomBlockPage.clickSaveBtn();
		pgWrapper.requestRoomBlockPage.processingRequest();

		pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);

		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		getDriver().get(aces2Url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Started processing the  reservation.");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndPickupDate("Adhoc-Hard", "14-Jan-2021");

	}

	@Test(description = "Ops Scenarios 1 - Reprocess the same ops file again", dataProvider = "TestDataFile", groups = {
			"Regression" })
	public void processSameOpsFile(String tenantName) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_reprocessSameFile",
				"Total Number of Equal Trips         : 3");
	}

	@Test(description = "Ops Scenarios 2 - Add Pairing", dataProvider = "TestDataFile", groups = { "Regression" })
	public void addPairingOps(String tenantName, String reservationId, String hotelName, String confirmationCode,
			String supplierType, String hotelRateValue, String toHotelGTRate, String checkInDateValue, String pccStatus)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_Addition",
				"Total Number of New Trips           : 1");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		String gtName = "";
		if (supplierType.equalsIgnoreCase("GT"))
			gtName = pgWrapper.pagePendingConfirmations.selectGTLink();
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, toHotelGTRate);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.clickOnShowNotesOfTrip(pccStatus);
	}

	@Test(description = "Ops Scenarios 3 - Delete Pairing", dataProvider = "TestDataFile", groups = { "Regression" })
	public void deletePairingOps(String tenantName) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_Deletion",
				"Total Number of Delete Trip Groups  : 1");
	}

	@Test(description = "Ops Scenarios 3 - Revise trip by decresing Crew members", dataProvider = "TestDataFile", groups = {
			"Regression" })
	public void revisePairingOpsChangeInCrew(String tenantName) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_Addition", "");
	}

	@Test(description = "Ops Scenarios 4 - Add Pairing", dataProvider = "TestDataFile", groups = { "Regression" })
	public void revisePairingOpsChangeInFlightNum(String tenantName) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_Addition", "");
		// Processing load-trips
//		OpsScenario_Addition
	}

	@Test(description = "Verify planning trips of Hotel to Airport", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_HotelToFromAirport(String runMode, String sshIndicator, String queries,
			String createScheduleFlag, String tenantName, String bidMonth, String city, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs,String keyWord2InLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String status,
			String verifyTime, String scenarioName, String migrateFrom) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs,keyWord2InLogs, migrateFrom);
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);

		}
	}

	@Test(description = "Verify processing planning file and processing same planning file in ops", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planningAsOpsFileNoChange(String runMode, String sshIndicator,String opsSSHIndicator, String queries,
			 String tenantName, String bidMonth, String city, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs,String keyWord2InLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String status,
			String verifyTime, String scenarioName, String migrateFrom) throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs,keyWord2InLogs, migrateFrom);
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			pgWrapper.pageHome.clickLogoutLink();
			if (opsSSHIndicator.equals("Yes")) {
				pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, scenarioFolderName,
						keyWordInLogs);
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
		}
	}

	@Test(description = "Verify Deleting trips through ops file and check the status on Find Trip", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void DeleteTripOpsFileProcessing(String runMode, String sshIndicator, String opsSSHIndicator, String queries,
			String createScheduleFlag, String tenantName, String bidMonth, String city, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs,String keyWord2InLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String status,
			String verifyTime, String scenarioName, String migrateFrom) throws Exception {

		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs,keyWord2InLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			pgWrapper.pageHome.clickLogoutLink();
			if (opsSSHIndicator.equals("Yes")) {
				pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_reprocessSameFile",
						"Total Number of Equal Trips         : 9");
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			pgWrapper.pageHome.clickLogoutLink();
			if (opsSSHIndicator.equals("Yes")) {
				pgWrapper.genericClass.ProcessOpsPairingFilesInSSH(tenantName, "OpsScenario_deletePairing",
						"Total Number of Equal Trips         : 8");
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
		}
	}

	// Delete Pairing from DB
	private void deletePairingsFromDB(String queries) throws Exception {
		List<String> deleteQueries = Arrays.asList(queries.split(","));
		String url = "jdbc:oracle:thin:@" + dbIP + ":" + dbPort + ":aces";
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, dbUserId, dbPassword);
		System.out.println("connection established!");
		Statement st = con.createStatement();
		for (int queryCount = 0; queryCount < deleteQueries.size(); queryCount++) {
			System.out.println(deleteQueries.get(queryCount));
			String deleteQuery = deleteQueries.get(queryCount);
			st.executeQuery(deleteQuery);

		}
		con.commit();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Commit Complete");
		con.close();
	}

	private void processPlanningAndMigrate(String tenantName, String bidMonth, String cityCode, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs, String keyWord2InLogs,String migrate) throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Processing Planning File for " + scenarioFolderName);
		pgWrapper.genericClass.uploadFileToSFTP(integrationAFFilePath, sftpFolderPath, scenarioFolderName);
		pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs,keyWord2InLogs);
		Thread.sleep(10000);
		// Errors and Warnings (DBRT - Ignore)
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickPlanningLink();
		Thread.sleep(10000);
		pgWrapper.planningMenu.clickErrorsWarningsLink();
		int countOfErrorsAndWarnings = pgWrapper.errorsWarningsPage
				.searchErrorsAndWarningsUsingBidPeriodAndCity(bidMonth, cityCode);
		if (countOfErrorsAndWarnings > 0) {
			pgWrapper.planningMenu.clickBulkAssignLink();
			pgWrapper.bulkAssignPage.bulkAssignPairingsFor(bidMonth, "Yes", "Yes", "Yes");
			pgWrapper.genericClass.verifylogsInPutty("", command, keyWordInLogs);
			pgWrapper.planningMenu.clickErrorsWarningsLink();
			int countOfErrorsAndWarningsAfterBulkAssign = pgWrapper.errorsWarningsPage
					.searchErrorsAndWarningsUsingBidPeriodAndCity(bidMonth, cityCode);
			if (countOfErrorsAndWarningsAfterBulkAssign > 0)
				Assert.fail(
						"Errors And Warnings Found, Please continue with execution after resolving all errors and warnings manually");
		}
		/* Code for Migrate Trip from Application */
		if (migrate.equals("Application")) {
			pgWrapper.planningMenu.clickMigrateLink();
			pgWrapper.migratePage.migrate();
		} else
			pgWrapper.genericClass.connectToSftpAndExecuteCommand(readPropValue("migrateCommandAF"),
					"BUILD SUCCESSFUL");

	}

	private void findTripAndVerifyStatus(String verifyTrips, String checkInDateValue, String hotelName, String gtName,
			String status) throws Exception {
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		List<String> reservId = Arrays.asList(verifyTrips.split(","));
		for (int reservIdCount = 0; reservIdCount < reservId.size(); reservIdCount++) {
			pgWrapper.pageFindTrip.findTrips(reservId.get(reservIdCount), checkInDateValue);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservId.get(reservIdCount));
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
			pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);
		}

	}

	private void createSchedules(String bidMonth, String serviceType, String city, String supplierName)
			throws Exception {

		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickCreateSchedulesLink();
		pgWrapper.createSchedulesPage.createSchedules(bidMonth, serviceType, city, supplierName);
		Thread.sleep(15000);
		pgWrapper.createSchedulesPage.clickOnCreateSchedules();
		pgWrapper.createSchedulesPage.waitForProcessingToComplete();
		pgWrapper.createSchedulesPage.verifySchResults(city, serviceType, supplierName);
		Thread.sleep(5000);

	}

	private void viewSchedules(String bidMonth, String serviceType, String city, String supplierName) throws Exception {
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(bidMonth, serviceType, city, supplierName, "", "", "", "", "",
				"", "", "");
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.clickSupplierLinkAndVerifyNotesPDF();

	}

	private void verifyOpsHotelSchedule(String city, String hotelName, String startDate, String endDate,
			String verifyTrips, String verifyTime) throws Exception {
		if (getDriver().findElement(By.id("smenu5")).getAttribute("style").equals("display: none;"))
			pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickSchedulesLink();
		pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
		pgWrapper.pageHotelSchedules.findHotelSchedules(city, hotelName, startDate, endDate);
		pgWrapper.pageHotelSchedules.verifyTimeAtHotel(verifyTrips, verifyTime);
	}

	private void verifyOpsGTSchedule(String city, String gtSupplier, String startDate, String endDate)
			throws Exception {
		pgWrapper.pageGTSchedules.clickGTSchedulesLink();
		pgWrapper.pageGTSchedules.verifyOpsGTSchedules(city, gtSupplier, startDate, endDate);
	}

}
