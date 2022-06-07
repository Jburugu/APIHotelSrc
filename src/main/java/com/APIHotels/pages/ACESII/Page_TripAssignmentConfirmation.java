package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_TripAssignmentConfirmation extends BasePage {

	@FindBy(id = "rateId0")
	public WebElement rate;

	@FindBy(id = "isTaxIncludedNo")
	public WebElement isTaxIncludedNo;

	@FindBy(id = "isTaxIncludedYes")
	public WebElement isTaxIncludedYes;
	
	@FindBy(id="alertOK")
	public WebElement continueAlert;

	@FindBy(xpath = "//select[contains(@id,'resvPayMethod')]")
	public WebElement billingMethod;

	@FindBy(id = "buttonFinish")
	public WebElement finishBtn;
	
	@FindBy(xpath ="//div[@id='AlertBox']//button[@id='alertOK']")
	public WebElement reservationBookedAlert;
	
	@FindBy(id = "apRateId")
	public WebElement gtRate;
	
	@FindBy(xpath = "//input[contains(@id,'frmHtRateId')]")
	public WebElement fromGTRate;
	
	@FindBy(xpath = "//input[contains(@id,'gtRateId')]")
	public WebElement toGTRate;
	
	@FindBy(xpath = ".//div[contains(@id,'tab')]")
	public List<WebElement> hotelProviderTabs;

	public EventFiringWebDriver driver = null;
	
	String hotelName = "//b[2]";
	
	public Page_TripAssignmentConfirmation(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void isTaxIncludedAndEnterRate(String rateValue,String isTaxIncluded) {
		typeInText(rate, rateValue);
		if (isTaxIncluded == "Yes") {
			clickOn(isTaxIncludedYes);
		} else
			clickOn(isTaxIncludedNo);
		clickOn(continueAlert);
	}
	
	public void selectBillingMethod(String billingMethodValue) {
		selectByIndex(billingMethod, Integer.parseInt(billingMethodValue));
	}
	
	public void clickFinishButton(){
		clickOn(finishBtn);
		clickOn(continueAlert);
		clickOn(reservationBookedAlert);
	}
	public void clickOnFinishButtonAndReservationALert(){
		clickOn(finishBtn);
		clickOn(reservationBookedAlert);
	}
	
	public String getHotelName() {
		String hotel = findElementByXpath(hotelName).getText().trim();
		return hotel;
	}
	public void enterRateGtReservation(String rateValue){
		typeInText(gtRate, rateValue);
	}
	
	public void enterFromAndToGTRates(String rate) {
		if (fromGTRate.isDisplayed())
			typeInText(fromGTRate, rate);
		if (toGTRate.isDisplayed())
			typeInText(toGTRate, rate);
	}

	public void enterBillingMethodForToHotelAndFromHotelGtProviders(String billingMethodValue) {
		int providersCount = hotelProviderTabs.size();
		for (int i = 0; i < providersCount; i++) {
			waitTime(3000);
			clickOn(findElementById("tab" + i));
			waitTime(3000);
			selectByIndex(findElementById("vendorContactLst[" + i + "].resvPayMethod"),
					Integer.parseInt(billingMethodValue));
			waitTime(3000);
		}
	}
}
