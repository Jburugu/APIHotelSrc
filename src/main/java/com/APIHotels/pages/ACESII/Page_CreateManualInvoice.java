package com.APIHotels.pages.ACESII;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_CreateManualInvoice extends BasePage{
	
	String apiInvNbr=null;
	
	@FindBy(id = "manualInvoice")
	public WebElement createManualInvoice;
	
	@FindBy(id = "hotel")
	public WebElement hotelServicesCreateMnlInv;
	
	@FindBy(id = "gt")
	public WebElement gtServicesCreateMnlInv;
	
	@FindBy(id = "locCdId")
	public WebElement cityCreateManualInvoice;
	
	@FindBy(css = "input[value='Get Supplier']")
	public WebElement getSupplierBtn;
	
	@FindBy(css = "#supplierid")
	public WebElement supplierIDCteManualInv;
	
	@FindBy(id = "invoiceNo")
	public WebElement aPIIvoiceNumber;
	
	@FindBy(id = "billingPeriod")
	public WebElement billingPeriodDropdown;
	
	@FindBy(id = "currency")
	public WebElement currencyDropdown;
	
	@FindBy(id = "dltId1")
	public WebElement paidNoShowsCheckboxFirstRow;
	
	@FindBy(id = "paidNoShow1")
	public WebElement reasonDropdownFirstRow;
	
	@FindBy(id = "paidNoShowComments1")
	public WebElement paidNoShowCommentsFirstRow;
	
	@FindBy(id = "pnsRides1")
	public WebElement noOfRoomsPickupsfirstRow;
	
	@FindBy(id = "pnsAmount1")
	public WebElement amountFirstRow;
	
	@FindBy(id = "pnsTaxTotal")
	public WebElement pnsTaxTotalFirstRow;
	
	@FindBy(xpath = "//legend[text()='Paid No Shows']/parent::fieldset//input[@value='Add']")
	public WebElement paidNoShowsAddBtn;
	
	@FindBy(xpath = "//legend[text()='Paid No Shows']/parent::fieldset//input[@value='Delete']")
	public WebElement paidNoShowsDeleteBtn;
	
	@FindBy(id = "delId1")
	public WebElement adjustmentsCheckbox1;

	@FindBy(id = "delId2")
	public WebElement adjustmentsCheckbox2;
	
	
	@FindBy(id = "adjustmentReason1")
	public WebElement adjustmentReason1;

	@FindBy(id = "adjustmentReason2")
	public WebElement adjustmentReason2;

	@FindBy(id = "adjustmentComments1")
	public WebElement adjustmentComments1;

	@FindBy(id = "adjustmentComments2")
	public WebElement adjustmentComments2;

	@FindBy(id = "adjustmentRides1")
	public WebElement adjustmentRides1;

	@FindBy(id = "adjustmentRides2")
	public WebElement adjustmentRides2;

	@FindBy(id = "adjustmentAmount1")
	public WebElement adjustmentAmount1;
	
	@FindBy(id = "adjustmentAmount2")
	public WebElement adjustmentAmount2;
	
	@FindBy(xpath = "//legend[text()='Adjustments']/parent::fieldset//input[@value='Add']")
	public WebElement adjustmentsAddBtn;

	@FindBy(xpath = "//legend[text()='Adjustments']/parent::fieldset//input[@value='Delete']")
	public WebElement adjustmentsDeleteBtn;
	
	@FindBy(id = "delTaxId1")
	public WebElement paymentInfoTaxesCheckbox1;
	
	@FindBy(xpath = "//legend[normalize-space()='Payment Information']/parent::fieldset//input[@value='Add']")
	public WebElement paymentInfoAddBtn;
	
	@FindBy(id = "taxReason1")
	public WebElement taxReason1;

	@FindBy(id = "taxType1")
	public WebElement taxType1;

	@FindBy(id = "taxName1")
	public WebElement taxName1;

	@FindBy(id = "taxComments1")
	public WebElement taxComments1;

	@FindBy(id = "taxAmount1")
	public WebElement taxAmount1;

	@FindBy(id = "taxTotal")
	public WebElement taxTotal;
	
	@FindBy(xpath = "//legend[normalize-space()='Payment Information']/parent::fieldset//input[@value='Delete']")
	public WebElement paymentInfoDeleteBtn;
	
	@FindBy(id = "flight")
	public WebElement flightAllocation;

	@FindBy(id = "grandTotal")
	public WebElement grandTotal;

	@FindBy(id = "comment1")
	public WebElement comment1;

	@FindBy(id = "CommentsId")
	public WebElement CommentsId;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Save']")
	public WebElement saveBtn;

	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Send to Manager']")
	public WebElement sendToManagerBtn;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType; //HOTEL, GT, BOTH
	
	public EventFiringWebDriver driver=null;
	
	public Page_CreateManualInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnCreateManualInvoiceTab() throws Exception {
		waitForElementVisibility(createManualInvoice);
		clickOn(createManualInvoice);

	}

	public void createManualInvoice(String serviceType,String cityJFK, String indexValue1, String currentMMMYYYY,
			String currencyUSDCreateManlInv, String commentsPaidNoShows, String noOfRoomsPaidNoshows,
			String amountPaidNoShows, String commentsAdj, String amountAdj,  String noOfRoomsAdj, String taxComments,
			String taxAmt, String commentCreateManInv) throws Exception {
		clickOnCreateManualInvoiceTab();
		waitForElementVisibility(hotelServicesCreateMnlInv);
		waitForElementVisibility(gtServicesCreateMnlInv);
		services(serviceType);
		waitForElementVisibility(cityCreateManualInvoice);
		typeInText(cityCreateManualInvoice, cityJFK);
		clickOn(getSupplierBtn);
		waitForPageLoad(driver);
		Integer selectSuppliersCreateManlInvParse = Integer.parseInt(indexValue1);
		waitForElementVisibility(supplierIDCteManualInv);
		selectByIndex(supplierIDCteManualInv, selectSuppliersCreateManlInvParse);
		waitForPageLoad(driver);
		apiInvNbr = getInvoiceNumber();
		//writeDataToFile(apiInvNbr);
		writeProp("ManualInvoiceNumber", apiInvNbr);
		
		selectByValue(billingPeriodDropdown, currentMMMYYYY);
		selectByValue(currencyDropdown, currencyUSDCreateManlInv);

		// Paid No Shows
		waitForElementVisibility(paidNoShowsCheckboxFirstRow);
		clickOn(paidNoShowsCheckboxFirstRow);
		waitForElementVisibility(reasonDropdownFirstRow);
		Integer reasonValueParse = Integer.parseInt(indexValue1);
		selectByIndex(reasonDropdownFirstRow, reasonValueParse);
		typeInText(paidNoShowCommentsFirstRow, commentsPaidNoShows);
		noOfRoomsPickupsfirstRow.clear();
		typeInText(noOfRoomsPickupsfirstRow, noOfRoomsPaidNoshows);
		amountFirstRow.clear();
		typeInText(amountFirstRow, amountPaidNoShows);
		waitForElementVisibility(pnsTaxTotalFirstRow);
		waitForElementVisibility(paidNoShowsAddBtn);
		waitForElementVisibility(paidNoShowsDeleteBtn);

		// Adjustments
		waitForElementVisibility(adjustmentsCheckbox1);
		clickOn(adjustmentsCheckbox1);
		waitForElementVisibility(adjustmentReason1);
		Integer adjReasonValueParse = Integer.parseInt(indexValue1);
		selectByIndex(adjustmentReason1, adjReasonValueParse);
		typeInText(adjustmentComments1, commentsAdj);
		//adjustmentRides1.clear();
		waitForElementVisibility(adjustmentRides1);
		adjustmentAmount1.clear();
		typeInText(adjustmentAmount1, amountAdj);
		waitForElementVisibility(adjustmentsAddBtn);
		waitForElementVisibility(adjustmentsDeleteBtn);

		// Payment Information -Taxes
		waitForElementVisibility(paymentInfoAddBtn);
		clickOn(paymentInfoAddBtn);
		waitForElementVisibility(paymentInfoTaxesCheckbox1);
		clickOn(paymentInfoTaxesCheckbox1);
		waitForElementVisibility(taxReason1);
		// Integer taxReasonValueParse = Integer.parseInt(indexValue1);
		// selectByIndex(taxReason1, taxReasonValueParse);
		Integer taxTypeValueParse = Integer.parseInt(indexValue1);
		selectByIndex(taxType1, taxTypeValueParse);

		// Integer taxNameValueParse = Integer.parseInt(indexValue1);
		// selectByIndex(taxName1, taxNameValueParse);

		typeInText(taxComments1, taxComments);
		typeInText(taxAmount1, taxAmt);
		waitForElementVisibility(taxTotal);
		waitForElementVisibility(paymentInfoDeleteBtn);

		waitForElementVisibility(flightAllocation);
		waitForElementVisibility(grandTotal);
		waitForElementVisibility(comment1);
		waitForElementVisibility(CommentsId);
		waitForElementVisibility(saveBtn);
		waitForElementVisibility(sendToManagerBtn);

	}

	public void getManulInvoiceSuccessMessage() throws IOException{
		Alert alrt = driver.switchTo().alert();
		System.out.println(alrt.getText());
		alrt.getText().contains("Manual Invoice has been Completed");
		Assert.assertEquals(alrt.getText(), "Manual Invoice has been Created", "Failed to Create Manual Invoice");
		alrt.accept();
		writeDataToFile(apiInvNbr);
	}
	
	public void getManulInvoiceSuccessMessageOnSaveToManager() throws IOException{
		Alert alrt = driver.switchTo().alert();
		System.out.println(alrt.getText());
		alrt.getText().contains("Manual Invoice has been Completed");
		Assert.assertEquals(alrt.getText(), "Manual Invoice has been Completed", "Failed to Create Manual Invoice");
		alrt.accept();
		writeDataToFile(apiInvNbr);
	}
	
	public String  getInvoiceNumber(){
		
		WebElement inv = driver.findElement(By.xpath("//*[@id='invoiceNo']"));
		String invNum = inv.getAttribute("value").toString();
		System.out.println("Invoice number :"+invNum);
		return invNum;
	
	}
	
	public void matchInvoiceFirstletter(String invoiceNumber){
	String first = invoiceNumber.substring(0, 1);
System.out.println(first);
	Assert.assertEquals(first, "M", "Invoice first letter should start with M");
	
}
	
	
	public void services(String serviceType){
		for(WebElement thisServiceType : this.serviceType)
			if(thisServiceType.getAttribute("value").equals(serviceType)){
				clickOn(thisServiceType);
				break;
			}
	}
	

	public void clickCreateManualInvoice() throws Exception{
		clickOnCreateManualInvoiceTab();
	}
	
	public void createAdditionalAdjustments(String indexValue1, String commentsAdj, String amountAdj ){
		waitForElementVisibility(adjustmentsAddBtn);
		clickOn(adjustmentsAddBtn);
		waitForElementVisibility(adjustmentsCheckbox2);
		clickOn(adjustmentsCheckbox2);
		waitForElementVisibility(adjustmentReason2);
		Integer adjReasonValueParse = Integer.parseInt(indexValue1);
		selectByIndex(adjustmentReason2, adjReasonValueParse);
		typeInText(adjustmentComments2, commentsAdj);
		//adjustmentRides1.clear();
		waitForElementVisibility(adjustmentRides2);
		adjustmentAmount1.clear();
		typeInText(adjustmentAmount1, amountAdj);
		waitForElementVisibility(adjustmentsAddBtn);
	}
	
	
	public void createManualInvoiceReg(String serviceType,String cityJFK, String supplier, String currentMMMYYYY,
			String currencyUSDCreateManlInv, String indexValue1, String commentsPaidNoShows, String noOfRoomsPaidNoshows,
			String amountPaidNoShows, String commentsAdj, String amountAdj, String taxTypeValue,String taxName, String noOfRoomsAdj, String taxComments,
			String taxAmt, String commentCreateManInv) throws Exception {
		clickOnCreateManualInvoiceTab();
		waitForElementVisibility(hotelServicesCreateMnlInv);
		waitForElementVisibility(gtServicesCreateMnlInv);
		services(serviceType);
		waitForElementVisibility(cityCreateManualInvoice);
		typeInText(cityCreateManualInvoice, cityJFK);
		clickOn(getSupplierBtn);
		//waitForPageLoad(driver);
		waitForPageToLoad("30");
		waitForElementVisibility(supplierIDCteManualInv);
		selectByVisibleText(supplierIDCteManualInv, supplier);
		waitForPageLoad(driver);
		apiInvNbr = getInvoiceNumber();
		writeProp("ManualInvoiceNumber", apiInvNbr);
		waitForPageLoad(driver);
		waitForElementVisibility(billingPeriodDropdown);
		selectByValue(billingPeriodDropdown, currentMMMYYYY);
		waitForPageToLoad("30");
		waitForPageLoad(driver);
		waitForElementVisibility(currencyDropdown);
		if (!currencyUSDCreateManlInv.isEmpty()) {
			selectByValue(currencyDropdown, currencyUSDCreateManlInv);
			waitForPageLoad(driver);
		}
		// Paid No Shows
		waitForElementVisibility(paidNoShowsCheckboxFirstRow);
		clickOn(paidNoShowsCheckboxFirstRow);
		waitForElementVisibility(reasonDropdownFirstRow);
		Integer reasonValueParse = Integer.parseInt(indexValue1);
		selectByIndex(reasonDropdownFirstRow, reasonValueParse);
		typeInText(paidNoShowCommentsFirstRow, commentsPaidNoShows);
		noOfRoomsPickupsfirstRow.clear();
		typeInText(noOfRoomsPickupsfirstRow, noOfRoomsPaidNoshows);
		amountFirstRow.clear();
		typeInText(amountFirstRow, amountPaidNoShows);
		waitForElementVisibility(pnsTaxTotalFirstRow);
		waitForElementVisibility(paidNoShowsAddBtn);
		waitForElementVisibility(paidNoShowsDeleteBtn);

		// Adjustments
		waitForElementVisibility(adjustmentsCheckbox1);
		if (!commentsAdj.isEmpty()) {
			clickOn(adjustmentsCheckbox1);
			waitForElementVisibility(adjustmentReason1);
			Integer adjReasonValueParse = Integer.parseInt(indexValue1);
			selectByIndex(adjustmentReason1, adjReasonValueParse);
			typeInText(adjustmentComments1, commentsAdj);
			// adjustmentRides1.clear();
			waitForElementVisibility(adjustmentRides1);
			adjustmentAmount1.clear();
			typeInText(adjustmentAmount1, amountAdj);
			waitForElementVisibility(adjustmentsAddBtn);
			waitForElementVisibility(adjustmentsDeleteBtn);
		}

		// Payment Information -Taxes
		waitForElementVisibility(paymentInfoAddBtn);
		if (!taxTypeValue.isEmpty()) {
			clickOn(paymentInfoAddBtn);
			waitForElementVisibility(paymentInfoTaxesCheckbox1);
			clickOn(paymentInfoTaxesCheckbox1);
			waitForElementVisibility(taxReason1);
			waitForPageLoad(driver);
			waitForPageToLoad("30");
			selectByVisibleText(taxType1, taxTypeValue);
			waitForPageLoad(driver);
			waitForPageToLoad("30");
			waitForElementVisibility(taxName1);
			selectByVisibleText(taxName1, taxName);
			waitForPageLoad(driver);
			waitForPageToLoad("30");
			typeInText(taxComments1, taxComments);
			typeInText(taxAmount1, taxAmt);
		}
	}
	
	
	public void createManualInvoiceRegM(String serviceType, String cityJFK, String supplier, String currentMMMYYYY,
			String currencyUSDCreateManlInv, String indexValue1, String commentsPaidNoShows, String noOfRoomsPaidNoshows,
			String amountPaidNoShows, String commentsAdj, String amountAdj, String taxTypeValue,String taxName, String noOfRoomsAdj, String taxComments,
			String taxAmt, String commentCreateManInv) throws Exception{
		
		createManualInvoiceReg(serviceType, cityJFK, supplier, currentMMMYYYY, currencyUSDCreateManlInv, indexValue1,
				commentsPaidNoShows, noOfRoomsPaidNoshows, amountPaidNoShows, commentsAdj, amountAdj, taxTypeValue,
				taxName, noOfRoomsAdj, taxComments, taxAmt, commentCreateManInv);
		clickOn(sendToManagerBtn);
	}
	
//	public void writeDataToFile(String data) throws IOException{
//		FileWriter fw=new FileWriter("D:\\testout.txt");
//		fw.write(data);
//		fw.close();
//	}
	
	
	public void clickOnSave(){
		clickOn(saveBtn);
	}
	
	
	
}
