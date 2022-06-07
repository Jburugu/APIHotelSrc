package com.APIHotels.pages.airlines;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

/**
 * @author abhilashk
 *
 */
public class ActionGTInvoicePage extends BasePage{

	public EventFiringWebDriver driver=null;
	
	// ACCOUNTING -- GROUND TRANSPORTATION -- ACTION GT
	// INVOICE(AZUL/AIRNEWZEALAND)

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Action GT Invoice')]")
	public WebElement actionGTInvoiceHeader;

	@FindBy(how = How.ID, using = "actionGTInvoiceForm:submit")
	public WebElement performActions;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'City')]")
	public WebElement city;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'City')]/img")
	public WebElement cityListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Transportation Provider')]/img")
	public WebElement transportationProviderListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Transportation Provider')]")
	public WebElement transportationProvider;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Action')])")
	public WebElement action;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Invoice Creation Date')]")
	public WebElement invoiceCreationDate;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Invoice Creation Date')]/img")
	public WebElement invoiceCreationDateListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Period')]")
	public WebElement billingPeriod;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Period')]/img")
	public WebElement billingPeriodListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total Pickups')]")
	public WebElement totalPickups;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total Pickups')]/img")
	public WebElement totalPickupsListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total Amount Due')]")
	public WebElement totalAmountDue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total Amount Due')]/img")
	public WebElement totalAmountDueListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Units')]")
	public WebElement units;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Units')]/img")
	public WebElement unitsListIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'API Invoice Number')]")
	public WebElement apiInvoiceNumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'API Invoice Number')]/img")
	public WebElement apiInvoiceNumberListIcon;
	
	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Accounting')]")
	public WebElement accountingLink;
	
	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Ground Transportation')]")
	public WebElement groundTransportationLink;

	@FindBy(xpath = "//*[@id='actionGTInvoiceForm:invoiceList:tb']//a")
	private List<WebElement> apiInvoiceNumbers;
	
	private String actionDropDownXpath1 = "//a[contains(text(), '";
	private String actionDropDownXpath2 = "')]/parent::td/preceding-sibling::td[8]/select[contains(@id, 'actionTypes')]";
	
	@FindBy(id = "commentsForm:dec1:newComment")
	private WebElement newCommentTextArea;
	
	@FindBy(xpath = "//*[@value='Add New Comment']")
	private WebElement addNewCommentBtn;
	
	@FindBy(xpath = "//textarea[contains(@id, 'comments') and @readonly='readonly']")
	private WebElement addedCommentsTextArea;
	
	@FindBy(xpath = "//*[@value='Save']")
	private WebElement saveBtn;
	
	public ActionGTInvoicePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnActionGTInvoice(String actionGTInvoiceText) {
		waitForElementVisibility(actionGTInvoiceHeader);
		Assert.assertEquals(actionGTInvoiceHeader.getText(), actionGTInvoiceText,
				"actionGtInvoice page is not displayed");
	}
	
	public void verifyApproveInvoiceExists(String invoiceNumber) throws Exception{
		List<String> invoiceNumbers = new ArrayList<String>();
		for(WebElement apiInvoiceNumber : apiInvoiceNumbers){
			invoiceNumbers.add(apiInvoiceNumber.getText().replace("G-", ""));
		}
		assertTrue(invoiceNumbers.contains(invoiceNumber), "InvoiceNumber "+invoiceNumber+" does not exist in ActionGTInvoiceQueue");
	}
	
	public void performActionOnInvoice(String action, String invoiceNumber, String comments) throws Exception {
		String parentWindow = driver.getWindowHandle();
		List<String> windowHandles = new ArrayList<String>();
		windowHandles.add(parentWindow);
		selectByVisibleText(findElementByXpath(actionDropDownXpath1 + invoiceNumber + actionDropDownXpath2), action, "ActionGTInvoicingPage -> Actions Dropdown");
		switch (action) {
		case "Reject":
			rejectInvoice(windowHandles, comments);
			break;
		}
		clickOn(performActions);
	}

	private void rejectInvoice(List<String> windowHandles, String comments) throws Exception{
		switchToNewWindow(windowHandles);
		typeInText(newCommentTextArea, comments, "ActionGTInvoicingPage -> Comments Textarea");
		clickOn(addNewCommentBtn, "ActionGTInvoicingPage -> Add, Button");
		Thread.sleep(2000);
		clickOn(saveBtn, "ActionGTInvoicingPage -> Save Button");
		switchToWindow(windowHandles.get(0));
	}

}
