package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_ScheduleNotes extends BasePage{

	@FindBy(id = "comSelectBidPeriod")
	private WebElement commonNotes_BidPeriod;
	
	@FindBy(name = "comServiceType")
	private List<WebElement> commonNotes_ServiceType;
	
	private String serviceTypeXpath1 = "//*[text()='";
	private String commonNotes_serviceTypeXpath2 = "']/input[@name = 'comServiceType']";
	private String supplierNotes_serviceTypeXpath2 = "']/input[@name = 'supServiceType']";
	
	@FindBy(xpath = "//*[@name = 'Notes' and @value = 'Search']")
	private WebElement searchBtn;
	
	@FindBy(name = "comFooterNote")
	private WebElement commonNotes_FooterNote;
	
	@FindBy(name = "comStandardNote")
	private WebElement commonNotes_StandardNote;
	
	@FindBy(name = "comSpecialNote")
	private WebElement commonNotes_SpecialNote;
	
	@FindBy(xpath = "//*[@name = 'Notes' and @value = 'Save Service Notes']")
	private WebElement commonNotes_SaveServiceNotesBtn;
	
	@FindBy(id = "Expand")
	private WebElement expandAllLink;
	
	@FindBy(id = "Collapse")
	private WebElement collapseAllLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Notes for Supplier')]")
	private WebElement notesForSupplierRow;
	
	@FindBy(name = "supSelectBidPeriod")
	private WebElement supplier_BidPeriod;
	
	@FindBy(name = "supServiceType")
	private List<WebElement> supplier_ServiceType;
	
	@FindBy(id = "supCity")
	private WebElement supplier_city;
	
	@FindBy(name = "GetSupplier")
	private WebElement getSupplierBtn;
	
	@FindBy(id = "supSelectSupplier")
	private WebElement supplier;
	
	@FindBy(name = "supSpecificNote")
	private WebElement supplier_Notes;
	
	@FindBy(xpath = "//*[@name = 'Search' and @value = 'Search']")
	private WebElement supplier_SearchBtn;
	
	@FindBy(xpath = "//*[@name = 'Save' and @value = 'Save']")
	private WebElement supplier_SaveBtn;
	
	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_ScheduleNotes(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void setCommonNotes(String commonNotes_ServiceType) throws Exception{
		waitForElementVisibility(expandAllLink);
		waitForElementVisibility(collapseAllLink);
		waitForElementVisibility(commonNotes_BidPeriod);
		clickOn(findElementByXpath(serviceTypeXpath1+commonNotes_ServiceType+commonNotes_serviceTypeXpath2));
		waitForElementVisibility(searchBtn);
	}
	
	public void clickSaveServiceNotesBtn(){
		clickOn(commonNotes_SaveServiceNotesBtn);
		clickOn(alertOkBtn);
	}
	
	public void clickSearchBtn(){
		clickOn(searchBtn);
	}
	
	public void enterCommonNotesText(String footerText, String standardNotes, String specialNotes){
		typeInText(commonNotes_FooterNote, footerText);
		typeInText(commonNotes_StandardNote, standardNotes);
		typeInText(commonNotes_SpecialNote, specialNotes);
		waitForElementVisibility(commonNotes_SaveServiceNotesBtn);
	}
	
	public void setSupplierNotes(String supplier_ServiceType, String city, String supplier_Notes) throws Exception{
		clickOn(notesForSupplierRow);
		waitForElementVisibility(supplier_BidPeriod);
		for(WebElement serviceType : this.supplier_ServiceType)
			if(serviceType.getAttribute("value").equals(supplier_ServiceType)){
				clickOn(serviceType);
				break;
			}
		typeInText(supplier_city, city);
		waitForElementVisibility(getSupplierBtn);
		waitForElementVisibility(supplier);//dropdown is not populated with any options for selected city BOS; Can use SelectByIndex
		waitForElementVisibility(supplier_SearchBtn);
		typeInText(this.supplier_Notes, supplier_Notes);
		waitForElementVisibility(supplier_SaveBtn);
	}
	
	public void clickSaveBtn(){
		clickOn(supplier_SaveBtn);
		clickOn(alertOkBtn);
	}
}
