package com.APIHotels.pages.airlines;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class AccountingReportsPage extends BasePage {

	@FindBy(xpath = "//form[@name='approvedInvoicesForm']//h2")
	private List<WebElement> header_ApprovedInvoicesPage;

	@FindBy(id = "iconapprovedInvoiceReport")
	public WebElement approvedInvoice;

	@FindBy(id = "icontaxExemptionReport")
	public WebElement taxExemptionReport;

	@FindBy(xpath = "//form[@name='taxemptionReportForm']//h2")
	private List<WebElement> header_TaxExamptionReportPage;

	@FindBy(xpath = "//h2[text()='No-Show Report']")
	private List<WebElement> header_NoShowReportPage;

	@FindBy(id = "iconnoShowReport")
	private WebElement noShowReport;

	@FindBy(id = "iconcrewIdIssuesReport")
	private WebElement crewIDIssuesReport;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;

	@FindBy(xpath = "//h2[text()='Crew ID Issues Report']")
	private List<WebElement> header_crewIDIssueReport;

	@FindBy(xpath = "//label[@class='ui-selectonemenu-label ui-inputfield ui-corner-all']")
	private WebElement select_SelectMonth;

	@FindBy(xpath = "//button[contains(@name,'approvedInvoicesForm')]//span[@class='ui-button-text ui-c']")
	private WebElement button_ApprovedInvoiceSearch;

	@FindBy(xpath = "//form[@name='approvedInvoicesForm']//th[1]")
	private WebElement awaitingPaymentAuthorization;

	@FindBy(xpath = "//form[@name='approvedInvoicesForm']//div//a//img")
	private WebElement img_DownloadExcelReport;

	@FindBy(xpath = "//button[contains(@name,'taxemptionReportForm')]//span[@class='ui-button-text ui-c']")
	private WebElement button_TaxExemptionReportSearch;

	@FindBy(xpath = "//span[contains(text(),'Search')]")
	private WebElement button_NoShowReportSearch;

	@FindBy(xpath = "//div[@id='taxemptionReportForm:taxExemptDataTable']")
	private WebElement taxExemptDataTable;

	@FindBy(xpath = "//div[@id='taxemptionReportForm:taxExCurrencyTable']")
	private WebElement taxExCurrencyTable;

	@FindBy(xpath = "//form[@name='taxemptionReportForm']//a[1]//img[1]")
	private WebElement img_TaxExemptionReportPDF;

	@FindBy(xpath = "//form[@name='taxemptionReportForm']//a[2]//img[1]")
	private WebElement img_TaxExemptionReportExcel;

	@FindBy(xpath = "//div[contains(@id,'noShowReportDataTable')]")
	private WebElement noShowReportDataTable;

	@FindBy(xpath = "//div[contains(@id,'noShowReportCurrencyTable')]")
	private WebElement noShowReportCurrencyTable;

	@FindBy(xpath = "//div[contains(@id,'noShowReportDataTable')]/following-sibling::div/a[1]")
	private WebElement img_noShowReportPDF;

	@FindBy(xpath = "//div[contains(@id,'noShowReportDataTable')]/following-sibling::div/a[2]")
	private WebElement img_noShowReportEXCEL;
	
	@FindBy (xpath = "//span[text()='Export as Excel']")
	private WebElement button_ExportAsExcel;
	
	@FindBy (xpath = "//div[contains(@id,'crewIdDataTable')]")
	private WebElement crewIdDataTable;

	@FindBy (xpath ="//div[contains(@id,'crewIdDataTable')]/following-sibling::div/a[1]")
	private WebElement crewIDPDFLink;
	
	@FindBy (xpath ="//div[contains(@id,'crewIdDataTable')]/following-sibling::div/a[2]")
	private WebElement crewIDEXCELLink;
	
	public EventFiringWebDriver driver = null;

	public AccountingReportsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void accountingReports() {

		windowHandler();
	}

	public void verifyApprovedInvoicesPage() throws InterruptedException, IOException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(approvedInvoice, "Accounting Reports Menu -> Approved Invoice Link");
		waitForPageLoad(driver);
		Thread.sleep(4000);
		switchToNewWindow(parentHandle);
		if (header_ApprovedInvoicesPage.size() > 0) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Approved Invoice Page got rendered successfully");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Approved Invoice Page failed to rendered ");
		}
		driver.manage().window().maximize();
		clickOn(button_ApprovedInvoiceSearch, "ApprovedInvoicePage -> ApprovedInvoiceSearch Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(awaitingPaymentAuthorization);
		clickOn(img_DownloadExcelReport, "ApprovedInvoicePage -> DownloadExcel Image");
		Thread.sleep(3000);
		takeScreenshots();
		getDriver().close();
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("Switched to Parent Window");

	}

	public void verifyTaxExemptionReportPage() throws InterruptedException, IOException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(taxExemptionReport, "Accounting Reports Menu -> Tax Exemption Report Link");
		waitForPageLoad(driver);
		Thread.sleep(4000);
		switchToNewWindow(parentHandle);
		if (header_TaxExamptionReportPage.size() > 0) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Tax Exemption Report Page got rendered successfully");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Tax Exemption Report Page failed to rendered ");
		}
		driver.manage().window().maximize();
		String selectMonth = previousMonthCurrentYearSelection();
		clickOn(select_SelectMonth, "clicked on Select Month dropdown");
		clickOn(findElementByXpath("//ul[contains(@id,'selectedMonth_items')]/li[text()='" + selectMonth + "']"),
				"Selected Month " + selectMonth);
		clickOn(button_TaxExemptionReportSearch, "Tax Exemption Report -> Search Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(taxExemptDataTable);
		clickOn(img_TaxExemptionReportPDF, "Tax Exemption Report -> PDF link");
		waitForPageLoad(driver);
		clickOn(img_TaxExemptionReportExcel, "Tax Exemption Report -> Excel link");
		Thread.sleep(3000);
		takeScreenshots();
		getDriver().close();
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("Switched to Parent Window");

	}

	public void verifyNoShowReportPage() throws InterruptedException, IOException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(noShowReport, "Accounting Reports Menu -> No-Show Report Link");
		waitForPageLoad(driver);
		Thread.sleep(4000);
		switchToNewWindow(parentHandle);
		if (header_NoShowReportPage.size() > 0) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "No-Show Report Page got rendered successfully");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No-Show Report Page failed to rendered ");
		}
		driver.manage().window().maximize();
		String selectMonth = previousMonthCurrentYearSelection();
		clickOn(select_SelectMonth, "clicked on Select Month dropdown");
		clickOn(findElementByXpath("//ul[contains(@id,'selectedMonth_items')]/li[text()='" + selectMonth + "']"),
				"Selected Month " + selectMonth);
		clickOn(button_NoShowReportSearch, "No-Show Report -> Search Button");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(noShowReportDataTable);
		clickOn(img_noShowReportPDF, "No-Show Report -> PDF link");
		waitForPageLoad(driver);
		clickOn(img_noShowReportEXCEL, "No-Show Report -> Excel link");
		Thread.sleep(3000);
		takeScreenshots();
		getDriver().close();
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("Switched to Parent Window");

	}

	public void verifyCrewIDIssuesReportPage() throws InterruptedException, IOException {
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(crewIDIssuesReport, "Accounting Reports Menu -> Crew ID Issues Report Link");
		waitForPageLoad(driver);
		Thread.sleep(4000);
		switchToNewWindow(parentHandle);
		if (header_crewIDIssueReport.size() > 0) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Crew ID Issues Report Page got rendered successfully");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Crew ID Issues Page failed to rendered ");
		}
		driver.manage().window().maximize();
		String selectMonth = previousMonthCurrentYearSelection();
		clickOn(select_SelectMonth, "clicked on Select Month dropdown");
		clickOn(findElementByXpath("//ul[contains(@id,'selectedMonth_items')]/li[text()='" + selectMonth + "']"),
				"Selected Month " + selectMonth);
		clickOn(button_NoShowReportSearch, "Crew ID Issue Report -> Search Button");
		waitForElementVisibility(crewIdDataTable);
		clickOn(crewIDPDFLink, "Crew ID Issues Reports Page -> PDF Link");
		waitForPageLoad(driver);
		clickOn(crewIDEXCELLink, "Crew ID Issues Reports Page -> Excel Link");
		takeScreenshots();
		getDriver().close();
		getDriver().switchTo().window(parentHandle.get(0));
		System.out.println("Switched to Parent Window");

	}

	public String previousMonthCurrentYearSelection() {
		DateFormat yr = new SimpleDateFormat("yy");
		DateFormat mn = new SimpleDateFormat("MMM");
		String yearShort = yr.format(Calendar.getInstance().getTime());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String monShort = mn.format(cal.getTime());
		String selectMonth = monShort + " " + yearShort;
		System.out.println(selectMonth);
		return selectMonth;
	}
}
