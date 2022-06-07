package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class GTPaymentFileReportsPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// REPORTS -- GT REPORTS -- GT PAYMENT FILE(VIRGIN AUSTRALIA)

	@FindBy(how = How.XPATH, using = "//label[text()=' Approved Date Range']/preceding-sibling::input[@type='radio']")
	public WebElement approvedDtRangeGTPaymentFile;

	@FindBy(how = How.XPATH, using = "//label[text()=' Billing Period Range']/preceding-sibling::input[@type='radio']")
	public WebElement billingPeriodRangeGTPaymentFile;

	@FindBy(how = How.ID, using = "gtPaymentFileForm:fromDateInputDate")
	public WebElement fromDateGTPaymentFile;

	@FindBy(how = How.ID, using = "gtPaymentFileForm:fromDatePopupButton")
	public WebElement fromDateCalendar;

	@FindBy(how = How.ID, using = "gtPaymentFileForm:toDateInputDate")
	public WebElement toDateGTPaymentFile;

	@FindBy(how = How.ID, using = "gtPaymentFileForm:toDatePopupButton")
	public WebElement toDateCalendar;

	@FindBy(how = How.XPATH, using = ".//*[@id='gtPaymentFileForm:subButton']")
	public WebElement downloadBtnPaymentFile;

	public GTPaymentFileReportsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void gtPaymentFile() {
		waitForElementVisibility(approvedDtRangeGTPaymentFile);
		Assert.assertTrue(approvedDtRangeGTPaymentFile.isSelected(), "Approved date range type is not selected");

		clickOn(billingPeriodRangeGTPaymentFile);
		Assert.assertTrue(billingPeriodRangeGTPaymentFile.isSelected(), "Billing period range type is not selected");

		waitForElementVisibility(fromDateGTPaymentFile);
		waitForElementVisibility(fromDateCalendar);

		waitForElementVisibility(toDateGTPaymentFile);
		waitForElementVisibility(toDateCalendar);

		waitForElementVisibility(downloadBtnPaymentFile);
	}

}
