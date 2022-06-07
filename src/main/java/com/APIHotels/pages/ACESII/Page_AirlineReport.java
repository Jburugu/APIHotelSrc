package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.framework.BasePage;

public class Page_AirlineReport extends BasePage{
	
	@FindBy(css = "#airlineReport > a")
	private WebElement airlineReportLink;
	
	@FindBy(name = "tenantIdArray")
	private WebElement tenant;
	
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
	
	@FindBy(xpath = "//*[@value = 'View Report']")
	private WebElement viewReportBtn;

	@FindBy(xpath = "//*[@id='airlineReportResult']/a[2]")
	private WebElement exportOption_ExcelLink;
	
	public EventFiringWebDriver driver=null;

	public Page_AirlineReport(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAirlineReport() throws Exception{
		clickOn(airlineReportLink);
	}
	
	public void viewAirlineReport(String tenant, String startDate, String endDate, int startTime, int endTime) throws Exception{
		selectByVisibleText(this.tenant, tenant);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		selectByIndex(this.startTime, startTime);
		selectByIndex(this.endTime, endTime);
		waitForElementVisibility(allSlotsIndicator);
		clickOn(viewReportBtn);
		waitForPageLoad(driver);
		clickOn(exportOption_ExcelLink);
		Thread.sleep(20000);
	}
	
	public void viewAirlineReport(DataTable opsReportsTable) throws Exception{
		selectByVisibleText(this.tenant, opsReportsTable.getFieldValue(1, "tenant"));
		typeInText(this.startDate, opsReportsTable.getFieldValue(1, "startDate"));
		typeInText(this.endDate, opsReportsTable.getFieldValue(1, "endDate"));
		selectByIndex(this.startTime, Integer.parseInt(opsReportsTable.getFieldValue(1, "startTime")));
		selectByIndex(this.endTime, Integer.parseInt(opsReportsTable.getFieldValue(1, "endTime")));
		waitForElementVisibility(allSlotsIndicator);
		clickOn(viewReportBtn);
		waitForPageLoad(driver);
		waitForElementVisibility(exportOption_ExcelLink);
	}
	
}
