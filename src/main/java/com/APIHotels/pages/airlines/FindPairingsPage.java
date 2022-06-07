package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class FindPairingsPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- FIND PAIRINGS

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'tripNumber')]")
	public WebElement pairing;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Operating Date']/parent::*/following-sibling::td//input[contains(@id,'InputDate')]")
	public WebElement operatingDate;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Operating Date']/parent::*/following-sibling::td//img")
	public WebElement operatingDateCalendar;

	@FindBy(how = How.ID, using = "findPairingsForm:findResLocCdArea:findResLocCd")
	public WebElement location;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'flightNbr')]")
	public WebElement flightNumber;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'crewId')]")
	public WebElement crewId;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Start Date']/parent::*/following-sibling::td[1]//input[@type='text']")
	public WebElement startDate;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Start Date']/parent::*/following-sibling::td[1]//img")
	public WebElement startDateCalendar;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='End Date']/parent::*/following-sibling::td//input[contains(@id,'InputDate')]")
	public WebElement endDate;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='End Date']/parent::*/following-sibling::td//img")
	public WebElement endDateCalendar;

	@FindBy(how = How.XPATH, using = "//select[contains(@name,'findPairingsForm:j_id')]")
	public WebElement timeFormat;

	@FindBy(how = How.ID, using = "findPairingsForm:retrieve")
	public WebElement retrieveButton;

	@FindBy(how = How.ID, using = "findPairingsForm:reset")
	public WebElement resetButton;

	// AIRCANADA
	@FindBy(how = How.ID, using = "findPairingsForm:deptArea:prgType")
	public WebElement dept;

	public FindPairingsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void findPairings(String pairingNumber, String locationValue, String flightNumberValue, String crewIdValue,
			String timeFormatValue) {

		typeInText(pairing, pairingNumber);
		waitForElementVisibility(operatingDateCalendar);
		typeInText(operatingDate, currentDate());
		typeInText(location, locationValue);
		typeInText(flightNumber, flightNumberValue);
		typeInText(crewId, crewIdValue);
		waitForElementVisibility(startDateCalendar);
		typeInText(startDate, currentDate());
		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, currentDate());
		selectByValue(timeFormat, timeFormatValue);
		waitForElementVisibility(retrieveButton);
		waitForElementVisibility(resetButton);

	}

	
	/**
	 * @param  Aircanada -->Department field
	 */
	public void department(String departmentValue) {
		// Aircanada -->dept field
		selectByVisibleText(dept, departmentValue);
	}

	public void findPairingsAirCanada(String departmentValue, String pairingNumber, String aircanadaCityYUL,
			String flightNumberValue, String crewIdValue, String timeFormatValue) {
		department(departmentValue);
		findPairings(pairingNumber, aircanadaCityYUL, flightNumberValue, crewIdValue, timeFormatValue);

	}

}
