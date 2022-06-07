package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ExportAssignedPairingsPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// PLANNING -- EXPORT ASSIGNED PAIRINGS (ALASKA)

	@FindBy(how = How.ID, using = "exportPairingForm:bidPeriodField:bidPeriods")
	public WebElement bidPeriod;

	@FindBy(how = How.ID, using = "exportPairingForm:exportButton")
	public WebElement exportButton;

	@FindBy(how = How.ID, using = "exportPairingForm:reset")
	public WebElement resetButton;

	public ExportAssignedPairingsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void exportAssignedPairings(String bidPeriodValue) {

		int bidPeriodVal = Integer.parseInt(bidPeriodValue);
		selectByIndex(bidPeriod, bidPeriodVal);
		waitForElementVisibility(exportButton);
		waitForElementVisibility(resetButton);
	}
}
