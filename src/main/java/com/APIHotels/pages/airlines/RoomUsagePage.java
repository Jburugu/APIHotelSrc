package com.APIHotels.pages.airlines;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class RoomUsagePage extends BasePage {

	public EventFiringWebDriver driver = null;
	// ACCOUNTING -- HOTEL -- ROOM USAGE

	// Commut Air elements

	@FindBy(how = How.ID, using = "roomUsageForm:calendarMonth:bidPeriods")
	public WebElement invoiceMonth;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Cost Center')]/parent::*/following-sibling::td//select")
	public WebElement costCenter;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Reason Code')]/parent::*/following-sibling::td//select")
	public WebElement reasonCode;

	@FindBy(how = How.ID, using = "roomUsageForm:retrieve")
	public WebElement retrieveBtn;

	// Alaska
	@FindBy(how = How.ID, using = "roomUsageForm:concalendarMonth:conslidatedbidPeriods")
	public WebElement consolidatedInvoiceMonth;

	@FindBy(how = How.XPATH, using = "//table[@id='roomUsageForm:consoldtInvoiceType' and @class='label']//input")
	public WebElement consolidatedInvoiceMonthRadioBtn;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'calendarMonth:bidPeriods')]/parent::div/parent::div/parent::td/preceding-sibling::td//input")
	public WebElement invoiceMonthRadioBtn;

	// Endeavor
	@FindBy(how = How.CSS, using = "input[id$='employeeNumber']")
	public WebElement employeeNumber;

	@FindBy(how = How.CSS, using = "select[id$='departmentCd']")
	public WebElement department;

	@FindBy(how = How.CSS, using = "input[id$='employeeName']")
	public WebElement employeeName;

	@FindBy(how = How.CSS, using = "input[id$='locCd']")
	public WebElement loc;

	@FindBy(how = How.CSS, using = "input[id$='accountCode']")
	public WebElement glCode;

	@FindBy(how = How.XPATH, using = "//table[@class='dr-table rich-table']/tbody/tr")
	public List<WebElement> roomUsageDetailsTable;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Excel')]")
	public WebElement exportToExcelLink;
	// Express Jet
	@FindBy(how = How.CSS, using = "input[id$='empAccLoc']")
	private WebElement accountLocation;

	// Mesa Airlines
	@FindBy(how = How.XPATH, using = "//select[contains(@id,'businessLineId')]")
	public WebElement mesaAirlinesCompany;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Wait Please...')]")
	private WebElement pleaseWaitPopup;

	public RoomUsagePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void invoiceMonth() {
		selectByVisibleText(invoiceMonth, getCurrentMonthAndYear());
	}

	public void roomUsage(String costCenterValue) {
		int costCenterVal = Integer.parseInt(costCenterValue);
		selectByIndex(costCenter, costCenterVal);
	}

	public void verifyRetrieveBtn() {
		waitForElementVisibility(retrieveBtn);
	}

	public void reasonCode(String reasonCodeValue) {
		int reasonCodeVal = Integer.parseInt(reasonCodeValue);
		selectByIndex(reasonCode, reasonCodeVal);
	}

	public void findRoomUsage() {
		waitForElementVisibility(costCenter);
		waitForElementVisibility(reasonCode);
		waitForElementVisibility(invoiceMonthRadioBtn);
		waitForElementVisibility(invoiceMonth);
		waitForElementVisibility(consolidatedInvoiceMonthRadioBtn);
		waitForElementVisibility(consolidatedInvoiceMonth);
		waitForElementVisibility(retrieveBtn);
	}

	public void invoiceMonthRadioAndConsolidatedInvoiceMonth() {
		clickOn(invoiceMonthRadioBtn);
		clickOn(consolidatedInvoiceMonthRadioBtn);
		selectByVisibleText(consolidatedInvoiceMonth, getCurrentMonthAndYear());
	}

	public void EmpNumberAndNameDepartmentLocGlcode(String employeeNumberValue, String employeeNameValue,
			String departmentValue, String locValue, String glCodeValue) {
		typeInText(employeeNumber, employeeNumberValue);
		typeInText(employeeName, employeeNameValue);
		int departmentVal = Integer.parseInt(departmentValue);
		selectByIndex(department, departmentVal);
		typeInText(loc, locValue);
		typeInText(glCode, glCodeValue);
	}

	public void accountLocation(String accountLocationValue) {
		typeInText(accountLocation, accountLocationValue);
	}

	// Mesa Airlines
	public void Company(String CompanyValue) {

		int mesaCompanyVal = Integer.parseInt(CompanyValue);
		selectByIndex(mesaAirlinesCompany, mesaCompanyVal);
	}

	public void findRoomUsageReportInvMonth(String costCenterValue, String reasonCodeValue, String invoiceMonthValue) {
		selectByVisibleText(costCenter, costCenterValue);
		selectByVisibleText(reasonCode, reasonCodeValue);
		clickOn(invoiceMonthRadioBtn);
		selectByVisibleText(invoiceMonth, invoiceMonthValue);
	}

	public void findRoomUsageReportConsolidatedInvMonth(String costCenterValue, String reasonCodeValue,
			String invoiceMonthValue) {
		selectByVisibleText(costCenter, costCenterValue);
		selectByVisibleText(reasonCode, reasonCodeValue);
		clickOn(consolidatedInvoiceMonthRadioBtn);
		selectByVisibleText(invoiceMonth, invoiceMonthValue);
	}

	public void clickonInvoiceMonth() {
		clickOn(invoiceMonthRadioBtn);
		selectByVisibleText(invoiceMonth, getCurrentMonthAndYear());
	}

	public void clickOnConsolidatedInvoice() {
		clickOn(consolidatedInvoiceMonthRadioBtn);
		selectByVisibleText(consolidatedInvoiceMonth, getCurrentMonthAndYear());
	}
	
	public void clickOnRetrieve() throws Exception{
		clickOn(retrieveBtn);
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(60));
		wait1.pollingEvery(Duration.ofSeconds(15));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(pleaseWaitPopup));
		if (roomUsageDetailsTable.size() > 1) {
			boolean downloaded = false;
			String filePath = downloadFilepath;
			clickOn(exportToExcelLink);
			int waitTimeInt = 1;
			while (!downloaded && waitTimeInt < 120) {
				Thread.sleep(10000);
				System.out.println("waitTimeForDownload: " + waitTimeInt);
				String newFilePath = getTheLatestFile(filePath, "xls");
				if (!filePath.equals(""))
					downloaded = latestFilePresent(newFilePath);
				waitTimeInt++;
			}
		} else {
			System.out.println("No Records found");
			clickOn(exportToExcelLink);
			Thread.sleep(70000);
		}
		
	}
	
	public void clickonInvoiceMonth(String billingMonthYear) {
		clickOn(invoiceMonthRadioBtn);
		selectByVisibleText(invoiceMonth, billingMonthYear);
	}

	public void clickOnConsolidatedInvoiceMonth(String billingMonthYear) {
		clickOn(consolidatedInvoiceMonthRadioBtn);
		selectByVisibleText(consolidatedInvoiceMonth, billingMonthYear);
	}
	
	public void clickOnRetrieve(String tenantName, String radioBtnType) throws Exception {
		int countBeforeDownload = queryDirectorySize(downloadFilepath);
		clickOn(retrieveBtn);
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(60));
		wait1.pollingEvery(Duration.ofSeconds(15));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(pleaseWaitPopup));
		if (roomUsageDetailsTable.size() > 1) {
			boolean downloaded = false;
			String filePath = downloadFilepath;
			clickOn(exportToExcelLink);
			int waitTimeInt = 1;
			while (!downloaded && waitTimeInt < 120) {
				Thread.sleep(10000);
				System.out.println("waitTimeForDownload: " + waitTimeInt);
				String newFilePath = getTheLatestFile(filePath, "xls");
				if (!filePath.equals(""))
					downloaded = latestFilePresent(newFilePath);
				waitTimeInt++;
			}
		} else {
			System.out.println("No Records found");
			clickOn(exportToExcelLink);
			Thread.sleep(70000);
		}
		int countAfterDownload = queryDirectorySize(downloadFilepath);
		if (countAfterDownload > countBeforeDownload)
			fileMoveToDesiredFolder(downloadFilepath, downloadFilepath+File.separator+"RuR", tenantName.replace(" ", "_") + radioBtnType + "_RUR.xls",
					"xls");
	}


}
