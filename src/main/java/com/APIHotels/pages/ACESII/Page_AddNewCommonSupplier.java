package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AddNewCommonSupplier extends BasePage{
	
	@FindBy(id = "newCommonSupplier")
	private WebElement addNewCommonSupplierLink;
	
	@FindBy(xpath = "//*[contains(text(), 'New Hotel Supplier')]")
	private WebElement hotelSupplierLink;
	
	@FindBy(xpath = "//*[contains(text(), 'New GT Supplier')]")
	private WebElement gtSupplierLink;
	
	public EventFiringWebDriver driver=null;

	public Page_AddNewCommonSupplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddNewCommonSupplierLink() throws Exception{
		clickOn(addNewCommonSupplierLink);
	}
	
	public void selectSupplierType(String supplierType) throws Exception{
		if(supplierType.equals("Hotel"))
			clickOn(hotelSupplierLink);
		else if(supplierType.equals("GT"))
			clickOn(gtSupplierLink);
	}
	
	
}
