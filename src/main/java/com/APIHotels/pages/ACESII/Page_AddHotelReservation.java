package com.APIHotels.pages.ACESII;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_AddHotelReservation extends BasePage {

	@FindBy(css = "#addHotelReserv > a")
	private WebElement addHotelReservationLink;

	@FindBy(id = "locCd")
	private WebElement city;

	@FindBy(xpath = "//*[@name = 'buttonFlag' and @value = 'Get Vendors']")
	private WebElement getVendorsBtn;

	private String adhocTypeXpath1 = "//*[@name = 'adHocType' and @value = '";
	private String adhocTypeXpath2 = "']";

	@FindBy(id = "supplierSelect")
	private WebElement supplier;

	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;

	@FindBy(id = "alertCancel")
	private WebElement alertCancelBtn;

	@FindBy(id = "hotelContractStartDate")
	private WebElement contractStartDate;

	@FindBy(id = "hotelContractEndDate")
	private WebElement contractEndDate;

	@FindBy(id = "hotelRoom")
	private WebElement rooms;

	@FindBy(id = "layStartDummy")
	private WebElement layoverStartTime;

	@FindBy(id = "layEndDummy")
	private WebElement layoverEndTime;

	@FindBy(id = "hotelLocCode")
	private WebElement layoverLocation;

	@FindBy(id = "useManualRateId")
	private WebElement dailyRateIndicator;

	@FindBy(id = "hotelDailyRate")
	private WebElement dailyRate;

	@FindBy(id = "hotelReasonCode")
	private WebElement reasonCode;

	@FindBy(xpath = "//*[@name = 'buttonFlag' and @value = 'Add Reservation']")
	private WebElement addReservationBtn;

	@FindBy(xpath = "//*[@name = 'buttonFlag' and @value = 'Cancel']")
	private WebElement cancelBtn;

	@FindBy(id = "adHocTypeHd")
	public WebElement hardAdhocType;

	@FindBy(id = "adHocTypeSt")
	public WebElement softAdhocType;

	@FindBy(id = "confirmCode0")
	public WebElement confirmationNumber;

	@FindBy(xpath = "//div[@class='Aces_Table']//div[2]//input[@value='Next']")
	private WebElement btn_Next;
	
	@FindBy (xpath="//body//div[@class='Aces_Table']//div//input[@value='Finish']")
	private WebElement btn_Finish;
	
	@FindBy(id="resvPayMethod")
	private WebElement select_PaymentMethod;

	public BasePage basePage;

	public EventFiringWebDriver driver = null;

	public Page_AddHotelReservation(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAddHotelReservationLink() throws Exception {
		clickOn(addHotelReservationLink);
	}

	public void addHotelReservation(String city, String supplier, String adhocType, String contractStartDate,
			String contractEndDate, String rooms, String layoverStartTime, String layoverEndTime, String dailyRate,
			int reasonCode) throws Exception {
		typeInText(this.city, city);
		clickOn(this.getVendorsBtn);
		selectByVisibleText(this.supplier, supplier);
		clickOn(findElementByXpath(adhocTypeXpath1 + adhocType + adhocTypeXpath2));
		typeInText(this.contractStartDate, contractStartDate);
		typeInText(this.contractEndDate, contractEndDate);
		typeInText(this.rooms, rooms);
		typeInText(this.layoverStartTime, layoverStartTime);
		typeInText(this.layoverEndTime, layoverEndTime);
		waitForElementVisibility(this.layoverLocation);
		clickOn(dailyRateIndicator);
		typeInText(this.dailyRate, dailyRate);
		selectByIndex(this.reasonCode, reasonCode);
		waitForElementVisibility(this.cancelBtn);
		clickOn(this.addReservationBtn);
		waitForElementVisibility(this.alertCancelBtn);
		clickOn(this.alertOkBtn);

	}

	public void addHotelReservation(String cityValue, String supplierValue, String adhocType, String startDateValue,
			String endDateValue, String roomsValue, String startTime, String endTime, String rateValue,
			String reasonCodeValue) {
		typeInText(city, cityValue, "Hotel Reservation Page -> City textbox");
		clickOn(getVendorsBtn ,"Hotel Reservation Page -> Get Vendors button");
		selectByVisibleText(supplier, supplierValue,"Hotel Reservation Page -> Supplier dropdown");
		if (adhocType.equals("Hard")) {
			clickOn(hardAdhocType, "Hotel Reservation Page -> Adhoc Type Hard radio button");
		} else
			clickOn(softAdhocType,"Hotel Reservation Page -> Adhoc Type Soft radio button");
		typeInText(contractStartDate, startDateValue, "Hotel Reservation Page -> Contract Start Date");
		typeInText(contractEndDate, endDateValue,"Hotel Reservation Page -> Contract End Date");
		typeInText(rooms, roomsValue, "Hotel Reservation Page -> Room textbox");
		typeInText(layoverStartTime, startTime,"Hotel Reservation Page -> Layover Start time");
		typeInText(layoverEndTime, endTime,"Hotel Reservation Page -> Layover End time");
		clickOn(dailyRateIndicator,"Hotel Reservation Page -> Daily Rate checkbox");
		typeInText(dailyRate, rateValue, "Hotel Reservation Page -> Daily Rate textbox");
		selectByValue(reasonCode, reasonCodeValue, "Hotel Reservation Page -> Reason Code dropdown");
		clickOn(addReservationBtn, "Hotel Reservation Page -> Add Reservation button");
		clickOn(alertOkBtn, "Hotel Reservation Page -> Reservation Alert OK button");
	}
	
	public void bookReservation(String cityValue, String supplierValue, String adhocType, String startDateValue,
			String endDateValue, String roomsValue, String startTime, String endTime, String rateValue,
			String reasonCodeValue) {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Hotel Reservation started");
		addHotelReservation(cityValue, supplierValue, adhocType, startDateValue, endDateValue, roomsValue, startTime, endTime, rateValue, reasonCodeValue);
		typeInText(confirmationNumber, "Booked","Confirmation Number textbox");
		confirmationNumber.sendKeys(Keys.TAB);
		selectByVisibleText(select_PaymentMethod, "Direct Billing","Payment Method select");
		clickOn(btn_Next, "Hotel Reservation Page -> Next button");
		clickOn(btn_Finish, "Hotel Reservation Page -> Finish button");
		unExpectedAcceptAlert();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Hotel Reservation completed successfully");
	}
	}
