package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_FindCommonSupplier extends BasePage{

	@FindBy(xpath = "//*[contains(text(), 'Find Common Supplier')]")
	private WebElement findCommonSupplierPageLink;
	
	@FindBy(id = "strCityName")
	private WebElement city;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType;
	
	String serviceTypeXpath1 = "//*[@name = 'serviceType' and @value = '";
	String seviceTypeXpath2 = "']";
	
	String supplierLinkXpath1 = "//*[contains(text(), '";
	String supplierLinkXpath2 = "')]";
	
	
	
	@FindBy(name = "searchValue")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id = 'commonSupplier']/tbody/tr")
	private List<WebElement> noOfResultRows;
	
	@FindBy(xpath = "//*[contains(text(),'CSV ')]")
	private WebElement exportOption_CSV;
	
	@FindBy(xpath = "//*[contains(text(),'Excel ')]")
	private WebElement exportOption_Excel;
	
	@FindBy(xpath = "//*[contains(text(),'PDF ')]")
	private WebElement exportOption_PDF ;
	
	@FindBy(xpath = "//*[contains(text(),'Print')]")
	private WebElement exportOption_Print;
	
	@FindBy(className = "Found")
	private WebElement noOfRowsText;
	
	@FindBy(id = "commonSupplier")
	private WebElement commonSuppliersTable;
	
	public EventFiringWebDriver driver=null;

	public Page_FindCommonSupplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickFindCommonSupplierLink() throws Exception{
		clickOn(findCommonSupplierPageLink);
		}
	
	public void searchSupplier(String city, String serviceId)throws Exception{
		typeInText(this.city, city);
		for(WebElement service: this.serviceType){
			if(service.getAttribute("value").equals(serviceId)){
				clickOn(service);
				assertTrue(service.isSelected(), serviceId+"is not selected");
				break;
			}
		}
		clickOn(searchBtn);
		int noOfRows = Integer.parseInt(noOfRowsText.getText().split(" ")[0]);
		waitForElementVisibility(commonSuppliersTable);
		assertTrue(noOfRows == noOfResultRows.size(), "No of Rows mismatch");
	}
	
	public void searchSupplier(String city, String serviceId, String supplier)throws Exception{
		typeInText(this.city, city);
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceId + seviceTypeXpath2));
		clickOn(searchBtn);
		//clickOn(findElementByXpath(supplierLinkXpath1 + supplier + supplierLinkXpath2));
		clickOn(findElementByPartialLinkText(supplier));
		waitForPageLoad(getDriver());
	}
	
	

}
