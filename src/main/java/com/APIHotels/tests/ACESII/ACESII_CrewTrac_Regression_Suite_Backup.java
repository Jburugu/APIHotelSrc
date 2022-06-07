package com.APIHotels.tests.ACESII;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_CrewTrac_Regression_Suite_Backup extends LocalDriverManager {

	public PgWrapper pgWrapper;
	
	//UPDATE test data - change trip-ids and checInDateValue in pairing files and test data files.
	@Test(description = "ATA-446 Crewtrac Scenairo 04 and ATA 418 Reuse more than one available reservations and verify synaptic", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void verifyCrewTracScenario4(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,String ccExpiryDate,String merchantLogValue, String supplierType, String status, String scenario,
			String processType) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				pgWrapper.genericClass.bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status, supplierType, scenario);
			} 
			else if (processType.equalsIgnoreCase("deletePairing1")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario);
			} 
			
			else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status, supplierType, scenario);
			} 
			
			else if (processType.equalsIgnoreCase("deletePairing2")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario);
			} 
			else if (processType.equalsIgnoreCase("addPairing3")) {
				pgWrapper.genericClass.verifyAddPairing3CrewTracSynaptic(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, supplierType, status, scenario);
			}
			
			else
				Assert.fail("ProcessType Not found");
		}
	}
	
	@Test(description = "", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void verifyCrewTracScenario1(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,String ccExpiryDate,String merchantLogValue, String supplierType, String status, String scenario,
			String processType) throws Exception {
		if (runMode.equals("Yes")) {
			//after revise pairings
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			pgWrapper.pageFindReservation.findReservationWithTripId(supplierType, reservationId, checkInDateValue);
			pgWrapper.pageFindReservation.getReservationBTStatus(status);
			if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status, supplierType, scenario);
			} 
	}
	}	
		private void processOpsFiles(String runMode, String sshIndicator, String scenarioFolderName, String tenantName)
				throws Exception {
			if (runMode.equals("Yes")) {
				if (sshIndicator.equals("Yes")) {
					pgWrapper = LocalDriverManager.getPageWrapper();
					pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
				}
			}
		}

		@Test(description = "Processig Add Pairing 1 PA alerts", groups = "Regression", dataProvider = "TestDataFile")
		public void crewTracScenarios(String runMode, String sshIndicator, String scenarioFolderName, String tenantName,
				String scenarioNo, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
				String hotelRateValue, String paymentMethod, String supplierType, String status, String scenario,
				String processType, String textToVerifyInNotes) throws Exception {
			if (runMode.equals("Yes")) {
				pgWrapper = LocalDriverManager.getPageWrapper();
				if (processType.equalsIgnoreCase("addPairing1")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					if(!sshIndicator.equals("Yes"))
					pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId,
							checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, status,
							supplierType, scenario);
					if(scenarioNo.equals("Scenario1B"))
					confirmPCC(reservationId, 1, "confirm");
				} 
				else if (processType.equalsIgnoreCase("revisePairing")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					if(!sshIndicator.equals("Yes"))
//					pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator, scenarioFolderName, tenantName,
//							reservationId, checkInDateValue, hotelName, status, scenario, processType,textToVerifyInNotes);
					revisePairings("Scenario1B", supplierType, reservationId, checkInDateValue, status);
				} 
				
				else if (processType.equalsIgnoreCase("addPairing2")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					bookAndVerifyTrip(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue, hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);
				}
		}
		}
		
		
		private void bookAndVerifyTrip(String scenarioNo, String tenantName, String reservationId, String hotelRateValue,
				String paymentMethod, String checkInDateValue, String hotelName, String confirmationCode, String status, 
				String supplierType, String ridersText) throws Exception {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenarioNo);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
			pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
			pgWrapper.pageDashBoard.clickOnReservationLink();
			String gtName = "";
			if (scenarioNo.equalsIgnoreCase("Scenario19") || scenarioNo.equalsIgnoreCase("Scenario20")
					|| scenarioNo.equalsIgnoreCase("Scenario21") || scenarioNo.equalsIgnoreCase("Scenario22") || scenarioNo.equalsIgnoreCase("Scenario14") ){
				gtName = pgWrapper.pagePendingConfirmations.selectToGTProvider("1");
				if(scenarioNo.equalsIgnoreCase("Scenario14"))
					pgWrapper.pagePendingConfirmations.confirmCodeAtoA(confirmationCode);
				pgWrapper.pagePendingConfirmations.clickNextButton();
			}
			if (scenarioNo.equalsIgnoreCase("Scenario18")){
				pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
				pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
				gtName= pgWrapper.pagePendingConfirmations.selectGTLink();
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.verifyErrorHeaderText(ridersText);
				pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);	
				pgWrapper.pagePendingConfirmations.addToHotelGTRates("12", "13");
			}
			if(scenarioNo.equalsIgnoreCase("Scenario15A")){
				pgWrapper.pagePendingConfirmations.selectOverrideAvailableReservation();
				pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
				gtName=hotelName;
				pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);		
			}
			if(scenarioNo.equalsIgnoreCase("Scenario1B")) {
				pgWrapper.pagePendingConfirmations.selectTripFromAvailableReservationBasedOnCheckInDate(checkInDateValue);
				pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.verifyErrorHeaderText(ridersText);
			}
			pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Completed Booking PA " + reservationId);
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId);
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
			pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);

		}
		
		
		private void revisePairings(String scenarioNo, String supplierType, String reservationId, String checkInDateValue, String status) throws Exception {

			switch (scenarioNo) {
			case "Scenario1A":
			case "Scenario1B":
				verifyStatusOfTrip(supplierType, reservationId, checkInDateValue, status);
				break;

			case "Scenario7":

				break;
			case "Scenario15A":

				break;

			case "Scenario15B":
			case "Scenario21":

				break;


			}

		}
		
		private void addPairings2(String scenarioNo, String supplierType, String reservationId, String checkInDateValue, String status) throws Exception {

			switch (scenarioNo) {
			case "Scenario1A":
				
			case "Scenario1B":

				break;

			case "Scenario7":

				break;
			case "Scenario15A":

				break;

			case "Scenario15B":
			case "Scenario21":

				break;


			}

		}
		
		public void verifyStatusOfTrip(String supplierType, String reservationId, String checkInDateValue, String status) throws Exception {
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails("jetBlue", Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageFindReservation.clickFindReservationLink();
			pgWrapper.pageFindReservation.findReservationWithTripId(supplierType, reservationId, checkInDateValue);
			pgWrapper.pageFindReservation.getReservationBTStatus("Pending Cancellation");
		}
		
		public void confirmPCC(String reservId,int triprowCount, String confirmationCode) throws Exception {
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails("jetBlue", Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
//			triprowCount = 1;
			pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
			pgWrapper.pagePendingConfirmations.findTrip(reservId);
//				pgWrapper.pagePendingConfirmations.bookPendingConfirmationCancelledStatus(triprowCount, confirmationCode);
				pgWrapper.pagePendingConfirmations.bookReservationsFromPendingConfirmations(confirmationCode);
		}
		
		

		@Test(description = "Processig Add Pairing 1 PA alerts", groups = "Regression", dataProvider = "TestDataFile")
		public void bookAddPairingReview(String runMode, String sshIndicator, String scenarioFolderName, String tenantName,
				String scenarioNo, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
				String hotelRateValue, String paymentMethod, String supplierType, String status, String scenario,
				String processType, String textToVerifyInNotes) throws Exception {
			if (runMode.equals("Yes")) {
				pgWrapper = LocalDriverManager.getPageWrapper();
				if (processType.equalsIgnoreCase("addPairing1")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					if(!sshIndicator.equals("Yes"))
					pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId,
							checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, status,
							supplierType, scenario);
				} else if (processType.equalsIgnoreCase("revisePairing")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					if(!sshIndicator.equals("Yes"))
					revisePairings(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue,
							hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);
					/*
					 * pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator,
					 * scenarioFolderName, tenantName, reservationId,
					 * checkInDateValue, hotelName, status, scenario, processType,
					 * textToVerifyInNotes);
					 */
				} else if (processType.equalsIgnoreCase("addPairing2")||processType.equalsIgnoreCase("addPairing3")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					if(!sshIndicator.equals("Yes"))
					bookAndVerifyTrip(scenarioNo, tenantName, reservationId,
						hotelRateValue, paymentMethod,
							checkInDateValue, hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);

				}/* else if (processType.equalsIgnoreCase("addPairing3")) {
					processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
					if(!sshIndicator.equals("Yes"))
					bookAndVerifyTrip(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod,
							checkInDateValue, hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);
				}*/
			}
		}

		private void revisePairings(String scenarioNo, String tenantName, String reservationId, String hotelRateValue,
				String paymentMethod, String checkInDateValue, String hotelName, String confirmationCode, String status, String supplierType,
				String ridersText) throws Exception {

			switch (scenarioNo) {
			case "Scenario1A":
			case "Scenario1B":
				
				break;

			case "Scenario7":

				break;
			case "Scenario18":
			case "Scenario19":
			case "Scenario20":
			case "Scenario21":
			case "Scenario22":
				bookAndVerifyTrip(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue,
						hotelName, confirmationCode, status, supplierType, ridersText);
				
			case "Scenario15A":
				bookAndVerifyTrip(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue, 
						hotelName, confirmationCode, status, supplierType, ridersText);
			}

		}

	
		
		@Test(description = "Verify that original reservation become Best Match for the PA alert - With confirmation code", groups = "Regression", dataProvider = "TestDataFile")
		public void scenario15(String runMode, String sshIndicator, String scenarioFolderName, String tenantName,
				String scenarioNo, String changeSystemDate, String reservationId, String checkInDateValue, String hotelName, 
				String confirmationCode, String hotelRateValue, String paymentMethod, String supplierType, String status, String scenario,
				String processType, String textToVerifyInNotes) throws Exception {
			if (runMode.equals("Yes")) {
				pgWrapper = LocalDriverManager.getPageWrapper();
				if(sshIndicator.equals("Yes"))
				pgWrapper.genericClass.verifylogsInPutty("/home/aces2dev/acesII/scripts", readPropValue("commandToBackSystemDate"),
						readPropValue("keyWordInLogs"));
				processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
				if(!sshIndicator.equals("Yes"))
				revisePairings(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue, hotelName, 
						confirmationCode, status, supplierType, textToVerifyInNotes);
			}
		}
}
