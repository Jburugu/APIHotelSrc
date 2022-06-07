package com.APIHotels.pages.airlines;

import java.util.List;

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

public class ViewAllowanceTransactionsPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- ALLOWANCE -- VIEW ALLOWANCE TRANSACTIONS(NEWZEALAND)

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:seriesNumArea']/div/input")
	public WebElement seriesNumber;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:startDateArea']/span/input[contains(@id,'InputDate')]")
	public WebElement startDate;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:endDateArea']/span/input[contains(@id,'InputDate')]")
	public WebElement endDate;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:locationArea']/div/input")
	public WebElement location;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:hotelIdArea']/div/select")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:crewEmpNumArea']/div/input")
	public WebElement crewId;

	@FindBy(how = How.XPATH, using = ".//*[@id='searchAllowanceTransactionsForm:allowanceTypeArea']/div/select")
	public WebElement type;

	@FindBy(xpath="//button[contains(text(),'Search')]")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'searchAllowanceTransactionsForm:startDateArea')]//img[contains(@id,'PopupButton')]")
	public WebElement startdateCalendar;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'searchAllowanceTransactionsForm:endDateArea')]//img[contains(@id,'PopupButton')]")
	public WebElement enddateCalendar;
	
	@FindBy(id="searchResultForm:resultTable:tb")
	public List<WebElement> allowanceTransactionsTable;
	
	@FindBy(linkText="Excel")
	private WebElement excelLink;

	@FindBy(xpath="//*[contains(text(),'No records found')]")
	public WebElement noRecordsFoundText;

	public ViewAllowanceTransactionsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void viewAllowanceTransaction(String locationValue, String hotelValue, String crewidValue, String typeValue,
			String seriesValue) {
		typeInText(startDate, currentDate());
		waitForElementVisibility(startdateCalendar);
		typeInText(endDate, currentDatePlus(1));
		waitForElementVisibility(enddateCalendar);
		typeInText(location, locationValue);
		location.sendKeys(Keys.TAB);
		waitTime(5000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		typeInText(crewId, crewidValue);
		selectByValue(type, typeValue);
		typeInText(seriesNumber, seriesValue);
	}
	
	public void searchAllowanceTransaction(String seriesValue, String startDate, String locationValue, String hotelValue,
			String crewIdValue, String typeValue) {
		typeInText(this.startDate, startDate, "Search Adhoc Allowances Request Page -> StartDate");
		waitTime(2000);
		typeInText(location, locationValue, "Search Adhoc Allowances Request Page -> Location");
		location.sendKeys(Keys.TAB);
		waitTime(5000);
		selectByVisibleText(hotel, hotelValue, "Search Adhoc Allowances Request Page -> Hotel Dropdown");
		typeInText(crewId, crewIdValue, "Search Adhoc Allowances Request Page -> Crew Id");
		selectByVisibleText(type, typeValue, "Search Adhoc Allowances Request Page -> Type Dropdown");
		typeInText(seriesNumber, seriesValue, "Search Adhoc Allowances Request Page -> Series Number");
		waitTime(2000);
		clickOn(searchButton, "Search Adhoc Allowances Request Page -> Search Button");
		if(allowanceTransactionsTable.size()>0){
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Allowance Transaction Report is displayed");
			clickOn(excelLink, "Search Adhoc Allowances Request Page -> Excel Link");
			waitTime(2000);
		}
		else
			Assert.assertTrue(noRecordsFoundText.isDisplayed(), "No Records Found Message is not displayed");
	}

}
