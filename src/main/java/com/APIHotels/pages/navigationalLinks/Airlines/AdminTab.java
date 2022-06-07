package com.APIHotels.pages.navigationalLinks.Airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class AdminTab extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = "//td[text()='Admin']")
	public WebElement adminLink;

	@FindBy(how = How.XPATH, using = "//td[text()='Manage User Account']")
	public WebElement manageUserAccountLink;

	@FindBy(how = How.ID, using = "iconcreateUser")
	public WebElement createUserLink;
	
	@FindBy(how = How.ID, using = "iconfindUser")
	public WebElement findUserLink;
	
	@FindBy(how = How.ID, using = "iconmanageGroup")
	public WebElement manageGrouplink;
	
	@FindBy(how = How.ID, using = "iconfindAuditLogs")
	public WebElement auditHistoryLink;
	
	@FindBy(how = How.ID, using = "iconmanageDateConfig")
	public WebElement manageDateConfiguration;

	public AdminTab(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnManageUserAccountLink() {
		clickOn(adminLink);
		clickOn(manageUserAccountLink);
	}
	public void clickOnCreateUserLink(){
		clickOnManageUserAccountLink();
		clickOn(createUserLink);
	}
	
	public void clickOnFindUserLink(){
		clickOnManageUserAccountLink();
		clickOn(findUserLink);
	}
	
	public void clickOnManageGroupLink(){
		clickOnManageUserAccountLink();
		clickOn(manageGrouplink);
	}
	
	public void clickOnAuditHistoryLink(){
		clickOnManageUserAccountLink();
		clickOn(auditHistoryLink);
	}
	
	public void clickOnManageDateConfigurationLink(){
		clickOn(adminLink);
		clickOn(manageDateConfiguration);
	}
	
	public void clickOnAdmin(){
		clickOn(adminLink);
	}
}
