package com.APIHotels.pages.ACES3;


import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_ManageUsers_Aces3Supplier extends BasePage{
	
	@FindBy(id = "userAccountManager:status")
	private WebElement userStatus;
	
	@FindBy(id = "userAccountManager:findUserName")
	private WebElement findUser;
	
	@FindBy(id = "userAccountManager:emailAddress")
	private WebElement emailAddress;
	
	@FindBy(id = "userAccountManager:find")
	private WebElement findBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	private String actionXpath1 = "//*[@id='userAccountManager:dataTable_data']//td[6]//span[contains(text(), '";
	private String actionXpath2 = "')]";
	
	@FindBy(id = "userAccountManager:dataTable")
	private List<WebElement> userListTable;
	
	@FindBy(id = "grantAccessForm:activatePopupUserName")
	private WebElement userActivation_userName;
	
	@FindBy(id = "grantAccessForm:activatePopupPasword")
	private WebElement userActivation_password;
	
	@FindBy(xpath = "//*[@id='grantAccessForm:activatePopup']//*[contains(text(), 'Grant Access')]")
	private WebElement grantAccessBtn;
	
	@FindBy(id = "userAccountManager:delete")
	private WebElement accountDeleteConfirmation_YesBtn;
	
	@FindBy(className = "ui-messages-info-summary")
	private WebElement msgInfo;
	
	@FindBy(xpath = "//*[@id = 'userAccountManager:deactivateConfirmation']//*[contains(text(), 'Yes')]")
	private WebElement accountSuspendConfirmation_YesBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_ManageUsers_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void findUser(String emailId){
		typeInText(this.emailAddress, emailId, "ManageUsersPage -> Email Address");
		clickOn(findBtn, "ManageUsersPage -> Find btn");
		waitForElementToDisappear(spinner);
		if(userListTable.size()>0)
			ExtentTestManager.getTest().log(LogStatus.INFO, "Searched user exists");
		else
			ExtentTestManager.getTest().log(LogStatus.INFO, "Searched user does not exist");
	}
	
	public void performActions(String action){
		clickOn(findElementByXpath(actionXpath1+action+actionXpath2), "ManageUsersPage -> "+action+" btn");
	}
	
	public void userActivation(String password){
		typeInText(userActivation_password, password, "ManageUsersPage -> UserActivation pop-up -> Password");
		clickOn(grantAccessBtn, "ManageUsersPage -> GrantAccess btn");
		waitForElementToDisappear(spinner);
	}
	
	public void accountDeleteConfirmation(){
		waitForElementToDisappear(spinner);
		clickOn(accountDeleteConfirmation_YesBtn, "ManageUsersPage -> Account Delete Confirmation pop-up -> Yes btn");
		waitForElementToDisappear(spinner);
		assertTrue(msgInfo.getText().equals("User account deleted successfully"), "Deleted Msg Not Displayed!!");
	}
	
	public void accountSuspendConfirmation(){
		waitForElementToDisappear(spinner);
		clickOn(accountSuspendConfirmation_YesBtn, "ManageUsersPage -> Account Suspend Confirmation pop-up -> Yes");
		waitForElementToDisappear(spinner);
		assertTrue(msgInfo.getText().equals("User account suspended successfully"), "Suspended Msg Not Displayed!!");
	}
}
