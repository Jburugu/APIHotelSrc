package com.APIHotels.pages.ACESII;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_HotelSchedules extends BasePage{
	
	@FindBy(id = "cityCode")
	private WebElement city;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Find Hotel']")
	private WebElement findHotelBtn;
	
	@FindBy(id = "supplierId")
	private WebElement hotel;
	
	@FindBy(id = "startDate")
	private WebElement startDate;
	
	@FindBy(id = "endDate")
	private WebElement endDate;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id='inc1']/div/form/div[1]")
	private WebElement noOfScheduledReservResultMessage;
	
	@FindBy(xpath = "//*[@id='inc2']/div/div[1]")
	private WebElement noOfCancelledReservResultMessage;
	
	@FindBy(xpath = "//*[@id = 'inc2' and @style = 'clear: both; border-top: 1px solid rgb(34, 52, 59); display: block;']")
	private WebElement cancelledReservDiv;
	
	@FindBy(xpath = "//*[@id = 'scheduledTable']/tbody/tr")
	private List<WebElement> scheduledReservationsTableRows;
	
	@FindBy(xpath = "//*[@id = 'cancelledTable']/tbody/tr")
	private List<WebElement> cancelledReservationsTableRows;
	
	@FindBy(id = "row2")
	private WebElement cancelledSectionHeader;
	
	@FindBy(id = "row1")
	private WebElement scheduledSectionHeader;
	
	@FindBy(css = "#opsHotelSchedules > a")
	private WebElement hotelSchedulesLink;
	
	String scheduledReservations= "//*[@id='scheduledTable']//tbody//tr";		
	String reservationStatusXpath1="//*[@id='scheduledTable']//tbody//tr[";		
	String reservationStatusXpath2="]//td[16]";		
	String reservationCheckboxXpath1="//*[@id='scheduledTable']//tbody/tr[";		
	String reservationCheckboxXpath2="]/td";		
			
	@FindBy(xpath="//input[@value='Bulk Re-assign']")		
	private WebElement bulkReassignButton;		
			
	@FindBy(xpath="//*[@id='tripProvider']//tbody//tr[1]//td[1]")		
	private WebElement selectRadioButton;		
			
	@FindBy(xpath="//*[@id='tripProvider']//tbody//tr[1]//td[9]/select")		
	private WebElement gtProviderToHotel;		
			
	@FindBy(xpath="//*[@id='tripProvider']//tbody//tr[1]//td[10]/select")		
	private WebElement gtProviderFromHotel;		
			
	@FindBy(xpath="//*[contains(text(),'Next')]")		
	private WebElement nextButton;		
			
	@FindBy(name="hotelConfirmationNumber")		
	private WebElement hotelConfirmationNumber;		
	/*@FindBy(id="finishButton")		
	private WebElement finishButton;*/		
			
	String finishButton="finishButton";

	String genericXpath="//*[contains(text(),'";
	String gtProviderToHotelXpath="')]/../td[9]/select";
	String gtProviderFromHotelXpath="')]/../td[10]/select";
	String checkboxXpath="')]/../preceding-sibling::td/input";
	String selectRadioBtn="')]/../td[1]/input";
	
	@FindBy(id="rateId0")
	private WebElement roomRate_hotel;
	
	@FindBy(id="hotelTextAreaId")
	private WebElement notes_hotel;
	
	@FindBy(id="hotelVendorTextAreaId")
	private WebElement commentsForVendorNotes_hotel;
	
	String gtConfirmationCode = "GTConfirmCode";
	
	String roomRate_gt = "gtResvRate";
	
	String notes_gt = "gtTextAreaId";
	
	String commentsForVendorNotes_gt = "gtVendorTextAreaId";
	
	String gtTab="tab";
	
	@FindBy(id="tab0")
	private WebElement hotelTab;
	
	@FindBy(xpath="//*[@id='reAssignResvCount']//following-sibling::div")
	private List<WebElement> tabsCount;
	
	@FindBy(name="vendorContactForm[0].creditCardDetails.paymentMethod")
	private WebElement billingMethodDropdown;
	
	@FindBy(id="vendorContactForm[0].apiCardNumber")
	private WebElement apiCardNumber;
	
	@FindBy(id="vendorContactForm[0].airlineCardNumber")
	private WebElement airlineCardNumber;
	
	@FindBy(id="vendorContactForm[0].cardNo")
	private WebElement airlineCardNo;
	
	@FindBy(id="vendorContactForm[0].synapticCardNumber")
	private WebElement synapticCardNumber;
	
	@FindBy(id="vendorContactForm[0].synapticCSVNumber")
	private WebElement synapticCSVNumber;
	
	@FindBy(id="vendorContactForm[0].synapticCardExpirationDate")
	private WebElement synapticCardExpirationDate;
	
	@FindBy(id="vendorContactForm[0].synapticMerchantLog")
	private WebElement synapticMerchantLog;
	
	@FindBy(name="vendorContactForm[1].creditCardDetails.paymentMethod")
	private WebElement billingMethodDropdown_GT;
	
	@FindBy(name="vendorContactForm[2].creditCardDetails.paymentMethod")
	private WebElement billingMethodDropdown_GT2;
	
	@FindBy(id="vendorContactForm[1].apiCardNumber")
	private WebElement apiCardNumber_GT;
	
	@FindBy(id="vendorContactForm[1].airlineCardNumber")
	private WebElement airlineCardNumber_GT;
	
	@FindBy(id="vendorContactForm[1].cardNo")
	private WebElement airlineCardNo_GT;
	
	@FindBy(id="vendorContactForm[1].synapticCardNumber")
	private WebElement synapticCardNumber_GT;
	
	@FindBy(id="vendorContactForm[1].synapticCSVNumber")
	private WebElement synapticCSVNumber_GT;
	
	@FindBy(id="vendorContactForm[1].synapticCardExpirationDate")
	private WebElement synapticCardExpirationDate_GT;
	
	@FindBy(id="vendorContactForm[1].synapticMerchantLog")
	private WebElement synapticMerchantLog_GT;
	
	@FindBy(id="AlertBox")
	private WebElement alertBox; 
	
	@FindBy(id="alertOK")
	private WebElement alertOK;
	
	@FindBy(xpath="//*[contains(text(),'Bulk Re-assign Summary')]")
	private WebElement bulkReassignSummaryText;
	
	@FindBy(id="GTConfirmCode0")
	private WebElement gtCancellationNumber;
	
	@FindBy(id="gtCancellationTextAreaId0")
	private WebElement gtCancellationTextAreaId0;
	
	@FindBy(id="gtCancellationCommentTextAreaId0")
	private WebElement gtCancellationCommentsForVendor;
	
	@FindBy(name="hotelcancellationNumber")
	private WebElement htCancellationNumber;
	
	@FindBy(id="hotelTextCancellationAreaId")
	private WebElement htCancellationNotes;
	
	@FindBy(id="hotelCommentsTextCancellationAreaId")
	private WebElement htCancellationCommentsForVendor;
	
	@FindBy(id="tabCancel1")
	private WebElement gttabCancel;
	
	String gtCancellationNum = "vendorContactCancellationForm[1].conformation";
	String gtCancellationNote = "gtCancellationTextAreaId";
	String gtCancellationCommentForVendor = "gtCancellationCommentTextAreaId";
	
	String tripXpath1 = "//*[@id='scheduledTable']//tbody//tr/td/div[contains(text(),'";
	String tripXpath2 = "')]";
	String timeAtHotelXpath = "')]/../parent::tr/td[9]";
	
	public EventFiringWebDriver driver=null;

	public Page_HotelSchedules(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickHotelSchedulesLink()throws Exception{
		clickOn(hotelSchedulesLink, "Schedules Link -> Hotel Schedules Link");
	}
	
	public void findHotelSchedules(String city, String hotel, String startDate, String endDate)throws Exception{
		typeInText(this.city, city);
		clickOn(findHotelBtn);
		selectByVisibleText(this.hotel, hotel);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		clickOn(searchBtn);
		int results = Integer.parseInt(noOfScheduledReservResultMessage.getText().trim().split(" ")[0]);
		System.out.println("No of Scheduled Reservations found: "+results);
		clickOn(cancelledSectionHeader);
		try{Thread.sleep(3000);}catch(Exception e){}
		System.out.println("No of Cancelled Reservations found: "+cancelledReservationsTableRows.size());
	}

	
	public void findHotelSchedules(String city, String hotel)throws Exception{
		typeInText(this.city, city);
		clickOn(findHotelBtn);
		selectByVisibleText(this.hotel, hotel);
		typeInText(startDate, currentMonthStartDate());
		typeInText(endDate, currentMonthEndDate());
		clickOn(searchBtn);
		int results = Integer.parseInt(noOfScheduledReservResultMessage.getText().trim().split(" ")[0]);
		System.out.println("No of Scheduled Reservations found: "+results);
		clickOn(cancelledSectionHeader);
		try{Thread.sleep(3000);}catch(Exception e){}
		System.out.println("No of Cancelled Reservations found: "+cancelledReservationsTableRows.size());
	}
	
	public void  findSchduledHotels(String city, String hotel, String startDate, String endDate){		
		typeInText(this.city, city, "Hotel Schedules Page -> City");		
		clickOn(findHotelBtn, "Hotel Schedules Page -> Find Hotel Button");		
		selectByVisibleText(this.hotel, hotel,"Hotel Schedules Page -> Hotel Name Dropdown");		
		typeInText(this.startDate, startDate, "Hotel Schedules Page -> Startdate");		
		typeInText(this.endDate, endDate, "Hotel Schedules Page-> Enddate");		
		clickOn(searchBtn, "Hotel Schedules Page -> Search Button");		
		int results = Integer.parseInt(noOfScheduledReservResultMessage.getText().trim().split(" ")[0]);		
		if(results==0){		
			Assert.fail("No scheduled reservations found");		
		}		
}		
		
	public void verifyReservationStatus(String reqStatus){		
		/*List<WebElement> reservations=findElementsByXpath(scheduledReservations);		
		int reservationCount=reservations.size();*/		
		for(int rowCount=1; rowCount<=3;rowCount++){		
			WebElement status=findElementByXpath(reservationStatusXpath1+rowCount+reservationStatusXpath2);		
			String scheduledStatus=status.getText();		
			if(scheduledStatus.equalsIgnoreCase(reqStatus)){		
				WebElement checkBox=findElementByXpath(reservationCheckboxXpath1+rowCount+reservationCheckboxXpath2);		
				clickOn(checkBox);		
			}		
					
		}		
		clickOn(bulkReassignButton);		
	}		
			
	public void bulkReassignReservations(){		
		clickOn(selectRadioButton);		
		selectByIndex(gtProviderToHotel, 1);		
		selectByIndex(gtProviderFromHotel, 1);		
		clickOn(nextButton);		
	}		
			
	public void enterConfirmationNumber(String confirmationValue){		
		typeInText(hotelConfirmationNumber, confirmationValue);		
	}		
			
	public void clickOnFinish(){		
		List<WebElement> finish= findElementsById(finishButton);		
		WebElement finishValue=finish.get(1);		
		clickOn(finishValue);		
				
				
	}
	
	public void clickOnBulkReassignBtn(){
		clickOn(bulkReassignButton);
	}
	
	public void selectTrips(String tripIds){
		List<String> trips= new ArrayList<String>(Arrays.asList(tripIds.split(",")));
		System.out.println();
		for(int tripCount=0; tripCount<trips.size(); tripCount++){
			List<WebElement> sameTrips = Arrays.asList(findElementByXpath(genericXpath+trips.get(tripCount)+checkboxXpath));
			if(sameTrips.size()==1)
				clickOn(findElementByXpath(genericXpath+trips.get(tripCount)+checkboxXpath), "Hotel Schedules Page-> Trip Checkbox");
			else clickOn(sameTrips.get(tripCount));
		}
	}
	
	public void selectGTProvider2ToHotel(String hotelName, String toGTProvider){
		selectByVisibleText(findElementByXpath(genericXpath+hotelName+gtProviderToHotelXpath), toGTProvider, "Bulk Reassign Page -> GT Provider - To Hotel");		
	}
	
	public void selectGTProvider2FromHotel(String hotelName, String fromGTProvider){
		selectByVisibleText(findElementByXpath(genericXpath+hotelName+gtProviderFromHotelXpath), fromGTProvider, "Bulk Reassign Page -> GT Provider - From Hotel");
	}
	
	public void enterDetailsForHotel(String confirmationValue, String roomRate, String notes, String vendoreNotes){
		clickOn(hotelTab, "Bulk Reassign Page -> Hotel Tab");
		typeInText(hotelConfirmationNumber, confirmationValue, "Bulk Reassign Page -> Hotel Confirmation Number");
		typeInText(notes_hotel, notes, "Bulk Reassign Page -> Hotel Notes");
		typeInText(commentsForVendorNotes_hotel, vendoreNotes, "Bulk Reassign Page -> Hotel Vendor Notes");
		typeInText(roomRate_hotel, roomRate, "Bulk Reassign Page ->Room Rate");
	}
	
	public void enterDetailsForGT(String confirmationValue, String gtRoomRate, String gtNotes, String gtvendoreNotes){
		int tabSize = tabsCount.size();
		for(int i =1; i< tabSize; i++){
			clickOn(findElementById(gtTab+i), "Bulk Reassign Page -> Gt Supplier Tab");
			typeInText(findElementById(gtConfirmationCode+i), confirmationValue, "Bulk Reassign Page -> gt Confirmation value");
			typeInText(findElementById(roomRate_gt+i), gtRoomRate,"Bulk Reassign Page -> gt room rate");
			typeInText(findElementById(notes_gt+i), gtNotes, "Bulk Reassign Page -> gt Notes");
			typeInText(findElementById(commentsForVendorNotes_gt+i), gtvendoreNotes, "Bulk Reassign Page -> gt Vendor Notes");
		}
	}
	
	public void selectRadioBtn(String hotelName){
		clickOn(findElementByXpath(genericXpath+hotelName+selectRadioBtn),"Bulk Reassign Page -> Select Radio Button" );
	}
	
	public void clickOnNextBtn(){
		clickOn(nextButton, "Bulk Reassign Page -> Next Button");
	}
	
	
	public void verifyBulkReassignSummary(){
		if(!bulkReassignSummaryText.isDisplayed())
			Assert.fail("Bulk Reassign Summary Page is not displayed");
	}
	
	public void selectPaymentMethod(String billingMethodValue){
		selectByVisibleText(billingMethodDropdown, billingMethodValue, "Bulk Reassign Page -> Billing Method DropDown");
	}
	
	public void selectPaymentMethod_GT(String billingMethodValue){
		selectByVisibleText(billingMethodDropdown_GT, billingMethodValue, "Bulk Reassign Page -> GT Billing Method DropDown");
	}
	
	public void selectPaymentMethod_GT2(String billingMethodValue){
		selectByVisibleText(billingMethodDropdown_GT2, billingMethodValue, "Bulk Reassign Page -> GT Billing Method DropDown");
	}
	
	public void apicreditCard() throws IOException{
		checkForAlertBox();
		selectByIndex(apiCardNumber, 1, "Bulk Reassign Page -> api Card Number Dropdown");
	}	
	
	public void airlineCreditCard(String airlineCardNum){
		checkForAlertBox();
		selectByIndex(airlineCardNumber, 1, "Bulk Reassign Page ->Airlines Card Number Dropdown");
		if(airlineCardNumber.getText().contains("Other"))
		typeInText(airlineCardNo, airlineCardNum, "Bulk Reassign Page -> Airlines Card Number");	
	}
	
	public void synapticCard(String synapticCardNum, String synapticCSVNum, String synapticCardExpiryDate,
			String synapticMerchantLogValue){	
		checkForAlertBox();
		typeInText(synapticCardNumber, synapticCardNum, "Bulk Reassign Page -> Credit Card Number");
		typeInText(synapticCSVNumber, synapticCSVNum, "Bulk Reassign Page -> CSV Number");
		typeInText(synapticCardExpirationDate, synapticCardExpiryDate, "Bulk Reassign Page -> Expiration Date");
		typeInText(synapticMerchantLog, synapticMerchantLogValue, "Bulk Reassign Page -> Merchant Log");
	}
	
	
	public void checkForAlertBox(){
		while(alertBox.isDisplayed()){
			clickOn(alertOK, "Alert -> Ok button");
		}
	}
	
	public void apicreditCard_GT() throws IOException{
		checkForAlertBox();
		selectByIndex(apiCardNumber_GT, 1, "Bulk Reassign Page -> GT API Card Number Dropdown");
	}	
	
	public void airlineCreditCard_GT(String airlineCardNum){
		checkForAlertBox();
		selectByIndex(airlineCardNumber_GT, 1, "Bulk Reassign Page -> GT Airlines Card Number Dropdown");
		if(airlineCardNumber_GT.getText().contains("Other"))
		typeInText(airlineCardNo_GT, airlineCardNum, "Bulk Reassign Page -> GT Airlines Card Number");	
	}
	
	public void synapticCard_GT(String synapticCardNum, String synapticCSVNum, String synapticCardExpiryDate,
			String synapticMerchantLogValue){	
		checkForAlertBox();
		typeInText(synapticCardNumber_GT, synapticCardNum, "Bulk Reassign Page -> Credit Card Number");
		typeInText(synapticCSVNumber_GT, synapticCSVNum, "Bulk Reassign Page -> CSV Number");
		typeInText(synapticCardExpirationDate_GT, synapticCardExpiryDate, "Bulk Reassign Page -> Expiration Date");
		typeInText(synapticMerchantLog_GT, synapticMerchantLogValue, "Bulk Reassign Page -> Merchant Log");
	}	
	
	public void gtBulkCancellation(String tabCount, String gtCancellationNumber, String gtCancellationNotes, String gtCancellationCommentsForVendor){
		typeInText(findElementByName(gtCancellationNum), gtCancellationNumber, "Bulk Reassign Page -> Cancellation Number");
		typeInText(findElementById(gtCancellationNote+tabCount), gtCancellationNotes, "Bulk Reassign Page -> Notes");
		typeInText(findElementById(gtCancellationCommentForVendor+tabCount), gtCancellationCommentsForVendor, "Bulk Reassign Page -> Comments For Vendor Notes");
	}
	
	public void hotelBulkCancellation(String hotelCancellationNumber, String hotelCancellationNotes, String hotelCancellationCommentsForVendor){
		typeInText(htCancellationNumber, hotelCancellationNumber, "Bulk Reassign Page -> Hotel Cancellation Number");
		typeInText(htCancellationNotes, hotelCancellationNotes, "Bulk Reassign Page -> Hotel API Notes");
		typeInText(htCancellationCommentsForVendor, hotelCancellationCommentsForVendor, "Bulk Reassign Page -> Hotel Comments For Vendor Notes");
	}
	
	public void clickOnGTTabCancel(){
		clickOn(gttabCancel, "Bulk Reassign Page -> Gt Cancel Tab");
	}
	
	public void verifyTimeAtHotel(String reservationIds, String timeAtHotel) {
		List<String> tripIds = Arrays.asList(reservationIds.split(","));
		List<String> hotelTime = Arrays.asList(timeAtHotel.split(","));
		List<String> tripsClubbed = new ArrayList<String>();
		List<String> tripsNotClubbed = new ArrayList<String>();
		for (int rowCount = 0; rowCount < tripIds.size(); rowCount++) {
			List<WebElement> foundReservation = Arrays
					.asList(findElementByXpath(tripXpath1 + tripIds.get(rowCount) + tripXpath2));
			if (foundReservation.size() == 1) {
				String time = findElementByXpath(tripXpath1 + tripIds.get(rowCount) + timeAtHotelXpath).getText();
				if (time.equalsIgnoreCase(hotelTime.get(rowCount).trim())) {
					if (hotelTime.get(0).equalsIgnoreCase(hotelTime.get(rowCount))) {
						tripsClubbed.add(tripIds.get(rowCount));
						System.out.println(tripsClubbed);
					}

					else {
						tripsNotClubbed.add(tripIds.get(rowCount));
					}
				} else
					Assert.fail("More than One reservation found with same trip number");
			}
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Trips Clubbed together are" + tripsClubbed);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Trips Not Clubbed together are" + tripsNotClubbed);
	}
	
	
}


