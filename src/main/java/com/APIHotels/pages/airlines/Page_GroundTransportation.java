package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class Page_GroundTransportation extends BasePage{

	public EventFiringWebDriver driver=null;
	
	@FindBy (xpath="//td[text()='Ground Transportation']")
	private WebElement tab_groundTransportation;
	
	@FindBy (id ="iconactionGTInvoice")
	private WebElement tab_ActionGTInvoice;
	
	@FindBy (xpath="//td[text()='Accounting']")
	private WebElement tab_Accounting;
	
	@FindBy (xpath ="//input[@value='Perform Actions']")
	private WebElement btn_PerformActions;
	
	@FindBy (xpath ="//input[@id='gtFindInvoiceForm:invoiceNumberEntry:invoiceNumber']")
	private WebElement textbox_InvoiceNumber;
	
	@FindBy (id="iconactionGTInvoiceNotaFiscal")
	private WebElement tab_ActionGTApprovedInvoiceNotaFiscal;
	
	String actionSelXpath1 ="//td/a[contains(text(),'"; 
	String actionSelXpath2 ="')]/parent::td/preceding-sibling::td[8]/select";
	
	public Page_GroundTransportation(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnAccountingTab(){
		clickOn(tab_Accounting, "HomePage-> Accounting Menu");
	}
	
	public void clickOnGroundTransportationTab(){
		clickOn(tab_groundTransportation, "Accouting Menu -> GroundTransportation Link");
		
	}
	
	public void clickOnActionGTInvoicetab(){
		clickOn(tab_ActionGTInvoice, "GroundTransportation Link -> ActionGTInvoice Link");
	}

	public void clickOnInvoiceLink(String invoiceNum){
		clickOn(findElementByPartialLinkText(invoiceNum), "ActionGTInvoice Page -> Invoice Number");
	}
	
	public void acceptGTInvoice(String invoiceNum){
		selectByVisibleText(findElementByXpath(actionSelXpath1+invoiceNum+actionSelXpath2), "Accept", "ActionGTInvoice Page -> Action dropdown");
	}
	
	public void rejectGTInvoice(String invoiceNum){
 		selectByVisibleText(findElementByXpath(actionSelXpath1+invoiceNum+actionSelXpath2), "Reject", "ActionGTInvoice Page -> Action dropdown");
	}
	
	public void clickOnPerformActionsBtn(){
		clickOn(btn_PerformActions, "ActionGTInvoice Page -> PerformActions Button");
	}
	
	public void findInvoice(String invoiceNum){
		typeInText(textbox_InvoiceNumber, invoiceNum, "ActionGTInvoice Page -> InvoiceNumber");
	}
	
	public void clickOnActionGTApprovedInvoiceNotaFiscal(){
		clickOn(tab_ActionGTApprovedInvoiceNotaFiscal, "ActionGTInvoice Page -> ApproveInvoiceNotaFiscal");
	}
	
	public void verifyGTInvoiceShown(String invoiceNum){
//		selectByVisibleText(findElementByXpath(actionSelXpath1+invoiceNum+actionSelXpath2), "Accept", "ActionGTInvoice Page -> Action dropdown");
		if(findElementByXpath(actionSelXpath1+invoiceNum+actionSelXpath2).isDisplayed())
		{
			System.out.println("We are able to see the G-+"+invoiceNum+" invoice in the Action GT Approved Invoice(Nota Fiscal) Page after attaching the document in the nota fiscal section on invoice");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"We are able to see the G-"+invoiceNum+" invoice in the Action GT Approved Invoice(Nota Fiscal) Page after attaching the document in the nota fiscal section on invoice");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL,
			"We are not able to see the G-"+invoiceNum+"  invoice in the Action GT Approved Invoice(Nota Fiscal) Page after attaching the document in the nota fiscal section on invoice");
		}
	}

	public void verifyGTInvoiceShownAfterDelete(String invoiceNum){
//		selectByVisibleText(findElementByXpath(actionSelXpath1+invoiceNum+actionSelXpath2), "Accept", "ActionGTInvoice Page -> Action dropdown");
		if(findElementsByXpath(actionSelXpath1+invoiceNum+actionSelXpath2).size() > 0)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"We are able to see that G-"+invoiceNum+"  invoice is displayed on the Action GT Approved Invoice(Nota Fiscal) Page after deleting the document in the nota fiscal section on invoice");
				
		}
		else
		{
			System.out.println("We are able to see that G-"+invoiceNum+" invoice is not displayed on the Action GT Approved Invoice(Nota Fiscal) Page after deleting the document in the nota fiscal section on invoice");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"We are able to see that G-"+invoiceNum+" invoice is not displayed on the Action GT Approved Invoice(Nota Fiscal) Page after deleting the document in the nota fiscal section on invoice");
		}
	}

	
}






