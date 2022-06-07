package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_FindInvoice_Aces3Client extends BasePage{

	@FindBy(xpath = "//*[@id='reconcileDashboard:dashboardAccordian']/div")
	private WebElement accountingDashboardDivCollapsable;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath="//table[contains(@id,'findBy')]//tr[1]//div[2]/span")
	private WebElement InvoiceNumberRB;
	
	@FindBy(xpath="//table[contains(@id,'findBy')]//tr[2]//div[2]/span")
	private WebElement billingPeriodAndStatusRB;
	
	@FindBy(xpath="//table[contains(@id,'findBy')]//tr[3]//div[2]/span")
	private WebElement suppllierAndLocationRB;
	
	@FindBy(id="findInvForm:search")
	private WebElement searchButton;
		
	@FindBy(xpath="//input[contains(@id,'startDate_input')]")
	private WebElement billingPeriod;
	
	String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/span[1]")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	@FindBy(xpath="//span[contains(@id, 'findInvForm')]/button")
	private List<WebElement> calendarBtn;
	
	@FindBy(xpath="//div[contains(@id,'creation')]")
	private WebElement invoiceCreation;
	
	String invoiceCreationListXpath="//*[contains(@id,'creation_items')]/li[contains(text(),'";
	String listXpath="')]";
	
	String invoiceRecordsXpath1="//button[@id='findInvForm:search']/following-sibling::div[2]//tbody/tr[";
	String invoiceRecordsXpath2="]";
	String invoiceNumberXpath1="]/td[";
	String invoiceNumberXpath2="]/a";
	
	//String supplierNameXpath="//button[@id='findInvForm:search']/following-sibling::div[2]//tbody/tr//td[contains(text(),'";
	@FindBy(id="dailyRoomDetail")
	private WebElement auditNotestable;

	@FindBy(xpath="//*[contains(text(), 'Crew ID Issues')]/following-sibling::td/a")
	private List<WebElement> countsXpath ;
	
	@FindBy(xpath = "//*[contains(text(), 'Specific')]/preceding-sibling::div/div[2]")
	private WebElement specificStatusRadioBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Pending Review')]/preceding-sibling::div/div[2]")
	private WebElement submittedPendingReviewChkBox;
	
	@FindBy(xpath = "//*[contains(text(), 'Submitted - on hold')]/preceding-sibling::div/div[2]")
	private WebElement submittedOnHoldChkBox;
	
	private String statusChkBoxXpath1 = "//*[contains(text(), '";
	private String statusChkBoxXpath2 = "')]/preceding-sibling::div/div[2]";
	
	private String invoiceNumberLinkXpath1 = "//*[contains(text(), '";
	private String invoiceNumberLinkXpath2 = "')]";
	
	private String invoiceNumberLinks1 = "//a[contains(text(), '";
	private String invoiceNumberLinks2 = "')]/../a";
	
	@FindBy(xpath = "//input[contains(@id, 'apiInvoiceNum')]")
	private WebElement apiInvoiceNumber;
	
	private String subStatusXpath1 = "//*[contains(text(), '";
	private String subStatusXpath2 = "')]/../parent::div/parent::td/following-sibling::td[8]";
	private String invoiceStatusXpath = "')]/../parent::div/parent::td/following-sibling::td[7]";
	private String invoiceStatus2Xpath = "')]/../following-sibling::td[6]";
	
	public EventFiringWebDriver driver=null;
	public Page_FindInvoice_Aces3Client(EventFiringWebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void searchInvoiceUsingBillingPeriodAndStatus(String billingPeriodDate){
		collapseAccountingDashboard();
		clickOn(billingPeriodAndStatusRB, "FindInvoicePage -> Billing Period and Status radio btn");
		waitForSpinnerToDisappear();
		clickOn(calendarBtn.get(0), "FindInvoicePage -> BillingPeriod start date calendar btn");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", billingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(searchButton, "FindInvoicePage -> Search btn");
		waitForSpinnerToDisappear();
	}
	
	/*Verifies crewID issues count of 5 suppliers in hotelNames list*/
	public void searchInvoiceWithBillingPeriodAndStatus(String billingPeriodDate, String status){
		collapseAccountingDashboard();
		clickOn(billingPeriodAndStatusRB, "FindInvoicePage -> Billing Period and Status radio btn");
		waitForSpinnerToDisappear();
		clickOn(calendarBtn.get(0), "FindInvoicePage -> Billing Period start date calendar btn");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", billingPeriodDate);
		waitForSpinnerToDisappear();
		clickOn(specificStatusRadioBtn, "FindInvoicePage -> Specific Status radio btn");
		waitForElementToDisappear(spinner);
		for(String thisStatus : status.split(","))
			clickOn(findElementByXpath(statusChkBoxXpath1+thisStatus+statusChkBoxXpath2), "FindInvoicePage -> "+thisStatus+" check box");
		clickOn(searchButton, "FindInvoicePage -> Search btn");
		waitForElementToDisappear(spinner);
	}
	
	public void searchInvoiceWithNumber(String invoiceNumber){
		collapseAccountingDashboard();
		clickOn(InvoiceNumberRB, "FindInvoicePage -> Invoice number radio btn");
		waitForSpinnerToDisappear();
		typeInText(apiInvoiceNumber, invoiceNumber, "FindInvoicePage -> API Invoice Number");
		clickOn(searchButton, "FindInvoicePage -> Search btn");
		waitForElementToDisappear(spinner);
	}
	
	private void collapseAccountingDashboard(){
		if (accountingDashboardDivCollapsable.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
			clickOn(accountingDashboardDivCollapsable, "FindinvoicePage -> Accounting Dashboard section");
			waitForSpinnerToDisappear();
		}
	}
	
	private void waitForSpinnerToDisappear() {
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(30));
		wait1.pollingEvery(Duration.ofSeconds(2));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}
	
	public void selectInvoiceLink(String invoiceNumber){
		clickOn(findElementByXpath(invoiceNumberLinkXpath1+invoiceNumber+invoiceNumberLinkXpath2), "FindInvoicePage -> "+invoiceNumber+" link");
	}
	
	public void verifyInvoiceConsolidatedOrNot(String invoiceNumber){
		assertFalse(findElementsByXpath(invoiceNumberLinks1+invoiceNumber+invoiceNumberLinks2).size()>1, "Invoice not consolidated!");
	}

	public void verifySubStatus(String invoiceNumber, String status) {
		assertTrue(findElementByXpath(subStatusXpath1+invoiceNumber+invoiceStatusXpath).getText().equals(status), "Status Mismatch!");
	}
	
	public void verifyInvoiceStatus(String invoiceNumber, String status) {
		assertTrue(findElementByXpath(subStatusXpath1+invoiceNumber+invoiceStatusXpath).getText().equals(status), "Status Mismatch!");
	}
	
	public void verifyVoidInvoiceStatus(String invoiceNumber, String status){
		assertTrue(findElementByXpath(subStatusXpath1+invoiceNumber+invoiceStatus2Xpath).getText().equals(status), "Status Mismatch!");
	}
}
