package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class RoomUsageExtractPage extends BasePage{
	// MESA AIRLINES -- REPORTS -- ACCOUNTING REPORTS -- ROOM USAGE EXTRACT
	public EventFiringWebDriver driver=null;
	
	@FindBy(how = How.ID, using = "roomUsageExtractForm:calendarMonth:bidPeriods")
	public WebElement invoiceMonth;
	
	@FindBy(how = How.ID, using = "roomUsageExtractForm:inputButton")
	public WebElement generateReportbtn;
	 
	
	public RoomUsageExtractPage(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void roomUsageExtract()
	{
		selectByVisibleText(invoiceMonth, getCurrentMonthAndYear());
		waitForElementVisibility(generateReportbtn);
	}
}
