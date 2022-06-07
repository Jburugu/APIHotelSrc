package com.APIHotels.pages.airlines;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ReservationPage extends BasePage {

	public EventFiringWebDriver driver=null;
	
	// OPERATIONS -- RESERVATION LINK

	@FindBy(id="reservationsForm:viewTypes")
	public WebElement selectRequestsBetween;

	@FindBy(id="reservationsForm:refreshMinutes")
	public WebElement setRefreshInterval;

	@FindBy(id="reservationsForm:retrieve")
	public WebElement retrievebutton;

	// @FindBy(id="reservationsForm:j_id162_lbl")
	@FindBy(xpath= "//*[contains(text(),'Ad Hoc Bookings')]")
	public WebElement adhocBooking;

	// @FindBy(id="reservationsForm:j_id212_lbl")
	@FindBy(xpath= "//*[contains(text(),'Pending Assignments')]")
	public WebElement pendingAssignments;

	@FindBy(xpath= "//*[contains(text(),'Pending Cancellations')]")
	public WebElement pendingCancellations;

	@FindBy(xpath= "//*[contains(text(),'Pending Confirmations')]")
	public WebElement pendingConfirmations;

	@FindBy(xpath= "//*[contains(text(),'Vendor Name Mismatch')]")
	public WebElement vendorNameMismatch;

	@FindBy(xpath= "//*[contains(text(),'Reservation Queries and Responses')]")
	public WebElement reservationQueriesAndResponses;

	@FindBy(xpath= "//*[@id='reservationsForm:dateGroup']//tr[1]//*[contains(@id,'InputDate')]")
	public WebElement startDate;
	
	@FindBy(xpath= "//*[@id='reservationsForm:dateGroup']//tr[2]//*[contains(@id,'InputDate')]")
	public WebElement endDate;
	

	@FindBy(xpath= "//*[@id='reservationsForm:resvQueryTable']//td[8]/a")
	public WebElement viewDetailsLink;
	
	@FindBy(xpath= "//*[@id='reservationsForm:resvQueryTable']/tbody/tr")
	public List<WebElement> queriesTable;
	
	@FindBy(xpath= "//input[contains(@value,'Respond')]")
	public WebElement respondButton;
	
	@FindBy(id="resvQueryForm:resvQueryText")
	public WebElement reservationQueriesComments;
	
	@FindBy(xpath="//input[@value='Add']")
	public WebElement addButton;
	
	@FindBy(xpath="//a[contains(text(),'Close Window')]")
	public WebElement closeWindow;

	String pairingxpath1="//*[@id='reservationsForm:resvQueryTable']//tr[";
	String pairingxpath2="]//td[3]";
	public ReservationPage(EventFiringWebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public void reservation(String selectRequestsBetweenValue,String setRefreshIntervalValue){
		int selectRequestsBetweenVal = Integer.parseInt(selectRequestsBetweenValue);
		selectByIndex(selectRequestsBetween, selectRequestsBetweenVal);
		int setRefreshIntervalVal = Integer.parseInt(setRefreshIntervalValue);
		selectByIndex(setRefreshInterval, setRefreshIntervalVal);
		waitForElementVisibility(retrievebutton);
		clickOn(adhocBooking);
		clickOn(pendingAssignments);
		clickOn(pendingCancellations);
		clickOn(pendingConfirmations);
		clickOn(reservationQueriesAndResponses);

	}

	public void vendorMismatchElement() {
		clickOn(vendorNameMismatch);
	}
	
	public void viewReservation(String selectRequestsBetweenValue, String startDateValue, String EndDateValue){
		int selectRequestsBetweenVal = Integer.parseInt(selectRequestsBetweenValue);
		selectByIndex(selectRequestsBetween, selectRequestsBetweenVal);
		typeInText(startDate, startDateValue);
		typeInText(endDate, EndDateValue);
		clickOn(retrievebutton);
	}
	
	public void clickOnViewReservationQueriesAndResponses(){
		waitForElementVisibility(reservationQueriesAndResponses);
		clickOn(reservationQueriesAndResponses);
		
	}
	
	public void respondToQuery(String pairingNumber, String comment) throws Exception {

		int tableSize = queriesTable.size();
		if (tableSize >= 1) {
			for (int tablerow = 1; tablerow <= tableSize; tablerow++) {
				WebElement pairingnum = findElementByXpath(pairingxpath1 + tablerow + pairingxpath2);
				String pairing = pairingnum.getText();
				if (pairing.equalsIgnoreCase(pairingNumber)) {
					clickOn(viewDetailsLink);
					switchToWindow();
					clickOn(respondButton);
					switchToWindow();
					typeInText(reservationQueriesComments, comment);
					clickOn(addButton);
					break;
				}

			}

		} else {
			throw new Exception("No Queries Found");
		}

	}
}


