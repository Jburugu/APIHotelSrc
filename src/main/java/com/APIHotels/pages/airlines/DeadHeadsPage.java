package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class DeadHeadsPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- DEADHEADS (JETBLUE)

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Deadheads')]")
	private WebElement deadHeadsLink;

	@FindBy(how = How.XPATH, using = "//input[@type='radio']/following-sibling::label[text()=' View Pending Deadhead Bookings / Cancelations']")
	private WebElement viewPendingDeadHeadBookingRadioBtn;

	@FindBy(how = How.XPATH, using = "//input[@type='radio']/following-sibling::label[text()=' View Booked and Canceled Deadheads']")
	private WebElement viewBookedAndCancelledDeadheads;

	@FindBy(how = How.ID, using = "deadheadsForm:refreshMinutes")
	private WebElement setrefreshIntervals;

	@FindBy(how = How.ID, using = "deadheadsForm:retrieve")
	private WebElement retrieveButton;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Pending Deadhead Bookings')]")
	private WebElement pendingDeadHeadBooking;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Pending Deadhead Cancelations')]")
	private WebElement pendingDeadHeadCancellations;

	public DeadHeadsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyDeadheadPage(){
		clickOn(deadHeadsLink);
		waitForElementVisibility(viewBookedAndCancelledDeadheads);
		waitForElementVisibility(viewPendingDeadHeadBookingRadioBtn);
		
		waitForElementVisibility(setrefreshIntervals);
		waitForElementVisibility(retrieveButton);
		
		waitForElementVisibility(pendingDeadHeadBooking);
		waitForElementVisibility(pendingDeadHeadCancellations);
		
	}
	
	
}
