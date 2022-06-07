package com.APIHotels.tests.ACES3;

import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3ACES_Workflows_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;
	ACES3ACES_Invoice_Regression_Suite invoiceReg;
	String getInvocieNbr;

	@Test(description = " ATA-160 : Verify that the invoice is taken to workflow as per the configuration: General work flow", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyConfiguringWorkflows(String tenantName, String workflowStep) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		workflowCreation(tenantName, workflowStep);
	}

	public void workflowCreation(String tenantName, String workflowStep) throws Exception {
		pgWrapper.aces3acesConfigurationPage.clickOnConfiguration();
		pgWrapper.aces3acesConfigurationPage.clickOnConfigureTenantFeaturesLink();
		pgWrapper.aces3acesConfigurationPage.configWorkflow(tenantName);
		pgWrapper.aces3acesConfigurationPage.removeAllSelected();
		pgWrapper.aces3acesConfigurationPage.AddAvailableToSelected(workflowStep);
		pgWrapper.aces3acesConfigurationPage.clickOnSaveConfigurationBtn();
	}

	public String createInvoice(String tenantName, String city, String supplierName, String billingPeriod,
			String reason, String comments, String amount, String numberOfRooms, String reasonNoshow,
			String amountNoShow, String otherTax, String flight, String inFlight, String flightNoOfRooms,
			String InflighNoShowValue, String otherTaxValue) throws Exception {
		pgWrapper.aces3acesManualInvoicePage.createManualInvoice(tenantName, city, supplierName, billingPeriod);
		getInvocieNbr = pgWrapper.aces3acesManualInvoicePage.getHotelInvoiceNbr();
		System.out.println("Invoice Number :" + getInvocieNbr);
		pgWrapper.aces3acesManualInvoicePage.numberOfRoomsAdjustments(reason, comments, amount, numberOfRooms);
		pgWrapper.aces3acesManualInvoicePage.noShowAdjustments(reasonNoshow, comments, amountNoShow);
		pgWrapper.aces3acesManualInvoicePage.auditorTaxAdjustments(otherTax);
		pgWrapper.aces3acesManualInvoicePage.AddPaymentGroup(flight, inFlight);
		pgWrapper.aces3acesManualInvoicePage.adjustmentsInPaymentGroup(flightNoOfRooms, InflighNoShowValue,
				otherTaxValue);
		// pgWrapper.aces3acesManualInvoicePage.currencyConversion();
		pgWrapper.aces3acesManualInvoicePage.clickOnApproveInvoice();
		return getInvocieNbr;
	}

	@Test(description = "ATA-162 - Verify that the invoice is allowed to accept / Reject payment group", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createAcceptRejectPayGrps(String tenantName, String workflowStep, String city, String supplierName,
			String billingPeriod, String reason, String comments, String amount, String numberOfRooms,
			String reasonNoshow, String amountNoShow, String otherTax, String flight, String inFlight,
			String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String approved, String subStatus,
			String commentsReject, String rejected, String subStatusRejected) throws Exception {

		// Start Work flow Creation
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		workflowCreation(tenantName, workflowStep);
		// Start Invoice Creation
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getIvoiceNbrAccept = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments,
				amount, numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getIvoiceNbrAccept, approved);
		// End Invoice Creation

		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		System.out.println("Invoice ID :" + getIvoiceNbrAccept);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentInFlightGroup(getIvoiceNbrAccept, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentFlightGroup(getIvoiceNbrAccept, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getIvoiceNbrAccept, approved);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceSubStatus(getIvoiceNbrAccept, subStatus);
		// Start Invoice Creation
		// pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getIvoiceNbrReject = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments,
				amount, numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getIvoiceNbrReject, approved);
		// End Invoice Creation
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		System.out.println("Invoice ID :" + getIvoiceNbrReject);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentInFlightGroupReject(getIvoiceNbrReject, inFlight,
				commentsReject);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentFlightGroupReject(getIvoiceNbrReject, flight,
				commentsReject);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getIvoiceNbrReject, rejected);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceSubStatus(getIvoiceNbrReject, subStatusRejected);

	}

	@Test(description = "ATA-169 : Verify that auto accept payment group workflow", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyAutoAcceptPaymentGrpWflow(String tenantName, String workflowStep, String city,
			String supplierName, String billingPeriod, String reason, String comments, String amount,
			String numberOfRooms, String reasonNoshow, String amountNoShow, String otherTax, String flight,
			String inFlight, String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String approved,
			String subStatus) throws Exception {

		// Work flow Creation
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		workflowCreation(tenantName, workflowStep);
		// Invoice Creation
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getIvoiceNbr1 = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments, amount,
				numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatusHOP(getIvoiceNbr1, approved);
		pgWrapper.aces3acesManualInvoicePage.verifySubStatusHOP(subStatus);

	}

	@Test(description = "ATA-168 : Verify that the invoice export work flow for HOP", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyPendingInvoiceExportHOP(String tenantName, String workflowStep, String city, String supplierName,
			String billingPeriod, String reason, String comments, String amount, String numberOfRooms,
			String reasonNoshow, String amountNoShow, String otherTax, String flight, String inFlight,
			String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String approved, String subStatus)
			throws Exception { // Work flow Creation
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		workflowCreation(tenantName, workflowStep);
		// Invoice Creation
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getIvoiceNbr1 = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments, amount,
				numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatusHOP(getIvoiceNbr1, approved);

		// Navigates to Aces 3 Client Site for Verifying Pending Export Invoice
		getDriver().get(aces3ClientUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenantName);
		pgWrapper.aces3ClientAccountingPage.clickOnAccountingTab();
		pgWrapper.aces3ClientAccountingPage.clickOnPendingInvoiceExportTab();
		pgWrapper.aces3ClientAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3ClientAccountingPage.acceptPendingInvoiceExportFlight(getIvoiceNbr1, flight);
		pgWrapper.aces3ClientAccountingPage.acceptPendingInvoiceExportInFlight(getIvoiceNbr1, inFlight);
		pgWrapper.aces3ClientAccountingPage.clickOnUpdateBtn();
		pgWrapper.aces3ClientAccountingPage.verifyInvoiceStatusHOP(getIvoiceNbr1, approved);
		pgWrapper.aces3ClientAccountingPage.verifySubStatusHOP(subStatus);
		// Verifying Pending Export Invoice Completed
	}

	@Test(description = "ATA-166A, ATA-161: Verify that the clients are allowed to accept the nota fiscal, ATA-167 Verify that once the notfiscal is accepted , it should change to payment groups accepted and it ends the invoice work flow	", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyNotaFiscalflow(String tenantName, String workflowStep, String city, String supplierName,
			String billingPeriod, String reason, String comments, String amount, String numberOfRooms,
			String reasonNoshow, String amountNoShow, String otherTax, String flight, String inFlight,
			String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String approved, String subStatus)
			throws Exception {
		// Start Work flow Creation
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
//		 workflowCreation(tenantName, workflowStep);

		// Invoice Creation
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getInvocieNbr = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments, amount,
				numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
//		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getIvoiceNbr1, approved);
		// Approving invoice work flow based on the configuration
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentFlightGroup(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentInFlightGroup(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.verifyEnterPaidDateInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyEnterPaidDateFlight(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.logoutACES3ACES();

		// Navigates to Supplier site and performs Nota fiscal operations by
		// uploading the files
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierAccountingPage.clickOnAccountingTab();
		pgWrapper.aces3SupplierAccountingPage.clickOnPendingActionQueueTab();
		pgWrapper.aces3SupplierAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3SupplierAccountingPage.verifyNotaFiscalUploadFileInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3SupplierAccountingPage.verifyNotaFiscalUploadFileFlight(getInvocieNbr, flight);
		pgWrapper.aces3SupplierAccountingPage.clickOnPerformActionsbtn();
		// End Supplier site

		// Navigates to Main ACES 3
		getDriver().get(aces3AcesUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3acesAccountingPage.verifyNotaFiscalAcceptInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyNotaFiscalAcceptFlight(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr, approved);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceSubStatus(getInvocieNbr, subStatus);

	}

	@Test(description = "ATA-166B - Verify that the clients are allowed to reject the nota fiscal", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyNotaFiscalflowReject(String tenantName, String workflowStep, String city, String supplierName,
			String billingPeriod, String reason, String comments, String amount, String numberOfRooms,
			String reasonNoshow, String amountNoShow, String otherTax, String flight, String inFlight,
			String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String rejectComments,
			String approved, String subStatus) throws Exception {
		// Start Work flow Creation
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		workflowCreation(tenantName, workflowStep);

		// Invoice Creation
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getInvocieNbr = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments, amount,
				numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr, approved);
		// Approving invoice work flow based on the configuration
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentFlightGroup(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentInFlightGroup(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.verifyEnterPaidDateInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyEnterPaidDateFlight(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.logoutACES3ACES();

		// Navigates to Supplier site and performs Nota fiscal operations by
		// uploading the files
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierAccountingPage.clickOnAccountingTab();
		pgWrapper.aces3SupplierAccountingPage.clickOnPendingActionQueueTab();
		pgWrapper.aces3SupplierAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3SupplierAccountingPage.verifyNotaFiscalUploadFileInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3SupplierAccountingPage.verifyNotaFiscalUploadFileFlight(getInvocieNbr, flight);
		pgWrapper.aces3SupplierAccountingPage.clickOnPerformActionsbtn();
		// End Supplier site
		// Navigates to Main ACES 3
		getDriver().get(aces3AcesUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3acesAccountingPage.verifyNotaFiscalRejectInFlight(getInvocieNbr, inFlight, rejectComments);
		pgWrapper.aces3acesAccountingPage.verifyNotaFiscalRejectFlight(getInvocieNbr, flight, rejectComments);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr, approved);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceSubStatus(getInvocieNbr, subStatus);

	}

	@Test(description = "ATA-166B - Verify that the clients are allowed to reject the nota fiscal", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyeWorkflowWithAllOptions(String tenantName, String workflowStep, String city, String supplierName,
			String billingPeriod, String reason, String comments, String amount, String numberOfRooms,
			String reasonNoshow, String amountNoShow, String otherTax, String flight, String inFlight,
			String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String rejectComments,
			String approved, String subStatus) throws Exception {
// Start Work flow Creation
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		//workflowCreation(tenantName, workflowStep);

// Invoice Creation
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		String getInvocieNbr = createInvoice(tenantName, city, supplierName, billingPeriod, reason, comments, amount,
				numberOfRooms, reasonNoshow, amountNoShow, otherTax, flight, inFlight, flightNoOfRooms,
				InflighNoShowValue, otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr, approved);
// Approving invoice work flow based on the configuration
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentFlightGroup(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.verifyInvoicePaymentInFlightGroup(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.verifyEnterPaidDateInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyEnterPaidDateFlight(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.verifyEnterRefNoInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyEnterRefNoFlight(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.verifyEnterVENCDateInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3acesAccountingPage.verifyEnterVENCDateFlight(getInvocieNbr, flight);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesAccountingPage.logoutACES3ACES();

// Navigates to Supplier site and performs Nota fiscal operations by
// uploading the files
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierAccountingPage.clickOnAccountingTab();
		pgWrapper.aces3SupplierAccountingPage.clickOnPendingActionQueueTab();
		pgWrapper.aces3SupplierAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3SupplierAccountingPage.verifyNotaFiscalUploadFileInFlight(getInvocieNbr, inFlight);
		pgWrapper.aces3SupplierAccountingPage.verifyNotaFiscalUploadFileFlight(getInvocieNbr, flight);
		pgWrapper.aces3SupplierAccountingPage.clickOnPerformActionsbtn();
// End Supplier site
// Navigates to Main ACES 3
		getDriver().get(aces3AcesUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		pgWrapper.aces3acesAccountingPage.clickOnPendingActionQueueLink();
		pgWrapper.aces3acesAccountingPage.selectTenant(tenantName);
		pgWrapper.aces3acesAccountingPage.sortForLatestInvoice();
		pgWrapper.aces3acesAccountingPage.verifyNotaFiscalRejectInFlight(getInvocieNbr, inFlight, rejectComments);
		pgWrapper.aces3acesAccountingPage.verifyNotaFiscalRejectFlight(getInvocieNbr, flight, rejectComments);
		pgWrapper.aces3acesAccountingPage.clickOnPerformActionBtn();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr, approved);
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceSubStatus(getInvocieNbr, subStatus);

	}

}