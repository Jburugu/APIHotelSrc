package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_FindInvoice extends BasePage{
	
	@FindBy(css = "#findInvoice>a")
	public WebElement findInvoice;
	
	@FindBy(xpath = "//input[@name='serviceTypeId' and @id='hotel']")
	public WebElement hotelRadioBtn;
	
	@FindBy(xpath = "//input[@name='serviceTypeId' and @id='gt']")
	public WebElement gtRadioBtn;
	
	@FindBy(id = "locCdID")
	public WebElement findInvoiceCity;
	
	@FindBy(css = "input[value='Get Supplier']")
	public WebElement getSupplierBtn;
	
	@FindBy(id = "selId")
	public WebElement supplierDropdown;
	
	@FindBy(id = "nonContractsupplierId")
	public WebElement includenonContractsupplierIdCheckbox;
	
	@FindBy(id = "inactiveDeletedSupplierId")
	public WebElement includeinactiveDeletedSupplierIdCheckbox;
	
	@FindBy(id = "numberOfMonthId")
	public WebElement includenumberOfMonthIdCheckbox;
	
	@FindBy(id = "voidInvoicesForSupplierId")
	public WebElement includevoidInvoicesForSupplierIdCheckbox;
	
	@FindBy(xpath = ".//*[@id='voidInvoicesForSupplierId']/parent::td/parent::tr/following-sibling::tr//input[@value='Search']")
	public WebElement findBySupplierNameSearchBtn;
	
	@FindBy(id = "invno")
	public WebElement aPIinvoiceNo;
	
	@FindBy(xpath = ".//*[@id='invno']/parent::td/parent::tr/following-sibling::tr//input[@value='Search']")
	public WebElement findByInvoiceNoSearchBtn;
	
	@FindBy(id = "monthid")
	public WebElement billingPeriod;
	
	@FindBy(xpath = "//input[@name='serviceType' and @id='gt']")
	public WebElement billingPeriodGT;
	
	@FindBy(xpath = "//input[@name='serviceType' and @id='hotel']")
	public WebElement billingPeriodHotel;
	
	@FindBy(xpath = "//input[@name='serviceType' and @id='both']")
	public WebElement billingPeriodGTandHotelBoth;
	
	@FindBy(id = "voidInvoicesForBillingId")
	public WebElement includeVoidInvoiceCheckbox;
	
	@FindBy(xpath = ".//*[@id='voidInvoicesForBillingId']/parent::td/parent::tr/following-sibling::tr//input[@value='Search']")
	public WebElement billingPeriodSearchBtn;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType;
	
	@FindBy(xpath ="//*[@id='findInvoice']//td[2]")
	private WebElement supplierCellInFindInvoiceTable;
	
	@FindBy(xpath="//*[@id='findInvoice']//td[10]")
	private WebElement invoiceValueIncolumn;
	
	@FindBy(xpath = "//*[@id='findInvoice']//td[10]/a")
	private WebElement invoiceNumberLink;

	@FindBy(xpath="//*[@id='findInvoice']//td[7]")
	private WebElement invoiceApproveIncolumn;
	
	@FindBy(id = "buttonResolved")
	private WebElement resolvedBtn;
	
	@FindBy(id = "supplierCommentNotesId")
	private WebElement commentsForSupplier;
	
	@FindBy(xpath = "//*[@value = 'OK']")
	private WebElement okBtn;
	
	@FindBy(id = "buttonReview")
	private WebElement reviewBtn;
	
	@FindBy (xpath ="//dd[5]//div[2]//a[1]")
	private WebElement findInvoiceLink;
	
	
	public EventFiringWebDriver driver=null;
	
	public Page_FindInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void FindInvoicesBySuppliersName(String cityJFK, String indexValue1) throws Exception {
		clickOn(findInvoice);
		clickOn(hotelRadioBtn);
		clickOn(gtRadioBtn);
		typeInText(findInvoiceCity, cityJFK);
		waitForElementVisibility(getSupplierBtn);
		clickOn(getSupplierBtn);
		waitForElementVisibility(supplierDropdown);
		Integer supplierValueFindInvoiceParseValue = Integer.parseInt(indexValue1);
		selectByIndex(supplierDropdown, supplierValueFindInvoiceParseValue);
		waitForElementVisibility(includenonContractsupplierIdCheckbox);
		waitForElementVisibility(includeinactiveDeletedSupplierIdCheckbox);
		waitForElementVisibility(includenumberOfMonthIdCheckbox);
		waitForElementVisibility(includevoidInvoicesForSupplierIdCheckbox);
		waitForElementVisibility(findBySupplierNameSearchBtn);

	}

	public void findInoviceIdByInvoiceNumber(String invoiceNoFindInvoice) {
		waitForElementVisibility(aPIinvoiceNo);
		typeInText(aPIinvoiceNo, invoiceNoFindInvoice);
		waitForElementVisibility(findByInvoiceNoSearchBtn);
	}
	
	public void clickSearchBtn_InvoiceNumber(){
		clickOn(findByInvoiceNoSearchBtn);
	}
	
	public void findInvoiceIdByBillingPeriod(String currentMMMYYYY) {
		waitForElementVisibility(billingPeriod);
		selectByVisibleText(billingPeriod, currentMMMYYYY);
		waitForElementVisibility(billingPeriodGT);
		waitForElementVisibility(billingPeriodHotel);
		waitForElementVisibility(billingPeriodGTandHotelBoth);
		waitForElementVisibility(includeVoidInvoiceCheckbox);
		waitForElementVisibility(billingPeriodSearchBtn);
	}
	
	public void FindInvoicesBySuppliers(String serviceType, String cityJFK, String supplier) throws Exception {
		clickOn(findInvoice);
		services(serviceType);
		typeInText(findInvoiceCity, cityJFK);
		waitForElementVisibility(getSupplierBtn);
		clickOn(getSupplierBtn);
		waitForElementVisibility(supplierDropdown);
		selectByVisibleText(supplierDropdown, supplier); 
		clickOn(findBySupplierNameSearchBtn);
		waitForElementVisibility(supplierCellInFindInvoiceTable);
		Assert.assertEquals(supplierCellInFindInvoiceTable.getText(), supplier, "Supplier Name search is working as expected");
		
	}

	public void services(String serviceType){
		for(WebElement thisServiceType : this.serviceType)
			if(thisServiceType.getAttribute("value").equals(serviceType)){
				clickOn(thisServiceType);
				break;
			}
	}
	
	
	public void findByInvoiceNumber() throws IOException {
		String invoiceNumber = readTextFile();
		String invNumber = invoiceNumber.substring(2);
		typeInText(aPIinvoiceNo, invNumber);
		waitForElementVisibility(findByInvoiceNoSearchBtn);
		clickOn(findByInvoiceNoSearchBtn);
		Assert.assertEquals(invoiceValueIncolumn.getText(), (invoiceNumber), "Created Invoice is not populated");

	}
	
	public void findByInvoiceNumber(String invoiceNumber) throws Exception{
		invoiceNumber = invoiceNumber.replace("P-", "");
		typeInText(aPIinvoiceNo, invoiceNumber);
		waitForElementVisibility(findByInvoiceNoSearchBtn);
		clickOn(findByInvoiceNoSearchBtn);
		Assert.assertEquals(invoiceValueIncolumn.getText().replace("P-", ""), (invoiceNumber), "Created Invoice is not populated");
	}
	
	public void clickOnBillingPeriodSearchBtn(){
		clickOn(billingPeriodSearchBtn);
	}
	
	
	public void verifyApprovedStatusOfInvoice() throws IOException{
		String invoiceNumber = readTextFile();
		Assert.assertEquals(invoiceApproveIncolumn.getText(), (invoiceNumber), "Created Invoice is not approved");
	}
	
	public void verifyStatusForInvoice(String expectedStatus){
		assertEquals(invoiceApproveIncolumn.getText(), expectedStatus, "status mismatch!");
	}
	
	public void resolveChallengedInvoice(String commentsForSupplier){
		String findInvoiceWindowHandle = getDriver().getWindowHandle();
		clickOn(invoiceNumberLink);
		switchToNewWindow(findInvoiceWindowHandle);
		String reviewInvoiceWindowHandle = getDriver().getWindowHandle();
		clickOn(resolvedBtn);
		switchToNewWindow(findInvoiceWindowHandle, reviewInvoiceWindowHandle);
		typeInText(this.commentsForSupplier, commentsForSupplier);
		clickOn(okBtn);
		switchToWindow(findInvoiceWindowHandle);
	}
	
	public void reviewInvoice(){
		List<String> availableWindowHandles = new ArrayList<String>(driver.getWindowHandles());
		clickOn(invoiceNumberLink);
		switchToNewWindow(availableWindowHandles);
		clickOn(reviewBtn);
		getDriver().close();
		getDriver().switchTo().window(availableWindowHandles.get(0));
	}
	

	public void clickOnFindInvoice() {
		clickOn(findInvoiceLink, "Accounting Menu -> Find Invoice Link");
		System.out.println("2nd try");
		clickOn(findInvoice);
	}
	
	public void verifyApprovedStatusOfInvoice(String status) throws IOException{
		Assert.assertEquals(invoiceApproveIncolumn.getText(), (status), "Created Invoice is not approved");
	}
	
	
}
