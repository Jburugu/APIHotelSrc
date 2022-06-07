package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AccountManagement_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[contains(text(), 'Change Password')]")
	private WebElement changePasswordTab;
	
	@FindBy(xpath = "//*[contains(text(), 'Change Email')]")
	private WebElement changeEmailTab;
	
	@FindBy(id = "tabView:changePassword:currentPassword")
	private WebElement oldPassword;
	
	@FindBy(id = "tabView:changePassword:newPass")
	private WebElement newPassword;
	
	@FindBy(id = "tabView:changePassword:confirmPassword")
	private WebElement confirmNewPassword;
	
	@FindBy(id = "tabView:changePassword:changePwdConfirmBtn")
	private WebElement pwdChangeSaveBtn;
	
	@FindBy(className = "ui-messages-info-summary")
	private WebElement msgInfo;
	
	@FindBy(id = "tabView:changeEmail:email")
	private WebElement newEmailId;
	
	@FindBy(id = "tabView:changeEmail:button")
	private WebElement emailChangeSaveBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_AccountManagement_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void changePassword(String oldPassword, String newPassword){
		clickOn(changePasswordTab, "AccountManagementPage -> ChangePassword Tab");
		waitForElementToDisappear(spinner);
		typeInText(this.oldPassword, oldPassword, "AccountManagementPage -> ChangePassword Tab -> Enter Old Password");
		typeInText(this.newPassword, newPassword, "AccountManagementPage -> ChangePassword Tab -> Enter New Password");
		this.newPassword.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		typeInText(this.confirmNewPassword, newPassword, "AccountManagementPage -> ChangePassword Tab -> Confirm Password");
		this.confirmNewPassword.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		clickOn(pwdChangeSaveBtn, "AccountManagementPage -> ChangePassword Tab -> Save btn");
		waitForElementToDisappear(spinner);
		assertTrue(msgInfo.getText().equals("Password has been changed successfully"), "Password changed msg not displayed!");
	}
	
	public void changeEmail(String newEmailId){
		clickOn(changeEmailTab, "AccountManagementPage -> Change Email Tab");
		waitForElementToDisappear(spinner);
		typeInText(this.newEmailId, newEmailId, "AccountManagementPage -> Change Email Tab -> New Email Address");
		clickOn(emailChangeSaveBtn, "AccountManagementPage -> Change Email Tab -> Save btn");
		waitForElementToDisappear(spinner);
		assertTrue(msgInfo.getText().equals("Email Address changed successfully"), "Email changed msg not displayed!");
	}
	
	
	
	
}
