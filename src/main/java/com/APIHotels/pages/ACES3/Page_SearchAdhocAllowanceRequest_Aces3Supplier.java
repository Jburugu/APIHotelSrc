package com.APIHotels.pages.ACES3;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;


public class Page_SearchAdhocAllowanceRequest_Aces3Supplier extends BasePage{
	
	@FindBy(id = "allowanceForecastsForm:tenantMenu_label")
	private WebElement selectAirlineDropDown;
	
	@FindBy(id = "allowanceForecastsForm:paymentId")
	private WebElement adhocRequestId;
	
	@FindBy(id = "allowanceForecastsForm:paytDatebutton_input")
	private WebElement paymentDate;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm']//div/button")
	private WebElement searchBtn;
	
	@FindBy(id = "allowanceForecastsForm:resetBtn")
	private WebElement resetBtn;
	
	String airlineXpath1="//*[@id='allowanceForecastsForm:tenantMenu_panel']//li[contains(text(),'";
	String airlineXpath2="')]";
	
	@FindBy(id = "allowanceForecastsForm:adhocAllowanceDetailsTable")
	private WebElement adhocAllowanceDetailsTable;
	
	String tableDataXpath="//*[@id='allowanceForecastsForm:adhocAllowanceDetailsTable_data']/tr/td[";
	
	String adhocRequestIdXpath="1]";
	String statusXpath="12]";
	
	@FindBy(xpath="//button[contains(@id,'adhocbutton-generate')]")
	private WebElement generateAdhocSheetBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[@id='allowanceForecastsForm:paytDatebutton']/button")
	private WebElement calender;
	
	
	public EventFiringWebDriver driver=null;
	
	public Page_SearchAdhocAllowanceRequest_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchAdhocAllowanceRequest(String adhocRequestId){
		waitForElementVisibility(selectAirlineDropDown);
		typeInText(this.adhocRequestId, adhocRequestId, "SearchAdhocAllowanceRequestPage -> Adhoc Request Id");
		waitForElementVisibility(paymentDate);
		waitForElementVisibility(searchBtn);
		waitForElementVisibility(resetBtn);
	}
	
	public void searchForAdhocAllowanceRequest(String tenantName, String adhocRequestId) throws InterruptedException{
		waitForElementVisibility(selectAirlineDropDown);
		clickOn(selectAirlineDropDown, "SearchAdhocAllowanceRequestPage -> SelectAirlineDropDown");
		clickOn(findElementByXpath(airlineXpath1+tenantName+airlineXpath2), "SearchAdhocAllowanceRequestPage ->Selected "+ tenantName);
		typeInText(this.adhocRequestId, adhocRequestId, "SearchAdhocAllowanceRequestPage -> Adhoc RequestId");
		typeInText(paymentDate, currentDate().replaceAll("-", " "), "SearchAdhocAllowanceRequestPage -> PaymentDate" );
		paymentDate.sendKeys(Keys.TAB);
		calender.sendKeys(Keys.TAB);
		clickOn(searchBtn, "SearchAdhocAllowanceRequestPage -> Search Button");
		Thread.sleep(2000);
		waitForElementVisibility(adhocAllowanceDetailsTable);
		String adhocRequestIdInTable= findElementByXpath(tableDataXpath+adhocRequestIdXpath).getText();
		if(adhocRequestIdInTable.equalsIgnoreCase(adhocRequestId)){
			String statusBeforeGenerateSheet= findElementByXpath(tableDataXpath+statusXpath).getText();
			Assert.assertTrue(statusBeforeGenerateSheet.equalsIgnoreCase("PENDING"), "Status is different than Pending");
			clickOn(generateAdhocSheetBtn, "SearchAdhocAllowanceRequestPage -> GenerateAdhocSheet Button");
			waitForElementToDisappear(spinner);
			String statusAfterGenerateSheet=findElementByXpath(tableDataXpath+statusXpath).getText();
			Assert.assertTrue(statusAfterGenerateSheet.equalsIgnoreCase("GENERATED"), "Status is different than Pending");
		}
		
	}
	
	

}
