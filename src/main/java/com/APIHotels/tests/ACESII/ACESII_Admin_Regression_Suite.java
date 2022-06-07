package com.APIHotels.tests.ACESII;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACESII_Admin_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;
	
	@Test(description="ATA-176	: Verify that the tenant is allowed to configure GT invoice	", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void configureGT_EInvoice(String tenantName, String tenant, String invoiceExportFrequency, String shippingToCompName, String billToCompName) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();	
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAdminLink();
		pgWrapper.adminMenu.clickConfigureGTEInvoiceLink();
		pgWrapper.pageGtEInvoiceConfiguration.setTenantDetailsAndOptionsReg(tenant, Integer.parseInt(invoiceExportFrequency), shippingToCompName, billToCompName);
		pgWrapper.pageGtEInvoiceConfiguration.clickOnGTConfSave();
	}
	
	
	@Test(description="ATA-177	Verify the Invoice export frequency with on and off	", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void verifyInvoiceFrequencyOnOff(String tenantName, String tenant, String invoiceExportFrequency, String shippingToCompName, String billToCompName) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();	
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAdminLink();
		pgWrapper.adminMenu.clickConfigureGTEInvoiceLink();
		pgWrapper.pageGtEInvoiceConfiguration.verifyInvoiceFrequencyOnOff(tenant, invoiceExportFrequency, shippingToCompName, billToCompName);
	}

	@Test(description="ATA-178	: Verify that the suppliers are allowed to activate GT Invoice	", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void VerifySuppAllowedToActGTInv(String tenantName, String tenant, String invoiceExportFrequency, String shippingToCompName, String billToCompName) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();	
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickAdminLink();
		pgWrapper.adminMenu.clickConfigureGTEInvoiceLink();
		pgWrapper.pageGtEInvoiceConfiguration.setTenantDetailsAndOptionsReg(tenant, Integer.parseInt(invoiceExportFrequency), shippingToCompName, billToCompName);
		
	}

}
