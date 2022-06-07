package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page_GTInvoicing_EdriveGTDashboard extends BasePage {

	@FindBy(id = "eDriveGTDashboard")
	private WebElement eDriveGTDashboard;

	@FindBy(name = "billingPeriods")
	private WebElement selectBillingPeriod;

	@FindBy(id = "allInvoicesForBilling")
	private WebElement allStatus;

	@FindBy(xpath = "//input[@value='Submitted']")
	private WebElement newOrSubmittedStatus;

	@FindBy(xpath = "//input[@value='Accepted']")
	private WebElement acceptedStatus;

	@FindBy(xpath = "//input[@value='Rejected']")
	private WebElement rejectedStatus;

	@FindBy(xpath = "//input[@value='Returned']")
	private WebElement returnedStatus;

	@FindBy(xpath = "//input[@value='Archived']")
	private WebElement archivedStatus;

	@FindBy(xpath = "//*[@id='findEDriveGT']//tbody/tr")
	private List<WebElement> edriveGTDashboardTable;

	String edriveGTDashboardTableXpath = "//*[@id='findEDriveGT']//tbody/tr[";
	String supplierNameinTable = "]/td[2]";
	String statusinTable = "]/td[9]";
	String attachmentsLinkInTable = "]/td[11]/a";
	String viewLinkInTable = "]/td[13]";

	String statusXpath1 = "//input[@value='";
	String statusXpath2 = "']";

	@FindBy(xpath = "//input[@value='Refresh Results']")
	private WebElement refreshResults;

	String invoiceMonthInTable = "]/td[3]";
	String airportCodeInTable="]/td[1]";
	
	@FindBy(xpath="//*[@class='Aces_Table EDriveDashboard']/div[contains(text(),'Found')]")
	private WebElement noOfInvoices;
	
	@FindBy(xpath="//h1[contains(text(),'View Invoice')]")
	private WebElement statusInaces3SupplierSite;

	public EventFiringWebDriver driver = null;

	public Page_GTInvoicing_EdriveGTDashboard(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* ACESII-> Accounting -> GT Invoicing -> Edrive Dashboard */
	public void clickOnEdriveGTDashboard() {
		clickOn(eDriveGTDashboard);
	}

	
	public void verifyInvoiceStatusandSupplier(String invoiceMonth,String status, String supplierName) throws InterruptedException, IOException {
		Select selectBox = new Select(selectBillingPeriod);
		selectBox.deselectAll();
		String billingPeriod=invoiceMonth.substring(3, 11).toUpperCase();
		selectBox.selectByValue(billingPeriod);
		if(findElementByXpath(statusXpath1 + "Submitted" + statusXpath2).isSelected())
			clickOn(findElementByXpath(statusXpath1 + "Submitted" + statusXpath2));
		if (!findElementByXpath(statusXpath1 + status + statusXpath2).isSelected())
			clickOn(findElementByXpath(statusXpath1 + status + statusXpath2));
		waitTime(2000);
		if (edriveGTDashboardTable.size() > 0) {
			for (int i = 1; i <= edriveGTDashboardTable.size(); i++) {
				List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
				System.out.println(parentHandle);
				String supplier = findElementByXpath(edriveGTDashboardTableXpath + i + supplierNameinTable).getText();
				String invoicestatus = findElementByXpath(edriveGTDashboardTableXpath + i + statusinTable).getText();
				if (supplier.equalsIgnoreCase(supplierName) && invoicestatus.equalsIgnoreCase(status)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Invoice with" + status + " status found for" + supplier + "in Edrive GT Dashboard Page");		
							
		/*ATA-383 While clicking "View" invoice, verify whether it navigates to ACES3Supplier site through SSO user*/
					clickOn(findElementByXpath(edriveGTDashboardTableXpath + i + viewLinkInTable));
					switchToNewWindow(parentHandle);
					if(statusInaces3SupplierSite.getText().contains(status))
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Invoice with" + status + " status found for" + supplier + 
								"in ACES3Supplier site when naviagted through SSO user");
						
					getDriver().switchTo().window(parentHandle.get(0));
				}
			}
		}

		else
			Assert.fail(
					"Invoice with" + status + " status not found for" + supplierName + "in Edrive GT Dashboard Page");

	}
	
	public void findByBillingPeriodAndStatus(String invoiceMonth, String status) {
		Select selectBox = new Select(selectBillingPeriod);
		selectBox.deselectAll();
		selectBox.selectByValue(invoiceMonth);
		if(findElementByXpath(statusXpath1 + "Submitted" + statusXpath2).isSelected())
			clickOn(findElementByXpath(statusXpath1 + "Submitted" + statusXpath2));
		if (!findElementByXpath(statusXpath1 + status + statusXpath2).isSelected())
			clickOn(findElementByXpath(statusXpath1 + status + statusXpath2));
		waitTime(2000);
	}

	public void verifyInvoiceStatus(String invoiceMonth, String status) {
		if (edriveGTDashboardTable.size() > 0) {
			for (int i = 1; i < edriveGTDashboardTable.size(); i++) {
				String invoiceMonthName = findElementByXpath(edriveGTDashboardTableXpath + i + invoiceMonthInTable).getText();
				String invoicestatus = findElementByXpath(edriveGTDashboardTableXpath + i + statusinTable).getText();
				if (invoiceMonth.equalsIgnoreCase(invoiceMonthName) && invoicestatus.equalsIgnoreCase(status)) 
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Invoice with" + status + " status found for" + invoiceMonth + "in Edrive GT Dashboard Page");
				}
		}
			else 
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Invoice with" + status + " status found for" + invoiceMonth + "in Edrive GT Dashboard Page");
		
		}
	
	public void verifyAllCount(String count){
		if(findElementByXpath(statusXpath1 + "Submitted" + statusXpath2).isSelected())
			clickOn(findElementByXpath(statusXpath1 + "Submitted" + statusXpath2));
		clickOn(allStatus);
		waitTime(2000);
		String invoiceCount= noOfInvoices.getText().substring(0, 3);
		System.out.println(invoiceCount);
		if(invoiceCount.equalsIgnoreCase(count))
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Invoices count for matched All Status in Edrive GT Dashboard Page");
		else ExtentTestManager.getTest().log(LogStatus.PASS,
				"Invoices count not matched for All Status in Edrive GT Dashboard Page, hence all records are not displayed");		
	}

	
	public void downloadAttachment(String supplierName, String invoiceMonth) throws Exception{
		for (int i = 1; i <= edriveGTDashboardTable.size(); i++) {
		String supplier = findElementByXpath(edriveGTDashboardTableXpath + i + supplierNameinTable).getText();
		if (supplier.equalsIgnoreCase(supplierName)) {
		String airportCode = findElementByXpath(edriveGTDashboardTableXpath +i+ airportCodeInTable).getText();
		clickOn(findElementByXpath(edriveGTDashboardTableXpath +i+ attachmentsLinkInTable));
		Thread.sleep(5000);
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\DownloadFileIE.exe");
		Thread.sleep(10000);
		String fileName=supplierName+"_"+airportCode+"_"+invoiceMonth;
		System.out.println(fileName);
		File file =new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/"+fileName+".zip");
		file.renameTo(new File(downloadFilepath + File.separator +file.getName()));
		isFileDownloaded(downloadFilepath, fileName+".zip");

			}
		}
	}
}

