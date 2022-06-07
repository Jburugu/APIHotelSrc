package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class UploadPairingsPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// PLANNING -- UPLOAD PAIRINGS

	@FindBy(how = How.ID, using = "uploadPairingForm:pairingTypeField:pairingTypes")
	public WebElement pairingType;

	@FindBy(how = How.ID, using = "uploadPairingForm:bidPeriodField:bidPeriods")
	public WebElement bidPeriod;

	@FindBy(how = How.ID, using = "uploadPairingForm:ignorePairingsField:ignorePairings")
	public WebElement pairingsToignore;

	@FindBy(how = How.XPATH, using = ".//*[@id='uploadPairingForm:uploadField']/table/tbody/tr/td/div[1]")
	public WebElement addFile;

	//@FindBy(how = How.ID, using = "uploadPairingForm:j_id181") //uploadPairingForm:j_id166
	//@FindBy(how = How.XPATH, using = ".//*[@value='Reset']")
	@FindBy(how = How.CSS, using = "input[value='Reset']")
	public WebElement resetButton;

	public UploadPairingsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void uploadPairing(String pairingTypeValue, String bidPeriodValue, String pairingsToIgnoreValue) {
		int pairingtype = Integer.parseInt(pairingTypeValue);
		selectByIndex(pairingType, pairingtype);
		int bidPeriodVal = Integer.parseInt(bidPeriodValue);
		selectByIndex(bidPeriod, bidPeriodVal);
		typeInText(pairingsToignore, pairingsToIgnoreValue);
		waitForElementVisibility(addFile);
		waitForElementVisibility(resetButton);
	}
}

