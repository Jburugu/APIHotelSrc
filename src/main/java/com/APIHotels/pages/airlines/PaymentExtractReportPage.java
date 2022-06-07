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

public class PaymentExtractReportPage extends BasePage {

	public EventFiringWebDriver driver = null;

	/**
	 * OPERATIONS -- ALLOWANCE -- PAYMENT EXTRACT REPORT(NEWZEALAND)
	 */
	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceForecastsForm:startDateDeco:startDateInputDate']")
	public WebElement dateFrom;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceForecastsForm:endDateDeco:endDateInputDate']")
	public WebElement dateTo;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceForecastsForm:contractGroupArea']/div/select[contains(@name,'contractGroupArea')]")
	public WebElement contractGroup;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceForecastsForm:staffNumberArea:staffNumber']")
	public WebElement staffNumber;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement searchButton;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:startDateDeco:startDatePopupButton")
	public WebElement dateFromCalendar;

	@FindBy(how = How.ID, using = "allowanceForecastsForm:endDateDeco:endDatePopupButton")
	public WebElement dateToCalendar;
	
	@FindBy(id="allowanceForecastsForm:londonReport")
	private WebElement downloadLondonReport;
	
	@FindBy(linkText="CSV")
	private WebElement csvLink;
	
	@FindBy(linkText="Excel")
	private WebElement excelLink;
	
	@FindBy(xpath="//*[contains(text(),'Roster Group')]/../following-sibling::td")
	public WebElement roasterGroupText;
	
	@FindBy(xpath="//*[contains(text(),'No Record(s) Found')]")
	public WebElement noRecordsFoundText;
	
	@FindBy(id="spinner")
	public WebElement spinner;
	
	@FindBy(xpath="//*[@id='allowanceForecastsForm:paymentExtractTable']//tr")
	private List<WebElement> PERTableRows;
	
	String tableXpath= "//*[@id='allowanceForecastsForm:paymentExtractTable']//tr[";
	String descriptiveOverrideXpath="]/td[6]";
	String employeeId="]/td[2]";
	String todAllow="]/td[5]";
	
	public PaymentExtractReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void paymentExtractReport(String contractGroupValue, String staffnumberValue) {
		typeInText(dateFrom, currentDate());
		waitForElementVisibility(dateFromCalendar);
		typeInText(dateTo, currentDatePlus(1));
		waitForElementVisibility(dateToCalendar);
		selectByValue(contractGroup, contractGroupValue);
		typeInText(staffNumber, staffnumberValue);
		waitForElementVisibility(searchButton);
	}
	
	public void verifyAllSearchOptionsAndExports(String fromDate, String contractGroups, String staffnumberValue, String roasterGroup) throws InterruptedException{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+fromDate+"';", dateFrom);
		dateFrom.sendKeys(Keys.TAB);
		typeInText(staffNumber, staffnumberValue);
		List<String> contractGroupList= Arrays.asList(contractGroups.split(","));
		List<String> roasterGroupList= Arrays.asList(roasterGroup.split(","));
		for(int i=0; i<contractGroupList.size();i++){
			selectByValue(contractGroup, contractGroupList.get(i),"PaymentExtractReport Page -> ContractGroup");
			Thread.sleep(2000);
			clickOn(searchButton,"PaymentExtractReport Page -> Search Button");
			if(!roasterGroupList.get(i).equalsIgnoreCase("null")){
				if (roasterGroupText.getText().equalsIgnoreCase(roasterGroupList.get(i))) {
					clickOn(csvLink);
					clickOn(excelLink);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Payment Extract Report displayed for Contract Group " + contractGroupList.get(i));
				}
				else Assert.fail("Payment Extract Report is not displayed for Contract Group " + contractGroupList.get(i));
			}
			else Assert.assertTrue(noRecordsFoundText.isDisplayed(), "No Records Found Message is not displayed");
			deselectByValue(contractGroup, contractGroupList.get(i), "PERReportPage -> ContractGroup");
		}
		
	}
	
	public void verifyTODAndPRTAndExportLondonReport(String fromDate, String contractGroupValue, String staffnumberValue,
			String tripNumber, String empID, String elementType) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+fromDate+"';", dateFrom);
		dateFrom.sendKeys(Keys.TAB);
		deselectByValue(contractGroup, "ALL", "PaymentExtractReport Page -> Deselect Contract Group");
		selectByValue(contractGroup, contractGroupValue, "PaymentExtractReport Page -> Select Contract Group");
		typeInText(staffNumber, staffnumberValue, "PaymentExtractReport Page -> Staff Number");
		clickOn(searchButton, "PaymentExtractReport Page -> Search Button");
		waitForPageToLoad("300");
		waitForElementVisibility(downloadLondonReport);
		clickOn(downloadLondonReport, "PaymentExtractReport Page -> DownloadLondonReport Button");
		int tableRows= PERTableRows.size();
		int index=0;
		List<String> empIDs= Arrays.asList(empID.split(","));
		for(int rowCount=1; rowCount<tableRows ; rowCount++){
			String tripId= findElementByXpath(tableXpath+rowCount+descriptiveOverrideXpath).getText().substring(0, 7);
			String employeeId= findElementByXpath(tableXpath+rowCount+this.employeeId).getText();
			if(tripId.equalsIgnoreCase(tripNumber)&& employeeId.equalsIgnoreCase(empIDs.get(index))){
				index++;
				String element= findElementByXpath(tableXpath+rowCount+todAllow).getText();
				if(elementType.equalsIgnoreCase(element)){
					ExtentTestManager.getTest().log(LogStatus.PASS,""
							+ elementType +" displayed for TripNumber: " + tripNumber);
				}
				else Assert.fail("Required Allowance did not displayed for "+ tripNumber);
			}
		}
	}

}
