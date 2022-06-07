package com.APIHotels.pages.ACES3;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.TestException;

import com.APIHotels.framework.BasePage;

public class Page_Accounting_Aces3Client extends BasePage {

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Reconcile Invoices')]")
	WebElement ReconcileInvoicesLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Find Invoice')]")
	WebElement FindInvoiceLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Create Manual Invoice')]")
	WebElement CreateManualInvoiceLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Pending Action Queue')]")
	WebElement PendingActionQueueLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Reject Payment Group')]")
	WebElement RejectPaymentGroupLink;

	@FindBy(xpath = "//label[@id='airlineActionQueueForm:tenantSelector_label']")
	WebElement dropdown_TenantSelector;

	@FindBy(xpath = "//ul[@id='airlineActionQueueForm:tenantSelector_items']/li")
	WebElement dropdown_TenantSelectorList;

	String tenantSelectorXPath = "//ul[@id='airlineActionQueueForm:tenantSelector_items']/li[text()='";
	String tenantSelectorXPath2 = "']";

	// @FindBy (xpath="//span[contains(text(),'Last Submit')]")
	@FindBy(xpath = "//span[contains(text(),'View Payment')]")
	WebElement columnHeader_LastSubmit;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;

	@FindBy(xpath = "//span[@class='ui-sortable-column-icon ui-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s']")
	private WebElement sort_LastSubmitLatest;

	@FindBy(xpath = "//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[1]")
	private WebElement invoiceLinkTextOnApp;

	@FindBy(xpath = "/html[1]/body[1]/div[10]/div[1]/ul[1]/li[text()='Accept']")
	private WebElement paymentGropuAccept;

	@FindBy(xpath = "/html[1]/body[1]/div[10]/div[1]/ul[1]/li[text()='Reject']")
	private WebElement paymentGropuReject;
	
	@FindBy(xpath ="//span[contains(text(),'Perform Actions')]")
	private WebElement btn_PerformActions;
	
	
	@FindBy(linkText = "Accounting")
	private WebElement tab_Accounting;

	@FindBy(linkText = "Pending Action Queue")
	private WebElement sub_Tab_PendingActionQueue;



	@FindBy(xpath = "//body/div[@id='container']/div[@id='subcontainer']/div[@id='tableRow']/div[@id='column2of3']/div[@id='content']/form[@id='airlineActionQueueForm']/div[@id='airlineActionQueueForm:actionTable']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='airlineActionQueueForm:actionTable_data']/tr[1]/td[2]//td/span")
	private WebElement link_UploadFile;

	@FindBy(xpath = "//tbody[@id='airlineExportInvoiceForm:invoiceExportViewTable_data']/tr")
	private List<WebElement> tableRowCount;
	
	@FindBy(linkText="Pending Invoice Export")
	private WebElement sub_Tab_PendingInvoiceExport;

	String tableCol1XPath1 = "//div[@id='reconcilecontent']//tr[";
	String tableCol1XPath2 = "]//td[1]";
	String tableCol2LabelStartXpath = "//label[@id='airlineExportInvoiceForm:invoiceExportViewTable:";
	String tableCol2LabelEndXpath = ":exportAction_label']";
	String option_ASAPXPath1 = "//li[@id='airlineExportInvoiceForm:invoiceExportViewTable:";
	String option_ASAPXPath2 = ":exportAction_0']";
	
	@FindBy(xpath = "//span[@class='ui-button-icon-left ui-icon ui-icon ui-icon-close']")
	private WebElement fileUploadProgressCloseImg;
	
	@FindBy(xpath ="//span[contains(text(),'Update')]")
	private WebElement btn_Update;
	
	@FindBy (xpath="//a[contains(@id,'findInvoice')]")
	private WebElement btn_findInvoiceTab;
	
	@FindBy(xpath="//tr[1]//td[1]//div[1]//div[2]//span[1]")
	private WebElement radio_InvoiceNumber;
	
	@FindBy(xpath="//input[contains(@id,'apiInvoiceNum')]")
	private WebElement textbox_InvoiceNum;
	
	@FindBy (xpath ="//span[contains(text(),'Search')]")
	private WebElement btn_SearchFindInvoice;
	
	@FindBy (xpath="//tbody[contains(@id,'findInvForm')]/tr[1]/td[7]")
	private WebElement cell_InoviceStatusHOP;
	
	@FindBy (xpath="//tbody[contains(@id,'findInvForm')]/tr[1]/td[8]")
	private WebElement cell_InoviceSubStatusHOP;

	public EventFiringWebDriver driver = null;

	public Page_Accounting_Aces3Client(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnReconcileInvoicesLink() {
		clickOn(ReconcileInvoicesLink);
	}

	public void clickOnFindInvoiceLink() {
		clickOn(FindInvoiceLink);
	}

	public void clickOnCreateManualInvoiceLink() {
		clickOn(CreateManualInvoiceLink);
	}

	public void clickOnPendingActionQueueLink() {
		clickOn(PendingActionQueueLink);
	}

	public void clickOnRejectPaymentGroupLink() {
		clickOn(RejectPaymentGroupLink);
	}
	
	public void clickOnPerformActionBtn(){
		clickOn(btn_PerformActions);
	}

	public void selectTenant(String tenantName) {
		clickOn(dropdown_TenantSelector);
		waitForElementVisibility(dropdown_TenantSelectorList);
		clickOn(findElementByXpath(tenantSelectorXPath + tenantName + tenantSelectorXPath2));
	}

	
	

	public void verifyInvoicePaymentFlightGroup(String invoiceNo, String flight) throws IOException, Exception {
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		String getCellString1 = getTextOfElement(invoiceLinkTextOnApp);
		System.out.println("Print App string :" + getCellString1);
		if (flightstring.equals(getCellString1)) {
			WebElement workflowGroup = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[2]/div/label"));
			String workflowgroupName = workflowGroup.getText();
			WebElement selectAcceptReject = driver.findElement(By.xpath(
					"//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[2]//div[contains(@id,'selectOne')]"));
			if (workflowgroupName.equals("Accept Payment Group?"))
				clickOn(selectAcceptReject);
			clickOn(paymentGropuAccept);
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);

		}
	}

	public void verifyInvoicePaymentInFlightGroup(String invoiceNo, String inFlight) {
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		String invoiceLinkTextOnApp2 = driver
				.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[1]")).getText();
		System.out.println("Print App string :" + invoiceLinkTextOnApp2);
	}

	public void verifyEnterVENCDateInFlight(String invoiceNo, String inFlight) throws Exception {
		String flightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + flightstring);
		String getCellString1 = getTextOfElement(
				findElementByXpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[1]"));
		System.out.println("Print App string :" + getCellString1);
		if (flightstring.equals(getCellString1)) {
			WebElement workflowGroup = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[2]/label"));
			String workflowgroupName = workflowGroup.getText();
			WebElement enterVENCDatePicker = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[2]/span/input"));
			if (workflowgroupName.equals("Enter VENC Date"))
				;
			clickOn(enterVENCDatePicker);
			String dateXPath1 = "//a[@class='ui-state-default'][contains(text(),'";
			String dateXPath2 = "')]";
			String lastDayOfTheMonth = getLastDayOfMonth();
			clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2));
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);
		}
	}

	public void verifyEnterVENCDateFlight(String invoiceNo, String flight) throws Exception {
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		String getCellString1 = getTextOfElement(
				findElementByXpath("//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[1]"));
		System.out.println("Print App string :" + getCellString1);
		if (flightstring.equals(getCellString1)) {
			WebElement workflowGroup = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[2]/label"));
			String workflowgroupName = workflowGroup.getText();
			WebElement enterVENCDatePicker = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[2]/span/input"));
			if (workflowgroupName.equals("Enter VENC Date"))
				;
			clickOn(enterVENCDatePicker);
			String dateXPath1 = "//a[@class='ui-state-default'][contains(text(),'";
			String dateXPath2 = "')]";
			String lastDayOfTheMonth = getLastDayOfMonth();
			clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2));
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);
		}
	}

	public void verifyEnterPaidDateFlight(String invoiceNo, String flight) throws Exception {
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		String getCellString1 = getTextOfElement(
				findElementByXpath("//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[1]"));
		System.out.println("Print App string :" + getCellString1);
		if (flightstring.equals(getCellString1)) {
			WebElement workflowGroup = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[2]/label"));
			String workflowgroupName = workflowGroup.getText();
			WebElement enterDatePicker = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[2]//td[2]/span/input"));
			if (workflowgroupName.equals("Enter Paid Date"))
				;
			clickOn(enterDatePicker);
			String dateXPath1 = "//a[@class='ui-state-default'][contains(text(),'";
			String dateXPath2 = "')]";
			String lastDayOfTheMonth = getLastDayOfMonth();
			clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2));
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);

		}
	}

	public void verifyEnterPaidDateInFlight(String invoiceNo, String inFlight) throws Exception {
		String flightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + flightstring);
		String getCellString1 = getTextOfElement(
				findElementByXpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[1]"));
		System.out.println("Print App string :" + getCellString1);
		if (flightstring.equals(getCellString1)) {
			WebElement workflowGroup = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[2]/label"));
			String workflowgroupName = workflowGroup.getText();
			WebElement enterPaidDatePicker = driver
					.findElement(By.xpath("//div[@id='airlineActionQueueForm:actionTable']//tr[1]//td[2]/span/input"));
			if (workflowgroupName.equals("Enter Paid Date"))
				;
			clickOn(enterPaidDatePicker);
			String dateXPath1 = "//a[contains(text(),'";
			String dateXPath2 = "')]";
			String lastDayOfTheMonth = getLastDayOfMonth();
			clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2));
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);
		}
	}

	public String getLastDayOfMonth() {

		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
		System.out.println("Today            : " + sdf.format(today));
		sdf = new SimpleDateFormat("dd");
		System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth));
		String dt = sdf.format(lastDayOfMonth);
		return dt;
	}

public void clickOnAccountingTab() {
		clickOn(tab_Accounting);
	}

	public void clickOnPendingActionQueueTab() {
		clickOn(sub_Tab_PendingActionQueue);
		waitForElementToDisappear(spinner);
	}
	
	public void clickOnPendingInvoiceExportTab(){
		clickOn(sub_Tab_PendingInvoiceExport);
	}

	public void sortForLatestInvoice() throws Exception {

		for (int i = 0; i < 5; i++) {
			clickOn(columnHeader_LastSubmit);
			waitForElementToDisappear(spinner);
			if (sort_LastSubmitLatest.isDisplayed() == true)
				break;

		}
		Thread.sleep(2000);
	}
	
	public void acceptPendingInvoiceExportFlight(String invoiceNo, String flight) throws IOException, Exception {
		takeScreenshots();
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement cellValue = null;
		int j;
		boolean record =false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			cellValue = findElementByXpath(tableCol1XPath1 + i + tableCol1XPath2);
			System.out.println("Print App string :" + cellValue.getText());
			WebElement selectDropdown = findElementByXpath(tableCol2LabelStartXpath + j + tableCol2LabelEndXpath);
				if (cellValue.getText().equals(flightstring)) {
				clickOn(selectDropdown);
				WebElement accept = findElementByXpath(option_ASAPXPath1 + j + option_ASAPXPath2);
				clickOn(accept);
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record =true;
				break;
				}
		}
				if(!record){
					throw new TestException("Invoice with number :"+invoiceNo+" not found");
				}
	}
	
	public void acceptPendingInvoiceExportInFlight(String invoiceNo, String inFlight) throws IOException, Exception {
		takeScreenshots();
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement cellValue = null;
		int j;
		boolean record =false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			cellValue = findElementByXpath(tableCol1XPath1 + i + tableCol1XPath2);
			System.out.println("Print App string :" + cellValue.getText());
			WebElement selectDropdown = findElementByXpath(tableCol2LabelStartXpath + j + tableCol2LabelEndXpath);
				if (cellValue.getText().equals(inFlightstring)) {
				clickOn(selectDropdown);
				WebElement accept = findElementByXpath(option_ASAPXPath1 + j + option_ASAPXPath2);
				clickOn(accept);
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record =true;
				break;
				}
		}
				if(!record){
					throw new TestException("Invoice with number :"+invoiceNo+" not found");
				}
	}
	
	
	public void verifyInvoiceStatusHOP(String Invoice_Number,String status) throws Exception{
		clickOn(btn_findInvoiceTab);
		clickOn(radio_InvoiceNumber);
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, Invoice_Number);
		takeScreenshots();
		clickOn(btn_SearchFindInvoice);
		waitForElementToDisappear(spinner);
		takeScreenshots();
		String statusOfInvoice = getTextOfElement(cell_InoviceStatusHOP);
		Assert.assertEquals(statusOfInvoice, status);
		
	}
	
	public void verifySubStatusHOP(String subStatus) throws IOException{
		String statusOfInvoice = getTextOfElement(cell_InoviceSubStatusHOP);
		takeScreenshots();
		Assert.assertEquals(statusOfInvoice, subStatus);
	}
	
	public void clickOnUpdateBtn() throws IOException{
		takeScreenshots();
		clickOn(btn_Update);
		takeScreenshots();
	}
	
}