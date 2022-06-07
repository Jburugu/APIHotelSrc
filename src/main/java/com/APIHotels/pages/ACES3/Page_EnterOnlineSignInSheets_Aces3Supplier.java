package com.APIHotels.pages.ACES3;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_EnterOnlineSignInSheets_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[contains(@id, 'resvDayQuery') and contains(@id, 'tenant_label')]")
	private WebElement selectAirline;
	
	private String airlineXpath1 = "//ul[contains(@id, 'resvDayQuery')]/li[contains(text(), '";
	private String airlineXpath2 = "')]";
	
	@FindBy(xpath = "//*[@aria-label='Show Calendar']")
	private List<WebElement> calendarBtn;
	
	private String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/select/option[@selected = 'selected']")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	
	@FindBy(xpath = "//*[contains(text(), 'Load')]")
	private WebElement loadBtn;
	
	@FindBy(xpath = "//*[@id='resvDayResults:nonWalkInFlightGroups']/div[contains(@id, 'resvDayResults')]")
	private List<WebElement> nonWalkInFlightGroupsTable;
	
	@FindBy(xpath = "//*[contains(@id, 'checkInTable_data')]//input[contains(@id, 'enteredEmployeeId')]")
	private List<WebElement> empIdList;
	
	@FindBy(xpath = "//*[contains(@id, 'mismatchGuestInfoTooltip')]")
	private List<WebElement> mismatchEmpIdTooltip;
	
	@FindBy(xpath = "//*[contains(text(), 'The Employee ID you have entered does not match the ID on file for this crew member')]")
	private WebElement empIdMismatchMsg;
	
	@FindBy(xpath = "//*[contains(@id, 'checkInTable_data')]//*[contains(@id, 'statusIcon')]/i")
	private List<WebElement> statusList;
	
	@FindBy(xpath = "//*[contains(@id, 'checkInTable_data')]//*[contains(@id, 'statusIcon')]/i[contains(@title, 'Uhsubmitted')]")
	private WebElement unSubmittedStatusIcon;
	
	@FindBy(xpath = "//*[contains(@id, 'checkInTable_data')]//input[contains(@id, 'roomNumber')]")
	private List<WebElement> roomNumberList;
	
	@FindBy(xpath = "//*[contains(@id, 'checkInTable_data')]//input[contains(@id, 'roomRate')]")
	private List<WebElement> roomRateList;
	
	@FindBy(id = "resvDayResults:addMember")
	private WebElement addCrewNotListedBelowBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Occupancy Percentage:')]/../span")
	private WebElement occupancyPercentageLink;
	
	@FindBy(xpath = "//*[contains(@id, 'occPercentage')]")
	private WebElement occupancyPercentageValue;
	
	@FindBy(xpath = "//*[@title = 'Save']")
	private WebElement occupancyPercentageSaveBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Attach')]")
	private WebElement attachBtn;
	
	@FindBy(xpath = "//div[contains(@id, 'fileUpload')]//span[contains(text(), 'Choose File')]/parent::span")
	private WebElement chooseFileBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Upload File')]")
	private WebElement uploadFileBtn;
	
	@FindBy(xpath = "//*[contains(@id, 'enteredGuestName')]")
	private WebElement empName;
	
	@FindBy(xpath = "//*[contains(text(), ' Return to previous activity')]")
	private WebElement returnToPrevActivityLink;
	
	private String crewSwapChkBoxForEmpXpath1 = "//*[contains(text(), '";
	private String crewSwapChkBoxForEmpXpath2 = "')]/../../following-sibling::td//*[contains(text(), 'Crew Swap')]";
	private String newEmpNameForOldEmpXpath = "')]/../input[contains(@id,'enteredGuestName')]";  
	private String statusIconForEmpXpath = "')]/../../following-sibling::td[8]/div/i";
	private String noBillChkBoxForEmpXpath = "')]/../../following-sibling::td//*[contains(text(), 'No Bill')]";
	private String noShowChkBoxForEmpXpath = "')]/../../following-sibling::td//*[contains(text(), 'No Show')]";
	private String walkinEmpNameXpath = "//*[contains(@value, '";
	private String mergeRoomNumberXpath = "')]/../../following-sibling::td[3]/div";
	
	public EventFiringWebDriver driver=null;
	private WebDriverWait wait;

	public Page_EnterOnlineSignInSheets_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		initializeWait();
	}
	
	private void initializeWait(){
		wait = new WebDriverWait(getDriver(), 30);
		wait.withTimeout(Duration.ofMinutes(20));
		wait.pollingEvery(Duration.ofMillis(5));
		wait.ignoring(StaleElementReferenceException.class);
	}
	
	public void loadSignInSheet(String tenant, String reservationDate){
		clickOn(selectAirline, "EnterOnlineSign-inSheetsPage -> Select Airline drop down");
		clickOn(findElementByXpath(airlineXpath1+tenant+airlineXpath2), "EnterOnlineSign-inSheetsPage -> "+tenant+" option");
		waitForElementToDisappear(spinner);
		clickOn(calendarBtn.get(0), "EnterOnlineSign-inSheetsPage -> ReservationDate Calendar btn");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", reservationDate);
		waitForElementToDisappear(spinner);
		clickOn(loadBtn, "EnterOnlineSign-inSheetsPage -> Load btn");
		waitForElementToDisappear(spinner);
	}
	
	public void loadSignInSheet(){
		clickOn(loadBtn, "EnterOnlineSign-inSheetsPage -> Load btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifySignInSheetLoaded(){
		assertTrue(nonWalkInFlightGroupsTable.size()>0, "Sign-In sheet for given Reservation Date does not exist!");
	}
	
	public void verifyValidationMsgForIncorrectEmpId(String empId){
		enterValueJavaScript(empIdList.get(0), "value", empId);
		empIdList.get(0).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		assertTrue(mismatchEmpIdTooltip.size()>0, "ValidationMsgIcon not displayed!");
	}
	
	public void enterEmpId() throws InterruptedException{
		for(int emp_i = 0; emp_i < empIdList.size(); emp_i++){
			//enterValueJavaScript(empIdList.get(5), "value", "noid");
			typeInText(empIdList.get(emp_i), "NO ID", "EnterOnlineSign-inSheetsPage -> "+emp_i+" row Emp Id");
			Thread.sleep(5000);
			empIdList.get(emp_i).sendKeys(Keys.TAB);
			waitForElementToDisappear(spinner);
			//Actions action = new Actions(driver);
			//roomRateList.get(5).sendKeys(Keys.TAB);
			Thread.sleep(5000);
			waitForElementToDisappear(spinner);
			Thread.sleep(10000);
		}
	}
	
	/*public void enterEmpId() throws InterruptedException{
		for(int emp_i = 0; emp_i < empIdList.size(); emp_i++){
			typeInText(empIdList.get(emp_i), "noid");
			Thread.sleep(2000);
			//empIdList.get(emp_i).sendKeys(Keys.TAB);
			clickOn(roomRateList.get(emp_i));
			waitForElementToDisappear(spinner);
			wait.until(ExpectedConditions.attributeToBe(statusList.get(emp_i), "title", "Submitted"));
		}
		for(WebElement statusIcon : statusList)
			wait.until(ExpectedConditions.attributeContains(statusIcon, "title", "Submitted"));
	}*/
	
	
	
	public void crewSwap(String empName, String newEmpName){
		clickOn(findElementByXpath(crewSwapChkBoxForEmpXpath1+empName+crewSwapChkBoxForEmpXpath2), "EnterOnlineSign-inSheetsPage -> Crew Swap check box against "+empName);
		waitForElementToDisappear(spinner);
		WebElement employeeName = findElementByXpath(crewSwapChkBoxForEmpXpath1+empName+newEmpNameForOldEmpXpath);
		enterValueJavaScript(employeeName, "value", newEmpName);
		employeeName.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		wait.until(ExpectedConditions.attributeToBe(By.xpath(crewSwapChkBoxForEmpXpath1+empName+statusIconForEmpXpath), "title", "Submitted"));
	}

	public void markNoBill(String noBillEmpName) {
		clickOn(findElementByXpath(crewSwapChkBoxForEmpXpath1+noBillEmpName+noBillChkBoxForEmpXpath), "EnterOnlineSign-inSheetsPage -> No Bill check box against "+noBillEmpName);
		waitForElementToDisappear(spinner);
		wait.until(ExpectedConditions.attributeToBe(By.xpath(crewSwapChkBoxForEmpXpath1+noBillEmpName+statusIconForEmpXpath), "title", "Submitted"));
	}

	public void markNoShow(String noShowEmpName, String noShowCategory) {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + noShowCategory);
		clickOn(findElementByXpath(crewSwapChkBoxForEmpXpath1+noShowEmpName+noShowChkBoxForEmpXpath), "EnterOnlineSign-inSheetsPage -> No Show check box against "+noShowEmpName);
		waitForElementToDisappear(spinner);
		wait.until(ExpectedConditions.attributeToBe(By.xpath(crewSwapChkBoxForEmpXpath1+noShowEmpName+statusIconForEmpXpath), "title", "Submitted"));
	}

	public void editRate(String rate) {
		enterValueJavaScript(roomRateList.get(0), "value", rate);
		roomRateList.get(0).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
	}

	public void editRoomNumber(String roomNumber) {
		enterValueJavaScript(roomNumberList.get(0), "value", roomNumber);
		roomNumberList.get(0).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
	}

	public void editReservationDates(String newReservationDate) {
		clickOn(calendarBtn.get(1), "EnterOnlineSign-inSheetsPage -> Reservation btn in the first Row");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", newReservationDate);
		waitForElementToDisappear(spinner);
	}

	public void addWalkIn(String walkInEmpName, String walkInEmpId, String walkInRoomNo, String walkInRate) throws Exception{
		clickOn(addCrewNotListedBelowBtn, "EnterOnlineSign-inSheetsPage -> Add Crew Not Listed Below btn");
		waitForElementToDisappear(spinner);
		/*enterValueJavaScript(empName, "value", walkInEmpName);
		enterValueJavaScript(empIdList.get(0), "value", walkInEmpId);
		enterValueJavaScript(roomNumberList.get(0), "value", walkInRoomNo);
		enterValueJavaScript(roomRateList.get(0), "value", walkInRate);
		wait.until(ExpectedConditions.attributeToBe(By.xpath(walkinEmpNameXpath+walkInEmpName+statusIconForEmpXpath), "title", "Submitted"));*/
		
		typeInText(empName, walkInEmpName, "EnterOnlineSign-inSheetsPage -> Walk-in Emp Name");
		Thread.sleep(3000);
		empName.sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		
		typeInText(empIdList.get(0), walkInEmpId, "EnterOnlineSign-inSheetsPage -> Walk-in Emp Id");
		Thread.sleep(3000);
		empIdList.get(0).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		
		typeInText(roomNumberList.get(0), walkInRoomNo, "EnterOnlineSign-inSheetsPage -> Walk-in Room No");
		Thread.sleep(3000);
		roomNumberList.get(0).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		
		typeInText(roomRateList.get(0), walkInRate);
		Thread.sleep(3000);
		roomRateList.get(0).sendKeys(Keys.TAB);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
	}
	
	public void enterOccupancyPercentage(String occupancyPercentage){
		clickOn(occupancyPercentageLink, "EnterOnlineSign-inSheetsPage -> Occupancy Percentage link");
		typeInText(occupancyPercentageValue, occupancyPercentage, "EnterOnlineSign-inSheetsPage -> Occupancy Percentage");
		clickOn(occupancyPercentageSaveBtn, "EnterOnlineSign-inSheetsPage -> Occupancy percentage Save btn");
		waitForElementToDisappear(spinner);
	}
	
	public void attachOccupancyReport() throws Exception{
		clickOn(attachBtn, "EnterOnlineSign-inSheetsPage -> Attach btn");
		waitForElementToDisappear(spinner);
		clickOn(chooseFileBtn, "EnterOnlineSign-inSheetsPage -> Choose File btn");
		Thread.sleep(3000);
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\lib\\uploadFile_Aces3.exe"+" "+System.getProperty("user.dir")+"\\testdata\\OccupancyReport.docx");
		waitForElementVisibility(By.xpath("//*[contains(text(), 'OccupancyReport.docx')]"));
		clickOn(uploadFileBtn, "EnterOnlineSign-inSheetsPage -> Upload File Btn");
		Thread.sleep(3000);
		waitForElementToDisappear(spinner);
		assertTrue(findElementByXpath("//*[contains(text(), 'OccupancyReport.docx')]").isDisplayed(), "OccupancyReport is not uploaded!");
	}
	
	public void returnToPrevActivity(){
		clickOn(returnToPrevActivityLink, "EnterOnlineSign-inSheetsPage -> Return to Previous Activity link");
		waitForElementToDisappear(spinner);
	}
	
	public void enterEmpIdIfEmpty() throws InterruptedException{
		for(int emp_i = 0; emp_i < empIdList.size(); emp_i++){
			//enterValueJavaScript(empIdList.get(5), "value", "noid");
			if (empIdList.get(emp_i).getAttribute("value").isEmpty()
					|| empIdList.get(emp_i).getAttribute("value") == null) {
				typeInText(empIdList.get(emp_i), "NO ID", "EnterOnlineSign-inSheetsPage -> " + emp_i + " row Emp Id");
				Thread.sleep(5000);
				empIdList.get(emp_i).sendKeys(Keys.TAB);
				waitForElementToDisappear(spinner);
				// Actions action = new Actions(driver);
				// roomRateList.get(5).sendKeys(Keys.TAB);
				Thread.sleep(5000);
				waitForElementToDisappear(spinner);
				Thread.sleep(10000);
			}
		}
	}
	


	public void crewMerge(String mergeEmp1Name, String  mergeEmp2Name) {
		System.out.println();
		WebElement emp1 = findElementByXpath(crewSwapChkBoxForEmpXpath1+mergeEmp1Name+mergeRoomNumberXpath);
		WebElement emp2 = findElementByXpath(crewSwapChkBoxForEmpXpath1+mergeEmp2Name+mergeRoomNumberXpath);
		dragAndDrop(emp1, emp2, "EnterOnlineSign-inSheetsPage -> "+mergeEmp1Name+" to "+mergeEmp2Name);
		waitForElementToDisappear(spinner);
		wait.until(ExpectedConditions.attributeToBe(By.xpath(crewSwapChkBoxForEmpXpath1+mergeEmp1Name+statusIconForEmpXpath), "title", "Submitted"));
	}
}
