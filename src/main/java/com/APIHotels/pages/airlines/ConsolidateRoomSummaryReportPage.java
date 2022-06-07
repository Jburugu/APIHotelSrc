package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ConsolidateRoomSummaryReportPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- CONSOLIDATE ROOM
	// SUMMARY(JETBLUE/ENDEAVOR)

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'consolidateRoomSummaryForm:consolidateInvoiceDateTable')]")
	public WebElement consolidatedInvoiceDate;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Usage Type')]")
	public WebElement usageType;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Room Amount')]")
	public WebElement roomAmount;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'No. of Rooms')]")
	public WebElement noOfRooms;

	public ConsolidateRoomSummaryReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void consolidateRoomSummary() {
		waitForElementVisibility(consolidatedInvoiceDate);
		waitForElementVisibility(usageType);
		waitForElementVisibility(roomAmount);
		waitForElementVisibility(noOfRooms);
	}

}
