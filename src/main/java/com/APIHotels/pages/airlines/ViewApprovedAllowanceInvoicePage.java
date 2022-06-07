package com.APIHotels.pages.airlines;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ViewApprovedAllowanceInvoicePage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- ALLOWANCES -- VIEW ALLOWANCE APPROVED INVOICE (NEWZEALAND)

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:locationArea']/div/input")
	public WebElement city;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:hotelIdArea']/div/select")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:monthArea']/div/select")
	public WebElement month;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:statusArea']/div/select")
	public WebElement status;

	@FindBy(how = How.XPATH, using = ".//*[@value='Search']")
	public WebElement searchButton;

	public ViewApprovedAllowanceInvoicePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void viewApprovedAllowanceInvoices(String cityValue, String hotelValue, String monthValue,
			String statusValue) {
		typeInText(city, cityValue);
		city.sendKeys(Keys.TAB);
		waitTime(7000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		selectByValue(month, monthValue);
		selectByVisibleText(status, statusValue);
		waitForElementVisibility(searchButton);
	}

}
