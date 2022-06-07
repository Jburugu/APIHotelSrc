package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_ManageUsers extends BasePage{
	
	@FindBy(how = How.CSS, using = "#userName")
	protected WebElement userName;

	@FindBy(how = How.CSS, using = "#password")
	protected WebElement password;

	@FindBy(how = How.CSS, using = "#retypePassword")
	protected WebElement reTypePassword;

	/*@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Tenant Name')]/following-sibling::td/select[@id = 'tenantId']")
	protected WebElement tenantName;*/
	
	@FindBy(id = "tenantId")
	private List<WebElement> tenantName;

	@FindBy(how = How.CSS, using = "#language")
	protected WebElement language;

	@FindBy(how = How.CSS, using = "button[value='Add']")
	protected WebElement addButton;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Create User')]")
	protected WebElement createUserHeaderLink;

	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Map users to Groups')]")
	protected WebElement mapUsersToGroupsHeaderLink;
	
	@FindBy(how = How.CSS, using = "select[name='userID']")
	protected WebElement users;

	@FindBy(how = How.CSS, using = "select[name='groupID']")
	protected WebElement userGroupList;

	@FindBy(how = How.NAME, using = "greater")
	protected WebElement addUserGroupsBtn;

	@FindBy(how = How.NAME, using = "lesser")
	protected WebElement removeUserGroupsBtn;

	@FindBy(how = How.CSS, using = "select[name='userGroupID']")
	protected WebElement userGroupSelectedList;

	@FindBy(how = How.CSS, using = "button[name='Save']")
	protected WebElement saveButton;
	
	public EventFiringWebDriver driver=null;

	public Page_ManageUsers(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setCreateUserDetails(String userName, String password, String tenantName, int languageIndex) throws Exception{
		//logger.info("*** Admin_ManageUsersPage -> setCreateUserDetails method started ***");
		clickOn(createUserHeaderLink);
		typeInText(this.userName, userName);
		typeInText(this.password, password);
		typeInText(this.reTypePassword, password);
		selectByVisibleText(this.tenantName.get(1), tenantName);
		//selectDropDownByIndex(this.tenantName, 3);
		selectByIndex(this.language, languageIndex);
		waitForElementVisibility(addButton);
		//logger.info("*** setCreateUserDetails method completed ***");
	}
	
	public void mapUsersToGroups(String user, String listOfGroups) throws Exception{
		//logger.info("*** Admin_ManageUsersPage -> mapUsersToGroups method started ***");
		clickOn(mapUsersToGroupsHeaderLink);
		selectByVisibleText(this.users, user);
		for(String group : listOfGroups.split(","))
			selectByVisibleText(this.userGroupList, group);
		clickOn(addUserGroupsBtn);
		waitForElementVisibility(removeUserGroupsBtn);
		waitForElementVisibility(userGroupSelectedList);
		waitForElementVisibility(saveButton);
		//logger.info("*** mapUsersToGroup method completed ***");
	}
	
}
