package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.framework.BasePage;

public class Page_AgentReport extends BasePage{
	
	@FindBy(css = "#agentReport > a")
	private WebElement agentReportLink;
	
	@FindBy(id = "agentNameId")
	private WebElement agent;
	
	@FindBy(id = "agentFormStartDate")
	private WebElement startDate;
	
	@FindBy(id = "agentFormEndDate")
	private WebElement endDate;
	
	@FindBy(id = "startDtTime")
	private WebElement startTime;
	
	@FindBy(id = "endDtTime")
	private WebElement endTime;
	
	@FindBy(name = "enabledCompleteTimeSlot")
	private WebElement allSlotsIndicator;
	
	@FindBy(name = "submitBtn")
	private WebElement viewReportBtn;
	
	@FindBy(xpath = "//*[@id='agentReportResult']/a[2]")
	private WebElement exportOption_Excel;

	public EventFiringWebDriver driver=null;
	
	public Page_AgentReport(EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	
	public void clickAgentReportLink() throws Exception{
		clickOn(agentReportLink);
	}
	
	public void viewAgentReport(int agent, String startDate, String endDate, int startTime, int endTime) throws Exception{
		selectByIndex(this.agent, agent);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		selectByIndex(this.startTime, startTime);
		selectByIndex(this.endTime, endTime);
		waitForElementVisibility(allSlotsIndicator);
		clickOn(viewReportBtn);
		clickOn(exportOption_Excel);
		Thread.sleep(20000);
	}
	
	
	
	public void viewAgentReport(DataTable opsReportsTable) throws Exception{
		selectByIndex(this.agent, Integer.parseInt(opsReportsTable.getFieldValue(1, "agent")));
		typeInText(this.startDate, opsReportsTable.getFieldValue(1, "startDate"));
		typeInText(this.endDate, opsReportsTable.getFieldValue(1, "endDate"));
		selectByIndex(this.startTime, Integer.parseInt(opsReportsTable.getFieldValue(1, "startTime")));
		selectByIndex(this.endTime, Integer.parseInt(opsReportsTable.getFieldValue(1, "endTime")));
		waitForElementVisibility(allSlotsIndicator);
		clickOn(viewReportBtn);
		waitForElementVisibility(exportOption_Excel);
	}
}
