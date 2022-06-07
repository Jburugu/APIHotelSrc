package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class HotelInfoPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- HOTEL INFO (AIRCANADA)

	@FindBy(how = How.ID, using = "crewLayoverForm:employeeNumberArea:employeeNumber")
	private WebElement employeeNumber;

	@FindBy(how = How.XPATH, using = ".//*[@value='Retrieve']")
	private WebElement retrievebutton;
	
	@FindBy(how = How.ID, using = "crewLayoverForm:selectedTimeFormat")
	private WebElement timeFormatdropdown;
	
	public HotelInfoPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void hotelInfo(String employeeValue) {
		typeInText(employeeNumber, employeeValue);
		Assert.assertEquals(employeeNumber.getAttribute("value"), employeeValue, "employee mismatch");
		waitForElementVisibility(retrievebutton);
	}
	
	public void hotelInfoUPS(String employeeValue, String timeFormat)
	{
		selectByValue(timeFormatdropdown, timeFormat);
	}

}
