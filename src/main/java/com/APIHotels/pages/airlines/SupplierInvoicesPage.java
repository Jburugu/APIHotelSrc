package com.APIHotels.pages.airlines;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class SupplierInvoicesPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// ACCOUNTING -- HOTEL -- SUPPLIER INVOICES

	@FindBy(xpath="//select[contains(@id,'billingMonths')]")
	public WebElement billingPeriod;

	@FindBy(css= "input[id$='yearText']")
	public WebElement bilingYear;

	@FindBy(xpath= "//label[text()=' Hotel']/preceding-sibling::input[@type='radio']")
	public WebElement hotelServicesRadioButton;

	@FindBy(xpath= "//label[text()=' Transportation']/preceding-sibling::input[@type='radio']")
	public WebElement transportationServicesRadioButton;

	@FindBy(css= "input[value='Retrieve']")
	public WebElement retrieveButton;
	
	@FindBy(xpath ="//td[contains(text(),'Wait Please...')]")
	public WebElement waitPleaseOverlay;
	
	@FindBy(xpath="//*[@id='invoicesForm:datesTable:tb']/tr")
	public List<WebElement> consolidatedInvoices;
	
	@FindBy(xpath="//*[contains(@id,'invoicesForm:CADinvoicesTable')]/tr")
	private List<WebElement> cadInvoicesTable;

	String dateLinkXpath1="//*[@id='invoicesForm:datesTable:tb']/tr//a[contains(text(),'";
	String dateLinkXpath2="')]";
	
	@FindBy(linkText= "CSV")
	public WebElement exportOptionCSV;

	@FindBy(linkText= "Excel")
	public WebElement exportOptionExcel;
	
	public SupplierInvoicesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifySupplierInvoices(String billingMonth, String billingYear, String serviceType) {
		selectByVisibleText(billingPeriod, billingMonth, "ViewSupplierConsolidatedInvoices Page -> Billing Period Dropdown" );
		typeInText(bilingYear, billingYear, "ViewSupplierConsolidatedInvoices Page -> Billing Year");
		if(serviceType.equalsIgnoreCase("Hotel"))
		clickOn(hotelServicesRadioButton, "ViewSupplierConsolidatedInvoices Page -> HotelServiceType Radiobutton");
		else
		clickOn(transportationServicesRadioButton, "ViewSupplierConsolidatedInvoices Page -> TransportationServiceType Radiobutton");
		clickOn(retrieveButton, "ViewSupplierConsolidatedInvoices Page -> Retrieve Button");
		waitOverlayToDisappear();
		if(consolidatedInvoices.size()>0){
			clickOn(findElementByXpath(dateLinkXpath1+readPropValue("ProformaCreatedDate")+dateLinkXpath2));
			waitOverlayToDisappear();
			if(cadInvoicesTable.size()>0){
				ExtentTestManager.getTest().log(LogStatus.PASS,	"Supplier Invoices generated in airlines for ->  "+serviceType);
				clickOn(exportOptionCSV, "ViewSupplierConsolidatedInvoices Page -> CSV Link");
				clickOn(exportOptionExcel, "ViewSupplierConsolidatedInvoices Page -> Excel Link");
			}
			else ExtentTestManager.getTest().log(LogStatus.FAIL, "Supplier Invoices generated in airlines for->  "+serviceType);
		}
	}
	
	public void waitOverlayToDisappear() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.invisibilityOf(waitPleaseOverlay));
	}

	
}
