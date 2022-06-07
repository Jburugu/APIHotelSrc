package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class GTOPSSchedulePage extends BasePage{
	
	public EventFiringWebDriver driver=null;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'tripNumber')]")
	public WebElement tripNumber;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'locCd')]")
	public WebElement location;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'crewId')]")
	public WebElement crewId;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'arrFlightNbr')]")
	public WebElement arrFlightNbr;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'depFlightNbr')]")
	public WebElement depFlightNbr;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'crewName')]")
	public WebElement crewName;
	
	@FindBy(how = How.ID, using = "findReservationsForm:startDateDeco:startDateInputDate")
	public WebElement startDate;
	
	@FindBy(how = How.ID, using = "findReservationsForm:endDateDeco:endDateInputDate")
	public WebElement endDate;
	
	@FindBy(how = How.ID, using = "findReservationsForm:includeHotelGTArea:includeHotelGT")
	public WebElement includeHotelGT;
	
	@FindBy(how = How.ID, using = "findReservationsForm:retrieve1")
	public WebElement retrievebutton;
	
	public GTOPSSchedulePage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void gtOpsSchedule(String tripNumberValue, String locationValue, String crewIdValue,
			String arrivalFlightValue, String departureFilghtValue, String crewNameValue) {

		typeInText(tripNumber, tripNumberValue);
		typeInText(location, locationValue);
		typeInText(crewId, crewIdValue);
		typeInText(arrFlightNbr, arrivalFlightValue);
		typeInText(depFlightNbr, departureFilghtValue);
		typeInText(crewName, crewNameValue);
		typeInText(startDate, currentDate());
		typeInText(endDate, currentDate());
		clickOn(includeHotelGT);
		waitForElementVisibility(retrievebutton);
		
	}
	
	@FindBy(xpath = "//table[@id='findReservationsForm:gtReservationsTable']//tr/td[4]")
	private WebElement getRiders;
	
	

	public void gtOpsSchedule(String tripNumberValue, String locationValue, String crewIdValue,
			String arrivalFlightValue, String departureFilghtValue, String crewNameValue, String startDateValue, String endDateValue) {

		typeInText(tripNumber, tripNumberValue);
		typeInText(location, locationValue);
		typeInText(crewId, crewIdValue);
		typeInText(arrFlightNbr, arrivalFlightValue);
		typeInText(depFlightNbr, departureFilghtValue);
		typeInText(crewName, crewNameValue);
		typeInText(startDate, startDateValue);
		typeInText(endDate, endDateValue);
		if(includeHotelGT.isSelected() == false)
		clickOn(includeHotelGT);
		waitForElementVisibility(retrievebutton);
		clickOn(retrievebutton);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void verifyRiders(String riderCountValue)
	{
		
		if (getRiders.isDisplayed() == true) 
		{
		String getRiderCount = getTextOfElement(getRiders);
		if(getRiderCount.equals(riderCountValue)) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Expected Rider count value is matching with Actual Rider count value");
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Mismatch between expected Rider count value and Actual Rider count value");
		}
	}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "No Results are displayed for the given search criteria");
		}
	}
}
