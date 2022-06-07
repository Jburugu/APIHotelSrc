package com.APIHotels.tests.ACES3;

import java.io.IOException;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3_PaymentGroups_Regression_Suite extends LocalDriverManager{
	public PgWrapper pgWrapper;
	
	
	@Test(description = "JIRA# ATA-157, ATA-158, ATA-159 - Verify User is able to create Manual groups and assign employees.", groups = {
	"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void createManualGroupAndAddCrews(String tenant, String billingPeriodDate, String status, String invoiceNumber, String flightEmpName, String inFlightEmpName, String cityCode, String supplierName, String toBillingPeriodDate, String period) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenant);
		pgWrapper.aces3ClientHomePage.clickOnAccountingLink();
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceWithBillingPeriodAndStatus(billingPeriodDate, status);
		pgWrapper.aces3ClientFindInvoicePage.selectInvoiceLink(invoiceNumber);
		// Add Flight Payment Group
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.addEmployeesToGroup(flightEmpName, "Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
		
		// Add In-Flight Payment Group
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.addEmployeesToGroup(inFlightEmpName, "In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
		
		//Add Other Payment Group and assign remaining employees
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("Other");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Other");
		pgWrapper.aces3ClientTaxInvoicePage.mapAllUnassignedEmployeesToGroup("None");//Select all unassigned employees with filter None
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
		
		//pgWrapper.aces3ClientTaxInvoicePage.adjustUnallocatedAmount("Flight");
		
		// Click on Group Name link and verify emp details in new window
		pgWrapper.aces3ClientTaxInvoicePage.verifyEmpDetailsForGroup("Flight", flightEmpName);
		pgWrapper.aces3ClientTaxInvoicePage.verifyEmpDetailsForGroup("In-Flight", inFlightEmpName);
		
		//Consolidate Payment Groups and Approve Invoice
		pgWrapper.aces3ClientTaxInvoicePage.consolidatePaymentGroups();
		pgWrapper.aces3ClientTaxInvoicePage.approveInvoice();
		pgWrapper.aces3ClientFindInvoicePage.verifyInvoiceConsolidatedOrNot(invoiceNumber);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
		//Verifying consolidation in ACES3ACES -> Consolidated E-Invoice Report is pending as it is rejected (from Lavanya)
		getDriver().get(aces3AcesUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnConsolidatedEInvoiceReportLink();
		pgWrapper.aces3acesConsolidateEInvoiceReportPage.actualReport(tenant, cityCode, supplierName, billingPeriodDate, toBillingPeriodDate, period);
		pgWrapper.aces3acesConsolidateEInvoiceReportPage.verifyConsolidatedPaymentGroups();
	}

	@Test(description = "JIRA# ATA-154, 156 - Verify User is able to create Auto Payment groups and move employees between groups.", groups = {
	"Regression" }, dataProvider = "TestDataFile")
	public void createAutoGroup(String tenant, String billingPeriodDate, String status, String invoiceNumber, String flightEmpName, String inFlightEmpName) throws InterruptedException, IOException{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenant);
		pgWrapper.aces3ClientHomePage.clickOnAccountingLink();
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceWithBillingPeriodAndStatus(billingPeriodDate, status);
		pgWrapper.aces3ClientFindInvoicePage.selectInvoiceLink(invoiceNumber);
		// Create Groups (auto)
		pgWrapper.aces3ClientTaxInvoicePage.createAutoGroups();
		//Move employees between groups
		//From Flight to In-Flight
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.addEmployeesToGroup(flightEmpName, "In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
		//From in-Flight to Flight
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.addEmployeesToGroup(inFlightEmpName, "Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
		// Click on Group Name link and verify emp details in new window
		pgWrapper.aces3ClientTaxInvoicePage.verifyEmpSwitchToGroup("Flight", inFlightEmpName);
		pgWrapper.aces3ClientTaxInvoicePage.verifyEmpSwitchToGroup("In-Flight", flightEmpName);
		
	}
}
