package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class AdminMenu extends BasePage{
	
	@FindBy(css = "#manageUser>a")
	protected WebElement manageUserlink;

	@FindBy(css = "#manageGroups>a")
	protected WebElement manageGroupsLink;
	
	@FindBy(css = "#faxstatus>a")
	protected WebElement faxStatusLink;
	
	@FindBy (css ="#viewResourceStatus>a")
	protected WebElement viewResourceStatusLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Configure GT E-Invoice')]")
	private WebElement configureGTEInvoiceLink;
	
	@FindBy (css ="#manageGTSchedule>a")
	protected WebElement manageGTScheduleLink;
	
	@FindBy (css ="#configAllowance>a")
	protected WebElement configHotelAllowanceLink;
	
	@FindBy (how =How.CSS, using ="#multiNightLayover>a")
	protected WebElement configureMultiNightGroupingLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Manage Tenant Features')]")
	private WebElement manageTenantFeaturesLink;
	
	public EventFiringWebDriver driver=null;

	public AdminMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickManageUsersLink() throws Exception{
		//logger.info("*** AdminMenu -> clickManageUsersLink method started ***");
		clickOn(manageUserlink,"AdminMenu -> ManageUsers Link");
		//logger.info("*** clickManageUsersLink method completed ***");
	}
	
	public void clickManageGroupsLink() throws Exception{
		//logger.info("*** AdminMenu -> clickManageGroupsLink method started ***");
		clickOn(manageGroupsLink,"AdminMenu -> ManageGroups Link");
		//logger.info("*** clickManageGroupsLink method completed ***");
	}
	
	public void clickFaxStatusLink() throws Exception{
		//logger.info("*** AdminMenu -> clickFaxStatusLink method started ***");
		clickOn(faxStatusLink, "AdminMenu -> FaxStatus Link");
		//logger.info("*** clickFaxStatusLink method completed ***");
	}
	
	public void clickViewResourceStatusLink() throws Exception{
		//logger.info("*** AdminMenu -> clickViewResourceStatusLink method started ***");
		clickOn(viewResourceStatusLink, "AdminMenu -> ViewResourcesStatus Link");
		//logger.info("*** clickViewResourceStatusLink method completed ***");
	}
	
	public void clickManageGTScheduleLink() throws Exception{
		//logger.info("*** AdminMenu -> clickManageGTScheduleLink method started ***");
		clickOn(manageGTScheduleLink, "Admin Menu -> Manage GT Schedule Link");
		//logger.info("*** clickManageGTScheduleLink method completed ***");
	}
	
	public void clickConfigHotelAllowanceLink() throws Exception{
		//logger.info("*** AdminMenu -> clickConfigHotelAllowanceLink method started ***");
		clickOn(configHotelAllowanceLink, "AdminMenu -> Config Hotel Allowances Link");
		//logger.info("*** clickConfigHotelAllowanceLink method completed ***");
	}
	
	public void clickConfigureMultiNightGroupingLink() throws Exception{
		//logger.info("*** AdminMenu -> clickConfigureMultiNightGroupingLink method started ***");
		clickOn(configureMultiNightGroupingLink, "AdminMenu -> ConfigureMultiNightGrouping Link");
		//logger.info("*** clickConfigureMultiNightGroupingLink method completed ***");
	}

	public void clickManageTenantFeaturesLink() throws Exception{
		//logger.info("*** AdminMenu -> clickManageTenantFeaturesLink method started ***");
		clickOn(manageTenantFeaturesLink, "AdminMenu -> ManageTenantFeature Link");
		//logger.info("*** clickManageTenantFeaturesLink method completed ***");
	}
	
	public void clickConfigureGTEInvoiceLink() throws Exception{
		//logger.info("*** AdminMenu -> clickConfigureGTEInvoiceLink method started ***");
		clickOn(configureGTEInvoiceLink, "AdminMenu-> Configure GTE-Invocie Link");
		//logger.info("*** clickConfigureGTEInvoiceLink method completed ***");
	}
}
