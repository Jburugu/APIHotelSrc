package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreateViewAllowanceSIS_Aces3Supplier extends BasePage{
	
	@FindBy(id = "createAllowanceSignInSearchForm:tenantMenu_label")
	private WebElement selectAirlineDropDown;
	
	@FindBy(id = "createAllowanceSignInSearchForm:arrivalDatebutton_input")
	private WebElement arrivalDate;
	
	@FindBy(xpath = "//span[contains(text(), 'Create')]")
	private WebElement createBtn;
	
	public EventFiringWebDriver driver=null;
	
	public Page_CreateViewAllowanceSIS_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createViewAllowanceSIS(){
		waitForElementVisibility(selectAirlineDropDown);
		waitForElementVisibility(arrivalDate);
		waitForElementVisibility(createBtn);
	}

}
