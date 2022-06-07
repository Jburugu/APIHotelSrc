package com.APIHotels.tests.ACES3;

import java.util.List;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3_InvoiceValidations_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	@Test(description = "Get all Unsubmitted Sign in Sheets for the invoice period and submit them and create invoice for the Invoice Period", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void createInvoiceFromSupplierSite(String tenantName, String city, String supplierName,
			String occupancyPercent, String hotelAdjustmentReason, String hotelAdjComments, String hotelAdjAmount,
			String hotelAdjNoOfRooms, String hotelAdjRoomType, String taxOption, String hotelTaxComments,
			String hotelTaxAmount, String invoiceComments, String attachmentType, String attachmentComments,
			String auditorAdjustmentReason, String auditorComments, String auditorAdjustmentAmount,
			String adjustmentNoOfRooms, String auditorAdjustmentRoomType, String auditorGLAllocationAmount,
			String auditorGLAdjustmentPercent, String auditorGLCode, String auditorTaxOption, String auditorTaxComment,
			String auditorTaxAmount, String auditorTaxAllocationAmount, String auditorTaxAdjustmentPercent,
			String auditorTaxGLCode, String conversionRate, String targetCurrency) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnCreateInvoiceLink();
		pgWrapper.aces3SupplierCreateInvoicePage.createInvoiceForFirstBillingPeriod(tenantName);
		int unSubmittedSIS = pgWrapper.aces3SupplierCreateInvoicePage.getUnsubmittedSIS();
		System.out.println(unSubmittedSIS);
		if (unSubmittedSIS > 0) {
			for (int i = unSubmittedSIS - 1; i >= 0; i--) {
				pgWrapper.aces3SupplierCreateInvoicePage.clickOnViewUnsubmittedSIS(i);
				pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.loadSignInSheet();
				pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.enterEmpIdIfEmpty();
				pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.returnToPrevActivity();
			}
		}
		boolean occLevelFlag = pgWrapper.aces3SupplierCreateInvoicePage.addMissingOccupancyLevels(occupancyPercent);
		if (occLevelFlag)
			pgWrapper.aces3SupplierCreateInvoicePage.createInvoiceForFirstBillingPeriod(tenantName);

		pgWrapper.aces3SupplierTaxInvoicePage.addHotelAdjustments(hotelAdjustmentReason, hotelAdjComments,
				hotelAdjAmount, hotelAdjNoOfRooms, hotelAdjRoomType);
		pgWrapper.aces3SupplierTaxInvoicePage.addHotelTaxAdjustments(taxOption, hotelTaxComments, hotelTaxAmount);
		pgWrapper.aces3SupplierTaxInvoicePage.addInvoiceComments(invoiceComments);
		// pgWrapper.aces3SupplierTaxInvoicePage.addInvoiceAttachments(attachmentType,
		// attachmentComments);
		pgWrapper.aces3SupplierTaxInvoicePage.submitInvoice();
		String invoiceNumber = pgWrapper.aces3SupplierTaxInvoicePage.getInvoiceNumber().split("\n")[0];
		writeProp("apiInvoiceNumber_InvValidation", invoiceNumber);
		// Verify Hotel Adjustments in supplier site
		verifyHotelAdjustments(hotelAdjustmentReason, hotelAdjComments, hotelAdjAmount, hotelTaxComments,
				hotelTaxAmount, invoiceComments);
		// Verify status
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
		System.out.println(invoiceNumber);

		getDriver().get(aces3ClientUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenantName);
		pgWrapper.aces3ClientHomePage.clickOnAccountingLink();
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		performActionsOnInvoiceFromClientSite(tenantName, readPropValue("apiInvoiceNumber_InvValidation"),
				auditorAdjustmentReason, auditorComments, auditorAdjustmentAmount, adjustmentNoOfRooms,
				auditorAdjustmentRoomType, auditorGLAllocationAmount, auditorGLAdjustmentPercent, auditorGLCode,
				auditorTaxOption, auditorTaxComment, auditorTaxAmount, auditorTaxAllocationAmount,
				auditorTaxAdjustmentPercent, auditorTaxGLCode, invoiceComments, attachmentType, attachmentComments);
		// calculate Invoice Amount With and Without Taxes
		pgWrapper.aces3ClientTaxInvoicePage.verifyInvoiceAmountWithAndWithoutTaxes();

		// eInvoice Conversions
		pgWrapper.aces3ClientTaxInvoicePage.setEInvoiceConversion(conversionRate, targetCurrency);

		// Verify Hotel Adjustments in ACES3 Client applicaiton
		verifyHotelAdjustments(hotelAdjustmentReason, hotelAdjComments, hotelAdjAmount, hotelTaxComments,
				hotelTaxAmount, invoiceComments);
		verifyAPIAuditorAdjustments(auditorAdjustmentReason, auditorComments, auditorAdjustmentAmount,
				auditorTaxComment, auditorTaxAmount, invoiceComments);

		// Return To Hotel and check status
		pgWrapper.aces3ClientTaxInvoicePage.returnToHotel();
		// Verify Status
		pgWrapper.aces3ClientFindInvoicePage.verifyInvoiceStatus(readPropValue("apiInvoiceNumber_InvValidation"),
				"Returned");
	}

	@Test(description = "Create invoice for the Selected Suppliers", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void createInvoiceFromICR(String tenantName, String supplierName, String auditorAdjustmentReason,
			String auditorComments, String auditorAdjustmentAmount, String adjustmentNoOfRooms,
			String auditorAdjustmentRoomType, String auditorGLAllocationAmount, String auditorGLAdjustmentPercent,
			String auditorGLCode, String auditorTaxOption, String auditorTaxComment, String auditorTaxAmount,
			String auditorTaxAllocationAmount, String auditorTaxAdjustmentPercent, String auditorTaxGLCode,
			String invoiceComments, String invoiceAttachmentType, String invoiceAttachmentComments) throws Exception {
		getDriver().get(aces3AcesUrl);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnReportsLink();
		pgWrapper.aces3acesReportsPage.clickOnInvoiceComplianceReportLink();
		pgWrapper.aces3acesInvoiceComplianceReportPage.searchNonCompliantInvoiceReports(tenantName);
		List<String> generatedInvoiceNumbers = pgWrapper.aces3acesInvoiceComplianceReportPage
				.generateInvoicesForSelectedSuppliers(supplierName);
		getDriver().get(aces3ClientUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenantName);
		pgWrapper.aces3ClientHomePage.clickOnAccountingLink();
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		performActionsOnInvoiceFromClientSite(tenantName, generatedInvoiceNumbers.get(0), auditorAdjustmentReason,
				auditorComments, auditorAdjustmentAmount, adjustmentNoOfRooms, auditorAdjustmentRoomType,
				auditorGLAllocationAmount, auditorGLAdjustmentPercent, auditorGLCode, auditorTaxOption,
				auditorTaxComment, auditorTaxAmount, auditorTaxAllocationAmount, auditorTaxAdjustmentPercent,
				auditorTaxGLCode, invoiceComments, invoiceAttachmentType, invoiceAttachmentComments);

		verifyAPIAuditorAdjustments(auditorAdjustmentReason, auditorComments, auditorAdjustmentAmount,
				auditorTaxComment, auditorTaxAmount, invoiceComments);
		// Hold Invoice
		pgWrapper.aces3ClientTaxInvoicePage.holdInvoice();
		pgWrapper.aces3ClientFindInvoicePage.verifySubStatus(generatedInvoiceNumbers.get(0), "Hold");
		// Regenerate Invoice
		pgWrapper.aces3ClientFindInvoicePage.selectInvoiceLink(generatedInvoiceNumbers.get(0));
		pgWrapper.aces3ClientTaxInvoicePage.holdAndRegenerate();
		// Add API Auditor Adjustments again to the regenerated invoice
		addAuditorAdjustments(auditorAdjustmentReason, auditorComments, auditorAdjustmentAmount, adjustmentNoOfRooms,
				auditorAdjustmentRoomType, auditorGLAllocationAmount, auditorGLAdjustmentPercent, auditorGLCode,
				auditorTaxOption, auditorTaxComment, auditorTaxAmount, auditorTaxAllocationAmount,
				auditorTaxAdjustmentPercent, auditorTaxGLCode, invoiceComments, invoiceAttachmentType,
				invoiceAttachmentComments);

		// Add Payment group again and also add unallocated amounts
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.mapAllUnassignedEmployeesToGroup("None");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();

		pgWrapper.aces3ClientTaxInvoicePage.addUnallocatedAdjustmentAmount();
		// Approve Invoice
		pgWrapper.aces3ClientTaxInvoicePage.approveInvoice();

		// Void Invoice
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceWithNumber(generatedInvoiceNumbers.get(0));
		pgWrapper.aces3ClientFindInvoicePage.selectInvoiceLink(generatedInvoiceNumbers.get(0));
		pgWrapper.aces3ClientTaxInvoicePage.addInvoiceComments("voiding");
		pgWrapper.aces3ClientTaxInvoicePage.voidInvoice();
		pgWrapper.aces3ClientFindInvoicePage.verifyVoidInvoiceStatus(generatedInvoiceNumbers.get(0), "VOID");

	}

	private void performActionsOnInvoiceFromClientSite(String tenantName, String invoiceNumber,
			String auditorAdjustmentReason, String auditorComments, String auditorAdjustmentAmount,
			String adjustmentNoOfRooms, String auditorAdjustmentRoomType, String auditorGLAllocationAmount,
			String auditorGLAdjustmentPercent, String auditorGLCode, String auditorTaxOption, String auditorTaxComment,
			String auditorTaxAmount, String auditorTaxAllocationAmount, String auditorTaxAdjustmentPercent,
			String auditorTaxGLCode, String invoiceComments, String invoiceAttachmentType,
			String invoiceAttachmentComments) throws Exception {

		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceWithNumber(invoiceNumber);
		pgWrapper.aces3ClientFindInvoicePage.selectInvoiceLink(invoiceNumber);
		addAuditorAdjustments(auditorAdjustmentReason, auditorComments, auditorAdjustmentAmount, adjustmentNoOfRooms,
				auditorAdjustmentRoomType, auditorGLAllocationAmount, auditorGLAdjustmentPercent, auditorGLCode,
				auditorTaxOption, auditorTaxComment, auditorTaxAmount, auditorTaxAllocationAmount,
				auditorTaxAdjustmentPercent, auditorTaxGLCode, invoiceComments, invoiceAttachmentType,
				invoiceAttachmentComments);
		// Add Payment Group
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.mapAllUnassignedEmployeesToGroup("None");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
	}

	private void addAuditorAdjustments(String auditorAdjustmentReason, String auditorComments,
			String auditorAdjustmentAmount, String adjustmentNoOfRooms, String auditorAdjustmentRoomType,
			String auditorGLAllocationAmount, String auditorGLAdjustmentPercent, String auditorGLCode,
			String auditorTaxOption, String auditorTaxComment, String auditorTaxAmount,
			String auditorTaxAllocationAmount, String auditorTaxAdjustmentPercent, String auditorTaxGLCode,
			String invoiceComments, String invoiceAttachmentType, String invoiceAttachmentComments) throws Exception {
		// Add API Auditor Adjustments
		pgWrapper.aces3ClientTaxInvoicePage.addApiAuditorAdjustments(auditorAdjustmentReason, auditorComments,
				auditorAdjustmentAmount, adjustmentNoOfRooms, auditorAdjustmentRoomType);
		pgWrapper.aces3ClientTaxInvoicePage.addApiAuditorAdjustments_glAllocations(auditorGLAllocationAmount,
				auditorGLAdjustmentPercent, auditorGLCode);
		pgWrapper.aces3ClientTaxInvoicePage.addApiAuditorTaxAdjustments(auditorTaxOption, auditorTaxComment,
				auditorTaxAmount);
		pgWrapper.aces3ClientTaxInvoicePage.addApiAuditorTaxAdjustments_glAllocations(auditorTaxAllocationAmount,
				auditorTaxAdjustmentPercent, auditorTaxGLCode);
		pgWrapper.aces3ClientTaxInvoicePage.addInvoiceComments(invoiceComments);
		pgWrapper.aces3ClientTaxInvoicePage.addInvoiceAttachments(invoiceAttachmentType, invoiceAttachmentComments);
	}

	private void verifyHotelAdjustments(String hotelAdjustmentReason, String hotelAdjComments, String hotelAdjAmount,
			String hotelTaxComments, String hotelTaxAmount, String invoiceComments) throws InterruptedException {
		pgWrapper.aces3SupplierTaxInvoicePage.verifyHotelAdjustments(hotelAdjustmentReason, hotelAdjComments,
				hotelAdjAmount, hotelTaxComments, hotelTaxAmount);
		pgWrapper.aces3SupplierTaxInvoicePage.verifyHotelInvoiceComments(invoiceComments);
	}

	private void verifyAPIAuditorAdjustments(String auditorAdjustmentReason, String auditorComments,
			String auditorAdjustmentAmount, String auditorTaxComment, String auditorTaxAmount, String invoiceComments)
			throws InterruptedException {
		pgWrapper.aces3ClientTaxInvoicePage.verifyAPIAdjustments(auditorAdjustmentReason, auditorComments,
				auditorAdjustmentAmount, auditorTaxComment, auditorTaxAmount);
		pgWrapper.aces3ClientTaxInvoicePage.verifyAPIAuditorInvoiceComments(invoiceComments);
	}

}
