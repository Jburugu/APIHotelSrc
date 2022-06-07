package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class ReconcilledInvoicesPage extends BasePage {

	public EventFiringWebDriver driver=null;

	/**
	 * ACCOUNTING -- RECONCILED INVOICES(AIRCANADA)
	 */
	@FindBy(how = How.XPATH, using = "//label[text()=' Hotel']/preceding-sibling::input[@type='radio']")
	public WebElement hotelServicesRadioButton;

	@FindBy(how = How.XPATH, using = "//label[text()=' Transportation']/preceding-sibling::input[@type='radio']")
	public WebElement transportationServicesRadioButton;

	@FindBy(how = How.XPATH, using = ".//*[@value='Retrieve']")
	public WebElement retrieveButton;

	public ReconcilledInvoicesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void reconciledInvoice() {

		clickOn(hotelServicesRadioButton);

		Assert.assertTrue(hotelServicesRadioButton.isSelected(), "Hotel services radio button is not selected");

		clickOn(transportationServicesRadioButton);

		Assert.assertTrue(transportationServicesRadioButton.isSelected(),
				"Transportation services radio button is not selected");

		waitForElementVisibility(retrieveButton);

	}

}
