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
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_RecoverableTaxReport_ACES3ACES extends BasePage{

	@FindBy(xpath="//div[contains(@id,'tableRow')]//div[contains(@id,'tenant')]")
	private WebElement tenantSelection;
	
	String tenantNameXpath="//*[contains(@id,'tenant_panel')]//ul/li[contains(text(),'";
	String genericXpath="')]";
	
	@FindBy(xpath="//*[contains(@id,'selectSupplierPanel')]//input[contains(@id,'input')]")
	private WebElement city;
	
	String cityValueXpath="//*[contains(@id,'panel')]/ul/li[(@data-item-value='";	
	
	@FindBy(xpath="//*[contains(@id,'selectSupplierPanel')]//div[contains(@id,'supplierSelector')]")
	private WebElement supplierSelection;
	
	String supplierListXpath="//ul[contains(@id,'supplierSelector_items')]/li[contains(text(),'";
	
	@FindBy(xpath="//table[contains(@id,'reportType')]//tr[2]//span")
	private WebElement actualReportType;
	
	String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/span[1]")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	@FindBy(xpath="//span[contains(@id,'bpStart')]/button/span[1]")
	private WebElement calendarBtn;
	
	@FindBy(xpath="//span[contains(text(),'Generate Report')]/..")
	private WebElement generateReport;

	@FindBy(xpath="//*[contains(@id,'RecoverableTaxesResults')]//a")
	private List<WebElement> exportExportResults;
	
	@FindBy(xpath = "//*[@id='reconcileDashboard:dashboardAccordian']/div")
	private WebElement accountingDashboardDivCollapsable;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	String recoverableXpath="//tbody[contains(@id,'RecoverableTaxesResults_data')]//td[contains(text(),'";
	
	@FindBy(xpath="//tbody[contains(@id,'RecoverableTaxesResults_data')]//tr")
	private WebElement recoverableTaxTable;
	
	public EventFiringWebDriver driver=null;
	public Page_RecoverableTaxReport_ACES3ACES(EventFiringWebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	private void selectAirlines(String tenantName) {
		clickOn(tenantSelection,"Recoverable Tax Report -> Tenant Select");
		clickOn(findElementByXpath(tenantNameXpath + tenantName + genericXpath),"Recoverable Tax Report -> Tenant Selected");
		}
	
	private void enterCity(String cityCode){
		typeInText(city, cityCode,"Recoverable Tax Report -> City Textbox");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(cityValueXpath + cityCode + genericXpath),"Recoverable Tax Report -> Tenant Suggestion List");
		waitForElementToDisappear(spinner);
	}
	
	private void selectSupplier(String supplierName) {
		clickOn(supplierSelection,"Recoverable Tax Report -> Supplier Select");
		clickOn(findElementByXpath(supplierListXpath + supplierName + genericXpath),"Recoverable Tax Report -> Supplier Suggestion List");
		}
	
	 private void selectBillingPeriod(String billingPeriodDate){
		 clickOn(calendarBtn,"Recoverable Tax Report -> Calender Button");
		 datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", billingPeriodDate);
	 }
	 
	 private void waitForSpinnerToDisappear() {
			WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
			wait1.withTimeout(Duration.ofMinutes(30));
			wait1.pollingEvery(Duration.ofSeconds(2));
			wait1.ignoring(StaleElementReferenceException.class);
			wait1.until(ExpectedConditions.invisibilityOf(spinner));
		}
	 
	public void searchAndVerifyRecoverableReport(String tenantName, String cityCode, String supplierName,
			String billingPeriodDate, String recoverableValue, String taxTypeValue) throws Exception {
		 selectAirlines(tenantName);
		 waitForSpinnerToDisappear();
		 enterCity(cityCode);
		 waitForSpinnerToDisappear();
		 selectSupplier(supplierName);
		 waitForSpinnerToDisappear();
		 clickOn(actualReportType,"Recoverable Tax Report -> Actual Report Type Radio Button");
		 waitForSpinnerToDisappear();
		 selectBillingPeriod(billingPeriodDate);
		 waitForSpinnerToDisappear();
		 clickOn(generateReport,"Recoverable Tax Report -> Generate Report Button");
		 waitForSpinnerToDisappear();
		 clickOn(exportExportResults.get(0),"Recoverable Tax Report -> Export Results Excel-1 link");
		 waitTime(5000);
		// clickOn(exportExportResults.get(1),"Recoverable Tax Report -> Export Results Excel-2 link");
		/* String reportName="RecoverableTax_"+tenantName.replace(" ", "+")+"_"+supplierName.replace(" ", "+")+"_"+fromAndToDate;
		 waitForFileToDownloadAndMove_Chrome(reportName, "xlsx", "aces3Reports");
		*/ if(recoverableTaxTable.isDisplayed())
			 ExtentTestManager.getTest().log(LogStatus.PASS,
						"Recoverable Tax report is displayed");
		 else ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Recoverable Tax report is not displayed");
				 
		 /*String recoverable = findElementByXpath(recoverableXpath+recoverableValue+genericXpath).getText();
		 String taxType= findElementByXpath(recoverableXpath+taxTypeValue+genericXpath).getText();
		 
		 if(recoverable.equalsIgnoreCase(recoverableValue)&&taxType.equalsIgnoreCase(taxTypeValue)){
			 
			 ExtentTestManager.getTest().log(LogStatus.PASS,
						"Recoverable Value for" + taxType + "is" +  recoverableValue + "as expected");
			} else
				Assert.fail("Recoverable Value for" + taxType
						+ "is different than recoverableValue" + recoverableValue);
	*/} 
}


