package com.APIHotels.pages.ACESII;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class Page_CreateProfarmaInvoices extends BasePage{
	
	@FindBy(xpath = "//a[text()='Create Proforma Invoices']")
	public WebElement createProformaInvoicesLink;
	
	@FindBy(id = "hotel")
	public WebElement hotelServicesCreateProformaInvoices;

	@FindBy(id = "gt")
	public WebElement gtServicesCreateProformaInvoices;

	@FindBy(id = "both")
	public WebElement bothServicesCreateProformaInvoices;
	
	@FindBy(id = "billingPeriodNumberMonth")
	public WebElement billingPeriodNumberMonth;
	
	@FindBy(xpath = "//input[@value='Create Invoices']")
	public WebElement creteInvoicesBtnMonBatchInvoice;

	@FindBy(id = "hotel1")
	public WebElement hotelDailyInvoice;

	@FindBy(id = "gt1")
	public WebElement gtDailyInvoice;
	
	@FindBy(id = "locCd")
	public WebElement citySingleInvoicet;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType; //HOTEL, GT, BOTH
	
	String monthlyServiceTypeXpath1 = "//*[@name = 'serviceTypeMonth' and @value = '";
	String singleServiceTypeXpath1 = "//*[@name = 'serviceTypeSingle' and @value = '";
	String serviceTypeXpath2 = "']";
	
	@FindBy(css = "input[value='Get Supplier']")
	public WebElement getSupplierBtnSingleInvoice;

	@FindBy(id = "supplierId")
	public WebElement suppliersSingleInvoice;
	
	@FindBy(id = "billingPeriodNumberSingle")
	public WebElement billingPeriodNumberSingle;
	
	@FindBy(xpath = "//input[@value='Create Invoice']")
	public WebElement createInvocieSingleInvoiceBtn;
	
	@FindBy(id = "wNotes")
	public WebElement warningNotes;
	
	@FindBy(id = "alertOK")
	public WebElement alertOkBtn;
	
	@FindBy(xpath = "//*[@id='ProgressBox']")
	public WebElement inProgressBox;
	
	@FindBy(xpath = "//a[contains(text(), 'P-')]")
	private WebElement invoiceNumberLink;
	
	public EventFiringWebDriver driver=null;
	
	public Page_CreateProfarmaInvoices(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createProformaInvoice(String cityJFK, String indexValue1, String currentMMMYYYY) throws Exception {
		waitForElementVisibility(createProformaInvoicesLink);
		clickOn(createProformaInvoicesLink);
		waitForElementVisibility(hotelServicesCreateProformaInvoices);
		waitForElementVisibility(gtServicesCreateProformaInvoices);
		waitForElementVisibility(bothServicesCreateProformaInvoices);
		waitForElementVisibility(billingPeriodNumberMonth);
		selectByVisibleText(billingPeriodNumberMonth, currentMMMYYYY);
		waitForElementVisibility(creteInvoicesBtnMonBatchInvoice);
		waitForElementVisibility(hotelDailyInvoice);
		waitForElementVisibility(gtDailyInvoice);
		clickOn(gtDailyInvoice);
		clickOn(hotelDailyInvoice);
		waitForElementVisibility(citySingleInvoicet);
		typeInText(citySingleInvoicet, cityJFK);
		clickOn(getSupplierBtnSingleInvoice);
		waitForElementVisibility(suppliersSingleInvoice);
		Integer supplierIndexValueCrteProformaInvParse = Integer.parseInt(indexValue1);
		selectByIndex(suppliersSingleInvoice, supplierIndexValueCrteProformaInvParse);
		selectByVisibleText(billingPeriodNumberSingle, currentMMMYYYY);
		waitForElementVisibility(createInvocieSingleInvoiceBtn);
		waitForElementVisibility(warningNotes);

	}

	
	public void services(String serviceType){
		clickOn(findElementByXpath(monthlyServiceTypeXpath1+serviceType+serviceTypeXpath2));
	}
	
	public void createProformaInvoiceMonthly(String serviceType, String currentMMMYYYY){
		services(serviceType);
		selectByVisibleText(billingPeriodNumberMonth, currentMMMYYYY.toUpperCase());
	}
	
	
	public void createProformaInvoiceSingle(String serviceType, String cityJFK,String supplier, String currentMMMYYYY){
		clickOn(findElementByXpath(singleServiceTypeXpath1+serviceType+serviceTypeXpath2));
		typeInText(citySingleInvoicet, cityJFK);
		clickOn(getSupplierBtnSingleInvoice);
		selectByVisibleText(suppliersSingleInvoice, supplier);
		selectByVisibleText(billingPeriodNumberSingle, currentMMMYYYY.toUpperCase());	
	}

	public void clickOnCreateInvoiceBtnMonthlyBatchInvoice() {
		clickOn(creteInvoicesBtnMonBatchInvoice);
	}
	
	public void clickAlertOk(){
		clickOn(alertOkBtn);
	}

	public void clickOnCreateInvoiceBtnSingleInvoice() {
		clickOn(createInvocieSingleInvoiceBtn);		
	}
	
	public void waitForInProgressToComplete(){
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.withTimeout(Duration.ofMinutes(2));
        wait.pollingEvery(Duration.ofSeconds((15)));
        wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.invisibilityOf(inProgressBox));
	}
	
	public void runBulkReviewQuery(String tenantName, String bidMonthYear) throws Exception{
		String url = "jdbc:oracle:thin:@"+dbIP+":"+dbPort+":aces";
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, dbUserId, dbPassword);
	    System.out.println("connection established!");
		Statement st = con.createStatement();
		String tenantIdQuery = "Select tenantid from Tenant where tenantname = '"+tenantName+"'";
		ResultSet rs = st.executeQuery(tenantIdQuery);
		rs.next();
		String tenantId = rs.getString(1);
		String query = "update invoice set status=1 where status=0 and tenantid="+tenantId+" and billingyear="+bidMonthYear.split(" ")[1]+" and billingmonth="+bidMonthYear.split(" ")[0];
		int updatedRows = st.executeUpdate(query);
		con.commit();
		System.out.println("Total Updated Rows: "+updatedRows);
		con.close();
	}
	
	public void writInvoiceNumToProp(){
		String invoiceNumber = this.invoiceNumberLink.getText().trim();
		writeProp("ProformaInvoiceNumber", invoiceNumber);
		
	}
	
	public String clickOnProformaInvoiceAndDownload() throws InterruptedException{
		String invoiceNumber = this.invoiceNumberLink.getText().trim();
		clickOn(invoiceNumberLink);
		Thread.sleep(3000);
		switchToWindowIndex(1);
		getDriver().switchTo().frame(driver.findElement(By.tagName("iframe")));
		System.out.println("Switched To PDF Window");
		driver.findElement(By.id("open-button")).click();
		Thread.sleep(6000);
		return invoiceNumber;
	}
	
}
