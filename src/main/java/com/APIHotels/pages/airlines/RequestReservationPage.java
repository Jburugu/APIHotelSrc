package com.APIHotels.pages.airlines;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class RequestReservationPage extends BasePage {

	public EventFiringWebDriver driver = null;

	// OPERATIONS -- BUSINESS TRAVEL -- REQUEST RESERVATION

	@FindBy(how = How.CSS, using = "select[name*='tenantList']")
	public WebElement company;

	@FindBy(how = How.ID, using = "bizTravelForm:locCdArea:locCd")
	public WebElement destination;

	@FindBy(how = How.CSS, using = "select[name*='timeZoneOptArea']")
	public WebElement timeFormat;

	@FindBy(how = How.ID, using = "bizTravelForm:arrvDtArea:arrivalDtInputDate")
	public WebElement arrivalDate;

	@FindBy(how = How.ID, using = "bizTravelForm:arrvDtArea:arrivalDtPopupButton")
	public WebElement arrivalDateCalendar;

	@FindBy(how = How.ID, using = "bizTravelForm:arrivalTimeArea:arrivalTime")
	public WebElement arrivalTime;

	@FindBy(how = How.ID, using = "bizTravelForm:deptDtArea:departureDtInputDate")
	public WebElement departureDate;

	@FindBy(how = How.ID, using = "bizTravelForm:deptDtArea:departureDtPopupButton")
	public WebElement departureDateCalendar;

	@FindBy(how = How.ID, using = "bizTravelForm:departureTimeArea:departureTime")
	public WebElement departureTime;

	@FindBy(how = How.ID, using = "bizTravelForm:hotelArea:hotels")
	public WebElement hotels;

	@FindBy(how = How.XPATH, using = "//select[@id='bizTravelForm:hotelArea:hotels']/option[text()='Other']")
	public WebElement hotelsDropdownValueOther;

	@FindBy(how = How.CSS, using = "input[id$='supplierDesc']")
	public WebElement hotelSupplierDesc;

	@FindBy(how = How.ID, using = "bizTravelForm:arrivalAlCodeArea:arrivalAlCode")
	public WebElement arrivalFlightCode;

	@FindBy(how = How.ID, using = "bizTravelForm:btHtArrFltNbrDiv:arrivalFlightNbr")
	public WebElement arrivalFlightNumber;

	@FindBy(how = How.ID, using = "bizTravelForm:deptAlCodeArea:departureAlCode")
	public WebElement departureFlightCode;

	@FindBy(how = How.ID, using = "bizTravelForm:btHtDepFltNbrDiv:departureFlightNbr")
	public WebElement departureFlightNumber;

	@FindBy(how = How.ID, using = "bizTravelForm:hotelEmail:hotelNotificationEmail")
	public WebElement additionalEmailAddresses;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'subscriptions')]")
	public WebElement room;

	@FindBy(how = How.CSS, using = "select[name*='crewType']")
	public WebElement guestType;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable:0:employeeNbr1Area:employeeNbr1")
	public WebElement empId;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable:0:btHtDblNm1Div:name1")
	public WebElement name;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable:0:btHtDblNm2Div:name2")
	public WebElement empName;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable:addMore")
	public WebElement addMore;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable1:addMore")
	public WebElement addMoreAdhoc;

	@FindBy(id = "bizTravelForm:reasonArea1:reason")
	public WebElement reasonAdhoc;
	// UPS
	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable1:0:employeeNbr1Area:employeeNbr1")
	public WebElement empIdUps;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable1:0:name1Area:name1")
	public WebElement empNameUps;

	@FindBy(how = How.ID, using = "bizTravelForm:roomRequestTable1:addMore")
	public WebElement addMoreUps;

	String endeavor_HotelXpath1 = "bizTravelForm:hotelDetailsLabelGroup:";
	String endeavor_HotelXpath2 = ":hotels";

	String endeavor_HotelTextXpath1 = "//*[contains(text(), '";
	String endeavor_HotelTextXpath2 = "')]/../td/input";

	// @FindBy(how = How.XPATH,
	// using="//select[contains(@name,'bizTravelForm:j_id')]")
	// public WebElement accountType;

	@FindBy(how = How.XPATH, using = "//select[contains(@name,'bizTravelForm:j_id')] | //select[contains(@id,'reason')]")
	public WebElement accountTypeOrReason;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Accounting Society / CECO')]")
	public WebElement accountingSociety;

	@FindBy(how = How.CSS, using = "textarea[id*='hotelNotes']")
	public WebElement notes;

	@FindBy(how = How.ID, using = "bizTravelForm:save")
	public WebElement saveButton;

	// Commut airlines
	@FindBy(how = How.XPATH, using = "//input[@title='VIP/Executive Reservation']")
	public WebElement vipOrExecutiveReservation;

	// Airnewzealand
	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'bizTravelForm:roomRequestTable:0')]//select[contains(@id,'department1')]")
	public WebElement departmentCode;

	// AeroMexico
	@FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//input[@type='checkbox']")
	public WebElement profilaxis;

	@FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[2]//select")
	public WebElement businessUnit;

	@FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[3]//select")
	public WebElement aeroMexicoCostCenter;

	@FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[4]//select")
	public WebElement equipment;

	@FindBy(how = How.XPATH, using = "//*[@id='bizTravelForm:accountCodeHotelPanel']//div[5]//select")
	public WebElement aeroMexicoLocation;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'bizTravelForm:roomRequestTable:0')]//select[contains(@id,'account1')]")
	public WebElement costCenter;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'bizTravelForm:roomRequestTable:0')]//select[contains(@id,'businessLine1')]")
	public WebElement aircraftType;

	@FindBy(how = How.CSS, using = "input[id$='supplierDesc']")
	public WebElement hotelDescription;

	// Endeavor
	@FindBy(how = How.CSS, using = "select[id$='businessLine']")
	public WebElement lineOfBusiness;

	@FindBy(how = How.CSS, using = "select[id$='account']")
	public WebElement reason;

	@FindBy(how = How.CSS, using = "input[title$='emailNotification']")
	public WebElement doNotSendMeEmailNotification;

	@FindBy(how = How.ID, using = "bizTravelForm:hotelDetailsLabelPnlGroup")
	public WebElement hotelTable;

	@FindBy(how = How.XPATH, using = ".//span[@id='bizTravelForm:accountCodeHotelPanel']//select[contains(@id,'account')]")
	public WebElement costCenterAirTransit;

	@FindBy(how = How.XPATH, using = "//span[@id='bizTravelForm:accountCodeHotelPanel']//select[contains(@id,'reason')]")
	public WebElement reasonAirTransit;

	@FindBy(how = How.XPATH, using = "//span[@id='bizTravelForm:accountCodeHotelPanel']//select[contains(@id,'account')]")
	public WebElement reasonEndeavor;

	// Alaska
	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'bizTravelForm:roomRequestTable:0')]//select[contains(@id,'accountLocation1')] | //input[contains(@id,'accountCodeHotelActivityLocCd')]")
	public WebElement location;

	@FindBy(how = How.ID, using = "bizTravelForm:send")
	public WebElement sendToVendorWebsite;

	@FindBy(how = How.ID, using = "bizTravelForm:email")
	public WebElement sendToHotelByEmail;

	@FindBy(how = How.ID, using = "bizTravelForm:print")
	public WebElement generateFax;

	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:roomRequestTable']//tbody//tr")
	public List<WebElement> employeeId;

	@FindBy(how = How.XPATH, using = ".//*[@id='saveBizPanelMessages']/dt/span")
	public WebElement processCompletedMessage;

	@FindBy(how = How.ID, using = "saveBizPanelHeader")
	public WebElement savePanelHeader;

	@FindBy(xpath = "//*[@id='bizTravelForm:roomRequestTable:tb']/tr")
	public List<WebElement> roomRows;

	@FindBy(xpath = "//*[@id='bizTravelForm:roomRequestTable1:tb']/tr")
	public List<WebElement> roomRowsAdhoc;

	@FindBy(xpath = "//*[@id='saveBizGrid']/tfoot/tr/td/span/a")
	public WebElement okBtn;

	String roomRequestTableID1 = "bizTravelForm:roomRequestTable:";
	String empNumberId1 = ":employeeNbr";
	String empNumberId2 = "Area:employeeNbr";

	String roomRequestTableIDAdhoc = "bizTravelForm:roomRequestTable1:";

	String empNameId1 = ":btHtDblNm";
	String empNameId2 = "Div:name";

	String empNameAdhoc = ":name1Area:name1";

	String empDeptId1 = ":j_id355:";
	String empDeptId2 = "";

	String roomTypeId = "//select[contains(@id,'subscriptions')]";
	String windowHandler;

	String empID = "bizTravelForm:roomRequestTable:0:employeeNbr1Area:employeeNbr1";
	String empID1 = "bizTravelForm:roomRequestTable1:0:employeeNbr1Area:employeeNbr1";

	// Virgin Australia
	@FindBy(how = How.ID, using = "bizTravelForm:CompanyAreaaccountCodeHotel:companyId")
	public WebElement viginAustraliaCompany;

	@FindBy(how = How.ID, using = "bizTravelForm:organisationAreaaccountCodeHotel:organisationId")
	public WebElement organisation;

	@FindBy(how = How.ID, using = "bizTravelForm:platformaccountCodeHotel:platformName")
	public WebElement platform;

	@FindBy(how = How.ID, using = "bizTravelForm:locationIdAreaaccountCodeHotel:locationId")
	public WebElement virginAustraliaLocation;

	// Princess Cruises
	@FindBy(how = How.ID, using = "bizTravelForm:keyDtArea:keyDtInputDate")
	public WebElement shipDate;

	@FindBy(how = How.ID, using = "bizTravelForm:keyDtArea:keyDtPopupButton")
	public WebElement shipDateCalendar;

	String roomTypePrincessCruise = "//select[contains(@id,'roomType')]";

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'numNights')]")
	public WebElement nights;

	// Mesa Airlines Comapny
	@FindBy(how = How.XPATH, using = "//select[contains(@id,'businessLineId')]")
	public WebElement mesaAirlinesCompany;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'acctReasonId')]")
	public WebElement reasonCode;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'accountId')]")
	public WebElement expenseCode;

	String roomRequest = "//div[contains(@id,'bizTravelForm:roomRequestTable:";
	String departmentXpath = "')]//select[contains(@id,'department1')]";
	String accountXpath = "')]//select[contains(@id,'account1')]";
	String departmentXpath1 = "')]//select[contains(@id,'department";
	String xpath = "')]";
	String accountXpath1 = "')]//select[contains(@id,'account";

	@FindBy(id = "bizTravelForm:costArea:cost")
	private List<WebElement> overrideRate;

	public RequestReservationPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String requestReservationPage(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue) {

		typeInText(destination, destinationValue);
//		Assert.assertEquals(destination.getAttribute("value"), destinationValue, "destination mismatch");

		selectByVisibleText(timeFormat, timeFormatValue);
		// Assert.assertEquals(timeFormat.getAttribute("selected"),timeFormatValue,"timeFormat
		// mismatch");

		waitForElementVisibility(arrivalDateCalendar);
		// typeInText(arrivalDate, currentDate());
		// Assert.assertEquals(arrivalDate.getAttribute("value"), currentDate(),
		// "arrivalDate mismatch");
		waitForElementVisibility(arrivalTime);
		typeInText(arrivalTime, arrivaltimeValue);
//		Assert.assertEquals(arrivalTime.getAttribute("value"), arrivaltimeValue, "arrivalTime mismatch");

		waitForElementVisibility(departureDateCalendar);
		// typeInText(departureDate, currentDate());
//		Assert.assertEquals(departureDate.getAttribute("value"), currentDate(), "departureDate mismatch");
		waitForElementVisibility(departureTime);
		typeInText(departureTime, departuretimeValue);
//		Assert.assertEquals(departureTime.getAttribute("value"), departuretimeValue, "departureTime mismatch");

		typeInText(arrivalFlightCode, arrivalFlightCodeValue);
		Assert.assertEquals(arrivalFlightCode.getAttribute("value"), arrivalFlightCodeValue,
				"arrivalFlightCode mismatch");

		typeInText(arrivalFlightNumber, arrivalFlightNumberValue);
		Assert.assertEquals(arrivalFlightNumber.getAttribute("value"), arrivalFlightNumberValue,
				"arrivalFlightNumber mismatch");

		typeInText(departureFlightCode, departureFlightCodeValue);
		Assert.assertEquals(departureFlightCode.getAttribute("value"), departureFlightCodeValue,
				"departureFlightCode mismatch");

		typeInText(departureFlightNumber, departureFlightNumberValue);
		// Assert.assertEquals(departureFlightNumber.getAttribute("value"),departureFlightNumber,"departureFlightNumber
		// mismatch");
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAdditionalEmailIds(additionalEmailAddressValue);
		return destinationValue;

		// room type , guest name , empname and empid need to be added
		// department code,costcenter,aircrafttype
	}

	public void addAdditionalEmailIds(String additionalEmailAddressValue) {
		for (String email : additionalEmailAddressValue.split(";")) {
			additionalEmailAddresses.sendKeys(email);
			additionalEmailAddresses.sendKeys(Keys.ENTER);
			assertTrue(additionalEmailAddresses.getAttribute("value").contains(email),
					"additionalEmailAddresses mismatch");
		}
	}

	// Exists for Commut airlines
	public void vipOrExecutiveReservation() {
		clickOn(vipOrExecutiveReservation);
	}

	public void company(String companyValue) {
		selectByValue(company, companyValue);
		Assert.assertEquals(company.getAttribute("value"), companyValue, "Company value mismatch");
	}

	public void accountType(String accountTypeValue) {
		int accountTypeVal = Integer.parseInt(accountTypeValue);
		selectByIndex(accountTypeOrReason, accountTypeVal);
	}

	public void adhocReason(String reasonValue) {
		int reasonVal = Integer.parseInt(reasonValue);
		selectByIndex(reasonAdhoc, reasonVal);
	}

	public void accountingSocietyLabel() {
		waitForElementVisibility(accountingSociety);
		// Account information fleet needs to be added
	}

	public void verifySaveBtn() {
		waitForElementVisibility(saveButton);
	}

	public void clickSaveBtn() {
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickOn(saveButton);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String clickGenerateFax() {
		windowHandler = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		clickOn(generateFax);
		return windowHandler;
	}

	public void closeNewPDFWindow(String windowHandler) {
		System.out.println("wndfhtgh" + driver.getTitle());
		switchToNewWindow(windowHandler);
		System.out.println("dfff" + driver.getTitle());
		driver.close();
		switchToWindow(windowHandler);
	}

	public String clickSendToHotelByEmail() {
		windowHandler = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		clickOn(sendToHotelByEmail);
		return windowHandler;
	}

	public void lineOfBusinessReasonAndLocation(String lineOfBusinessValue, String reasonValue, String locationValue) {
		int lineOfBusinessVal = Integer.parseInt(lineOfBusinessValue);
		selectByIndex(lineOfBusiness, lineOfBusinessVal);
		int reasonVal = Integer.parseInt(reasonValue);
		selectByIndex(reason, reasonVal);
		typeInText(location, locationValue);

	}

	public void doNotSendMeEmailNotification() {
		clickOn(doNotSendMeEmailNotification);
	}

	public void verifyHotelsTable() {
		waitForElementVisibility(hotelTable);
	}

	public void location(String locationValue) {
		int locationVal = Integer.parseInt(locationValue);
		selectByIndex(location, locationVal);
	}

	public void verifySendVendorEmailAndGenerateFax() {
		waitForElementVisibility(sendToVendorWebsite);
		waitForElementVisibility(sendToHotelByEmail);
		waitForElementVisibility(generateFax);
	}

	public String hotelDescription(String hotelSupDescValue) {
		waitForElementVisibility(hotelDescription);
		typeInText(hotelDescription, hotelSupDescValue);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotelSupDescValue;
	}

		public void hotel(String hotelValue) {
			waitForElementVisibility(hotelsDropdownValueOther);
			selectByVisibleText(hotels, hotelValue);
			hotels.sendKeys(Keys.TAB);
			try {
				takeScreenshots();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
public void selectHotel(String hotelValue) {

		List<WebElement> hotelList = findElementsByXpath(
				endeavor_HotelTextXpath1 + hotelValue + endeavor_HotelTextXpath2);
		if (hotelList.size() > 0) {
			clickOn(hotelList.get(0));
			hotelList.get(0).sendKeys(Keys.TAB);
		} else {
			WebElement hotel = findElementById(endeavor_HotelXpath1 + 0 + endeavor_HotelXpath2);
			clickOn(hotel);
			hotel.sendKeys(Keys.TAB);
		}
	}

	public void requestReservation(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotelValue, String hotelSupDescValue) {
		requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue);
		hotel(hotelValue);
		hotelDescription(hotelSupDescValue);
		verifySaveBtn();

	}

	public void costCenter(String costCenterValue) {
		waitForElementVisibility(costCenterAirTransit);
		Integer costCenterVal = Integer.parseInt(costCenterValue);
		selectByIndex(costCenterAirTransit, costCenterVal);
	}

	public void reason(String reasonCodeValue) {
		waitForElementVisibility(reasonAirTransit);
		Integer reasonCodeVal = Integer.parseInt(reasonCodeValue);
		selectByIndex(reasonAirTransit, reasonCodeVal);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void selectReason(String reasonCodeValue) {
		waitForElementVisibility(reasonEndeavor);
		Integer reasonCodeVal = Integer.parseInt(reasonCodeValue);
		selectByIndex(reasonEndeavor, reasonCodeVal);
	}

	public void notes(String notesValue) {
		notes.sendKeys(notesValue);
	}

	public void clickOnSendToAPI() {
		clickOn(saveButton);
	}

	// Mesa Airlines
	public void Company(String CompanyValue) {
		int mesaCompanyVal = Integer.parseInt(CompanyValue);
		selectByIndex(mesaAirlinesCompany, mesaCompanyVal);
	}

	public void reasonCode(String reasonCodeValue) {

		int reasonCodeVal = Integer.parseInt(reasonCodeValue);
		selectByIndex(reasonCode, reasonCodeVal);
	}

	public void expenseCode(String expenseCodeValue) {

		int expenseCodeVal = Integer.parseInt(expenseCodeValue);
		selectByIndex(expenseCode, expenseCodeVal);
	}

	public void accountInformationMesa(String CompanyValue, String expenseCodeValue, String reasonCodeValue) {
		Company(CompanyValue);
		expenseCode(expenseCodeValue);
		reasonCode(reasonCodeValue);
	}

	// AeroMexico
	public void businessUnit(String businessUnitValue) {
		int businessUnitVal = Integer.parseInt(businessUnitValue);
		selectByIndex(businessUnit, businessUnitVal);
	}

	public void aeroMexicocostCenter(String aeroMexicocostCenterValue) {
		int aeroMexicocostCenterVal = Integer.parseInt(aeroMexicocostCenterValue);
		selectByIndex(aeroMexicoCostCenter, aeroMexicocostCenterVal);
	}

	public void equipment(String equipmentValue) {
		int equipmentVal = Integer.parseInt(equipmentValue);
		selectByIndex(equipment, equipmentVal);
	}

	public void aeroMexicoLocation(String aeroMexicoLocationValue) {
		int aeroMexicoLocationVal = Integer.parseInt(aeroMexicoLocationValue);
		selectByIndex(aeroMexicoLocation, aeroMexicoLocationVal);
	}

	public void aeroMexicoaccountInfo(String businessUnitValue, String aeroMexicocostCenterValue, String equipmentValue,
			String aeroMexicoLocationValue) {
		clickOn(profilaxis);
		businessUnit(businessUnitValue);
		aeroMexicocostCenter(aeroMexicocostCenterValue);
		equipment(equipmentValue);
		aeroMexicoLocation(aeroMexicoLocationValue);

	}

	public void enterEmpDetailsForRoom(String singleRoomType, String empName) {
		selectByIndex(room, 1);
		typeInText(empIdUps, singleRoomType);
		empIdUps.sendKeys(Keys.TAB);
		waitTime(3000);
		typeInText(empNameUps, empName);
		waitForElementVisibility(addMoreUps);
	}

	// UPS
	public void enterRoomDetails(String singleRoomType) {
		selectByIndex(room, 1);
		typeInText(empIdUps, singleRoomType);
		waitForElementVisibility(addMoreUps);

	}

	public String processingRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		String saveCompleteMessage = processCompletedMessage.getText();
		String reservationId = saveCompleteMessage.split(" ")[5];
		System.out.println("reservationId " + reservationId);
		// System.out.println("save message "
		// +saveCompleteMessage.substring(32));
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation got created with the following ID : reservationId");
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickOn(okBtn);
		return reservationId;
		// writeToExcel("Reservation ID", "ResvID", reservationId);
	}

	public void clickSendToVendorWebsite() {
		clickOn(sendToVendorWebsite);
	}

	public void selectHotelByIndex(String hotelValue) throws Exception {
		waitForElementVisibility(hotelsDropdownValueOther);
		int hotelVal = Integer.parseInt(hotelValue);
		selectByIndex(hotels, hotelVal);
		hotels.sendKeys(Keys.TAB);
		Thread.sleep(3000);
		if (overrideRate.size() > 0) {
			typeInText(overrideRate.get(0), "10");
			overrideRate.get(0).sendKeys(Keys.TAB);
			Thread.sleep(3000);
		}
	}

	public void verifyFunctionalityWithTab() {
		destination.clear();
		destination.equals(driver.switchTo().activeElement());
		destination.sendKeys(Keys.TAB);
		timeFormat.equals(driver.switchTo().activeElement());
		timeFormat.sendKeys(Keys.TAB);
		arrivalDate.equals(driver.switchTo().activeElement());
		arrivalDate.sendKeys(Keys.TAB);
		arrivalTime.equals(driver.switchTo().activeElement());
		arrivalTime.sendKeys(Keys.TAB);
		departureDate.equals(driver.switchTo().activeElement());
		departureDate.sendKeys(Keys.TAB);
		departureTime.equals(driver.switchTo().activeElement());
		departureTime.sendKeys(Keys.TAB);
		hotels.equals(driver.switchTo().activeElement());
		hotels.sendKeys(Keys.TAB);
		arrivalFlightCode.equals(driver.switchTo().activeElement());
		arrivalFlightCode.sendKeys(Keys.TAB);
		arrivalFlightNumber.equals(driver.switchTo().activeElement());
		arrivalFlightNumber.sendKeys(Keys.TAB);
		departureFlightCode.equals(driver.switchTo().activeElement());
		departureFlightCode.sendKeys(Keys.TAB);
		departureFlightNumber.equals(driver.switchTo().activeElement());
		departureFlightNumber.sendKeys(Keys.TAB);
		additionalEmailAddresses.equals(driver.switchTo().activeElement());
		additionalEmailAddresses.sendKeys(Keys.TAB);
		doNotSendMeEmailNotification.equals(driver.switchTo().activeElement());
		doNotSendMeEmailNotification.sendKeys(Keys.TAB);
		room.equals(driver.switchTo().activeElement());
		room.sendKeys(Keys.TAB);
		empId.equals(driver.switchTo().activeElement());
		empId.sendKeys(Keys.TAB);
		accountTypeOrReason.equals(driver.switchTo().activeElement());
		accountTypeOrReason.sendKeys(Keys.TAB);
		accountTypeOrReason.sendKeys(Keys.TAB);
		notes.equals(driver.switchTo().activeElement());
		notes.sendKeys(Keys.TAB);
		saveButton.equals(driver.switchTo().activeElement());
		saveButton.sendKeys(Keys.TAB);
		sendToVendorWebsite.equals(driver.switchTo().activeElement());
		sendToVendorWebsite.sendKeys(Keys.TAB);
		sendToHotelByEmail.equals(driver.switchTo().activeElement());
		sendToHotelByEmail.sendKeys(Keys.TAB);
		generateFax.equals(driver.switchTo().activeElement());

	}

	public void selectRoomTypeAndEnterDetails(String singleRoomType, String doubleRoomType) {
		int newRow = 0;
		if (!singleRoomType.isEmpty()) {
			if (!findElementById(empID).getAttribute("value").isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(singleRoomType.split(",")));
			int i = 0;
			for (String empId : empIds) {
				if (i != 0) {
					// newRow++;
					newRow = roomRows.size();
				}
				if (newRow != 0) {
					clickOn(addMore);
					try {
						Thread.sleep(7000);
					} catch (Exception e) {
					}
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "SINGLE");
				WebElement emp = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp, empId);
				emp.sendKeys(Keys.TAB);
				// wait.until(visibilityofelementlocated(By.id(roomRequestTableID1+newRow+empNameId1+1+empNameId2+1)));
				waitTime(7000);
				WebElement empName = findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1);
				waitForElementTextVisibility(empName, "value");
				waitForPageLoad(getDriver());
				i++;
			}

		}
		if (!doubleRoomType.isEmpty()) {
			if (!findElementById(empID).getAttribute("value").isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(doubleRoomType.split(";")));
			int i = 0;
			for (String empList : empIds) {
				if (i != 0) {
					// newRow++;
					newRow = roomRows.size();
				}
				if (newRow != 0) {
					clickOn(addMore);
					try {
						Thread.sleep(7000);
					} catch (Exception e) {
					}

				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "DOUBLE");
				List<String> empIdList = new ArrayList<String>(Arrays.asList(empList.split(",")));
				String emp1Id = empIdList.get(0);
				String emp2Id = empIdList.get(1);
				WebElement emp1 = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp1, emp1Id);
				emp1.sendKeys(Keys.TAB);
				try {
					Thread.sleep(7000);
				} catch (Exception e) {
				}
				WebElement empName1 = findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1);
				waitForElementTextVisibility(empName1, "value");
				waitForPageLoad(getDriver());
				// wait.until(visibilityofelementlocated(By.id(roomRequestTableID1+newRow+empNameId1+1+empNameId2+1)));
				WebElement emp2 = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 2 + empNumberId2 + 2);
				typeInText(emp2, emp2Id);
				emp2.sendKeys(Keys.TAB);
				try {
					Thread.sleep(7000);
				} catch (Exception e) {
				}
				WebElement empName2 = findElementById(roomRequestTableID1 + newRow + empNameId1 + 2 + empNameId2 + 2);
				waitForElementTextVisibility(empName2, "value");
				waitForPageLoad(getDriver());
				// wait.until(visibilityofelementlocated(By.id(roomRequestTableID1+newRow+empNameId1+2+empNameId2+2)));
				i++;
			}

		}
	}

	public void selectRoomTypeAndEnterDetailsForNonEmployees(String singleRoomType, String doubleRoomType,
			String singleRoomEmpName, String doubleRoomEmpName) {
		int newRow = 0;
		if (!singleRoomType.isEmpty()) {
			if (!findElementById(empID).getAttribute("value").isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(singleRoomType.split(",")));
			List<String> empNames = new ArrayList<String>(Arrays.asList(singleRoomEmpName.split(",")));
			int i = 0;
			for (String empId : empIds) {
				if (newRow != 0) {
					clickOn(addMore);
					if (i != 0)
						newRow++;
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "SINGLE");
				WebElement emp = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp, empId);
				emp.sendKeys(Keys.TAB);
				waitTime(9000);
				typeInText(findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1),
						empNames.get(i));
				i++;
			}

		}
		if (!doubleRoomType.isEmpty()) {
			if (!findElementById(empID).getAttribute("value").isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(doubleRoomType.split(";")));
			List<String> empNames = new ArrayList<String>(Arrays.asList(doubleRoomEmpName.split(";")));
			int i = 0;
			for (String empList : empIds) {
				if (newRow != 0) {
					clickOn(addMore);
					if (i != 0)
						newRow++;
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "DOUBLE");
				List<String> empIdList = new ArrayList<String>(Arrays.asList(empList.split(",")));
				List<String> empNameList = new ArrayList<String>(Arrays.asList(empNames.get(i).split(",")));

				String emp1Id = empIdList.get(0);
				String emp2Id = empIdList.get(1);
				String emp1Name = empNameList.get(0);
				String emp2Name = empNameList.get(1);

				WebElement emp1 = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp1, emp1Id);
				emp1.sendKeys(Keys.TAB);
				waitTime(9000);
				WebElement empName1 = findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1);

				typeInText(empName1, emp1Name);

				WebElement emp2 = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 2 + empNumberId2 + 2);
				typeInText(emp2, emp2Id);
				emp2.sendKeys(Keys.TAB);
				waitTime(9000);
				WebElement empName2 = findElementById(roomRequestTableID1 + newRow + empNameId1 + 2 + empNameId2 + 2);
				typeInText(empName2, emp2Name);
				i++;
			}

		}
	}

	public void elementVisibilityForCompanyOrganisationAndPlatform() {
		waitForElementVisibility(viginAustraliaCompany);
		waitForElementVisibility(organisation);
		waitForElementVisibility(platform);
		waitForElementVisibility(virginAustraliaLocation);
	}

	public void requestReservationPrincessCruises(String portCodeValue, String timeFormatValue, String hotelValue,
			String emailValue) {
		typeInText(destination, portCodeValue);
		selectByVisibleText(timeFormat, timeFormatValue);
		typeInText(shipDate, currentDate());
		waitForElementVisibility(shipDateCalendar);
		hotel(hotelValue);
		typeInText(additionalEmailAddresses, emailValue);
		doNotSendMeEmailNotification();
	}

	public void selectRoomTypeAndEnterEmployeeDetails(String singleRoomType, String doubleRoomType,
			String singleRoomEmpName, String doubleRoomEmpName, String departmentValue, String accountValue) {
		int newRow = 0;
		if (!singleRoomType.isEmpty()) {
			if (!findElementById("bizTravelForm:roomRequestTable:0:employeeNbr1Area:employeeNbr1").getAttribute("value")
					.isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(singleRoomType.split(",")));
			List<String> empNames = new ArrayList<String>(Arrays.asList(singleRoomEmpName.split(",")));
			List<String> department = new ArrayList<>(Arrays.asList(departmentValue.split(",")));
			List<String> account = new ArrayList<>(Arrays.asList(accountValue.split(",")));
			int i = 0;
			for (String empId : empIds) {
				if (i != 0) {
					newRow = roomRows.size();
				}
				if (newRow != 0) {
					clickOn(addMore);
					try {
						Thread.sleep(7000);
					} catch (Exception e) {
					}
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "SINGLE");
				WebElement emp = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp, empId);
				emp.sendKeys(Keys.TAB);
				waitTime(9000);
				typeInText(findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1),
						empNames.get(i));
				selectByIndex(findElementByXpath(roomRequest + newRow + departmentXpath),
						Integer.parseInt(department.get(i)));
				selectByIndex(findElementByXpath(roomRequest + newRow + accountXpath),
						Integer.parseInt(account.get(i)));
				i++;
			}

		}
		if (!doubleRoomType.isEmpty()) {
			if (!findElementById("bizTravelForm:roomRequestTable:0:employeeNbr1Area:employeeNbr1").getAttribute("value")
					.isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(doubleRoomType.split(";")));
			List<String> empNames = new ArrayList<String>(Arrays.asList(doubleRoomEmpName.split(";")));
			int i = 0;
			for (String empList : empIds) {
				if (i != 0) {
					newRow = roomRows.size();
				}
				if (newRow != 0) {
					clickOn(addMore);
					try {
						Thread.sleep(7000);
					} catch (Exception e) {
					}
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "DOUBLE");
				List<String> empIdList = new ArrayList<String>(Arrays.asList(empList.split(",")));
				List<String> empNameList = new ArrayList<String>(Arrays.asList(empNames.get(i).split(",")));
				List<String> departmentList = new ArrayList<>(Arrays.asList(departmentValue.split(",")));
				List<String> accountList = new ArrayList<>(Arrays.asList(accountValue.split(",")));
				String emp1Id = empIdList.get(0);
				String emp2Id = empIdList.get(1);
				String emp1Name = empNameList.get(0);
				String emp2Name = empNameList.get(1);
				String department1 = departmentList.get(0);
				String department2 = departmentList.get(1);
				String account1 = accountList.get(0);
				String account2 = accountList.get(1);

				WebElement emp1 = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp1, emp1Id);
				emp1.sendKeys(Keys.TAB);
				waitTime(9000);
				WebElement empName1 = findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1);
				typeInText(empName1, emp1Name);

				selectByIndex(findElementByXpath(roomRequest + newRow + departmentXpath1 + 1 + xpath),
						Integer.parseInt(department1));
				selectByIndex(findElementByXpath(roomRequest + newRow + accountXpath1 + 1 + xpath),
						Integer.parseInt(account1));

				WebElement emp2 = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 2 + empNumberId2 + 2);
				typeInText(emp2, emp2Id);
				emp2.sendKeys(Keys.TAB);
				waitTime(9000);
				WebElement empName2 = findElementById(roomRequestTableID1 + newRow + empNameId1 + 2 + empNameId2 + 2);
				typeInText(empName2, emp2Name);
				selectByIndex(findElementByXpath(roomRequest + newRow + departmentXpath1 + 2 + xpath),
						Integer.parseInt(department2));
				selectByIndex(findElementByXpath(roomRequest + newRow + accountXpath1 + 2 + xpath),
						Integer.parseInt(account2));
				i++;
			}
		}
	}

	public void selectRoomTypeAndEnterDetailsForNonEmployeesAdhoc(String singleRoomType, String singleRoomEmpName) {
		int newRow = 0;
		if (!singleRoomType.isEmpty()) {
			if (!findElementById(empID1).getAttribute("value").isEmpty())
				newRow = roomRowsAdhoc.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(singleRoomType.split(",")));
			List<String> empNames = new ArrayList<String>(Arrays.asList(singleRoomEmpName.split(",")));
			int i = 0;
			for (String empId : empIds) {
				if (newRow != 0) {
					clickOn(addMoreAdhoc);
					if (i != 0)
						newRow++;
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "SINGLE");
				WebElement emp = findElementById(
						roomRequestTableIDAdhoc + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp, empId);
				emp.sendKeys(Keys.TAB);
				waitTime(9000);
				typeInText(findElementById(roomRequestTableIDAdhoc + newRow + empNameAdhoc), empNames.get(i));
				i++;
			}

		}
	}

	public void selectSingleRoomTypeAndEnterDetails(String singleRoomType) {
		int newRow = 0;
		if (!singleRoomType.isEmpty()) {
			if (!findElementById(empID).getAttribute("value").isEmpty())
				newRow = roomRows.size();
			List<String> empIds = new ArrayList<String>(Arrays.asList(singleRoomType.split(",")));
			int i = 0;
			for (String empId : empIds) {
				if (i != 0) {
					// newRow++;
					newRow = roomRows.size();
				}
				if (newRow != 0) {
					clickOn(addMore);
					try {
						Thread.sleep(7000);
					} catch (Exception e) {
					}
				}
				selectByValue(findElementsByXpath(roomTypeId).get(newRow), "SINGLE");
				WebElement emp = findElementById(roomRequestTableID1 + newRow + empNumberId1 + 1 + empNumberId2 + 1);
				typeInText(emp, empId);
				emp.sendKeys(Keys.TAB);
				// wait.until(visibilityofelementlocated(By.id(roomRequestTableID1+newRow+empNameId1+1+empNameId2+1)));
				waitTime(7000);
				WebElement empName = findElementById(roomRequestTableID1 + newRow + empNameId1 + 1 + empNameId2 + 1);
				waitForElementTextVisibility(empName, "value");
				waitForPageLoad(getDriver());
				i++;
			}

		}
	}

	@FindBy(xpath = "//div[contains(@id,'bizTravelForm:roomRequestTable:0')]//input[contains(@id,'employeeNbr1')]")
	private WebElement singleEmpNbr;

	@FindBy(xpath = "//div[contains(@id,'bizTravelForm:roomRequestTable:0')]//select[contains(@id,'department1')]")
	private WebElement singleEmpDept;

	@FindBy(xpath = "//div[contains(@id,'bizTravelForm:roomRequestTable:1')]//input[contains(@id,'employeeNbr1')]")
	private WebElement doubleEmpNbr1;

	@FindBy(xpath = "//div[contains(@id,'bizTravelForm:roomRequestTable:1')]//select[contains(@id,'department1')]")
	private WebElement doubleEmpDept1;

	@FindBy(xpath = "//div[contains(@id,'bizTravelForm:roomRequestTable:1')]//input[contains(@id,'employeeNbr2')]")
	private WebElement doubleEmpNbr2;

	@FindBy(xpath = "//div[contains(@id,'bizTravelForm:roomRequestTable:1')]//select[contains(@id,'department2')]")
	private WebElement doubleEmpDept2;

	@FindBy(xpath = "//select[contains(@id,'bizTravelForm:roomRequestTable:1') and contains(@id,'subscriptions')]")
	private WebElement doubleRoomSelect;

	public void enterEmpDetailsForMesa(String singleRoomType, String empID1, String dep1, String doubleRoomType,
			String empID2a, String dep1a, String empID2b, String dep1b) throws InterruptedException, IOException {
		selectByVisibleText(room, singleRoomType);
		
		typeInText(singleEmpNbr, empID1);
		singleEmpNbr.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		selectByValue(singleEmpDept, dep1);
		waitForElementVisibility(addMore);
		
		clickOn(addMore);
		Thread.sleep(4000);
		selectByVisibleText(doubleRoomSelect, doubleRoomType);
		
		typeInText(doubleEmpNbr1, empID2a);
		doubleEmpNbr1.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		selectByValue(doubleEmpDept1, dep1a);
		
		typeInText(doubleEmpNbr2, empID2b);
		doubleEmpNbr2.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		selectByValue(doubleEmpDept2, dep1b);
		
		if (singleEmpDept.getText().equalsIgnoreCase("-select-"))
			selectByValue(singleEmpDept, dep1);
		takeScreenshots();
	}

	public void CompanyMesa(String CompanyValue) {
		selectByVisibleText(mesaAirlinesCompany, CompanyValue);
	}

	public void reasonCodeMesa(String reasonCodeValue) {
		selectByVisibleText(reasonCode, reasonCodeValue);
	}

	public void expenseCodeMesa(String expenseCodeValue) {
		selectByVisibleText(expenseCode, expenseCodeValue);
	}

	public void additionalInformationMesa(String companyValue, String expenseCodeValue, String reasonCodeValue) {
		CompanyMesa(companyValue);
		expenseCodeMesa(expenseCodeValue);
		reasonCodeMesa(reasonCodeValue);
	}

	public void enterDates(String arrivalDate, String departureDate) {
		typeInText(this.arrivalDate, arrivalDate);
		typeInText(this.departureDate, departureDate);
	}

	@FindBy(id = "warnSavePanelHeader")
	private WebElement warningPopup;

	@FindBy(xpath = "//table[@id='warnSavePanelContentTable']//form//a[text()='Save Anyway']")
	private WebElement warningPopupSaveAnyWayBtn;

	public String processingRequestMesa() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
//		waitForElementVisibility(warningPopup);
		Thread.sleep(2000);
		boolean checkPOP_Up = warningPopup.isDisplayed();
		if (checkPOP_Up == true) {
			clickOn(warningPopupSaveAnyWayBtn);
		Thread.sleep(3000);}
		waitForElementVisibility(savePanelHeader);
		String saveCompleteMessage = processCompletedMessage.getText();
		String reservationId = saveCompleteMessage.split(" ")[5];
		System.out.println("reservationId " + reservationId);
		clickOn(okBtn);
		return reservationId;
	}
	
	public void enterEmpDetailsFor9E(String singleRoomType, String empID1, String doubleRoomType,
			String empID2a, String empID2b) throws InterruptedException, IOException {
		selectByVisibleText(room, singleRoomType);
		
		typeInText(singleEmpNbr, empID1);
		singleEmpNbr.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		waitForElementVisibility(addMore);
		
		clickOn(addMore);
		Thread.sleep(4000);
		selectByVisibleText(doubleRoomSelect, doubleRoomType);
		
		typeInText(doubleEmpNbr1, empID2a);
		doubleEmpNbr1.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		typeInText(doubleEmpNbr2, empID2b);
		doubleEmpNbr2.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		takeScreenshots();
	}

	public void selectReason9E(String reasonCodeValue) {
		waitForElementVisibility(reasonEndeavor);
		selectByVisibleText(reasonEndeavor, reasonCodeValue);
	}
	
	public void selectReasonEnvoy(String reasonCodeValue) {
waitForElementVisibility(reasonAdhoc);
selectByVisibleText(reasonAdhoc, reasonCodeValue);
	}
	
	public void enterEmpDetailsForVA(String singleRoomType, String empID1, String doubleRoomType,
			String empID2a) throws InterruptedException, IOException {
		selectByVisibleText(room, singleRoomType);
		
		typeInText(singleEmpNbr, empID1);
		singleEmpNbr.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		waitForElementVisibility(addMore);
		
		clickOn(addMore);
		Thread.sleep(4000);
		selectByVisibleText(doubleRoomSelect, doubleRoomType);
		
		typeInText(doubleEmpNbr1, empID2a);
		doubleEmpNbr1.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		takeScreenshots();
	}
}
