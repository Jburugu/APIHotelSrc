

package com.APIHotels.tests.airlines;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class Regression_BT_Updated extends LocalDriverManager {
	
	public PgWrapper pgWrapper;
	String reservId ="";
	/*String airlinesUrl = "http://10.10.103.152:3080/ACESAIR/";
	String aces2Url = "http://10.10.103.152:3005/ACES/";*/
	
	//@Test(description = "BT request for single, double or multiple rooms", dataProvider = "TestDataFile", groups = "Regression")
	public void createRequestReservation(String envoyUsername, String envoyPassword, String destinationValue,
				String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
				String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
				String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
				String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
				String tenantName, String bidMonthIndex, String timeFrameFilterValue,String refreshIntervalValue) throws Exception {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

			//logger.info("**** Create a reservation in request reservation page ");
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
					departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
					departureFlightNumberValue, additionalEmailAddressValue);
			pgWrapper.requestReservationPage.hotel(hotelValue);
			pgWrapper.requestReservationPage.accountType(accountTypeValue);
			pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
			pgWrapper.requestReservationPage.clickOnSendToAPI();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("****  Verifying the created reservation in ACES Application ");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
		}
		
	//@Test(description = "Airline can cancel BT for single / double / multiple rooms on creation", dataProvider = "TestDataFile", groups = "Regression")
	public void SingleOrDoubleRoomsCancellation(String envoyUsername, String envoyPassword, String destinationValue,
				String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
				String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
				String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
				String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
				String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
				String serviceType, String expectedValue, String cancelReservForEmployees) throws Exception {

			ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

			//logger.info("**** Create a Single or Double Reservation in request reservation page ");
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
					departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
					departureFlightNumberValue, additionalEmailAddressValue);
			pgWrapper.requestReservationPage.hotel(hotelValue);
			pgWrapper.requestReservationPage.accountType(accountTypeValue);
			pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
			pgWrapper.requestReservationPage.clickOnSendToAPI();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();

			//logger.info("*** Verifying the created reservation in ACES Application");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
			//logger.info("Verification of reservation under aces application is done");
			
			//logger.info("**** find the reservation under find reservation page airlines site");
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
			pgWrapper.findReservationBTPage.processCancellation(cancelReservForEmployees); // needs to verify the method
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("**** verify whether the reservation is cancelled");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			pgWrapper.pageFindReservation.findReservationAndVerifyStatus(serviceType,reservId,expectedValue);
			pgWrapper.pageHome.clickLogoutLink();
			//logger.info("Verification of cancelled reservation is completed");
			
		}
		
	//@Test(description = "Book a BT request with confirmation number", dataProvider = "TestDataFile", groups = "Regression")
	public void BookBTWithConfirmationNo(String envoyUsername, String envoyPassword, String destinationValue,
				String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
				String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
				String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
				String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
				String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
				String confirmationCode,String ClearConfirmationCode,String serviceType,String expectedValue) throws Exception {

			ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

			//logger.info("**** Create a Multiple Reservation in request reservation page ");
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
					departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
					departureFlightNumberValue, additionalEmailAddressValue);
			pgWrapper.requestReservationPage.hotel(hotelValue);
			pgWrapper.requestReservationPage.accountType(accountTypeValue);
			pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
			pgWrapper.requestReservationPage.clickOnSendToAPI();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("**** Find a reservation in find reservation page ");
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();

			//logger.info("*** Verifying the created reservation in ACES Application");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
			pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
			pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,ClearConfirmationCode);
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			pgWrapper.pageFindReservation.findReservationAndVerifyStatus(serviceType,reservId,expectedValue);//Booked
			//logger.info("Verification of reservation under aces application is done");
			
		}

	//@Test(description = "Book a BT request without confirmation number", dataProvider = "TestDataFile", groups = "Regression")
	public void BTRequestWithoutConfirmationNo(String envoyUsername, String envoyPassword, String destinationValue,
				String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
				String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
				String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
				String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
				String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
				String assignmentAlertExpectedResult,String serviceType) throws Exception {
	
			ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			
			//logger.info("**** Create a Multiple Reservation in request reservation page ");
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
					departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
					departureFlightNumberValue, additionalEmailAddressValue);
			pgWrapper.requestReservationPage.hotel(hotelValue);
			pgWrapper.requestReservationPage.accountType(accountTypeValue);
			pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
			pgWrapper.requestReservationPage.clickOnSendToAPI();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("**** Find a reservation in find reservation page ");
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			//String reservationID = pgWrapper.findReservationBTPage.readFromExcel();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("*** Verifying the created reservation in ACES Application");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			// String reservationID = pgWrapper.pageDashBoard.readFromExcel();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
			pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
			pgWrapper.pagePendingConfirmations.hotelProvider();
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			pgWrapper.pageDashBoard.verifyAssignmentAlert(assignmentAlertExpectedResult);
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			String expected ="Pending Assignment Confirmation";
			pgWrapper.pageFindReservation.findReservationAndVerifyStatus(serviceType,reservId,expected);
			//logger.info("Verification of reservation under aces application is done");
			
			//logger.info("**** Find a reservation in find reservation page ");
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,expected);
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
		}
		

	//@Test(description = "Unconfirmed BT Can Be Edited", dataProvider = "TestDataFile", groups = "Regression")
	public void UnconfirmedBTCanBeEdited(String envoyUsername, String envoyPassword, String destinationValue,
				String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
				String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
				String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
				String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
				String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
				String assignmentAlertExpectedResult, String serviceType, String departureDateValue, String empValue,
				String confirmationCode, String ClearConfirmationCode) throws Exception {

			//logger.info("creation of pending assignment confirmation");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			
			//logger.info("**** Create a Multiple Reservation in request reservation page ");
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
					departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
					departureFlightNumberValue, additionalEmailAddressValue);
			pgWrapper.requestReservationPage.hotel(hotelValue);// Other
			pgWrapper.requestReservationPage.accountType(accountTypeValue);// 2
			pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);// SINGLE
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
			pgWrapper.requestReservationPage.clickOnSendToAPI();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("**** Find a reservation in find reservation page ");
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			//String reservationID = pgWrapper.findReservationBTPage.readFromExcel();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			
			//logger.info("*** Verifying the created reservation in ACES Application");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
			pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
			pgWrapper.pagePendingConfirmations.hotelProvider();
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			pgWrapper.pageDashBoard.verifyAssignmentAlert(assignmentAlertExpectedResult);
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			String expected ="Pending Assignment Confirmation";
			pgWrapper.pageFindReservation.findReservationAndVerifyStatus(serviceType,reservId,expected);
			//logger.info("Verification of reservation under aces application is done");
			
			//logger.info("**** Find a reservation in find reservation page ");
			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
			pgWrapper.operationsTab.clickOnBusinessTravelLink();
			pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
			pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
			pgWrapper.findReservationBTPage.clickOnRetrieveButton();
			pgWrapper.findReservationBTPage.verifyReservationExists(reservId,expected);
			pgWrapper.findReservationBTPage.clickEditLink();
			pgWrapper.findReservationBTPage.editReservationInformation(departureDateValue, empValue);
			pgWrapper.airlinesLoginPage.clickOnLogoutButton();
			

			//logger.info("*** Verifying the created reservation in ACES Application");
			getDriver().get(aces2Url);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.verifyAssignments(reservId);
			pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
			pgWrapper.pagePendingConfirmations.hotelProvider();
			pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,ClearConfirmationCode);
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			// Need to write validation in aces find reservation and airlines  find reservation page
			/*String expected ="Pending Assignment Confirmation";
			pgWrapper.pageFindReservation.enterStartAndEndDates(reservId,expected);*/
			
		}
	
	
	
	
	
	//@Test(description = "Confirmed BT Can Be Edited Cancelled", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void confirmedBTCanBeEditedCancelled(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
			String confirmationCode,String clearConfirmationCode, String serviceType, String departureDateValue, String empValue, 
			String cancellationIForEmpIds, String cancellationIIForEmpIds, String statusAfterReservCreation_Airline, 
			String statusAfterReservConfirmation_ACES, String statusAfterReservConfirmation_Airline, String statusAfterEdit_Airline, 
			String statusAfterCancelDoubleRoom_Airline, String statusAfterCancelDoubleRoom_ACES,
			String statusAfterCancelBoth_Airline, String statusAfterCancelFinish_ACES, String statusAfterCancelFinish_Airline, String statusAfterCancelConfirm_ACES, String statusAfterCancelConfirm_Airline) throws Exception{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a Multiple Reservation in request reservation page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verifying the created reservation in ACES Application ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,clearConfirmationCode);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterReservConfirmation_ACES);
		
		//logger.info("*** Verify Booked status in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservConfirmation_Airline);
		
		//logger.info("*** Edit DoubleRoom reservation details and check status in Airlines Application");
		pgWrapper.findReservationBTPage.clickEditLink();
		pgWrapper.findReservationBTPage.editReservationInformation(departureDateValue, empValue);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterEdit_Airline);
		
		//logger.info("*** Cancel Reservation on changed reservation and Verify Status in Airlines Application ***");
		pgWrapper.findReservationBTPage.processCancellation(cancellationIForEmpIds);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancelDoubleRoom_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verify status in ACESII application ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterCancelDoubleRoom_ACES);
		
		//logger.info("**** Cancel Single and Double Room Reservation from Airlines application *** ");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservation_Operations(reservId);
		pgWrapper.findReservationBTPage.processCancellation(cancellationIIForEmpIds);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancelBoth_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Cancel Reservation from ACESII application and Verify Status");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		//Double Room Cancellation
		pgWrapper.pageDashBoard.verifyCancellations(reservId);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink();
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		//Single Room Cancellation
		pgWrapper.pageDashBoard.verifyCancellations(reservId);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink();
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterCancelFinish_ACES);
		
		//logger.info("**** Verify Status from Airlines application after Cancellation from ACESII*** ");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterCancelFinish_Airline);
		
		//logger.info("*** Enter Confirmation Number and Cancel Reservation under Pending Cancellation from ACESII application and Verify Status");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.confirmReservationCancellation(reservId, confirmationCode);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterCancelConfirm_ACES);
		
		//logger.info("**** Verify Status from Airlines application after Cancel Confirmed*** ");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterCancelConfirm_Airline);
	}
	
	//@Test(description = "BT Reservation With Supplier Change", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void btReservationWithSupplierChange(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
			String confirmationCode,String clearConfirmationCode, String serviceType, String departureDateValue, 
			String empValue, String overrideRate, String billingValue, String statusAfterReservCreation_Airline, String statusAfterReservConfirmation_ACES,
			String statusAfterReservConfirmation_Airline, String statusAfterEdit_Airline, String statusAfterSupplierChange_ACES, String statusAfterSupplierChange_Airlines,
			String statusAfterCancelConfirm_ACES, String statusAfterCancelConfirm_Airline) throws Exception{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		//logger.info("**** Create a Multiple Reservation in request reservation page ");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);//Pending Assignment, Pending Assignment
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verify the status of created reservation in ACES Application");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,clearConfirmationCode);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterReservConfirmation_ACES);//Booked, Booked
		
		//logger.info("*** Verifying the status of reservation in Airlines Application");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservConfirmation_Airline);//Booked, Booked
		//logger.info("**** Edit Reservation in find reservation page ****");
		pgWrapper.findReservationBTPage.clickEditLink();
		pgWrapper.findReservationBTPage.editReservationInformation(departureDateValue, empValue);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterEdit_Airline);//Booked, (Pending-Revision)Pending Assignment
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Access the reservation from Pending Assignment in ACES, modify the Hotel Provider(Supplier) and Confirm Reservation");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.changeHotelProvider(overrideRate, billingValue);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode, clearConfirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		//logger.info(" *** Verify status of BT after Supplier Change in ACES Application ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterSupplierChange_ACES);//Booked, Pending Cancellation, Booked
		
		//logger.info("*** Verify status of BT after Supplier change in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterSupplierChange_Airlines);//Booked, (Revised Cancelled)Pending Cancellation, (Revised)Booked
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Confirm Pending Cancellation Reservation from ACES and Verify Status)
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellations(reservId);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink();
		pgWrapper.pageCancelReservation.enterConfirmationNumber(confirmationCode);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		//logger.info("*** Verify status in ACESII Application after Cancel Confirm");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterCancelConfirm_ACES);//Booked, Pending Cancellation, Booked
		
		//logger.info("*** Verify status of BT Reservation after Cancel Confirm in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterCancelConfirm_Airline);//Booked, (Revised-Cancelled)Pending Cancellation, (Revised)Booked
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
	}

	//@Test(description = "Ignore BT Reservation", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void ignoreBTReservation(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
			String confirmationCode,String clearConfirmationCode, String serviceType, String reason, String empValue, String statusAfterReservCreation_Airline, 
			String statusAfterIgnoreBTReserv_ACES, String statusAfterIgnoreBTReserv_Airline, String statusAfterReservConfirmation_ACES, String statusAfterReservConfirmation_Airline, 
			String statusAfterRoomEdit_Airline, String statusAfterIgnorePA_ACES, String statusAfterIgnorePA_Airline) throws Exception{
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		//logger.info("**** Create a Multiple Reservation in request reservation page and Verify status in Airlines Application ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);//Pending Assignment, Pending Assignment
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Ignore BT from ACESII Application ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode, clearConfirmationCode);
		pgWrapper.pagePendingConfirmations.ignoreReservation();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		//logger.info("*** Verify status of Ignored BT in ACES Application ***");
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterIgnoreBTReserv_ACES);//Ignored, Ignored
		
		//logger.info("*** Create New BT Reservation for Single and DoubleRooms in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterIgnoreBTReserv_Airline);//No Records Found
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);//Pending Assignment, Pending Assignment
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		//logger.info("*** Book the Pending Assignment BT from ACESII Application and Verify the status ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,clearConfirmationCode);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterReservConfirmation_ACES);//Booked, Booked
		
		//logger.info("*** Verify the Booked status in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservConfirmation_Airline);//Booked, Booked
		//logger.info("*** Edit Reservation of DoubleRoom to Single and Verify status in Airline Application ***");
		pgWrapper.findReservationBTPage.processEdit_RoomTypeChange(empValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterRoomEdit_Airline);//Booked, (Pending-Revision)Pending Assignment
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Ignore the Reservation from Pending Assignment in ACES Application ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.ignoreReservation();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		//logger.info("*** Verify status in ACES Application ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterIgnorePA_ACES);
		//logger.info("*** Verify Ignored BT is not displayed in Airline Website ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterIgnorePA_Airline);//Booked, (Original)Booked
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "Ignore BT Reservation From ACES", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void ignoreBTRequestFromACES(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String overrideRate, String billingValue, String reason, String serviceType, 
			String statusAfterReservCreation_Airline, String statusAfterSupplierChange_ACES, String statusAfterSupplierChange_Airline, String empValue, 
			String statusAfterRoomEdit_Airline, String statusAfterIgnoreBTReserv_ACES, String statusAfterIgnoreBTReserv_Airline) throws Exception{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		
		//logger.info("**** Create a Multiple Reservation in request reservation page ");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);// PA, PA
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Change Supplier from ACES and Book without Confirmation#  ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.changeHotelProvider(hotelValue, overrideRate, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		//logger.info("*** Edit Supplier and Book without Confirmation Number in ACES Application and verify Status ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterSupplierChange_ACES);//PAC, PAC
		
		//logger.info("*** Verify the status after Supplier Change in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterSupplierChange_Airline);//PAC, PAC
		//logger.info("*** Edit Double Room To Single from Airlines Application and Verify Status ***");
		pgWrapper.findReservationBTPage.processEdit_RoomTypeChange(empValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterRoomEdit_Airline);//PAC, (Pending-Revision)Pending Assignment
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		
		//logger.info("*** Ignore the Pending Assignment in ACES Application and Verify Status ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnIgnoreLink_PA();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		//logger.info("*** Verify Status after Ignore in ACES Application ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterIgnoreBTReserv_ACES);//Edited Room -> Ignored, PAC, PAC
		
		//logger.info("*** Verify the status after Ignore in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterIgnoreBTReserv_Airline);//PAC, (Original)PAC
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}
	
	//@Test(description = "Unconfirmed BT Can Be Cancelled By Airline User", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void unConfirmedBTCanBeCancelled(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String overrideRate, String billingValue,
			String serviceType, String cancellationIForEmpIds, String confirmationCode, String clearConfirmationCode, String alertMsg,
			String statusAfterReservCreation_Airline, String statusAfterSupplierChange_ACES, String statusAfterSupplierChange_Airline, 
			String statusAfterCancelDoubleRoom_Airline, String finalStatus_ACES, String finalStatus_Airline) throws Exception{
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		
		//logger.info("**** Create a Multiple Reservation in request reservation page ");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);// PA, PA
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Change Supplier from ACES and Book without Confirmation#  ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.changeHotelProvider(hotelValue, overrideRate, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		//logger.info("*** Edit Supplier and Book without Confirmation Number in ACES Application and verify Status ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterSupplierChange_ACES);//PAC, PAC
		
		//logger.info("*** Verify the status after Supplier Change in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterSupplierChange_Airline);//PAC, PAC
		//logger.info("*** Cancel Reservation on changed reservation and Verify Status in Airlines Application ***");
		pgWrapper.findReservationBTPage.processCancellation(cancellationIForEmpIds);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancelDoubleRoom_Airline);//PAC, Pending Cancellation
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verify status of Pending Cancellation under Dashboard -> PC and Cancel COnfirm from ACES Application");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.helper.confirmPendingCancellation(reservId, confirmationCode);
		pgWrapper.pageCancelReservation.clickFinishBtn(alertMsg);
		
		//logger.info("*** Verify Pending Assignment Confirmation BT from Pending Confirmations Menu in ACES APplication ***");
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.clickPACReservation();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode, clearConfirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		
		//logger.info("*** Cancel Confirm from ACES Application ***")
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.helper.confirmPendingCancellation(reservId, confirmationCode);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		//logger.info("*** Verify Status of BT in ACES Application after Cancel Confirmation ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, finalStatus_ACES);//Booked, Cancelled
		
		
		//logger.info("*** Verify status of BT in Airline Application after Cancel Confirmation ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, finalStatus_Airline);//Booked, Cancelled
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "Unconfirmed BT Can Be Ignored after Cancellation By Airline User", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void unConfirmedBTIgnoreAfterCancel(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String overrideRate, String billingValue,
			String serviceType, String cancellationIForEmpIds, String reason, String statusAfterReservCreation_Airline, String statusAfterSupplierChange_ACES, String statusAfterSupplierChange_Airline, 
			String statusAfterCancelDoubleRoom_Airline, String expectedCancellationAlertRows, String statusAfterIgnoreBTReserv_Airline) throws Exception{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		//logger.info("**** Create a Multiple Reservation in request reservation page ");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);// PA, PA
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Change Supplier from ACES and Book without Confirmation#  ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.changeHotelProvider(hotelValue, overrideRate, billingValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		pgWrapper.pagePendingConfirmations.clickAlertOk();
		//logger.info("*** Edit Supplier and Book without Confirmation Number in ACES Application and verify Status ***");
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterSupplierChange_ACES);//PAC, PAC
		
		//logger.info("*** Verify the status after Supplier Change in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterSupplierChange_Airline);//PAC, PAC
		//logger.info("*** Cancel Reservation on changed reservation and Verify Status in Airlines Application ***");
		pgWrapper.findReservationBTPage.processCancellation(cancellationIForEmpIds);
		pgWrapper.findReservationBTPage.verifyStatus(statusAfterCancelDoubleRoom_Airline);//PAC, Pending Cancellation
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verify status of Pending Cancellation under Dashboard -> PC and Ignore BT from ACES Application");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellations(reservId);
		pgWrapper.pageDashBoard.clickOnIgnoreLink_PC();
		pgWrapper.pageIgnoreReservation.enterReason(reason);
		pgWrapper.pageIgnoreReservation.clickOk();
		pgWrapper.pageDashBoard.verifyCancellationAlert(Integer.parseInt(expectedCancellationAlertRows));
		//No rows available under PC table
		//logger.info("*** Verify  BT after Ignore from Pending Confirmations Menu in ACES APplication ***");
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
		pgWrapper.pagePendingConfirmations.verifyBTExists_PendingConfirmation();
		
		//logger.info("*** Verify the status after Ignore BT in Airlines Application ***");
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterIgnoreBTReserv_Airline);//PAC, Pending Cancellation
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
	}

	//@Test(description = "Verify Additional Email Notifications", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void additionalEmailNotifications(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,String EmpID, String EmpName,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue,
			String confirmationCode,String clearConfirmationCode, String serviceType, 
			String statusAfterReservCreation_Airline, String statusAfterReservConfirmation_ACES) throws Exception{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a Multiple Reservation in request reservation page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		reservId = pgWrapper.helper.createRequestReservation_Operations(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue, 
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue, additionalEmailAddressValue, hotelValue, accountTypeValue, singleRoomType, doubleRoomType, hotelSupDescValue);
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		//logger.info("*** Verifying the created reservation in ACES Application ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,clearConfirmationCode);
		pgWrapper.helper.findReservationAndVerifyStatus_OpsDesk_BT(serviceType, reservId, statusAfterReservConfirmation_ACES);
		
		//Verify Email sent to the admin and Verify To field consists of all the email ids given under Additional Email Notifications field
	}

	//@Test(description = "Verify Do No Send Email Option", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void verifyDoNotSendEmailOption(String envoyUsername, String envoyPassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String statusAfterReservCreation_Airline, String userName, String password,
			String tenantName, String bidMonthIndex, String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode, String clearConfirmationCode) throws Exception{
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);

		//logger.info("**** Create a Multiple Reservation in request reservation page and Verify status in Airlines ***");
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotelValue);// Other
		pgWrapper.requestReservationPage.accountType(accountTypeValue);// 2
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);// SINGLE
		if(hotelValue.equals("Other"))
			pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
		pgWrapper.requestReservationPage.doNotSendMeEmailNotification();
		pgWrapper.requestReservationPage.clickOnSendToAPI();
		reservId = pgWrapper.requestReservationPage.processingRequest();
		pgWrapper.helper.findReservationAndVerifyStatus_Operations(reservId, statusAfterReservCreation_Airline);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		//logger.info("*** Verify the Reservation in ACES Application ***");
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.helper.selectTenantNameAndBidPeriodInAcesHomepage(tenantName, bidMonthIndex);
		pgWrapper.helper.refreshResultInOPSDashboard_ACESII(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.enterConfirmationCodesForRoomTypes(confirmationCode,clearConfirmationCode);
		//Verify there is no email sent to the mailid. 
	}

	//@Test(description = "Verify VIP/Executive Reservation Option", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void verifyVIP_ExecutiveReservation(String airlineUserId, String airlinePassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String singleRoomType, String doubleRoomType, 
			String reasonCodeValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue, String color) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserId, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotel(hotelValue);// Other
		pgWrapper.requestReservationPage.vipOrExecutiveReservation();
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
		pgWrapper.requestReservationPage.selectReason(reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		reservId = pgWrapper.requestReservationPage.processingRequest();
		//reservId = "894875";
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		//String reservationID = pgWrapper.findReservationBTPage.readFromExcel();
		// String reservationID = "904686";
		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
		pgWrapper.findReservationBTPage.clickEditLink();
		pgWrapper.findReservationBTPage.verifyVIPReservation();
		pgWrapper.findReservationBTPage.closeEditWindow();
		
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		getDriver().get(aces2Url);
		//reservId = "939695";
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
				refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.verifyAssignmentBGColor(color);
	}
	
	//@Test(description = "Create Reservation For Non Employees", groups = { "Airlines_Regression" }, dataProvider = "TestDataFile")
	public void createReservForNonEmployees(String airlineUserId, String airlinePassword, String destinationValue,
			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String singleRoomType, String doubleRoomType, String singleRoomEmpName, 
			String doubleRoomEmpName, String reasonCodeValue, String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String refreshIntervalValue) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(airlineUserId, airlinePassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotel(hotelValue);// Other
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetailsForNonEmployees(singleRoomType, doubleRoomType, singleRoomEmpName, doubleRoomEmpName);
		pgWrapper.requestReservationPage.selectReason(reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		reservId = pgWrapper.requestReservationPage.processingRequest();
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
		//verify the values entered during creation
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
				refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.verifyReservationDetails_DoubleRoom(singleRoomEmpName, singleRoomType);
		//verify the values under RoomDetails table
	}
	
	
}
