package com.APIHotels.pages.ACES3;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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

public class Page_MasterOpsReport_ACES3ACES extends BasePage{

	@FindBy(xpath = "//div[contains(@id,'start')]")
	WebElement spinner;
	
	String tenantNameXpath="//*[@id='viewReservationsForm:tenantSel:morTenants']//li[contains(text(),'";
	String genericXpath="')]";
	
	String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/span[1]")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	@FindBy(xpath="//span[@id='viewReservationsForm:startDate']/button/span[1]")
	private WebElement startDateCalendarBtn;
	
	@FindBy(xpath="//span[@id='viewReservationsForm:endDate']/button/span[1]")
	private WebElement endDateCalendarBtn;
	
	@FindBy(id="viewReservationsForm:city_input")
	private WebElement city;
	
	@FindBy(id="viewReservationsForm:supplier")
	private WebElement supplierSelection;
	
	String supplierListXpath="//ul[@id='viewReservationsForm:supplier_items']/li[contains(text(),'";
	
	@FindBy(id="viewReservationsForm:employee")
	private WebElement employeeID;
	
	@FindBy(xpath="//span[contains(text(),'Search')]/..")
	private WebElement searchButton;
	
	@FindBy(xpath="//*[contains(@src,'excel.jpg')]/..")
	private WebElement excelExport;
	
	/*String employeeIdXpath="//td[contains(text(),'";
	String employeeName="')]/following-sibling::td[1]";
	String reservationStatusXpath="')]/following-sibling::td[7]";
	String tripIdXpath="')]/following-sibling::td[16]";*/
	
	String tripIdXpath="//td[contains(text(),'";
	String employeeXpath="')]/preceding-sibling::td[16]";
	String resvstatusXpath="')]/preceding-sibling::td[9]";
	

	
	public EventFiringWebDriver driver= null;
	public Page_MasterOpsReport_ACES3ACES(EventFiringWebDriver driver){
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
	
	private void selectAirline(String tenantName){
		clickOn(findElementByXpath(tenantNameXpath+tenantName+genericXpath),"Reservations -> Master Ops Report Page -> Airline Select");
		waitForSpinnerToDisappear();
	}
	
	private void selectDates(String startDate, String endDate){
		clickOn(startDateCalendarBtn,"Reservations -> Master Ops Report Page -> Stat Date Picker Button");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", startDate);
		waitForSpinnerToDisappear();
		clickOn(endDateCalendarBtn,"Reservations -> Master Ops Report Page -> End Date Picker Button");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", endDate);
		waitForSpinnerToDisappear();
	}
	
	private void enterCity(String cityCode) throws InterruptedException{
		typeInText(city, cityCode,"Reservations -> Master Ops Report Page -> City Textbox");
		Thread.sleep(2000);
		waitForSpinnerToDisappear();
	}
	
	private void selectSupplier(String supplierName){
		clickOn(supplierSelection,"Reservations -> Master Ops Report Page -> Supplier Select");
		clickOn(findElementByXpath(supplierListXpath+supplierName+genericXpath),"Reservations -> Master Ops Report Page -> Supplier Selected From Suggestions List");
		waitForSpinnerToDisappear();
	}
	
	private void enterEmployeeId(String empID){
		typeInText(employeeID, empID,"Reservations -> Master Ops Report Page -> Employee ID Textbox" );
		waitForSpinnerToDisappear();
	}
	
	private void clickOnSearch(){
		
		clickOn(searchButton,"Reservations -> Master Ops Report Page -> Search Button");
		waitForSpinnerToDisappear();
	}
	
	public void verifyMasterOpsReport(String tenantName, String startDate, String endDate, String cityCode,		
		String supplierName, Map<String, List<String>> crewInTripMap, String status) throws Exception {
		selectAirline(tenantName);
		selectDates(startDate, endDate);
		enterCity(cityCode);
		selectSupplier(supplierName);
		
		crewInTripMap.forEach((tripId, employeeIDs) -> {
			for(int i=0; i<employeeIDs.size(); i++){
			System.out.println("tripId: " + tripId + ", employeeId: " + employeeIDs.get(i));
			String employeeId= employeeIDs.get(i);
			enterEmployeeId(employeeId);
			clickOnSearch();
			String empId= findElementByXpath(tripIdXpath+tripId+employeeXpath).getText();
			String reservationStatus=findElementByXpath(tripIdXpath+tripId+resvstatusXpath).getText();
			if(empId.equalsIgnoreCase(employeeId)&& reservationStatus.equalsIgnoreCase(status)){
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Crew Id from ACES2" + employeeId + "displaying in ACES3 for trip id" + tripId);
			}
			else{
				Assert.fail("Crew Details from ACES2 are not in ACES3");
			}			
			}	
		});
	
}
}
