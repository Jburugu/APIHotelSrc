package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class AccountingMenu extends BasePage{
	
	
	@FindBy(xpath = "//a[text()='Create Proforma Invoices']")
	protected WebElement createProformaInvoicesLink;

	@FindBy(xpath = "//a[text()='Find Invoices']")
	protected WebElement findInvoicesLink;
	
	@FindBy(id="gtEInvoice")
	protected WebElement gtInvoicingLink;
	
	@FindBy(xpath = "//a[text()='Create Consolidated Invoices']")
	private WebElement createConsolidatedInvoicesLink;
	
	@FindBy(xpath ="//div[@class='subMenu']//a")
	private WebElement signInSheetlink;
	
	@FindBy (xpath ="//body//div[text()='Allowance Invoices']")
	private WebElement allowanceInvoicesLink;
	
	public EventFiringWebDriver driver=null;

	public AccountingMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickCreateProformaInvoicesLink() throws Exception {
		clickOn(createProformaInvoicesLink, "Accounting Menu-> Create Proforma Invoices Link");
	}

	public void clickFindInvoices() throws Exception {
		clickOn(findInvoicesLink, "Accounting Menu-> Find Invoices Link");
	}
	
	public void clickGtInvoicing() throws Exception {
		clickOn(gtInvoicingLink, "Accounting Menu-> GT invoicing Link");
	}
	
	public void clickCreateConsolidatedInvoices() throws Exception{
		clickOn(createConsolidatedInvoicesLink, "Accounting Menu-> Create Consolidate Invocies Link");
	}	

	public void clickSignInReport() throws Exception {
		clickOn(signInSheetlink, "Accounting Menu-> Sign In Report Link");
	}
	
	public void clickOnAllowanceInvoicesLink() {
		clickOn(allowanceInvoicesLink);
	}
	
}
