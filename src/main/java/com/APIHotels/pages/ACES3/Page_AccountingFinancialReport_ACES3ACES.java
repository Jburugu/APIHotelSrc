package com.APIHotels.pages.ACES3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.Utilities.XlsWorkbook;
import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_AccountingFinancialReport_ACES3ACES extends BasePage{
	
	@FindBy(xpath = "//*[@id='reconcileDashboard:dashboardAccordian']/div")
	WebElement accountingDashboardDivCollapsable;

	@FindBy(xpath="//label[contains(@id,'tenant_label')]")
	WebElement selectAirline; 
	
	@FindBy(xpath="//ul[contains(@id,'tenant_items')]/li")
	WebElement airlinesList;
		
	String tenantXpath1 = "//*[contains(@id, 'tenant_items')]/li[contains(text(), '";
	String tenantXpath2 = "')]";
		
	@FindBy(xpath="//*[contains(@id,'selectSupplierPanel')]//tr//span/span/input")
	private WebElement city;
	
	String cityNameXpath ="//span[contains(text(),'";
	String listxpath = "')]";
	String supplierNameXpath="//li[contains(text(),'";
	
	@FindBy(xpath="//label[contains(@id,'supplierSelector_label')]")
	WebElement supplier; 
	
	@FindBy(xpath="//label[contains(text(),'Actual')]")
	WebElement actual; 
		
	@FindBy(xpath="//label[contains(text(),'Accrual')]")
	WebElement accrual;
		
	@FindBy(xpath="//label[contains(text(),'Un-billed')]")
	WebElement unbilled; 
	
	@FindBy(xpath="//label[contains(text(),'Variance')]")
	WebElement variance; 
	
	@FindBy(xpath="//input[contains(@id,'bpStart_input')]")
	WebElement fromBillingPeriodText; 
	
	@FindBy(xpath="//input[contains(@id,'bpEnd_input')]")
	WebElement toBillingPeriodText; 
	
	@FindBy(xpath="//span[contains(@id,'bpStart')]")
	WebElement fromBillingPeriod; 
	
	@FindBy(xpath="//span[contains(@id,'bpEnd')]")
	WebElement toBillingPeriod; 
	
	@FindBy(xpath="//input[contains(@id,'bpStart_input')]")
	WebElement fromApprovedPeriodText; 
	
	@FindBy(xpath="//input[contains(@id,'bpEnd_input')]")
	WebElement toApprovedPeriodText; 
	
	@FindBy(xpath="//label[contains(text(),'Approved Date :')]")
	WebElement approvedDate; 
	
	@FindBy(xpath="//span[contains(@id,'abpStart')]")
	WebElement fromApprovedDate; 
	
	@FindBy(xpath="//span[contains(@id,'abpEnd')]")
	WebElement toApprovedDate; 
		
	@FindBy(xpath="//label[contains(text(),'Account/GL Code')]")
	WebElement accountOrGLCode; 
	
	@FindBy(xpath="//label[contains(text(),'Cost Center')]")
	WebElement costCenter;
	
	@FindBy(xpath="//label[contains(text(),'Location and Supplier')]")
	WebElement locationAndSupplier;
	
	@FindBy(xpath="//span[contains(text(),'Generate Report')]")
	WebElement generateReport;
	
	@FindBy(xpath="//a[contains(text(),'Download Report')]")
	WebElement downloadReport;
	
	@FindBy(className="ui-datepicker-title")
	WebElement monthYearTitle;
	
	@FindBy(how=How.ID, using="ui-datepicker-div")
	WebElement calendar;
	
	@FindBy(xpath="//div[contains(@id,'start')]")
	WebElement spinner;
	
	String previousMonth="//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	

	

	public EventFiringWebDriver driver = null;
	
	public Page_AccountingFinancialReport_ACES3ACES(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	private void actualFinancialReports(String fromBillingPeriodDate, String toBillingPeriodDate, String period) throws Exception{	
		clickOn(actual, "Accounting Financial Reports Page -> Actual Radio Button");		
		waitForSpinnerToDisappear();
		
		if(period.equalsIgnoreCase("BillingPeriod")){
			clickOn(fromBillingPeriodText,"Accounting Financial Reports Page -> Actual -> Billing Period From Date ");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
			waitForElementToDisappear(spinner);
			clickOn(toBillingPeriodText,"Accounting Financial Reports Page -> Actual -> Billing Period To Date ");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
			waitForElementToDisappear(spinner);
		 }
		else if(period.equalsIgnoreCase("ApprovedDate")){
			clickOn(approvedDate, "Accounting Financial Reports Page -> Approved Date Radio Button");
			waitForSpinnerToDisappear();
			clickOn(fromApprovedDate, "Accounting Financial Reports Page -> Approved Date From DatePicker");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
			waitForElementToDisappear(spinner);
			clickOn(toApprovedDate, "Accounting Financial Reports Page -> Approved Date From DatePicker");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
			waitForElementToDisappear(spinner);			
		}
	}

	private void accrualFinancialReports(String fromBillingPeriodDate, String toBillingPeriodDate) throws Exception
	{	
		/*clickOn(accrual);
		waitForSpinnerToDisappear();*/
		clickOn(fromBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		waitForSpinnerToDisappear();
		}
	
	private void unbilledFinancialReports(String fromBillingPeriodDate, String toBillingPeriodDate) throws Exception
	{	
		clickOn(unbilled);
		waitForSpinnerToDisappear();
		clickOn(fromBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		waitForSpinnerToDisappear();		
	}
	
	private void varianceFinancialReport(String fromBillingPeriodDate, String toBillingPeriodDate) throws Exception
	{
		clickOn(variance);
		waitForSpinnerToDisappear();
		clickOn(fromBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod);
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		waitForSpinnerToDisappear();		
	}
	
	private String MoveFileToDesiredFolder(String airlinesName, String fromDate, String toDate, String reporttype) throws Exception{
		
		String filename=airlinesName+ " "+fromDate+ "-" +toDate+" "+ reporttype;
		String folderPath = "AccountingFinancialReport";
		System.out.println(folderPath);
		String fileName= fileMoveToDesiredFolder_Updated(downloadFilepath, downloadFilepath+File.separator+folderPath, filename+".xlsx", "xlsx");
		return fileName; 
	}		
	
	public void waitForSpinnerToDisappear()
	{
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
        wait1.withTimeout(Duration.ofMinutes(30));
        wait1.pollingEvery(Duration.ofSeconds(2));
        wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}
	
	private void selectAirlines(String airlinesName){
		if(accountingDashboardDivCollapsable.getAttribute("aria-expanded").equalsIgnoreCase("true")){
			clickOn(accountingDashboardDivCollapsable, "ACES 3 ACES Header -> Accounting Dashboard Collapsable Button");
			waitForSpinnerToDisappear();
		}
		clickOn(selectAirline, "Accounting Financial Reports Page -> Airline Select");
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(tenantXpath1+airlinesName+tenantXpath2), "Accounting Financial Reports Page -> Airline Selected From Suggestion List");
		waitForSpinnerToDisappear();
		
	}
	
	private void selectReportType(String reportType, String fromBillingPeriodDate, String toBillingPeriodDate, 
			String period) throws Exception{
		switch (reportType){
		case "ACTUAL" : 
			actualFinancialReports(fromBillingPeriodDate, toBillingPeriodDate, period);
			break;
		case "ACCRUAL" :
			accrualFinancialReports(fromBillingPeriodDate, toBillingPeriodDate);
			break;
		case "UNBILLED":
			unbilledFinancialReports(fromBillingPeriodDate, toBillingPeriodDate);
			break;
		case "VARIANCE": 
			 varianceFinancialReport(fromBillingPeriodDate, toBillingPeriodDate);
			break;
		}	
	}

	
	private void selectShowByOptions(){
		clickOn(accountOrGLCode,"Accounting Financial Reports Page -> Account/GL Code Checkbox");
		if(costCenter.isEnabled())
		clickOn(costCenter,"Accounting Financial Reports Page -> Cost Center Checkbox");
		clickOn(locationAndSupplier,"Accounting Financial Reports Page -> Location And Supplier Checkbox");
		clickOn(generateReport,"Accounting Financial Reports Page -> Generate Report Button");
		waitForSpinnerToDisappear();
		waitForElementVisibility(downloadReport);
		clickOn(downloadReport,"Accounting Financial Reports Page -> Download Report Link");
	}
	
	private void selectCityAndSupplier(String cityCode, String supplierName){
		waitForElementVisibility(city);
		typeInText(city, cityCode, "Accounting Financial Reports Page -> City Textbox");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(cityNameXpath+cityCode+listxpath), "Accounting Financial Reports Page -> City Selected From Suggestion List");
		waitForElementToDisappear(spinner);
		clickOn(supplier, "Accounting Financial Reports Page -> Supplier Select");
		clickOn(findElementByXpath(supplierNameXpath + supplierName + listxpath), "Accounting Financial Reports Page -> Supplier Selected From Suggestion List");
		waitForElementToDisappear(spinner);
		
	}
	
	
	private String waitForFileToDownload(String airlinesName, String fromDate, String toDate, String reporttype) throws Exception {		
		String expectedFileName=airlinesName+ " "+ fromDate+ "-" + toDate+ " " + reporttype;
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.withTimeout(Duration.ofMinutes(3));
		wait.pollingEvery(Duration.ofSeconds(15));		
		Path path= Paths.get(downloadFilepath);
		File fileToCheck =path.resolve(expectedFileName).toFile();
		String FileDownloaded= fileToCheck.getName();
		System.out.println(FileDownloaded);
		 /*Checks for required file in downloaded_files.
		 * If not exists, wait till names of expected filename and file to be download are equal.
		*/
		if (!fileToCheck.exists()) {						
			ExpectedCondition<Boolean> elementTextEqualsStringIgnoreCase = arg0 -> expectedFileName.equalsIgnoreCase
					(FileDownloaded);
			wait.until(elementTextEqualsStringIgnoreCase);
			System.out.println("File Downloaded Successfully");
		}
		
		String fileName=MoveFileToDesiredFolder(airlinesName, fromDate, toDate, reporttype);
		return fileName;
		}
	
	private void verifyTotalAmountInExcel(String xlFileName, String xlSheetName, String columnName, String expectedValue) throws IOException
	{
		XlsWorkbook workbook = new XlsWorkbook(downloadFilepath+File.separator+"AccountingFinancialReport"+File.separator+xlFileName);
		DataTable table = workbook.getTestDataTable(xlSheetName);
		for(int rowCount=1; rowCount<=5; rowCount++){
		String fieldValueInExcel= table.getFieldValue(rowCount, columnName);
		if((expectedValue).equalsIgnoreCase(fieldValueInExcel))
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Total Amount in excel "+fieldValueInExcel + " is equal to expected Amount" );
		else ExtentTestManager.getTest().log(LogStatus.FAIL,
				"Total Amount in excel "+fieldValueInExcel + " is not equal to expected Amount");
		
		}
	}
	
	private void verifyHeaderNamesInExcel(String xlFileName, String xlSheetName, String columnName) throws IOException{
	XlsWorkbook workbook = new XlsWorkbook(downloadFilepath+File.separator+"AccountingFinancialReport"+File.separator+xlFileName);
	DataTable table = workbook.getTestDataTable(xlSheetName);
	List<String> columnNames= Arrays.asList(columnName.split(","));
	for(int i=0; i<columnNames.size(); i++){
	String columnNameInExcel= table.getFieldValue(0, columnNames.get(i));
	System.out.println(columnNameInExcel);
	if(columnNames.get(i).equalsIgnoreCase(columnNameInExcel))
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Expected column " +columnNames.get(i)+ " is available in downloaded file" );
	else ExtentTestManager.getTest().log(LogStatus.FAIL,
			"Expected column " +columnNames.get(i)+ " is not available in downloaded file");
	}
	}
	
	
	
	public void accountingFinancialReportForBatch(String airlinesName, String fromBillingPeriodDate, 
			String toBillingPeriodDate, String period, String reportType, String amount) throws Exception{
		selectAirlines(airlinesName);
		selectReportType(reportType, fromBillingPeriodDate, toBillingPeriodDate, period);
		selectShowByOptions();
		try{Thread.sleep(20000);}catch(Exception e){}
		String downloadedFileName= waitForFileToDownload(airlinesName, fromBillingPeriodDate, toBillingPeriodDate, reportType);
		verifyTotalAmountInExcel(downloadedFileName, "All Suppliers", "Total", amount);
		verifyHeaderNamesInExcel(downloadedFileName, "All Suppliers", "Fleet,Arrival Flight Code,Arrival Flight,Flight Arrival Time,Departure Flight Code,Departure Flight,Flight Departure Time");
	}
	
	public void accoutingFinancialReportForSupplier(String airlinesName, String cityCode, String supplierName,
			String reportType, String fromBillingPeriodDate, String toBillingPeriodDate, String period, String amount) throws Exception{
		selectAirlines(airlinesName);
		selectCityAndSupplier(cityCode, supplierName);
		selectReportType(reportType, fromBillingPeriodDate, toBillingPeriodDate, period);
		selectShowByOptions();
		try{Thread.sleep(20000);}catch(Exception e){}
		String downloadedFileName= waitForFileToDownload(airlinesName, fromBillingPeriodDate, toBillingPeriodDate, reportType);
		verifyTotalAmountInExcel(downloadedFileName, "All Suppliers", "Total", amount);
		verifyHeaderNamesInExcel(downloadedFileName, "All Suppliers", "Fleet,Arrival Flight Code,Arrival Flight,Flight Arrival Time,Departure Flight Code,Departure Flight,Flight Departure Time");
	}
}


	



