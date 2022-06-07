package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class AuditHistoryPage extends BasePage {

	public EventFiringWebDriver driver=null;

	// ADMIN -- MANAGE USER -- AUDIT HISTORY

	@FindBy(how = How.ID, using = "auditForm:fromDtArea:fromDateInputDate")
	public WebElement fromDate;

	@FindBy(how = How.ID, using = "auditForm:fromDtArea:fromDatePopupButton")
	public WebElement fromDateCalendar;

	@FindBy(how = How.ID, using = "auditForm:toDtArea:toDateInputDate")
	public WebElement toDate;

	@FindBy(how = How.ID, using = "auditForm:toDtArea:toDatePopupButton")
	public WebElement toDateCalendar;

	@FindBy(how = How.ID, using = "auditForm:retrieveButton")
	public WebElement retrieveButton;
	
	public AuditHistoryPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void auditHistory() {
		waitForElementVisibility(fromDateCalendar);
		fromDate.clear();
		typeInText(fromDate, currentDate());
		waitForElementVisibility(toDateCalendar);
		toDate.clear();
		typeInText(toDate, currentDate());
		waitForElementVisibility(retrieveButton);
	}
	
}
