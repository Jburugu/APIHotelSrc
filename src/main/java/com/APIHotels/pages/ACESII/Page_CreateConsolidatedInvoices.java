package com.APIHotels.pages.ACESII;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
public class Page_CreateConsolidatedInvoices extends BasePage{
	
	@FindBy(xpath = ".//*[@id='consoidatedInvoice']/a")
	public WebElement createConsolidatedInvoice;
	
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
	
	@FindBy(name = "serviceTypeMonth")
	private List<WebElement> serviceTypeMonth; //HOTEL, GT, BOTH
	
	@FindBy(name = "serviceTypeSingle")
	private List<WebElement> serviceTypeSingle; //HOTEL, GT, BOTH
	
	String serviceTypeXpath1 = "//*[@name = 'serviceType' and @id = '";
	String serviceTypeXpath2 = "']";
	
	
	@FindBy(xpath="//td[@class='Aces_Body']//tr[1]//td[1]//table[1]/tbody/tr[2]/td[1]")
	private WebElement selectFirstRecordInvoiceWithEDP;

	@FindBy(xpath="//div[@class='Aces_Table']//div[@class='Aces_Table']//table[@class='Border displayTable']/tbody/tr[2]/td[1]")
	private WebElement selectFirstRecordInvoiceWithoutEDP;
	
	@FindBy(xpath="//td[@class='Aces_Body']//tr[1]//td[1]//table[1]/tbody/tr")
	private List<WebElement> invoiceWithEDP;
	
	@FindBy(xpath="//div[@class='Aces_Table']//div[@class='Aces_Table']//table[@class='Border displayTable']/tbody/tr")
	private List<WebElement> invoiceWithoutEDP;
	
	
	@FindBy(xpath = "//*[@value = 'Create Consolidated Invoice']")
	private WebElement createConsolidatedInvoiceBtn;
	
	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;
	
	String parentWindowHandle = "";
	
	
	public EventFiringWebDriver driver=null;
	
	public Page_CreateConsolidatedInvoices(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void clickOnCreateConsolidatedInvoices() throws Exception {
		waitForElementVisibility(createConsolidatedInvoice);
		clickOn(createConsolidatedInvoice);
	}

	public void createConsolidatedInvoicesMainTab() throws Exception {
		clickOnCreateConsolidatedInvoices();
		createConsolidatedInvoices();
	}

	private void createConsolidatedInvoices() {
		waitForElementVisibility(hotelServices);
		waitForElementVisibility(gtServices);
		waitForElementVisibility(searchRefresh);
		waitForElementVisibility(reviewConsolidatedInvocieBtn);
		waitForElementVisibility(selectAllBtn);
	}

	public void services(String serviceType){
		clickOn(findElementByXpath(serviceTypeXpath1+serviceType.toLowerCase()+serviceTypeXpath2));
	}
	
	public void createConsolidatedInvoice(String serviceType) throws Exception {
		clickOnCreateConsolidatedInvoices();
		services(serviceType);
		clickOn(searchRefresh);
		
	}
	
	public void verifyInvoice() throws IOException{
		String invoiceNumber = readTextFile();
		Assert.assertEquals(invoiceNumber, driver.getPageSource().contains(invoiceNumber), "Invoice present not on Create Consolidate Invocie page");
		
	}

	public void reviewConsolidatedInvoice(String serviceType) throws Exception{
		
		clickOn(createConsolidatedInvoice);
		services(serviceType);
		clickOn(searchRefresh);
		
		if (invoiceWithEDP.size()>2)
		{
			clickOn(selectFirstRecordInvoiceWithEDP);
		}
		else if(invoiceWithoutEDP.size()>2)
		{
			clickOn(selectFirstRecordInvoiceWithoutEDP);
		}
		else {
//			throw new TestException("No Record Found");
			System.out.println("No element found");
		}
	
		clickOn(reviewConsolidatedInvocieBtn);
	}
	
	public void searchRefresh(String serviceType){
		services(serviceType);
		clickOn(searchRefresh);
	}
	
	public void clickOnReviewConsolidateInvoice(){
		clickOn(selectAllBtn);
		List<String> availableWindowHandles = new ArrayList<String>(getDriver().getWindowHandles());
		clickOn(reviewConsolidatedInvocieBtn);
		switchToNewWindow(availableWindowHandles);
		clickOn(createConsolidatedInvoiceBtn);
		clickOn(alertOkBtn);
		switchToWindow(availableWindowHandles.get(0));
	}
	
	
	public void reviewConsolidatedInvoice(){
		parentWindowHandle = getDriver().getWindowHandle();
		clickOn(selectAllBtn);
		clickOn(reviewConsolidatedInvocieBtn);
		switchToNewWindow(parentWindowHandle);
		clickOn(createConsolidatedInvoiceBtn);
		clickOn(alertOkBtn);
		switchToWindow(parentWindowHandle);
	}
	

}
