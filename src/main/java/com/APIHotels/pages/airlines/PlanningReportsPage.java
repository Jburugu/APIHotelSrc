package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class PlanningReportsPage extends BasePage {

	public EventFiringWebDriver driver=null;

	// REPORTS -- PLANNING REPORTS
	//Following Elements are same for all reports under planning reports

	@FindBy(id="reportsForm:tripNumberField:bidPeriods")
	public WebElement bidPeriod;

	@FindBy(xpath= "//input[@value='pdf']")
	public WebElement acrobatAdobeAllVersionsRadioButton;

	@FindBy(xpath= "//input[@value='xls']")
	public WebElement microsoftExcelRadioButton;

	@FindBy(xpath= "//input[contains(@title,'includeStayoverInReport')]")
	public WebElement includeDoubleOvernightsInReportCheckBox;

	@FindBy(css= "input[value='Export']")
	public WebElement exportButton;

	@FindBy(xpath="//input[contains(@title,'listCrewTypeInReport')]")
	public WebElement listByFlightInflightAndOtherCheckbox;
	
	// REPORTS -- PLANNING REPORTS -- DAILY USAGE REPORT --> End

	public PlanningReportsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void dailyUsageReport(String bidPeriodValue) {

		int bidPeriodVal = Integer.parseInt(bidPeriodValue);
		selectByIndex(bidPeriod, bidPeriodVal);
		// Assert.assertEquals(bidPeriod.getAttribute("value"),
		// bidPeriodVal,"bid period mismatch");

		clickOn(acrobatAdobeAllVersionsRadioButton);
		Assert.assertTrue(acrobatAdobeAllVersionsRadioButton.isSelected(), "Acrobat Adobe All Version is not selected");

		clickOn(microsoftExcelRadioButton);
		Assert.assertTrue(microsoftExcelRadioButton.isSelected(), "Microsoft excel is not selected");

		clickOn(includeDoubleOvernightsInReportCheckBox);
		waitForElementVisibility(exportButton);
	}
	
	public void verifyDailyUsageReport(String bidperiodValue, String exportFormat, String reportType, String fileExt) throws Exception{
		selectByVisibleText(bidPeriod, bidperiodValue, "DailyUsageReport Page-> BidPeriod select");
		if(exportFormat.equalsIgnoreCase("PDF"))
			clickOn(acrobatAdobeAllVersionsRadioButton, "DailyUsageReport Page-> AcrobatAdobeAllVersions RadioButton");
		else if(exportFormat.equalsIgnoreCase("Excel"))
			clickOn(microsoftExcelRadioButton, "DailyUsageReport Page-> MicrosoftExcel RadioButton");
		clickOn(includeDoubleOvernightsInReportCheckBox, "DailyUsageReport Page-> IncludeDoubleOvernightsInReport CheckBox");
		clickOn(listByFlightInflightAndOtherCheckbox, "DailyUsageReport Page-> ListByFlightInflightAndOther Checkbox");
		clickOn(exportButton, "DailyUsageReport Page-> Export Button");
		Thread.sleep(10000);
		waitForFileToDownloadAndMove_Chrome(reportType, fileExt, "PlanningReports");
	}
	
	public void verifyLowWaterMarkReport(String bidperiodValue, String exportFormat, String expectedFileName, String fileExt) throws Exception{
		selectByVisibleText(bidPeriod, bidperiodValue, "LowWaterMarkReport Page-> BidPeriod select");
		if(exportFormat.equalsIgnoreCase("PDF"))
			clickOn(acrobatAdobeAllVersionsRadioButton, "LowWaterMarkReport Page-> AcrobatAdobeAllVersions RadioButton");
		else if(exportFormat.equalsIgnoreCase("Excel"))
			clickOn(microsoftExcelRadioButton, "LowWaterMarkReport Page-> MicrosoftExcel RadioButton");
		clickOn(includeDoubleOvernightsInReportCheckBox, "LowWaterMarkReport Page-> IncludeDoubleOvernightsInReport CheckBox");
		clickOn(listByFlightInflightAndOtherCheckbox, "LowWaterMarkReport Page-> ListByFlightInflightAndOther Checkbox");
		clickOn(exportButton, "LowWaterMarkReport Page-> Export Button");
		try{Thread.sleep(10000);}catch(Exception e){}
		waitForFileToDownloadAndMove_Chrome(expectedFileName, fileExt, "PlanningReports");
	}
	
	public void verifyMonthlyActivityReport(String bidperiodValue, String exportFormat, String expectedFileName, String fileExt) throws Exception{
		selectByVisibleText(bidPeriod, bidperiodValue, "MonthlyActivityReport Page-> BidPeriod select");
		if(exportFormat.equalsIgnoreCase("PDF"))
			clickOn(acrobatAdobeAllVersionsRadioButton, "MonthlyActivityReport Page-> AcrobatAdobeAllVersions RadioButton");
		else if(exportFormat.equalsIgnoreCase("Excel"))
			clickOn(microsoftExcelRadioButton, "MonthlyActivityReport Page-> MicrosoftExcel RadioButton");
		clickOn(includeDoubleOvernightsInReportCheckBox, "MonthlyActivityReport Page-> IncludeDoubleOvernightsInReport CheckBox");
		clickOn(listByFlightInflightAndOtherCheckbox, "MonthlyActivityReport Page-> ListByFlightInflightAndOther Checkbox");
		clickOn(exportButton, "MonthlyActivityReport Page-> Export Button");
		try{Thread.sleep(10000);}catch(Exception e){}
		waitForFileToDownloadAndMove_Chrome(expectedFileName, fileExt, "PlanningReports");
	}

}
