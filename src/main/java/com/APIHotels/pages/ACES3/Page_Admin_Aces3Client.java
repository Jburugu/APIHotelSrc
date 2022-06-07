package com.APIHotels.pages.ACES3;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_Admin_Aces3Client extends BasePage {
	
	@FindBy(xpath="//a[contains(text(),'Create User')]")
	private WebElement createUserLink; 
	
	@FindBy(xpath="//a[contains(text(),'Manage Users')]")
	private WebElement manageUsersLink;
	
	@FindBy(xpath="//a[contains(text(),'Manage Roles')]")
	private WebElement manageRolesLink; 
	
	@FindBy(xpath="//a[contains(text(),'Location Groups')]")
	private WebElement locationGroupsLink; 
	
	@FindBy(id = "myForm:firstName")
	private WebElement firstName;
	
	@FindBy(id = "myForm:lastName")
	private WebElement lastName;
	
	@FindBy(id = "myForm:email")
	private WebElement email;
	
	private String selectRoleXpath1 = "//li[contains(text(), '";
	private String selectRoleXpath2 = "')]";
	
	@FindBy(xpath = "//div[@id='myForm:roleSelection']//button[@title='Add']")
	private WebElement selectRoleAddBtn;
	
	@FindBy(xpath = "//div[@id='myForm:roleSelection']//button[@title='Add All']")
	private WebElement selectRoleAddAllBtn;
	
	@FindBy(xpath = "//div[@id='myForm:roleSelection']//button[@title='Remove']")
	private WebElement selectRoleRemoveBtn;
	
	@FindBy(xpath = "//div[@id='myForm:roleSelection']//button[@title='Remove All']")
	private WebElement selectRoleRemoveAllBtn;
	
	@FindBy(xpath = "//div[@id='myForm:roleSelection']//button[@title='Remove All']")
	private WebElement availableTenantsAddBtn;
	
	@FindBy(xpath = "//div[@id='myForm:selectableTenants']//button[@title='Add All']")
	private WebElement availableTenantsAddAllBtn;
	
	@FindBy(xpath = "//div[@id='myForm:selectableTenants']//button[@title='Remove']")
	private WebElement availableTenantsRemoveBtn;
	
	@FindBy(xpath = "//div[@id='myForm:selectableTenants']//button[@title='Remove All']")
	private WebElement availableTenantsRemoveAllBtn;
	
	@FindBy(xpath = "//*[@value='BY_EMAIL']")
	private WebElement sendActivationEmailRadioBtn;
	
	@FindBy(xpath = "//*[@value='BY_ADMIN']")
	private WebElement grantAccessRadioBtn;
	
	@FindBy(id = "myForm:userNameRadio")
	private WebElement temporaryUserName;
	
	@FindBy(id = "myForm:passwordRadio")
	private WebElement temporaryPassword;
	
	@FindBy(id = "myForm:createUser")
	private WebElement createUserBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath="//span[contains(text(),'Reset')]/..")
	private WebElement resetBtn;
	
	@FindBy(id="userAccountManager:status")
	private WebElement userStatusdrdp;
	
	@FindBy(id = "userAccountManager:findUserName")
	private WebElement findUser;
	
	@FindBy(id = "userAccountManager:emailAddress")
	private WebElement emailAddress;
	
	@FindBy(id = "userAccountManager:find")
	private WebElement findBtn;
	
	@FindBy(id = "manageRoleFrm:roleTypeId:0")
	private WebElement privateRoles;
	
	@FindBy(id = "manageRoleFrm:roleTypeId:1")
	private WebElement sharedRoles;
	
	@FindBy(id = "manageRoleFrm:roleDataId")
	private WebElement roleDataTable;
	
	@FindBy(xpath="//span[contains(text(),'Add Role')]/..")
	private WebElement addRolebtn;
	
	@FindBy(xpath="//span[contains(text(),'View')]/..")
	private WebElement viewBtn;
	
	@FindBy(xpath="//span[contains(text(),'Edit')]/..")
	private WebElement editBtn;

	@FindBy(xpath="//span[contains(text(),'Delete')]/..")
	private WebElement deleteBtn;

	@FindBy(xpath="//h3[contains(text(),'Location Groups')]")
	private WebElement locationGroupsText;
	
	@FindBy(id = "locationGrpForm:addButton")
	private WebElement locationGroupsAddBtn;
	
	@FindBy(id = "locationGrpForm:locGrpDataTable")
	private WebElement locationGroupsTable;
	
	
	public EventFiringWebDriver driver = null;
	public Page_Admin_Aces3Client(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyCreateUser(String firstName, String lastName, String emailId, String role, String tenantName, String tempUsername, String tempPassword)
	{
		clickOn(createUserLink, "NavigationalLinksPanel -> Admin Menu -> Create User link");
		waitForElementVisibility(this.firstName);
		typeInText(this.firstName, firstName, "CreateUserPage -> First Names");
		typeInText(this.lastName, lastName, "CreateUserPage -> Last Name");
		typeInText(this.email, emailId, "CreateUserPage -> Email Address");
		WebElement selectRole = findElementByXpath(selectRoleXpath1+role+selectRoleXpath2);
		clickOn(selectRole, "CreateUserPage -> Select Role list: "+role+" option");
		clickOn(selectRoleAddBtn, "CreateUserPage -> Add Role btn");
		clickOn(selectRoleAddAllBtn, "CreateUserPage -> Add All Roles btn");
		WebElement selectedRole = findElementByXpath(selectRoleXpath1+role+selectRoleXpath2);
		clickOn(selectedRole, "CreateUserPage -> Roles Selected list: "+role+" option");
		clickOn(selectRoleRemoveBtn, "CreateUserPage -> Roles Selected list: "+role+" option remove btn");
		clickOn(selectRoleRemoveAllBtn, "CreateUserPage -> Roles Selected list remove all btn");
		
		if(tenantName.equalsIgnoreCase("Air New Zealand")){
		WebElement availableTenants = findElementByXpath(selectRoleXpath1+tenantName+selectRoleXpath2);
		clickOn(availableTenants, tenantName+" option from Available Tenants list");
		clickOn(availableTenantsAddBtn, "CreateUserPage -> Available Tenants Add btn");
		clickOn(availableTenantsAddAllBtn, "CreateUserPage -> Available Tenants Add All btn");
		WebElement selectableTenants = findElementByXpath(selectRoleXpath1+tenantName+selectRoleXpath2);
		clickOn(selectableTenants, "CreateUserPage -> Selectable Tenants "+tenantName+" option");
		clickOn(availableTenantsRemoveBtn, "CreateUserPage -> Selectable Tenants Remove btn");
		clickOn(availableTenantsRemoveAllBtn, "CreateUserPage -> Selectable Tenants Remove All btn");
		}
		
		clickOn(sendActivationEmailRadioBtn, "CreateUserPage -> Send Activation Email radio btn");
		waitForElementToDisappear(spinner);
		clickOn(grantAccessRadioBtn, "CreateUserPage -> Grant Access radio btn");
		waitForElementToDisappear(spinner);
		
		typeInText(temporaryUserName, tempUsername, "CreateUserPage -> Temporary Username");
		typeInText(temporaryPassword, tempPassword, "CreateUserPage -> Temporary Password");
		
		waitForElementVisibility(createUserBtn);
		waitForElementVisibility(resetBtn);
		}

	
	public void verifyManageUsers(String userStatuses, String userName, String emailId)
	{
		clickOn(manageUsersLink, "NavigationalLinksPanel -> Admin Menu -> Manage Users link");
		Select userStatus= new Select(userStatusdrdp);
		userStatus.selectByVisibleText(userStatuses);
		typeInText(findUser, userName, "ManageUsersPage -> User Name");
		typeInText(emailAddress, emailId, "ManageUsersPage -> Email Address");
		
		waitForElementVisibility(findBtn);
	}
	
	public void verifyManageRoles()
	{
		clickOn(manageRolesLink, "NavigationalLinksPanel -> Admin Menu -> Manage Roles link");
		Assert.assertTrue(privateRoles.isSelected(), "Private Roles Radio Button is not selected by Default");
		waitForElementVisibility(addRolebtn);
		waitForElementVisibility(roleDataTable);
		waitForElementVisibility(viewBtn);
		waitForElementVisibility(editBtn);
		waitForElementVisibility(deleteBtn);
		
		clickOn(sharedRoles, "ManageRolesPage -> Shared Roles radio btn");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(addRolebtn);
		waitForElementVisibility(roleDataTable);
		waitForElementVisibility(viewBtn);
		waitForElementVisibility(editBtn);
		waitForElementVisibility(deleteBtn);
		
	}
	
	public void verifyLocationGroups(){
		
		 clickOn(locationGroupsLink, "NavigationalLinksPanel -> Admin Menu -> Location Groups link");
		 Assert.assertTrue(locationGroupsText.isDisplayed(), "Location Grops Page not loaded");
		 waitForElementVisibility(locationGroupsAddBtn);
		 waitForElementVisibility(locationGroupsTable);	 
	}
	
	

}
