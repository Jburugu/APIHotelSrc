package com.APIHotels.pages.airlines;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ViewSubmittedAllowanceInvoicesPage extends BasePage{
	
	
	public EventFiringWebDriver driver = null;
	
	private String invoiceNumberXpath1 = "//*[@id='allowanceInvoiceSubmittedSearchForm:alwnInvTable']//td[text()='";
	private String invoiceNumberXpath2 = "']";
	private String actionXpath = "/preceding-sibling::td[7]/select";
	
	@FindBy(id = "allowanceInvoiceSubmittedSearchForm:invoiceApplyStatusButton")
	private WebElement applyStatusBtn;
	
	@FindBy(id = "confirmStatusForm:statusConfirmButton")
	private WebElement confirmActionSubmitBtn;
	
	public ViewSubmittedAllowanceInvoicesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyIfInvoiceExists(String invoiceNumber){
		assertTrue(findElementsByXpath(invoiceNumberXpath1+invoiceNumber+invoiceNumberXpath2).size()>0, "Invoice does not exist!");
	}
	
	public void performAction(String invoiceNumbersAndActions) throws Exception{
		
		for(String thisInvoiceAction : invoiceNumbersAndActions.split(";")){
			String invoiceNumber = thisInvoiceAction.split(",")[0];
			String action = thisInvoiceAction.split(",")[1];
			WebElement actionDD = findElementByXpath(invoiceNumberXpath1+invoiceNumber+invoiceNumberXpath2+actionXpath);
			selectByVisibleText(actionDD, action);
			actionDD.sendKeys(Keys.TAB);
			Thread.sleep(3000);
		}
		clickOn(applyStatusBtn);
		clickOn(confirmActionSubmitBtn);
	}
}
