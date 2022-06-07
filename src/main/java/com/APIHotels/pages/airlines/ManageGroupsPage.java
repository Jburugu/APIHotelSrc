package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ManageGroupsPage extends BasePage {

	public EventFiringWebDriver driver=null;
	
	/**
	 * ADMIN -- MANAGE USER -- MANAGE GROUPS
	 */
	@FindBy(how = How.ID, using = "groupForm:groupTable")
	public WebElement manageGroupTable;

	@FindBy(how = How.ID, using = "groupForm:editRole")
	public WebElement addGroup;
	
	public ManageGroupsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void manageGroups() {

		waitForElementVisibility(manageGroupTable);
		waitForElementVisibility(addGroup);

	}
	
}
