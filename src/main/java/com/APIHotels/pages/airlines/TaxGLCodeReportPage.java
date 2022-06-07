package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class TaxGLCodeReportPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- TAX GL CODE(HAWAIIAN)

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'glCode')]")
	public WebElement glCode;

	@FindBy(how = How.ID, using = "roomUsageExtractForm:taxCodeArea:taxGlCode")
	public WebElement taxGLCode;

	@FindBy(how = How.ID, using = "roomUsageExtractForm:currencyArea:currency")
	public WebElement currency;

	@FindBy(how = How.ID, using = "roomUsageExtractForm:calendarMonth:bidPeriods")
	public WebElement invoiceMonthBidPeriod;

	@FindBy(how = How.XPATH, using = "roomUsageExtractForm:inputButton")
	public WebElement generateReport;

	public TaxGLCodeReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void taxGLCodeByCurrencyQuery(String glCodeValue,String taxGLCodeValue,String currencyValue) {
		typeInText(glCode, glCodeValue);
		typeInText(taxGLCode, taxGLCodeValue);
		typeInText(currency, currencyValue);
	}

}
