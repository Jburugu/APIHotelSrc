package com.APIHotels.tests.ACES3;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3_SignInSheets_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;
	@Test(description = " ATA- 91 SIR - Verify that the SIR is generated for the selected billing period", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void verifyGenPandMInvoiceInSupSite(String tenantName,String city,String supplierName, String billingPeriod) throws Exception{
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"), readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3ClientHomePage.selectTenant(tenantName);
		pgWrapper.aces3SignInSheetsClientPage.clickOnSignInSheetTab();
		pgWrapper.aces3SignInSheetsClientPage.clickOnSignInSheetReportTab();
		pgWrapper.aces3SignInSheetsClientPage.generateReport(city, supplierName, billingPeriod);
		
}
	
	@Test(description = " ATA-133 SIR - Verify that the view details popup shows the scheduled check ins for the relevant date", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceViewDetails(String tenantName,String city,String supplierName, String billingPeriod) throws Exception{
		
		verifyGenPandMInvoiceInSupSite(tenantName, city, supplierName, billingPeriod);
		pgWrapper.aces3SignInSheetsClientPage.clickOnViewDetailsLink();
		
}
	
	@Test(description = "ATA-90 - Verify that the View SIS page is created for the supplier with the sign in sheet id and as per the configuration entered in Configure SIS (ACES3ACES)", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void verifyGenSignInSheet(String tenantName,String city,String supplierName) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces3AcesUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"), readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnConfigurationLink();
		pgWrapper.aces3acesConfigurationPage.clickOnConfigureSignInSheetLink();
		pgWrapper.aces3acesConfigurationPage.selectTenant(tenantName);
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"), readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SignInSheetsClientPage.clickOnSignInSheetTab();
		pgWrapper.aces3SignInSheetsClientPage.clickOnViewOrPrintSignInSheets();
		pgWrapper.aces3SignInSheetsClientPage.selectTenant(tenantName);
		pgWrapper.aces3SignInSheetsClientPage.generateSignInSheetView();
	
}
	
}