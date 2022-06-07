package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class CATTSReportPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- CATTS REPORT(UPS)

	@FindBy(how = How.ID, using = "iconCATTSreport")
	public WebElement cattsReportLink;

	@FindBy(how = How.ID, using = "CATTSForm:startDateCalInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "CATTSForm:endDateCalInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "CATTSForm:startDateCalPopupButton")
	public WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "CATTSForm:endDateCalPopupButton")
	public WebElement endDateCalendar;

	@FindBy(how = How.ID, using = "CATTSForm:inputButton")
	public WebElement generateButton;

	public CATTSReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void CATTSReportRequest()
	{
		typeInText(startDate, currentDate());
		typeInText(endDate, currentDate());
		waitForElementVisibility(generateButton);
		
	}

}
