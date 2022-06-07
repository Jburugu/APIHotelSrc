package com.APIHotels.tests.ACESII;

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

public class ACESII_GTOptimization_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	// @Test(description = "Edit Supplier Details", groups = { "Regression" },
	// dataProvider = "TestDataFile")
	private void editSupplierDetails(String tenantName, String city, String serviceId, String status,
			String supplierName, String NewpairingFileName, String NewcontractEndDate, String HtProvidesTransportation)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(supplierName);
		pgWrapper.pageAirlineHotelSupplier.setGeneralHotelSupplierDetails(NewpairingFileName);
		pgWrapper.pageAirlineHotelSupplier.setOnlyContractEndDate(NewcontractEndDate);
		pgWrapper.pageAirlineHotelSupplier.setContractIndicator();
		// click on contract details and check Hotel Provides GT if not checked
		// earlier
		pgWrapper.pageAirlineHotelSupplier.clickHotelContractDetailsBtn();
		String HtTransport = "Yes";
		if (HtTransport.equalsIgnoreCase(HtProvidesTransportation))
			pgWrapper.pageHotelSupplierContractDetails.selectHotelProvidesTransportation();
		else
			pgWrapper.pageHotelSupplierContractDetails.deselectHotelProvidesTransportation();
		pgWrapper.pageHotelSupplierContractDetails.setRoomLimits("1-JAN-2015", "31-DEC-2040", "", "998");
		// pgWrapper.pageHotelSupplierContractDetails.editRoomRate("S,M,T,W,TH,F,ST",
		// "1-JAN-2021", "31-DEC-2040", "Any", "120.00");
		pgWrapper.pageHotelSupplierContractDetails.clickSaveDetails();
		pgWrapper.pageAirlineHotelSupplier.editAirportVenueHotelTransitTimesDetails(1, "S,M,T,W,TH,F,ST", city,
				"1-JAN-2015", "31-DEC-2040", "", "", "60");
		pgWrapper.pageAirlineHotelSupplier.editHotelUseRules("S,M,T,W,TH,F,ST", city, "1-JAN-2015", "31-DEC-2040",
				"00:00", "");
		pgWrapper.pageAirlineHotelSupplier.saveDetails();

	}

	private void editGTSupplier(String city, String serviceId, String status, String gtName, String NewcontractEndDate)
			throws Exception {
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(gtName);
		pgWrapper.pageAirlineHotelSupplier.setOnlyContractEndDate(NewcontractEndDate);
		pgWrapper.pageAirlineHotelSupplier.setGTContractIndicator();
		pgWrapper.pageAirlineHotelSupplier.saveDetails();
	}

	/*
	 * Start -- Hotel To Airport
	 */
	@Test(description = "Verify planning trips of Hotel to Airport", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_HotelToAirport(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String verifyTime, String scenarioName, String migrateFrom)
			throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			if (createScheduleFlag.equals("True")) {
				createSchedules(bidMonth, "HOTEL", city, hotelName);
				createSchedules(bidMonth, "GT", city, gtName);
				viewSchedules(bidMonth, "HOTEL", city, hotelName);
				viewSchedules(bidMonth, "GT", city, gtName);
			}
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of Hotel to Airport", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_HotelToAirport(String runMode, String sshIndicator, String tenantName, String bidMonth, String city,
			String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String serviceType, String verifyTrips, String scenarioName, String newGtName,
			String textToVerifyInHotelPDF, String textToVerifyInGTPDF) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				bookPA(reservationId, "Booked", newGtName);
				findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, newGtName, "Booked");
				verifyPDFsFromShowNotes(hotelName, newGtName, textToVerifyInHotelPDF, textToVerifyInGTPDF);

			}
			if (!verifyTrips.equals(""))
				verifyOpsGTSchedule(city, gtName, checkOutDateValue, checkOutDateValue);
			if (scenarioName.equals("Scenario1") || scenarioName.equals("Scenario2") && !verifyTrips.equals(""))
				gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, "14");
		}

	}

	/*
	 * End -- Hotel To Airport
	 */

	/*
	 * Start -- Airport To Hotel
	 */
	@Test(description = "Verify planning trips of Airport to Hotel", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_AirportToHotel(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String verifyTime, String scenarioName, String migrateFrom)
			throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			if (createScheduleFlag.equals("True")) {
				createSchedules(bidMonth, "HOTEL", city, hotelName);
				createSchedules(bidMonth, "GT", city, gtName);
				viewSchedules(bidMonth, "HOTEL", city, hotelName);
				viewSchedules(bidMonth, "GT", city, gtName);
			}
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of Airport to Hotel", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_AirportToHotel(String runMode, String sshIndicator, String tenantName, String bidMonth, String city,
			String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String serviceType, String verifyTrips, String scenarioName, String newGtName,
			String textToVerifyInHotelPDF, String textToVerifyInGTPDF) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				bookPA(reservationId, "Booked", newGtName);
				findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, newGtName, "Booked");
				verifyPDFsFromShowNotes(hotelName, newGtName, textToVerifyInHotelPDF, textToVerifyInGTPDF);

			}
			if (!verifyTrips.equals(""))
				verifyOpsGTSchedule(city, gtName, checkOutDateValue, checkOutDateValue);
			if (scenarioName.equals("Scenario3") || scenarioName.equals("Scenario4") && !verifyTrips.equals(""))
				gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, "14");
		}

	}

	/*
	 * Start -- Hotel To Venue
	 */
	@Test(description = "Verify planning trips of Hotel to Venue", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_HtToVenueScenario10_11(String runMode, String sshIndicator, String queries,
			String createScheduleFlag, String tenantName, String bidMonth, String city, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String status,
			String verifyTime, String scenarioName, String migrateFrom, String groupNumber, String groupName,
			String groupDisplayName, String venueCode, String directions, String groupValues) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				manageVenues(tenantName, city, groupNumber, groupName, groupDisplayName, venueCode, directions,
						groupValues);
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "BNE_05",
						"30-Aug-2030", "Yes");
				// Code to add venues
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of Hotel to Venue", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void ops_HotelToVenueScenario10(String runMode, String sshIndicator, String tenantName, String bidMonth,
			String city, String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String verifyTrips, String scenarioName, String newGtName, String textToVerifyInHotelPDF,
			String textToVerifyInGTPDF) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				bookPA(reservationId, "Booked", newGtName);
				findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, newGtName, "Booked");
				verifyPDFsFromShowNotes(hotelName, newGtName, textToVerifyInHotelPDF, textToVerifyInGTPDF);

			}
			if (!verifyTrips.equals(""))
				verifyOpsGTSchedule(city, gtName, checkOutDateValue, checkOutDateValue);
			if (scenarioName.equals("Scenario10") || scenarioName.equals("Scenario11") && !verifyTrips.equals(""))
				gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, "14");
		}

	}

	@Test(description = "Verify planning trips of Hotel to Venue For 3rd Party GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_HtToVenueScenario13_14(String runMode, String sshIndicator, String queries,
			String createScheduleFlag, String tenantName, String bidMonth, String city, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String status,
			String verifyTime, String scenarioName, String migrateFrom) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
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
			String scenarioFolderName, String command, String keyWordInLogs, String migrate) throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Processing Planning File for " + scenarioFolderName);
		pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
		pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
		Thread.sleep(5000);
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
			pgWrapper.genericClass.connectToSftpAndExecuteCommand(readPropValue("migrateCommandVA"),
					"BUILD SUCCESSFUL");

	}

	private void findTripAndVerifyStatus(String verifyTrips, String checkInDateValue, String hotelName, String gtName,
			String status) throws Exception {
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		List<String> reservId = Arrays.asList(verifyTrips.split(","));
		for (int reservIdCount = 0; reservIdCount < reservId.size(); reservIdCount++) {
			pgWrapper.pageFindTrip.findTrips(reservId.get(reservIdCount), checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
			pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);
		}

	}

	private void calculateCheckInPickUpAndShowTime(String showTime, String pickupTime, String checkInTime)
			throws Exception {
		String showTimeValue = pgWrapper.pageFindTrip.getShowTime();
		String pickupTimeValue = pgWrapper.pageFindTrip.getpickUpTime();
		String checkInTimeValue = pgWrapper.pageFindTrip.getCheckInTime();
		if (showTimeValue.equals(showTime))
			ExtentTestManager.getTest().log(LogStatus.PASS, "Show Time displayed as expected");
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Time not displayed as expected");
		if (pickupTimeValue.equals(pickupTime))
			ExtentTestManager.getTest().log(LogStatus.PASS, "PickUp Time displayed as expected");
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "PickUP Time not displayed as expected");
		if (checkInTimeValue.equals(checkInTime))
			ExtentTestManager.getTest().log(LogStatus.PASS, "CheckIn Time displayed as expected");
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CheckIn Time not displayed as expected");

	}

	private void bookPA(String reservationId, String confirmationCode, String gtName) throws Exception {
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		pgWrapper.pagePendingConfirmations.selectTripFromAvailableReservationBasedOnTripId(reservationId);
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.enterGTConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider("0", gtName);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider("1", gtName);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate("25");
		pgWrapper.pagePendingConfirmations.addToHotelGTRates("10", "10");
		pgWrapper.pagePendingConfirmations.clickFinishButton();
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

	private void gtOpsScheduleAirlines(String tenantName, String city, String startdate, String endDate,
			String noOfRiders) throws Exception {
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.ClickOnGTOPSSchedule();
		pgWrapper.gtOpsSchedulePage.gtOpsSchedule("", city, "", "", "", "", startdate, endDate);
		pgWrapper.gtOpsSchedulePage.verifyRiders(noOfRiders);

	}

	private void verifyPDFsFromShowNotes(String hotelName, String newGtName, String textToVerifyInHotelPDF,
			String textToVerifyInGTPDF) throws Exception {
		pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotelName, "Booked");
		pgWrapper.pageFindTrip.verifyNotesForTrips("Trip Revised. New Reservation added");
		String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotelName, newGtName, "2");
		System.out.println(fileName);
		String pdfs[] = fileName.split(",");
		String hotelPDF = pdfs[0];
		String gtPDF = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(hotelPDF, textToVerifyInHotelPDF);
		pgWrapper.pageFindTrip.readPDF(gtPDF, textToVerifyInGTPDF);
	}

	private void manageVenues(String tenantName, String cityCode, String groupNumber, String groupName,
			String groupDisplayName, String venueCode, String directions, String groupValues) throws Exception {
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickManageVenuesLink();
		pgWrapper.pageManageVenues.searchVenues(cityCode);
		pgWrapper.pageManageVenues.editVenueGroups(groupNumber, groupName, groupDisplayName);// "BNE
																								// FROM
																								// G22",
																								// "Test"
		pgWrapper.pageManageVenues.editVenues(venueCode, groupNumber);
		pgWrapper.pageManageVenues.editVenueGroupMergeDetails(directions, groupValues);// Both
																						// Direction
		pgWrapper.pageManageVenues.clickOnSaveButton();
	}

	/*
	 * private void backDateSystem() throws Exception{
	 * pgWrapper.genericClass.connectToSftpAndExecuteCommand(readPropValue(
	 * "commandToBackSystemDate"), "BUILD SUCCESSFUL"); }
	 * 
	 * private void restartServer() throws Exception{
	 * pgWrapper.genericClass.connectToSftpAndExecuteCommand(readPropValue(
	 * "stopServer"), "");//Add Keyword here Thread.sleep(20000);
	 * pgWrapper.genericClass.connectToSftpAndExecuteCommand(readPropValue(
	 * "startServer"), "");//Add Keyword here }
	 */

	// Bulk Assign check - Ignore DBRT -> verify in logs - Bulk Assign
	// Completed
	/*
	 * pgWrapper.planningMenu.clickBulkAssignLink();
	 * pgWrapper.bulkAssignPage.bulkAssignPairingsFor(bidMonth, "Yes", "Yes",
	 * "Yes"); pgWrapper.genericClass.verifylogsInPutty("", command,
	 * keyWordInLogs); ExtentTestManager.getTest().log(LogStatus.INFO,
	 * "Bulk Assign Completed"); Thread.sleep(5000);
	 */
	// pgWrapper.genericClass.connectToSftpAndExecuteCommand(readPropValue("migrateCommandVA"),
	// "BUILD SUCCESSFUL");

	/*
	 * Start -- LAX: HT Provided GT
	 */
	@Test(description = "Verify planning trips of LAX Hotel Provided GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_LAX_HT_GT(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String verifyTime, String scenarioName, String migrateFrom,
			String showTime, String pickupTime, String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "LAX_VA2",
						"30-Aug-2030", "Yes");
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			calculateCheckInPickUpAndShowTime(showTime, pickupTime, checkInTime);
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of LAX Hotel Provided GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_LAX_HT_GT(String runMode, String sshIndicator, String tenantName, String bidMonth, String city,
			String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String verifyTrips, String scenarioName, String newGtName, String textToVerifyInHotelPDF,
			String notesInTrip) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
				pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation("Booked");
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.addHotelRate("25");
				pgWrapper.pagePendingConfirmations.addToHotelGTRates("10", "10");
				pgWrapper.pagePendingConfirmations.clickFinishButton();
				findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, newGtName, "Booked");
				pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotelName, "Booked");
				pgWrapper.pageFindTrip.verifyNotesForTrips(notesInTrip);
				String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotelName, newGtName, "1");
				System.out.println(fileName);
				String pdfs[] = fileName.split(",");
				String hotelPDF = pdfs[0];
				pgWrapper.pageFindTrip.readPDF(hotelPDF, textToVerifyInHotelPDF);

			}
			if (!verifyTrips.equals("")) {
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, gtName, checkOutDateValue, checkOutDateValue);
			}
			if (scenarioName.contains("Scenario38"))
				gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, "14");
		}

	}

	/*
	 * End -- LAX: HT Provided GT
	 */

	/*
	 * Start -- LAX: 3rd Party GT
	 */
	@Test(description = "Verify planning trips of LAX Hotel Provided GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_LAX_3rdParty_GT(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String verifyTime, String scenarioName, String migrateFrom,
			String showTime, String pickupTime, String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "LAX_VA3",
						"30-Aug-2030", "No");
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			calculateCheckInPickUpAndShowTime(showTime, pickupTime, checkInTime);
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	/*
	 * Start -- HKG: Hotel Provided GT
	 */
	@Test(description = "Verify planning trips of LAX Hotel Provided GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_HKG_HT_GT(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String verifyTime, String scenarioName, String migrateFrom,
			String showTime, String pickupTime, String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "HKG1", "30-Aug-2030",
						"Yes");
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			calculateCheckInPickUpAndShowTime(showTime, pickupTime, checkInTime);
			if (createScheduleFlag.equals("True")) {
				createSchedules(bidMonth, "HOTEL", city, hotelName);
				createSchedules(bidMonth, "GT", city, gtName);
				viewSchedules(bidMonth, "HOTEL", city, hotelName);
				viewSchedules(bidMonth, "GT", city, gtName);
			}
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of LAX Hotel Provided GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_HKG_HT_GT(String runMode, String sshIndicator, String tenantName, String bidMonth, String city,
			String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String verifyTrips, String scenarioName, String newGtName, String textToVerifyInHotelPDF,
			String notesInTrip) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
				pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation("Booked");
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.addHotelRate("25");
				pgWrapper.pagePendingConfirmations.addToHotelGTRates("10", "10");
				pgWrapper.pagePendingConfirmations.clickFinishButton();
				findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, newGtName, "Booked");
				pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotelName, "Booked");
				pgWrapper.pageFindTrip.verifyNotesForTrips(notesInTrip);
				String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotelName, newGtName, "1");
				System.out.println(fileName);
				String pdfs[] = fileName.split(",");
				String hotelPDF = pdfs[1];
				pgWrapper.pageFindTrip.readPDF(hotelPDF, textToVerifyInHotelPDF);

			}
			if (!verifyTrips.equals("")) {
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, gtName, checkOutDateValue, checkOutDateValue);
			}
			if (scenarioName.contains("Scenario41"))
				gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, "10");
		}

	}

	/*
	 * End -- HKG: HT Provided GT
	 */
	/*
	 * Start -- HKG: 3rd Party GT
	 */
	@Test(description = "Verify planning trips of LAX 3rd Party GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_HKG_3rdParty_GT(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String verifyTime, String scenarioName, String migrateFrom,
			String showTime, String pickupTime, String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "HKG2", "30-Aug-2030",
						"No");
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			calculateCheckInPickUpAndShowTime(showTime, pickupTime, checkInTime);
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of LAX 3rd Party Provided GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_HKG_3rdParty_GT(String runMode, String sshIndicator, String tenantName, String bidMonth,
			String city, String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String verifyTrips, String scenarioName, String newGtName, String textToVerifyInHotelPDF,
			String notesInTrip, String textToVerifyInGTPDF) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
				pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation("Booked");
				pgWrapper.pagePendingConfirmations.enterGTConfirmationCode("Booked");
				pgWrapper.pagePendingConfirmations.selectReqToGTProvider("0", newGtName);
				pgWrapper.pagePendingConfirmations.selectReqFromGTProvider("1", newGtName);
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.addHotelRate("25");
				pgWrapper.pagePendingConfirmations.addToHotelGTRates("10", "10");
				pgWrapper.pagePendingConfirmations.clickFinishButton();
				findTripAndVerifyStatus(reservationId, checkInDateValue, hotelName, newGtName, "Booked");
				pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotelName, "Booked");
				pgWrapper.pageFindTrip.verifyNotesForTrips(notesInTrip);
				String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotelName, newGtName, "2");
				System.out.println(fileName);
				String pdfs[] = fileName.split(",");
				String hotelPDF = pdfs[0];
				String gtPDF = pdfs[1];
				pgWrapper.pageFindTrip.readPDF(hotelPDF, textToVerifyInHotelPDF);
				pgWrapper.pageFindTrip.readPDF(gtPDF, textToVerifyInGTPDF);

			}
			if (!verifyTrips.equals("")) {
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, newGtName, checkOutDateValue, checkOutDateValue);
			}
			if (scenarioName.contains("Scenario44"))
				gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, "3");
		}

	}
	/*
	 * End -- HKG: 3rd Party GT
	 */

	/*
	 * Start -- Car Park To Or From Terminal
	 */
	@Test(description = "Verify planning trips of CarPark To Or From Termnal", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_CarParkToOrFromTermnal(String runMode, String sshIndicator, String queries,
			String createScheduleFlag, String tenantName, String bidMonth, String city, String sftpFolderPath,
			String scenarioFolderName, String command, String keyWordInLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String status,
			String verifyTime, String scenarioName, String migrateFrom, String showTime, String pickupTime,
			String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "DPS1", "30-Aug-2030",
						"Yes");
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			String showTimeValue = pgWrapper.pageFindTrip.showTime();
			String pickupTimeValue = pgWrapper.pageFindTrip.getpickUpTime();
			String checkInTimeValue = pgWrapper.pageFindTrip.getCheckInTime();
			if (showTimeValue.equals(showTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "Show Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Time not displayed as expected");
			if (pickupTimeValue.equals(pickupTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "PickUp Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PickUP Time not displayed as expected");
			if (checkInTimeValue.equals(checkInTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "CheckIn Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CheckIn Time not displayed as expected");
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of Car Parking To Or From Terminal", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_CarParkToOrFromTerminal(String runMode, String sshIndicator, String tenantName, String bidMonth,
			String city, String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String verifyTrips, String scenarioName, String newGtName, String textToVerifyInHotelPDF,
			String notesInTrip, String noOfRiders, String alertType) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			if (!scenarioName.contains("NoLayOver"))
				pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
			pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				String gtProviderName = null;
				List<String> alertTypes = Arrays.asList(alertType.split(","));
				for (int tripCount = 0; tripCount < alertTypes.size(); tripCount++) {
					pgWrapper.opsDeskMenu.clickDashBoardLink();
					pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
					pgWrapper.pageDashBoard.filterByAlertType(alertTypes.get(tripCount));
					pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(reservationId);
					if (alertTypes.get(tripCount).contains("Airport")) {
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.confirmCodeAtoA("Booked");
						pgWrapper.pagePendingConfirmations.enterAirportRate("12");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");
					} else if (alertTypes.get(tripCount).contains("Layover")) {
						pgWrapper.pagePendingConfirmations.clickOnFirstHotelProvider();
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1", "1", "Booked");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.addHotelRate("23");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");

					}

					pgWrapper.pagePendingConfirmations.clickFinishButton();
					pgWrapper.pagePendingConfirmations.checkForAlertBox();
					pgWrapper.opsDeskMenu.clickFindTripLink();
					pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
					pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
					pgWrapper.pageFindTrip.verifySupplierAndStatus(gtProviderName, "Booked");
				}
			}
			if (!verifyTrips.equals("")) {
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, hotelName, checkOutDateValue, checkOutDateValue);
				if (scenarioName.contains("Scenario51") || scenarioName.contains("Scenario52"))
					gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, noOfRiders);
			}

		}

	}

	/*
	 * End -- Car Park To Or From Terminal
	 */

	/*
	 * Start -- Terminal To Terminal
	 */
	@Test(description = "Verify planning trips Terminal To Terminal", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void TerminalToTerminalPlanning(String runMode, String sshIndicator, String queries,
			String createScheduleFlag, String tenantName, String bidMonth, String city, String gtCity,
			String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs, String verifyTrips,
			String checkInDateValue, String checkOutDateValue, String hotelName, String gtName, String gtSupplier,
			String status, String verifyTime, String scenarioName, String migrateFrom, String showTime,
			String pickupTime, String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "HKG2", "30-Aug-2040",
						"Yes");
				for (int cityCount = 0; cityCount < 2; cityCount++) {
					List<String> cityName = Arrays.asList(gtCity.split(","));
					List<String> gtSupplierName = Arrays.asList(gtSupplier.split(","));
					editGTSupplier(cityName.get(cityCount), "Both", "CONTRACT,NON_CONTRACT",
							gtSupplierName.get(cityCount), "30-Aug-2040");
				}
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			String showTimeValue = pgWrapper.pageFindTrip.showTime();
			String pickupTimeValue = pgWrapper.pageFindTrip.getpickUpTime();
			String checkInTimeValue = pgWrapper.pageFindTrip.getCheckInTime();
			if (showTimeValue.equals(showTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "Show Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Time not displayed as expected");
			if (pickupTimeValue.equals(pickupTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "PickUp Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PickUP Time not displayed as expected");
			if (checkInTimeValue.equals(checkInTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "CheckIn Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CheckIn Time not displayed as expected");
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, hotelName, checkInDateValue, checkInDateValue);
			List<String> gtSupplierCity = Arrays.asList(gtCity.split(","));
			verifyOpsGTSchedule(gtSupplierCity.get(0), gtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of Terminal to Terminal for HKG", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_TerminalToTerminal(String runMode, String sshIndicator, String tenantName, String bidMonth,
			String city, String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String gtstatus, String verifyTrips, String scenarioName, String noOfRiders,
			String alertType) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			if (!scenarioName.contains("Deleted")) {
				pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
				pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, gtstatus);
			} else {
				pgWrapper.pageFindTrip.verifyDeletedTripStatus(hotelName, status);
				pgWrapper.pageFindTrip.verifyDeletedTripStatus(gtName, gtstatus);
			}
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				String gtProviderName = null;
				List<String> alertTypes = Arrays.asList(alertType.split(","));
				for (int tripCount = 0; tripCount < alertTypes.size(); tripCount++) {
					pgWrapper.opsDeskMenu.clickDashBoardLink();
					pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
					pgWrapper.pageDashBoard.filterByAlertType(alertTypes.get(tripCount));
					pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(reservationId);
					if (alertTypes.get(tripCount).contains("Airport")) {
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.confirmCodeAtoA("Booked");
						pgWrapper.pagePendingConfirmations.enterAirportRate("12");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");
					} else if (alertTypes.get(tripCount).contains("Layover")) {
						pgWrapper.pagePendingConfirmations.clickOnFirstHotelProvider();
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1", "1", "Booked");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.addHotelRate("23");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");

					}

					pgWrapper.pagePendingConfirmations.clickFinishButton();
					pgWrapper.pagePendingConfirmations.checkForAlertBox();
					pgWrapper.opsDeskMenu.clickFindTripLink();
					pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
					pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
					pgWrapper.pageFindTrip.verifySupplierAndStatus(gtProviderName, "Booked");
				}
			}
			if (!verifyTrips.equals("")) {
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, hotelName, checkOutDateValue, checkOutDateValue);
				if (scenarioName.contains("Scenario56") || scenarioName.contains("Scenario58")
						|| scenarioName.contains("Scenario59") || scenarioName.contains("Scenario60")
						|| scenarioName.contains("Scenario61"))
					gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, noOfRiders);
			}

		}

	}

	/*
	 * End -- Terminal To Terminal
	 */

	/*
	 * Start -- Car Park To Or From Terminal - HKG
	 * 
	 */

	@Test(description = "Verify Ops trips of Car Park To Or From Terminal - HKG", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_CarPark_Terminal_HKG(String runMode, String sshIndicator, String tenantName, String bidMonth,
			String city, String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String gtstatus, String verifyTrips, String scenarioName, String noOfRiders,
			String alertType, String newgtName) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "HKG1", "30-Aug-2040",
						"Yes");
				editGTSupplier(city, "Both", "CONTRACT,NON_CONTRACT", newgtName, "30-Aug-2040");
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			if (!scenarioName.contains("Deleted")) {
				pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
				pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, gtstatus);
			} else {
				pgWrapper.pageFindTrip.verifyDeletedTripStatus(hotelName, status);
				pgWrapper.pageFindTrip.verifyDeletedTripStatus(gtName, gtstatus);
			}
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				String gtProviderName = null;
				List<String> alertTypes = Arrays.asList(alertType.split(","));
				for (int tripCount = 0; tripCount < alertTypes.size(); tripCount++) {
					pgWrapper.opsDeskMenu.clickDashBoardLink();
					pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
					pgWrapper.pageDashBoard.filterByAlertType(alertTypes.get(tripCount));
					pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(reservationId);
					if (alertTypes.get(tripCount).contains("Airport")) {
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.confirmCodeAtoA("Booked");
						pgWrapper.pagePendingConfirmations.enterAirportRate("12");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");
					} else if (alertTypes.get(tripCount).contains("Layover")) {
						pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("2", "2", "Booked");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.addHotelRate("23");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");

					}

					pgWrapper.pagePendingConfirmations.clickFinishButton();
					pgWrapper.pagePendingConfirmations.checkForAlertBox();
					pgWrapper.opsDeskMenu.clickFindTripLink();
					pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
					pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
					pgWrapper.pageFindTrip.verifySupplierAndStatus(gtProviderName, "Booked");
				}
			}
			if (!verifyTrips.equals("")) {
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, newgtName, checkInDateValue, checkOutDateValue);
				gtOpsScheduleAirlines(tenantName, city, checkInDateValue, checkOutDateValue, noOfRiders);
			}

		}
	}

	/*
	 * End -- Car Park To Or From Terminal - HKG
	 * 
	 */

	/*
	 * Start -- Venue To Airport
	 * 
	 */

	@Test(description = "Verify planning trips of Venue To Airport", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_VenueToAirport(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String hotelName, String gtName, String status, String newgtName, String verifyTime, String scenarioName,
			String migrateFrom, String showTime, String pickupTime, String checkInTime) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				editSupplierDetails(tenantName, city, "Both", "CONTRACT,NON_CONTRACT", hotelName, "BNE_05",
						"30-Aug-2040", "Yes");
				editGTSupplier(city, "Both", "CONTRACT,NON_CONTRACT", newgtName, "30-Aug-2040");
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			findTripAndVerifyStatus(verifyTrips, checkInDateValue, hotelName, gtName, status);
			String showTimeValue = pgWrapper.pageFindTrip.showTime();
			String pickupTimeValue = pgWrapper.pageFindTrip.getpickUpTime();
			String checkInTimeValue = pgWrapper.pageFindTrip.getCheckInTime();
			if (showTimeValue.equals(showTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "Show Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Time not displayed as expected");
			if (pickupTimeValue.equals(pickupTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "PickUp Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PickUP Time not displayed as expected");
			if (checkInTimeValue.equals(checkInTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "CheckIn Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CheckIn Time not displayed as expected");
			/*
			 * if (createScheduleFlag.equals("True")) {
			 * createSchedules(bidMonth, "HOTEL", city, hotelName);
			 * createSchedules(bidMonth, "GT", city, gtName);
			 * viewSchedules(bidMonth, "HOTEL", city, hotelName);
			 * viewSchedules(bidMonth, "GT", city, gtName); }
			 */
			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsHotelSchedule(city, hotelName, checkInDateValue, checkOutDateValue, verifyTrips, verifyTime);
			verifyOpsGTSchedule(city, newgtName, checkInDateValue, checkInDateValue);
		}
	}

	@Test(description = "Verify Ops trips of Venue To Airport", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void ops_VenueToAirport(String runMode, String sshIndicator, String tenantName, String bidMonth, String city,
			String sftpFolderPath, String scenarioFolderName, String command, String keyWordInLogs,
			String reservationId, String checkInDateValue, String checkOutDateValue, String hotelName, String gtName,
			String status, String gtstatus, String verifyTrips, String scenarioName, String noOfRiders,
			String alertType, String newgtName) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(gtOptimizationFilesPath, sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
				Thread.sleep(5000);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying for trip " + reservationId);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
			pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, gtstatus);
			Thread.sleep(2000);
			if (scenarioName.contains("Book")) {
				String gtProviderName = null;
				List<String> alertTypes = Arrays.asList(alertType.split(","));
				for (int tripCount = 0; tripCount < alertTypes.size(); tripCount++) {
					pgWrapper.opsDeskMenu.clickDashBoardLink();
					pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
					pgWrapper.pageDashBoard.filterByAlertType(alertTypes.get(tripCount));
					pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(reservationId);
					if (alertTypes.get(tripCount).contains("Airport")) {
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.confirmCodeAtoA("Booked");
						pgWrapper.pagePendingConfirmations.enterAirportRate("12");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");
					} else if (alertTypes.get(tripCount).contains("Layover")) {
						pgWrapper.pagePendingConfirmations.clickOnFirstHotelProvider();
						gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider("1", "1", "Booked");
						System.out.println(gtProviderName);
						pgWrapper.pagePendingConfirmations.addHotelRate("23");
						pgWrapper.pageDashBoard.selectPaymentMethod("Direct Billing");

					}

					pgWrapper.pagePendingConfirmations.clickFinishButton();
					pgWrapper.pagePendingConfirmations.checkForAlertBox();
					pgWrapper.opsDeskMenu.clickFindTripLink();
					pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
					pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
					pgWrapper.pageFindTrip.verifySupplierAndStatus(gtProviderName, "Booked");
				}
			}
			if (!verifyTrips.equals("")) {
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickSchedulesLink();
				verifyOpsGTSchedule(city, newgtName, checkOutDateValue, checkOutDateValue);
				if (scenarioName.contains("Scenario19") || scenarioName.contains("Scenario20"))
					gtOpsScheduleAirlines(tenantName, city, checkOutDateValue, checkOutDateValue, noOfRiders);
			}
		}
	}

	/*
	 * End -- Venue To Airport
	 * 
	 */

	/*
	 * Start -- Airport To Venue
	 * 
	 */

	@Test(description = "Verify planning trips of Venue To Airport", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void planning_AirportToVenue(String runMode, String sshIndicator, String queries, String createScheduleFlag,
			String tenantName, String bidMonth, String city, String sftpFolderPath, String scenarioFolderName,
			String command, String keyWordInLogs, String verifyTrips, String checkInDateValue, String checkOutDateValue,
			String gtName, String status, String scenarioName, String migrateFrom, String showTime, String pickupTime)
			throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing " + scenarioName);
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sshIndicator.equals("Yes")) {
				manageVenues(tenantName, city, "6", "BNE BOTH G6", "BNE TRNG CTR", "BNE VA 1,TNGC", "To Venue",
						"1,2,3,5,6,7");
				pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
				editGTSupplier(city, "Both", "CONTRACT,NON_CONTRACT", gtName, "30-Aug-2040");	
				Thread.sleep(2000);
				deletePairingsFromDB(queries);
				processPlanningAndMigrate(tenantName, bidMonth, city, sftpFolderPath, scenarioFolderName, command,
						keyWordInLogs, migrateFrom);
				pgWrapper.pageHome.clickLogoutLink();
			}
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservId = Arrays.asList(verifyTrips.split(","));
			for (int reservIdCount = 0; reservIdCount < reservId.size(); reservIdCount++) {
				pgWrapper.pageFindTrip.findTrips(reservId.get(reservIdCount), checkInDateValue);
				pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
				pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);
			}
			String showTimeValue = pgWrapper.pageFindTrip.showTime();
			String pickupTimeValue = pgWrapper.pageFindTrip.getpickUpTime();
			if (showTimeValue.equals(showTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "Show Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Time not displayed as expected");
			if (pickupTimeValue.equals(pickupTime))
				ExtentTestManager.getTest().log(LogStatus.PASS, "PickUp Time displayed as expected");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PickUP Time not displayed as expected");

			if (createScheduleFlag.equals("True")) {
				createSchedules(bidMonth, "GT", city, gtName);
				viewSchedules(bidMonth, "GT", city, gtName);
			}

			Thread.sleep(2000);
			if (!verifyTrips.equals(""))
				verifyOpsGTSchedule(city, gtName, checkInDateValue, checkInDateValue);
		}
	}

}
