package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ActionStatusPage extends BasePage{

	public EventFiringWebDriver driver=null;

	// OPERATIONS -- ACTION STATUS

	@FindBy(how = How.ID, using = "viewNotesForm:viewTypes")
	public WebElement selectRequestsBetween;

	@FindBy(how = How.ID, using = "viewNotesForm:refreshMinutes")
	public WebElement setRefreshedInterval;

	@FindBy(how = How.ID, using = "viewNotesForm:retrieve")
	public WebElement retrieveButton;

	public ActionStatusPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void actionStatus(String selectRequestsBetweenValue, String setRefreshIntervalValue) {

		int selectRequestsBetweenVal = Integer.parseInt(selectRequestsBetweenValue);
		selectByIndex(selectRequestsBetween, selectRequestsBetweenVal);
		int refreshVal = Integer.parseInt(setRefreshIntervalValue);
		selectByIndex(setRefreshedInterval, refreshVal);
		waitForElementVisibility(retrieveButton);

	}
	
}
