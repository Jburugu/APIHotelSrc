package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.framework.BasePage;

public class Page_TripTrackingReport extends BasePage{
	
	@FindBy(css = "#tripTrackingReport > a")
	private WebElement tripTrackingReportLink;
	
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
	
	@FindBy(name = "submitBtn")
	private WebElement viewReportBtn;
	
	private String serviceTypeXpath1 = "//*[@name = 'serviceType' and @value = '";
	private String serviceTypeXpath2 = "']";
	
	@FindBy(xpath = "//*[@id='tripTrackingReportResult']//a[contains(text(), 'Excel ')]")
	private WebElement exportOption_Excel;
	
	public EventFiringWebDriver driver=null;

	public Page_TripTrackingReport(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickTripTrackingReportLink() throws Exception{
		clickOn(tripTrackingReportLink);
	}
	
	public void viewTripTrackingReport(int agent, String startDate, String endDate, int startTime, int endTime, String serviceType) throws Exception{
		selectByIndex(this.agent, agent);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		selectByIndex(this.startTime, startTime);
		selectByIndex(this.endTime, endTime);
		clickOn(findElementByXpath(serviceTypeXpath1+serviceType+serviceTypeXpath2));
		clickOn(viewReportBtn);
		waitForPageLoad(getDriver());
		clickOn(exportOption_Excel);
		Thread.sleep(20000);
	}
	
	public void viewTripTrackingReport(DataTable opsReportsTable) throws Exception{
		selectByIndex(this.agent, Integer.parseInt(opsReportsTable.getFieldValue(1, "agent")));
		typeInText(this.startDate, opsReportsTable.getFieldValue(1, "startDate"));
		typeInText(this.endDate, opsReportsTable.getFieldValue(1, "endDate"));
		selectByIndex(this.startTime, Integer.parseInt(opsReportsTable.getFieldValue(1, "startTime")));
		selectByIndex(this.endTime, Integer.parseInt(opsReportsTable.getFieldValue(1, "endTime")));
		clickOn(findElementByXpath(serviceTypeXpath1+opsReportsTable.getFieldValue(1, "serviceType")+serviceTypeXpath2));
		clickOn(viewReportBtn);
	}
}
