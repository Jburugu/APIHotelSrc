package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class CreateUserPage extends BasePage {

	public EventFiringWebDriver driver=null;

	// ADMIN -- MANAGE USER ACCOUNT -- CREATE USER

	@FindBy(how = How.CSS, using = "input[id$='userId']")
	public WebElement username;

	@FindBy(how = How.ID, using = "userForm:ssoUserArea:ssoUser")
	public WebElement ssoUserCheckBox;

	@FindBy(how = How.ID, using = "userForm:passwordArea:password")
	public WebElement password;

	@FindBy(how = How.ID, using = "userForm:rePasswordArea:rePassword")
	public WebElement reenterPassword;

	@FindBy(how = How.CSS, using = "input[name*='employeeNbrArea']")
	public WebElement employee;

	@FindBy(how = How.ID, using = "userForm:employeeEmailArea:email")
	public WebElement employeeEmail;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Airlines']/following-sibling::input[1]")
	public WebElement lanAirlinesTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Express']/following-sibling::input[1]")
	public WebElement lanExpressTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Peru']/following-sibling::input[1]")
	public WebElement lanPeruTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Argentina']/following-sibling::input[1]")
	public WebElement lanArgentinaTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Ecuador']/following-sibling::input[1]")
	public WebElement lanEcuadorTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Cargo']/following-sibling::input[1]")
	public WebElement lanCargoTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='MasAir']/following-sibling::input[1]")
	public WebElement masAirTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='Lanco']/following-sibling::input[1]")
	public WebElement lancoTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='LAN Colombia']/following-sibling::input[1]")
	public WebElement lanColombiaTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='Absa TAM Cargo']/following-sibling::input[1]")
	public WebElement absaTAMCargoTenantMapping;

	@FindBy(how = How.XPATH, using = "//span[text()='TAM']/following-sibling::input[1]")
	public WebElement tam;

	@FindBy(how = How.XPATH, using = "//span[text()='TAM Mercosur']/following-sibling::input[1]")
	public WebElement tamMercosur;

	@FindBy(how = How.XPATH, using = ".//*[@id='userForm:assignGroupArea:assignGroup:source::1']/td")
	public WebElement assignGroups;

	@FindBy(how = How.XPATH, using = "//td[text()='Roles Basico']")
	public WebElement rolesBasicoAssignGroups;

	@FindBy(how = How.XPATH, using = "//td[text()='Movement']")
	public WebElement movementAssignGroups;

	@FindBy(how = How.XPATH, using = "//td[text()='Reports']")
	public WebElement reportsAssignGroups;

	@FindBy(how = How.XPATH, using = "//td[text()='admin']")
	public WebElement adminAssignGroups;

	@FindBy(how = How.CSS, using = "a[id*='assignGroupcopyAll']")
	public WebElement copyAll;

	@FindBy(how = How.CSS, using = "a[id*='assignGroupcopy']")
	public WebElement copy;

	@FindBy(how = How.CSS, using = "a[id*='assignGroupremove']")
	public WebElement remove;

	@FindBy(how = How.CSS, using = "a[id*='assignGroupremoveAll']")
	public WebElement removeAll;

	@FindBy(how = How.ID, using = "userForm:save")
	public WebElement saveButton;

	public CreateUserPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createUser(String testUser, String email) {

		waitForElementVisibility(username);
		waitForElementVisibility(ssoUserCheckBox);
		waitForElementVisibility(password);
		waitForElementVisibility(reenterPassword);
		typeInText(employee, testUser);
		typeInText(employeeEmail, email);
		clickOn(copyAll);
		clickOn(removeAll);
		clickOn(assignGroups);
		clickOn(copy);
		clickOn(assignGroups);
		clickOn(remove);
		waitForElementVisibility(saveButton);
	}

	public void lanSpecificTenants() {

		clickOn(lanExpressTenantMapping);
		clickOn(lanPeruTenantMapping);
		clickOn(lanArgentinaTenantMapping);
		clickOn(lanEcuadorTenantMapping);
		clickOn(lanCargoTenantMapping);
		clickOn(masAirTenantMapping);
		clickOn(lancoTenantMapping);
		clickOn(lanColombiaTenantMapping);
		clickOn(absaTAMCargoTenantMapping);
		clickOn(tam);
		clickOn(tamMercosur);
	}

}
