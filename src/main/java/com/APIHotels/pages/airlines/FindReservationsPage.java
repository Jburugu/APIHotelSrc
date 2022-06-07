package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class FindReservationsPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- FIND RESERVATIONS (AIRTRANSAT/CATHAYPACIFIC)

	@FindBy(how = How.XPATH, using = "//label[text()=' Hotel']/preceding-sibling::input[@type='radio']")
	private WebElement hotelServiceTypeRadioButton;

	@FindBy(how = How.XPATH, using = "//label[text()=' GT']/preceding-sibling::input[@type='radio']")
	private WebElement gtServiceTypeRadioButton;

	@FindBy(how = How.CSS, using = "input[id$='tripNumber']")
	private WebElement tripNumber;

	@FindBy(how = How.CSS, using = "input[id$='locCd']")
	private WebElement locCd;

	@FindBy(how = How.XPATH, using = "//img[@src='img/lookup_arrow.gif']")
	private WebElement locationImg;

	@FindBy(how = How.CSS, using = "input[id$='crewId']")
	private WebElement crewId;

	@FindBy(how = How.CSS, using = "input[id$='arrFlightNbr']")
	private WebElement arrivalFlight;

	@FindBy(how = How.CSS, using = "input[id$='depFlightNbr']")
	private WebElement departureFlight;

	@FindBy(how = How.CSS, using = "input[id$='crewName']")
	private WebElement crewName;

	@FindBy(how = How.ID, using = "findReservationsForm:startDateDeco:startDateInputDate")
	private WebElement startDate;

	@FindBy(how = How.ID, using = "findReservationsForm:startDateDeco:startDatePopupButton")
	private WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "findReservationsForm:endDateDeco:endDateInputDate")
	private WebElement endDate;

	@FindBy(how = How.ID, using = "findReservationsForm:endDateDeco:endDatePopupButton")
	private WebElement endDateCalendar;

	@FindBy(how = How.ID, using = "findReservationsForm:includeHotelGTArea:includeHotelGT")
	private WebElement includeHotelGTCheckBox;

	@FindBy(how = How.ID, using = "findReservationsForm:retrieve1")
	private WebElement retrieveButton;
	
	

	// OPERATIONS -- FIND RESERVATIONS (AIRTRANSAT/CATHAY PACIFIC) -->> End

	public FindReservationsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void findReservations(String tripNumberValue, String locationValue, String crewIdValue,
			String arrivalFltValue, String departureFltValue, String crewNameValue) {
		clickOn(hotelServiceTypeRadioButton);
		findReservationWithOutHotelAndGtRadioBtns(tripNumberValue, locationValue, crewIdValue, arrivalFltValue,
				departureFltValue, crewNameValue);
		clickOn(gtServiceTypeRadioButton);
		Assert.assertTrue(includeHotelGTCheckBox.isSelected(), "Include Hotel GT checkbox is not selected under GT");

	}

	public void findReservationWithOutHotelAndGtRadioBtns(String tripNumberValue, String locationValue,
			String crewIdValue, String arrivalFltValue, String departureFltValue, String crewNameValue) {
		typeInText(tripNumber, tripNumberValue);
		typeInText(locCd, locationValue);
		typeInText(crewId, crewIdValue);
		typeInText(arrivalFlight, arrivalFltValue);
		typeInText(departureFlight, departureFltValue);
		typeInText(crewName, crewNameValue);
		typeInText(startDate, currentDate());
		typeInText(endDate, currentDate());
		clickOn(includeHotelGTCheckBox);
		waitForElementVisibility(retrieveButton);
	}

}
