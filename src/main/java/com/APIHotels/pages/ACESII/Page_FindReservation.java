package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.TestException;
import org.xml.sax.SAXException;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_FindReservation extends BasePage {

	@FindBy(css = "#opsFindResv > a")
	private WebElement findReservationLink;

	private String serviceTypeXpath1 = "//*[text()='";
	private String serviceTypeXpath2 = "']/input";

	@FindBy(id = "tripNumber")
	private WebElement tripNumber;

	@FindBy(id = "locCD")
	private WebElement location;

	@FindBy(id = "crewId")
	private WebElement crewId;

	@FindBy(id = "arrFlightNbr")
	private WebElement arrivalFlight;

	@FindBy(id = "depFlightNbr")
	private WebElement departureFlight;

	@FindBy(id = "includeHotelGTId")
	private WebElement includeHotelGTIndicator;

	@FindBy(id = "fromDt")
	private WebElement startDate;

	@FindBy(id = "toDt")
	private WebElement endDate;

	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Retrieve']")
	private WebElement retrieveBtn;

	@FindBy(xpath = "//div[@class='Aces_Table']/div")
	private WebElement noRecordsFound;

	@FindBy(xpath = ".//*[@id='row']/tbody/tr")
	public List<WebElement> tripTable;

	@FindBy(xpath = "//*[@id='row']/tbody/tr/td[6]")
	public WebElement tripStatus;

	@FindBy(xpath = "//*[@id='row']/tbody/tr/td[7]")
	public WebElement supplierName;

	String statusXpath1 = "//*[contains(text(), '";
	String statusXpath2 = "')]/following-sibling::td[1]";

	String reservationListXpath = ".//*[@id='row']/tbody/tr";
	String reservationRows = "]/td";
	String reservationStatus = "/td[6]";

	@FindBy(xpath = "//a[contains(text(),'Reassign')]")
	private WebElement reassignLink;

	@FindBy(xpath = "//*[@id='row']/tbody/tr/td[17]/a[1]")
	private WebElement notesLink;

	String actionXpath1 = "//*[@id='row']/tbody/tr[";
	String actionXpath2 = "]/td[18]/a[1]";

	String reservationStatusXpath1 = "//*[@id='row']/tbody/tr[";
	String reservationStatusXpath2 = "]/td[6]";
	
	@FindBy(xpath="//table[@id='resvAction']//tr/td[6]/a")
	private List<WebElement> links_InNotes;
	
	@FindBy (xpath="//*[@id='row']/tbody/tr/td[16]/a")
	private WebElement BTResignButton;
	
	@FindBy(xpath = "//*[@id='row']/tbody/tr/td[contains(text(),'Cancelled')]/following-sibling::td[9]/a")
	private WebElement clickOnPCnotesLink;
	
	public EventFiringWebDriver driver = null;
	
	@FindBy (xpath= "//input[contains(@id,'confirmCode')]")
	private List<WebElement> textbox_ConfirmCode;
	
	@FindBy (id ="buttonFinish")
	private WebElement button_Finish;
	
	public Page_FindReservation(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickFindReservationLink() throws Exception {
		clickOn(findReservationLink);
	}

	// SmokeTest FindReservation
	public void findReservation(String serviceType, String tripNo, String startDate, String endDate) throws Exception {
		waitForElementVisibility(crewId);
		waitForElementVisibility(arrivalFlight);
		waitForElementVisibility(departureFlight);
		waitForElementVisibility(includeHotelGTIndicator);
		waitForElementVisibility(location);
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(this.tripNumber, tripNo);
		typeInText(this.startDate, startDate);
		waitForElementVisibility(this.endDate);
		// typeInText(this.endDate, endDate);
		clickOn(retrieveBtn);
	}

	// Chitra's merged code
	public void findReservation(String serviceType, String reservId) throws IOException {
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(tripNumber, reservId);
		typeInText(startDate, currentDate());
		takeScreenshots();
		clickOn(retrieveBtn);
		takeScreenshots();
	}

	public void findReservationReg(String serviceType, String locationValue, String startDateValue) throws Exception {
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(location, locationValue);
		typeInText(this.startDate, startDateValue);
		clickOn(retrieveBtn);
	}

	public void enterLocationStartDateToFindReservation(String serviceType, String locationValue,
			String startDateValue) {
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(location, locationValue);
		typeInText(this.startDate, startDateValue);
		clickOn(retrieveBtn);
	}

	public void findReservationAndVerifyStatus(String serviceType, String reservId, String expectedValue) throws IOException {
		findReservation(serviceType, reservId);
		verifyStatus(expectedValue);
	}

	public void findReservationWithTripId(String serviceType, String tripIDValue, String startDateValue) {
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		typeInText(tripNumber, tripIDValue);
		typeInText(this.startDate, startDateValue);
		clickOn(retrieveBtn);
	}

	public void verifyStatus(String roomTypeAndStatus) {
		List<String> confirmationCodes = new ArrayList<String>(Arrays.asList(roomTypeAndStatus.split(";")));
		for (String confirmationCode : confirmationCodes) {
			String empId = confirmationCode.split(",")[0];
			String confirmationMsg = confirmationCode.split(",")[1];
			// String statusText =
			// findElementByXpath(statusXpath1+empId+statusXpath2).getText();
			List<WebElement> statuses = findElementsByXpath(statusXpath1 + empId + statusXpath2);
			int size = statuses.size();
			boolean matchesStatus = false;
			for (int i = 0; i < size; i++) {
				String statusText = statuses.get(i).getText();
				if (statusText.equals(confirmationMsg)) {
					matchesStatus = true;
					break;
				}
			}
			assertTrue(matchesStatus, "Status does not match!");
		}
	}

	public void findReservationAndVerifyStatus_ConferenceRoom(String serviceType, String reservId,
			String expectedValue) throws IOException {
		findReservation(serviceType, reservId);
		int tripTableSize = tripTable.size();
		if (tripTableSize >= 1) {
			for (int i = 1; i <= tripTableSize; i++) {
				String actualResult = findElementByXpath(reservationListXpath + "[" + i + "]" + reservationStatus)
						.getText();
				if (expectedValue.contains(","))
					expectedValue = expectedValue.split(",")[i - 1];
				assertEquals(actualResult, expectedValue, "reservation is not booked");
			}
		} else {
			String expectedResult = "No Record(s) Found ";
			assertEquals(noRecordsFound.getText().trim(), expectedResult.trim(), "Records found");
		}
	}

	public int verifyReservationExists(String adhocReservation, String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String hotelName) {
		int reservationRow = 0;
		List<WebElement> row = findElementsByXpath(reservationListXpath);
		for (int i = 1; i <= row.size(); i++) {
			boolean trip = false;
			boolean checkInDate = false;
			boolean checkOutDate = false;
			boolean supplier = false;
			List<WebElement> column = findElementsByXpath(reservationListXpath + "[" + i + reservationRows);
			for (int j = 0; j < column.size(); j++) {
				if (!trip)
					trip = column.get(j).getText().equals(adhocReservation);
				if (!checkInDate) {
					String date = checkInDateValue.substring(0, 2);
					String month = checkInDateValue.substring(3, 6);
					String year = checkInDateValue.substring(9, 11);
					System.out.println("checkInDate : " + date + month + year + " " + checkInTimeValue);
					checkInDate = column.get(j).getText().equals(date + month + year + " " + checkInTimeValue);
				}
				if (!checkOutDate) {
					String date = checkOutDateValue.substring(0, 2);
					String month = checkOutDateValue.substring(3, 6);
					String year = checkOutDateValue.substring(9, 11);
					System.out.println("checkOutDate : " + date + month + year + " " + checkOutTimeValue);
					checkOutDate = column.get(j).getText().equals(date + month + year + " " + checkOutTimeValue);
				}
				if (!supplier)
					supplier = column.get(j).getText().equals(hotelName);
			}
			reservationRow++;
			if (trip && checkInDate && checkOutDate && supplier == true) {
				return reservationRow;
			}
		}
		return reservationRow;
	}

	public void verifyReservationStatus(int reservationRow, String expectedResult) {
		String status = findElementByXpath(reservationListXpath + "[" + reservationRow + reservationStatus).getText();
		assertEquals(status, expectedResult, "Expected result is not " + expectedResult);
	}

	public void clickReassignSupplier() {
		clickOn(reassignLink);
	}

	public int verifyReservation(String departmentValue, String empName, String empID, String hotelName,
			String arrivalDate, String arrivaTime) {
		int reservationRow = 0;
		List<WebElement> row = findElementsByXpath(reservationListXpath);
		for (int i = 1; i <= row.size(); i++) {
			boolean crew = false;
			boolean supplier = false;
			boolean arrivalFlightTime = false;
			List<WebElement> column = findElementsByXpath(reservationListXpath + "[" + i + reservationRows);
			for (int j = 0; j < column.size(); j++) {
				if (!crew)
					crew = column.get(j).getText().contains(departmentValue + " " + empName + " (" + empID + ")");
				if (!supplier)
					supplier = column.get(j).getText().contains(hotelName);
				if (!arrivalFlightTime) {
					System.out.println("checkInDate : " + arrivalDate + " " + arrivaTime);
					arrivalFlightTime = column.get(j).getText().equals(arrivalDate + " " + arrivaTime);
				}
			}
			reservationRow++;
			if (crew && supplier && arrivalFlightTime == true)
				return reservationRow;
		}
		return reservationRow;
	}

	public void verifyReservationStatusPA(int reservationRow, String expectedResult) {
		int tripTableSize = tripTable.size();
		System.out.println("Size of the table : " + tripTableSize);
		if (tripTableSize >= 1) {
			verifyReservationStatus(reservationRow, expectedResult);
		} else {
			throw new TestException("No Record Found on Find Reservation page.");
		}
	}

	/*
	 * This Method is used to click on Assign/Reassign link of trip from table
	 * which matches with the given status parameter
	 */
	public void getReservationStatus(String status) {
		boolean reservationListStatus = false;
		int tripTableSize = tripTable.size();
		System.out.println("Size of the table : " + tripTableSize);
		if (tripTableSize >= 1) {
			for (int tablerow = 1; tablerow <= tripTableSize; tablerow++) {
				WebElement tripStatus = findElementByXpath(
						reservationStatusXpath1 + tablerow + reservationStatusXpath2);
				String tripStatuss = tripStatus.getText();
				if (tripStatuss.equalsIgnoreCase(status)) {
					WebElement action = findElementByXpath(actionXpath1 + tablerow + actionXpath2);
					clickOn(action);
					reservationListStatus = true;
					break;
				}
			}
			if (!reservationListStatus)
				throw new TestException("No Record Found with Pending Assignment Reservation Status");

		} else {
			throw new TestException("No Record Found on Find Reservation page.");
		}

	}

	public void verifySupplier(String supplierValue) {

		String supplier = supplierName.getText();
		if (supplier.equalsIgnoreCase(supplierValue)) {
			System.out.println("Supplier successfully changed");
		} else {
			throw new TestException("Supplier Not Changed");
		}
	}

	public void clickOnNotesLink() {

		clickOn(notesLink);
	}

	public void enterConfirmationCode(String confirmCode) {
		typeInText(textbox_ConfirmCode.get(0), confirmCode);
		textbox_ConfirmCode.get(0).sendKeys(Keys.TAB);
		clickOnFinishButton();
		try {
			driver.findElement(By.id("alertOK")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		unExpectedAcceptAlert();
	}
	
	public void clickOnFinishButton() {
		clickOn(button_Finish);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitTime(3000);
		
	}
	public void getReservationBTStatus(String status) throws IOException {
		boolean reservationListStatus = false;
		int tripTableSize = tripTable.size();
		System.out.println("Size of the table : " + tripTableSize);
		takeScreenshots();
		if (tripTableSize >= 1) {
			for (int tablerow = 1; tablerow <= tripTableSize; tablerow++) {
				WebElement tripStatus = findElementByXpath(
						reservationStatusXpath1 + tablerow + reservationStatusXpath2);
				String tripStatuss = tripStatus.getText();
				if (tripStatuss.equalsIgnoreCase(status)) {
					reservationListStatus = true;
					ExtentTestManager.getTest().log(LogStatus.INFO, "Required reservation status found");
					break;
				}
			}
			if (!reservationListStatus)
				throw new TestException("No Record Found with  Status");

		} else {
			throw new TestException("No Record Found on Find Reservation page.");
		}

	}

	public String verifyViewNotes(String matchingText) throws Exception {
		String winHandleBefore = getDriver().getWindowHandle();
		notesLink.click();
		getDriver().manage().window().maximize();
		takeScreenshots();
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
		getDriver().manage().window().maximize();
		String bodyText = driver.findElement(By.tagName("body")).getText();
		if (bodyText.contains(matchingText)) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancellation Confirmation code found in the notes");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Cancellation Confirmation code not found in the notes");

		}
		int links_InNotesSize = links_InNotes.size() - 1;
		String faxNumber = links_InNotes.get(links_InNotesSize).getText();
		// getDriver().close();
		// getDriver().switchTo().window(winHandleBefore);
		return faxNumber;
	}

	public void clickOnBTGTResignButton() {
		clickOn(BTResignButton);
	}

	public String verifyBTPCViewNotes(String matchingText) throws Exception {
		String winHandleBefore = getDriver().getWindowHandle();
		clickOnPCnotesLink.click();
		getDriver().manage().window().maximize();
		takeScreenshots();
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
		getDriver().manage().window().maximize();
		String bodyText = driver.findElement(By.tagName("body")).getText();
		if (bodyText.contains(matchingText)) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancellation Confirmation code found in the notes");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Cancellation Confirmation code not found in the notes");

		}
		int links_InNotesSize = links_InNotes.size() - 1;
		String faxNumber = links_InNotes.get(links_InNotesSize).getText();
		// getDriver().close();
		// getDriver().switchTo().window(winHandleBefore);
		return faxNumber;
	}

	
	public void verifyReservationStatus(String status) {
		int tripTableSize = tripTable.size();
		System.out.println("Size of the table : " + tripTableSize);
		if (tripTableSize >= 1) {
			for (int tablerow = 1; tablerow <= tripTableSize; tablerow++) {
				WebElement tripStatus = findElementByXpath(
						reservationStatusXpath1 + tablerow + reservationStatusXpath2);
				String tripStatuss = tripStatus.getText();
				try {
					takeScreenshots();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (tripStatuss.equalsIgnoreCase(status)) {
					ExtentTestManager.getTest().log(LogStatus.INFO, status+" status is matcing");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, status+" status is not matcing");
				}
			}
	}
	}
}
