package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ViewConsolidatedReconcileInvoice extends BasePage{
	
	public EventFiringWebDriver driver=null;
	
	@FindBy(xpath = ".//*[@id='viewConsolidateRecInvoices']/a")
	public WebElement viewConsolidateRecInvoicesLink;
	
	@FindBy(id = "hotel")
	public WebElement hotelServices;

	@FindBy(id = "gt")
	public WebElement gtServices;
	
	@FindBy(css = "input[value='Search/Refresh']")
	public WebElement searchRefresh;
	
	public Page_ViewConsolidatedReconcileInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void viewConsolidatedReconcileInvoices(String currentMMMYYYY, String cityJFK, String indexValue1) throws Exception {
		clickOn(viewConsolidateRecInvoicesLink);
		viewConsolidatedInvoices();

	}
	
	private void viewConsolidatedInvoices() throws Exception {
		waitForElementVisibility(hotelServices);
		waitForElementVisibility(gtServices);
		waitForElementVisibility(searchRefresh);
		clickOn(searchRefresh);
	}
}
