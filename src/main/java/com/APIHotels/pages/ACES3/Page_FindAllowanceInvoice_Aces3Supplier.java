package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_FindAllowanceInvoice_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "allowanceInvoiceSearchForm:tenant")
	private WebElement selectAirlineDropDown;
	
	@FindBy(id = "allowanceInvoiceSearchForm:invoiceStatus")
	private WebElement invoiceStatusDropDown;
	
	@FindBy(xpath = "//span[contains(text(), 'Find')]")
	private WebElement findBtn;
	
	String invoiceStatusXpath1 = "//ul[@id='allowanceInvoiceSearchForm:invoiceStatus_items']/li[text()='";
	String invoiceStatusXpath2 = "']";
	
	String tenantNameXpath1 = "//ul[@id='allowanceInvoiceSearchForm:tenant_items']/li[text()='";
	String tenantNameXpath2 = "']";
	
	String apiInvoiceNumberXpath1 = "//*[@id='allowanceInvoiceSearchForm:allowanceSearchTable_data']//td[text()='";
	String apiInvoiceNumberXpath2 = "']";
	String viewLinkXpath = "/preceding-sibling::td/a";
	
	public EventFiringWebDriver driver=null;
	
	public Page_FindAllowanceInvoice_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public void findAllowanceInvoice(String invoiceStatus, String invoiceNumber, String tenant){
		clickOn(selectAirlineDropDown, "FindAllowanceInvoicePage -> Select Airline drop down");
		clickOn(findElementByXpath(tenantNameXpath1+tenant+tenantNameXpath2), "FindAllowanceInvoicePage -> "+tenant+" option");
		clickOn(invoiceStatusDropDown, "FindAllowanceInvoicePage -> Status drop down");
		clickOn(findElementByXpath(invoiceStatusXpath1+invoiceStatus+invoiceStatusXpath2), "FindAllowanceInvoicePage -> Invoice Status: "+invoiceStatus +" option");
		clickOn(findBtn, "FindAllowanceInvoicePage -> Find Btn");
		waitForElementToDisappear(spinner);
		assertTrue(findElementByXpath(apiInvoiceNumberXpath1+invoiceNumber+apiInvoiceNumberXpath2).isDisplayed(), "Invoice Number does not exist!!");
	}
	
	public void viewAllowanceInvoice(String invoiceNumber){
		clickOn(findElementByXpath(apiInvoiceNumberXpath1+invoiceNumber+apiInvoiceNumberXpath2+viewLinkXpath), "FindAllowanceInvoicePage -> "+invoiceNumber+" link");
		waitForElementToDisappear(spinner);
	}
	
	public void findAllowanceInvoice_workflow(String invoiceStatus, String invoiceNumber, String tenant){
		clickOn(selectAirlineDropDown, "FindAllowanceInvoicePage -> Select Airline drop down");
		clickOn(findElementByXpath(tenantNameXpath1+tenant+tenantNameXpath2), "FindAllowanceInvoicePage -> "+tenant+" option");
		clickOn(invoiceStatusDropDown, "FindAllowanceInvoicePage -> Status drop down");
		clickOn(findElementByXpath(invoiceStatusXpath1+invoiceStatus+invoiceStatusXpath2), "FindAllowanceInvoicePage -> Invoice Status: "+invoiceStatus +" option");
		waitForElementVisibility(findBtn);
	}
	

}
