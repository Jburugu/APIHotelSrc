package com.APIHotels.pages.ACES3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_InvoiceDashboard_Aces3Supplier extends BasePage{
	
	@FindBy(id="EDriveSupplierDashboardForm:tenantSelector")
	private WebElement tenantSelector;
	
	String tenantXpath="//*[@id='EDriveSupplierDashboardForm:tenantSelector_panel']";
	String listXpath="//ul/li/label[contains(text(),'";
	
	
	@FindBy(id="EDriveSupplierDashboardForm:invoiceSelect_label")
	private WebElement invoiceSelect;

	String invoiceXpath="//*[@id='EDriveSupplierDashboardForm:invoiceSelect_panel']";
	
	@FindBy(id="EDriveSupplierDashboardForm:statusSelect")
	private WebElement statusSelect;
	
	String statusXpath="//*[@id='EDriveSupplierDashboardForm:statusSelect_panel']";
	
	String checkboxXpath="')]/../div";
	
	@FindBy(xpath="//*[@id='EDriveSupplierDashboardForm:statusSelect_panel']//div[1]/div/div/span")
	private WebElement checkAllCheckbox;

	@FindBy(id="EDriveSupplierDashboardForm:find")
	private WebElement findButton;
	
	String dashboardTable = "//*[@id='EDriveSupplierDashboardForm:dashboardTable_data']/tr";
	
	String dashboardDataXpath1= "//*[@id='EDriveSupplierDashboardForm:dashboardTable_data']/tr[";
	String dashboardDataXpath2="]";

	String viewLink="]/td[7]/a";
	
	@FindBy(id="viewEDriveInv:cancelButton")
	private WebElement cancelButton;
	
	String listClose="//a/span";
	
	String checkboxSelection="//span";
	
	@FindBy(xpath="//div[contains(@id,'start')]")
	private WebElement spinner;
	
	String resubmitId1="EDriveSupplierDashboardForm:dashboardTable:";
	String resubmitId2=":resubmitEDriveHotel";
	String resubmitIdGT=":resubmitEDriveGround";
	
	@FindBy(xpath="//*[@id='uploadEDriveInv']/div[1]//span[2]")
	private WebElement CommentWhileReturned;
	
	@FindBy(xpath="//div[@id='uploadEDriveInv:fileUpload']/div/span")
	private WebElement browseButton;
	
	@FindBy(xpath="//button[contains(@class,'fileupload-upload')]")
	private WebElement uploadButton;
	
	@FindBy(xpath="//*[contains(@id,'uploadEDriveInv:attachmentsTable')]/img")
	private WebElement deleteImage;
	
	@FindBy(id="uploadEDriveInv:comment")
	private WebElement comment;
	
	@FindBy(id="uploadEDriveInv:submitButton")
	private WebElement submitButton;
	
	@FindBy(xpath="//*[contains(@id,'uploadEDriveInv:')]//li/span")
	private WebElement savedMessage;
	
	@FindBy(xpath="//*[contains(@id,'uploadGTriveInv:')]//li/span")
	private WebElement gtSavedMessage;
	
	public EventFiringWebDriver driver= null;
	public Page_InvoiceDashboard_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	private void selectTenant(String tenantName){
		clickOn(tenantSelector, "InvoiceDashboardPage -> Airlines drop down");
		List<String> tenants= new ArrayList<String>(Arrays.asList(tenantName.split(",")));
		for(int i=0; i<tenants.size(); i++){
			clickOn(findElementByXpath(tenantXpath+listXpath+tenantName+checkboxXpath), "InvoiceDashboardPage -> Airlines drop down "+tenantName+" option");
		}
		clickOn(findElementByXpath(tenantXpath+listClose), "InvoiceDashboardPage -> Airlines List close icon");
		
	}
	
	private void selectInvoiceMonth(String invoiceMonth) throws InterruptedException{
		Thread.sleep(1000);
		clickOn(invoiceSelect, "InvoiceDashboardPage -> Invoice Months list");
		List<String> months= new ArrayList<String>(Arrays.asList(invoiceMonth.split(",")));
		for(int i=0; i<months.size();i++){
		clickOn(findElementByXpath(invoiceXpath+listXpath+invoiceMonth+checkboxXpath), "InvoiceDashboardPage -> Invoice Months drop down "+invoiceMonth+" option");
		}
		
		clickOn(findElementByXpath(invoiceXpath+listClose), "InvoiceDashboardPage -> Invoice Months dropdown close icon");
	}
	
	private void selectStatus(String status) throws InterruptedException {
		Thread.sleep(1000);
		clickOn(statusSelect, "InvoiceDashboardPage -> Statuses drop down");
		clickOn(checkAllCheckbox, "InvoiceDashboardPage -> Statuses drop down -> Select all check box");
		clickOn(checkAllCheckbox, "InvoiceDashboardPage -> Statuses drop down -> Select all check box");
		List<String> statuses = new ArrayList<String>(Arrays.asList(status.split(",")));
		for (int i = 0; i < statuses.size(); i++) {
			//if (!findElementByXpath(statusXpath + listXpath + status + checkboxXpath + checkboxSelection).getAttribute("class").contains("check")) {
				clickOn(findElementByXpath(statusXpath + listXpath + status + checkboxXpath), "InvoiceDashboardPage -> Statuses drop down -> "+status+" check boxs");
			}
		clickOn(findElementByXpath(statusXpath + listClose), "InvoiceDashboardPage -> Statuses drop down close icon");
	}
	
	private void clickFind() throws InterruptedException{
		Thread.sleep(1000);
		clickOn(findButton, "InvoiceDashboardPage -> Find btn");
		waitForElementToDisappear(spinner);
	}
	
	public void selectTenantMonthAndStatus(String tenantName, String invoiceMonth, String status) throws InterruptedException{
		selectTenant(tenantName);
		selectInvoiceMonth(invoiceMonth);
		selectStatus(status);
		clickFind();
	}
	
	public void clickView(String tenantName, String invoiceMonth, String status) {
		boolean recordFound = false;
		List<WebElement> rowsInDashboard = findElementsByXpath(dashboardTable);
		for (int rowcount = 1; rowcount <= rowsInDashboard.size(); rowcount++) {
			String data = findElementByXpath(dashboardDataXpath1 + rowcount + dashboardDataXpath2).getText();
			System.out.println(data);
			if (data.contains(tenantName) && data.contains(invoiceMonth.replace("-", "")) && data.contains(status)) {
				clickOn(findElementByXpath(dashboardDataXpath1 + rowcount + viewLink), "InvoiceDashboardPage -> View Link on Row: "+rowcount);
				recordFound = true;
				break;
			}
			if (!recordFound) {
				Assert.fail("Invoice not Found to click on View link");
			}
		}
	}
	
	public void resubmitHotelInvoice(String tenantName, String invoiceMonth, String status, String returnComment, String resubmitComment) throws Exception{
		boolean recordFound = false;
		List<WebElement> rowsInDashboard = findElementsByXpath(dashboardTable);
		for (int rowcount = 1; rowcount <= rowsInDashboard.size(); rowcount++) {
			String data = findElementByXpath(dashboardDataXpath1 + rowcount + dashboardDataXpath2).getText();
			System.out.println(data);
			if (data.contains(tenantName) && data.contains(invoiceMonth.replace("-", "")) && data.contains(status)) {
				clickOn(findElementById(resubmitId1+(rowcount-1)+resubmitId2), "InvoiceDashboardPage -> Resubmit on Row: "+rowcount);
				String commentOnReturnedInvoice=CommentWhileReturned.getText();
				if(commentOnReturnedInvoice.equalsIgnoreCase(returnComment)){
					clickOn(browseButton, "InvoiceDashboardPage -> Browse btn");
					Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile.exe"+" "+System.getProperty("user.dir")+"\\lib\\test.docx");
					Thread.sleep(3000);
					clickOn(uploadButton, "InvoiceDashboardPage -> Upload btn");
					waitForElementVisibility(deleteImage);
					typeInText(comment, resubmitComment, "InvoiceDashboardPage -> Comment");
					clickOn(submitButton, "InvoiceDashboardPage -> Submit btn");
					if(savedMessage.getText().equalsIgnoreCase("Invoice Resubmitted Successfully"))
						System.out.println("Invoice returned successfully");
						else Assert.fail("Unable to accept invoice for " + invoiceMonth + "due to error");
				}
				recordFound = true;
				break;
			}
			if (!recordFound) {
				Assert.fail("Invoice not Found to click on Resubmit link");
			}
		}
	}
	
	public void resubmitGTInvoice(String tenantName, String invoiceMonth, String status, String returnComment, String resubmitComment) throws Exception{
		boolean recordFound = false;
		List<WebElement> rowsInDashboard = findElementsByXpath(dashboardTable);
		for (int rowcount = 1; rowcount <= rowsInDashboard.size(); rowcount++) {
			String data = findElementByXpath(dashboardDataXpath1 + rowcount + dashboardDataXpath2).getText();
			System.out.println(data);
			if (data.contains(tenantName) && data.contains(invoiceMonth.replace("-", "")) && data.contains(status)) {
				clickOn(findElementById(resubmitId1+(rowcount-1)+resubmitIdGT), "InvoiceDashboardPage -> Resubmit on Row: "+rowcount);
				String commentOnReturnedInvoice=CommentWhileReturned.getText();
				if(commentOnReturnedInvoice.equalsIgnoreCase(returnComment)){
					clickOn(browseButton, "InvoiceDashboardPage -> Browse btn");
					Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile.exe"+" "+System.getProperty("user.dir")+"\\lib\\test.docx");
					Thread.sleep(3000);
					clickOn(uploadButton, "InvoiceDashboardPage -> Upload btn");
					waitForElementVisibility(deleteImage);
					typeInText(comment, resubmitComment, "InvoiceDashboardPage -> Comments");
					clickOn(submitButton, "InvoiceDashboardPage -> Submit btn");
					if(gtSavedMessage.getText().equalsIgnoreCase("Invoice Resubmitted Successfully"))
						System.out.println("Invoice returned successfully");
						else Assert.fail("Unable to accept invoice for " + invoiceMonth + "due to error");
				}
				recordFound = true;
				break;
			}
			if (!recordFound) {
				Assert.fail("Invoice not Found to click on Resubmit link");
			}
		}
	}
	
	

}
