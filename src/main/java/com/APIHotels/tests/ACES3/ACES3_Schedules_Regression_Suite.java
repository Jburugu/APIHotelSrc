package com.APIHotels.tests.ACES3;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.APIHotels.tests.ACESII.ACESII_Planning_Regression_Suite;

public class ACES3_Schedules_Regression_Suite extends LocalDriverManager{
	
	public PgWrapper pgWrapper;
	ACESII_Planning_Regression_Suite aPRS;
	
	@Test(description = "JIRA# ATA-72, ATA-73 - Verify the schedules released from ACES shows in supplier website", groups = {"Regression" }, dataProvider = "TestDataFile")
	public void verifySchedulesReleased_Ack_SS(String tenantName, String serviceType,
			String city, String supplier, String overwriteIndicator, String testScenario,
			String supplierName, String user) throws Exception{
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		aPRS = new ACESII_Planning_Regression_Suite();
		
		//Create Schedule from ACESII Application
		getDriver().get(aces2Url);
		aPRS.createSchedulesWithDiffFilters(readPropValue("username"), readPropValue("password"), tenantName, readPropValue("bidMonthIndex"), 
				serviceType, city, supplier, overwriteIndicator, testScenario);
		
		//Release Schedule from ACESII Application
		String noStatusFilter = "";
		pgWrapper.schedulesMenu.clickViewSchedulesLink();
		pgWrapper.viewSchedulesPage.setSchedulesCriteria(serviceType, city, Integer.parseInt(supplier), noStatusFilter,
				noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter, noStatusFilter,
				noStatusFilter);
		pgWrapper.viewSchedulesPage.clickFindBtn();
		pgWrapper.viewSchedulesPage.releaseSchedules();
		String releasedDate = pgWrapper.viewSchedulesPage.getReleasedDate();
		//GoTo ACESIII Supplier Site, Select Supplier, Navigate to SupplierSchedules Page
		getDriver().get(aces3SupplierUrl);
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"), readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnSchedulesLink();
		
		//Verify Released schedule
		pgWrapper.aces3SupplierSchedulesPage.verifyReleaseDate(releasedDate);
		pgWrapper.aces3SupplierSchedulesPage.downloadPdf();
		pgWrapper.aces3SupplierSchedulesPage.verifyAcknowledged(user);
		pgWrapper.aces3SupplierSchedulesPage.export();
	}

}
