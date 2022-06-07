package com.APIHotels.tests.ACES3;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3_ESIS_EinvoiceValidations_Regression_Suite extends LocalDriverManager{
	
	public PgWrapper pgWrapper;
	private String apiInvoiceNumber;
	
	
	@Test(description = "JIRA# ATA-139 - Verify that the Online Sign in sheets are loaded on the reservation date entered", groups = {
	"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void verifySignInSheetLoaded(String tenantName, String city, String supplierName, String reservationDate){
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnOnlineSignInSheetsLink();
		pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.loadSignInSheet(tenantName, reservationDate);
		pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.verifySignInSheetLoaded();
	}
	
	@Test(description = "JIRA# ATA-140 - Verify that entering incorrect employee id for the first time triggers a validation message", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 1)
	public void verifyValidationOnInvalidEmpId(String tenantName, String city, String supplierName, String reservationDate, String incorrectEmpId) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnOnlineSignInSheetsLink();
		pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.loadSignInSheet(tenantName, reservationDate);
		pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.verifyValidationMsgForIncorrectEmpId(incorrectEmpId);
	}
	
	
	@Test(description = "JIRA# ATA-141 - Verify the Crew swap, Billable No Show, Non Billable No Show, No Bill, Split, Merge, Room Number, Rate actions are allowed in ESIS and the same is reflected in invoice;", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 2)
	public void performAction(String runMode, String tenantName, String city, String supplierName, String reservationDate,
			String empName, String newEmpName, String occupancyPercentage, String noBillEmpName, String noShowEmpName,
			String noShowCategory, String roomNumber, String rate, String newReservationDate, String walkInEmpName,
			String walkInEmpId, String walkInRoomNo, String walkInRate, String crewMergeEmp1Name, String crewMergeEmp2Name) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			pgWrapper.aces3SupplierHomePage.clickOnOnlineSignInSheetsLink();
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.loadSignInSheet(tenantName, reservationDate);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.enterEmpIdIfEmpty();
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.crewSwap(empName, newEmpName);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.enterOccupancyPercentage(occupancyPercentage);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.attachOccupancyReport();
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.markNoBill(noBillEmpName);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.markNoShow(noShowEmpName, noShowCategory);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.crewMerge(crewMergeEmp1Name, crewMergeEmp2Name);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.editRate(rate);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.editRoomNumber(roomNumber);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.editReservationDates(newReservationDate);
			pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.addWalkIn(walkInEmpName, walkInEmpId, walkInRoomNo,
					walkInRate);
		}
	}
	
	@Test(description = "Get all Unsubmitted Sign in Sheets for the invoice period and submit them and create invoice for the Invoice Period", groups = {
	"Regression" }, dataProvider = "TestDataFile", priority = 3)
	public void createInvoice(String tenantName, String city, String supplierName, String invoicePeriod, String occupancyPercent) throws InterruptedException{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnCreateInvoiceLink();
		pgWrapper.aces3SupplierCreateInvoicePage.createInvoice(invoicePeriod);
		int unSubmittedSIS = pgWrapper.aces3SupplierCreateInvoicePage.getUnsubmittedSIS();
		System.out.println(unSubmittedSIS);
		if (unSubmittedSIS > 0) {
			for (int i = unSubmittedSIS-1; i >= 0; i--) {
				pgWrapper.aces3SupplierCreateInvoicePage.clickOnViewUnsubmittedSIS(i);
				pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.loadSignInSheet();
				pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.enterEmpIdIfEmpty();
				pgWrapper.aces3SupplierEnterOnlineSignInSheetsPage.returnToPrevActivity();
			}
			
			pgWrapper.aces3SupplierCreateInvoicePage.addMissingOccupancyLevels(occupancyPercent);
			pgWrapper.aces3SupplierCreateInvoicePage.createInvoice(invoicePeriod);
		}
		pgWrapper.aces3SupplierTaxInvoicePage.submitInvoice();
		apiInvoiceNumber = pgWrapper.aces3SupplierTaxInvoicePage.getInvoiceNumber();
		writeProp("apiInvoiceNumber_ESIS", apiInvoiceNumber);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
	}
	
	@Test(description = "Approve Invoice from Client site", groups = {"Regression"}, dataProvider = "TestDataFile", priority = 4)
	public void approveInvoiceFromClient(String tenant, String billingPeriodDate, String status, String noShowEmpName, String noBillEmpName) throws Exception{
		/*
		 * Login to Client site Select tenant Navigate to
		 * Accounting->FindInvoice screen Find by Invoice number clickOn invoice link
		 * invoice payment groups create groups create new group
		 * 
		 */
		apiInvoiceNumber = readPropValue("apiInvoiceNumber_ESIS");
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces3ClientUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenant);
		pgWrapper.aces3ClientHomePage.clickOnAccountingLink();
		pgWrapper.aces3ClientAccountingPage.clickOnFindInvoiceLink();
		pgWrapper.aces3ClientFindInvoicePage.searchInvoiceWithBillingPeriodAndStatus(billingPeriodDate, status);
		pgWrapper.aces3ClientFindInvoicePage.selectInvoiceLink(apiInvoiceNumber);

		// Add Flight Payment Group
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Flight");
		pgWrapper.aces3ClientTaxInvoicePage.addEmployeesToGroup(noShowEmpName, "Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();

		// Add In-Flight Payment Group
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.addEmployeesToGroup(noBillEmpName, "In-Flight");
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();

		// Add Other Payment Group and assign remaining employees
		pgWrapper.aces3ClientTaxInvoicePage.addInvoicePaymentGroup("Other");
		pgWrapper.aces3ClientTaxInvoicePage.clickOnEditPaymentGroup("Other");
		pgWrapper.aces3ClientTaxInvoicePage.mapAllUnassignedEmployeesToGroup("None");//Select all unassigned employees with filter None
		pgWrapper.aces3ClientTaxInvoicePage.clickPaymentGroupDialogOK();
		
		//verify no show details and approve invoice
		pgWrapper.aces3ClientTaxInvoicePage.verifyBillableNoShowDetails(noShowEmpName);
		pgWrapper.aces3ClientTaxInvoicePage.approveInvoice();
	}
}
