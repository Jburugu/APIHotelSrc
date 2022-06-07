package com.APIHotels.pages.ACES3;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;


public class Page_UploadInvoice_Aces3Supplier extends BasePage{

	//Upload Invoice page Elements for Hotel
	@FindBy(id="uploadEDriveInv:clientSelector")
	private WebElement clientSelector;
	
	String selectClientXpath1="//*[contains(@id,'clientSelector_items')]/li[contains(text(),'";
	String selectClientXpath2="')]";
	
	@FindBy(id="uploadEDriveInv:billingPeriodSelector")
	private WebElement billingPeriodSelector;

	String billingPeriodXpath1="//*[contains(@id,'billingPeriodSelector_items')]/li[contains(text(),'";
	String billingPeriodXpath2="')]";
	
	@FindBy(xpath="//div[@id='uploadEDriveInv:fileUpload']/div/span")
	private WebElement browseButton;
	
	@FindBy(xpath="//button[contains(@class,'fileupload-upload')]")
	private WebElement uploadButton;
	
	@FindBy(id="uploadEDriveInv:roomsBilled")
	private WebElement roomsBilled;

	@FindBy(id="uploadEDriveInv:roomsAmount")
	private WebElement roomsAmount;
	
	@FindBy(id="uploadEDriveInv:taxAmount")
	private WebElement taxAmount;

	@FindBy(id="uploadEDriveInv:exceptionAmount")
	private WebElement exceptionAmount;
	
	@FindBy(id="uploadEDriveInv:invoiceAmount")
	private WebElement invoiceAmount ;
	
	@FindBy(id="uploadEDriveInv:contactEmail")
	private WebElement contactEmail;
	
	@FindBy(id="uploadEDriveInv:comment")
	private WebElement comment;
	
	@FindBy(id="uploadEDriveInv:submitButton")
	private WebElement submitButton;
	
	@FindBy(xpath="//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath="//*[@id='uploadEDriveInv:attachmentsTable_data']/tr/td[1]")
	private WebElement tableData;
	
	@FindBy(xpath="//*[contains(text(),'No records found.')]")
	private WebElement noRecordsText;
	
	@FindBy(xpath="//*[contains(@id,'uploadEDriveInv:')]//li/span")
	private List<WebElement> savedMessage;
	
	@FindBy(xpath="//*[contains(@id,'uploadEDriveInv:attachmentsTable')]/img")
	private WebElement deleteImage;
	
	//Upload Invoice page Elements for GT
	@FindBy(id="uploadGTriveInv:clientSelector")
	private WebElement gtClientSelector;
	
	@FindBy(id="uploadGTriveInv:billingPeriodSelector")
	private WebElement gtbillingPeriodSelector;
	
	@FindBy(xpath="//div[@id='uploadGTriveInv:fileUpload']/div/span")
	private WebElement gtBrowseButton;
	
	@FindBy(xpath="//div[@id='uploadGTriveInv:fileUpload']/div/button[1]")
	private WebElement gtUploadButton;
	
	@FindBy(xpath="//div[@id='uploadGTriveInv:fileUpload']/div/button[2]")
	private WebElement gtFileUploadCancelButton;
	
	@FindBy(xpath="//*[contains(@id, 'uploadGTriveInv:attachmentsTable')]/img")
	private WebElement gtDeleteImage;
	
	@FindBy(id = "uploadGTriveInv:tripsBilled")
	private WebElement tripsBilled;

	@FindBy(id = "uploadGTriveInv:tripAmount")
	private WebElement tripAmount;
	
	@FindBy(id = "uploadGTriveInv:taxAmount")
	private WebElement gtTaxAmount;
	
	@FindBy(id = "uploadGTriveInv:invoiceAmount")
	private WebElement gtInvoiceAmount;
	
	@FindBy(id = "uploadGTriveInv:contactEmail")
	private WebElement gtContactEmail;
	
	@FindBy(id = "uploadGTriveInv:comment")
	private WebElement gtComment;
		
	@FindBy(id = "uploadGTriveInv:submitButton")
	private WebElement gtSubmitButton;
	
	@FindBy(id = "uploadGTriveInv:cancelButton")
	private WebElement gtCancelButton;
	
	@FindBy(xpath="//*[contains(@id,'uploadGTriveInv:')]//li/span")
	private WebElement gtSavedMessage;
	
	public EventFiringWebDriver driver=null;
	
	public Page_UploadInvoice_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void uploadInvoiceForHotel(String tenantName, String billingPeriod, String roomBillValue, String roomAmountValue,
			String taxAmountValue, String exceptionAmountValue, String invoiceAmountValue, String contactEmailValue, String commentText) throws Exception {
		clickOn(clientSelector, "UploadInvoicePage -> Client drop down");
		clickOn(findElementByXpath(selectClientXpath1+tenantName+selectClientXpath2), "UploadInvoicePage -> Client drop down "+tenantName+" option");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(billingPeriodSelector);
		clickOn(billingPeriodSelector, "UploadInvoicePage -> Billing Period drop down");
		clickOn(findElementByXpath(billingPeriodXpath1+billingPeriod+billingPeriodXpath2), "UploadInvoicePage -> Billing Period drop down "+billingPeriod+" option");
		waitForElementToDisappear(spinner);
		clickOn(browseButton, "UploadInvoicePage -> Browse btn");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile.exe"+" "+System.getProperty("user.dir")+"\\lib\\test.docx");
		Thread.sleep(3000);
		clickOn(uploadButton, "UploadInvoicePage -> upload btn");
		waitForElementVisibility(deleteImage);
		//assertTrue(findElementByXpath("//*[contains(text(), 'test.docx')]").isDisplayed(), "Invoice is not uploaded!");
		typeInText(roomsBilled, roomBillValue, "UploadInvoicePage -> Rooms Billed");
		typeInText(roomsAmount, roomAmountValue, "UploadInvoicePage -> Rooms Amount");
		typeInText(taxAmount, taxAmountValue, "UploadInvoicePage -> Tax Amount");
		typeInText(exceptionAmount, exceptionAmountValue, "UploadInvoicePage -> Exemption Amount");
		typeInText(invoiceAmount, invoiceAmountValue, "UploadInvoicePage -> Invoice Amount");
		typeInText(contactEmail, contactEmailValue, "UploadInvoicePage -> Contact Email");
		typeInText(comment, commentText, "UploadInvoicePage -> Comments");
		waitForElementToDisappear(spinner);
		clickOn(submitButton, "UploadInvoicePage -> Submit btn");
		if(savedMessage.size()>0){
		String successMessage= savedMessage.get(0).getText();
		System.out.println(successMessage);
		}
		else Assert.fail("Unable to upload invoice for " + billingPeriod + "due to invalid room amount");
	}
	
	public void uploadInvoiceForGT(String tenantName, String billingPeriod, String tripsBilledAmount,
			String tripAmountValue, String gtTaxAmountValue, String gtInvoiceAmountValue, String gtEmail,
			String gtCommentText) throws Exception {
		
		clickOn(gtClientSelector, "UploadInvoicePage -> Client drop down");
		selectByVisibleText(gtClientSelector, tenantName, "UploadInvoicePage -> Client drop down "+tenantName+" option");
		waitForElementToDisappear(spinner);
		clickOn(gtbillingPeriodSelector, "UploadInvoicePage -> Billing Period drop down");
		selectByVisibleText(gtbillingPeriodSelector, billingPeriod, "UploadInvoicePage -> Billing Period drop down "+billingPeriod+" option");
		waitForElementToDisappear(spinner);
		clickOn(gtBrowseButton, "UploadInvoicePage -> Browse btn");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile.exe"+" "+System.getProperty("user.dir")+"\\lib\\test.docx");
		Thread.sleep(2000);
		clickOn(gtUploadButton, "UploadInvoicePage -> Upload btn");
		//assertTrue(findElementByXpath("//*[contains(text(), 'test.docx')]").isDisplayed(), "Invoice is not uploaded!");
		waitForElementVisibility(gtDeleteImage);
		typeInText(tripsBilled, tripsBilledAmount, "UploadInvoicePage -> Trips Billed");
		typeInText(tripAmount, tripAmountValue, "UploadInvoicePage -> Trip Amount");
		typeInText(gtTaxAmount, gtTaxAmountValue, "UploadInvoicePage -> Tax Amount");
		typeInText(gtInvoiceAmount, gtInvoiceAmountValue, "UploadInvoicePage -> Invoice Amount");
		typeInText(gtContactEmail, gtEmail, "UploadInvoicePage -> Contact Email");
		typeInText(gtComment, gtCommentText, "UploadInvoicePage -> Comments");
		clickOn(gtSubmitButton, "UploadInvoicePage -> Submit btn");
		waitForElementToDisappear(spinner);
		if(gtSavedMessage.getText().equalsIgnoreCase("Invoice Saved Successfully"))
			System.out.println("GT invoice uploaded successfully");
		else Assert.fail("Unable to upload GT invoice for " + billingPeriod + "due to error");
	
	}
}
