package com.APIHotels.tests.airlines;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class Regression_LimoGT extends LocalDriverManager{
	public PgWrapper pgWrapper;
	String reservId ="";
	String acesURL = "http://10.10.103.152:3005/ACES/";
	String airlinesURL = "http://10.10.103.152:3080/ACESAIR/";
	
	private String createLimoRequest(String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue){
		pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
		pgWrapper.requestLimoPage.requestLimoPageUnderBT(locationValue, pickUpTimeValue, pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue);
		pgWrapper.requestLimoPage.addAdditionalPickUpDetails(pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue);
		pgWrapper.requestLimoPage.noOfPassengers(passengers);
		pgWrapper.requestLimoPage.costCenter(reasonValue);
		pgWrapper.requestLimoPage.clickSaveBtn();
		reservId = pgWrapper.requestLimoPage.processingRequest();
		return reservId;
	}
	
	private void createGTProviderForRoute(String city, String serviceId, String status, String fromAirportCode, String toAirportCode, String rate, String units) throws Exception{
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier();
		pgWrapper.pageAirlineGTSupplier.setRoutesAndRates(fromAirportCode, toAirportCode, rate, units);
		pgWrapper.pageAirlineGTSupplier.saveDetails();
	}
	
	private void bookPendingReservationFromACES(String locationValue, String serviceId, String status, String dropOffLocValue, String rate, String units, 
			String tenantName, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, 
			String billingValue) throws Exception{
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		try{Thread.sleep(2000);}catch(Exception e){}
		boolean gtProviderExists = pgWrapper.pagePendingConfirmations.checkForGTProviders();
		if(!gtProviderExists){
			createGTProviderForRoute(locationValue, serviceId, status, locationValue, dropOffLocValue, rate, units);
			pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
			pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		}
		pgWrapper.pagePendingConfirmations.hotelProvider();
		pgWrapper.pagePendingConfirmations.enterBillingMethod(billingValue);
	}
	
	@Test(description = "Book Limo GT With Confirmation Code", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void bookLimoWithConfirmationCode(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline, String supplierAfterAirlineCreation_Airline, 
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, 
			String serviceId, String status, String rate, String units, String confirmationCode, String billingValue, String statusAfterReservConfirmation_ACES, String statusAfterReservConfirmation_Airline) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.findReservationBTPage.verifyTitle(reservId, locationValue, pickUpDetailValue, dropOffLocValue, dropOffDetailValue);
		pgWrapper.findReservationBTPage.verifySupplierName(reservId, supplierAfterAirlineCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		
		//logger.info("*** Enter Confirmation Code and Book Limo Request from Pending Assignments in ACES Application and Verify Status");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceId, reservId, statusAfterReservConfirmation_ACES);
		//logger.info("*** Verify the status after Reservation is Booked in Airlines Application ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservConfirmation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	@Test(description = "Book Limo GT Without Confirmation Code", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void bookLimoWithoutConfirmationCode(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String serviceId, String status, String rate, String units, String billingValue, String statusAfterReservBooking_Airline) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Without Entering Confirmation Code Book Limo Request from Pending Assignments in ACES Application and Verify Status from Pending Confirmations Page");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		//logger.info("*** Verify reservation from Pending Confirmations Page in ACES Application ***");
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.verifyBTExists_PendingConfirmation();
		//logger.info("*** Verify the status in Airlines Application after Booking ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservBooking_Airline);//PAC
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	@Test(description = "Verify Unconfirmed Cancelled Limo Request", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void unconfirmedCancelledLimo(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue,
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String cancelReservForEmployees, String statusAfterCancel_Airline) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);//PA
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verify existance of Limo reservation in Dashboard Pending Assignments ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		//logger.info("*** Cancel created Limo Request from Airline Application ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservation_Operations(reservId);
		pgWrapper.findReservationBTPage.processCancellation(cancelReservForEmployees);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancel_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		//logger.info("*** Verify Cancelled Reservation noy exists in Dashboard -> Pending Cancellation ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(reservId);
		pgWrapper.pageDashBoard.verifyCancellationAlert(0);
	}
	
	@Test(description = "Verify Confirmed Cancelled Limo Request", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void cancelConfirmedLimoRequest(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, 
			String serviceId, String status, String rate, String units, String confirmationCode, String billingValue, String statusAfterReservConfirmation_ACES, String statusAfterReservConfirmation_Airline,
			String cancelReservForEmployees, String statusAfterCancel_Airline, String statusAfterCancelConfirmation_ACES, String statusAfterCancelConfirmation_Airline) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);//PA
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		//logger.info("*** Enter Confirmation Code and Book Limo Request from Pending Assignments in ACES Application and Verify Status");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceId, reservId, statusAfterReservConfirmation_ACES);//Booked
		//logger.info("*** Verify the status after Reservation is Booked in Airlines Application ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservConfirmation_Airline);//Booked
		pgWrapper.findReservationBTPage.processCancellation(cancelReservForEmployees);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancel_Airline);//Pending Cancellation
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Confirm Cancellation from Dashboard->PC in ACES Application and Verify Status ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.helper.confirmPendingCancellation(reservId, confirmationCode);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		pgWrapper.pageDashBoard.verifyCancellationAlert(0);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceId, reservId, statusAfterCancelConfirmation_ACES);//Cancelled
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterCancelConfirmation_Airline);//Cancelled
	}
	
	@Test(description = "Ignore Limo Request", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void ignorePCLimoReservation(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue, String pickUpDetailValue,
			String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, 
			String serviceId, String status, String rate, String units, String confirmationCode, String billingValue, String statusAfterReservConfirmation_ACES, String statusAfterReservConfirmation_Airline,
			String cancelReservForEmployees, String statusAfterCancel_Airline, String reason, String statusAfterIgnore_ACES, String statusAfterIgnore_Airline) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);//PA
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		//logger.info("*** Enter Confirmation Code and Book Limo Request from Pending Assignments in ACES Application and Verify Status");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceId, reservId, statusAfterReservConfirmation_ACES);//Booked
		//logger.info("*** Verify the status after Reservation is Booked in Airlines Application ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservConfirmation_Airline);//Booked
		pgWrapper.findReservationBTPage.processCancellation(cancelReservForEmployees);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancel_Airline);//Pending Cancellation
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Ignore Pending Cancellation from Dashboard->PC in ACES Application and Verify Status ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(reservId);
		pgWrapper.pageDashBoard.clickOnIgnoreLink_PC();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageDashBoard.verifyCancellationAlert(0);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceId, reservId, statusAfterIgnore_ACES);//Pending Cancellation
		//logger.info("*** Verify the status after Reservation is Ignored in Airline Application ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterIgnore_Airline);//Pending Cancellation
	}
	
	@Test(description = "Verify No Of Riders", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void verifyNoOfRiders(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String serviceId, String status, String rate, String units, String billingValue, String cancelReservForEmployees, String confirmationCode) throws Exception{
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Without Entering Confirmation Code Book Limo Request from Pending Assignments in ACES Application and Verify Status from Pending Confirmations Page");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		//logger.info("*** Verify reservation from Pending Confirmations Page in ACES Application ***");
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.verifyBTExists_PendingConfirmation();
		pgWrapper.pagePendingConfirmations.verifyNoOfRoomsForBT(passengers);//5
		//logger.info("*** Cancel the BT limo Request from Airline Application ***");
		getDriver().get(airlinesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservation_Operations(reservId);
		pgWrapper.findReservationBTPage.processCancellation(cancelReservForEmployees);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Verify No Of Rooms for Cancelled BT in Dashboard->PC in ACES Application ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(reservId);
		pgWrapper.pageDashBoard.verifyNoOfRooms_PC(passengers);//5
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.confirmReservationCancellation(reservId, confirmationCode);
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.verifyNoOfRooms_PC(passengers);
	}
	
	@Test(description = "Verify No Of Riders After Reassign", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void verifyNoOfRidersWhenReassigned(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String serviceId, String status, String rate, String units, String billingValue, String confirmationCode, String overrideRate) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Without Entering Confirmation Code Book Limo Request from Pending Assignments in ACES Application and Verify Status from Pending Confirmations Page");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		//logger.info("Change GT Supplier from Find Reservation Page and Verify No Of Riders in OpsDesk PC and Pending COnfirmations screens");
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservation(serviceId, reservId);
		pgWrapper.pageFindReservation.clickReassignSupplier();
		pgWrapper.pagePendingConfirmations.changeGTProvider(overrideRate, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.verifyCancellationAlerts(reservId);
		pgWrapper.pageDashBoard.verifyNoOfRooms_PC(passengers);//5
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.verifyNoOfRoomsForBT(passengers);
	}
	
	@Test(description = "Verify Additional Email Notifications", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void additionalEmailNotifications(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String serviceId, String status, String rate, String units, String billingValue, String confirmationCode) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = createLimoRequest(locationValue, pickUpTimeValue, pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue, 
				dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue, passengers, reasonValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Without Entering Confirmation Code Book Limo Request from Pending Assignments in ACES Application and Verify Status from Pending Confirmations Page");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
	}

	@Test(description = "Verify Do Not Send Email Notification option", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void verifyDoNotSendEmailOption(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue,
			String serviceId, String status, String rate, String units, String billingValue, String confirmationCode) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		
		pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
		pgWrapper.requestLimoPage.requestLimoPageUnderBT(locationValue, pickUpTimeValue, pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue);
		pgWrapper.requestLimoPage.addAdditionalPickUpDetails(pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue);
		pgWrapper.requestLimoPage.noOfPassengers(passengers);
		pgWrapper.requestLimoPage.costCenter(reasonValue);
		pgWrapper.requestLimoPage.clickDoNotSendEmailNotification();
		pgWrapper.requestLimoPage.clickSaveBtn();
		reservId = pgWrapper.requestLimoPage.processingRequest();
		
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Enter COnfirmation code and Book the PA request in Dashboard ACES Application");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		bookPendingReservationFromACES(locationValue, serviceId, status, dropOffLocValue, rate, units, tenantName, timeFrameFilterValue, 
				timeFormatValue, refreshIntervalValue, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
	}
	
	@Test(description = "Verify VIP/Executive Reservation option", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void verifyVIP_ExecutiveReservation(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String statusAfterReservCreation_Airline,
			String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, String color) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		
		pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
		pgWrapper.requestLimoPage.requestLimoPageUnderBT(locationValue, pickUpTimeValue, pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue);
		pgWrapper.requestLimoPage.addAdditionalPickUpDetails(pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue);
		pgWrapper.requestLimoPage.noOfPassengers(passengers);
		pgWrapper.requestLimoPage.costCenter(reasonValue);
		pgWrapper.requestLimoPage.clickVipExecutiveReservation();
		pgWrapper.requestLimoPage.clickSaveBtn();
		reservId = pgWrapper.requestLimoPage.processingRequest();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verify the PA request is displayed among the first few rows of Assignment Alerts with blue color highlight in Dashboard ACES APplication ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.verifyAssignmentBGColor(color);
	}
	
	@Test(description = "Request Limo for Non Employees", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void createLimoReservForNonEmployees(String airlineUserName, String airlinePassword, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String pickUpAPCodeValue, String pickUpFlightNumber, String pickUpPlaceValue, String dropOffLocValue, String dropOffDetailValue, 
			String dropOffTimeValue, String additionalEmailAddressValue, String empIdValue, String passengers, String reasonValue, String empName, String empDepartment, 
			String statusAfterReservCreation_Airline, String acesUserName, String acesPassword, String tenantName, String bidMonthIndex, String timeFrameFilterValue, 
			String timeFormatValue, String refreshIntervalValue) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserName, airlinePassword);
		//logger.info("**** Create a Limo Reservation for Airport To Hotel in request limo page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
		pgWrapper.requestLimoPage.requestLimoPageUnderBT(locationValue, pickUpTimeValue, pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue, empIdValue);
		pgWrapper.requestLimoPage.addAdditionalPickUpDetails(pickUpDetailValue, pickUpAPCodeValue, pickUpFlightNumber, pickUpPlaceValue);
		pgWrapper.requestLimoPage.noOfPassengers(passengers);
		pgWrapper.requestLimoPage.costCenter(reasonValue);
		pgWrapper.requestLimoPage.addEmpName(empName);
		pgWrapper.requestLimoPage.empDepartment(empDepartment);
		pgWrapper.requestLimoPage.clickSaveBtn();
		reservId = pgWrapper.requestLimoPage.processingRequest();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.findReservationBTPage.verifyEmpName(reservId, empName, empIdValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		//logger.info("*** Verify the PA request is displayed among the first few rows of Assignment Alerts with blue color highlight in Dashboard ACES APplication ***");
		getDriver().get(acesURL);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(acesUserName, acesPassword);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.verifyReservationDetails(empName, empIdValue);
	}
}
