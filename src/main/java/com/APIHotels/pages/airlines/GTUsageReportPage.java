package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class GTUsageReportPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// REPORTS -- GT REPORTS -- GT USAGE REPORTS (VIRGIN AUSTRALIA)

	@FindBy(how = How.XPATH, using = "//label[text()=' Actual']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeActual;

	@FindBy(how = How.XPATH, using = "//label[text()=' Accrual']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeAccrual;

	@FindBy(how = How.XPATH, using = "//label[text()=' Variance']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeVariance;

	@FindBy(how = How.XPATH, using = ".//*[@id='gtUsageVAForm:gtUsageVAField:bidPeriod']")
	public WebElement billingPeriodUsageReport;

	@FindBy(how = How.XPATH, using = "//input[@value='Download']")
	public WebElement downloadBtnGTUsageReport;

	public GTUsageReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void gtUsageReport(String billingPeriodValue){
		waitForElementVisibility(oracleExtractReportTypeActual);
		Assert.assertTrue(oracleExtractReportTypeActual.isSelected(), "Actual report type is not selected");
		
		clickOn(oracleExtractReportTypeAccrual);
		Assert.assertTrue(oracleExtractReportTypeAccrual.isSelected(), "Accrual report type is not selected");
		
		clickOn(oracleExtractReportTypeVariance);
		Assert.assertTrue(oracleExtractReportTypeVariance.isSelected(), "Variance report type is not selected");
		
		selectByIndex(billingPeriodUsageReport, Integer.parseInt(billingPeriodValue));
		waitForElementVisibility(downloadBtnGTUsageReport);
	}

}
