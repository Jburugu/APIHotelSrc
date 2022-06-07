package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ConfirmReservationBooking extends BasePage{
	
	@FindBy(id = "confirmCode0")
	private WebElement confirmationCode;
	
	@FindBy(id = "buttonFinish")
	private WebElement finishBtn;
	
	@FindBy(id = "alertOK")
	
	public EventFiringWebDriver driver=null;
	public Page_ConfirmReservationBooking(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterConfirmationCode(String confirmationCode){
		typeInText(this.confirmationCode, confirmationCode);
	}
	
	public void clickFinishBtn(){
		clickOn(finishBtn);
		
	}
}
