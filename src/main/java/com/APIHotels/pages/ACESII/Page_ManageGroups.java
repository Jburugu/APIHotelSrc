package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_ManageGroups extends BasePage{
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Create Groups')]")
	private WebElement createGroupsHeader;

	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Map Roles to Groups')]")
	private WebElement mapRolesToGroupsHeader;
	
	@FindBy(how = How.CSS, using = "input[name='groupName']")
	private WebElement groupName;

	@FindBy(how = How.XPATH, using = "//*[@name = 'tenantId']")
	private List<WebElement> tenantName;

	@FindBy(how = How.CSS, using = "button[value='Add']")
	private WebElement addGroupBtn;
	
	@FindBy(how = How.CSS, using = "select[name='groupId']")
	private WebElement group;

	@FindBy(how = How.NAME, using = "Go")
	private List<WebElement> mapRolesToGroupGoBtn;

	@FindBy(how = How.XPATH, using = "//div[@class='Aces_Bottom_btns']/button[@value='Save']")
	private WebElement mapRolesToGroupSaveBtn;
	
	private String rowXpath1 = "//*[contains(text(), '";
	private String rowXpath2 = "')]/../td/input[@value = '";
	private String rowXpath3 = "']";
	
	public EventFiringWebDriver driver=null;

	public Page_ManageGroups(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createGroups(String groupName, String tenantName) throws Exception{
		//logger.info("*** Admin_ManageGroupsPage -> createGroups method started ***");
		clickOn(createGroupsHeader);
		typeInText(this.groupName, groupName);
		selectByVisibleText(this.tenantName.get(1), tenantName);
		waitForElementVisibility(addGroupBtn);
		//logger.info("*** createGroups method completed ***");
	}
	
	
	public void mapRolesToGroups(String group, String roles, String permissionType) throws Exception{
		//logger.info("*** Admin_ManageGroupsPage -> mapRolesToGrps method started ***");
		clickOn(mapRolesToGroupsHeader);
		selectByVisibleText(this.group, group);
		clickOn(mapRolesToGroupGoBtn.get(1));
		for(int row = 0; row < roles.split(",").length; row++){
			String role = roles.split(",")[row];
			String givenPermission = permissionType.split(",")[row];
			clickOn((findElementByXpath(rowXpath1+role+rowXpath2+givenPermission+rowXpath3)));
		}
		waitForElementVisibility(mapRolesToGroupSaveBtn);
		//logger.info("*** mapRolesToGroups method completed ***");
	}
}
