package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ManageOpenInvoice extends BasePage{
	
	@FindBy(id = "openlInvoice")
	public WebElement manageOpenInvoicesLink;
	
	@FindBy(id = "hotel")
	public WebElement hotelServices;
	
	@FindBy(id = "gt")
	public WebElement gtServices;
	
	@FindBy(id = "both")
	public WebElement bothServices;
	
	@FindBy(css = "input[value='proforma']")
	public WebElement newProforma;
	
	@FindBy(css = "input[value='allManual']")
	public WebElement allOpenManualInvoiceCheckbox;
	
	@FindBy(css = "input[value='myManual']")
	public WebElement myOpenManualInvoiceCheckbox;
	
	@FindBy(css = "body > table > tbody > tr:nth-child(2) > td.Aces_Body > form > div > fieldset:nth-child(3) > div > input")
	public WebElement manageOpenInvoicesSearchBtn;
	
	@FindBy(css = "input[value='Challenged']")
	public WebElement challengedCheckbox;
	
	@FindBy(css = "input[value='accepted']")
	public WebElement acceptedCheckbox;
	
	@FindBy(css = "input[value='reviewed']")
	public WebElement reviewedCheckbox;
	
	@FindBy(id = "period")
	public WebElement billingPeriodSuppliersResponses;
	
	@FindBy(css = "body > table > tbody > tr:nth-child(2) > td.Aces_Body > form > div > fieldset:nth-child(4) > table > tbody > tr > td:nth-child(3) > div > input")
	public WebElement manageSupplierResponsesSearchBtn;
	
	
	
	public EventFiringWebDriver driver=null;
	
	public Page_ManageOpenInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void manageOpenInvoice(String currentMMMYYYY) throws Exception {
		clickOn(manageOpenInvoicesLink);
		waitForElementVisibility(hotelServices);
		clickOn(hotelServices);
		waitForElementVisibility(gtServices);
		clickOn(gtServices);
		waitForElementVisibility(bothServices);
		clickOn(bothServices);
		waitForElementVisibility(newProforma);
		waitForElementVisibility(allOpenManualInvoiceCheckbox);
		clickOn(allOpenManualInvoiceCheckbox);
		clickOn(newProforma);
		waitForElementVisibility(myOpenManualInvoiceCheckbox);
		clickOn(myOpenManualInvoiceCheckbox);
		waitForElementVisibility(manageOpenInvoicesSearchBtn);
		waitForElementVisibility(challengedCheckbox);
		waitForElementVisibility(acceptedCheckbox);
		clickOn(acceptedCheckbox);
		clickOn(challengedCheckbox);
		waitForElementVisibility(reviewedCheckbox);
		clickOn(reviewedCheckbox);
		waitForElementVisibility(reviewedCheckbox);
		selectByVisibleText(billingPeriodSuppliersResponses, currentMMMYYYY);
		waitForElementVisibility(manageSupplierResponsesSearchBtn);

	}
	
}
