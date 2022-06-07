package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class AirlinesLoginPage extends BasePage{

	public EventFiringWebDriver driver=null;
	
	@FindBy(id = "loginForm:usernameField:username")
	public WebElement airlineUserName;

	@FindBy(id = "loginForm:passwordField:password")
	public WebElement airlinePassword;

	@FindBy(id = "loginForm:login")
	public WebElement airineLogin;
	
	@FindBy(how= How.ID,using="iconnormlogout")
	private WebElement logoutButton;

	public AirlinesLoginPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToAirlines(String username, String password) {

		typeInText(airlineUserName, username, "LoginPage Airlines -> Username");
		typeInText(airlinePassword, password, "LoginPage Airlines-> Password");
		clickOn(airineLogin, "LoginPage -> Login Button");
	}
	
	public void clickOnLogoutButton(){
		clickOn(logoutButton, "HomePage -> Logout Menu");
	}

}
