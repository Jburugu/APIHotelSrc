package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_LockSchedules extends BasePage{

	@FindBy(id = "comSelectBidPeriod")
	private WebElement month;
	
	@FindBy(xpath = "//*[@name = 'Search' and @value = 'Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@class = 'Aces_SubHead']")
	private List<WebElement> searchResult_Message; //The schedules are currently locked for
   // 02 Dec 18-31 Dec 18
	
	public EventFiringWebDriver driver=null;

	public Page_LockSchedules(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyLockSchedules() throws Exception{
		//logger.info("*** LockSchedulesPage -> verifyLockSchedules method started ***");
		waitForElementVisibility(month);
		clickOn(searchBtn);
		if(searchResult_Message.get(1).getText().contains("The schedules are currently locked for"))
			System.out.println("Message displayed successfully!\n"+searchResult_Message.get(1).getText());
		else
			System.out.println(searchResult_Message.get(1).getText());
	}

}
