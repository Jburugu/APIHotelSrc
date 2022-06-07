package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_SignInReport extends BasePage{
	
	public EventFiringWebDriver driver=null;
	
	@FindBy(css = "#checkInReport>a")
	public WebElement signInReport;

	@FindBy(id = "locCd")
	public WebElement citySigninReport;
	
	@FindBy(xpath = "//input[@value='Get Suppliers']")
	public WebElement getSuppliersBtn;
	
	@FindBy(css = "select[name='billingPeriodSelection']")
	public WebElement billingPeriodSelection;

	@FindBy(xpath = "//input[@value='Generate']")
	public WebElement generateBtn;
	
	@FindBy(xpath= "//select[@id='supplierId']")
	public WebElement selectSuppliers;
	
	@FindBy(id = "content")
	private WebElement signReportTable;

	@FindBy(xpath="//*[@id='content']//tr[2]/td[10]/a")
	private WebElement viewDetailsLink;
	
	@FindBy(xpath="//input[@value='Close']")
	private WebElement closeButton;
	
	@FindBy (xpath ="//form[@name='checkInReportForm']/following-sibling::div/table")
	public List<WebElement> resultsTable;
	
	@FindBy (xpath ="//form[@name='checkInReportForm']/table")
	public List<WebElement> resultsDetails;
	
	public Page_SignInReport(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void generateSignInReportReg(String city, String supplierName, String billingPeriodValueSignInReport)
			throws Exception {
		clickOn(signInReport, "Accounting Menu -> Sign In Report Link");
		typeInText(citySigninReport, city, "Sign-In Sheet -> City textbox");
		clickOn(getSuppliersBtn,"Sign-In Sheet -> Get Supplier button");
		Thread.sleep(4000);
		selectByVisibleText(selectSuppliers, supplierName, "Supplier dropdown");
		selectByVisibleText(billingPeriodSelection, billingPeriodValueSignInReport, "Billing Period dropdown");
		waitForElementVisibility(generateBtn);
		clickOn(generateBtn);
		if(resultsDetails.size() >0 && resultsTable.size()>0) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Results Details are displayed on clicking on Search button");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Results Details are not displayed on clicking on Search button");
		}
		

	}

}
