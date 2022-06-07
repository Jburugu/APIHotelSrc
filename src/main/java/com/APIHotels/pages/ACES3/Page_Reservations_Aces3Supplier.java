package com.APIHotels.pages.ACES3;


import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_Reservations_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//a[contains(text(), 'Find Reservation')]")
	private WebElement findReservationTab;
	
	@FindBy(xpath = "//a[contains(text(), 'Pending Confirmation')]")
	private WebElement pendingConfirmationTab;
	
	String previousMonth = "//*[@id='ui-datepicker-div']//span[contains(text(),'Previous')]";
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div/div/select/option[@selected = 'selected']")
	private WebElement monthYearTitle;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	
	@FindBy(xpath = "//span[contains(@id, 'tabView:frForm:')]")
	private List<WebElement> calendarBtn;
	
	@FindBy(id = "tabView:frForm:searchButton")
	private WebElement retriveBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[@id = 'tabView:frForm:resTable_data']//td[2]")
	private List<WebElement> refColumn;
	
	private String bookingConfFieldXpath1 = "//*[contains(text(), '";
	private String confirmationNbrFieldXpath =	"')]/../td[11]/input[contains(@id, 'bookConfNbr')]";
	private String confirmBtnXpath = "')]/../td[14]//span";
	private String declineBtnXpath = "')]/../td[15]//span";
	
	private String findResevStatusXpath1 = "//*[@id='tabView:frForm:resTable']//td[contains(text(), '";
	private String statusXpath = "')]/../td[8]/span";
	
	@FindBy(xpath = "//*[@id = 'tabView:form:acceptDlg']/div[2]//span[contains(text(), 'Confirm')]")
	private WebElement accept_ConfirmBtn;
	
	@FindBy(xpath = "//*[@id='tabView:frForm:resTable_head']//span[1]")
	private List<WebElement> reservationTableHeader;
	
	@FindBy(id = "tabView:form:notes")
	private WebElement declineNotes;
	
	@FindBy(xpath = "//*[@id='tabView:form:declineDlg']//span[text()='Decline']")
	private WebElement declineConfirmBtn;
	
	@FindBy(id="tabView:frForm:startDate_input")
	private WebElement startDate;
	
	@FindBy(id="tabView:frForm:endDate_input")
	private WebElement endDate;
	
	@FindBy(xpath="//*[@id='tabView:frForm:inputGrid']//tr[2]/td[4]/input")
	private WebElement confirmationNumber;
	
	@FindBy(xpath="//*[@id='tabView:frForm:resTable']//a[contains(@id,'notes')]")
	private WebElement notesLink;
	
	@FindBy(xpath="//*[@id='tripResvNotesTable_data']/tr")//[1]/td[3]")
	private List<WebElement> tripResvNotes;
	
	String tripNotesXpath1="//*[@id='tripResvNotesTable_data']/tr[";
	String tripNotesXpath2="]/td[3]";
	
	@FindBy(xpath="//*[@id='busNotesTable_data']/tr")
	private List<WebElement> tripResvNotes_BT;
	
	String tripNotesBTXpath1="//*[@id='busNotesTable_data']/tr[";
	String tripNotesBTXpath2="]/td[3]";
	
	
	private String cancelConfirmNbrFieldXpath =	"')]/../td[12]/input[contains(@id, 'cancelConfNumber')]";
	
	public EventFiringWebDriver driver=null;

	public Page_Reservations_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnFindReservationTab(){
		clickOn(findReservationTab, "ReservationsPage -> Find Reservation tab");
		waitForElementToDisappear(spinner);
	}
	
	public void clickOnPendingConfirmationTab(){
		clickOn(pendingConfirmationTab, "ReservationsPage -> Pending Confirmation tab");
		waitForElementToDisappear(spinner);
	}
	
	public void findReservationForDateRange(){
		clickOn(calendarBtn.get(0), "ReservationsPage -> Reservation From Date calendar btn");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", getDateInFormat("d-MMM"));
		clickOn(calendarBtn.get(1), "ReservationsPage -> Reservation To Date calendar btn");
		datePicker_ACESIII(monthYearTitle, previousMonth, calendar, "Custom", getDateInFormat("d-MMM"));
		clickOn(retriveBtn, "ReservationsPage -> Retrive btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifyReservationDetails(String reservationId){
		boolean flag = false;
		for(WebElement ref : refColumn){
			if(ref.getText().trim().equals(reservationId)){
				flag = true;
				ExtentTestManager.getTest().log(LogStatus.PASS, "ReservationId: "+reservationId+" Exists!! ");
				break;
			}
		}
		if(!flag)
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not find ReservationId: " + reservationId + " in Find Reservations screen!");
	}
	
	public void enterConfirmationNumberForReserv(String reservId, String confirmationNumber) throws InterruptedException{
		typeInText(findElementByXpath(bookingConfFieldXpath1+reservId+confirmationNbrFieldXpath), confirmationNumber, "ReservationsPage -> Confirmation Number for Reserv: "+reservId);
		clickOn(findElementByXpath(bookingConfFieldXpath1+reservId+confirmBtnXpath), "ReservationsPage -> Confirm/Accept Btn for "+reservId);
		waitForElementToDisappear(spinner);
		clickOn(accept_ConfirmBtn, "ReservationsPage -> Accept Confirm btn");
		Thread.sleep(5000);
		waitForElementToDisappear(spinner);
	}
	
	public void declineReservation(String reservId, String notes){
		clickOn(findElementByXpath(bookingConfFieldXpath1+reservId+declineBtnXpath), "ReservationsPage -> Decline btn for "+reservId);
		waitForElementToDisappear(spinner);
		typeInText(declineNotes, notes);
		clickOn(declineConfirmBtn, "ReservationsPage -> Decline Confirm Btn");
		waitForElementToDisappear(spinner);
	}
	
	public void verifyStatus(String reservId, String expectedStatus){
		assertEquals(findElementByXpath(findResevStatusXpath1+reservId+statusXpath).getText(), expectedStatus, "Status Mismatch!");
	}
	
	public void findReservationUsingConfirmation(String confirmationCode, String startDateValue, String endDateValue){
		typeInText(confirmationNumber, confirmationCode, "ReservationPage -> Confirmation Number");
		typeInText(startDate, startDateValue, "ReservationPage -> Start Date");
		typeInText(endDate, endDateValue, "ReservationPage -> End Date");
		clickOn(retriveBtn);
		waitForElementToDisappear(spinner);
	}
	
	public void verifyNotesOfReservation(String confirmationNum, String alertType) throws Exception {
		String confirmNotes = "Confirmed reservation with ***" + confirmationNum + " (Hotel)";
		String cancelNotes = "Cancelled Reservation with ***" + confirmationNum + " (Hotel)";
		String tripReservationNotes = "";
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		clickOn(notesLink);
		Thread.sleep(5000);
		switchToNewWindow(parentHandle);
		waitForPageToLoad("20");
		getDriver().manage().window().maximize();
		if (tripResvNotes.size() > 0) {
			for (int i = 1; i < tripResvNotes.size(); i++) {
				tripReservationNotes = findElementByXpath(tripNotesXpath1 + i + tripNotesXpath2).getText();
				System.out.println(tripReservationNotes);
				if (tripReservationNotes.equalsIgnoreCase(confirmNotes)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, confirmNotes + "found in trip reservation notes");
					break;
				} else if (tripReservationNotes.equalsIgnoreCase(cancelNotes)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, cancelNotes + "found in trip reservation notes");
					break;
				}

			}
		} else if (tripResvNotes_BT.size() > 0) {
			for (int i = 1; i < tripResvNotes_BT.size(); i++) {
				tripReservationNotes = findElementByXpath(tripNotesBTXpath1 + i + tripNotesBTXpath2).getText();
				System.out.println(tripReservationNotes);
				if (tripReservationNotes.equalsIgnoreCase(confirmNotes)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, confirmNotes + "found in trip reservation notes");
					break;
				} else if (tripReservationNotes.equalsIgnoreCase(cancelNotes)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, cancelNotes + "found in trip reservation notes");
					break;
				}
			}
		}

		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Comments not found in trip reservation notes");

	}
	
	public void enterCancelConfirmNumberForReserv(String reservId, String cancellationNumber) throws InterruptedException{
		typeInText(findElementByXpath(bookingConfFieldXpath1+reservId+cancelConfirmNbrFieldXpath), cancellationNumber, "ReservationsPage -> Confirmation Number for Reserv: "+reservId);
		clickOn(findElementByXpath(bookingConfFieldXpath1+reservId+confirmBtnXpath), "ReservationsPage -> Confirm/Accept Btn for "+reservId);
		waitForElementToDisappear(spinner);
		clickOn(accept_ConfirmBtn, "ReservationsPage -> Accept Confirm btn");
		Thread.sleep(10000);
		waitForElementToDisappear(spinner);
	}
	
}
