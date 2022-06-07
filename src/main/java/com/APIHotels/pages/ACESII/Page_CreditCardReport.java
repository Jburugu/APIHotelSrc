package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreditCardReport extends BasePage{
	
	public EventFiringWebDriver driver=null;
	
	@FindBy(xpath = ".//*[@id='ccReport']/a")
	public WebElement creditCardReportLink;
	
	@FindBy(id="tenantId")
	public WebElement tenantIDMultiselect;
	
	@FindBy(id = "loc")
	public WebElement location;
	
	@FindBy(css = "#ccStartDate")
	public WebElement ccStartDate;
	
	@FindBy(css = "#ccEndDate")
	public WebElement ccEndDate;
	
	@FindBy(css = "input[value='Fetch Suppliers']")
	public WebElement fetchSuppliersBtn;
	
	@FindBy(xpath = ".//*[@id='supplier']")
	public WebElement supplierCCR;
	
	@FindBy(css = "select[name='cardType']")
	public WebElement cardType;
	
	@FindBy(linkText = "Trip #")
	public WebElement ccTrip;

	@FindBy(linkText = "Loc")
	public WebElement ccLoc;

	@FindBy(linkText = "Service Type")
	public WebElement ccServiceType;

	@FindBy(linkText = "Inbound Flight")
	public WebElement ccInboundFlight;

	@FindBy(linkText = "Outbound Flight")
	public WebElement ccOutboundFlight;

	@FindBy(linkText = "Checkin Date")
	public WebElement ccCheckinDate;

	@FindBy(linkText = "Checkout Date")
	public WebElement ccCheckoutDate;

	@FindBy(linkText = "Crew / Guest Name")
	public WebElement ccCrewGuestName;

	@FindBy(linkText = "Supplier")
	public WebElement ccSupplier;

	@FindBy(linkText = "Status")
	public WebElement ccStatus;

	@FindBy(linkText = "Time@Hotel")
	public WebElement ccTimeAtHotel;

	@FindBy(linkText = "Room Rate")
	public WebElement ccRoomRate;

	@FindBy(linkText = "# Rooms")
	public WebElement ccRooms;

	@FindBy(linkText = "# Nights")
	public WebElement ccNights;

	@FindBy(linkText = "Total Amount")
	public WebElement ccTotalAmount;

	@FindBy(linkText = "Comm.")
	public WebElement ccComm;

	@FindBy(linkText = "Card Type")
	public WebElement ccCardType;

	@FindBy(linkText = "Card Number")
	public WebElement ccCardNumber;
	
	@FindBy(css = "input[value='Retrieve']")
	public WebElement retrieveBtn;
	
	
	public Page_CreditCardReport(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void creditCardReport(String tenantJetBlue, String cityJFK, String indexValue1) throws Exception{
		clickOn(creditCardReportLink);
		selectByVisibleText(tenantIDMultiselect, tenantJetBlue);
		typeInText(location, cityJFK);
		typeInText(ccStartDate, currentDate());
		typeInText(ccEndDate, currentDate());
		clickOn(fetchSuppliersBtn);
		waitForElementVisibility(supplierCCR);
		Integer supplierValuePars = Integer.parseInt(indexValue1);
		selectByIndex(supplierCCR, supplierValuePars);	
		selectByIndex(cardType, supplierValuePars);
		waitForElementVisibility(retrieveBtn);
		waitForElementVisibility(ccTrip);
		waitForElementVisibility(ccLoc);
		waitForElementVisibility(ccServiceType);
		waitForElementVisibility(ccInboundFlight);
		waitForElementVisibility(ccOutboundFlight);
		waitForElementVisibility(ccCheckinDate);
		waitForElementVisibility(ccCheckoutDate);
		waitForElementVisibility(ccCrewGuestName);
		waitForElementVisibility(ccSupplier);
		waitForElementVisibility(ccStatus);
		waitForElementVisibility(ccTimeAtHotel);
		waitForElementVisibility(ccRoomRate);
		waitForElementVisibility(ccRooms);
		waitForElementVisibility(ccNights);
		waitForElementVisibility(ccTotalAmount);
		waitForElementVisibility(ccComm);
		waitForElementVisibility(ccCardNumber);
		
	}

}
