package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ViewAllowanceApprovedInvoicePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- ALLOWANCES -- VIEW ALLOWANCE APPROVED INVOICE (NEWZEALAND)

	@FindBy(how = How.ID, using = "iconsearchApprovedInvoice")
	public WebElement viewApprovedAllowanceInvoicesLink;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:locationArea']/div/input")
	public WebElement city;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:hotelIdArea']/div/select")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:monthArea']/div/select")
	public WebElement month;

	@FindBy(how = How.XPATH, using = ".//*[@value='Search']")
	public WebElement searchButton;

	public ViewAllowanceApprovedInvoicePage(EventFiringWebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

}
