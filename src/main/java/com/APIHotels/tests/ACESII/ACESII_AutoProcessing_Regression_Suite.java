package com.APIHotels.tests.ACESII;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_AutoProcessing_Regression_Suite extends LocalDriverManager{
	public PgWrapper pgWrapper;
	
	/* STEPS:
	 * 1. Configure autoprocessing options to Fax/Email in application 2.
	 * Fax/Email Related add pairing 1 Xml should run - add_pairing_1_Fax.xml 3.
	 * Book all trips processed using add_pairing_1_Fax.xml and verify status,
	 * show notes for fax/email in Find Trip 4. Fax/Email Related delete pairing
	 * Xml should run - deletePairings.xml 5. Verify PC status for trips deleted
	 * 6. Fax/Email Related add pairing 2 Xml should run - add_pairing_2_Fax.xml
	 * 7. Verify trips in add_pairing_2_Fax.xml are in booked status and also
	 * verify changes related to scenarios in show notes of find trip
	 * 
	 * 
	 * 8. Configure autoprocessing options to Print in application 9. "Print"
	 * Related Xml should run - add_pairing_1_Print.xml 10. Book All trips
	 * processed using add_pairing_1_Print.xml and verify status, show notes for
	 * print in Find Trip 11. "Print" Related delete pairing Xml should run -
	 * deletePairings.xml 12. Verify PC status for trips deleted 13. Print
	 * Related add pairing 2 Xml should run - add_pairing_2_Print.xml 14. Verify
	 * trips in add_pairing_2_Print.xml are in booked status and also verify
	 * changes related to scenarios in show notes of find trip
	 */

	@Test(description = "Check for migrated record in BIDMONTHSTATUS table, If not available Insert record for required BidPeriod", 
			groups = {"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void checkForMigrateRecordInDB(String runMode, String tenantName, String bidPeriodNumber, String bidPeriodYear,String dbCreds) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,	"Checking For Migrating Records In DB");
			pgWrapper = LocalDriverManager.getPageWrapper();	
			
		String url = "jdbc:oracle:thin:@"+dbIP+":"+dbPort+":aces";
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, dbCreds, dbCreds);//As username and password are same, created a single parameter
	    System.out.println("connection established!");
		Statement st = con.createStatement();
		String tenantIdQuery = "Select tenantid from Tenant where tenantname = '"+tenantName+"'";
		ResultSet rs = st.executeQuery(tenantIdQuery);
		rs.next();
		String tenantId = rs.getString(1);		
		String retriveMigrateRecordQuery = "select * from BIDMONTHSTATUS where BIDPERIODNUMBER="+bidPeriodNumber+ " and BIDPERIODYEAR="+bidPeriodYear+" and TENANTID="+tenantId+" and STATUSCODE=27";
		ResultSet rs1 = st.executeQuery(retriveMigrateRecordQuery);
		rs1.next();
		int noOfRecordsCount=rs1.getRow();
		if(noOfRecordsCount==0){
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Migrate record is not available in BIDMONTHSTATUSTABLE for selected BIDPERIOD, So inserting migrated record into BIDMONTHSTATUS Table");
			String insertMigrateRecordQuery="insert into bidmonthstatus (BIDMONTHSTATUSID,BIDPERIODNUMBER,BIDPERIODYEAR,STATUSCODE,STATUS,"
					+ "TENANTID,PAIRINGTYPE) values (BIDMONTHSTATUS_SEQ.nextval,"+bidPeriodNumber+","+bidPeriodYear+",27,'migrated',"+tenantId+",-1)";
			int insertedRows = st.executeUpdate(insertMigrateRecordQuery);//For Latam throwing error as Insufficient privileges manually
			con.commit();
			System.out.println("Inserted Row Count"+insertedRows);
			if(insertedRows==1)
				ExtentTestManager.getTest().log(LogStatus.INFO,"Successfully Inserted migrated record into BIDMONTHSTATUS table");
			else
				Assert.fail("Record not inserted into BIDMONTHSTATUS table");
		}else ExtentTestManager.getTest().log(LogStatus.INFO,
				"Migrated record is already exists in BIDMONTHSTATUS table for required BidPeriod");		
	}
}	


	@Test(description = "Configure Notification Type to Fax/Email for all options in autoprocessing", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 1)
	public void configureToFaxOrEmail(String runMode, String tenantName) throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Configure Notification Type to Fax/Email for all options in autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.activateHotelAutoprocessing();
			pgWrapper.configurationPage.allConfigSetToFaxOrEmail();
		}
	}

	@Test(description = "Executing Autoprocessing scenarios for Fax or Email", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 2)
	public void autoProcessing_FaxorEmailTCs(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String supplierType, String status, String scenario,
			String processType, String faxNote, String textToVerifyInNotes) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, confirmationCode, hotelRateValue, paymentMethod, status, supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.verifyAddPairing2Trips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario, faxNote);
			} else if (processType.equalsIgnoreCase("revisePairing")) {
				pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario, processType,textToVerifyInNotes);
			} else
				Assert.fail("ProcessType Not found");
		}
	}

	@Test(description = "Configure Notification Type to Fax/Email for all options in autoprocessing", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 3)
	public void configureToPrint(String runMode, String tenantName) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Configure Notification Type to Print for all options in autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.activateHotelAutoprocessing();
			pgWrapper.configurationPage.allConfigSetToPrint();
		}
	}

	@Test(description = "Executing Autoprocessing scenarios for Print", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 4)
	public void autoProcessing_PrintTCs(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String supplierType, String status, String scenario,
			String processType, String textToVerifyInNotes) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, confirmationCode, hotelRateValue, paymentMethod, status, supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.verifyAddPairing2Trips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario, textToVerifyInNotes);
			} else if (processType.equalsIgnoreCase("revisePairing")) {
				pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario, processType,textToVerifyInNotes);
			} else
				Assert.fail("ProcessType Not found");
		}
	} 
	

	@Test(description = "Configure Notification Type to Fax/Email for all options in autoprocessing", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void configureToDefault(String runMode, String tenantName) throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Configure Notifications to Default in autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.activateHotelAutoprocessing();
			pgWrapper.configurationPage.allConfigSetToDefault();
		}
	}
	
	@Test(description = "Configure Notification Type to Fax/Email for all options in autoprocessing", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void configureFax_Latam(String runMode, String tenantName) throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"Configure Notification Type to Fax/Email for all options in autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			getDriver().get(acesLatamUrl);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.activateHotelAutoprocessing();
			pgWrapper.configurationPage.allConfigSetToFaxOrEmail();
		}
	}

	@Test(description = "Autoprocessing Latam", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void FaxAutoProcessing_Latam(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName,
			String confirmationCode, String hotelRateValue, String paymentMethod, String supplierType, String status,
			String scenario, String processType, String textToVerifyInNotes) throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				pgWrapper.genericClass.bookAddPairing1Trips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, confirmationCode, hotelRateValue, paymentMethod, status, supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.verifyAddPairing2Trips(sshIndicator, scenarioFolderName, tenantName, reservationId, checkInDateValue,
						hotelName, status, scenario, textToVerifyInNotes);

			} else
				Assert.fail("ProcessType Not found");
		}
	}
	
	/*Room Block Reuse Scenarios start */
	@Test(description = "Configure Resue Room Blocks", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void configurerReuseRoomBlocks(String runMode, String tenantName, String locationValue,
			String checkInDateValue, String checkInTimeValue, String checkOutDateValue, String checkOutTimeValue,
			String roomCountValue, String reasonCountValue, String adhocType, String notesValue,
			String scenarioFolderName) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Configure Reuse Room Blocks for autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.reuseRoomBlock();
			pgWrapper.pageHome.clickLogoutLink();

			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("jetBlueUsername"),
					readPropValue("jetBluePassword"));
			pgWrapper.operationsTab.clickOnAddAdhocHotelRoomsLink();
			pgWrapper.requestRoomBlockPage.createRoomblock(locationValue, checkInDateValue, checkInTimeValue,
					checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
			pgWrapper.requestRoomBlockPage.clickSaveBtn();
			pgWrapper.requestRoomBlockPage.processingRoomBlockRequest();

			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA ");
			pgWrapper.pageDashBoard.searchTripWithCreatedDate();// 10-Jun-2020
			pgWrapper.pageDashBoard.searchForAdhocPATrip("Adhoc-Hard", locationValue);
			String hotelName = pgWrapper.pageDashBoard.hotelSelectionWithGTAndBook();
			pgWrapper.pageHome.clickLogoutLink();
			String tripNo = getParingNumberFromXML(scenarioFolderName);
			pgWrapper.genericClass.processPairingFilesInSSHRoomBlock(tenantName, scenarioFolderName);

			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(tripNo, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, "Booked");
//			verifyViewNotes(scenarioFolderName);

		}
	}


	public static String getParingNumberFromXML(String scenarioFolderName)
			throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(new FileInputStream(new File(
				autoprocessingFilesPath + File.separator + scenarioFolderName + File.separator + "add_pairing_1.xml")));
		String attrValue = null;
		NodeList entries = doc.getElementsByTagName("pairing");
		int num = entries.getLength();
		for (int i = 0; i < num; i++) {
			Element node = (Element) entries.item(i);
			NamedNodeMap attributes = node.getAttributes();
			int numAttrs = attributes.getLength();
			for (int j = 0; j < numAttrs; j++) {
				Attr attr = (Attr) attributes.item(j);
				String attrName = attr.getNodeName();
				attrValue = attr.getNodeValue();
				if (attrName.contains("pairing-number"))
					break;
			}
			System.out.println(attrValue);
		}
		return attrValue;
	}

	public static String[] getFlightNumbersFromXML(String scenarioFolderName)
			throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(new FileInputStream(new File(
				autoprocessingFilesPath + File.separator + scenarioFolderName + File.separator + "add_pairing_1.xml")));
		String attrValue = null;
		String ttt[] = null;
		NodeList entries = doc.getElementsByTagName("pairing:segment");

		int num = entries.getLength();
		ttt = new String[num];
		for (int i = 0; i < num; i++) {
			Element node = (Element) entries.item(i);
//            listAllAttributes(node);
			NamedNodeMap attributes = node.getAttributes();
			int numAttrs = attributes.getLength();
//            System.out.println(num);

			for (int j = 0; j < numAttrs; j++) {
				Attr attr = (Attr) attributes.item(j);
				String attrName = attr.getNodeName();
				attrValue = attr.getNodeValue();
				if (attrName.contains("flight-number"))
					System.out.println(attrValue);

//                System.out.println("Found attribute: " + attrName + " with value: " + attrValue);
			}
			ttt[i] = attrValue.trim();
		}
		return ttt;
	}

//Note: Change Trip number and dates to future dates in add_pairing_1.xml in RoomBlock_Aces Appl_AddPairing folder, update the testdata file with the same dates.
	@Test(description = "Resue Room Blocks from ACES", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void reuseRoomBlockFromACES(String runMode, String tenantName, String cityValue, String supplierValue,
			String adhocType, String startDateValue, String endDateValue, String roomsValue, String startTime,
			String endTime, String rateValue, String reasonCodeValue, String scenarioFolderName)
			throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Reuse Room Blocks for autoprocessing through ACES");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageAddHotelReservation.clickAddHotelReservationLink();
			pgWrapper.pageAddHotelReservation.bookReservation(cityValue, supplierValue, adhocType, startDateValue,
					endDateValue, roomsValue, startTime, endTime, rateValue, reasonCodeValue);
			pgWrapper.pageHome.clickLogoutLink();

			String tripNo = getParingNumberFromXML(scenarioFolderName);
			pgWrapper.genericClass.processPairingFilesInSSHRoomBlock(tenantName, scenarioFolderName);
			String[] getttt = getFlightNumbersFromXML(scenarioFolderName);

			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			pgWrapper.pageFindTrip.findTrips(tripNo, startDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			pgWrapper.pageFindTrip.verifySupplierAndStatus(supplierValue, "Booked");
			pgWrapper.pageFindTrip.verifyViewNotes(getttt);

		}

	}

	// Note: Change Trip number and dates to future dates in add_pairing_1.xml in
	// RoomBlock_Aces Appl_AddPairing folder, update the testdata file with the same
	// dates.
	@Test(description = "verify Resue Room block with 3rdParty GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyResueRoomblck3rdParyGT(String runMode, String tenantName, String checkInEarlyTolerance,
			String checkoutLateTolerance, String faxEmailFromTime, String faxEmailToTime, String printFromTime,
			String printToTime, String cityValue, String supplierValue, String adhocType, String startDateValue,
			String endDateValue, String roomsValue, String startTime, String endTime, String rateValue,
			String reasonCodeValue, String scenarioFolderName) throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Configure Reuse Room Blocks for autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.configResueRoomBlock3rdPartyGT(checkInEarlyTolerance, checkoutLateTolerance,
					faxEmailFromTime, faxEmailToTime, printFromTime, printToTime);
			pgWrapper.configurationPage.configThirdPartyGT();
			pgWrapper.configurationPage.allConfigSetToYesFor3rdPartyGT();
			pgWrapper.configurationPage.allConfigSetToFaxOrEmailFor3rdPartyGT();
			pgWrapper.opsDeskMenu.clickReservationsLink();
			pgWrapper.pageAddHotelReservation.clickAddHotelReservationLink();
			pgWrapper.pageAddHotelReservation.bookReservation(cityValue, supplierValue, adhocType, startDateValue,
					endDateValue, roomsValue, startTime, endTime, rateValue, reasonCodeValue);
			pgWrapper.pageHome.clickLogoutLink();

			String tripNo = getParingNumberFromXML(scenarioFolderName);
			pgWrapper.genericClass.processPairingFilesInSSHRoomBlock(tenantName, scenarioFolderName);
			String[] pairingFile = getFlightNumbersFromXML(scenarioFolderName);

			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			Thread.sleep(80000);
			pgWrapper.pageFindTrip.findTrips(tripNo, startDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			pgWrapper.pageFindTrip.verifySupplierAndStatus(supplierValue, "Booked");
			String[] viewNotes = pgWrapper.pageFindTrip.verifNotes();
			verifyFlightNumbers(viewNotes, pairingFile);

		}
	}

	public void verifyFlightNumbers(String[] viewNotesValues, String[] pairingFileValues) {
		String[] viewNotesFlightNbr = new String[viewNotesValues.length];
		for (int i = 0; i < viewNotesValues.length; i++) {
			int spacePos = viewNotesValues[i].lastIndexOf(" ");
			String suffix = viewNotesValues[i].substring(spacePos + 1, viewNotesValues[i].length());
			System.out.println("Flight number : " + i + " " + suffix);
			viewNotesFlightNbr[i] = suffix;
		}
		for (int i = 0; i < pairingFileValues.length; i++) {
			for (int j = 0; j < viewNotesValues.length; j++) {
				if (pairingFileValues[i].contains(viewNotesFlightNbr[j]))
					System.out.println("Pass");
			}
			break;
		}
	}

	// Note: Change Trip number and dates to future dates in add_pairing_1.xml in
	// RoomBlock_Aces Appl_AddPairing folder, update the testdata file with the same
	// dates.
	@Test(description = "Request Add pairing and verify that system will reuse the available Room block request. (Early Check-in)", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyResueRoomblckEarlyChkIn(String runMode, String tenantName, String checkInEarlyTolerance,
			String checkoutLateTolerance, String faxEmailFromTime, String faxEmailToTime, String printFromTime,
			String printToTime, String locationValue, String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String roomCountValue, String reasonCountValue,
			String adhocType, String notesValue, String scenarioFolderName) throws NumberFormatException, Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Configure Reuse Room Blocks for autoprocessing");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickConfigurationLink();
			pgWrapper.configurationPage.configResueRoomBlock3rdPartyGT(checkInEarlyTolerance, checkoutLateTolerance,
					faxEmailFromTime, faxEmailToTime, printFromTime, printToTime);
			pgWrapper.pageHome.clickLogoutLink();

			getDriver().get(airlinesUrl);
			pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue("jetBlueUsername"),
					readPropValue("jetBluePassword"));
			pgWrapper.operationsTab.clickOnAddAdhocHotelRoomsLink();
			pgWrapper.requestRoomBlockPage.createRoomblock(locationValue, checkInDateValue, checkInTimeValue,
					checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
			pgWrapper.requestRoomBlockPage.clickSaveBtn();
			pgWrapper.requestRoomBlockPage.processingRoomBlockRequest();
		
			String tripNo = getParingNumberFromXML(scenarioFolderName);
			pgWrapper.genericClass.processPairingFilesInSSHRoomBlock(tenantName, scenarioFolderName);
			String[] pairingFile = getFlightNumbersFromXML(scenarioFolderName);

			getDriver().get(aces2Url);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			Thread.sleep(80000);
			pgWrapper.pageFindTrip.findTrips(tripNo, checkInDateValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
//				pgWrapper.pageFindTrip.verifySupplierAndStatus(supplierValue, "Booked");
//				String[] viewNotes = pgWrapper.pageFindTrip.verifNotes();
//				verifyFlightNumbers(viewNotes, pairingFile);

		}
	}
}
	
	
