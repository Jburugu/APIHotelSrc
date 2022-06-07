package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ManageDateConfigurationPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// ADMIN -- MANAGE DATE CONFIGURATION (AIRTRANSAT)

	@FindBy(how = How.CSS, using = "select[id$='dateFormatValue']")
	public WebElement selectDateFormat;

	@FindBy(how = How.XPATH, using = "//input[@value='Save']")
	public WebElement saveButton;

	public ManageDateConfigurationPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void manageDateConfiguration(String selectDateFormatValue) {
		int selectDateFormatVal = Integer.parseInt(selectDateFormatValue);
		selectByIndex(selectDateFormat, selectDateFormatVal);
		clickOn(saveButton);
	}
}
