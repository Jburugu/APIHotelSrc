package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_SignInSheets_Aces3Supplier extends BasePage{
	
	@FindBy(linkText = "Sign-in Sheets")
	private WebElement btn_SignInSheet;
	
	@FindBy(linkText = "Sign-in Report")
	private WebElement btn_SignInReport;
	
	@FindBy(id = "signInReportForm:locCd_input")
	private WebElement txtBox_City;
	
	@FindBy(id = "signInReportForm:supplier_label")
	private WebElement dropdown_Supplier;
	
	@FindBy(id = "signInReportForm:billingPeriod_label")
	private WebElement dropdown_BillingPeriod;

	@FindBy(xpath = "//span[contains(text(),'Create')]")
	private WebElement btn_Create;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//ul[contains(@id,'signInReportForm:supplier')]/li")
	private WebElement suppliersList;

	@FindBy(xpath = "//ul[contains(@class,'ui-autocomplete-items')]/li")
	private WebElement tenantList;
	
	@FindBy (xpath ="//ul[contains(@id,'signInReportForm:billingPeriod_items')]/li")
	private WebElement billingPeriodList;
	
	@FindBy (xpath ="//*[@id='signInReportForm:billingPeriod_1']")
	private WebElement billingPeriodFirstPeriod;
	
	@FindBy(xpath ="//form[@name='signInReportForm']/div[2]/div[@class='ui-datatable-tablewrapper']/table/tbody")
	private WebElement signInSheetRecordsTable;
	
	@FindBy (xpath ="//form[@name='signInReportForm']/div[2]/div[@class='ui-datatable-tablewrapper']/table/tbody/tr[1]/td/a[text()='View Details']")
	private WebElement viewDetailsFirstRecordSignInSheet;
	
	@FindBy (xpath ="//div[@id='detailsPrintArea']")
	private WebElement SignInSheetViewDetailsTable;
	
	@FindBy (xpath ="//a[text()='View/Print Sign-in Sheets']")
	private WebElement viewOrPrintSignInSheetsLink;
	
	@FindBy (xpath ="//label[contains(@id,'client_label')]")
	private WebElement selectTenant_ViewOrPrintSISPage;
	
	
	@FindBy (xpath="//label[contains(@id,'client_label')]")
	private WebElement select_Airlines;
	
	@FindBy (xpath ="//ul[contains(@id,'client_items')]/li")
	private WebElement airlinesList;
	
		String airlineXPath1 ="//ul[contains(@id,'client_items')]/li[text()='";
		String airlineXPath2 ="']";
	
	private String cityXpath1 = "//ul[contains(@class,'ui-autocomplete-items')]/li/span[contains(text(),'";
	private String cityXpath2 = "')]";

	private String supplierXpath1 = "//ul[contains(@id,'signInReportForm:supplier_items')]/li[contains(text(),'";
	
	public EventFiringWebDriver driver=null;

	public Page_SignInSheets_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void generateReport(String city, String supplier, String billingPeriod){
		
		clickOn(txtBox_City);
		typeInText(this.txtBox_City, city);
		waitForElementVisibility(tenantList);
		clickOn(findElementByXpath(cityXpath1+city+cityXpath2));
		
		waitForElementToDisappear(spinner);
		clickOn(dropdown_Supplier);
		waitForElementVisibility(suppliersList);
		clickOn(findElementByXpath(supplierXpath1+supplier+cityXpath2));
		
		waitForElementToDisappear(spinner);
		clickOn(dropdown_BillingPeriod);
		clickOn(billingPeriodFirstPeriod);
		waitForElementToDisappear(spinner);
		clickOn(btn_Create);
		waitForElementToDisappear(spinner);
		 Assert.assertEquals(true, signInSheetRecordsTable.isDisplayed());
		System.out.println("Records are displayed");
		
		
	}
	
	public void clickOnSignInSheetTab(){
		clickOn(btn_SignInSheet);
	}
	
	public void clickOnSignInSheetReportTab(){
		clickOn(btn_SignInReport);
	}
	
	public void clickOnViewDetailsLink(){
		clickOn(viewDetailsFirstRecordSignInSheet);
		waitForElementToDisappear(spinner);
		 Assert.assertEquals(true, SignInSheetViewDetailsTable.isDisplayed());
		 System.out.println("View Records Pop up is displayed");
	}
	
	public void clickOnViewOrPrintSignInSheets(){
		clickOn(viewOrPrintSignInSheetsLink);
	}
	
	public void selectTenant(String tenantName){
		clickOn(selectTenant_ViewOrPrintSISPage);
//		waitForElementToDisappear(spinner);
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(airlineXPath1+tenantName+airlineXPath2));
		
	}

 public void generateSignInSheetView(){
	 clickOn(btn_Create);
	 waitForPageLoad(driver);
 }

}
