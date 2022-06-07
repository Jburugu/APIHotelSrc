package com.APIHotels.pages.airlines;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class FindReservationBTPage extends BasePage {

	public EventFiringWebDriver driver = null;
	// OPERATIONS -- BUSINESS TRAVEL -- FIND RESERVATION

	@FindBy(how = How.XPATH, using = "//td[text()='Find Reservation']")
	public WebElement findReservationLink;

	@FindBy(how = How.ID, using = "bizTravelForm:employeeIdArea:employeeId")
	public WebElement employeeId;

	@FindBy(how = How.ID, using = "bizTravelForm:findResLocCdArea:findResLocCd")
	public WebElement location;

	@FindBy(how = How.ID, using = "bizTravelForm:findResNameArea:findResName")
	public WebElement requestor;

	@FindBy(how = How.ID, using = "bizTravelForm:findResResvIdArea:findResResvId")
	public WebElement reservationId;

	@FindBy(how = How.XPATH, using = "//select[contains(@name,'bizTravelForm:j')]")
	public WebElement roomType;

	@FindBy(how = How.CSS, using = "select[name*='tzOptArea']")
	public WebElement timeFormat;

	@FindBy(how = How.CSS, using = "input[id*='startRetrieveArea']")
	public WebElement startDate;

	@FindBy(how = How.CSS, using = "input[id*='startRetrieveArea']+img")
	public WebElement startDateCalendar;

	@FindBy(how = How.CSS, using = "input[id*='endDateIdInputDate']")
	public WebElement endDate;

	@FindBy(how = How.CSS, using = "input[id*='endDateIdInputDate']+img")
	public WebElement endDateCalendar;

	@FindBy(how = How.ID, using = "bizTravelForm:findTenantList:tenantMenuId")
	public WebElement company;

	@FindBy(how = How.CSS, using = "input[value='Retrieve']")
	public WebElement retrievebutton;

	@FindBy(how = How.XPATH, using = "//td[text()='Operations']")
	public WebElement operationsLink;

	// .//*[@id='bizTravelForm:resvTable']/tbody/tr[1]/td[1]
	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr")
	public List<WebElement> reservationList;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]/td[6]/span[2]")
	public WebElement reservationStatus;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:findGlobalMessages']/dt/span")
	public WebElement noRecordsFoundMessage;

	@FindBy(how = How.XPATH, using = "//input[@value='Save']")
	private WebElement saveBtn;

	@FindBy(id = "j_id317")
	public WebElement editOkBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='cancelBizGrid']/tfoot/tr/td/span/a")
	public WebElement okBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr")
	public List<WebElement> reservationTable;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]//td//a[text()='Cancel']")
	public WebElement cancelBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]//td//a[text()='Edit']")
	public WebElement editBtn;

	@FindBy(how = How.ID, using = "bizTravelForm:deptDtArea:departureDtInputDate")
	public WebElement departureDate;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'subscriptions')]")
	public WebElement room;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable:0:employeeNbr1Area:employeeNbr1")
	public WebElement empId;

	@FindBy(how = How.XPATH, using = "//textarea[contains(@id,'notes')]")
	public WebElement notes;

	@FindBy(how = How.ID, using = "bizTravelForm:print")
	public WebElement generateFax;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]//td//a[text()='Confirm']")
	public WebElement confirmLink;

	@FindBy(how = How.ID, using = "bizTravel:cancelConfNum:cancelConfNumber")
	public WebElement cancellationNumber;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]//td//a[text()='Notes']")
	public WebElement notesLink;

	@FindBy(how = How.XPATH, using = "//tbody[@id='bizTravelNotesTable:tb']/tr[4]/td[3]")
	public WebElement notesTable;

	String resvTable = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]";

	@FindBy(xpath = "//*[@title = 'VIP/Executive Reservation']")
	public WebElement vipExecutiveReservationIndicator;

	@FindBy(how = How.XPATH, using = "//div/span[text()='Reservation Group Creation Notes']")
	public WebElement reservationGroupCreationNotes;

	@FindBy(how = How.XPATH, using = "//div/span[text()='Reservation Notes']")
	public WebElement reservationNotes;

	@FindBy(how = How.XPATH, using = "//table[@id='bizTravelNotesTable'][1]//td[3]")
	public WebElement conferenceNotes;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'confirm')]")
	public WebElement confirmationNumber;

	String currentWindowHandle = null;

	String statusXpath1 = "//*[contains(text(), '";
	String statusXpath2 = "')]/following-sibling::td/span[2]";
	String statusXpath3 = "')]/following-sibling::td/span[1]";
	String guestXpath = "')]/following-sibling::td[4]";

	/*
	 * String cancelXpath1 = "//*[contains(text(), '"; String cancelXpath2 =
	 * "')]/following-sibling::td[12]/div[2]/a";
	 */

	String cancelXpath1 = "//*[contains(text(), '";
	String cancelXpath2 = "')]/following-sibling::td[8]/div//a[text()='Cancel']";
	String editXpath = "')]/following-sibling::td[8]/div[1]";

	String supplierXpathRow1 = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[2]//a";
	String supplierXpathRow2 = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[4]//a";

	String titleXpath1 = "//*[contains(text(), '";
	String titleXpath2 = "')]/../following-sibling::tr/td";
	String supplierNameXpath = "')]/../following-sibling::tr/td/text()[2]";

	String firstrowResvId = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]/td[1]";
	String thridrowResvId = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[3]/td[1]";
	String firstrowStatus = "//*[@id='bizTravelForm:resvTable']/tbody/tr[1]/td[6]/span[2]";
	String thirdrowStatus = "//*[@id='bizTravelForm:resvTable']/tbody/tr[3]/td[6]/span[2]";

	String guestEmpId1 = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[1]//td[contains(text(),'1.')]";
	String cancelLink = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[3]//td//a[text()='Cancel']";
	String editlink = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[3]//td//a[text()='Edit']";
	String confirmlink = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[3]//td//a[text()='Confirm']";
	String notelink = ".//*[@id='bizTravelForm:resvTable']/tbody/tr[3]//td//a[text()='Notes']";

	@FindBy(linkText = "Close Window")
	public WebElement closeWindowLinkText;

	public FindReservationBTPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void findReservation(String empIdValue, String locationValue, String requestorValue,
			String reservationIdValue, String roomTypeValue, String timeFormatValue) {
		typeInText(employeeId, empIdValue);
		Assert.assertEquals(employeeId.getAttribute("value"), empIdValue, "Employee id mismatch");

		typeInText(location, locationValue);
		Assert.assertEquals(location.getAttribute("value"), locationValue, "Location mismatch");

		typeInText(requestor, requestorValue);
		Assert.assertEquals(requestor.getAttribute("value"), requestorValue, "requestor value mismatch");

		enterSearchCriteria(reservationIdValue);

		selectByVisibleText(roomType, roomTypeValue);
		// Assert.assertEquals(roomType.getAttribute("value"), roomTypeValue,
		// "roomtype mismatch");

		selectByVisibleText(timeFormat, timeFormatValue);

	}

	public void company(String companyValue) {
		selectByValue(company, companyValue);
		// Assert.assertEquals(company.getAttribute("value"), companyVal,
		// "company mismatch");
	}

	public void verifyRetrieveBtn() {
		waitForElementVisibility(retrievebutton);
	}

	public void enterSearchCriteria(String reservationIdValue) {

		typeInText(reservationId, reservationIdValue);
		Assert.assertEquals(reservationId.getAttribute("value"), reservationIdValue, "reservation value mismatch");

		waitForElementVisibility(startDateCalendar);
		typeInText(startDate, currentDate());
		Assert.assertEquals(startDate.getAttribute("value"), currentDate(), "startdate mismatch");

		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, currentDate());
		Assert.assertEquals(endDate.getAttribute("value"), currentDate(), "enddate mismatch");
	}

	public void enterSearchCriteria() {
		typeInText(startDate, currentDate());
		typeInText(endDate, currentDate());
	}

	public void clickOnRetrieveButton() {
		clickOn(retrievebutton);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyReservationExists(String reservationID, String expectedResult) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int reservationListSize = reservationList.size();

		if (reservationListSize == 0) {
			Assert.assertEquals(noRecordsFoundMessage.getText(), "No Records Found",
					"No Records Found Message is not displayed and the reservation doesnot exist ");
		} else {
			String resv = findElementByXpath(firstrowResvId).getText();
			String resvStatus = findElementByXpath(firstrowStatus).getText();

			if (reservationListSize <= 2) {
				Assert.assertEquals(resv, reservationID, "Reservation doesnot exist");
				Assert.assertEquals(resvStatus, expectedResult, "Reservation status is not " + reservationStatus);

			} else if (reservationListSize > 2) {
				String resv2 = findElementByXpath(thridrowResvId).getText();
				String resvStatus2 = findElementByXpath("thirdrowStatus").getText();
				Assert.assertEquals(resv, reservationID, "Reservation doesnot exist");
				Assert.assertEquals(resvStatus, expectedResult, "Reservation status is not " + reservationStatus);
				Assert.assertEquals(resv2, reservationID, "Reservation doesnot exist");
				Assert.assertEquals(resvStatus2, expectedResult, "Reservation status is not " + reservationStatus);
			}

		}
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processCancellation(String reservationId, String roomType) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		List<WebElement> cancelLinks = findElementsByXpath(cancelXpath1 + reservationId + cancelXpath2);
		int i = 1;
		currentWindowHandle = getDriver().getWindowHandle();
		for (WebElement cancelLink : cancelLinks) {
			boolean isMultiple = findElementByXpath(".//*[@id='bizTravelForm:resvTable']/tbody/tr[" + i + "]//td[5]")
					.getText().contains("1.");

			if (roomType.equals("Single") && !isMultiple)
				clickOn(cancelLink);

			if (roomType.equals("Single") && isMultiple)
				continue;

			if (roomType.equals("Double") && isMultiple)
				clickOn(cancelLink);

			if (roomType.equals("Double") && !isMultiple)
				continue;

			if (roomType.equals("Both")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(cancelXpath1 + reservationId + cancelXpath2)));
				WebElement cancel = findElementByXpath(cancelXpath1 + reservationId + cancelXpath2);
				clickOn(cancel);
			}

			waitForPageLoad(driver);
			switchToNewWindow(currentWindowHandle);
			clickOn(saveBtn);
			clickOn(okBtn);
			unExpectedAcceptAlert();
			switchToWindow(currentWindowHandle);
			i = i + 2;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		}

	}

	public void processCancellation(String cancelReservForEmployees) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		List<String> employeeIds = new ArrayList<String>(Arrays.asList(cancelReservForEmployees.split(",")));
		for (String emp : employeeIds) {
			currentWindowHandle = getDriver().getWindowHandle();
			clickOn(findElementByXpath(cancelXpath1 + emp + cancelXpath2));
			waitForPageLoad(driver);
			switchToNewWindow(currentWindowHandle);
			clickOn(saveBtn);
			clickOn(okBtn);
			unExpectedAcceptAlert();
			switchToWindow(currentWindowHandle);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		}

	}

	public void cancelLinkGenerateFax(String notesValue) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int count = reservationTable.size();
		String currentWindowHandle = driver.getWindowHandle();
		if (count == 2) {
			clickOn(cancelBtn);

		} else if (count > 2) {
			boolean guestEmpIds = findElementByXpath(guestEmpId1).isDisplayed();
			if (guestEmpIds == true) {
				clickOn(cancelBtn);
			} else
				clickOn(findElementByXpath(cancelLink));
		}
		waitForPageLoad(driver);
		switchToNewWindow(currentWindowHandle);
		typeInText(notes, notesValue);
		clickOn(generateFax);
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
	}

	public void clickEditLink() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int count = reservationTable.size();
		currentWindowHandle = driver.getWindowHandle();
		String currentWindowHandle = getDriver().getWindowHandle();
		if (count == 2) {
			clickOn(editBtn);

		} else if (count > 2) {
			boolean guestEmpIds = findElementByXpath(guestEmpId1).isDisplayed();
			if (guestEmpIds == true) {
				clickOn(editBtn);
			} else
				clickOn(findElementByXpath(editlink));
		}

		switchToNewWindow(currentWindowHandle, 10);
	}

	public void processEdit_RoomTypeChange(String editReservForEmployees) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		List<String> employeeIds = new ArrayList<String>(Arrays.asList(editReservForEmployees.split(",")));
		for (String emp : employeeIds) {
			currentWindowHandle = getDriver().getWindowHandle();
			clickOn(findElementByXpath(cancelXpath1 + emp + editXpath));
			waitForPageLoad(driver);
			switchToNewWindow(currentWindowHandle);
			editReservationFromDoubleToSingle();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		}
	}

	public void editReservationInformation(String departureDateValue, String empValue) {

		typeInText(departureDate, currentDatePlus(1));
		if (room.getAttribute("value").equals("DOUBLE")) {
			selectByValue(room, "SINGLE");
		} else {
			selectByValue(room, "DOUBLE");
		}
		typeInText(empId, empValue);
		clickOn(saveBtn);
		clickOn(editOkBtn);
		unExpectedAcceptAlert();
		switchToWindow(currentWindowHandle);
	}

	public void editReservationFromDoubleToSingle() {
		if (room.getAttribute("value").equals("DOUBLE")) {
			selectByValue(room, "SINGLE");
		} else {
			selectByValue(room, "DOUBLE");
		}
		clickOn(saveBtn);
		unExpectedAcceptAlert();
		clickOn(editOkBtn);
		switchToWindow(currentWindowHandle);
	}

	public void confirmLinkCancellationValue(String cancellationValue, String notesValue) {
		String currentWindowHandle = driver.getWindowHandle();
		clickOnConfirmLink();
		typeInText(cancellationNumber, cancellationValue);
		typeInText(notes, notesValue);
		clickOn(saveBtn);
		switchToWindow(currentWindowHandle);
	}

	public void clickOnConfirmLink() {

		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int count = reservationTable.size();
		if (count == 2) {
			clickOn(confirmLink);

		} else if (count > 2) {
			boolean guestEmpIds = findElementByXpath(guestEmpId1).isDisplayed();
			if (guestEmpIds == true) {
				clickOn(confirmLink);
			} else
				clickOn(findElementByXpath(confirmlink));
		}
	}

	public String clickOnNotesLink() {

		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		String currentWindowHandle = driver.getWindowHandle();
		int count = reservationTable.size();
		if (count == 2) {
			clickOn(notesLink);

		} else if (count > 2) {
			boolean guestEmpIds = findElementByXpath(guestEmpId1).isDisplayed();
			if (guestEmpIds == true) {
				clickOn(notesLink);
			} else
				clickOn(findElementByXpath(notelink));
		}
		switchToNewWindow(currentWindowHandle);
		String value = notesTable.getText();
		String faxNumber = value.substring(Math.max(0, value.length() - 8));
		System.out.println("Fax number" + faxNumber);
		switchToWindow(currentWindowHandle);
		close();
		return faxNumber;

	}

	public void verifyVIPReservation() {
		assertFalse(vipExecutiveReservationIndicator.isEnabled(), "VIP/Executive Reservation is not Disabled!");
	}

	public void verifyStatus(String reservationForEmpAndStatus) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int reservationListSize = reservationList.size();
		if (reservationListSize == 0) {
			Assert.assertEquals(noRecordsFoundMessage.getText(), "No Records Found",
					"No Records Found Message is not displayed and the reservation doesnot exist ");
		} else {
			List<String> confirmationCodes = new ArrayList<String>(
					Arrays.asList(reservationForEmpAndStatus.split(";")));
			for (String confirmationCode : confirmationCodes) {
				String empId = confirmationCode.split(",")[0];
				String confirmationMsg = confirmationCode.split(",")[1];
				String span1Text = findElementByXpath(statusXpath1 + empId + statusXpath2).getText();
				String span2Text = findElementByXpath(statusXpath1 + empId + statusXpath3).getText();
				if (!span2Text.isEmpty())
					assertEquals(span2Text + span1Text, confirmationMsg, "Status is not matching");
				else
					assertEquals(span1Text + span2Text, confirmationMsg, "Status is not matching");
			}
		}
	}

	public void verifyTitle(String reservId, String locationValue, String pickUpDetailValue, String dropOffLocValue,
			String dropOffDetailValue) {
		String expectedTitle = locationValue + " " + pickUpDetailValue + " -- " + dropOffLocValue + " "
				+ dropOffDetailValue;
		String actualTitle = findElementByXpath(titleXpath1 + reservId + titleXpath2).getText().split(":")[1].trim()
				.replace("Supplier", "");
		assertTrue(expectedTitle.equals(actualTitle), "Title Mismatch!");
	}

	public void verifySupplierName(String reservId, String supplierName) {
		String actualSupplierValue = findElementByXpath(titleXpath1 + reservId + titleXpath2).getText().split(":")[2]
				.trim();
		assertTrue(supplierName.equals(actualSupplierValue), "Supplier Mismatch!");
	}

	public void saveEdit() {
		clickOn(saveBtn);
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
	}

	public void closeEditWindow() {
		clickOn(closeWindowLinkText);
		switchToWindow(currentWindowHandle);
	}

	public void verifyCancelAndNotesLink() {
		Assert.assertTrue(cancelBtn.isDisplayed(), "Cancel Link is not displayed");
		Assert.assertTrue(notesLink.isDisplayed(), "Notes link is not displayed");
	}

	public void verifyConferenceNotes(String currentWindowHandle, String notesValue) {
		switchToNewWindow(currentWindowHandle);
		Assert.assertTrue(reservationGroupCreationNotes.isDisplayed(),
				"Reservation Group Creation Notes is not displayed");
		Assert.assertTrue(reservationNotes.isDisplayed(), "Reservation notes is not displayed");
		Assert.assertEquals(conferenceNotes.getText(), notesValue, "entered notes is not displayed");
		close();
		switchToWindow(currentWindowHandle);
	}

	public void verifySupplierNameIncluded(String supplierName) {
		Assert.assertEquals(findElementByXpath(supplierXpathRow1).getText(), supplierName, "Supplier Mismatch");
	}

	public String getSupplierName() {
		return findElementByXpath(supplierXpathRow1).getText();
	}

	public String clickOnNotesLinkAndValidateFaxNumber() {
		currentWindowHandle = clickOnNotesLink();
		switchToNewWindow(currentWindowHandle);
		String value = notesTable.getText();
		String faxNumber = value.substring(Math.max(0, value.length() - 8));
		System.out.println("Fax number" + faxNumber);
		close();
		switchToWindow(currentWindowHandle);
		return faxNumber;
	}

	public void confirmLinkCancellationValue(String cancellationValue, String confirmationValue, String notesValue) {
		String currentWindowHandle = driver.getWindowHandle();
		clickOnConfirmLink();
		switchToNewWindow(currentWindowHandle);
		System.out.println(driver.getTitle());
		if (!cancellationValue.isEmpty()) {
			typeInText(cancellationNumber, cancellationValue);
		} else {
			typeInText(confirmationNumber, confirmationValue);
		}
		typeInText(notes, notesValue);
		clickOn(saveBtn);
		System.out.println(driver.getTitle());
		switchToWindow(currentWindowHandle);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
	}

	public void verifyEmpName(String reservId, String empName, String empId) {
		String actualGuestText = findElementByXpath(statusXpath1 + reservId + guestXpath).getText();
		String actualEmpName = actualGuestText.split("\\(")[0];
		String actualEmpId = actualGuestText.split("\\(")[1].split("\\)")[0];
		assertEquals(actualEmpName, empName, "EmployeeName Mismatch!");
		assertEquals(actualEmpId, empId, "EmployeeId Mismatch!");
	}

	public void findReservationMesa(String reservationIdValue , String tenantName) {

		typeInText(reservationId, reservationIdValue);
		waitForElementVisibility(startDateCalendar);
		typeInText(this.startDate, currentDate());
		waitForElementVisibility(endDateCalendar);
		if(tenantName.equalsIgnoreCase("Virgin Australia")) {
		typeInText(this.endDate, currentDatePlus(1));
		}
		else
		{
			typeInText(this.endDate, currentDate());
		}
		clickOn(retrievebutton);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));

	}

	public void verifyBTStatusAirlines(String tenantName, String status) {
		if(tenantName.equalsIgnoreCase("ExpressJet ERJ")) 
		{
			WebElement firstRowStatus = findElementByXpath("//*[@id='bizTravelForm:resvTable']/tbody/tr[1]/td[7]/span[2]");
			WebElement secondRowStatus = findElementByXpath("//*[@id='bizTravelForm:resvTable']/tbody/tr[3]/td[7]/span[2]");
			if(firstRowStatus.getText().contains(status))
				ExtentTestManager.getTest().log(LogStatus.INFO, "First rows status is as expected");
			if(secondRowStatus.getText().contains(status))
				ExtentTestManager.getTest().log(LogStatus.INFO, "Sceond rows status is as expected");
		}
		else {
		WebElement firstRowStatus = findElementByXpath(firstrowStatus);
		WebElement secondRowStatus = findElementByXpath(thirdrowStatus);
		if(firstRowStatus.getText().contains(status))
			ExtentTestManager.getTest().log(LogStatus.INFO, "First rows status is as expected");
		if(secondRowStatus.getText().contains(status))
			ExtentTestManager.getTest().log(LogStatus.INFO, "Sceond rows status is as expected");
		}
	}
	
	public void findReservationAF(String reservationIdValue) {
		
		typeInText(reservationId, reservationIdValue);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			}
}
