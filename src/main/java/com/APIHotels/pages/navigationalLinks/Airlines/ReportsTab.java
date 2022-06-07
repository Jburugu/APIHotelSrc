package com.APIHotels.pages.navigationalLinks.Airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ReportsTab extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = "//td[text()='Reports']")
	public WebElement reportsLink;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Planning Reports')]")
	private WebElement planningReportsLink;

	@FindBy(how = How.ID, using = "icondailyUsageReport")
	public WebElement dailyUsageReportlink;

	@FindBy(how = How.ID, using = "iconlowWaterMarkReport")
	public WebElement lowWaterMarkReportLink;

	@FindBy(how = How.ID, using = "iconmonthlyActivityReport")
	public WebElement monthlyActivityReportLink;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Accounting Reports')]")
	public WebElement accountingReportlink;
	
	@FindBy(how = How.ID, using = "iconapprovedInvoiceReport")
	public WebElement approvedInvoice;

	@FindBy(how = How.ID, using = "icontaxExemptionReport")
	public WebElement taxExemptionReport;

	@FindBy(how = How.ID, using = "iconnoShowReport")
	public WebElement noShowReport;

	@FindBy(how = How.ID, using = "iconcrewIdIssuesReport")
	public WebElement crewIDIssueReport;
	
	@FindBy(how = How.ID, using = "iconglCodeReport")
	public WebElement glCodeReport;
	
	@FindBy(how = How.ID, using = "iconCATTSreport")
    public WebElement CATTSReport;
	
	@FindBy(how = How.ID, using = "iconconsolidateRoomSummary")
	public WebElement consolidateRoomSummaryReportLink;
	
	@FindBy(how = How.ID, using = "iconroomUsageExtract")
	public WebElement taxGLCodeReportLink;
	
	@FindBy(how = How.ID, using = "icongtOracleReport")
	public WebElement gtOracleReportLink;
	
	@FindBy(how = How.XPATH, using = "//td[text()='GT Reports']")
	public WebElement gtReportsLink;

	@FindBy(how = How.ID, using = "icongtPaymentFile")
	public WebElement gtPaymentFileLink;
	
	@FindBy(how = How.ID, using = "icongtUsageVA")
	public WebElement gtUsageReportLink;
	
	@FindBy(how = How.ID, using = "icongtConsInv")
	public WebElement gtConsolidatedInvoiceLink;
	
	public ReportsTab(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPlanningLink() {
		clickOn(reportsLink);
		clickOn(planningReportsLink);
	}

	public void clickOnDailyUsageReportLink() {
		clickOnPlanningLink();
		clickOn(dailyUsageReportlink);
	}

	public void clickOnLowWaterMarkReportLink() {
		clickOnPlanningLink();
		clickOn(lowWaterMarkReportLink);
	}

	public void clickOnMonthlyActivityReportLink() {
		clickOnPlanningLink();
		clickOn(monthlyActivityReportLink);
	}

	public void clickOnAccountingReportLink() {
		clickOn(reportsLink);
		clickOn(accountingReportlink);
	}
	
	public void clickOnApprovedInvoiceReportLink(){
		clickOnAccountingReportLink();
		clickOn(approvedInvoice);
	}
	
	public void clickOnTaxExemptionReportLink(){
		clickOnAccountingReportLink();
		clickOn(taxExemptionReport);
	}
	
	public void clickOnNoShowReportLink(){
		clickOnAccountingReportLink();
		clickOn(noShowReport);
	}
	
	public void clickOnCrewIDIssueReportLink(){
		clickOnAccountingReportLink();
		clickOn(crewIDIssueReport);
	}
	
	public void clickOnGLCodeReportLink(){
		clickOnAccountingReportLink();
		clickOn(glCodeReport);
	}
	
	public void clickOnCATTSReport(){
        clickOnAccountingReportLink();
        clickOn(CATTSReport);
    }
	
	public void clickOnConsolidateRoomSummaryReportLink(){
		clickOnAccountingReportLink();
		clickOn(consolidateRoomSummaryReportLink);
	}
	
	public void clickOnTaxGLCodeReportLink(){
		clickOnAccountingReportLink();
		clickOn(taxGLCodeReportLink);
	}
	
	public void clickOnReportTab() {
		clickOn(reportsLink);
	}
	
	public void clickOnGTReports(){
		clickOnReportTab();
		clickOn(gtReportsLink);
	}
	
	public void clickOnGTOracleReport(){
		clickOn(gtOracleReportLink);
	}
	
	public void clickOnGTPaymentFile(){
		clickOn(gtPaymentFileLink);
	}
	
	public void clickOnGTUsageReportLink(){
		clickOn(gtUsageReportLink);
	}
	
	public void clickOnGTConsolidatedInvoiceLink(){
		clickOn(gtConsolidatedInvoiceLink);
	}
}
