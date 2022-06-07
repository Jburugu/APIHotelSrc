package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_AirlinesReport_ACES3ACES extends BasePage{

	@FindBy(how=How.XPATH, using="//a[contains(text(),'Oracle Financial Reports')]")
	WebElement OracleFinancialReportsLink; 
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Virgin Australia Payment File')]")
	WebElement VirginAustraliaPaymentFileLink; 
	
	@FindBy(how = How.LINK_TEXT, using = "Airline Reports")
	WebElement link_AirlineReports;

	@FindBy(how = How.LINK_TEXT, using = "Oracle Financial Reports")
	WebElement link_OracleFinancialReports;

	@FindBy(how = How.LINK_TEXT, using = "Virgin Australia Payment File")
	WebElement link_VirginAustraliaPaymentFile;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Oracle Accrual')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_OracleAccrual;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Oracle Actual')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_OracleActual;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Oracle Variance')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_OracleVariance;

	@FindBy(how = How.ID, using = "OracleFinancialReportsForm:bpStart__input")
	WebElement textbox_OracleAccrualStartDate;

	@FindBy(how = How.ID, using = "OracleFinancialReportsForm:bpStart_input")
	WebElement textbox_OracleActualStartDate;

	@FindBy(how = How.ID, using = "OracleFinancialReportsForm:abpStart_input")
	WebElement textbox_OracleActualApprovedStartDate;

	@FindBy(how = How.XPATH, using = "//span[@id='OracleFinancialReportsForm:bpStart_']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_OracleAccrualStartDate;

	@FindBy(how = How.XPATH, using = "//span[@id='OracleFinancialReportsForm:bpStart']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_OracleActualStartDate;

	@FindBy(how = How.XPATH, using = "//span[@id='OracleFinancialReportsForm:abpStart']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_OracleActualApprovedStartDate;

	@FindBy(how = How.ID, using = "OracleFinancialReportsForm:bpEnd__input")
	WebElement textbox_OracleAccrualEndDate;

	@FindBy(how = How.ID, using = "OracleFinancialReportsForm:bpEnd_input")
	WebElement textbox_OracleActualEndDate;

	@FindBy(how = How.ID, using = "OracleFinancialReportsForm:abpEnd_input")
	WebElement textbox_OracleActualApprovedEndDate;

	@FindBy(how = How.XPATH, using = "//span[@id='OracleFinancialReportsForm:bpEnd_']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_OracleAccrualEndDate;

	@FindBy(how = How.XPATH, using = "//span[@id='OracleFinancialReportsForm:bpEnd']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_OracleActualEndDate;

	@FindBy(how = How.XPATH, using = "//span[@id='OracleFinancialReportsForm:abpEnd']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepicker_OracleActualApprovedEndDate;
	
	@FindBy(how=How.ID, using="vaaPaymentFileForm:bpStart_input")
	WebElement dateStart_VirginAutraliaPytFile;
	
	@FindBy(how=How.ID, using="vaaPaymentFileForm:bpEnd_input")
	WebElement dateEnd_VirginAutraliaPytFile;
	
	
	@FindBy(how=How.XPATH, using="//span[@id='vaaPaymentFileForm:bpStart']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepickerStart_VirginAutraliaPytFile;
	
	@FindBy(how=How.XPATH, using="//span[@id='vaaPaymentFileForm:bpEnd']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepickerEnd_VirginAutraliaPytFile;
	
	@FindBy(how=How.ID, using="vaaPaymentFileForm:abpStart_input")
	WebElement dateAStart_VirginAutraliaPytFile;
	
	@FindBy(how=How.ID, using="vaaPaymentFileForm:abpEnd_input")
	WebElement dateAEnd_VirginAutraliaPytFile;

	@FindBy(how=How.XPATH, using="//span[@id='vaaPaymentFileForm:abpStart']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepickerAStart_VirginAutraliaPytFile;
	
	@FindBy(how=How.XPATH, using="//span[@id='vaaPaymentFileForm:abpEnd']//span[@class='ui-button-icon-left ui-icon ui-icon-calendar']")
	WebElement datepickerAEnd_VirginAutraliaPytFile;
	
	@FindBy(how=How.XPATH, using ="//span[contains(text(),'Download')]")
	WebElement btn_DownloadVirginAusReport;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'tenant_label')]")
	WebElement selectAirline;
		
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Generate Report')]")
	WebElement generateReport;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Approved Date')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_ApprovedDate;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Billing Period')]/parent::td/div/div[contains(@class,'radiobutton')]")
	WebElement radiobtn_BillingPeriod;

	
	
	public EventFiringWebDriver driver = null;
	public Page_AirlinesReport_ACES3ACES(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnOracleFinancialReportsLink()
	{
		clickOn(OracleFinancialReportsLink,"Airlines Reports Menu -> Oracle Financial Reports Link");
	}
	
	public void clickOnVirginAustraliaPaymentFileLink()
	{
		clickOn(VirginAustraliaPaymentFileLink,"Airlines Reports Menu -> Virgin Australia Payment File Link");
	}
	
	public void clickOnAirlineReportsLink() {
		clickOn(link_AirlineReports,"Accounting Menu -> Airlines Reports Link");
	}

	public void clickOnOracleFinancialReports() {
		clickOn(link_OracleFinancialReports,"Airlines Reports Menu -> Oracle Financial Reports Link");
	}

	public void verifyElementsOnOracleFinancialReportsPage() {
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_OracleAccrualStartDate);
		waitForElementVisibility(datepicker_OracleAccrualStartDate);
		waitForElementVisibility(textbox_OracleAccrualEndDate);
		waitForElementVisibility(datepicker_OracleAccrualEndDate);
		waitForElementVisibility(radiobtn_OracleAccrual);
		waitForElementVisibility(radiobtn_OracleActual);
		waitForElementVisibility(radiobtn_OracleVariance);
		waitForElementVisibility(generateReport);

		clickOn(radiobtn_OracleActual,"Oracle Financial Reports Page -> Oracle Actaul Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_OracleActualStartDate);
		waitForElementVisibility(datepicker_OracleActualStartDate);
		waitForElementVisibility(textbox_OracleActualEndDate);
		waitForElementVisibility(datepicker_OracleActualEndDate);
		waitForElementVisibility(radiobtn_OracleAccrual);
		waitForElementVisibility(radiobtn_OracleActual);
		waitForElementVisibility(radiobtn_OracleVariance);
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(generateReport);
		clickOn(radiobtn_ApprovedDate,"Oracle Financial Reports Page -> Oracle Actaul -> Approved Date Radio Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_OracleActualApprovedStartDate);
		waitForElementVisibility(datepicker_OracleActualApprovedStartDate);
		waitForElementVisibility(textbox_OracleActualApprovedEndDate);
		waitForElementVisibility(datepicker_OracleActualApprovedEndDate);
		waitForElementVisibility(radiobtn_OracleAccrual);
		waitForElementVisibility(radiobtn_OracleActual);
		waitForElementVisibility(radiobtn_OracleVariance);
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(generateReport);
		
		clickOn(radiobtn_OracleVariance,"Oracle Financial Reports Page -> Oracle Variance Radio Button");
		waitForElementVisibility(selectAirline);
		waitForElementVisibility(textbox_OracleAccrualStartDate);
		waitForElementVisibility(datepicker_OracleAccrualStartDate);
		waitForElementVisibility(textbox_OracleAccrualEndDate);
		waitForElementVisibility(datepicker_OracleAccrualEndDate);
		waitForElementVisibility(radiobtn_OracleAccrual);
		waitForElementVisibility(radiobtn_OracleActual);
		waitForElementVisibility(radiobtn_OracleVariance);
		waitForElementVisibility(generateReport);
	}
	

	public void verifyElementOnVirginAustraliaPaymentFilePage(){
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(radiobtn_ApprovedDate);
		waitForElementVisibility(dateStart_VirginAutraliaPytFile);
		waitForElementVisibility(dateEnd_VirginAutraliaPytFile);
		waitForElementVisibility(datepickerStart_VirginAutraliaPytFile);
		waitForElementVisibility(datepickerEnd_VirginAutraliaPytFile);
		waitForElementVisibility(btn_DownloadVirginAusReport);
		clickOn(radiobtn_ApprovedDate,"Oracle Financial Reports Page -> VirginAustralia Payment File -> Approved Date Radio Button");
		waitForElementVisibility(radiobtn_BillingPeriod);
		waitForElementVisibility(dateAStart_VirginAutraliaPytFile);
		waitForElementVisibility(dateAEnd_VirginAutraliaPytFile);
		waitForElementVisibility(datepickerAStart_VirginAutraliaPytFile);
		waitForElementVisibility(datepickerAEnd_VirginAutraliaPytFile);
		waitForElementVisibility(btn_DownloadVirginAusReport);
		
	}
	
}
