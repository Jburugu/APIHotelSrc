package com.APIHotels.pages.generic;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.LogStatus;

public class GenericClass_new extends LocalDriverManager {
	PgWrapper pgWrapper;
	String conferenceReservationId;
	String windowHandler;
	String reservId;
	int availableReservationRowCount;
	String reservationHotelProviderName;
	String adhocReservation;
	String hotelName;
	int rowCount;
	int reservationRowCount;
	int assignmentAlertRowCount;
	String roomsToCancel;
	String noOfRooms;
	private EventFiringWebDriver driver2;
	/*String airlinesUrl = "http://10.10.103.152:3080/ACESAIR/";
    String aces2Url = "http://10.10.103.152:3005/ACES/";*/
	
	public EventFiringWebDriver driver=null;

	/*public GenericClass(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	*/

	public String createConferenceRoom(String destinationValue, String startDateTimeValue, String endDateTimeValue,
			String confRoomsNeededValue, String seatingStyleValue, String NbrOfAttendeesValue,
			String hotelDescriptionValue, String notesValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnManualBookingLink();
		pgWrapper.operationsTab.clickOnRequestConferenceRoomLink();
		pgWrapper.requestConferenceRoomPage.enterDetailsForConferenceRoom(destinationValue, startDateTimeValue,
				endDateTimeValue, confRoomsNeededValue, seatingStyleValue, NbrOfAttendeesValue);
		pgWrapper.requestConferenceRoomPage.hotelByVisibleText();
		pgWrapper.requestConferenceRoomPage.hotelDescription(hotelDescriptionValue);
		pgWrapper.requestConferenceRoomPage.enterNotes(notesValue);
		pgWrapper.requestConferenceRoomPage.clickSaveBtn();
		return conferenceReservationId = pgWrapper.requestConferenceRoomPage.processingRequest();

	}

	public void airlinesLoginAndClickOnManualBookingLink(String mesaUsername, String mesaPassword) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(mesaUsername, mesaPassword);
		pgWrapper.operationsTab.clickOnManualBookingLink();
	}

	public void findReservation(String conferenceReservationId, String expectedResult) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
		pgWrapper.findReservationBTPage.enterSearchCriteria(conferenceReservationId);
		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
		pgWrapper.findReservationBTPage.verifyReservationExists(conferenceReservationId, expectedResult);
	}
	
	public void createRequestLimo(String pairingNumber, String pickUpLocationValue, String pickUpDetailValue,
            String pickUpDateValue, String pickUpFlightValue, String pickUpTimeValue, String dropOffLocationValue,
            String dropOffDetailValue, String dropOffFlightValue, String dropOffDateValue, String dropOffTimeValue,
            String individualEmpId, String deptValue, String empName, String passengers) {
        pgWrapper.operationsTab.clickOnAcesDirectLink();
        pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
        pgWrapper.acesDirectRequestLimoPage.requestLimo(pairingNumber, pickUpLocationValue, pickUpDetailValue,
                pickUpDateValue, pickUpFlightValue, pickUpTimeValue, dropOffLocationValue, dropOffDetailValue,
                dropOffFlightValue, dropOffDateValue, dropOffTimeValue);
        pgWrapper.acesDirectRequestLimoPage.selectDeptPositionEmpIdEmpNameAndPassengersUnderIndividualAndGroupBooking(
                individualEmpId, deptValue, empName, passengers);
        pgWrapper.acesDirectRequestLimoPage.clickOnSaveButton();
        pgWrapper.acesDirectRequestLimoPage.processingRequest();
    }
	
	public void clickOnAssignmentLinkAndConfirmWithConfirmationNo(String conferenceReservationId,
			String confirmationCode, String notesValue, String billingValue, String conferenceRoomNameValue,
			String rate) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageDashBoard.verifyAssignments(conferenceReservationId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pageDashBoard.clickYesAlertBtn();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode, notesValue, notesValue,
				billingValue);
		pgWrapper.pagePendingConfirmations.enterConferenceRoomName(conferenceRoomNameValue);
		pgWrapper.pagePendingConfirmations.enterOverrideRate(rate);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.clickOkBtn();
	}

	public void findReservation_OPSDesk(String serviceType, String conferenceReservationId, String expectedResult)
			throws Exception {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationAndVerifyStatus_ConferenceRoom(serviceType,
				conferenceReservationId, expectedResult);
	}

	public int findReservationVerifyStatus(String serviceType, String deptValue, String pairingNumber,
            String pickUpLocationValue, String pickUpDateValue, String dropOffDateValue, String pickUpTimeValue,
            String dropOffTimeValue, String hotelName, String expectedResult) {
        pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
        pgWrapper.acesDirectFindReservationPage.findReservation(serviceType, deptValue, pairingNumber,
                pickUpLocationValue, pickUpDateValue, dropOffDateValue);
        rowCount = pgWrapper.acesDirectFindReservationPage.verifyReservation(pickUpDateValue, pickUpTimeValue,
                dropOffDateValue, dropOffTimeValue, hotelName);
        pgWrapper.acesDirectFindReservationPage.verifyStatus(rowCount, expectedResult);
        return rowCount;
    }
	
	public String createTrainingReservation(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotelValue, String accountTypeValue, String singleRoomType, String doubleRoomType,
			String saveButtonType) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnTrainingReservations();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotelByIndex(hotelValue);// 1
		pgWrapper.requestReservationPage.accountType(accountTypeValue);// 2
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);// SINGLE

		if (saveButtonType.equals("GenerateFax")) {
			windowHandler = pgWrapper.requestReservationPage.clickGenerateFax();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.requestReservationPage.closeNewPDFWindow(windowHandler);
		} else if (saveButtonType.equals("SendToVendorWebsite")) {
			pgWrapper.requestReservationPage.clickSendToVendorWebsite();
			reservId = pgWrapper.requestReservationPage.processingRequest();
		} else if (saveButtonType.equals("SendToAPI")) {
			pgWrapper.requestReservationPage.clickOnSendToAPI();
			reservId = pgWrapper.requestReservationPage.processingRequest();
		} else {
			windowHandler = pgWrapper.requestReservationPage.clickSendToHotelByEmail();
			reservId = pgWrapper.requestReservationPage.processingRequest();
			pgWrapper.requestReservationPage.closeNewPDFWindow(windowHandler);
		}
		return reservId;
	}

	public void acesLoginDetailsAndSetTenantDetails(String userName, String password, String tenantName,
			String bidMonthIndex) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
	}

	public void airlinesLoginAndNavigateToTrainingReservation(String envoyUsername, String envoyPassword) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
		pgWrapper.operationsTab.clickOnTrainingReservations();
	}

	public void clickOnPendingConfirmationLinkUnderOPSDesk() throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
	}

	public void createRoomBlock(String locationValue, String timeFormatValue, String checkInDateValue,
			String checkInTimeValue, String checkOutDateValue, String checkOutTimeValue, String roomCountValue,
			String reasonCountValue, String adhocType, String notesValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnRequestRoomBlockUnderBT();
		pgWrapper.requestRoomBlockPage.createRoomBlock(locationValue, timeFormatValue, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType,
				notesValue);
		pgWrapper.requestRoomBlockPage.clickSaveBtn();
		pgWrapper.requestRoomBlockPage.processingRequest();
	}
	
	 public void createRoomBlockUnderAcesDirect(String locationValue, String checkInDateValue, String checkInTimeValue,
	            String checkOutDateValue, String checkOutTimeValue, String roomCountValue, String reasonCountValue,
	            String adhocType, String notesValue) {
	        pgWrapper.operationsTab.clickOnRoomBlockRequests();
	        pgWrapper.requestRoomBlockPage.createRoomblock(locationValue, checkInDateValue, checkInTimeValue,
	                checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType, notesValue);
	        pgWrapper.requestRoomBlockPage.clickSaveBtn();
	        pgWrapper.requestRoomBlockPage.processingRequest();
	    }
	 

	public int findOrEditRoomBlockRequest(String locationValue, String adhocType, String checkInDateValue,
			String checkInTimeValue, String checkOutDateValue, String checkOutTimeValue, String roomCountValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.operationsTab.clickOnFindOrEditRoomBlockRequestsLink();
		pgWrapper.findOrEditRoomBlockRequestsPage.findOrEditRoomBlockRequest(locationValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickRetrieveBtn();
		return rowCount = pgWrapper.findOrEditRoomBlockRequestsPage.verifyAdhocBooking(adhocType, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
	}

	public void acesLoginAndNavigateToOpsDashboard(String userName, String password, String tenantName,
			String bidMonthIndex, String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
	}

	public void airlinesLoginAndNavigateToBT(String endeavorUsername, String endeavorPassword) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get("airlinesURL");
		pgWrapper.airlinesLoginPage.loginToAirlines(endeavorUsername, endeavorPassword);
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
	}

	public String clickOnCancelLinkAndPerformActions(int rowCount, String roomCountValue, String cancelRoomValue,
			String cancelNotesValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnCancelLink(rowCount);
		roomsToCancel = pgWrapper.findOrEditRoomBlockRequestsPage.cancelRoomBlock(roomCountValue, cancelRoomValue,
				cancelNotesValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.clickOnOkBtn();
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyCancelledRowStatus(rowCount, roomCountValue, roomsToCancel);
		return roomsToCancel;
	}

	public String clickAdhocReservationLinkAndBookTripWithConfirmationNumber(String adhocType, String roomCountValue,
			String locationValue, String checkInDateValue, String confirmationCode, String hotelNotesValue,
			String rateValue, String isTaxIncluded, String billingMethodValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		adhocReservation = "Adhoc-" + adhocType;
		System.out.println(adhocReservation);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(adhocReservation);
		noOfRooms = "S" + roomCountValue;
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(noOfRooms, locationValue,
				checkInDateValue);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		pgWrapper.pageDashBoard.clickYesAlertBtn();
		pgWrapper.pagePendingConfirmations.hotelProvider();
		if (!confirmationCode.isEmpty()) {
			pgWrapper.pagePendingConfirmations.enterCancellationCode(confirmationCode, hotelNotesValue);
		}
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.selectBillingMethod(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		return hotelName;
	}

	public String createRequestReservation(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotel, String singleRoomType, String doubleRoomType, String reasonCodeValue) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotel(hotel);
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);// 11790
		pgWrapper.requestReservationPage.selectReason(reasonCodeValue);
		pgWrapper.requestReservationPage.clickSaveBtn();
		return reservId = pgWrapper.requestReservationPage.processingRequest();
	}

	public void enterConfirmationDetailsAndBookReservation(String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String roomCountValue, String hotelName,
			String expectedStatus, String confirmationCode, String hotelNotesValue, String billingMethodValue)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		availableReservationRowCount = pgWrapper.pagePendingConfirmations.getHotelProviderRow(checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue, hotelName);
		if (!expectedStatus.isEmpty()) {
			pgWrapper.pagePendingConfirmations.verifyStatus(availableReservationRowCount, expectedStatus);
		}
		System.out.println("availableReservationRowCount: " + availableReservationRowCount);
		pgWrapper.pagePendingConfirmations.clickAvailableReservation(availableReservationRowCount);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.enterNotes(hotelNotesValue);
		pgWrapper.pagePendingConfirmations.enterBillingMethod(billingMethodValue);
		pgWrapper.pagePendingConfirmations.verifyHotelNameAndClickFinishButton(hotelName, reservationHotelProviderName);
	}

	public void findReservationUnderReservationsAndVerifyStatus(String serviceType, String locationValue,
			String checkInDateValue, String adhocType, String checkInTimeValue, String checkOutDateValue,
			String checkOutTimeValue, String hotelName, String expectedResult) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.enterLocationStartDateToFindReservation(serviceType, locationValue,
				checkInDateValue);
		adhocReservation = "Adhoc-" + adhocType;
		reservationRowCount = pgWrapper.pageFindReservation.verifyReservationExists(adhocReservation, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, hotelName);
		pgWrapper.pageFindReservation.verifyReservationStatus(reservationRowCount, expectedResult);
	}

	public String clickOnAssignmentLinkAndGetHotelName(String reservId) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageDashBoard.verifyAssignments(reservId);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		reservationHotelProviderName = pgWrapper.pagePendingConfirmations.hotelProviderName();
		return reservationHotelProviderName;
	}
	public void airlinesLoginAndNavigateToAcesDirect(String aircanadaUsername, String aircanadaPassword) {
		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get("airlinesURL");
		pgWrapper.airlinesLoginPage.loginToAirlines(aircanadaUsername, aircanadaPassword);
		pgWrapper.operationsTab.clickOnAcesDirectLink();
	}
	
	public String verifyAssignmentAndBookReservation(String pairingNumber, String roomCountValue,
            String pickUpLocationValue, String pickUpDateValue, String gtProviderValue, String confirmationCode,
            String rateValue, String billingMethodValue) throws Exception {
        pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
        assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(roomCountValue,
                pickUpLocationValue, pickUpDateValue);
        pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
        System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
        pgWrapper.pagePendingConfirmations.selectGTProvider(gtProviderValue);
        pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
        pgWrapper.pagePendingConfirmations.clickNextButton();
        hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
        System.out.println("hotelName :" + hotelName);
        pgWrapper.pageTripAssignmentConfirmation.enterRateGtReservation(rateValue);
        pgWrapper.pageTripAssignmentConfirmation.selectBillingMethod(billingMethodValue);
        pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
        return hotelName;
    }
	
	public void createNewDriverInstance(String url, String browser) throws Exception{
		Driver dr = new Driver();
		driver2 = dr.createDriverInstance(nodeURL, browser, url);
		setWebDriver(driver2);
		setPageWrapper(driver2);
		pgWrapper = getPageWrapper();
		driver2.get(url);
	}
	
	public void closeNewDriver(){
		driver2.quit();
	}
	
	public void switchToOldBrowser(String url) throws Exception{
		setWebDriver(driver);
		setPageWrapper(driver);
		pgWrapper = getPageWrapper();
		driver.get(url);
	}
	
	/*
	 * Connects to SFTP and copies the file from local machine to specified
	 * location in Winscp
	 */
	public void uploadFileToSFTP(String MainFolderName, String sftpFolderPath, String scenarioFolderName) throws Exception {
		String[] sftpHostValue = sftpHost.split(",");
		String sftpHost = sftpHostValue[0];
		String sftpHost_Latam = sftpHostValue[1];
		
		JSch jsch = new JSch();
		Session session;
		if (scenarioFolderName.contains("Latam"))
		 session = jsch.getSession(sftpUser, sftpHost_Latam, 22);
		else 
		 session = jsch.getSession(sftpUser, sftpHost, 22);
		session.setPassword(sftpPassword);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");

		session.setConfig(config);
		session.connect();
		Channel channel = session.openChannel("sftp");
		channel.connect();
		System.out.println("sftp channel opened and connected.");
		ChannelSftp channelSftp = (ChannelSftp) channel;
		String sftpDirectory = sftpFolderPath;

		File directory = new File(MainFolderName + File.separator + scenarioFolderName + File.separator);
		File[] fList = directory.listFiles();

		for (File file : fList) {
			if (file.isFile()) {
				String filename = file.getAbsolutePath();
				channelSftp.put(filename, sftpDirectory, ChannelSftp.OVERWRITE);
				System.out.println(filename + " transferred to " + sftpDirectory);
			}
		}
	}

	/*
	 * This method connects to SSH and run the given command. Stores the
	 * terminal logs into string and verify whether expected string found or not
	 */
	public void verifylogsInPutty(String sftpFolderPath, String command, String keyWordInLogs) throws Exception {
		String[] sftpHostValue = sftpHost.split(",");
		String sftpHost_JetBlue = sftpHostValue[0];
		String sftpHost_Latam = sftpHostValue[1];
		
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");		
		JSch jsch = new JSch();
		Session session;
		if (sftpFolderPath.contains("LATAM"))
			 session = jsch.getSession(sftpUser, sftpHost_Latam, 22);
			else 
			 session = jsch.getSession(sftpUser, sftpHost_JetBlue, 22);
		session.setPassword(sftpPassword);
		session.setConfig(config);
		session.connect();
		System.out.println("Connected");
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
		ExtentTestManager.getTest().log(LogStatus.INFO,	"Executed ant command in path " + sftpFolderPath);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();
		System.out.println(in);
		channel.connect();

		byte[] tmp = new byte[1024];
		while (true) {			
			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				String logs = new String(tmp, 0, i);
				System.out.println(logs);
				ExtentTestManager.getTest().log(LogStatus.INFO,
						"The Log " + logs);
				
				if (logs.contains(keyWordInLogs)) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"The Keyword " + keyWordInLogs + " is found in logs ");
					System.out.println("The Keyword you are searching is found in log " + keyWordInLogs);
					channel.disconnect();
					break;
				}
				else if(logs.contains("BUILD FAILED")||logs.contains("Error")){
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"The Keyword BUILD FAILED or Error is found in logs ");
					Assert.fail("Failed to process the file in sftp");
				}
				Thread.sleep(1000);
				//await().atMost(60, SECONDS).until(() -> in.available()>0);
			}
			
			if (channel.isClosed()) {
				// System.out.println("exit-status: " +
				// channel.getExitStatus());
				break;
			}
		}

		//channel.disconnect();
		session.disconnect();
		System.out.println("DONE");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Proceesing of file is successful");
	}
	
	/*
	 * This method moves files from Local to SSH location and Process Files
	 * inside the scenarioFolderName based on tenantName
	 */

	public void processPairingFilesInSSH(String tenantName, String scenarioFolderName) throws Exception {
	System.out.println("Executing processPairingFilesInSSH");
	if (tenantName.equalsIgnoreCase("JetBlue")) {
		uploadFileToSFTP(autoprocessingFilesPath,readPropValue("sftpPath_JetBlue"), scenarioFolderName);
		verifylogsInPutty(readPropValue("sftpPath_JetBlue"), readPropValue("command_JetBlue"),
				readPropValue("keyWordInLogs"));
		connectToSftpAndExecuteCommand("tail -f /home/aces2dev/acesII/logs/tripCompareServerConsole.*", "SUCCESS");
	} else if (tenantName.equalsIgnoreCase("LAN Airlines")) {
		uploadFileToSFTP(autoprocessingFilesPath,readPropValue("sftpPath_Latam"), scenarioFolderName);
		verifylogsInPutty(readPropValue("sftpPath_Latam"), readPropValue("command_Latam"),
				readPropValue("keyWordInLogs"));

	}
}
	
	public void ProcessOpsPairingFilesInSSH(String tenantName, String scenarioFolderName) throws Exception {
	System.out.println("Executing processPairingFilesInSSH");
	if (tenantName.equalsIgnoreCase("JetBlue")) {
		uploadFileToSFTP(autoprocessingFilesPath,readPropValue("sftpPath_JetBlue"), scenarioFolderName);
//		verifylogsInPutty(readPropValue("sftpPath_JetBlue"), readPropValue("command_JetBlue"),
//				readPropValue("keyWordInLogs"));
		connectToSftpAndExecuteCommand("tail -f /home/aces2dev/acesII/logs/tripCompareServerConsole.*", "SUCCESS"); }
//	} else if (tenantName.equalsIgnoreCase("LAN Airlines")) {
//		uploadFileToSFTP(autoprocessingFilesPath,readPropValue("sftpPath_Latam"), scenarioFolderName);
//		verifylogsInPutty(readPropValue("sftpPath_Latam"), readPropValue("command_Latam"),
//				readPropValue("keyWordInLogs"));
//
//	}
}
	
	/*
	 * This Method call processPairingFilesInSSH and process add_Pairing_1.xml
	 * file. Books the trips from opsDesk-> Dashboard and verifies the status in
	 * Find Trip page
	 */
	/* As per Steffy(QA), there will be no GT for Latam, So ignoring GT scenarios*/
	
	public void bookAddPairing1Trips(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String status, String supplierType, String scenario)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (sshIndicator.equals("Yes")) {
			processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		String gtName="";
		if (supplierType.equalsIgnoreCase("GT")){
			gtName= pgWrapper.pagePendingConfirmations.selectGTLink();
			if(confirmationCode.equalsIgnoreCase("ClearGT"))
				pgWrapper.pagePendingConfirmations.cleargtConfirmationCode();	
		}
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Completed Booking PA " + reservationId);
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		if(supplierType.equalsIgnoreCase("Hotel"))
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		if(supplierType.equalsIgnoreCase("GT")){
			pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);
			}
	}


	/*
	 * This Method call processPairingFilesInSSH and process delete_pairing.xml
	 * file. Verifies the status in Find Trip page
	 */
	public void verifyDeletePairingTrips(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String status, String scenario)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		if (tenantName.equalsIgnoreCase("LAN Airlines"))
			getDriver().get(acesLatamUrl);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatusAfterDeletePairing(hotelName, status);
	}

	/*
	 * This Method call processPairingFilesInSSH and process add_pairing_2.xml
	 * file. Verifies the status and changes in trips by clicking on show Notes
	 * link in Find Trip page
	 */
	public void verifyAddPairing2Trips(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String status, String scenario,
			String textToVerifyInNotes) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		if (tenantName.equalsIgnoreCase("LAN Airlines"))
			getDriver().get(acesLatamUrl);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.clickOnShowNotesLink();
		if (scenarioFolderName.contains("FaxorEmail"))
			pgWrapper.pageFindTrip.verifyFaxOrEmailVendorInReservationNotes();
		pgWrapper.pageFindTrip.verifyNotesForTrips(textToVerifyInNotes);

	}

	public void verifyRevisePairingTrips(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String status, String scenario,
			String processType, String textToVerifyInNotes) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.clickOnShowNotesLink();
		pgWrapper.pageFindTrip.verifyNotesForTrips(textToVerifyInNotes);

	}
	
	public void processPairingFilesInSSHRoomBlock(String tenantName, String scenarioFolderName) throws Exception {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Add_Pairing file process started ");
		System.out.println("executing processPairingFilesInSSH");
		if (tenantName.equalsIgnoreCase("JetBlue")) {
			uploadFileToSFTP(autoprocessingFilesPath,readPropValue("sftpPath_JetBlue_RoomBlock"), scenarioFolderName);
			verifylogsInPutty(readPropValue("sftpPath_JetBlue_RoomBlock"), readPropValue("command_JetBlue_RoomBlock"),
					readPropValue("keyWordInLogs"));
		} else if (tenantName.equalsIgnoreCase("Latam")) {
			uploadFileToSFTP(autoprocessingFilesPath,readPropValue("sftpPath_Latam"), scenarioFolderName);
			verifylogsInPutty(readPropValue("sftpPath_Latam"), readPropValue("command_Latam"),
					readPropValue("keyWordInLogs"));
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Add_Pairing file process ended ");
	}


	public void bookAddPairing1TripsSynapticAutoReuseHTGT(String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue, String status, String supplierType, String scenario)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		String gtName = "";
		if (supplierType.equalsIgnoreCase("GT"))
			gtName = pgWrapper.pagePendingConfirmations.selectGTLink();
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pageDashBoard.selectPaymentMethodSynaptic(paymentMethod, ccNumberValue, ccCSVNumberValue,
				ccExpiryDate, merchantLogValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		if (supplierType.equalsIgnoreCase("Hotel"))
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		if (supplierType.equalsIgnoreCase("GT")) {
			pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
			pgWrapper.pageFindTrip.verifySupplierAndStatus(gtName, status);
		}
	}

	public void verifyAddPairing2TripsSynaptic(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String status, String scenario,
			String textToVerifyInNotes, String synapticCCNumber) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		// if (tenantName.equalsIgnoreCase("LAN Airlines"))
		// getDriver().get(acesLatamUrl);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotelName, status);
		pgWrapper.pageFindTrip.verifyNotesForTrips(textToVerifyInNotes);
		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotelName, "", "1");
		String pdfs[] = filename.split(",");
		String getPDF = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(getPDF, synapticCCNumber);

	}
	
	public void verifyAddPairing3CrewTracSynaptic(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String confirmationCode, String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate, String merchantLogValue, String supplierType,
			String status,String scenario) throws Exception {
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
//		if (tenantName.equalsIgnoreCase("LAN Airlines"))
//				getDriver().get(acesLatamUrl);
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, "All", "Local", "0");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Started Booking PA " + reservationId);
		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
		pgWrapper.pageDashBoard.clickOnReservationLink();
		pgWrapper.pageDashBoard.selectAvailableReservations();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Completed Booking PA " + reservationId);
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotelName, status);
		pgWrapper.pageFindTrip.verifyNotesForTrips(confirmationCode);
		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotelName, "", "1");
		String pdfs[] = filename.split(",");
		String getPDF = pdfs[0];
		pgWrapper.pageFindTrip.readPDF(getPDF, confirmationCode);

	}

	
public void connectToSftpAndExecuteCommand(String command, String keyWord) throws Exception{
		
		String[] sftpHostValue = sftpHost.split(",");
		String sftpHost = sftpHostValue[0];
	
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");		
		JSch jsch = new JSch();
		Session session;
		session = jsch.getSession(sftpUser, sftpHost, 22); 
		session.setPassword(sftpPassword);
		session.setConfig(config);
		session.connect();
		System.out.println("Connected");
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();
		System.out.println(in);
		channel.connect();

		byte[] tmp = new byte[1024];
		while (true) {
			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				String logs = new String(tmp, 0, i);
				System.out.println(logs);
				if (logs.contains(keyWord)) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"The Keyword "+keyWord+" is found in logs ");
					System.out.println("The Keyword you are searching is found in log");
					channel.disconnect();
					break;
				}
				else if(logs.contains("FAILURE")){
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"The Keyword failure is found in logs ");
					Assert.fail("Failed to process the file in sftp");
				}
				Thread.sleep(10000);
			}
			if (channel.isClosed()) {
				break;
			}
		}

		//channel.disconnect();
		session.disconnect();
		System.out.println("DONE");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Proceesing of parining file is successful ");
	}




}


	

