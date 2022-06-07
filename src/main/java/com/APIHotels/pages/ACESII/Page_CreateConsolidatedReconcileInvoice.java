package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreateConsolidatedReconcileInvoice extends BasePage{
	
	public EventFiringWebDriver driver=null;
	
	@FindBy(css = "#createConsRecInvoice>a")
	public WebElement createReconConsolidatedInvoiceLink;
	
	@FindBy(id = "hotel")
	public WebElement hotelServices;

	@FindBy(id = "gt")
	public WebElement gtServices;
	
	@FindBy(css = "input[value='Search/Refresh']")
	public WebElement searchRefresh;
	
	@FindBy(id = "reviewButton")
	public WebElement reviewConsolidatedInvocieBtn;
	
	@FindBy(id = "buttonSelectFlag")
	public WebElement selectAllBtn;

	public Page_CreateConsolidatedReconcileInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createConsolidatedInvoice(String currentMMMYYYY, String cityJFK, String indexValue1) throws Exception {
		clickOn(createReconConsolidatedInvoiceLink);
		createConsolidatedInvoices();
	}
	
	private void createConsolidatedInvoices() {
		waitForElementVisibility(hotelServices);
		waitForElementVisibility(gtServices);
		waitForElementVisibility(searchRefresh);
		waitForElementVisibility(reviewConsolidatedInvocieBtn);
		waitForElementVisibility(selectAllBtn);
	}
}
