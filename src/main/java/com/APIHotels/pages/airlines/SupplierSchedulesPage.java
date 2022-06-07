package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class SupplierSchedulesPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// PLANNING -- SUPPLIER SCHEDULES

	//// @FindBy(how = How.ID, using = "schedulesForm:j_id152:cityText")
	// @FindBy(how = How.ID, using = "schedulesForm:j_id137:cityText")
	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'cityText')]")
	public WebElement cityField;

	@FindBy(how = How.ID, using = "schedulesForm:tripNumberField:bidPeriods")
	public WebElement bidPeriod;

	@FindBy(how = How.XPATH, using = "//input[@type='radio']/following-sibling::label[text()=' Hotel']")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = "//input[@type='radio']/following-sibling::label[text()=' Transportation']")
	public WebElement transportation;

	@FindBy(how = How.XPATH, using = "//input[@type='radio']/following-sibling::label[text()=' Both']")
	public WebElement both;

	@FindBy(how = How.XPATH, using = ".//*[@value='Retrieve']")
	public WebElement retrievebutton;

	// princess cruises
	@FindBy(how = How.ID, using = "schedulesForm:startRetrieveArea:startDateInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "schedulesForm:startRetrieveArea:startDatePopupButton")
	public WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "schedulesForm:startRetrieveArea:startDateInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "schedulesForm:startRetrieveArea:startDatePopupButton")
	public WebElement endDateCalendar;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'cityText')]")
	public WebElement portCode;
	
	
	public SupplierSchedulesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void viewSupplierSchedules(String cityValues, String bidPeriodValue) {
		typeInText(cityField, cityValues);
		int bidperiodVal = Integer.parseInt(bidPeriodValue);
		selectByIndex(bidPeriod, bidperiodVal);
		clickOn(transportation);
		clickOn(hotel);
		clickOn(both);
		waitForElementVisibility(retrievebutton);

	}
	
	public void supplierSchedulesPrincessCruises(String portCodeValue) {
		typeInText(startDate, currentDate());
		waitForElementVisibility(startDateCalendar);
		typeInText(endDate, currentDatePlus(1));
		waitForElementVisibility(endDateCalendar);
		typeInText(portCode, portCodeValue);
		waitForElementVisibility(retrievebutton);
	}

}
