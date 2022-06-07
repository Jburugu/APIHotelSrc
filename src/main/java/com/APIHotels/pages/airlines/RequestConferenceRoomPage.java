package com.APIHotels.pages.airlines;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class RequestConferenceRoomPage extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.CSS, using = "select[name*='confTenantList']")
	public WebElement companyExpressJetERJ;

	// Endeavor
	@FindBy(how = How.ID, using = "bizTravelForm:destCRArea:locCdCR")
	public WebElement destination;

	@FindBy(how = How.CSS, using = "input[id$='startDtInputDate']")
	public WebElement startDate;

	@FindBy(how = How.CSS, using = "input[id$='startDtInputDate'] + img")
	public WebElement startDateCalender;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='End Date*']/parent::td/following-sibling::td/div/span/input")
	public WebElement endDate;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='End Date*']/parent::td/following-sibling::td/div/span/img")
	public WebElement endDateCalender;

	@FindBy(how = How.CSS, using = "input[id$='startTimeCR']")
	public WebElement startDateTime;

	@FindBy(how = How.CSS, using = "input[id$='endTimeCR']")
	public WebElement endDateTime;

	@FindBy(how = How.CSS, using = "input[id$='confRooms']")
	public WebElement confRoomsNeeded;

	@FindBy(how = How.CSS, using = "select[id$='seatingStyle']")
	public WebElement seatingStyle;

	@FindBy(how = How.CSS, using = "input[id$='attendees']")
	public WebElement NbrOfAttendees;

	@FindBy(how = How.ID, using = "bizTravelForm:hotelsCRArea:hotelsCR")
	public WebElement hotel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Breakout Room']")
	public WebElement breakoutRoomLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Welcome Table']")
	public WebElement welcomeTableLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Projector']")
	public WebElement projectorLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Screen']")
	public WebElement screenLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='White Board']")
	public WebElement whiteBoardLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Internet']")
	public WebElement internetLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Breakfast']")
	public WebElement breakFastLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Lunch']")
	public WebElement lunchLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Dinner']")
	public WebElement dinnerLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Snacks']")
	public WebElement snacksLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Coffee Service']")
	public WebElement coffeeServiceLabel;

	@FindBy(how = How.XPATH, using = "//label[normalize-space='Water Service']")
	public WebElement waterServiceLabel;

	@FindBy(how = How.CSS, using = "select[id$='department']")
	public WebElement department;

	@FindBy(how = How.CSS, using = "select[id$='account']")
	public WebElement reasonOrAccount;

	@FindBy(how = How.CSS, using = "textarea[id$='hotelNotesCR']")
	public WebElement hotelNotes;

	@FindBy(how = How.CSS, using = "input[value='Save']")
	public WebElement saveBtn;

	@FindBy(how = How.CSS, using = "input[title$='emailNotification']")
	public WebElement doNotSendMeEmailNotification;

	@FindBy(how = How.XPATH, using = "//select[@id='bizTravelForm:hotelsCRArea:hotelsCR']/option[text()='Other']")
	public WebElement hotelsDropdownValueOther;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'breakoutRoom')]//input[@value='Yes']")
	public WebElement breakoutRoomYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'breakoutRoom')]//input[@value='No']")
	public WebElement breakoutRoomNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'projector')]//input[@value='Yes']")
	public WebElement projectorYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'projector')]//input[@value='No']")
	public WebElement projectorNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'whiteBoard')]//input[@value='Yes']")
	public WebElement whiteBoardYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'whiteBoard')]//input[@value='No']")
	public WebElement whiteBoardNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'breakfast')]//input[@value='Yes']")
	public WebElement breakfastYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'breakfast')]//input[@value='No']")
	public WebElement breakfastNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'dinner')]//input[@value='Yes']")
	public WebElement dinnerYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'dinner')]//input[@value='No']")
	public WebElement dinnerNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'coffeeService')]//input[@value='Yes']")
	public WebElement coffeeServiceYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'coffeeService')]//input[@value='No']")
	public WebElement coffeeServiceNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'welcomeTable')]//input[@value='Yes']")
	public WebElement welcomeTableYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'welcomeTable')]//input[@value='No']")
	public WebElement welcomeTableNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'screen')]//input[@value='Yes']")
	public WebElement screenYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'screen')]//input[@value='No']")
	public WebElement screenNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'internet')]//input[@value='Yes']")
	public WebElement internetYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'internet')]//input[@value='No']")
	public WebElement internetNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'lunch')]//input[@value='Yes']")
	public WebElement lunchYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'lunch')]//input[@value='No']")
	public WebElement lunchNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'snacks')]//input[@value='Yes']")
	public WebElement snacksYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'snacks')]//input[@value='No']")
	public WebElement snacksNoRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'waterService')]//input[@value='Yes']")
	public WebElement waterServiceYesRadioBtn;

	@FindBy(how = How.XPATH, using = "//table[contains(@id,'waterService')]//input[@value='No']")
	public WebElement waterServiceNoRadioBtn;

	@FindBy(how = How.CSS, using = "select[id$='businessLine']")
	public WebElement lineOfBusiness;

	@FindBy(how = How.CSS, using = "input[id$='accountPageConfActivityLocCd']")
	public WebElement location;

	@FindBy(how = How.CSS, using = "input[id$='supplierDesc']")
	public WebElement hotelDescription;

	@FindBy(how = How.XPATH, using = ".//*[@id='saveBizPanelMessages']/dt/span")
	public WebElement processCompletedMessage;

	@FindBy(how = How.ID, using = "saveBizPanelHeader")
	public WebElement savePanelHeader;

	@FindBy(xpath = "//*[@id='saveBizGrid']/tfoot/tr/td/span/a")
	public WebElement okBtn;

	public RequestConferenceRoomPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void requestConferenceRoom(String destinationValue, String startDateTimeValue, String endDateTimeValue,
			String confRoomsNeededValue, String seatingStyleValue, String NbrOfAttendeesValue, String reasonValue,
			String notesValue) {	

		enterDetailsForConferenceRoom(destinationValue, startDateTimeValue, endDateTimeValue, confRoomsNeededValue,
				seatingStyleValue, NbrOfAttendeesValue);

		waitForElementVisibility(startDateCalender);
		waitForElementVisibility(endDateCalender);

		verifyConferenceRoomDefaultDetails();
		hotelByVisibleText();

		clickOn(breakoutRoomYesRadioBtn);
		Assert.assertTrue(breakoutRoomYesRadioBtn.isSelected(), "Breakout room yes radio button is selected");

		clickOn(welcomeTableYesRadioBtn);
		Assert.assertTrue(welcomeTableYesRadioBtn.isSelected(), "welcome Table yes radio button is selected");

		clickOn(projectorYesRadioBtn);
		Assert.assertTrue(projectorYesRadioBtn.isSelected(), "Projector yes radio button is selected");

		clickOn(screenYesRadioBtn);
		Assert.assertTrue(screenYesRadioBtn.isSelected(), "Screen yes radio button is selected");

		clickOn(whiteBoardYesRadioBtn);
		Assert.assertTrue(whiteBoardYesRadioBtn.isSelected(), "white board yes radio button is selected");

		clickOn(internetYesRadioBtn);
		Assert.assertTrue(internetYesRadioBtn.isSelected(), "internet yes radio button is selected");

		clickOn(breakfastYesRadioBtn);
		Assert.assertTrue(breakfastYesRadioBtn.isSelected(), "Breakfast yes radio button is selected");

		clickOn(lunchYesRadioBtn);
		Assert.assertTrue(lunchYesRadioBtn.isSelected(), "lunch yes radio button is selected");

		clickOn(dinnerYesRadioBtn);
		Assert.assertTrue(dinnerYesRadioBtn.isSelected(), "dinner yes radio button is selected");

		clickOn(snacksYesRadioBtn);
		Assert.assertTrue(snacksYesRadioBtn.isSelected(), "snacks yes radio button is selected");

		clickOn(coffeeServiceYesRadioBtn);
		Assert.assertTrue(coffeeServiceYesRadioBtn.isSelected(), "coffee Service yes radio button is selected");

		clickOn(waterServiceYesRadioBtn);
		Assert.assertTrue(waterServiceYesRadioBtn.isSelected(), "water service yes radio button is selected");

		int reasonVal = Integer.parseInt(reasonValue);
		selectByIndex(reasonOrAccount, reasonVal);
		 enterNotes(notesValue);
	}

	public void department(String departmentValue) {
		int departmentVal = Integer.parseInt(departmentValue);
		selectByIndex(department, departmentVal);

	}

	public void location(String locationValue) {
		typeInText(location, locationValue);
	}

	public void lineOfBusiness(String lineOfBusinessValue) {
		int lineOfBusinessVal = Integer.parseInt(lineOfBusinessValue);
		selectByIndex(lineOfBusiness, lineOfBusinessVal);
	}

	public void hotel(String hotelValue) {
        waitForElementVisibility(hotelsDropdownValueOther);
        selectByVisibleText(hotel, hotelValue);
        hotel.sendKeys(Keys.TAB);
    }
	
	public void hotelDescription(String hotelDescriptionValue) {
		 waitForElementVisibility(hotelDescription);
		typeInText(hotelDescription, hotelDescriptionValue);
	}

	public void verifySaveBtn() {
		waitForElementVisibility(saveBtn);
	}

	public void clickEmailNotification() {
		clickOn(doNotSendMeEmailNotification);
	}

	public void enterDetailsForConferenceRoom(String destinationValue, String startDateTimeValue,
			String endDateTimeValue, String confRoomsNeededValue, String seatingStyleValue,
			String NbrOfAttendeesValue) {

		typeInText(destination, destinationValue);
		typeInText(startDate, currentDate());
		typeInText(startDateTime, startDateTimeValue);
		typeInText(endDate, currentDate());
		typeInText(endDateTime, endDateTimeValue);
		typeInText(confRoomsNeeded, confRoomsNeededValue);
		selectByIndex(seatingStyle, Integer.parseInt(seatingStyleValue));
		typeInText(NbrOfAttendees, NbrOfAttendeesValue);

	}

	public void verifyConferenceRoomDefaultDetails() {
		Assert.assertTrue(breakoutRoomNoRadioBtn.isSelected(), "Breakout room yes radio button is selected");
		Assert.assertTrue(welcomeTableNoRadioBtn.isSelected(), "welcome Table no radio button is selected");
		Assert.assertTrue(projectorNoRadioBtn.isSelected(), "Projector no radio button is selected");
		Assert.assertTrue(screenNoRadioBtn.isSelected(), "Screen no radio button is selected");
		Assert.assertTrue(whiteBoardNoRadioBtn.isSelected(), "white board no radio button is selected");
		Assert.assertTrue(internetNoRadioBtn.isSelected(), "internet no radio button is selected");
		Assert.assertTrue(breakfastNoRadioBtn.isSelected(), "Breakfast no radio button is selected");
		Assert.assertTrue(lunchNoRadioBtn.isSelected(), "lunch no radio button is selected");
		Assert.assertTrue(dinnerNoRadioBtn.isSelected(), "dinner no radio button is selected");
		Assert.assertTrue(snacksNoRadioBtn.isSelected(), "snacks no radio button is selected");
		Assert.assertTrue(coffeeServiceNoRadioBtn.isSelected(), "coffee Service no radio button is selected");
		Assert.assertTrue(waterServiceNoRadioBtn.isSelected(), "water service yes radio button is selected");
	}

	public String enterNotes(String notesValue) {
		typeInText(hotelNotes, notesValue);
		return notesValue;
	}

	public void hotelByVisibleText() {
		waitForElementVisibility(hotelsDropdownValueOther);
		selectByVisibleText(hotel, "Other");
	}

	public void hotelByIndex(String hotelValue) {
		waitForElementVisibility(hotelsDropdownValueOther);
		selectByIndex(hotel, Integer.parseInt(hotelValue));
	}

	public void clickSaveBtn() {
		clickOn(saveBtn);
	}

	public String processingRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		String saveCompleteMessage = processCompletedMessage.getText();
		String conferenceReservationId = saveCompleteMessage.substring(Math.max(0, saveCompleteMessage.length() - 6));
		System.out.println("reservationId " + conferenceReservationId);
		clickOn(okBtn);
		return conferenceReservationId;
	}
}
