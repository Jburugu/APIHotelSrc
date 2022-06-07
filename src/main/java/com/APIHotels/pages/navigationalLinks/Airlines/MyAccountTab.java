package com.APIHotels.pages.navigationalLinks.Airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class MyAccountTab extends BasePage{

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = ".//*[text()='My Account']")
	public WebElement myaccountLink;

	@FindBy(how = How.ID, using = "iconeditprofile")
	public WebElement editProfileLink;
	
	public MyAccountTab(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnEditProfileLink(){
		clickOn(myaccountLink);
		unExpectedAcceptAlert();
		clickOn(editProfileLink);
	}
}
