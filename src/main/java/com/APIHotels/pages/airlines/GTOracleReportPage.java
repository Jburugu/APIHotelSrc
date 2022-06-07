package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class GTOracleReportPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// REPORTS -- GTREPORTS -- GT ORACLE REPORTS (VIRGIN AUSTRALIA)

	@FindBy(how = How.XPATH, using = "//label[text()=' Actual']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeActual;

	@FindBy(how = How.XPATH, using = "//label[text()=' Accrual']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeAccrual;

	@FindBy(how = How.XPATH, using = "//label[text()=' Variance']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeVariance;

	@FindBy(how = How.XPATH, using = ".//*[@id='reportsForm:calendarMonth:bidPeriods']")
	public WebElement oracleExtractBillingPeriod;

	@FindBy(how = How.XPATH, using = "//input[@value='Export']")
	public WebElement oracleExtractExportBtn;

	@FindBy(how = How.XPATH, using = "//label[text()=' Approved Date Range']/preceding-sibling::input[@type='radio']")
	public WebElement approvedDtRangeGTPaymentFile;

	@FindBy(how = How.XPATH, using = "//label[text()=' Billing Period Range']/preceding-sibling::input[@type='radio']")
	public WebElement billingPeriodRangeGTPaymentFile;

	@FindBy(how = How.ID, using = "reportsForm:fromDateInputDate")
	public WebElement fromDate;

	@FindBy(how = How.ID, using = "reportsForm:fromDatePopupButton")
	public WebElement fromDateCalendar;

	@FindBy(how = How.ID, using = "reportsForm:toDateInputDate")
	public WebElement toDate;

	@FindBy(how = How.ID, using = "reportsForm:toDatePopupButton")
	public WebElement toDateCalendar;

	public GTOracleReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	public void gtOracleReport(String billingPeriodValue) {
		waitForElementVisibility(oracleExtractReportTypeActual);
		if (oracleExtractReportTypeActual.isSelected() == true) {
			waitForElementVisibility(approvedDtRangeGTPaymentFile);
			Assert.assertTrue(approvedDtRangeGTPaymentFile.isSelected(), "Approved date range type is not selected");

			clickOn(billingPeriodRangeGTPaymentFile);
			Assert.assertTrue(billingPeriodRangeGTPaymentFile.isSelected(),"Billing period range type is not selected");
			waitForElementVisibility(fromDate);
			waitForElementVisibility(fromDateCalendar);
			waitForElementVisibility(toDate);
			waitForElementVisibility(toDateCalendar);
		}
		clickOn(oracleExtractReportTypeAccrual);
		if (oracleExtractReportTypeAccrual.isSelected() == true)
			selectByIndex(oracleExtractBillingPeriod, Integer.parseInt(billingPeriodValue));

		clickOn(oracleExtractReportTypeVariance);
		if (oracleExtractReportTypeVariance.isSelected() == true)
			selectByIndex(oracleExtractBillingPeriod, Integer.parseInt(billingPeriodValue));

		waitForElementVisibility(oracleExtractExportBtn);

	}
}
