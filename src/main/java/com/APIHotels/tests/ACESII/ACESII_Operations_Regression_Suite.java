package com.APIHotels.tests.ACESII;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_Operations_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;
	String reservId = "";


	// ATA-32- Verify the Check-In and Check-Out time is calculated based on the
	// below for the Hotel and GT Reservation
	@Test(description = "Verify Pickup time, CheckIn and CheckOut Times based on Hotel and GT Reservations", groups = "Regression", dataProvider = "TestDataFile")
	public void calculateCheckInandCheckOutTime(String tenantName, String locationValue, String routeType, String tripIdValue, String operatingDateValue)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

		// To convert date formats
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));

		// To Get GateToCurbTime
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickGateTimesLink();
		String gateMins = pgWrapper.pageGateTimes.getgateTimes(locationValue);
		String[] gatetoCurbTimearray = gateMins.split(" ");
		String gtoCTime = gatetoCurbTimearray[0];
		Date gateToCurbTime = df.parse(gtoCTime);

		// To Get CurbToGateTime
		String[] curbToGatearray = gateMins.split(" ");
		String ctoGTime = curbToGatearray[1];
		Date curbToGateTime = df.parse(ctoGTime);

		// To get arrivalTime, ShowTime, PickUpTime, CheckInTime, CheckOutTime,
		// supplierName
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripIdValue, operatingDateValue);
		pgWrapper.pageFindTrip.verifyTripDetails(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		String FlightarrivalTime = pgWrapper.pageFindTrip.getArrivalTime();
		Date arrivalTime = df.parse(FlightarrivalTime);
		String showTimeValue = pgWrapper.pageFindTrip.getShowTime();
		Date showTime = df.parse(showTimeValue);
		String pickupTime = pgWrapper.pageFindTrip.getpickUpTime();
		String checkInTime = pgWrapper.pageFindTrip.getCheckInTime();
		String checkOutTime = pgWrapper.pageFindTrip.getCheckOutTime();
		String supplierName = pgWrapper.pageFindTrip.getSupplierName();

		// To Get Transit Time of supplier
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(locationValue, "Hotel", "CONTRACT");
		pgWrapper.pageFindExistingAirlineSupplier.selectRequiredSupplier(supplierName);
		String transitTimeFromAptoHotel = pgWrapper.pageAirlineHotelSupplier.getTransitTime(routeType, locationValue);
		Date transitTimeAToH = df.parse(transitTimeFromAptoHotel);

		// Calculate PickUpTime = Arrival Time + Gate to Curb
		long addGToCandArrivalTimes = gateToCurbTime.getTime() + arrivalTime.getTime();
		String sumOfArrivalAndGateToCurb = df.format(new Date(addGToCandArrivalTimes));
		System.out.println("sumOfArrivalAndGateToCurb:" + sumOfArrivalAndGateToCurb);
		if (pickupTime.equalsIgnoreCase(sumOfArrivalAndGateToCurb))
			System.out.println(" Verified that Pick-Up Time" + pickupTime + " = " + "Arrival Time + Gate to Curb "
					+ sumOfArrivalAndGateToCurb);
		else
			Assert.assertEquals(pickupTime, sumOfArrivalAndGateToCurb,
					"Pickup time is not equal to Sum of Arrival Time and Gate to Curb Times");

		// Calculate CheckInTime = Arrival Time + Gate to Curb + Transit Time
		long addGToCandArrivalandTransitTimes = arrivalTime.getTime() + gateToCurbTime.getTime()
				+ transitTimeAToH.getTime();
		String sumOfArrivalGateToCurbAndTransit = df.format(new Date(addGToCandArrivalandTransitTimes));
		if (checkInTime.equalsIgnoreCase(sumOfArrivalGateToCurbAndTransit))
			System.out.println(" Verified that Check-In Time" + checkInTime + "="
					+ "Arrival Time + Gate to Curb + Transit Time " + sumOfArrivalGateToCurbAndTransit);
		else
			Assert.assertEquals(checkInTime, sumOfArrivalGateToCurbAndTransit,
					"CheckIn Time is not equal to Sum of Arrival Time , Gate to Curb Time and Transit Time");

		// Calculate CheckOutTime = show time - (transit time + Curb to gate
		// mins)
		long diffDeptureShowTransitTimeandCToG = showTime.getTime()
				- (transitTimeAToH.getTime() + curbToGateTime.getTime());
		String diffOfDeptureShowTransitandCToGTimes = df.format(new Date(diffDeptureShowTransitTimeandCToG));
		if (checkOutTime.equalsIgnoreCase(diffOfDeptureShowTransitandCToGTimes))
			System.out.println(" Verified that Check out time " + checkOutTime + "="
					+ "show time - (transtim time + Curb to gate min)" + diffOfDeptureShowTransitandCToGTimes);
		else
			Assert.assertEquals(checkOutTime, diffOfDeptureShowTransitandCToGTimes,
					"Checkout Time is not equal to show time - (transtim time + Curb to gate min)");
	}

	// ATA-38 - Operations | Split reservations for booked and PA
	@Test(description = "Split reservations for PA", groups = "Regression", dataProvider = "TestDataFile")
	public void splitReservationForPA(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValuebeforeSplit, String locationValue, String operatingDate,
			String roomCountValueAfterSplit) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();

		// Verify Split reservation for PA
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		int assignmentAlertRowCountBeforeSplit = pgWrapper.pageDashBoard
				.selectTripUnderAssignmentsAlert("S" + roomCountValuebeforeSplit, locationValue, operatingDate);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCountBeforeSplit);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCountBeforeSplit);
		String tripIdValue = pgWrapper.pageDashBoard.getTripNumber();

		// Verify room count before split
		int beforeSpiltRoomCount = pgWrapper.pagePendingConfirmations.getRoomCount();
		System.out.println(beforeSpiltRoomCount);
		pgWrapper.pagePendingConfirmations.splitReservation();

		// Verify room count after split
		int afterSpiltRoomCount = pgWrapper.pagePendingConfirmations.getRoomCount();
		System.out.println(afterSpiltRoomCount);

		if (beforeSpiltRoomCount > afterSpiltRoomCount)
			System.out.println("Split reservation for PA is Successful");
		else
			Assert.assertTrue(beforeSpiltRoomCount > afterSpiltRoomCount, "Split reservation Failed");

		// Navigate to DashBoard page to Validate trips with same reservation id
		// is increased or not
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);

		int assignmentAlertRowCountAfterSplit = pgWrapper.pageDashBoard.searchWithTripNumber(tripIdValue);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCountAfterSplit);
		if (assignmentAlertRowCountBeforeSplit < assignmentAlertRowCountAfterSplit)
			System.out.println("One reservation is splited into two or more PA reservations");
		else
			Assert.assertTrue(assignmentAlertRowCountBeforeSplit < assignmentAlertRowCountAfterSplit,
					"Only one reservation found after spliting PA reservation");
	}

	@Test(description = "Split reservations for Booked", groups = "Regression", dataProvider = "TestDataFile")
	public void splitReservationForBooked(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String roomCountValuebeforeSplit, String locationValue, String operatingDate,
			String confirmationCode) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();

		// Verify Split Reservation for Booked
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		int assignmentAlertRowCountBeforeSplit = pgWrapper.pageDashBoard
				.selectTripUnderAssignmentsAlert("S" + roomCountValuebeforeSplit, locationValue, operatingDate);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCountBeforeSplit);
		String tripIdValue = pgWrapper.pageDashBoard.getTripNumber();
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		pgWrapper.pagePendingConfirmations.selectHotelProvider();
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripIdValue, tripStartDate);
		pgWrapper.pageFindTrip.verifyTripDetails(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		int noofTripsBeforeSplit = pgWrapper.pageFindTrip.noOfTrips();
		System.out.println("no. Of Trips Count before Split:" + noofTripsBeforeSplit);

		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		// Verify room count before split

		int beforeSpiltRoomCountForBooked = pgWrapper.pagePendingConfirmations.getRoomCount();
		System.out.println(beforeSpiltRoomCountForBooked);
		pgWrapper.pagePendingConfirmations.splitReservation();

		// Verify room count after split
		int afterSpiltRoomCountForBooked = pgWrapper.pagePendingConfirmations.getRoomCount();
		System.out.println(afterSpiltRoomCountForBooked);

		if (beforeSpiltRoomCountForBooked > afterSpiltRoomCountForBooked)
			System.out.println("Split reservation for Booked is Successful");
		else
			Assert.assertTrue(beforeSpiltRoomCountForBooked > afterSpiltRoomCountForBooked, "Split reservation Failed");

		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripIdValue, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		int noofTripsafterSplit = pgWrapper.pageFindTrip.noOfTrips();
		System.out.println("no. Of Trips Count after Split:" + noofTripsafterSplit);

		// Validate trips count with same reservation Id in Find trip Page
		if (noofTripsBeforeSplit < noofTripsafterSplit)
			System.out.println("One reservation is splited into two or more Booked reservations");
		else
			Assert.assertTrue(noofTripsBeforeSplit < noofTripsafterSplit,
					"Only one reservation found after spliting Booked reservation");

	}

	// ATA-40 -> 41,42,43,44 Process the Airport to Airport PA , Hotel Limo PA,
	// Day room PA, Layover Hotel alert types and Verify them in Find Trip

	@Test(description = "Process the Airport to Airport PA , Hotel Limo PA, Day room PA, Layover alert types and Verify them in Find Trip", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void processingOfDifferentAlertTypes(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String alertType, String tripId, String gtProviderValue,
			String confirmationCode, String status, String toGTProviderValue, String fromGTProviderValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.filterByAlertType(alertType);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		String gtProviderName = "";
		switch (alertType) {
		case "Airport":
			gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider(gtProviderValue);
			System.out.println(gtProviderName);
			pgWrapper.pagePendingConfirmations.confirmCodeAtoA(confirmationCode);
			pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
			break;

		case "Limo":
			pgWrapper.pagePendingConfirmations.hotelProvider();
			pgWrapper.pagePendingConfirmations.enterfromConfirmationCode(confirmationCode);
			gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue, fromGTProviderValue,
					confirmationCode);
			System.out.println(gtProviderName);
			pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
			pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
			pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
			break;

		case "Day Rooms":
			pgWrapper.pagePendingConfirmations.hotelProvider();
			pgWrapper.pagePendingConfirmations.enterGTConfirmationCode(confirmationCode);
			gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue, fromGTProviderValue,
					confirmationCode);
			System.out.println(gtProviderName);
			pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
			pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
			pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
			break;

		case "Layover Hotel":
			pgWrapper.pagePendingConfirmations.hotelProvider();
			gtProviderName = pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue, fromGTProviderValue,
					confirmationCode);
			System.out.println(gtProviderName);
			pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
			pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
			break;

		default:
			Assert.fail("No alert Found to proceed");
			break;
		}
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pagePendingConfirmations.checkForAlertBox();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripId, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(gtProviderName, status);

	}

	@Test(description = "#ATA-79 Re-assign by ignoring To Hotel GT or From Hotel GT", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 1)
	public void reassignIgnoringGTs(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String tripId, String hotelName, String confirmationCode, String reqToGTValue,
			String toGTProviderValue, String notesToIgnoreValue, String reqFromGTValue, String fromGTProviderValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod, String gtStaus)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripId);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		if (toGTProviderValue.equalsIgnoreCase("IGNORED"))
			pgWrapper.pageDashBoard.notesToIgnore(notesToIgnoreValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		if (fromGTProviderValue.equalsIgnoreCase("IGNORED"))
			pgWrapper.pageDashBoard.notesToIgnore(notesToIgnoreValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripId, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtStaus);
	}

	/*
	 * Process PA by ignoring both GT's, Process PA by ignoring 'To Hotel GT',
	 * Process PA by ignoring 'From Hotel GT'.
	 */
	@Test(description = "ATA-106,107and108", groups = { "Regression" }, dataProvider = "TestDataFile", priority = 3)
	public void processPAByIgnoringGTs(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String tripId, String hotelName, String confirmationCode, String reqToGTValue,
			String toGTProviderValue, String notesToIgnoreValue, String reqFromGTValue, String fromGTProviderValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod, String gtStaus)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		if (toGTProviderValue.equalsIgnoreCase("IGNORED"))
			pgWrapper.pageDashBoard.notesToIgnore(notesToIgnoreValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		if (fromGTProviderValue.equalsIgnoreCase("IGNORED"))
			pgWrapper.pageDashBoard.notesToIgnore(notesToIgnoreValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.roomRateTaxYes();
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripId, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtStaus);
	}

	// ATA-47 & 48 - Process PA alert using available reservation(Matched
	// Reservation � PC)
	@Test(description = "Process PA alert using matched PC available reservation", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void processPAUsingMatchedReserv(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String tripId, String status, String gtConfirmationCodeValue,
			String paymentMethod, String statusAfterBooked) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		String colorCodeofRoomsText = pgWrapper.pagePendingConfirmations.getColorCodeOfRooms();
		String hotelName = pgWrapper.pagePendingConfirmations.verifyMatchedReservation(colorCodeofRoomsText, status);
		pgWrapper.pagePendingConfirmations.enterGTConfirmationCode(gtConfirmationCodeValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripId);
		pgWrapper.pageFindTrip.clickOnRequiredTrip(tripId);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, statusAfterBooked);
	}

	// ATA-50 - Operations | Re-assign the reservation using available
	// reservation.
	@Test(description = "Re-assign the supplier for Booked reservation using available reservation", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassignReservations(String tenantName, String serviceType, String locationValue, String startDateValue,
			String status, String toGTProviderValue, String fromGTProviderValue, String confirmationCode)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationReg(serviceType, locationValue, startDateValue);
		pgWrapper.pageFindReservation.getReservationStatus(status);
		String tripNumber = pgWrapper.pageDashBoard.getReservationId();
		String tripStartDate = pgWrapper.pageDashBoard.getCheckIndate();
		pgWrapper.pagePendingConfirmations.selectAvailableTrip();
		String colorCodeofRoomsText = pgWrapper.pagePendingConfirmations.getColorCodeOfRooms();
		String supplierName = pgWrapper.pagePendingConfirmations.verifyMatchedReservation(colorCodeofRoomsText, status);
		pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue, fromGTProviderValue, confirmationCode);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripNumber, tripStartDate);
		pgWrapper.pageFindReservation.verifySupplier(supplierName);

	}

	// ATA-51 Verify buttons �Check with Airline�, �Ignore Reservation�, �Find
	// Related Reservation�, �Override available reservation� on PA page

	@Test(description = "Verify Check with Airline button on PA page", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyCheckWithAirlineButton(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String alertType, String query, String selectRequestsBetweenValue,
			String startDateValue, String endDateValue, String comment) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.filterByAlertType(alertType);
		System.out.println();
		pgWrapper.pageDashBoard.clickOnAvailableTrip();
		pgWrapper.pagePendingConfirmations.checkForAlertBox();
		String tripIdValue = pgWrapper.pageDashBoard.getTripNumber();
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		System.out.println(tripStartDate);
		pgWrapper.pageDashBoard.checkWithAirlines(query);
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndCreatedDate(tripIdValue, tripStartDate);
		pgWrapper.pageDashBoard.verifyNotesInDashboard(query, "trip");

		// Navigate to Airlines site and Enter comments in reservations sections
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "")+"Username"),
				readPropValue(tenantName.replace(" ", "")+"Password"));
		pgWrapper.operationsTab.clickOnReservationLink();
		pgWrapper.reservationPage.viewReservation(selectRequestsBetweenValue, startDateValue, endDateValue);
		pgWrapper.reservationPage.clickOnViewReservationQueriesAndResponses();
		pgWrapper.reservationPage.respondToQuery(tripIdValue, comment);

		// Navigate back to ACES and verify the Response from Airlines in Notes
		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndCreatedDate(tripIdValue, tripStartDate);
		pgWrapper.pageDashBoard.clickOnViewNotesLink();
		pgWrapper.pageDashBoard.verifyNotesInDashboard(comment, "trip");

	}

	@Test(description = "Verify Ignore button on PA page", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void verifyIgnoreButton(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String alertType, String query, String serviceType, String statusBeforeIgnore,
			String statusAfterIgnore) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.filterByAlertType(alertType);
		pgWrapper.pageDashBoard.clickOnAvailableTrip();
		pgWrapper.pagePendingConfirmations.checkForAlertBox();
		String tripIDValue = pgWrapper.pageDashBoard.getTripNumber();
		String startDateValue = pgWrapper.pageDashBoard.getarrivalDate();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripIDValue, startDateValue);
		pgWrapper.pageFindReservation.getReservationStatus(statusBeforeIgnore);
		pgWrapper.pageDashBoard.clickOnignoreReservation();
		pgWrapper.pageDashBoard.ignoreReservation();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripIDValue, startDateValue);
		pgWrapper.pageFindReservation.getReservationStatus(statusAfterIgnore);

	}

	@Test(description = "Verify spilt Reservation button on PA page for Airport To Airport Trip", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifySplitReservationButton(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String alertType, String roomCount, String confirmationCode,
			String serviceType) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.filterByAlertType(alertType);
		pgWrapper.pageDashBoard.filterByRoomCount(roomCount);
		pgWrapper.pageDashBoard.clickOnAvailableTrip();
		String tripIdValue = pgWrapper.pageDashBoard.getTripNumber();
		String tripStartDate = pgWrapper.pageDashBoard.getarrivalDate();
		int beforeSpiltRoomCount = pgWrapper.pagePendingConfirmations.getRoomCount();
		System.out.println(beforeSpiltRoomCount);
		pgWrapper.pagePendingConfirmations.splitReservation();
		int afterSpiltRoomCount = pgWrapper.pagePendingConfirmations.getRoomCount();
		System.out.println(afterSpiltRoomCount);
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripIdValue, tripStartDate);

	}

	@Test(description = "Verify Find Related Reservation button on PA page", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyFindRelatedReservationBtn(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String alertType) throws Exception {
		// String tripIdValue= B2431B
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.filterByAlertType(alertType);
		pgWrapper.pageDashBoard.clickOnAvailableTrip();
		String arrivalFlight = pgWrapper.pageDashBoard.getarrivalFilghtNumber();
		pgWrapper.pageDashBoard.clickOnFindRelatedReservation();
		pgWrapper.pageDashBoard.getRelatedReservation(arrivalFlight);

	}// ATA - 51 End

	// ATA-52 Operations | Book a PA with Adhoc Room Block.
	@Test(description = "Book a PA with Adhoc Room Block", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void bookPAWithRoomBlock(String locationValue, String timeFormatValue, String checkInDateValue,
			String checkInTimeValue, String checkOutDateValue, String checkOutTimeValue, String roomCountValue,
			String reasonCountValue, String adhocType, String notesValue, String expectedValue, String tenantName,
			String statusValue, String confirmationCode, String paymentMethod, String destinationValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotelValue, String hotelDescription, String singleRoomType,
			String singleRoomEmpName, String accountTypeValue, String reasonCodeValue, String toHotelProviderValue)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		getDriver().get(airlinesUrl);
		Thread.sleep(2000);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue("jetblueUsername"),
				readPropValue("jetbluePassword"));

		// ****Create Room Block Requests by clicking On Adhoc Hotel/Limo
		// Request
		pgWrapper.operationsTab.clickOnAdhocHotelRoomBlockRequests();
		pgWrapper.requestRoomBlockPage.createRoomBlock(locationValue, timeFormatValue, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue, reasonCountValue, adhocType,
				notesValue);
		pgWrapper.requestRoomBlockPage.clickSaveBtn();
		pgWrapper.requestRoomBlockPage.processingRequest();

		// *****Finds above created Room Block Request and verify Status
		int rowCount = pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue,
				checkInTimeValue, checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyStatus(rowCount, expectedValue);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// **** Navigates to ACES, Search for Active Block And Verify Status
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(readPropValue("username"), readPropValue("password"),
				tenantName, readPropValue("bidMonthIndex"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.searchForActiveBlock(statusValue, checkInDateValue, checkOutDateValue);
		int activeBlockCount = pgWrapper.pageDashBoard.verifyActiveBlocks(adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.pageDashBoard.verifyStatus(activeBlockCount, expectedValue);

		/// *** Book the above active block
		pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert(roomCountValue, locationValue, checkInDateValue);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.hotelProvider();
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		// pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue,
		// fromGTProviderValue, confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.clickFinishButton();

		// *** Create a Reservation from airlines site
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue("jetblueUsername"),
				readPropValue("jetbluePassword"));
		pgWrapper.operationsTab.clickOnAdhocHotelRequestReservationRequests();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotelValue);
		pgWrapper.requestReservationPage.accountType(accountTypeValue);
		pgWrapper.requestReservationPage.adhocReason(reasonCodeValue);
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetailsForNonEmployeesAdhoc(singleRoomType,
				singleRoomEmpName);
		pgWrapper.requestReservationPage.notes("test");
		pgWrapper.requestReservationPage.hotelDescription(hotelDescription);
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservationId = pgWrapper.requestReservationPage.processingRequest();

		pgWrapper.airlinesLoginPage.clickOnLogoutButton();

		// *** Book the reservation using Adhoc reservation In ACES
		pgWrapper.genericClass.acesLoginDetailsAndSetTenantDetails(readPropValue("username"), readPropValue("password"),
				tenantName, readPropValue("bidMonthIndex"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndCreatedDate(reservationId, checkInDateValue);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		pgWrapper.pagePendingConfirmations.selectTripFromAvailableReservation();
		pgWrapper.pagePendingConfirmations.clickFinishButton();

		// Verify change in supplier in Airlines site
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue("jetblueUsername"),
				readPropValue("jetbluePassword"));
		pgWrapper.operationsTab.clickOnAdhocHotelRequestReservationRequests();
		pgWrapper.genericClass.findOrEditRoomBlockRequest(locationValue, adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
		pgWrapper.findOrEditRoomBlockRequestsPage.verifyAdhocBooking(adhocType, checkInDateValue, checkInTimeValue,
				checkOutDateValue, checkOutTimeValue, roomCountValue);
	}

	// Process BT reservations.
	@Test(description = "Process BT reservations", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void processBTReservation(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotelValue, String singleRoomType, String doubleRoomType, String reasonCodeValue, String tenantName,
			String timeFrameFilterValue, String refreshIntervalValue, String confirmationCode) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
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
		System.out.println(reservId);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		getDriver().get(aces2Url);
		// reservId = "939695";
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndCreatedDate(reservId, getDateInFormat("ddMMMyy"));// checkInDateValue);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink();
		if (!confirmationCode.isEmpty())
			pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode, "");
		pgWrapper.pagePendingConfirmations.clickFinishButton();
	}

	public String getReservationId() {
		return reservId;
	}

	// Re-assign Hotel Provided GT to 3rd party GT (Modify supplier settings)
	@Test(description = "Reassigning the Trip to a different hotel and to different GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassignHotelProGTToThrdPartyGT(String tenantName, String tripIdValue, String hotelNameBeforeReassign,
			String gtNameBeforeReassign, String hotelName, String confirmationCode, String paymentMethod,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String gtNameAfterReassign,
			String status) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		// logger.info("**** Starting verification of Supplier Schedules Tab");
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		// logger.info("**** Starting verification of Supplier Schedules Tab");
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		// logger.info("**** Starting verification of Supplier Schedules Tab");
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		// logger.info("**** Starting verification of Supplier Schedules Tab");
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelNameBeforeReassign, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtNameBeforeReassign, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		// / Reassigning hotel Name and GT of all Trips /
		String gtTitle = pgWrapper.pagePendingConfirmations.getGTTitle();
		pgWrapper.pagePendingConfirmations.selectGTLink();
		pgWrapper.pagePendingConfirmations.clickNextButton();
		System.out.println("GT Provider Name :" + gtTitle);
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtTitle, status);
	}

	@Test(description = "JIRA# ATA -109 Bulk Re-assign Hotel provided GT to 3rd party GT and vice versa", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkReassign(String tenantName, String city, String hotel, String startDate, String endDate,
			String reqStatus, String confirmationValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickSchedulesLink();
		pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
		pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
		pgWrapper.pageHotelSchedules.verifyReservationStatus(reqStatus);
		pgWrapper.pageHotelSchedules.bulkReassignReservations();
		pgWrapper.pageHotelSchedules.enterConfirmationNumber(confirmationValue);
		pgWrapper.pageHotelSchedules.clickOnFinish();
	}

	@Test(description = "JIRA# ATA -112 View Notes to be tested for trips & BT reservations", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void viewNotesForTrip(String tenantName, String tripIdValue, String tripStartDate, String notes,
			String airlinestenantName, String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotelValue, String singleRoomType, String doubleRoomType, String reasonCodeValue,
			String timeFrameFilterValue, String refreshIntervalValue, String BTnotes) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.verifyTripUsingTripNumberAndCreatedDate(tripIdValue, tripStartDate);
		pgWrapper.pageDashBoard.addNotes(notes);
		pgWrapper.pageDashBoard.verifyNotesInDashboard(notes, "trip");
		pgWrapper.pageHome.clickLogoutLink();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.selectHotel(hotelValue);// Other
		pgWrapper.requestReservationPage.vipOrExecutiveReservation();
		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
		pgWrapper.requestReservationPage.selectReason(reasonCodeValue);
		pgWrapper.requestReservationPage.notes(notes);
		pgWrapper.requestReservationPage.clickSaveBtn();
		reservId = pgWrapper.requestReservationPage.processingRequest();
		System.out.println(reservId);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		getDriver().get(aces2Url);
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchWithTripNumber(reservId);
		pgWrapper.pageDashBoard.verifyNotesInDashboard(BTnotes, "btTrip");

	}

	@Test(description = "JIRA# ATA -110 Process PAC & PC alerts", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void processPACandPCAlerts(String tenantName, String serviceType, String tripID, String startDateValue,
			String statusBeforeProcess, String statusAfterProcess, String confirmationCode, String roomCountValue,
			String locationValue, String checkInDateValue) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

		/*
		 * Process PAC
		 */
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripID, startDateValue);
		pgWrapper.pageFindReservation.verifyStatus(statusBeforeProcess);
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		pgWrapper.pagePendingConfirmations.findTrip(tripID);

		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripID, startDateValue);
		pgWrapper.pageFindReservation.verifyStatus(statusAfterProcess);

		/*
		 * Process PC alert-Airport to Airport
		 */
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		int cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts(roomCountValue,
				locationValue, checkInDateValue);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, tripID, startDateValue);
		pgWrapper.pageFindReservation.verifyStatus(statusBeforeProcess);
	}

	/*
	 * MultiNight Planning Scenarios Start ----
	 */

	// Scenario --8 -- ATA-63
	@Test(description = "Reassigning the Trip to a different hotel and to different GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassignTripToDiffHotelAndGT(String tenantName, String tripIdValue, String hotelNameBeforeReassign,
			String gtNameBeforeReassign, String hotelName, String confirmationCode, String reqToGTValue,
			String toGTProviderValue, String reqFromGTValue, String fromGTProviderValue, String paymentMethod,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String gtNameAfterReassign,
			String status) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelNameBeforeReassign, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtNameBeforeReassign, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		/* Reassigning hotel Name and GT of all Trips */
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtNameAfterReassign, status);

	}

	// Scenario --20 -- ATA-64
	@Test(description = "Reassigning both the GT's of last", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void reassigningBothGTsOfLast(String tenantName, String tripIdValue, String hotelNameBeforeReassign,
			String hotelName, String gtProviderValue, String confirmationCode, String reqToGTValue,
			String toGTProviderValue, String reqFromGTValue, String fromGTProviderValue, String paymentMethod,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String status) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
	
		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelNameBeforeReassign, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		/* Reassigning hotel Name and To, From GTs of last Trip only */
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
	}

	// Scenario-21 -- ATA-65
	@Test(description = "Reassigning all the GT's of Multinight Trip", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassigningAllGTsOfMultinight(String tenantName, String tripIdValue, String gtNameBeforeReassign,
			String hotelName, String gtNameAfterReassign, String confirmationCode, String paymentMethod,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String status) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtNameBeforeReassign, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		/* Reassigning only GT without changing Hotel */
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectGTLink();
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtNameAfterReassign, status);
	}

	// Scenario-22 - ATA-66
	@Test(description = "Reassigning one of the GT's of last round trip", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassignOneGTofLastRoundTrip(String tenantName, String tripIdValue, String hotelName,
			String gtNameBeforeReassign, String confirmationCode, String reqFromGTValue, String fromGTProviderValue,
			String paymentMethod, String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String status)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtNameBeforeReassign, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		/* Reassigning last "From Gt" without changing Hotel */
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(fromGTProviderValue, status);

	}

	// Scenario-23 -- ATA- 67
	@Test(description = "Reassigning the Trip to a different hotel", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassignTripToDiffHotel(String tenantName, String tripIdValue, String hotelNameBeforeReassign,
			String gtName, String hotelName, String confirmationCode, String paymentMethod, String hotelRateValue,
			String toHotelGTRate, String fromHotelGTRate, String status) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelNameBeforeReassign, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		/* Reassigning hotel to different hotel without changing GT */
		pgWrapper.pagePendingConfirmations.selectReqHotel(hotelName);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, status);

	}

	// Scenario-24 -- ATA- 68
	@Test(description = "On reassignment when same 3rd Party GT is assigned to all TO Hotel and same 3rd Party GT is assigned to all FROM Hotel", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void reassignDiffGTstoToAndFrom(String tenantName, String tripIdValue, String toGTProviderBeforeAssign,
			String hotelName, String gtProviderValue, String confirmationCode, String reqToGTValue,
			String toGTProviderValue, String reqFromGTValue, String fromGTProviderValue, String paymentMethod,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String status) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderBeforeAssign, status);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		/* Reassigning all "To GTs" and all "From GTs" without changing Hotel */
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderValue, status);
		pgWrapper.pageFindTrip.verifyGTAndStatus(fromGTProviderValue, status);
	}
	
	// Scenario 25: Processing the PC from the ops desk
		// Scenario 26: Processing the GT PC from the ops desk
		@Test(description = "Cancel PC and GT PC from ops desk", groups = { "Regression" }, dataProvider = "TestDataFile")
		public void cancelPCAndGTPCFromOps(String tenantName, String tripIdValue, String cancellationCode)
				throws Exception {
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

			pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.searchPCWithTripNumber(tripIdValue);
			pgWrapper.pageDashBoard.enterCancellationCodeforHotelandGt(cancellationCode);
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			pgWrapper.pageHome.clickLogoutLink();
		}

	@Test(description = "Verify Planning Trips Before Ops1 File Processing", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyPlanningTrips(String tenantName, String tripIdValue, String layOverValue, String hotelName,
			String hotelStatus, String gtName, String gtStatus) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyNoOfLOs(layOverValue);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatus);
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtName, gtStatus);
	}

	/*
	 * MultiNight Planning Scenarios Ends
	 */

	/*
	 * MultiNight OPS1 File Scenarios Start
	 */

	// Scenario6 -- ATA- 75, Scenario7--ATA-76 and Scenario10 -- ATA-77 (All
	// these Test cases uses Hotel Provider GT)
	@Test(description = "Mismatch in the count of GT's - 3 L/O used by 4 L/O AND Mismatch in the count of GT's - 4 L/O used by 3 L/O AND Using multiple available reservations", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void assigningMismatchGTsCount(String runMode, String tenantName, String tripIdValue, String layOverValue, String hotelName,
			String hotelStatusBeforeAssign, String toGTProviderBeforeAssign, String statusBeforeAssign,
			String planningTripId, String confirmationCode, String hotelRateValue, String hotelStatusAfterAssign,
			String toGTProviderAfterAssign, String statusAfterAssign) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyNoOfLOs(layOverValue);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusBeforeAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderBeforeAssign, statusBeforeAssign);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		pgWrapper.pagePendingConfirmations.selectTripFromAvailableReservationBasedOnTripId(planningTripId);
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusAfterAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderAfterAssign, statusAfterAssign);
		pgWrapper.pageHome.clickLogoutLink();
	}
}

	// Scenario 18: Mismatch in the count of GT's - 3 L/O used by 4 L/O - Using
	// 3rd party GT -- ATA- 71
	// Scenario 19: Mismatch in the count of GT's - 4 L/O used by 3 L/O - Using
	// 3rd party GT -- ATA- 70
	// Scenario 27 Using multiple available reservation - Using 3rd party GT -
	// ATA--78
	@Test(description = "Mismatch in the count of GT's - 3 L/O used by 4 L/O - Using 3rd party GT AND Mismatch in the count of GT's - 4 L/O used by 3 L/O - Using 3rd party GT AND Using multiple available reservation - Using 3rd party GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void assignMismatch3rdPartyGTsCount(String tenantName, String tripIdValue, String layOverValue,
			String hotelName, String hotelStatusBeforeAssign, String toGTProviderBeforeAssign,
			String statusBeforeAssign, String planningTripId, String reqToGTValue, String toGTProviderValue,
			String reqFromGTValue, String fromGTProviderValue, String confirmationCode, String gtConfirmationCodeValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String hotelStatusAfterAssign,
			String toGTProviderAfterAssign, String statusAfterAssign) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyNoOfLOs(layOverValue);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusBeforeAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderBeforeAssign, statusBeforeAssign);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		pgWrapper.pagePendingConfirmations.selectTripFromAvailableReservationBasedOnTripId(planningTripId);
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.enterGTConfirmationCode(gtConfirmationCodeValue);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusAfterAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderAfterAssign, statusAfterAssign);
		pgWrapper.pageHome.clickLogoutLink();
	}


	// Scenario 11: Changing the billing method during the reuse - 3rd Party
	@Test(description = "Changing the billing method during the reuse - 3rd Party", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void changeBillingMethodToAPICC(String tenantName, String tripIdValue, String hotelName,
			String hotelStatusBeforeAssign, String toGTProviderBeforeAssign, String statusBeforeAssign,
			String planningTripId, String confirmationCode, String reqToGTValue, String toGTProviderValue,
			String reqFromGTValue, String fromGTProviderValue, String hotelRateValue, String toHotelGTRate,
			String fromHotelGTRate, String paymentMethodForGT, String cardNumber, String hotelStatusAfterAssign,
			String toGTProviderAfterAssign, String statusAfterAssign) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusBeforeAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderBeforeAssign, statusBeforeAssign);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectAPICreditCardForGT(paymentMethodForGT, cardNumber);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusAfterAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderAfterAssign, statusAfterAssign);
		pgWrapper.pageHome.clickLogoutLink();
	}

	// Scenario 28 Check-in time verification of the partial reuse
	@Test(description = "Check-in time verification of the partial reuse", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void checkInTimeVerification(String tenantName, String tripIdValue, String layOverValue, String hotelName,
			String hotelStatusBeforeAssign, String toGTProviderBeforeAssign, String statusBeforeAssign,
			String planningTripId, String confirmationCode, String gtConfirmationCodeValue, String hotelRateValue,
			String toHotelGTRate, String fromHotelGTRate, String hotelStatusAfterAssign, String toGTProviderAfterAssign,
			String statusAfterAssign) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyNoOfLOs(layOverValue);
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusBeforeAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderBeforeAssign, statusBeforeAssign);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		pgWrapper.pagePendingConfirmations.selectTripFromAvailableReservationBasedOnTripId(planningTripId);
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.enterfromConfirmationCode(gtConfirmationCodeValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusAfterAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderAfterAssign, statusAfterAssign);
		pgWrapper.pageHome.clickLogoutLink();

	}

	@Test(description = "Book OpsFile1 Trips Before OpsFile2 File Processing", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bookOpsFile1Trips(String runMode, String tenantName, String tripIdValue, String hotelName,
			String hotelStatusBeforeAssign, String toGTProviderBeforeAssign, String statusBeforeAssign,
			String confirmationCode, String reqToGTValue, String toGTProviderValue, String reqFromGTValue,
			String fromGTProviderValue, String hotelRateValue, String toHotelGTRate, String fromHotelGTRate,
			String paymentMethod, String hotelStatusAfterAssign, String toGTProviderAfterAssign,
			String statusAfterAssign) throws Exception {
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));

		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTripUsingTripId(tripIdValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusBeforeAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderBeforeAssign, statusBeforeAssign);
		pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
		pgWrapper.pagePendingConfirmations.selectSupplierAndconfirmPAResrvation(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.pageFindTrip.verifySupplierAndStatus(hotelName, hotelStatusAfterAssign);
		pgWrapper.pageFindTrip.verifyGTAndStatus(toGTProviderAfterAssign, statusAfterAssign);
		pgWrapper.pageHome.clickLogoutLink();
		}
	}

	/*
	 * MultiNight OPS1 File Scenarios End
	 */

	/*
	 * MultiNight OPS2 File Scenarios Start
	 */
	// Scenario-1,2,3,4
	@Test(description = "Processing a revision on the first L/O To hotel time", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyOps2HtProvidedGtTrips(String runMode, String tenantName, String tripIdValue, String layOverValue,
			String hotelName, String hotelStatusBeforeAssign, String toGTProviderBeforeAssign,
			String statusBeforeAssign, String planningTripId, String confirmationCode, String hotelRateValue, 
			String hotelStatusAfterAssign, String toGTProviderAfterAssign, String statusAfterAssign) throws Exception {

		assigningMismatchGTsCount(runMode, tenantName, tripIdValue, layOverValue, hotelName, hotelStatusBeforeAssign,
				toGTProviderBeforeAssign, statusBeforeAssign, planningTripId, confirmationCode, hotelRateValue,
				hotelStatusAfterAssign, toGTProviderAfterAssign, statusAfterAssign);

	}

	// Scenario-14,15,16
	@Test(description = "These testcase covers scenario 14,15 and 16", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verify3rdPartyProvidedGtTrips2(String tenantName, String tripIdValue, String layOverValue,
			String hotelName, String hotelStatusBeforeAssign, String toGTProviderBeforeAssign,
			String statusBeforeAssign, String planningTripId, String reqToGTValue, String toGTProviderValue,
			String reqFromGTValue, String fromGTProviderValue, String confirmationCode, String gtConfirmationCodeValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String hotelStatusAfterAssign,
			String toGTProviderAfterAssign, String statusAfterAssign) throws Exception {

		assignMismatch3rdPartyGTsCount(tenantName, tripIdValue, layOverValue, hotelName, hotelStatusBeforeAssign,
				toGTProviderBeforeAssign, statusBeforeAssign, planningTripId, reqToGTValue, toGTProviderValue,
				reqFromGTValue, fromGTProviderValue, confirmationCode, gtConfirmationCodeValue, hotelRateValue,
				toHotelGTRate, fromHotelGTRate, hotelStatusAfterAssign, toGTProviderAfterAssign, statusAfterAssign);
	}

	// Scenario -13 Processing a revision on the first L/O To hotel time - Using 3rd party GT
	@Test(description = "Processing a revision on the first L/O To hotel time - Using 3rd party GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void usingAPICCOps2(String tenantName, String tripIdValue, String hotelName, String hotelStatusBeforeAssign,
			String toGTProviderBeforeAssign, String statusBeforeAssign, String planningTripId, String confirmationCode,
			String reqToGTValue, String toGTProviderValue, String reqFromGTValue, String fromGTProviderValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethodForGT,
			String cardNumber, String hotelStatusAfterAssign, String toGTProviderAfterAssign, String statusAfterAssign)
			throws Exception {

		changeBillingMethodToAPICC(tenantName, tripIdValue, hotelName, hotelStatusBeforeAssign,
				toGTProviderBeforeAssign, statusBeforeAssign, planningTripId, confirmationCode, reqToGTValue,
				toGTProviderValue, reqFromGTValue, fromGTProviderValue, hotelRateValue, toHotelGTRate, fromHotelGTRate,
				paymentMethodForGT, cardNumber, hotelStatusAfterAssign, toGTProviderAfterAssign, statusAfterAssign);

	}
	/*
	 * MultiNight OPS2 File Scenarios End---
	 */
	
	@Test(description= "Run delete pairing scripts in database", groups={"Regression"}, dataProvider ="TestDateFile")
	public void deletePairingsFromDB(String queries) throws Exception{
		List<String> deleteQueries= Arrays.asList(queries.split(","));
		String url = "jdbc:oracle:thin:@"+dbIP+":"+dbPort+":aces";
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, dbUserId, dbPassword);
	    System.out.println("connection established!");
		Statement st = con.createStatement();
		for(int queryCount=0; queryCount<deleteQueries.size(); queryCount++){
			String deleteQuery= deleteQueries.get(queryCount);
			ResultSet rs = st.executeQuery(deleteQuery);
			rs.next();
		}
		con.commit();
		con.close();
	}
	
	
	private void connectToSftpAndExecuteCommand(String command, String keyWord) throws Exception{
		
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
					break;
				}
				Thread.sleep(10000);
			}
			if (channel.isClosed()) {
				break;
			}
		}

		channel.disconnect();
		session.disconnect();
		System.out.println("DONE");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Proceesing of parining file is successful ");
	}

	@Test(description= "Change Server Date", groups={"Regression"}, dataProvider ="TestDateFile")
	public void changeServerDate() throws Exception{
		connectToSftpAndExecuteCommand("cd /home/aces2dev/acesII/scripts/ ; ant smoketestant setTime -Ddate.time=011120160001 -Durl=http://+"+aces2Url+"/tomee/ejb -Dzone=JFK", "BUILD SUCCESSFUL");
	}
	
	@Test(description= "Migrate planning file", groups={"Regression"}, dataProvider ="TestDateFile")
	public void migratePlanningFile() throws Exception{
		connectToSftpAndExecuteCommand("cd /home/aces2dev/acesII/scripts/ ; ./migrate-ANZ.sh 12 2016", "BUILD SUCCESSFUL");
	}
	
	@Test(description= "Change supplier settings", groups={"Regression"}, dataProvider ="TestDateFile")
	public void makeChangesInSupplier(String runMode, String tenantName, String city, String serviceId, String status, String supplierName,
			String roomLimitIndex, String roomLimitStartDate, String roomLimitEndDate, String roomLimitMaxRooms) throws Exception{
		if(runMode.equals("Yes")){
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.selectTenantAndBidMonth(tenantName, readPropValue("multiNightBidMonth"));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(supplierName);
		if(supplierName.equalsIgnoreCase("Crowne Plaza Costa Mesa"))
		pgWrapper.pageAirlineHotelSupplier.clickHotelContractDetailsBtn();
		pgWrapper.pageHotelSupplierContractDetails.selectHotelProvidesTransportation();
		pgWrapper.pageHotelSupplierContractDetails.setRoomLimit(roomLimitIndex, roomLimitStartDate, roomLimitEndDate, roomLimitMaxRooms);
		}
	}
	
	/*
	 * Bulk Cancellation
	 */

/*	@Test(description = "ATA-399, Verify Bulk Cancel of PCs available on Dashboard", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelMultipleTrips(String tenantName, String hotelServiceType, String gtServiceType,
			String confirmationCode) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
//		pgWrapper.pageDashBoard.selectMultipleTenants("Cathay Pacific");
//		String hotelTripID = pgWrapper.pageDashBoard.selectServiceType(hotelServiceType);
//		String gtTripID = pgWrapper.pageDashBoard.selectServiceType(gtServiceType);
//		System.out.println("Selected Trip Ids are " + hotelTripID + " , " + gtTripID);
		pgWrapper.pageDashBoard.clickBulkCancelBtn();
		pgWrapper.pageDashBoard.enterHotelCancelConfirmCode(confirmationCode);
		pgWrapper.pageDashBoard.enterGTCancelConfirmCode(confirmationCode);
		pgWrapper.pageDashBoard.clickOnFinishCancelAllButton();
		pgWrapper.pageDashBoard.clickOnFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
//		if (!(hotelTripID == null)) {
//			int index = hotelTripID.indexOf(" ");
//			String hotelTripIDValue = hotelTripID.substring(0, index);
//			System.out.println(hotelTripIDValue);
//			pgWrapper.pageFindTrip.findTripUsingTripId(hotelTripIDValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
			String faxNumber = pgWrapper.pageFindTrip.verifyViewNotes(confirmationCode);
//			pgWrapper.pageFindTrip.clickOnLatestPDFlinkAndDownload();
			Thread.sleep(7000);
//			pgWrapper.pageFindTrip.downloadFaxPDF();
			pgWrapper.pageFindTrip.readPDF(faxNumber, confirmationCode);
//		} else {
//			System.out.println("No Trip found with service type Hotel");
//		}

//		if (!(gtTripID == null)) {
//			int index = gtTripID.indexOf(" ");
//			String gtTripIDValue = gtTripID.substring(0, index);
//			System.out.println(gtTripIDValue);
//			pgWrapper.pageFindTrip.findTripUsingTripId(gtTripIDValue);
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
//			String faxNumber = pgWrapper.pageFindTrip.verifyViewNotes(confirmationCode);
//			pgWrapper.pageFindTrip.clickOnLatestPDFlinkAndDownload();
			Thread.sleep(7000);
//			pgWrapper.pageFindTrip.downloadFaxPDF();
			pgWrapper.pageFindTrip.readPDF(faxNumber, confirmationCode);

//		} else {
//			System.out.println("No Trip found with service type GT");
//		}
	}*/

	/*
	 * // Partial Cancel of HOTEL
	 * 
	 * @Test(description = "ATA-401, Verify Partial Cancel of HOTEL", groups = {
	 * "Regression" }, dataProvider = "TestDataFile") public void
	 * bulkCancelHotelPartial(String tenantName, String hotelServiceType, String
	 * confirmationCode) throws Exception { String hotelTripIDValue = null;
	 * pgWrapper = LocalDriverManager.getPageWrapper();
	 * pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"),
	 * readPropValue("password")); pgWrapper.pageHome.setTenantDetails(tenantName,
	 * Integer.parseInt(readPropValue("bidMonthIndex")));
	 * 
	 * pgWrapper.pageHome.clickOPSDeskLink();
	 * pgWrapper.opsDeskMenu.clickDashBoardLink(); ////
	 * pgWrapper.pageDashBoard.selectMultipleTenants("Cathay Pacific"); String
	 * hotelTripID[] = pgWrapper.pageDashBoard.selectServiceType(hotelServiceType);
	 * System.out.println("Selected Trip Ids are : " + hotelTripID[0]);
	 * System.out.println("Selected GT : " + hotelTripID[1]);
	 * 
	 * // pgWrapper.pageDashBoard.clickBulkCancelBtn(); ////
	 * pgWrapper.pageDashBoard.enterHotelCancelConfirmCode(confirmationCode); //
	 * pgWrapper.pageDashBoard.clickOnFinishCancelAllButton(); //
	 * pgWrapper.pageDashBoard.clickOnFinishButton();
	 * pgWrapper.opsDeskMenu.clickFindTripLink(); // if (!(hotelTripID == null)) {
	 * // int index = hotelTripID.indexOf(" "); // hotelTripIDValue =
	 * "9492930";//hotelTripID.substring(0, index); //
	 * System.out.println(hotelTripIDValue); //
	 * pgWrapper.pageFindTrip.findTripUsingTripId(hotelTripIDValue); //
	 * pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable(); //
	 * pgWrapper.pageFindTrip.clickOnShowNotesforPCC(); // String downloadedFile =
	 * pgWrapper.pageFindTrip.clickOnLatestPDFlinkAndDownload(hotelServiceType); //
	 * Thread.sleep(7000); // pgWrapper.pageFindTrip.readPDF(downloadedFile,
	 * confirmationCode); // } else { //
	 * System.out.println("No Trip found with service type Hotel"); // } //
	 * pgWrapper.opsDeskMenu.clickPendingConfirmationsLink(); //
	 * pgWrapper.pagePendingConfirmations.clickOnTripId(hotelTripIDValue); //
	 * pgWrapper.pagePendingConfirmations.confirmPendingCancellation(
	 * confirmationCode); // Thread.sleep(5000);
	 * 
	 * // if (!(gtTripID == null)) { // int index = gtTripID.indexOf(" "); // String
	 * gtTripIDValue = gtTripID.substring(0, index); //
	 * System.out.println(gtTripIDValue); //
	 * pgWrapper.pageFindTrip.findTripUsingTripId(gtTripIDValue); //
	 * pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable(); // String faxNumber
	 * = pgWrapper.pageFindTrip.verifyViewNotes(confirmationCode); //
	 * pgWrapper.pageFindTrip.verifyPDFReport(); // Thread.sleep(7000); //
	 * pgWrapper.pageFindTrip.downloadFaxPDF(); //
	 * pgWrapper.pageFindTrip.readPDF(faxNumber, confirmationCode); // // } else {
	 * // System.out.println("No Trip found with service type GT"); // } }
	 */
	
	
	  @Test(description = "ATA-401, Verify Partial Cancel of HOTEL", groups = {
			  "Regression" }, dataProvider = "TestDataFile") public void
			  bulkCancelGTPartial(String tenantName, String timeFrameFilterValue, String timeFormatValue,
						String refreshIntervalValue,  String hotelServiceType,  String tripIds
			  ) throws Exception {
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
				refreshIntervalValue);
			pgWrapper.pageDashBoard.filterByAlertTypeCancelAlertList(hotelServiceType);
			pgWrapper.pageDashBoard.tripSelection(tripIds);
			pgWrapper.pageDashBoard.clickBulkCancelBtn();
			pgWrapper.pageDashBoard.unCheckGTOnBulkCancellationPage();
			pgWrapper.pageDashBoard.clickOnFinishCancelAllButton();
			pgWrapper.pageDashBoard.clickOnFinishButton();
		
//			pgWrapper.opsDeskMenu.clickPendingConfirmationsLink(); 
//			Thread.sleep(10000);
			String tripIds2 = "IV8012U";
//			List<String> tripIDsList= Arrays.asList(tripIds2.split(","));
//			pgWrapper.pagePendingConfirmations.clickOnTripId(tripIds2,"CANCEL369");
//			pgWrapper.pagePendingConfirmations.clickOnTripId(tripIDsList.get(0),"CANCEL369");
//			pgWrapper.pagePendingConfirmations.clickOnTripId(tripIDsList.get(1),"CANCEL369");
//			pgWrapper.pagePendingConfirmations.clickOnTripId(tripIDsList.get(1),"CANCEL369");
//			
			pgWrapper.opsDeskMenu.clickFindTripLink();
			Thread.sleep(10000);
//			pgWrapper.pageFindTrip.findTripUsingTripId(tripIDsList.get(0)); 
//			pgWrapper.pageDashBoard.selectMultipleTenants("Air Canada");
			pgWrapper.pageFindTrip.findTrips(tripIds2,"21-Jul-2020"); 
			pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable(); 
			pgWrapper.pageFindTrip.clickOnShowNotesforPCC();
			// pgWrapper.pageFindTrip.clickOnLatestPDFlinkAndDownload(hotelServiceType);
	  
	  }
}

	
	

