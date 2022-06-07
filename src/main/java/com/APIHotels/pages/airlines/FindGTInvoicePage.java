package com.APIHotels.pages.airlines;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class FindGTInvoicePage extends BasePage {

	public EventFiringWebDriver driver = null;
	// ACCOUNTING -- GROUND TRANSPORTATION -- FIND GT INVOICE
	// (AZUL/AIRNEWZEALAND)

	@FindBy(how = How.ID, using = "gtFindInvoiceForm:locationCodeEntry:locationCode")
	public WebElement city;

	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Get Supplier')]")
	public WebElement getSuppliers;

	@FindBy(how = How.ID, using = "gtFindInvoiceForm:supplierEntry:supplierId")
	public WebElement supplier;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'supplierEntry')]/following-sibling::div/input[@value='Search']")
	public WebElement findBySupplierNameSearchButton;

	@FindBy(how = How.ID, using = "gtFindInvoiceForm:invoiceNumberEntry:invoiceNumber")
	public WebElement apiInvoiceNo;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'invoiceNumberEntry')]/following-sibling::div/input[@value='search']")
	public WebElement findByInvoiceNumberSearchButton;

	@FindBy(how = How.ID, using = "gtFindInvoiceForm:calendarMonth:bidPeriods")
	public WebElement billingPeriod;

//	@FindBy(how = How.XPATH, using = "//label[text()='All']/preceding-sibling::input[@type='checkbox']")
	@FindBy(how = How.ID, using = "gtFindInvoiceForm:status:0")
	public WebElement findByBillingStatusALLCheckBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Open']/preceding-sibling::input[@type='checkbox']")
	public WebElement findByBillingStatusOPENCheckBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Approved']/preceding-sibling::input[@type='checkbox']")
	public WebElement findByBillingStatusAPPROVEDCheckBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Void']/preceding-sibling::input[@type='checkbox']")
	public WebElement findByBillingStatusVOIDCheckBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Accepted']/preceding-sibling::input[@type='checkbox']")
	public WebElement findByBillingStatusACCEPTEDCheckBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Rejected']/preceding-sibling::input[@type='checkbox']")
	public WebElement findByBillingStatusREJECTEDCheckBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Manual']/preceding-sibling::input[@type='checkbox']")
	public WebElement findByBillingStatusMANUALCheckBox;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'calendarMonth')]/following-sibling::div/input[@value='Search']")
	public WebElement findByBillingPeriodAndStatusSearchButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='waitHeader']")
	public WebElement waitHeader;

	@FindBy(xpath = "//div[@id='content']//tr//tr/td/input[@type='checkbox']")
	private List<WebElement> checkbox_StatusList;

	@FindBy(xpath = "//span[@id='gtFindInvoiceForm:searchResult']//tr")
	private List<WebElement> recordsFoundInInvoicesTable;

	@FindBy(xpath = "//span[@id='gtFindInvoiceForm:searchResult']//tr//td[11]/a")
	private WebElement apiNumberLink;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Wait Please...')]")
	public WebElement waitPleaseOverlay;

	@FindBy(xpath = "//table[@class='dr-table rich-table']//tr/td[4]/a")
	private WebElement link_ExportXML;
	
	@FindBy(xpath = "//table[@class='dr-table rich-table']//tr/td[4]/a")
	private List<WebElement> link_ExportXMLList;

	@FindBy(xpath = "//table[@class='dr-table rich-table']//tr/td[5]/input")
	private WebElement checkbox_ExportXML;
	
	@FindBy(xpath = "//table[@class='dr-table rich-table']//tr/td[5]/input")
	private List<WebElement> checkbox_ExportXMLList;
	
	static String[] toppings = new String[20];
	static String invoiceNo;
	static String supplierName;
	static String airportCode;
	static String gtInvoiceStatus;

	public FindGTInvoicePage(EventFiringWebDriver driver) {
		this.driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	public void findBySupplierName(String cityValue, String supplierValue) {
		typeInText(city, cityValue);
		clickOn(getSuppliers);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int supplierVal = Integer.parseInt(supplierValue);
		selectByIndex(supplier, supplierVal);
		waitForElementVisibility(findBySupplierNameSearchButton);
	}

	public void findByInvoiceNumber(String InvoiceNumberValue) {
		typeInText(apiInvoiceNo, InvoiceNumberValue);
		waitForElementVisibility(findByInvoiceNumberSearchButton);

	}

	public void findByBillingPeriodAndStatus(String billingPeriodValue) {
		int billingPeriodVal = Integer.parseInt(billingPeriodValue);
		selectByIndex(billingPeriod, billingPeriodVal);

		waitForElementVisibility(findByBillingStatusALLCheckBox);
		Assert.assertTrue(findByBillingStatusALLCheckBox.isSelected(), "All checkbox is not selected");

		clickOn(findByBillingStatusOPENCheckBox);
		Assert.assertTrue(findByBillingStatusOPENCheckBox.isSelected(), "Open checkbox is not selected");

		clickOn(findByBillingStatusAPPROVEDCheckBox);
		Assert.assertTrue(findByBillingStatusAPPROVEDCheckBox.isSelected(), "Approved checkbox is not selected");

		clickOn(findByBillingStatusVOIDCheckBox);
		Assert.assertTrue(findByBillingStatusVOIDCheckBox.isSelected(), "Void checkbox is not selected");

		clickOn(findByBillingStatusACCEPTEDCheckBox);
		Assert.assertTrue(findByBillingStatusACCEPTEDCheckBox.isSelected(), "Accepted checkbox is not selected");

		clickOn(findByBillingStatusREJECTEDCheckBox);
		Assert.assertTrue(findByBillingStatusREJECTEDCheckBox.isSelected(), "Rejected checkbox is not selected");

		clickOn(findByBillingStatusMANUALCheckBox);
		Assert.assertTrue(findByBillingStatusMANUALCheckBox.isSelected(), "Manual checkbox is not selected");

		waitForElementVisibility(findByBillingPeriodAndStatusSearchButton);
	}

	public void verifyOtherInvoiceStatuses(String inoviceMonth, String invoiceNumber) throws IOException {
		selectByVisibleText(billingPeriod, inoviceMonth);
		
		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifing Invoice Status in All option");
		clickOn(findByBillingStatusALLCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		System.out.println(apiNumberLink.getText());
		if (apiNumberLink.getText().equals(invoiceNumber)) {
			System.out.println("Invoice found wiht Status as All- valid scenario");
			validateVisibilityOfExportXMLElements();
			takeScreenshots();
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Verifing Invoice Status in Approved status option");
			Assert.fail("Inovie number not found in All status");
		}
		
		
		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Inovie number checking for Open status");
		clickOn(findByBillingStatusOPENCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		takeScreenshots();
		if (recordsFoundInInvoicesTable.size() > 1) {
			if (apiNumberLink.getText().equals(invoiceNumber))
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Inovie number not found in Open status");
				Assert.fail("Inovie number found in Open status");
		} else {

			System.out.println("Invoice  not found wiht Status as Open- valid scenario");
		}
		
		
		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Inovie number checking for Approved status");
		clickOn(findByBillingStatusAPPROVEDCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		takeScreenshots();
		if (recordsFoundInInvoicesTable.size() > 1) {
			if (apiNumberLink.getText().equals(invoiceNumber))
				ExtentTestManager.getTest().log(LogStatus.INFO,"Inovie number found in Approved status");
			validateVisibilityOfExportXMLElements();
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Inovie number not found in Approved status");
			Assert.fail("Inovie number not found in Approved status");
		}
		
		
		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Inovie number checking for Void status");
		clickOn(findByBillingStatusVOIDCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		takeScreenshots();
		if (recordsFoundInInvoicesTable.size() > 1) {
			if (apiNumberLink.getText().equals(invoiceNumber))
				throw new TestException("Inovie number found in VOID status");
		} else {

			System.out.println("Invoice  not found witH Status as VOID- valid scenario");
		}

		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Inovie number checking for Accepted status");
		clickOn(findByBillingStatusACCEPTEDCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		takeScreenshots();
		if (recordsFoundInInvoicesTable.size() > 1) {
			if (apiNumberLink.getText().equals(invoiceNumber))
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Inovie number found in ACCEPTED status");	
				Assert.fail("Inovie number found in ACCEPTED status");
		} else {

			ExtentTestManager.getTest().log(LogStatus.INFO,"Invoice  not found witH Status as ACCEPTED- valid scenario");
		}
		
		
		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Inovie number checking for Rejected status");
		clickOn(findByBillingStatusREJECTEDCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		takeScreenshots();
		if (recordsFoundInInvoicesTable.size() > 1) {
			if (apiNumberLink.getText().equals(invoiceNumber))
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Inovie number found in REJECTED status");
				Assert.fail("Inovie number found in REJECTED status");
		} else {

			ExtentTestManager.getTest().log(LogStatus.INFO,"Invoice  not found witH Status as REJECTED- valid scenario");
		}
	
		unCheckInvoiceStatusCheckboxes();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Inovie number checking for Manual status");
		clickOn(findByBillingStatusMANUALCheckBox);
		clickOn(findByBillingPeriodAndStatusSearchButton);
		waitForElementToDisappear(waitPleaseOverlay);
		takeScreenshots();
		if (recordsFoundInInvoicesTable.size() > 1) {
			if (apiNumberLink.getText().equals(invoiceNumber))
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Inovie number found in MANUAL status");
				Assert.fail("Inovie number found in MANUAL status");
		} else {

			ExtentTestManager.getTest().log(LogStatus.INFO,"Invoice  not found witH Status as MANUAL- valid scenario");
		}

	}

	public void unCheckInvoiceStatusCheckboxes() {
		for (int i = 0; i < checkbox_StatusList.size(); i++) {
			if (checkbox_StatusList.get(i).isSelected())
				clickOn(checkbox_StatusList.get(i));

		}
	}

//	public void validateVisibilityOfExportXMLElements() {
//		if (!link_ExportXML.isDisplayed())
//			throw new TestException("Error -Export XML file link is not displayed for the invoice");
//		if (!checkbox_ExportXML.isDisplayed())
//			throw new TestException("Error - Export XML checkbox is not displayed for the inovice");
//	}

	public void validateVisibilityOfExportXMLElements() {
		boolean link_ExportXMLTest = link_ExportXML.isDisplayed();
		boolean checkbox_ExportXMLTest = checkbox_ExportXML.isDisplayed();
		if (link_ExportXMLTest==false)
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Error -Export XML file link is not displayed");
		Assert.assertTrue(link_ExportXMLTest,"Error -Export XML file link is not displayed");
		if (checkbox_ExportXMLTest ==false)
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Error -Export checkbox file link is not displayed");
		Assert.assertTrue(checkbox_ExportXMLTest,"Error -Export XML checkbox is not displayed");
	}
	
	public void downloadXMLFileAndValidate(String invoiceNumber, String supplier, String city, String inovieStatus) throws ParserConfigurationException, SAXException, IOException {
		 clickOn(link_ExportXML);
				String expectedFileName = invoiceNumber + ".xml";
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
}
