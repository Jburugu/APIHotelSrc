package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_EditUserAccount_Aces3Supplier extends BasePage{

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "myForm:email")
	private WebElement emailAddress;
	
	@FindBy(id = "myForm:firstName")
	private WebElement firstName;
	
	@FindBy(id = "myForm:lastName")
	private WebElement lastName;
	
	private String selectRoleXpath1 = "//*[@id = 'myForm:roleSelection']//li[contains(text(), '";
	private String selectRoleXpath2 = "')]";
	
	@FindBy(xpath = "//*[@title='Add']")
	private WebElement addRoleBtn;
	
	@FindBy(id = "myForm:createUser")
	private WebElement saveBtn;
	
	@FindBy(className = "ui-messages-info-summary")
	private WebElement msgInfo;
	
	public EventFiringWebDriver driver=null;

	public Page_EditUserAccount_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void editUserAccount(String newEmailId, String newFirstName, String newLastName, String roles){
		typeInTextIfInputNotEmpty(this.emailAddress, newEmailId, "EditUserAccountPage -> Email Address");
		typeInTextIfInputNotEmpty(this.firstName, newFirstName, "EditUserAccountPage -> First Name");
		typeInTextIfInputNotEmpty(this.lastName, newLastName, "EditUserAccountPage -> Last Name");
		for(String role : roles.split(",")){
			WebElement thisRole = findElementByXpath(selectRoleXpath1+role+selectRoleXpath2);
			clickOn(thisRole, "EditUserAccountPage -> "+ role+" option");
			Actions actions = new Actions(getDriver());
			actions.sendKeys(Keys.CONTROL);
		}
		if(!roles.isEmpty())
			clickOn(addRoleBtn, "EditUserAccountPage -> Add Role to User btn");
		clickOn(saveBtn, "EditUserAccountPage -> Save btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifyUserUpdated(){
		assertTrue(msgInfo.getText().equals("User Profile Updated Successfully"), "User Profile Update Msg Not Displayed!!");
	}
}
