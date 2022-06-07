package com.APIHotels.pages.ACESII;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_GtEInvoicing_CreateInvoice extends BasePage {

	@FindBy(id = "createGTInvoice")
	private WebElement createInvoiceLink;

	@FindBy(id = "manualGTInvoice")
	private WebElement createManualInvoice;

	@FindBy(id = "periodChkbox")
	private WebElement bimonthlyCheckBox;

	@FindBy(id = "billingPeriodNumberMonth")
	private WebElement batchBillingPeriod;

	@FindBy(css = "input[value='Create Invoices']")
	private WebElement createInvoicesBatch;

	@FindBy(id = "locCd")
	private WebElement citySingleInvoice;

	@FindBy(css = "input[value='Get Supplier']")
	private WebElement getSupplierBtnSingleInvoice;

	@FindBy(id = "supplierId")
	private WebElement suppliersSingleInvoice;

	@FindBy(id = "billingPeriodNumberSingle")
	private WebElement billingPeriodNumberSingle;

	@FindBy(css = "input[value='Create Invoice']")
	private WebElement createInvocieSingleInvoiceBtn;

	@FindBy(id = "ProgressBox")
	private WebElement inProgressBox;

	@FindBy(xpath = "//*[@class='Aces_Table']//table//tr")
	private List<WebElement> invoiceRecords;

	@FindBy(xpath = "//*[@class='Aces_Table']//table//tr[2]//td[9]/a")
	private WebElement apiNumber;

	@FindBy(xpath = "//*[@class='Aces_Table']//table//tr//td[2]")
	private WebElement status;

	String invoicesXpath = "//*[@class='Aces_Table']//table//tr[";
	String apiNumberXpath = "]//td[9]/a";
	String supplierNameXpath = "]/td[2]";

	@FindBy(id = "AlertBox")
	private WebElement alertBox;

	@FindBy(id = "alertOK")
	private WebElement okBtn;

	@FindBy(id = "editInvoiceAreaButton")
	private WebElement editButton;

	@FindBy(id = "supplierInvoiceNumber")
	private WebElement supplierInvoiceNumber;

	@FindBy(id = "paymentTerms")
	private WebElement paymentTerms;

	@FindBy(id = "saveInvoiceAreaButton")
	private WebElement saveButton;

	@FindBy(id = "buttonApprove_")
	private WebElement approveInvoice;

	@FindBy(id = "buttonVoid")
	private WebElement voidInvoice;

	@FindBy(id = "buttonHistory")
	private WebElement historyBtn;

	@FindBy(css = "input[value='Comments']")
	private WebElement commentsBtn;

	@FindBy(css = "input[value='Exit']")
	private WebElement exitBtn;

	@FindBy(id = "supplierInvoiceNumber_")
	private WebElement supplierInvoiceNumText;

	@FindBy(id = "addComment")
	private WebElement addComment;

	@FindBy(xpath = "//form[@name='InvoiceHistoryForm']//input[@value='OK']")
	private WebElement commentOK;

	@FindBy(xpath = "//*[@class='Aces_Table']//table//td[3]")
	private WebElement invoiceActionInHistorytable;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement logout;
	


	String parentWindowHandle = getDriver().getWindowHandle();

	public EventFiringWebDriver driver = null;

	public Page_GtEInvoicing_CreateInvoice(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForInProgressToComplete() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.withTimeout(Duration.ofMinutes(60));
		wait.pollingEvery(Duration.ofSeconds((15)));
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.invisibilityOf(inProgressBox));
	}

	public void clickAlertOk(){
		try{
		clickOn(okBtn, "Alert -> OK Button" );
		}catch(Exception e){}
	}
	
	public void clickOnCreateInvoices(){
		clickOn(createInvoiceLink, "GTInvoicing Link -> CreateInvoices Link");
	}

	public void createSingleGTEInvoice(String city,String supplier, String billingPeriod){
		clickOnCreateInvoices();
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"Create a Single Invoice section is displayed in Create Invoice Page");
		typeInText(citySingleInvoice, city, "CreateInvoices Page-> City");
		clickOn(getSupplierBtnSingleInvoice, "CreateInvoices Page-> GetSupplier Button" );
		selectByVisibleText(suppliersSingleInvoice, supplier, "CreateInvoices Page -> Supplier Dropdown");
		selectByVisibleText(billingPeriodNumberSingle, billingPeriod, "CreateInvoices Page -> Billing Period");
		clickOn(createInvocieSingleInvoiceBtn, "CreateInvoices Page -> CreateInvoice Button");
		waitForInProgressToComplete();
		List<WebElement> invoices= invoiceRecords;
		for(int i=2; i<=invoices.size(); i++){
			String supplierName=findElementByXpath(invoicesXpath+i+supplierNameXpath).getText();
			if(supplierName.equalsIgnoreCase(supplier))
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Single GT invoice created successfully for a supplier " +supplier);
			else 
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Failed to create Single GT Invoice for " +supplier);
				Assert.fail("Single GT Invoice is not created for "+supplier);				
		}
	}

	public void createMonthlyBatchGTEInvoice(String monthBillingPeriod) {
		clickOnCreateInvoices();
		selectByVisibleText(batchBillingPeriod, monthBillingPeriod, "CreateInvoicesPage -> BatchBilingPeriod Dropdown");
		clickOn(createInvoicesBatch, "CreateInvoicesPage -> CreateInvoices Button");
		waitForElementVisibility(alertBox);
		clickAlertOk();
		waitForInProgressToComplete();
		waitForElementVisibility(alertBox);
		clickAlertOk();
		if (invoiceRecords.size() >= 2) {
			System.out.println("Monthly Batch Invoice created successfully for " + monthBillingPeriod);
		} else
			Assert.fail("Unable to create Monthly Batch invoice for " + monthBillingPeriod);
	}

	public void createBiMonthlyBatchGTEInvoice(String biMonthlyBillingPeriod) throws Exception {
		clickOnCreateInvoices();
		clickOn(bimonthlyCheckBox, "CreateInvoicesPage -> BiMonthly Checkbox");
		clickOn(batchBillingPeriod, "CreateInvoicesPage -> BillingPeriod Dropdownbox");
		Thread.sleep(2000);
		selectByVisibleText(batchBillingPeriod, biMonthlyBillingPeriod, "CreateInvoicesPage -> BillingPeriod Dropdown");
		clickOn(createInvoicesBatch, "CreateInvoicesPage -> CreateInvoices Button");
		clickAlertOk();
		waitForInProgressToComplete();
		// verifyStatus(primaryInvoiceStatus);
		if (invoiceRecords.size() >= 2) {
			System.out.println("Monthly Batch Invoice created successfully for " + biMonthlyBillingPeriod);
		} else
			Assert.fail("Unable to create Monthly Batch invoice for " + biMonthlyBillingPeriod);
	}

	/*public List<String> verifyInvoiceActions(String billingPeriod, String supplierinvoiceNumberValue,
			String paymentTermsText, String commentText) throws Exception {
		boolean status = false;
		List<String> apiNumbers = new ArrayList<String>();
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		for (int i = 2; i < 4; i++) {
			if (findElementByXpath(invoicesXpath + i + apiNumberXpath).isDisplayed()) {
				String apiNum = findElementByXpath(invoicesXpath + i + apiNumberXpath).getText().substring(2, 8);
				apiNumbers.add(apiNum);
				clickOn(findElementByXpath(invoicesXpath + i + apiNumberXpath));
			}
			if (status == false) {
				switchToNewWindow(parentHandle);
				waitForPageToLoad("30");
				clickOn(editButton);
				typeInText(supplierInvoiceNumber, supplierinvoiceNumberValue);
				typeInText(paymentTerms, paymentTermsText);
				clickOn(saveButton);
				Thread.sleep(5000);
				clickOn(approveInvoice);
				System.out.println("done with approve");
				getDriver().switchTo().window(parentHandle.get(0));
				System.out.println("switched to parent");
				
				 * Before verifying Invoice Status through FindInvoice, need to
				 * click on bimonthlyCheckBox element to activate parent window
				 
				clickOn(bimonthlyCheckBox);
				status = true;
			}
		}
		if (status == true) {
			switchToNewWindow(parentHandle);
			waitForPageToLoad("30");
			clickOn(editButton);
			typeInText(supplierInvoiceNumber, supplierinvoiceNumberValue);
			typeInText(paymentTerms, paymentTermsText);
			clickOn(saveButton);
			Thread.sleep(5000);
			clickOn(voidInvoice);
			switchToNewWindow(parentHandle);
			typeInText(addComment, commentText);
			clickOn(commentOK);
			System.out.println("done with void");
			getDriver().switchTo().window(parentHandle.get(0));
			System.out.println("switched to parent");
			
			 * Before verifying Invoice Status through FindInvoice, need to
			 * click on bimonthlyCheckBox element to activate parent window
			 
			clickOn(bimonthlyCheckBox);
		}

		else
			Assert.fail("Unable to edit Monthly Batch invoice for " + billingPeriod);

		return apiNumbers;
	}
*/
	public void verifyStatus(String invoiceStatus) {
		String statusVal = status.getText();
		if (invoiceStatus.equalsIgnoreCase(statusVal)) {
			System.out.println(statusVal);
		}
	}

	public String approveInvoice(String supplierinvoiceNumberValue, String paymentTermsText) throws Exception {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		String invoiceNumber = findElementByXpath(invoicesXpath + 2 + apiNumberXpath).getText().substring(2, 8);
		clickOn(findElementByXpath(invoicesXpath + 2 + apiNumberXpath), "CreateInvoicesPage -> InvoiceNumber Link");
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		clickOn(editButton, "TaxInvoicePage-> Edit Button");
		typeInText(supplierInvoiceNumber, supplierinvoiceNumberValue, "TaxInvoicePage-> SupplierInvoiceNumber" );
		typeInText(paymentTerms, paymentTermsText, "TaxInvoicePage-> PaymentTerms");
		clickOn(saveButton, "TaxInvoicePage-> Save Button");
		Thread.sleep(5000);
		clickOn(approveInvoice, "TaxInvoicePage-> Approve Invoice Button");
		System.out.println("done with approve");
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("switched to parent");
		/*
		 * Before clicking on FindInvoice, to activate parent window need to
		 * click on bimonthlyCheckBox element
		 */
		clickOn(bimonthlyCheckBox, "CreateInvoicePage-> BiMonthly CheckBox");
		return invoiceNumber;
	}
	
	public String voidInvoice(String supplierinvoiceNumberValue, String paymentTermsText, String commentText) throws InterruptedException, IOException{	
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		String invoiceNumber = findElementByXpath(invoicesXpath + 2 + apiNumberXpath).getText().substring(2, 8);
		clickOn(findElementByXpath(invoicesXpath + 2 + apiNumberXpath), "CreateInvoicesPage -> InvoiceNumber Link");
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		takeScreenshot();	
		typeInText(supplierInvoiceNumber, supplierinvoiceNumberValue, "TaxInvoicePage-> SupplierInvoiceNumber");
		typeInText(paymentTerms, paymentTermsText, "TaxInvoicePage-> PaymentTerms");
		clickOn(saveButton, "TaxInvoicePage-> Save Button");
		Thread.sleep(5000);
		clickOn(voidInvoice, "TaxInvoicePage-> Void Invoice Button");
		switchToNewWindow(parentHandle);
		typeInText(addComment, commentText, "InvoiceCommentsPage  -> Comment TextArea");
		clickOn(commentOK, "InvoiceCommentsPage -> Ok button");
		System.out.println("done with void");
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("switched to parent");
		
		/* Before verifying Invoice Status through FindInvoice, need to
		 * click on bimonthlyCheckBox element to activate parent window*/
		 
		clickOn(bimonthlyCheckBox, "CreateInvoicePage-> BiMonthly CheckBox");
		return invoiceNumber;
	}
	
	public String approveInvoiceInvExp(String supplierinvoiceNumberValue, String paymentTermsText) throws Exception {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		String invoiceNumber = findElementByXpath(invoicesXpath + 2 + apiNumberXpath).getText();
		clickOn(findElementByXpath(invoicesXpath + 2 + apiNumberXpath), "CreateInvoicesPage -> InvoiceNumber Link");
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		// clickOn(editButton, "TaxInvoicePage-> Edit Button");
		// typeInText(supplierInvoiceNumber, supplierinvoiceNumberValue,
		// "TaxInvoicePage-> SupplierInvoiceNumber" );
		// typeInText(paymentTerms, paymentTermsText, "TaxInvoicePage->
		// PaymentTerms");
		// clickOn(saveButton, "TaxInvoicePage-> Save Button");
		Thread.sleep(5000);
		clickOn(approveInvoice, "TaxInvoicePage-> Approve Invoice Button");
		System.out.println("done with approve");
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("switched to parent");
		/*
		 * Before clicking on FindInvoice, to activate parent window need to
		 * click on bimonthlyCheckBox element
		 */
		// clickOn(bimonthlyCheckBox, "CreateInvoicePage-> BiMonthly CheckBox");
		return invoiceNumber;
	}

}
