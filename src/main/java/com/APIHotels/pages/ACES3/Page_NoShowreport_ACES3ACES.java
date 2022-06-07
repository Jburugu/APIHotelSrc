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

import com.APIHotels.framework.BasePage;

public class Page_NoShowreport_ACES3ACES extends BasePage {


	@FindBy(xpath = "//*[@id='reconcileDashboard:dashboardAccordian']/div")
	private WebElement accountingDashboardDivCollapsable;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath="//*[@id='reconcilecontent']//div[contains(@id,'selectedMonth')]")
	private WebElement MonthSelection;
	
	String monthNameXpath="//*[contains(@id,'selectedMonth_panel')]//ul/li[contains(text(),'";
	String listXpath="')]";
	
	@FindBy(xpath="//*[@id='reconcilecontent']//div[contains(@id,'tenant')]")
	private WebElement tenantSelection;
	
	String tenantNameXpath="//*[contains(@id,'tenant_panel')]//ul/li[contains(text(),'";
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/..")
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[contains(@id,'noShowReportDataTable_data')]/tr")
	private List<WebElement> noShowReportDataTable;
	
	String dataTableRowXpath = "//*[contains(@id,'noShowReportDataTable_data')]/tr[";
	String viewInvoiceLinkXpath = "]/td[1]/a";
	String billableNoShow="]/td[5]";
	String billableNoShowNightsSaved="]/td[6]";
	String NonBillableNoShow="]/td[7]";
	String nonBillableNoShowNightsSaved="]/td[8]";
	
	String supplierName = "]/td[3]";
	
	@FindBy(xpath="//a[contains(text(),'Download No Bill Extract')]")
	private WebElement downloadNoBill;
	
	@FindBy(xpath="//a[@type='pdf']")
	private WebElement pdfImage;

	@FindBy(xpath="//a[@type='pdf']/following-sibling::a")
	private WebElement excelImage;
	
	@FindBy(id="dailyRoomDetail")
	private WebElement auditNotestable;
	
	@FindBy(xpath="//*[@id='dailyRoomDetailTable_data']//td[contains(text(),'Billable No-shows')]/following-sibling::td")
	private List<WebElement> tdCountinAuditNotesForBillableNoShows;
	
	String countsInAuditNotesForBillableNoShowsXpath1="//*[@id='dailyRoomDetailTable_data']//td[contains(text(),'Billable No-shows')]/following-sibling::td[";
	String countsInAuditNotesForBillableNoShowsXpath2="]";
	
	public EventFiringWebDriver driver = null;

	public Page_NoShowreport_ACES3ACES(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyNoShowReport(String month, String tenantName, String supplier, String billableNoShowsCount,
			String billableNoshowsNightsCount, String nonBillableNoshowsCount, String nonBillableNoshowsNightsCount)
			throws Exception {
		if (accountingDashboardDivCollapsable.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
			clickOn(accountingDashboardDivCollapsable,"ACES 3 Header -> Accounting Dashboard Dropdown Close Button");
			waitForSpinnerToDisappear();
		}
		
		selectMonth(month);
		selectAirlines(tenantName);
		clickOnSearch();
		waitForSpinnerToDisappear();
		clickOn(pdfImage,"No Show Report Page -> No Show Report PDF Link");
		waitForElementVisibility(excelImage);
		clickOn(excelImage,"No Show Report Page -> No Show Report Excel Link");		
		waitTime(2000);
		viewInvoiceOfBillableNoShow(billableNoShowsCount, supplier, billableNoshowsNightsCount, nonBillableNoshowsCount, nonBillableNoshowsNightsCount);
		clickOn(downloadNoBill,"No Show Report Page -> Download No Bill Link");
		verifyBillableNoShowsInAuditNotes(billableNoShowsCount);
	}
	
	private void selectMonth(String month) {
		clickOn(MonthSelection,"No-Show Report Page -> Select Month Select");
		List<String> months = new ArrayList<String>(Arrays.asList(month.split(",")));
		for (int i = 0; i < months.size(); i++) {
			clickOn(findElementByXpath(monthNameXpath + month + listXpath),"No Show Report Page -> Select Month Selected");
		}
	}

	private void selectAirlines(String tenantName) {
		clickOn(tenantSelection,"No-Show Report Page -> Select Airline Select");
		List<String> tenantNames = new ArrayList<String>(Arrays.asList(tenantName.split(",")));
		for (int i = 0; i < tenantNames.size(); i++) {
			clickOn(findElementByXpath(tenantNameXpath + tenantName + listXpath),"No Show Report Page -> Select Month Selected");
		}
	}

	private void clickOnSearch() {
		clickOn(searchButton,"No Show Report Page -> Search Button");
	}
	
	private void viewInvoiceOfBillableNoShow(String billableNoShowsCount, String supplier, String billableNoshowsNightsCount, String nonBillableNoshowsCount, String nonBillableNoshowsNightsCount) {
		List<WebElement> viewInvoiceRows = noShowReportDataTable; 
		for (int rowCount = 1; rowCount <= viewInvoiceRows.size(); rowCount++) {
			String supplierText= findElementByXpath(dataTableRowXpath + rowCount + supplierName).getText();
			System.out.println(supplier);
			String billableNoshows = findElementByXpath(dataTableRowXpath + rowCount + billableNoShow).getText();
			System.out.println(billableNoshows);
			String billableNoshowsNights = findElementByXpath(dataTableRowXpath + rowCount + billableNoShowNightsSaved).getText();
			String nonBillableNoshows = findElementByXpath(dataTableRowXpath + rowCount + NonBillableNoShow).getText();
			String nonBillableNoshowsNights = findElementByXpath(dataTableRowXpath + rowCount + nonBillableNoShowNightsSaved).getText();
			if (billableNoshows.contains(billableNoShowsCount)
					&& billableNoshowsNights.contains(billableNoshowsNightsCount)
					&& nonBillableNoshows.contains(nonBillableNoshowsCount)
					&& nonBillableNoshowsNights.contains(nonBillableNoshowsNightsCount)
					&& supplierText.equalsIgnoreCase(supplier)) {
				clickOn(findElementByXpath(dataTableRowXpath + rowCount + viewInvoiceLinkXpath),"No Show Report Page -> View Invoice Link");
				break;
			}
		}
	}
	
	private void verifyBillableNoShowsInAuditNotes(String billableNoShowsCount){
		switchToWindow();
		int sum=0; String numbers = null;
		waitForElementVisibility(auditNotestable);
		List<WebElement> noOfBillableNoShows=tdCountinAuditNotesForBillableNoShows;
		int noOfBillableNoShowsCount=noOfBillableNoShows.size()-2;
		int total=noOfBillableNoShows.size()-1;
		String totalCount =findElementByXpath(countsInAuditNotesForBillableNoShowsXpath1+total+countsInAuditNotesForBillableNoShowsXpath2).getText();
		for (int rowCount = 1; rowCount < noOfBillableNoShowsCount; rowCount++) {
			System.out.println();
			String numbersTexts=findElementByXpath(countsInAuditNotesForBillableNoShowsXpath1+rowCount+countsInAuditNotesForBillableNoShowsXpath2).getText();
			System.out.println(numbersTexts);
			if(numbersTexts.contains("-"))
				numbers= numbersTexts.replace("-", "0");
			else
				numbers= numbersTexts;
			sum=sum+Integer.parseInt(numbers);
			System.out.println(sum);
		}
		
		if(sum==Integer.parseInt(billableNoShowsCount)&&totalCount.equalsIgnoreCase(billableNoShowsCount)){
			System.out.println("sum and total are same");
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
