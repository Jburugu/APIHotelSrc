package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CancelReservation extends BasePage{
	
	@FindBy(id = "alertBody")
	private WebElement alertBody;
	
	@FindBy(id = "buttonCancel")
	private WebElement cancelBtn;
	
	@FindBy(id = "buttonFinish")
	private WebElement finishBtn;
	
	@FindBy(xpath = "//input[contains(@id,'confirmCode')]")
	private WebElement confirmationCode;
	
	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;
	
	public EventFiringWebDriver driver=null;
	public Page_CancelReservation(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterConfirmationNumber(String confirmationCode){
		typeInText(this.confirmationCode, confirmationCode);
		waitTime(3000);
	}
	
	public void clickFinishBtn(){
		clickOn(finishBtn);
		clickOn(alertOkBtn);
	}
	
	public void clickFinishBtn(String alertMsg){
		clickOn(finishBtn);
		assertTrue(alertBody.getText().contains(alertMsg), "Msg does not match");
		clickOn(alertOkBtn);
	}

}
