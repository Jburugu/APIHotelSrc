package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;

public class Page_HotelSupplier_ContractDetails extends BasePage{
	
	@FindBy(how = How.NAME, using = "plannedBookingNonCancellable")
	private WebElement plannedRoomsNotCancellableIndicator;
	
	@FindBy(id = "transportProvided")
	private WebElement transportationByHotelIndicator;
	
	@FindBy(id = "roomRuleId")
	private WebElement roomRule;
	
	@FindBy(id = "checkInDummy")
	private WebElement checkIn;
	
	@FindBy(id = "checkOutDummy")
	private WebElement checkOut;
	
	@FindBy(id = "hotelStayHoursId")
	private WebElement hotelStayHours;
	
	@FindBy(id = "gracePeriodId")
	private WebElement gracePeriod;
	
	@FindBy(id = "graceOnFirstDayOnly")
	private WebElement graceOnFirstDayOnlyIndicator;
	
	@FindBy(name = "cancelRuleIdentifier")
	private List <WebElement> cancellationPolicy;
	
	String cancellationPolicyXpath1 = "//*[@name = 'cancelRuleIdentifier' and @value = '";
	String cancellationPolicyXpath2 = "']";
	
	@FindBy(id = "currencyCode")
	private WebElement currency;
	
	@FindBy(id = "earlyPaymentDiscount")
	private WebElement earlyPaymentDiscount;
	
	@FindBy(xpath = "//*[@onclick = 'addContractRoomRateRow();ACES_Modified();' and @class = 'Aces_Btn' and @value = 'Add']")
	private WebElement roomRatesRow_AddBtn;
	
	@FindBy(name = "contractRoomRate[0].selectedDays")
	private List<WebElement> daysOfWeek;
	
	private String daysOfWeekXpath1 = "//*[@name = 'contractRoomRate[0].selectedDays' and @value = '";
	private String daysOfWeekXpath2 = "']";
	
	@FindBy(id = "startDate0")
	private WebElement roomRate_StartDate;
	
	@FindBy(id = "endDate0")
	private WebElement roomRate_EndDate;
	
	@FindBy(name = "contractRoomRate[0].roomRate")
	private WebElement roomRate;
	
	@FindBy(id = "roomType0")
	private WebElement roomType;
	
	@FindBy(name = "contractRoomRate[0].minRooms")
	private WebElement minRooms;
	
	@FindBy(name = "contractRoomRate[0].maxRooms")
	private WebElement maxRooms;
	
	@FindBy(xpath = "//*[@onclick = 'addContractMealRateRow();ACES_Modified();' and @class = 'Aces_Btn' and @value = 'Add']")
	private WebElement mealRatesRow_AddBtn;
	
	@FindBy(id = "mealStartDate0")
	private WebElement mealRate_StartDate;
	
	@FindBy(id = "mealEndDate0")
	private WebElement mealRate_EndDate;
		
	@FindBy(id = "mealRate0")
	private WebElement mealRate;
	
	@FindBy(id = "mealType0")
	private WebElement mealType;
	
	@FindBy(name = "Ok")
	private WebElement okBtn;
	
	@FindBy(xpath = "//*[@name = 'Cancel' and @onclick = 'childWindowExit()']")
	private WebElement cancelBtn;
	
	@FindBy(how = How.NAME, using = "cancelPolicy")
	private List<WebElement> cancelPolicyRadioBtn;
	
	String cancelPolicyRadioBtnXpath1 = "//*[@name = 'cancelPolicy' and @value = '";
	String cancelPolicyRadioBtnXpath2 = "']";
	
	@FindBy(how = How.ID, using = "timeOfDayDisplay")
	private WebElement timeOfDay;
	
	@FindBy(how = How.ID, using = "noOfDays")
	private WebElement daysPrior;
	
	@FindBy(how = How.ID, using = "hoursBefore")
	private WebElement hoursBeforePickup;
	
	@FindBy(xpath = "//*[@onclick = 'addContractRoomLimitsRow();ACES_Modified()' and @class = 'Aces_Btn']")
	private WebElement roomLimits_AddBtn;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn_Add' and @value = 'Delete']")
	private WebElement roomLimits_DeleteRowBtn;
	
	@FindBy(id = "contractRoomLimitsList[0].startDate")
	private WebElement roomLimitStartDate;
	
	@FindBy(id = "contractRoomLimitsList[0].endDate")
	private WebElement roomLimitEndDate;
	
	@FindBy(id = "contractRoomLimitsList[0].minRoomsLwm")
	private WebElement roomLimitMinRooms;
	
	@FindBy(id = "contractRoomLimitsList[0].maxRoomsLwm")
	private WebElement roomLimitMaxRooms;
	
	@FindBy(id = "contractRoomLimit0")
	private WebElement roomLimitDeleteRowCheckBox;
	
	private String roomRatesTableRowsXpath1 = "//*[@id = 'roomRateTable']/tbody/tr";
	private String roomRatesTableRowsXpath2 = "[";
	private String maxRoomsPerRow = "]/td[7]/input";
	@FindBy(id = "roomRateIndex")
	private WebElement noOfRoomRateTableRows ;
	
	@FindBy(name = "roomLimitIndex")
	private WebElement roomLimitIndex;
	
	private String roomLimitXpath = "contractRoomLimitsList[";
	private String roomLimit_startDate = "].startDate";
	private String roomLimit_endDate = "].endDate";
	private String roomLimit_minRooms = "].minRoomsLwm";
	private String roomLimit_maxRooms = "].maxRoomsLwm";
	
	@FindBy(id = "roomRateIndex")
	private WebElement roomRateIndex;
	
	@FindBy(id = "alertOK")
	private WebElement alert_okBtn;
	
	@FindBy(xpath = "//*[@id = 'shuttleOperateTimesTable']//tr[contains(@id, 'shuttleRow')]")
	private List<WebElement> shuttleOperatingTimesRows;
	
	private String shuttleTimes_daysOfWeekXpath1 = "//*[@name = 'shuttleOperateTime[";
	private String shuttleTimes_daysOfWeekXpath2 = "].selectedDays' and @value = '";
	private String shuttleTimes_daysOfWeekXpath3 = "']";
	
	private String shuttleTimes_Xpath = "shuttleOperateTime[";
	private String shuttleTimes_startTimeXpath = "].startTime";
	private String shuttleTimes_endTimeXpath = "].endTime";
	
	private String shuttleTimes_startDate = "shuttleStartDate";
	private String shuttleTimes_endDate = "shuttleEndDate";
	
	@FindBy(name = "shuttleOperateTimeIndex")
	private WebElement shuttleOperatingTimeIndex;
	
	@FindBy(xpath = "//*[@onclick = 'addShuttleOperateTimesRow();ACES_Modified();' and @class = 'Aces_Btn']")
	private WebElement addShuttleOperatingTimesRowBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_HotelSupplier_ContractDetails(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setPrimaryHotelRulesAndRates()throws Exception{
		System.out.println(driver.getTitle());
		clickOn(plannedRoomsNotCancellableIndicator);
		assertTrue(plannedRoomsNotCancellableIndicator.isSelected(), "Planned Rooms Not Cancellable indicator is not selected");
		clickOn(transportationByHotelIndicator);
		assertTrue(transportationByHotelIndicator.isSelected(), "transportation By Hotel Indicator is not selected");
		
	}
	
	public void editPrimaryHotelRulesAndRates()throws Exception{
		clickOn(plannedRoomsNotCancellableIndicator);
		clickOn(transportationByHotelIndicator);
	}
	
	public void verifyPrimaryHotelRulesAndRates()throws Exception{
		System.out.println(driver.getTitle());
		assertTrue(plannedRoomsNotCancellableIndicator.isSelected(), "plannedRoomsNotCancellableIndicator is not selected");
		assertTrue(transportationByHotelIndicator.isSelected(), "transportation By Hotel Indicator is not selected");
		
	}
	
	public void setRoomUseRules(String roomRule)throws Exception{
		waitForElementVisibility(this.roomRule);
		waitForElementVisibility(this.checkIn);
		waitForElementVisibility(this.checkOut);
		if(roomRule.equals("Hours Based"))
			waitForElementVisibility(this.hotelStayHours);
		else if(roomRule.equals("Days Plus Grace")){
			waitForElementVisibility(gracePeriod);
			waitForElementVisibility(graceOnFirstDayOnlyIndicator);
		}
	}
	
	public void verifyRoomUseRules(String roomRule)throws Exception{
		Select roomRuleDD = new Select(this.roomRule);
		assertEquals(roomRuleDD.getFirstSelectedOption().getAttribute("value"), roomRule, roomRule + " is not selected");
	}
	
	
	public void setCancellationPolicy(String cancelRuleValue, String cancelTimeValue) throws Exception{ 
		String cancelPolicy = "";
		for(WebElement canPolicy: cancellationPolicy)
			if(canPolicy.getAttribute("value").equals(cancelRuleValue)){
				canPolicy.click();
				cancelPolicy = canPolicy.getText();
				assertTrue(canPolicy.isSelected(), canPolicy.getAttribute("value")+"is not selected");
				break;
			}
		if(cancelPolicy.contains("Cancel Policy")){
			for(WebElement cancelTimeDetail : cancelPolicyRadioBtn)
				if(cancelTimeDetail.getAttribute("value").equals(cancelTimeValue)){
					clickOn(cancelTimeDetail);
					assertTrue(cancelTimeDetail.isSelected(), "radio button not selected");
					waitForElementVisibility(timeOfDay);
					waitForElementVisibility(daysPrior);
					waitForElementVisibility(hoursBeforePickup);
				}
		}
	}
	
	public void verifyCancellationPolicy(String cancelRuleValue, String cancelTimeValue) throws Exception{
		WebElement canPolicy = findElementByXpath(cancellationPolicyXpath1+cancelRuleValue+cancellationPolicyXpath2);
		String cancelPolicy = canPolicy.getAttribute("value");
		assertTrue(canPolicy.isSelected(), cancelPolicy+"is not selected");
		if(cancelPolicy.contains("Cancel Policy")){
			WebElement cancelPolicyRadioBtn = findElementByXpath(cancelPolicyRadioBtnXpath1+cancelTimeValue+cancelPolicyRadioBtnXpath2);
			assertTrue(cancelPolicyRadioBtn.isSelected(), cancelPolicyRadioBtn.getAttribute("value")+" is not selected");
			
		}
	}
	

	public void setRoomRates(String currency, String daysOfWeek)throws Exception{
		selectByVisibleText(this.currency, currency);
		waitForElementVisibility(this.earlyPaymentDiscount);
		clickOn(roomRatesRow_AddBtn);
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		waitForElementVisibility(roomRate_StartDate);
		waitForElementVisibility(roomRate_EndDate);
		waitForElementVisibility(this.roomType);
		waitForElementVisibility(this.roomRate);
		waitForElementVisibility(this.minRooms);
		waitForElementVisibility(this.maxRooms);
		
	}
	
	public void setRoomRates(String currency, String daysOfWeek, String roomRateStartDate, String roomRateEndDate, String roomType, String roomRate, String minRooms)throws Exception{
		selectByVisibleText(this.currency, currency);
		waitForElementVisibility(this.earlyPaymentDiscount);
		clickOn(roomRatesRow_AddBtn);
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		typeInText(roomRate_StartDate, roomRateStartDate);
		typeInText(roomRate_EndDate, roomRateEndDate);
		selectByVisibleText(this.roomType, roomType);
		typeInText(this.roomRate, roomRate);
		typeInText(this.minRooms, minRooms);
	}
	
	public void editRoomRates(String currency, String daysOfWeek, String roomRateStartDate, String roomRateEndDate, String roomType, String roomRate, String minRooms)throws Exception{
		selectByVisibleText(this.currency, currency);
		waitForElementVisibility(this.earlyPaymentDiscount);
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		typeInText(roomRate_StartDate, roomRateStartDate);
		typeInText(roomRate_EndDate, roomRateEndDate);
		selectByVisibleText(this.roomType, roomType);
		typeInText(this.roomRate, roomRate);
		typeInText(this.minRooms, minRooms);
	}
	
	public void editRoomRates(String currency, String daysOfWeek, String roomRateStartDate, String roomRateEndDate, String roomType, String roomRate, String minRooms, String maxRooms)throws Exception{
		if(Integer.parseInt(roomRateIndex.getAttribute("value"))<=0)
			clickOn(roomRatesRow_AddBtn);
		selectByVisibleText(this.currency, currency);
		waitForElementVisibility(this.earlyPaymentDiscount);
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				if(!findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2).isSelected())
					clickOn(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		typeInText(roomRate_StartDate, roomRateStartDate);
		typeInText(roomRate_EndDate, roomRateEndDate);
		selectByVisibleText(this.roomType, roomType);
		typeInText(this.roomRate, roomRate);
		try{clickOn(alert_okBtn);}catch(Exception e){}
		typeInText(this.minRooms, minRooms);
		typeInText(this.maxRooms, maxRooms);
	}
	
	public void verifyRoomRates(String currency, String roomRatesDaysOfWeek, String roomRateStartDate, String roomRateEndDate, String roomType, String roomRate, String minRooms) throws Exception{
		Select currencyDD = new Select(this.currency);
		assertEquals(currencyDD.getFirstSelectedOption().getAttribute("value"), currency, currency +" is not selected");
		Arrays.asList(roomRatesDaysOfWeek.split(",")).forEach(day -> {
			try {
				assertTrue(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2).isSelected(), day+ " is not selected");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		assertEquals(roomRate_StartDate.getAttribute("value"), roomRateStartDate, "RoomRate_StartDate Mismatch!");
		assertEquals(roomRate_EndDate.getAttribute("value"), roomRateEndDate, "RoomRate_EndDate Mismatch!");
		Select roomTypeDD = new Select(this.roomType);
		assertEquals(roomTypeDD.getFirstSelectedOption().getText(), roomType, "RoomType Mismatch!");
		assertEquals(this.roomRate.getAttribute("value"), roomRate, "RoomRate Mismatch!");
		assertEquals(this.minRooms.getAttribute("value"), minRooms, "MinRooms Mismatch!");
	}
	
	public void setMealRates(String mealType)throws Exception{
		clickOn(mealRatesRow_AddBtn);
		waitForElementVisibility(mealRate_StartDate);
		waitForElementVisibility(mealRate_EndDate);
		selectByVisibleText(this.mealType, mealType);
		waitForElementVisibility(this.mealRate);
	}
	
	public void setMealRates(String mealType, String mealRate)throws Exception{
		clickOn(mealRatesRow_AddBtn);
		waitForElementVisibility(mealRate_StartDate);
		waitForElementVisibility(mealRate_EndDate);
		selectByVisibleText(this.mealType, mealType);
		typeInText(this.mealRate, mealRate);
	}
	
	public void editMealRates(String mealRateStartDate, String mealRateEndDate, String mealType, String mealRate)throws Exception{
		typeInText(mealRate_StartDate, mealRateStartDate);
		typeInText(mealRate_EndDate, mealRateEndDate);
		selectByVisibleText(this.mealType, mealType);
		typeInText(this.mealRate, mealRate);
	}
	
	public void setMealRates(String mealRateStartDate, String mealRateEndDate, String mealType, String mealRate)throws Exception{
		clickOn(mealRatesRow_AddBtn);
		typeInText(mealRate_StartDate, mealRateStartDate);
		typeInText(mealRate_EndDate, mealRateEndDate);
		selectByVisibleText(this.mealType, mealType);
		typeInText(this.mealRate, mealRate);
	}
	
	public void verifyMealRates(String mealRateStartDate, String mealRateEndDate, String mealType, String mealRate) throws Exception{
		assertEquals(mealRate_StartDate.getAttribute("value"), mealRateStartDate, "mealRateStartDate Mismatch!");
		assertEquals(mealRate_EndDate.getAttribute("value"), mealRateEndDate, "mealRateEndDate Mismatch!");
		Select mealTypeDD = new Select(this.mealType);
		assertEquals(mealTypeDD.getFirstSelectedOption().getText(), mealType, mealType+" is not selected");
		assertEquals(this.mealRate.getAttribute("value"), mealRate, "mealRate Mismatch!");
	}
	
	public void setRoomLimits() throws Exception{
		clickOn(roomLimits_AddBtn);
		waitForElementVisibility(roomLimits_DeleteRowBtn);
		waitForElementVisibility(roomLimitStartDate);
		waitForElementVisibility(roomLimitEndDate);
		waitForElementVisibility(roomLimitMinRooms);
		waitForElementVisibility(roomLimitMaxRooms);
		waitForElementVisibility(roomLimitDeleteRowCheckBox);
	}
	
	
	public void setRoomLimits(String roomLimitStartDate, String roomLimitEndDate, String roomLimitMinRooms, String roomLimitMaxRooms) throws Exception{
		int roomLimitIndex = Integer.parseInt(this.roomLimitIndex.getAttribute("value"));
		if(roomLimitIndex == 999){
			clickOn(roomLimits_AddBtn);
			roomLimitIndex = Integer.parseInt(this.roomLimitIndex.getAttribute("value"));
		}
		typeInText(findElementById(roomLimitXpath+(roomLimitIndex)+roomLimit_startDate), roomLimitStartDate);
		typeInText(findElementById(roomLimitXpath+(roomLimitIndex)+roomLimit_endDate), roomLimitEndDate);
		typeInText(findElementByName(roomLimitXpath+(roomLimitIndex)+roomLimit_minRooms), roomLimitMinRooms);
		typeInText(findElementByName(roomLimitXpath+(roomLimitIndex)+roomLimit_maxRooms), roomLimitMaxRooms);
	}
	
	//Following method used to click on OK button on the Contract Details page
	//and save the details entered related to the Contract
	//On clicking ok, the page closes and control goes back to the parent window
	//ie., Hotel/GT supplier screen
	public void saveDetails()throws Exception{
		/*clickOn(okBtn);
		waitForPageToLoad("3");
		switchToWindow();*/
		waitForElementVisibility(okBtn);
	}
	
	public void clickSaveDetails()throws Exception{
		Thread.sleep(10000);
		clickOn(okBtn);
		//waitForPageToLoad("3");
		switchToWindow();
		//waitForElementVisibility(okBtn);
	}
	
	public void cancelDetails()throws Exception{
		waitForElementVisibility(cancelBtn);
		driver.close();
		switchToWindow();
	}
	
	public void changeMaxRooms(String maxRooms) throws Exception{
		//logger.info("*** ContractDetailsPage -> changeMaxRooms method started ***");
		System.out.println(driver.getTitle());
		int noOfRowsInRoomRatesTable = findElementsByXpath(roomRatesTableRowsXpath1).size();
		System.out.println(findElementsByXpath(roomRatesTableRowsXpath1).size());
		WebElement maxRoomsField = findElementByXpath(roomRatesTableRowsXpath1+roomRatesTableRowsXpath2+noOfRowsInRoomRatesTable+maxRoomsPerRow);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", maxRoomsField);
		typeInText(maxRoomsField, maxRooms);
		//logger.info("*** changeMaxRooms method completed ***");
	}
	
	public void setShuttleOperatingTimes(String daysOfWeek, String startDate, String endDate, String startTime, String endTime) throws Exception{
		
		int count = findElementsByName("shuttleOperateTimeIndex").size();
		if(count>0){
		int shuttleOperatingTimesIndex = Integer.parseInt(shuttleOperatingTimeIndex.getAttribute("value"));
		if(shuttleOperatingTimesIndex<=0){
			clickOn(addShuttleOperatingTimesRowBtn);
			Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
				try {
					if(!findElementByXpath(shuttleTimes_daysOfWeekXpath1+(shuttleOperatingTimesIndex)+shuttleTimes_daysOfWeekXpath2+day+shuttleTimes_daysOfWeekXpath3).isSelected())
						clickOn(findElementByXpath(shuttleTimes_daysOfWeekXpath1+(shuttleOperatingTimesIndex)+shuttleTimes_daysOfWeekXpath2+day+shuttleTimes_daysOfWeekXpath3));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			typeInText(findElementById(shuttleTimes_startDate+(shuttleOperatingTimesIndex)), startDate);
			typeInText(findElementById(shuttleTimes_endDate+(shuttleOperatingTimesIndex)), endDate);
			typeInText(findElementById(shuttleTimes_Xpath+(shuttleOperatingTimesIndex)+shuttleTimes_startTimeXpath), startTime);
			typeInText(findElementById(shuttleTimes_Xpath+(shuttleOperatingTimesIndex)+shuttleTimes_endTimeXpath), endTime);
		}else{
			Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
				try {
					if(!findElementByXpath(shuttleTimes_daysOfWeekXpath1+(shuttleOperatingTimesIndex)+shuttleTimes_daysOfWeekXpath2+day+shuttleTimes_daysOfWeekXpath3).isSelected())
						clickOn(findElementByXpath(shuttleTimes_daysOfWeekXpath1+(shuttleOperatingTimesIndex)+shuttleTimes_daysOfWeekXpath2+day+shuttleTimes_daysOfWeekXpath3));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			typeInText(findElementById(shuttleTimes_startDate+(shuttleOperatingTimesIndex-1)), startDate);
			typeInText(findElementById(shuttleTimes_endDate+(shuttleOperatingTimesIndex-1)), endDate);
			typeInText(findElementById(shuttleTimes_Xpath+(shuttleOperatingTimesIndex-1)+shuttleTimes_startTimeXpath), startTime);
			typeInText(findElementById(shuttleTimes_Xpath+(shuttleOperatingTimesIndex-1)+shuttleTimes_endTimeXpath), endTime);
		}
		
		}
	}
	
	public void selectHotelProvidesTransportation(){
		if(transportationByHotelIndicator.isSelected()==false)
		clickOn(transportationByHotelIndicator);
	}
	
	public void setRoomLimit(String roomLimitIndex, String roomLimitStartDate, String roomLimitEndDate, String roomLimitMaxRooms) throws Exception{
		clickOn(roomLimits_AddBtn);
		typeInText(findElementById(roomLimitXpath+roomLimitIndex+roomLimit_startDate), roomLimitStartDate);
		typeInText(findElementById(roomLimitXpath+roomLimitIndex+roomLimit_endDate), roomLimitEndDate);
		//typeInText(findElementByName(roomLimitXpath+(roomLimitIndex)+roomLimit_minRooms), roomLimitMinRooms);
		typeInText(findElementByName(roomLimitXpath+roomLimitIndex+roomLimit_maxRooms), roomLimitMaxRooms);
		clickSaveDetails();
	}
	

	public void deselectHotelProvidesTransportation(){
		if(transportationByHotelIndicator.isSelected())
		clickOn(transportationByHotelIndicator);
	}
	
}
