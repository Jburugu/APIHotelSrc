package com.APIHotels.pages.ACES3;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AccountManagement_Aces3Client extends BasePage {
	
	@FindBy(xpath="//div[@id='tabView']//li[1]")
	private WebElement editProfileTab;
	
	@FindBy(xpath="//div[@id='tabView']//li[2]")
	private WebElement changePasswordTab;

	/*@FindBy(xpath="//div[@id='tabView']//li[3]")
	private WebElement changeEmailTab;*/
	
	@FindBy(xpath="//a[contains(text(),'Change Email')]")
	private WebElement changeEmailTab;
	
	@FindBy(id="tabView:editProfileU:firstName")
	private WebElement firstname;
	
	@FindBy(id="tabView:editProfileU:lastName")
	private WebElement lastname;
	
	@FindBy(id="tabView:editProfileU:update")
	private WebElement editProfile_saveBtn;
	
	@FindBy(id="tabView:editProfileU:cancel")
	private WebElement editProfile_cancelBtn;
	
	@FindBy(id="tabView:changePassword:currentPassword")
	private WebElement enterOldPassword;
	
	@FindBy(id="tabView:changePassword:newPass")
	private WebElement enterNewPassword;
	
	@FindBy(id="tabView:changePassword:confirmPassword")
	private WebElement confirmPassword;
	
	@FindBy(id="tabView:changePassword:changePwdConfirmBtn")
	private WebElement changePassword_SaveBtn;
	
	@FindBy(id="tabView:changePassword:changePwdResetBtn")
	private WebElement changePassword_ResetBtn;
	
	@FindBy(id="tabView:changeEmail:email")
	private WebElement newEmailAddress;
	
	@FindBy(id="tabView:activateEmail:acode")
	private WebElement activationCode;
	
	@FindBy(id="tabView:changeEmail:button")
	private WebElement changeEmail_SaveBtn;
	
	@FindBy(id="tabView:changeEmail:button")
	private WebElement changeEmail_ResetBtn;

	@FindBy(xpath="//button[contains(@id,'tabView:activateEmail')]")
	private WebElement activateBtn;

	@FindBy(xpath = "//div[contains(@id,'start')]//img")
	private WebElement spinner;

	public EventFiringWebDriver driver=null;
	
	public Page_AccountManagement_Aces3Client(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyEditProfile(String firstName, String lastName) throws Exception{
		clickOn(editProfileTab, "AccountManagermentPage -> Edit Profile tab");
		JavascriptExecutor jexe = (JavascriptExecutor) driver;
		jexe.executeScript("document.getElementById('tabView:editProfileU:firstName').value='firstName';");
		JavascriptExecutor jexe1 = (JavascriptExecutor) driver;
		jexe1.executeScript("document.getElementById('tabView:editProfileU:lastName').value='lastName';");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(editProfile_saveBtn);
		waitForElementVisibility(editProfile_cancelBtn);
	}
	
	public void verifyChangePassword(String oldPassword, String newPassword, String confirmPwd) throws InterruptedException{
		clickOn(changePasswordTab, "AccountManagermentPage -> Change Password tab");
		waitForElementToDisappear(spinner);
		typeInText(enterOldPassword, oldPassword, "AccountManagermentPage -> Change Password tab -> Enter Old Password");	
		waitForElementToDisappear(spinner);
		typeInText(enterNewPassword, newPassword, "AccountManagermentPage -> Change Password tab -> Enter New Password");	
		waitForElementToDisappear(spinner);
		typeInText(confirmPassword, confirmPwd, "AccountManagermentPage -> Change Password tab -> Confirm Password");	
		confirmPassword.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		waitForElementVisibility(changePassword_SaveBtn);
		waitForElementVisibility(changePassword_ResetBtn);
		Thread.sleep(5000);
		waitForElementToDisappear(spinner);
	}
	
	public void verifyChangeEmail(String emailAddress, String activateCode) throws Exception{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", changeEmailTab);
		typeInText(newEmailAddress, emailAddress, "AccountManagermentPage -> Change Email tab -> New Email Address");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(changeEmail_SaveBtn);
		waitForElementVisibility(changeEmail_ResetBtn);
		typeInText(activationCode, activateCode, "AccountManagermentPage -> Change Emaild tab -> Activation Code");
		waitForElementVisibility(activateBtn);
	}
	
	
}
