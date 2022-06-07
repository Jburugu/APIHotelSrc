package com.APIHotels.tests.ACES3;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACES3ACES_Reports_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	@Test(description = "JIRA#143 Tax Exemption Report", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void taxExemptionReport(String month, String tenantName, String supplier, String exemptAmount,
			String taxTypes, String exempted) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnTaxExemptionReportLink();
		pgWrapper.aces3acesTaxExemptionReportPage.verifyTaxExemptionReport(month, tenantName, supplier, exemptAmount,
				taxTypes, exempted);
	}

	@Test(description = "JIRA#144 No Show Report", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void noShowReport(String month, String tenantName, String supplier, String billableNoShowsCount,
			String billableNoshowsNightsCount, String nonBillableNoshowsCount, String nonBillableNoshowsNightsCount)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnNoShowReportLink();
		pgWrapper.aces3acesNoShowReportPage.verifyNoShowReport(month, tenantName, supplier, billableNoShowsCount,
				billableNoshowsNightsCount, nonBillableNoshowsCount, nonBillableNoshowsNightsCount);

	}

	@Test(description = "JIRA#145 Crew ID Issue Report", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void crewIDIssueReport(String month, String tenantName, String billingPeriodDate) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnCrewIDIssuesReportLink();
		pgWrapper.aces3acesCrewIDIssuesPage.searchForCrewIDIssues(month, tenantName);

		/* Get supplier list and Counts list from ACES3ACES */
		List<List<String>> lists = pgWrapper.aces3acesCrewIDIssuesPage.getSuppliersAndCounts_CrewIDIssues();
		List<String> hotelNames = lists.get(0);
		List<String> issueCounts = lists.get(1);

		/*
		 * Login to aces3client application and verify the crewId Issues Count
		 */
		getDriver().get(aces3ClientUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenantName);
		pgWrapper.aces3ClientHomePage.clickOnAccountingLink();
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceUsingBillingPeriodAndStatus(billingPeriodDate);
		pgWrapper.aces3ClientTaxInvoicePage.verifyCrewIDIssueCounts(hotelNames, issueCounts);
		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceUsingBillingPeriodAndStatus(billingPeriodDate);
	}

	/* Data with Recoverable as "Yes" not found during trail run */
	@Test(description = "JIRA#147 Recoverable Tax Report", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void recoverableTaxReport(String tenantName, String cityCode, String supplierName, String billingPeriodDate,
			String recoverableValue, String taxTypeValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();

		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnRecoverableTaxReportLink();
		pgWrapper.aces3acesRecoverableTaxReportPage.searchAndVerifyRecoverableReport(tenantName, cityCode, supplierName,
				billingPeriodDate, recoverableValue, taxTypeValue);
	}

	@Test(description = "JIRA#148 Invoice Status Report", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void invoiceStatusReport(String runMode, String apiInvoiceNumber, String supplierInvoiceNumber,
			String invoiceStatusValue, String tenantName, String billingPeriodDate, String reportUsing,
			String selectSpecificStatus, String cityCode, String supplierName) throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Invoice status report executed for " + reportUsing + " status " + invoiceStatusValue);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
					readPropValue("aces3acesPassword"));
			pgWrapper.aces3acesHomePage.clickOnReportsLink();
			pgWrapper.aces3acesReportsPage.clickOnInvoiceStatusReportLink();
			pgWrapper.aces3acesInvoiceStatusReportPage.verifyInvoiceStatusWithInvoiceNumber(apiInvoiceNumber,
					supplierInvoiceNumber, invoiceStatusValue, reportUsing);
			pgWrapper.aces3acesInvoiceStatusReportPage.verifyInvoiceStatusUsingBiilingPeriodAndStatus(tenantName,
					billingPeriodDate, apiInvoiceNumber, invoiceStatusValue, reportUsing, selectSpecificStatus);
			pgWrapper.aces3acesInvoiceStatusReportPage.verifyInvoiceStatusWithSupplierAndLocation(tenantName,
					billingPeriodDate, cityCode, supplierName, apiInvoiceNumber, invoiceStatusValue, reportUsing);
		}
	}

	/*
	 * Data not found for pending cancellation and pending assignment
	 * cancellation
	 */
	@Test(description = "JIRA#149 Master ops Report", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void masterOpsReport(String runMode, String tenantName, String tripIdValue, String startDate, String endDate,
			String cityCode, String supplierName, String status) throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Master Ops status report executed for status " + status);
			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
			Map<String, List<String>> tripAndcrewId = pgWrapper.pageFindTrip.getCrewMembers(tripIdValue);

			getDriver().get(aces3AcesUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
					readPropValue("aces3acesPassword"));
			pgWrapper.aces3acesHomePage.clickOnReportsLink();
			pgWrapper.aces3acesReportsPage.clickOnReservationsLink();
			pgWrapper.aces3acesMasterOpsReportPage.verifyMasterOpsReport(tenantName, startDate, endDate, cityCode,
					supplierName, tripAndcrewId, status);
		}
	}

	@Test(description = "JIRA#150 Consolidate EInvoice Actual Report", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void consolidateEInvoiceReport(String runMode, String tenantName, String cityCode, String supplierName,
			String fromBillingPeriodDate, String toBillingPeriodDate, String period, String reportType)
			throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Verifying consolidated E invoice for " + reportType + " report " + period);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
					readPropValue("aces3acesPassword"));
			pgWrapper.aces3acesHomePage.clickOnReportsLink();
			pgWrapper.aces3acesReportsPage.clickOnConsolidatedEInvoiceReportLink();
			List<String> headersInConsolidatedReport = null;
			if (reportType.equalsIgnoreCase("Actual"))
				headersInConsolidatedReport = pgWrapper.aces3acesConsolidateEInvoiceReportPage.actualReport(tenantName,
						cityCode, supplierName, fromBillingPeriodDate, toBillingPeriodDate, period);
			else if (reportType.equalsIgnoreCase("Accrual"))
				headersInConsolidatedReport = pgWrapper.aces3acesConsolidateEInvoiceReportPage.accuralReport(tenantName,
						cityCode, supplierName, fromBillingPeriodDate, toBillingPeriodDate);
			else if (reportType.equalsIgnoreCase("Variance"))
				headersInConsolidatedReport = pgWrapper.aces3acesConsolidateEInvoiceReportPage.varianceReport(
						tenantName, cityCode, supplierName, fromBillingPeriodDate, toBillingPeriodDate, period);
			else
				Assert.fail("Report Type not found");
			Collections.sort(headersInConsolidatedReport);
			System.out.println(headersInConsolidatedReport);
			Thread.sleep(2000);
			pgWrapper.aces3acesHomePage.clickOnConfigurationLink();
			pgWrapper.aces3acesConfigurationPage.clickOnConfigureConsolidatedEInvoiceLink();
			List<String> headersInConfiguration = pgWrapper.aces3acesConfigurationPage
					.getSelectedColumnsFromConfigureConsolidatedEInvoice(tenantName);
			Collections.sort(headersInConfiguration);
			System.out.println(headersInConfiguration);
			if (headersInConsolidatedReport.equals(headersInConfiguration))
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Consolidated E Invocie Report displayed correct information");
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Consolidated E Invocie Report displayed incorrect information");

		}
	}

	@Test(description = "Reports -> Accrual Snapshot", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void accurualSnapshot(String tenantName, String fromBillingPeriodDate, String toBillingPeriodDate)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnAccrualSnapshotLink();
		pgWrapper.aces3acesAccurualSnapshotPage.accrualSnapshot(tenantName, fromBillingPeriodDate, toBillingPeriodDate);
	}

	@Test(description="JIRA#146 Accounting Financial Report" , groups = {"Regression" }, dataProvider = "TestDataFile")
	public void accountingFinancialReport_Batch(String runMode, String tenantName, String fromBillingPeriodDate, 
			String toBillingPeriodDate, String period, String reportType, String amount) throws Exception{
	
		if(runMode.equals("Yes")){
		ExtentTestManager.getTest().log(LogStatus.INFO,	"Executing Accounting Financial Report for Batch - " +reportType + " "
			+period+" report");
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"), readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnAccountingFinancialReportsLink();
		pgWrapper.aces3acesAccountingFinancialReportPage.accountingFinancialReportForBatch(tenantName, fromBillingPeriodDate, toBillingPeriodDate, period, reportType, amount);		
		
		
		}
	}
	
	@Test(description="JIRA#146 Accounting Financial Report" , groups = {"Regression"}, dataProvider = "TestDataFile")
	public void accountFinancialReport_Supplier(String runMode, String tenantName, String cityCode, String supplierName, String fromBillingPeriodDate, 
			String toBillingPeriodDate, String period, String reportType, String amount) throws Exception{
	
		if(runMode.equals("Yes")){
		ExtentTestManager.getTest().log(LogStatus.INFO,	"Executing Accounting Financial Report for Supplier - " +reportType + " "
			+period+" report");
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"), readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnAccountingFinancialReportsLink();
		pgWrapper.aces3acesAccountingFinancialReportPage.accoutingFinancialReportForSupplier(tenantName, cityCode, supplierName, reportType, fromBillingPeriodDate, toBillingPeriodDate, period, amount);
		
		}
	}
}
