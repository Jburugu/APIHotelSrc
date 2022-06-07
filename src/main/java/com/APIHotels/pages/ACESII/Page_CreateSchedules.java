package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_CreateSchedules extends BasePage{
	
	@FindBy(id = "scheduleType")
	private WebElement scheduleType;
	
	@FindBy(id = "bidmonth")
	private WebElement bidPeriod;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType;
	
	String serviceTypeXpath1 = "//*[@name = 'serviceType' and @value = '";
	String serviceTypeXpath2 = "']";
	
	@FindBy(id = "locCd")
	private WebElement city;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Get Supplier']")
	private WebElement getSupplierBtn;
	
	@FindBy(id = "suppliers")
	private WebElement supplier;
	
	@FindBy(id = "overwriteId")
	private WebElement overwriteIndicator;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Create Schedules']")
	private WebElement createSchedulesBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Server Print']")
	private WebElement serverPrintBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Local Print']")
	private WebElement localPrintBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Send to Disk']")
	private WebElement sendToDiskBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Regenerate Schedules']")
	private WebElement regenerateSchedulesBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Release Schedules']")
	private WebElement releaseSchedulesBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Delete Schedules']")
	private WebElement deleteSchedulesBtn;
	
	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Fax']")
	private WebElement faxBtn;
	
	@FindBy(id = "CreateScheduleProgress")
	private WebElement inProgressDiv;
	
	@FindBy(xpath = "//*[@id = 'schedule']/tbody/tr")
	private List<WebElement> scheduleRows;
	
	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[3]")
	private List<WebElement> scheduleCity;
	
	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[4]")
	private List<WebElement> scheduleSupplierType;
	
	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[5]/a")
	private List<WebElement> scheduleSupplier;
	
	
	public EventFiringWebDriver driver=null;

	public Page_CreateSchedules(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setSchedulesCriteria(String serviceType, String city, int supplier, String overwriteIndicator) throws Exception{
		//logger.info("*** CreateSchedulesPage -> setSchedulesCriteria method started ***");
		waitForElementVisibility(scheduleType);
		waitForElementVisibility(bidPeriod);
		/*for(WebElement service : this.serviceType)
			if(service.getAttribute("value").equals(serviceType)){
				clickOn(service);
				break;
			}*/
		clickOn(findElementByXpath(serviceTypeXpath1+serviceType+serviceTypeXpath2));
		typeInText(this.city, city);
		clickOn(getSupplierBtn);
		if(supplier != -1)
			selectByIndex(this.supplier, supplier);
		if(overwriteIndicator.equalsIgnoreCase("Yes"))
			clickOn(this.overwriteIndicator);
		waitForElementVisibility(createSchedulesBtn);
		//logger.info("*** setSchedulesCriteria method completed ***");
	}
	
	public void clickOnCreateSchedules(){
		clickOn(createSchedulesBtn);
	}
	
	public void waitForProcessingToComplete(){
		waitForElementToDisappear(inProgressDiv);
		waitForPageLoad(getDriver());
	}
	
	
	/**
	 * @param city
	 * @param serviceType
	 * @param supplier
	 * 
	 * This method is used to verify if the schedules are created based on the filtration criteria or not
	 */
	
	public void verifyResults(String city, String serviceType, String supplier){
		int noOfRows = scheduleRows.size();
		if(Integer.parseInt(supplier) != -1){
			Select select = new Select(this.supplier);
			supplier = select.getOptions().get(Integer.parseInt(supplier)).getText();
		}
		
		if(noOfRows > 0){
			if(!city.isEmpty())
				for(WebElement thisCity : scheduleCity)
					assertEquals(thisCity.getText().trim(), city, "City Mismatch!");
			
			if(!supplier.equals("-1"))
				for(WebElement thisSupplier : scheduleSupplier)
					assertEquals(thisSupplier.getText(), supplier);
			
			for(WebElement thisServiceType : scheduleSupplierType)
				if(!serviceType.equals("BOTH"))
					assertEquals(thisServiceType.getText().trim(), serviceType);
				else
					if(!thisServiceType.getText().trim().equals("HOTEL") && !thisServiceType.getText().trim().equals("GT"))
						ExtentTestManager.getTest().log(LogStatus.FAIL, "ServiceType Mismatch! Showing: "+thisServiceType);
		}
	}
	
	public void schedules() throws Exception{
		//logger.info("*** CreateSchedulesPage -> schedules method started ***");
		waitForElementVisibility(serverPrintBtn);
		waitForElementVisibility(localPrintBtn);
		waitForElementVisibility(sendToDiskBtn);
		waitForElementVisibility(regenerateSchedulesBtn);
		waitForElementVisibility(releaseSchedulesBtn);
		waitForElementVisibility(deleteSchedulesBtn);
		waitForElementVisibility(faxBtn);
		//logger.info("*** schedules method completed ***");
	}
	
	public void createSchedules(String bidPeriodValue, String serviceType, String city, String supplierValue) {
		waitForElementVisibility(scheduleType);
		selectByVisibleText(bidPeriod, bidPeriodValue);
		clickOn(findElementByXpath(serviceTypeXpath1+serviceType+serviceTypeXpath2));
		typeInText(this.city, city);
		clickOn(getSupplierBtn);
		selectByVisibleText(this.supplier, supplierValue);
	}
	
	public void verifySchResults(String city, String serviceType, String supplier){
		int noOfRows = scheduleRows.size();
			
		if(noOfRows > 0){
			if(!city.isEmpty())
				for(WebElement thisCity : scheduleCity)
					assertEquals(thisCity.getText().trim(), city, "City Mismatch!");
			
			if(!supplier.isEmpty())
				for(WebElement thisSupplier : scheduleSupplier)
					assertEquals(thisSupplier.getText(), supplier);
			
			for(WebElement thisServiceType : scheduleSupplierType)
				if(!serviceType.equals("BOTH"))
					assertEquals(thisServiceType.getText().trim(), serviceType);
				else
					if(!thisServiceType.getText().trim().equals("HOTEL") && !thisServiceType.getText().trim().equals("GT"))
						ExtentTestManager.getTest().log(LogStatus.FAIL, "ServiceType Mismatch! Showing: "+thisServiceType);
		}
	}
	
}
