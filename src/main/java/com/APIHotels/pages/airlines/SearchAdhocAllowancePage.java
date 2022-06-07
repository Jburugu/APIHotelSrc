package com.APIHotels.pages.airlines;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class SearchAdhocAllowancePage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- ALLOWANCE -- SEARCH ADHOC ALLOWANCE (NEWZEALAND)

	@FindBy(xpath="//input[contains(@name,'arrivalFlightNumArea')]")
	public WebElement arrivalFlight;

	@FindBy(id= "searchAdhocAllowanceReqForm:crewIdArea:crewIdInput")
	public WebElement crewID;

	@FindBy(xpath=".//*[@id='searchAdhocAllowanceReqForm:locationArea']/div/input[contains(@name,'locationArea')]")
	public WebElement location;

	@FindBy(xpath=".//*[@id='searchAdhocAllowanceReqForm:hotelIdArea']/div/select[contains(@name,'hotelIdArea')]")
	public WebElement hotel;

	@FindBy(xpath=".//input[contains(@id,'searchAdhocAllowanceReqForm:paymentStartDateArea') and @type='text']")
	public WebElement paymentStartDate;

	@FindBy(xpath=".//input[contains(@id,'searchAdhocAllowanceReqForm:paymentEndDateArea') and @type='text']")
	public WebElement paymentEndDate;

	@FindBy(xpath="//*[@id='searchAdhocAllowanceReqForm']//button[contains(text(),'Search')]")
	public WebElement searchButton;
	
	@FindBy(id="crewID;searchAdhocAllowanceReqForm:alwnTable:tb")
	public WebElement allowancesResultTable;
	
	@FindBy(xpath="//*[@id='searchAdhocAllowanceReqForm:alwnTable:tb']/tr/td[1]")
	public WebElement adhocRequestId;
	
	@FindBy(xpath="//*[@id='searchAdhocAllowanceReqForm:alwnTable:tb']//tr//a[contains(text(),'Cancel Allowance')]/parent::td/preceding-sibling::td[9]")
	public List<WebElement> adhocCrewId;

	@FindBy(xpath="//*[@id='searchAdhocAllowanceReqForm:alwnTable:tb']//tr//a[contains(text(),'Cancel Allowance')]")
	public List<WebElement> cancelAllowanceLink;

	@FindBy(id="waitHeader")
	public WebElement waitHeader;
	
	@FindBy(id="confDisablePanelCDiv")
	public WebElement cancelAdhocAllowancePopUp;
	
	@FindBy(id="removeAllowancepopupPanelContentTable")
	public WebElement cancelMultipleAllowancesPopUp;
	
	@FindBy(linkText="Cancel")
	public WebElement cancelLink;
	
	@FindBy(xpath="//*[@id='searchAdhocAllowanceReqForm:globalMessages']//span")
	public WebElement noRecordFoundMsg;
	
	@FindBy(id="spinner")
	public WebElement spinner;
	
	@FindBy(xpath="//*[@id='searchAdhocAllowanceReqForm:alwnTable:tb']//tr")
	private List<WebElement> allowanceRequests;
	
	String checkBoxXpath1="//*[@id='searchAdhocAllowanceReqForm:alwnTable:";
	String checkBoxXpath2=":checkboxpanel']";
	
	@FindBy(xpath="//*[contains(@id,'checkboxpanel')]")
	private List<WebElement> checkboxes;
	
	@FindBy(id="searchAdhocAllowanceReqForm:removeSelectedAllowance")
	public WebElement cancelAllowanceBtn;
	
	public SearchAdhocAllowancePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchAhocAllowance(String arrivalFlightNumber, String crewValue, String locationValue,
			String hotelValue) {
		typeInText(arrivalFlight, arrivalFlightNumber);
		typeInText(crewID, crewValue);
		typeInText(location, locationValue);
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		typeInText(paymentStartDate, currentDate());
		typeInText(paymentEndDate, currentDatePlus(1));
		waitForElementVisibility(searchButton);

	}
	
	public void searchAdhocAllowance() {
		typeInText(paymentStartDate, currentDate(), "SearchAdhocAllowanceRequestPage -> PaymentStartDate");
		typeInText(paymentEndDate, currentDatePlus(1), "SearchAdhocAllowanceRequestPage -> PaymentEndDate");
		clickOn(searchButton, "SearchAdhocAllowanceRequestPage -> Search Button");
		waitForElementToDisappear(spinner);
	}
	
	public void cancelSingleAllowance(){
		String crewId=adhocCrewId.get(0).getText();
		clickOn(cancelAllowanceLink.get(0), "SearchAdhocAllowanceRequestPage -> CancelAllowance Link");
		waitForElementToDisappear(waitHeader);
		waitForElementVisibility(cancelAdhocAllowancePopUp);
		clickOn(cancelLink, "SearchAdhocAllowanceRequestPage -> Cancel Link");
		//waitTime(9000);
		waitForElementToDisappear(cancelAdhocAllowancePopUp);
		clickOn(searchButton, "SearchAdhocAllowanceRequestPage -> Search Button");
		waitForElementToDisappear(spinner);
		Assert.assertTrue(!adhocCrewId.get(0).getText().equalsIgnoreCase(crewId), "Adhoc Request Id found in table after allowance is cancelled");
	}
	
	public void cancelMultipleAllowances(){
		
		for(int checkBoxCount=0; checkBoxCount<checkboxes.size(); checkBoxCount++){
			//if((findElementByXpath(checkBoxXpath1+checkBoxCount+checkBoxXpath2)).isDisplayed())
				clickOn(checkboxes.get(checkBoxCount), "SearchAdhocAllowanceRequestPage -> select Cancel Checkboxes");
		}
		
		clickOn(cancelAllowanceBtn, "SearchAdhocAllowanceRequestPage -> CancelAllowance Button");
		waitForElementToDisappear(waitHeader);
		waitForElementVisibility(cancelMultipleAllowancesPopUp);
		clickOn(cancelLink);
		//waitTime(4000);
		waitForElementToDisappear(cancelMultipleAllowancesPopUp);
		clickOn(searchButton, "SearchAdhocAllowanceRequestPage -> Search Button");
		waitForElementToDisappear(spinner);
		//Assert.assertTrue(!adhocCrewId.get(0).getText().equalsIgnoreCase(crewId), "Adhoc Request Id found in table after allowance is cancelled");
	}
	

}
