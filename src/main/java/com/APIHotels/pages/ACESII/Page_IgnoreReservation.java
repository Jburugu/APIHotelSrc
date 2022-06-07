package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_IgnoreReservation extends BasePage{
	
	String reasonXpath1 = "//*[@name = 'reason' and @value = '";
	String reasonXpath2 = "']";
	
	@FindBy(xpath = "//*[@value='Add']/parent::div/following-sibling::div//button[@value='OK']")
	private WebElement OkBtn;
	
	@FindBy(id = "alertOK")
	private WebElement alert_OkBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_IgnoreReservation(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterReason(String reason){
		clickOn(findElementByXpath(reasonXpath1+reason+reasonXpath2));
	}
	
	public void clickOk(){
		clickOn(OkBtn);
		clickOn(alert_OkBtn);
	}

}
