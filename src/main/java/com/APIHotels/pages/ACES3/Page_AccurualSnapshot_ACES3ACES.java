package com.APIHotels.pages.ACES3;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class Page_AccurualSnapshot_ACES3ACES extends BasePage{
	
	@FindBy(xpath="//label[contains(@id,'tenant_label')]")
	WebElement selectAirline; 
	
	@FindBy(xpath="//ul[contains(@id,'tenant_items')]/li")
	WebElement airlinesList;
		
	String tenantXpath1 = "//*[contains(@id, 'tenant_items')]/li[contains(text(), '";
	String tenantXpath2 = "')]";
	
	@FindBy(xpath="//div[contains(@id,'start')]//img")
	private WebElement spinner;
	
	@FindBy(id="accrualSnapshotForm:bpStart")
	private WebElement fromBillingPeriod; 
	
	@FindBy(id="accrualSnapshotForm:bpEnd")
	private WebElement toBillingPeriod; 
	
	@FindBy(className="ui-datepicker-title")
	private WebElement monthYearTitle;
	
	@FindBy(id="ui-datepicker-div")
	private WebElement calendar;
	
	String previousMonth="//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath="//span[contains(text(),'Create New Snapshot')]/..")
	private WebElement createNewSnapshotBtn;
	
	@FindBy(xpath="//td[contains(text(),'January 2020')]")
	private WebElement accuralMonthInCreatedSnapshotTable;
	
	public EventFiringWebDriver driver = null;
	public Page_AccurualSnapshot_ACES3ACES(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void accrualSnapshot(String airlinesName, String fromBillingPeriodDate, String toBillingPeriodDate) throws Exception
	{	
		clickOn(selectAirline,"Accrual Snapshot Page -> Airline Select");
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(tenantXpath1+airlinesName+tenantXpath2),"Accrual Snapshot Page -> Airline Selected From Suggestions List");
		waitForSpinnerToDisappear();
		clickOn(fromBillingPeriod,"Accrual Snapshot Page -> Billing Period From DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", fromBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(toBillingPeriod,"Accrual Snapshot Page -> Billing Period To DatePicker");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", toBillingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(createNewSnapshotBtn,"Accrual Snapshot Page -> Create New Snapshot Button");
		waitForSpinnerToDisappear();
		waitForElementVisibility(accuralMonthInCreatedSnapshotTable);
	}
	
	public void waitForSpinnerToDisappear()
	{
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
        wait1.withTimeout(Duration.ofMinutes(30));
        wait1.pollingEvery(Duration.ofSeconds(2));
        wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}
}
