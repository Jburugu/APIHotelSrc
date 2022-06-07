package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class SchedulesMenu extends BasePage{
	
	@FindBy(xpath = "//*[contains(text(), 'Create Schedules')]")
	private WebElement createSchedulesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'View Schedules')]")
	private WebElement viewSchedulesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Schedule Notes')]")
	private WebElement scheduleNotesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Lock Schedules')]")
	private WebElement lockSchedulesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Ready To Export')]")
	private WebElement readyToExportLink;

	public EventFiringWebDriver driver=null;

	public SchedulesMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickCreateSchedulesLink() throws Exception{
		//logger.info("*** SchedulesMenu -> clickCreateSchedules method started ***");
		clickOn(createSchedulesLink, "Schedules Menu -> CreateSchedules Link");
		//logger.info("*** clickCreateSchedulesLink method completed ***");
	}
	
	public void clickViewSchedulesLink() throws Exception{
		//logger.info("*** SchedulesMenu -> clickViewSchedules method started ***");
		clickOn(viewSchedulesLink,  "Schedules Menu -> ViewSchedules Link");
		//logger.info("*** clickViewSchedules method completed ***");
	}
	
	public void clickScheduleNotesLink() throws Exception{
		//logger.info("*** SchedulesMenu -> clickScheduleNotesLink method started ***");
		clickOn(scheduleNotesLink,  "Schedules Menu -> SchedulesNotes Link");
		//logger.info("*** clickScheduleNotesLink method completed ***");
	}
	
	public void clickLockSchedulesLink() throws Exception{
		//logger.info("*** SchedulesMenu -> clickLockSchedulesLink method started ***");
		clickOn(lockSchedulesLink, "Schedules Menu -> LockSchedules Link");
		//logger.info("*** clickLockSchedulesLink method completed ***");
	}
	
	public void clickReadyToExportLink() throws Exception{
		//logger.info("*** SchedulesMenu -> clickReadyToExportLink method started ***");
		clickOn(readyToExportLink, "Schedules Menu -> ReadyToExport Link");
		//logger.info("*** clickReadyToExportLink method completed ***");
	}
		
}
