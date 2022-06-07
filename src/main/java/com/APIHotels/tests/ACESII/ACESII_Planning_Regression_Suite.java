package com.APIHotels.tests.ACESII;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.ACESII.Page_GTSchedules;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_Planning_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	@Test(description = "TC_Planning_27 - Verify the trip details using 'Find Trip' option by entering flight number and period", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyTripDetails(String userName, String password, String tenantName, String bidMonthIndex,
			String tripIdValue, String timeFormatValue, String crewIdValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrip(tripIdValue, timeFormatValue, crewIdValue);
		pgWrapper.pageFindTrip.clickOnRetriveBtn();
		pgWrapper.pageFindTrip.verifyTripDetails(tripIdValue);

	}

	@Test(description = "TC_Planning_12 - Verify the user is able to create schedules for various bit periods", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createSchedulesWithDiffFilters(String userName, String password, String tenantName,
			String bidMonthIndex, String serviceType, String city, String supplier, String overwriteIndicator,
			String testScenario) throws Exception {
		pgWrapper = getPageWrapper();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testScenario);
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickCreateSchedulesLink();
		pgWrapper.createSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(supplier),
				overwriteIndicator);
		Thread.sleep(15000);
		pgWrapper.createSchedulesPage.clickOnCreateSchedules();
		pgWrapper.createSchedulesPage.waitForProcessingToComplete();
		pgWrapper.createSchedulesPage.verifyResults(city, serviceType, supplier);
	}

	@Test(description = "TC_Planning_13 - Verify the user is able to create new schedules by overwriting existing schedules", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void overwriteExistingSchedules(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city, String supplier, String overwriteIndicator,
			String changedOverwriteIndicator, String changedSupplier) throws Exception {
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickCreateSchedulesLink();
		pgWrapper.createSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(supplier),
				overwriteIndicator);
		Thread.sleep(15000);
		pgWrapper.createSchedulesPage.clickOnCreateSchedules();
		pgWrapper.createSchedulesPage.waitForProcessingToComplete();
		pgWrapper.createSchedulesPage.verifyResults(city, serviceType, supplier);
		// Change supplier, click overwrite option and click on create schedules
		pgWrapper.createSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(changedSupplier),
				changedOverwriteIndicator);
		pgWrapper.createSchedulesPage.verifyResults(city, serviceType, changedSupplier);
	}

	@Test(description = "TC_Planning_28 - Verify the Hotel schedules by selecting the City and Hotel details", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyHotelSchedules(String userName, String password, String tenantName, String bidMonthIndex,
			String city, String hotel) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickSchedulesLink();
		pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
		pgWrapper.pageHotelSchedules.findHotelSchedules(city, hotel);

	}

	@Test(description = "TC_Planning_29 - Verify the GT schedules by selecting the City and GT supplier details", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyGTSchedules(String userName, String password, String tenantName, String bidMonthIndex,
			String city, String hotel) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickSchedulesLink();
		pgWrapper.pageGTSchedules.clickGTSchedulesLink();
		pgWrapper.pageGTSchedules.verifyGTSchedules(city, hotel);

	}

	@Test(description = "TC_Planning_30 - Verify the ground transport Check -in and Check-out time for A/P to A/P schedule", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyChkInChkOutAPToAP(String userName, String password, String tenantName, String bidMonthIndex,
			String tripIdValue, String crewIdValue, String city, String hotel, String startDate, String endDate)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrip(tripIdValue, crewIdValue, endDate);
		pgWrapper.pageFindTrip.verifyPickupAndDropofftime(tripIdValue);
		String pickupTime = pgWrapper.pageFindTrip.getPickUpTime();
		String dropoffTime = pgWrapper.pageFindTrip.getDropOffTime();
		pgWrapper.opsDeskMenu.clickSchedulesLink();
		pgWrapper.pageGTSchedules.clickGTSchedulesLink();
		pgWrapper.pageGTSchedules.verifyGTSchedules(city, hotel, startDate, endDate);
		pgWrapper.pageGTSchedules.clickOnScheuldesSection();
		String pickupTimeGT = pgWrapper.pageGTSchedules.getPickupTime();
		String dropoffTimeGT = pgWrapper.pageGTSchedules.getDropoffTime();
		String startTime = Page_GTSchedules.removeCharAt(pickupTimeGT, 2);
		String endTime = Page_GTSchedules.removeCharAt(dropoffTimeGT, 2);
		Assert.assertEquals(startTime, pickupTime,
				"Find Trip Pick up time is not matching with the Pick up time on GT Schedules page");
		Assert.assertEquals(endTime, dropoffTime,
				"Find Trip Drop off time is not matching with the Pick up time on GT Schedules page");

	}

	@Test(description = "TC_Planning_15 - Verify the user is able to notify the supplier by adding notes in action column", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void acknowledgeSupplierWithNotes(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city, String supplier, String notifyIndicator, String confirmedWith,
			String hotelNotes) throws Exception {
		String noStatusFilter = "";
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(supplier), noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter,
				noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.notifyAndVerifyNotes(notifyIndicator, confirmedWith, hotelNotes);
		//pgWrapper.viewSchedulesPage.verifyAcknowledgeDateAndTime();
	}

	@Test(description = "TC_Planning_16 - Verify the 'Schedule Notes' option by adding notes in Footer Text, Standard Notes and Special Notes for Bit Period", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyScheduleNotesOption(String userName, String password, String tenantName, String bidMonthIndex,
			String commonNotes_ServiceType, String footerText, String standardNotes, String specialNotes,
			String serviceType, String city, String supplier) throws Exception {
		String noStatusFilter = "";
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickScheduleNotesLink();
		pgWrapper.scheduleNotesPage.setCommonNotes(commonNotes_ServiceType);
		pgWrapper.scheduleNotesPage.clickSearchBtn();
		pgWrapper.scheduleNotesPage.enterCommonNotesText(footerText, standardNotes, specialNotes);
		pgWrapper.scheduleNotesPage.clickSaveServiceNotesBtn();
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		int supplierId = -1;
		if (!supplier.isEmpty())
			supplierId = Integer.parseInt(supplier);
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, supplierId, noStatusFilter, noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.regenerateSchedules();
		pgWrapper.viewSchedulesPage.clickSupplierLinkAndVerifyNotes(footerText, standardNotes, specialNotes, userName,
				password);
	}

	@Test(description = "TC_Planning_21 - Verify whether the user is able to create and view the schedules", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createAndViewSchedules(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city, String supplier, String overwriteIndicator, String testScenario)
			throws Exception {
		String noStatusFilter = "";
		createSchedulesWithDiffFilters(userName, password, tenantName, bidMonthIndex, serviceType, city, supplier,
				overwriteIndicator, testScenario);
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		int supplierId = -1;
		if (!supplier.equals("-1"))
			supplierId = Integer.parseInt(supplier);
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, supplierId, noStatusFilter, noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.verifyResults(city, serviceType, supplier);
	}

	@Test(description = "TC_Planning_22 - Verify user able to Regenerate the schedules", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void regenerateSchedules(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city, String supplier, String notifyIndicator, String confirmedWith,
			String hotelNotes) throws Exception {
		String noStatusFilter = "";
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(supplier), noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter,
				noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		boolean ackonwledgedFlag = pgWrapper.viewSchedulesPage.verifyAcknowledgedDateExists();
		if (!ackonwledgedFlag) {
			pgWrapper.viewSchedulesPage.notifyAndVerifyNotes(notifyIndicator, confirmedWith, hotelNotes);
		}
		pgWrapper.viewSchedulesPage.regenerateSchedules();
		//pgWrapper.viewSchedulesPage.verifyRegenerateDateAndTime();
	}

	@Test(description = "TC_Planning_22 - Verify user able to Release the schedules", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void releaseSchedules(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city, String supplier) throws Exception {
		String noStatusFilter = "";
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(supplier), noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter,
				noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.releaseSchedules();
		//pgWrapper.viewSchedulesPage.verifyReleaseDateAndTime();
	}

	@Test(description = "TC_Planning_23 - Verify user able to Delete the schedules", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void deleteSchedule(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city, String supplier) throws Exception {
		String noStatusFilter = "";
		int supplierId = -1;
		if (!supplier.isEmpty())
			supplierId = Integer.parseInt(supplier);
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSchedulesLink();
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, supplierId, noStatusFilter, noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.deleteSchedule();
	}

	@Test(description = "TC_Planning_17 - Verify unassigned pairings can be manually assigned from the Assignments page", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void manualAssignFromAssignments(String userName, String password, String tenantName, String bidMonthIndex,
			String serviceType, String city) throws Exception {
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickPlanningLink();
		pgWrapper.planningMenu.clickAssignmentLink();
		pgWrapper.assignmentsPage.searchAssignments(serviceType, city);
		if (serviceType.equals("HOTEL"))
			pgWrapper.assignmentsPage.assignHotelSupplierToReservation();
		else if (serviceType.equals("GT"))
			pgWrapper.assignmentsPage.assignGTSupplierToReservation();
	}

	@Test(description = "TC_Planning_18 - Verify unassigned pairings can be manually assigned from the Errors & Warnings page", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void manualAssignFromErrorsWarnings(String userName, String password, String tenantName,
			String bidMonthIndex) throws Exception {
		getDriver().quit();
		Driver dr = new Driver();
		EventFiringWebDriver driver2 = dr.createDriverInstance(nodeURL, "chrome", URL);
		setWebDriver(driver2);
		setPageWrapper(driver2);
		pgWrapper = getPageWrapper();
		driver2.get(URL);
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickPlanningLink();
		pgWrapper.planningMenu.clickErrorsWarningsLink();
		pgWrapper.errorsWarningsPage.searchAndSelectAssignmentErrors();
		pgWrapper.assignmentsPage.assignHotelSupplierToReservationFromErrorsAndWarnings();
	}

	@Test(description = "TC_Planning_9,19,20 Clear Errors and Warnings and Bulk Assign", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void clearErrorsAndWarnings(String userName, String password, String tenantName, String bidMonthIndex) throws Exception{
		pgWrapper = getPageWrapper();
		String keepTbaIndicator = "Yes", keepManualIndicator = "Yes", keepAsWarningIndicator = "Yes";
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickPlanningLink();
		pgWrapper.planningMenu.clickBulkAssignLink();
		pgWrapper.bulkAssignPage.bulkAssignPairings(keepTbaIndicator, keepManualIndicator, keepAsWarningIndicator);
		EventFiringWebDriver ieDriver = getDriver();
		
		Driver dr = new Driver();
		EventFiringWebDriver chromeDriver = dr.createDriverInstance(nodeURL, "chrome", URL);
		setWebDriver(chromeDriver);
		setPageWrapper(chromeDriver);
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickPlanningLink();
		pgWrapper.planningMenu.clickErrorsWarningsLink();
		pgWrapper.errorsWarningsPage.searchAndProcessErrorsAndWarnings(ieDriver);
	}
	
	@Test(description = "Bulk Assign", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void bulkAssign(String userName, String password, String tenantName, String bidMonthIndex) throws Exception{
		String keepTbaIndicator = "Yes", keepManualIndicator = "Yes", keepAsWarningIndicator = "Yes";
		pgWrapper = getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickPlanningLink();
		pgWrapper.planningMenu.clickBulkAssignLink();
		pgWrapper.bulkAssignPage.bulkAssignPairings(keepTbaIndicator, keepManualIndicator, keepAsWarningIndicator);
	}

}
