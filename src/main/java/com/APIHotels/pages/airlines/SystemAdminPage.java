package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class SystemAdminPage  extends BasePage{

	public EventFiringWebDriver driver=null;
	// ADMIN -- SYSTEM ADMIN(JETBLUE)

	@FindBy(how = How.ID, using = "iconsysAdmin")
	public WebElement systemAdminLink;

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'System Admin')]")
	public WebElement systemAdminHeader;

	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Reload DropDown values')]")
	public WebElement reloladDropDownvalues;

	public SystemAdminPage(EventFiringWebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	
	public void systemAdminPage(){
		waitForElementVisibility(systemAdminLink);
		clickOn(systemAdminLink);
		waitForElementVisibility(reloladDropDownvalues);
	}
	
}
