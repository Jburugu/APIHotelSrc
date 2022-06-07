package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_NewInvoices_Aces3Supplier extends BasePage{
	
	private String invoiceNumXpath1 = "//*[contains(text(),'";
	private String invoiceNumXpath2 = "')]";
	private String acceptLinkXpath = "/..//a[contains(text(), 'Accept')]";
	private String chanllengeXpath = "/..//a[contains(text(), 'Challenge')]";
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "form1:acceptButton")
	private WebElement acceptBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'OK')]")
	private WebElement acceptOkBtn;

	public EventFiringWebDriver driver=null;

	public Page_NewInvoices_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyInvoiceExist(String invoiceNumber){
		if(findElementsByXpath(invoiceNumXpath1+invoiceNumber+invoiceNumXpath2).size()>0)
			ExtentTestManager.getTest().log(LogStatus.PASS, "invoice: "+invoiceNumber+" created in ACES2 exists in ACES3!");
		else
			ExtentTestManager.getTest().log(LogStatus.INFO, "invoice: "+invoiceNumber+" created in ACES2 does not exists in ACES3!");
	}
	
	public void acceptInvoice(String invoiceNumber){
		clickOn(findElementByXpath(invoiceNumXpath1+invoiceNumber+invoiceNumXpath2+acceptLinkXpath), "NewInvoicePage -> Accept link for "+invoiceNumber);
		clickOn(acceptBtn, "NewInvoicePage -> Accept btn");
		waitForElementToDisappear(spinner);
		clickOn(acceptOkBtn, "NewInvoicePage -> Accept confirmation pop-up -> Ok Btn");
	}
	
	public void challengeInvoice(String invoiceNumber){
		clickOn(findElementByXpath(invoiceNumXpath1+invoiceNumber+invoiceNumXpath2+chanllengeXpath), "NewInvoicePage -> Challenge link for "+invoiceNumber);
		waitForElementToDisappear(spinner);
	}
}
