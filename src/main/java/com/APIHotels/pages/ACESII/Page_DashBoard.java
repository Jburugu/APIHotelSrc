package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

public class Page_DashBoard extends BasePage {

	@FindBy(name = "dashTenantId")
	private WebElement tenants;

	@FindBy(id = "allRefresh")
	private WebElement refreshBtn;

	@FindBy(id = "formTimeFrameId")
	private WebElement timeFrameFilter;

	@FindBy(id = "alertFromRange")
	private WebElement alert_CustomFromTime;

	@FindBy(id = "alertToRange")
	private WebElement alert_CustomToTime;

	@FindBy(name = "alertToRangeUnit")
	private WebElement alert_CustomTimeUnit;

	@FindBy(css = "#timeFrameCustom > span > a")
	private WebElement seeRangesLink;

	@FindBy(name = "timeZone")
	private WebElement timeFormat;

	@FindBy(id = "formRefreshTime")
	private WebElement refreshInterval;

	@FindBy(id = "topNumAssign")
	private WebElement assignmentAlertNo;

	@FindBy(id = "topNumCancel")
	private WebElement cancellationAlertNo;

	@FindBy(id = "Expand")
	private WebElement expandAllLink;

	@FindBy(id = "Collapse")
	private WebElement collapseAllLink;

	@FindBy(xpath = "//*[@id = 'assignmentAlertsList']/tbody/tr")
	private List<WebElement> assignmentAlertRows;

	@FindBy(xpath = "//*[@id = 'cancelAlertsList']/tbody/tr")
	private List<WebElement> cancellationAlertRows;

	private String timeFrameId = "formTimeFrameId";
	private String timeZone = "//*[@name = 'timeZone']";

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/thead/tr/th[2]/img")
	public WebElement assignmentTrip;

	@FindBy(xpath = "//table[contains(@id,'assignmentAlertsList')]/div/ul[1]/li[3]//input")
	public WebElement assignmentTripSearchField;

	@FindBy(xpath = "//table[@id='assignmentAlertsList']/div/ul/li[5]")
	public WebElement assignmentTextFilters;

	@FindBy(xpath = "//table[@id='assignmentAlertsList']/div/ul/li[5]/ul/li[2]/a")
	private WebElement assignmentContainsTextFilters;

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/tbody/tr/td[2]/a")
	public WebElement tripAssignmentReservationResult;

	@FindBy(xpath = ".//*[@id='cancelAlertsList']/tbody/tr/td/a")
	public WebElement tripCancellationReservationResult;

	@FindBy(xpath = "//*[@id = 'assignmentAlertsList']/tbody/tr/td[1]")
	public WebElement assignmentAlertColumn;

	@FindBy(id = "numAssign")
	public WebElement assignmentAlert;

	@FindBy(xpath = ".//*[@id='cancelAlertsList']/thead/tr/th[3]/img")
	public WebElement cancellationTrip;

	@FindBy(xpath = "//table[contains(@id,'cancelAlertsList')]/div/ul[1]/li[3]//input")
	public WebElement cancelTripSearchField;

	@FindBy(xpath = "//table[@id='cancelAlertsList']/div/ul/li[5]")
	public WebElement cancelTextFilters;

	@FindBy(xpath = "//table[@id='cancelAlertsList']/div/ul/li[5]/ul/li[2]/a")
	private WebElement cancelContainsTextFilters;

	@FindBy(xpath = "//*[@id='assignmentAlertsList']/tbody/tr/td[11]/a")
	private WebElement pendingAssignments_IgnoreLink;

	@FindBy(css = "#cancelAlertsList > tbody > tr > td:nth-child(13) > a")
	private WebElement pendingCancellation_IgnoreLink;

	@FindBy(xpath = ".//*[@id='alertOK']")
	public WebElement YesAlertBtn;

	@FindBy(xpath = "//table[contains(@id,'assignmentAlertsList')]/div/ul[1]/li[4]//input")
	public WebElement pickUpSearchField;

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/thead/tr/th[9]/img")
	public WebElement createdTime;

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/thead/tr/th[10]/img")
	public WebElement pickUp;

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/thead/tr/th[4]/img")
	public WebElement noOfRooms;

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/thead/tr/th[3]/img")
	public WebElement assignmentLocation;

	String assignmentAlertRowsXpath = ".//*[@id='assignmentAlertsList']/tbody/tr[";
	String assignmentAlertRowsXpath1 = "]/td[2]/a";
	String ignorelink = "]/td[11]/a";

	@FindBy(xpath = ".//*[@id='cancelAlertsList']/thead/tr/th[10]/img")
	public WebElement cancellationPickupTime;

	@FindBy(xpath = "//table[contains(@id,'cancelAlertsList')]/div/ul[1]/li[4]//input")
	public WebElement cancellationpickUpSearchField;

	@FindBy(xpath = ".//*[@id='cancelAlertsList']/thead/tr/th[5]/img")
	public WebElement cancellationNoOfRooms;

	@FindBy(xpath = "//*[@id = 'cancelAlertsList']/tbody/tr/td[5]")
	public WebElement noOfRooms_PC;

	@FindBy(xpath = ".//*[@id='cancelAlertsList']/thead/tr/th[4]/img")
	public WebElement cancellationLocation;

	String cancellationAlertRowsXpath = ".//*[@id='cancelAlertsList']/tbody/tr[";
	String cancellationAlertRowsXpath1 = "]/td[3]/a";

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/tbody/tr/td[11]/a")
	public WebElement ignoreLink;

	@FindBy(xpath = ".//*[@id='assignmentAlertsList']/tbody/tr/td[12]")
	public WebElement notesLink;

	@FindBy(xpath = "//table[@id='assignmentAlertsList']//th[@class='acesGridFilter'][contains(text(),'Alert Type')]/img")
	public WebElement searchAlertType;

	@FindBy(xpath = "//table[@id='assignmentAlertsList']//div[@id='gridFilter']//ul[@id='_gfMenuBuilder']//li[@id='_gfTextFilter']//div//input[@id='_gfText']")
	public WebElement textSearchAlertType;

	@FindBy(xpath = "//td[@class='Aces_Body']//div//tr[2]//td[2]//a[1]")
	public WebElement fistTripInList;

	@FindBy(xpath = "//form[@name='tripReservationDetailForm']/div[@class='Aces_Table']//tr[2]/td[1]")
	public WebElement getTripNumber;

	@FindBy(xpath = "//form[@name='tripReservationDetailForm']/div[@class='Aces_Table']//tr[2]/td[2]")
	public WebElement getStartDate;

	@FindBy(xpath = "//form[@name='tripReservationDetailForm']/div[@class='Aces_Table']//tr[2]/td[3]")
	public WebElement getLocation;

	@FindBy(xpath = "//input[@id='globalId0']")
	public WebElement firtRecordInReservations;

	@FindBy(xpath = "//*[@id='inc1']/div/div[2]/table[1]/tbody/tr[2]/td[2]")
	public WebElement firtRecordFlightNumberInReservations;

	@FindBy(xpath = "//button[@value='Next']")
	public WebElement nextBtn;

	@FindBy(xpath = "//button[@value='Yes']")
	public WebElement popUpYes;

	@FindBy(xpath = "//div[@id='AlertBox']//div[@id='alertBot']/button[@value='OK']")
	public WebElement popUpOk;

	@FindBy(xpath = "//div[@id='row2']")
	public WebElement clickOnGTSectionHeader;

	@FindBy(xpath = "//div[@id='AlertBox']//div[@id='alertBody']")
	public WebElement alertBody;

	@FindBy(xpath = "//div[@id='AlertBox']//div[@id='alertHead']")
	public WebElement alertHeader;

	@FindBy(id = "buttonFinish")
	public WebElement button_Finish;

	@FindBy(id = "toHotelGtSupplierId0")
	public WebElement toHotelGTSupplier;

	@FindBy(id = "fromHotelGtSupplierId1")
	public WebElement fromHotelGTSupplier;

	@FindBy(xpath = "//*[contains(@id, 'tab')]")
	public WebElement tab_GT;

	@FindBy(id = "tab1")
	public WebElement hotelTab;

	String gtTab = "tab";

	@FindBy(xpath = "//input[@id='confirmCode0']")
	public WebElement confirmCode;

	@FindBy(xpath = "//select[@id='vendorContactLst[0].resvPayMethod']")
	public WebElement hotelPaymentMethod;

	@FindBy(xpath = "//select[@id='vendorContactLst[1].resvPayMethod']")
	public WebElement gtPaymentMethod;

	String gtPaymentXPath1 = "//select[@id='vendorContactLst[";
	String gtPaymentXpath2 = "].resvPayMethod']";

	@FindBy(xpath = "//div[@id='alertBody']//textarea[@id='addIgnoreNoteId']")
	public WebElement ignoreNotesToGT;

	@FindBy(id = "resvQueryId")
	public WebElement checkWithAirline;

	@FindBy(id = "textAreaId")
	public WebElement enterQueryTextbox;

	@FindBy(id = "addQueryBtn")
	public WebElement addButton;

	@FindBy(xpath = "//*[@id='resvQueryTable']//tr")
	public List<WebElement> queriesAndResponses;

	@FindBy(xpath = "//*[@id='resvQueryTable']//td[3]")
	public WebElement tripNotes;

	String tripNotesXpath1 = "//*[@id='resvQueryTable']//tr[";
	String tripNotesXpath2 = "]/td[3]";

	@FindBy(id = "buttonCancel")
	public WebElement closeButton;

	@FindBy(xpath = "//button[contains(text(),'Ignore Reservation')]")
	public WebElement ignoreReservationButton;

	@FindBy(xpath = "//*[@id='resvAction']/tbody/tr")
	public List<WebElement> reservationNotesTableRows;

	@FindBy(xpath = "//*[@id='resvAction']/tbody/tr/td[6]")
	public WebElement reservationNotes;

	String reservationNotesXpath1 = "//*[@id='resvAction']/tbody/tr[";
	String reservationNotesXpath2 = "]/td[6]";

	@FindBy(xpath = ".//*[@id='inc1']/div/table[1]//tbody/tr[2]/td[1]")
	public WebElement arrivalFlight;

	@FindBy(xpath = ".//*[@id='inc1']/div/table[1]//tbody/tr[2]/td[2]")
	public WebElement arrivalDate;

	@FindBy(xpath = "//button[contains(text(),'Find Related Reservations')]")
	public WebElement findRelatedReservationButton;

	@FindBy(xpath = "//form[@name='tripReservationDetailForm']/div[@class='Aces_Table']//tr")
	public List<WebElement> relatedReservationTable;

	String arrivalFlightXpath1 = "//form[@name='tripReservationDetailForm']/div[@class='Aces_Table']//tr[";
	String arrivalFlightXpath2 = "]/td[2]";

	@FindBy(id = "row0")
	public WebElement activeBlocks;

	@FindBy(xpath = "//option[@value='70']")
	public WebElement statuses;

	@FindBy(id = "startDate")
	public WebElement startDate;

	@FindBy(id = "endDate")
	public WebElement endDate;

	@FindBy(name = "adhocRefresh")
	public WebElement refreshActiveBlocks;

	@FindBy(xpath = "//*[@id='activeBlocksList']//tr")
	public List<WebElement> activeBlocksTable;

	String reservationTableRowXpath = ".//*[@id='activeBlocksList']//tr[";
	String reservationTableRowXpath1 = "]/td";
	String supplier = "]/td[7]";

	// String tabsCount= "//*[contains(@id, 'tab')]";
	String tabsCount = "//*[contains(@onclick, 'OpsconfirmTab')]";

	@FindBy(id = "numBlock")
	public WebElement noOfActiveBlocks;

	@FindBy(xpath = "//table[@id='assignmentAlertsList']/div/ul/li[7]/a")
	public WebElement clearAllFilters;

	@FindBy(xpath = ".//*[@id='inc1']/table[1]/tbody/tr[2]/td[1]")
	public WebElement reservationId;

	@FindBy(xpath = ".//*[@id='inc1']/table[1]/tbody/tr[2]/td[2]")
	public WebElement checkInDate;

	@FindBy(xpath = "//*[contains(text(),'Other')]")
	public WebElement selectReasonOther;

	@FindBy(id = "addNoteBtn")
	public WebElement addCommentButton;

	@FindBy(xpath = "//form[@name='ignoreReservationForm']//button[contains(text(),'OK')]")
	public WebElement okButton;

	@FindBy(id = "confirmCode0")
	private WebElement Assignment_ConfirmationCode;

	@FindBy(xpath = "//*[contains(@id,'Code')]")
	public WebElement confCode;

	String gtCancellationCode = "GTConfirmCode";

	String paymentNotesId1 = "vendorContactLst[";
	String paymentNotesId2 = "].resvPaymentNotes";

	String apiCardNumberXpath1 = "//select[@id='vendorContactLst[";
	String apiCardNumberXpath2 = "].apiCardNumber']";

	String reservationGroupNotes1 = "//*[@class='TabBorder']//tr[";
	String reservationGroupNotes2 = "]//td[3]";

	@FindBy(id = "AlertBox")
	public WebElement alertBox;

	@FindBy(id = "alertOK")
	public WebElement alertOK;

	@FindBy(xpath = "//*[contains(text(),'Cancel')]")
	public WebElement cancelBtn;

	@FindBy(xpath = "//table[@id='assignmentAlertsList']/tbody/tr/td[2]")
	private List<WebElement> tripsPAList;

	@FindBy(xpath = "//div[@id='alertBot']/button[@value='Yes']")
	private WebElement alert_CheckNotes;

	String tableTRXPath = "//table[@id='assignmentAlertsList']/tbody/tr[";
	String endingXPathLoc = "]/td[3]";
	String endingXPathTripName = "]/td[2]";

	@FindBy(xpath = "//div[@class='inc']//div[contains(text(),'Hotel Providers')]/following-sibling::table//tr/td[12]")
	private List<WebElement> list_columnGT;

	String hotelSelectionXPath1 = "//div[@class='inc']//div[contains(text(),'Hotel Providers')]/following-sibling::table//tr[";
	String hotelSelectionXPath2 = "]/td[1]/input";
	String hotelSelectionXPathName = "]/td[2]";
	String hotelSelectionXPathGT = "]/td[12]";

	@FindBy(id = "NextButton")
	private WebElement btn_Next;

	@FindBy(xpath = "//div[@id='AlertBox']//div[@class='bot']/button[@id='alertOK']")
	private WebElement bookingAlertOK;

	@FindBy(xpath = "//div[4]//div[3]//button[@id='alertOK']")
	private WebElement reservationConfirmAlert;

	@FindBy(id = "rateId0")
	private WebElement roomRate;

	@FindBy(xpath = "//table[@id='cancelAlertsList']//tr/td")
	private List<WebElement> serviceTypeCell;

	@FindBy(xpath = "//table[@id='cancelAlertsList']//tr/td[12]")
	private List<WebElement> serviceTypeCellValue;

	@FindBy(xpath = "//table[@id='cancelAlertsList']//tr/td[1]/input")
	private List<WebElement> serviceTypeCellCheckbox;

	@FindBy(xpath = "//table[@id='cancelAlertsList']//tr/td[3]/a")
	private List<WebElement> getCancelAlertTripNumber;

	@FindBy(xpath = "//button[@name='bulkCancelBtn']")
	private WebElement btn_BulkCancel;

	@FindBy(xpath = "//input[@name='hotelCancellationNumber']")
	private WebElement textbox_HotelConfirmationNumber;

	@FindBy(xpath = "//input[@name='gtCancellationNumber']")
	private WebElement textbox_GTConfirmationNumber;

	@FindBy(xpath = "//*[@value='Finish Cancel All']")
	private WebElement btn_FinishCancelAll;

	@FindBy(xpath = "//button[@name='buttonFlag']")
	private WebElement btn_Finish;

	@FindBy(xpath = "//table[@id='cancelAlertsList']//th[@class='acesGridFilter'][contains(text(),'Service Type')]/img")
	private WebElement cancelAlertSeriveTypeSearch;

	@FindBy(xpath = "//table[@id='cancelAlertsList']//div[@id='gridFilter']//ul[@id='_gfMenuBuilder']//li[@id='_gfTextFilter']//div//input[@id='_gfText']")
	public WebElement cancelAlertTextSearchAlertType;

	@FindBy(xpath = "//table[@id='cancelAlertsList']/div/ul/li[5]")
	public WebElement cancelAlertTextFilters;

	@FindBy(xpath = "//table[@id='cancelAlertsList']/div/ul/li[5]/ul/li[2]/a")
	private WebElement cancelAlertContainsTextFilters;

	@FindBy(xpath = "//*[@id='bulkResvGTCancellationCandidatesTable']/following-sibling::table//tr/td//input[@type='checkbox' and contains(@name,'bulkCancellationGtTenantSummaryForm')]")
	private WebElement unCheckGTBulkCancel;

	@FindBy(xpath = "//select[@id='resvPayMethod']")
	private WebElement paymentType;

	@FindBy(xpath = "//input[@name='vendorContactLst[0].creditCardDetails.synapticCardNumber']")
	private WebElement ccNumber;

	@FindBy(xpath = "//input[@name='vendorContactLst[0].creditCardDetails.synapticCSVNumber']")
	private WebElement ccCSVNumber;

	@FindBy(xpath = "//input[@name='vendorContactLst[0].creditCardDetails.synapticCardExpirationDate']")
	private WebElement expDateCC;

	@FindBy(xpath = "//input[@name='vendorContactLst[0].creditCardDetails.synapticMerchantLog']")
	private WebElement merchantLog;

	@FindBy(xpath = "//input[@name='vendorContactLst[1].creditCardDetails.synapticCardNumber']")
	private WebElement ccNumberGT;

	@FindBy(xpath = "//input[@name='vendorContactLst[1].creditCardDetails.synapticCSVNumber']")
	private WebElement ccCSVNumberGT;

	@FindBy(xpath = "//input[@name='vendorContactLst[1].creditCardDetails.synapticCardExpirationDate']")
	private WebElement expDateCCGT;

	@FindBy(xpath = "//input[@name='vendorContactLst[1].creditCardDetails.synapticMerchantLog']")
	private WebElement merchantLogGT;

	String gtPaymentXpath3 = "].creditCardDetails.cardTypeAirline']";
	String gtPaymentXpath4 = "].creditCardDetails.cardNumber']";
	String gtPaymentXpaht5 = "//select[@name='vendorContactLst[";
	String gtPaymentXPath6 = "//input[@name='vendorContactLst[";

	@FindBy(xpath = "//input[@name='availableTripResvLst[0].selected']")
	private WebElement availableReservation1;

	@FindBy(xpath = "//input[@name='availableTripResvLst[1].selected']")
	private WebElement availableReservation2;

	@FindBy(xpath = ".//*[@id='cancelAlertsList']/tbody/tr")
	private List<WebElement> rows_PCList;

	@FindBy(xpath = "//input[@name='pcResvAlertIds']")
	private WebElement selectFirstRecordInPCAlertList;

	String selectGTProviderBTPAXPath1 = "//body//div[@class='inc']//div//div[@class='Aces_Table']//tr//td[contains(normalize-space(),'";
	String selectGTProviderBTPAXPath2 = "')]/preceding-sibling::td/input";

	@FindBy(xpath = "//input[@name='bizGtResvForm.confirmation']")
	private WebElement textbox_ConfirmCode;

	@FindBy(xpath = "//select[@name='creditCardDetails.paymentMethod']")
	private WebElement select_PaymentMethod;

	String selectCancellationAlertTripXPath1 = "//table[@id='cancelAlertsList']//tr/td[3]/a[contains(text(),'";
	String selectCancellationAlertTripXPath2 = "')]/parent::td/preceding-sibling::td[2]/input";

	public EventFiringWebDriver driver = null;

	public Page_DashBoard(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyAssignmentAndCancellationAlerts(String tenant, String fromTime, String toTime) throws Exception {
		selectByVisibleText(tenants, tenant);
		List<WebElement> timeFilterOptions = new Select(timeFrameFilter).getOptions();
		for (int timeFilterOptionIndex = 0; timeFilterOptionIndex < timeFilterOptions.size(); timeFilterOptionIndex++) {
			String filterOption = timeFilterOptions.get(timeFilterOptionIndex).getText();
			selectByVisibleText(timeFrameFilter, filterOption);
			if (filterOption.trim().equals("Custom Range")) {
				typeInText(alert_CustomFromTime, fromTime);
				typeInText(alert_CustomToTime, toTime);
				clickOn(seeRangesLink);
			} else {
				clickOn(refreshBtn);
				// timeFrameFilter = findElementById(timeFrameId);
				timeFilterOptions = new Select(timeFrameFilter).getOptions();
			}
			System.out.println("No of Assignment Alerts Found: " + assignmentAlertNo.getText()
					+ " and No of Cancellation Alerts Found: " + cancellationAlertNo.getText());
		}

		List<WebElement> timeFormatOptions = new Select(timeFormat).getOptions();
		for (int timeFormatOption = 0; timeFormatOption < timeFormatOptions.size(); timeFormatOption++) {
			selectByVisibleText(timeFormat, timeFormatOptions.get(timeFormatOption).getText());
			clickOn(refreshBtn);
			// timeFormat = findElementByXpath(timeZone);
			timeFormatOptions = new Select(timeFormat).getOptions();
			System.out.println("No of Assignment Alerts Found: " + assignmentAlertNo.getText()
					+ " and No of Cancellation Alerts Found: " + cancellationAlertNo.getText());
		}

		waitForElementVisibility(refreshInterval);
	}

	public void verifyNoOfAlertRowsForAAAndPA() {
		assertTrue(Integer.parseInt(assignmentAlertNo.getText()) == assignmentAlertRows.size(),
				"No of Assignment Alert Rows are not matching with the message!");
		assertTrue(Integer.parseInt(cancellationAlertNo.getText()) == cancellationAlertRows.size(),
				"No of Cancellation Alert Rows not matching with the message!");
	}

	public void refreshResults(String tenant, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue) throws InterruptedException {
		selectByVisibleText(tenants, tenant);
		selectByValue(timeFrameFilter, timeFrameFilterValue);
		selectByVisibleText(timeFormat, timeFormatValue);
		selectByValue(refreshInterval, refreshIntervalValue);
		clickOn(refreshBtn);
	}

	public void verifyAssignmentTrip(String reservationId) {
		clickOn(assignmentTrip);
		clickOn(assignmentTripSearchField);
		typeInText(assignmentTripSearchField, reservationId);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
	}

	public void verifyAssignments(String reservationId) throws Exception {
		verifyAssignmentTrip(reservationId);
		String reservationResult = tripAssignmentReservationResult.getText();
		System.out.println("table" + reservationResult.substring(Math.max(0, reservationResult.length() - 6)));
		System.out.println("reservation" + reservationId);
		assertEquals((reservationResult.substring(Math.max(0, reservationResult.length() - 6))), reservationId,
				"Reservation is not created under assignment section ");
	}

	public void verifyAssignmentBGColor(String color) {
		String colorString = assignmentAlertColumn.getAttribute("style");
		String[] arrColor = colorString.split("rgb");
		assertTrue(getBGColorForCode(arrColor[1].split(";")[0]).equalsIgnoreCase(color), "Color does not match!");
	}

	private String getBGColorForCode(String code) {
		String color = "";
		if (code.equals("(173, 216, 230)"))
			color = "blue";
		return color;
	}

	public void verifyCancellations(String reservationId) throws Exception {
		verifyCancellationAlerts(reservationId);
		String reservationResult = tripCancellationReservationResult.getText();
		System.out.println("table" + reservationResult.substring(Math.max(0, reservationResult.length() - 6)));
		System.out.println("reservation" + reservationId);
		assertEquals((reservationResult.substring(Math.max(0, reservationResult.length() - 6))), reservationId,
				"Reservation is not created under cancellation section ");
	}

	public void verifyCancellationAlerts(String reservationId) {
		clickOn(cancellationTrip);
		clickOn(cancelTripSearchField);
		typeInText(cancelTripSearchField, reservationId);
		clickOn(cancelTextFilters);
		clickOn(cancelContainsTextFilters);
	}

	public void clickOnAssignmentReservationLink() {
		clickOn(tripAssignmentReservationResult);
		pouUpExitsClickYes();
		// popupAlert();
	}

	public void clickOnAssignmentReservationLink(int assignmentRow) {
		clickOn(findElementByXpath(assignmentAlertRowsXpath + assignmentRow + assignmentAlertRowsXpath1));
	}

	public void clickOnCancellationReservationLink() {
		clickOn(tripCancellationReservationResult, "DashboardPage -> CanellationAlertsTripLink");
	}

	public void verifyAssignmentAlert(String assignmentAlertExpectedResult) {
		Assert.assertEquals(assignmentAlert.getText(), assignmentAlertExpectedResult, "Reservation exists");
	}

	public void verifyCancellationAlert(int expectedRows) {
		Assert.assertEquals(cancellationAlertRows.size(), expectedRows, "Cancellation reservation is visible");
	}

	public void clickOnIgnoreLink_PA() {
		clickOn(pendingAssignments_IgnoreLink);
	}

	public void clickOnIgnoreLink_PC() {
		clickOn(pendingCancellation_IgnoreLink);
	}

	public void clickYesAlertBtn() {
		clickOn(YesAlertBtn);
	}

	public void clickOnIgnoreLink(int assignmentRow) {
		clickOn(findElementByXpath(assignmentAlertRowsXpath + assignmentRow + ignorelink));
	}

	public void clickOnIgnoreLink() {
		clickOn(ignoreLink);
	}

	public void clickOnViewNotesLink() {
		clickOn(notesLink);
	}

	public void clickOnCancellationIgnoreLink(int cancellationRow) {
		clickOn(findElementByXpath(cancellationAlertRowsXpath + cancellationRow + "]/td[13]/a"));
	}

	public int selectTripUnderAssignmentsAlert(String roomCountValue, String locationValue, String checkInDateValue) {

		clickOn(createdTime);
		clickOn(pickUpSearchField);
		typeInText(pickUpSearchField, checkInDateValue);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		int rowCount = assignmentAlertRows.size();
		if (rowCount > 1) {
			clickOn(noOfRooms);
			clickOn(assignmentTripSearchField);
			typeInText(assignmentTripSearchField, roomCountValue);
			clickOn(assignmentTextFilters);
			clickOn(assignmentContainsTextFilters);
			rowCount = assignmentAlertRows.size();
			System.out.println(rowCount);
			if (rowCount == 1) {
				return rowCount;
			} else if (assignmentAlertRows.size() > 1) {
				// VERIFY THE BELOW CODE
				rowCount = assignmentAlertRows.size();
				clickOn(assignmentLocation);
				clickOn(assignmentTripSearchField);
				typeInText(assignmentTripSearchField, locationValue);
				clickOn(assignmentTextFilters);
				clickOn(assignmentContainsTextFilters);
				rowCount = assignmentAlertRows.size();
				return rowCount;
			} else
				return rowCount;
		} else
			return rowCount;
	}

	public int selectTripUnderCancellationAlerts(String roomCountValue, String locationValue, String checkInDateValue) {

		clickOn(cancellationPickupTime);
		clickOn(cancellationpickUpSearchField);
		String date = checkInDateValue.substring(0, 2);
		String month = checkInDateValue.substring(3, 6);
		String year = checkInDateValue.substring(9, 11);
		typeInText(cancellationpickUpSearchField, date + month + year);
		clickOn(cancelTextFilters);
		clickOn(cancelContainsTextFilters);
		int rowCount = cancellationAlertRows.size();
		if (rowCount > 1) {
			clickOn(cancellationNoOfRooms);
			clickOn(cancelTripSearchField);
			typeInText(cancelTripSearchField, "S" + roomCountValue);
			clickOn(cancelTextFilters);
			clickOn(cancelContainsTextFilters);
			rowCount = cancellationAlertRows.size();
			System.out.println(rowCount);
			if (rowCount == 1) {
				return rowCount;
			} else if (cancellationAlertRows.size() > 1) {
				// VERIFY THE BELOW CODE
				clickOn(cancellationLocation);
				clickOn(cancelTripSearchField);
				typeInText(cancelTripSearchField, locationValue);
				clickOn(cancelTextFilters);
				clickOn(cancelContainsTextFilters);
				rowCount = cancellationAlertRows.size();
				return rowCount;
			} else
				return rowCount;
		} else
			return rowCount;
	}

	public void verifyAssignmentAlertRow(int row) {
		Assert.assertEquals(assignmentAlertRows.size(), row, "Assignment reservation is visible");
	}

	public void verifyNoOfRooms_PC(String expectedValue) {
		assertEquals(noOfRooms_PC.getText().trim(), expectedValue, "No Of Rooms Mismatch!");
	}

	public void clickOnCancellationReservationLink(int cancellationRow) {
		clickOn(findElementByXpath(cancellationAlertRowsXpath + cancellationRow + cancellationAlertRowsXpath1));
	}

	public String getAssignmentReservationLinkText(int assignmentRow) {
		String trip = findElementByXpath(assignmentAlertRowsXpath + assignmentRow + assignmentAlertRowsXpath1)
				.getText();
		return trip;
	}

	public void filterByAlertType(String alertType) {
		waitForPageLoad(driver);
		clickOn(searchAlertType);
		typeInText(textSearchAlertType, alertType);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		waitForPageLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int filterByRoomCount(String roomCount) {
		waitForPageLoad(driver);
		clickOn(noOfRooms);
		clickOn(assignmentTripSearchField);
		typeInText(assignmentTripSearchField, roomCount);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		waitForPageLoad(driver);
		int rowCount = assignmentAlertRows.size();
		if (rowCount < 1) {
			Verify.verify(false, "No Trips found in dashboard with room count" + roomCount, rowCount);
		}
		return rowCount;
	}

	public void clickOnAvailableTrip() {
		int rowCount = assignmentAlertRows.size();
		if (rowCount >= 1)
			clickOn(fistTripInList);
		else
			Assert.fail("No records found with given Alert Type");
	}

	public String getTripNumber() {
		String tripNumber = getTextOfElement(getTripNumber);
		System.out.println("Trip Number :" + tripNumber);
		return tripNumber;
	}

	public String getStartDate() throws Exception {
		String tripStartDate = getTextOfElement(getStartDate);
		String trimDate = tripStartDate.trim();

		SimpleDateFormat fromUser = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");

		try {

			String reformattedStr = myFormat.format(fromUser.parse(trimDate));
			System.out.println(reformattedStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println("Trip Number :" + trimDate);
		// Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(trimDate);
		// return date1;
		return myFormat.format(fromUser.parse(trimDate));
	}

	public String getLocation() {
		String tripLoc = getTextOfElement(getLocation);
		System.out.println("Trip Number :" + tripLoc.trim());
		return tripLoc.trim();
	}

	public String getFlightNumber() {
		String flightNumber = getTextOfElement(firtRecordFlightNumberInReservations);
		System.out.println("Flight Number of the selected record is : " + flightNumber);
		return flightNumber;
	}

	public void processTheAlert(String confirmationCode, String paymentMethod) throws InterruptedException {
		Thread.sleep(2000);
		clickOn(firtRecordInReservations);
		Thread.sleep(2000);
		pouUpExitsClickYes();
		waitForPageLoad(driver);
		Thread.sleep(3000);

		WebElement gtSection = driver.findElement(By.className("hed"));
		boolean gtSectionVisiblity = gtSection.isDisplayed();
		if (gtSectionVisiblity == true)
			clickOn(clickOnGTSectionHeader);
		System.out.println("clicked on GT");
		Thread.sleep(10000);
		toHotelGTSelection();
		fromHotelGTSelection();
		clickOn(nextBtn);
		waitForPageLoad(driver);
		pouUpExitsClickYes();
		Thread.sleep(3000);
		confirmCode.clear();
		typeInText(confirmCode, confirmationCode);
		selectPaymentMethod(paymentMethod);
		clickOn(button_Finish);
		System.out.println("Alert Body :" + alertBody.getText());
		if (alertBody.getText().contains("Reservation has been booked"))
			;
		clickOn(popUpOk);

	}

	public void toHotelGTSelection() {
		Select sel = new Select(toHotelGTSupplier);
		// driver.findElement(By.id("toHotelGtSupplierId0")));
		WebElement selectedValue = sel.getFirstSelectedOption();
		String selectValueIs = selectedValue.getText();
		System.out.println("Select option is :" + selectValueIs);
		if (selectValueIs.equals("Select"))
			sel.selectByIndex(1);

	}

	public void fromHotelGTSelection() {
		Select sel1 = new Select(fromHotelGTSupplier);
		// driver.findElement(By.id("fromHotelGtSupplierId1")));
		WebElement selectedFromValue = sel1.getFirstSelectedOption();
		sel1.selectByVisibleText("IGNORED");
		String selectFromValueIs = selectedFromValue.getText();
		System.out.println("Select option is :" + selectFromValueIs);
		boolean notesPopup = ignoreNotesToGT.isDisplayed();
		if (notesPopup == true) {
			typeInText(ignoreNotesToGT, "Testing GT ignored on confirm booking");
			clickOn(popUpOk);
		}
	}

	public void pouUpExitsClickYes() {
		try {
			boolean checkPOP_Up = popUpYes.isDisplayed();
			if (checkPOP_Up == true)
				clickOn(popUpYes);
		} catch (Exception e) {
		}
	}

	public void selectPaymentMethod(String paymentMethod) {
		Select venPayment = new Select(hotelPaymentMethod);
		venPayment.selectByVisibleText(paymentMethod);
		/* Changed code- Checks for tab and perform operations on it */
		try {

			List<WebElement> tabs = findElementsByXpath(tabsCount);
			if (tabs.size() >= 1) {
				for (int tabcount = 1; tabcount <= tabs.size() - 1; tabcount++) {
					clickOn(findElementById(gtTab + tabcount), "AddHotelAndGtReservationPage -> GTName Tab");
					// typeInText(findElementById(gtCancellationCode+1),
					// "confirm");
					Select venGTPayment = new Select(findElementByXpath(gtPaymentXPath1 + tabcount + gtPaymentXpath2));
					venGTPayment.selectByVisibleText(paymentMethod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int verifyTripUsingTripNumberAndCreatedDate(String reservationId, String checkInDateValue) {
		waitForElementVisibility(assignmentTrip);
		clickOn(assignmentTrip);
		clickOn(assignmentTripSearchField);
		typeInText(assignmentTripSearchField, reservationId);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		clickOn(createdTime);
		// clickOn(pickUp);
		waitForElementVisibility(pickUpSearchField);
		typeInText(pickUpSearchField, checkInDateValue);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		waitTime(5000);
		int rowCount = assignmentAlertRows.size();
		if (rowCount == 0)
			Assert.fail("Trips not found in dashboard");
		return rowCount;

	}

	public int searchWithTripNumber(String reservationId) {
		clickOn(assignmentTrip);
		clickOn(assignmentTripSearchField);
		typeInText(assignmentTripSearchField, reservationId);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		int rowCount = assignmentAlertRows.size();
		if (rowCount == 0)
			Assert.fail("Trip id " + reservationId + "not found in dashboard");
		return rowCount;
	}

	public void selectTrip() {
		clickOn(tripAssignmentReservationResult);
	}

	public void clearAllFilter() {
		clickOn(assignmentTrip);
		// clickOn(assignmentTripSearchField);
		clickOn(clearAllFilters);

	}

	/*
	 * This Method clicks on CheckWithAirlines Button, switches to Query Request and
	 * Responses Window, Adds Query and Verifies Query and closes window
	 */
	public void checkWithAirlines(String text) {

		try {
			waitForElementVisibility(checkWithAirline);
			clickOn(checkWithAirline);
			// Switch to Reservation Queries for Trip Window
			switchToWindow();
			typeInText(enterQueryTextbox, text);
			clickOn(addButton);

			// To check Query is successfully added or not
			int tablesize = queriesAndResponses.size();
			for (int rowcount = 2; rowcount < tablesize; rowcount++) {
				WebElement tripNote = findElementByXpath(tripNotesXpath1 + rowcount + tripNotesXpath2);
				String tripNotes = tripNote.getText();
				if (tripNotes.equalsIgnoreCase(text)) {
					System.out.println("Trip Note successfully added");
					break;
				}
				rowcount++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickOn(closeButton);
		switchToWindow();
	}

	/*
	 * Get color of Trip Row
	 */
	public String getRowColorofTrip() throws Exception {
		String getColor = assignmentAlertColumn.getCssValue("background-color");
		System.out.println(getColor);
		return getColor;
	}

	/*
	 * Compares Trip color
	 */
	public void compareTripColor(String reqcolor) throws Exception {
		String getColor = getRowColorofTrip();
		if (getColor.equalsIgnoreCase(reqcolor))
			throw new Exception("Changes not happened in background color");
		else
			System.out.println("Changes happened in background color");
	}

	public void clickOnignoreReservation() {
		waitForElementVisibility(ignoreReservationButton);
		clickOn(ignoreReservationButton);
	}

	public void ignoreReservation() {
		waitForElementVisibility(selectReasonOther);
		clickOn(addCommentButton);
		clickOn(okButton);
	}

	public String getarrivalFilghtNumber() {
		String arrivalFlightVal = arrivalFlight.getText().trim();
		return arrivalFlightVal;
	}

	public String getarrivalDate() {
		String arrivalDateVal = arrivalDate.getText().trim();
		return arrivalDateVal;

	}

	public void clickOnFindRelatedReservation() {
		waitForElementVisibility(findRelatedReservationButton);
		clickOn(findRelatedReservationButton);
		switchToWindow();

	}

	public void getRelatedReservation(String arrivalFlightNum) throws Exception {
		switchToWindow();
		getDriver().manage().window().maximize();
		int tablesize = relatedReservationTable.size();
		if (tablesize > 1) {
			for (int rowcount = 2; rowcount <= tablesize; rowcount++) {
				WebElement arrivalFlights = findElementByXpath(arrivalFlightXpath1 + rowcount + arrivalFlightXpath2);
				String arrivalFlightValue = arrivalFlights.getText();
				if (arrivalFlightNum.equalsIgnoreCase(arrivalFlightValue)) {
					System.out.println();
					Assert.assertEquals(arrivalFlightNum, arrivalFlightValue, "Related Reservation Found");
					switchToWindow();
					break;
				}
			}
		} else
			throw new Exception("No Related Reservation Available");
	}

	public void searchForActiveBlock(String statusValue, String startDateValue, String endDateValue) {
		waitForElementVisibility(activeBlocks);
		clickOn(activeBlocks);
		clickOn(statuses);
		clickOn(statuses);
		typeInText(startDate, startDateValue);
		typeInText(endDate, endDateValue);
		clickOn(refreshActiveBlocks);
	}

	public int verifyActiveBlocks(String adhocType, String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String roomCountValue) {

		waitForElementVisibility(noOfActiveBlocks);
		int rowCount = 0;
		for (int i = 1; i < activeBlocksTable.size(); i++) {
			List<WebElement> data = findElementsByXpath(reservationTableRowXpath + i + reservationTableRowXpath1);
			boolean checkInTime = false;
			boolean checkOutTime = false;
			boolean originalRooms = false;
			String adhoc = null;
			for (int j = 0; j < data.size(); j++) {

				if (j == 0) {
					adhoc = data.get(j).getText();
				}
				if (adhoc.contains("Adhoc-" + adhocType)) {
					if (!checkInTime)
						checkInTime = data.get(j).getText().equals(checkInDateValue + " " + checkInTimeValue);
					if (!checkOutTime)
						checkOutTime = data.get(j).getText().equals(checkOutDateValue + " " + checkOutTimeValue);
					if (!originalRooms)
						originalRooms = data.get(j).getText().equals(roomCountValue);
				}

			}
			rowCount++;
			if (checkInTime && checkOutTime && originalRooms == true)
				return rowCount;

		}
		return rowCount;
	}

	public void verifyStatus(int rowCount, String expectedValue) {
		String status = findElementByXpath(reservationTableRowXpath + rowCount + supplier).getText();
		Assert.assertEquals(status, expectedValue, "Status is not changed to " + status);
	}

	public String getReservationId() {
		String reservId = reservationId.getText();
		System.out.println(reservId);
		return reservId;
	}

	public String getCheckIndate() {
		String checkIndate = checkInDate.getText();
		System.out.println(checkIndate);
		return checkIndate;
	}

	public void addNotes(String text) {
		clickOnViewNotesLink();
		switchToWindow();
		typeInText(enterQueryTextbox, text);
		clickOn(addCommentButton);
		clickOn(button_Finish);
		clickOn(closeButton);
		switchToWindow();
	}

	public void verifyNotesInDashboard(String text, String indicator) throws Exception {
		clickOnViewNotesLink();
		switchToWindow();
		int resevNotesCount = reservationNotesTableRows.size();
		if (resevNotesCount > 1) {
			for (int rowcount = 1; rowcount <= resevNotesCount; rowcount++) {
				if (indicator == "trip") {
					WebElement tripNote = findElementByXpath(
							reservationNotesXpath1 + rowcount + reservationNotesXpath2);
					String tripNotes = tripNote.getText();
					if (tripNotes.equalsIgnoreCase(text)) {
						System.out.println("Trip Note successfully added");
						break;
					}
				} else if (indicator == "btTrip") {
					do {
						rowcount = rowcount + 1;
					} while (indicator != "btTrip");
					WebElement btTripNote = findElementByXpath(
							reservationGroupNotes1 + rowcount + reservationGroupNotes2);
					String btTripsNotes = btTripNote.getText();
					if (btTripsNotes.equalsIgnoreCase(text)) {
						System.out.println("Trip Note successfully added");
						break;
					}
				}

			}
		} else
			throw new Exception("Notes Not Found");

		clickOn(closeButton);
		switchToWindow();
	}

	public void searchPCWithTripNumber(String tripId) {
		System.out.println();
		clickOn(cancellationTrip, "DashboardPage -> Trip Filter");
		clickOn(cancelTripSearchField, "DashboardPage -> Search");
		typeInText(cancelTripSearchField, tripId, "DashboardPage -> SearchTrip");
		clickOn(cancelTextFilters, "DashboardPage -> TextFilters");
		clickOn(cancelContainsTextFilters, "DashboardPage -> Contains Option");
		clickOnCancellationReservationLink();
		checkForAlertBox();
	}

	public void enterCancellationCodeforHotelandGt(String cancellationCode) {
		try {
			Thread.sleep(4000);
			typeInText(confCode, cancellationCode, "HotelLayoverToBeAssigned -> ConfirmationCode");
			confCode.sendKeys(Keys.TAB);
			List<WebElement> tabs = findElementsByXpath(tabsCount);
			if (tabs.size() >= 1) {
				for (int tabcount = 1; tabcount <= tabs.size() - 1; tabcount++) {
					clickOn(findElementById(gtTab + tabcount), "HotelAndGtReservationPage -> GT Tab");
					typeInText(findElementById(gtCancellationCode + tabcount), cancellationCode,
							"HotelAndGtReservationPage -> Confirmationcodes");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectAPICreditCardForGT(String paymentMethodForGT, String cardNumber) {
		try {
			List<WebElement> tabs = findElementsByXpath(tabsCount);
			if (tabs.size() >= 1) {
				for (int tabcount = 1; tabcount <= tabs.size() - 1; tabcount++) {
					clickOn(findElementById(gtTab + tabcount), "HotelAndGtReservationPage -> GT Tab");
					typeInText(findElementById(gtCancellationCode + 1), "confirm");
					Select venGTPayment = new Select(findElementByXpath(gtPaymentXPath1 + tabcount + gtPaymentXpath2));
					venGTPayment.selectByVisibleText(paymentMethodForGT);
					checkForAlertBox();
					Select apiCardNum = new Select(
							findElementByXpath(apiCardNumberXpath1 + tabcount + apiCardNumberXpath2));
					apiCardNum.selectByVisibleText(cardNumber);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectTripUsingTripNumber(String tripId) {
		clickOn(assignmentTrip);
		clickOn(assignmentTripSearchField);
		typeInText(assignmentTripSearchField, tripId);
		clickOn(assignmentTextFilters);
		clickOn(assignmentContainsTextFilters);
		clickOn(tripAssignmentReservationResult);

	}

	public void notesToIgnore(String notesToIgnoreValue) {
		boolean notesPopup = ignoreNotesToGT.isDisplayed();
		if (notesPopup == true) {
			typeInText(ignoreNotesToGT, notesToIgnoreValue);
			clickOn(popUpOk);
		}
	}

	public void checkForAlertBox() {
		while (alertBox.isDisplayed()) {
			clickOn(alertOK);
		}
	}

	public void clickOnReservationLink() throws IOException {
		waitTime(2000);
		takeScreenshots();
		clickOn(tripAssignmentReservationResult);
		waitTime(2000);
		pouUpExitsClickYes();

	}

	public void clickOnReservationLink_Latam() {
		waitTime(2000);
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		clickOn(tripAssignmentReservationResult);
		checkForAlertBox();
		switchToNewWindow(parentHandle);
		getDriver().close();
		getDriver().switchTo().window(parentHandle.get(0));

	}

	public int searchTripWithCreatedDate() {
		clickOn(createdTime, "Created Date Header");
		// clickOn(pickUp);
		waitForElementVisibility(pickUpSearchField);
		typeInText(pickUpSearchField, currentDate(), "Created Date Search textbox");
		clickOn(assignmentTextFilters, "Created Date Serach - Text Filters");
		clickOn(assignmentContainsTextFilters, "Created Date Serach - Text Contains link");
		int rowCount = assignmentAlertRows.size();
		if (rowCount == 0)
			Assert.fail("Trips not found in dashboard");
		return rowCount;

	}

	public void searchForAdhocPATrip(String tripName, String locationName) throws Exception {
		boolean statusFlag = false;
		for (int tripRow = 1; tripRow <= tripsPAList.size(); tripRow++) {
			String rowValue = findElementByXpath(tableTRXPath + tripRow + endingXPathTripName).getText();
			String locValue = findElementByXpath(tableTRXPath + tripRow + endingXPathLoc).getText();
			System.out.println(rowValue);
			if (rowValue.contains(tripName.trim()) && locValue.contains(locationName.trim())) {
				ExtentTestManager.getTest().log(LogStatus.INFO,
						"Trip with " + tripName + " and " + locationName + " status is found " + rowValue);
				statusFlag = true;
				clickOn(findElementByXpath(tableTRXPath + tripRow + endingXPathTripName),
						"Dashboard Page -> Trip :" + rowValue);
				takeScreenshots();
				clickOn(alert_CheckNotes, "Dashboard Page -> Check Notes Alert OK button");
				takeScreenshots();
				break;
			}

		}
		if (!statusFlag) {
			throw new Exception("Trip not found with required hotel " + tripName + " and status " + locationName);
		}

	}

	public String hotelSelectionWithGTAndBook() throws IOException, InterruptedException {
		String hotelName = null;
		for (int i = 2; i < list_columnGT.size(); i++) {
			hotelName = driver.findElement(By.xpath(hotelSelectionXPath1 + i + hotelSelectionXPathName)).getText();
			String gtStatus = driver.findElement(By.xpath(hotelSelectionXPath1 + i + hotelSelectionXPathGT)).getText();
			WebElement hotelCheckbox = driver.findElement(By.xpath(hotelSelectionXPath1 + i + hotelSelectionXPath2));
			if (gtStatus.trim().equals("Yes"))
				waitForElementVisibility(hotelCheckbox);
			clickOn(hotelCheckbox, "Hotel Checkbox");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Following Hotel as been selected : " + hotelName);
			takeScreenshots();
			break;

		}
		typeInText(confirmCode, "booked", "Trip Booking Page -> Confirmation Code textbox");
		confirmCode.sendKeys(Keys.TAB);
		takeScreenshots();
		clickOn(btn_Next, "Trip Booking Page -> Next button");
		if (driver.findElements(By.xpath("//div[@id='AlertBox']//div[@class='bot']/button[@id='alertOK']")).size() > 0)
			clickOn(bookingAlertOK, "Alert Ok button");
		takeScreenshots();
		typeInText(roomRate, "89", "Trip Booking Page -> Room Rate textbox");
		takeScreenshots();
		clickOn(button_Finish, "Trip Booking Page -> Finish Button");
		takeScreenshots();
		WebElement element = driver.findElement(By.xpath("//div[4]//div[3]//button[@id='alertOK']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		takeScreenshots();
		unExpectedAcceptAlert();
		Thread.sleep(4000);
		return hotelName;
	}

	public void selectMultipleTenants(String tenant) {
		selectByVisibleText(tenants, tenant);
		clickOn(refreshBtn);
	}

	@FindBy(xpath = "//table[@id='cancelAlertsList']//tr/td[11]")
	private List<WebElement> venderList;

	public String[] selectServiceType(String serviceType) throws IOException {
		String returnValue[] = new String[3];
		for (int i = 0; i < serviceTypeCellValue.size(); ++i) {
			if (serviceTypeCellValue.get(i).getText().contains(serviceType)) {
				returnValue[0] = getCancelAlertTripNumber.get(i).getText();
				returnValue[1] = venderList.get(i).getText();
				serviceTypeCellCheckbox.get(i).click();
				break;
			}
		}
		takeScreenshots();
		return returnValue;

	}

	public void clickBulkCancelBtn() {
		clickOn(btn_BulkCancel, "OpsDashboard -> Bulk Assign Button");
	}

	public void enterHotelCancelConfirmCode(String confirmationCode) {
		typeInText(textbox_HotelConfirmationNumber, confirmationCode);
	}

	public void enterGTCancelConfirmCode(String confirmationCode) {
		typeInText(textbox_GTConfirmationNumber, confirmationCode);
	}

	public void clickOnFinishButton() throws IOException {
		clickOn(btn_Finish, "Bulk Cancellation page - > Finish Button");
		checkForAlertBox();
		takeScreenshots();
	}

	public void clickOnFinishCancelAllButton() {
		clickOn(btn_FinishCancelAll, "Bulk Cancellation page -> Finish Cancel All Button");
	}

	public void tripSelection(String tripIds) throws Exception {
		int textCount = 0;
		List<String> tripIDsList = Arrays.asList(tripIds.split(","));
		List<WebElement> tripsRowList = driver.findElements(By.xpath("//tables[@id='cancelAlertsList']//tr/td[3]/a"));
		List<WebElement> tripCheckBoxList = driver
				.findElements(By.xpath("//table[@id='cancelAlertsList']//tr/td[1]/input"));
		for (int tripRow = 0; tripRow < tripsRowList.size(); tripRow++) {
			if (textCount < tripIDsList.size())
				if (tripsRowList.get(tripRow).getText().trim().contains(tripIDsList.get(textCount))) {
					tripCheckBoxList.get(tripRow).click();
					textCount++;
				}
		}
		takeScreenshots();
	}

	public void filterByAlertTypeCancelAlertList(String alertType) {
		waitForPageLoad(driver);
		clickOn(cancelAlertSeriveTypeSearch);
		typeInText(cancelAlertTextSearchAlertType, alertType);
		clickOn(cancelAlertTextFilters);
		clickOn(cancelAlertContainsTextFilters);
		waitForPageLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void unCheckGTOnBulkCancellationPage() {
		if (unCheckGTBulkCancel.isSelected()) {
			clickOn(unCheckGTBulkCancel);
		} else {
			System.out.println("GT is already unchecked or No GT found for this trip");
		}

	}

	public void selectPaymentDetails_BT(String paymentMethod) throws InterruptedException {
		waitForElementVisibility(paymentType);
		Thread.sleep(2000);
		selectByVisibleText(paymentType, paymentMethod);
		Thread.sleep(3000);
	}

	public void selectPaymentMethodSynaptic(String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue) {
		Select venPayment = new Select(hotelPaymentMethod);
		venPayment.selectByVisibleText(paymentMethod);
		/* Changed code- Checks for tab and perform operations on it */
		try {
			try {
				checkForAlertBox();
			} catch (Exception e) {
				e.printStackTrace();
			}

			typeInText(ccNumber, ccNumberValue, "Synaptic Credit Card Number text box");
			typeInText(ccCSVNumber, ccCSVNumberValue, "Synaptic Credit Card CVV Number text box");
			typeInText(expDateCC, ccExpiryDate, "Synaptic Credit Card Expiry Date text box");
			typeInText(merchantLog, merchantLogValue, "Synaptic Credit Card Merchant log text box");
			takeScreenshots();
			List<WebElement> tabs = findElementsByXpath(tabsCount);
			if (tabs.size() >= 1) {
				for (int tabcount = 1; tabcount <= tabs.size() - 1; tabcount++) {
					clickOn(findElementById(gtTab + tabcount), "AddHotelAndGtReservationPage -> GTName Tab");
					Select venGTPayment = new Select(findElementByXpath(gtPaymentXPath1 + tabcount + gtPaymentXpath2));
					venGTPayment.selectByVisibleText(paymentMethod);
					try {
						checkForAlertBox();
					} catch (Exception e) {
						e.printStackTrace();
					}
					typeInText(ccNumberGT, ccNumberValue, "Synaptic Credit Card Number text box");
					typeInText(ccCSVNumberGT, ccCSVNumberValue, "Synaptic Credit Card CVV Number text box");
					typeInText(expDateCCGT, ccExpiryDate, "Synaptic Credit Card Expiry Date text box");
					typeInText(merchantLogGT, merchantLogValue, "Synaptic Credit Card Merchant log text box");
					takeScreenshots();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectPaymentMethodSynapticSingleGT(String paymentMethodHotel, String gt1PaymentMethod,
			String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate, String merchantLogValue,
			String gt2PaymentMethod, String airlineCCType) {
		Select venPayment = new Select(hotelPaymentMethod);
		venPayment.selectByVisibleText(paymentMethodHotel);
		/* Changed code- Checks for tab and perform operations on it */
		try {
			takeScreenshots();
			List<WebElement> tabs = findElementsByXpath(tabsCount);
			if (tabs.size() >= 1) {
				clickOn(findElementById(gtTab + 1), "AddHotelAndGtReservationPage -> GTName Tab");
				Select venGTPayment = new Select(findElementByXpath(gtPaymentXPath1 + 1 + gtPaymentXpath2));
				venGTPayment.selectByVisibleText(gt1PaymentMethod);
				try {
					checkForAlertBox();
				} catch (Exception e) {
					e.printStackTrace();
				}
				typeInText(ccNumberGT, ccNumberValue, "Synaptic Credit Card Number text box");
				typeInText(ccCSVNumberGT, ccCSVNumberValue, "Synaptic Credit Card CVV Number text box");
				typeInText(expDateCCGT, ccExpiryDate, "Synaptic Credit Card Expiry Date text box");
				typeInText(merchantLogGT, merchantLogValue, "Synaptic Credit Card Merchant log text box");
				takeScreenshots();
			}
			if (tabs.size() >= 2) {
				clickOn(findElementById(gtTab + 2), "AddHotelAndGtReservationPage -> GTName Tab");
				Select venGTPayment = new Select(findElementByXpath(gtPaymentXPath1 + 2 + gtPaymentXpath2));
				venGTPayment.selectByVisibleText(gt2PaymentMethod);
				Select cardNumber = new Select(findElementByXpath(gtPaymentXpaht5 + 2 + gtPaymentXpath3));
				cardNumber.selectByVisibleText(airlineCCType);
				typeInText(findElementByXpath(gtPaymentXPath6 + 2 + gtPaymentXpath4), ccNumberValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectAvailableReservations() {
		clickOn(availableReservation1, "Alert Reservation Page -> Available Reservation 1 Checkbox ");
		clickOn(availableReservation2, "Alert Reservation Page -> Available Reservation 2 Checkbox ");

	}

	public void selectGTProviderBTPA(String gtProviderName) throws InterruptedException {
		clickOn(findElementByXpath(selectGTProviderBTPAXPath1 + gtProviderName + selectGTProviderBTPAXPath2));
		Thread.sleep(3000);

	}

	public void bookBTGT(String confirmCode) throws InterruptedException {
		typeInText(textbox_ConfirmCode, confirmCode, "BT GT booking page confirmation code textbox");
		clickOn(button_Finish);
		Thread.sleep(5000);
		checkForAlertBox();

	}

	public void selectCancellationAlert(String tripId) throws IOException {
		takeScreenshots();
		clickOn(findElementByXpath(selectCancellationAlertTripXPath1 + tripId + selectCancellationAlertTripXPath2));
	}

	public int searchWithTripNumberPC(String reservationId) {
		clickOn(cancellationTrip);
		clickOn(cancelTripSearchField);
		typeInText(cancelTripSearchField, reservationId);
		clickOn(cancelTextFilters);
		clickOn(cancelContainsTextFilters);
		int rowCount = rows_PCList.size();
		if (rowCount == 0)
			Assert.fail("Trip id " + reservationId + "not found in dashboard");
		return rowCount;
	}

	public void selectFirstRecord() {
		clickOn(selectFirstRecordInPCAlertList);
	}

	public void ignorebothGTs() {
		Select sel1 = new Select(toHotelGTSupplier);
		WebElement selectedToValue = sel1.getFirstSelectedOption();
		sel1.selectByVisibleText("IGNORED");
		String selectToValueIs = selectedToValue.getText();
		System.out.println("Select option is :" + selectToValueIs);
		boolean notesPopup = ignoreNotesToGT.isDisplayed();
		if (notesPopup == true) {
			typeInText(ignoreNotesToGT, "Testing GT ignored on confirm booking");
			clickOn(popUpOk);
		}
		Select sel2 = new Select(fromHotelGTSupplier);
		WebElement selectedFromValue = sel2.getFirstSelectedOption();
		sel2.selectByVisibleText("IGNORED");
		String selectFromValueIs = selectedFromValue.getText();
		System.out.println("Select option is :" + selectFromValueIs);
		boolean notesFromPopup = ignoreNotesToGT.isDisplayed();
		if (notesFromPopup == true) {
			typeInText(ignoreNotesToGT, "Testing GT ignored on confirm booking");
			clickOn(popUpOk);
		}
	}

	public void enterBTConfirmationCode(String confirmCode) {
		typeInText(this.confirmCode, confirmCode);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.confirmCode.getText().equals(""))
			typeInText(this.confirmCode, confirmCode);
		this.confirmCode.sendKeys(Keys.TAB);
	}

	String hotelSelectionBTResXPath1 = "//div[contains(text(),'Hotel Providers')]/following-sibling::div/table//tr";
	String hotelProviderXpath1 = "//div[contains(text(),'Hotel Providers')]/following-sibling::div/table//tr[";
	String hotelProviderXpath2 = "]/td[2]";
	String hotelProviderCheckBox = "]/td[1]/input";
	String hotelProviderSelectBoxXpath1 = "//div[contains(text(),'Hotel Providers')]/following-sibling::div/table//a[contains(text(),'";
	String hotelProviderSelectBoxXpath2 = "')]/../preceding-sibling::td/input";

	public void selectReqHotel(String hotelName) throws IOException {
		List<WebElement> hotelProviderRowList = findElementsByXpath(hotelSelectionBTResXPath1);
		for (int hotelProviderRow = 2; hotelProviderRow <= hotelProviderRowList.size(); hotelProviderRow++) {
			String reqHotelName = findElementByXpath(hotelProviderXpath1 + hotelProviderRow + hotelProviderXpath2)
					.getText().trim();
			if (reqHotelName.equalsIgnoreCase(hotelName)) {
				clickOn(findElementByXpath(hotelProviderSelectBoxXpath1 + hotelName + hotelProviderSelectBoxXpath2),
						"HotelLayoverToBeAssignedPage -> Hotel Checkbox");
				break;

			}
		} takeScreenshots();
	}
	
	public void clickOnFinishBtn() throws IOException, InterruptedException {
		clickOn(button_Finish);
			Thread.sleep(1200);
		takeScreenshots();
		checkForAlertBox();
	}
public void selectPaymentMethodManualBooking(String valueToSelect) {
	selectByVisibleText(select_PaymentMethod, valueToSelect);
	try {
		takeScreenshots();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void selectTheSupplier() {
	clickOn(firtRecordInReservations);
}
public void clickYesAlertBtnMB() {
	waitForElementVisibility(findElementById("alertHead"));
	try {
		takeScreenshots();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	clickOn(YesAlertBtn);
}
public int verifyTripUsingTripNumberAndPickupDate(String reservationId, String pickUpDateValue) {
	waitForElementVisibility(assignmentTrip);
	clickOn(assignmentTrip);
	clickOn(assignmentTripSearchField);
	typeInText(assignmentTripSearchField, reservationId);
	clickOn(assignmentTextFilters);
	clickOn(assignmentContainsTextFilters);
	 clickOn(pickUp);
	waitForElementVisibility(pickUpSearchField);
	typeInText(pickUpSearchField, pickUpDateValue);
	clickOn(assignmentTextFilters);
	clickOn(assignmentContainsTextFilters);
	waitTime(5000);
	try {
		takeScreenshots();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int rowCount = assignmentAlertRows.size();
	if (rowCount == 0)
		Assert.fail("Trips not found in dashboard");
	return rowCount;

}

}
