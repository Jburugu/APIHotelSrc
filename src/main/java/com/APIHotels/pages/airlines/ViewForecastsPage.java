package com.APIHotels.pages.airlines;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ViewForecastsPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- ALLOWANCE -- VIEW FORECAST(NEWZEALAND/AirNelson)

	@FindBy(how = How.ID, using = "allowanceForecastsForm:seriesNumDeco:seriesNumber")
	public WebElement seriesNumber;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:flightNumDeco:flightNumber")
	public WebElement flightNumber;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:crewIdDeco:crewId")
	public WebElement crewId;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:locCdDeco:locCd")
	public WebElement location;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:startDateDeco:startDateInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:endDateDeco:endDateInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:hotelAreaDeco:hotels")
	public WebElement hotels;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:startDateDeco:startDatePopupButton")
	public WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:endDateDeco:endDatePopupButton")
	public WebElement endDateCalendar;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:retrieve")
	public WebElement searchBtn;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:reset")
	public WebElement resetBtn;
	
	@FindBy(id = "allowanceForecastsForm:forecastTable")
	private WebElement forecastsResultsTable;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:forecastTable']//tfoot//td[2]")
	private WebElement totalCashForecastValue;
	
	@FindBy(xpath = "//*[@id = 'allowanceForecastsForm:forecastTable:tb']//td[2]/a")
	private WebElement cashForecastAmountLink;
	
	@FindBy(id = "allowanceForecastsForm:allowanceDetailsTable")
	private WebElement allowanceForecastsDetailsTable;
	
	@FindBy(xpath = "//*[@id = 'allowanceForecastsForm:allowanceDetailsTable:f']//td[2]/div")
	private List<WebElement> crewNames;
	
	@FindBy(xpath = "//*[@id='ClientUI_Box23']//th[31]//span")
	private WebElement tableFooterTotalAmount;

	public ViewForecastsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void viewForecast(String flightno, String crewidValue, String locationValue,
			String hotelValue,String seriesValue) {
		typeInText(flightNumber, flightno);
		typeInText(crewId, crewidValue);
		typeInText(location, locationValue);
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotels, Integer.parseInt(hotelValue));
		typeInText(startDate, currentDate());
		typeInText(endDate, currentDatePlus(1));
		waitForElementVisibility(startDateCalendar);
		waitForElementVisibility(endDateCalendar);
		typeInText(seriesNumber, seriesValue);
		waitForElementVisibility(searchBtn);
		waitForElementVisibility(resetBtn);

	}
	
	public void searchForAllowanceForecasts(String flightno, String crewidValue, String locationValue,
			String hotelValue,String startDateValue, String seriesValue){
		typeInTextIfInputNotEmpty(flightNumber, flightno);
		flightNumber.sendKeys(Keys.TAB);
		waitTime(3000);
		typeInTextIfInputNotEmpty(crewId, crewidValue);
		crewId.sendKeys(Keys.TAB);
		waitTime(3000);
		typeInTextIfInputNotEmpty(location, locationValue);
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByVisibleText(hotels, hotelValue);
		hotels.sendKeys(Keys.TAB);
		waitTime(5000);
		typeInText(startDate, startDateValue+"-2020");
		startDate.sendKeys(Keys.TAB);
		waitTime(5000);
		typeInTextIfInputNotEmpty(seriesNumber, seriesValue);
		clickOn(searchBtn);
		waitForElementVisibility(forecastsResultsTable);
	}
	
	public void viewAllowanceForecasts(String totalCashForecastAmt, String crewName){
		assertTrue(totalCashForecastValue.getText().equals(totalCashForecastAmt), "Total Cash Forecast Amount Mismatch!");
		clickOn(cashForecastAmountLink);
		waitForElementVisibility(allowanceForecastsDetailsTable);
		int i = 0;
		for(String name : crewName.split(",")){
			assertTrue(crewNames.get(i).getText().equals(name), "Crew Name Mismatch!");
			i++;
		}
		
		assertTrue(tableFooterTotalAmount.getText().equals(totalCashForecastAmt), "Total Amount Mismatch!");
	}
	
	
	
}
