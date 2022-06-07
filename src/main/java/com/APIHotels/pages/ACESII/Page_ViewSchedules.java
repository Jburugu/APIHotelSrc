package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_ViewSchedules extends BasePage {

	@FindBy(id = "scheduleType")
	private WebElement scheduleType;

	@FindBy(id = "bidmonth")
	private WebElement bidPeriod;

	@FindBy(name = "serviceType")
	private List<WebElement> serviceType;

	@FindBy(id = "locCd")
	private WebElement city;

	@FindBy(xpath = "//*[@name = 'supplierName' and @value = 'Get Supplier']")
	private WebElement getSupplierBtn;

	@FindBy(id = "suppliers")
	private WebElement supplier;

	@FindBy(id = "releaseid")
	private WebElement releasedIndicator;

	@FindBy(name = "releasedStatusType")
	private List<WebElement> releasedStatusType;

	@FindBy(id = "ackStatusid")
	private WebElement acknowledgedIndicator;

	@FindBy(name = "ackStatusType")
	private List<WebElement> acknowledgedStatusType;

	@FindBy(id = "activityStatusid")
	private WebElement activityIndicator;

	@FindBy(name = "activityStatusType")
	private List<WebElement> activityStatusType;

	@FindBy(id = "emailStatusid")
	private WebElement emailedIndicator;

	@FindBy(name = "emailStatusType")
	private List<WebElement> emailedStatusType;

	@FindBy(id = "findSchedules")
	private WebElement findBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Server Print']")
	private WebElement serverPrintBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Local Print']")
	private WebElement localPrintBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Send to Disk']")
	private WebElement sendToDiskBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Regenerate Schedules']")
	private WebElement regenerateSchedulesBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Release Schedules']")
	private WebElement releaseSchedulesBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Delete Schedules']")
	private WebElement deleteSchedulesBtn;

	@FindBy(xpath = "//*[@name = 'btnSubmit' and @value = 'Fax']")
	private WebElement faxBtn;

	String serviceTypeXpath1 = "//*[@name = 'serviceType' and @value = '";
	String serviceTypeXpath2 = "']";

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[12]/a")
	private WebElement actionLink;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody/tr")
	private List<WebElement> scheduleRows;

	private String notifyIndicatorXpath1 = "//*[@name = 'notifyStatusType' and @value = '";
	private String notifyIndicatorXpath2 = "']";

	@FindBy(id = "confirmedWith")
	private WebElement confirmedWith;

	@FindBy(name = "hotelNotes")
	private WebElement hotelNotes;

	@FindBy(name = "Ok")
	private WebElement notify_okBtn;

	@FindBy(xpath = "//*[@id = 'notes']/tbody//td[5]")
	private List<WebElement> notes;

	@FindBy(name = "close")
	private WebElement notes_closeBtn;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[3]")
	private List<WebElement> scheduleCity;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[4]")
	private List<WebElement> scheduleSupplierType;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[5]/a")
	private List<WebElement> scheduleSupplier;

	@FindBy(xpath = "//*[@id='schedule']/tbody//td[9]")
	private WebElement acknowledged;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[1]/input[@name = 'supplierScheduleIds']")
	private WebElement selectCheckBox;

	@FindBy(xpath = "//*[contains(text(), 'Select All ')]/input")
	private WebElement selectAllCheckBox;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[7]")
	private List<WebElement> scheduleCreated;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[9]")
	private List<WebElement> scheduleAcknowledged;

	@FindBy(xpath = "//*[@id = 'schedule']/tbody//td[8]")
	private List<WebElement> scheduleReleased;

	@FindBy(xpath = "//*[@id = 'plugin']")
	private WebElement pdfUrl;

	EventFiringWebDriver driver2 = null;

	public EventFiringWebDriver driver = null;

	public Page_ViewSchedules(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setSchedulesCriteria(String serviceType, String city, int supplier, String releasedIndicator,
			String releasedStatusType, String acknowledgedIndicator, String acknowledgedStatusType,
			String activityIndicator, String activityStatusType, String emailedIndicator, String emailedStatusType)
			throws Exception {
		waitForElementVisibility(scheduleType);
		waitForElementVisibility(bidPeriod);
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(this.city, city);
		clickOn(getSupplierBtn);
		if (supplier != -1)
			selectByIndex(this.supplier, supplier);
		setScheduleStatus(releasedIndicator, releasedStatusType, acknowledgedIndicator, acknowledgedStatusType,
				activityIndicator, activityStatusType, emailedIndicator, emailedStatusType);
		waitForElementVisibility(findBtn);
	}

	public void clickFindBtn() {
		clickOn(findBtn);
	}

	private void setScheduleStatus(String releasedIndicator, String releasedStatusType, String acknowledgedIndicator,
			String acknowledgedStatusType, String activityIndicator, String activityStatusType, String emailedIndicator,
			String emailedStatusType) throws Exception {
		// logger.info("*** ViewSchedulesPage -> setSchedulesStatus method started
		// ***");
		if (releasedIndicator.equals("Yes")) {
			clickOn(this.releasedIndicator);
			for (WebElement releaseType : this.releasedStatusType)
				if (releaseType.getAttribute("value").equals(releasedStatusType)) {
					clickOn(releaseType);
					break;
				}
		}
		if (acknowledgedIndicator.equals("Yes")) {
			clickOn(this.acknowledgedIndicator);
			for (WebElement acknowledgedType : this.acknowledgedStatusType)
				if (acknowledgedType.getAttribute("value").equals(acknowledgedStatusType)) {
					clickOn(acknowledgedType);
					break;
				}
		}
		if (activityIndicator.equals("Yes")) {
			clickOn(this.activityIndicator);
			for (WebElement activityType : this.activityStatusType)
				if (activityType.getAttribute("value").equals(activityStatusType)) {
					clickOn(activityType);
					break;
				}
		}
		if (emailedIndicator.equals("Yes")) {
			clickOn(this.emailedIndicator);
			for (WebElement emailedType : this.emailedStatusType)
				if (emailedType.getAttribute("value").equals(emailedStatusType)) {
					clickOn(emailedType);
					break;
				}
		}
		// logger.info("*** setSchedulesStatus method completed ***");
	}

	public void schedules() throws Exception {
		// logger.info("*** ViewSchedulesPage -> schedules method started ***");
		waitForElementVisibility(serverPrintBtn);
		waitForElementVisibility(localPrintBtn);
		waitForElementVisibility(sendToDiskBtn);
		waitForElementVisibility(regenerateSchedulesBtn);
		waitForElementVisibility(releaseSchedulesBtn);
		waitForElementVisibility(deleteSchedulesBtn);
		waitForElementVisibility(faxBtn);
		// logger.info("*** schedules method completed ***");
	}

	public void notifyAndVerifyNotes(String notifyIndicator, String confirmedWith, String hotelNotes) {
		waitForPageLoad(getDriver());
		if (!scheduleRows.get(0).getAttribute("class").equals("empty")) {
			// Click on Notify Link
			clickOn(actionLink);
			clickOn(findElementByXpath(notifyIndicatorXpath1 + notifyIndicator + notifyIndicatorXpath2));
			typeInText(this.confirmedWith, confirmedWith);
			typeInText(this.hotelNotes, hotelNotes);
			clickOn(notify_okBtn);
			waitForPageLoad(getDriver());
			// Click on View Notes Link
			clickOn(actionLink);
			assertTrue(notes.get(0).getText().contains(confirmedWith), "confirmedWith Mismatch!");
			assertTrue(notes.get(1).getText().contains(hotelNotes), "hotelNotes Mismatch!");
			clickOn(notes_closeBtn);
		}
	}

	public void verifyAcknowledgeDateAndTime() {
		assertEquals(acknowledged.getText(), getCurrentTimeInET(), "AcknowledgedTime Mismatch!");
	}

	public boolean verifyAcknowledgedDateExists() {
		if (acknowledged.getText().isEmpty())
			return false;
		else
			return true;
	}

	public void verifyResults(String city, String serviceType, String supplier) {
		int noOfRows = scheduleRows.size();
		if (Integer.parseInt(supplier) != -1) {
			Select select = new Select(this.supplier);
			supplier = select.getOptions().get(Integer.parseInt(supplier)).getText();
		}
		if (noOfRows > 0) {
			if (!city.isEmpty())
				for (WebElement thisCity : scheduleCity)
					assertEquals(thisCity.getText().trim(), city, "City Mismatch!");

			if (!supplier.equals("-1"))
				for (WebElement thisSupplier : scheduleSupplier)
					assertEquals(thisSupplier.getText(), supplier);

			for (WebElement thisServiceType : scheduleSupplierType)
				if (!serviceType.equals("BOTH"))
					assertEquals(thisServiceType.getText().trim(), serviceType);
				else if (!thisServiceType.getText().trim().equals("HOTEL")
						&& !thisServiceType.getText().trim().equals("GT"))
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"ServiceType Mismatch! Showing: " + thisServiceType);
		}
	}

	public void regenerateSchedules() {
		clickOn(selectAllCheckBox);
		clickOn(regenerateSchedulesBtn);
		waitForPageLoad(getDriver());
	}

	public void releaseSchedules() {
		clickOn(selectCheckBox);
		clickOn(releaseSchedulesBtn);
		waitForPageLoad(getDriver());
	}

	public void verifyRegenerateDateAndTime() {
		int noOfRows = scheduleRows.size();
		for (int i = 0; i < noOfRows; i++) {
			if (scheduleAcknowledged.get(i).getText().isEmpty()) {
				assertEquals(scheduleCreated.get(i).getText(), getCurrentTimeInET(), "CreatedTime Mismatch!");
				break;
			}
		}
	}

	public void verifyReleaseDateAndTime() {
		assertEquals(scheduleReleased.get(0).getText(), getCurrentTimeInET(), "ReleaseTime Mismatch!");
	}

	public String getReleasedDate() {
		return (scheduleReleased.get(0).getText().split(" ")[0]).replace("-", " ");
	}

	public void deleteSchedule() {
		int noOfRows = scheduleRows.size();
		clickOn(selectCheckBox);
		clickOn(deleteSchedulesBtn);
		waitForPageLoad(getDriver());
		int newRows = scheduleRows.size();
		assertTrue(newRows == noOfRows - 1, "Schedule Not Deleted!");
	}

	/**
	 * @param footerText
	 * @param standardNotesForBidPeriod
	 * @param specialNotesForBidPeriod
	 * @param username
	 * @param password
	 * @throws Exception
	 * 
	 *                   This method is used to click on Supplier Link in View
	 *                   Schedules Page and subsequently verify Footer Text,
	 *                   Standard Notes for Bid Period and Special Notes for
	 *                   BidPeriod in the PDF
	 * 
	 *                   Before clicking on the supplier link, this code fetches the
	 *                   size of download_files directory to later use it to verify
	 *                   success/failure of the download On clicking the link, a new
	 *                   window is opened with pdf file loaded The code fetches the
	 *                   src url embeded in the iframe tag and passes it to
	 *                   createDriverAndLogin method Once Login is complete, pdf is
	 *                   downloaded using downloadPDF method. It finally verifies
	 *                   the latest size of download_files directory to check if
	 *                   latest file is downloaded successfully or not If the
	 *                   download is successful, the code fetches the text from the
	 *                   pdf and verifies if the ScheduleNotes are visible in the
	 *                   PDF or not If the download fails, the status of testcase is
	 *                   marked as FAILED.
	 */
	public void clickSupplierLinkAndVerifyNotes(String footerText, String standardNotesForBidPeriod,
			String specialNotesForBidPeriod, String username, String password) throws Exception {
		String currentWindowHandle = getDriver().getWindowHandle();
		int countBeforeDownload = queryDirectorySize(downloadFilepath);
		clickOn(scheduleSupplier.get(0));
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		switchToNewWindow(currentWindowHandle);
		String url = driver.findElements(By.tagName("iframe")).get(0).getAttribute("src");
		url = URL.substring(0, 25) + url;
		System.out.println(url);
		createDriverAndLogin(username, password, url);
		System.out.println("Test");
		downloadPDF("", "ScheduleNotes" + "_" + getCurDate() + "_" + getCurTime());
		int countAfterDownload = queryDirectorySize(downloadFilepath);
		System.out.println();
		if (countAfterDownload > countBeforeDownload) {
			String pdfContent = fetchPDFContent(getTheLatestFile(downloadFilepath, "pdf"));
			assertTrue(pdfContent.contains(footerText), "Given Footer Text does not exist!");
			assertTrue(pdfContent.contains(standardNotesForBidPeriod),
					"Given Standard Notes for Bid Period does not exist!");
			assertTrue(pdfContent.contains(specialNotesForBidPeriod),
					"Given Special Notes for Bid Period does not exist!");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Supplier PDF download failed! Could not verify ScheduleNotes");
		}
		getDriver().close();
		switchToWindow(currentWindowHandle);
	}

	public void setSchedulesCriteria(String bidPeriodValue, String serviceType, String city, String supplierValue,
			String releasedIndicator, String releasedStatusType, String acknowledgedIndicator,
			String acknowledgedStatusType, String activityIndicator, String activityStatusType, String emailedIndicator,
			String emailedStatusType) throws Exception {
		waitForElementVisibility(scheduleType);
		waitForElementVisibility(bidPeriod);
		selectByVisibleText(bidPeriod, bidPeriodValue);
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(this.city, city);
		clickOn(getSupplierBtn);
		selectByVisibleText(this.supplier, supplierValue);
		setScheduleStatus(releasedIndicator, releasedStatusType, acknowledgedIndicator, acknowledgedStatusType,
				activityIndicator, activityStatusType, emailedIndicator, emailedStatusType);
		waitForElementVisibility(findBtn);
	}

	
	public void clickSupplierLinkAndVerifyNotesPDF(String footerText, String standardNotesForBidPeriod,
			String specialNotesForBidPeriod,   String supplierValue) throws Exception {
		String renamedFileName = "";
		String currentWindowHandle = getDriver().getWindowHandle();
		int countBeforeDownload = queryDirectorySize(downloadFilepath);
		clickOn(scheduleSupplier.get(0));
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		switchToNewWindow(currentWindowHandle);
		getDriver().switchTo().frame(driver.findElement(By.tagName("iframe")));
		System.out.println("Switched To PDF Window");
		driver.findElement(By.id("open-button")).click();
		Thread.sleep(6000);
		int countAfterDownload = queryDirectorySize(downloadFilepath);
		if (countAfterDownload > countBeforeDownload) {
			System.out.println("File Downlaoded Successfully");
			String pdfContent = fetchPDFContent(getTheLatestFile(downloadFilepath, "pdf"));
			assertTrue(pdfContent.contains(footerText), "Given Footer Text does not exist!");
			assertTrue(pdfContent.contains(standardNotesForBidPeriod),
					"Given Standard Notes for Bid Period does not exist!");
			assertTrue(pdfContent.contains(specialNotesForBidPeriod),
					"Given Special Notes for Bid Period does not exist!");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Required text matching with the downloaded PDF");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Supplier PDF download failed! Could not verify ScheduleNotes");
		}
		switchToWindow(currentWindowHandle);
	}
	
	public void clickSupplierLinkAndVerifyNotesPDF() throws Exception {
		String currentWindowHandle = getDriver().getWindowHandle();
		int countBeforeDownload = queryDirectorySize(downloadFilepath);
		clickOn(scheduleSupplier.get(0));
			Thread.sleep(3000);
		switchToNewWindow(currentWindowHandle);
		getDriver().switchTo().frame(driver.findElement(By.tagName("iframe")));
		System.out.println("Switched To PDF Window");
		driver.findElement(By.id("open-button")).click();
		Thread.sleep(6000);
		int countAfterDownload = queryDirectorySize(downloadFilepath);
		if (countAfterDownload > countBeforeDownload) {
			System.out.println("File Downlaoded Successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"File Downlaoded Successfully");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Supplier PDF download failed! Could not verify ScheduleNotes");
		}
		switchToWindow(currentWindowHandle);
	}
}
