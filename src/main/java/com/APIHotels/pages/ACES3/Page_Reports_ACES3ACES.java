package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Reports_ACES3ACES extends BasePage{

	@FindBy(xpath="//a[contains(text(),'Tax Exemption Report')]")
	private WebElement TaxExemptionReportLink;
	
	@FindBy(xpath="//a[contains(text(),'No Show Report')]")
	private WebElement NoShowReportLink;
	
	@FindBy(xpath="//a[contains(text(),'Crew ID Issues Report')]")
	private WebElement CrewIDIssuesReportLink;
	
	@FindBy(xpath="//a[contains(text(),'Accounting Financial Reports')]")
	private WebElement AccountingFinancialReportsLink;
	
	@FindBy(xpath="//a[contains(text(),'Recoverable Tax Report')]")
	private WebElement RecoverableTaxReportLink;
	
	@FindBy(xpath="//a[contains(text(),'SIS Compliance Report')]")
	private WebElement SISComplianceReportLink;
	
	@FindBy(xpath="//a[contains(text(),'Reservations')]")
	private WebElement ReservationsLink;
	
	@FindBy(xpath="//a[contains(text(),'Invoice Compliance Report')]")
	private WebElement InvoiceComplianceReportLink;
	
	@FindBy(xpath="//a[contains(text(),'Invoice Status Report')]")
	private WebElement InvoiceStatusReportLink;
	
	@FindBy(xpath="//a[contains(text(),'Accrual Snapshot')]")
	private WebElement AccrualSnapshotLink;
	
	@FindBy(xpath="//a[contains(text(),'Consolidated E-Invoice Report')]")
	private WebElement ConsolidatedEInvoiceReportLink;
	
	
	@FindBy(linkText = "Reports")
	WebElement reportsLink;

	@FindBy(xpath = "//a[contains(text(),'Tax Exemption Report')]")
	WebElement taxExemptionReportLink;

	@FindBy(xpath = "//label[@id='taxemptionReportForm:selectedMonth_label']")
	WebElement select_MonthTaxExmpRpt;

	@FindBy(xpath = "//label[contains(@id,'selectedMonth_label')]")
	WebElement select_MonthNoShowRpt;
	
	@FindBy(xpath = "//div[contains(@id,'tenant')]//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
	private WebElement dropDown_Airlines;

	@FindBy(xpath = "//label[@id='taxemptionReportForm:tenant_label']")
	WebElement select_TenantTaxExmpRpt;

	@FindBy(xpath = "//a[contains(text(),'Crew ID Issues Report')]")
	WebElement crewIDIssuesReportLink;

	@FindBy(xpath = "//a[contains(text(),'No Show Report')]")
	WebElement noShowReportLink;

	@FindBy(xpath = "//label[contains(@id,'airline_label')]")
	WebElement select_AirlinesNoShowReport;

	@FindBy(xpath = "//a[contains(text(),'Accounting Financial Reports')]")
	WebElement accountingFinancialReportLink;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'tenant_label')]")
	WebElement selectAirline;

	@FindBy(how = How.XPATH, using = "//ul[contains(@id,'tenant_items')]/li")
	WebElement airlinesList;

	String tenantXpath1 = "//*[contains(@id, 'tenant_items')]/li[contains(text(), '";
	String tenantXpath2 = "')]";

	@FindBy(how = How.XPATH, using = "//*[@role='application']")
	WebElement city;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'supplierSelector_label')]")
	WebElement supplier;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Actual')]")
	WebElement actual;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Accrual')]")
	WebElement accrual;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Un-billed')]")
	WebElement unbilled;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Variance')]")
	WebElement variance;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Account/GL Code')]/parent::td/div/div/span")
	WebElement accountOrGLCodeCheckbox;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Cost Center')]/parent::td/div/div/span")
	WebElement costCenterCheckbox;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Location and Supplier')]/parent::td/div/div/span")
	WebElement locationAndSupplierCheckbox;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'bpStart_input')]")
	WebElement fromBillingPeriodText;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'bpEnd_input')]")
	WebElement toBillingPeriodText;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'bpStart')]")
	WebElement fromBillingPeriod;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'bpEnd')]")
	WebElement toBillingPeriod;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'bpStart_input')]")
	WebElement fromApprovedPeriodText;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'bpEnd_input')]")
	WebElement toAppPeriodText;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Approved Date :')]")
	WebElement approvedDate;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'abpStart')]")
	WebElement fromApprovedDate;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'abpEnd')]")
	WebElement toApprovedDate;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Account/GL Code')]")
	WebElement accountOrGLCode;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Cost Center')]")
	WebElement costCenter;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Location and Supplier')]")
	WebElement locationAndSupplier;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Generate Report')]")
	WebElement generateReport;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Recoverable Tax Report')]")
	WebElement recoverableTaxReportLink;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'City:')]/parent::span/span/input")
	WebElement select_City;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'supplierSelector_label')]")
	WebElement select_Supplier;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Accrual')]/parent::td/div/div/span")
	WebElement radiobtn_Accrual;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Actual')]/parent::td/div/div/span")
	WebElement radiobtn_Actual;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Un-billed')]/parent::td/div/div/span")
	WebElement radiobtn_Unbilled;

	@FindBy(how = How.LINK_TEXT, using = "SIS Compliance Report")
	WebElement linkSISComplianceReport;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'billingPeriod_label')]")
	WebElement select_BillingPeriod;

	@FindBy(how = How.XPATH, using = "//span[@class='ui-chkbox-icon ui-icon ui-icon-check ui-c']")
	WebElement checkbox_DisplayAllDayHotalCompliant;

	@FindBy(how = How.XPATH, using = "//div[@class='ui-selectlistbox-listcontainer']")
	WebElement listbox_Airlines;

	@FindBy(how = How.XPATH, using = "//label[@id='viewReservationsForm:mode_label']")
	WebElement select_ReservationMode;

	@FindBy(how = How.ID, using = "viewReservationsForm:startDate_input")
	WebElement textBox_ReservStartDate;

	@FindBy(how = How.XPATH, using = "//input[@id='viewReservationsForm:startDate_input']/following-sibling::button/span[contains(@class,'icon')]")
	WebElement datepicker_ReservStartDate;

	@FindBy(how = How.XPATH, using = "//input[@id='viewReservationsForm:endDate_input']/following-sibling::button/span[contains(@class,'icon')]")
	WebElement datepicker_ReservEndDate;

	@FindBy(how = How.ID, using = "viewReservationsForm:city_input")
	WebElement textbox_ReservationCity;

	@FindBy(how = How.ID, using = "viewReservationsForm:supplier_label")
	WebElement select_ReservationsSupplier;

	@FindBy(how = How.ID, using = "viewReservationsForm:employee")
	WebElement viewReservationsFormEmployee;

	@FindBy(how = How.LINK_TEXT, using = "Reservations")
	WebElement link_Reservations;

	@FindBy(how = How.LINK_TEXT, using = "Invoice Compliance Report")
	WebElement link_InvoiceComplianceReport;

	@FindBy(how = How.ID, using = "invComplianceForm:startDate_input")
	WebElement textbox_InvComplianceFormStartDate;

	@FindBy(how = How.ID, using = "invComplianceForm:endDate_input")
	WebElement textbox_InvComplianceFormEndDate;

	@FindBy(how = How.XPATH, using = "//span[@id='invComplianceForm:startDate']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_InvCompliaanceStartDate;

	@FindBy(how = How.XPATH, using = "//span[@id='invComplianceForm:endDate']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_InvCompliaanceEndDate;

	@FindBy(how = How.XPATH, using = "//span[@class='ui-chkbox-icon ui-icon ui-icon-check ui-c']")
	WebElement checkbox_NonComplaintOnly;

	@FindBy(how = How.LINK_TEXT, using = "Invoice Status Report")
	WebElement link_InvoiceStatusReport;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Invoice number')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_InvoiceNumber;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Billing Period and Status')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_billingPeriodAndStatus;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Supplier and Location')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_SupplierAndLoc;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'apiInvoiceNum')]")
	WebElement textbox_APIIvoiceNumber;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'supplierInvoiceNum')]")
	WebElement textbox_SupplierInvNum;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'creation_label')]")
	WebElement select_InvCreation;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'All')]/parent::td/div/div/span")
	WebElement radio_StatusAll;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Specific')]/parent::td/div/div/span")
	WebElement radio_StatusSpecific;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Not Submitted')]/parent::td/div/div/span")
	WebElement radio_StatusNotSubmitted;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Submitted - Pending Review')]/parent::td/div/div/span")
	WebElement checkbox_Submitted_PendingReview;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Submitted - on hold')]/parent::td/div/div/span")
	WebElement checkbox_Submitted_onhold;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Returned to hotel')]/parent::td/div/div/span")
	WebElement checkbox_Returnedtohotel;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Approved For Payment')]/parent::td/div/div/span")
	WebElement checkbox_ApprovedForPayment;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Include Void Invoices')]/parent::td/div/div/span")
	WebElement checkbox_IncludeVoidInvoices;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted - Pending Reference Number')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted_PendingReferenceNumber;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted - Pending VENC')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted_PendingVENC;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted - Pending Nota Fiscal')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted_PendingNotaFiscal;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted - Pending Nota Fiscal Acceptance')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted_PendingNotaFiscalAcceptance;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted - Pending Paid Date')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted_PendingPaidDate;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Payment Group Accepted - Workflow Complete')]/parent::td/div/div/span")
	WebElement checkbox_PaymentGroupAccepted_WorkflowComplete;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'city_input')]")
	WebElement textbox_InvStatusReportCity;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'supplier_label')]")
	WebElement select_InvStatusReportSupplier;

	@FindBy(how = How.LINK_TEXT, using = "Accrual Snapshot")
	WebElement link_AccrualSnapshot;

	@FindBy(how = How.LINK_TEXT, using = "Consolidated E-Invoice Report")
	WebElement link_ConsolidatedEInvoiceReport;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'City:')]/parent::span/span/input")
	WebElement textbox_CityConsolidatedEInvoiceReport;

	@FindBy(how = How.ID, using = "consolidatedInvoiceReport:startDate_input")
	WebElement consolidatedInvoiceReport_StartDate;

	@FindBy(how = How.XPATH, using = "//span[@id='consolidatedInvoiceReport:startDate']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_consolidatedInvoiceReport_StartDate;

	@FindBy(how = How.ID, using = "consolidatedInvoiceReport:endDate_input")
	WebElement consolidatedInvoiceReport_EndDate;

	@FindBy(how = How.XPATH, using = "//span[@id='consolidatedInvoiceReport:endDate']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_consolidatedInvoiceReport_EndDate;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Submit')]")
	WebElement btn_Submit;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Accrual')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_AccrualConInvoRpt;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Actual')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_ActualConInvoRpt;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Variance')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_Variance;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Approved Date')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_ApprovedDate;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Billing Period')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_BillingPeriod;

	
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//span[contains(text(),'Search')]")
	private WebElement btn_SearchFindInvoice;
	
	@FindBy(xpath = "//input[contains(@id,'startDate_input')]")
	private WebElement startDateTextBox;

	@FindBy(xpath = "//input[contains(@id,'endDate_input')]")
	private WebElement endDateTextBox;
	
	@FindBy(xpath = "//span[contains(@id,'startDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement startDate_BillingPeriod;

	@FindBy(xpath = "//span[contains(@id,'endDate')]//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	private WebElement endDate_BillingPeriod;
	
	public EventFiringWebDriver driver = null;
	public Page_Reports_ACES3ACES(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnTaxExemptionReportLink()
	{
		clickOn(TaxExemptionReportLink,"Reports Menu -> Tax Exemption Report Link");
	}
	
	public void clickOnNoShowReportLink()
	{
		clickOn(NoShowReportLink,"Reports Menu -> No Show Report Link");
	}
	
	
	public void clickOnAccountingFinancialReportsLink() throws InterruptedException
	{
		Thread.sleep(3000);
		clickOn(AccountingFinancialReportsLink,"Reports Menu -> Accounting Financial Reports Link");
	}
	
		
		
	
	public void clickOnInvoiceComplianceReportLink()
	{
		clickOn(InvoiceComplianceReportLink,"Reports Menu -> Ivoice Compliance Report Link");
	}
	
	public void clickOnInvoiceStatusReportLink()
	{
		clickOn(InvoiceStatusReportLink,"Reports Menu -> Invoice Status Report Link");
	}
	
	
	
	
	public void clickOnReportsLink() {
		clickOn(reportsLink,"ACES 3 Menu -> Reports Link");
	}

	public void clickOnTaxExemptionReportsLink() {
		clickOn(taxExemptionReportLink,"Reports Menu -> Tax Exemption Report Link");
	}

	public void verifyTaxExamRptPageElements() {
		waitForElementVisibility(select_MonthTaxExmpRpt);
		waitForElementVisibility(select_TenantTaxExmpRpt);
		waitForElementVisibility(btn_SearchFindInvoice);

	}

	public void clickOnNoShowReport() {
		clickOn(noShowReportLink,"Reports Menu -> No Show Report Link");
	}

	public void clickOnCrewIDIssuesReportLink() {
		clickOn(crewIDIssuesReportLink,"Reports Menu -> Crew ID Issues Report");
	}

	public void verifyElementsOnNoShowReportPage() {
		waitForElementVisibility(select_MonthNoShowRpt);
		waitForElementVisibility(dropDown_Airlines);

	}

	public void verifyElementsOnCrewIDIssueReportPage() {
		waitForElementVisibility(select_MonthNoShowRpt);
		waitForElementVisibility(select_AirlinesNoShowReport);

	}

	public void clickOnAccountingFinancialReportLink() {
		clickOn(accountingFinancialReportLink);
	}

	public void verifyElementsOnAccFinReports() {
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(city);
		waitForElementVisibility(supplier);
		waitForElementVisibility(actual);
		waitForElementVisibility(accrual);
		waitForElementVisibility(unbilled);
		waitForElementVisibility(variance);
		waitForElementVisibility(accountOrGLCodeCheckbox);
		waitForElementVisibility(costCenterCheckbox);
		waitForElementVisibility(locationAndSupplierCheckbox);
		waitForElementVisibility(generateReport);
		clickOn(actual,"Accounting Financial Reports Page -> Actual Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(accrual);
		waitForElementVisibility(unbilled);
		waitForElementVisibility(variance);
		waitForElementVisibility(accountOrGLCodeCheckbox);
		waitForElementVisibility(costCenterCheckbox);
		waitForElementVisibility(locationAndSupplierCheckbox);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(approvedDate);
		waitForElementVisibility(generateReport);
		clickOn(approvedDate,"Accounting Financial Reports Page -> Approved Date Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(accrual);
		waitForElementVisibility(unbilled);
		waitForElementVisibility(variance);
		waitForElementVisibility(accountOrGLCodeCheckbox);
		waitForElementVisibility(costCenterCheckbox);
		waitForElementVisibility(locationAndSupplierCheckbox);
		waitForElementVisibility(fromApprovedDate);
		waitForElementVisibility(toApprovedDate);
		waitForElementVisibility(generateReport);
		clickOn(accrual,"Accounting Financial Reports Page -> Accrual Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(actual);
		waitForElementVisibility(unbilled);
		waitForElementVisibility(variance);
		waitForElementVisibility(accountOrGLCodeCheckbox);
		waitForElementVisibility(costCenterCheckbox);
		waitForElementVisibility(locationAndSupplierCheckbox);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(generateReport);
		clickOn(unbilled,"Accounting Financial Reports Page -> Un-billed Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(actual);
		waitForElementVisibility(accrual);
		waitForElementVisibility(variance);
		waitForElementVisibility(accountOrGLCodeCheckbox);
		waitForElementVisibility(costCenterCheckbox);
		waitForElementVisibility(locationAndSupplierCheckbox);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(generateReport);
		clickOn(variance,"Accounting Financial Reports Page -> Variance Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(actual);
		waitForElementVisibility(accrual);
		waitForElementVisibility(unbilled);
		waitForElementVisibility(accountOrGLCodeCheckbox);
		waitForElementVisibility(costCenterCheckbox);
		waitForElementVisibility(locationAndSupplierCheckbox);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(generateReport);
	}

	public void clickOnRecoverableTaxReportLink() {
		clickOn(recoverableTaxReportLink,"Reports Menu -> Recoverable Tax Report Link");
	}

	public void verifyElementsOnRecoverableTaxReportPage() {
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(select_City);
		waitForElementVisibility(select_Supplier);
		waitForElementVisibility(radiobtn_Accrual);
		waitForElementVisibility(radiobtn_Actual);
		waitForElementVisibility(radiobtn_Unbilled);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(fromBillingPeriod);
		waitForElementVisibility(toBillingPeriod);
		waitForElementVisibility(generateReport);
		clickOn(radiobtn_Actual,"Recoverable Tax Reports Page -> Actual Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(select_City);
		waitForElementVisibility(select_Supplier);
		waitForElementVisibility(radiobtn_Accrual);
		waitForElementVisibility(radiobtn_Actual);
		waitForElementVisibility(radiobtn_Unbilled);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(fromBillingPeriod);
		waitForElementVisibility(toBillingPeriod);
		waitForElementVisibility(generateReport);
		clickOn(radiobtn_Unbilled,"Recoverable Tax Reports Page -> Un-billed Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(select_City);
		waitForElementVisibility(select_Supplier);
		waitForElementVisibility(radiobtn_Accrual);
		waitForElementVisibility(radiobtn_Actual);
		waitForElementVisibility(radiobtn_Unbilled);
		waitForElementVisibility(fromBillingPeriodText);
		waitForElementVisibility(toBillingPeriodText);
		waitForElementVisibility(fromBillingPeriod);
		waitForElementVisibility(toBillingPeriod);
		waitForElementVisibility(generateReport);

	}

	public void clickOnSISComplianceReportLink() {
		clickOn(linkSISComplianceReport,"Reports Menu -> SIS Compliance Report Link");
	}

	public void verifyElementsOnSISComplianceReports() {
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(select_BillingPeriod);
		waitForElementVisibility(checkbox_DisplayAllDayHotalCompliant);
		waitForElementVisibility(generateReport);

	}

	public void clickOnReservationsLink() {
		clickOn(link_Reservations,"Reports Menu -> Rreservations Report Link");
	}

	public void verifyElementsOnReservationsPage() {
		waitForElementVisibility(listbox_Airlines);
		waitForElementVisibility(select_ReservationMode);
		waitForElementVisibility(textBox_ReservStartDate);
		waitForElementVisibility(datepicker_ReservStartDate);
		waitForElementVisibility(datepicker_ReservEndDate);
		waitForElementVisibility(textbox_ReservationCity);
		waitForElementVisibility(select_ReservationsSupplier);
		waitForElementVisibility(viewReservationsFormEmployee);
		waitForElementVisibility(btn_SearchFindInvoice);
	}

	public void clickOnInvoiceComplianceReport() {
		clickOn(link_InvoiceComplianceReport,"Reports Menu -> Invoice Compliance Report Link");
	}

	public void verifyElementsOnInvoiceComplianceReport() {
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_InvComplianceFormStartDate);
		waitForElementVisibility(textbox_InvComplianceFormEndDate);
		waitForElementVisibility(datepicker_InvCompliaanceStartDate);
		waitForElementVisibility(datepicker_InvCompliaanceEndDate);
		waitForElementVisibility(checkbox_NonComplaintOnly);
		waitForElementVisibility(btn_SearchFindInvoice);
	}

	public void clickOnInvoiceStatusReport() {
		clickOn(link_InvoiceStatusReport,"Reports Menu -> Invocie Status Report Link");
	}

	public void verifyElementsOnInvoiceStatusReportPage() {
		clickOn(radiobtn_InvoiceNumber,"Invocie Status Report Page -> Invoice Number Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementToDisappear(spinner);
		waitForElementVisibility(textbox_APIIvoiceNumber);
		waitForElementVisibility(textbox_SupplierInvNum);
		waitForElementVisibility(radiobtn_billingPeriodAndStatus);
		waitForElementVisibility(radiobtn_SupplierAndLoc);
		waitForElementVisibility(btn_SearchFindInvoice);
		clickOn(radiobtn_billingPeriodAndStatus,"Invocie Status Report Page -> Billing Period and Status Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(startDateTextBox);
		waitForElementVisibility(endDateTextBox);
		waitForElementVisibility(startDate_BillingPeriod);
		waitForElementVisibility(endDate_BillingPeriod);
		waitForElementVisibility(select_InvCreation);
		waitForElementVisibility(radio_StatusAll);
		waitForElementVisibility(radio_StatusSpecific);
		waitForElementVisibility(radio_StatusNotSubmitted);
		waitForElementVisibility(btn_SearchFindInvoice);
		clickOn(radio_StatusSpecific,"Invocie Status Report Page -> Billing Period and Status -> Specific Status Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(checkbox_Submitted_PendingReview);
		waitForElementVisibility(checkbox_Submitted_onhold);
		waitForElementVisibility(checkbox_Returnedtohotel);
		waitForElementVisibility(checkbox_ApprovedForPayment);
		waitForElementVisibility(checkbox_IncludeVoidInvoices);
		waitForElementVisibility(checkbox_PaymentGroupAccepted);
		waitForElementVisibility(checkbox_PaymentGroupAccepted_PendingReferenceNumber);
		waitForElementVisibility(checkbox_PaymentGroupAccepted_PendingVENC);
		waitForElementVisibility(checkbox_PaymentGroupAccepted_PendingNotaFiscal);
		waitForElementVisibility(checkbox_PaymentGroupAccepted_PendingNotaFiscalAcceptance);
		waitForElementVisibility(checkbox_PaymentGroupAccepted_PendingPaidDate);
		waitForElementVisibility(checkbox_PaymentGroupAccepted_WorkflowComplete);
		waitForElementVisibility(btn_SearchFindInvoice);
		clickOn(radio_StatusNotSubmitted,"Invocie Status Report Page -> Billing Period and Status -> Not Submitted Status Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(startDateTextBox);
		waitForElementVisibility(endDateTextBox);
		waitForElementVisibility(startDate_BillingPeriod);
		waitForElementVisibility(endDate_BillingPeriod);
		waitForElementVisibility(select_InvCreation);
		waitForElementVisibility(radio_StatusAll);
		waitForElementVisibility(radio_StatusSpecific);
		waitForElementVisibility(radio_StatusNotSubmitted);
		waitForElementVisibility(btn_SearchFindInvoice);

	}

	public void clickOnAccrualSnapshotLink() {
		clickOn(link_AccrualSnapshot,"Reports Menu -> Accrual Snapshot Report Link");
	}

	public void verifyElementsOnAccrualSnapshotPage() {
		waitForElementVisibility(selectAirline);
	}

	public void clickOnConsolidatedEInvoiceReportLink() {
		clickOn(link_ConsolidatedEInvoiceReport,"Reports Menu -> Consolidated E-Invoice Report Link");
	}

	public void verifyElementsOnConsolidatedEInvoiceReportPage() throws Exception {
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_CityConsolidatedEInvoiceReport);
		waitForElementVisibility(supplier);
		waitForElementVisibility(radiobtn_AccrualConInvoRpt);
		waitForElementVisibility(radiobtn_ActualConInvoRpt);
		waitForElementVisibility(radiobtn_Variance);
		waitForElementVisibility(consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(btn_Submit);

		clickOn(radiobtn_ActualConInvoRpt,"Consolidated E-Invoice Report -> Actual Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_CityConsolidatedEInvoiceReport);
		waitForElementVisibility(supplier);
		waitForElementVisibility(radiobtn_AccrualConInvoRpt);
		waitForElementVisibility(radiobtn_ActualConInvoRpt);
		waitForElementVisibility(radiobtn_Variance);
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(btn_Submit);
		clickOn(radiobtn_ApprovedDate,"Consolidated E-Invoice Report -> Actual Report Type -> Approved Date Radio Button");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_CityConsolidatedEInvoiceReport);
		waitForElementVisibility(supplier);
		waitForElementVisibility(radiobtn_AccrualConInvoRpt);
		waitForElementVisibility(radiobtn_ActualConInvoRpt);
		waitForElementVisibility(radiobtn_Variance);
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(btn_Submit);

		clickOn(radiobtn_Variance,"Consolidated E-Invoice Report -> Variance Report Type Radio Button ");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_CityConsolidatedEInvoiceReport);
		waitForElementVisibility(supplier);
		waitForElementVisibility(radiobtn_AccrualConInvoRpt);
		waitForElementVisibility(radiobtn_ActualConInvoRpt);
		waitForElementVisibility(radiobtn_Variance);
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(btn_Submit);
		clickOn(radiobtn_ApprovedDate,"Consolidated E-Invoice Report -> Variance Report Type -> Approved Date Radio Button");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_CityConsolidatedEInvoiceReport);
		waitForElementVisibility(supplier);
		waitForElementVisibility(radiobtn_AccrualConInvoRpt);
		waitForElementVisibility(radiobtn_ActualConInvoRpt);
		waitForElementVisibility(radiobtn_Variance);
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_StartDate);
		waitForElementVisibility(consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(datepicker_consolidatedInvoiceReport_EndDate);
		waitForElementVisibility(btn_Submit);
	}

	
}
	

