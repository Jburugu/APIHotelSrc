package com.APIHotels.pages.airlines;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class ConfigureSupplierFeaturesPage extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:locationArea']/div/input")
	public WebElement city;

	@FindBy(how = How.XPATH, using = ".//*[@id='allowanceInvoiceSearchForm:hotelIdArea']/div/select")
	public WebElement hotel;

	@FindBy(xpath="//button[contains(text(),'Search')]")
	public WebElement searchBtn;
	
	@FindBy(xpath="//*[contains(@id,'billingPeriod')]//td/input")
	public List<WebElement> billingPeriods;
	
	String billingPeriodXpath="//*[contains(@id,'billingPeriod')]//td[";
	String xpath="]";
	
	@FindBy(css= "input[value='BIMONTHLY_FULL']")
	private WebElement semiMonthlyRB;
	
	@FindBy(css="input[value='MONTHLY_FULL']")
	private WebElement MonthlyRB;
	
	@FindBy(css="input[value='WEEKLY_FULL']")
	private WebElement WeeklyRB;
	
	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm:supplierPaymentTermArea']//input")
	private WebElement supplierPaymentTerm;
	
	@FindBy(id="allowanceInvoiceSearchForm:autoSendActiveArea:autoSendActiveInput:0")
	private WebElement activeRB;
	
	@FindBy(id="allowanceInvoiceSearchForm:autoSendDaysArea:autoSendDaysInputcopyAll")
	private WebElement copyAllBtn;
	
	@FindBy(id="allowanceInvoiceSearchForm:autoSendDaysArea:autoSendDaysInputremoveAlllink")
	private List<WebElement> removeAllBtn;
	
	@FindBy(id="allowanceInvoiceSearchForm:autoSendActiveArea:autoSendActiveInput:1")
	private WebElement inactiveRB;
	
	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm:supplierEmailArea']//textarea")
	private WebElement contact;
	
	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm:supplierFaxArea']//input")
	private WebElement faxNumber;
	
	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm:supplierSendPrefArea']//td[1]/input")
	private WebElement emailRB;
	
	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm:supplierSendPrefArea']//td[2]/input")
	private WebElement faxRB;
	
	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm:supplierSendPrefArea']//td[3]/input")
	private WebElement BothRB;
	
	@FindBy(id="allowanceInvoiceSearchForm:saveButton")
	private WebElement saveBtn;

	@FindBy(xpath="//*[@id='allowanceInvoiceSearchForm']//dl//span")
	private WebElement successMessage;
	
	public ConfigureSupplierFeaturesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void configureSupplierFeatures(String cityValue, String hotelValue) {
		typeInText(city, cityValue);
		city.sendKeys(Keys.TAB);
		waitTime(9000);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
		waitForElementVisibility(searchBtn);
	}

	public void manageSupplierFeatures_Monthly(String cityValue, String hotelValue, String supplierPaymentTermValue, String email, String faxId, String message) throws InterruptedException{
		typeInText(city, cityValue, "ManageSupplierFeature -> City");
		city.sendKeys(Keys.TAB);
		waitTime(4000);
		selectByVisibleText(hotel, hotelValue);
		clickOn(searchBtn, "ManageSupplierFeature Page -> Search Button");
		clickOn(MonthlyRB, "ManageSupplierFeature Page -> Monthly RadioButton");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+supplierPaymentTermValue+"';", supplierPaymentTerm);
		if(!activeRB.isSelected())
		clickOn(activeRB, "ManageSupplierFeature Page -> Active RadioButton");
		/*if(removeAllBtn.size()>0)
			clickOn(removeAllBtn.get(0));
		*/waitForElementVisibility(copyAllBtn);
		clickOn(copyAllBtn, "ManageSupplierFeature Page -> CopyAll Button");
		typeInText(contact, email, "ManageSupplierFeature Page -> Contact");
		typeInText(faxNumber, faxId, "ManageSupplierFeature Page -> Fax Number");
		clickOn(emailRB, "ManageSupplierFeature Page -> Email RadioButton");
		waitTime(60000);
		clickOn(saveBtn, "ManageSupplierFeature Page -> Save Button");
		
		if(successMessage.getText().equalsIgnoreCase(message)){
			ExtentTestManager.getTest().log(LogStatus.PASS,"Billing period is configured for Monthly ");
		}
		else 
			Assert.fail("Unable to configure billing period for Monthly");
	}
	
	public void manageSupplierFeatures_SemiMonthly(String cityValue, String hotelValue, String supplierPaymentTermValue, 
			String email, String faxId, String message) throws InterruptedException{
		typeInText(city, cityValue, "ManageSupplierFeature Page -> City");
		city.sendKeys(Keys.TAB);
		waitTime(4000);
		selectByVisibleText(hotel, hotelValue, "ManageSupplierFeature Page -> Hotel Dropdown");
		clickOn(searchBtn, "ManageSupplierFeature Page -> Search Button");
		clickOn(semiMonthlyRB, "ManageSupplierFeature Page -> SemiMonthly RadioButton");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+supplierPaymentTermValue+"';", supplierPaymentTerm);
		waitTime(5000);
		clickOn(inactiveRB, "ManageSupplierFeature Page -> Inactive RadioButton");
		typeInText(contact, email, "ManageSupplierFeature Page -> Contact");
		typeInText(faxNumber, faxId, "ManageSupplierFeature Page -> Fax Number");
		clickOn(faxRB, "ManageSupplierFeature Page -> Fax RadioButton");
		waitTime(60000);
		clickOn(saveBtn, "ManageSupplierFeature Page -> Save Button");
		//waitTime(120000);
		if(successMessage.getText().equalsIgnoreCase(message)){
			ExtentTestManager.getTest().log(LogStatus.PASS,"Billing period is configured for SemiMonthly ");
		}
		else 
			Assert.fail("Unable to configure billing period for SemiMonthly");
	}
	
	public void manageSupplierFeatures_Weekly(String cityValue, String hotelValue, String supplierPaymentTermValue, String email,
			String faxId, String message) throws InterruptedException{
		typeInText(city, cityValue, "ManageSupplierFeature Page -> City");
		city.sendKeys(Keys.TAB);
		waitTime(4000);
		selectByVisibleText(hotel, hotelValue, "ManageSupplierFeature Page -> Hotel Dropdown");
		clickOn(searchBtn, "ManageSupplierFeature Page -> Search Button");
		clickOn(WeeklyRB, "ManageSupplierFeature Page -> Weekly RadioButton");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+supplierPaymentTermValue+"';", supplierPaymentTerm);
		waitTime(5000);
		clickOn(inactiveRB, "ManageSupplierFeature Page -> Inactive RadioButton");
		typeInText(contact, email, "ManageSupplierFeature Page -> Contact");
		typeInText(faxNumber, faxId, "ManageSupplierFeature Page -> Fax Number");
		clickOn(BothRB, "ManageSupplierFeature Page -> Both RadioButton");
		waitTime(60000);
		clickOn(saveBtn, "ManageSupplierFeature Page -> Save Button");
		//waitTime(120000);
		if(successMessage.getText().equalsIgnoreCase(message)){
			ExtentTestManager.getTest().log(LogStatus.PASS,"Billing period is configured for Weekly ");
		}
		else 
			Assert.fail("Unable to configure billing period for SemiMonthly");
	}
	
	public void manageSupplierFeature(String cityValue, String hotelValue, String supplierPaymentTermValue, String email, String faxId, String message){
		typeInText(city, cityValue, "ManageSupplierFeature Page -> City");
		city.sendKeys(Keys.TAB);
		waitTime(4000);
		selectByVisibleText(hotel, hotelValue, "ManageSupplierFeature Page -> Hotel Dropdown");
		clickOn(searchBtn, "ManageSupplierFeature Page -> Search Button");
		if(billingPeriods.size()>0){
		for(int i=0; i<billingPeriods.size();i++){
			clickOn(searchBtn, "ManageSupplierFeature Page -> Search Button");
			clickOn(billingPeriods.get(i));
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='"+supplierPaymentTermValue+"';", supplierPaymentTerm);
			waitTime(2000);
			typeInText(contact, email, "ManageSupplierFeature Page -> Contact");
			typeInText(faxNumber, faxId, "ManageSupplierFeature Page -> Fax Number");
			clickOn(BothRB, "ManageSupplierFeature Page -> Both RadioButton");
			waitTime(60000);
			clickOn(saveBtn, "ManageSupplierFeature Page -> Save Button");
			waitTime(3000);
			if(successMessage.getText().equalsIgnoreCase(message)){
				ExtentTestManager.getTest().log(LogStatus.PASS,"Billing period is configured ");
				}
			else 
				Assert.fail("Unable to configure billing period for SemiMonthly");

			}
		}
		else 
			Assert.fail("Unable to configure billing period for SemiMonthly");
	
		
	}
}
	
	


