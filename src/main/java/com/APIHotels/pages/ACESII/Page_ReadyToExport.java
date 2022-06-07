package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_ReadyToExport extends BasePage{

	@FindBy(id = "comSelectBidPeriod")
	private WebElement bidPeriod;
	
	@FindBy(name = "readyToExportComm")
	private WebElement comments;
	
	@FindBy(xpath = "//*[@name = 'ReadyToExport' and @value = 'Ready To Export']")
	private WebElement readyToExportBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_ReadyToExport(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyReadyToExport(String comments) throws Exception{
		//logger.info("*** ReadyToExportPage -> verifyReadyToExport method started ***");
		waitForElementVisibility(bidPeriod);
		typeInText(this.comments, comments);
		waitForElementVisibility(readyToExportBtn);
		//expected pop-up needs to be handled. However, not tested yet
		//logger.info("*** verifyReadyToExport method completed ***");
	}
	
}
