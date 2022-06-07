package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreateUser_Aces3Aces extends BasePage{
	
	@FindBy(id = "myForm:firstName")
	private WebElement firstName;
	
	@FindBy(id = "myForm:lastName")
	private WebElement lastName;
	
	@FindBy(id = "myForm:email")
	private WebElement email;
	
	private String selectRoleXpath1 = "//li[contains(text(), '";
	private String selectRoleXpath2 = "')]";
	
	@FindBy(xpath = "//*[@title='Add']")
	private WebElement addRoleBtn;
	
	@FindBy(xpath = "//*[@value='BY_EMAIL']")
	private WebElement sendActivationEmailRadioBtn;
	
	@FindBy(xpath = "//*[@value='BY_ADMIN']")
	private WebElement grantAccessRadioBtn;
	
	@FindBy(id = "myForm:createUser")
	private WebElement createUserBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "myForm:userNameRadio")
	private WebElement tempUsername;
	
	@FindBy(id = "myForm:passwordRadio")
	private WebElement tempPassword;
	
	@FindBy(xpath = "//*[contains(text(), 'User created successfully')]")
	private WebElement userCreatedMsg;
	
	private String selectedRoleXpath1 = "//*[@aria-label='Role(s) Selected']//*[contains(text(), '";
	private String selectedRoleXpath2 = "']";
	
	
	
	public EventFiringWebDriver driver=null;

	public Page_CreateUser_Aces3Aces(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createUser(String firstName, String lastName, String emailId, String roles,String createByIndicator, String tempUserName, String tempPassword) throws Exception{
		typeInText(this.firstName, firstName, "CreateUserPage -> First Name");
		typeInText(this.lastName, lastName, "CreateuserPage -> Last Name");
		typeInText(this.email, emailId, "CreateuserPage -> Email Id");
		for(String role : roles.split(",")){
			WebElement thisRole = findElementByXpath(selectRoleXpath1+role+selectRoleXpath2);
			clickOn(thisRole, "CreateuserPage -> "+thisRole+" option");
			//thisRole.sendKeys(Keys.CONTROL);
			Actions actions = new Actions(getDriver());
			actions.sendKeys(Keys.CONTROL);
		}
		clickOn(addRoleBtn, "CreateuserPage -> AddRole to user btn");
		waitForElementToDisappear(spinner);
		if(createByIndicator.equals("Email"))
			clickOn(sendActivationEmailRadioBtn, "CreateuserPage -> Send Activation Email Radio btn");
		else if(createByIndicator.equals("Grant")){
			clickOn(grantAccessRadioBtn, "CreateuserPage -> Grant Access radio btn");
			waitForElementToDisappear(spinner);
			typeInText(this.tempUsername, tempUserName, "CreateuserPage -> Temporary Username");
			typeInText(this.tempPassword, tempPassword, "CreateuserPage -> Temporary Password");
		}
		clickOn(createUserBtn, "CreateuserPage -> CreateUser btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifyUserCreated(){
		assertTrue(userCreatedMsg.isDisplayed(), "UserCreated Msg Not Displayed!!");
	}
	
	
}
