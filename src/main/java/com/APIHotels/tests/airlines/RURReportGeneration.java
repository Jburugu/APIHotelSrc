package com.APIHotels.tests.airlines;

import java.sql.SQLException;

import org.testng.annotations.Test;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class RURReportGeneration extends Driver {

	public PgWrapper pgWrapper;

	@Test(description = "Accounting Tab  - Room Usage page verification", dataProvider = "TestDataFile", groups = "ExpressJetERJ")
	public void RoomUsageReport(String airlinesUsername, String airlinesPassword)
			throws SQLException, ClassNotFoundException, Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlinesUsername, airlinesPassword);

		pgWrapper.accountingTab.clickOnRoomUsageLink();
		pgWrapper.roomUsagePage.clickOnConsolidatedInvoice();
		pgWrapper.roomUsagePage.clickOnRetrieve();
	}

	@Test(description = "Accounting Tab  - Room Usage page verification", dataProvider = "TestDataFile", groups = "ExpressJetERJ")
	public void GTUsageReport(String airlinesUsername, String airlinesPassword)
			throws SQLException, ClassNotFoundException, Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlinesUsername, airlinesPassword);

		pgWrapper.accountingTab.clickOnGTUsageLink();
		pgWrapper.gtUsagePage.generateGLCodeReprotForConsolidatedInvoiceMonth();
		pgWrapper.gtUsagePage.clickOnRetrieveButton();
	}

}
