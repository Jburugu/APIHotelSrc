package com.APIHotels.pages.airlines;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

/**
 * @author abhilashk
 *
 */

public class AcesDirectRequestReservationPage extends BasePage {

	public EventFiringWebDriver driver=null;

	// Aircanada
	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:pairingTypeArea:pairingType']")
	public WebElement department;

	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:roomReferenceNbrArea:referenceNbr']")
	public WebElement pairing;

	@FindBy(how = How.ID, using = "flightResvsForm:locCdArea:locCd")
	public WebElement hotelLocation;

	@FindBy(how = How.ID, using = "flightResvsForm:arrivalLocCdArea:arrivalLocCd")
	public WebElement arrivalLocation;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'arrivalAlCode')]")
	public WebElement arrivalFlightCode;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'arrivalFlightNbr')]")
	public WebElement arrivalFlightNumber;

	@FindBy(how = How.ID, using = "flightResvsForm:arrvDtArea:arrivalDateInputDate")
	public WebElement arrivalDate;

	@FindBy(how = How.ID, using = "flightResvsForm:arrvDtArea:arrivalDatePopupButton")
	public WebElement arrivalDateCalendar;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'arrivalTime')]")
	public WebElement arrivalTime;

	@FindBy(how = How.ID, using = "flightResvsForm:deptLocCdArea:deptLocCd")
	public WebElement departureLocation;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'deptAlCode')]")
	public WebElement departureFlightCode;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'deptFlightNbr')]")
	public WebElement departureFlightNumber;

	@FindBy(how = How.ID, using = "flightResvsForm:deptDtArea:departureDtInputDate")
	public WebElement departureDate;

	@FindBy(how = How.ID, using = "flightResvsForm:deptDtArea:departureDtPopupButton")
	public WebElement departureDateCalendar;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'departureTime')]")
	public WebElement departureTime;

	@FindBy(how = How.ID, using = "flightResvsForm:reportDate:reportDateInputDate")
	public WebElement reportDate;

	@FindBy(how = How.ID, using = "flightResvsForm:reportDate:reportDatePopupButton")
	public WebElement reportDateCalendar;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'reportTime')]")
	public WebElement reportTime;

	@FindBy(how = How.CSS, using = "select[name$='debriefTime']")
	public WebElement debriefTime;

	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Brief Time']/parent::td/following-sibling::td//select[contains(@id,'briefTime')]")
	public WebElement briefTime;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelindcrewRequestTable:0:hotelCrewTypenArea:crewTypes")
	public WebElement individualBookingDept;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelindcrewRequestTable:0:hotelCrewPositionArea:crews")
	public WebElement individualBookingPosition;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelindcrewRequestTable:0:empIdArea:empId")
	public WebElement individualBookingEmpId;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelindcrewRequestTable:0:empNameArea:empName")
	public WebElement individualBookingEmpName;

	@FindBy(how = How.XPATH, using = "//tbody[@id='flightResvsForm:hotelindcrewRequestTable:tb']//tr[contains(@class,'firstrow')]/td/a[contains(text(),'Delete')]")
	public WebElement individualBookingDelete;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelgroupBooking_lbl")
	public WebElement groupBooking;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelgrpcrewRequestTable:0:hotelCrewTypenArea:crewTypes")
	public WebElement groupBookingDept;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelgrpcrewRequestTable:0:hotelCrewPositionArea:crews")
	public WebElement groupBookingPosition;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelgrpcrewRequestTable:0:roomArea:rooms")
	public WebElement groupBookingRooms;

	@FindBy(how = How.XPATH, using = "//tbody[@id='flightResvsForm:hotelgrpcrewRequestTable:tb']//tr[contains(@class,'firstrow')]/td/a[contains(text(),'Delete')]")
	public WebElement groupBookingDelete;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelindcrewRequestTable:addMore")
	public WebElement individualBookingAddMoreBtn;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelindcrewRequestTable:reset")
	public WebElement individualBookingResetBtn;

	@FindBy(how = How.XPATH, using = ".//*[@class='labelHeading']/following-sibling::span//textarea[contains(@id,'note')]")
	public WebElement notes;

	@FindBy(how = How.XPATH, using = "//input[@value='Save']")    
	public WebElement saveBtn;

	@FindBy(how = How.XPATH, using = "//span/input[@value='Reset']")
	public WebElement resetBtn;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelgrpcrewRequestTable:addMore")
	public WebElement groupBookingAddMore;

	@FindBy(how = How.ID, using = "flightResvsForm:hotelgrpcrewRequestTable:reset")
	public WebElement groupBookingResetBtn;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:hotelindcrewRequestTable']/tbody/tr")
	public List<WebElement> individualBookingRows;

	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:hotelgrpcrewRequestTable']/tbody/tr")
	public List<WebElement> groupBookingRows;
	
	
	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:loHotels']/tbody/tr[1]/td[1]/input")
    public WebElement selectHotelCheckbox;
    
    @FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:loHotels']/tbody/tr[1]/td[2]/label")
    public WebElement HotelName;
        
    String IndividualBookingdeptId="flightResvsForm:hotelindcrewRequestTable:0:hotelCrewTypenArea:crewTypes";
    String GroupBookingdeptId="flightResvsForm:hotelgrpcrewRequestTable:0:hotelCrewTypenArea:crewTypes";
    
    String individualBookingTable = ".//*[@id='flightResvsForm:hotelindcrewRequestTable:";
	String departmentXpath = ":hotelCrewTypenArea:crewTypes']";
	String positionXpath = ":hotelCrewPositionArea:crews']";
	String empIdXpath = ":empIdArea:empId']";
	String empNameXpath = ":empNameArea:empName']";
	String groupBookingTable = ".//*[@id='flightResvsForm:hotelgrpcrewRequestTable:";
	String roomsXpath = ":roomArea:rooms']";

	// AirCanada
	@FindBy(how = How.XPATH, using = "//input[@id='flightResvsForm:hotelindcrewRequestTable:addMore']")
	public WebElement addMoreEmpAC;

	@FindBy(how = How.XPATH, using = "flightResvsForm:hotelindcrewRequestTable:1:hotelCrewTypenArea:crewTypes")
	public WebElement department1AC;

	@FindBy(how = How.XPATH, using = "//select[@id='flightResvsForm:hotelindcrewRequestTable:1:hotelCrewPositionArea:crews']")
	public WebElement position1AC;

	@FindBy(how = How.XPATH, using = "//input[@id='flightResvsForm:hotelindcrewRequestTable:1:empIdArea:empId']")
	public WebElement empId1AC;

	@FindBy(how = How.XPATH, using = "//input[@id='flightResvsForm:hotelindcrewRequestTable:1:empNameArea:empName']")
	public WebElement empName1AC;
	
	@FindBy(how=How.ID,using ="saveFlightResvPanelHeader")
	public WebElement savePanelHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@id='saveFlightResvGrid']/tfoot/tr/td/span/a")
	public WebElement okBtn;
	
	@FindBy(how = How.XPATH,using = ".//*[@id='flightResvsForm:loHotels']/tbody/tr")
	public List<WebElement> hotelTableRows;
	
	public AcesDirectRequestReservationPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is used to do Request Reservation on ACES Direct tab
	 */
	public void requestReservationAcesDirect(String departmentValue, String pairingNumber, String locationValue,
			String arrivalFlightCodeValue, String arrivalFlightNumberValue, String arrivalTimeValue,
			String dropOffLocValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String departureTimeValue, String reportTimeValue, String positionValue, String empIdValue,
			String empNameValue, String roomValue, String notesValue) {
		
		Integer departmentVal = Integer.parseInt(departmentValue);
		selectByIndex(department, departmentVal);
		
		typeInText(pairing, pairingNumber);
		typeInText(hotelLocation, locationValue);
		typeInText(arrivalLocation, locationValue);
		typeInText(arrivalFlightCode, arrivalFlightCodeValue);
		typeInText(arrivalFlightNumber, arrivalFlightNumberValue);
		waitForElementVisibility(arrivalDateCalendar);
		typeInText(arrivalDate, currentDate());
		typeInText(arrivalTime, arrivalTimeValue);
		typeInText(departureLocation, dropOffLocValue);
		departureLocation.sendKeys(Keys.TAB);
		//waitForPageLoad(driver);
		waitForElementVisibility(debriefTime);
		typeInText(departureFlightCode, departureFlightCodeValue);
		typeInText(departureFlightNumber, departureFlightNumberValue);
		waitForElementVisibility(departureDateCalendar);
		typeInText(departureDate, currentDate());
		typeInText(departureTime, departureTimeValue);
		waitForElementVisibility(reportDateCalendar);
		typeInText(reportDate, currentDate());
		typeInText(reportTime, reportTimeValue);
		waitForElementVisibility(debriefTime);
		waitForElementVisibility(briefTime);
		selectByIndex(individualBookingDept, departmentVal);
		// selectByValue(individualBookingPosition, positionValue);
		typeInText(individualBookingEmpId, empIdValue);
		typeInText(individualBookingEmpName, empNameValue);
		waitForElementVisibility(individualBookingDelete);
		waitForElementVisibility(individualBookingAddMoreBtn);
		waitForElementVisibility(individualBookingResetBtn);
		waitForElementVisibility(groupBooking);
		clickOn(groupBooking);
		waitForElementVisibility(groupBookingDept);
		waitForElementVisibility(groupBookingPosition);
		typeInText(groupBookingRooms, roomValue);
		waitForPageLoad(driver);
		waitForElementVisibility(groupBookingDelete);
		waitForElementVisibility(groupBookingResetBtn);
		typeInText(notes, notesValue);
		waitForElementVisibility(saveBtn);
		waitForElementVisibility(resetBtn);

	}
	
	public void employeeDetailsAC(String deptType, String empId, String empName, String empId1, String empName1) {
		selectByVisibleText(individualBookingDept, deptType);
		waitForElementVisibility(individualBookingPosition);
		waitTime(2000);
		// selectByVisibleText(individualBookingPosition, position);
		typeInText(individualBookingEmpId, empId);
		individualBookingEmpId.sendKeys(Keys.TAB);
		waitTime(4000);
		typeInText(individualBookingEmpName, empName);
		waitTime(4000);
		clickOn(addMoreEmpAC);
		waitTime(2000);
		typeInText(empId1AC, empId1);
		empId1AC.sendKeys(Keys.TAB);
		waitTime(4000);
		waitForElementVisibility(empName1AC);
		typeInText(empName1AC, empName1);
		waitTime(2000);
		typeInText(individualBookingEmpName, empName);
		waitTime(2000);
		typeInText(empName1AC, empName1);
		waitTime(2000);
		typeInText(individualBookingEmpName, empName);
		waitTime(2000);
		try {
			takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<String> requestReservation(String departmentValue, String pairingNumber, String locationValue,
			String arrivalFlightNumberValue, String arrivalTimeValue, String departureFlightNumberValue,
			String departureTimeValue, String reportTimeValue) {
		List<String> list = new ArrayList<>();
		selectByVisibleText(department, departmentValue);
		waitTime(2000);
		typeInText(pairing, pairingNumber);
		waitTime(2000);
		typeInText(hotelLocation, locationValue);
		hotelLocation.sendKeys(Keys.TAB);
		waitTime(2000);
		waitForElementTextVisibility(arrivalLocation, "value");
		waitTime(3000);
		typeInText(arrivalFlightNumber, arrivalFlightNumberValue);
		waitTime(2000);
		typeInText(arrivalDate, currentDate());
		waitTime(2000);
		typeInText(arrivalTime, arrivalTimeValue);
		waitTime(2000);
		waitForElementTextVisibility(departureLocation, "value");
		waitTime(3000);
		typeInText(departureFlightNumber, departureFlightNumberValue);
		waitTime(2000);
		waitForElementTextVisibility(departureDate, "value");
		typeInText(departureTime, departureTimeValue);
		waitTime(2000);
		typeInText(reportDate, currentDatePlus(1));
		waitTime(2000);
		typeInText(reportTime, reportTimeValue);
		waitTime(2000);
		waitForElementTextVisibility(debriefTime, "value");
		waitForElementTextVisibility(briefTime, "value");
		list.add(arrivalDate.getAttribute("value"));
		list.add(departureDate.getAttribute("value"));
		return list;
}
	
	public void clickOnSaveReservation(){
		clickOn(saveBtn);
	}
	
	public void processingRequest() {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		waitForElementVisibility(savePanelHeader);
		clickOn(okBtn);
	}
	
	public void selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(String individualEmpId,
			String deptValue, String empName, String noOfRooms) {
		int newRow = 0;
		if (!individualEmpId.isEmpty()) {
			if (!findElementById(IndividualBookingdeptId)
					.getAttribute("value").isEmpty())
				newRow = individualBookingRows.size();
			List<String> empIds = new ArrayList<>(Arrays.asList(individualEmpId.split(",")));
			List<String> empNames = new ArrayList<>(Arrays.asList(empName.split(",")));
			int i = 0;
			for (String emp : empIds) {
				if (i != 0) {
					newRow = individualBookingRows.size();
				}
				if (newRow != 0) {
					clickOn(individualBookingAddMoreBtn);
					waitTime(7000);
				}
				WebElement dept = findElementByXpath(individualBookingTable + newRow + departmentXpath);
				selectByVisibleText(dept, deptValue);
				waitTime(7000);
				WebElement position = findElementByXpath(individualBookingTable + newRow + positionXpath);
				waitForElementTextVisibility(position, "value");
				typeInText(findElementByXpath(individualBookingTable + newRow + empIdXpath), emp);
				waitTime(7000);
				typeInText(findElementByXpath(individualBookingTable + newRow + empNameXpath), empNames.get(newRow));
				waitTime(8000);
				waitForPageLoad(getDriver());
				i++;
			}
		}
		if (!noOfRooms.isEmpty()) {
			clickOn(groupBooking);
			waitTime(7000);
			 if (!findElementById(GroupBookingdeptId)
					.getAttribute("value").isEmpty()) {
				List<String> passengersList = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
				for (int i = 0; i < groupBookingRows.size(); i++) {
					boolean dept = false;
					boolean position = false;
					WebElement groupDept = findElementByXpath(groupBookingTable + i + departmentXpath);
					WebElement groupBookingPosition = findElementByXpath(groupBookingTable + i + positionXpath);
					if (!dept) {
						Select department = new Select(groupDept);
						dept = department.getFirstSelectedOption().getText().equals(deptValue);
					}
					if (!position)
						position = groupBookingPosition.getAttribute("value").equals(deptValue);
					if (dept && position == true) {
						typeInText(findElementByXpath(groupBookingTable + i + roomsXpath), passengersList.get(i));
						waitTime(7000);
					}
				}

			} else if (findElementById(GroupBookingdeptId)
					.getAttribute("value").isEmpty()) {
				if (!findElementById(GroupBookingdeptId).getAttribute("value").isEmpty()) {
							newRow = groupBookingRows.size();
				}
				List<String> passengersList = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
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
					typeInText(findElementByXpath(groupBookingTable + newRow + roomsXpath), passenger);
					waitTime(7000);
					waitForPageLoad(getDriver());
					i++;
				}
			}
		}

	}
	
	public String selectHotel() {
		clickOn(selectHotelCheckbox);
		waitTime(5000);
		String hotelName = HotelName.getText();
		return hotelName;
	}
}
