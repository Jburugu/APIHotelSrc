package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_PendingConfirmations extends BasePage {

	@FindBy(name = "dashTenantId")
	private WebElement tenants;

	@FindBy(name = "timeZone")
	private WebElement timeFormat;

	@FindBy(name = "confirmRefresh")
	private WebElement refreshBtn;

	@FindBy(xpath = "//*[@id = 'confirmAlertsList']/tbody/tr")
	private List<WebElement> confirmAlertListTableRows;

	@FindBy(xpath = "//*[@id = 'confirmAlertsList']/tbody/tr/td[3]/a")
	private WebElement tripLink;

	@FindBy(xpath = "//*[@id='confirmAlertsList']/tbody/tr/td[2]")
	private WebElement statusType;

	@FindBy(id = "confirmCode0")
	private WebElement Assignment_ConfirmationCode;

	@FindBy(id = "confirmCode")
	private WebElement confirmCodeAtoA;

	@FindBy(id = "confirmationCode")
	private WebElement confirmationCode;

	@FindBy(id = "addNotes0")
	private WebElement hotelNotes;

	@FindBy(id = "Confirmation_OK")
	private WebElement confirmation_OkBtn;

	@FindBy(css = "#PendingAlert > form > table > tbody > tr:nth-child(4) > td > button:nth-child(2)")
	private WebElement confirmation_CancelBtn;

	@FindBy(id = "buttonFinish")
	private WebElement Assignment_finishBtn;

	@FindBy(xpath = "//*[@name = 'buttonFlag' and @value = 'Ignore']")
	public WebElement ignoreBtn;

	@FindBy(id = "buttonCancel")
	private WebElement Assignment_CancelBtn;

	@FindBy(xpath = ".//*[@id='guestDiv']/table/tbody/tr")
	public List<WebElement> roomDetailsTable;

	@FindBy(id = "globalId0")
	public WebElement hotelProvidersLink;

	@FindBy(id = "globalId1")
	public WebElement hotelProvidersLink1;

	@FindBy(id = "alertOK")
	public WebElement okBtn;

	@FindBy(id = "notesId")
	public WebElement notes;

	@FindBy(id = "vendorNotesId")
	public WebElement vendorNotes;

	@FindBy(id = "resvPayMethod")
	public WebElement billingMethod;

	@FindBy(xpath = ".//*[@id='confirmAlertsList']/thead/tr/th[3]/img")
	public WebElement trip;

	@FindBy(id = "_gfText")
	public WebElement tripSearchField;

	@FindBy(xpath = "//ul[@id='_gfMenuBuilder']/li[5]")
	public WebElement textFilters;

	@FindBy(xpath = "//ul[@id='_gfMenuBuilder']/li[5]/ul/li[2]/a")
	public WebElement containsTextFilters;

	@FindBy(xpath = ".//*[@id='confirmAlertsList']/tbody/tr/td[13]/a")
	public WebElement viewLink;

	@FindBy(xpath = ".//*[@id='resvAction']/tbody/tr[4]/td[6]/a")
	public WebElement faxNumberLink;

	@FindBy(name = "bizConfForm.roomName")
	public WebElement conferenceRoomName;

	String firstHPNameXpath1 = "//*[@id='globalId";
	String firstHPNameXpath2 = "']/../following-sibling::td[1]/a";
	String firstGTNameXpath2 = "']/../following-sibling::td[1]";

	String confirmationNumber = "confirmCode";

	@FindBy(css = "#inc1 > table:nth-child(6) > tbody > tr.R1 > td:nth-child(15)")
	public WebElement originalHotelProvider;

	@FindBy(xpath = ".//*[@id='inc1']/div/table[1]//tbody/tr[2]/td[9]")
	public WebElement originalHotelProviderName;

	String hotelProviderSelectBoxId = "globalId";
	String hotelProviderSelectBoxXpath1 = "//div[contains(text(),'Hotel Providers')]/following-sibling::table//a[contains(text(),'";
	String hotelProviderSelectBoxXpath2 = "')]/../preceding-sibling::td/input";

	@FindBy(xpath = "//*[@id='inc1']/div[5]/table/tbody/tr")
	public List<WebElement> hotelProviderRows;

	@FindBy(xpath = ".//*[@id='inc1']/div/table[2]/tbody/tr[contains(@class,'R')]")
	public List<WebElement> hotelProvider;

	@FindBy(css = "#inc1 > div:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(4)")
	public WebElement originalGTProvider;

	@FindBy(xpath = "//*[@id='inc1']/div[3]/div[2]/table/tbody/tr")
	public List<WebElement> GTProviderRows;

	@FindBy(id = "NextButton")
	public WebElement nextBtn;

	@FindBy(xpath = "//button[contains(text(),'Previous')]")
	public WebElement previousBtn;

	String availableRowXpath = ".//*[@id='inc1']/table[2]/tbody/tr";
	String availableRowXpathCount = ".//*[@id='inc1']/table[2]/tbody/tr[";
	String availableRowXpathCount1 = "]/td";
	String statusXpath1 = "//*[@id='confirmAlertsList']/tbody/tr[";
	String statusXpath2 = "]/td";
	String tripLinkXpath = "]/td[3]/a";
	String confirmAlertList = ".//*[@id='confirmAlertsList']/tbody/tr";

	String hotelName = ".//*[@id='inc1']/table[1]/tbody/tr[2]/td[7]";

	String arrivalReservationListXpath1 = ".//*[@id='inc1']/table[2]/tbody/tr[";
	String arrivalReservationListXpath2 = "]";
	String arrivalReservationListXpath3 = "/td[1]/input[contains(@id,'avlglobalId')]";
	String arrivalReservationStatusXpath = "/td[14]";
	String endeavor_Notes = "notesId";

	@FindBy(xpath = "//*[@id='confirmAlertsList']/tbody/tr/td[10]")
	private WebElement noOfRooms;

	@FindBy(xpath = ".//*[@id='confirmAlertsList']/tbody/tr/td[14]/a")
	public WebElement faxImageLink;

	@FindBy(id = "avlglobalId0")
	public WebElement availableReservation;

	@FindBy(id = "overrideRate")
	public WebElement overrideRate;

	@FindBy(xpath = "//*[@id='inc1']/div[2]/table/tbody/tr/td[2]")
	public WebElement empName_Id_ReservationDetails;

	@FindBy(xpath = "//select[contains(@id,'toHotelGtSupplierId')]")
	public WebElement toHotelGTProvider;

	@FindBy(xpath = "//select[contains(@id,'fromHotelGtSupplierId')]")
	public WebElement fromHotelGTProvider;

	String hotelProviderRow = "//div[contains(text(),'Hotel Providers')]/following-sibling::table//tr";
	String hotelProviderXpath1 = "//div[contains(text(),'Hotel Providers')]/following-sibling::table//tr[";
	String hotelProviderXpath2 = "]/td[2]";
	String hotelProviderCheckBox = "]/td[1]/input";

	String cityXpath1 = "]/td[3]";

	@FindBy(xpath = "//select[@id='ApGtSupplierId']")
	public WebElement gtProvider;

	@FindBy(xpath = "//div[@id='AlertBox']//button[@id='alertOK']")
	public WebElement layoverLengthAlert;

	@FindBy(id = "noOfcrewId")
	public WebElement noOfRoomscount;

	@FindBy(xpath = "//div[@class='Aces_Table']/table[1]/tbody[1]/tr[2]/td[5]")
	public WebElement crewMembers;

	@FindBy(id = "findRelatedId")
	public WebElement splitReservationBtn;

	@FindBy(xpath = "//*[@id='splitHotelResvTable']//tr[2]/td/input[2]")
	public WebElement selectRoomToSplit;

	@FindBy(id = "AlertBox")
	public WebElement alertBox;

	@FindBy(id = "alertHead")
	public WebElement alertHead;

	@FindBy(id = "alertCancel")
	public WebElement alertCancel;

	@FindBy(id = "alertOK")
	public WebElement alertOK;

	@FindBy(xpath = "//*[@id='inc1']//div[2]/table[1]//tbody[1]/tr")
	public WebElement availableResvSize;

	@FindBy(xpath = "//div[contains(text(),'Available Reservation')]/following-sibling::table//tr[2]/td[9]")
	public WebElement availableReservationRoomCount;

	String roomCountXpath1 = "//div[contains(text(),'Available Reservation')]/following-sibling::table//tr[";
	String roomCountXpath2 = "]/td[9]";
	String availableReservationCheckbox = "]/td[1]";

	String availableReservationStatusXpath1 = "//div[contains(text(),'Available Reservation')]/following-sibling::table//tr[";
	String availableReservationStatusXpath2 = "]/td[14]";

	@FindBy(xpath = "//div[contains(text(),'Available Reservation')]/following-sibling::table//tr[2]/td[14]")
	public WebElement availableReservationStatus;

	@FindBy(xpath = "//div[contains(text(),'Available Reservation')]/following-sibling::table//tr[2]/td[15]/a")
	public WebElement availableReservationhotelName;

	@FindBy(id = "rateId0")
	public WebElement hotelRates;

	public String hotelRate = "rateId0";
	public String toGTReservationRate = "//*[contains(@id,'gtRateId')]";
	public String fromGTReservationRate = "//*[contains(@id,'frmHtRateId')]";
	public String toHotelGTReservationRate = "gtRateId";
	public String fromHotelGTReservationRate = "frmHtRateId";

	@FindBy(xpath = "//form[@name='tripReservationDetailForm']/div[@class='Aces_Table']//tr[2]/td[1]")
	public WebElement getTripNumber;

	@FindBy(xpath = ".//*[@id='inc1']/div/table[1]//tbody/tr[2]/td[2]")
	public WebElement arrivalDate;

	String availableReservationSize = "//*[@id='inc1']//div[2]/table[1]//tbody[1]/tr";
	String reassignAvailableResevSize = ".//*[@id='inc1']/table[2]/tbody/tr";

	String assignmentAlertRowXpath1 = "//*[@id = 'assignmentAlertsList']/tbody/tr";
	String assignmentAlertRowXpath2 = "[";
	String assignmentAlertRowXpath3 = "]//td[2]//a[1]";

	@FindBy(xpath = "//*[@id='gtProviders']//tr//a[contains(text(),'Select')]")
	public List<WebElement> reqGtProvider;

	@FindBy(id = "toHotelGtSupplierId0")
	public WebElement gtProviders;

	String toGTProvider = "toHotelGtSupplierId";
	String fromGTProvider = "fromHotelGtSupplierId";
	String toGTProviders = "//*[contains(@id,'toHotelGtSupplierId')]";
	String fromGTProviders = "//*[contains(@id,'fromHotelGtSupplierId')]";

	String availableReservationTripIdXpath1 = "//div[contains(text(),'Available Reservation')]/following-sibling::table//tr[";
	String availableReservationTripIdXpath2 = "]/td[12]";

	String availableTripSelectBoxXpath1 = "//*[contains(text(),'";
	String availableTripSelectBoxXpath2 = "')]/preceding-sibling::td/input";

	String gtCancellationCode = "GTConfirmCode";

	@FindBy(id = "toConfirmCode0")
	public WebElement gtConfirmationCode;

	@FindBy(xpath = "//*[contains(@id, 'fromConfirmCode')]")
	public WebElement fromConfirmationCode;

	@FindBy(xpath = "//div[@id='AlertBox']//div[@id='alertBody']")
	public WebElement alertBody;

	@FindBy(xpath = "//div[@id='AlertBox']//div[@id='alertBot']/button[@value='OK']")
	public WebElement popUpOk;

	@FindBy(xpath = "//tr[@id='resvRate0']//input[@id='isTaxIncludedNo']")
	public WebElement roomRateTaxNo;

	@FindBy(xpath = "//tr[@id='resvRate0']//input[@id='isTaxIncludedYes']")
	public WebElement roomRateTaxYes;

	@FindBy(xpath = "//tr[@id='resvRate0']//input[@id='resevIncludesTax']")
	public WebElement resevIncludesTax;

	@FindBy(xpath = "//*[@id='gtProviders']//tr//a[contains(text(),'Select')]/parent::td/following-sibling::td[1]/input")
	public List<WebElement> gtProviderName;

	@FindBy(xpath = "//*[contains(@id,'confirmCode')]")
	private List<WebElement> listOfConfirmationCodes;

	@FindBy(id = "Confirmation_OK")
	private WebElement btn_Confirmation_OK;

	@FindBy(xpath = "//table[@id='confirmAlertsList']//tr/td[3]/a")
	private List<WebElement> serviceTypeCellValue;

	@FindBy(id = "apRateId")
	private WebElement airportRate;

	@FindBy(xpath = "//div[@class='Aces_Errors'][3]")
	private WebElement warrningMessageHeader;

	@FindBy(id = "avlReservationOverride")
	private WebElement avlReservationOverride;

	@FindBy(xpath = "//table[@id=\"confirmAlertsList\"]//tr/td[8]")
	private WebElement td_Hotel;

	public EventFiringWebDriver driver = null;

	public Page_PendingConfirmations(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void bookReservationsFromPendingConfirmations(String confirmationCode) throws Exception {
		waitForElementVisibility(tenants);
		String getServiceType = getTextOfElement(td_Hotel);
		if (confirmAlertListTableRows.size() > 0 && getServiceType.equalsIgnoreCase("Hotel")) {
			clickOn(tripLink, "ConfirmationAlerts -> Trip Link");
			typeInText(this.Assignment_ConfirmationCode, confirmationCode,
					"ConfirmReservationPopup -> ConfirmationNumber");
			// waitForElementVisibility(hotelNotes);
			Assignment_ConfirmationCode.sendKeys(Keys.TAB);
			waitForElementVisibility(confirmation_OkBtn);
			clickOn(confirmation_OkBtn, "ConfirmReservationPopup -> OK Button");
		}
	}

	public void bookReservationsFromPendingConfirmations_Updated(String confirmationCode) throws Exception {
		waitForElementVisibility(tenants);
		waitForElementVisibility(timeFormat);
		waitForElementVisibility(refreshBtn);
		if (confirmAlertListTableRows.size() > 1) {
			String statusType = this.statusType.getText().trim();
			clickOn(tripLink, "ConfirmationAlerts -> Trip Link");
			switch (statusType) {
			case "Assignment": {
				typeInText(this.confirmationCode, confirmationCode, "ConfirmReservationPopup -> ConfirmationNumber");
				waitForElementVisibility(confirmation_OkBtn);
				clickOn(confirmation_CancelBtn, "ConfirmReservationPopup -> Cancel Button");
				break;
			}
			case "Cancelled": {
				typeInText(this.confirmationCode, confirmationCode, "ConfirmReservationPopup -> ConfirmationNumber");
				waitForElementVisibility(confirmation_OkBtn);
				clickOn(confirmation_CancelBtn, "ConfirmReservationPopup -> Cancel Button");
				break;
			}
			}
		}
	}

	public void enterConfirmationCodesForRoomTypes(String confirmationCode, String ClearConfirmationCode)
			throws InterruptedException {
		hotelProvider();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		List<String> confirmationCodes = new ArrayList<String>(Arrays.asList(confirmationCode.split(",")));
		for (int i = 0; i < confirmationCodes.size(); i++) {
			typeInText(findElementById(confirmationNumber + i), confirmationCodes.get(i),
					"ConfirmReservationPopup -> ConfirmationNumber");
			if (ClearConfirmationCode.equals("Yes")) {
				findElementById(confirmationNumber + (i + 1)).clear();
			}
		}
		clickFinishButton();
	}

	public void enterConfirmationCode(String confirmationCode, String clearConfirmationCode) {
		List<String> confirmationCodes = new ArrayList<String>(Arrays.asList(confirmationCode.split(",")));
		for (int i = 0; i < confirmationCodes.size(); i++) {
			typeInText(findElementById(confirmationNumber + i), confirmationCodes.get(i),
					"ConfirmReservationPopup -> ConfirmationNumber");
			if (clearConfirmationCode.equals("Yes")) {
				findElementById(confirmationNumber + (i + 1)).clear();
			}
		}
	}

	public void ignoreReservation() {
		clickOn(ignoreBtn);
	}

	public void hotelProvider() {
		if (hotelProvidersLink.isSelected())
			clickOn(hotelProvidersLink1);
		else
			clickOn(hotelProvidersLink);
		waitForPageLoad(getDriver());
	}

	public void enterConfirmationCode(String confirmationCode, String hotelNotesValue, String vendorNotesValue,
			String billingValue) {
		clickOn(hotelProvidersLink);
		typeInText(Assignment_ConfirmationCode, confirmationCode);
		typeInText(notes, hotelNotesValue);
		typeInText(vendorNotes, vendorNotesValue);
		enterBillingMethod(billingValue);
	}

	public boolean checkForGTProviders() {
		try {
			int count = findElementsById("globalId0").size();
			if (count > 0)
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public void clickFinishButton() throws InterruptedException {
		Thread.sleep(5000);
		clickOn(Assignment_finishBtn, "AddHotelandGTReservation -> Finish Button");
		/*
		 * if (alertBody.getText().contains("Reservation has been booked")){
		 * clickOn(popUpOk); checkForAlertBox();} else
		 */
		checkForAlertBox();
	}

	public void bookPendingConfirmation(String reservId, String confirmationCode) throws InterruptedException {
		findTrip(reservId);
		clickTripLinkAndEnterconfirmationDetails(confirmationCode);
	}

	public void confirmReservationCancellation(String reservId, String confirmationCode) throws InterruptedException {
		findTrip(reservId);
		int size = confirmAlertListTableRows.size();
		for (int i = 1; i <= size; i++) {
			clickOn(tripLink);
			typeInText(Assignment_ConfirmationCode, confirmationCode);
			clickFinishButton();
		}
	}

	public void clickAlertOk() {
		try {
			clickOn(okBtn);
		} catch (Exception e) {
		}
	}

	public void findTrip(String reservId) {
		clickOn(trip);
		typeInText(tripSearchField, reservId);
		clickOn(textFilters);
		clickOn(containsTextFilters);
		clickOn(refreshBtn);
		int rows = findElementsByXpath(".//*[@id='confirmAlertsList']/tbody/tr").size();
		if (rows == 1)
			ExtentTestManager.getTest().log(LogStatus.PASS, "Trip Found in PC");
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Trip not Found in PC");
	}

	public void clickOnNotesLink(String faxNumber) {
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(viewLink);
		switchToNewWindow(currentWindowHandle);
		Assert.assertEquals(faxNumberLink.getText(), faxNumber, "fax number doesn't match");
		clickOn(faxNumberLink);
		switchToNewWindow(currentWindowHandle);
		close();
		switchToWindow(currentWindowHandle);

	}

	public void changeHotelProvider(String overrideRate, String billingValue) {
		String originalHP = originalHotelProvider.getText().trim();
		int hpRows = hotelProviderRows.size();
		for (int i = 0; i < hpRows - 2; i++) {
			String hpName = findElementByXpath(firstHPNameXpath1 + i + firstHPNameXpath2).getText().trim();
			if (!hpName.equals(originalHP)) {
				clickOn(findElementById(hotelProviderSelectBoxId + i));
				break;
			}
		}
		enterOverrideRate(overrideRate);
		enterBillingMethod(billingValue);
	}

	public String selectHotelProvider() {
		String hpName = "";
		String cityName = "";
		// String originalHP = originalHotelProviderName.getText().trim();
		for (int i = 2; i <= hotelProvider.size(); i++) {
			hpName = findElementByXpath(hotelProviderXpath1 + i + hotelProviderXpath2).getText().trim();
			if (hpName.contains("(")) {
				String hpName1 = hpName.split("\\(")[0].trim();
				hpName = hpName1;
			}
			cityName = findElementByXpath(hotelProviderXpath1 + i + cityXpath1).getText().trim();
			// if(!hpName.equals(originalHP)){
			clickOn(findElementByXpath(hotelProviderXpath1 + i + hotelProviderCheckBox));
			break;
			// }
		}
		return hpName + "," + cityName;
	}

	public void changeHotelProvider(String originalHotelProvider, String overrideRate, String billingValue) {
		int hpRows = hotelProviderRows.size();
		for (int i = 0; i < hpRows - 2; i++) {
			String hpName = findElementByXpath(firstHPNameXpath1 + i + firstHPNameXpath2).getText().trim();
			if (!hpName.equals(originalHotelProvider)) {
				clickOn(findElementById(hotelProviderSelectBoxId + i));
				break;
			}
		}
		enterOverrideRate(overrideRate);
		enterBillingMethod(billingValue);
	}

	public void clickPACReservation() {
		for (int i = 1; i <= confirmAlertListTableRows.size(); i++) {
			WebElement td = findElementByXpath(statusXpath1 + i + statusXpath2);
			if (td.getAttribute("style").isEmpty())
				clickOn(findElementById(statusXpath1 + i + tripLinkXpath));
		}
	}

	public void verifyBTExists_PendingConfirmation() {
		assertEquals(confirmAlertListTableRows.size(), 1, "Result Exist!");
	}

	public void enterConferenceRoomName(String conferenceRoomNameValue) {
		typeInText(conferenceRoomName, conferenceRoomNameValue);
	}

	public void enterOverrideRate(String overrideRate) {
		typeInText(this.overrideRate, overrideRate);
	}

	public void clickOkBtn() {
		clickOn(okBtn);
	}

	public void verifyHotelNameAndClickFinishButton(String hotelName1, String hotelName2) throws InterruptedException {
		if (!hotelName1.equals(hotelName2)) {
			clickFinishButton();
			clickOkBtn();
			clickOkBtn();
		} else {
			clickFinishButton();
			clickOkBtn();
		}
	}

	public void enterBillingMethod(String billingValue) {
		selectByValue(billingMethod, billingValue);
	}

	public void enterCancellationCode(String cancellationCode, String cancelNotesValue) {
		enterConfirmationCode(cancellationCode);
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
		}
		typeInText(hotelNotes, cancelNotesValue);
	}

	public void enterConfirmationCode(String cancellationCode) {
		waitTime(3000);
		typeInText(Assignment_ConfirmationCode, cancellationCode, "Hotel -> ConfirmationCode");
		Assignment_ConfirmationCode.sendKeys(Keys.TAB);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickNextButton() throws Exception {
		Thread.sleep(2000);
		clickOn(nextBtn, "HotelLayoverToBeAssignedPage -> Next Button");
		checkForAlertBox();
	}

	public String hotelProviderName() {
		String hotelProvider = findElementByXpath(hotelName).getText();
		return hotelProvider;
	}

	public void clickAvailableReservation() {
		clickOn(availableReservation);
	}

	public int getHotelProviderRow(String checkInDateValue, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String roomCountValue, String hotelName) {
		int availableReservationRowCount = 1;
		List<WebElement> row = findElementsByXpath(availableRowXpath);
		for (int i = 2; i <= row.size(); i++) {
			boolean rooms = false;
			boolean hotel = false;
			boolean arrivalDate = false;
			boolean arrivalTime = false;
			boolean departDate = false;
			boolean departTime = false;
			List<WebElement> column = findElementsByXpath(availableRowXpathCount + i + availableRowXpathCount1);
			for (int j = 1; j < column.size(); j++) {

				if (!arrivalDate)
					arrivalDate = column.get(j).getText().equals(checkInDateValue);

				if (!arrivalTime)
					arrivalTime = column.get(j).getText().equals(checkInTimeValue);

				if (!departDate)
					departDate = column.get(j).getText().equals(checkOutDateValue);

				if (!departTime)
					departTime = column.get(j).getText().equals(checkOutTimeValue);

				if (!rooms)
					rooms = column.get(j).getText().equals(roomCountValue);

				if (!hotel)
					hotel = column.get(j).getText().equals(hotelName);

			}
			availableReservationRowCount++;
			if (arrivalDate && arrivalTime && departDate && departTime && rooms && hotel == true)
				return availableReservationRowCount;
		}
		return availableReservationRowCount;
	}

	public void hotelGTProvider(String toHotelProviderValue, String fromHotelGTProviderValue) {
		Select GTProvider = new Select(toHotelGTProvider);
		String toHotelProvider = GTProvider.getFirstSelectedOption().getText();
		if (toHotelProvider.equals("Select"))
			selectByVisibleText(toHotelGTProvider, toHotelProviderValue);
		if (fromHotelGTProvider.isDisplayed()) {
			Select fromHotelProvider = new Select(fromHotelGTProvider);
			String fromHotelProviderName = fromHotelProvider.getFirstSelectedOption().getText();
			waitTime(3000);
			if (fromHotelProviderName.equals("Select"))
				selectByVisibleText(fromHotelGTProvider, fromHotelGTProviderValue);
		}
	}

	public void clickAvailableReservation(int availableReservationRowCount) {
		clickOn(findElementByXpath(arrivalReservationListXpath1 + availableReservationRowCount
				+ arrivalReservationListXpath2 + arrivalReservationListXpath3));
	}

	public void enterNotes(String notes) {
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
		}
		typeInText(findElementById(endeavor_Notes), notes);
	}

	public void verifyStatus(int statusPC, String expectedResult) {
		String Status = findElementByXpath(
				arrivalReservationListXpath1 + statusPC + arrivalReservationListXpath2 + arrivalReservationStatusXpath)
						.getText();
		Assert.assertEquals(Status.trim(), expectedResult, "status mismatch");
	}

	public int bookPendingConfirmation(String reservId, String checkInDateValue, String checkinTimeValue,
			String locationvalue, String roomsValue) {
		findTrip(reservId);
		int triprowCount = 0;
		int rows = findElementsByXpath(confirmAlertList).size();
		for (int i = 1; i <= rows; i++) {
			boolean pickup = false;
			boolean location = false;
			boolean rooms = false;
			List<WebElement> column = findElementsByXpath(statusXpath1 + i + statusXpath2);
			for (int j = 1; j <= column.size(); j++) {
				if (!pickup) {
					String date = checkInDateValue.substring(0, 2);
					String month = checkInDateValue.substring(3, 6);
					String year = checkInDateValue.substring(9, 11);
					pickup = column.get(j).getText().equals(date + month + year + " " + checkinTimeValue);
				}
				if (!location)
					location = column.get(j).getText().equals(locationvalue);
				if (!rooms)
					rooms = column.get(j).getText().equals("S" + roomsValue);

			}
			triprowCount++;
			if (pickup && location && rooms == true) {
				return triprowCount;
			}
		}
		return triprowCount;
	}

	public void clickTripLinkAndEnterconfirmationDetails(String confirmationCode) throws InterruptedException {
		clickOn(tripLink);
		List<String> confirmationCodes = new ArrayList<String>(Arrays.asList(confirmationCode.split(",")));
		for (int i = 0; i < confirmationCodes.size(); i++) {
			typeInText(findElementById(confirmationNumber + i), confirmationCodes.get(i));

		}
		clickFinishButton();
	}

	public void clickOnTripAndEnterConfirmationCode(int triprowCount, String confirmationCode) {
		clickOn(findElementByXpath(statusXpath1 + triprowCount + tripLinkXpath));
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
		}
		typeInText(this.Assignment_ConfirmationCode, confirmationCode);
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
		}
		clickOn(confirmation_OkBtn);
	}

	public void clickOnFaxImageLink() {
		String currentWindowHandle = driver.getWindowHandle();
		String faxNumber = faxImageLink.getText();
		clickOn(faxImageLink);
		switchToNewWindow(currentWindowHandle);
		System.out.println("Title:" + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains(faxNumber), "Fax pdf is not displayed");
		driver.close();
		switchToWindow(currentWindowHandle);

	}

	public void verifyNoOfRoomsForBT(String expectedResult) {
		assertEquals(noOfRooms.getText().trim(), expectedResult, "No Of Rooms Mismatch!");
	}

	public void changeGTProvider(String overrideRate, String billingValue) {
		String originalGT = originalGTProvider.getText().trim();
		int hpRows = GTProviderRows.size();
		for (int i = 0; i <= hpRows - 2; i++) {
			String hpName = findElementByXpath(firstHPNameXpath1 + i + firstGTNameXpath2).getText().trim();
			if (!hpName.equals(originalGT)) {
				clickOn(findElementById(hotelProviderSelectBoxId + i));
				break;
			}
		}
		enterOverrideRate(overrideRate);
		enterBillingMethod(billingValue);
	}

	public void verifyReservationDetails(String empName, String empId) {
		String actualEmpDetails = empName_Id_ReservationDetails.getText().trim();
		String actualEmpName = actualEmpDetails.split("\\(")[0].trim();
		String actualEmpId = actualEmpDetails.split("\\(")[1].split("\\)")[0];
		assertEquals(actualEmpName, empName, "Emp Name Mismatch!");
		assertEquals(actualEmpId, empId, "Emp Id Mismatch!");
	}

	public void verifyReservationDetails_DoubleRoom(String empName, String empId) {
		String actualEmpDetails = empName_Id_ReservationDetails.getText().trim();
		String actualEmpName = actualEmpDetails.split("\\(")[0].trim();
		String actualEmpId = actualEmpDetails.split("\\(")[1].split("\\)")[0];
		assertEquals(actualEmpName, empName.split(",")[0], "Emp Name Mismatch!");
		assertEquals(actualEmpId, empId.split(",")[0], "Emp Id Mismatch!");
	}

	public int findPendingConfirmation(String reservId, String serviceType, String locationvalue, String roomsValue,
			String hotelName) {
		findTrip(reservId);
		int triprowCount = 0;
		int rows = findElementsByXpath(".//*[@id='confirmAlertsList']/tbody/tr").size();
		for (int i = 1; i <= rows; i++) {
			boolean location = false;
			boolean rooms = false;
			boolean supplier = false;
			boolean type = false;
			List<WebElement> column = findElementsByXpath(statusXpath1 + i + statusXpath2);
			for (int j = 1; j < column.size(); j++) {
				if (!location)
					location = column.get(j).getText().equals(locationvalue);
				if (!rooms)
					rooms = column.get(j).getText().equals(roomsValue);
				if (!supplier)
					supplier = column.get(j).getText().contains(hotelName);
				if (!type)
					type = column.get(j).getText().equals(serviceType);
			}
			triprowCount++;
			if (supplier && location && rooms && type == true) {
				return triprowCount;
			}
		}
		return triprowCount;
	}

	public void bookPendingConfirmationCancelledStatus(int triprowCount, String confirmationCode) {
		clickOn(findElementByXpath(statusXpath1 + triprowCount + tripLinkXpath));
		typeInText(this.confirmationCode, confirmationCode);
		waitTime(4000);
		clickOn(confirmation_OkBtn);
	}

	public String selectGTProvider(String gtProviderValue) {
		// selectByIndex(gtProvider, Integer.parseInt(gtProviderValue));
		Select dropdown = new Select(gtProvider);
		dropdown.selectByIndex(Integer.parseInt(gtProviderValue));
		WebElement option = dropdown.getFirstSelectedOption();
		String gtProviderName = option.getText();
		System.out.println(gtProviderName);
		return gtProviderName;
	}

	public void clickOnLayoverLengthAlert() {
		clickOn(layoverLengthAlert);
		try {
			clickAlertOk();
		} catch (Exception e) {
		}
	}

	public void splitReservation() {
		clickOn(splitReservationBtn);
		clickOn(selectRoomToSplit);
		clickOn(alertOK);
	}

	public int getRoomCount() {
		String roomCount = noOfRoomscount.getAttribute("value");
		System.out.println(roomCount);
		return Integer.parseInt(roomCount);
	}

	public void selectSupplierAndconfirmPAResrvation(String confirmationCode) throws InterruptedException {
		List<String> confirmationCodes = new ArrayList<String>(Arrays.asList(confirmationCode.split(",")));
		for (int i = 0; i < confirmationCodes.size(); i++) {
			typeInText(findElementById(confirmationNumber + i), confirmationCodes.get(i),
					"ConfirmReservationPage -> ConfirmationNumber");
		}
		Thread.sleep(2000);
	}

	public void confirmCodeAtoA(String confirmCode) throws Exception {
		typeInText(confirmCodeAtoA, confirmCode);
		clickNextButton();
		checkForAlertBox();
	}

	public String selectGTProvider(String toGTProviderValue, String fromGTProviderValue, String confirmationCodeValue)
			throws Exception {
		String gtProviderName = "";
		enterConfirmationCode(confirmationCodeValue);
		if (toHotelGTProvider.isDisplayed()) {
			Select dropdown = new Select(toHotelGTProvider);
			dropdown.selectByIndex(Integer.parseInt(toGTProviderValue));
			WebElement option = dropdown.getFirstSelectedOption();
			gtProviderName = option.getText();
			System.out.println("gtProviderName*****************" + gtProviderName);
		}
		if (fromHotelGTProvider.isDisplayed()) {
			selectByIndex(fromHotelGTProvider, Integer.parseInt(fromGTProviderValue));
		}
		clickNextButton();
		checkForAlertBox();
		return gtProviderName;
	}

	public String getColorCodeOfRooms() {
		String getColor = availableReservationRoomCount.getCssValue("color");
		return getColor;
	}

	public String getHotelName() {
		String hotelName = availableReservationhotelName.getText();
		return hotelName;
	}

	public String getTripNumber() {
		String tripNumber = getTextOfElement(getTripNumber);
		System.out.println("Trip Number :" + tripNumber);
		return tripNumber;
	}

	public String getarrivalDate() {
		String arrivalDateVal = arrivalDate.getText().trim();
		return arrivalDateVal;

	}

	public String verifyMatchedReservation(String colorCode, String status) throws Exception {

		String hotelName = "";
		boolean recordfound = false;
		List<WebElement> availableReservationCount = findElementsByXpath(availableReservationSize);
		for (int rowcount = 2; rowcount <= availableReservationCount.size(); rowcount++) {

			WebElement roomCountTextColor = findElementByXpath(roomCountXpath1 + rowcount + roomCountXpath2);
			String roomsTextColor = roomCountTextColor.getCssValue("color");
			WebElement statusText = findElementByXpath(
					availableReservationStatusXpath1 + rowcount + availableReservationStatusXpath2);
			String reservationStatus = statusText.getText();

			if (roomsTextColor.equalsIgnoreCase(colorCode) && reservationStatus.equalsIgnoreCase(status)) {
				hotelName = availableReservationhotelName.getText();
				recordfound = true;
				break;

			}
		}
		/*
		 * if (!recordfound) { clickOn(previousBtn); List<WebElement> assignmentAlert =
		 * findElementsByXpath(assignmentAlertRowXpath1); int tripsCount =
		 * assignmentAlert.size(); System.out.println(tripsCount); if (tripsCount > i) {
		 * WebElement trips = findElementByXpath( assignmentAlertRowXpath1 +
		 * assignmentAlertRowXpath2 + i + assignmentAlertRowXpath3);
		 * System.out.println(trips); clickOn(trips); checkForAlertBox(); } }
		 */

		if (!recordfound)
			throw new Exception("Available Reservations with status " + status + " are not found");

		return hotelName;

	}

	public void addHotelRate(String hotelRateValue) {
		if (findElementsById(hotelRate).size() > 0 && hotelRates.getAttribute("readonly") == null) {
			typeInText(findElementById(hotelRate), hotelRateValue, "AddHotelandGTReservation -> HotelReservationRate");
			hotelRates.sendKeys(Keys.TAB);
		}
	}

	public void addToHotelGTRates(String toHotelGTRate, String fromHotelGTRate) {
		try {
			int n = 0;
			int toGtReservationsCount = findElementsByXpath(toGTReservationRate).size();
			if (toGtReservationsCount > 0) {
				for (int i = 1; i <= toGtReservationsCount; i++) {

					typeInText(findElementById(toHotelGTReservationRate + n), toHotelGTRate,
							"AddHotelandGTReservation -> ToHotelReservationRate");
					n = n + 2;
				}
			}
			addFromHotelGTRates(fromHotelGTRate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFromHotelGTRates(String fromHotelGTRate) {
		try {
			int n = 1;
			int fromGtReservationsCount = findElementsByXpath(fromGTReservationRate).size();
			if (fromGtReservationsCount > 0) {
				for (int i = 1; i <= fromGtReservationsCount; i++) {
					typeInText(findElementById(fromHotelGTReservationRate + n), fromHotelGTRate,
							"AddHotelandGTReservation -> FromHotelReservationRate");
					n = n + 2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkForAlertBox() {
		while (alertBox.isDisplayed()) {
			clickOn(alertOK, "Alert -> Ok button");
		}
	}

	public void selectAvailableTrip() {
		for (int i = 2; i <= 5; i++) {
			List<WebElement> availableResv = findElementsByXpath(availableReservationSize);
			int rowCount = availableResv.size();
			if (rowCount == 1) {
				clickOn(previousBtn);
				List<WebElement> assignmentAlert = findElementsByXpath(assignmentAlertRowXpath1);
				int tripsCount = assignmentAlert.size();
				System.out.println(tripsCount);
				if (tripsCount > i) {
					WebElement trips = findElementByXpath(
							assignmentAlertRowXpath1 + assignmentAlertRowXpath2 + i + assignmentAlertRowXpath3);
					System.out.println(trips);
					clickOn(trips);
					checkForAlertBox();
				}

			} else if (rowCount > 1) {
				break;
			} else
				System.out.println("No Trips availble to verify this case");
		}
	}

	public void selectTripFromAvailableReservation() {
		List<WebElement> availableResv = findElementsByXpath(availableReservationSize);
		int availableResvCount = availableResv.size();
		if (availableResvCount > 0) {
			for (int rowCount = 2; rowCount <= availableResvCount; rowCount++) {
				WebElement selectReservation = findElementByXpath(
						roomCountXpath1 + rowCount + availableReservationCheckbox);
				clickOn(selectReservation);
				break;
			}
			checkForAlertBox();
		}
	}

	public void selectReqHotel(String hotelName) {
		List<WebElement> hotelProviderRowList = findElementsByXpath(hotelProviderRow);
		for (int hotelProviderRow = 2; hotelProviderRow <= hotelProviderRowList.size(); hotelProviderRow++) {
			String reqHotelName = findElementByXpath(hotelProviderXpath1 + hotelProviderRow + hotelProviderXpath2)
					.getText().trim();
			if (reqHotelName.equalsIgnoreCase(hotelName)) {
				clickOn(findElementByXpath(hotelProviderSelectBoxXpath1 + hotelName + hotelProviderSelectBoxXpath2),
						"HotelLayoverToBeAssignedPage -> Hotel Checkbox");
				break;

			}
		}
	}

	public String selectGTLink() throws InterruptedException {
		Thread.sleep(5000);
		if (reqGtProvider.size() > 0)
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", reqGtProvider.get(0));
		// clickOn(reqGtProvider.get(0), "HotelLayoverToBeAssignedPage -> Select Link");
		Thread.sleep(2000);
		String gtName = gtProviderName.get(0).getAttribute("value");
		System.out.println(gtName);
		return gtName;

	}

	public void selectReqToGTProvider(String reqToGTValue, String toGTProviderValue) throws InterruptedException {
		waitForElementVisibility(gtProviders);
		List<WebElement> toGtProviderList = findElementsByXpath(toGTProviders);
		List<String> reqToGTs = new ArrayList<String>(Arrays.asList(reqToGTValue.split(",")));
		if (toGtProviderList.size() > 0 && gtProviders.getAttribute("disabled") == null) {
			for (int i = 0; i < reqToGTs.size(); i++) {
				if (!reqToGTs.get(i).isEmpty())
					selectByVisibleText(findElementById(toGTProvider + reqToGTs.get(i)), toGTProviderValue,
							"HotelLayoverToBeAssignedPage -> ToHotel-GTProvider Dropdown");
				Thread.sleep(1000);
			}
		}
		Thread.sleep(2000);
	}

	public void selectReqFromGTProvider(String reqFromGTValue, String fromGTProviderValue) throws InterruptedException {
		waitForElementVisibility(gtProviders);
		List<WebElement> fromGtProviderList = findElementsByXpath(fromGTProviders);
		List<String> reqFromGTs = new ArrayList<String>(Arrays.asList(reqFromGTValue.split(",")));
		if (fromGtProviderList.size() > 0 && gtProviders.getAttribute("disabled") == null) {
			for (int i = 0; i < reqFromGTs.size(); i++) {
				if (!reqFromGTs.get(i).isEmpty())
					selectByVisibleText(findElementById(fromGTProvider + reqFromGTs.get(i)), fromGTProviderValue,
							"HotelLayoverToBeAssignedPage -> ToHotel-GTProvider Dropdown");
				Thread.sleep(1000);
			}

		}
		Thread.sleep(2000);
	}

	public void selectTripFromAvailableReservationBasedOnTripId(String tripValue) throws Exception {
		List<String> tripIds = new ArrayList<String>(Arrays.asList(tripValue.split(",")));
		for (int i = 0; i < tripIds.size(); i++) {
			clickOn(findElementByXpath(availableTripSelectBoxXpath1 + tripIds.get(i) + availableTripSelectBoxXpath2),
					"HotelLayoverToBeAssignedPage -> AvailableReservations Checkbox");
			checkForAlertBox();
			Thread.sleep(5000);
		}
	}

	public void enterGTConfirmationCode(String gtConfirmationCodeValue) throws InterruptedException {
		Thread.sleep(2000);
		typeInText(gtConfirmationCode, gtConfirmationCodeValue,
				"HotelLayoverToBeAssignedPage -> ToHotelGTConfirmationCode");
	}

	public void enterfromConfirmationCode(String gtFromConfirmationCodeValue) {
		typeInText(fromConfirmationCode, gtFromConfirmationCodeValue,
				"HotelLayoverToBeAssignedPage -> FromHotelGTConfirmationCode");
	}

	public String getGTTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public void roomRateTaxYes() {
		if (resevIncludesTax.getAttribute("value").equalsIgnoreCase("false"))
			clickOn(roomRateTaxYes);
		clickAlertOk();
	}

	public void confirmPendingCancellation(String confirmationCode) {
		int confirmationCodesList = listOfConfirmationCodes.size();
		System.out.println("Pending Cancellation Confirmation emp list : " + confirmationCodesList);
		listOfConfirmationCodes.get(0).clear();
		listOfConfirmationCodes.get(0).sendKeys(confirmationCode);
		listOfConfirmationCodes.get(0).sendKeys(Keys.TAB);
//		clickOn(btn_Confirmation_OK,"Pending Conformation Page -> Pop up -> Confirmation OK Button ");
		driver.findElement(By.xpath("//td[@class='Aces_Body']//button[2]")).click();
	}

	public void clickOnTripId(String tripId, String confirmationCode) throws IOException {
		for (int i = 0; i < serviceTypeCellValue.size(); ++i) {
			if (serviceTypeCellValue.get(i).getText().contains(tripId)) {
				serviceTypeCellValue.get(i).click();
				break;
			} else {
				System.out.println("No trip found");
			}
		}
		confirmPendingCancellation(confirmationCode);
		takeScreenshots();

	}

	public void enterAirportRate(String rate) {
		typeInText(airportRate, rate, "Add GT Reservation Page -> Rate");
	}

	public void clickOnTripId(String tripId) throws IOException, InterruptedException {
		for (int i = 0; i < serviceTypeCellValue.size(); ++i) {
			if (serviceTypeCellValue.get(i).getText().contains(tripId)) {
				serviceTypeCellValue.get(i).click();
				break;
			} else {
				System.out.println("No trip found");
			}
		}
		takeScreenshots();

	}

	public void confirmBTPendingCancellation(String confirmationCode) throws InterruptedException {
		int confirmationCodesList = listOfConfirmationCodes.size();
		System.out.println("Pending Cancellation Confirmation emp list : " + confirmationCodesList);
		listOfConfirmationCodes.get(0).clear();
		listOfConfirmationCodes.get(0).sendKeys(confirmationCode);
		listOfConfirmationCodes.get(0).sendKeys(Keys.TAB);
		clickOn(Assignment_finishBtn, "Pending Conformation Page -> Pop up -> Finish Button ");
		unExpectedAcceptAlert();

	}

	public void selectReqHotelName(String hotelName) {
		List<WebElement> hotelProviderRowList = findElementsByXpath(hotelProviderRow);
		for (int hotelProviderRow = 2; hotelProviderRow <= hotelProviderRowList.size(); hotelProviderRow++) {
			String reqHotelName = findElementByXpath(hotelProviderXpath1 + hotelProviderRow + hotelProviderXpath2)
					.getText().trim();
			if (reqHotelName.contains(hotelName)) {
				clickOn(findElementByXpath(hotelProviderSelectBoxXpath1 + hotelName + hotelProviderSelectBoxXpath2),
						"HotelLayoverToBeAssignedPage -> Hotel Checkbox");
				break;

			}
		}
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cleargtConfirmationCode() {
		gtConfirmationCode.clear();
	}

	public String selectToGTProvider(String gtProviderValue) throws InterruptedException {
		waitForElementVisibility(gtProviders);
		Thread.sleep(5000);
		Select dropdown = new Select(toHotelGTProvider);
		dropdown.selectByIndex(Integer.parseInt(gtProviderValue));
		WebElement option = dropdown.getFirstSelectedOption();
		String gtProviderName = option.getText();
		System.out.println(gtProviderName);
		return gtProviderName;
	}

	public void verifyErrorHeaderText(String textToVerify) {
		System.out.println(warrningMessageHeader.getText());
		if (warrningMessageHeader.getText().contains(textToVerify))
			ExtentTestManager.getTest().log(LogStatus.INFO, "Crew Increased info is displayed");
		else
			Assert.fail("Crew Increased info is not displayed");
	}

	public void selectOverrideAvailableReservation() {
		if (avlReservationOverride.isDisplayed())
			clickOn(avlReservationOverride, "HotelLayoverToBeAssignedPage -> Override Available Reservation Checkbox");
		else
			Assert.fail("Override Available Reservation check box not displayed");
	}

	public void selectTripFromAvailableReservationBasedOnCheckInDate(String checkInDate) throws Exception {
//		List<String> tripIds = new ArrayList<String>(Arrays.asList(tripValue.split(",")));
//		for (int i=0; i<tripIds.size(); i++){
		clickOn(findElementByXpath(availableTripSelectBoxXpath1 + checkInDate + availableTripSelectBoxXpath2),
				"HotelLayoverToBeAssignedPage -> AvailableReservations Checkbox");
		checkForAlertBox();
		Thread.sleep(5000);
//			}		
	}

	public void confirmTripAvailibilityOnPCPage(int triprowCount, String reserID) {
		String getTripText = getTextOfElement(findElementByXpath(statusXpath1 + triprowCount + tripLinkXpath));
		if (getTripText.contains(reserID))
			ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation ID found in Pending Confirmation Page");
		else
			ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation ID found in Pending Confirmation Page");
	}
	public void clickOnFirstHotelProvider(){
		if (!hotelProvidersLink.isSelected())
		clickOn(hotelProvidersLink);
	}

}
