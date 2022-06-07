package com.APIHotels.tests.ACESII;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_NewSupplierSite_Allowances_Regression_Suite extends LocalDriverManager{
	
	public PgWrapper pgWrapper;
	
	@Test(description = "JIRA#ATA-259", dataProvider = "TestDataFile", groups = "Regression")
	public void submitSISUploadingTheSheets(String tenantName, String city, String supplierName,String billingPeriod, 
			String supplierInvoiceNumber) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
		pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
		pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
		pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(tenantName, city, supplierName,billingPeriod, supplierInvoiceNumber);
		//pgWrapper.aces3SupplierCreateAllowanceInvoicePage.submitSISheets();
	}
	
	@Test(description = "JIRA#ATA-259", dataProvider = "TestDataFile", groups = "Regression")
	public void addUnplannedAllowances(String tenantName, String city, String supplierName,String billingPeriod, String supplierInvoiceNumber, String allowanceNameValue, String allowanceRankValue, String allowanceBreakFastNmbr, String allowanceBreakfastRateValue, String allowanceCurrencyCodeValue, String allowanceReason ) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
		pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
		pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
		pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(tenantName, city, supplierName,billingPeriod, supplierInvoiceNumber);
		pgWrapper.aces3SupplierCreateAllowanceInvoicePage.addUnplannedAllowance(allowanceNameValue, allowanceRankValue, allowanceBreakFastNmbr, allowanceBreakfastRateValue, allowanceCurrencyCodeValue, allowanceReason);
	}
	
	// ATA-255 ANZ Allowance | Create an Adhoc Allowance Request in Airline site
	// ATA-256 Verify the same in Search Adhoc
	// ATA-257 Verify the cancel for Individual/Multiple adhoc allowance
	// requests

	@Test(description = "JIRA#ATA-255,256,257", dataProvider = "TestDataFile", groups = "Regression")
	public void createSrchCancelAdhocAllowance(String runMode, String arrivalTime, String arrivalFlightValue, String currencyValue,
			String locationValue, String hotelValue, String paymentValue, String crewValue, String crewfirstNameValue,
			String crewlastNameValue, String crewRankValue, String costCenterValue, String departmentValue,
			String paymentAmountValue, String commentsValue, String tenantName) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ","")+"Username"), readPropValue(tenantName.replace(" ","")+"Password"));
		
		// logger.info("**** Starting Verification of Create Adhoc Allownce Page
		// Tab");
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnCreateAdhocAllowanceLink();
		pgWrapper.createAdhocAllowancePage.createmultipleAdhocRequestAllowances(arrivalTime, arrivalFlightValue,
				currencyValue, locationValue, hotelValue, paymentValue, crewValue, crewfirstNameValue,
				crewlastNameValue, crewRankValue, costCenterValue, departmentValue, paymentAmountValue, commentsValue);
		pgWrapper.operationsTab.clickOnSearchAdhocAllowanceLink();
		pgWrapper.searchAdhocAllowancePage.searchAdhocAllowance();
		pgWrapper.searchAdhocAllowancePage.cancelSingleAllowance();
		pgWrapper.searchAdhocAllowancePage.cancelMultipleAllowances();
	}
}

	// ATA-258 Search for the Adhoc request in Supplier site & Generate the
	// Sheet
	@Test(description = "JIRA#ATA-258", dataProvider = "TestDataFile", groups = "Regression")
	public void srchAdhocReqAndGenerateSheet(String runMode, String arrivalTime, String arrivalFlightValue, String currencyValue,
			String locationValue, String hotelValue, String paymentValue, String crewValue, String crewfirstNameValue,
			String crewlastNameValue, String crewRankValue, String costCenterValue, String departmentValue,
			String paymentAmountValue, String commentsValue, String tenantName, String supplier) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ","")+"Username"), readPropValue(tenantName.replace(" ","")+"Password"));
	
		// Creates Adhoc allowance Request and logout from airlines site
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnCreateAdhocAllowanceLink();
		String adhocRequestId = pgWrapper.createAdhocAllowancePage.createSingleAdhocAllowanceRequest(arrivalTime,
				arrivalFlightValue, currencyValue, locationValue, hotelValue, paymentValue, crewValue,
				crewfirstNameValue, crewlastNameValue, crewRankValue, costCenterValue, departmentValue,
				paymentAmountValue, commentsValue, supplier);
		System.out.println(adhocRequestId + "adhocRequestId");
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// Login to Aces3Supplier Site and click on Allowances-> View Forecast
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, locationValue, supplier);
		String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
		pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
		pgWrapper.aces3SupplierHomePage.clickOnViewAdhocAllowanceLink();
		pgWrapper.aces3SupplierSearchAdhocAllowanceRequestPage.searchForAdhocAllowanceRequest(tenantName,
				adhocRequestId);
		}

	}

	// Reports: Payment Extract Report
	// ATA-273 Verify all the search options, Export the reports and Verify
	@Test(description = "JIRA#ATA-273,ATA-276", dataProvider = "TestDataFile", groups = "Regression")
	public void verifyAllSearchOptions_PER(String runMode, String tenantName, String fromDate, String contractGroups,
			String staffnumberValue, String roasterGroup) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ","")+"Username"), readPropValue(tenantName.replace(" ","")+"Password"));
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnPaymentExtractReportLink();
		pgWrapper.paymentExtractReportPage.verifyAllSearchOptionsAndExports(fromDate, contractGroups, staffnumberValue,
				roasterGroup);
		}
	}

	// ATA-274 Verify the TOD Allowance & Overseas PRT values for the trip,
	// ATA-275 Verify London Report
	@Test(description = "JIRA#ATA-274,275", dataProvider = "TestDataFile", groups = "Regression")
	public void verifyTODAndPRTAndExports(String runMode, String tenantName, String fromDate, String contractGroup, String staffnumberValue,
			String tripNumber, String empID, String elementType) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ","")+"Username"), readPropValue(tenantName.replace(" ","")+"Password"));
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnPaymentExtractReportLink();
		pgWrapper.paymentExtractReportPage.verifyTODAndPRTAndExportLondonReport(fromDate, contractGroup,
				staffnumberValue, tripNumber, empID, elementType);
		}
	}

	// Reports: DEA Report
	// ATA-277 Verify all the search Options, ATA-278 Export the reports and
	// Verify
	@Test(description = "JIRA#ATA-277, 278", dataProvider = "TestDataFile", groups = "Regression")
	public void deaReport(String runMode, String tenantName, String fromDate, String baseValue, String staffValue,
			String resourceGroups) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ","")+"Username"), readPropValue(tenantName.replace(" ","")+"Password"));		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnDEAReportLink();
		pgWrapper.deaReportPage.verifyAllSearchOptions_DEAReport(fromDate, baseValue, staffValue, resourceGroups);
		}
	}

	// Reports: View Allowance Transactions
	// ATA - 279 Verify all the search options
	@Test(description = "JIRA#ATA-279", dataProvider = "TestDataFile", groups = "Regression")
	public void viewAllowanceTransactions(String runMode, String tenantName, String seriesValue, String startDate, String locationValue,
			String hotelValue, String crewIdValue, String typeValue) throws Exception {
		if(runMode.equals("Yes")){
			ExtentTestManager.getTest().log(LogStatus.INFO,"Verifying View allowance transactions for tenant "+tenantName + " and for type " +typeValue);
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ","")+"Username"), readPropValue(tenantName.replace(" ","")+"Password"));	
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnViewAllowanceTransactionsLink();
		pgWrapper.viewAllowanceTransactionsPage.searchAllowanceTransaction(seriesValue, startDate, locationValue,
				hotelValue, crewIdValue, typeValue);
		}
	}

	// Reports: Configure Supplier Features
	// Verify the Screen with combination of all options
	@Test(description = "JIRA#ATA-279", dataProvider = "TestDataFile", groups = "Regression")
	public void configureSupplierFeature(String runMode, String tenantName, String cityValue, String hotelValue,
			String supplierPaymentTermValue, String email, String faxId, String message, String billingPeriod) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnConfigureSupplierFeatureLink();
		if (tenantName.equalsIgnoreCase("Air New Zealand"))
			pgWrapper.configureSupplierFeaturesPage.manageSupplierFeature(cityValue, hotelValue,
					supplierPaymentTermValue, email, faxId, message);

		else if (tenantName.equalsIgnoreCase("Air Nelson")) {
			if(billingPeriod.equalsIgnoreCase("Monthly"))
			pgWrapper.configureSupplierFeaturesPage.manageSupplierFeatures_Monthly(cityValue, hotelValue,
					supplierPaymentTermValue, email, faxId, message);
			else if(billingPeriod.equalsIgnoreCase("SemiMonthly"))
			pgWrapper.configureSupplierFeaturesPage.manageSupplierFeatures_SemiMonthly(cityValue, hotelValue,
					supplierPaymentTermValue, email, faxId, message);
			else if(billingPeriod.equalsIgnoreCase("Weekly"))
			pgWrapper.configureSupplierFeaturesPage.manageSupplierFeatures_Weekly(cityValue, hotelValue,
					supplierPaymentTermValue, email, faxId, message);
		}

		else
			Assert.fail("Tenant Not Found");
		}
	}

	// Verify logging into aces3 site as superadmin and verify the workflow
	@Test(description = "JIRA#ATA-281", dataProvider = "TestDataFile", groups = "Regression")
	public void workflowAsAces3Admin(String runMode, String tenant, String airportCityCode, String supplier, String flightNumber,
			String adhocRequestId, String supplierInvoiceNumber, String invoiceStatus, String invoiceNumber)
			throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenant, airportCityCode, supplier);
		String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
		pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
		pgWrapper.aces3SupplierHomePage.clickOnViewAllowanceForecastsLink();
		pgWrapper.aces3SupplierViewAllowanceForecastsPage.viewAllowanceForcasts(flightNumber);
		pgWrapper.aces3SupplierHomePage.clickOnViewAdhocAllowanceLink();
		pgWrapper.aces3SupplierSearchAdhocAllowanceRequestPage.searchAdhocAllowanceRequest(adhocRequestId);
		pgWrapper.aces3SupplierHomePage.clickOnCreateViewAllowanceSISLink();
		pgWrapper.aces3SupplierCreateViewAllowanceSISPage.createViewAllowanceSIS();
		pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
		pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(supplierInvoiceNumber);
		pgWrapper.aces3SupplierHomePage.clickOnFindAllowanceInvoiceLink();
		pgWrapper.aces3SupplierFindAllowanceInvoicePage.findAllowanceInvoice_workflow(invoiceStatus, invoiceNumber, tenant);
		}

	}

	// Verify logging into aces3 site as Supplier and verify the workflow
	@Test(description = "JIRA#ATA-282", dataProvider = "TestDataFile", groups = "Regression")
	public void workflowAsAces3Supplier(String runMode, String tenant, String airportCityCode, String supplier, String flightNumber, String adhocRequestId,
			String supplierInvoiceNumber, String invoiceStatus, String invoiceNumber, String firstName, 
			String lastName, String emailId, String roles, String createByIndicator, String tempUserName,
			String tempPassword) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenant, airportCityCode, supplier);
		pgWrapper.aces3SupplierHomePage.clickOnCreateUserLink();
		pgWrapper.aces3SupplierCreateUserPage.createUser(firstName, lastName, emailId, roles, createByIndicator,
				tempUserName, tempPassword);
		pgWrapper.aces3SupplierCreateUserPage.verifyUserCreated();
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierName"),
				readPropValue("aces3SupplierPwd"));
		
		
		
		String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();

		// Navigates to New ACES2SupplierSite -> Allowances
		pgWrapper.aces3SupplierHomePage.clickOnAllowancesMenu();
		pgWrapper.aces3SupplierHomePage.clickOnViewAllowanceForecastLink(parentWindow);
		pgWrapper.aces3SupplierViewAllowanceForecastsPage.viewAllowanceForcasts(flightNumber);
		pgWrapper.aces3SupplierHomePage.clickOnViewAdhocAllowanceLink();
		pgWrapper.aces3SupplierSearchAdhocAllowanceRequestPage.searchAdhocAllowanceRequest(adhocRequestId);
		pgWrapper.aces3SupplierHomePage.clickOnCreateViewAllowanceSISLink();
		pgWrapper.aces3SupplierCreateViewAllowanceSISPage.createViewAllowanceSIS();
		pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
		pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(supplierInvoiceNumber);
		pgWrapper.aces3SupplierHomePage.clickOnFindAllowanceInvoiceLink();
		pgWrapper.aces3SupplierFindAllowanceInvoicePage.findAllowanceInvoice_workflow(invoiceStatus, invoiceNumber, tenant);
		}

	}

	// Verify logging into aces2 site as superadmin and verify the workflow
	@Test(description = "JIRA#ATA-283", dataProvider = "TestDataFile", groups = "Regression")
	public void workflowAsAces2Admin(String runMode, String tenant, String airportCityCode, String supplier, String flightNumber,
			String adhocRequestId, String supplierInvoiceNumber, String invoiceStatus, String invoiceNumber)
			throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces2SupplierUrl);
		pgWrapper.pageLoginACESII.loginToAces2Supplier(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenant, airportCityCode, supplier);
		pgWrapper.aces3SupplierHomePage.clickOnAllowancesMenu();
		pgWrapper.aces3SupplierHomePage.clickOnViewAllowanceForecastsLink();
		pgWrapper.aces3SupplierViewAllowanceForecastsPage.viewAllowanceForcasts(flightNumber);
		pgWrapper.aces3SupplierHomePage.clickOnViewAdhocAllowanceLink();
		pgWrapper.aces3SupplierSearchAdhocAllowanceRequestPage.searchAdhocAllowanceRequest(adhocRequestId);
		pgWrapper.aces3SupplierHomePage.clickOnCreateViewAllowanceSISLink();
		pgWrapper.aces3SupplierCreateViewAllowanceSISPage.createViewAllowanceSIS();
		pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
		pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(supplierInvoiceNumber);
		pgWrapper.aces3SupplierHomePage.clickOnFindAllowanceInvoiceLink();
		pgWrapper.aces3SupplierFindAllowanceInvoicePage.findAllowanceInvoice_workflow(invoiceStatus, invoiceNumber, tenant);
		
		}

	}
	
	@Test(description = "JIRA#ATA-252, ATA-253", dataProvider = "TestDataFile", groups = "Regression")
	public void verifyViewForecasts(String runMode, String scenario, String tenantName, String city,
			String supplierName, String flightNumber, String arrivalDate, String startdate, String continueIndicator, String totalCashForecastAmt,
			String crewName, String cashForecastAmount, String crewidValue, String seriesValue) throws Exception {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + scenario);
		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
			pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
			pgWrapper.aces3SupplierHomePage.clickOnViewAllowanceForecastsLink();
			pgWrapper.aces3SupplierViewAllowanceForecastsPage.searchForAllowanceForecasts(tenantName, flightNumber, arrivalDate, startdate);
			if(continueIndicator.equals("Yes")){
				pgWrapper.aces3SupplierViewAllowanceForecastsPage.viewAllowanceForecasts(totalCashForecastAmt, crewName, cashForecastAmount);
				//pgWrapper.aces3SupplierViewAllowanceForecastsPage.generateAllowanceSheet();
			}
			getDriver().close();
			getDriver().switchTo().window(parentWindow);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
			
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			pgWrapper.operationsTab.clickOnAllowancesLink();
			pgWrapper.operationsTab.clickOnViewForecastLink();
			pgWrapper.viewForecastsPage.searchForAllowanceForecasts(flightNumber, crewidValue, city, supplierName, startdate, seriesValue);
			if(continueIndicator.equals("Yes"))
				pgWrapper.viewForecastsPage.viewAllowanceForecasts(totalCashForecastAmt, crewName);
			
		}
	}
	
	@Test(description = "JIRA#ATA-259, ATA-265, ATA-266, ATA-267, ATA-268, ATA-269, ATA-271, ATA-272", dataProvider = "TestDataFile", groups = "Regression")
	public void createAllowanceInvoice(String runMode, String tenantName, String city, String supplierName,
			String billingPeriod, String supplierInvoiceNumber, String allowanceAmounts, String adjustmentAmounts,
			String totalDailyAmounts, String hotelAdjustmentReason, String hotelAdjustmentComments,
			String hotelAdjustmentAmount, String comments, String invoiceAttachmentComments,
			String authorizeRejectIndicator, String approvedInvoiceStatus) throws Exception {

		if (runMode.equalsIgnoreCase("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			String parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
			pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
			pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(tenantName, city, supplierName,
					billingPeriod, supplierInvoiceNumber);
			int unSubmittedSIS = pgWrapper.aces3SupplierCreateAllowanceInvoicePage.getUnsubmittedSIS();
			System.out.println(unSubmittedSIS);
			if (unSubmittedSIS > 0) {
				submitUnsubmittedSIS(tenantName, city, supplierName, billingPeriod, supplierInvoiceNumber,
						unSubmittedSIS);
				pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(tenantName, city, supplierName,
						billingPeriod, supplierInvoiceNumber);
			}

			String apiInvoiceNumber = pgWrapper.aces3SupplierCreateAllowanceInvoicePage.getAPIInvoiceNumber();
			System.out.println(apiInvoiceNumber);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.verifyDailyPayments(allowanceAmounts, adjustmentAmounts,
					totalDailyAmounts);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.addHotelAdjustments(hotelAdjustmentReason,
					hotelAdjustmentComments, hotelAdjustmentAmount);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.addComments(comments);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.uploadInvoiceAttachments(comments);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.saveInvoice();

			// String apiInvoiceNumber = "A-7124";
			findAllowanceInvoiceAndVerifyStatus("New", apiInvoiceNumber, tenantName);
			pgWrapper.aces3SupplierFindAllowanceInvoicePage.viewAllowanceInvoice(apiInvoiceNumber);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.verifyEditable(true);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.submitInvoice();

			findAllowanceInvoiceAndVerifyStatus("Submitted", apiInvoiceNumber, tenantName);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.verifyEditable(false);
			getDriver().close();
			getDriver().switchTo().window(parentWindow);

			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

			// Reject the invoice from Airlines site and check the status in
			// Aces2 supplier site

			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			verifyInvoiceAndPerformActionInAirlineSite(tenantName, apiInvoiceNumber, "REJECT", city, supplierName, billingPeriod);
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			// String apiInvoiceNumber = "A-7124";
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
			pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
			findAllowanceInvoiceAndVerifyStatus("Rejected", apiInvoiceNumber, tenantName);
			pgWrapper.aces3SupplierFindAllowanceInvoicePage.viewAllowanceInvoice(apiInvoiceNumber);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.verifyEditable(true);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.submitInvoice();
			getDriver().close();
			getDriver().switchTo().window(parentWindow);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

			// String apiInvoiceNumber = "A-7124";
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			verifyInvoiceAndPerformActionInAirlineSite(tenantName, apiInvoiceNumber, "APPROVE", city, supplierName, billingPeriod);
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();

			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
			pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
			findAllowanceInvoiceAndVerifyStatus("Approved", apiInvoiceNumber, tenantName);
			pgWrapper.aces3SupplierFindAllowanceInvoicePage.viewAllowanceInvoice(apiInvoiceNumber);
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.verifyEditable(false);
			getDriver().close();
			getDriver().switchTo().window(parentWindow);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();

			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			pgWrapper.operationsTab.clickOnAllowancesLink();
			pgWrapper.operationsTab.clickOnViewApprovedAllowanceInvoicesLink();
			pgWrapper.viewAllowanceInvoicePage.verifyAllowanceInvoiceExists(city, supplierName, billingPeriod,
					"Approved", apiInvoiceNumber);
			pgWrapper.viewAllowanceInvoicePage.performAction(apiInvoiceNumber, authorizeRejectIndicator);
			if(authorizeRejectIndicator.equals("Authorize")){
				pgWrapper.viewAllowanceInvoicePage.verifyAllowanceInvoiceExists(city, supplierName, billingPeriod,
					approvedInvoiceStatus, apiInvoiceNumber);
				pgWrapper.viewAllowanceInvoicePage.generateGLExtractReport(apiInvoiceNumber);
			}

			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
			parentWindow = pgWrapper.aces3SupplierHomePage.clickOnViewForecast();
			pgWrapper.aces3SupplierHomePage.navigateToACESIISupplierSiteWindow(parentWindow);
			findAllowanceInvoiceAndVerifyStatus(approvedInvoiceStatus, apiInvoiceNumber, tenantName);
			getDriver().close();
			getDriver().switchTo().window(parentWindow);
			pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
		}
	}
	
	private void submitUnsubmittedSIS(String tenantName, String city, String supplierName, String billingPeriod, String supplierInvoiceNumber, int unSubmittedSIS) throws Exception{
		for (int i = unSubmittedSIS - 1; i >= 0; i--) {
			String date = pgWrapper.aces3SupplierCreateAllowanceInvoicePage.clickOnViewUnsubmittedSIS(i);
			date = date.split(" ")[0] + "-" + date.split(" ")[1];
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.uploadAllowanceSheet();
			pgWrapper.aces3SupplierCreateAllowanceInvoicePage.clickOnSubmitSIS();
			boolean isMissingAllowanceSheet = pgWrapper.aces3SupplierCreateAllowanceInvoicePage
					.isMissingAllowanceSheet();
			if (isMissingAllowanceSheet) {
				pgWrapper.aces3SupplierHomePage.clickOnViewAllowanceForecastsLink();
				pgWrapper.aces3SupplierViewAllowanceForecastsPage.searchForAllowanceForecasts(tenantName, "", "",
						date);
				int size = pgWrapper.aces3SupplierViewAllowanceForecastsPage.getAllowanceForecastsCount();
				for (int j = 0; j < size; j++) {
					pgWrapper.aces3SupplierViewAllowanceForecastsPage.generateAllowanceSheetForEachRecord(j);
					pgWrapper.aces3SupplierViewAllowanceForecastsPage.clickOnGenerateAllowanceSheetBtnIfPresent();
				}
				pgWrapper.aces3SupplierHomePage.clickOnCreateAllowanceInvoiceLink();
				pgWrapper.aces3SupplierCreateAllowanceInvoicePage.createAllowanceInvoice(tenantName, city,
						supplierName, billingPeriod, supplierInvoiceNumber);
				pgWrapper.aces3SupplierCreateAllowanceInvoicePage.clickOnViewUnsubmittedSIS(i);
				pgWrapper.aces3SupplierCreateAllowanceInvoicePage.clickOnSubmitSIS();
			}
		}
	}
	
	private void findAllowanceInvoiceAndVerifyStatus(String invoiceStatus, String invoiceNumber, String tenantName) throws Exception{
		pgWrapper.aces3SupplierHomePage.clickOnFindAllowanceInvoiceLink();
		pgWrapper.aces3SupplierFindAllowanceInvoicePage.findAllowanceInvoice(invoiceStatus, invoiceNumber, tenantName);
	}
	
	private void verifyInvoiceAndPerformActionInAirlineSite(String tenantName, String invoiceNumber, String action, String city, String supplierName, String billingPeriod) throws Exception{
		pgWrapper.operationsTab.clickOnAllowancesLink();
		pgWrapper.operationsTab.clickOnViewApprovedAllowanceInvoicesLink();
		pgWrapper.viewAllowanceInvoicePage.verifyAllowanceInvoiceExists(city, supplierName, billingPeriod,
				"Submitted", invoiceNumber);
		pgWrapper.operationsTab.clickOnViewSubmittedAllowanceInvoicesLink();
		pgWrapper.viewSubmittedAllowanceInvoicePage.verifyIfInvoiceExists(invoiceNumber);
		pgWrapper.viewSubmittedAllowanceInvoicePage.performAction(invoiceNumber+","+action);
	}
	
	@Test(description = "JIRA#ATA-270", dataProvider = "TestDataFile", groups = "Regression")
	public void acceptRejectSubmittedAIwnceInv(String runMode, String tenantName, String invoiceNumbersAndActions, String scenario) throws Exception{
		if (runMode.equalsIgnoreCase("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + scenario);
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
					readPropValue(tenantName.replace(" ", "") + "Password"));
			pgWrapper.operationsTab.clickOnAllowancesLink();
			pgWrapper.operationsTab.clickOnViewSubmittedAllowanceInvoicesLink();
			pgWrapper.viewSubmittedAllowanceInvoicePage.performAction(invoiceNumbersAndActions);
		}
	}
	
}