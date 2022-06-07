package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_TaxInvoice_Aces3Supplier extends BasePage{
	
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[contains(@id, 'eInvoiceForm')]/span[contains(text(), 'Submit Invoice')]")
	private WebElement submitInvoiceBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'eInvoiceForm')]/span[contains(text(), 'Submit Anyway')]")
	private List<WebElement> submitAnywayBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'eInvoiceForm')]/span[contains(text(), 'OK')]")
	private WebElement invoiceSubmitted_okBtn;
	
	@FindBy(xpath = "//*[@id='addresses']/fieldset[1]")
	private WebElement apiInvoiceNumber;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:adjustmentPanel_header']/span[contains(text(), 'Hotel Adjustments')]/../a")
	private WebElement hotelAdjustmentsToggleIcon;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:adjustmentPanel_header']/span[contains(text(), 'Hotel Adjustments')]/../a/span")
	private WebElement hotelAdjustmentsToggleIconClass;
	
	@FindBy(xpath = "//h4[contains(text(),'Hotel Adjustments')]/parent::td/parent::tr/following-sibling::tr[1]//button/span[text()='Add Row']")
	private WebElement hotelAdjustmentsAddRowBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'reason_label')]")
	private WebElement hotelAdjustmentsReasonDropDown;
	
	private String hotelAdjustmentReasonXpath1 = "//ul[contains(@id, 'reason_items')]/li[contains(text(), '";
	private String hotelAdjustmentReasonXpath2 = "')]";
	
	@FindBy(xpath = "//textarea[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'comment')]")
	private WebElement hotelAdjustmentsComments;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'amount')]")
	private WebElement hotelAdjustmentsAmount;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'roomNightCount')]")
	private WebElement hotelAdjustmentsNoOfRooms;
	
	@FindBy(xpath = "//label[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'roomNightType_label')]")
	private WebElement hotelAdjustmentsRoomTypeDropDown;
	
	private String hotelAdjustmentsRoomTypeXpath1 = "//ul[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'roomNightType_items')]/li[contains(text(),'";
	private String hotelAdjustmentsRoomTypeXpath2 = "')]";
	
	@FindBy(xpath = "//div[contains(@id, 'adjustmentDlgPanel')]//table[2]//button[contains(@id, 'adjustmentsDialogForm')]/span[contains(text(), 'OK')]")
	private WebElement hotelAdjustmentsOKBtn;
	
	@FindBy(xpath = "//h4[contains(text(),'Hotel Tax Adjustments')]/parent::td/parent::tr/following-sibling::tr[1]//button/span[text()='Add Row']")
	private WebElement hotelTaxAdjustmentsAddRowBtn;
	
	@FindBy(xpath = "//label[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'tax_label')]")
	private WebElement hotelTaxAdjustments_TaxDropDown;
	
	/*private String taxOptionXpath1 = "//ul[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'tax_items')]/li[contains(text(), '";
	private String taxOptionXpath2 = "')]";*/
	
	@FindBy(xpath = "//ul[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'tax_items')]/li")
	private List<WebElement> taxOptionList;
	
	@FindBy(xpath = "//textarea[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'taxComment')]")
	private WebElement hotelTaxAdjustmentsComments;
	
	@FindBy(xpath = "//input[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'taxAmount')]")
	private WebElement hotelTaxAdjustmentsAmount;
	
	@FindBy(xpath = "//div[contains(@id, 'adjustmentsDialogForm') and contains(@id, 'taxAdjustmentDlgPanel')]//table[2]//button/span[contains(text(), 'OK')]")
	private WebElement hotelTaxAdjustmentsOKBtn;
	
	@FindBy(xpath = "//textarea[contains(@id, 'eInvoiceForm:invoiceAdjustments:')]")
	private WebElement adjustmentComments;
	
	@FindBy(id = "eInvoiceForm:invoiceComments:commentsPanel_toggler")
	private WebElement invoiceCommentsToggler;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceComments:commentsPanel_toggler']/span")
	private WebElement invoiceCommentsTogglesClass;
	
	@FindBy(xpath = "//h4[contains(text(),'Hotel Comments')]/parent::td/table//button/span[text()='Add Comment']")
	private WebElement addCommentBtn;
	
	@FindBy(xpath = "//textarea[@id='hotelCommentsDialogForm:invoiceHotelCommentsDlg:hotelComments']")
	private WebElement invoiceHotelComments;
	
	@FindBy(xpath = "//button[contains(@id, 'hotelCommentsDialogForm:invoiceHotelCommentsDlg')]/span[contains(text(), 'OK')]")
	private WebElement invoiceCommentsDialogOKBtn;
	
	@FindBy(id = "eInvoiceForm:invoiceAttachments:attachmentsPanel_toggler")
	private WebElement invoiceAttachmentToggler;
	
	@FindBy(xpath = "//*[@id = 'eInvoiceForm:invoiceAttachments:attachmentsPanel_toggler']/span")
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
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:adjustmentsTable_data']//td[1]/span")
	private WebElement hotelAdjustmentsReasonData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:adjustmentsTable_data']//td[2]/span")
	private WebElement hotelAdjustmentsCommentsData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:adjustmentsTable_data']//td[3]/span")
	private WebElement hotelAdjustmentsAmountData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:taxesTable_data']//td[1]/span")
	private WebElement hotelTaxAdjustmentTaxData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:taxesTable_data']//td[2]/span")
	private WebElement hotelTaxAdjustmentCommentsData;
	
	@FindBy(xpath = "//*[@id='eInvoiceForm:invoiceAdjustments:taxesTable_data']//td[3]/span")
	private WebElement hotelTaxAdjustmentTaxAmountData;
	
	@FindBy(xpath = "//*[contains(text(), 'Hotel Comments')]/following-sibling::table//table//td[3]/span")
	private WebElement hotelInvoiceCommentsData;
	
	public EventFiringWebDriver driver=null;
	
	public Page_TaxInvoice_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void submitInvoice(){
		clickOn(submitInvoiceBtn, "ACES3Supplier TaxInvoicePage -> Submit Invoice btn");
		waitForElementToDisappear(spinner);
		if(submitAnywayBtn.size()>0){
			clickOn(submitAnywayBtn.get(0), "ACES3Supplier TaxInvoicePage -> SubmitAnyways btn");
			waitForElementToDisappear(spinner);
		}
		clickOn(invoiceSubmitted_okBtn, "ACES3Supplier TaxInvoicePage -> Invoice Submitted Msg pop-up OK btn");
		waitForElementToDisappear(spinner);
	}
	
	public String getInvoiceNumber(){
		System.out.println(apiInvoiceNumber.getText());
		return apiInvoiceNumber.getText().split(":")[6].trim();
	}
	
	public void addHotelAdjustments(String reason, String comments, String amount, String noOfRooms, String roomType) throws InterruptedException{
		if(hotelAdjustmentsToggleIconClass.getAttribute("class").contains("plus"))
			clickOn(hotelAdjustmentsToggleIcon, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments section toggle");
		clickOn(hotelAdjustmentsAddRowBtn, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments Add btn");
		waitForElementToDisappear(spinner);
		clickOn(hotelAdjustmentsReasonDropDown, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-uo -> Reason drop down");
		clickOn(findElementByXpath(hotelAdjustmentReasonXpath1+reason+hotelAdjustmentReasonXpath2), "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> Reason: "+reason+" option");
		waitForElementToDisappear(spinner);
		typeInText(hotelAdjustmentsComments, comments, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> Comments");
		typeInText(hotelAdjustmentsAmount, amount, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> Amount");
		if(reason.equals("Number of Rooms")){
			typeInText(hotelAdjustmentsNoOfRooms, noOfRooms, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> No of Rooms");
			clickOn(hotelAdjustmentsRoomTypeDropDown, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> Room Type drop down");
			clickOn(findElementByXpath(hotelAdjustmentsRoomTypeXpath1+roomType+hotelAdjustmentsRoomTypeXpath2), "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> Room Type: "+roomType+" option");
			clickOn(hotelAdjustmentsOKBtn, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments pop-up -> OK btn");
			waitForElementToDisappear(spinner);
			Thread.sleep(2000);
		}
	}
	
	public void addHotelTaxAdjustments(String taxOption, String hotelTaxComments, String hotelTaxAmount) throws InterruptedException{
		clickOn(hotelTaxAdjustmentsAddRowBtn, "ACES3Supplier TaxInvoicePage -> Hotel Tax Adjustments Add btn");
		waitForElementToDisappear(spinner);
		if(!taxOption.equals("")){
			clickOn(hotelTaxAdjustments_TaxDropDown, "ACES3Supplier TaxInvoicePage -> Hotel Tax Adjustments pop-up -> Tax drop down");
			clickOn(taxOptionList.get(1), "ACES3Supplier TaxInvoicePage -> Hotel Tax Adjustments pop-up -> Tax drop down :"+taxOptionList.get(1).getText()+" option");
		}
		typeInText(hotelTaxAdjustmentsComments, hotelTaxComments, "ACES3Supplier TaxInvoicePage -> Hotel Tax Adjustments pop-up -> Comments");
		typeInText(hotelTaxAdjustmentsAmount, hotelTaxAmount, "ACES3Supplier TaxInvoicePage -> Hotel Tax Adjustments pop-up -> Tax Amount");
		clickOn(hotelTaxAdjustmentsOKBtn, "ACES3Supplier TaxInvoicePage -> Hotel Tax Adjustments pop-up -> OK btn");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
	}
	
	public void addInvoiceComments(String comments) throws InterruptedException{
		if(invoiceCommentsTogglesClass.getAttribute("class").contains("plus"))
			clickOn(invoiceCommentsToggler);
		Thread.sleep(2000);
		clickOn(addCommentBtn, "ACES3Supplier TaxInvoicePage -> Invoice Comments Add btn");
		waitForElementToDisappear(spinner);
		typeInText(invoiceHotelComments, comments, "CES3Supplier TaxInvoicePage -> Invoice Comments pop-up -> Comments");
		clickOn(invoiceCommentsDialogOKBtn, "CES3Supplier TaxInvoicePage -> Invoice Comments pop-up -> OK btn");
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
	}
	
	public void addInvoiceAttachments(String attachmentType, String attachmentComments) throws Exception{
		if(invoiceAttachmentTogglerClass.getAttribute("class").contains("plus"))
			clickOn(invoiceAttachmentToggler, "ACES3Supplier TaxInvoicePage -> Invoice Attachment section header");
		waitForElementToDisappear(spinner);
		clickOn(uploadAttachmentsBtn, "ACES3Supplier TaxInvoicePage -> Upload Attachments btn");
		waitForElementToDisappear(spinner);
		clickOn(this.attachmentTypeDropDown, "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Type drop down");
		clickOn(findElementByXpath(attachmentTypeXpath1+attachmentType+attachmentTypeXpath2), "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Type drop down: "+attachmentType+" option");
		clickOn(applicableDayDropDown, "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Applicable Day drop down");
		clickOn(applicableDayOption, "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Applicable Day 1st option");
		clickOn(chooseFileBtn, "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Choose File btn");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile_Aces3.exe"+" "+System.getProperty("user.dir")+"\\testdata\\OccupancyReport.docx");
		typeInText(fileAttachment_Comments, attachmentComments, "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Attachment Comments");
		clickOn(uploadFileBtn, "ACES3Supplier TaxInvoicePage -> Invoice Attachment pop-up -> Upload File btn");
		Thread.sleep(2000);
	}
	
	public void verifyHotelAdjustments(String reason, String comments, String amount, String hotelTaxComments, String hotelTaxAmount) throws InterruptedException{
		if(hotelAdjustmentsToggleIconClass.getAttribute("class").contains("plus"))
			clickOn(hotelAdjustmentsToggleIcon, "ACES3Supplier TaxInvoicePage -> Hotel Adjustments section toggle");
		Thread.sleep(2000);
		assertTrue(hotelAdjustmentsReasonData.getText().trim().equals(reason), "Hotel Adjustment Reason mismatch!");
		assertTrue(hotelAdjustmentsCommentsData.getText().equals("comments"), "Hotel Adjustment Comments mismatch!");
		assertTrue(hotelAdjustmentsAmountData.getText().contains(amount), "Hotel Adjustment Amount mismatch!");
		
		assertTrue(hotelTaxAdjustmentCommentsData.getText().equals(hotelTaxComments), "Hotel tax adjusments Comments mismatch!");
		assertTrue(hotelTaxAdjustmentTaxAmountData.getText().contains(hotelTaxAmount), "Hotel tax adjustments Amount mismatch!");
		
	}

	public void verifyHotelInvoiceComments(String invoiceComments) throws InterruptedException{
		if(invoiceCommentsTogglesClass.getAttribute("class").contains("plus"))
			clickOn(invoiceCommentsToggler);
		Thread.sleep(2000);
		assertTrue(hotelInvoiceCommentsData.getText().equals(invoiceComments), "Hotel invoice Comments mismatch!");
	}
}
