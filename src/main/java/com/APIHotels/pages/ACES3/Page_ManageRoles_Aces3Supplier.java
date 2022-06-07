package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ManageRoles_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[contains(text(), 'Add Role')]")
	private WebElement addRoleBtn;
	
	@FindBy(xpath = "//*[@value = 'SHARED']")
	private WebElement sharedRolesRadioBtn;
	
	@FindBy(xpath = "//*[@value = 'PRIVATE']")
	private WebElement privateRolesRadioBtn;
	
	private String roleInListXpath = "//*[@id = 'manageRoleFrm:roleDataId_data']//td[contains(text(), '";
	private String roleInListXpath2 = "')]";
	private String actionBtnXpath1 = "/..//span[contains(text(), '";
	private String actionBtnXpath2 = "')]";
	
	@FindBy(xpath = "//*[@id = 'manageRoleFrm:confirmDialogId']//*[contains(text(), 'Yes')]")
	private WebElement deleteRoleYesBtn;
	
	@FindBy(className = "ui-messages-info-summary")
	private WebElement msgInfo;
	
	public EventFiringWebDriver driver=null;

	public Page_ManageRoles_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addPrivateRole(){
		clickOnPrivateRole();
		clickOn(addRoleBtn, "ManageRolesPage -> Add Role btn");
	}
	
	public void addSharedRole(){
		clickOnSharedRole();
		clickOn(addRoleBtn, "ManageRolesPage -> Add Role btn");
	}
	
	public void verifyCreatedRole(String roleName){
		assertTrue(findElementByXpath(roleInListXpath+roleName+roleInListXpath2).isDisplayed(), "new role created does not exist!");
	}
	
	public void performActionOnRole(String roleName, String action){
		clickOn(findElementByXpath(roleInListXpath+roleName+roleInListXpath2+actionBtnXpath1+action+actionBtnXpath2), "ManageRolesPage -> "+action+" Action btn");
	}
	
	public void clickOnPrivateRole(){
		clickOn(privateRolesRadioBtn, "ManageRolesPage -> Private Role radio btn");
		waitForElementToDisappear(spinner);
	}
	
	public void clickOnSharedRole(){
		clickOn(sharedRolesRadioBtn, "ManageRolesPage -> Shared role radio btn");
		waitForElementToDisappear(spinner);
	}
	
	public void deleteRole(){
		waitForElementToDisappear(spinner);
		clickOn(deleteRoleYesBtn, "ManageRolesPage -> Delete Role confirmation pop-up Yes btn");
		waitForElementToDisappear(spinner);
		assertTrue(msgInfo.getText().equals("Deleted successfully"), "Role Deleted msg not displayed!");
	}
}
