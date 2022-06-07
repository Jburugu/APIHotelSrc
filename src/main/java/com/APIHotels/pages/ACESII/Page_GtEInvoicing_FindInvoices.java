package com.APIHotels.pages.ACESII;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.TestException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_GtEInvoicing_FindInvoices extends BasePage {
		
	@FindBy(id = "findGTInvoice")
	private WebElement findInvoices;

	@FindBy(id = "locCdID")
	private WebElement city;

	@FindBy(css = "input[value='Get Supplier']")
	public WebElement getSupplierBtn;

	@FindBy(id = "selId")
	private WebElement supplier;

	@FindBy(xpath = "//*[@id='locCdID']//parent::div//ancestor::fieldset//tr[4]//input[@value='Search']")
	private WebElement findBySupplierNameSearchBtn;

	@FindBy(id = "invno")
	private WebElement findByAPIInvoiceNumber;

	@FindBy(xpath = "//*[@id='invno']/ancestor::fieldset//tr[2]//input[@value='Search']")
	private WebElement findByAPIInvoiceNumberSearchBtn;

	@FindBy(id = "monthid")
	private WebElement billingPeriod;

	@FindBy(id = "allInvoicesForBilling")
	private WebElement allStatuses;

	@FindBy(id = "invoiceStatusCode3")
	private WebElement openStatus;

	@FindBy(id = "invoiceStatusCode5")
	private WebElement approvedStatus;

	@FindBy(id = "voidInvoicesForBillingId")
	private WebElement voidStatus;

	@FindBy(id = "invoiceStatusCode7")
	private WebElement acceptedStatus;

	@FindBy(id = "invoiceStatusCode8")
	private WebElement rejectedStatus;

	@FindBy(id = "invoiceType4")
	private WebElement manualStatus;

	@FindBy(xpath = "//*[@id='voidInvoicesForBillingId']/parent::td/parent::tr/following-sibling::tr//input[@value='Search']")
	public WebElement billingPeriodSearchBtn;

	@FindBy(xpath = "//table[@id='findInvoice']//tbody//tr//td[6]")
	private WebElement statusInFindInvoicesTable;

	@FindBy(xpath = "//table[@id='findInvoice']")
	private WebElement invoicesTable;

	@FindBy(xpath = "//table[@id='findInvoice']//tbody//tr")
	private List<WebElement> recordsFoundInInvoicesTable;

	@FindBy(xpath = ".//*[@class='Found']")
	private WebElement invoiceFoundMessage;

	@FindBy(xpath = "//table[@id='findInvoice']//tr[1]/td[9]/a")
	private WebElement apiNumberLink;

	String apiNumberLinkXpath1 = "//table[@id='findInvoice']//tr[";
	String apiNumberLinkXpath2 = "]/td[9]/a";

	@FindBy(id = "convertedFlag")
	private WebElement currencyConversionChkBox;

	@FindBy(id = "ConversionRate")
	private WebElement conversionRate;

	@FindBy(name = "GtConCur")
	private WebElement currency;

	@FindBy(id = "PaymentSource")
	private WebElement paymentSource;

	@FindBy(id = "datepickerPaymentDate")
	private WebElement paymentDate;

	@FindBy(id = "conversionsSave")
	private WebElement convertButton;

	@FindBy(id = "conversionsSaveBack")
	private WebElement convertCancelButton;

	@FindBy(xpath = "//div[@class='invoiceInfo']//fieldset[2]//tr[1]/td[2]")
	private WebElement currencyText;

	@FindBy(xpath = "//table[@class='Border displayTable invoiceSummary']//b//parent::td//following-sibling::td/b")
	private WebElement totalAmountDueValue;

	@FindBy(xpath = "//table[@id='invoiceDetailsE']//tbody//tr[1]/td[10]")
	private WebElement rateInPickUpDetailsTable;

	String invoiceNotesXpath1 = "//table[@class='TabBorder']//tr[";
	String invoiceNotesXpath2 = "]//td[4]";

	@FindBy(xpath = "//table[@class='TabBorder']//tr")
	private List<WebElement> invoiceHistory;

	@FindBy(id = "buttonHistory")
	private WebElement historyButton;

	@FindBy(css = "input[value='Exit']")
	private WebElement exitButton;

	@FindBy(id = "addedPickupsTableButton")
	private WebElement addedPickupsAddButton;

	@FindBy(xpath = "//input[@class='datepicker hasDatepicker']")
	private WebElement pickUpDate;

	@FindBy(xpath = "//input[@class='apupu']")
	private WebElement pickUpLocation;

	@FindBy(xpath = "//input[@class='apudo']")
	private WebElement dropOffLocation;

	@FindBy(xpath = "//input[@class='addedPickupsPax']")
	private WebElement pax;

	@FindBy(xpath = "//input[@class='addedPickupRate']")
	private WebElement rate;

	@FindBy(id = "saveAlladdedPickupsButton")
	private WebElement saveAllAddedPickupsButton;

	@FindBy(id = "results_generated_added")
	private WebElement addedPickupsTable;

	@FindBy(id = "pickupMasterEditAdded")
	private WebElement editAddedPickUpsButton;

	@FindBy(xpath = "//input[@class='datepicker AddedPU hasDatepicker']")
	private WebElement pickUpDateEdit;

	@FindBy(xpath = "//input[@class='AddedPU editPickupPlaceNameAP']")
	private WebElement pickUpLocationEdit;

	@FindBy(xpath = "//input[@class='AddedPU editDropoffPlaceNameAP']")
	private WebElement dropOffLocationEdit;

	@FindBy(xpath = "//input[@class='AddedPU editPickupPaxAP']")
	private WebElement paxEdit;

	@FindBy(xpath = "//input[@class='AddedPU editPickupRateAP']")
	private WebElement rateEdit;

	@FindBy(id = "pickupMasterSaveEditAdded")
	private WebElement saveAllEditedAddedPickupsButton;

	@FindBy(id = "pickupMasterDeleteAdded")
	private WebElement deleteAddedPickUpsButton;

	@FindBy(xpath = "//input[@class='deleteCheckboxAdded']")
	private WebElement deleteCheckBox;

	@FindBy(id = "pickupMasterSaveDeleteAdded")
	private WebElement saveAfterDeleteAddedPickUpsButton;

	@FindBy(css = "input[name='EditPickupAdded']")
	private WebElement editPickupAddedChkBox;

	@FindBy(xpath = "//table[@class='Border displayTable byDaySummary']//tr[2]/td")
	private List<WebElement> noOfDatesInByDaySummaryTable;

	String dateInByDaySummaryXpath1 = "//table[@class='Border displayTable byDaySummary']//tr[2]/td[";
	String dateInByDaySummaryXpath2 = "]";

	String addedPickUpsInByDaySummaryXpath1 = "//table[@class='Border displayTable byDaySummary']//tr[8]/td[";
	String addedPickUpsInByDaySummaryXpath2 = "]";

	@FindBy(id = "addAdjustmentButton")
	private WebElement addAdjustmentsButton;

	@FindBy(xpath = "//*[@class='DropDown GtAdjustmentReason']")
	private WebElement adjustmentsReason;

	@FindBy(id = "adjustmentComments")
	private WebElement adjustmentComments;

	@FindBy(id = "adjustmentAmount")
	private WebElement adjustmentAmount;

	@FindBy(id = "saveAdjustmentButton")
	private WebElement saveAdjustmentButton;

	@FindBy(xpath = "//td[@class='CenterText invAdjListCBCR']")
	private WebElement adjutsmentsReason;

	@FindBy(xpath = "//table[@id='adjItem']//tfoot//td[4]")
	private WebElement amountInAdjustments;

	@FindBy(xpath = "//table[@class='Border displayTable invoiceSummary']//tr[5]/td[2]")
	private WebElement adjustmentsInInvoiceSummaryTable;

	@FindBy(xpath = "//*[@class='delIdAdj']")
	private WebElement selectAdjustmentsCheckBox;

	@FindBy(id = "deleteAdjustmentsButton")
	private WebElement deleteAdjustmentsButton;

	@FindBy(id = "addAttachmentsButton")
	private WebElement addAttachmentsButton;

	@FindBy(xpath = "//textarea[contains(@id,'comments')]")
	private WebElement commentsInAttachements;

	@FindBy(xpath = "//table[@id='addedAttachments']//tbody/tr/td/input[contains(@id,'formFile')]")
	private WebElement browseInAttachements;

	// table[@id='addedAttachments']//tbody/tr/td/input[contains(@id,'formFile')]

	@FindBy(xpath = "//button[@id='saveAttachmentsButton']")
	private WebElement saveAttachmentsButton;

	@FindBy(xpath = "//table[@id='attachmentItems']//tr//td/a")
	private WebElement fileNameInAttachments;

	@FindBy(xpath = "//table[@id='attachmentItems']//tr//td[contains(text(),'Delete')]")
	private WebElement deleteAttachmentsButton;

	@FindBy(xpath = "//table[@id='findInvoice']//tbody//tr//td[6]")
	private WebElement statusInFindInvoice;

	@FindBy(xpath = "//input[@id='gtFindInvoiceForm:invoiceNumberEntry:invoiceNumber']")
	private WebElement textbox_InvoiceNumber;

	@FindBy(xpath = "//span[contains(text(),'Find by Invoice Number')]/parent::legend/parent::fieldset/div/input[@value='search']")
	private WebElement btn_FindByInvoiceNumSearchBtn;

	@FindBy(xpath = "//table[@id='gtFindInvoiceForm:invoiceList']//tr/td[3]")
	private WebElement getStatusValue;

	// @FindBy(xpath = "//button[@id='addAttachmentsButtonNotaFiscal']")
	@FindBy(xpath = "//body/div/form[@name='ReviewGTInvoiceForm']/div[@class='Aces_Table']/div/div[4]/fieldset[1]/div[1]/div/button[text()='Add']")
	private WebElement addAttachmentBtnNotaFiscal;

	@FindBy(id = "commentsNF0")
	private WebElement commentsNotaFiscal;

	// @FindBy(id = "saveAttachmentsButtonNotaFiscal")
	@FindBy(xpath = "//body/div/form[@name='ReviewGTInvoiceForm']/div[@class='Aces_Table']/div/div[4]/fieldset[1]/div[1]/div/button[text()='Save']")
	private WebElement saveAttachmentBtnNotaFiscal;

	@FindBy(xpath = "//table[@id='attachmentItems']//td[3]/a")
	private WebElement attachementLink;

	@FindBy(xpath = "//input[@id='formFileNF0']")
	private WebElement clickBrowseBtn;

	@FindBy(xpath = "//div[@id='actionInvoice']//input[@value='Exit']")
	private WebElement clickExitBtn;

	@FindBy(xpath = "//table[@id='gtFindInvoiceForm:invoiceList']//tr/td[4]/a")
	private WebElement invoiceExportXmlfilelink;

	@FindBy(xpath = "//table[@id='gtFindInvoiceForm:invoiceList']//tr/td[5]/input")
	private WebElement exportInvoiceXMLCheckbox;

	@FindBy(xpath = "//input[@id='gtFindInvoiceForm:submit']")
	private WebElement btn_ExportToXML;

	@FindBy(how = How.XPATH, using = "//td[@id='iconnormlogout']")
	private WebElement logoutAirlines;
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

	@FindBy(xpath="//span[@class='pagebanner']")
	private WebElement paginationBanner;
	
	String pageLinksXPath1 = "//span[@class='pagelinks']/a[text()='";
	String pageLinksXPath2 = "']";
	
	@FindBy(xpath="//*[@id='invoiceDetailsE']//tbody/tr")
	private List<WebElement> recordsInPickupDetails;
	
	@FindBy(linkText="First")
	private WebElement firstLink;
	
	@FindBy(linkText="Prev")
	private WebElement PrevLink;
	
	@FindBy(linkText="Next")
	private WebElement NextLink;
	
	@FindBy(linkText="Last")
	private WebElement LastLink;
	
	@FindBy(id="pickupPageSize")
	private WebElement pageSize;
	
	@FindBy(xpath="//span[contains(text(),'CSV')]")
	private WebElement csvLink;

	@FindBy(xpath="//span[contains(text(),'Excel')]")
	private WebElement excelLink;

	@FindBy(xpath="//span[contains(text(),'PDF')]")
	private WebElement pdfLink;

	@FindBy(xpath="//span[contains(text(),'Print')]")
	private WebElement printLink;
	
	@FindBy(xpath="//button[@class='Aces_Btn nfDelete']")
	private WebElement button_DeleteNotaAttacht;

	@FindBy(xpath = "//td[@class='CenterText']//a")
	private List<WebElement> link_Attachment;

	static String[] toppings = new String[20];
	static String invoiceNo;
	static String supplierName;
	static String airportCode;
	static String gtInvoiceStatus;

	
	public EventFiringWebDriver driver = null;

	public Page_GtEInvoicing_FindInvoices(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnFindInvoicesLink() {
		clickOn(findInvoices, "GTInvoicing Link -> FindInvoices Link");
	}

	public void findInvoiceBySupplier(String cityCode, String supplierName) {
		waitForElementVisibility(city);
		typeInText(city, cityCode, "FindInvoices -> City");
		waitForElementVisibility(getSupplierBtn);
		clickOn(getSupplierBtn, "FindInvoices -> GetSupplier button");
		waitForElementVisibility(supplier);
		selectByVisibleText(supplier, supplierName, "FindInvoices -> Suppliers dropdown");
		waitForElementVisibility(findBySupplierNameSearchBtn);
		clickOn(findBySupplierNameSearchBtn, "FindInvoices -> FindBy button");
		waitForElementVisibility(invoicesTable);
		verifyRecordsPresentInInvoicesTable();
	}

	public void findInvoiceByInvoiceNumber(String invoiceNumber) {
		typeInText(findByAPIInvoiceNumber, invoiceNumber, "FindInvoices -> APIInvoiceNumber");
		clickOn(findByAPIInvoiceNumberSearchBtn, "FindInvoices -> Search button");
		waitForElementVisibility(invoicesTable);
		verifyRecordsPresentInInvoicesTable();
	}

	/*
	 * public void verifyInvoiceStatuses(List<String> invoiceNumber, String
	 * expectedStatus) { List<String> expectedStatuses = new
	 * ArrayList<String>(Arrays.asList(expectedStatus.split(","))); for (int i =
	 * 0; i < invoiceNumber.size(); i++) {
	 * findInvoiceByInvoiceNumber(invoiceNumber.get(i));
	 * verifyInvoiceStatus(invoiceNumber.get(i), expectedStatuses.get(i)); } }
	 */
	public void verifyInvoiceStatus(String invoiceNumber, String expectedStatus) {
		String invoiceStatus = statusInFindInvoice.getText();
		if (invoiceStatus.equalsIgnoreCase(expectedStatus)) {
			System.out.println(invoiceNumber + "Invoice is in " + expectedStatus + " Status");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Status changed is matched with status in find Invoice page" + invoiceStatus);
		} else
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Status changed did not match with status in find Invoice page. Expected: " + expectedStatus
							+ "; Found:" + invoiceStatus);
	}

	public void findByBillingPeriodAndStatus(String billingMonth) throws InterruptedException {
		selectByVisibleText(billingPeriod, billingMonth, "FindInvoices -> Billing Period Dropdown");
		clickOn(allStatuses, "FindInvoices -> All Checkbox");
		waitForElementVisibility(billingPeriodSearchBtn);
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		waitForElementVisibility(invoicesTable);
		verifyRecordsPresentInInvoicesTable();
		// clickOn(allStatuses);
		Thread.sleep(3000);

		clickOn(openStatus, "FindInvoices -> Open Checkbox" );
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		waitForElementVisibility(invoicesTable);
		verifyRecordsPresentInInvoicesTable();
		clickOn(openStatus, "FindInvoices -> Open Checkbox" );
		Thread.sleep(3000);

		clickOn(approvedStatus, "FindInvoices -> Approved Checkbox");
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		waitForElementVisibility(invoicesTable);
		verifyRecordsPresentInInvoicesTable();
		clickOn(approvedStatus, "FindInvoices -> Approved Checkbox");
		Thread.sleep(3000);

		clickOn(voidStatus, "FindInvoices -> Void Checkbox");
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		waitForElementVisibility(invoicesTable);
		verifyRecordsPresentInInvoicesTable();
		clickOn(voidStatus, "FindInvoices -> Void Checkbox");
		Thread.sleep(3000);

		Thread.sleep(1000);
		clickOn(acceptedStatus, "FindInvoices -> Accepted Checkbox");
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		verifyRecordsPresentInInvoicesTable();
		clickOn(acceptedStatus, "FindInvoices -> Accepted Checkbox");

		Thread.sleep(1000);
		clickOn(rejectedStatus, "FindInvoices -> Rejected Checkbox");
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		verifyRecordsPresentInInvoicesTable();
		clickOn(rejectedStatus, "FindInvoices -> Rejected Checkbox");

		Thread.sleep(1000);
		clickOn(manualStatus, "FindInvoices -> Manual Checkbox");
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Billing Period dropdown");
		verifyRecordsPresentInInvoicesTable();
		clickOn(manualStatus, "FindInvoices -> Manual Checkbox");
	}

	public void verifyRecordsPresentInInvoicesTable() {
		Assert.assertTrue(recordsFoundInInvoicesTable.size() >= 1, "Invoice Found");
	}

	public void verifyStatusOfInvoice(List<String> invoiceNumber, String approvedStatus, String voidStatus) {
		for (int i = 0; i < invoiceNumber.size(); i++) {
			typeInText(findByAPIInvoiceNumber, invoiceNumber.get(i), "FindInvoicePage-> APIInvoiceNumber");
			clickOn(findByAPIInvoiceNumberSearchBtn, "FindInvoices -> FindBy Button");
			String findInvoiceStatus = statusInFindInvoicesTable.getText();
			if (findInvoiceStatus.equalsIgnoreCase(approvedStatus)) {
				System.out.println(invoiceNumber.get(i) + "Invoice is in approved Status");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Status changed is matched with status in find Invoice page" + findInvoiceStatus);
			} else if (findInvoiceStatus.equalsIgnoreCase(voidStatus)) {
				System.out.println(invoiceNumber.get(i) + "Invoice is in void Status");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Status changed is matched with status in find Invoice page" + findInvoiceStatus);
			} else
				Assert.fail("Status changed did not matched with status in find Invoice page" + findInvoiceStatus);
		}
	}

	public void clickOnfirstInvoiceApiNumber() {
		clickOn(apiNumberLink, "FindInvoices -> InvoiceNumber Link in Table");
	}

	public void findByOpenStatus(String billingMonth) {
		waitForElementVisibility(billingPeriod);
		selectByVisibleText(billingPeriod, billingMonth, "FindInvoices ->BillingPeriod dropdown");
		clickOn(allStatuses, "FindInvoices -> All Checkbox");
		clickOn(openStatus,"FindInvoices -> Open Checkbox");
		clickOn(billingPeriodSearchBtn, "FindInvoices -> Search Button");
		waitForElementVisibility(invoicesTable);
	}

	public void verifyCurrencyConversion(String billingMonth, String conversionRateValue, String currencyValue,
			String paymentSourceValue, String paymentDateValue) throws Exception {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		for (int i = 1; i < recordsFoundInInvoicesTable.size(); i++) {
			clickOn(findElementByXpath(apiNumberLinkXpath1 + i + apiNumberLinkXpath2), "TaxInvoicePage -> APIInvoiceNumberLink");
			switchToNewWindow(parentHandle);
			waitForPageToLoad("30");
			if (currencyConversionChkBox.isSelected()) {
				clickOn(exitButton, "TaxInvoicePage -> Exit Button");
			}
		}
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		if (currencyConversionChkBox.isSelected()) {
			clickOn(currencyConversionChkBox, "TaxInvoicePage -> Show Currency Conversion options for Invoice Checkbox");
			waitForPageToLoad("20");
		}

		// Get currency text before conversion
		String currencyBeforeConversionText = currencyText.getText();
		System.out.println(currencyBeforeConversionText);
		// Get Total Amount Due from Invoice Summary before Conversion
		String totalAmountDueBeforeConversion = totalAmountDueValue.getText();
		System.out.println(totalAmountDueBeforeConversion);
		// Get Rate from PickUp Details table before Conversion
		String rateBeforeConversion = rateInPickUpDetailsTable.getText();
		System.out.println(rateBeforeConversion);

		// Currency Conversion section will display only when Show Currency
		// Conversion Options for invoice is checked
		clickOn(currencyConversionChkBox, "TaxInvoicePage -> Show Currency Conversion options for Invoice Checkbox");
		waitForPageToLoad("20");

		// Enter Values in Currency Conversion section and Save
		SaveCurrencyConversion(conversionRateValue, currencyBeforeConversionText, paymentSourceValue, paymentDateValue);

		// Get currency after conversion and validate current conversion
		String currencyAfterConversionText = currencyText.getText();
		System.out.println(currencyAfterConversionText);
		if (!currencyAfterConversionText.equals(currencyBeforeConversionText)) {
			System.out.println("Currency code from " + currencyBeforeConversionText + " successfully changed to "
					+ currencyAfterConversionText);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Currency code from " + currencyBeforeConversionText
					+ "successfully changed to " + currencyAfterConversionText);
		}

		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Currency conversion from " + currencyBeforeConversionText + 
			"to" + currencyAfterConversionText + " is failed");
			Assert.fail("Currency conversion from " + currencyBeforeConversionText + "to" + currencyAfterConversionText
					+ " is failed");
		}

		// Get Total Amount Due after conversion and verify the change in Amount
		String totalAmountDueAfterConversion = totalAmountDueValue.getText();
		System.out.println(totalAmountDueAfterConversion);
		if (!totalAmountDueAfterConversion.equals(totalAmountDueBeforeConversion)) {
			System.out.println("Total Amount Due" + totalAmountDueBeforeConversion + " successfully changed to "
					+ totalAmountDueAfterConversion);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Total Amount Due" + totalAmountDueBeforeConversion
					+ "successfully changed to " + totalAmountDueAfterConversion);
		}

		else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"Total Amount Due is failed to change to required currency" + totalAmountDueBeforeConversion
					+ " ," + totalAmountDueAfterConversion);
			Assert.fail("Total Amount Due is failed to change to required currency" + totalAmountDueBeforeConversion
					+ " ," + totalAmountDueAfterConversion);
		}

		// Get Rate From PickUp details table after conversion and verify change in rate
		String rateAfterConversion = rateInPickUpDetailsTable.getText();
		System.out.println(rateAfterConversion);
		if (!rateAfterConversion.equals(rateBeforeConversion)) {
			System.out.println("Rate" + rateAfterConversion + " successfully changed to " + rateBeforeConversion);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Rate in Pickup details" + rateBeforeConversion + "successfully changed to " + rateAfterConversion);
		}

		else {
			ExtentTestManager.getTest().log(LogStatus.PASS,	"Rate conversion is failed to required currency" + rateBeforeConversion + " ,"
					+ rateAfterConversion);
			Assert.fail("Rate conversion is failed to required currency" + rateBeforeConversion + " ,"
					+ rateAfterConversion);
		}

		// verify History, part of Invoice Action test case is covered here
		clickOn(historyButton, "TaxInvoicePage -> History Button");
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		for (int i = 2; i < invoiceHistory.size() - 1; i++) {
			String invoiceNotesText = findElementByXpath(invoiceNotesXpath1 + i + invoiceNotesXpath2).getText();
			if (invoiceNotesText.contains("conversion converted")) {
				System.out.println("conversion converted text displayed in history");
				break;
			} else {
				Assert.fail("Currency conversion from " + currencyBeforeConversionText + "to"
						+ currencyAfterConversionText + " is failed");
			}

			// verify History, part of Invoice Action test case is covered here
			verifyHistory(parentHandle);
		}
	}

	// Enter Values in Currency Conversion section and Save
	private void SaveCurrencyConversion(String conversionRateValue, String currencyBeforeConversionText,
			String paymentSourceValue, String paymentDateValue) throws InterruptedException {
		// Enter Values in Currency Conversion section and Save
		typeInText(conversionRate, conversionRateValue, "TaxInvoicePage -> ConversionRate");
		Select curen = new Select(currency);
		List<WebElement> currencyCodes = curen.getOptions();
		if (!currencyBeforeConversionText.equals(currencyCodes.get(1).getText()))
			selectByVisibleText(currency, currencyCodes.get(1).getText());
		else
			selectByVisibleText(currency, currencyCodes.get(2).getText());
		typeInText(paymentSource, paymentSourceValue, "TaxInvoicePage -> PaymentSource");
		typeInText(paymentDate, paymentDateValue, "TaxInvoicePage -> PaymentDate");
		clickOn(convertButton, "TaxInvoicePage -> Convert Button");
		Thread.sleep(5000);
	}

	private void verifyHistory(List<String> parentHandle) {
		clickOn(historyButton, "TaxInvoicePage -> History Button");
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		for (int i = 2; i < invoiceHistory.size() - 1; i++) {
			String invoiceNotesText = findElementByXpath(invoiceNotesXpath1 + i + invoiceNotesXpath2).getText();
			if (invoiceNotesText.contains("conversion converted")) {
				System.out.println("conversion converted text displayed in history");
				ExtentTestManager.getTest().log(LogStatus.PASS, "conversion converted text displayed in history");
				break;
			} else
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Conversion did not show up in history hence history functionality is not working");
				Assert.fail("Conversion did not show up in history hence history functionality is not working");
		}
	}

	public void verifyAddedPickUpsActions(String pickupDate, String pickupLocation, String dropoffLocation,
			String paxValue, String rateValue) throws InterruptedException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");

		clickOn(addedPickupsAddButton, "TaxInvoicePage -> Add Button");
		typeInText(pickUpDate, pickupDate, "TaxInvoicePage -> PickUp Calendar");
		typeInText(pickUpLocation, pickupLocation, "TaxInvoicePage -> PickupLocation");
		typeInText(dropOffLocation, dropoffLocation, "TaxInvoicePage -> DropoffLocation");
		typeInText(pax, paxValue, "TaxInvoicePage -> Pax");
		typeInText(rate, rateValue, "TaxInvoicePage -> Rate");
		// Saves Added PickUps
		clickOn(saveAllAddedPickupsButton, "TaxInvoicePage -> Save Button");
		waitForElementVisibility(addedPickupsTable);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Added PickUps Saved Successfully");
		verifyAddedPickUpsInByDaySummaryTable(pickupDate);
		// Edits Added PickUps
		clickOn(editAddedPickUpsButton, "TaxInvoicePage -> Edit Button");
		waitForElementVisibility(editPickupAddedChkBox);
		clickOn(editPickupAddedChkBox, "TaxInvoicePage -> Edit Checkbox");
		waitForElementVisibility(pickUpDateEdit);
		typeInText(pickUpDateEdit, pickupDate, "TaxInvoicePage -> PickUp Calendar");
		typeInText(pickUpLocationEdit, pickupLocation, "TaxInvoicePage -> PickupLocation");
		typeInText(dropOffLocationEdit, dropoffLocation, "TaxInvoicePage -> DropoffLocation");
		typeInText(paxEdit, paxValue, "TaxInvoicePage -> Pax");
		// typeInText(rateEdit, rateValue, "TaxInvoicePage -> Rate");
		rateEdit.sendKeys(Keys.chord(Keys.CONTROL, "a"), rateValue);
		clickOn(saveAllEditedAddedPickupsButton, "TaxInvoicePage -> Save Button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edited Added PickUps Successfully");
		// Deletes Added PickUps
		waitForElementVisibility(deleteAddedPickUpsButton);
		clickOn(deleteAddedPickUpsButton,"TaxInvoicePage -> Delete Button");
		clickOn(deleteCheckBox, "TaxInvoicePage -> Delete Checkbox");
		clickOn(saveAfterDeleteAddedPickUpsButton, "TaxInvoicePage -> Save Button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Deleted Added PickUps Successfully");
		waitForElementVisibility(exitButton);
		clickOn(exitButton, "TaxInvoicePage -> Exit Button");
	}

	// Verify count of added pickups in By Day Summary Table
	public void verifyAddedPickUpsInByDaySummaryTable(String pickupDate) {
		String dateValue = pickupDate.substring(0, 2);
		System.out.println(dateValue);
		List<WebElement> dates = noOfDatesInByDaySummaryTable;
		System.out.println(dates);
		if (dates.size() > 1) {
			for (int datescount = 1; datescount < dates.size(); datescount++) {
				String date = findElementByXpath(dateInByDaySummaryXpath1 + datescount + dateInByDaySummaryXpath2)
						.getText();
				if (date.equalsIgnoreCase(dateValue)) {
					String addedPickUpsCount = findElementByXpath(
							addedPickUpsInByDaySummaryXpath1 + datescount + addedPickUpsInByDaySummaryXpath2).getText();
					System.out.println(addedPickUpsCount);
					if (addedPickUpsCount.equalsIgnoreCase("1")) {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"No. Of Added Pickups on date " + dateValue + " is " + addedPickUpsCount);
					}
				}
			}
		} else
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Added PickUps count is not displayed in By Day Summary Table");

	}

	public void verifyAdjustmentsActions(String reason, String comments, String amount) throws Exception {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		clickOn(addAdjustmentsButton, "TaxInvoicePage -> Add Button");
		selectByVisibleText(adjustmentsReason, reason, "TaxInvoicePage -> Reason dropdown");
		typeInText(adjustmentComments, comments, "TaxInvoicePage -> Comments");
		typeInText(adjustmentAmount, amount, "TaxInvoicePage -> Amount");
		clickOn(saveAdjustmentButton, "TaxInvoicePage -> Save Button");
		waitForElementVisibility(adjutsmentsReason);
		String reasonInAdjustments = adjutsmentsReason.getText();
		if (reasonInAdjustments.equalsIgnoreCase(reason)) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Reason for Adjustments is added into Adjustments Section");
		} else
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Reason for Adjustments is not added into Adjustments Section");

		// Get Total Amount from Adjustments Sections and compare with
		// adjustments in Invoice Summary.
		String totalAmount = amountInAdjustments.getText();
		System.out.println(totalAmount);
		String adjustmentsInInvoiceSummary = adjustmentsInInvoiceSummaryTable.getText();
		System.out.println(adjustmentsInInvoiceSummary);
		if (totalAmount.equalsIgnoreCase(adjustmentsInInvoiceSummary))
			ExtentTestManager.getTest().log(LogStatus.PASS, "Adjustments In Invoice Summary showing correctly");
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Adjustments In Invoice Summary showing wrongly");
		clickOn(selectAdjustmentsCheckBox, "TaxInvoicePage -> Adjustmenst Checkbox");
		clickOn(deleteAdjustmentsButton, "TaxInvoicePage -> Delete Button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Adjustments Deleted Successfully");
		waitForElementVisibility(exitButton);
		// clickOn(exitButton);
	}

	public void addAttachement(String commentsForAttachement) throws IOException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		clickOn(addAttachmentsButton, "TaxInvoicePage -> Add Button");
		typeInText(commentsInAttachements, commentsForAttachement, "TaxInvoicePage -> Comments");
		Actions builder = new Actions(driver);
		builder.doubleClick(browseInAttachements).perform();
		System.out.println("clicked On browse");
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\lib\\uploadFileIE.exe" + " "
				+ System.getProperty("user.dir") + "\\lib\\test.docx");
		clickOn(saveAttachmentsButton, "TaxInvoicePage -> Save Button");
		waitForElementVisibility(fileNameInAttachments);
		String fileName = fileNameInAttachments.getText();
		if (fileName.equalsIgnoreCase("test.docx")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Attachment Added Successfully");
		} else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to add Attachment");
	}

	public void findInvoiceByNumber(String invoiceNum, String status) {
		typeInText(textbox_InvoiceNumber, invoiceNum, "FindInvoicePage -> InvoiceNumber");
		clickOn(btn_FindByInvoiceNumSearchBtn, "FindInvoicePage -> SearchButton" );
		Assert.assertEquals(getStatusValue.getText(), status);
	}

	@FindBy(xpath = "//tbody[@id='gtFindInvoiceForm:invoiceList:tb']//tr/td[4]/a")
	private WebElement xmlFileLink;

	public void downloadXMLFileAndValidate(String invoiceNumber, String supplier, String city, String inovieStatus) throws ParserConfigurationException, SAXException, IOException {
		 clickOn(xmlFileLink);
				String expectedFileName = invoiceNumber + ".xml";
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Path path = Paths.get(downloadFilepath);
		File fileToCheck = path.resolve(expectedFileName).toFile();
		System.out.println(fileToCheck);
		String FileDownloaded = fileToCheck.getName();
	
		
		/*
		 * Checks for required file in downloaded_files. If not exists, wait till names
		 * of expected filename and file to be download are equal.
		 * 
		 */
		if (!fileToCheck.exists()) {

			ExpectedCondition<Boolean> elementTextEqualsStringIgnoreCase = arg0 -> expectedFileName
					.equalsIgnoreCase(FileDownloaded);
			wait.until(elementTextEqualsStringIgnoreCase);
		}
		System.out.println("File Downloaded Successfully");
		String[] attValues = readXMLAttributeValues(path+"\\"+FileDownloaded);
		Assert.assertEquals(attValues[0].equals(invoiceNumber), true);
		Assert.assertEquals(attValues[1].equals(supplier), true);
		Assert.assertEquals(attValues[2].equals(city), true);
		Assert.assertEquals(attValues[3].equals(inovieStatus), true);
		File newFile = new File(downloadFilepath+"\\"+invoiceNumber+"_" +System.currentTimeMillis()+".xml");
		System.out.println(newFile);
		fileToCheck.renameTo(newFile);
		System.out.println("Rename of file is done");
	}
	
	
	

	public void addNotaFisAttToInvoice(String invoiceNum) throws IOException, Exception {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(findElementByPartialLinkText(invoiceNum), "TaxInvoicePage -> InvoiceNumber");
		switchToNewWindow(parentHandle);
		Thread.sleep(5000);
		waitForElementVisibility(addAttachmentBtnNotaFiscal);
		clickOn(addAttachmentBtnNotaFiscal, "TaxInvoicePage -> Add Button");
		Thread.sleep(3000);
		typeInText(commentsNotaFiscal, "test", "TaxInvoicePage -> Comments Button");
		unExpectedAcceptAlert();
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.doubleClick(clickBrowseBtn).perform();
		Thread.sleep(2000);
		System.out.println("clicked On Browse bttn");
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\lib\\uploadFileIE.exe" + " "
				+ System.getProperty("user.dir") + "\\lib\\test.docx");
		unExpectedAcceptAlert();
		Thread.sleep(2000);
		clickOn(saveAttachmentBtnNotaFiscal, "TaxInvoicePage -> Save Button");
		unExpectedAcceptAlert();
		Thread.sleep(2000);
		if (saveAttachmentBtnNotaFiscal.isDisplayed())
			clickOn(saveAttachmentBtnNotaFiscal);
		waitForElementVisibility(attachementLink);
		System.out.println("Attachement got added to Nota Fiscal Section");
		clickOn(clickExitBtn, "TaxInvoicePage -> Exit Button");
		System.out.println("done with Exit");
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("switched to parent");

	}

	public void verifyInvoiceExportElementsCheck() {
		waitForElementVisibility(invoiceExportXmlfilelink);
		waitForElementVisibility(exportInvoiceXMLCheckbox);
		waitForElementVisibility(btn_ExportToXML);
	}

	public void checkXMLElementVisibiltyOnNoTrigger() {
		if (driver.findElements(By.xpath("//table[@id='gtFindInvoiceForm:invoiceList']//tr/td[4]/a")).size() > 0)
			throw new TestException("Export XML file link should not be displayed");
		if (driver.findElements(By.xpath("//table[@id='gtFindInvoiceForm:invoiceList']//tr/td[5]/input")).size() > 0)
			System.out.println("Export XML checkbox should not be displayed ");
	}

	public void clickLogoutAirlines() throws Exception {
		clickOn(logoutAirlines, "AirlinesHomePage -> Logout Link");
	}

	public void approveInvoice(String supplierinvoiceNumberValue, String paymentTermsText) throws Exception {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		clickOn(editButton, "TaxInvoicePage -> Edit Button");
		typeInText(supplierInvoiceNumber, supplierinvoiceNumberValue, "TaxInvoicePage -> SupplierInvoiceNumber");
		typeInText(paymentTerms, paymentTermsText, "TaxInvoicePage -> PaymentTerms"); 
		clickOn(saveButton, "TaxInvoicePage -> Save Button");
		Thread.sleep(5000);
		clickOn(approveInvoice, "TaxInvoicePage ->  ApproveInvoice Button");
		System.out.println("done with approve");
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("switched to parent");
		clickOn(findByAPIInvoiceNumber, "TaxInvoicePage -> FindBy Button");
	}

	public void pickUpPaginationAndPageSize() throws InterruptedException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		
		String totalCountText = paginationBanner.getText();
		System.out.println("Pagination string :" + totalCountText);
		String totalpickupsCount = null;
		int spacePos = totalCountText.indexOf(" ");
		if (spacePos > 0) {
			totalpickupsCount = totalCountText.substring(0, spacePos - 0);
			System.out.println(totalpickupsCount);
		}
		double m = 10;
		double y = (Double.parseDouble(totalpickupsCount) / m);
		BigDecimal value = new BigDecimal(y);
		value = value.setScale(0, RoundingMode.UP);
		System.out.println("Rounding Mod UP :" + value);
		int size = value.intValue();
		System.out.println("Link size :" + size);
		List<WebElement> recordsinEachPage=recordsInPickupDetails;
		int count =10;
		if (size > 0) {
			System.out.println("pagination exists");
			// click on pagination link
			for (int i = 1; i < size; i++) {
				clickOn(findElementByXpath(pageLinksXPath1 +(i+1) + pageLinksXPath2), "TaxInvoicePage -> Pagination Links");
				waitForPageToLoad("20");
				count += recordsinEachPage.size();
				
			}
			System.out.println(count);
		clickOn(firstLink, "TaxInvoicePage -> FirstPagination Link");
		waitForPageToLoad("20");
		clickOn(NextLink, "TaxInvoicePage -> NextPagination Link");
		waitForPageToLoad("20");
		clickOn(PrevLink, "TaxInvoicePage -> PrevPagination Link");
		waitForPageToLoad("20");
		clickOn(LastLink, "TaxInvoicePage -> LastPagination Link");
		waitForPageToLoad("20");
		clickOn(firstLink, "TaxInvoicePage -> FirstPagination Link");
		waitForPageToLoad("20");
		
		} else 
		System.out.println("pagination not exists");
	
		int totalCount= Integer.parseInt(totalpickupsCount);
	if(totalCount==count)
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Pagination is working, as count in no. of records " + totalCount +" is matched with total pickup in pickup details table " +count);
	else 
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Total pickups count does not match with pageStotal no of records");
		Assert.fail("Total pickups count does not match with pageStotal no of records");

	}
	
	public void verifyPageSize(String pageSizes) throws InterruptedException{
		/*List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOnfirstInvoiceApiNumber();
		switchToNewWindow(parentHandle);
		waitForPageToLoad("30");
		*/
		List<String> pageSizeList= new ArrayList<String>(Arrays.asList(pageSizes.split(",")));
		for (int i=0;i<pageSizeList.size(); i++){
		selectByVisibleText(pageSize, pageSizeList.get(i));
		Thread.sleep(20000);
		
		String pagesizeText = paginationBanner.getText();
		System.out.println("Pagination string :" + pagesizeText);		
		String reqPageSizes= pagesizeText.substring(pagesizeText.length()-3).replace(".", "");
		System.out.println(reqPageSizes);
		int reqPageSize= Integer.parseInt(reqPageSizes);
		System.out.println(reqPageSize);
		
		int pageSizeInDropdown=Integer.parseInt(pageSizeList.get(i));
		if(pageSizeInDropdown==reqPageSize)
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Select page Size displayed in page size indicator text");
			else Assert.fail("Pickup details are not displayed as per selected page size");
		}
	}
	
	public void verifyExportOptions() throws InterruptedException{
		clickOn(csvLink, "TaxInvoicePage -> CSV Link");
		Thread.sleep(2000);
		clickOn(excelLink, "TaxInvoicePage -> Excel Link");
		Thread.sleep(2000);
		clickOn(pdfLink, "TaxInvoicePage -> PDF Link");
		Thread.sleep(2000);
		clickOn(printLink, "TaxInvoicePage -> Print Link");
		Thread.sleep(2000);
		}
	
	public static String[] readXMLAttributeValues(String path)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new File(path));
		NodeList nodeListHeader = document.getElementsByTagName("InvoiceHeader");
		NodeList nodeList = document.getElementsByTagName("Supplier");
		toppings = new String[20];
		for (int x = 0, size = nodeList.getLength(); x < size; x++) {
			invoiceNo = nodeList.item(x).getAttributes().getNamedItem("InvoiceNumber").getNodeValue();
			toppings[0] = invoiceNo;
			supplierName = nodeList.item(x).getAttributes().getNamedItem("Name").getNodeValue();
			toppings[1] = supplierName;
		}
		for (int y = 0, size = nodeListHeader.getLength(); y < size; y++) {
			airportCode = nodeListHeader.item(y).getAttributes().getNamedItem("AirportCode").getNodeValue();
			toppings[2] = airportCode;
			gtInvoiceStatus = nodeListHeader.item(y).getAttributes().getNamedItem("GTInvoiceStatus").getNodeValue();
			toppings[3] = gtInvoiceStatus;
		}
		return toppings;
	}
	
	public void downloadXMLFile(String apiNumber) throws InterruptedException{
		// clickOn(xmlFileLink);
		/*xmlFileLink.getAttribute("href");
		System.out.println(xmlFileLink.getAttribute("href"));
		getDriver().get(xmlFileLink.getAttribute("href"));
		Thread.sleep(10000);

		clickOn(exportInvoiceXMLCheckbox);
		clickOn(btn_ExportToXML);*/
		clickOn(xmlFileLink);
		Thread.sleep(10000);

	}
	
	public void deleteNotaFisAttachment(String invoiceNum) throws InterruptedException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(findElementByPartialLinkText(invoiceNum), "TaxInvoicePage -> InvoiceNumber");
		switchToNewWindow(parentHandle);
		Thread.sleep(5000);
		waitForElementVisibility(button_DeleteNotaAttacht);
		clickOn(button_DeleteNotaAttacht, "TaxInvoicePage -> Delete Button");
		unExpectedAcceptAlert();
		Thread.sleep(3000);
		if(link_Attachment.size() > 0 )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on Delete button, attachment still appers on the invoice");
	}else
		{
		ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on Delete button, attachment got deleted from the invoice");
		}
		clickOn(exitButton, "TaxInvoicePage -> Exit Button");
		Thread.sleep(3000);
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("switched to parent");
	}
}


