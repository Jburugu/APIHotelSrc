package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class Page_ConsolidatedEInvoiceReport_ACES3ACES extends BasePage {

	@FindBy( xpath= "//label[contains(@id,'tenant_label')]")
	private WebElement selectAirline; 
	
	@FindBy(xpath ="//ul[contains(@id,'tenant_items')]/li")
	private WebElement airlinesList;
		
	String tenantXpath1 = "//*[contains(@id, 'tenant_items')]/li[contains(text(), '";
	String tenantXpath2 = "')]";
		
	@FindBy(xpath="//*[contains(@id,'selectSupplierPanel')]//tr//span/span/input")
	private WebElement city;
	
	String cityNameXpath ="//span[contains(text(),'";
	String listxpath = "')]";
	String supplierNameXpath="//li[contains(text(),'";
	
	@FindBy(xpath="//label[contains(@id,'supplierSelector_label')]")
	private WebElement supplier; 
	
	@FindBy(xpath="//label[contains(text(),'Actual')]")
	private WebElement actual; 
		
	@FindBy(xpath="//label[contains(text(),'Accrual')]")
	private WebElement accrual;
		
	@FindBy(xpath="//label[contains(text(),'Variance')]")
	private WebElement variance; 
	
	@FindBy(xpath="//div[contains(@id,'start')]//img")
	private WebElement spinner;
	
	@FindBy(id="consolidatedInvoiceReport:startDate")
	private WebElement fromBillingPeriod; 
	
	@FindBy(id="consolidatedInvoiceReport:endDate")
	private WebElement toBillingPeriod; 
	
	@FindBy(className="ui-datepicker-title")
	private WebElement monthYearTitle;
	
	@FindBy(id="ui-datepicker-div")
	private WebElement calendar;
	
	String previousMonth="//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(id="consolidatedInvoiceReport:submitButton")
	private WebElement submitBtn;
	
	@FindBy(xpath="//div[@id='consolidatedInvoiceReport:results']/a")
	private WebElement exportExcelLink;
	
	@FindBy(xpath="//label[contains(text(),'Approved Date')]")
	private WebElement approvedDateRadioBtn; 
	
	@FindBy(id="consolidatedInvoiceReport:resultTable")
	private WebElement consolidatedResultTable;
	
	@FindBy(id="consolidatedInvoiceReport:epdSummaryTable")
	private WebElement epdSummaryTable;
	
	@FindBy(id="consolidatedInvoiceReport:nonEpdSummaryTable")
	private WebElement nonEpdSummaryTable;
	
	@FindBy(id="consolidatedInvoiceReport:filterSummaryTable")
	private WebElement filterSummaryTable;
	
	@FindBy(xpath = "//*[@id='consolidatedInvoiceReport:resultTable_data']/tr")
	private List<WebElement> consolidatedInvoiceReport_resultRows;
	
	@FindBy(xpath="//*[@id='consolidatedInvoiceReport:resultTable_head']//th")
	private List<WebElement> consolidatedInvoiceReport_resultHeader;
	
	String consolidatedInvoiceReportXpath1 = "//*[@id='consolidatedInvoiceReport:resultTable_head']//th[";
	String consolidatedInvoiceReportXpath2= "]/span[1]";
	
	
	
	public EventFiringWebDriver driver = null;
	public Page_ConsolidatedEInvoiceReport_ACES3ACES(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> accuralReport(String airlinesName, String cityCode, String supplierName, String fromBillingPeriodDate, String toBillingPeriodDate) throws Exception
	{	
		clickOn(selectAirline,"Consolidated E-Invoice Report Page -> Airline Select");
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(tenantXpath1+airlinesName+tenantXpath2),"Consolidated E-Invoice Report Page -> Airline Selected From Suggestion List");
		waitForSpinnerToDisappear();
		waitForElementVisibility(city);
		typeInText(city, cityCode,"Consolidated E-Invoice Report Page -> City textbox");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(cityNameXpath+cityCode+listxpath),"Consolidated E-Invoice Report Page -> City Selected From Suggestion List");
		waitForElementToDisappear(spinner);
		clickOn(supplier, "Consolidated E-Invoice Report Page -> Supplier Select");
		clickOn(findElementByXpath(supplierNameXpath + supplierName + listxpath), "Consolidated E-Invoice Report Page -> Supplier Selected From Suggestion List");
		waitForSpinnerToDisappear();
		
		clickOn(accrual, "Consolidated E-Invoice Report Page -> Accrual Radio Button");
		waitForSpinnerToDisappear();
		clickOn(fromBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period From DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(submitBtn, "Consolidated E-Invoice Report Page -> Submit Button");
		waitForSpinnerToDisappear();
		List<WebElement> consolidatedReportHeaders=consolidatedInvoiceReport_resultHeader;
		List<String> headersInConsolidatedReport= new ArrayList<String>();
		for(int i=1; i<=consolidatedReportHeaders.size(); i++){
			String header=findElementByXpath(consolidatedInvoiceReportXpath1+i+consolidatedInvoiceReportXpath2).getText();
			headersInConsolidatedReport.add(header);
		}
		waitForElementVisibility(exportExcelLink);
		clickOn(exportExcelLink, "Consolidated E-Invoice Report Page -> Export Excel Link");
		//waitForElementVisibility(consolidatedResultTable);
		waitForElementVisibility(filterSummaryTable);
		waitForElementVisibility(epdSummaryTable);
		waitForElementVisibility(nonEpdSummaryTable);
		return headersInConsolidatedReport;
	}

	public List<String> actualReport(String airlinesName, String cityCode, String supplierName, String fromBillingPeriodDate, String toBillingPeriodDate, String period) throws Exception
	{	
		clickOn(selectAirline,"Consolidated E-Invoice Report Page -> Airline Select");
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(tenantXpath1+airlinesName+tenantXpath2),"Consolidated E-Invoice Report Page -> Airline Select From Suggestion List");
		waitForSpinnerToDisappear();
		waitForElementVisibility(city);
		typeInText(city, cityCode,"Consolidated E-Invoice Report Page -> City textbox");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(cityNameXpath+cityCode+listxpath),"Consolidated E-Invoice Report Page -> City Selected From Suggestion List");
		waitForElementToDisappear(spinner);
		clickOn(supplier, "Consolidated E-Invoice Report Page -> Supplier Select");
		clickOn(findElementByXpath(supplierNameXpath + supplierName + listxpath), "Consolidated E-Invoice Report Page -> Supplier Selected From Suggestion List");
		waitForSpinnerToDisappear();
		
		clickOn(actual, "Consolidated E-Invoice Report Page -> Actual Radio Button");
		waitForSpinnerToDisappear();
		
		if(period.equalsIgnoreCase("BillingPeriod")){
		clickOn(fromBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period From DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period To DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		}
		else if(period.equalsIgnoreCase("ApprovedDate")){
			clickOn(approvedDateRadioBtn);
			waitForSpinnerToDisappear();
			clickOn(fromBillingPeriod, "Consolidated E-Invoice Report Page -> Approved Date From DatePicker");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
			waitForSpinnerToDisappear();
			clickOn(toBillingPeriod, "Consolidated E-Invoice Report Page -> Approved Date To DatePicker");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
			
		}
		waitForSpinnerToDisappear();
		clickOn(submitBtn, "Consolidated E-Invoice Report Page -> Submit Button");
		waitForSpinnerToDisappear();
		List<WebElement> consolidatedReportHeaders=consolidatedInvoiceReport_resultHeader;
		List<String> headersInConsolidatedReport= new ArrayList<String>();
		for(int i=1; i<=consolidatedReportHeaders.size(); i++){
			String header=findElementByXpath(consolidatedInvoiceReportXpath1+i+consolidatedInvoiceReportXpath2).getText();
			headersInConsolidatedReport.add(header);
		}
 	
		waitForElementVisibility(exportExcelLink);
		clickOn(exportExcelLink, "Consolidated E-Invoice Report Page -> Export Excel Link");
		//waitForElementVisibility(consolidatedResultTable);
		waitForElementVisibility(filterSummaryTable);
		waitForElementVisibility(epdSummaryTable);
		waitForElementVisibility(nonEpdSummaryTable);
		return headersInConsolidatedReport;
	}
	
	public void verifyConsolidatedPaymentGroups(){
		assertTrue(consolidatedInvoiceReport_resultRows.size()==1, "Not Consolidated!! "+consolidatedInvoiceReport_resultRows.size()+" groups are displayed in the results.");
	}
	
	
	
	public List<String> varianceReport(String airlinesName, String cityCode, String supplierName, String fromBillingPeriodDate, String toBillingPeriodDate, String period) throws Exception
	{	
		clickOn(selectAirline,"Consolidated E-Invoice Report Page -> Airline Select");
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(tenantXpath1+airlinesName+tenantXpath2),"Consolidated E-Invoice Report Page -> Airline Select From Suggestion List");
		waitForSpinnerToDisappear();
		waitForElementVisibility(city);
		typeInText(city, cityCode,"Consolidated E-Invoice Report Page -> City textbox");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(cityNameXpath+cityCode+listxpath));
		waitForElementToDisappear(spinner);
		clickOn(supplier, "Consolidated E-Invoice Report Page -> Supplier Select");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(supplierNameXpath + supplierName + listxpath), "Consolidated E-Invoice Report Page -> Supplier Selected From Suggestion List");
		waitForSpinnerToDisappear();
		
		clickOn(variance, "Consolidated E-Invoice Report Page -> Variance Radio Button");
		waitForSpinnerToDisappear();
		if(period.equalsIgnoreCase("billingPeriod")){
		clickOn(fromBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period From DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period To DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		waitForSpinnerToDisappear();
		}
		else if(period.equalsIgnoreCase("approvedDate")){
			clickOn(approvedDateRadioBtn);
			waitForSpinnerToDisappear();
			clickOn(fromBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period From DatePicker");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
			waitForSpinnerToDisappear();
			clickOn(toBillingPeriod, "Consolidated E-Invoice Report Page -> Billing Period To DatePicker");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
			waitForSpinnerToDisappear();		
		}
		clickOn(submitBtn, "Consolidated E-Invoice Report Page -> Submit Button");
		waitForSpinnerToDisappear();
		List<WebElement> consolidatedReportHeaders=consolidatedInvoiceReport_resultHeader;
		List<String> headersInConsolidatedReport= new ArrayList<String>();
		for(int i=1; i<=consolidatedReportHeaders.size(); i++){
			String header=findElementByXpath(consolidatedInvoiceReportXpath1+i+consolidatedInvoiceReportXpath2).getText();
			headersInConsolidatedReport.add(header);
		}
 	
		waitForElementVisibility(exportExcelLink);
		clickOn(exportExcelLink, "Consolidated E-Invoice Report Page -> Export Excel Link");
		//waitForElementVisibility(consolidatedResultTable);
		waitForElementVisibility(filterSummaryTable);
		waitForElementVisibility(epdSummaryTable);
		waitForElementVisibility(nonEpdSummaryTable);
		return headersInConsolidatedReport;
	}
		
	public void waitForSpinnerToDisappear()
	{
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
        wait1.withTimeout(Duration.ofMinutes(30));
        wait1.pollingEvery(Duration.ofSeconds(2));
        wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}
	

}
