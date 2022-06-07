package com.APIHotels.tests.airlines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class AcesTest extends LocalDriverManager {

	public PgWrapper pgWrapper;
	int assignmentAlertRowCount;
	String arrivalDate;
	String hotelName;
	int cancellationAlertRowCount;
	int rowCount;

	@Test(description = "Book reservation", dataProvider = "TestDataFile", groups ="bookReservation",dependsOnGroups="createReservation1")
	public void bookReservation(String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, String pairingNumber,
			String roomCountValue, String locationValue, String confirmationCode, String toHotelProviderValue,
			String fromHotelGTProviderValue, String rateValue, String isTaxIncluded, String billingMethodValue)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyAssignmentTrip(pairingNumber);
		assignmentAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderAssignmentsAlert("S" + roomCountValue,
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnAssignmentReservationLink(assignmentAlertRowCount);
		System.out.println("assignmentAlertRowCount:" + assignmentAlertRowCount);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.hotelGTProvider(toHotelProviderValue, fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pageTripAssignmentConfirmation.isTaxIncludedAndEnterRate(rateValue, isTaxIncluded);
		pgWrapper.pageTripAssignmentConfirmation.enterFromAndToGTRates(rateValue);
		pgWrapper.pageTripAssignmentConfirmation
				.enterBillingMethodForToHotelAndFromHotelGtProviders(billingMethodValue);
		hotelName = pgWrapper.pageTripAssignmentConfirmation.getHotelName();
		System.out.println("hotelName :" + hotelName);
		pgWrapper.pageTripAssignmentConfirmation.clickFinishButton();
		pgWrapper.pageHome.clickLogoutLink();
	}

	//@Test(description = "Cancel trip from cancellation alerts", dataProvider = "TestDataFile", groups = "cancelAlerts", dependsOnGroups = {
			//"cancelCrew" })
	public void cancelAlerts(String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, String pairingNumber,
			String noOfRooms, String locationValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.verifyCancellationAlerts(pairingNumber);
		List<String> rooms = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
		cancellationAlertRowCount = pgWrapper.pageDashBoard.selectTripUnderCancellationAlerts("S" + rooms.get(0),
				locationValue, arrivalDate);
		pgWrapper.pageDashBoard.clickOnCancellationReservationLink(cancellationAlertRowCount);
		pgWrapper.pageCancelReservation.clickFinishBtn();
		pgWrapper.pageHome.clickLogoutLink();
	}

	//@Test(description = "Pending confirmation", dataProvider = "TestDataFile", groups = "pendingConfirmation", dependsOnGroups = {
		//	"cancelledStatus" })
	public void pendingConfirmation(String userName, String password, String tenantName, String bidMonthIndex,
			String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, String pairingNumber,
			String serviceType, String locationValue, String noOfRooms, String confirmationCode,
			String fromHotelGTProviderValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.genericClass.acesLoginAndNavigateToOpsDashboard(userName, password, tenantName, bidMonthIndex,
				timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		List<String> rooms = new ArrayList<>(Arrays.asList(noOfRooms.split(",")));
		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber, serviceType.toUpperCase(),
				locationValue, "S" + rooms.get(0), hotelName);
		pgWrapper.pagePendingConfirmations.clickOnTripAndEnterConfirmationCode(rowCount, confirmationCode);

		rowCount = pgWrapper.pagePendingConfirmations.findPendingConfirmation(pairingNumber, "GT", locationValue,
				rooms.get(0), fromHotelGTProviderValue);
		pgWrapper.pagePendingConfirmations.bookPendingConfirmationCancelledStatus(rowCount, confirmationCode);
		pgWrapper.pageHome.clickLogoutLink();

	}
}
