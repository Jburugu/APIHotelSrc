package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class EditProfilePage extends BasePage {

	public EventFiringWebDriver driver=null;
	// MYACCOUNT -- EDIT PROFILE

	@FindBy(how = How.CSS, using = ".tenant_logo+br+b")
	public WebElement tenantUserName;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Username')]/parent::td/following-sibling::td/label")
	public WebElement userName;

	@FindBy(how = How.ID, using = "editProfileForm:passwordArea:currPassword")
	public WebElement currentPassword;

	@FindBy(how = How.ID, using = "editProfileForm:newPasswordArea:newPassword")
	public WebElement newPassword;

	@FindBy(how = How.ID, using = "editProfileForm:rePasswordArea:rePassword")
	public WebElement reEnterPassword;

	@FindBy(how = How.ID, using = "editProfileForm:employeeEmailArea:email")
	public WebElement employeeEmail;

	@FindBy(how = How.CSS, using = "input[id$='save']")
	public WebElement saveButton;

	public EditProfilePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void editProfile(String currentPasswordValue,String newPasswordValue, String reenterPasswordValue,
			String email){

		unExpectedAcceptAlert();
		typeInText(currentPassword, currentPasswordValue);
		typeInText(newPassword, newPasswordValue);
		typeInText(reEnterPassword, reenterPasswordValue);
		typeInText(employeeEmail, email);
		waitForElementVisibility(saveButton);
	}
}
