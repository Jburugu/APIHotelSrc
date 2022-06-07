package com.APIHotels.pages.ACES3;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.TestException;

import com.APIHotels.framework.BasePage;

public class Page_Accounting_ACES3ACES extends BasePage {

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

	@FindBy(xpath = "//span[contains(text(),'Perform Actions')]")
	private WebElement btn_PerformActions;

	@FindBy(xpath = "//div[@id='airlineActionQueueForm:actionTable']/div/table/tbody/tr/td[1]")
	private List<WebElement> tableRowCount;

	String cellValueXPath1 = "//div[@id='airlineActionQueueForm:actionTable']/div/table/tbody/tr[";
	String cellValueXPath2 = "]/td[1]";

	// div[@id='airlineActionQueueForm:actionTable']//tr[5]//td[2]/label
	String workflowGroupXPath1 = "//div[@id='airlineActionQueueForm:actionTable']//tr[";
	String workflowGroupXPath2 = "]//td[2]/div/label";
	String workflowVENCDateEndXPath = "]//td[2]/span/input";
	String workflowGroupXPath3 = "]//td[2]/label";

	String selectAcceptRejectEleEndingXpath = "]//td[2]//div[contains(@id,'selectOne')]";
	String acceptBtnXPath1 = "//div[contains(@id,'airlineActionQueueForm:actionTable:";
	String acceptBtnXPath2 = "')]//ul/li[text()='Accept']";
	String rejecttBtnXPath3 = "')]//ul/li[text()='Reject']";
	String textAreaXPathReject = "')]/parent::td/textarea";
	String dateXPath1 = "//a[@class='ui-state-default'][contains(text(),'";
	String dateXPath2 = "')]";

	@FindBy(xpath = "//a[@id='menuForm:logout']")
	private WebElement logutACES3ACES;

	String rejectCommentsXPath1 = "//div[@id='airlineActionQueueForm:actionTable']//tr[";
	String rejectCommentsXPath2 = "]//td[2]/textarea";

	@FindBy(xpath = "//label[@id='airlineActionQueueForm:tenantSelector_label']")
	WebElement dropdown_TenantSelector;
	@FindBy(xpath = "//ul[@id='airlineActionQueueForm:tenantSelector_items']/li")
	WebElement dropdown_TenantSelectorList;
	String tenantSelectorXPath = "//ul[@id='airlineActionQueueForm:tenantSelector_items']/li[text()='";
	String tenantSelectorXPath2 = "']";

	@FindBy(xpath = "//label[@id='airlineActionQueueForm:tenantSelector_label']")
	WebElement pendingActionQueueTenantDropdown;

	@FindBy(xpath = "//tr[1]//td[1]//div[1]//div[2]//span[1]")
	private WebElement radio_InvoiceNumber;

	@FindBy(xpath = "//input[contains(@id,'apiInvoiceNum')]")
	private WebElement textbox_InvoiceNum;

	@FindBy(xpath = "//span[contains(text(),'Search')]")
	private WebElement btn_SearchFindInvoice;

	@FindBy(xpath = "//thead[@id='rejectPaymentGroupForm:resultTable_head']")
	private WebElement tableLayout;

	@FindBy(xpath = "//div[@id='reconcileDashboard:dashboardAccordian']")
	private WebElement accountingDashboard;

	@FindBy(xpath = "//label[contains(text(),'Billing Period')]/preceding-sibling::div")
	WebElement radioBtn_BillingPeriod;

	@FindBy(xpath = "//div[contains(@id,'tenant')]//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
	private WebElement dropDown_Airlines;

	@FindBy(xpath = "//input[contains(@id,'startDate_input')]")
	private WebElement startDateTextBox;

	@FindBy(xpath = "//input[contains(@id,'endDate_input')]")
	private WebElement endDateTextBox;

	@FindBy(xpath = "//span[contains(@id,'startDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement startDate_BillingPeriod;

	@FindBy(xpath = "//span[contains(@id,'endDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement endDate_BillingPeriod;

	String workflowReferenceNumberXPath = "]//td[2]/input";

	public EventFiringWebDriver driver = null;

	public Page_Accounting_ACES3ACES(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnReconcileInvoicesLink() {
		clickOn(ReconcileInvoicesLink, "Accounting Menu -> Reconcile Invoice Link");
	}

	public void clickOnFindInvoiceLink() {
		clickOn(FindInvoiceLink, "Accounting Menu -> Find Invoice Link");
	}

	public void clickOnCreateManualInvoiceLink() {
		clickOn(CreateManualInvoiceLink, "Accounting Menu -> Create Manual Invoice Link");
	}

	public void clickOnPendingActionQueueLink() throws IOException {
		clickOn(PendingActionQueueLink, "Accounting Menu -> Pending Action Queue Link");
		takeScreenshots();
	}

	public void clickOnRejectPaymentGroupLink() {
		clickOn(RejectPaymentGroupLink, "Accounting Menu -> Reject Payment Group Link");
	}

	public void clickOnPerformActionBtn() throws Exception {
		takeScreenshots();
		clickOn(btn_PerformActions, "Pending Action Queue Page -> Perform Action Button");
		takeScreenshots();
	}

	public void selectTenant(String tenantName) {
		clickOn(dropdown_TenantSelector, "Pending Action Queue Page -> Tenant Select");
		waitForElementVisibility(dropdown_TenantSelectorList);
		clickOn(findElementByXpath(tenantSelectorXPath + tenantName + tenantSelectorXPath2),
				"Pending Action Queue Page -> Tenant Selected From Suggestions List");
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

	public void verifyInvoicePaymentFlightGroup(String invoiceNo, String flight) throws IOException, Exception {
		takeScreenshots();
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectAcceptReject = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept Payment Group?"))
					clickOn(selectAcceptReject, "Pending Action Queue Page -> Accept Payment Group");
				takeScreenshots();
				WebElement accept = findElementByXpath(acceptBtnXPath1 + j + acceptBtnXPath2);
				clickOn(accept, "Pending Action Queue Page -> Accept Payment Group -> Accept Select");
				waitForElementToDisappear(spinner);
				takeScreenshots();
				Thread.sleep(2000);
				record = true;
				break;
			}
		}

		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyInvoicePaymentFlightGroupReject(String invoiceNo, String flight, String commentsReject)
			throws IOException, Exception {
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectAcceptReject = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept Payment Group?"))
					clickOn(selectAcceptReject, "Pending Action Queue Page -> Accept Payment Group");
				takeScreenshots();
				WebElement reject = findElementByXpath(acceptBtnXPath1 + j + rejecttBtnXPath3);
				clickOn(reject, "Pending Action Queue Page -> Accept Payment Group -> Reject Select");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				typeInText(findElementByXpath(rejectCommentsXPath1 + i + rejectCommentsXPath2), commentsReject,
						"Pending Action Queue Page -> Accept Payment Group -> Reject Comment Textarea");
				findElementByXpath(rejectCommentsXPath1 + i + rejectCommentsXPath2).sendKeys(Keys.TAB);
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}

		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyInvoicePaymentInFlightGroup(String invoiceNo, String inFlight) throws Exception {
		takeScreenshots();
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectAcceptReject = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept Payment Group?"))
					clickOn(selectAcceptReject, "Pending Action Queue Page -> Accept Payment Group");
				takeScreenshots();
				WebElement accept = findElementByXpath(acceptBtnXPath1 + j + acceptBtnXPath2);
				clickOn(accept, "Pending Action Queue Page -> Accept Payment Group -> Accept Select");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}

		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}

	}

	public void verifyInvoicePaymentInFlightGroupReject(String invoiceNo, String inFlight, String commentsReject)
			throws Exception {
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectAcceptReject = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept Payment Group?"))
					clickOn(selectAcceptReject, "Pending Action Queue Page -> Accept Payment Group");
				takeScreenshots();
				WebElement reject = findElementByXpath(acceptBtnXPath1 + j + rejecttBtnXPath3);
				clickOn(reject, "Pending Action Queue Page -> Accept Payment Group -> Reject Select");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				typeInText(findElementByXpath(rejectCommentsXPath1 + i + rejectCommentsXPath2), commentsReject,
						"Pending Action Queue Page -> Accept Payment Group -> Reject Comments Textarea");
				findElementByXpath(rejectCommentsXPath1 + i + rejectCommentsXPath2).sendKeys(Keys.TAB);
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}

		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}

	}

	public void verifyEnterVENCDateInFlight(String invoiceNo, String inFlight) throws Exception {
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		WebElement workflowGroup = null;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath3);
				String workflowgroupName = workflowGroup.getText();
				WebElement enterVENCDatePicker = findElementByXpath(workflowGroupXPath1 + i + workflowVENCDateEndXPath);
				if (workflowgroupName.equals("Enter VENC Date"))
					clickOn(enterVENCDatePicker, "Pending Action Queue Page -> Enter VENC Date Picker");
				String lastDayOfTheMonth = getLastDayOfMonth();
				clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2),
						"Pending Action Queue Page -> Enter VENC Date Textbox");
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}

	}

	public void verifyEnterVENCDateFlight(String invoiceNo, String flight) throws Exception {
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		boolean record = false;
		WebElement workflowGroup = null;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath3);
				String workflowgroupName = workflowGroup.getText();
				WebElement enterVENCDatePicker = findElementByXpath(workflowGroupXPath1 + i + workflowVENCDateEndXPath);
				if (workflowgroupName.equals("Enter VENC Date"))
					clickOn(enterVENCDatePicker, "Pending Action Queue Page -> Enter VENC Date Picker");
				String lastDayOfTheMonth = getLastDayOfMonth();
				clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2),
						"Pending Action Queue Page -> Enter VENC Date Texbox");
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyEnterPaidDateFlight(String invoiceNo, String flight) throws Exception {
		takeScreenshots();
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath3);
				String workflowgroupName = workflowGroup.getText();
				WebElement enterDatePicker = findElementByXpath(workflowGroupXPath1 + i + workflowVENCDateEndXPath);
				if (workflowgroupName.equals("Enter Paid Date"))
					clickOn(enterDatePicker, "Pending Action Queue Page -> Enter Paid Date Picker");
				takeScreenshots();
				String lastDayOfTheMonth = getLastDayOfMonth();
				clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2),
						"Pending Action Queue Page -> Enter Paid Date Textbox");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyEnterPaidDateInFlight(String invoiceNo, String inFlight) throws Exception {
		takeScreenshots();
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath3);
				String workflowgroupName = workflowGroup.getText();
				WebElement enterDatePicker = findElementByXpath(workflowGroupXPath1 + i + workflowVENCDateEndXPath);
				if (workflowgroupName.equals("Enter Paid Date"))
					clickOn(enterDatePicker, "Pending Action Queue Page -> Enter Paid Date Picker");
				takeScreenshots();
				String lastDayOfTheMonth = getLastDayOfMonth();
				clickOn(findElementByXpath(dateXPath1 + lastDayOfTheMonth + dateXPath2),
						"Pending Action Queue Page -> Enter Paid Date Textbox");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyNotaFiscalAcceptInFlight(String invoiceNo, String inFlight) throws Exception {
		takeScreenshots();
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectDropdown = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept NotaFiscal?"))
					clickOn(selectDropdown, "Pending Action Queue Page -> Accept NotaFiscal? Select");
				takeScreenshots();
				Thread.sleep(2000);
				WebElement accept = findElementByXpath(acceptBtnXPath1 + j + acceptBtnXPath2);
				clickOn(accept, "Pending Action Queue Page -> Accept NotaFiscal? -> Accept Select");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyNotaFiscalRejectInFlight(String invoiceNo, String inFlight, String rejectComments)
			throws Exception {
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectDropdown = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept NotaFiscal?"))
					clickOn(selectDropdown, "Pending Action Queue Page -> Accept NotaFiscal? Select");
				WebElement reject = findElementByXpath(acceptBtnXPath1 + j + rejecttBtnXPath3);
				clickOn(reject, "Pending Action Queue Page -> Accept NotaFiscal? -> Reject Select");
				WebElement rejectCommentBox = findElementByXpath(acceptBtnXPath1 + j + textAreaXPathReject);
				typeInText(rejectCommentBox, rejectComments,
						"Pending Action Queue Page -> Accept NotaFiscal? -> Reject Comment Textarea");
				findElementByXpath(acceptBtnXPath1 + j + textAreaXPathReject).sendKeys(Keys.TAB);
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyNotaFiscalRejectFlight(String invoiceNo, String flight, String rejectComments) throws Exception {
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectDropdown = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept NotaFiscal?"))
					clickOn(selectDropdown, "Pending Action Queue Page -> Accept NotaFiscal? Select");
				Thread.sleep(1000);
				WebElement reject = findElementByXpath(acceptBtnXPath1 + j + rejecttBtnXPath3);
				clickOn(reject, "Pending Action Queue Page -> Accept NotaFiscal? -> Reject Select");
				WebElement rejectCommentBox = findElementByXpath(acceptBtnXPath1 + j + textAreaXPathReject);
				typeInText(rejectCommentBox, rejectComments,
						"Pending Action Queue Page -> Accept NotaFiscal? -> Reject Comments Textarea");
				findElementByXpath(acceptBtnXPath1 + j + textAreaXPathReject).sendKeys(Keys.TAB);
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyNotaFiscalAcceptFlight(String invoiceNo, String flight) throws Exception {
		takeScreenshots();
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		int j;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			j = i - 1;
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath2);
				String workflowgroupName = workflowGroup.getText();
				WebElement selectDropdown = findElementByXpath(
						workflowGroupXPath1 + i + selectAcceptRejectEleEndingXpath);
				if (workflowgroupName.equals("Accept NotaFiscal?"))
					clickOn(selectDropdown, "Pending Action Queue Page -> Accept NotaFiscal?  Select");
				takeScreenshots();
				WebElement accept = findElementByXpath(acceptBtnXPath1 + j + acceptBtnXPath2);
				clickOn(accept, "Pending Action Queue Page -> Accept NotaFiscal? -> Accept Select");
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
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

	public void logoutACES3ACES() {
		clickOn(logutACES3ACES, "ACES 3 ACES Menu -> Logout Link");
	}

	public void verifyTenantDropdown() {
		waitForElementVisibility(pendingActionQueueTenantDropdown);
	}

	public void verifyAccountingDashboard() {
		waitForElementVisibility(accountingDashboard);
	}

	public void findIvoiceWithInvNum(String invoiceNumber) {
		clickOn(radio_InvoiceNumber, "Find Invoice Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, invoiceNumber, "Find Invoice Page -> Invoice Number Textbox");
		clickOn(btn_SearchFindInvoice, "Find Invoice Page -> Search Button");
		waitForElementVisibility(tableLayout);
		waitForElementVisibility(btn_SearchFindInvoice);

	}

	public void findInvoiceWithBillingPeriod() {
		clickOn(radioBtn_BillingPeriod, "Find Invoice Page -> Billing Period Radio Button");
		waitForElementVisibility(dropDown_Airlines);
		waitForElementVisibility(startDateTextBox);
		waitForElementVisibility(startDate_BillingPeriod);
		waitForElementVisibility(endDateTextBox);
		waitForElementVisibility(endDate_BillingPeriod);
		waitForElementVisibility(btn_SearchFindInvoice);

	}

	@FindBy(xpath = "//label[contains(text(),'Supplier:')]/parent::span/div/div[contains(@class,'selectonemenu')]")
	WebElement dropdown_SupplierSIS;
	@FindBy(xpath = "//input[contains(@id,'reservationDate_input')]")
	WebElement textBox_ReservationDateSIS;

	@FindBy(xpath = "//span[contains(@id,'reservationDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_ReservationDateSIS;

	public void verifyEnterRefNoInFlight(String invoiceNo, String inFlight) throws Exception {
		takeScreenshots();
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath3);
				String workflowgroupName = workflowGroup.getText();
				if (workflowgroupName.equals("Enter Reference Number"))
					typeInText(findElementById(workflowGroupXPath1 + i + workflowReferenceNumberXPath), "1234",
							"Pending Action Queue Page -> Enter Refrence Number Textbox");
				takeScreenshots();
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}

	public void verifyEnterRefNoFlight(String invoiceNo, String flight) throws Exception {
		takeScreenshots();
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		boolean record = false;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			WebElement cellValue = findElementByXpath(cellValueXPath1 + i + cellValueXPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(workflowGroupXPath1 + i + workflowGroupXPath3);
				String workflowgroupName = workflowGroup.getText();
				if (workflowgroupName.equals("Enter Reference Number"))
					typeInText(findElementById(workflowGroupXPath1 + i + workflowReferenceNumberXPath), "1234",
							"Pending Action Queue Page -> Enter Refrence Number Textbox");
				takeScreenshots();
				Thread.sleep(2000);
				record = true;
				break;
			}
		}
		if (!record) {
			throw new TestException("Invoice with number :" + invoiceNo + " not found");
		}
	}
	
	

}