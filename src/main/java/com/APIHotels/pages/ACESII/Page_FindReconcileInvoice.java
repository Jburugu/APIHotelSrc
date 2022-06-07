package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_FindReconcileInvoice extends BasePage{
	
public EventFiringWebDriver driver=null;

	@FindBy(xpath = "//*[text()='Reconcile Invoices']")
	public WebElement reconcileInvoicesLink;
	
	@FindBy(xpath = ".//*[@id='reconcileInvoice']/a")
	public WebElement findReconcileInvoices;
	
	@FindBy(id = "billingPeriod")
	public WebElement billingPeriodDropdown;
	
	@FindBy(id = "hotel")
	public WebElement hotelServices;

	@FindBy(id = "gt")
	public WebElement gtServices;
	
	@FindBy(id = "both")
	public WebElement bothServices;
	
	@FindBy(id = "locCd")
	public WebElement citySigninReport;
	
	@FindBy(css = "input[value='Get Suppliers']")
	public WebElement getSuppliersBtn;
	
	@FindBy(id = "supplierId")
	public WebElement selectSuppliers;
	
	@FindBy(css = "input[value='Find']")
	public WebElement findBtn;
	
	
	
	public Page_FindReconcileInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void findReconcileInvoices(String currentMMMYYYY, String cityJFK, String indexValue1) throws Exception {
		waitForElementVisibility(reconcileInvoicesLink);
		clickOn(reconcileInvoicesLink);
		waitForElementVisibility(findReconcileInvoices);
		clickOn(findReconcileInvoices);
		selectByVisibleText(billingPeriodDropdown, currentMMMYYYY);
		waitForElementVisibility(hotelServices);
		waitForElementVisibility(gtServices);
		waitForElementVisibility(bothServices);
		waitForElementVisibility(citySigninReport);
		typeInText(citySigninReport, cityJFK);// JFK
		clickOn(getSuppliersBtn);
		waitForElementVisibility(selectSuppliers);
		waitForElementVisibility(findBtn);
	}

}
