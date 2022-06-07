package com.APIHotels.pages.ACES3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_CreateInvoice_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id = "createInvForm:billingPeriod_label")
	private WebElement invoicePeriod;
	
	@FindBy(id = "createInvForm:invoiceNumber")
	private WebElement supplierInvoiceNumber;
	
	@FindBy(xpath = "//*[contains(@id, 'createInvForm')]//*[contains(text(), 'Create Invoice')]")
	private WebElement createInvoiceBtn;
	
	@FindBy(xpath = "//*[@id='createInvForm:unsubTbl_data']//td[1]")
	private List<WebElement> unsubmittedSISDateList;
	
	@FindBy(xpath = "//*[@id='createInvForm:unsubTbl_data']//td[2]/a")
	private List<WebElement> viewUnSubmittedSISList;
	
	private String selectInvoicePeriodXpath1 = "//ul[@id='createInvForm:billingPeriod_items']/li[contains(text(), '";
	private	String selectInvoicePeriodXpath2 = "')]";
	
	@FindBy(xpath = "//*[contains(@id, 'createInvForm:unsubTbl') and contains(@id, 'dispSis')]")
	private List<WebElement> viewSISLinks;
	
	@FindBy(xpath = "//ul[@id='createInvForm:billingPeriod_items']/li[2]")
	private WebElement firstInvoicePeriodOption;
	
	@FindBy(xpath = "//tbody[@id='createInvForm:missingOccPercentTbl_data']//input[contains(@id, 'occPercent')]")
	private List<WebElement> occupancyLevel;
	
	@FindBy(xpath = "//*[@id='createInvForm:applyOccPercentsButton']/span[contains(text(), 'Apply')]")
	private WebElement occupancyLevelApplyBtn;
	
	@FindBy(id = "createInvForm:airline_label")
	private WebElement selectAirline;
	
	private String tenantOptionXpath1 = "//ul[@id='createInvForm:airline_items']/li[text()='";
	private String tenantOptionXpath2 = "']";
	
	public EventFiringWebDriver driver=null;
	
	public Page_CreateInvoice_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createInvoice(String invoicePeriod){
		Random r = new Random();
		clickOn(this.invoicePeriod, "CreateInvoicePage -> Create Invoice btn");
		clickOn(findElementByXpath(selectInvoicePeriodXpath1+invoicePeriod+selectInvoicePeriodXpath2), "CreateInvoicePage -> Invoice Period drop down");
		typeInText(supplierInvoiceNumber, Integer.toString(r.nextInt((10000 - 100) + 1)+100), "CreateInvoicePage -> Supplier Invoice Number");
		clickOn(createInvoiceBtn, "CreateInvoicePage -> Create Invoice btn");
		waitForElementToDisappear(spinner);
	}
	
	public String createInvoiceForFirstBillingPeriod(String tenant){
		Random r = new Random();
		clickOn(this.selectAirline, "CreateInvoicePage -> Select Airline drop down");
		clickOn(findElementByXpath(tenantOptionXpath1+tenant+tenantOptionXpath2), "CreateInvoicePage -> "+tenant+" option");
		waitForElementToDisappear(spinner);
		clickOn(this.invoicePeriod, "CreateInvoicePage -> Select Invoice Period drop down");
		clickOn(firstInvoicePeriodOption, "CreateInvoicePage -> Select Invoice Period drop down -> First option");
		String invoiceMonth = invoicePeriod.getText().split(" ")[1];
		typeInText(supplierInvoiceNumber, Integer.toString(r.nextInt((10000 - 100) + 1)+100), "CreateInvoicePage -> Supplier Invoice Number");
		clickOn(createInvoiceBtn, "CreateInvoicePage -> Create Invoice btn");
		waitForElementToDisappear(spinner);
		return invoiceMonth;
	}
	
	public List<String> getUnSubmittedSISDates(String invoicePeriod){
		List<String> unSubmittedSignInSheetDates = new ArrayList<String>();
		if(unsubmittedSISDateList.size()>0){
			for(WebElement unSubSIS : unsubmittedSISDateList){
				String date = unSubSIS.getText().trim();
				unSubmittedSignInSheetDates.add(date.split(" ")[0]+"-"+date.split(" ")[1]);
			}
		}
		return unSubmittedSignInSheetDates;
	}
	
	public int getUnsubmittedSIS(){
		return viewUnSubmittedSISList.size();
	}
	
	public void clickOnViewUnsubmittedSIS(int index){
		clickOn(viewUnSubmittedSISList.get(index), "CreateInvoicePage -> "+index+"th View Sign-in Sheet");
		waitForElementToDisappear(spinner);
	}
	
	public boolean addMissingOccupancyLevels(String occupancyLevel){
		if(this.occupancyLevel.size()>0){
			for(WebElement thisOccupancyLevel : this.occupancyLevel)
				typeInText(thisOccupancyLevel, occupancyLevel, "CreateInvoicePage -> Occupancy Level");
			clickOn(occupancyLevelApplyBtn, "CreateInvoicePage -> Occupancy Level -> Apply btn");
			clickOn(createInvoiceBtn, "CreateInvoicePage -> Create Invoice btn");
			waitForElementToDisappear(spinner);
			return true;
		}else
			return false;
	}
}
