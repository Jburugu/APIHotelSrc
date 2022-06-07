package com.APIHotels.pages.airlines;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class AirNelsonReportPage extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = "//div[@id='searchAllowanceAirNelsonReportForm:startDateArea']//input[contains(@id,'InputDate')]")
	public WebElement dateFrom;

	@FindBy(how = How.XPATH, using = "//div[@id='searchAllowanceAirNelsonReportForm:startDateArea']//img[contains(@id,'PopupButton')]")
	public WebElement dateFromCalendar;

	@FindBy(how = How.XPATH, using = "//div[@id='searchAllowanceAirNelsonReportForm:endDateArea']//input[contains(@id,'InputDate')]")
	public WebElement dateTo;

	@FindBy(how = How.XPATH, using = "//div[@id='searchAllowanceAirNelsonReportForm:endDateArea']//img[contains(@id,'PopupButton')]")
	public WebElement dateToCalendar;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceAirNelsonReportForm:locationArea']/div/input")
	public WebElement location;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceAirNelsonReportForm:hotelIdArea']/div/select")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceAirNelsonReportForm:mealOptArea']/div/select")
	public WebElement allowanceOption;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'submitReq')]")
	public WebElement searchBtn;

	public AirNelsonReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void airnelsonReport(String locationValue, String hotelValue, String allowanceValue) {
		typeInText(dateFrom, currentDate());
		waitForElementVisibility(dateFromCalendar);
		typeInText(dateTo, currentDatePlus(1));
		waitForElementVisibility(dateToCalendar);
		typeInText(location, locationValue);
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		selectByValue(allowanceOption, allowanceValue);
		waitForElementVisibility(searchBtn);
	}

}
