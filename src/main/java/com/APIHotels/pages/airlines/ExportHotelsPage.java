package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ExportHotelsPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// PLANNING -- EXPORT HOTELS
	@FindBy(how = How.ID, using = "exportHotelsForm:startDateArea:startDateInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "exportHotelsForm:startDateArea:startDatePopupButton")
	public WebElement startDateCalender;

	@FindBy(how = How.ID, using = "exportHotelsForm:endDateArea:endDateInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "exportHotelsForm:formatDateArea:format")
	public WebElement format;

	@FindBy(how = How.ID, using = "exportHotelsForm:endDateArea:endDatePopupButton")
	public WebElement endDateCalender;

	// @FindBy(how = How.XPATH, using = ".//*[@value='Export']")
	@FindBy(how = How.CSS, using = "input[value='Export']")
	public WebElement exportButton;

	/**
	 * AIRCANADA -- currency mapping file and target currency
	 * 
	 */

	@FindBy(how = How.XPATH, using = ".//*[@id='exportHotelsForm:uploadField']/table/tbody/tr/td/div[1]")
	public WebElement currencyMappingFile;

	@FindBy(how = How.ID, using = "exportHotelsForm:inputText")
	public WebElement targetCurrency;

	public ExportHotelsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void startDate() {
		waitForElementVisibility(startDateCalender);
		typeInText(startDate, currentDate());
	}

	public void currencyMappingFileAndTargetCurrencyForAC(String targetCurrencyValue) {
		// AirCanada --> currency mapping file , Add button and target currency
		waitForElementVisibility(currencyMappingFile);
		waitForElementVisibility(targetCurrency);
		typeInText(targetCurrency, targetCurrencyValue);
	}

	public void endDate() {
		waitForElementVisibility(endDateCalender);
		typeInText(endDate, currentDate());
	}

	public void verifyFormatAndExportButton() {
		waitForElementVisibility(format);
		waitForElementVisibility(exportButton);
	}
	
	public void exportHotels(String currencyCAN){
		startDate();
		endDate();
		currencyMappingFileAndTargetCurrencyForAC(currencyCAN);
		verifyFormatAndExportButton();
		endDate();
	}
	
	public void exportHotels(){
		startDate();
		endDate();
		verifyFormatAndExportButton();
		endDate();
	}
	
}
