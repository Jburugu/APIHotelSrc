package com.APIHotels.pages.navigationalLinks.Airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ChangeTenantLink extends BasePage{
	//AEROMEXICO AIrlines - Home Page Rigth Side
	public EventFiringWebDriver driver=null;
	
	@FindBy(how = How.ID, using = "selectTenant")
	private WebElement changeTenantLink;
	
	@FindBy(how = How.ID, using = "TenantSelectForm:tenanttypeId:tenantId")
	private WebElement tenant;
	
	@FindBy(how = How.ID, using = "TenantSelectForm:selectionBtn")
	private WebElement selectbtn;
	
	@FindBy(how = How.ID, using = "//*[@id='TenantSelectForm']//input[contains(@value,'Cancel')]")
	private WebElement cancelbtn;
	
	public ChangeTenantLink(EventFiringWebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void changeTenant(String tanantValue){
		
		clickOn(changeTenantLink);
		int tanantVal= Integer.parseInt(tanantValue);
		selectByIndex(tenant, tanantVal);
		waitForElementVisibility(selectbtn);
				
	}
}

	
