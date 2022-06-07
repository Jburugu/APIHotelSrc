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

public class GTUsagePage extends BasePage {

	public EventFiringWebDriver driver=null;
	// ACCOUNTING -- GT USAGE(AIRCANADA)

	@FindBy(how = How.ID, using = "gtUsageForm:calendarMonth:bidPeriods")
	public WebElement invoiceMonth;

	@FindBy(how = How.XPATH, using = "//input[@value='invoiceMonth']")
	public WebElement invoiceMonthRadioButton;

	@FindBy(how = How.ID, using = "gtUsageForm:concalendarMonth:conslidatedbidPeriods")
	public WebElement consolidatedInvoiceMonth;

	@FindBy(how = How.XPATH, using = "//input[@value='consolidtdInvoice']")
	public WebElement consolidatedInvoiceMonthRadioButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='gtUsageForm:retrieve']")
	public WebElement retrieveButton;

	 //Mesa Airlines
    @FindBy(how = How.XPATH, using = "//select[contains(@id,'businessLineId')]")
    public WebElement mesaAirlinesCompany;
	
	/**
	 * Commut airlines
	 */
	@FindBy(how = How.CSS, using = "select[id$='departmentCd']")
	public WebElement costCenter;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Reason Code')]/parent::*/following-sibling::td//select")
	public WebElement reasonCode;

	/**
	 * Express Jet
	 */
	@FindBy(how = How.CSS, using = "input[id$='empAccLoc']")
	public WebElement accountLocation;

	@FindBy(how = How.CSS, using = "input[id$='employeeNumber']")
	public WebElement employeeNumber;

	@FindBy(how = How.CSS, using = "input[id$='employeeName']")
	public WebElement employeeName;

	@FindBy(how = How.CSS, using = "input[id$='locCd']")
	public WebElement location;

	@FindBy(how = How.CSS, using = "input[id$='accountCode']")
	public WebElement glCode;

//	@FindBy(how=How.XPATH, using ="//td[contains(text(),'Wait Please...')]")
	@FindBy(how=How.XPATH, using ="//*[@id='waitContentTable']/tbody/tr[2]/td")
private WebElement pleaseWaitPopup;	
	
	@FindBy(how =How.XPATH, using ="//*[@id='gtUsageForm:gtUsageTable']//tbody/tr")
	private List<WebElement> gtUsageDetailsTable;
	
	@FindBy(how =How.XPATH, using ="//input[@name='gtUsageForm:consoldtInvoiceType']")
	public WebElement consolidatedInvoiceRadioBtn;
	
	@FindBy (how=How.XPATH,using ="//a[contains(text(),'Excel')]")
	public WebElement exportToExcelLink;
	
	public GTUsagePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void gtUsage() {
		selectByVisibleText(invoiceMonth, getCurrentMonthAndYear());
		clickOn(consolidatedInvoiceMonthRadioButton);
		selectByVisibleText(consolidatedInvoiceMonth, getCurrentMonthAndYear());
	}
	
	public void clickOninvoiceMonthRadioButton(){
		clickOn(invoiceMonthRadioButton);
	}

	public void costCenter(String costCenterValue) {
		int costCenterVal = Integer.parseInt(costCenterValue);
		selectByIndex(costCenter, costCenterVal);

	}

	public void reasonCode(String reasonCodeValue) {
		int reasonCodeVal = Integer.parseInt(reasonCodeValue);
		selectByIndex(reasonCode, reasonCodeVal);
	}

	public void accountLocation(String accountLocationValue) {
		typeInText(accountLocation, accountLocationValue);
	}

	public void EmpNumberAndNametLocationGlcode(String employeeNumberValue, String employeeNameValue, String locValue,
			String glCodeValue) {
		typeInText(employeeNumber, employeeNumberValue);
		typeInText(employeeName, employeeNameValue);
		typeInText(location, locValue);
		typeInText(glCode, glCodeValue);
	}

	public void verifyRetrieveButton() {
		waitForElementVisibility(retrieveButton);
	}
	
	public void generateGLCodeReprotForInvoiceMonth(String month){
		selectByVisibleText(invoiceMonth, month);
		verifyRetrieveButton();
		}
	
	public void generateGLCodeReprotForConsolidatedInvoiceMonth(String month){
		selectByVisibleText(consolidatedInvoiceMonth, month);
		verifyRetrieveButton();
	}

	// Mesa Airlines
	public void Company(String CompanyValue) {

		int mesaCompanyVal = Integer.parseInt(CompanyValue);
		selectByIndex(mesaAirlinesCompany, mesaCompanyVal);
	}

	public void clickOnRetrieveButton(String tenantName, String radioBtnType) throws Exception {
		int countBeforeDownload = queryDirectorySize(downloadFilepath);
		clickOn(retrieveButton);
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(60));
		wait1.pollingEvery(Duration.ofSeconds(15));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(pleaseWaitPopup));
		if (gtUsageDetailsTable.size() > 2) {
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
			fileMoveToDesiredFolder(downloadFilepath, downloadFilepath + File.separator + "GTuR",
					tenantName.replace(" ", "_") + radioBtnType + "_GTuR.xls", "xls");

	}

	public void generateGLCodeReprotForConsolidatedInvoiceMonth() {
		clickOn(consolidatedInvoiceRadioBtn);
		selectByVisibleText(consolidatedInvoiceMonth, getCurrentMonthAndYear());

	}
	
	public void generateRuRForConsolidatedInvoiceMonth(String billingMonthYear) {
		clickOn(consolidatedInvoiceRadioBtn);
		selectByVisibleText(consolidatedInvoiceMonth, billingMonthYear);

	}
	
	public void clickOnRetrieveButton() throws InterruptedException {
		clickOn(retrieveButton);
		Thread.sleep(2000);
		WebDriverWait wait2 = new WebDriverWait(getDriver(), 30);
		wait2.withTimeout(Duration.ofMinutes(60));
		wait2.pollingEvery(Duration.ofSeconds(15));
		// wait2.ignoring(StaleElementReferenceException.class);
		wait2.until(ExpectedConditions.invisibilityOf(pleaseWaitPopup));
		if (gtUsageDetailsTable.size() > 2) {
			clickOn(exportToExcelLink);
			Thread.sleep(90000);
		} else {
			System.out.println("No Records found");
			clickOn(exportToExcelLink);
			Thread.sleep(90000);
		}

	}


}
