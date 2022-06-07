package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreateAllowanceInvoice_Aces3Supplier extends BasePage{
	
	@FindBy(id = "createAllowanceInvoiceSearchForm:tenant_label")
	private WebElement selectAirlineDropDown;
	
	@FindBy(id = "createAllowanceInvoiceSearchForm:bidPeriodDate_label")
	private WebElement billingPeriodDropDown;
	
	@FindBy(id = "createAllowanceInvoiceSearchForm:invoiceNumber")
	private WebElement supplierInvoiceNumber;
	
	@FindBy(xpath = "//span[contains(text(), 'Create')]")
	private WebElement createBtn;

	@FindBy(xpath = "//a[text()=\"Create Allowance Invoice\"]")
	private WebElement creteAllowancesInvoiceLink;

	@FindBy(xpath = "//span[text()= 'Allowances']")
	private WebElement AllowancesLinkNewSupplier;
	
	private String tenantXpath1 = "//*[@id='createAllowanceInvoiceSearchForm:tenant_items']/li[text()='";
	private String tenantXpath2 = "']";
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//label[@id='createAllowanceInvoiceSearchForm:bidPeriodDate_label' and contains(@class, 'ui-selectonemenu-label')]")
	private WebElement dropdwon_BillingPeriod;
	
	@FindBy(id = "selectSupplierForm:supplierName_input")
	private WebElement supplierNameInput;
	
	private String selectBillingPeriodXpath1 ="//*[@id='createAllowanceInvoiceSearchForm:bidPeriodDate_items']/li[contains(text(), '";
	private String selectBillingPeriodXpath2 = "')]";
	
	@FindBy (xpath="//li[@class='ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all' and contains(@data-label,'30 SEP 2019 - 06 OCT 2019')]")
	private WebElement billingPeriod;
	
	@FindBy (id="createAllowanceInvoiceSearchForm:invoiceNumber")
	private WebElement textbox_InvoiceNumber;
	
	@FindBy (xpath="//*[@id='createAllowanceInvoiceSearchForm:unsumbiteSigninSheetTable_data']//td[2]/a")
	private List<WebElement> SignInSheetList;
	
	@FindBy (xpath ="//div[@id='createAllowanceSignInSearchForm:allowancePanel']//table/parent::div/parent::div/parent::div/button")
	private List<WebElement> attachAllowanceSheetButtons;
	
	@FindBy(xpath = "//*[@id='createAllowanceSignInSearchForm:attachmentTable_data']//td[3]/a")
	private WebElement attachments;
	
	@FindBy(id = "createAllowanceSignInSearchForm:submitSheetButton")
	private WebElement submitSISBtn;
	
	@FindBy (xpath = "//*[@id='createAllowanceInvoiceSearchForm:unsumbiteSigninSheetTable_data']//td[1]")
	List<WebElement> sisDateList;
	
	@FindBy(xpath = "//*[@id='createAllowanceSignInSearchForm:messages']//li/span")
	List<WebElement> allowanceSheetErrorList;
	
	@FindBy(xpath = "//*[text()='API Invoice Number']/../following-sibling::td")
	private WebElement apiInvoiceNumber;
	
	@FindBy(xpath = "//*[text()='Allowance']/following-sibling::td")
	private List<WebElement> allowanceAmountList;
	
	@FindBy(xpath = "//*[text()='Adjustment']/following-sibling::td")
	private List<WebElement> adjustmentAmountList;
	
	@FindBy(xpath = "//*[text()='Total Daily Amount']/following-sibling::td")
	private List<WebElement> totalDailyAmountList;
	
	@FindBy(xpath = "//span[text()='Add Adjustment']")
	private WebElement addHotelAdjustmentBtn;
	
	@FindBy(id = "allowanceViewInvoiceForm:reasonID_label")
	private WebElement reasonDropDown;
	
	private String reasonXpath1 = "//ul[@id='allowanceViewInvoiceForm:reasonID_items']/li[text()='";
	private String reasonXpath2 = "']";
	
	@FindBy(id = "allowanceViewInvoiceForm:adjustmentCommentID")
	private WebElement commentTextArea;
	
	@FindBy(id = "allowanceViewInvoiceForm:amountID")
	private WebElement amount;
	
	@FindBy(xpath = "//span[text()='OK']")
	private WebElement hotelAdjustments_OKBtn;
	
	@FindBy(xpath = "//*[@id='allowanceViewInvoiceForm:hotelAdjustmentsTable_data']/tr[@data-ri='0']")
	private WebElement hotelAdjustments_tableData;
	
	@FindBy(xpath = "//span[text()='Add Comment']")
	private WebElement addCommentBtn;
	
	@FindBy(id = "allowanceViewInvoiceForm:commentCommentID")
	private WebElement hotelCommentsTextArea;
	
	@FindBy(xpath = "//*[@id='allowanceViewInvoiceForm:commentdialogValues']//span[text()='OK']")
	private WebElement hotelCommentsPopUpOKBtn;
	
	@FindBy(xpath = "//*[@id='allowanceViewInvoiceForm:hotelCommentsTable_data']/tr[@data-ri='0']")
	private WebElement hotelCommentsTableData;
	
	@FindBy(xpath = "//span[text()='Upload']")
	private WebElement invoiceAttachmentsUploadBtn;
	
	@FindBy(id = "uploadForm:attachCommentID")
	private WebElement allowanceInvoiceAttachmentsComments;
	
	@FindBy(xpath = "//*[@id='uploadForm:fileUpload']//span")
	private WebElement chooseFileBtn;
	
	@FindBy(id = "uploadForm:filenameUpdate")
	private WebElement fileNameUpdateText;
	
	@FindBy(xpath = "//*[@id='uploadForm:attachDialogValues']//span[text()='OK']")
	private WebElement invoiceAttachmentsOKBtn;
	
	@FindBy(xpath = "//*[@id='allowanceViewInvoiceForm:hotelAttachTable_data']/tr[@data-ri='0']")
	private WebElement invoiceAttachmentsTableData;
	
	@FindBy(xpath = "//span[text()='Save Invoice']")
	private WebElement saveInvoiceBtn;
	
	@FindBy(xpath = "//span[text()='Submit Invoice']")
	private List<WebElement> submitInvoiceBtn;
	
	@FindBy(xpath = "//*[@id='uploadAttachmentForm']//div")
	private WebElement popUp_UploadFile;

	@FindBy(xpath = "//div[@id='createAllowanceInvoiceSearchForm:createInvoiceResultsSubPanel']//table[2]//tr/td[2]")
	private List<WebElement> tablelist;
	
	@FindBy(xpath = "//div[@id='createAllowanceSignInSearchForm:allowancePanel']//table/parent::div/parent::div/parent::div/button[1]")
	private WebElement btn_AttachAllowancesSheet;
	
	@FindBy(id = "uploadAttachmentForm:uploadAttachmentVersionInput_input")
	private WebElement input_Version;

	@FindBy(xpath = "//form[@id='uploadAttachmentForm']//span[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left ui-fileupload-choose']")
	private WebElement btn_ChooseFile;

	@FindBy(xpath = "//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left ui-fileupload-upload']")
	private WebElement btn_UploadFile;

	@FindBy(xpath = "//div[@id='createAllowanceSignInSearchForm:unplannedAllowancePanel']//span[normalize-space()='Add Unplanned Allowance']")
	private WebElement link_AddUnplannedAllowance;

	@FindBy(xpath = "//input[@id='createAllowanceSignInSearchForm:unplannedCrewName']")
	private WebElement textbox_NameUnplannedAllowance;

	@FindBy(xpath = "//input[@id='createAllowanceSignInSearchForm:unplannedCrewRank']")
	private WebElement textbox_RankUnplannedAllowance;

	@FindBy(xpath = "//input[@id='createAllowanceSignInSearchForm:unplannedBfastCount_input']")
	private WebElement textbox_BreakfastUnplannedAllowance;

	@FindBy(xpath = "//input[@id='createAllowanceSignInSearchForm:unplannedBfastRate_input']")
	private WebElement textbox_BreakfastRateUnplannedAllowance;

	@FindBy(xpath = "//input[@id='createAllowanceSignInSearchForm:unplannedCurrencyCode']")
	private WebElement textbox_CurrencyCodeUnplannedAllowance;

	@FindBy(xpath = "//input[@id='createAllowanceSignInSearchForm:unplannedReason']")
	private WebElement textbox_ReasonUnplannedAllowance;

	@FindBy(xpath = "//table[@id='createAllowanceSignInSearchForm:dialogValues']//button/span[text()='Add Unplanned Allowance']")
	private WebElement btn_OnPopUp_AddUnplannedAllowance;

	@FindBy(xpath = "//span[text()='Submit']")
	private WebElement btn_SubmitAllowances;
	
	public EventFiringWebDriver driver=null;
	
	public Page_CreateAllowanceInvoice_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createAllowanceInvoice(String supplierInvoiceNumber){
		waitForElementVisibility(selectAirlineDropDown);
		waitForElementVisibility(billingPeriodDropDown);
		typeInText(this.supplierInvoiceNumber, supplierInvoiceNumber, "CreateAllowanceInvoicePage -> SupplierInvoiceNumber");
		waitForElementVisibility(createBtn);
	}
	
	public void createAllowanceInvoice(String tenant, String airportCityCode, String supplier, String billingPeriodValue, String textbox_InvoiceNumberValue ){
		clickOn(selectAirlineDropDown, "CreateAllowanceInvoicePage -> Airline dropdown");
		waitForElementVisibility(selectAirlineDropDown);
		clickOn(findElementByXpath(tenantXpath1+tenant+tenantXpath2), "Airline: "+tenant+" option");
		waitForElementToDisappear(spinner);
		clickOn(dropdwon_BillingPeriod, "CreateAllowanceInvoicePage -> Billing Period dropdown");
		waitForElementToDisappear(spinner);
		//waitForElementVisibility(billingPeriod);
		clickOn(findElementByXpath(selectBillingPeriodXpath1+billingPeriodValue+selectBillingPeriodXpath2), "Billing period: "+billingPeriodValue+" option");
		waitForElementToDisappear(spinner);
		typeInText(this.textbox_InvoiceNumber, textbox_InvoiceNumberValue, "CreateAllowanceInvoicePage -> Invoice Number");
		this.textbox_InvoiceNumber.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);                                  
		clickOn(createBtn, "CreateAllowanceInvoicePage -> Create Invoice button");
		waitForElementToDisappear(spinner);
		waitForPageLoad(getDriver());
	}
	
	public int getUnsubmittedSIS() throws Exception{
		return SignInSheetList.size();
	}
	
	public void uploadAllowanceSheet() throws Exception{
		for(int i = 0; i < attachAllowanceSheetButtons.size(); i++){
			Thread.sleep(3000);
			clickOn(attachAllowanceSheetButtons.get(i), "CreateAllowanceInvoicePage -> Attach Allowance Sheet btn");
			waitForElementVisibility(popUp_UploadFile);
			typeInText(input_Version, "1", "CreateAllowanceInvoicePage -> Input Version");
			clickOn(btn_ChooseFile, "CreateAllowanceInvoicePage -> Choose File btn");
			Thread.sleep(3000);
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile.exe"+" "+System.getProperty("user.dir")+"\\lib\\test.docx");
			Thread.sleep(3000);
			clickOn(btn_UploadFile, "CreateAllowanceInvoicePage -> Choosefile pop-up -> Upload File btn");
			Thread.sleep(8000);
		}
	}
	
	public String clickOnViewUnsubmittedSIS(int i) throws Exception{
		String date = sisDateList.get(i).getText();
		clickOn(SignInSheetList.get(i), "CreateAllowanceInvoicePage -> View "+i+"th Sign-in Sheet link");
		waitForPageLoad(getDriver());
		return date;
	}
	
	public void clickOnSubmitSIS(){
		clickOn(submitSISBtn, "CreateAllowanceInvoicePage -> Submit btn");
		waitForElementToDisappear(spinner);
	}
	
	public boolean isMissingAllowanceSheet(){
		if(allowanceSheetErrorList.size()>0)
			return true;
		else
			return false;
	}
	
	
	public void verifyDailyPayments(String allowanceAmounts, String adjustmentAmounts, String totalDailyAmounts){
		int i = 0;
		for(String allowanceAmount : allowanceAmounts.split(";")){
			if(!allowanceAmount.isEmpty())
				assertEquals(allowanceAmount, allowanceAmountList.get(i).getText(), "Allowance Amount mismatch at day# "+(i+1));
			i++;
		}
		i=0;
		for(String adjustmentAmount : adjustmentAmounts.split(";")){
			if(!adjustmentAmount.isEmpty())
				assertEquals(adjustmentAmount, adjustmentAmountList.get(i).getText(), "Allowance Adjustment Amount mismatch at day# "+(i+1));
			i++;
		}
		i=0;
		for(String totalDailyAmount : totalDailyAmounts.split(";")){
			if(!totalDailyAmount.isEmpty())
				assertEquals(totalDailyAmount, totalDailyAmountList.get(i).getText(), "Total Daily Amount mismatch at day# "+(i+1));
			i++;
		}
	}
	
	public void addHotelAdjustments(String reason, String comment, String amount){
		clickOn(addHotelAdjustmentBtn, "CreateAllowanceInvoicePage -> Hotel Adjustment Add btn");
		waitForElementToDisappear(spinner);
		clickOn(reasonDropDown, "CreateAllowanceInvoicePage -> Hotel Adjustment pop-up -> Reason drop down");
		clickOn(findElementByXpath(reasonXpath1+reason+reasonXpath2), "CreateAllowanceInvoicePage -> Hotel Adjustment pop-up -> "+reason +" option");
		typeInText(commentTextArea, comment, "CreateAllowanceInvoicePage -> Hotel Adjustment pop-up -> Comments");
		typeInText(this.amount, amount, "CreateAllowanceInvoicePage -> Hotel Adjustment pop-up -> Amount");
		clickOn(hotelAdjustments_OKBtn, "CreateAllowanceInvoicePage -> Hotel Adjustment pop-up -> OK btn");
		waitForElementVisibility(hotelAdjustments_tableData);
	}
	
	public void addComments(String comment){
		clickOn(addCommentBtn, "CreateAllowanceInvoicePage -> Comments Add btn");
		waitForElementToDisappear(spinner);
		typeInText(hotelCommentsTextArea, comment, "CreateAllowanceInvoicePage -> Comments pop-up -> Comments");
		clickOn(hotelCommentsPopUpOKBtn, "CreateAllowanceInvoicePage -> Comments pop-up -> OK btn");
		waitForElementVisibility(hotelCommentsTableData);
	}
	
	public void uploadInvoiceAttachments(String comment) throws Exception{
		clickOn(invoiceAttachmentsUploadBtn, "CreateAllowanceInvoicePage -> Invoice Attachments -> upload btn");
		waitForElementToDisappear(spinner);
		typeInText(allowanceInvoiceAttachmentsComments, comment, "CreateAllowanceInvoicePage -> Invoice Attachments pop-up -> Comment");
		clickOn(chooseFileBtn, "CreateAllowanceInvoicePage -> Invoice Attachments ->Choose File btn");
		Thread.sleep(3000);
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile.exe"+" "+System.getProperty("user.dir")+"\\lib\\test.docx");
		Thread.sleep(3000);
		clickOn(invoiceAttachmentsOKBtn, "CreateAllowanceInvoicePage -> Invoice Attachments ->OK btn");
		waitForElementVisibility(invoiceAttachmentsTableData);
	}
	
	public String getAPIInvoiceNumber(){
		return apiInvoiceNumber.getText();
	}
	
	public void saveInvoice(){
		clickOn(saveInvoiceBtn, "CreateAllowanceInvoicePage -> Save Invoice btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifyEditable(boolean editable){
		if(editable)
			assertTrue(submitInvoiceBtn.size()>0, "Invoice is Not Editable!");
		else
			assertTrue(submitInvoiceBtn.size()==0, "Invoice is Editable!");
	}
	
	public void submitInvoice(){
		clickOn(submitInvoiceBtn.get(0), "CreateAllowanceInvoicePage -> Submit Invoice btn");
		waitForElementToDisappear(spinner);
	}

	public void clickOnCreateInvoices() {
		clickOn(creteAllowancesInvoiceLink);

	}

	public void clickOnAllowancesLinkNewSupplier() {
		clickOn(AllowancesLinkNewSupplier);

	}

	
	public void submitSISheets() throws IOException, InterruptedException {
		for (int i = 0; i < tablelist.size(); i++) {
			System.out.println(tablelist.get(i).getText());
			clickOn(tablelist.get(i));
			waitForPageLoad(getDriver());
			uploadSISheets();
		}
	}

	public void uploadSISheets() throws InterruptedException, IOException {
		clickOn(btn_AttachAllowancesSheet);
		waitForElementVisibility(popUp_UploadFile);
		typeInText(input_Version, "1");
		clickOn(btn_ChooseFile);
		Thread.sleep(3000);
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\lib\\uploadFile.exe" + " "
				+ System.getProperty("user.dir") + "\\lib\\test.docx");
		Thread.sleep(3000);
		clickOn(btn_UploadFile);
	}
	
	public void addUnplannedAllowance(String allowanceNameValue, String allowanceRankValue, String allowanceBreakFastNmbr, String allowanceBreakfastRateValue, String allowanceCurrencyCodeValue, String allowanceReason) {
		clickOn(link_AddUnplannedAllowance);
		typeInText(textbox_NameUnplannedAllowance, allowanceNameValue);
		typeInText(textbox_RankUnplannedAllowance, allowanceRankValue);
		typeInText(textbox_BreakfastUnplannedAllowance, allowanceBreakFastNmbr);
		typeInText(textbox_BreakfastRateUnplannedAllowance, allowanceBreakfastRateValue);
		typeInText(textbox_CurrencyCodeUnplannedAllowance, allowanceCurrencyCodeValue);
		typeInText(textbox_ReasonUnplannedAllowance, allowanceReason);
		clickOn(btn_OnPopUp_AddUnplannedAllowance);
	}
	
	public void addUnplannedAllowances( ) throws IOException, InterruptedException {
		for (int i = 0; i < tablelist.size(); i++) {
			System.out.println(tablelist.get(i).getText());
			clickOn(tablelist.get(i));
			waitForPageLoad(getDriver());

		}
	}
}
