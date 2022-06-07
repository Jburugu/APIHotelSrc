package com.APIHotels.pages.ACESII;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ApproveManualInvoice extends BasePage {

	@FindBy(css = "#ApproveManualInvoices>a")
	public WebElement approveManualInvoices;

	@FindBy(css = "input[value='Search/Refresh']")
	public WebElement searchRefresh;

	@FindBy(linkText = "City")
	public WebElement approveInvoiceCity;

	@FindBy(linkText = "Supplier")
	public WebElement approveInvoiceSupplier;

	@FindBy(linkText = "Type")
	public WebElement approveInvoiceType;

	@FindBy(linkText = "Contract?")
	public WebElement approveInvoiceContract;

	@FindBy(linkText = "Invoice Date")
	public WebElement approveInvoiceInvoiceDate;

	@FindBy(linkText = "Status")
	public WebElement approveInvoiceStatus;

	@FindBy(linkText = "Amount")
	public WebElement approveInvoiceAmount;

	@FindBy(linkText = "Units")
	public WebElement approveInvoiceUnits;

	@FindBy(linkText = "API Number")
	public WebElement approveInvoiceAPINumber;

	@FindBy(linkText = "Accepted")
	public WebElement approveInvoiceAccepted;

	@FindBy(linkText = "Challenged")
	public WebElement approveInvoiceChallenged;

	@FindBy(linkText = "Resolved")
	public WebElement approveInvoiceResolved;

	@FindBy(xpath = "//input[@value='Approve']")
	public WebElement buttonApprove;

	public EventFiringWebDriver driver = null;

	public Page_ApproveManualInvoice(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void approveManualInvoice() throws Exception {
		waitForElementVisibility(approveManualInvoices);
		clickOn(approveManualInvoices);
		waitForElementVisibility(searchRefresh);
		clickOn(searchRefresh);
		waitForPageLoad(driver);
		waitForElementVisibility(approveInvoiceCity);
		waitForElementVisibility(approveInvoiceSupplier);
		waitForElementVisibility(approveInvoiceType);
		waitForElementVisibility(approveInvoiceContract);
		waitForElementVisibility(approveInvoiceInvoiceDate);
		waitForElementVisibility(approveInvoiceStatus);
		waitForElementVisibility(approveInvoiceAmount);
		waitForElementVisibility(approveInvoiceUnits);
		waitForElementVisibility(approveInvoiceAPINumber);
		waitForElementVisibility(approveInvoiceAccepted);
		waitForElementVisibility(approveInvoiceChallenged);
		waitForElementVisibility(approveInvoiceResolved);

	}

	public void openInvoiceAndApprove() throws IOException, Exception {
		String invoiceNumber = readPropValue("ManualInvoiceNumber");// readTextFile();
		WebElement invoice = driver.findElement(By.xpath("//a[contains(text(),'" + invoiceNumber + "')]"));
		String winHandleBefore = driver.getWindowHandle();
		clickOn(invoice);
		Thread.sleep(4000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		clickOn(buttonApprove);
		unExpectedAcceptAlert();
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(4000);
	}

}
