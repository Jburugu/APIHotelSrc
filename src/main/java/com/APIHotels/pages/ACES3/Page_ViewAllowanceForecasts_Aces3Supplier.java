package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ViewAllowanceForecasts_Aces3Supplier extends BasePage{
	
	@FindBy(id = "allowanceForecastsForm:tenantMenu_label")
	private WebElement selectAirlineDropDown;
	
	@FindBy(id = "allowanceForecastsForm:flightNumber")
	private WebElement flightNumber;
	
	@FindBy(id = "allowanceForecastsForm:arrivalDatebutton_input")
	private WebElement arrivalDate;
	
	@FindBy(id = "allowanceForecastsForm:startDateButton_input")
	private WebElement startDate;
	
	@FindBy(id = "allowanceForecastsForm:endDateButton_input")
	private WebElement endDate;
	
	@FindBy(xpath = "//*[contains(text(), 'Search')]")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Reset')]")
	private WebElement resetBtn;
	
	private String tenantNameXpath1 = "//ul[@id = 'allowanceForecastsForm:tenantMenu_items']/li[text()='";
	private String tenantNameXpath2 = "']";
	
	@FindBy(xpath="//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//span[contains(@id, 'allowanceForecastsForm:arrivalDatebutton')]")
	private WebElement arrivalDateCalendarBtn;
	
	@FindBy(xpath = "//span[contains(@id, 'allowanceForecastsForm:startDateButton')]")
	private WebElement startDateCalendarBtn;
	
	@FindBy(xpath = "//span[contains(@id, 'allowanceForecastsForm:endDateButton')]")
	private WebElement endDateCalendarBtn;
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/select/option[@selected = 'selected']")
	private WebElement monthYearTitle;
	
	String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:searchResultsPanel']//table")
	private WebElement resultAllowanceTable;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:searchResultsPanel']//table//tfoot//td[2]")
	private WebElement totalCashForecastValue;
	
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:searchResultsPanel']//table//tbody//td[2]/a")
	private List<WebElement> cashForecastAmountLink;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:searchResultsPanel']//table//tbody//td[3]/a")
	private WebElement totalAdjustmentsLink;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:allowanceDetailsTable_frozenTbody']//td[1]")
	private List<WebElement> crewNames;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:allowanceDetailsTable_scrollableTfoot']//td[27]")
	private WebElement tableFooterTotalAmount;
	
	@FindBy(id = "allowanceForecastsForm:allowanceGenerateButton")
	private WebElement generateAllowanceSheetBtn;
	
	@FindBy(id = "allowanceForecastsForm:allowanceGenerateButton")
	private List<WebElement> generateAllowanceSheetBtnList;
	
	@FindBy(id = "allowanceForecastsForm:allowanceViewButton")
	private WebElement viewAllowanceSheetBtn;
	
	public EventFiringWebDriver driver=null;
	
	public Page_ViewAllowanceForecasts_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void viewAllowanceForcasts(String flightNumber){
		waitForElementVisibility(selectAirlineDropDown);
		typeInText(this.flightNumber, flightNumber, "ViewAllowanceForecastsPage -> Flight Number");
		waitForElementVisibility(selectAirlineDropDown);
		waitForElementVisibility(arrivalDate);
		waitForElementVisibility(startDate);
		waitForElementVisibility(endDate);
		waitForElementVisibility(searchBtn);
		waitForElementVisibility(resetBtn);
	}
	
	public void searchForAllowanceForecasts(String tenantName, String flightNum, String arrivalDate, String startDate) {
		
		clickOn(selectAirlineDropDown, "ViewAllowanceForecastsPage -> Select Airline drop down");
		clickOn(findElementByXpath(tenantNameXpath1+tenantName+tenantNameXpath2), "ViewAllowanceForecastsPage -> Select Airline drop down "+tenantName+" option");
		waitForElementToDisappear(spinner);
		typeInTextIfInputNotEmpty(flightNumber , flightNum, "ViewAllowanceForecastsPage -> Flight Num");
		if(!arrivalDate.isEmpty()){
			clickOn(arrivalDateCalendarBtn, "ViewAllowanceForecastsPage -> Arrival Date calendar btn");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", arrivalDate);
		}
		if(!startDate.isEmpty()){
			clickOn(startDateCalendarBtn, "ViewAllowanceForecastsPage -> Start Date calendar btn");
			datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", startDate);
			waitForElementToDisappear(spinner);
		}
		
		clickOn(searchBtn, "ViewAllowanceForecastsPage -> Search btn");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(resultAllowanceTable);
	}
	
	public void viewAllowanceForecasts(String totalCashForecastAmt, String crewName, String cashForecastAmount){
		assertTrue(totalCashForecastValue.getText().equals(totalCashForecastAmt), "Total Cash Forecast Amount Mismatch!");
		clickOn(cashForecastAmountLink.get(0), "ViewAllowanceForecastsPage -> Cash forecast amount: "+cashForecastAmountLink.get(0).getText()+" link");
		waitForElementToDisappear(spinner);
		int i = 0;
		for(String name : crewName.split(",")){
			assertTrue(crewNames.get(i).getText().equals(name), "Crew Name Mismatch!");
			i++;
		}
		
		assertTrue(tableFooterTotalAmount.getText().equals(cashForecastAmount), "Total Amount Mismatch!");
	}
	
	public int getAllowanceForecastsCount(){
		return cashForecastAmountLink.size();
	}
	
	public void generateAllowanceSheetForEachRecord(int index){
		clickOn(cashForecastAmountLink.get(0), "ViewAllowanceForecastsPage -> Cash forecast amount: "+cashForecastAmountLink.get(0).getText()+" link");
		waitForElementToDisappear(spinner);
	}
	
	public void clickOnGenerateAllowanceSheetBtnIfPresent(){
		
		if(viewAllowanceSheetBtn.getAttribute("style").equals("display: none;")){
			generateAllowanceSheet();
		}
			
	}
	
	public void generateAllowanceSheet(){
		clickOn(generateAllowanceSheetBtn, "ViewAllowanceForecastsPage -> Generate Allowance Sheet btn");
		waitForElementToDisappear(spinner);
		assertTrue(generateAllowanceSheetBtn.getAttribute("style").equals("display: none;"), "Generate Allowance Sheet button is still being displayed!!");
		waitForElementVisibility(viewAllowanceSheetBtn);
	}


}
