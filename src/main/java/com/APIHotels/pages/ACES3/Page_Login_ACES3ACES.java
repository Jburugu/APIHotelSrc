package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Login_ACES3ACES extends BasePage {
	
	@FindBy(id="login:jusername")
	private WebElement acesIIIUsername;
	
	@FindBy(id="login:jpassword")
	private WebElement acesIIIPassword;
	
	@FindBy(xpath= "//span[contains(text(),'Login')]")
	private WebElement acesIIILoginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Forgot Username?')]")
	private WebElement forgotUsername;
	
	@FindBy(xpath="//a[contains(text(),'Forgot Password?')]")
	private WebElement forgotPassword;
	
	@FindBy(id="menuForm:loginCurrent")
	private WebElement loginlink;
	
	
	public EventFiringWebDriver driver=null;
	
	public Page_Login_ACES3ACES(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginToACESIII(String userName, String password){
		typeInText(acesIIIUsername, userName , "ACES 3 ACES Username Textbox");
		typeInText(acesIIIPassword, password, "ACES 3 ACES Password Textbox");
		clickOn(acesIIILoginBtn, "ACES 3 ACES Login Button");
	}
	
}
