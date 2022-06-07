package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class VendorMismatchPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- VENDOR MISMATCH (VIRGIN AUSTRALIA)

	@FindBy(how = How.XPATH, using = "//span[text()='Reservation Start Date']/parent::td/following-sibling::td//input[@type='text']")
	public WebElement reservationStartDateVendorMismatch;

	@FindBy(how = How.XPATH, using = "//span[text()='Reservation End Date']/parent::td/following-sibling::td//input[@type='text']")
	public WebElement reservationEndDateVendorMismatch;

	@FindBy(how = How.ID, using = "reservationsForm:button")
	public WebElement removeFromQueueBtn;

	@FindBy(how = How.XPATH, using = ".//*[@value='Submit']")
	public WebElement submitBtn;

	public VendorMismatchPage(EventFiringWebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public void vendorMismatch(){
		typeInText(reservationStartDateVendorMismatch, currentDate());
		typeInText(reservationEndDateVendorMismatch, currentDatePlus(1));
		waitForElementVisibility(removeFromQueueBtn);
		waitForElementVisibility(submitBtn);
	}
}
