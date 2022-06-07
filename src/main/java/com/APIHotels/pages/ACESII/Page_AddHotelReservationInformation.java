package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AddHotelReservationInformation extends BasePage{
	
	@FindBy(id = "confirmCode0")
	private WebElement confirmationNumber;
	
	@FindBy(id = "addNotes0")
	private WebElement apiNotes;
	
	@FindBy(name = "creditCardDetails.commissionable")
	private WebElement commissionableIndicator;
	
	@FindBy(id = "resvPayMethod")
	private WebElement billingMethod;
	
	@FindBy(id = "printOnlyId0")
	private WebElement printOnlyIndicator;
	
	@FindBy(id = "addButton0")
	private WebElement notificationOptionsAddBtn;
	
	@FindBy(id = "sendFaxId00")
	private WebElement faxIndicator;
	
	@FindBy(id = "faxNumberId00")
	private WebElement faxNumber;
	
	@FindBy(id = "sendEmailId00")
	private WebElement emailIndicator;
	
	@FindBy(id = "emailAddressId00")
	private WebElement emailAddress;
	
	@FindBy(xpath = "//*[@id = 'buttonFlag' and @value = 'Previous']")
	private WebElement previousBtn;
	
	@FindBy(xpath = "//*[@id = 'buttonFlag' and @value ='Next']")
	private WebElement nextBtn;
	
	@FindBy(id = "hotelVendorComments")
	private WebElement commentsForHotelVendor;
	
	@FindBy(id = "roomRow0")
	private WebElement confirmationNo;
	
	@FindBy(xpath = "//*[@id = 'buttonFlag' and @value = 'Preview']")
	private WebElement previewBtn;
	
	@FindBy(xpath = "//*[@id = 'buttonFlag' and @value = 'Finish']")
	private WebElement finishBtn;
		
	public BasePage basePage;
	
	public EventFiringWebDriver driver=null;

	public Page_AddHotelReservationInformation(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addHotelReservationInformation(String confirmationNumber, String billingMethod) throws Exception{
		waitForElementVisibility(this.confirmationNumber);
		typeInText(this.confirmationNumber, confirmationNumber);
		waitForElementVisibility(apiNotes);
		waitForElementVisibility(commissionableIndicator);
		selectByVisibleText(this.billingMethod, billingMethod);
		waitForElementVisibility(printOnlyIndicator);
		waitForElementVisibility(notificationOptionsAddBtn);
		waitForElementVisibility(faxIndicator);
		waitForElementVisibility(emailIndicator);
		waitForElementVisibility(emailAddress);
		waitForElementVisibility(previousBtn);
		clickOn(nextBtn);
		waitForElementVisibility(commentsForHotelVendor);
		waitForElementVisibility(confirmationNo);
		waitForElementVisibility(previewBtn);
		waitForElementVisibility(finishBtn);
		//clickOn(finishBtn);
		//alertAccept();
	}
	
	public void addHotelReservationInformation(String confirmationNumberValue,String notesValue,String billingValue,String vendorComments) {
		try{Thread.sleep(4000);}catch(Exception e){}
		typeInText(confirmationNumber, confirmationNumberValue);
		typeInText(apiNotes, notesValue);
		selectByValue(billingMethod, billingValue);
		clickOn(nextBtn);
		typeInText(commentsForHotelVendor, vendorComments);
		clickOn(finishBtn);
		unExpectedAcceptAlert();
	}
	
	
}
