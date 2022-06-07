package com.APIHotels.tests.ACES3;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.APIHotels.tests.ACESII.ACESII_Accounting_Regression_Suite;

public class ACES3_SupplierInvoices_Regression_Suite extends LocalDriverManager{
	
	public PgWrapper pgWrapper;
	public ACESII_Accounting_Regression_Suite aRS;
	
	private void verifyInvoicesInACES3Supplier(String tenantName, String city, String supplierName, String invoiceNumber) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnNewInvoicesLink();
		pgWrapper.aces3SuppliernewInvoicesPage.verifyInvoiceExist(invoiceNumber);
		
	}
	
	@Test(description = "Pre-requisite: Create Consolidated Invoice for one Manual and one Proforma Invoice", groups = {"Regression"}, priority = 0, dataProvider = "TestDataFile" )
	public void createConsolidatedInvoice(String tenantName, String serviceType, String cityValue,
			String supplier, String billingPeriod, String paidNoShowReason,
			String commentsPaidNoShows, String noOfRoomsPaidNoshows, String amountPaidNoShows) throws Exception {
		aRS = new ACESII_Accounting_Regression_Suite();
		String noValue = "";
		getDriver().quit();
		Driver dr = new Driver();
		EventFiringWebDriver driver2 = dr.createDriverInstance(nodeURL, "ie", URL);
		setWebDriver(driver2);
		setPageWrapper(driver2);
		pgWrapper = getPageWrapper();
		getDriver().get(aces2Url);
		
		//Create and approve Manual Invoice
		aRS.approveManualInvoice(readPropValue("username"), readPropValue("password"), tenantName, readPropValue("bidMonthIndex"), serviceType, cityValue, supplier,
				billingPeriod, noValue, paidNoShowReason, commentsPaidNoShows, noOfRoomsPaidNoshows,
				amountPaidNoShows, noValue, noValue, noValue, noValue, noValue, noValue);
		
		//Create Proforma Invoice
		if(serviceType.equals("0"))
			serviceType = "HOTEL";
		
		aRS.createProformaInvoiceSingle(tenantName, serviceType, cityValue, supplier, billingPeriod);
		pgWrapper.pageCreateProfarmaInvoices.writInvoiceNumToProp();
		pgWrapper.accountingMenu.clickFindInvoices();
		pgWrapper.pageFindInvoice.findByInvoiceNumber(readPropValue("ProformaInvoiceNumber"));
		pgWrapper.pageFindInvoice.reviewInvoice();

		//Create Consolidated Invoice
		
		pgWrapper.accountingMenu.clickCreateConsolidatedInvoices();
		pgWrapper.pageCreateConsolidatedInvoices.searchRefresh(serviceType);
		pgWrapper.pageCreateConsolidatedInvoices.clickOnReviewConsolidateInvoice();
		
	}
	
	@Test(description = "JIRA# ATA-88, ATA-89 - Verify that invoice created in ACES2 exists in ACES3 and the user is allowed to Accept the invoice and the same is moved to Reviewed invoices list", groups = {
	"Regression" }, dataProvider = "TestDataFile")
	public void verifyAcceptInvoices(String tenantName, String city, String supplierName) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		String invoiceNumber = readPropValue("ProformaInvoiceNumber");
		verifyInvoicesInACES3Supplier(tenantName, city, supplierName, invoiceNumber);
		pgWrapper.aces3SuppliernewInvoicesPage.acceptInvoice(invoiceNumber);
		pgWrapper.aces3SupplierHomePage.clickOnReviewedInvoicesLink();
		pgWrapper.aces3SupplierReviewedInvoicesPage.verifyStatus(invoiceNumber, "Accepted");
		verifyInvoiceStatusInAces2(tenantName, invoiceNumber, "ACCEPTED");
	}
	
	@Test(description = "JIRA# ATA-88, ATA-89 - Verify that invoice created in ACES2 exists in ACES3 and the user is allowed to Reject the invoice and the same is moved to Reviewed invoices list", groups = {
	"Regression" }, dataProvider = "TestDataFile")
	public void verifychallengeInvoices(String tenantName, String city, String supplierName,
			String comments, String firstName, String lastName, String phoneNumber, String email,
			String commentsForSupplier) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		String invoiceNumber = readPropValue("ManualInvoiceNumber");
		verifyInvoicesInACES3Supplier(tenantName, city, supplierName, invoiceNumber);
		pgWrapper.aces3SuppliernewInvoicesPage.challengeInvoice(invoiceNumber);
		pgWrapper.aces3SupplierChallegeInvoicePage.challengeInvoice(commentsForSupplier, firstName, lastName, phoneNumber, email);
		pgWrapper.aces3SupplierReviewedInvoicesPage.verifyStatus(invoiceNumber, "Challenged");
		verifyInvoiceStatusInAces2(tenantName, invoiceNumber, "CHALLENGED");
		pgWrapper.pageFindInvoice.resolveChallengedInvoice(commentsForSupplier);
		pgWrapper.pageFindInvoice.findInoviceIdByInvoiceNumber(invoiceNumber.split("-")[1]);
		pgWrapper.pageFindInvoice.clickSearchBtn_InvoiceNumber();
		pgWrapper.pageFindInvoice.verifyStatusForInvoice("RESOLVED");
	}
	
	private void verifyInvoiceStatusInAces2(String tenantName, String invoiceNumber, String expectedStatus) throws Exception{
		getDriver().quit();
		Driver dr = new Driver();
		EventFiringWebDriver driver2 = dr.createDriverInstance(nodeURL, "ie", URL);
		setWebDriver(driver2);
		setPageWrapper(driver2);
		pgWrapper = getPageWrapper();
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickFindInvoices();
		pgWrapper.pageFindInvoice.findInoviceIdByInvoiceNumber(invoiceNumber.split("-")[1]);
		pgWrapper.pageFindInvoice.clickSearchBtn_InvoiceNumber();
		pgWrapper.pageFindInvoice.verifyStatusForInvoice(expectedStatus);
	}
}
