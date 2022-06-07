package com.APIHotels.pages.airlines;

import java.io.IOException;

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
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class RequestRoomBlockPage extends BasePage {

	public EventFiringWebDriver driver=null;
	
	// OPERATIONS -- REQUEST ROOM BLOCK

	// Company field exists for latam only
	@FindBy(how = How.CSS, using = "select[id*='roomBlkTenantList']")
	public WebElement company;

	@FindBy(how = How.CSS, using = "input[id*='roomBlockLocation']")
	public WebElement location;

	// time format field exists for latam only
	@FindBy(how = How.CSS, using = "div[id='bizTravelForm:roomBlockTimeZone']>div>select")
	public WebElement timeFormat;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Check-in']/parent::*/following-sibling::td//input[contains(@id,'InputDate')]")
	public WebElement checkInDate;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Check-in']/parent::*/following-sibling::td//img")
	public WebElement checkInDateCalendar;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Check-out']/parent::*/following-sibling::td//input[contains(@id,'InputDate')]")
	public WebElement checkOutDate;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Check-out']/parent::*/following-sibling::td//img")
	public WebElement checkOutDateCalendar;

	@FindBy(how = How.CSS, using = "input[id$='checkinTm']")
	public WebElement checkInTime;

	@FindBy(how = How.CSS, using = "input[id$='checkoutTm']")
	public WebElement checkOutTime;

	@FindBy(how = How.CSS, using = "input[id$='roomCount']")
	public WebElement roomCount;

	@FindBy(how = How.CSS, using = "select[id$='reasonCodes']")
	public WebElement reasonCode;

	@FindBy(how = How.XPATH, using = "//label[text()=' Hard']/preceding-sibling::input[@type='radio']")
	public WebElement hardAdhocType;

	@FindBy(how = How.XPATH, using = "//label[text()=' Soft']/preceding-sibling::input[@type='radio']")
	public WebElement softAdhocType;

	@FindBy(how = How.CSS, using = "textarea[id*='adhocNotes']")
	public WebElement notes;

	@FindBy(how = How.CSS, using = "input[value='Save']")
	public WebElement saveButton;
	
	@FindBy(how=How.ID,using ="bizTravelForm:saveRoomBlockPanelHeader")
	public WebElement savePanelHeader;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='bizTravelForm:saveRoomBlockGrid']//span/a")
	public WebElement okBtn;
	
	@FindBy(id="addAdhocForm:saveRoomBlockPanelHeader")
	public WebElement saveRoomBlockPanelHeader;
	
	@FindBy(xpath="//table[@id='addAdhocForm:saveRoomBlockGrid']/tfoot//a[text()='OK']")
	public WebElement btn_OKSaveRoomBlockPanelHeader;

	public RequestRoomBlockPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void requestRoomBlock(String locationValue, String checkinTimeValue, String checkOutTimeValue,
			String roomCountValue, String reasonCodeValue, String notesValue) {
		typeInText(location, locationValue);
		// Assert.assertEquals(location.getAttribute("value"), locationValue,
		// "location mismatch");

		waitForElementVisibility(checkInDateCalendar);

		typeInText(checkInDate, currentDate());
		Assert.assertEquals(checkInDate.getAttribute("value"), currentDate(), "checkin date mismatch");

		typeInText(checkInTime, checkinTimeValue);
		Assert.assertEquals(checkInTime.getAttribute("value"), checkinTimeValue, "checkInTime mismatch");

		waitForElementVisibility(checkOutDateCalendar);

		typeInText(checkOutDate, currentDatePlus(1));
		// Assert.assertEquals(checkInDate.getAttribute("value"),
		// currentDate(),"checkin date mismatch");

		typeInText(checkOutTime, checkOutTimeValue);
		// Assert.assertEquals(checkOutTime.getAttribute("value"),
		// checkOutTimeValue, "checkOutTime mismatch");

		typeInText(roomCount, roomCountValue);
		// Assert.assertEquals(roomCount.getAttribute("value"), roomCountValue,
		// "roomCount mismatch");

		selectByValue(reasonCode, reasonCodeValue);
		// Assert.assertEquals(reasonCode.getAttribute("value"),
		// reasonCodeValue, "reasonCode mismatch");

		clickOn(hardAdhocType);
		Assert.assertTrue(hardAdhocType.isSelected(), "hard adhoc type is not selected");

		clickOn(softAdhocType);
		Assert.assertTrue(softAdhocType.isSelected(), "Soft adhoc type is not selected");

		typeInText(notes, notesValue);
		Assert.assertEquals(notes.getAttribute("value"), notesValue, "notes mismatch");

	}

	public void timeFormat(String timeFormatValue) {
		selectByValue(timeFormat, timeFormatValue);
		// Assert.assertEquals(timeFormat.getAttribute("selected"),timeFormatValue,
		// "timeformat mismatch");
	}

	public void company(String companyValue) {
		selectByValue(company, companyValue);
		// Assert.assertEquals(company.getAttribute("selected"),
		// companyValue,"company mismatch");
	}

	
	public void verifySaveBtn() {
		waitForElementVisibility(saveButton);
	}
	
	public void verifyTabFunctionality(){
		location.clear();
		location.equals(driver.switchTo().activeElement());
		location.sendKeys(Keys.TAB);
		timeFormat.equals(driver.switchTo().activeElement());
		timeFormat.sendKeys(Keys.TAB);
		checkInDate.equals(driver.switchTo().activeElement());
		checkInDate.sendKeys(Keys.TAB);
		checkInDateCalendar.equals(driver.switchTo().activeElement());
		checkInDateCalendar.sendKeys(Keys.TAB);
		checkInTime.equals(driver.switchTo().activeElement());
		checkInTime.sendKeys(Keys.TAB);
		checkOutDate.equals(driver.switchTo().activeElement());
		checkOutDate.sendKeys(Keys.TAB);
		checkOutDateCalendar.equals(driver.switchTo().activeElement());
		checkOutDateCalendar.sendKeys(Keys.TAB);
		checkOutTime.equals(driver.switchTo().activeElement());
		checkOutTime.sendKeys(Keys.TAB);
		roomCount.equals(driver.switchTo().activeElement());
		roomCount.sendKeys(Keys.TAB);
		reasonCode.equals(driver.switchTo().activeElement());
		reasonCode.sendKeys(Keys.TAB);
		reasonCode.sendKeys(Keys.TAB);
		reasonCode.sendKeys(Keys.TAB);
		notes.equals(driver.switchTo().activeElement());
		notes.sendKeys(Keys.TAB);
		saveButton.equals(driver.switchTo().activeElement());
		
	}
	
	public void createRoomBlock(String locationValue, String timeFormatValue, String checkInDateValue,
			String checkInTimeValue, String checkOutDateValue, String checkOutTimeValue, String roomCountValue,
			String reasonCountValue, String adhocType, String notesValue) {
		selectByVisibleText(timeFormat, timeFormatValue);
		createRoomblock(locationValue, checkInDateValue, checkInTimeValue, checkOutDateValue, checkOutTimeValue,
				roomCountValue, reasonCountValue, adhocType, notesValue);
	}
	
	public void clickSaveBtn() {
		clickOn(saveButton);
	}
	
	public void processingRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickOn(okBtn);
	}
	
	public void createRoomblock(String locationValue, String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String roomCountValue, String reasonCountValue,
			String adhocType, String notesValue) {
		typeInText(location, locationValue);
		typeInText(checkInDate, checkInDateValue);
		typeInText(checkInTime, checkInTimeValue);
		typeInText(checkOutDate, checkOutDateValue);
		typeInText(checkOutTime, checkOutTimeValue);
		typeInText(roomCount, roomCountValue);
		selectByValue(reasonCode, reasonCountValue);
		if (adhocType.equals("Hard")) {
			clickOn(hardAdhocType);
		} else
			clickOn(softAdhocType);
		typeInText(notes, notesValue);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processingRoomBlockRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(saveRoomBlockPanelHeader);
		clickOn(btn_OKSaveRoomBlockPanelHeader,"Save Room Block Popup -> OK Button");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Room Blocks Request Saved Successfully");
	}

}
