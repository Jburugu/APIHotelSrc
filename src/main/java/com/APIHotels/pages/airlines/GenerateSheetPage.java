package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class GenerateSheetPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- CREW SIGNIN SHEET -- GENERATE SHEET

	@FindBy(how = How.ID, using = "crewSignInForm:locationCodeEntry:locationCode")
	public WebElement location;

	@FindBy(how = How.ID, using = "crewSignInForm:supplierEntry:supplierDD")
	public WebElement hotel;

	@FindBy(how = How.ID, using = "crewSignInForm:resvDtEntry:resvDtInputDate")
	public WebElement reservationDate;

	@FindBy(how = How.ID, using = "crewSignInForm:resvDtEntry:resvDtPopupButton")
	public WebElement reservationDateCalendar;

	@FindBy(how = How.ID, using = "crewSignInForm:generate")
	public WebElement generateButton;

	public GenerateSheetPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void crewSignInGenerateSheet(String locationValue, String hotelValue) {
		typeInText(location, locationValue);
		waitForElementVisibility(hotel);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		waitForElementVisibility(reservationDateCalendar);
		typeInText(reservationDate, currentDate());
		// Assert.assertEquals(reservationDate.getAttribute("value"),
		// currentDate(), "reservation date mismatch");
		waitForElementVisibility(generateButton);
	}

}
