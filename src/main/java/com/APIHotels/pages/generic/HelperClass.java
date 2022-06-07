package com.APIHotels.pages.generic;

import com.APIHotels.framework.LocalDriverManager;

public class HelperClass extends LocalDriverManager{

	//public EventFiringWebDriver driver = null;
	PgWrapper pgWrapper;
	String reservId;
	String conferenceReservationId;

	/*public HelperClass(EventFiringWebDriver driver) {
		this.driver = driver;
	}*/

	/**
	 * @param destinationValue
	 * @param timeFormatValue
	 * @param arrivaltimeValue
	 * @param departuretimeValue
	 * @param arrivalFlightCodeValue
	 * @param arrivalFlightNumberValue
	 * @param departureFlightCodeValue
	 * @param departureFlightNumberValue
	 * @param additionalEmailAddressValue
	 * @param hotelValue
	 * @param accountTypeValue
	 * @param singleRoomType
	 * @param doubleRoomType
	 * @param hotelSupDescValue
	 * 
	 *            This method Creates a request reservation under BT / Manual
	 *            Booking or By training reservation(Training Reservation -
	 *            Request Reservation)in Airline site
	 */
	public String createRequestReservation_Operations(String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotelValue);// Other
		pgWrapper.requestReservationPage.accountType(accountTypeValue);// 2
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);// SINGLE
		if(hotelValue.equals("Other"))
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
		pgWrapper.requestReservationPage.clickOnSendToAPI();
		return reservId = pgWrapper.requestReservationPage.processingRequest();
	}

	/**
	 * @param reservId
	 * @param expectedResult
	 *            This method clicks on Find reservation link in
	 *            BT/ManualBooking under Operations Tab in AIRLINE SITE and
	 *            verifies whether the reservation exists
	 */
	public void findReservationAndVerifyStatus_Operations(String reservId, String expectedResult) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		findReservation_Operations(reservId);
		//pgWrapper.findReservationBTPage.verifyReservationExists(reservId, expectedResult);
		pgWrapper.findReservationBTPage.verifyStatus(expectedResult);
	}
	
	
	
	/**
	 * @param reservId
	 * 
	 * 		 This method clicks on Find reservation link in
	 *       BT/ManualBooking under Operations Tab in AIRLINE SITE 
	 */
	public void findReservation_Operations(String reservId){
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
	}
	/**
	 * @param tenantName
	 * @param bidMonthIndex
	 * @throws Exception
	 *             This method selects tenant and bid period in ACES home page
	 */
	public void selectTenantNameAndBidPeriodInAcesHomepage(String tenantName, String bidMonthIndex) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
	}

	/**
	 * @param tenantName
	 * @param bidMonthIndex
	 * @param timeFrameFilterValue
	 * @param timeFormatValue
	 * @param refreshIntervalValue
	 * @throws Exception
	 * 
	 *             This method navigates to OPSDesk DashBoard link and performs
	 *             refresh results(Selecting tenantname , timeframefilter ,
	 *             timeformat , refresh interval and clicks on refresh button
	 * 
	 */
	public void refreshResultInOPSDashboard_ACESII(String tenantName, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
	}

	/**
	 * @param reservId
	 * @param expectedResult
	 * @throws Exception
	 *             This method clicks on Find reservation link under OpsDesk Tab
	 *             in Main ACES SITE and verifies whether the reservation exists
	 */
	public void findReservationAndVerifyStatus_OpsDesk_BT(String serviceType,String reservId, String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationAndVerifyStatus(serviceType, reservId, expectedResult);
	}
	
	public void findReservation_OpsDesk(String serviceType,String reservId, String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationAndVerifyStatus_ConferenceRoom(serviceType,reservId, expectedResult);
	}

	/**
	 * @param destinationValue
	 * @param startDateValue
	 * @param startDateTimeValue
	 * @param endDateValue
	 * @param endDateTimeValue
	 * @param confRoomsNeededValue
	 * @param seatingStyleValue
	 * @param NbrOfAttendeesValue
	 * @param hotelDescriptionValue
	 * @param notesValue
	 * @return conferenceReservationsId
	 * 
	 *         This method creates a conference room in BT/Manual booking under
	 *         Operation Tab - Airline site
	 * 
	 */
	/*public String createConferenceRoom_Operations(String destinationValue, String startDateValue,
			String startDateTimeValue, String endDateValue, String endDateTimeValue, String confRoomsNeededValue,
			String seatingStyleValue, String NbrOfAttendeesValue, String hotelDescriptionValue, String notesValue) {
		pgWrapper.operationsTab.clickOnRequestConferenceRoomLink();
		pgWrapper.requestConferenceRoomPage.enterDetailsForConferenceRoom(destinationValue, startDateValue,
				startDateTimeValue, endDateValue, endDateTimeValue, confRoomsNeededValue, seatingStyleValue,
				NbrOfAttendeesValue);
		pgWrapper.requestConferenceRoomPage.hotelByVisibleText();
		pgWrapper.requestConferenceRoomPage.hotelDescription(hotelDescriptionValue);
		pgWrapper.requestConferenceRoomPage.enterNotes(notesValue);
		pgWrapper.requestConferenceRoomPage.clickSaveBtn();
		return conferenceReservationId = pgWrapper.requestConferenceRoomPage.processingRequest();
	}*/
	
	
	/**
	 * @param destinationValue
	 * @param timeFormatValue
	 * @param arrivaltimeValue
	 * @param departuretimeValue
	 * @param arrivalFlightCodeValue
	 * @param arrivalFlightNumberValue
	 * @param departureFlightCodeValue
	 * @param departureFlightNumberValue
	 * @param additionalEmailAddressValue
	 * @param hotelValue
	 * @param accountTypeValue
	 * @param singleRoomType
	 * @param doubleRoomType
	 * 
	 * This method is to create a training reservation under Training Reservation - Request reservation
	 */
	public void createTrainingReservation(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotelValue, String accountTypeValue, String singleRoomType, String doubleRoomType) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotelByIndex(hotelValue);// 1
		pgWrapper.requestReservationPage.accountType(accountTypeValue);// 2
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);// SINGLE
	}
	
	public void confirmPendingCancellation(String reservId, String confirmationCode) throws Exception{
		pgWrapper.pageDashBoard.verifyCancellations(reservId);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink();
		pgWrapper.pageCancelReservation.enterConfirmationNumber(confirmationCode);
		
	}
	
	public String createConferenceRoom_Operations(String destinationValue, String startDateValue,
			String startDateTimeValue, String endDateValue, String endDateTimeValue, String confRoomsNeededValue,
			String seatingStyleValue, String NbrOfAttendeesValue, String hotelDescriptionValue, String notesValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnRequestConferenceRoomLink();
		pgWrapper.requestConferenceRoomPage.enterDetailsForConferenceRoom(destinationValue, startDateTimeValue,
				endDateTimeValue, confRoomsNeededValue, seatingStyleValue, NbrOfAttendeesValue);
		pgWrapper.requestConferenceRoomPage.hotelByVisibleText();
		pgWrapper.requestConferenceRoomPage.hotelDescription(hotelDescriptionValue);
		pgWrapper.requestConferenceRoomPage.enterNotes(notesValue);
		pgWrapper.requestConferenceRoomPage.clickSaveBtn();
		return conferenceReservationId = pgWrapper.requestConferenceRoomPage.processingRequest();
	}
}
