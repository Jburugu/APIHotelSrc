package com.APIHotels.pages.ACES3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_InvoiceStatusReport_ACES3ACES extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	WebElement spinner;
	
	@FindBy(xpath="//table[contains(@id,'findBy')]//tr[1]//div[2]/span")
	private WebElement InvoiceNumberRB;
	
	@FindBy(xpath="//table[contains(@id,'findBy')]//tr[2]//div[2]/span")
	private WebElement billingPeriodAndStatusRB;
	
	@FindBy(xpath="//table[contains(@id,'findBy')]//tr[3]//div[2]/span")
	private WebElement supplierAndLocationRB;
	
	@FindBy(id="reportInvForm:search")
	private WebElement searchButton;
	
	@FindBy(xpath="//input[contains(@id,'apiInvoiceNum')]")
	private WebElement apiInvoiceNumTextBox;
	
	@FindBy(xpath="//input[contains(@id,'supplierInvoiceNum')]")
	private WebElement supplierInvoiceNumTextBox;
	
	@FindBy(xpath="//tbody[@id='reportInvForm:invStatusReportDataTable_data']/tr")
	private List<WebElement> recordsInInvoiceStatusTable;
	
	String invoiceStatusXpath1 ="//*[contains(text(), '";
	String invoiceStatusXpath2 ="')]/../td[8]";
	
	@FindBy(xpath="//*[contains(@src,'excel.jpg')]/..")
	private WebElement excelExport;
	
	@FindBy(xpath="//div[contains(@id,'tableRow')]//div[contains(@id,'tenant')]")
	private WebElement tenantSelection;
	
	String tenantNameXpath="//*[contains(@id,'tenant_panel')]//ul/li[contains(text(),'";
	String genericXpath="')]";
	
	String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/span[1]")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	@FindBy(xpath="//span[contains(@id,'startDate')]/button/span[1]")
	private WebElement calendarBtn;
	
	String statusXpath1="//*[contains(text(),'";
	String statusXpath2="')]/../div/div[2]/span";
	
	@FindBy(xpath="//*[contains(@id,'city_input')]")
	private WebElement city;
	
	String cityValueXpath="//*[contains(@id,'city_panel')]/ul/li[(@data-item-value='";

	@FindBy(xpath="//div[contains(@id,'supplier')]")
	private WebElement supplierSelection;
	
	String supplierListXpath="//ul[contains(@id,'supplier_items')]/li[contains(text(),'";
	
	public EventFiringWebDriver driver= null;
	public Page_InvoiceStatusReport_ACES3ACES(EventFiringWebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	private void waitForSpinnerToDisappear() {
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(30));
		wait1.pollingEvery(Duration.ofSeconds(2));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}
	
	private void selectAirlines(String tenantName) {
		clickOn(tenantSelection,"Invoice Status Report Page -> Airline Select");
		clickOn(findElementByXpath(tenantNameXpath + tenantName + genericXpath),"Invoice Status Report Page -> Airline Selected");
		}
	
	private void enterCity(String cityCode){
		typeInText(city, cityCode,"Invoice Status Report Page -> City Textbox");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(cityValueXpath + cityCode + genericXpath),"Invoice Status Report Page -> City Selected From Suggestion List");
		waitForElementToDisappear(spinner);
	}
	
	private void selectSupplier(String supplierName) {
		clickOn(supplierSelection,"Invoice Status Report Page -> Supplier Select");
		clickOn(findElementByXpath(supplierListXpath + supplierName + genericXpath),"Invoice Status Report Page -> Supplier Selected From Suggestion List");
		}
	
	
	 private void selectBillingPeriod(String billingPeriodDate){
		 clickOn(calendarBtn,"Invoice Status Report Page -> Billing Period Date Picker Button");
		 datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", billingPeriodDate);
	 }
	 
	 private void verifyInvoiceStatus(String apiInvoiceNumber, String invoiceStatusValue){
			
		 if(recordsInInvoiceStatusTable.size()>0){
			String invoiceStatus= findElementByXpath(invoiceStatusXpath1+apiInvoiceNumber+invoiceStatusXpath2).getText();
			if(invoiceStatus.equalsIgnoreCase(invoiceStatusValue)){
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Invoice Status For " + apiInvoiceNumber + " is " + invoiceStatusValue);
			} else
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Invoice Status For " + apiInvoiceNumber + " is different than " + invoiceStatusValue);
		} else ExtentTestManager.getTest().log(LogStatus.FAIL,
				apiInvoiceNumber+ " Not Found with searched filters");
	 }
	
	public void verifyInvoiceStatusWithInvoiceNumber(String apiInvoiceNumber, String supplierInvoiceNumber, String invoiceStatusValue, String reportUsing){
		if(reportUsing.equalsIgnoreCase("Invoice number")){
		clickOn(InvoiceNumberRB,"Invoice Status Report Page -> Invoice Number Radio Button");
		waitForSpinnerToDisappear();
		typeInText(apiInvoiceNumTextBox, apiInvoiceNumber,"Invoice Status Report Page -> Invoice Number Textbox");
		typeInText(supplierInvoiceNumTextBox, supplierInvoiceNumber,"Invoice Status Report Page -> Supplier Invoice Number Textbox");
		clickOn(searchButton,"Invoice Status Report Page -> Search Button");
		waitForSpinnerToDisappear();
		verifyInvoiceStatus(apiInvoiceNumber, invoiceStatusValue);
		clickOn(excelExport,"Invoice Status Report Page -> Excel Icon Image Link");
		waitTime(5000);
		}
	}
	
	public void verifyInvoiceStatusUsingBiilingPeriodAndStatus(String tenantName, String billingPeriodDate,
			String apiInvoiceNumber, String invoiceStatusValue, String reportUsing, String selectSpecificStatus) {

		if (reportUsing.equalsIgnoreCase("Billing number and All Statuses")) {
			clickOn(billingPeriodAndStatusRB,"Invoice Status Report Page -> Billing Period And Status Radio Button");
			selectAirlines(tenantName);
			waitForSpinnerToDisappear();
			selectBillingPeriod(billingPeriodDate);
			waitForSpinnerToDisappear();
			clickOn(searchButton,"Invoice Status Report Page -> Search Button");
			waitForSpinnerToDisappear();
			verifyInvoiceStatus(apiInvoiceNumber, invoiceStatusValue);
			clickOn(excelExport,"Invoice Status Report Page -> Excel Icon Image Link");
			waitTime(5000);
		}

		else if (reportUsing.equalsIgnoreCase("Billing number and Specific Status")) {
			clickOn(billingPeriodAndStatusRB,"Invoice Status Report Page -> Billing Period And Status Radio Button");
			selectAirlines(tenantName);
			waitForSpinnerToDisappear();
			selectBillingPeriod(billingPeriodDate);
			waitForSpinnerToDisappear();
			clickOn(findElementByXpath(statusXpath1 + "Specific" + statusXpath2),"Invoice Status Report Page -> Billing Period And Status -> Specific Status Radio Button");
			waitForSpinnerToDisappear();
			clickOn(findElementByXpath(statusXpath1 + selectSpecificStatus + statusXpath2));
			clickOn(searchButton,"Invoice Status Report Page -> Search Button");
			waitForSpinnerToDisappear();
			verifyInvoiceStatus(apiInvoiceNumber, invoiceStatusValue);
			clickOn(excelExport,"Invoice Status Report Page -> Excel Icon Image Link");
			waitTime(5000);
		}

		else if (reportUsing.equalsIgnoreCase("Billing number and Not Submitted Status")) {
			clickOn(billingPeriodAndStatusRB,"Invoice Status Report Page -> Billing Period And Status Radio Button");
			selectAirlines(tenantName);
			waitForSpinnerToDisappear();
			selectBillingPeriod(billingPeriodDate);
			waitForSpinnerToDisappear();
			clickOn(findElementByXpath(statusXpath1 + "Not Submitted" + statusXpath2),"Invoice Status Report Page -> Billing Period And Status -> Not Submitted Status Radio Button");
			waitForSpinnerToDisappear();
			clickOn(searchButton,"Invoice Status Report Page -> Search Button");
			waitForSpinnerToDisappear();
			verifyInvoiceStatus(apiInvoiceNumber, invoiceStatusValue);
			clickOn(excelExport,"Invoice Status Report Page -> Excel Icon Image Link");
			waitTime(5000);
		}
	}

	public void verifyInvoiceStatusWithSupplierAndLocation(String tenantName, String billingPeriodDate, String cityCode, String supplierName, String apiInvoiceNumber, String invoiceStatusValue, String reportUsing){
		if(reportUsing.equalsIgnoreCase("Supplier And Location")){
		clickOn(supplierAndLocationRB,"Invoice Status Report Page -> Supplier And Location Radio Button");
		waitForSpinnerToDisappear();
		selectAirlines(tenantName);
		waitForSpinnerToDisappear();
		enterCity(cityCode);
		waitForSpinnerToDisappear();
		selectSupplier(supplierName);
		waitForSpinnerToDisappear();
		selectBillingPeriod(billingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(searchButton,"Invoice Status Report Page -> Search Button");
		waitForSpinnerToDisappear();
		verifyInvoiceStatus(apiInvoiceNumber, invoiceStatusValue);
		clickOn(excelExport,"Invoice Status Report Page -> Excel Icon Image Link");
		waitTime(5000);
	}
	}

		
}
	
	
	
	
	
	 
	 

	


