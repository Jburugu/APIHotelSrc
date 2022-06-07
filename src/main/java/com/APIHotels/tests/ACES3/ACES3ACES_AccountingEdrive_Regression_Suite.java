package com.APIHotels.tests.ACES3;

import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACES3ACES_AccountingEdrive_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;
	
	/* This Test Method is used to configure the Edrive menu in both ACESII and ACESIII".
	 * Without execution this test first remaining test cases will fail hence set priority to 0"
	 * Note: We don't have Jira ticket for this ticket considered it as a part of other ticket
	 */
	@Test(description = "Precondition : Configure E drive for tenant in ACES2 and ACES3", groups = {"Regression" }, dataProvider = "TestDataFile", priority=0)
	public void configureEdriveinACESIIandIII(String tenantName, String billToCompName) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		/*
		 * To get Accounting E-drive Menu in ACESIII Supplier, initially E-drive
		 * should be configured at the tenant level in ACES3 Site
		 */
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnConfigurationLink();
		pgWrapper.aces3acesConfigurationPage.clickOnConfigureEDriveLink();
		pgWrapper.aces3acesManageEdrivePage.activateEdrive(tenantName);
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenant(tenantName);
		pgWrapper.pageHome.clickAdminLink();
		//pgWrapper.adminMenu.clickManageUsersLink();		
		pgWrapper.adminMenu.clickConfigureGTEInvoiceLink();
		pgWrapper.pageGtEInvoiceConfiguration.setGTInvoicingConfigForTenant(tenantName, billToCompName, billToCompName);
		pgWrapper.pageHome.clickLogoutLink();
	}


	/* Hotel--
	 * This Test Method configures Accounting Edrive for Tenant in ACES3ACES Site,
	 * Uploads Hotel Invoice for selected supplier in Aces3SupplierSite, Searches
	 * for uploaded Hotel Invoice in Invoice Dashboard of Aces3SupplierSite,
	 * Verifies whether uploaded Hotel Invoice is displaying in Edrive Dashboard
	 * of ACES3ACES Site and Accepts the Invoice
	 */
	@Test(description = "Jira# ATA-135 Verify that the user is allowed to upload invoice in supplier website (Edirve should be configured at the tenant level)", groups = {
			"Regression" }, dataProvider = "TestDataFile" ,priority=1)
	public void uploadAndVerifyInvoice(String tenantName, String city, String supplierName, String billingPeriod,
			String roomBillValue, String roomAmountValue, String taxAmountValue, String exceptionAmountValue,
			String invoiceAmountValue, String contactEmailValue, String commentText, String invoiceMonth, String status,
			String statusInaces3, String supplierType) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		/* Accounting eDrive -> Upload Hotel invoice from supplier website */
		clickOnuploadInvoiceLink(tenantName, city, supplierName);
		pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForHotel(tenantName, billingPeriod, roomBillValue,
				roomAmountValue, taxAmountValue, exceptionAmountValue, invoiceAmountValue, contactEmailValue,
				commentText);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
		/*
		 * View above uploaded invoice in supplier website Accounting eDrive
		 * ->Invoice Dashboard
		 */
		verifyUploadedInvoiceInInvoiceDashboard(tenantName, invoiceMonth, status, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

		/* Uploaded invoice should display in ACES3ACES site */
		verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusInaces3, supplierType, supplierName);
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();
	}

	/* GT---
	 * Test Method configures Accounting Edrive for Tenant in ACES3ACES Site,
	 * Uploads GT Invoice for selected supplier in Aces3SupplierSite, Verify for
	 * uploaded GT Invoice in Invoice Dashboard of Aces3SupplierSite, Verifies
	 * whether uploaded GT Invoice is displaying in Edrive Dashboard of
	 * ACES3ACES Site and also in acesII site
	 */
	@Test(description = "Jira# ATA-135 Verify that the user is allowed to upload invoice in supplier website (Edirve should be configured at the tenant level), Jira# ATA-136 Verify that the Accounting dashboard displays uploaded invoice (Edrive))", groups = {
			"Regression" }, dataProvider = "TestDataFile",priority=2)
	public void uploadAndVerifyGTInvoice(String tenantName, String city, String supplierName, String billingPeriod,
			String tripsBilledAmount, String tripAmountValue, String gtTaxAmountValue, String gtInvoiceAmountValue,
			String gtEmail, String gtCommentText, String invoiceMonth, String status, String statusInaces3,
			String supplierType) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();

		/* Accounting eDrive -> Upload GT invoice from supplier website */
		clickOnuploadInvoiceLink(tenantName, city, supplierName);
		pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForGT(tenantName, billingPeriod, tripsBilledAmount,
			tripAmountValue, gtTaxAmountValue, gtInvoiceAmountValue, gtEmail, gtCommentText);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

		/*
		 * View above uploaded invoice in supplier website Accounting eDrive
		 * ->Invoice Dashboard
		 */
		verifyUploadedInvoiceInInvoiceDashboard(tenantName, invoiceMonth, status, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

		/* Uploaded invoice should display in ACES3ACES site */
		verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusInaces3, supplierType, supplierName);
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();
		
		/* Uploaded invoice should display in ACESII site*/
		verifyInvoiceStatusInEdriveGTDashboardOfAcesII(tenantName, billingPeriod, status, supplierName);
	}

	/*
	 * Accept Invoice of Hotel in EdriveDashboard of ACES3ACES and verify status
	 */
	@Test(description = "Accept Hotel and GT Invoice", groups = { "Regression" }, dataProvider = "TestDataFile", priority=3)
	public void acceptAndArchiveHTAndGTInvoices(String runMode, String tenantName, String city, String supplierName,
			String billingPeriod, String roomBillValue, String roomAmountValue, String taxAmountValue,
			String exceptionAmountValue, String invoiceAmountValue, String contactEmailValue, String commentText,
			String invoiceMonth, String commentToAccept, String statusBeforeAccept, String statusAfterAccept,
			String supplierType, String tripsBilledAmount, String tripAmountValue, String commentToArchieve,
			String statusAfterArchive) throws Exception {
		
		if(runMode.equals("Yes")){
		ExtentTestManager.getTest().log(LogStatus.INFO,
					"Accepting and Archiving the Invoice for " + supplierName + " and supplier Type is " +supplierType);
		pgWrapper = LocalDriverManager.getPageWrapper();

		/* Upload Invoice from Aces3supplier site */
		clickOnuploadInvoiceLink(tenantName, city, supplierName);

		if (supplierType.equalsIgnoreCase("Hotel")) {
			pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForHotel(tenantName, billingPeriod, roomBillValue,
					roomAmountValue, taxAmountValue, exceptionAmountValue, invoiceAmountValue, contactEmailValue,
					commentText);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
			
			/* Accept uploaded invoice from ACES3ACES site and verify Accepted
			 status */
			 
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusBeforeAccept, supplierType, supplierName);
			pgWrapper.aces3acesEDriveDashboardPage.acceptInvoiceofHotel(commentToAccept, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterAccept, supplierType, supplierName);
			
			/* Archive accepted invoice from ACES3ACES site  */
			
			pgWrapper.aces3acesEDriveDashboardPage.archiveAcceptedHotelInvoice(commentToArchieve, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterArchive, supplierType, supplierName);
		} else if (supplierType.equalsIgnoreCase("GT")) {
			
			pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForGT(tenantName, billingPeriod, tripsBilledAmount,
					tripAmountValue, taxAmountValue, invoiceAmountValue, contactEmailValue, commentText);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
			
			/* Accept uploaded invoice from ACES3ACES site and verify Accepted
			 * status */
			 
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusBeforeAccept, supplierType, supplierName);
			pgWrapper.aces3acesEDriveDashboardPage.acceptInvoiceOfGT(commentToAccept, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceStatusInEdriveGTDashboardOfAcesII(tenantName, billingPeriod, statusAfterAccept, supplierName);
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterAccept, supplierType, supplierName);
			
			/* Archive accepted invoice from ACES3ACES site  */
			pgWrapper.aces3acesEDriveDashboardPage.archiveAcceptedGTInvoice(commentToArchieve, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterArchive, supplierType, supplierName);
			verifyInvoiceStatusInEdriveGTDashboardOfAcesII(tenantName, billingPeriod, statusAfterArchive, supplierName);
			}
		}
	}
	

	/*
	 * Return Invoice of Hotel and GT in EdriveDashboard of ACES3ACES and
	 * Resubmit them from InvoiceDashboard of Aces3Supplier
	 */
	@Test(description = "Return Hotel and GT Invoice from Aces3Aces Site AND Resubmit them from Aces3supplier Site", groups = {
			"Regression" }, dataProvider = "TestDataFile" ,priority=4)
	public void returnAndResubmitHTAndGT(String runMode, String tenantName, String city, String supplierName,
			String billingPeriod, String roomBillValue, String roomAmountValue, String taxAmountValue,
			String exceptionAmountValue, String invoiceAmountValue, String contactEmailValue, String commentText,
			String invoiceMonth, String commentToReturn, String statusBeforeReturn, String statusAfterReturn,
			String supplierType, String tripsBilledAmount, String tripAmountValue, String commentToResubmit,
			String statusAfterResubmitted) throws Exception {

		if(runMode.equals("Yes")){
			ExtentTestManager.getTest().log(LogStatus.INFO,
						"Accepting and Archiving the Invoice for " + supplierName + " and supplier Type is " +supplierType);
			
		pgWrapper = LocalDriverManager.getPageWrapper();

		/* Upload Invoice from Aces3supplier site */
		clickOnuploadInvoiceLink(tenantName, city, supplierName);

		if (supplierType.equalsIgnoreCase("Hotel")) {
			pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForHotel(tenantName, billingPeriod, roomBillValue,
					roomAmountValue, taxAmountValue, exceptionAmountValue, invoiceAmountValue, contactEmailValue,
					commentText);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusBeforeReturn, supplierType, supplierName);

			/* Return Invoice from Aces3Aces site */
			pgWrapper.aces3acesEDriveDashboardPage.returnHotelInvoice(commentToReturn, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();

			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterReturn, supplierType, supplierName);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();

			/* Resubmit returned Invoice from Aces3supplier site */
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			pgWrapper.aces3SupplierHomePage.clickOnAccountingEdriveMenu();
			pgWrapper.aces3SupplierHomePage.clickOninvoiceDashboard();
			pgWrapper.aces3SupplierInvoiceDashboardPage.selectTenantMonthAndStatus(tenantName, invoiceMonth,
					statusAfterReturn);
			pgWrapper.aces3SupplierInvoiceDashboardPage.resubmitHotelInvoice(tenantName, invoiceMonth,
					statusAfterReturn, commentToReturn, commentToResubmit);
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterResubmitted, supplierType,
					supplierName);
			
		} else if (supplierType.equalsIgnoreCase("GT")) {
			pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForGT(tenantName, billingPeriod, tripsBilledAmount,
					tripAmountValue, taxAmountValue, invoiceAmountValue, contactEmailValue, commentText);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusBeforeReturn, supplierType, supplierName);

			/* Return Invoice from Aces3Aces site */
			pgWrapper.aces3acesEDriveDashboardPage.returnGTInvoice(commentToReturn, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceStatusInEdriveGTDashboardOfAcesII(tenantName, billingPeriod, statusAfterReturn, supplierName);
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterReturn, supplierType, supplierName);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();

			/* Resubmit returned Invoice from Aces3supplier site */
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			pgWrapper.aces3SupplierHomePage.clickOnAccountingEdriveMenu();
			pgWrapper.aces3SupplierHomePage.clickOninvoiceDashboard();
			pgWrapper.aces3SupplierInvoiceDashboardPage.selectTenantMonthAndStatus(tenantName, invoiceMonth,
					statusAfterReturn);
			pgWrapper.aces3SupplierInvoiceDashboardPage.resubmitGTInvoice(tenantName, invoiceMonth, statusAfterReturn,
					commentToReturn, commentToResubmit);
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterResubmitted, supplierType,
					supplierName);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceStatusInEdriveGTDashboardOfAcesII(tenantName, billingPeriod, statusAfterResubmitted, supplierName);
			
		}
	}
}	
	/*
	 * Reject Invoice of Hotel and GT in EdriveDashboard of ACES3ACES and verify status 
	 */
	@Test(description = "Reject Invoice from Aces3Aces Site", groups = {"Regression" }, dataProvider = "TestDataFile" , priority=5)
	public void rejectHTAndGTInvoices(String runMode, String tenantName, String city, String supplierName, String billingPeriod,
			String roomBillValue, String roomAmountValue, String taxAmountValue, String exceptionAmountValue,
			String invoiceAmountValue, String contactEmailValue, String commentText, String invoiceMonth, 
			String commentToReject,	String statusBeforeReject, String statusAfterReject, String supplierType,  
			String tripsBilledAmount, String tripAmountValue) throws Exception {
		
		if(runMode.equals("Yes")){
			ExtentTestManager.getTest().log(LogStatus.INFO,
						"Accepting and Archiving the Invoice for " + supplierName + " and supplier Type is " +supplierType);		
		pgWrapper = LocalDriverManager.getPageWrapper();

	/* Upload Invoice from Aces3supplier site */
	clickOnuploadInvoiceLink(tenantName, city, supplierName);

	if (supplierType.equalsIgnoreCase("Hotel")) {
		pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForHotel(tenantName, billingPeriod, roomBillValue,
				roomAmountValue, taxAmountValue, exceptionAmountValue, invoiceAmountValue, contactEmailValue,
				commentText);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
		verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusBeforeReject, supplierType, supplierName);
		
		/*Reject the Resubmitted Invoice from Aces3Aces Site*/
		pgWrapper.aces3acesEDriveDashboardPage.rejectHotelInvoice(commentToReject, invoiceMonth);
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();
		verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterReject, supplierType, supplierName);
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();
	}
		else if (supplierType.equalsIgnoreCase("GT")) {
			pgWrapper.aces3SupplierUploadInvoicePage.uploadInvoiceForGT(tenantName, billingPeriod, tripsBilledAmount,
					tripAmountValue, taxAmountValue, invoiceAmountValue, contactEmailValue, commentText);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusBeforeReject, supplierType, supplierName);
			/*Reject the Resubmitted Invoice from Aces3Aces Site*/

			pgWrapper.aces3acesEDriveDashboardPage.rejectGTInvoice(commentToReject, invoiceMonth);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceInEdriveDashboard(tenantName, invoiceMonth, statusAfterReject, supplierType, supplierName);
			pgWrapper.aces3acesHomePage.clickOnLogoutLink();
			verifyInvoiceStatusInEdriveGTDashboardOfAcesII(tenantName, billingPeriod, statusAfterReject, supplierName);
			}
		}
			
	}
	
	/*Verify the invoices are filtered correctly based on the selection of "Billing Period and Invoice Status"*/
	@Test(description = "Verify the invoices are filtered correctly based on the selection of Billing Period and Invoice Status", groups = {"Regression" },
			dataProvider = "TestDataFile" , priority=6)
	public void verifyFiltersInEdriveDashboard(String runMode, String tenantName, String invoiceMonth, String status,
			String count) throws Exception{
		if(runMode.equals("Yes")){
			ExtentTestManager.getTest().log(LogStatus.INFO,
						"Verifying the invoice for billing period " + invoiceMonth + " and for status "	+status);	
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenant(tenantName);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingEdriveDashboard.clickOnEdriveGTDashboard();
		if(!status.equalsIgnoreCase("All")){
		pgWrapper.pageGTInvoicingEdriveDashboard.findByBillingPeriodAndStatus(invoiceMonth, status);
		pgWrapper.pageGTInvoicingEdriveDashboard.verifyInvoiceStatus(invoiceMonth, status);
		}
		else
			pgWrapper.pageGTInvoicingEdriveDashboard.verifyAllCount(count);
		
	}
}
	
	/*ATA-382 Verify able to download the attachments*/
	@Test(description = "Verify able to download the attachments", groups = {"Regression"},
			dataProvider = "TestDataFile" , priority=7)
	public void verifyDownloadInAcesII(String tenantName, String invoiceMonth, String status, String supplierName)
			throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenant(tenantName);
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingEdriveDashboard.clickOnEdriveGTDashboard();
		pgWrapper.pageGTInvoicingEdriveDashboard.findByBillingPeriodAndStatus(invoiceMonth, status);
		pgWrapper.pageGTInvoicingEdriveDashboard.downloadAttachment(supplierName, invoiceMonth);
	}	

	
			
	/* Clicks on Upload Invoice menu link in aces3SupplierSite */
	private void clickOnuploadInvoiceLink(String tenantName, String city, String supplierName) {
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnUploadInvoice();

	}

	/* Verify Uploaded Invoice In InvoiceDashboard of aces3Supplier */
	private void verifyUploadedInvoiceInInvoiceDashboard(String tenantName, String invoiceMonth, String status,
			String city, String supplierName) throws Exception {
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnAccountingEdriveMenu();
		pgWrapper.aces3SupplierHomePage.clickOninvoiceDashboard();
		pgWrapper.aces3SupplierInvoiceDashboardPage.selectTenantMonthAndStatus(tenantName, invoiceMonth, status);
		pgWrapper.aces3SupplierInvoiceDashboardPage.clickView(tenantName, invoiceMonth, status);

	}

	
	/* Verify Invoice In EdriveDashboard of aces3aces */
	private void verifyInvoiceInEdriveDashboard(String tenantName, String invoiceMonth, String statusInaces3,
			String supplierType, String supplierName) throws Exception {
		getDriver().get(aces3AcesUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3acesUserName"),
				readPropValue("aces3acesPassword"));
		pgWrapper.aces3acesHomePage.clickOnEDriveDashboardLink();
		pgWrapper.aces3acesEDriveDashboardPage.selectTenantMonthAndStatus(tenantName, invoiceMonth, statusInaces3,
				supplierType);
		pgWrapper.aces3acesEDriveDashboardPage.clickViewAces3(tenantName, invoiceMonth, statusInaces3, supplierName);
	}
	
	/*Verify the invoices with all status (New/Resubmitted, Returned, Rejected, Archived, Accepted) created through ACES3Supplier 
	 * are available in ACES2-> E-Drive GT Dashboard*/
	/*This method covers the above mentioned functionality by calling it in different methods which verifies invoice actions*/
	private void verifyInvoiceStatusInEdriveGTDashboardOfAcesII(String tenantName, String invoiceMonth, String status, String supplierName) throws Exception{
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAccountingLink();
		pgWrapper.accountingMenu.clickGtInvoicing();
		pgWrapper.pageGTInvoicingEdriveDashboard.clickOnEdriveGTDashboard();
		pgWrapper.pageGTInvoicingEdriveDashboard.verifyInvoiceStatusandSupplier(invoiceMonth, status, supplierName);
		
	}
	
}
