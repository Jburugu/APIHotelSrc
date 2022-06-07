package com.APIHotels.pages.airlines;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class CreateAdhocAllowancePage extends BasePage {

	public EventFiringWebDriver driver = null;

	// OPERATIONS -- ALLOWANCE -- CREATE ADHOC ALLOWANCE(NEWZEALAND)

	@FindBy(id= "createAdhocAllowanceReqForm:paymentDateArea:paymentDateCalendarInputDate")
	public WebElement paymentDate;

	@FindBy(id= "createAdhocAllowanceReqForm:localDateArrivalArea:localDateArrivalCalendarInputDate")
	public WebElement localDateArrival;

	@FindBy(xpath= ".//*[@id='createAdhocAllowanceReqForm:localTimeArrivalArea']/div/input")
	public WebElement localTimeArrival;

	@FindBy(id= "createAdhocAllowanceReqForm:arrivalFlightNumArea:arrivalFlightNumInput")
	public WebElement arrivalFlightNum;

	@FindBy(xpath= "//*[@id='createAdhocAllowanceReqForm:currencyCodeArea']/div/input")
	public WebElement currencyCode;

	@FindBy(xpath= ".//*[@id='createAdhocAllowanceReqForm:locationArea']/div/input")
	public WebElement location;

	@FindBy(id= "createAdhocAllowanceReqForm:hotelIdArea:hotelIdSelect")
	public WebElement hotel;

	@FindBy(id= "createAdhocAllowanceReqForm:paymentReasonSelectDropDownArea:paymentReasonSelectDropDown")
	public WebElement paymentReason;

	@FindBy(id= "createAdhocAllowanceReqForm:crewRequestTable:0:crewIdArea:crewIdInput")
	public WebElement crewId;

	@FindBy(xpath= "//input[contains(@name,'crewFirstNameArea')]")
	public WebElement crewFirstName;

	@FindBy(xpath= "//input[contains(@name,'crewLastNameArea')]")
	public WebElement crewLastName;

	@FindBy(xpath= "//input[contains(@name,'crewRankArea')]")
	public WebElement crewRank;

	@FindBy(xpath= "//select[contains(@name,'costCenterIdArea')]")
	public WebElement costCenter;

	@FindBy(xpath= "//select[contains(@name,'departmentIdArea')]")
	public WebElement department;

	@FindBy(xpath= "//input[contains(@name,'paymentAmountArea')]")
	public WebElement paymentAmount;

	@FindBy(xpath= "//textarea[contains(@name,'commentsArea')]")
	public WebElement comments;

	@FindBy(id= "createAdhocAllowanceReqForm:crewRequestTable:addMore")
	public WebElement addMoreBtn;

	@FindBy(xpath="//button[contains(text(),'Submit Request')]")
	public WebElement submitRequest;
	
	@FindBy(id="createAdhocAllowanceReqForm:paymentDateWarnPanelContentTable")
	public WebElement warningPopUp;
	
	@FindBy(xpath="//input[@value='Confirm']")
	public WebElement confirmBtn;
	
	@FindBy(id="saveAdhocAlwnPanelContentTable")
	public WebElement savePopUp;
	
	@FindBy(xpath="//*[@id='saveAdhocAlwnPanelMessages']//span")
	public WebElement requestIdMessage;
	
	@FindBy(linkText="OK")
	public WebElement okLinkInSavePopUp;

	@FindBy(xpath= "//table[contains(@id,'createAdhocAllowanceReqForm:crewRequestTable')]//a")
	public WebElement deleteBtn;
	
	@FindBy(id="selectTenant")
	public WebElement changeTenantLink;
	
	@FindBy(id="TenantSelectForm:tenanttypeId:tenantId")
	public WebElement tenantSelection;
	
	@FindBy(id="TenantSelectForm:selectionBtn")
	public WebElement selectButton;
	
	String crewtextBoxesXpath="//*[@id='createAdhocAllowanceReqForm:crewRequestTable:";
	String crewIdXpath=":crewIdArea:crewIdInput']";
	String crewFirstNameXpath=":crewFirstNameArea']//input";
	String crewLastNameXpath=":crewLastNameArea']//input";
	String crewRankXpath=":crewRankArea']//input";
	String costCenterXpath=":costCenterIdArea']//select";
	String departmentXpath=":departmentIdArea']//select";
	String paymentAmountXpath=":paymentAmountArea']//input";
	String commentsXpath=":commentsArea']//textarea";
	
	public CreateAdhocAllowancePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createAdhocAllowanceRequest(String arrivalTime, String arrivalFlightValue, String currencyValue,
			String locationValue, String hotelValue, String paymentValue, String crewValue, String crewfirstNameValue,
			String crewlastNameValue, String crewRankValue, String costCenterValue, String departmentValue,
			String paymentAmountValue, String commentsValue) {
		typeInText(paymentDate, currentDate());
		typeInText(localDateArrival, currentDate());
		typeInText(localTimeArrival, arrivalTime);
		typeInText(arrivalFlightNum, arrivalFlightValue);
		typeInText(currencyCode, currencyValue);
		typeInText(location, locationValue);
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		selectByIndex(paymentReason, Integer.parseInt(paymentValue));
		typeInText(crewId, crewValue);
		typeInText(crewFirstName, crewfirstNameValue);
		typeInText(crewLastName, crewlastNameValue);
		typeInText(crewRank, crewRankValue);
		waitTime(9000);
		selectByIndex(costCenter, Integer.parseInt(costCenterValue));
		selectByIndex(department, Integer.parseInt(departmentValue));
		typeInText(paymentAmount, paymentAmountValue);
		typeInText(comments, commentsValue);
		waitForElementVisibility(addMoreBtn);
		waitForElementVisibility(submitRequest);
		waitForElementVisibility(deleteBtn);
	}
	
	
	public void createmultipleAdhocRequestAllowances(String arrivalTime, String arrivalFlightValue, String currencyValue,
			String locationValue, String hotelValue, String paymentValue, String crewValue, String crewfirstNameValue,
			String crewlastNameValue, String crewRankValue, String costCenterValue, String departmentValue,
			String paymentAmountValue, String commentsValue) {
	
		typeInText(paymentDate, currentDate(), "Create Adhoc Allowance Request Page -> Payment Date");
		typeInText(localDateArrival, currentDate(), "Create Adhoc Allowance Request Page -> LocalDateArrival");
		typeInText(localTimeArrival, arrivalTime, "Create Adhoc Allowance Request Page -> LocalTimeArrival");
		typeInText(arrivalFlightNum, arrivalFlightValue, "Create Adhoc Allowance Request Page -> ArrivalFlightNumber");
		typeInText(currencyCode, currencyValue, "Create Adhoc Allowance Request Page -> CurrencyCode");
		typeInText(location, locationValue,"Create Adhoc Allowance Request Page -> Location");
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotel, Integer.parseInt(hotelValue), "Create Adhoc Allowance Request Page -> Hotel Dropdown");
		selectByIndex(paymentReason, Integer.parseInt(paymentValue), "Create Adhoc Allowance Request Page -> PaymentReason Dropdown");
		int i=0;
		while(i<4){
			clickOn(addMoreBtn, "Create Adhoc Allowance Request Page -> AddMore Button");
			waitTime(2000);
			i++;
		}
		
		for(int count=0; count<=4; count++){
				List<String> crewIds= Arrays.asList(crewValue.split(","));
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].value='"+crewIds.get(count)+"';", findElementByXpath(crewtextBoxesXpath+count+crewIdXpath));
				waitTime(2000);
				
				List<String> crewfirstNameVal= Arrays.asList(crewfirstNameValue.split(","));
				js.executeScript("arguments[0].value='"+crewfirstNameVal.get(count)+"';", findElementByXpath(crewtextBoxesXpath+count+crewFirstNameXpath));
				waitTime(2000);
				
				List<String> crewLastNameVal= Arrays.asList(crewlastNameValue.split(","));
				js.executeScript("arguments[0].value='"+crewLastNameVal.get(count)+"';", findElementByXpath(crewtextBoxesXpath+count+crewLastNameXpath));
				waitTime(2000);
				
				List<String> crewRankVal= Arrays.asList(crewRankValue.split(","));
				js.executeScript("arguments[0].value='"+crewRankVal.get(count)+"';", findElementByXpath(crewtextBoxesXpath+count+crewRankXpath));
				waitTime(9000);
				
				List<String> costCenterVal= Arrays.asList(costCenterValue.split(","));
				selectByIndex(findElementByXpath(crewtextBoxesXpath+count+costCenterXpath), Integer.parseInt(costCenterVal.get(count)) , "Create Adhoc Allowance Request Page -> Costcenter");
				waitTime(2000);
				
				List<String> departmentVal= Arrays.asList(departmentValue.split(","));
				selectByIndex(findElementByXpath(crewtextBoxesXpath+count+departmentXpath), Integer.parseInt(departmentVal.get(count)), "Create Adhoc Allowance Request Page -> Department Dropdown");
				waitTime(2000);
				
				List<String> paymentAmountVal= Arrays.asList(paymentAmountValue.split(","));
				
				typeInText(findElementByXpath(crewtextBoxesXpath+count+paymentAmountXpath), paymentAmountVal.get(count), "Create Adhoc Allowance Request Page -> PaymentAmount");
				waitTime(2000);
				
				typeInText(findElementByXpath(crewtextBoxesXpath+count+commentsXpath), commentsValue, "Create Adhoc Allowance Request Page -> Comments");
				waitTime(2000);
				
				
			}

		clickOn(submitRequest, "Create Adhoc Allowance Request Page -> Submit Button");
		if(warningPopUp.isDisplayed())
			clickOn(confirmBtn, "Create Adhoc Allowance Request Page -> Confirm Button");
		waitForElementVisibility(savePopUp);
		String allowanceId=requestIdMessage.getText();
		String requestId=allowanceId.substring(Math.max(0, allowanceId.length() - 5));
		System.out.println(requestId);		
		clickOn(okLinkInSavePopUp, "Create Adhoc Allowance Request Page -> Save Button In PopUpWindow");
		
	}
	
	public String createSingleAdhocAllowanceRequest(String arrivalTime, String arrivalFlightValue, String currencyValue,
			String locationValue, String hotelValue, String paymentValue, String crewValue, String crewfirstNameValue,
			String crewlastNameValue, String crewRankValue, String costCenterValue, String departmentValue,
			String paymentAmountValue, String commentsValue, String supplierName) {
		typeInText(paymentDate, currentDate(),"Create Adhoc Allowance Request Page -> PaymentDate");
		typeInText(localDateArrival, currentDate(), "Create Adhoc Allowance Request Page -> LocalDateArrival");
		typeInText(localTimeArrival, arrivalTime, "Create Adhoc Allowance Request Page -> LocalTimeArrial");
		typeInText(arrivalFlightNum, arrivalFlightValue, "Create Adhoc Allowance Request Page -> ArrivalFlightNumber");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		waitTime(2000);
		typeInText(currencyCode, currencyValue, "Create Adhoc Allowance Request Page -> CurrenctyCode");
		waitTime(2000);
		typeInText(location, locationValue, "Create Adhoc Allowance Request Page -> Location");
		location.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByVisibleText(hotel, supplierName, "Create Adhoc Allowance Request Page -> Hotel Dropdown");
		selectByIndex(paymentReason, Integer.parseInt(paymentValue), "Create Adhoc Allowance Request Page -> Paymnet Reason Dropdown");
		waitTime(2000);		
		js.executeScript("arguments[0].value='"+crewValue+"';", crewId);
		js.executeScript("arguments[0].value='"+crewfirstNameValue+"';", crewFirstName);
		js.executeScript("arguments[0].value='"+crewlastNameValue+"';", crewLastName);		
		js.executeScript("arguments[0].value='"+crewRankValue+"';", crewRank);
		waitTime(9000);
		selectByIndex(costCenter, Integer.parseInt(costCenterValue), "Create Adhoc Allowance Request Page -> Costcenter Dropdown");
		waitTime(2000);
		selectByIndex(department, Integer.parseInt(departmentValue), "Create Adhoc Allowance Request Page -> Department dropdown");
		waitTime(2000);
		js.executeScript("arguments[0].value='"+paymentAmountValue+"';", paymentAmount);
		js.executeScript("arguments[0].value='"+commentsValue+"';", comments);
		clickOn(submitRequest, "Create Adhoc Allowance Request Page -> Submit Button");
		if(warningPopUp.isDisplayed())
			clickOn(confirmBtn, "Create Adhoc Allowance Request Page -> Confirm Button");
		waitForElementVisibility(savePopUp);
		String allowanceId=requestIdMessage.getText();
		String requestId=allowanceId.substring(Math.max(0, allowanceId.length() - 5));
		System.out.println(requestId);		
		clickOn(okLinkInSavePopUp, "Create Adhoc Allowance Request Page -> Ok LinkInSavePopUpWindow");
		return requestId;
				
	}
	
	
	
}
