package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ExportPairingsPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// PLANNING -- EXPORT PAIRINGS

	@FindBy(how = How.ID, using = "exportPairingForm:bidPeriodField:bidPeriods")
	public WebElement bidPeriod;

	@FindBy(how = How.ID, using = "exportPairingForm:pairingFileField:file")
	public WebElement exportpairingFile;

	@FindBy(how = How.ID, using = "exportPairingForm:exportButton")
	public WebElement exportButton;

	@FindBy(how = How.ID, using = "exportPairingForm:reset")
	public WebElement resetButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='exportPairingForm:pairingFileField']/table/tbody/tr/td/div[1]")
	public WebElement addFile;

	public ExportPairingsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void exportPairing(String bidperiodValue) {
		int exportPairingsbidperiod = Integer.parseInt(bidperiodValue);
		// Unable to fetch the bidperiod values in endeavor, commenting the
		// below line
		selectByIndex(bidPeriod, exportPairingsbidperiod);
		waitForElementVisibility(addFile);
		waitForElementVisibility(exportButton);
		waitForElementVisibility(resetButton);

	}

}
