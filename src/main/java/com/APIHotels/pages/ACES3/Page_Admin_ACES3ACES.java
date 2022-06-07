package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Admin_ACES3ACES extends BasePage{

		@FindBy(how=How.XPATH, using="//a[contains(text(),'Create User')]")
		WebElement CreateUserLink; 
		
		@FindBy(how=How.XPATH, using="//a[contains(text(),'Manage Users')]")
		WebElement ManageUsersLink;
		
		@FindBy(how=How.XPATH, using="//a[contains(text(),'Manage Roles')]")
		WebElement ManageRolesLink; 
		
		public EventFiringWebDriver driver = null;
		public Page_Admin_ACES3ACES(EventFiringWebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		public void clickOnCreateUserLink()
		{
		clickOn(CreateUserLink, "Admin Menu -> Create User link");
		}
		
		public void clickOnManageUsersLink()
		{
			clickOn(ManageUsersLink, "Admin Menu -> Manage Users link");
		}
		
		public void clickOnManageRolesLink()
		{
			clickOn(ManageRolesLink, "Admin Menu -> ManageRoles link");
		}
			
}
