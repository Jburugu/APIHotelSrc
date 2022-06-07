package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ActionGTApprovedInvoicePage {

	public EventFiringWebDriver driver=null;
	
	// ACCOUNTING -- GROUND TRANSPORTATION -- ACTION GT APPROVED INVOICE(AZUL)

	@FindBy(how = How.ID, using = "iconactionGTInvoiceNotaFiscal")
	public WebElement actionGTApprovedInvoiceLink;

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Action GT Invoice')]")
	public WebElement actionGTInvoiceHeader;

	public ActionGTApprovedInvoicePage(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
