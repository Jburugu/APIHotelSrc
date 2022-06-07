package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_AllowanceInvoices extends BasePage {
	public EventFiringWebDriver driver = null;

	public Page_AllowanceInvoices(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy (linkText = "Allowance Dashboard")
	private WebElement link_AllowanceDashboard;
	
	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[3]/td[2]/a")
	private List<WebElement> newInvoicesANZ;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[3]/td[3]/a")
	private WebElement myHoldQueueANZ;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[3]/td[4]/a")
	private WebElement returnedToHotelANZ;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[3]/td[5]/a")
	private WebElement rejectedInvoicesANZ;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[2]/td[2]/a")
	private WebElement newInvoicesAirNelson;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[2]/td[3]/a")
	private WebElement myHoldQueueAirNelson;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[2]/td[4]/a")
	private WebElement returnedToHotelAirNelson;

	@FindBy(xpath = "//div[@class='Aces_Table']/table/tbody/tr[2]/td[5]/a")
	private WebElement rejectedInvoicesAirNelson;
	
	@FindBy (xpath="//th[text()='Status']/parent::tr/td")
	private WebElement getInvoiceStatus;
	
	String xpath1_InvoiceNumber = "//div[@class='Aces_Table'][2]/table/tbody/tr/td[1]/a[text()='";
	String xpath2_InvoiceNumber = "']";
	
	@FindBy (name ="AddComment")
	private WebElement button_AddComment;

	@FindBy (id ="commentComments")
	private WebElement textbox_commentComments;

	@FindBy (xpath = "//span[text()='Ok']")
	private WebElement button_Ok;
	
	@FindBy (xpath="//input[@name='approve']")
	private WebElement button_Approve;
	
	@FindBy (xpath="//input[@name='hold']")
	private WebElement button_Hold;
	
	@FindBy (xpath="//input[@name='return']")
	private WebElement button_Return;
	
	@FindBy (xpath="//input[@name='reject']")
	private WebElement buttton_Reject;
	
	@FindBy (xpath="//input[@name='void']")
	private WebElement button_Void;
	
	public void clickOnAllowanceDashboard() {
		clickOn(link_AllowanceDashboard,"Allowance Invoices Menu -> Allowance Dashboard link");
	}
	
	public void clickOnNewInvoices(String invoiceNumber) throws Exception {
		if(newInvoicesANZ.size()>0) {
			clickOn(newInvoicesANZ.get(0));
			takeScreenshots();
			clickOn(findElementByXpath(xpath1_InvoiceNumber+invoiceNumber+xpath2_InvoiceNumber),"Invoice Number link");
			takeScreenshots();
		}
	}
	public void performActionsOnNewInvoiceAndVerifyStatus(String invoiceNumber, String expectedStatus) throws IOException {
			String invoiceStatus = getTextOfElement(getInvoiceStatus);
			System.out.println("Invoice Status is "+invoiceStatus);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Invoice Status is "+invoiceStatus);
			clickOn(button_AddComment, "Invoice Dashboard Page -> Add Comment button");
			typeInText(textbox_commentComments, "testComments","Invoice Comments text box");
			takeScreenshots();
			clickOn(button_Ok,"Invoice Comments-> Ok Button");
			
			if(expectedStatus.equals("Approved")) 
			{
			clickOn(button_Approve, "Invoice Dashboard -> Approve Button");
			takeScreenshots();
			assertTrue(getTextOfElement(getInvoiceStatus).equals(expectedStatus), "Invoice Status is Approved");
					ExtentTestManager.getTest().log(LogStatus.INFO, "Invoice Status is "+getTextOfElement(getInvoiceStatus));
			}
			else if(expectedStatus.equals("Held")) 
			{
						clickOn(button_Hold, "Invoice Dashboard -> Hold Button");
						takeScreenshots();
						assertTrue(getTextOfElement(getInvoiceStatus).equals(expectedStatus), "Invoice Status is Approved");
								ExtentTestManager.getTest().log(LogStatus.INFO, "Invoice Status is "+getTextOfElement(getInvoiceStatus));
						
			}
			else if(expectedStatus.equals("Returned")) 
			{
						clickOn(button_Return, "Invoice Dashboard -> Return Button");
						takeScreenshots();
						assertTrue(getTextOfElement(getInvoiceStatus).equals(expectedStatus), "Invoice Status is Approved");
								ExtentTestManager.getTest().log(LogStatus.INFO, "Invoice Status is "+getTextOfElement(getInvoiceStatus));
						
			}
			else if(expectedStatus.equals("Rejected")) 
			{
						clickOn(buttton_Reject, "Invoice Dashboard -> Reject Button");
						takeScreenshots();
						assertTrue(getTextOfElement(getInvoiceStatus).equals(expectedStatus), "Invoice Status is Approved");
								ExtentTestManager.getTest().log(LogStatus.INFO, "Invoice Status is "+getTextOfElement(getInvoiceStatus));
						
			}
		}
			
	

}
