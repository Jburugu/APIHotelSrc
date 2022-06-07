package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AirportToAirportTransitTimes extends BasePage{

	@FindBy(xpath = "//*[@value = 'Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//*[@onclick = 'addTransitRow();' and @value = 'Add']")
	private WebElement addBtn;
	
	@FindBy(xpath = "//*[@id = 'rowCount' and @type = 'hidden']")
	private WebElement rowCount;
	
	private String routeType = "routeTypeObjID";
	private String fromAirport = "frAirPortCode";
	private String venue = "venueCodeAObjID";
	private String toAirport = "toAirPortCode";
	private String toVenue = "venueCodeBObjID";
	private String startDate = "startDt";
	private String endDate = "endDt";
	private String startTime = "startTimeDummy";
	private String endTime = "endTimeDummy";
	private String transitTime = "transitTime";
	private String deleteCheckBox = "delTransId";
	public EventFiringWebDriver driver=null;

	public Page_AirportToAirportTransitTimes(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addTransitRow(int routeTypeIndex, String daysOfWeek, String fromAirportCode, String toAirportCode, String transitTimeInMinutes) throws Exception{
		String rowId = rowCount.getAttribute("value").toString();
		clickOn(addBtn);
		//waitForElementVisibility(findElementById(routeType+rowId)); //dropdown not coming up on screen
		for(String currentDay: daysOfWeek.split(",")){
			String daysOfWeekXpath = "//*[@name = 'apToApIndexList["+rowId+"].selectedItems' and @value = '"+currentDay+"']";
			clickOn(findElementByXpath(daysOfWeekXpath));
		}
		typeInText(findElementById(fromAirport+rowId), fromAirportCode);
		//waitForElementVisibility(findElementById(venue+rowId)); //dropdown not coming up on screen
		typeInText(findElementById(toAirport+rowId), toAirportCode);
		//waitForElementVisibility(findElementById(toVenue+rowId)); //dropdown not coming up on screen
		waitForElementVisibility(findElementById(startDate+rowId));
		waitForElementVisibility(findElementById(endDate+rowId));
		waitForElementVisibility(findElementById(startTime+rowId));
		waitForElementVisibility(findElementById(endTime+rowId));
		typeInText(findElementById(transitTime+rowId), transitTimeInMinutes);
		waitForElementVisibility(findElementById(deleteCheckBox+rowId));
	}
	
	public void saveDetails() throws Exception{
		waitForElementVisibility(saveBtn);
	}

}
