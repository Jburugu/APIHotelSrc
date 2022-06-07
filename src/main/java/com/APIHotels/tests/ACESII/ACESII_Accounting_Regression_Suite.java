package com.APIHotels.tests.ACESII;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.Utilities.XlsWorkbook;
import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_Accounting_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	@Test(description = "Verify Create Proforma Invoice Single", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createProformaInvoiceSingle(String tenantName, String serviceType, String cityJFK,
			String selectSupplier, String currentMMMYYYY) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickCreateProformaInvoicesLink();
		pgWrapper.pageCreateProfarmaInvoices.createProformaInvoiceSingle(serviceType, cityJFK, selectSupplier,
				currentMMMYYYY);
		pgWrapper.pageCreateProfarmaInvoices.clickOnCreateInvoiceBtnSingleInvoice();
		try {
			// pgWrapper.pageCreateProfarmaInvoices.clickAlertOk();
			pgWrapper.pageCreateProfarmaInvoices.waitForInProgressToComplete();
		} catch (Exception e) {
			pgWrapper.pageCreateProfarmaInvoices.waitForInProgressToComplete();
		}
	}

	@Test(description = "JIRA#181 Verify that the Single GT invoice is allowed to be created for a supplier", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createSingleGTEInvoice(String tenantName, String city, String supplier, String billingPeriod)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);

	}

	@Test(description = "JIRA#183 Verify that the monthly Batch invoices are allowed to be created for the whole month", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createMonthlyBatchGTEInvoice(String tenantName, String billingPeriod) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createMonthlyBatchGTEInvoice(billingPeriod);
	}

	@Test(description = "JIRA#183 Verify that the BiMonthly Batch invoices are allowed to be created", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createBiMonthlyBatchGTEInvoice(String tenantName, String biMonthlyBillingPeriod) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createBiMonthlyBatchGTEInvoice(biMonthlyBillingPeriod);
	}

	@Test(description = "Verify Invoice Status Actions APPROVE, VOID, COMMENTS and HISTORY for BiMontly invoice", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceActionForBiMonthly(String runMode, String tenantName, String biMonthlyBillingPeriod,
			String supplierinvoiceNumberValue, String paymentTermsText, String commentText, String status,
			String actionType) throws Exception {

		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Verifying " + actionType + " action for BiMonthly Invoice");

			createBiMonthlyBatchGTEInvoice(tenantName, biMonthlyBillingPeriod);
			if (actionType.equalsIgnoreCase("approve")) {
				String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoice(supplierinvoiceNumberValue,
						paymentTermsText);
				System.out.println(apiNumber);
				pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
				pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(apiNumber);
				pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(apiNumber, status);
			} else if (actionType.equalsIgnoreCase("void")) {
				String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.voidInvoice(supplierinvoiceNumberValue,
						paymentTermsText, commentText);
				System.out.println(apiNumber);
				pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
				pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(apiNumber);
				pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(apiNumber, status);
			}
		}
	}

	@Test(description = "Verify Invoice Status Actions APPROVE, VOID, COMMENTS and HISTORY for Monthly invoice", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceActionForMonthly(String runMode, String tenantName, String monthBillingPeriod,
			String supplierinvoiceNumberValue, String paymentTermsText, String commentText, String status,
			String actionType) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying " + actionType + " action for Monthly Invoice");

			createMonthlyBatchGTEInvoice(tenantName, monthBillingPeriod);
			if (actionType.equalsIgnoreCase("approve")) {
				String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoice(supplierinvoiceNumberValue,
						paymentTermsText);
				System.out.println(apiNumber);
				pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
				pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(apiNumber, status);
			} else if (actionType.equalsIgnoreCase("void")) {
				String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.voidInvoice(supplierinvoiceNumberValue,
						paymentTermsText, commentText);
				System.out.println(apiNumber);
				pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
				pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(apiNumber, status);
			}
		}

	}

	@Test(description = "JIRA#ATA-185 Verify that the Find by Supplier Name m, Find by Invoice number and Find by Billing period and status retrieve the search results correspondingly", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyfindGTEInvoices(String tenantName, String cityCode, String supplierName, String invoiceNumber,
			String billingMonth) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceBySupplier(cityCode, supplierName);
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.findByBillingPeriodAndStatus(billingMonth);

	}

	@Test(description = "JIRA#ATA-187 Apply the currency conversion applications, and verify that the invoice total and pick up details are updated with currency", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifycurrencyConversion(String tenantName, String billingMonth, String conversionRateValue,
			String currencyValue, String paymentSourceValue, String paymentDateValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findByOpenStatus(billingMonth);
		pgWrapper.pageGTInvoicingFindInvoice.verifyCurrencyConversion(billingMonth, conversionRateValue, currencyValue,
				paymentSourceValue, paymentDateValue);
	}

	@Test(description = "JIRA#ATA-188 & 189  Verify the pickups are allowed to edit, delete and add entries AND Verify the added pickups entry gets added and shows in By Day Summary", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifypickUpActions(String tenantName, String billingMonth, String pickupDate, String pickupLocation,
			String dropoffLocation, String paxValue, String rateValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findByOpenStatus(billingMonth);
		pgWrapper.pageGTInvoicingFindInvoice.verifyAddedPickUpsActions(pickupDate, pickupLocation, dropoffLocation,
				paxValue, rateValue);
	}

	@Test(description = "JIRA#ATA-190  Verify that the adjustments added gets added to the invoice, and shows in invoice summary correctly", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyAdjustmentsActions(String tenantName, String billingMonth, String reason, String comments,
			String amount) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findByOpenStatus(billingMonth);
		pgWrapper.pageGTInvoicingFindInvoice.verifyAdjustmentsActions(reason, comments, amount);
	}

	@Test(description = "JIRA#ATA-191  Verify the attachments are allowed to added to the invoice", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyAttachementsAllowed(String tenantName, String billingMonth, String commentsForAttachement)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findByOpenStatus(billingMonth);
		pgWrapper.pageGTInvoicingFindInvoice.addAttachement(commentsForAttachement);
	}

	@Test(description = "JIRA#ATA-188 & 189  Verify the pickups are allowed to edit, delete and add entries AND Verify the added pickups entry gets added and shows in By Day Summary", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyPickupDetailsTable(String tenantName, String billingMonth, String pageSizes) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findByOpenStatus(billingMonth);
		pgWrapper.pageGTInvoicingFindInvoice.pickUpPaginationAndPageSize();
		pgWrapper.pageGTInvoicingFindInvoice.verifyPageSize(pageSizes);
		pgWrapper.pageGTInvoicingFindInvoice.verifyExportOptions();
	}
	/* GT Invoicing test cases End */

	@Test(description = "ATA-192  : Verify the manual gt einvoice creation process and verify the same is displayed in Find invoice	", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyCreateGTManulInvoice(String tenantName, String cityValue, String supplierValue,
			String billingPeriodValue, String currencyValue, String reasonValue, String commentsAdj,
			String noOfPickupsValue, String amountAdj, String invoiceComments, String approvedStatus,String expectedStatus) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
//		pgWrapper.accountingMenu.clickGtInvoicing();
		String invoiceNumber = pgWrapper.aces2GTInvoicingCreateManualInvoicePage.createGTManualInvoice(cityValue,
				supplierValue, billingPeriodValue, currencyValue, reasonValue, commentsAdj, noOfPickupsValue, amountAdj,
				invoiceComments);
		System.out.println("Invoice Number is :" + invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(invoiceNumber, expectedStatus);

	}

	// Test data : There should be data in the system to create invoice for any
	// month, based on the availability we have to update month and year in the
	// test data sheet
	@Test(description = "ATA-207,208,209,210,211  : Nota fiscal upload through ACES", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyCreateGTApproveAndAccept(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger, String city, String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String commentText, String acceptedStatus, String finalInvoiceStatus)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		// configuring the supplier to have accepted nota fiscal invoice export
		// trigger
		verifyGTConfiguration(tenantName, supplier, invoiceExportFreq, inoviceExportTrigger);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);
		String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoiceInvExp(supplierinvoiceNumberValue,
				paymentTermsText);
		String invoiceNumber = apiNumber.substring(2, 8);
		System.out.println(invoiceNumber);
		writeProp("auto_billigPeriodInvoiceStatusATA207", billingPeriod);
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTInvoicetab();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
		pgWrapper.airlinesGroundTransportationPage.clickOnPerformActionsBtn();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(invoiceNumber, acceptedStatus);

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.addNotaFisAttToInvoice(invoiceNumber);
		pgWrapper.pageHome.clickLogoutLink();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTApprovedInvoiceNotaFiscal();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
		pgWrapper.airlinesGroundTransportationPage.clickOnPerformActionsBtn();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(invoiceNumber, finalInvoiceStatus);
		pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceExportElementsCheck();

	}

	public void verifyGTConfiguration(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger) throws Exception {
		pgWrapper.pageHome.clickAdminLink();
		pgWrapper.adminMenu.clickConfigureGTEInvoiceLink();
		pgWrapper.pageGtEInvoiceConfiguration.supplierGTConfig(tenantName, supplier, invoiceExportFreq,
				inoviceExportTrigger);
	}

	// Test data : There should be data in the system to create invoice for any
	// month, based on the availability we have to update month and year in the
	// test data sheet
	@Test(description = "ATA-212, ATA-213 : Verify that on configuring invoice export as Approved, allows the invoice to export only when the invoice status is approved", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceExportOnApprove(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger, String city, String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String commentText, String acceptedStatus, String finalInvoiceStatus)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		verifyGTConfiguration(tenantName, supplier, invoiceExportFreq, inoviceExportTrigger);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);
		String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoice(supplierinvoiceNumberValue,
				paymentTermsText);
		System.out.println(apiNumber);
		writeProp("auto_billigPeriodInvoiceStatusATA212", billingPeriod);
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(apiNumber, acceptedStatus);
		pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceExportElementsCheck();
		pgWrapper.findGTInvoicePage.downloadXMLFileAndValidate(apiNumber, supplier, city, acceptedStatus);
		pgWrapper.findGTInvoicePage.verifyOtherInvoiceStatuses(readPropValue("auto_billigPeriodInvoiceStatusATA212"),
				apiNumber);

	}

	@Test(description = "ATA-193 to ATA-198 Action GT invoice queue and Notafiscal queue validations", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyAprvdInvExistsInGTQueue(String tenantName, String city, String supplier, String billingPeriod,
			String supplierinvoiceNumberValue, String paymentTermsText, String comments) throws Exception {

		// Test data : There should be data in the system to create invoice for
		// any
		// month, based on the availability we have to update month and year in
		// the
		// test data sheet
		pgWrapper = LocalDriverManager.getPageWrapper();
		createSingleGTEInvoice(tenantName, city, supplier, billingPeriod);
		String invoiceNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoice(supplierinvoiceNumberValue,
				paymentTermsText);
		// pgWrapper.pageHome.clickLogoutLink();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.accountingTab.clickOnAccountingAndGroundTransportationLinks();
		pgWrapper.accountingTab.clickOnActionGTInvoiceLink();
		pgWrapper.actionGTInvoicePage.verifyApproveInvoiceExists(invoiceNumber);
		// Reject Invoice
		pgWrapper.actionGTInvoicePage.performActionOnInvoice("Reject", invoiceNumber, comments);

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(invoiceNumber, "REJECTED");
		pgWrapper.pageGTInvoicingFindInvoice.approveInvoice(supplierinvoiceNumberValue, paymentTermsText);
		// pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceStatus(invoiceNumber, "APPROVED");
		pgWrapper.pageHome.clickLogoutLink();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.accountingTab.clickOnAccountingAndGroundTransportationLinks();
		pgWrapper.accountingTab.clickOnActionGTInvoiceLink();
		pgWrapper.actionGTInvoicePage.verifyApproveInvoiceExists(invoiceNumber);
		// Accept Invoice
		pgWrapper.actionGTInvoicePage.performActionOnInvoice("Accept", invoiceNumber, comments);

		// Verify Nota fiscal queue in ACES or Supplier site
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.addNotaFisAttToInvoice(invoiceNumber);
		pgWrapper.pageHome.clickLogoutLink();

		/*
		 * Login to Airlines site and navigate to
		 * ActionGTApprovedInvoiceNotaFiscal Queue Verify invoice exists
		 */

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTApprovedInvoiceNotaFiscal();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
	}

	@Test(description = "ATA-214, ATA-215 : Verify that on configuring invoice export as Accepted, allows the invoice to export only when the invoice status is accepted", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceExportOnAccepted(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger, String city, String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String commentText, String acceptedStatus, String finalInvoiceStatus)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		verifyGTConfiguration(tenantName, supplier, invoiceExportFreq, inoviceExportTrigger);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);
		String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoiceInvExp(supplierinvoiceNumberValue,
				paymentTermsText);
		String invoiceNumber = apiNumber.substring(2, 8);
		System.out.println(invoiceNumber);
		writeProp("auto_billigPeriodInvoiceStatusATA214", billingPeriod);

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTInvoicetab();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
		pgWrapper.airlinesGroundTransportationPage.clickOnPerformActionsBtn();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(invoiceNumber, acceptedStatus);
		pgWrapper.pageGTInvoicingFindInvoice.verifyInvoiceExportElementsCheck();
		pgWrapper.pageGTInvoicingFindInvoice.downloadXMLFile(apiNumber);

	}

	// Test data : There should be data in the system to create invoice for any
	// month, based on the availability we have to update month and year in the
	// test data sheet
	@Test(description = "ATA-218 Verify that when no export is configured, Invoice should not show export to xml check box or XML export link for any status of invoice", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceExportOnNoTrigger(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger, String city, String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String commentText, String acceptedStatus, String finalInvoiceStatus)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		verifyGTConfiguration(tenantName, supplier, invoiceExportFreq, inoviceExportTrigger);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);
		String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoiceInvExp(supplierinvoiceNumberValue,
				paymentTermsText);
		String invoiceNumber = apiNumber.substring(2, 8);
		System.out.println(invoiceNumber);
		writeProp("auto_billigPeriodInvoiceStatusATA214", billingPeriod);

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTInvoicetab();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
		pgWrapper.airlinesGroundTransportationPage.clickOnPerformActionsBtn();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(invoiceNumber, acceptedStatus);
		pgWrapper.pageGTInvoicingFindInvoice.checkXMLElementVisibiltyOnNoTrigger();
		pgWrapper.pageGTInvoicingFindInvoice.clickLogoutAirlines();

	}

	public void verifyNotaFiscalUploadThruSS(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger, String city, String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String comments) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		verifyGTConfiguration(tenantName, supplier, invoiceExportFreq, inoviceExportTrigger);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);
		String invoiceNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoice(supplierinvoiceNumberValue,
				paymentTermsText);
		System.out.println(invoiceNumber);

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.accountingTab.clickOnActionGTInvoiceLink();
		pgWrapper.actionGTInvoicePage.verifyApproveInvoiceExists(invoiceNumber);
		// Accept Invoice
		pgWrapper.actionGTInvoicePage.performActionOnInvoice("Accept", invoiceNumber, comments);

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.addNotaFisAttToInvoice(invoiceNumber);
		pgWrapper.pageHome.clickLogoutLink();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("azulUsername"), readPropValue("azulPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTApprovedInvoiceNotaFiscal();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
		pgWrapper.airlinesGroundTransportationPage.clickOnPerformActionsBtn();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(invoiceNumber, "");// Invoice
																					// status
																					// to
																					// be
																					// added
		pgWrapper.pageGTInvoicingFindInvoice.checkXMLElementVisibiltyOnNoTrigger();
	}

	@Test(description = "ATA-359 Create Batch Proforma Invoice", groups = {
			"Functional" }, dataProvider = "TestDataFile")
	public void createProformaInvoice(String tenantName, String serviceType, String billingPeriod, String runMode)
			throws Exception {
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickAccountingLink();
			// Create Proforma Invoice
			writeProp("ProformaCreatedDate", getDateInFormat("DD-MMM-YYYY"));
			pgWrapper.accountingMenu.clickCreateProformaInvoicesLink();
			pgWrapper.pageCreateProfarmaInvoices.createProformaInvoiceMonthly(serviceType, billingPeriod);
			pgWrapper.pageCreateProfarmaInvoices.clickOnCreateInvoiceBtnMonthlyBatchInvoice();
			try {
				pgWrapper.pageCreateProfarmaInvoices.clickAlertOk();
				pgWrapper.pageCreateProfarmaInvoices.waitForInProgressToComplete();
			} catch (Exception e) {
				pgWrapper.pageCreateProfarmaInvoices.waitForInProgressToComplete();
			}
		}
	}

	@Test(description = "ATA-359 Bulk Review Proforma Invoices and Create Consolidated Invoices", groups = {
			"Functional" }, dataProvider = "TestDataFile")
	public void bulkReview(String tenantName, String billingMonthYear, String runMode) throws Exception {
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickAccountingLink();
			// Bulk Review the Proforma Invoices created
			pgWrapper.pageCreateProfarmaInvoices.runBulkReviewQuery(tenantName, billingMonthYear);
		}
	}

	@Test(description = "ATA-359, ATA-362 Bulk Review Proforma Invoices and Create Consolidated Invoices", groups = {
			"Functional" }, dataProvider = "TestDataFile")
	public void createConsolidatedInvoice(String tenantName, String billingMonthYear, String serviceType,
			String runMode) throws Exception {
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickAccountingLink();
			System.out.println(getDateInFormat("dd-MMM-YYYY"));
			pgWrapper.pageCreateConsolidatedInvoices.createConsolidatedInvoice(serviceType);
			pgWrapper.pageCreateConsolidatedInvoices.reviewConsolidatedInvoice();
		}
	}

	@Test(description = "ATA-363 Save Consolidated Invoices and Total Values from ACES for comparison", groups = {
			"Functional" }, dataProvider = "TestDataFile")
	public void saveValuesForComparison_ACES(String tenantName, String serviceType, String runMode) throws Exception {
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickAccountingLink();
			// View Consolidated Invoices
			pgWrapper.pageViewConsolidatedInvoices.clickOnViewConsolidatedInvoices();
			pgWrapper.pageViewConsolidatedInvoices.viewConsolidatedInvoices(serviceType);
			pgWrapper.pageViewConsolidatedInvoices.viewConsolidatedInvoiceByCurrentMonth();
			HashMap<String, String> invoiceValuesMap = pgWrapper.pageViewConsolidatedInvoices
					.fetchValuesFromInvoiceTables(tenantName);
			pgWrapper.pageViewConsolidatedInvoices.storeValuesForComparisonInProp(invoiceValuesMap,
					tenantName.replace(" ", ""));
		}
	}

	@Test(description = "ATA-364 Accounting Tab  - Room Usage page verification", dataProvider = "TestDataFile", groups = "ExpressJetERJ")
	public void roomUsageReport(String tenantName, String billingMonthYear, String radioBtnType, String runMode)
			throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			pgWrapper.accountingTab.clickOnRoomUsageLink();
			if (radioBtnType.equals("ConsolidatedInvoiceMonth"))
				pgWrapper.roomUsagePage.clickOnConsolidatedInvoiceMonth(billingMonthYear);
			else if (radioBtnType.equals("InvoiceMonth"))
				pgWrapper.roomUsagePage.clickonInvoiceMonth(billingMonthYear);
			pgWrapper.roomUsagePage.clickOnRetrieve(tenantName, radioBtnType);
		}
	}

	@Test(description = "ATA-365 ATA-366 Save Consolidated Invoices and Total Values from ACES for comparison", groups = {
			"Functional" })
	public void compareValuesFromCIToRUR(String tenantName, String radioBtnType) throws IOException {

			XlsWorkbook rurWorkbook = new XlsWorkbook(downloadFilepath + File.separator + "RuR"
					+ File.separator + tenantName.replace(" ", "_") +  radioBtnType + "_RUR.xlsx");
			DataTable rurReport = rurWorkbook.getTestDataTable(0);
			
			String totalRooms = readPropValue("ACES_TotalRooms_"+tenantName.replace(" ", ""));
			compareTotalValues(rurReport, tenantName, "TotalRooms", totalRooms);

			String totalAmount = readPropValue("ACES_TotalAmount_"+tenantName.replace(" ", ""));
			String totalEDP = readPropValue("ACES_TotalEPD_"+tenantName.replace(" ", ""));

			String total = String.valueOf(
					Math.round((Double.parseDouble(totalAmount) + Double.parseDouble(totalEDP)) * 100.00) / 100.00);
			if (total.split("\\.")[1].equals("0") || total.split("\\.")[1].equals("00"))
				total = total.split("\\.")[0];
			compareTotalValues(rurReport, tenantName, "TotalAmount", total);
		
	}

	private void compareTotalValues(DataTable rurReport, String tenantName, String fieldName, String acesValue)
			throws IOException {
		int totalRows = rurReport.getNoOfDataRowsForNonTable();
		switch (fieldName) {
		case "TotalRooms":
			String totalRoomsFromRUR = rurReport.getFieldValueFromNonTable(totalRows, "# Rooms billed");
			totalRoomsFromRUR = totalRoomsFromRUR.replace(",", "");
			assertEquals(totalRoomsFromRUR, acesValue, "TotalRoomsValue Mismtach!");
			break;
		case "TotalAmount":
			String totalAmountFromRUR = rurReport.getFieldValueFromNonTable(totalRows, "Total");
			totalAmountFromRUR = totalAmountFromRUR.replace(",", "");
			assertEquals(totalAmountFromRUR, acesValue, "TotalAmountValue Mismtach!");
			break;
		}
	}

	@Test(description = "ATA-367 Accounting Tab  - GT Usage page verification", dataProvider = "TestDataFile", groups = "ExpressJetERJ")
	public void GTUsageReport(String tenantName, String billingMonthYear, String radioBtnType, String runMode) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "UserName"),
				readPropValue(tenantName.replace(" ", "") + "Password"));

		pgWrapper.accountingTab.clickOnGTUsageLink();
		if(radioBtnType.equals("ConsolidatedInvoiceMonth"))
			pgWrapper.gtUsagePage.generateRuRForConsolidatedInvoiceMonth(billingMonthYear);
		else if(radioBtnType.equals("InvoiceMonth")){
			pgWrapper.gtUsagePage.clickOninvoiceMonthRadioButton();
			pgWrapper.gtUsagePage.generateGLCodeReprotForInvoiceMonth(billingMonthYear);
		}
		pgWrapper.gtUsagePage.clickOnRetrieveButton(tenantName, radioBtnType);
	}

	@Test(description = "ATA-370 Reports Tab  - Ops Reports Verification", dataProvider = "TestDataFile", groups = "ExpressJetERJ")
	public void downloadOpsReports(String tenantName, String agent, String startDate, String endDate, String startTime,
			String endTime, String serviceType) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickReportsLink();
		pgWrapper.reportsMenu.clickOpsReportsLink();
		pgWrapper.pageAgentReport.clickAgentReportLink();
		pgWrapper.pageAgentReport.viewAgentReport(Integer.parseInt(agent), startDate, endDate,
				Integer.parseInt(startTime), Integer.parseInt(endTime));
		pgWrapper.pageAirlineReport.clickAirlineReport();
		pgWrapper.pageAirlineReport.viewAirlineReport(tenantName, startDate, endDate, Integer.parseInt(startTime),
				Integer.parseInt(endTime));
		pgWrapper.pageTripTrackingReport.clickTripTrackingReportLink();
		pgWrapper.pageTripTrackingReport.viewTripTrackingReport(Integer.parseInt(agent), startDate, endDate,
				Integer.parseInt(startTime), Integer.parseInt(endTime), serviceType);

	}

	@Test(description = "JIRA #ATA-368 Planning reports validation: 1. Daily usage report 2. Low watermark report 3. Monthly activity report", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void downloadPlanningReports(String runMode, String tenantName, String bidperiodValue, String exportFormat,
			String reportType, String fileExt) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Executing test to download reports in Accouting -> Planning Reports ->  " + reportType);
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName + "Username"),
					readPropValue(tenantName + "Password"));
			if (reportType.equalsIgnoreCase("DAILYUSAGEREPORT")) {
				pgWrapper.reportsTab.clickOnDailyUsageReportLink();
				pgWrapper.planningReportsPage.verifyDailyUsageReport(bidperiodValue, exportFormat, reportType, fileExt);
			} else if (reportType.equalsIgnoreCase("LOWWATERMARKREPORT")) {
				pgWrapper.reportsTab.clickOnLowWaterMarkReportLink();
				pgWrapper.planningReportsPage.verifyLowWaterMarkReport(bidperiodValue, exportFormat, reportType,
						fileExt);
			} else if (reportType.equalsIgnoreCase("MONTHLYACTIVITYREPORT")) {
				pgWrapper.reportsTab.clickOnMonthlyActivityReportLink();
				pgWrapper.planningReportsPage.verifyMonthlyActivityReport(bidperiodValue, exportFormat, reportType,
						fileExt);
			} else
				Assert.fail("Report Type Not Found");
		}
	}

	@Test(description = "JIRA #ATA-369 Accounting reports : 1. GL code report (Hotel and GT)", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void glCodeReportForHtAndGt(String runMode, String tenantName, String serviceType,
			String bulkProformaRunDate) throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName + "Username"),
					readPropValue(tenantName + "Password"));
			pgWrapper.reportsTab.clickOnGLCodeReportLink();
			pgWrapper.glCodeReportPage.verifyGeneratingGLCodeReport(serviceType);

		}
	}

	@Test(description = "JIRA #ATA-371 Validate Supplier invoices in airline site", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void supplierInvoicesInAirines(String runMode, String tenantName, String billingMonth, String billingYear,
			String serviceType) {
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName + "Username"),
					readPropValue(tenantName + "Password"));
			pgWrapper.accountingTab.clickOnSupplierInvoicesLink();
			pgWrapper.supplierInvoicesPage.verifySupplierInvoices(billingMonth, billingYear, serviceType);
		}
	}

	@Test(description = "ATA -361 | Create manual invoice with reason codes and approve the same", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void approveManualInvoice(String tenantName, String bidMonthIndex, String serviceType, String cityValue,
			String supplier, String currentMMMYYYY, String currencyUSDCreateManlInv, String indexValue1,
			String commentsPaidNoShows, String noOfRoomsPaidNoshows, String amountPaidNoShows, String commentsAdj,
			String amountAdj, String taxTypeValue, String taxName, String noOfRoomsAdj, String taxComments,
			String taxAmt, String commentCreateManInv) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickAccountingLink();
		// create Manual Invoice and approve
		pgWrapper.pageCreateManualInvoice.clickOnCreateManualInvoiceTab();
		pgWrapper.pageCreateManualInvoice.createManualInvoiceRegM(serviceType, cityValue, supplier, currentMMMYYYY,
				currencyUSDCreateManlInv, indexValue1, commentsPaidNoShows, noOfRoomsPaidNoshows, amountPaidNoShows,
				commentsAdj, amountAdj, taxTypeValue, taxName, noOfRoomsAdj, taxComments, taxAmt, commentCreateManInv);
		pgWrapper.pageApproveManualInvoice.approveManualInvoice();
		pgWrapper.pageApproveManualInvoice.openInvoiceAndApprove();
		String invoiceNumber = readPropValue("ManualInvoiceNumber");
		String invoiceNumberNumeric = invoiceNumber.substring(2, 8);
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.pageFindInvoice.clickOnFindInvoice();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumberNumeric);// clickOnFindInvoicesLink
		pgWrapper.pageFindInvoice.verifyApprovedStatusOfInvoice("APPROVED");

	}

	@Test(description = "ATA-380 | Verify that the ACCEPTED invoice should not be available in ACESAIR's \"Action GT Approved Invoice (Nota Fiscal)\" section, once the attachments are removed", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void VerifyInvOnNotaFisAttRemoval(String tenantName, String supplier, String invoiceExportFreq,
			String inoviceExportTrigger, String city, String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String commentText, String acceptedStatus, String finalInvoiceStatus)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		// configuring the supplier to have accepted nota fiscal invoice export
		// trigger
		// verifyGTConfiguration(tenantName, supplier, invoiceExportFreq,
		// inoviceExportTrigger);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingCreateInvoice.createSingleGTEInvoice(city, supplier, billingPeriod);
		String apiNumber = pgWrapper.pageGTInvoicingCreateInvoice.approveInvoiceInvExp(supplierinvoiceNumberValue,
				paymentTermsText);
		// String invoiceNumber = "732720";
		String invoiceNumber = apiNumber.substring(2, 8);
		System.out.println(invoiceNumber);
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AzulAirlinesUsername"),
				readPropValue("AzulAirlinesPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTInvoicetab();
		pgWrapper.airlinesGroundTransportationPage.acceptGTInvoice(invoiceNumber);
		pgWrapper.airlinesGroundTransportationPage.clickOnPerformActionsBtn();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByNumber(invoiceNumber, acceptedStatus);

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.addNotaFisAttToInvoice(invoiceNumber);
		pgWrapper.pageHome.clickLogoutLink();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AzulAirlinesUsername"),
				readPropValue("AzulAirlinesPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTApprovedInvoiceNotaFiscal();
		pgWrapper.airlinesGroundTransportationPage.verifyGTInvoiceShown(invoiceNumber);

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingFindInvoice.clickOnFindInvoicesLink();
		pgWrapper.pageGTInvoicingFindInvoice.findInvoiceByInvoiceNumber(invoiceNumber);
		pgWrapper.pageGTInvoicingFindInvoice.deleteNotaFisAttachment(invoiceNumber);

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AzulAirlinesUsername"),
				readPropValue("AzulAirlinesPassword"));
		pgWrapper.airlinesGroundTransportationPage.clickOnAccountingTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnGroundTransportationTab();
		pgWrapper.airlinesGroundTransportationPage.clickOnActionGTApprovedInvoiceNotaFiscal();
		pgWrapper.airlinesGroundTransportationPage.verifyGTInvoiceShownAfterDelete(invoiceNumber);

	}

	@Test(description = "ATA-380 | Verify that the ACCEPTED invoice should not be available in ACESAIR's \"Action GT Approved Invoice (Nota Fiscal)\" section, once the attachments are removed", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void VerifyGenAirlineReportsforSSO(String tenantName) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("AirNewZealandUsernameSSO"),
				readPropValue("AirNewZealandPasswordSSO"));
		pgWrapper.reportsTab.clickOnAccountingReportLink();
		pgWrapper.accountingReportsPage.verifyApprovedInvoicesPage();
		pgWrapper.accountingReportsPage.verifyTaxExemptionReportPage();
		pgWrapper.accountingReportsPage.verifyNoShowReportPage();
		pgWrapper.accountingReportsPage.verifyCrewIDIssuesReportPage();

	}

	@Test(description = "ATA-385 | Verify that Verify Generating of Sign-In Report", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void VerifyGenSignInReport(String tenantName, String city, String supplierName,
			String billingPeriodValueSignInReport) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();		
		pgWrapper.pageSignInReport.generateSignInReportReg(city, supplierName, billingPeriodValueSignInReport);

	}

}
