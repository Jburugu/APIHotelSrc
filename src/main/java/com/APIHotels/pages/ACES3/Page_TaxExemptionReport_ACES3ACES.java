package com.APIHotels.pages.ACES3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_TaxExemptionReport_ACES3ACES extends BasePage {

	@FindBy(xpath = "//*[@id='reconcileDashboard:dashboardAccordian']/div")
	private WebElement accountingDashboardDivCollapsable;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	WebElement spinner;

	@FindBy(id = "taxemptionReportForm:selectedMonth")
	private WebElement MonthSelection;

	String monthNameXpath = "//*[@id='taxemptionReportForm:selectedMonth_panel']//ul/li[contains(text(),'";
	String listxpath = "')]";

	@FindBy(id = "taxemptionReportForm:tenant")
	private WebElement tenantSelection;

	String tenantListXpath = "//*[@id='taxemptionReportForm:tenant_panel']//ul/li[contains(text(),'";

	@FindBy(xpath = "//span[contains(text(),'Search')]/..")
	private WebElement searchButton;

	@FindBy(xpath = "//*[@id='taxemptionReportForm:taxExemptDataTable_data']/tr")
	private List<WebElement> taxExemptDataTable;

	String dataTableRowXpath = "//*[@id='taxemptionReportForm:taxExemptDataTable_data']/tr[";
	String viewInvoiceLinkXpath = "]/td[1]/a";
	String exemptionAmount = "]/td[5]";
	String supplierName = "]/td[3]";

	@FindBy(xpath = "//a[contains(text(),'View Tax Exemption Info')]")
	private WebElement viewTaxExemptionInfoLink;

	@FindBy(xpath = "//*[@id='eInvoiceForm']//table[2]//tr[1]/td[2]/fieldset[1]")
	private WebElement hotelName;

	@FindBy(xpath = "//*[contains(@id, 'summaryItemTable_data')]/tr/td[contains(text(),'Exempt Taxes')]/following-sibling::td")
	private WebElement exemptAmountInInvoiceSummary;

	@FindBy(xpath = "//*[contains(@id, 'invoiceTaxTable_data')]/tr")
	private List<WebElement> taxesTable;

	String taxesRow = " //*[contains(@id, 'invoiceTaxTable_data')]/tr[";
	String taxType = "]/td[1]";
	String exemptedQuestion = "]/td[4]";
	String exemeptedAmount = "]/td[5]";

	@FindBy(xpath = "//*[contains(@id, 'invoiceTaxTable_foot')]/tr/td[5]")
	private WebElement totalExemptedAmount;
	
	@FindBy(id="taxExemptionTable")
	private WebElement taxExemptionCalculationTable;
	
	String totalTaxExemptionXpath1="//*[@id='taxExemptionTable_data']//td[contains(text(),'";
	String totalTaxExemptionXpath2="')]";
	
	@FindBy(xpath="//a[@type='pdf']")
	private WebElement pdfImage;

	@FindBy(xpath="//a[@type='pdf']/following-sibling::a")
	private WebElement excelImage;
	
	public EventFiringWebDriver driver = null;

	public Page_TaxExemptionReport_ACES3ACES(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyTaxExemptionReport(String month, String tenantName, String supplier, String exemptAmount, String taxTypes,
			String exempted) throws Exception {
		if (accountingDashboardDivCollapsable.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
			clickOn(accountingDashboardDivCollapsable,"ACES 3 Header -> Accounting Dashboard Dropdown Close Button");
			waitForSpinnerToDisappear();
		}

		selectMonth(month);
		selectAirlines(tenantName);
		clickOnSearch();
		waitForSpinnerToDisappear();
		clickOn(pdfImage,"Tax Exemption Report Page -> Tax Exemption PDF Link");
		waitForElementVisibility(excelImage);
		clickOn(excelImage,"Tax Exemption Report Page -> Tax Exemption Excel Link");
		waitTime(2000);
		viewInvoiceOfTaxExemptionAmount(exemptAmount, supplier);
		switchToWindow();
		verifyExemptAmountInInvoiceSummary(supplier, exemptAmount);
		verifyTaxesTable(taxTypes, exempted, exemptAmount);
		verifyViewTaxExemptionInfo(exemptAmount);
	}

	private void selectMonth(String month) {
		clickOn(MonthSelection, "Tax Exemption Report Page -> Select Month Select");
		List<String> months = new ArrayList<String>(Arrays.asList(month.split(",")));
		for (int i = 0; i < months.size(); i++) {
			clickOn(findElementByXpath(monthNameXpath + month + listxpath),"Tax Exemption Report Page -> Select Month Selected");
		}
	}

	private void selectAirlines(String tenantName) {
		clickOn(tenantSelection,"Tax Exemption Report Page -> Select Airline Select");
		List<String> tenantNames = new ArrayList<String>(Arrays.asList(tenantName.split(",")));
		for (int i = 0; i < tenantNames.size(); i++) {
			clickOn(findElementByXpath(tenantListXpath + tenantName + listxpath), "Tax Exemption Report Page -> Select Airline Select");
		}
	}

	private void clickOnSearch() {
		clickOn(searchButton,"Tax Exemption Report Page -> Search Button");
	}

	private void viewInvoiceOfTaxExemptionAmount(String exemptAmount, String supplier) {
		List<WebElement> viewInvoiceRows = taxExemptDataTable;
		for (int rowCount = 1; rowCount < viewInvoiceRows.size(); rowCount++) {
			String supplierText= findElementByXpath(dataTableRowXpath + rowCount + supplierName).getText();
			System.out.println(supplier);
			String exemptionAmountText = findElementByXpath(dataTableRowXpath + rowCount + exemptionAmount).getText();
			System.out.println(exemptionAmountText);
			if (exemptionAmountText.contains(exemptAmount)&&supplierText.equalsIgnoreCase(supplier)) {
				clickOn(findElementByXpath(dataTableRowXpath + rowCount + viewInvoiceLinkXpath),"Tax Exemption Report Page -> View Invoice Link");
				break;
			}
		}
	}

	private void verifyExemptAmountInInvoiceSummary(String supplier, String exemptAmount) {
		if (hotelName.getText().contains(supplier)) {
			String exemptAmtInInvoiceSummary = exemptAmountInInvoiceSummary.getText();
			if (exemptAmtInInvoiceSummary.equalsIgnoreCase(exemptAmount)) {
				System.out.println("Exempt Amount in Invoice Summary and expected exempt Amount are equal");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Exempt Amount in Invoice Summary and expected exempt Amount are equal");
			} else
				Assert.fail("Exempt Amount In InvoiceSummary" + exemptAmtInInvoiceSummary
						+ "is different than exempt Amount" + exemptAmount);
		}
	}

	private void verifyTaxesTable(String taxTypes, String exempted, String exemptAmount) {
		List<String> taxTypeVal = new ArrayList<String>(Arrays.asList(taxTypes.split(",")));
		List<String> exemptedVal = new ArrayList<String>(Arrays.asList(exempted.split(",")));
		if (taxesTable.size() > 0) {
			for (int rowCount = 1; rowCount <= taxTypeVal.size(); rowCount++) {
				String taxTypeValue = findElementByXpath(taxesRow + rowCount + taxType).getText();
				String exemptedValue = findElementByXpath(taxesRow + rowCount + exemptedQuestion).getText();
				System.out.println();
				if (taxTypeValue.equalsIgnoreCase(taxTypeVal.get(rowCount - 1))&&exemptedValue.equalsIgnoreCase(exemptedVal.get(rowCount - 1))==true) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Exempted Amount in Taxes is equal to expected Exempted Amount");
				}
			}
			if (totalExemptedAmount.getText().equalsIgnoreCase(exemptAmount)) {
				System.out.println("Exempted Amount in Total is equal to expected Exempted Amount");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Exempted Amount in Total is equal to expected Exempted Amount");
			}

		}

		else
			Assert.fail("Taxes Table Not Found");
	}

	private void verifyViewTaxExemptionInfo(String exemptAmount) {
		waitForElementVisibility(viewTaxExemptionInfoLink);
		clickOn(viewTaxExemptionInfoLink, " Tax Invoice(Read-Only) Page - > View Tax Exemption Info Link");
		switchToWindow();
		waitForElementVisibility(taxExemptionCalculationTable);
		if(findElementByXpath(totalTaxExemptionXpath1+exemptAmount+totalTaxExemptionXpath2).isDisplayed()){
			System.out.println("Exempted Amount in Total is equal to expected Exempted Amount");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Exempted Amount in Total is equal to expected Exempted Amount");
		}
	}

	private void waitForSpinnerToDisappear() {
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(30));
		wait1.pollingEvery(Duration.ofSeconds(2));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}

}
