package com.APIHotels.pages.airlines;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class ViewAllowanceInvoicePage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- ALLOWANCE -- VIEW ALLOWANCE INVOICE (NEWZEALAND)

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:locationArea']/div/input")
	public WebElement city;

	@FindBy(xpath= ".//*[@id='allowanceInvoiceSearchForm:hotelIdArea']/div/select")
	public WebElement hotel;
	
	@FindBy(xpath = "//*[@id='allowanceInvoiceSearchForm:statusArea']//select")
	private WebElement status;

	@FindBy(xpath = "//*[@id='allowanceInvoiceSearchForm:monthBidArea']//select")
	private WebElement billingPeriod;
	
	@FindBy(how = How.ID, using = "allowanceInvoiceSearchForm:submitReq")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id='allowanceInvoiceSearchForm:submitReq']/following-sibling::button")
	private WebElement searchBtnNew;
	
	private String apiInvoiceNumberXpath1 = "//*[@id='allowanceInvoiceSearchForm:alwnInvTable']//td[text()='";
	private String apiInvoiceNumberXpath2 = "']";
	private String actionXpath1 = "/preceding-sibling::td/a[text()='";
	private String actionXpath2 = "']";
	private String generateLinkXpath = "/preceding-sibling::td/a[text()='Generate']";
	
	@FindBy(id = "confirmStatusForm:statusConfirmButton")
	private WebElement confirmAction_AuthorizeBtn;

	public ViewAllowanceInvoicePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void viewAllowanceInvoice(String cityValue, String hotelValue) {
		typeInText(city, cityValue);
		city.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		waitForElementVisibility(searchBtn);

	}
	
	public void verifyAllowanceInvoiceExists(String city, String hotel, String billingPeriod, String status, String invoiceNumber){
		typeInText(this.city, city);
		this.city.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByVisibleText(this.hotel, hotel);
		this.hotel.sendKeys(Keys.TAB);
		waitTime(3000);
		selectByVisibleText(this.billingPeriod, billingPeriod);
		this.billingPeriod.sendKeys(Keys.TAB);
		waitTime(3000);
		selectByVisibleText(this.status, status);
		clickOn(searchBtnNew);
		waitForPageLoad(getDriver());
		assertTrue(findElementsByXpath(apiInvoiceNumberXpath1+invoiceNumber+apiInvoiceNumberXpath2).size()>0, "Invoice does not exist!!");
	}
	
	public void performAction(String invoiceNumber, String action){
		clickOn(findElementByXpath(apiInvoiceNumberXpath1+invoiceNumber+apiInvoiceNumberXpath2+actionXpath1+action+actionXpath2));
		waitTime(3000);
		clickOn(confirmAction_AuthorizeBtn);
	}
	
	public void generateGLExtractReport(String invoiceNumber){
		clickOn(findElementByXpath(apiInvoiceNumberXpath1+invoiceNumber+apiInvoiceNumberXpath2+generateLinkXpath));
		waitTime(10000);
	}

}
