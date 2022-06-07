package com.APIHotels.pages.airlines;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class DEAReportPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- ALLOWANCE REPORT -- DEA REPORT(NEWZEALAND)

	@FindBy(how = How.ID, using = "allowanceForecastsForm:startDateDeco:startDateInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:startDateDeco:startDatePopupButton")
	public WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:endDateDeco:endDateInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:endDateDeco:endDatePopupButton")
	public WebElement endDateCalendar;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceForecastsForm:resourceGroupArea']/div/select[contains(@name,'resourceGroupArea')]")
	public WebElement resourceGroup;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceForecastsForm:baseArea']/div/input[contains(@name,'baseArea')]")
	public WebElement base;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:staffNumberArea:staffNumber")
	public WebElement staffNumber;

	@FindBy(xpath="//button[contains(text(),'Search')]")
	public WebElement searchBtn;
	
	@FindBy(id="allowanceForecastsForm:deaTable")
	private List<WebElement> deaReportTable;
	
	@FindBy(xpath="//*[contains(text(),'No Record(s) Found')]")
	public WebElement noRecordsFoundText;
	
	@FindBy(linkText="CSV")
	private WebElement csvLink;
	
	@FindBy(linkText="Excel")
	private WebElement excelLink;
	
	public DEAReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void deaReport(String resourceGroupValue, String baseValue, String staffValue) {
		typeInText(startDate, currentDate());
		waitForElementVisibility(startDateCalendar);
		typeInText(endDate, currentDatePlus(1));
		waitForElementVisibility(endDateCalendar);
		selectByValue(resourceGroup, resourceGroupValue);
		typeInText(base, baseValue);
		typeInText(staffNumber, staffValue);
		waitForElementVisibility(searchBtn);
	}
	
	public void verifyAllSearchOptions_DEAReport(String fromDate, String baseValue, String staffValue, String resourceGroups) throws InterruptedException{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+fromDate+"';", startDate);
		startDate.sendKeys(Keys.TAB);
		typeInText(base, baseValue, "DEA Report Page -> Base");
		typeInText(staffNumber, staffValue, "DEA Report Page -> Staff Number");
		List<String> resourceGroupList= Arrays.asList(resourceGroups.split(","));
		for(int i=0; i<resourceGroupList.size();i++){
			selectByValue(resourceGroup, resourceGroupList.get(i), "DEA Report Page ->Select ResourceGroup");
			Thread.sleep(2000);
			clickOn(searchBtn, "DEA Report Page -> Search Button");
			if (deaReportTable.size()>0) {
				clickOn(csvLink, "DEA Report Page -> CSV Link");
				clickOn(excelLink, "DEA Report Page -> Excel Link");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"DEA Report displayed for Resouce Group " + resourceGroupList.get(i));
				}
			 else Assert.assertTrue(noRecordsFoundText.isDisplayed(), "No Records Found Message is not displayed");
			
			deselectByValue(resourceGroup, resourceGroupList.get(i) , "DEA Report Page ->  -> ResourceGroup");
		}
		
		
		
	}
	

}
