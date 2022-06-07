package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Reports extends BasePage{

	@FindBy(css = "#reportPlanning > a")
	private WebElement planningReportsLink;
	
	@FindBy(id = "dailyBidPeriod")
	private WebElement bidMonth;
	
	@FindBy(id = "dailyIncludeStayover")
	private WebElement dailyIncludeStayoverIndicator;
	
	@FindBy(xpath = "//*[@name = 'reportFlag' and @value = 'Generate Report']")
	private WebElement generateReportBtn;
	
	@FindBy(xpath = "//*[@id='inc2']/fieldset/table/tbody/tr[2]/td[2]/input")
	private WebElement watermark_GenerateReportBtn;
	
	@FindBy(xpath = "//*[@id='inc3']/fieldset/table/tbody/tr[2]/td[2]/input")
	private WebElement monthlyActivity_GenerateReportBtn;
	
	@FindBy(id = "row1")
	private WebElement dailyUsageReportSectionHeader;
	
	@FindBy(id = "row2")
	private WebElement lowWatermarkReportSectionHeader;
	
	@FindBy(id = "row3")
	private WebElement monthlyActivityReportSectionHeader;
	
	@FindBy(id = "waterBidPeriod")
	private WebElement watermark_BidPeriod;
	
	@FindBy(id = "monthlyBidPeriod")
	private WebElement monthlyActivity_BidPeriod;
	
	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_Reports(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickPlanningReportsLink() throws Exception{
		clickOn(planningReportsLink);
	}
	
	public void generateDailyUsageReport() throws Exception{
		clickOn(dailyUsageReportSectionHeader);
		waitForElementVisibility(bidMonth);
		//waitForElementVisibility(dailyIncludeStayoverIndicator);
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(generateReportBtn);
		waitForPageLoad(driver);
		switchToNewWindow(currentWindowHandle);
		close();
		switchToWindow(currentWindowHandle);	
	}
	
	public void generateLowWatermarkReport() throws Exception{
		clickOn(lowWatermarkReportSectionHeader);
		waitForElementVisibility(watermark_BidPeriod);
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(watermark_GenerateReportBtn);
		waitForPageLoad(driver);
		switchToNewWindow(currentWindowHandle);
		close();
		switchToWindow(currentWindowHandle);	
	}
	
	public void generateMonthlyActivityReport() throws Exception{
		clickOn(monthlyActivityReportSectionHeader);
		waitForElementVisibility(monthlyActivity_BidPeriod);
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(monthlyActivity_GenerateReportBtn);
		waitForPageLoad(driver);
		switchToNewWindow(currentWindowHandle);
		close();
		switchToWindow(currentWindowHandle);
	}
	
	
}
