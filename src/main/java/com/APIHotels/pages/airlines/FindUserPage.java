package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class FindUserPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// ADMIN -- MANAGE USER ACCOUNT -- FIND USER

	@FindBy(how = How.CSS, using = "input[name*='userIdArea']")
	public WebElement username;

	@FindBy(how = How.CSS, using = "input[name*='employeeNbrArea']")
	public WebElement employee;

	@FindBy(how = How.CSS, using = "input[value='Search']")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//label[text()=' Active']/preceding-sibling::input[@type='radio']")
	public WebElement activeRadioButton;

	@FindBy(how = How.XPATH, using = "//label[text()=' Inactive']/preceding-sibling::input[@type='radio']")
	public WebElement inactiveRadioButton;

	@FindBy(how = How.LINK_TEXT, using = "Excel")
	public WebElement excelExportOptions;
	
	public FindUserPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void findUser(String usernameValue) {
		waitForElementVisibility(username);
		typeInText(username, usernameValue);

		waitForElementVisibility(employee);

		waitForElementVisibility(searchButton);

		clickOn(activeRadioButton);

		clickOn(inactiveRadioButton);

		waitForElementVisibility(excelExportOptions);

	}
	
}
