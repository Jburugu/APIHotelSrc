package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Home_ACES3ACES extends BasePage {
	
	@FindBy(id="home - menuForm:main")
	private WebElement HomeLink;
	
	@FindBy(xpath="//a[contains(text(), 'Accounting')]")
	private WebElement AccountingLink;
	
	@FindBy(xpath="//a[contains(text(), 'Sign-in Sheets')]")
	private WebElement SignInSheetsLink;
	
	@FindBy(xpath="//a[contains(text(), 'Reports')]")
	private WebElement ReportsLink;

	@FindBy(xpath="//a[contains(text(), 'Airline Reports')]")
	private WebElement AirlineReportsLink;
	
	@FindBy(xpath="//a[contains(text(), 'Admin')]")
	private WebElement AdminLink;
	
	@FindBy(xpath="//a[contains(text(), 'Configuration')]")
	private WebElement ConfigurationLink;
	
	@FindBy(xpath="//a[contains(text(), 'Account Management')]")
	private WebElement AccountManagementLink;
	
	@FindBy(xpath="//a[contains(text(), 'EDrive Dashboard')]")
	private WebElement EDriveDashboardLink;

	@FindBy(xpath="//a[contains(text(), 'Allowances')]")
	private WebElement allowancesLink;

	@FindBy(xpath="//a[contains(text(), 'View Forecast')]")
	private WebElement viewForecastLink;
	
	@FindBy(id="menuForm:logout")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Create User')]")
	private WebElement createUserLink;
		
	@FindBy(xpath = "//*[contains(text(), 'Manage Users')]")
	private WebElement manageUsersLink;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	
	public EventFiringWebDriver driver=null;
	public Page_Home_ACES3ACES(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnHomeLink()
	{
		clickOn(HomeLink, "ACES 3 ACES Menu -> Home Link");
	}
	
	public void clickOnAccountingLink()
	{
		clickOn(AccountingLink, "ACES 3 ACES Menu -> Accounting Link");
	}
	
	public void clickOnSignInSheetsLink()
	{
		clickOn(SignInSheetsLink, "ACES 3 ACES Menu -> Sign In Sheets Link");
	}
	
	public void clickOnReportsLink()
	{
		clickOn(ReportsLink, "ACES 3 ACES Menu -> Report Link");
	
	}
	
	public void clickOnAirlineReportsLink()
	{
		clickOn(AirlineReportsLink, "ACES 3 ACES Menu -> Airline Report Link");
	}
	
	public void clickOnAdminLink()
	{
		clickOn(AdminLink, "ACES 3 ACES Menu -> Admin Link");
	}
	
	public void clickOnConfigurationLink()
	{
		clickOn(ConfigurationLink, "ACES 3 ACES Menu -> Configurations Link");
	}
	
	public void clickOnAccountManagementLink()
	{
		clickOn(AccountManagementLink, "ACES 3 ACES Menu -> Accounting Management Link");
	}
	
	public void clickOnEDriveDashboardLink()
	{
		clickOn(EDriveDashboardLink, "ACES 3 ACES Menu -> E Drive Dashboard Link");
	}
	
	public void clickOnLogoutLink()
	{
		clickOn(logoutLink, "ACES 3 ACES Menu -> Logout Link");
	}

	public void clickOnAllowancesLink()
	{
		clickOn(allowancesLink, "ACES 3 ACES Menu -> Allowances Link");
	}
	
	public void clickOnViewForecastLink()
	{
		clickOn(viewForecastLink,"Allowances Menu -> View Forecast Link");
	}
	

	public void clickOnCreateUserLink(){
		
		waitForElementToDisappear(spinner);
		clickOn(createUserLink, "NavigationalLinksPanel -> AdminMenu -> Create User link");
	}
	
	public void clickOnManageUserLink(){
		
		waitForElementToDisappear(spinner);
		clickOn(manageUsersLink, "NavigationalLinksPanel -> AdminMenu -> manage User link");
	}

	
}
