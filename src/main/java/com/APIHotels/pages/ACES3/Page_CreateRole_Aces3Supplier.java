package com.APIHotels.pages.ACES3;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreateRole_Aces3Supplier extends BasePage{
	
	@FindBy(id = "roleForm:roleNameTxtId")
	private WebElement roleName;
	
	private String modulePermissionXpath1 = "//*[contains(text(), '";
	private String modulePermissionXpath2 = "')]/..//input";
	
	@FindBy(id = "roleForm:saveRoleBtn")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//*[@id='roleForm:panelRoleId']//*[contains(text(), 'Cancel')]")
	private WebElement cancelBtn;
	
	@FindBy(className = "ui-messages-info-summary")
	private WebElement createRoleMsg;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	public EventFiringWebDriver driver=null;

	public Page_CreateRole_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createRole(String roleName, String moduleName){
		typeInText(this.roleName, roleName, "CreateRolePage -> Role Name");
		List<WebElement> actionChkBoxes = findElementsByXpath(modulePermissionXpath1+moduleName+modulePermissionXpath2);
		for(WebElement actionCheckBox : actionChkBoxes)
			clickOn(actionCheckBox, "CreateRolePage -> "+moduleName+ "all action checkboxes");
		clickOn(saveBtn, "CreateRolePage -> Save btn");
		waitForElementToDisappear(spinner);
		clickOn(cancelBtn, "CreateRolePage -> Cancel btn");
	}
}
