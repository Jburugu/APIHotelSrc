package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class FindSheetPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- CREW SIGNIN SHEET -- FIND SHEET

	@FindBy(how = How.ID, using = "crewSignInForm:locationCodeEntry:locationCode")
	public WebElement location;

	@FindBy(how = How.ID, using = "crewSignInForm:supplierEntry:supplierDD")
	public WebElement hotel;

	@FindBy(how = How.ID, using = "crewSignInForm:startDateEntry:resvDtInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "crewSignInForm:startDateEntry:resvDtPopupButton")
	public WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "crewSignInForm:endDateEntry:resvDtInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "crewSignInForm:endDateEntry:resvDtPopupButton")
	public WebElement endDateCalendar;

	@FindBy(how = How.ID, using = "crewSignInForm:crewSignInIdEntry:crewSignInId")
	public WebElement signInSheetId;

	@FindBy(how = How.CSS, using = "input[value='Retrieve']")
	public WebElement retrieveButton;

	public FindSheetPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void crewSignInFindSheet(String locationValue, String hotelValue, String signInSheetIdValue) {
		typeInText(location, locationValue);
		int hotelVal = Integer.parseInt(hotelValue);
		selectByIndex(hotel, hotelVal);
		waitForElementVisibility(startDateCalendar);
		typeInText(startDate, currentDate());
		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, currentDate());
		typeInText(signInSheetId, signInSheetIdValue);
		Assert.assertEquals(signInSheetId.getAttribute("value"), signInSheetIdValue, "signInSheetId mismatch");
		waitForElementVisibility(retrieveButton);

	}

}
