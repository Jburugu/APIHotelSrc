package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_FindExistingAirlineSupplier extends BasePage{

	@FindBy(id = "findAirlineSuppliers")
	private WebElement findSupplierlink;
	
	@FindBy(id = "locCd")
	private WebElement city;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType;
	
	@FindBy(name = "status")
	private List<WebElement> status;
	
	@FindBy(id = "effectiveDate")
	private WebElement asOfDate;
	
	@FindBy(id = "checkFlag")
	private WebElement searchBtn;
	
	@FindBy(id = "airlineSupplier")
	private WebElement airlineSuppliersTable;
	
	@FindBy(xpath = "//*[@id = 'airlineSupplier']/tbody/tr")
	private List<WebElement> noOfResultRows;
	
	@FindBy(xpath = "//*[@id = 'airlineSupplier']/tbody/tr/td[3]")
	private List<WebElement> supplierList;
	
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
	
	@FindBy(how = How.XPATH, using = "//*[@id = 'alertBot']/input[1]")
	private WebElement makeChangesAlert_OkBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id = 'alertBot']/input[2]")
	private WebElement makeChangesAlert_CancelBtn;
	
	@FindBy(xpath = "//*[@id = 'airlineSupplier']/tbody/tr/td[3]/a")
	private WebElement supplierNameLink;
	
	private String supplierTitle1 = "//*[contains(text(), '";
	private String supplierTitle2 = "')]";
	
	String supplierLinkXpath1 = "//*[contains(text(), '";
	String supplierLinkXpath2 = "')]";
	
	String supplierNameXpath1="//*[@id = 'airlineSupplier']/tbody/tr[";
	String supplierNameXpath2=	"]/td[3]/a";
	//table#airlineSupplier tr:nth-child(2) td:nth-child(3) a -css
	private String serviceTypeXpath1 = "//*[text()='";
	private String serviceTypeXpath2 = "']/input";
	
	private String serviceType1 = "//*[@name = 'serviceType' and @value = '";
	private String serviceType2 = "']";
	
	public EventFiringWebDriver driver=null;

	public Page_FindExistingAirlineSupplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickFindSupplierLink()throws Exception{
		clickOn(findSupplierlink);
	}
	
	public void searchSupplier(String city, String serviceId, String status)throws Exception{
		typeInText(this.city, city);
		clickOn(findElementByXpath(serviceTypeXpath1+serviceId+serviceTypeXpath2));
		//clickOn(findElementByXpath(serviceType1+serviceId+serviceType2));
		System.out.println();
		for(String tmpStatus : Arrays.asList(status.split(","))){
			for(WebElement statusType : this.status){
				if(statusType.getAttribute("value").equals(tmpStatus)){
					if(!statusType.isSelected())
						statusType.click();
					assertTrue(statusType.isSelected(), tmpStatus+" is not selected");
					break;
				}
			}
		}
		
		waitForElementVisibility(asOfDate);
		clickOn(searchBtn);
		int noOfRows = Integer.parseInt(noOfRowsText.getText().split(" ")[0]);
		waitForElementVisibility(airlineSuppliersTable);
		assertTrue(noOfRows == noOfResultRows.size(), "No of Rows mismatch");
	}
	
	public void searchSupplier(String city, int serviceId, String status)throws Exception{
		typeInText(this.city, city);
		for(String tmpStatus : Arrays.asList(status.split(","))){
			for(WebElement statusType : this.status){
				if(statusType.getAttribute("value").equals(tmpStatus)){
					if(!statusType.isSelected())
						statusType.click();
					assertTrue(statusType.isSelected(), tmpStatus+" is not selected");
				}else{
					if(statusType.isSelected())
						statusType.click();
					assertFalse(statusType.isSelected(), tmpStatus+" is selected");
				}
			}
		}
		
		clickOn(findElementByXpath(serviceType1+serviceId+serviceType2));
		
		waitForElementVisibility(asOfDate);
		clickOn(searchBtn);
		int noOfRows = Integer.parseInt(noOfRowsText.getText().split(" ")[0]);
		waitForElementVisibility(airlineSuppliersTable);
		assertTrue(noOfRows == noOfResultRows.size(), "No of Rows mismatch");
	}
	
	public void selectSupplier() throws Exception{
		//logger.info("*** FindExistingAirlineSupplierPage -> selectSupplier method completed ***");
		String supplierName = supplierNameLink.getText();
		clickOn(supplierNameLink);
		waitForPageToLoad("7");
		dynamicWaitForXpath(supplierTitle1+supplierName+supplierTitle2, 30);
		//logger.info("*** selectSupplier method completed ***");
	}
	
	public void selectSupplier(String supplierName) throws Exception{
		clickOn(findElementByXpath(supplierLinkXpath1+supplierName+supplierLinkXpath2));
		waitForPageLoad(getDriver());
	}
	
	public void selectRequiredSupplier(String supplierName){
		
		int listOfSupplier= supplierList.size();
		for(int supplierRow=1; supplierRow<=listOfSupplier; supplierRow++)
		{	
			
			WebElement nameOfSupplier=findElementByXpath(supplierNameXpath1+supplierRow+supplierNameXpath2);
			String supplier=nameOfSupplier.getText();
			if (supplier.equalsIgnoreCase(supplierName))
			{
				clickOn(nameOfSupplier);
				break;
			}
		}
	}
}
