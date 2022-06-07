package com.APIHotels.pages.ACES3;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AccountConfirmation_Aces3Supplier extends BasePage{
	
	@FindBy(id = "confirmUserAccountForm:newPassTxtId")
	private WebElement newPassword;
	
	@FindBy(id = "confirmUserAccountForm:confPassTxtId")
	private WebElement confirmPassword;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[contains(text(), 'Submit')]")
	private WebElement submitBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_AccountConfirmation_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void accountConfirmation(String newPassword){
		typeInText(this.newPassword, newPassword, "AccountConfirmationPage -> New Password");
		this.newPassword.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		typeInText(this.confirmPassword, newPassword, "AccountConfirmationPage -> Confirm Password");
		this.confirmPassword.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		clickOn(submitBtn, "AccountConfirmationPage -> Submit btn");
		waitForElementToDisappear(spinner);
	}
}
