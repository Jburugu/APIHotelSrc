package com.APIHotels.pages.airlines;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class AcesDirectRequestLimoPage extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.ID, using = "flightResvsForm:referenceNbrArea:referenceNbr")
	public WebElement pairing;

	@FindBy(how = How.ID, using = "flightResvsForm:pickupLocationArea:pickupLocationCd")
	public WebElement pickUpLocation;

	@FindBy(how = How.ID, using = "flightResvsForm:pickupSupplierArea:pickupSupplier")
	public WebElement pickUpDetail;

	@FindBy(how = How.CSS, using = "input[id$='arrivalAlCodeGt']")
	public WebElement pickUpArrivalCode;

	@FindBy(how = How.CSS, using = "input[id$='pickupFlightNbr']")
	public WebElement pickUpFlightNumber;

	@FindBy(how = How.ID, using = "flightResvsForm:pickDtArea:pickupDateInputDate")
	public WebElement pickUpDate;

	@FindBy(how = How.ID, using = "flightResvsForm:pickDtArea:pickupDatePopupButton")
	public WebElement pickUpDateCalendar;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'pickupTimeGt')]")
	public WebElement pickUpTime;

	@FindBy(how = How.ID, using = "flightResvsForm:dropoffLocationArea:dropoffLocationCd")
	public WebElement dropOffLocation;

	@FindBy(how = How.ID, using = "flightResvsForm:dropoffSupplierArea:dropoffSupplier")
	public WebElement dropOffDetail;

	@FindBy(how = How.CSS, using = "input[id$='deptAlCodeGt']")
	public WebElement dropOffDeptCode;

	@FindBy(how = How.CSS, using = "input[id$='dropoffFlightNbr']")
	public WebElement dropOffFlightNumber;

	@FindBy(how = How.ID, using = "flightResvsForm:dropDtArea:dropoffDateInputDate")
	public WebElement dropOffDate;

	@FindBy(how = How.ID, using = "flightResvsForm:dropDtArea:dropoffDatePopupButton")
	public WebElement dropOffDateCalendar;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'dropoffTimeGt')]")
	public WebElement dropOffTime;

	@FindBy(how = How.ID, using = "flightResvsForm:gtindcrewRequestTable:0:gtCrewTypenArea:crewTypes")
	public WebElement individualBookingDept;

	@FindBy(how = How.ID, using = "flightResvsForm:gtindcrewRequestTable:0:gtCrewPositionArea:crews")
	public WebElement individualBookingPosition;

	@FindBy(how = How.ID, using = "flightResvsForm:gtindcrewRequestTable:0:empIdArea:empId")
	public WebElement individualBookingEmpId;

	@FindBy(how = How.ID, using = "flightResvsForm:gtindcrewRequestTable:0:empNameArea:empName")
	public WebElement individualBookingEmpName;

	@FindBy(how = How.XPATH, using = "//tbody[@id='flightResvsForm:gtindcrewRequestTable:tb']//tr[contains(@class,'firstrow')]/td/a[contains(text(),'Delete')]")
	public WebElement individualBookingDelete;

	@FindBy(how = How.ID, using = "flightResvsForm:gtgroupBooking_lbl")
	public WebElement groupBooking;

	@FindBy(how = How.ID, using = "flightResvsForm:gtgrpcrewRequestTable:0:gtCrewTypenArea:crewTypes")
	public WebElement groupBookingDept;

	@FindBy(how = How.ID, using = "flightResvsForm:gtgrpcrewRequestTable:0:gtCrewPositionArea:crews")
	public WebElement groupBookingPosition;

	@FindBy(how = How.ID, using = "flightResvsForm:gtgrpcrewRequestTable:0:roomArea:rooms")
	public WebElement groupBookingNoOfPassengers;

	@FindBy(how = How.XPATH, using = "//tbody[@id='flightResvsForm:gtgrpcrewRequestTable:tb']//tr[contains(@class,'firstrow')]/td/a[contains(text(),'Delete')]")
	public WebElement groupBookingDelete;

	@FindBy(how = How.ID, using = "flightResvsForm:gtindcrewRequestTable:addMore")
	public WebElement individualBookingAddMoreBtn;

	@FindBy(how = How.ID, using = "flightResvsForm:gtindcrewRequestTable:reset")
	public WebElement individualBookingResetBtn;

	@FindBy(how = How.XPATH, using = ".//*[@class='labelHeading']/following-sibling::span//textarea[contains(@id,'note')]")
	public WebElement notes;

	@FindBy(how = How.XPATH, using = "//input[@value='Save']")
	public WebElement saveBtn;

	@FindBy(how = How.XPATH, using = "//span/input[@value='Reset']")
	public WebElement resetBtn;

	@FindBy(how = How.ID, using = "flightResvsForm:gtgrpcrewRequestTable:addMore")
	public WebElement groupBookingAddMore;

	@FindBy(how = How.ID, using = "flightResvsForm:gtgrpcrewRequestTable:reset")
	public WebElement groupBookingResetBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:pickupSupplierArea:pickupSupplier']/option[text()='Terminal']")
	public WebElement pickupDetailTerminalDropdownValue;

	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:dropoffSupplierArea:dropoffSupplier']/option[text()='Terminal']")
	public WebElement dropOffDetailTerminalDropdownValue;

	String IndividualBookingdeptId="flightResvsForm:gtindcrewRequestTable:0:gtCrewTypenArea:crewTypes";
    String GroupBookingdeptId="flightResvsForm:gtgrpcrewRequestTable:0:gtCrewTypenArea:crewTypes";
    
	String individualBookingTable = ".//*[@id='flightResvsForm:gtindcrewRequestTable:";
	String departmentXpath = ":gtCrewTypenArea:crewTypes']";
	String positionXpath = ":gtCrewPositionArea:crews']";
	String individualempId =":empIdArea:empId']";
	String individualempName = ":empNameArea:empName']";
	
	@FindBy(how = How.XPATH,using = ".//*[@id='flightResvsForm:gtindcrewRequestTable']/tbody")
	public List<WebElement> individualRows; 
	
	@FindBy(how = How.XPATH, using = ".//*[@id='saveBizPanelMessages']/dt/span")
	public WebElement processCompletedMessage;

	@FindBy(how=How.ID,using ="saveFlightResvPanelHeader")
	public WebElement savePanelHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@id='saveFlightResvGrid']/tfoot/tr/td/span/a")
	public WebElement okBtn;
	
	@FindBy(how = How.XPATH,using = ".//*[@id='flightResvsForm:gtgrpcrewRequestTable']/tbody/tr")
	public List<WebElement> groupBookingRows;
	
	String groupBookingTable = ".//*[@id='flightResvsForm:gtgrpcrewRequestTable:";
	String groupPassengers = ":roomArea:rooms']";
	
	public AcesDirectRequestLimoPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void requestLimoACESDirect(String pairingNumber, String pickUpLocationValue, String pickUpDetailValue,
			String arrivalCode, String pickUpFlightValue, String pickUpTimeValue, String dropOffLocationValue,
			String dropOffDetailValue, String departureCode, String dropOffFlightValue, String dropOffTimeValue,
			String indexValue, String empIdValue, String empNameValue, String notesValue) {
		typeInText(pairing, pairingNumber);

		typeInText(pickUpLocation, pickUpLocationValue);
		pickUpLocation.sendKeys(Keys.TAB);

		waitForElementVisibility(pickupDetailTerminalDropdownValue);
		selectByVisibleText(pickUpDetail, pickUpDetailValue);
		pickUpDetail.sendKeys(Keys.TAB);

		typeInText(dropOffLocation, dropOffLocationValue);
		dropOffLocation.sendKeys(Keys.TAB);

		waitForElementVisibility(dropOffDetailTerminalDropdownValue);
		selectByVisibleText(dropOffDetail, dropOffDetailValue);

		typeInText(pickUpDate, currentDate());

		waitForElementVisibility(pickUpDateCalendar);

		typeInText(pickUpTime, pickUpTimeValue);

		waitForElementVisibility(pickUpArrivalCode);
		typeInText(pickUpArrivalCode, arrivalCode);
		waitForElementVisibility(pickUpFlightNumber);
		typeInText(pickUpFlightNumber, pickUpFlightValue);

		typeInText(dropOffDeptCode, departureCode);
		typeInText(dropOffFlightNumber, dropOffFlightValue);

		typeInText(dropOffDate, currentDate());

		//waitForElementVisibility(dropOffDateCalendar);
		typeInText(dropOffTime, dropOffTimeValue);

		Integer indexValue1Parse = Integer.parseInt(indexValue);
		selectByIndex(individualBookingDept, indexValue1Parse);

		waitForElementVisibility(individualBookingPosition);

		typeInText(individualBookingEmpId, empIdValue);

		typeInText(individualBookingEmpName, empNameValue);

		waitForElementVisibility(individualBookingDelete);

		waitForElementVisibility(individualBookingAddMoreBtn);
		waitForElementVisibility(individualBookingResetBtn);

		clickOn(groupBooking);

		waitForElementVisibility(groupBookingDept);

		waitForElementVisibility(groupBookingPosition);

		waitForElementVisibility(groupBookingDelete);

		waitForElementVisibility(groupBookingResetBtn);
		typeInText(notes, notesValue);

		waitForPageLoad(driver);

		// waitForElementVisibility(saveBtn);
		waitForElementVisibility(resetBtn);

	}

	public void requestLimo(String pairingNumber, String pickUpLocationValue, String pickUpDetailValue,
			String pickUpDateValue, String pickUpFlightValue, String pickUpTimeValue, String dropOffLocationValue,
			String dropOffDetailValue, String dropOffFlightValue, String dropOffDateValue, String dropOffTimeValue) {
		typeInText(pairing, pairingNumber);
		typeInText(pickUpLocation, pickUpLocationValue);
		pickUpLocation.sendKeys(Keys.TAB);
		waitTime(3000);
		waitForElementVisibility(pickupDetailTerminalDropdownValue);
		selectByVisibleText(pickUpDetail, pickUpDetailValue);
		waitTime(4000);
		typeInText(pickUpFlightNumber, pickUpFlightValue);
		typeInText(pickUpDate, pickUpDateValue);
		typeInText(pickUpTime, pickUpTimeValue);

		typeInText(dropOffLocation, dropOffLocationValue);
		dropOffLocation.sendKeys(Keys.TAB);
		waitTime(3000);
		waitForElementVisibility(dropOffDetailTerminalDropdownValue);
		selectByVisibleText(dropOffDetail, dropOffDetailValue);
		waitTime(3000);
		typeInText(dropOffFlightNumber, dropOffFlightValue);
		typeInText(dropOffDate, dropOffDateValue);
		typeInText(dropOffTime, dropOffTimeValue);

	}
	
	public void selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(String individualEmpId, String deptValue,
			String empName, String passengers) {
		int newRow = 0;
		if (!individualEmpId.isEmpty()) {
			if (!findElementById(IndividualBookingdeptId).getAttribute("value").isEmpty())
				newRow = individualRows.size();
			List<String> empIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
			List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
			List<String> departments = new ArrayList<>(Arrays.asList(deptValue.split(",")));
			int i = 0;
			for (String emp : empIds) {
				if (i != 0) {
					newRow = individualRows.size();
				}
				if (newRow != 0) {
					clickOn(individualBookingAddMoreBtn);
					waitTime(7000);
				}
				WebElement dept = findElementByXpath(individualBookingTable + newRow + departmentXpath);
				selectByVisibleText(dept, departments.get(newRow));
				waitTime(7000);
				WebElement position = findElementByXpath(individualBookingTable + newRow + positionXpath);
				waitForElementTextVisibility(position, "value");
				typeInText(findElementByXpath(individualBookingTable + newRow + individualempId), emp);
				waitTime(3000);
				typeInText(findElementByXpath(individualBookingTable + newRow + individualempName), empNames.get(newRow));
				waitTime(7000);
				waitForPageLoad(getDriver());
				i++;
			}
		}
		if (!passengers.isEmpty()) {
			clickOn(groupBooking);
			waitTime(3000);
			if (!findElementById(GroupBookingdeptId)
					.getAttribute("value").isEmpty()) {
				List<String> passengersList = new ArrayList<>(Arrays.asList(passengers.split(",")));
				List<String> departments = new ArrayList<>(Arrays.asList(deptValue.split(",")));
				for (int i = 0; i < groupBookingRows.size(); i++) {
					boolean dept = false;
					boolean position = false;
					WebElement groupDept = findElementByXpath(groupBookingTable + i + departmentXpath);
					WebElement groupBookingPosition = findElementByXpath(groupBookingTable + i + positionXpath);
					if (!dept) {
						Select department = new Select(groupDept);
						dept = department.getFirstSelectedOption().getText().equals(departments.get(i));
					}
					if (!position)
						position = groupBookingPosition.getAttribute("value").equals(departments.get(i));
					if (dept && position == true) {
						typeInText(findElementByXpath(groupBookingTable + i + groupPassengers),
								passengersList.get(i));
						waitTime(3000);
					}
				}

			}  else if (findElementById(GroupBookingdeptId)
					.getAttribute("value").isEmpty()) {
				 if (!findElementById(GroupBookingdeptId)
						.getAttribute("value").isEmpty()){
					newRow = groupBookingRows.size();
				}
				List<String> passengersList = new ArrayList<>(Arrays.asList(passengers.split(",")));
				List<String> departments = new ArrayList<>(Arrays.asList(deptValue.split(",")));
				int i = 0;
				for (String passenger : passengersList) {
					if (i != 0) {
						newRow = groupBookingRows.size();
					}
					if (newRow != 0) {
						clickOn(groupBookingAddMore);
						waitTime(7000);
					}
					WebElement groupDept = findElementByXpath(groupBookingTable + newRow + departmentXpath);
					selectByVisibleText(groupDept, departments.get(newRow));
					waitTime(7000);
					WebElement groupBookingPosition = findElementByXpath(groupBookingTable + newRow + positionXpath);
					waitForElementTextVisibility(groupBookingPosition, "value");
					typeInText(findElementByXpath(groupBookingTable + newRow + groupPassengers), passenger);
					waitTime(7000);
					waitForPageLoad(getDriver());
					i++;
				}
			}
		}

	}
	public void clickOnSaveButton(){
		clickOn(saveBtn);
	}
	
	public void processingRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		clickOn(okBtn);
	}

	public void selectDeptAndPassengersUnderGroupBooking(String passengers, String deptValue) {
		int newRow = 0;
		if (!passengers.isEmpty()) {
			clickOn(groupBooking);
			waitTime(3000);
			 if (!findElementById(GroupBookingdeptId)
					.getAttribute("value").isEmpty())
				newRow = groupBookingRows.size();
			List<String> passengersList = new ArrayList<>(Arrays.asList(passengers.split(",")));
			int i = 0;
			for (String passenger : passengersList) {
				if (i != 0) {
					newRow = groupBookingRows.size();
				}
				if (newRow != 0) {
					clickOn(groupBookingAddMore);
					waitTime(7000);
				}
				WebElement groupDept = findElementByXpath(groupBookingTable + newRow + departmentXpath);
				selectByVisibleText(groupDept, deptValue);
				waitTime(7000);
				WebElement groupBookingPosition = findElementByXpath(groupBookingTable + newRow + positionXpath);
				waitForElementTextVisibility(groupBookingPosition, "value");
				typeInText(findElementByXpath(groupBookingTable + newRow + groupPassengers), passenger);
				waitTime(3000);
				waitForPageLoad(getDriver());
				i++;
			}
		}
	}
}
