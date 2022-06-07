package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Login_Aces3Supplier extends BasePage{
	
	@FindBy(id = "login:jusername")
	private WebElement aces3UserName;
	
	@FindBy(id = "login:jpassword")
	private WebElement aces3Password;
	
	@FindBy(xpath = "//button[contains(@id, 'login')]")
	private WebElement aces3LoginBtn;
	
	@FindBy(id = "termsAndCondition:termsAndConditionIdCheckBox")
	private WebElement iAgreeCheckBox;
	
	@FindBy(xpath = "//*[contains(text(), 'Continue')]")
	private WebElement continueBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	public EventFiringWebDriver driver=null;

	public Page_Login_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginToACES3(String userName, String password){
		typeInText(aces3UserName, userName, "LoginPage -> Username");
		typeInText(aces3Password, password, "LoginPage -> password");
		clickOn(aces3LoginBtn, "LoginPage -> Login button");
	}
	
	public void firstTimeUserTerms(){
		clickOn(iAgreeCheckBox, "FirstTimeUser -> AgreeTerms checkbox");
		waitForElementToDisappear(spinner);
		clickOn(continueBtn, "FirstTimeUser -> Continue button");
	}
}
