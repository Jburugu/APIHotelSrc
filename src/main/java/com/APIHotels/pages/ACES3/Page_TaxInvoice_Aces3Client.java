package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_TaxInvoice_Aces3Client extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "eInvoiceForm:paymentGroupPanel:groupName")
	private WebElement groupName;
	
	@FindBy(id = "eInvoiceForm:paymentGroupPanel:addGroupBtn")
	private WebElement addGroupBtn;
	
	private String paymentGroupEditLinkXpath1 = "//a[contains(text(), '";
	private String paymentGroupEditLinkXpath2 = "')]/../following-sibling::td[6]/a";
	private String pgAdjustmentsLinkXpath = "')]/../following-sibling::td[4]/a";
	
	@FindBy(xpath = "//input[@id='paymentGroupDialogForm:paymentGroupEditDialog:folioTable:nameColumn:filter']")
	private WebElement editGroupNameFilter;
	
	private String noneLinkXpath1 = "//*[contains(text(), '";
	private String noneLinkXpath2 = "')]/../following-sibling::td[5]/a";
	
	@FindBy(id = "paymentGroupDialogForm:paymentGroupSelectDialog:paymentGroup_label")
	private WebElement paymentGroupDropDown;
	 
	private String paymentGroupOptionXpath1 = "//ul[@id='paymentGroupDialogForm:paymentGroupSelectDialog:paymentGroup_items']/li[contains(text(), '";
	private String paymentGroupOptionXpath2 = "')]";
	
	@FindBy(xpath = "//button[contains(@id, 'paymentGroupDialogForm:paymentGroupSelectDialog')]/span[contains(text(), 'OK')]")
	private WebElement selectPaymentGroupOkBtn;
	
	@FindBy(xpath = "//button[contains(@id, 'paymentGroupDialogForm:paymentGroupEditDialog')]/span[contains(text(), 'OK')]")
	private WebElement editPaymentGroupDialogOKBtn;
	
	@FindBy(id = "paymentGroupDialogForm:paymentGroupEditDialog:folioTable:paymentGroupFilterId:filter")
	private WebElement paymentGroupFilterDropDown;
	
	@FindBy(xpath = "//*[contains(@id, 'paymentGroupDialogForm:paymentGroupEditDialog') and contains(text(), 'All')]")
	private WebElement includeInPaymentGroup_AllLink;
	
	private String paymentGroupNameLinkXpath1 = "//a[(text()='";
	private String paymentGroupNameLinkXpath2 = "')]";
	
	@FindBy(xpath = "//h4[@class='invoiceScreenTitle']")
	private WebElement invocieWindowTitle;
	
	@FindBy(xpath = "//*[contains(text(), 'Check-out Rooms')]/following-sibling::td")
	private List<WebElement> billingDatesList;
	
	@FindBy(xpath = "//*[contains(@id, 'detailsLink')]")
	private List<WebElement> dailyCheckOutsDetailsLinkList;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceDailyRoomDetails:dataTableCheckOuts_data']/tr/td[4]/span")
	private List<WebElement> dailyRoomDetails_EmpName;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceDailyRoomDetails:invoiceDailyRoomDialogId_title']/following-sibling::a[@aria-label='Close']")
	private WebElement dailyRoomDetails_CloseIcon;
	
	@FindBy(xpath = "//*[contains(text(), 'Consolidate Payment Groups')]/../../div[contains(@title,'Consolidate Payment Groups into this invoice')]")
	private WebElement consolidatePaymentGroupsChkBox;
	
	@FindBy(id = "eInvoiceForm:approveBtn")
	private WebElement approveBtn;
	
	@FindBy(xpath = "//h4[contains(text(), 'Invoice Exceptions')]/following-sibling::table//td[contains(text(), 'Billable No-shows')]/following-sibling::td/a")
	private List<WebElement> exception_BillableNoShowsList;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiAdjustmentPanel_header']/span[contains(text(), 'API Auditor Adjustments')]/../a")
	private WebElement apiAuditorAdjustmentsToggleIcon;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiAdjustmentPanel_header']/span[contains(text(), 'API Auditor Adjustments')]/../a/span")
	private WebElement apiAuditorAdjustmentsToggleIconClass;
	
	@FindBy(xpath = "//h4[contains(text(),'API Auditor Adjustments')]/parent::td/parent::tr/following-sibling::tr[1]//button/span[text()='Add Row']")
	private WebElement apiAuditorAdjustments_addRowBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiReason_label')]")
	private WebElement apiAuditorAdjustmentsReasonDropDown;
	
	private String apiAuditorAdjustmentReasonXpath1 = "//ul[contains(@id, 'apiReason_items')]/li[contains(text(), '";
	private String apiAuditorAdjustmentReasonXpath2 = "')]";
	
	@FindBy(xpath = "//textarea[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiComment')]")
	private WebElement apiAuditorAdjustmentsComments;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiAmount')]")
	private WebElement apiAuditorAdjustmentsAmount;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiRoomNightCount')]")
	private WebElement apiAuditorAdjustmentsNoOfRooms;
	
	@FindBy(xpath = "//label[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiRoomType_label')]")
	private WebElement apiAuditorAdjustmentsRoomTypeDropDown;
	
	private String apiAuditorAdjustmentsRoomTypeXpath1 = "//ul[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiRoomType_items')]/li[contains(text(),'";
	private String apiAuditorAdjustmentsRoomTypeXpath2 = "')]";
	
	@FindBy(xpath = "//div[contains(@id, 'apiAdjustmentDlgPanel')]//table[2]//button[contains(@id, 'adjustmentsDialogForm')]/span[contains(text(), 'OK')]")
	private WebElement apiAuditorAdjustmentsOKBtn;
	
	@FindBy(xpath = "//a[contains(@id, 'glAllocStatus')]")
	private WebElement glAllocationsLink;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Allocation')]")
	private WebElement addAllocationLink;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentAmount')]")
	private WebElement glAllocationAmount;
	
	@FindBy(xpath = "//*[contains(@id, 'adjustmentPercent')]")
	private WebElement glAdjustmentPercent;
	
	@FindBy(xpath = "//*[@id='unexpectedDlgId']//span[contains(text(), 'OK')]")
	private WebElement unexpectedErrorOkBtn;
	
	@FindBy(xpath = "//input[contains(@id, 'glCode')]")
	private WebElement glCode;
	
	@FindBy(xpath = "//button[contains(@id, 'glAllocationDialogForm')]/span[contains(text(), 'OK')]")
	private WebElement glAllocationsOkBtn;
	
	@FindBy(xpath = "//h4[contains(text(),'API Auditor Tax Adjustments')]/parent::td/parent::tr/following-sibling::tr[1]//button/span[text()='Add Row']")
	private WebElement apiAuditorTaxAdjustments_addRowBtn;
	
	@FindBy(xpath = "//label[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiTax_label')]")
	private WebElement apiAuditorTaxAdjustment_taxDropDown;
	
	private String taxOptionXpath1 = "//ul[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiTax_items')]/li[contains(text(), '";
	private String taxOptionXpath2 = "')]";
	
	@FindBy(xpath = "//ul[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiTax_items')]/li")
	private List<WebElement> taxOptionList;
	
	@FindBy(xpath = "//textarea[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiTaxComment')]")
	private WebElement apiAuditorTaxAdjustmentsComments;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiTaxAmount')]")
	private WebElement apiAuditorTaxAdjustmentsAmount;
	
	@FindBy(xpath = "//div[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'apiTaxAdjustmentDlgPanel')]//table[2]//button/span[contains(text(), 'OK')]")
	private WebElement apiAuditorTaxAdjustmentsOKBtn;
	
	@FindBy(xpath = "//div[contains(@id, 'apiTaxesTable')]/a[contains(@id, 'glAllocStatus')]")
	private WebElement apiTaxGLAllocationsLink;
	
	@FindBy(id = "eInvoiceForm:invoiceComments:commentsPanel_toggler")
	private WebElement invoiceCommentsToggler;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceComments:commentsPanel_toggler']/span")
	private WebElement invoiceCommentsTogglerClass;
	
	@FindBy(xpath = "//h4[contains(text(),'Hotel Comments')]/parent::td/table//button/span[text()='Add Comment']")
	private WebElement addCommentBtn;
	
	@FindBy(xpath = "//textarea[@id='auditorCommentDialogForm:invoiceAuditorCommentsDlg:auditorComments']")
	private WebElement invoiceAuditorComments;
	
	@FindBy(xpath = "//button[contains(@id, 'auditorCommentDialogForm:invoiceAuditorCommentsDlg')]/span[contains(text(), 'OK')]")
	private WebElement invoiceCommentsDialogOKBtn;
	
	@FindBy(id = "eInvoiceForm:invoiceAttachments:attachmentsPanel_toggler")
	private WebElement invoiceAttachmentToggler;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAttachments:attachmentsPanel_toggler']/span")
	private WebElement invoiceAttachmentTogglerClass;
	
	@FindBy(xpath = "//*[contains(text(), 'Upload Attachments')]")
	private WebElement uploadAttachmentsBtn;
	
	@FindBy(id = "attachmentUploadForm:invoiceAttachmentUpload:attachmentTypeSelect_label")
	private WebElement attachmentTypeDropDown;
	
	private String attachmentTypeXpath1 = "//ul[@id='attachmentUploadForm:invoiceAttachmentUpload:attachmentTypeSelect_items']/li[contains(text(), '";
	private String attachmentTypeXpath2 = "')]";
	
	@FindBy(id = "attachmentUploadForm:invoiceAttachmentUpload:daySelect_label")
	private WebElement applicableDayDropDown;
	
	@FindBy(xpath = "//ul[@id='attachmentUploadForm:invoiceAttachmentUpload:daySelect_items']/li[3]")
	private WebElement applicableDayOption;
	
	@FindBy(id = "attachmentUploadForm:invoiceAttachmentUpload:fileUpload")
	private WebElement chooseFileBtn;
	
	@FindBy(id = "attachmentUploadForm:invoiceAttachmentUpload:comments")
	private WebElement fileAttachment_Comments;
	
	@FindBy(id = "attachmentUploadForm:invoiceAttachmentUpload:submit")
	private WebElement uploadFileBtn;
	
	@FindBy(id = "eInvoiceForm:holdBtn")
	private WebElement holdBtn;
	
	@FindBy(id = "eInvoiceForm:holdAndRegenBtn")
	private WebElement regenerateBtn;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:paymentGroupPanel:paymentGroupTable_data']/tr/td")
	private List<WebElement> consolidatedPaymentGroupData;
	
	@FindBy(xpath = "//button[@id='eInvoiceForm:approveBtn']/following-sibling::button//span[contains(text(), 'Return to Hotel')]")
	private WebElement returnToHotelBtn;
	
	@FindBy(id = "eInvoiceForm:returnBtn")
	private WebElement returnInvoiceToHotelBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'currencyConversionPanel_content')]//span")
	private WebElement enableCurrencyConversion;
	
	@FindBy(xpath = "//*[contains(@id, 'conversionRate_input')]")
	private WebElement conversionRate;
	
	@FindBy(xpath = "//*[contains(@id, 'targetCurrency_input')]")
	private WebElement targetCurrency;
	
	private String targetCurrencyOptXpath1 = "//*[contains(@id, 'targetCurrency_panel')]/ul/li/span[contains(text(), '";
	private String targetCurrencyOptXpath2 = "')]";
	
	@FindBy(xpath = "//*[contains(text(), 'Recalculate Invoice Amount')]")
	private WebElement recalcInvoiceAmountBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'summaryItemTable_foot')]//td[2]")
	private WebElement totalForNoOfRooms;
	
	@FindBy(xpath = "//*[contains(@id, 'invoiceTaxTable_foot')]//td[3]")
	private WebElement taxesTotal;
	
	@FindBy(xpath = "//*[contains(text(), 'Total Billable Rooms')]/following-sibling::td[33]")
	private WebElement totalBillableRooms;
	
	@FindBy(xpath = "//*[contains(text(), 'Total Single Room Amount')]/following-sibling::td")
	private WebElement totalBillableRooms_invoiceSummary;
	
	@FindBy(xpath = "//*[contains(text(), 'Single Room Taxes')]/following-sibling::td")
	private WebElement singleRoomTaxes_InvoiceSummary;
	
	@FindBy(xpath = "//*[contains(text(), 'Check-out Rooms')]/following-sibling::td[32]")
	private WebElement checkOutRoomsAmount;
	
	@FindBy(xpath = "//*[contains(text(), 'Total for') and contains(text(), 'Single Rooms')]/following-sibling::td")
	private WebElement totalAmountForSingleRooms_InvoiceSummary;
	
	private String taxAdjustmentRows = "//*[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupTaxAdjTable']//tbody/tr";
	private String taxAdjustmentAmount = "/td[3]";
	private String addAdjustmentAmountToFirstGroup = "/td[4]/input";
	
	@FindBy(id = "paymentGroupDialogForm:paymentGroupAdjustmentDialog:savePaymentGroupAdjs")
	private WebElement paymentGroupAdjustmentsDialogOkBtn;
	
	private String invoiceException_billableNoShowEmpXpath1 = "//*[contains(@id, 'exceptionsTable_data')]//span[contains(text(), '";
	private String invoiceException_billableNoShowEmpXpath2 = "')]";
	
	@FindBy(xpath = "//span[contains(text(), 'Close')]")
	private WebElement invoiceExceptionsCloseBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Void Invoice')]")
	private WebElement voidInvoiceBtn;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:cantVoid']//span[contains(text(), 'OK')]")
	private WebElement voidInvoiceEnterReasonAlert_OKBtn;
	
	private String totalBillableRoomsXpath1 = "//*[contains(text(), 'Total Billable Rooms')]/following-sibling::td[";
	private String totalAmountXpath = "]";
	
	private String checkOutRoomsAmountXpath1 = "//*[contains(text(), 'Check-out Rooms')]/following-sibling::td[";
	
	@FindBy(xpath = "//*[@id='dailRoomDetails_header2']//th")
	private List<WebElement> dailyCheckOutsTableHeaders;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:paymentGroupPanel:paymentGroupTable_foot']//td[5]/span")
	private WebElement unallocatedAdjustment;
	
	@FindBy(id = "eInvoiceForm:void")
	private WebElement voidInvoiceConfirmBtn;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:paymentGroupPanel:paymentGroupTable_data']//td[5]/a")
	private WebElement adjustmentAmountEditLink;
	
	@FindBy(xpath = "//*[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupApiItemAdjTable']//td[5]/span")
	private WebElement apiAuditorAdjAmt;
	
	@FindBy(xpath = "//*[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupApiItemAdjTable']//td[4]/input")
	private WebElement auditorAdjAmtForGrp;
	
	@FindBy(xpath = "//*[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupApiTaxAdjTable']//td[5]/span")
	private WebElement apiAuditorTaxAdjAmt;
	
	@FindBy(xpath = "//*[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:paymentGroupApiTaxAdjTable']//td[4]/input")
	private WebElement auditorTaxAmtForGrp;
	
	@FindBy(xpath = "//*[@id='paymentGroupDialogForm:paymentGroupAdjustmentDialog:savePaymentGroupAdjs']/span")
	private WebElement auditorAdjAmtDialogBoxOkBtn;
	
	@FindBy(xpath = "//span[contains(text(), 'Create Groups')]")
	private WebElement createGroupsBtn;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:paymentGroupPanel:paymentGroupTable_data']/tr")
	private List<WebElement> paymentGroupRows;
	
	String supplierNameXpath="//td[contains(text(),'";
	String InvoiceNumberXpath="')]/preceding-sibling::td[5]/a";
	
	@FindBy(id="dailyRoomDetail")
	private WebElement auditNotestable;
	
	@FindBy(xpath="//*[contains(text(), 'Crew ID Issues')]/following-sibling::td/a")
	private List<WebElement> countsXpath ;
	
	@FindBy(id = "eInvoiceForm:invoiceAdjustments:adjustmentPanel_toggler")
	private WebElement hotelAdjustmentHeaderToggler;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:adjustmentPanel_toggler']/span")
	private WebElement hotelAdjustmentHeaderClass;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiAdjustmentsTable_data']//td[1]/span")
	private WebElement apiAuditorAdj_ReasonData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiAdjustmentsTable_data']//td[2]/span")
	private WebElement apiAuditorAdj_CommentsData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiAdjustmentsTable_data']//td[3]/span")
	private WebElement apiAuditorAdj_AmountData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiTaxesTable_data']//td[2]/span")
	private WebElement apiAuditorTaxAdj_CommentsData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:apiInvoiceAdjustments:apiTaxesTable_data']//td[3]/span")
	private WebElement apiAuditorTaxAdj_AmountData;
	
	
	@FindBy(xpath = "//*[contains(text(), 'Auditor Comments')]/following-sibling::table//table//td[5]/span")
	private WebElement apiAuditorInvoiceCommentsData;

	
	public EventFiringWebDriver driver=null;
	public Page_TaxInvoice_Aces3Client(EventFiringWebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addInvoicePaymentGroup(String groupName){
		typeInText(this.groupName, groupName, "ACES3Client TaxInvoicePage -> Group Name");
		clickOn(addGroupBtn, "ACES3Client TaxInvoicePage -> Add Group btn");
		waitForElementToDisappear(spinner);
		
	}
	
	public void clickOnEditPaymentGroup(String groupName){
		clickOn(findElementByXpath(paymentGroupEditLinkXpath1+groupName+paymentGroupEditLinkXpath2), "ACES3Client TaxInvoicePage -> Edit link for PaymentGroup: "+groupName);
		waitForElementToDisappear(spinner);
	}
	
	public void addEmployeesToGroup(String empName, String groupName) throws InterruptedException{
		typeInText(editGroupNameFilter, empName, "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> Name filter");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		clickOn(findElementByXpath(noneLinkXpath1+empName+noneLinkXpath2), "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> None link againt "+empName+" row");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		clickOn(paymentGroupDropDown, "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> PaymentGroup drop down");
		clickOn(findElementByXpath(paymentGroupOptionXpath1+groupName+paymentGroupOptionXpath2), "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> Payment Group drop down "+groupName+ " option");
		clickOn(selectPaymentGroupOkBtn, "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> Select payment group dialog -> OK btn");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		assertTrue(findElementByXpath(noneLinkXpath1+empName+noneLinkXpath2).getText().equals(groupName), "PaymentGroup not assigned!");
	}
	
	
	
	public void clickPaymentGroupDialogOK(){
		clickOn(editPaymentGroupDialogOKBtn, "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> OK btn");
		waitForElementToDisappear(spinner);
	}
	
	public void mapAllUnassignedEmployeesToGroup(String groupName){
		selectByVisibleText(paymentGroupFilterDropDown, groupName, "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> Payment Group filter drop down");
		waitForElementToDisappear(spinner);
		clickOn(includeInPaymentGroup_AllLink, "ACES3Client TaxInvoicePage -> PaymentGroup pop-up -> Include All link");
		waitForElementToDisappear(spinner);
	}

	public void verifyEmpDetailsForGroup(String groupName, String empName) throws InterruptedException {
		String taxInvoiceWindowHandle = driver.getWindowHandle();
		clickOn(findElementByXpath(paymentGroupNameLinkXpath1+groupName+paymentGroupNameLinkXpath2), "ACES3Client TaxInvoicePage -> Payment Group section -> "+groupName+" link");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		switchToNewWindow(taxInvoiceWindowHandle);
		/*if(!groupName.equals("In-Flight"))
			*/assertTrue(invocieWindowTitle.getText().contains(groupName), "Invoice window with group name is not opened!");
		/*else
			assertTrue(invocieWindowTitle.getText().contains("Cabin"), "Invoice window with group name is not opened!");*/
		int i;
		for(i=0; i<billingDatesList.size(); i++){
			if(!billingDatesList.get(i).getText().equals("-"))
				break;
		}
		clickOn(dailyCheckOutsDetailsLinkList.get(i), "ACES3Client TaxInvoicePage -> "+groupName+" TaxInvoicePage -> Daily Check Out Details btn");
		waitForElementToDisappear(spinner);
		assertTrue(dailyRoomDetails_EmpName.get(0).getText().equals(empName), "Employee name mismatch!");
		clickOn(dailyRoomDetails_CloseIcon, "ACES3Client TaxInvoicePage -> DailyRoomDetails pop-up Close btn");
		waitForElementToDisappear(spinner);
		driver.close();
		switchToWindow(taxInvoiceWindowHandle);
	}
	
	public void consolidatePaymentGroups(){
		clickOn(consolidatePaymentGroupsChkBox, "ACES3Client TaxInvoicePage -> Consolidate Payment Groups checkbox");
		waitForElementToDisappear(spinner);
	}
	
	public void approveInvoice(){
		clickOn(approveBtn, "ACES3Client TaxInvoicePage -> Approve Invoice btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifyBillableNoShowDetails(String empName){
		clickOn(exception_BillableNoShowsList.get(0), "ACES3Client TaxInvoicePage -> Invoice Exceptions - Billable No Show first link");
		waitForElementToDisappear(spinner);
		//verification code pending because of data
		assertTrue(findElementByXpath(invoiceException_billableNoShowEmpXpath1+empName+invoiceException_billableNoShowEmpXpath2).isDisplayed(), "BillableNoShow Failed!!");
		clickOn(invoiceExceptionsCloseBtn, "ACES3Client TaxInvoicePage -> Invoice Exceptions details pop-up close icon");
		waitForElementToDisappear(spinner);
	}
	
	public void addApiAuditorAdjustments(String reason, String comments, String amount, String noOfRooms, String roomType) throws InterruptedException{
		if(apiAuditorAdjustmentsToggleIconClass.getAttribute("class").contains("plus"))
			clickOn(apiAuditorAdjustmentsToggleIcon, "ACES3Client TaxInvoicePage -> API Auditor Adjustments Section header");
		waitForElementToDisappear(spinner);
		clickOn(apiAuditorAdjustments_addRowBtn, "ACES3Client TaxInvoicePage -> API Auditor Adjustments Add Row btn");
		waitForElementToDisappear(spinner);
		clickOn(apiAuditorAdjustmentsReasonDropDown, "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> Reason drop down");
		clickOn(findElementByXpath(apiAuditorAdjustmentReasonXpath1+reason+apiAuditorAdjustmentReasonXpath2), "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> Reason drop down "+reason +" option");
		waitForElementToDisappear(spinner);
		typeInText(apiAuditorAdjustmentsComments, comments, "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> Comments");
		typeInText(apiAuditorAdjustmentsAmount, amount, "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> Amount");
		if(reason.equals("Number of Rooms")){
			typeInText(apiAuditorAdjustmentsNoOfRooms, noOfRooms, "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> No of Rooms");
			clickOn(apiAuditorAdjustmentsRoomTypeDropDown, "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> Room Type drop down");
			clickOn(findElementByXpath(apiAuditorAdjustmentsRoomTypeXpath1+roomType+apiAuditorAdjustmentsRoomTypeXpath2), "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> Room Type drop down "+roomType+" option");
			clickOn(apiAuditorAdjustmentsOKBtn, "ACES3Client TaxInvoicePage -> API Auditor Adjustments pop-up -> OK btn");
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);
		}
	}
	
	public void addApiAuditorAdjustments_glAllocations(String allocationAmount, String adjustmentPercent, String glCode) throws InterruptedException{
		clickOn(glAllocationsLink, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations icon");
		waitForElementToDisappear(spinner);
		clickOn(addAllocationLink, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> Add Allocation link");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		typeInText(glAllocationAmount, allocationAmount, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> Allocation Amount");
		glAllocationAmount.sendKeys(Keys.TAB);
		Thread.sleep(3000);
		typeInText(glAllocationAmount, allocationAmount, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> Allocation Amount");
		glAllocationAmount.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		typeInText(glAdjustmentPercent, adjustmentPercent, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> Percent");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		clickOn(unexpectedErrorOkBtn, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> Unexpected Error OK btn");
		waitForElementToDisappear(spinner);
		clickOn(unexpectedErrorOkBtn, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> Unexpected Error OK btn");
		waitForElementToDisappear(spinner);
		glAdjustmentPercent.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		typeInText(this.glCode, glCode, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> GLCode");
		this.glCode.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		clickOn(glAllocationsOkBtn, "ACES3Client TaxInvoicePage -> API Auditor Adjustments GL Allocations pop-up -> OK btn");
		waitForElementToDisappear(spinner);
	}
	
	public void addApiAuditorTaxAdjustments(String taxOption, String auditorComment, String auditorTaxAmount) throws InterruptedException{
		clickOn(apiAuditorTaxAdjustments_addRowBtn, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments Add Row btn");
		waitForElementToDisappear(spinner);
		clickOn(apiAuditorTaxAdjustment_taxDropDown, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments pop-up -> Tax drop down");
		clickOn(taxOptionList.get(1), "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments pop-up -> Tax drop down "+taxOptionList.get(1).getText()+" option");
		typeInText(apiAuditorTaxAdjustmentsComments, auditorComment, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments pop-up -> Comments");
		typeInText(apiAuditorTaxAdjustmentsAmount, auditorTaxAmount, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments pop-up -> Tax Amount");
		clickOn(apiAuditorTaxAdjustmentsOKBtn, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments pop-up -> OK btn");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
	}
	
	public void addApiAuditorTaxAdjustments_glAllocations(String allocationAmount, String adjustmentPercent, String glCode) throws InterruptedException{
		clickOn(apiTaxGLAllocationsLink, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations link");
		waitForElementToDisappear(spinner);
		clickOn(addAllocationLink, "ACES3Client TaxInvoicePage -> API Auditor TaxAdjustments GL Allocations pop-up -> Add Allocation link");
		waitForElementToDisappear(spinner);
		enterValueJavaScript(glAllocationAmount, "value", allocationAmount);//(glAllocationAmount, allocationAmount);
		glAllocationAmount.sendKeys(Keys.TAB);
		typeInText(glAllocationAmount, allocationAmount, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations pop-up -> Allocation Amount");
		glAllocationAmount.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		typeInText(glAdjustmentPercent, adjustmentPercent, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations pop-up -> Percent");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		clickOn(unexpectedErrorOkBtn, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations pop-up -> Unexpected Error OK btn");
		waitForElementToDisappear(spinner);
		clickOn(unexpectedErrorOkBtn, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations pop-up -> Unexpected Error OK btn");
		waitForElementToDisappear(spinner);
		glAdjustmentPercent.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		glAdjustmentPercent.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		typeInText(this.glCode, glCode, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations pop-up -> GLCode");
		this.glCode.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		clickOn(glAllocationsOkBtn, "ACES3Client TaxInvoicePage -> API Auditor Tax Adjustments GL Allocations pop-up -> OK btn");
		waitForElementToDisappear(spinner);
	}
	
	
	public void addInvoiceComments(String comments) throws InterruptedException{
		if(invoiceCommentsTogglerClass.getAttribute("class").contains("plus"))
			clickOn(invoiceCommentsToggler, "ACES3Client TaxInvoicePage -> Invoice Comments header");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		clickOn(addCommentBtn, "ACES3Client TaxInvoicePage -> Invoice Comments Add Comment btn");
		waitForElementToDisappear(spinner);
		typeInText(invoiceAuditorComments, comments, "ACES3Client TaxInvoicePage -> Invoice Comments pop-up -> Comments");
		clickOn(invoiceCommentsDialogOKBtn, "ACES3Client TaxInvoicePage -> Invoice Comments pop-up -> OK btn");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
	}
	
	public void addInvoiceAttachments(String attachmentType, String attachmentComments) throws Exception{
		if(invoiceAttachmentTogglerClass.getAttribute("class").contains("plus"))
			clickOn(invoiceAttachmentToggler, "ACES3Client TaxInvoicePage -> Invoice Attachments header");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		waitForElementToDisappear(spinner);
		clickOn(uploadAttachmentsBtn, "ACES3Client TaxInvoicePage -> Invoice Attachments -> Upload Attachments btn");
		waitForElementToDisappear(spinner);
		clickOn(this.attachmentTypeDropDown, "ACES3Client TaxInvoicePage -> Choose File pop-up -> Type drop down");
		clickOn(findElementByXpath(attachmentTypeXpath1+attachmentType+attachmentTypeXpath2), "ACES3Client TaxInvoicePage -> Choose File pop-up -> Type drop down "+ attachmentType+" option");
		clickOn(applicableDayDropDown, "ACES3Client TaxInvoicePage -> Choose File pop-up -> Applicable Day drop down");
		clickOn(applicableDayOption, "ACES3Client TaxInvoicePage -> Choose File pop-up -> Applicable Day drop down first option");
		clickOn(chooseFileBtn, "ACES3Client TaxInvoicePage -> Choose File pop-up -> Choose File btn");
		Thread.sleep(3000);
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile_Aces3.exe"+" "+System.getProperty("user.dir")+"\\testdata\\OccupancyReport.docx");
		typeInText(fileAttachment_Comments, attachmentComments, "Comments");
		clickOn(uploadFileBtn, "ACES3Client TaxInvoicePage -> Choose File pop-up -> Upload File btn");
		Thread.sleep(2000);
	}
	
	public void holdInvoice(){
		clickOn(holdBtn, "ACES3Client TaxInvoicePage -> Hold btn");
		waitForElementToDisappear(spinner);
	}

	public void holdAndRegenerate() {
		clickOn(regenerateBtn, "ACES3Client TaxInvoicePage -> Regenerate btn");
		waitForElementToDisappear(spinner);
		assertFalse(consolidatedPaymentGroupData.size()>1, "Regenerate Failed!");
	}
	
	public void returnToHotel(){
		clickOn(returnToHotelBtn, "ACES3Client TaxInvoicePage -> Return to Hotel btn");
		waitForElementToDisappear(spinner);
		clickOn(returnInvoiceToHotelBtn, "ACES3Client TaxInvoicePage -> Return Invoice To Hotel btn");
		waitForElementToDisappear(spinner);
	}
	
	public void setEInvoiceConversion(String conversionRate, String targetCurrency) throws InterruptedException{
		String beforeConversion = totalForNoOfRooms.getText();
		clickOn(enableCurrencyConversion, "ACES3Client TaxInvoicePage -> Enable Currency Conversion check box");
		waitForElementToDisappear(spinner);
		Thread.sleep(4000);
		typeInText(this.conversionRate, conversionRate, "ACES3Client TaxInvoicePage -> Conversion Rate");
		waitForElementToDisappear(spinner);
		Thread.sleep(4000);
		//clickOn(this.targetCurrency);
		typeInText(this.targetCurrency, targetCurrency, "ACES3Client TaxInvoicePage -> Target Currency");
		waitForElementToDisappear(spinner);
		clickOn(findElementByXpath(targetCurrencyOptXpath1+targetCurrency+targetCurrencyOptXpath2), "ACES3Client TaxInvoicePage -> Target Currency drop down "+targetCurrency+" option");
		waitForElementToDisappear(spinner);
		clickOn(recalcInvoiceAmountBtn, "ACES3Client TaxInvoicePage -> Recalculate Invoice Amount btn");
		waitForElementToDisappear(spinner);
		assertFalse(beforeConversion.equals(totalForNoOfRooms.getText()), "EConversion Failed!");
	}
	
	public void verifyInvoiceAmountWithAndWithoutTaxes(){
		
		int i = 0;
		for(; i<dailyCheckOutsTableHeaders.size(); i++)
			if(dailyCheckOutsTableHeaders.get(i).getText().equals("Amount"))
				break;
		
		
		String totalTaxAmount = taxesTotal.getText();
		if(totalTaxAmount.contains(","))
			totalTaxAmount = totalTaxAmount.replace(",", "");
		
		String totalTaxAmount_InvoiceSummary = singleRoomTaxes_InvoiceSummary.getText();
		if(totalTaxAmount_InvoiceSummary.contains(","))
			totalTaxAmount_InvoiceSummary = totalTaxAmount_InvoiceSummary.replace(",", "");
		
		assertTrue(totalTaxAmount.equals(totalTaxAmount_InvoiceSummary), "taxAmount mismatch!");
		
		String totalBillableRooms = findElementByXpath(totalBillableRoomsXpath1+i+totalAmountXpath).getText();
		if(totalBillableRooms.contains(","))
			totalBillableRooms = totalBillableRooms.replace(",", "");
		
		
		String totalBillableRooms_InvoiceSummary = this.totalBillableRooms_invoiceSummary.getText();
		if(totalBillableRooms_InvoiceSummary.contains(","))
			totalBillableRooms_InvoiceSummary = totalBillableRooms_InvoiceSummary.replace(",", "");
			
		assertTrue(totalBillableRooms.equals(totalBillableRooms_InvoiceSummary));
		
		String checkOutRoomsAmount = findElementByXpath(checkOutRoomsAmountXpath1+i+totalAmountXpath).getText();
		if(checkOutRoomsAmount.contains(","))
			checkOutRoomsAmount = checkOutRoomsAmount.replace(",", "");
		
		String totalAmountForSingleRooms_InvoiceSummary = this.totalAmountForSingleRooms_InvoiceSummary.getText();
		if(totalAmountForSingleRooms_InvoiceSummary.contains(","))
			totalAmountForSingleRooms_InvoiceSummary = totalAmountForSingleRooms_InvoiceSummary.replace(",", "");
		
		assertTrue(checkOutRoomsAmount.equals(totalAmountForSingleRooms_InvoiceSummary));
		
		assertEquals(Double.parseDouble(totalBillableRooms_InvoiceSummary)+Double.parseDouble(totalTaxAmount_InvoiceSummary), Double.parseDouble(checkOutRoomsAmount));
	}
	
	public void adjustUnallocatedAmount(String groupName) throws InterruptedException {
		clickOn(findElementByXpath(paymentGroupEditLinkXpath1 + groupName + pgAdjustmentsLinkXpath), "ACES3Client TaxInvoicePage -> Payment Groups -> Adjusments link for "+ groupName);
		waitForElementToDisappear(spinner);
		
		findElementByXpath(this.taxAdjustmentRows + "[" + 1 + "]" + addAdjustmentAmountToFirstGroup).clear();
		waitForElementToDisappear(spinner);
		findElementByXpath(this.taxAdjustmentRows + "[" + 1 + "]" + addAdjustmentAmountToFirstGroup).sendKeys(""+-82.84);
		findElementByXpath(this.taxAdjustmentRows + "[" + 1 + "]" + addAdjustmentAmountToFirstGroup).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		
		findElementByXpath(this.taxAdjustmentRows + "[" + 2 + "]" + addAdjustmentAmountToFirstGroup).clear();
		waitForElementToDisappear(spinner);
		findElementByXpath(this.taxAdjustmentRows + "[" + 2 + "]" + addAdjustmentAmountToFirstGroup).sendKeys(""+-3398.40);
		findElementByXpath(this.taxAdjustmentRows + "[" + 2 + "]" + addAdjustmentAmountToFirstGroup).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		
		findElementByXpath(this.taxAdjustmentRows + "[" + 3+ "]" + addAdjustmentAmountToFirstGroup).clear();
		waitForElementToDisappear(spinner);
		findElementByXpath(this.taxAdjustmentRows + "[" + 3 + "]" + addAdjustmentAmountToFirstGroup).sendKeys(""+-1274.40);
		findElementByXpath(this.taxAdjustmentRows + "[" + 3 + "]" + addAdjustmentAmountToFirstGroup).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		
		/*
		 * typeInText(findElementByXpath(this.taxAdjustmentRows+
		 * addAdjustmentAmountToFirstGroup), ""+unallocatedAmountForTaxType);
		 * waitForElementToDisappear(spinner);
		 */

		waitForElementToDisappear(spinner);
		clickOn(paymentGroupAdjustmentsDialogOkBtn, "ACES3Client TaxInvoicePage -> Payment Groups Adjusments pop-up OK btn");
		waitForElementToDisappear(spinner);
	}
	
	public void voidInvoice(){
		clickOn(voidInvoiceBtn, "ACES3Client TaxInvoicePage -> Void Invoice btn");
		waitForElementToDisappear(spinner);
		clickOn(voidInvoiceConfirmBtn, "ACES3Client TaxInvoicePage -> Void Invoice confirm btn");
		waitForElementToDisappear(spinner);
		//clickOn(voidInvoiceEnterReasonAlert_OKBtn);
	}
	
	public void addUnallocatedAdjustmentAmount(){
		if(unallocatedAdjustment.getText() != null && !unallocatedAdjustment.getText().equals("0")){
			clickOn(adjustmentAmountEditLink, "ACES3Client TaxInvoicePage -> Payment Groups ->Adjustment Amount Edit link");
			waitForElementToDisappear(spinner);
			String auditorAdjAmt = apiAuditorAdjAmt.getText();
			typeInText(auditorAdjAmtForGrp, auditorAdjAmt, "ACES3Client TaxInvoicePage -> Auditor Adjustment Amount pop-up -> Adjustment Amount");
			auditorAdjAmtForGrp.sendKeys(Keys.TAB);
			waitForElementToDisappear(spinner);
			String auditorTaxAdjAmt = apiAuditorTaxAdjAmt.getText();
			typeInText(auditorTaxAmtForGrp, auditorTaxAdjAmt, "ACES3Client TaxInvoicePage -> Auditor Adjustment Amount pop-up -> Tax Adjustment Amount");
			auditorTaxAmtForGrp.sendKeys(Keys.TAB);
			waitForElementToDisappear(spinner);
			clickOn(auditorAdjAmtDialogBoxOkBtn, "ACES3Client TaxInvoicePage -> Auditor Adjustment Amount pop-up -> OK btn");
			waitForElementToDisappear(spinner);
		}
	}
	
	public void createAutoGroups(){
		clickOn(createGroupsBtn, "ACES3Client TaxInvoicePage -> Payment Groups -> Create Groups btn");
		waitForElementToDisappear(spinner);
		assertTrue(paymentGroupRows.size()>0, "Auto Payment Group Not Created!!");
	}
	
	public void verifyEmpSwitchToGroup(String groupName, String empName) throws InterruptedException{
		String taxInvoiceWindowHandle = driver.getWindowHandle();
		clickOn(findElementByXpath(paymentGroupNameLinkXpath1+groupName+paymentGroupNameLinkXpath2));
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		switchToNewWindow(taxInvoiceWindowHandle);
		assertTrue(invocieWindowTitle.getText().contains(groupName), "Invoice window with group name is not opened!");
		clickOn(dailyCheckOutsDetailsLinkList.get(1));
		waitForElementToDisappear(spinner);
		if(groupName.equals("Flight"))
			assertTrue(dailyRoomDetails_EmpName.get(7).getText().equals(empName), "Employee is not moved to group!");
		else if(groupName.equals("In-flight"))
			assertTrue(dailyRoomDetails_EmpName.get(9).getText().equals(empName), "Employee is not moved to group!");
		clickOn(dailyRoomDetails_CloseIcon);
		waitForElementToDisappear(spinner);
		driver.close();
		switchToWindow(taxInvoiceWindowHandle);
	}
	
	
	
	public void verifyCrewIDIssueCounts(List<String> hotelNames, List<String> issueCounts) {

		for (int count =0 ; count < 5; count++) {
			int sum = 0;
			clickOn(findElementByXpath(supplierNameXpath + hotelNames.get(count) + InvoiceNumberXpath));
			waitForElementVisibility(auditNotestable);			
			for (int tdcount = 0; tdcount < countsXpath.size(); tdcount++) {	
				String issueCount=countsXpath.get(tdcount).getText();				
				sum = sum + Integer.parseInt(issueCount);
				System.out.println(sum);
			}
			int issuecount = Integer.parseInt(issueCounts.get(count));
			System.out.println(issuecount);
			if (sum == issuecount)
				Assert.assertEquals(sum, issuecount,
						"No. Of CrewId Issues in CrewIDIssues report are equal to total no.of CrewIDIssues in Invoice Exceptions");
			else
				Assert.fail("Mismatch In CrewId counts");
			System.out.println();
			if(count!=4){
			driver.navigate().back();
			
			}
		}

	}

	public void verifyAPIAdjustments(String reason, String comments, String amount, String taxComment, String taxAmount){
		if(apiAuditorAdjustmentsToggleIconClass.getAttribute("class").contains("plus"))
			clickOn(apiAuditorAdjustmentsToggleIcon, "ACES3Client TaxInvoicePage -> API Auditor Adjustments Section header");
		waitForElementToDisappear(spinner);
		
		assertTrue(apiAuditorAdj_ReasonData.getText().equals(reason), "Api Auditor Adjustment Reason mismatch!");
		assertTrue(apiAuditorAdj_CommentsData.getText().equals(comments), "Api Auditor Adjustment Comments mismatch!");
		assertTrue(apiAuditorAdj_AmountData.getText().contains(amount), "Api Auditor Adjustment Amount mismatch!");
		
		assertTrue(apiAuditorTaxAdj_CommentsData.getText().equals(taxComment), "Api Auditor Tax Adjustment Comments mismatch!");
		assertTrue(apiAuditorTaxAdj_AmountData.getText().contains(taxAmount), "Api Auditor Tax Adjustment Amount mismatch!");
		            
	}

	public void verifyAPIAuditorInvoiceComments(String invoiceComments) throws InterruptedException{
		if(invoiceCommentsTogglerClass.getAttribute("class").contains("plus"))
			clickOn(invoiceCommentsToggler, "ACES3Client TaxInvoicePage -> Invoice Comments header");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
		assertTrue(apiAuditorInvoiceCommentsData.getText().equals(invoiceComments), "Hotel invoice Comments mismatch!");
	}
	
	
}
