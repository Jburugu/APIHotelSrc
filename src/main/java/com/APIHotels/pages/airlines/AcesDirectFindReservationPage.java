package com.APIHotels.pages.airlines;

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

public class AcesDirectFindReservationPage extends BasePage{

	public EventFiringWebDriver driver=null;
	
	@FindBy(how = How.XPATH, using = "//label[text()=' Hotel']/preceding-sibling::input[@type='radio']")
	public WebElement hotelServiceTypeRadioBtn;

	@FindBy(how = How.XPATH, using = "//label[text()=' GT']/preceding-sibling::input[@type='radio']")
	public WebElement gtServiceTypeRadioBtn;

	@FindBy(how = How.ID, using = "flightResvsForm:deptArea:prgType")
	public WebElement dept;

	@FindBy(how = How.ID, using = "flightResvsForm:tripNumArea:tripNum")
	public WebElement pairing;

	@FindBy(how = How.ID, using = "flightResvsForm:opRetrieveArea:opDateInputDate")
	public WebElement operatingDate;

	@FindBy(how = How.ID, using = "flightResvsForm:opRetrieveArea:opDatePopupButton")
	public WebElement operatingDateCalendar;

	@FindBy(how = How.ID, using = "flightResvsForm:findResLocCdArea:findResLocCd")
	public WebElement location;

	@FindBy(how = How.ID, using = "flightResvsForm:arrFlightNumArea:arrFlightNbr")
	public WebElement arrivalFlight;

	@FindBy(how = How.ID, using = "flightResvsForm:depFlightNumArea:depFlightNbr")
	public WebElement departureFlight;

	@FindBy(how = How.ID, using = "flightResvsForm:startRetrieveArea:startDateInputDate")
	public WebElement startDate;

	@FindBy(how = How.ID, using = "flightResvsForm:startRetrieveArea:startDatePopupButton")
	public WebElement startDateCalendar;

	@FindBy(how = How.ID, using = "flightResvsForm:endRetrieveArea:endDateInputDate")
	public WebElement endDate;

	@FindBy(how = How.ID, using = "flightResvsForm:endRetrieveArea:endDatePopupButton")
	public WebElement endDateCalendar;

	@FindBy(how = How.ID, using = "flightResvsForm:retrieveButton")
	public WebElement retrieve;
	
	String serviceTypeXpath1 = "//input[@value='";
	String serviceTypeXpath2 = "']";
	
	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:tripResvTable']/tbody/tr")
	public List<WebElement> tableRow;
	
	String tableRowXpath = ".//*[@id='flightResvsForm:tripResvTable']//tr[";
	String tableRowXpath1="]/td";
	String statusXpath = "]/td[10]/span";
	String cancelXpath = "]/td[16]/div/a[text()='Cancel']";
	String editXpath = "]/td[16]/div/a[text()='Edit']";

	@FindBy(how= How.XPATH,using =".//*[@id='flightResvsForm:hotelcrewEditRequestTable']/tbody/tr")
	public List<WebElement> cancelTableRows;
	
	@FindBy(how = How.XPATH,using = "//input[@value='Cancel Resv']")
	public WebElement cancelResvBtn;
	
	@FindBy(how = How.XPATH,using="//a[text()='OK']")
	public WebElement okBtn;

	@FindBy(how = How.ID,using = "flightResvsForm:hotelcrewEditRequestTable:hotelcrewSelectAll")
	public WebElement selectAll;
	
	@FindBy(how= How.XPATH,using = ".//*[@id='flightResvsForm:findGlobalMessages']/dt/span")
	public WebElement noRecordsFoundMessage;
	
	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Wait Please...')]")
	public WebElement waitPleaseOverlay;

	@FindBy(name = "flightResvsForm:serviceTypeArea:serviceType")
	private List<WebElement> serviceType; // HOTEL, GT, BOTH
	
	String cancelTripTable = "//table[contains(@id,'flightResvsForm:hotelcrewEditRequestTable')]/tbody/tr";
	String crewEditTable = "flightResvsForm:hotelcrewEditRequestTable:";
	String hotelCrewSelect = ":hotelcrewSelectArea']/div/input";
	String crewEditPosition = ":hotelCrewPositionArea:crews";
	String crewEditEmpId = ":empIdArea:empId";
	String crewEditEmpName = ":empNameArea:empName";
	String selectXpath1 = ".//*[@id='flightResvsForm:hotelcrewEditRequestTable:";
	String selectXpath2 = ":hotelcrewSelectArea']/div/input";
	
	@FindBy(id = "flightResvsForm:hotelcrewEditRequestTable:addMore")
	public WebElement addMoreBtn;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='flightResvsForm:hotelcrewEditRequestTable']/tbody/tr")
	public List<WebElement> editTableRow;
	
	String editTableXpath = ".//*[@id='flightResvsForm:hotelcrewEditRequestTable:";
	String empXpath = ":empIdArea:empId']";
	String nameXpath = ":empNameArea:empName']";
	
	@FindBy(xpath = "//input[@value='Save Data']")
	public WebElement saveDataBtn;
	
	String currentWindowHandle;
	String editTableRowXpath = ".//*[@id='flightResvsForm:hotelcrewEditRequestTable']/tbody/tr[";
	String editTableColumnXpath = "]/td[";
	String editTableCoumnXpath2 = "]//input";
	String rowXpath1 = ".//*[@id='flightResvsForm:hotelcrewEditRequestTable:0:hotelcrewSelectArea']/div/input";
	String EditCrewXpath1=".//*[@id='flightResvsForm:hotelcrewEditRequestTable']/tbody/tr[";
    String EditCrewXpath2="]/td";
    
    String xpathPart1 ="//td[contains(text(),'";
	String xpathPart2 ="')]/parent::tr/td/span[contains(@id,'resvStatus')]";
	String cancelxpathPart1 ="//td[contains(text(),'";
	String cancelxpathPart2 = "')]/parent::tr/td/div/a[contains(text(),'Cancel')]";
    
	public AcesDirectFindReservationPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void findReservationACESDirect(String deptIndex, String pairingNumber, String locationValue,
			String arrivalFlightNumber, String departureFlightNumber)  {
		
		clickOn(hotelServiceTypeRadioBtn);
		Integer indexValueParse = Integer.parseInt(deptIndex);
		selectByIndex(dept, indexValueParse);
		
		typeInText(pairing, pairingNumber);
		
		waitForElementVisibility(operatingDateCalendar);
		
		typeInText(operatingDate, currentDate());
		
		typeInText(location, locationValue);
		
		typeInText(arrivalFlight, arrivalFlightNumber);
		
		typeInText(departureFlight, departureFlightNumber);
		
		waitForElementVisibility(startDateCalendar);
		
		typeInText(startDate, currentDate());
		
		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, currentDate());
		
		waitForElementVisibility(retrieve);
		
		clickOn(gtServiceTypeRadioBtn);
		
	}
	
	public void findReservation(String serviceType, String deptValue, String pairingNumber,
			String locationValue,String startDateValue,String endDateValue) {
		List<String> departments = new ArrayList<>(Arrays.asList(deptValue.split(",")));
		clickOn(findElementByXpath(serviceTypeXpath1 + serviceType + serviceTypeXpath2));
		selectByVisibleText(dept, departments.get(0));
		typeInText(pairing, pairingNumber);
		typeInText(location, locationValue);
		typeInText(startDate, startDateValue);
		typeInText(endDate, endDateValue);
		clickOn(retrieve);
	}
	
	public int verifyReservation(String pickUpDateValue, String pickUpTimeValue,
			String dropOffDateValue, String dropOffTimeValue, String hotelName) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int rowCount = 0;
		for (int i = 1; i <= tableRow.size(); i++) {
				List<WebElement> data = findElementsByXpath(tableRowXpath + i + tableRowXpath1);
				boolean arrivalFlightDate = false;
				boolean dropOffDate = false;
				boolean supplier = false;
				for (int j = 1; j < data.size(); j++) {

					if (!arrivalFlightDate)
						arrivalFlightDate = data.get(j).getText().equals(pickUpDateValue + " " + pickUpTimeValue);
					if (!dropOffDate)
						dropOffDate = data.get(j).getText().equals(dropOffDateValue + " " + dropOffTimeValue);
					if (!supplier)
						supplier = data.get(j).getText().contains(hotelName);
				}
				rowCount++;
				if (arrivalFlightDate && dropOffDate && supplier == true)
					return rowCount;
		}
		return rowCount;
	}
	
	public void verifyStatus(int rowCount, String expectedResult) {
		Assert.assertEquals(findElementByXpath(tableRowXpath + rowCount + statusXpath).getText(), expectedResult,
				"Status is not as expected");
	}
	
	public void clickOnCancelLink(int rowCount) {
		currentWindowHandle = clickOnCancel(rowCount);
		clickOn(selectAll);
		waitTime(2000);
		clickOn(cancelResvBtn);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
		wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));

	}
	
	public void findReservationACESDirectAC(String serviceType, String deptType, String pairingNumber,
			String locationValue, String arrivalFlightNumber, String departureFlightNumber) {

		services(serviceType);

		selectByVisibleText(dept, deptType);

		typeInText(pairing, pairingNumber);

		waitForElementVisibility(operatingDateCalendar);

		typeInText(operatingDate, currentDate());

		typeInText(location, locationValue);

		typeInText(arrivalFlight, arrivalFlightNumber);

		typeInText(departureFlight, departureFlightNumber);

		waitForElementVisibility(startDateCalendar);

		typeInText(startDate, currentDate());

		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, currentDate());

		waitForElementVisibility(retrieve);

	}

	
	public void clickOnRetrieve(){
		clickOn(retrieve);
		waitOverlayToDisappear();
	}
	
	/**
	 * wait for the "Wait Please..." overlay to disappear.
	 */
	public void waitOverlayToDisappear() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.invisibilityOf(waitPleaseOverlay));
	}

	public void services(String serviceType) {
		for (WebElement thisServiceType : this.serviceType)
			if (thisServiceType.getAttribute("value").equals(serviceType)) {
				clickOn(thisServiceType);
				break;
			}
	}

	public void validteResvStatus(String pairingNumber){
				
		WebElement resvStatus = driver.findElement(By.xpath(xpathPart1+pairingNumber+xpathPart2));
		System.out.println("Reservation status is : " + resvStatus.getText()+" for the following Paring Number : "+pairingNumber);
		Assert.assertEquals(resvStatus.getText(), "Pending Assignment");
		
	}
	
	public void cancelRes(String pairingNumber){
		
		WebElement lnkCancel = driver.findElement(By.xpath(cancelxpathPart1+pairingNumber+cancelxpathPart2));
		lnkCancel.click();
		
	}
	
	public List<String> cancelCrewMembers(int rowCount, String individualEmpId, String empName) {
		List<String> list = new ArrayList<>();
		currentWindowHandle = clickOnCancel(rowCount);
		int rows = findElementsByXpath(cancelTripTable).size();
		int count = 0;
		for (int i = 1; i <= rows; i++) {
			boolean id = false;
			boolean name = false;
			if (!id)
				id = findElementById(crewEditTable + count + crewEditEmpId)
						.getAttribute("value").equals(individualEmpId);
			if (!name) {
				name = findElementById(crewEditTable + count + crewEditEmpName)
						.getAttribute("value").equals(empName);
			}
			if (id && name == true) {
				clickOn(findElementByXpath(selectXpath1 + count + selectXpath2));
			}
			count++;
		}
		waitTime(2000);
		clickOn(cancelResvBtn);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
		return list;

	}
	
	public int verifyCancelledRooms(String departmentValue, String empName, String empId, String expectedStatus) {
		wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int rowCount = 0;
		for (int i = 1; i <= tableRow.size(); i++) {
			List<WebElement> data = findElementsByXpath(tableRowXpath + i + tableRowXpath1);
			boolean employeeName = false;
			boolean status = false;

			for (int j = 1; j < data.size(); j++) {

				if (!employeeName)
					employeeName = data.get(j).getText().contains(departmentValue + " "+empName + " (" + empId + ")");
				if (!status)
					status = data.get(j).getText().equals(expectedStatus);
			}
			rowCount++;
			if (employeeName && status == true) {
				return rowCount;
			}
		}
		return rowCount;
	}
	
	public void editReservation(int rowCount, String empId, String empName) {
		currentWindowHandle = clickOnEditLink(rowCount);
		clickOn(addMoreBtn);
		int count = 0;
		for (int i = 1; i <= editTableRow.size(); i++) {
			 List<WebElement> column = findElementsByXpath(EditCrewXpath1 + i +EditCrewXpath2);
			boolean delete = false;
			for (int j = 1; j <column.size(); j++) {
				if (!delete)
					delete = column.get(j).getText().equals("Delete");
			}
			if (delete == true) {
				System.out.println(findElementByXpath(editTableXpath + count + empXpath).isDisplayed());
				typeInText(findElementByXpath(editTableXpath + count + empXpath), empId);
				typeInText(findElementByXpath(editTableXpath + count + nameXpath), empName);
			}
			count++;
		}
		clickOn(saveDataBtn);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
	}
	
	public String clickOnEditLink(int rowCount) {
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(findElementByXpath(tableRowXpath + rowCount + editXpath));
		switchToNewWindow(currentWindowHandle);
		return currentWindowHandle;
	}
	
	public void cancelEmployee(String currentWindowHandle, String empid, String empName) {
		int count = 0;
		for (int i = 1; i <= editTableRow.size(); i++) {
			List<WebElement> column = findElementsByXpath(editTableRowXpath + i + tableRowXpath1);
			boolean employeeId = false;
			boolean employeeName = false;
			for (int j = 3; j < column.size(); j++) {
				if (!employeeId)
					employeeId = findElementByXpath(
							editTableRowXpath + i + editTableColumnXpath + j + editTableCoumnXpath2)
									.getAttribute("value").equals(empid);
				if (!employeeName)
					employeeName = findElementByXpath(
							editTableRowXpath + i + editTableColumnXpath + j + editTableCoumnXpath2)
									.getAttribute("value").equals(empName);
			}
			if (employeeId && employeeName == true) {
				clickOn(findElementByXpath(selectXpath1 + count	+ hotelCrewSelect));
			}
			count++;
		}
		clickOn(saveDataBtn);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
	}
	
	public String clickOnCancel(int rowCount) {
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(findElementByXpath(tableRowXpath + rowCount + cancelXpath));
		switchToNewWindow(currentWindowHandle);
		return currentWindowHandle;

	}
	
	public void cancelSingleCrew(int rowCount){
		currentWindowHandle = 	clickOnCancel(rowCount);
		clickOn(findElementByXpath(rowXpath1));
		waitTime(2000);
		clickOn(cancelResvBtn);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		clickOn(okBtn);
		switchToWindow(currentWindowHandle);
		
	}
	
	
	public void verifyReservationDoesntExist(){
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
			Assert.assertEquals(noRecordsFoundMessage.getText(), "No records found for the given criteria",
					"No Records Found Message is not displayed and the reservation doesnot exist ");
	}
}
