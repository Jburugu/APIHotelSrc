package com.APIHotels.pages.ACES3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import static org.awaitility.Awaitility.*;
import com.APIHotels.framework.BasePage;
import static java.util.concurrent.TimeUnit.*;

public class Page_ManualInvoice_ACES3ACES extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(linkText = "Create Manual Invoice")
	private WebElement tab_CreateManualInvoice;

	@FindBy(xpath = "//label[@id='createInvoiceForm:tenant_label']")
	private WebElement dropdown_Airlines;

	@FindBy(xpath = "//input[@id='createInvoiceForm:city_input']")
	private WebElement textBox_City;

	@FindBy(xpath = "//label[@id='createInvoiceForm:supplier_label']")
	private WebElement dropdwon_Supplier;

	@FindBy(xpath = "//select[@id='createInvoiceForm:supplier_input']")
	private WebElement dropdwon_Supplier1;

	@FindBy(xpath = "//ul[@id='createInvoiceForm:supplier_items']/li")
	private WebElement dropdown_supplierList;

	@FindBy(xpath = "//ul[@id='createInvoiceForm:tenant_items']/li")
	private WebElement dropdwon_TenantList;

	@FindBy(xpath = "//label[@id='createInvoiceForm:billingPeriod_label']")
	private WebElement dropdown_InvoicePeriod;

	@FindBy(xpath = "//span[contains(text(),'Create Manual Invoice')]")
	private WebElement btn_CreateManualInvoice;

	String airlinesXPath1 = "//ul[@id='createInvoiceForm:tenant_items']/li[text()='";
	String supplierXPath1 = "//ul[@id='createInvoiceForm:supplier_items']/li[text()='";
	String billingPeriodXPath1 = "//ul[@id='createInvoiceForm:billingPeriod_items']/li[text()='";
	String reasonXPath1 = "//ul[contains(@id,'apiReason_items')]/li[text()='";
	String taxTypeXPath1 = "//ul[contains(@id,'apiTax_items')]/li[text()='";
	String endingXpath = "']";

	@FindBy(xpath = "//label[@id='createInvoiceForm:billingPeriod_label']")
	private WebElement dropdwon_BillingPeriod;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;

	@FindBy(xpath = "//ul[@id='createInvoiceForm:billingPeriod_items']/li")
	private WebElement dropdwon_BillingPeriodList;

	@FindBy(xpath = "//label[contains(text(),'Hotel Invoice No:')]/following-sibling::input[contains(@class,'hotelInvoiceNumber')]")
	private WebElement textBox_HotelInvoice;

	@FindBy(xpath = "//a[@id='eInvoiceForm:apiInvoiceAdjustments:apiAdjustmentPanel_toggler']/span")
	private WebElement apiAdjustmentPanel;

	@FindBy(xpath = "//button[contains(@onclick,'apiAdjustmentDlg')]/span[text()='Add Row']")
	private WebElement btn_AddRow;

	@FindBy(xpath = "//button[contains(@onclick,'apiTaxAdjustmentDlg')]/span[text()='Add Row']")
	private WebElement btn_AddRowTax;

	@FindBy(xpath = "//label[contains(@id,'apiTax_label')]")
	private WebElement selectTaxType;

	@FindBy(xpath = "//ul[contains(@id,'apiTax_items')]/li")
	private WebElement select_TaxTypeList;

	@FindBy(xpath = "//label[contains(@id,'apiReason_label')]")
	private WebElement dropdown_Reason;

	@FindBy(xpath = "//ul[contains(@id,'apiReason_items')]/li")
	private WebElement dropdown_ReasonList;

	@FindBy(xpath = "//textarea[contains(@id,'apiComment')]")
	private WebElement textarea_Comments;

	@FindBy(xpath = "//textarea[contains(@id,'apiTaxComment')]")
	private WebElement textarea_CommentsTax;

	@FindBy(xpath = "//input[contains(@id,'apiAmount')]")
	private WebElement textBox_Amount;

	@FindBy(xpath = "//input[contains(@id,'apiTaxAmount')]")
	private WebElement textBox_AmountTax;

	@FindBy(xpath = "//input[contains(@id,'apiRoomNightCount')]")
	private WebElement textBox_numOfRooms;

	@FindBy(xpath = "//div[contains(@id,'apiAdjustmentDlgPanel')]/div/table//button/span[text()='OK']")
	private WebElement btn_OK_AdjustmentDlgPanel;

	@FindBy(xpath = "//div[contains(@id,'apiTax')]/div/table//button/span[text()='OK']")
	private WebElement btn_OK_ApiTaxAdjustments;

	@FindBy(xpath = "//a[@id='eInvoiceForm:paymentGroupPanel:paymentGroupPanel_toggler']//span[@class='ui-icon ui-icon-plusthick']")
	private WebElement paymentGroupPanel;

	@FindBy(xpath = "//input[@id='eInvoiceForm:paymentGroupPanel:groupName']")
	private WebElement textBox_GroupName;

	@FindBy(xpath = "//span[contains(text(),'Add Group')]")
	private WebElement btn_AddGroupName;

	@FindBy(xpath = "//tbody[@id='eInvoiceForm:paymentGroupPanel:paymentGroupTable_data']/tr[1]/td[5]/a")
	private WebElement adjustmentlink1;

	@FindBy(xpath = "//tbody[@id='eInvoiceForm:paymentGroupPanel:paymentGroupTable_data']/tr[2]/td[5]/a")
	private WebElement adjustmentlink2;

	@FindBy(xpath = "//form[@id='paymentGroupDialogForm']//tr[1]//td[4]/input")
//	@FindBy(xpath = "//tr[@class='ui-widget-content']//td[contains(text(),'Number of Rooms')]/following-sibling::td[3]/input")
	private WebElement textBox_flightNoOfRooms;

	@FindBy(xpath = "//form[@id='paymentGroupDialogForm']//tr[2]//td[5]/input")
	private WebElement textBox_InflightNoShow;

	@FindBy(xpath = "//div[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupApiTaxAdjTable']//td[5]/input")
	private WebElement textBox_OtherTax;

	@FindBy(xpath = "//button[contains(@id,'savePaymentGroupAdjs')]")
	private WebElement btn_OKPaymentGroup;
	
	@FindBy(xpath="//div[contains(@id,'currencyConversionPanel_content')]//div/span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]")
	private WebElement checkbox_CurrencyConv;
	
	@FindBy(xpath ="//input[contains(@id,'conversionRate_input')]")
	private WebElement textbox_ConvRate;
	
	@FindBy(xpath ="//input[contains(@id,'targetCurrency_input')]")
//	@FindBy(xpath ="//span[@class='ui-button-icon-primary ui-icon ui-icon-triangle-1-s']")
	private WebElement select_TargetCurrency;
	
	@FindBy(xpath="//div[contains(@id,'targetCurrency_panel')]/ul/li")
	private WebElement select_TargetCurrencyList;
	
	@FindBy (xpath="//span[contains(text(),'Approve Invoice')]")
	WebElement btn_ApproveInvoice;
	
	@FindBy (xpath="//a[contains(@id,'findInvoice')]")
	private WebElement btn_findInvoiceTab;
	
	@FindBy(xpath="//tr[1]//td[1]//div[1]//div[2]//span[1]")
	private WebElement radio_InvoiceNumber;
	
	@FindBy(xpath="//input[contains(@id,'apiInvoiceNum')]")
	private WebElement textbox_InvoiceNum;

	@FindBy (xpath ="//span[contains(text(),'Search')]")
	private WebElement btn_SearchFindInvoice;
	
	@FindBy (xpath="//button[@id='findInvForm:search']/following-sibling::div/div/table/tbody/tr[1]/td[8]")
	private WebElement cell_InoviceStatus;
	
	@FindBy (xpath="//tbody[contains(@id,'findInvForm')]/tr[1]/td[7]")
	private WebElement cell_InoviceStatusHOP;

	@FindBy (xpath="//button[@id='findInvForm:search']/following-sibling::div/div/table/tbody/tr[1]/td[9]")
	private WebElement cell_InoviceStatusHold;

	@FindBy (xpath="//tbody[contains(@id,'findInvForm')]/tr[1]/td[8]")
	private WebElement cell_InoviceSubStatusHOP;
	
	@FindBy(xpath ="//span[@class='ui-button-text ui-c'][contains(text(),'Hold')]")
	private WebElement btn_HoldInvoice;
	
	@FindBy(xpath="//button[@id='eInvoiceForm:holdBtn']/following-sibling::button/span[text()='Void Invoice']")
	private WebElement btn_VoidInvoice;
	
	
	@FindBy(xpath ="//a[@id='eInvoiceForm:invoiceComments:commentsPanel_toggler']//span[@class='ui-icon ui-icon-plusthick']")
	private WebElement toggler_CommentsPanel;
	
	@FindBy(xpath ="//span[contains(text(),'Add Comment')]")
	private WebElement btn_AddComment;
	
	@FindBy(xpath ="//textarea[@id='auditorCommentDialogForm:invoiceAuditorCommentsDlg:auditorComments']")
	private WebElement textarea_CommentDlg;
	
	@FindBy(xpath="//div[@id='auditorCommentDialogForm:invoiceAuditorCommentsDlg:auditorCommentsDlgPanel']//table//button/span[text()='OK']")
	private WebElement btn_OKCommentDlg;
	
	@FindBy(xpath="//button[@id='eInvoiceForm:void']//span[@class='ui-button-text ui-c'][contains(text(),'Void Invoice')]")
	private WebElement btn_popUp_OK_CommentDlg;
	
	String targetCurrencyXPath="//div[contains(@id,'targetCurrency_panel')]/ul/li[text()='";
	
	//FindInvoiceWithInvoiceNumber Elements
	@FindBy(xpath ="//div[contains(text(),'Report Date/Time')]")
	private WebElement tableLayout;
	
	@FindBy(xpath ="//td[contains(text(),'Total Amounts by Currency')]")
	private WebElement tableLayoutCurreny;
	
	@FindBy (xpath ="//div[@id='reconcileDashboard:dashboardAccordian']")
	private WebElement accountingDashboard;
	
	@FindBy(xpath="//label[contains(text(),'Billing Period and Status')]/preceding-sibling::div/div/span")
	private WebElement radio_BillingPeriod;

	
	//FindInvoiceWithBillingPeriod Elements
	@FindBy(xpath="//label[contains(@id,'tenant_label')]")
	private WebElement dropDown_Airlines;
	
	@FindBy(xpath="//span[contains(@id,'startDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement startDate_BillingPeriod;

	@FindBy(xpath="//span[contains(@id,'endDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement endDate_BillingPeriod;

	@FindBy(xpath ="//input[contains(@id,'startDate_input')]")
	private WebElement startDateTextBox;

	@FindBy(xpath ="//input[contains(@id,'endDate_input')]")
	private WebElement endDateTextBox;

	@FindBy(xpath="//label[contains(text(),'All')]/preceding-sibling::div/div/span")
	private WebElement radio_StatusAll;

	@FindBy(xpath="//label[contains(text(),'Specific')]/preceding-sibling::div/div/span")
	private WebElement radio_StatusSpecific;

	@FindBy(xpath="//label[contains(text(),'Not Submitted')]/preceding-sibling::div/div/span")
	private WebElement radio_StatusNotSubmitted;

	@FindBy(xpath ="//button[@id='helpbutton']//img")
	private WebElement img_Help;
	
	
	//FindInvoiceWithSupplierAndLocation Elements
	@FindBy(xpath="//label[contains(text(),'Supplier and Location')]/preceding-sibling::div/div/span")
	private WebElement radio_SupplierAndLocation;

	@FindBy (xpath="//label[contains(@id,'tenant_label')]")
	private WebElement dropdown_Tenant;

	@FindBy (xpath ="//input[contains(@id,'city_input')]")
	private WebElement textBox_CitySupplierAndLocation;

	@FindBy (xpath ="//label[contains(@id,'supplier_label')]")
	private WebElement dropdown_Supplier;

	@FindBy (xpath ="//input[contains(@id,'startDate_input')]")
	private WebElement textBox_StartDateSupAndLoc;

	@FindBy (xpath="//span[contains(@id,'startDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement picker_StartDate;

	@FindBy(xpath="//input[contains(@id,'endDate_input')]")
	private WebElement textBox_EndDateSupAndLoc;

	@FindBy(xpath="//span[contains(@id,'endDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement picker_EndDate;
	
	@FindBy(xpath = "//form[@id='paymentGroupDialogForm']//tr[1]//td[5]/input")
//	@FindBy(xpath = "//tr[@class='ui-widget-content']//td[contains(text(),'Number of Rooms')]/following-sibling::td[4]/input")
	private WebElement textBox_inFlightNoOfRooms;

	@FindBy(xpath = "//form[@id='paymentGroupDialogForm']//tr[2]//td[4]/input")
	private WebElement textBox_FlightNoShow;
	
	@FindBy(xpath = "//div[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupApiTaxAdjTable']//td[4]/input")
	private WebElement textBox_OtherTaxFlight;
	
	public Page_ManualInvoice_ACES3ACES(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnCreateManualInvoice() {
		clickOn(tab_CreateManualInvoice,"Accounting Menu -> Create Manual Invoice Link");
	}

	public void selectAirline(String airline) {
		clickOn(dropdown_Airlines,"Create Manual Invoice Page -> Airline Select");
		waitForElementVisibility(dropdwon_TenantList);
		clickOn(findElementByXpath(airlinesXPath1 + airline + endingXpath),"Create Manual Invoice Page -> Airline Selected From Suggestion List");
	}

	public void selectSupplier(String supplier) {
		clickOn(dropdwon_Supplier,"Create Manual Invoice Page -> Supplier Select");
		waitForElementVisibility(dropdown_supplierList);
		clickOn(findElementByXpath(supplierXPath1 + supplier + endingXpath),"Create Manual Invoice Page -> Supplier Selected From Suggestion List");
	}

	public void selectBillingPeriod(String billingPeriod) {
		clickOn(dropdwon_BillingPeriod,"Create Manual Invoice Page -> Billing Period Select");
		waitForElementVisibility(dropdwon_BillingPeriodList);
		clickOn(findElementByXpath(billingPeriodXPath1 + billingPeriod + endingXpath),"Create Manual Invoice Page -> Billing Period Selected From Suggestion List");
	}

	public void createManualInvoice(String airline, String city, String supplier, String billingPeriod)
			throws Exception {
		clickOnCreateManualInvoice();
		selectAirline(airline);
		waitForElementToDisappear(spinner);
		typeInText(textBox_City, city,"Create Manual Invoice Page -> City Textbox");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		selectSupplier(supplier);
		waitForElementToDisappear(spinner);
		selectBillingPeriod(billingPeriod);
		takeScreenshots();
		clickOn(btn_CreateManualInvoice,"Create Manual Invoice Page -> Create Invoice Button");
	}

	public String getHotelInvoiceNbr() {
		String invoiceNumber = textBox_HotelInvoice.getAttribute("value");
		return invoiceNumber;
	}

	public void createAdjustments() {
		clickOn(apiAdjustmentPanel,"Create Manual Invoice Page -> Create Adjustments Button");

	}

	public void numberOfRoomsAdjustments(String reason, String comments, String amount, String numberOfRooms) throws IOException {
		createAdjustments();
		waitForElementToDisappear(spinner);
		clickOn(btn_AddRow,"Create Manual Invoice Page -> API Adjustments -> Add Row Button");
		waitForElementToDisappear(spinner);
		selectReasonOnAdjustments(reason);
		waitForElementToDisappear(spinner);
		typeInText(textarea_Comments, comments ,"Create Manual Invoice Page -> Number Of Rooms Comments Textarea");
		typeInText(textBox_Amount, amount,"Create Manual Invoice Page -> Number Of Rooms Amount Textbox");
		typeInText(textBox_numOfRooms, numberOfRooms,"Create Manual Invoice Page -> Number of Rooms Count Textbox");
		takeScreenshots();
		clickOn(btn_OK_AdjustmentDlgPanel,"Create Manual Invoice Page -> Ok Button");
		takeScreenshots();
		waitForElementToDisappear(spinner);
	}

	public void noShowAdjustments(String reasonNoshow, String comments, String amountNoShow) throws IOException {
		waitForElementToDisappear(spinner);
		clickOn(btn_AddRow,"Create Manual Invoice Page -> API Adjustments -> Add Row Button");
		takeScreenshots();
		waitForElementToDisappear(spinner);
		selectReasonOnAdjustments(reasonNoshow);
		waitForElementToDisappear(spinner);
		typeInText(textarea_Comments, comments ,"Create Manual Invoice Page -> No Show Comments Textarea");
		typeInText(textBox_Amount, amountNoShow,"Create Manual Invoice Page -> No Show Amount Textbox");
		takeScreenshots();
		clickOn(btn_OK_AdjustmentDlgPanel,"Create Manual Invoice Page -> Ok Button");
		takeScreenshots();
		waitForElementToDisappear(spinner);
		
	}

	public void selectReasonOnAdjustments(String reason) {
		clickOn(dropdown_Reason,"Create Manaul Invoice -> Add API Adjustments -> Reason Select");
		waitForElementVisibility(dropdown_ReasonList);
		clickOn(findElementByXpath(reasonXPath1 + reason + endingXpath),"Create Manaul Invoice -> Add API Adjustments -> Reason Selected From Suggestions List");
	}

	public void auditorTaxAdjustments(String taxType) throws IOException {
		clickOn(btn_AddRowTax,"Create Manaul Invoice -> Add Tax Adjustments -> Add Row Button");
		waitForElementToDisappear(spinner);
		clickOn(selectTaxType,"Create Manaul Invoice -> Add Tax Adjustments -> Tax Type Select");
		waitForElementVisibility(select_TaxTypeList);
		clickOn(findElementByXpath(taxTypeXPath1 + taxType + endingXpath),"Create Manaul Invoice -> Add Tax Adjustments -> Tax Type Selected From Suggestions List");
		typeInText(textarea_CommentsTax, "test comments","Create Manaul Invoice -> Add Tax Adjustments -> Comments Textarea");
		typeInText(textBox_AmountTax, "100","Create Manaul Invoice -> Add Tax Adjustments -> Tax Amount Textbox");
		takeScreenshots();
		clickOn(btn_OK_ApiTaxAdjustments,"Create Manaul Invoice -> Add Tax Adjustments -> Ok Button");
		takeScreenshots();
		waitForElementToDisappear(spinner);

	}

	public void AddPaymentGroup(String flight, String inFlight) throws IOException {

		typeInText(textBox_GroupName, flight,"Create Manaul Invoice -> Add Payment Group -> Group Name Textbox" );
		clickOn(btn_AddGroupName,"Create Manaul Invoice -> Add Payment Group -> Add Group Name Button");
		waitForElementToDisappear(spinner);
		typeInText(textBox_GroupName, inFlight,"Create Manaul Invoice -> Add Payment Group -> Group Name Textbox");
		clickOn(btn_AddGroupName,"Create Manaul Invoice -> Add Payment Group -> Add Group Name Button");
		takeScreenshots();
		waitForElementToDisappear(spinner);

	}
	

	public void adjustmentsInPaymentGroup(String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue) throws Exception {
	
		clickOn(adjustmentlink1,"Create Manaul Invoice -> Payment Group -> Add Adjustment Link");
		waitForElementToDisappear(spinner);
		//Integer  flight = Integer.valueOf(flightNoOfRooms);
		Integer flight =50;
		
		System.out.println(flight);
		textBox_flightNoOfRooms.clear();
		Thread.sleep(5000);
		//await().pollInterval(TWO_MINUTES).until(()->in.available()>0);
		textBox_flightNoOfRooms.sendKeys(""+flight);
		textBox_flightNoOfRooms.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		textBox_inFlightNoOfRooms.clear();
		Thread.sleep(5000);
		textBox_inFlightNoOfRooms.sendKeys(""+flight);
		textBox_inFlightNoOfRooms.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		textBox_FlightNoShow.clear();
		Thread.sleep(5000);
		textBox_FlightNoShow.sendKeys(""+flight);
		textBox_FlightNoShow.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		textBox_InflightNoShow.clear();
		Thread.sleep(5000);
		textBox_InflightNoShow.sendKeys(""+flight);
		textBox_InflightNoShow.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		textBox_OtherTaxFlight.clear();
		Thread.sleep(5000);
		textBox_OtherTaxFlight.sendKeys(""+flight);
		textBox_OtherTaxFlight.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		textBox_OtherTax.clear();
		Thread.sleep(5000);
		textBox_OtherTax.sendKeys(""+flight);
		textBox_OtherTax.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		
		takeScreenshots();
		clickOn(btn_OKPaymentGroup,"Create Manaul Invoice -> Payment Group -> Ok Button");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		takeScreenshots();
	}
	
	
	
	
	public void currencyConversion() throws Exception{
		
		if(checkbox_CurrencyConv.isEnabled())
		{
			clickOn(checkbox_CurrencyConv,"Create Manaul Invoice -> Currency Conversion -> Conversion Conversion Checkbox");
			waitForElementToDisappear(spinner);
			Thread.sleep(3000);
			}
		textbox_ConvRate.sendKeys(""+1);
		textbox_ConvRate.sendKeys(Keys.BACK_SPACE);
		textbox_ConvRate.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
//		waitForElementToDisappear(spinner);
		clickOn(select_TargetCurrency,"Create Manaul Invoice -> Currency Conversion -> Target Currency Select");
		typeInText(select_TargetCurrency, "AUD","Create Manaul Invoice -> Currency Conversion -> Target Currency Selected From Suggestion List");
//		waitForElementVisibility(select_TargetCurrencyList);
//		clickOn(findElementByXpath(targetCurrencyXPath+"AUD"+endingXpath));
		Thread.sleep(3000);
		
		
	}
	
	
	public void clickOnApproveInvoice() throws Exception{
		takeScreenshots();
		clickOn(btn_ApproveInvoice,"Create Manaul Invoice -> Approve Invoice Button");
		takeScreenshots();
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
	}
	
	
	public void clickOnHoldInvocie(){
		
		clickOn(btn_HoldInvoice,"Create Manaul Invoice -> Hold Invoice Button");
	}
	
	public  void verifyAccountingDashboard(){
		waitForElementVisibility(accountingDashboard);
	}
	
	
	public void findInvoice(String invoiceNumber){
	
		clickOn(btn_findInvoiceTab,"Accounting Menu -> Find Invoice Link");
		clickOn(radio_InvoiceNumber,"Find Invoice Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, invoiceNumber,"Find Invoice Page -> Invoice Number Textbox");
		clickOn(btn_SearchFindInvoice,"Find Invoice Page -> Serach Button");
		clickOn(findElementByPartialLinkText(invoiceNumber),"Find Invoice Page -> Invoice Number Link");
		
	}
	
	public void verifyInvoiceStatus(String Invoice_Number,String status) throws Exception{
		clickOn(btn_findInvoiceTab,"Accounting Menu -> Find Invoice Link");
		clickOn(radio_InvoiceNumber,"Find Invoice Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, Invoice_Number,"Find Invoice Page -> Invoice Number Textbox");
		clickOn(btn_SearchFindInvoice,"Find Invoice Page -> Serach Button");
		waitForElementToDisappear(spinner);
		takeScreenshots();
		String statusOfInvoice = getTextOfElement(cell_InoviceStatus);
		Assert.assertEquals(statusOfInvoice, status);
		takeScreenshots();
		Thread.sleep(5000);
		
	}

	public void verifyInvoiceStatusHOP(String Invoice_Number,String status) throws Exception{
		clickOn(btn_findInvoiceTab,"Accounting Menu -> Find Invoice Link");
		clickOn(radio_InvoiceNumber,"Find Invoice Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, Invoice_Number,"Find Invoice Page -> Invoice Number Textbox");
		takeScreenshots();
		clickOn(btn_SearchFindInvoice,"Find Invoice Page -> Serach Button");
		waitForElementToDisappear(spinner);	
		takeScreenshots();
		String statusOfInvoice = getTextOfElement(cell_InoviceStatusHOP);
		Assert.assertEquals(statusOfInvoice, status);
		Thread.sleep(5000);
		
	}
	
	public void verifyInvoiceSubStatus(String Invoice_Number,String status) throws IOException{
		clickOn(btn_findInvoiceTab,"Accounting Menu -> Find Invoice Link");
		clickOn(radio_InvoiceNumber,"Find Invoice Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, Invoice_Number,"Find Invoice Page -> Invoice Number Textbox");
		clickOn(btn_SearchFindInvoice,"Find Invoice Page -> Serach Button");
		takeScreenshots();
		String statusOfInvoice = getTextOfElement(cell_InoviceStatusHold);
		Assert.assertEquals(statusOfInvoice, status);
		
	}
	
	
	public void verifySubStatusHOP(String subStatus){
		String statusOfInvoice = getTextOfElement(cell_InoviceSubStatusHOP);
		Assert.assertEquals(statusOfInvoice, subStatus);
	}
	
	public void clickOnVoidInvoice(){
		clickOn(btn_VoidInvoice,"Create Manaul Invoice -> Void Invoice Button");
	}
	
	
	public void invoiceComments() {
		clickOn(toggler_CommentsPanel,"Create Manaul Invoice -> Void Invoice -> Comments Panel Button");
		waitForElementToDisappear(spinner);
		clickOn(btn_AddComment,"Create Manaul Invoice -> Void Invoice -> Add Comment Button");
		waitForElementToDisappear(spinner);
		typeInText(textarea_CommentDlg, "Void Invoice Testing" ,"Create Manaul Invoice -> Void Invoice -> Comments Textarea");
		clickOn(btn_OKCommentDlg,"Create Manaul Invoice -> Void Invoice -> Comments Ok Button");
		waitForElementToDisappear(spinner);
		clickOnVoidInvoice();
		clickOn(btn_popUp_OK_CommentDlg,"Create Manaul Invoice -> Void Invoice -> Comments Pop up Ok Button");
	}
	
	public void findIvoiceWithInvNum(String invoiceNumber){
//		clickOn(btn_findInvoiceTab);
		clickOn(radio_InvoiceNumber,"Find Invoice Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		typeInText(textbox_InvoiceNum, invoiceNumber,"Find Invoice Page -> Invoice Number Textbox");
		clickOn(btn_SearchFindInvoice,"Find Invoice Page -> Serach Button");
		waitForElementVisibility(tableLayout);
		waitForElementVisibility(tableLayoutCurreny);
		verifyAccountingDashboard();
		waitForElementVisibility(btn_SearchFindInvoice);
		
		
	}
	
	public void findInvoiceWithBillingPeriod(){
		
		clickOn(radio_BillingPeriod,"Find Invoice Page -> Billing Period Radio Button");
		waitForElementVisibility(dropDown_Airlines);
		waitForElementVisibility(startDateTextBox);
		waitForElementVisibility(startDate_BillingPeriod);
		waitForElementVisibility(endDateTextBox);
		waitForElementVisibility(endDate_BillingPeriod);
		waitForElementVisibility(radio_StatusAll);
		waitForElementVisibility(radio_StatusSpecific);
		waitForElementVisibility(radio_StatusNotSubmitted);
		waitForElementVisibility(img_Help);
		verifyAccountingDashboard();
		waitForElementVisibility(btn_SearchFindInvoice);
		
		}
	
	public  void findInvoiceWithSupAndLoc(){
		clickOn(radio_SupplierAndLocation,"Find Invoice Page -> Supplier And Location Radio Button");
		waitForElementVisibility(dropdown_Tenant);
		waitForElementVisibility(textBox_CitySupplierAndLocation);
		waitForElementVisibility(dropdown_Supplier);
		waitForElementVisibility(textBox_StartDateSupAndLoc);
		waitForElementVisibility(picker_StartDate);
		waitForElementVisibility(textBox_EndDateSupAndLoc);
		waitForElementVisibility(picker_EndDate);
//		verifyAccountingDashboard();
		waitForElementVisibility(btn_SearchFindInvoice);
		
	}
	

}
