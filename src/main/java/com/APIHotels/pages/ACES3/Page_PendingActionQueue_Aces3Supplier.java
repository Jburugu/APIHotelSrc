package com.APIHotels.pages.ACES3;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_PendingActionQueue_Aces3Supplier extends BasePage {

	public EventFiringWebDriver driver = null;

	public Page_PendingActionQueue_Aces3Supplier(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Accounting")
	private WebElement tab_Accounting;

	@FindBy(linkText = "Pending Action Queue")
	private WebElement sub_Tab_PendingActionQueue;

	@FindBy(xpath = "//span[contains(text(),'View Payment')]")
	WebElement columnHeader_LastSubmit;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;

	@FindBy(xpath = "//span[@class='ui-sortable-column-icon ui-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s']")
	private WebElement sort_LastSubmitLatest;

	@FindBy(xpath = "//body/div[@id='container']/div[@id='subcontainer']/div[@id='tableRow']/div[@id='column2of3']/div[@id='content']/form[@id='airlineActionQueueForm']/div[@id='airlineActionQueueForm:actionTable']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='airlineActionQueueForm:actionTable_data']/tr[1]/td[2]//td/span")
	private WebElement link_UploadFile;

	@FindBy(xpath = "//div[@id='airlineActionQueueForm:actionTable']/div/table/tbody/tr/td[1]")
	private List<WebElement> tableRowCount;

	String tableCol1XPath1 = "//tbody[@id='airlineActionQueueForm:actionTable_data']/tr[";
	String tableCol1XPath2 = "]/td[1]";
	String tableCol2Xpath2 = "]/td[2]//span[text()='Upload Files']";
	String tableCol2LabelEndXpath = "]/td[2]/label";
	String chooseFileXPathEnd = "')]//span[@class='ui-button-text ui-c'][contains(text(),'Upload')]/parent::button/parent::div/span";
	String UplodDailogBoxXPath1 = "//div[contains(@id,'airlineActionQueueForm:actionTable:";
	String clickUploadBtnXpathEnd = "')]//span[@class='ui-button-text ui-c'][contains(text(),'Upload')]";
	String clickCloseBtnXPath2 = "')]//span[@class='ui-icon ui-icon-closethick']";
	String chooseFileXPathStart = "//div[contains(@id,'airlineActionQueueForm:actionTable:";
	
	@FindBy(xpath = "//span[@class='ui-button-icon-left ui-icon ui-icon ui-icon-close']")
	private WebElement fileUploadProgressCloseImg;
	
	@FindBy(xpath = "//span[contains(text(),'Perform Actions')]")
	private WebElement btn_PerformActions;

	public void clickOnAccountingTab() {
		clickOn(tab_Accounting, "NavigationalMenu -> Accounting link");
	}
	
	public void clickOnPendingActionQueueTab() throws IOException{
		clickOn(sub_Tab_PendingActionQueue, "NavigationalMenu -> AccountingMenu -> Pending action Queue link");
		takeScreenshots();
		waitForElementToDisappear(spinner);
	}

	public void sortForLatestInvoice() throws Exception {

		for (int i = 0; i < 5; i++) {
			clickOn(columnHeader_LastSubmit, "PendingActionQueuePage -> Last Submit Date header");
			waitForElementToDisappear(spinner);
			if (sort_LastSubmitLatest.isDisplayed() == true)
				break;

		}
		Thread.sleep(2000);
	}

	public void clickOnUploadFile() {
		clickOn(link_UploadFile, "PendingActionQueuePage -> Upload File btn");
	}

	public void verifyNotaFiscalUploadFileInFlight(String invoiceNo, String inFlight) throws Exception {
		takeScreenshots();
		String inFlightstring = invoiceNo + "-02 " + inFlight;
		System.out.println("Print custom string :" + inFlightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			int j = i - 1;
			WebElement cellValue = findElementByXpath(tableCol1XPath1 + i + tableCol1XPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(inFlightstring)) {
				workflowGroup = findElementByXpath(tableCol1XPath1 + i + tableCol2LabelEndXpath);
				String workflowgroupName = workflowGroup.getText();
				WebElement uploadFile = findElementByXpath(tableCol1XPath1 + i + tableCol2Xpath2);
				if (workflowgroupName.equals("Attach NotaFiscal"))
					clickOn(uploadFile);
				takeScreenshots();
					clickOn(uploadFile, "PendingActionQueuePage -> Upload file btn");
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				System.out.println("XPath : " + chooseFileXPathStart + j + chooseFileXPathEnd);
				clickOn(findElementByXpath(chooseFileXPathStart + j + chooseFileXPathEnd), "PendingActionQueuePage -> UploadFile pop-up -> Choose File btn");
				waitForElementToDisappear(spinner);
				Thread.sleep(3000);
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\lib\\uploadFile.exe" + " "
						+ System.getProperty("user.dir") + "\\lib\\test.docx");
				Thread.sleep(2000);
				System.out.println("XPath : " + UplodDailogBoxXPath1 + j + clickUploadBtnXpathEnd);
				takeScreenshots();
				clickOn(findElementByXpath(UplodDailogBoxXPath1 + j + clickUploadBtnXpathEnd));
				Thread.sleep(3000);
				takeScreenshots();
				clickOn(findElementByXpath(UplodDailogBoxXPath1 + j + clickCloseBtnXPath2));
				takeScreenshots();
				clickOn(findElementByXpath(UplodDailogBoxXPath1 + j + clickUploadBtnXpathEnd), "PendingActionQueuePage -> Upload File pop-up -> Upload btn");
				Thread.sleep(3000);
				clickOn(findElementByXpath(UplodDailogBoxXPath1 + j + clickCloseBtnXPath2), "PendingActionQueuePage -> Upload File pop-up Close btn");
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);

			}
		}
	}
	
	public void verifyNotaFiscalUploadFileFlight(String invoiceNo, String flight) throws Exception {
		takeScreenshots();
		String flightstring = invoiceNo + "-01 " + flight;
		System.out.println("Print custom string :" + flightstring);
		System.out.println("Row Size " + tableRowCount.size());
		WebElement workflowGroup = null;
		for (int i = 1; i <= tableRowCount.size(); i++) {
			int j = i - 1;
			WebElement cellValue = findElementByXpath(tableCol1XPath1 + i + tableCol1XPath2);
			System.out.println("Print App string :" + cellValue.getText());
			if (cellValue.getText().equals(flightstring)) {
				workflowGroup = findElementByXpath(tableCol1XPath1 + i + tableCol2LabelEndXpath);
				String workflowgroupName = workflowGroup.getText();
				WebElement uploadFile = findElementByXpath(tableCol1XPath1 + i + tableCol2Xpath2);
				if (workflowgroupName.equals("Attach NotaFiscal"))
					clickOn(uploadFile);
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);
				System.out.println("XPath : " + chooseFileXPathStart + j + chooseFileXPathEnd);
				clickOn(findElementByXpath(chooseFileXPathStart + j + chooseFileXPathEnd));
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(1000);
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\lib\\uploadFile.exe" + " "
						+ System.getProperty("user.dir") + "\\lib\\test.docx");
				Thread.sleep(2000);
				System.out.println("XPath : " + UplodDailogBoxXPath1 + j + clickUploadBtnXpathEnd);
				takeScreenshots();
				clickOn(findElementByXpath(UplodDailogBoxXPath1 + j + clickUploadBtnXpathEnd));
				takeScreenshots();
				Thread.sleep(3000);
				clickOn(findElementByXpath(UplodDailogBoxXPath1 + j + clickCloseBtnXPath2));
				takeScreenshots();
				waitForElementToDisappear(spinner);
				Thread.sleep(2000);

			}
		}
	}
	
	public void clickOnPerformActionsbtn(){
		clickOn(btn_PerformActions);
	}
}
