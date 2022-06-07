package com.APIHotels.tests.ACESII;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_CrewTrac_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	// UPDATE test data - change trip-ids and checInDateValue in pairing files
	// and test data files.
	@Test(description = "Check for migrated record in BIDMONTHSTATUS table, If not available Insert record for required BidPeriod", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void checkForMigrateRecordInDB(String runMode, String tenantName, String bidPeriodNumber,
			String bidPeriodYear, String dbCreds) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Checking For Migrating Records In DB");
			pgWrapper = LocalDriverManager.getPageWrapper();

			String url = "jdbc:oracle:thin:@" + dbIP + ":" + dbPort + ":aces";
			Connection con = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, dbCreds, dbCreds);// As
																		// username
																		// and
																		// password
																		// are
																		// same,
																		// created
																		// a
																		// single
																		// parameter
			System.out.println("connection established!");
			Statement st = con.createStatement();
			String tenantIdQuery = "Select tenantid from Tenant where tenantname = '" + tenantName + "'";
			ResultSet rs = st.executeQuery(tenantIdQuery);
			rs.next();
			String tenantId = rs.getString(1);
			String retriveMigrateRecordQuery = "select * from BIDMONTHSTATUS where BIDPERIODNUMBER=" + bidPeriodNumber
					+ " and BIDPERIODYEAR=" + bidPeriodYear + " and TENANTID=" + tenantId + " and STATUSCODE=27";
			ResultSet rs1 = st.executeQuery(retriveMigrateRecordQuery);
			rs1.next();
			int noOfRecordsCount = rs1.getRow();
			if (noOfRecordsCount == 0) {
				ExtentTestManager.getTest().log(LogStatus.INFO,
						"Migrate record is not available in BIDMONTHSTATUSTABLE for selected BIDPERIOD, So inserting migrated record into BIDMONTHSTATUS Table");
				String insertMigrateRecordQuery = "insert into bidmonthstatus (BIDMONTHSTATUSID,BIDPERIODNUMBER,BIDPERIODYEAR,STATUSCODE,STATUS,"
						+ "TENANTID,PAIRINGTYPE) values (BIDMONTHSTATUS_SEQ.nextval," + bidPeriodNumber + ","
						+ bidPeriodYear + ",27,'migrated'," + tenantId + ",-1)";
				int insertedRows = st.executeUpdate(insertMigrateRecordQuery);// For
																				// Latam
																				// throwing
																				// error
																				// as
																				// Insufficient
																				// privileges
																				// manually
				con.commit();
				System.out.println("Inserted Row Count" + insertedRows);
				if (insertedRows == 1)
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"Successfully Inserted migrated record into BIDMONTHSTATUS table");
				else
					Assert.fail("Record not inserted into BIDMONTHSTATUS table");
			} else
				ExtentTestManager.getTest().log(LogStatus.INFO,
						"Migrated record is already exists in BIDMONTHSTATUS table for required BidPeriod");
		}
	}

	@Test(description = "ATA-446 Crewtrac Scenairo 04 and ATA 418 Reuse more than one available reservations and verify synaptic", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyCrewTracScenario4(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue, String supplierType, String status, String scenario,
			String processType) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				pgWrapper.genericClass.bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName,
						tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue,
						paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status,
						supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing1")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario);
			}

			else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName,
						tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue,
						paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status,
						supplierType, scenario);
			}

			else if (processType.equalsIgnoreCase("deletePairing2")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing3")) {
				pgWrapper.genericClass.verifyAddPairing3CrewTracSynaptic(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod,
						ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, supplierType, status,
						scenario);
			}

			else
				Assert.fail("ProcessType Not found");
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
	//			processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
				if(!sshIndicator.equals("Yes"))
//				pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId,
//						checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, status,
//						supplierType, scenario);
				if(scenarioNo.equals("Scenario1B"))
					confirmPCC(tenantName,reservationId, 1, "confirm");
				if(scenarioNo.equals("Scenario5"))
					pgWrapper.genericClass.bookAddPairing1TripsIgnoringBothGT(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, status, supplierType, scenario);
				if(scenarioNo.equals("Scenario7") || scenarioNo.equals("Scenario9"))
					pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId,
							checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, status,
							supplierType, scenario);
				if(scenarioNo.equals("Scenario12A"))
					verifyCombiningTwoTrips(scenarioNo, tenantName, reservationId);
				if(scenarioNo.equals("Scenario14"))
				bookAndVerifyTrip(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue, hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);
					
			} else if (processType.equalsIgnoreCase("revisePairing")) {
				processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
				if(!sshIndicator.equals("Yes"))
//				revisePairings(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue,
//						hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);
				if(scenarioNo.equals("Scenario7"))
					verifyTripFromFindTrip(tenantName, reservationId, checkInDateValue, hotelName, status,textToVerifyInNotes);
				if(scenarioNo.equals("Scenario9")) {
					verifyTripFromFindTrip(tenantName, reservationId, checkInDateValue, hotelName, status,textToVerifyInNotes);
				verifyTheTripInPCPage(tenantName, 1, reservationId);
				}
			} 
			else if (processType.equalsIgnoreCase("deletePairing1")) {
				pgWrapper = LocalDriverManager.getPageWrapper();
				pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickFindTripLink();
				pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
				pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId);
				pgWrapper.pageFindTrip.verifySupplierAndStatusAfterDeletePairing(hotelName, status);
				pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
				pgWrapper.pagePendingConfirmations.findTrip(reservationId);
					pgWrapper.pagePendingConfirmations.bookPendingConfirmationCancelledStatus(1, confirmationCode);
					pgWrapper.opsDeskMenu.clickFindTripLink();
					pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
					pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId+"A");
					pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, "Booked");
			}
			else if (processType.equalsIgnoreCase("addPairing2")||processType.equalsIgnoreCase("addPairing3")) {
				processOpsFiles(runMode, sshIndicator, scenarioFolderName, tenantName);
				if(!sshIndicator.equals("Yes"))
//				bookAndVerifyTrip(scenarioNo, tenantName, reservationId,
//					hotelRateValue, paymentMethod,
//						checkInDateValue, hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);
					if(scenarioNo.equals("Scenario14"))
						bookAndVerifyTrip(scenarioNo, tenantName, reservationId, hotelRateValue, paymentMethod, checkInDateValue, hotelName, confirmationCode, status, supplierType, textToVerifyInNotes);	
					if(scenarioNo.equals("Scenario12A"))
						pgWrapper = LocalDriverManager.getPageWrapper();
						ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenarioNo);
					pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
					pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
					pgWrapper.pageHome.clickOPSDeskLink();
					pgWrapper.opsDeskMenu.clickDashBoardLink();
					pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
					ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
					pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
					pgWrapper.pageDashBoard.clickOnReservationLink();
					String tripNumber = pgWrapper.pageDashBoard.getTripNumber();
					if(tripNumber.equalsIgnoreCase(textToVerifyInNotes));
					ExtentTestManager.getTest().log(LogStatus.PASS, "Trip B Combined with the existing Trip A");

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
			verifyStatusOfTrip(tenantName,supplierType, reservationId, checkInDateValue, status);
			break;

		case "Scenario7":
			verifyTripFromFindTrip(tenantName, reservationId, checkInDateValue, hotelName, status, ridersText);
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

	private void bookAndVerifyTrip(String scenarioNo, String tenantName, String reservationId, String hotelRateValue,
			String paymentMethod, String checkInDateValue, String hotelName, String confirmationCode, String status, 
			String supplierType, String ridersText) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
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
				|| scenarioNo.equalsIgnoreCase("Scenario21") || scenarioNo.equalsIgnoreCase("Scenario22")){
			gtName = pgWrapper.pagePendingConfirmations.selectToGTProvider("1");
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
		if(scenarioNo.equalsIgnoreCase("Scenario14")) {
			gtName = pgWrapper.pagePendingConfirmations.selectGTProvider("1");
			pgWrapper.pagePendingConfirmations.confirmCodeAtoA(confirmationCode);
			pgWrapper.pageTripAssignmentConfirmation.enterRateGtReservation(hotelRateValue);
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
	
	public void verifyStatusOfTrip(String tenantName,String supplierType, String reservationId, String checkInDateValue, String status) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(supplierType, reservationId, checkInDateValue);
		pgWrapper.pageFindReservation.getReservationBTStatus(status);
	}
	public void confirmPCC( String tenantName,String reservId,int triprowCount, String confirmationCode) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
			pgWrapper.pagePendingConfirmations.bookPendingConfirmationCancelledStatus(triprowCount, confirmationCode);
			pgWrapper.pagePendingConfirmations.bookReservationsFromPendingConfirmations(confirmationCode);
	}
	
	public void verifyTripFromFindTrip(String tenantName,String reservId, String checkInDateValue, String hotelName, String status, String textToVerify) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnRequiredTrip(reservId);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		String getGTName = pgWrapper.pageFindTrip.clickOnShowNotesOfReqHotelOrGT(status);
		pgWrapper.pageFindTrip.verifyNotesForTripTransaction(textToVerify);

	}
	
	public void verifyTheTripInPCPage(String tenantName,int triprowCount, String reservId) throws Exception {
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(reservId);
			pgWrapper.pagePendingConfirmations.confirmTripAvailibilityOnPCPage(triprowCount, reservId);
	}
	
	public void verifyCombiningTwoTrips(String scenarioNo, String tenantName, String reservationId) throws NumberFormatException, Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenarioNo);
	pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
	pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
	pgWrapper.pageHome.clickOPSDeskLink();
	pgWrapper.opsDeskMenu.clickDashBoardLink();
	pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
	ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
	pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
	pgWrapper.pageDashBoard.clickOnReservationLink();
	String tripNumber = pgWrapper.pageDashBoard.getTripNumber();
	if(tripNumber.equalsIgnoreCase(reservationId))
		ExtentTestManager.getTest().log(LogStatus.PASS, "Found the following "+reservationId+" ID");
	}
}
