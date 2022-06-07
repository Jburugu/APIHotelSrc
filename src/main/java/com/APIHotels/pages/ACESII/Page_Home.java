package com.APIHotels.pages.ACESII;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.framework.BasePage;

public class Page_Home extends BasePage{

	@FindBy(how = How.ID, using = "tenantId")
	private WebElement tenant;
	
	@FindBy(how = How.ID, using = "bidMonthName")
	private WebElement bidMonth;
	
	@FindBy(how = How.NAME, using = "Go")
	private WebElement goBtn;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'Supplier Profile')]")
	private WebElement supplierProfileLink;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'Planning')]")
	private WebElement planningLink;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'Schedules')]")
	private WebElement schedulesLink;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'OPS Desk')]")
	private WebElement opsDeskLink;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'Accounting')]")
	private WebElement accountingLink;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'Reports')]")
	private WebElement reportsLink;
	
	@FindBy(how = How.XPATH, using = "//dt[contains(text(), 'Admin')]")
	private WebElement adminLink;
	
	@FindBy(how = How.XPATH,using ="//td[@class='Aces_Logout']/a")
	private WebElement logoutLink;
	
	
	
	@FindBy(id="bidMonth")
	private WebElement morebidMonth;
	
	@FindBy(xpath= "//button[@name='Update']")
	private WebElement updateButton;
	
	public EventFiringWebDriver driver=null;

	public Page_Home(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setTenantDetails(String tenantName, int bidMonthIndex)throws Exception{
		waitForElementVisibility(tenant);
		Select tenantDropdown = new Select(tenant);
		selectByVisibleText(tenant, tenantName,"HomePage -> TenantName dropdown");
		WebElement selectedTenant = tenantDropdown.getFirstSelectedOption();
		Assert.assertEquals(selectedTenant.getText(), tenantName, "Tenant mismatch");
		selectByIndex(bidMonth, bidMonthIndex, "HomePage -> BidMonth dropdown");
		clickOn(goBtn, "Home Page-> Go button");
		takeScreenshots();
	}
	
	public void setTenantDetails(DataTable tenantDetailsTable)throws Exception{
		Select tenantDropdown = new Select(tenant);
		selectByVisibleText(tenant, tenantDetailsTable.getFieldValue(1, "tenantName"), "HomePage -> TenantName dropdown" );
		WebElement selectedTenant = tenantDropdown.getFirstSelectedOption();
		Assert.assertEquals(selectedTenant.getText(), tenantDetailsTable.getFieldValue(1, "tenantName"), "Tenant mismatch");
		selectByIndex(bidMonth, Integer.parseInt(tenantDetailsTable.getFieldValue(1, "bidMonthIndex")), "HomePage -> BidMonth dropdown");
		clickOn(goBtn, "Home Page-> Go button");
	}
	
	
	public void setTenant(String tenantName)throws Exception{
		//Select tenantDropdown = new Select(tenant);
		selectByVisibleText(tenant, tenantName,"HomePage -> TenantName dropdown");
		clickOn(goBtn, "Home Page-> Go button");
	}
			
	public void selectTenantAndBidMonth(String tenantName, String bidMonth) throws InterruptedException{
		Select tenantDropdown = new Select(tenant);
		selectByVisibleText(tenant, tenantName,"HomePage -> TenantName dropdown");
		WebElement selectedTenant = tenantDropdown.getFirstSelectedOption();
		Assert.assertEquals(selectedTenant.getText(), tenantName, "Tenant mismatch");
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		selectByVisibleText(this.bidMonth, "More..","HomePage-> More Option in BidMonth dropdown");
		//clickOn(goBtn);
		Thread.sleep(2000);
		switchToNewWindow(parentHandle);
		selectByVisibleText(morebidMonth, bidMonth, "BidMonth Popup-> date option in List");
		clickOn(updateButton);
		switchToWindow();
		selectByVisibleText(this.bidMonth, bidMonth , "BidMonth Popup-> date option in List");
		Thread.sleep(3000);
		clickOn(goBtn, "Home Page-> Go button");
	}
	
	
	public void clickSupplierProfileLink()throws Exception{
		clickOn(supplierProfileLink,"NavigationalLinksPanel -> SupplierProfile Menu");
	}
	
	public void clickPlanningLink()throws Exception{
		clickOn(planningLink,"NavigationalLinksPanel-> Planning Menu");
	}
	
	public void clickSchedulesLink()throws Exception{
		clickOn(schedulesLink, "NavigationalLinksPanel -> Schedules Menu");
	}
	
	public void clickOPSDeskLink()throws Exception{
		clickOn(opsDeskLink, "NavigationalLinksPanel -> OpsDesk Menu");
	}
	
	public void clickAccountingLink()throws Exception{
		clickOn(accountingLink, "NavigationalLinksPanel -> Accounting Menu");
	}
	
	public void clickReportsLink()throws Exception{
		clickOn(reportsLink, "NavigationalLinksPanel -> Reports Menu");
	}
	
	public void clickAdminLink()throws Exception{
		clickOn(adminLink,"NavigationalLinksPanel -> Admin Menu");
	}

	/*public void setTenantDetails() throws Exception{
		Select tenantDropdown = new Select(tenant);
		selectDropDownByVisibleText(tenant, tenantDetails.getFieldValue(1, "TenantName"));
		WebElement selectedTenant = tenantDropdown.getFirstSelectedOption();
		Assert.assertEquals(selectedTenant.getText(), tenantDetails.getFieldValue(1, "TenantName"), "Tenant mismatch");
		selectDropDownByIndex(bidMonth, Integer.parseInt(tenantDetails.getFieldValue(1, "BidPeriod")));
		clickOn(goBtn);
	}*/
	
	public void clickLogoutLink() throws Exception{
		clickOn(logoutLink, "NavigationalLinksPanel -> Logout Menu");
	}
	
	
}
