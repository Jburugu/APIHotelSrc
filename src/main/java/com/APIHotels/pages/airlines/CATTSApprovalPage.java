package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class CATTSApprovalPage extends BasePage{

	public EventFiringWebDriver driver= null;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:startDateCalInputDate")
	public WebElement start;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:endDateCalInputDate")
	public WebElement end;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:crewIdInput")
	public WebElement crewID;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:crewIdInput")
	public WebElement matchStatusNewandHold;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:approvedBox")
	public WebElement matchStatusApprovedBox;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:ignoredBox")
	public WebElement matchStatusIgnored;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:inputButton")
	public WebElement retrieve;
	
	@FindBy(how = How.XPATH, using = ".//*[contains(text(),'Deviation Matches: ')]")
	public WebElement deviationMatches;
	
	@FindBy(how = How.XPATH, using = ".//*[contains(text(),'Unmatched: ')]")
	public WebElement unmatched;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:approveDeviationButton")
	public WebElement approveSelected;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:ignoreDeviationButton")
	public WebElement ignoreSelected;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:holdDeviationButton")
	public WebElement holdSelected;
	
	@FindBy(how = How.ID, using = "reviewMatchesForm:deviationMatchTable:allDeviationBox")
	public WebElement selectAll;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Status')]")
	public WebElement status;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Ticket #')]")
	public WebElement ticket;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Flight #')]")
	public WebElement flight;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Crew ID')]")
	public WebElement crewid;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Origin')]")
	public WebElement origin;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Departure (UTC)')]")
	public WebElement departmentUTC;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Destination')]")
	public WebElement destination;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Arrival (UTC)')]")
	public WebElement arrivalUTC;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//span[contains(text(),'Match Type')]")
	public WebElement matchType;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reviewMatchesForm:deviationMatchTable']//th[11]/div")
	public WebElement action;
	
	
	public CATTSApprovalPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void CATTSProcessingReview(String crewIDValue)
	{
		waitForElementVisibility(start);
		typeInText(start, currentDate());
		typeInText(end, currentDate());
		typeInText(crewID, crewIDValue);
		clickOn(matchStatusNewandHold);
		clickOn(matchStatusApprovedBox);
		clickOn(matchStatusIgnored);
		waitForElementVisibility(retrieve);
		clickOn(deviationMatches);
		waitForElementVisibility(approveSelected);
		waitForElementVisibility(ignoreSelected);
		waitForElementVisibility(holdSelected);
		clickOn(selectAll);
		clickOn(status);
		clickOn(ticket);
		clickOn(flight);
		clickOn(crewid);
		clickOn(origin);
		clickOn(departmentUTC);
		clickOn(destination);
		clickOn(arrivalUTC);
		clickOn(matchType);
		clickOn(unmatched);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
