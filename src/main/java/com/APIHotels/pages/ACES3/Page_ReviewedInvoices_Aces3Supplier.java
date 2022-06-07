package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ReviewedInvoices_Aces3Supplier extends BasePage{
	
	private String reviewedInvoiceNumXpath1 = "//*[contains(text(), '";
	private String reviewedInvoiceNumXpath2 = "')]";
	private String reviewedInvoiceStatusXpath = "/../td[7]";
	
	public EventFiringWebDriver driver=null;

	public Page_ReviewedInvoices_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyStatus(String invoiceNumber, String expectedStatus){
		assertEquals(findElementByXpath(reviewedInvoiceNumXpath1+invoiceNumber+reviewedInvoiceNumXpath2+reviewedInvoiceStatusXpath).getText().trim(), expectedStatus, "Status not expected!");
	}
}
