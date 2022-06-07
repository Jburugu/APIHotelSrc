package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class GTConsolidatedInvoicePage extends BasePage {

	public EventFiringWebDriver driver=null;
	// REPORTS -- GTREPORTS -- GT CONSOLIDATED INVOICES(VIRGIN AUSTRALIA)

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'locCd')]")
	public WebElement city;

	@FindBy(how = How.ID, using = "GTConsInvReptForm:supplierId")
	public WebElement selectSuppliers;

	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Get Supplier')]")
	public WebElement getSuppliers;

	@FindBy(how = How.XPATH, using = "//label[text()=' Actual']/preceding-sibling::input[@type='radio']")
	public WebElement oracleExtractReportTypeActual;

	@FindBy(how = How.ID, using = "GTConsInvReptForm:gtConsInvReptField:bidPeriod")
	public WebElement billingPeriod;

	@FindBy(how = How.ID, using = "GTConsInvReptForm:fromDateDec:fromDateInputDate")
	public WebElement fromDate;

	@FindBy(how = How.ID, using = "GTConsInvReptForm:fromDateDec:fromDatePopupButton")
	public WebElement fromDateCalendar;

	@FindBy(how = How.ID, using = "GTConsInvReptForm:toDateDec:toDateInputDate")
	public WebElement toDate;

	@FindBy(how = How.ID, using = "GTConsInvReptForm:toDateDec:toDatePopupButton")
	public WebElement toDateCalendar;

	@FindBy(how = How.XPATH, using = ".//*[@value='Generate Report']")
	public WebElement generateReportButton;

	public GTConsolidatedInvoicePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void gtConsolidatedInvoice(String cityValue, String supplierValue, String billingPeriodValue) {
		typeInText(city, cityValue);
		clickOn(getSuppliers);
		waitTime(5000);
		selectByIndex(selectSuppliers, Integer.parseInt(supplierValue));
		waitForElementVisibility(oracleExtractReportTypeActual);
		selectByIndex(billingPeriod, Integer.parseInt(billingPeriodValue));
		typeInText(fromDate, currentDate());
		waitForElementVisibility(fromDateCalendar);
		typeInText(toDate, currentDatePlus(1));
		waitForElementVisibility(toDateCalendar);
		waitForElementVisibility(generateReportButton);

	}

}
