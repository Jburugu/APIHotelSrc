package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ManageEDrive_ACES3ACES extends BasePage{
	
	@FindBy(id="configEDriveForm:tenantSelector")
	private WebElement tenantDropdown;
	
	@FindBy(id="eDriveTenantFeature:0")
	private WebElement activateFeatureYes;
	
	@FindBy(id="eDriveTenantFeature:1")
	private WebElement activateFeatureNo;
	
	@FindBy(id="configEDriveForm:FileSize")
	private WebElement fileSize;
	
	@FindBy(id="configEDriveForm:NoOfFiles")
	private WebElement noOfFiles;
	
	@FindBy(id="configEDriveForm:BillingPeriod:0")
	private WebElement monthlyBillingPeriod;
	
	@FindBy(id="configEDriveForm:BillingPeriod:1")
	private WebElement SemiMonthlyBillingPeriod;
	
	@FindBy(id="configEDriveForm:BillingPeriodHistory:0")
	private WebElement pastHistory12Months;
	
	@FindBy(id="configEDriveForm:BillingPeriodHistory:1")
	private WebElement pastHistory8Months;
	
	@FindBy(id="crewTypeRequired:0")
	private WebElement crewTypeYes;
	
	@FindBy(id="crewTypeRequired:1")
	private WebElement crewTypeNo;
	
	@FindBy(id="configEDriveForm:EmailAddress")
	private WebElement emailAddress;
	
	String dashBoardTable="configEDriveForm:dashboardTable:";
	String acesNoOfDays=":acesNosOfDays";
	String supplierNoOfDays =":supplierNosOfDays";
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	private WebElement saveButton;

	public EventFiringWebDriver driver = null;

	public Page_ManageEDrive_ACES3ACES(EventFiringWebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void activateEdrive(String tenantName) throws Exception{
		//clickOn(tenantDropdown);
		selectByVisibleText(tenantDropdown, tenantName,"Manage E Drive Page -> Tenant Select");
		if(activateFeatureYes.isSelected()==false){
			clickOn(activateFeatureYes,"Manage E Drive Page -> Active Feature Yes Radio Button" );
			waitForElementVisibility(emailAddress);
			clickOn(saveButton,"Manage E Drive Page -> Active Edrive Save Button");
		}
		else{
			System.out.println("Edrive is already activated for the" + tenantName);
		}
	}
	
	

	
	
}
