package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class SupplierProfileMenu extends BasePage{

	@FindBy(xpath = "//div[text()='Airline Supplier']")
	private WebElement airlineSupplierLink;
	
	@FindBy(xpath = "//div[text()='Common Supplier']")
	private WebElement commonSupplierLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Sort Suppliers')]")
	private WebElement sortSuppliersLink;
	
	@FindBy(xpath = "//*[contains(text(), 'A/P to A/P Times')]")
	private WebElement apTimesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Gate Times')]")
	private WebElement gateTimesLink;	

	@FindBy(id = "ManageVenues")
	private WebElement manageVenuesLink;
	
	public EventFiringWebDriver driver=null;

	public SupplierProfileMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAirlineSupplierLink()throws Exception{
		clickOn(airlineSupplierLink, "SupplierProfile Menu -> AirlinesSupplier Link");
	}
	
	public void clickCommonSupplierLink()throws Exception{
		clickOn(commonSupplierLink, "SupplierProfile Menu -> CommonSupplier Link");
	}
	
	public void clickSortSupplierLink()throws Exception{
		clickOn(sortSuppliersLink, "SupplierProfile Menu -> SortSupplier Link");
	}
	
	public void clickApToApTimesLink()throws Exception{
		clickOn(apTimesLink, "SupplierProfile Menu -> ApToApTimes Link");
	}
	
	public void clickGateTimesLink()throws Exception{
		clickOn(gateTimesLink, "SupplierProfile Menu -> GateTimes Link");
	}
	
	public void clickManageVenuesLink()throws Exception{
		clickOn(manageVenuesLink, "SupplierProfile Menu -> ManageVenues Link");
	}
	

}
