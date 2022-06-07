package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class UploadRoamingListPage extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.ID, using = "uploadPairingForm:portCodeAread:locCd")
	public WebElement portCode;

	@FindBy(how = How.ID, using = "uploadPairingForm:shipDateArea:shipDateInputDate")
	public WebElement shipDate;

	@FindBy(how = How.ID, using = "uploadPairingForm:shipDateArea:shipDatePopupButton")
	public WebElement shipDateCalendar;

	@FindBy(how = How.ID, using = "uploadPairingForm:hotels:hotels")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = ".//*[@id='uploadPairingForm:uploadField']/table/tbody/tr/td/div[1]")
	public WebElement addFile;

	@FindBy(how = How.CSS, using = "input[value='Reset']")
	public WebElement resetButton;

	public UploadRoamingListPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void uploadRoamingList(String portCodeValue, String hotelValue) {
		typeInText(portCode, portCodeValue);
		typeInText(shipDate, currentDate());
		waitForElementVisibility(shipDateCalendar);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		waitForElementVisibility(addFile);
		waitForElementVisibility(resetButton);

	}
}
