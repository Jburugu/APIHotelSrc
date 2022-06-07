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

public class Page_CrewIDIssuesReport_ACES3ACES extends BasePage{

	@FindBy(xpath = "//*[@id='reconcileDashboard:dashboardAccordian']/div")
	private WebElement accountingDashboardDivCollapsable;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	WebElement spinner;
	
	@FindBy(xpath="//*[@id='reconcilecontent']//div[contains(@id,'selectedMonth')]")
	private WebElement MonthSelection;
	
	String monthNameXpath="//*[contains(@id,'selectedMonth_panel')]//ul/li[contains(text(),'";
	String listXpath="')]";
	
	@FindBy(xpath="//*[@id='reconcilecontent']//div[contains(@id,'airline')]")
	private WebElement tenantSelection;
	
	String tenantNameXpath="//*[contains(@id,'airline_panel')]//ul/li[contains(text(),'";
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/..")
	private WebElement searchButton;
	
	@FindBy(xpath="//a[@type='pdf']")
	private WebElement pdfImage;
	
	@FindBy(xpath="//a[@type='pdf']/following-sibling::a")
	private WebElement excelImage;
	
	@FindBy(xpath="//*[contains(@id,'crewIdDataTable_data')]/tr")
	private List<WebElement> crewIdDataTable;
	
	String crewIDTableRowXpath="//*[contains(@id,'crewIdDataTable_data')]/tr[";
	String viewInvoiceLinkXpath="]/td[1]";
	String hotelNameXpath="]/td[3]";
	String crewIDIssuesCount="]/td[5]";
	
	
	
	public EventFiringWebDriver driver=null;
	
	public Page_CrewIDIssuesReport_ACES3ACES(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	private void waitForSpinnerToDisappear() {
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(30));
		wait1.pollingEvery(Duration.ofSeconds(2));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}

	
	public void searchForCrewIDIssues(String month, String tenantName){
		if (accountingDashboardDivCollapsable.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
			clickOn(accountingDashboardDivCollapsable,"ACES 3 Header -> Accounting Dashboard Dropdown Close Button");
			waitForSpinnerToDisappear();
		}
		selectMonth(month);
		selectAirlines(tenantName);
		clickOnSearch();
		waitForSpinnerToDisappear();
		clickOn(pdfImage,"Crew ID Issues Report Page -> Crew ID Issues Report PDF Link");
		waitForElementVisibility(excelImage);
		clickOn(excelImage,"Crew ID Issues Report Page -> Crew ID Issues Report PDF Link");		
	}
	
	private void selectMonth(String month) {
		clickOn(MonthSelection,"Crew ID Issues Report Page -> Select Month Select");
		List<String> months = new ArrayList<String>(Arrays.asList(month.split(",")));
		for (int i = 0; i < months.size(); i++) {
			clickOn(findElementByXpath(monthNameXpath + month + listXpath),"Crew ID Issues Report Page -> Select Month Selected");
		}
	}

		private void selectAirlines(String tenantName) {
			clickOn(tenantSelection,"Crew ID Issues Report Page -> Select Airline Select");
			List<String> tenantNames = new ArrayList<String>(Arrays.asList(tenantName.split(",")));
			for (int i = 0; i < tenantNames.size(); i++) {
				clickOn(findElementByXpath(tenantNameXpath + tenantName + listXpath),"Crew ID Issues Report Page -> Select Airline Selected");
			}
		}

	private void clickOnSearch() {
		clickOn(searchButton,"Crew ID Issues Report Page -> Search Button");
	}
	
	/* Get Crew ID Issues Counts For first 5 supplier from table*/
	public List<List<String>> getSuppliersAndCounts_CrewIDIssues(){
		List<List<String>> suppCrewIssuesList = new ArrayList<List<String>>();
		List<String> hotelarr= new ArrayList<String>();
		List<String> countarr= new ArrayList<String>();
		for(int rowcount=1; rowcount<=5; rowcount++){
			String hotelNames=findElementByXpath(crewIDTableRowXpath+rowcount+hotelNameXpath).getText();
			hotelarr.add(hotelNames);
			System.out.println(hotelarr);
			String issuesCounts=findElementByXpath(crewIDTableRowXpath+rowcount+crewIDIssuesCount).getText();
			countarr.add(issuesCounts);
			System.out.println(countarr);
			
		}
		suppCrewIssuesList.add(hotelarr);
		suppCrewIssuesList.add(countarr);
		return suppCrewIssuesList;

	}
	
}
