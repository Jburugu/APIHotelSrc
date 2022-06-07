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

public class Page_EDriveDashboard_ACES3ACES extends BasePage {
	
	@FindBy(id="EDriveDashboardForm:tenantSelector")
	private WebElement tenantSelection;
	
	String tenantXpath="//*[@id='EDriveDashboardForm:tenantSelector_panel']";
	String listXpath="//li/label[contains(text(),'";
	String checkboxXpath="')]/../div";
	
	@FindBy(id="EDriveDashboardForm:invoiceSelect")
	private WebElement invoiceSelection;
	
	String invoiceXpath="//*[@id='EDriveDashboardForm:invoiceSelect_panel']";
	
	@FindBy(id="EDriveDashboardForm:statusSelect")
	private WebElement statuses;
	
	String statusXpath="//*[@id='EDriveDashboardForm:statusSelect_panel']";
	
	@FindBy(id="EDriveDashboardForm:supplierTypeSelect")
	private WebElement supplierType;
	
	String supplierTypeXpath="//*[@id='EDriveDashboardForm:supplierTypeSelect_panel']"; 
	
	@FindBy(id="EDriveDashboardForm:find")
	private WebElement findButton;
	
	String dashboardTableXpath = "//*[@id='EDriveDashboardForm:dashboardTable_data']/tr";
	@FindBy(id="EDriveDashboardForm:dashboardTable")
	private WebElement  dashboardTable;
	
	String dashboardDataXpath1= "//*[@id='EDriveDashboardForm:dashboardTable_data']/tr[";
	String dashboardDataXpath2="]";

	String viewLink="]/td[17]";
	
	String invoiceStatus="]/td[13]";
	
	String listClose="//a/span";
	
	@FindBy(id="viewEDriveHT:cancelButton")
	private WebElement cancelButton;
	
	@FindBy(id="viewEDriveHT:acceptButton")
	private WebElement acceptButton;
	
	@FindBy(id="viewEDriveHT:returnButton")
	private WebElement returnButton;
	
	@FindBy(id="viewEDriveHT:rejectButton")
	private WebElement rejectButton;
	
	@FindBy(id="viewEDriveHT:archiveButton")
	private WebElement archiveButton;
	
	@FindBy(id="viewEDriveHT:confirmDialogId")
	private WebElement archiveDialog;
	
	@FindBy(xpath="//*[contains(text(),'Yes')]")
	private WebElement archiveYes;

	@FindBy(xpath="//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(id="viewEDriveHT:comment")
	private WebElement comment;
	
	@FindBy(xpath="//*[contains(@id,'viewEDriveHT')]//li/span")
	private WebElement savedMessage;
	
	@FindBy(xpath="//span[contains(text(),'Cancel')]")
	private WebElement cancelText;
	
	@FindBy(id="viewEDriveGT:acceptButton")
	private WebElement gtAcceptButton;
	
	@FindBy(id="viewEDriveGT:returnButton")
	private WebElement gtReturnButton;
	
	@FindBy(id="viewEDriveGT:rejectButton")
	private WebElement gtRejectButton;
	
	@FindBy(id="viewEDriveGT:cancelButton")
	private WebElement gtCancelButton;
	
	@FindBy(id="viewEDriveGT:archiveButton")
	private WebElement gtArchiveButton;
	
	@FindBy(id="viewEDriveGT:confirmDialogId")
	private WebElement gtArchiveDialog;
	
	@FindBy(id="viewEDriveGT:comment")
	private WebElement gtComment;
	
	@FindBy(xpath="//*[contains(@id,'viewEDriveGT')]//li/span")
	private WebElement gtsaveMessage;
	
	@FindBy(xpath="//*[@id='EDriveDashboardForm:statusSelect_panel']//div[1]/div/div/span")
	private WebElement checkAllCheckbox;
	
	@FindBy (linkText ="EDrive Dashboard")
	private WebElement link_EdriveDashboard;
	
	public EventFiringWebDriver driver= null;
	public Page_EDriveDashboard_ACES3ACES(EventFiringWebDriver driver ){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	private void selectTenant(String tenantName) throws InterruptedException{
		clickOn(tenantSelection, "E Drive Dashboard -> Tenant Select");
		List<String> tenants= new ArrayList<String>(Arrays.asList(tenantName.split(",")));
		for(int i=0; i<tenants.size(); i++){
		clickOn(findElementByXpath(tenantXpath+listXpath+tenantName+checkboxXpath),"E Drive Dashboard -> Tenant Selected");
		}
		clickOn(findElementByXpath(tenantXpath+listClose),"E Drive Dashboard -> Tenant List close Button");
	}
	
	private void selectInvoiceMonth(String invoiceMonth) throws InterruptedException{
		Thread.sleep(1000);
		clickOn(invoiceSelection, "E Drive Dashboard -> Invoice Select");
		List<String> months= new ArrayList<String>(Arrays.asList(invoiceMonth.split(",")));
		for(int i=0; i<months.size();i++){
		clickOn(findElementByXpath(invoiceXpath+listXpath+invoiceMonth+checkboxXpath), "E Drive Dashboard -> Invoice Selected");
		}
		clickOn(findElementByXpath(invoiceXpath+listClose), "E Drive Dashboard -> Invoice list Close Button");
	}
	
	private void selectStatus(String status) throws InterruptedException{
		Thread.sleep(1000);
		clickOn(statuses, "E Drive Dashboard -> Status Select");
		clickOn(checkAllCheckbox, "E Drive Dashboard -> All Checkbox");
		clickOn(checkAllCheckbox, "E Drive Dashboard -> All Checkbox");
		List<String> statuses=new ArrayList<String>(Arrays.asList(status.split(",")));
		for(int i=0; i<statuses.size();i++){
			clickOn(findElementByXpath(statusXpath+listXpath+status+checkboxXpath));
		}
		clickOn(findElementByXpath(statusXpath+listClose), "E Drive Dashboard -> Status List Close Button");
	}
	
	private void selectSupplierType(String supplier) throws InterruptedException{
		Thread.sleep(1000);
		clickOn(supplierType, "E Drive Dashboard -> Supplier Type Select");
		List<String> statuses=new ArrayList<String>(Arrays.asList(supplier.split(",")));
		for(int i=0; i<statuses.size();i++){
			clickOn(findElementByXpath(supplierTypeXpath+listXpath+supplier+checkboxXpath), "E Drive Dashboard -> Supplier Type Selected");
		}
		clickOn(findElementByXpath(supplierTypeXpath+listClose), "E Drive Dashboard -> Supplier Type Select List Close Button");
	}
	
	
	private void clickFind() throws InterruptedException{
		Thread.sleep(1000);
		clickOn(findButton, "E Drive Dashboard -> Find Button");
		waitForElementToDisappear(spinner);
		
	}
	
	public void selectTenantMonthAndStatus(String tenantName, String invoiceMonth, String status, String supplier) throws InterruptedException{
		selectTenant(tenantName);
		selectInvoiceMonth(invoiceMonth);
		selectStatus(status);
		selectSupplierType(supplier);
		clickFind();
	}
	
	public void clickViewAces3(String tenantName, String invoiceMonth, String status, String supplier){
		waitForElementVisibility(dashboardTable);
		List<WebElement> rowsInDashboard= findElementsByXpath(dashboardTableXpath);
		for(int rowcount=1; rowcount<=rowsInDashboard.size(); rowcount++){
			String data=findElementByXpath(dashboardDataXpath1+rowcount+dashboardDataXpath2).getText();
			if(data.contains(tenantName)&&data.contains(invoiceMonth.replace("-", ""))&&data.contains(status)&&data.contains(supplier)){
				clickOn(findElementByXpath(dashboardDataXpath1+rowcount+viewLink), "E Drive Dashboard -> View Link");
				//waitForElementVisibility(cancelText);
				break;
			}		
		}
	}
	
	public void acceptInvoiceofHotel(String commentToAccept, String invoiceMonth){
		typeInText(comment, commentToAccept, "E Drive Dashboard -> Accept Invoice Hotel Comments TextArea");
		clickOn(acceptButton, "E Drive Dashboard -> Hotel Accept Button");
		if(savedMessage.getText().equalsIgnoreCase("Invoice accepted successfully"))
			System.out.println("Hotel Invoice accepted successfully");
			else Assert.fail("Unable to accept Hotel invoice for " + invoiceMonth + "due to error");
	}
	
	public void acceptInvoiceOfGT(String gtCommentToAccept, String gtinvoiceMonth){
		typeInText(gtComment, gtCommentToAccept, "E Drive Dashboard -> Accept Invoice GT Comments TextArea");
		clickOn(gtAcceptButton, "E Drive Dashboard -> GT Accept Button");
		if(gtsaveMessage.getText().equalsIgnoreCase("Invoice accepted successfully"))
			System.out.println("GT Invoice accepted successfully");
			else Assert.fail("Unable to accept GT invoice for " + gtinvoiceMonth + "due to error");
	}
	
	public void archiveAcceptedHotelInvoice(String commentToArchieve, String invoiceMonth) throws Exception{
		typeInText(comment, commentToArchieve, "E Drive Dashboard -> Archive Accepted Hotel TextArea");
		clickOn(archiveButton, "E Drive Dashboard -> Archive Button");
		Thread.sleep(1000);
		waitForElementVisibility(archiveDialog);
		clickOn(archiveYes, "E Drive Dashboard -> Archive Yes Button");
		if(savedMessage.getText().equalsIgnoreCase("Invoice archived successfully"))
			System.out.println("Hotel Invoice archived successfully");
			else Assert.fail("Unable to archive invoice for " + invoiceMonth + "due to error");
	}
	
	public void archiveAcceptedGTInvoice(String commentToArchieve, String invoiceMonth) throws InterruptedException{
		typeInText(gtComment, commentToArchieve, "E Drive Dashboard -> Archive Accepted Invoice GT Comments TextArea");
		clickOn(gtArchiveButton, "E Drive Dashboard -> GT Archive Button");
		Thread.sleep(1000);
		waitForElementVisibility(gtArchiveDialog);
		clickOn(archiveYes, "E Drive Dashboard -> Archive Yes Button");
		if(gtsaveMessage.getText().equalsIgnoreCase("Invoice archived successfully"))
			System.out.println("GT Invoice archived successfully");
			else Assert.fail("Unable to archive invoice for " + invoiceMonth + "due to error");
	}
	
	public void returnHotelInvoice(String commentToReturn, String invoiceMonth){
		typeInText(comment, commentToReturn, "E Drive Dashboard -> Return Hotel Invoice Comments TextArea");
		clickOn(returnButton, "E Drive Dashboard -> Hotel Return Button");	
		if(savedMessage.getText().equalsIgnoreCase("Invoice returned successfully"))
			System.out.println("Hotel Invoice returned successfully");
			else Assert.fail("Unable to return Hotel invoice for " + invoiceMonth + "due to error");
	}	
	
	public void returnGTInvoice(String commentToReturn, String invoiceMonth){
		typeInText(gtComment, commentToReturn, "E Drive Dashboard -> Return GT Invocie Comments TextArea");
		clickOn(gtReturnButton, "E Drive Dashboard -> GT Return Button");
		if(gtsaveMessage.getText().equalsIgnoreCase("Invoice returned successfully"))
			System.out.println("Gt Invoice returned successfully");
			else Assert.fail("Unable to return Gt invoice for " + invoiceMonth + "due to error");
		
	}
	
	public void rejectHotelInvoice(String commentToReject, String invoiceMonth){
		typeInText(comment, commentToReject, "E Drive Dashboard -> Reject Hotel Invoice Comments TextArea");
		clickOn(rejectButton, "E Drive Dashboard -> Reject Button");
		if(savedMessage.getText().equalsIgnoreCase("Invoice rejected successfully"))
			System.out.println("Hotel Invoice returned successfully");
			else Assert.fail("Unable to reject Hotel invoice for " + invoiceMonth + "due to error");
	}
	
	public void rejectGTInvoice(String commentToReject, String invoiceMonth){
		typeInText(gtComment, commentToReject,"E Drive Dashboard -> Reject GT Ivoice Comments TextArea");
		clickOn(gtRejectButton);
		if(gtsaveMessage.getText().equalsIgnoreCase("Invoice rejected successfully"))
			System.out.println("Hotel Invoice returned successfully");
			else Assert.fail("Unable to reject Hotel invoice for " + invoiceMonth + "due to error");
	}



	public void clickOnEDriveDashboardLink(){
		clickOn(link_EdriveDashboard,"ACES 3 Menu -> EDrive Dashboard Link");
	}
	
	public void verifyElementsOnEDriveDashboardPage(){
		waitForElementVisibility(tenantSelection);
		waitForElementVisibility(invoiceSelection);
		waitForElementVisibility(statuses);
		waitForElementVisibility(supplierType);
		waitForElementVisibility(findButton);
		
	}
}
	
	
