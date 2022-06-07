package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_GTInvoicing_CreateManaulInvoice extends BasePage {

	public EventFiringWebDriver driver=null;
	public Page_GTInvoicing_CreateManaulInvoice(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='locCdId']")
	private WebElement textBox_City;

	@FindBy(xpath="//fieldset//input[@name='supplierName']")
	private WebElement btn_getSupplierName;

	@FindBy(xpath="//select[@id='supplierid']")
	private WebElement select_SupplierID;

	@FindBy(xpath="//select[@id='billingPeriod']")
	private WebElement select_BillingPeriod;

	@FindBy(xpath="//select[@id='currency']")
	private WebElement select_Currency;

	@FindBy(xpath="//select[@id='adjustmentReason1']")
	private WebElement select_adjustmentReason1;

	@FindBy(xpath="//input[@id='adjustmentComments1']")
	private WebElement textBox_adjustmentComments;

	@FindBy(xpath="//input[@id='adjustmentRides1']")
	private WebElement textBox_adjustmentRides1;

	@FindBy(xpath="//input[@id='adjustmentAmount1']")
	private WebElement textBox_adjustmentAmount1;

	@FindBy(xpath="//input[@value='Create Manual Invoice']")
	private WebElement btn_CreateManualInvoice;

	@FindBy(xpath="//textarea[@id='comment1']")
	private WebElement textarea_Comments;

	@FindBy(xpath="//li[@id='manualGTInvoice']//a[contains(text(),'Create Manual Invoice')]")
	private WebElement tab_createManulInvoice;

	@FindBy(xpath="//input[@id='invoiceNo']")
	private WebElement invoiceNumber;

	public String createGTManualInvoice(String cityValue,String supplierValue,String billingPeriodValue,String currencyValue, String reasonValue,String commentsAdj,String noOfPickupsValue,String amountAdj,String invoiceComments) throws InterruptedException{
		//clickOn(tab_createManulInvoice, "GTInvoicingLink -> CreateManualInvoiceLink");
		System.out.println("I am here");
		Thread.sleep(10000);
		typeInText(textBox_City, cityValue,  "CreateGTManualInvoicePage -> City");
		clickOn(btn_getSupplierName, "CreateGTManualInvoicePage -> GetSupplier Button");
		selectByVisibleText(select_SupplierID,supplierValue, "CreateGTManualInvoicePage -> Supplier Dropdown");
		selectByVisibleText(select_BillingPeriod, billingPeriodValue, "CreateGTManualInvoicePage -> BiilingPeriod Dropdown");
		Thread.sleep(2000);
		selectByVisibleText(select_Currency, currencyValue, "CreateGTManualInvoicePage -> Currency Dropdown");
		selectByVisibleText(select_adjustmentReason1, reasonValue, "CreateGTManualInvoicePage -> Reason Dropdown");
		typeInText(textBox_adjustmentComments, commentsAdj, "CreateGTManualInvoicePage -> Comments");
		typeInText(textBox_adjustmentRides1, noOfPickupsValue, "CreateGTManualInvoicePage -> Pickups");
		typeInText(textBox_adjustmentAmount1, amountAdj, "CreateGTManualInvoicePage -> Amount");
//		typeInText(textarea_Comments, invoiceComments,"CreateGTManualInvoicePage -> Comments");
		String getInvoce = invoiceNumber.getAttribute("value");
		String actString = getInvoce.substring(getInvoce.indexOf("-") + 1, getInvoce.length());
		clickOn(btn_CreateManualInvoice, "CreateGTManualInvoicePage -> CreateManualInvoice Button");
		unExpectedAcceptAlert();
		return actString;
	}

}
