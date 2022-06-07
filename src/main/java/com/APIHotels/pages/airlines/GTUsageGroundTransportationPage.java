package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class GTUsageGroundTransportationPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// ACCOUNTING -- GROUND TRANSPORTATION -- GT USAGES(AZUL/Airnewzealand)

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'gtEInvUsageForm:invoiceMonthRadio')]")
	public WebElement invoiceMonthRadioBtn;

	@FindBy(how = How.ID, using = "gtEInvUsageForm:calendarMonth:bidPeriods")
	public WebElement invoiceMonthBidPeriod;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'gtEInvUsageForm:invoiceApprovedMonthRadio')]")
	public WebElement invoiceApprovedMonthRadioBtn;

	@FindBy(how = How.ID, using = "gtEInvUsageForm:calendarApprvMonth:approvedBidPeriods")
	public WebElement invoiceApprovedMonthBidPeriod;

	@FindBy(how = How.ID, using = "gtEInvUsageForm:retrieve")
	public WebElement retrieveButton;
	
	public GTUsageGroundTransportationPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void gtUsageAndGTPaymentFileUnderGT(String invoiceMonthValue, String invoiceApprovedMonthValue) {
		commonFunctionalityForGTusageAndGtPaymentFile(invoiceMonthValue, invoiceApprovedMonthValue);
	}

	public void commonFunctionalityForGTusageAndGtPaymentFile(String invoiceMonthValue,
			String invoiceApprovedMonthValue) {
		Assert.assertTrue(invoiceMonthRadioBtn.isSelected(), "Invoice month radio btn is not selected");
		int invoiceMonthVal = Integer.parseInt(invoiceMonthValue);
		selectByIndex(invoiceMonthBidPeriod, invoiceMonthVal);

		clickOn(invoiceApprovedMonthRadioBtn);
		int invoiceApprovedMonthVal = Integer.parseInt(invoiceApprovedMonthValue);
		selectByIndex(invoiceApprovedMonthBidPeriod, invoiceApprovedMonthVal);

		waitForElementVisibility(retrieveButton);
	}
}
