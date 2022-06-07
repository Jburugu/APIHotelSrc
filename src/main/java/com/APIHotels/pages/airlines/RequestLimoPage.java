package com.APIHotels.pages.airlines;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class RequestLimoPage extends BasePage {

	protected EventFiringWebDriver driver=null;
	// OPERATIONS -- REQUEST LIMO

	@FindBy(how = How.CSS, using = "select[name*='gtTenantList']")
	public WebElement company;

	@FindBy(how = How.ID, using = "bizTravelForm:pickupLocationArea:pickupLocCd")
	public WebElement pickUplocation;

	@FindBy(how = How.CSS, using = "select[id$='bizTravelForm:pickupSupplierArea:pickupSupplier']")
	public WebElement pickUpDetail;

	@FindBy(how = How.CSS, using = "input[id*='pickupDateInputDate']")
	public WebElement pickUpDate;

	@FindBy(how = How.CSS, using = "img[id*='pickupDatePopupButton']")
	public WebElement pickUpDateCalendar;

	@FindBy(how = How.CSS, using = "input[id$='pickupTime']")
	public WebElement pickUpTime;

	@FindBy(how = How.ID, using = "bizTravelForm:dropoffLocationArea:dropoffLocCd")
	public WebElement dropOffLocation;

	@FindBy(how = How.ID, using = "bizTravelForm:dropoffSupplierArea:dropoffSupplier")
	public WebElement dropOffDetail;

	@FindBy(how = How.CSS, using = "input[id*='dropoffDateInputDate']")
	public WebElement dropOffDate;

	@FindBy(how = How.CSS, using = "img[id*='dropoffDatePopupButton']")
	public WebElement dropOffDateCalendar;

	@FindBy(how = How.CSS, using = "input[id*='dropoffTime']")
	public WebElement dropOffTime;

	@FindBy(how = How.ID, using = "bizTravelForm:limoEmail:limoNotificationEmail")
	public WebElement additionalEmailAddress;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:empNbrArea:limoEmployeeNbr'] | .//*[@id='bizTravelForm:guestInfoTable:0:employeeNbrArea:employeeNbr']")
	public WebElement empId;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'limoEmpName')] | //input[contains(@id,'name')]")
	public WebElement limoEmpName;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'limoEmpDept')]| //select[contains(@id,'department')]")
	public WebElement limoEmpDepartment;

	@FindBy(how = How.CSS, using = "input[id*='limoNbrRiders']")
	public WebElement noOfPassengers;

	@FindBy(how = How.CSS, using = "textarea[id*='limoNotes']")
	public WebElement notes;

	@FindBy(how = How.CSS, using = "input[value*='Save']")
	public WebElement saveButton;

	// AeroMexico
    @FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountPageGtPanel']//input[@type='checkbox']")
    public WebElement profilaxis;
 
    @FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[2]//select")
    public WebElement businessUnit;
 
    @FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[3]//select")
    public WebElement aeroMexicoCostCenter;
 
    @FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[4]//select")
    public WebElement equipment;
 
    @FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[5]//select")
    public WebElement aeroMexicoLocation;
    
	// AirNewzealand
	@FindBy(how = How.XPATH, using = "//select[contains(@id,'account')]")
	public WebElement costCenterOrReason;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'account')]/option[text()='Flight Crew - Charter']")
	public WebElement costCenterFlightCrewCharter;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'businessLine')]")
	public WebElement airCraftTypeOrLineOfBusiness;

	@FindBy(how = How.CSS, using = "select[id$='reason']")
	public WebElement reason;

	// HawaiianArilines
	@FindBy(how = How.CSS, using = "input[id$='pickupPlace']")
	public WebElement pickupPlace;

	@FindBy(how = How.CSS, using = "input[id$='dropoffPlace']")
	public WebElement dropOffPlace;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:pickupSupplierArea:pickupSupplier']/option[text()='Terminal']")
	public WebElement pickupSupplierTerminalDropdownValue;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:dropoffSupplierArea:dropoffSupplier']/option[text()='Terminal']")
	public WebElement dropOffDetailTerminalDropdownValue;

	// Endeavor
	@FindBy(how = How.CSS, using = "input[title$='emailNotification']")
	public WebElement doNotSendEmailNotification;

	@FindBy(how = How.CSS, using = "input[title^='VIP/Executive']")
	public WebElement vipExecutiveReservation;

	@FindBy(how = How.CSS, using = "input[id$='accountPageGtActivityLocCd']")
	public WebElement location;
	
	@FindBy(xpath = "//*[contains(@id,'arrivalAlCode')]")
	private WebElement pickUpAPCode;
	
	@FindBy(id = "bizTravelForm:pickupAirlineCodeArea:pickupFlightNbr")
	private WebElement pickUpFlightNbr;
	
	@FindBy(id = "bizTravelForm:departureAlCodeArea:departureAlCode")
	private WebElement dropOffAPCode;
	
	@FindBy(xpath = "//*[contains(@id,'dropoffFlightNbr')]")
	private WebElement dropOffFlightNbr;
	
	@FindBy(how = How.ID, using = "saveBizPanelHeader")
	public WebElement savePanelHeader;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='saveBizPanelMessages']/dt/span")
	public WebElement processCompletedMessage;
	
	@FindBy(xpath ="//*[@id='saveBizGrid']/tfoot/tr/td/span/a")
	public WebElement okBtn;
	
	@FindBy(how = How.ID, using = "bizTravelForm:CompanyAreaaccountPageGt:companyId")
	public WebElement virginAustraliaCompany;

	@FindBy(how = How.ID, using = "bizTravelForm:organisationAreaaccountPageGt:organisationId")
	public WebElement organisation;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'accountId')]")
	public WebElement account;

	@FindBy(how = How.ID, using = "bizTravelForm:platformaccountPageGt:platformName")
	public WebElement platform;

	@FindBy(how = How.ID, using = "bizTravelForm:locationIdAreaaccountPageGt:locationId")
	public WebElement virginAustraliaLocation;

	// String costCenterValue = "Flight Crew - Charter";

	public RequestLimoPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void requestLimoPageUnderBT(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String empNameValue, String notesValue) {

		typeInText(pickUplocation, locationValue);
		typeInText(pickUpDate, currentDate());
		typeInText(pickUpTime, pickUpTimeValue);
		waitForElementVisibility(pickupSupplierTerminalDropdownValue);
		selectByVisibleText(pickUpDetail, pickUpDetailValue);
		typeInText(dropOffLocation, dropOffLocValue);
		waitForElementVisibility(dropOffDetailTerminalDropdownValue);
		selectByVisibleText(dropOffDetail, dropOffDetailValue);
		waitForElementVisibility(dropOffDateCalendar);
		typeInText(dropOffDate, currentDate());
		typeInText(dropOffTime, dropOffTimeValue);
		typeInText(additionalEmailAddress, additionalEmailAddressValue);
		typeInText(empId, empIdValue);
		waitForElementVisibility(limoEmpName);
		typeInText(notes, notesValue);

	}
	
	public void requestLimoPageUnderBT(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue) {
		typeInText(pickUplocation, locationValue);
		typeInText(pickUpDate, currentDate());
		typeInText(pickUpTime, pickUpTimeValue);
		typeInText(dropOffLocation, dropOffLocValue);
		typeInText(dropOffDate, currentDatePlus(1));
		waitForElementVisibility(dropOffDateCalendar);
		waitForElementVisibility(pickupSupplierTerminalDropdownValue);
		selectByVisibleText(pickUpDetail, pickUpDetailValue);
		typeInText(dropOffTime, dropOffTimeValue);
		addAdditionalEmailIds(additionalEmailAddressValue);
		waitForElementVisibility(dropOffDetailTerminalDropdownValue);
		selectByVisibleText(dropOffDetail, dropOffDetailValue);
		typeInText(empId, empIdValue);
		empId.sendKeys(Keys.TAB);
		try{Thread.sleep(3000);}catch(Exception e){}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'limoEmpName')] | //input[contains(@id,'name')]")));
		waitForElementTextVisibility(limoEmpName, "value");
	}
	
	public void addEmpName(String empName){
		typeInText(limoEmpName, empName);
	}
	
	public void addAdditionalEmailIds(String additionalEmailAddressValue){
		for(String email : additionalEmailAddressValue.split(";")){
			additionalEmailAddress.sendKeys(email);
			additionalEmailAddress.sendKeys(Keys.ENTER);
		}
	}
	
	public void requestLimoPageUnderBTReg(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String empNameValue, String notesValue) {
		requestLimoPageUnderBT(locationValue, pickUpTimeValue, pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, empNameValue, notesValue);
		clickSaveBtn();
	}
	
	public void pickUpPlace(String pickupPlaceValue){
		 waitForElementVisibility(pickupPlace);
		typeInText(pickupPlace, pickupPlaceValue);
	}
	
	public void dropOffPlace(String dropOffPlaceValue){
		typeInText(dropOffPlace, dropOffPlaceValue);
	}
	
	public void pickUpFlightDetails(String pickUpAPCodeValue, String pickUpFlightNumber){
		typeInText(pickUpAPCode, pickUpAPCodeValue);
		typeInText(pickUpFlightNbr, pickUpFlightNumber);
	}
	
	public void dropOffFlightDetails(String dropOffAPCodeValue, String dropOffFlightNumber){
		typeInText(dropOffAPCode, dropOffAPCodeValue);
		typeInText(dropOffFlightNbr, dropOffFlightNumber);
	}
	
	
	public void noOfPassengers(String passengers) {
		typeInText(noOfPassengers, passengers);
		Assert.assertEquals(noOfPassengers.getAttribute("value"), passengers, "passengers mismatch");
	}

	public void airCraftType(String aircraftTypeValue) {
		waitForElementVisibility(airCraftTypeOrLineOfBusiness);
		int aircraftTypeVal = Integer.parseInt(aircraftTypeValue);
		selectByIndex(airCraftTypeOrLineOfBusiness, aircraftTypeVal);
	}

	public void costCenter(String costCenterValue) {
		waitForElementVisibility(costCenterOrReason);
		int costCenterVal = Integer.parseInt(costCenterValue);
		selectByIndex(costCenterOrReason, costCenterVal);
	}

	public void company(String companyValue) {
		selectByValue(company, companyValue);
	}

	public void empDepartment(String empDepartment) {
		int empDepartmentVal = Integer.parseInt(empDepartment);
		selectByIndex(limoEmpDepartment, empDepartmentVal);
		
	}

	public void reasonElementEnvoyBTReqLimo(String resonValue) {
		int resonVal = Integer.parseInt(resonValue);
		selectByIndex(reason, resonVal);

	}

	public void verifySaveBtn() {
		waitForElementVisibility(saveButton);

	}

	public void clickDoNotSendEmailNotification() {
		clickOn(doNotSendEmailNotification);
	}

	public void clickVipExecutiveReservation() {
		clickOn(vipExecutiveReservation);
	}

	public void location(String locationVal) {
		typeInText(location, locationVal);
	}
	
	public void addAdditionalPickUpDetails(String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickupPlaceValue){
		
		switch(pickUpDetailValue){
		case "Flight":
			pickUpFlightDetails(pickUpAPCodeValue, pickUpFlightNumber);
			break;
		case "Terminal":
			pickUpPlace(pickupPlaceValue);
			break;
		}
		
	}
	
	public void addAdditionalDropOffDetails(String dropOffDetailValue, String dropOffAPCodeValue, String dropOffFlightNumber, String dropOffPlaceValue){
		switch(dropOffDetailValue){
		case "Flight":
			pickUpFlightDetails(dropOffAPCodeValue, dropOffFlightNumber);
			break;
		case "Terminal":
			pickUpPlace(dropOffPlaceValue);
			break;
		}
	}
	
	// AeroMexico
    public void businessUnit(String businessUnitValue) {
        int businessUnitVal = Integer.parseInt(businessUnitValue);
        selectByIndex(businessUnit, businessUnitVal);
    }
 
    public void aeroMexicocostCenter(String aeroMexicocostCenterValue) {
        int aeroMexicocostCenterVal = Integer.parseInt(aeroMexicocostCenterValue);
        selectByIndex(aeroMexicoCostCenter, aeroMexicocostCenterVal);
    }
 
    public void equipment(String equipmentValue) {
        int equipmentVal = Integer.parseInt(equipmentValue);
        selectByIndex(equipment, equipmentVal);
    }
 
    public void aeroMexicoLocation(String aeroMexicoLocationValue) {
        int aeroMexicoLocationVal = Integer.parseInt(aeroMexicoLocationValue);
        selectByIndex(aeroMexicoLocation, aeroMexicoLocationVal);
    }
    
    public void profilaxis()
    {
        clickOn(profilaxis);
    }
 
    public void aeroMexicoaccountInfo(String businessUnitValue, String aeroMexicocostCenterValue, String equipmentValue,
            String aeroMexicoLocationValue) {
        clickOn(profilaxis);
        businessUnit(businessUnitValue);
        aeroMexicocostCenter(aeroMexicocostCenterValue);
        equipment(equipmentValue);
        aeroMexicoLocation(aeroMexicoLocationValue);
 
    }
    
	public void clickSaveBtn(){
		clickOn(saveButton);
	}
	
	public String processingRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		String saveCompleteMessage = processCompletedMessage.getText();
		String reservationId = saveCompleteMessage.substring(Math.max(0, saveCompleteMessage.length() - 6));
		System.out.println("reservationId " + reservationId);
		clickOn(okBtn);
		return reservationId;
	}
	
	public void verifyElementsVisibility() {
		waitForElementVisibility(virginAustraliaCompany);
		waitForElementVisibility(organisation);
		waitForElementVisibility(account);
		waitForElementVisibility(platform);
		waitForElementVisibility(virginAustraliaLocation);
	}
	
	public String requestLimoPageUnderBTBC(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String dropOffLocValue,  String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String empNameValue, String noOfPassengersValue, String notesValue,String costCenterValue, String reasonValue) throws Exception {

		typeInText(pickUplocation, locationValue);
		typeInText(pickUpDate, currentDatePlus(1));
		typeInText(pickUpTime, pickUpTimeValue);
		waitForElementVisibility(pickupSupplierTerminalDropdownValue);
		selectByVisibleText(pickUpDetail, pickUpDetailValue);
		typeInText(dropOffLocation, dropOffLocValue);
		dropOffLocation.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		selectByVisibleText(dropOffDetail, dropOffDetailValue);
		typeInText(dropOffDate, currentDatePlus(1));
		typeInText(dropOffTime, dropOffTimeValue);
		waitForElementVisibility(dropOffDateCalendar);
		typeInText(dropOffDate, currentDatePlus(1));
		typeInText(dropOffTime, dropOffTimeValue);
		typeInText(additionalEmailAddress, additionalEmailAddressValue);
		typeInText(empId, empIdValue);
		empId.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		waitForElementVisibility(limoEmpName);
		typeInText(limoEmpName, empNameValue);
		typeInText(noOfPassengers, noOfPassengersValue);
		Thread.sleep(2000);
		selectByVisibleText(costCenterOrReason, costCenterValue);
		selectByVisibleText(reason, reasonValue);
		typeInText(notes, notesValue);
		clickSaveBtn();
		return currentDatePlus(1);
	
		

	}
	
	public String processingRequestBT() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String saveCompleteMessage = processCompletedMessage.getText();
		String reservationId = saveCompleteMessage.substring(Math.max(0, saveCompleteMessage.length() - 7));
		System.out.println("reservationId " + reservationId);
		clickOn(okBtn);
		return reservationId;
	}
	
	public String requestLimoPageUnderBTAF(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String dropOffLocValue,  String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String empNameValue, String noOfPassengersValue, String notesValue,String pickupPlaceValue, String dropOffPlaceValue) throws Exception {
		takeScreenshots();
		typeInText(pickUplocation, locationValue);
		typeInText(pickUpDate, currentDateFormatdMMyyyy());
		typeInText(pickUpTime, pickUpTimeValue);
		waitForElementVisibility(pickupSupplierTerminalDropdownValue);
		selectByVisibleText(pickUpDetail, pickUpDetailValue);
		typeInText(dropOffLocation, dropOffLocValue);
		dropOffLocation.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		selectByVisibleText(dropOffDetail, dropOffDetailValue);
		typeInText(dropOffDate, currentDateFormatdMMyyyy());
		typeInText(dropOffTime, dropOffTimeValue);
		waitForElementVisibility(dropOffDateCalendar);
		typeInText(dropOffDate, currentDateFormatdMMyyyy());
		typeInText(dropOffTime, dropOffTimeValue);
		typeInText(additionalEmailAddress, additionalEmailAddressValue);
		typeInText(empId, empIdValue);
		empId.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		waitForElementVisibility(limoEmpName);
		typeInText(limoEmpName, empNameValue);
		typeInText(noOfPassengers, noOfPassengersValue);
		Thread.sleep(2000);
		takeScreenshots();
//		selectByVisibleText(costCenterOrReason, costCenterValue);
//		selectByVisibleText(reason, reasonValue);
		typeInText(notes, notesValue);
			 waitForElementVisibility(pickupPlace);
			typeInText(pickupPlace, pickupPlaceValue);
			typeInText(dropOffPlace, dropOffPlaceValue);
			takeScreenshots();
		clickSaveBtn();
		return currentDatePlus(1);
	
		

	}

}
