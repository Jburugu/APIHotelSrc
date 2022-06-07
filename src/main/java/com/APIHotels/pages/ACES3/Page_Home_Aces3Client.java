package com.APIHotels.pages.ACES3;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Home_Aces3Client extends BasePage{
	
	@FindBy(linkText = "Select Tenant")
	private WebElement linkSelectTenant;
	
	@FindBy(xpath = "//label[@id='selectTenantForm:tenantId_label']")
	private WebElement dropDownSelectTenant;
		
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath ="//span[@class='ui-button-text ui-c'][contains(text(),'Select')]")
	private WebElement btn_Select;
	
	@FindBy(xpath = "//ul[contains(@id,'tenantId_items')]/li")
	private WebElement tenantList;
	
	private String tenantXpath1 = "//*[contains(@id, 'tenantId_items')]/li[contains(text(), '";
	private String tenantXpath2 = "')]";
	
	@FindBy(xpath = "//*[contains(@id, 'airportcode')]")
	private WebElement airportCityCode;
	
	@FindBy(id = "selectSupplierForm:supplierName_input")
	private WebElement supplierNameInput;
	
	/*@FindBy(id = "selectSupplierForm:supplierName_panel")
	private WebElement selectSupplierOption;*/
	
	private String selectSupplierOptionXpath1 ="//*[@id = 'selectSupplierForm:supplierName_panel']/ul/li[contains(@data-item-label,'";
	private String selectSupplierOptionXpath2 = "')]";
	
	@FindBy(id = "selectSupplierForm:selectSupplier")
	private WebElement selectBtn;
	
	@FindBy(id = "menuForm:schedules")
	private WebElement supplierSchedulesMenuLink;
	
	@FindBy(id = "menuForm:reservation")
	private WebElement supplierReservationsMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Admin')]")
	private WebElement adminMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Create User')]")
	private WebElement createUserLink;
	
	@FindBy(xpath="//a[contains(text(), 'Accounting')]")
	private WebElement AccountingLink;
	
	public EventFiringWebDriver driver=null;

	public Page_Home_Aces3Client(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectTenant(String tenant) throws IOException {
		clickOn(linkSelectTenant, "ACES3Client HomePage -> Select Tenant link");
		//waitForElementToDisappear(spinner);
		takeScreenshots();	
		clickOn(dropDownSelectTenant, "ACES3Client HomePage -> Select Tenant drop down");	
		waitForElementVisibility(tenantList);
		clickOn(findElementByXpath(tenantXpath1+tenant+tenantXpath2), "ACES3Client -> select tenant pop-up -> "+tenant+" option");		
		takeScreenshots();		
		clickOn(btn_Select, "ACES3Client HomePage -> Select btn");
		takeScreenshots();	
	}

	
	public void clickOnAccountingLink()
	{
		clickOn(AccountingLink, "ACES3Client HomePage -> NavigationalLinksPanel -> Accounting link");
	}
}
