package com.APIHotels.pages.ACES3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_InvoiceComplianceReport_ACES3ACES extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "invComplianceForm:tenant_label")
	private WebElement selectAirlineDropDown;
	
	@FindBy(xpath = "//*[@aria-label='Show Calendar']")
	private List<WebElement> calendarBtn;
	
	private String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/select/option[@selected = 'selected']")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	private String selectAirlineXpath1 = "//ul[@id='invComplianceForm:tenant_items']/li[text()='";
	private String selectAirlineXpath2 = "']";
	
	@FindBy(xpath = "//*[contains(text(), 'Search')]")
	private WebElement searchBtn;
	
	private String selectSupplierChkBoxXpath1 = "//*[@id='invComplianceForm:table_data']//td[text()='";
	private String selectSupplierChkBoxXpath2 = "']/following-sibling::td[5]/div";
	
	@FindBy(xpath = "//*[contains(text(), 'Generate Invoices for Selected Suppliers')]")
	private WebElement generateInvoicesForSuppliersBtn;
	
	@FindBy(xpath = "//div[@id = 'invComplianceForm:good']")
	private WebElement createdInvoices;
	
	public EventFiringWebDriver driver=null;
	
	public Page_InvoiceComplianceReport_ACES3ACES(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchNonCompliantInvoiceReports(String tenantName) throws InterruptedException{
		clickOn(selectAirlineDropDown, "Invoice Compliance Report -> Airline Select");
		clickOn(findElementByXpath(selectAirlineXpath1+tenantName+selectAirlineXpath2), "Invoice Compliance Report -> Airline Selected From Suggestions List");
		clickOn(calendarBtn.get(0));
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", "1-Aug");
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
		clickOn(searchBtn, "Invoice Compliance Report -> Search Button");
		waitForElementToDisappear(spinner);
	}
	
	public List<String> generateInvoicesForSelectedSuppliers(String supplierName){
		for(String supplier : supplierName.split(","))
			clickOn(findElementByXpath(selectSupplierChkBoxXpath1+supplier+selectSupplierChkBoxXpath2), "Invoice Compliance Report -> Supplier Selected From Suggestion List");
		clickOn(generateInvoicesForSuppliersBtn, "Invoice Compliance Report -> Generate Invoice For Supplier Button");
		waitForElementToDisappear(spinner);
		List<String> invoiceNumbers = new ArrayList<String>();
		List<String> invoiceNumberText = new ArrayList<String>(Arrays.asList(createdInvoices.getText().split("\n")));
		for(int i=2; i<invoiceNumberText.size(); i++){
			String a = invoiceNumberText.get(i).substring(invoiceNumberText.get(i).indexOf("-")+1, invoiceNumberText.get(i).length()).split(" ")[0];
			invoiceNumbers.add(a);
		}
		return invoiceNumbers;
	}
	
}
