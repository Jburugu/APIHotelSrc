package com.APIHotels.tests.ACESII;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_OpsEfficiency_Regression_Suite extends LocalDriverManager {
	public PgWrapper pgWrapper;

	/*
	 * Bulk Reassign
	 */
	@Test(description = "#ATA-389 Verify whether able to select same Hotel supplier with different GT supplier and Ignored GTs, #ATA-394 Verify whether able to select same Hotel supplier and different GT supplier", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void brSameHotelAndToDiffAndIgnoreGT(String runMode, String testcase, String tenantName, String city,
			String hotel, String startDate, String endDate, String tripIds, String toGTProvider, String fromGTProvider,
			String hotelconfirmationValue, String roomRate, String notes, String vendorNotes,
			String gtconfirmationValue, String gtRoomRate, String gtNotes, String gtvendorNotes, String paymentMethod,
			String operatingDate, String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testcase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(hotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(hotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(hotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes, gtvendorNotes);
			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(notes);
			pgWrapper.pageFindTrip.verifyNotesForTrips(vendorNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotel, toGTProvider, requiredPDFLinks);
			System.out.println(fileName);
			String pdfs[] = fileName.split(",");
			String hotelPDF = pdfs[0];
			String gtPDF = pdfs[1];
			pgWrapper.pageFindTrip.readPDF(hotelPDF, vendorNotes);
			pgWrapper.pageFindTrip.readPDF(gtPDF, gtvendorNotes);
		}
	}

	/*
	 * 1. Login to Aces2 application 2. Select Tenant "Tenant Name" 3. Click on
	 * OpsDesk Menu 4. Click on Schedules Link 5. Click on Hotel Schedules Link =>
	 * Hotel Schedule page is displayed 6. Enter City, Hotel, Start date, End date
	 * and click on Search button => Reservations available with in the selected
	 * time frame is displayed 7. Select required trips to reassign and click on
	 * Bulk Reassign button => Bulk Reassign page is displayed 8. Select radio
	 * button of same hotel supplier and also select same Hotel provider in GT
	 * Provider - To and From Hotel 9. Click on Next Button -> Service provider
	 * Details and Contract Information page is displayed 10. Fill hotel
	 * Confirmation Number, Room rate, Notes, Vendor Notes and click on Finish
	 * Button => Bulk REASSIGN sUMMARYR PAGE IS DISPLAYED 11. Click on Find Trip 12.
	 * Search for trip using trip number and operating date 13. Click on Trip link
	 * 14. Click on Show Notes link of required trip 15. Verify Notes and Vendor
	 * notes matches
	 */

	@Test(description = "#ATA-391 Verify with all the payment modes while selecting same hotel supplier,"
			+ " #ATA-393 Verify the Notes and Comments for Vendor Notes while reassigning existing reservations", groups = {
					"Regression" }, dataProvider = "TestDataFile")
	public void diffPaymentMethods(String runMode, String testCase, String tenantName, String city, String hotel,
			String startDate, String endDate, String tripIds, String toGTProvider, String fromGTProvider,
			String hotelconfirmationValue, String roomRate, String notes, String vendorNotes, String paymentMethod,
			String airlineCardNum, String synapticCardNum, String synapticCSVNum, String synapticCardExpiryDate,
			String synapticMerchantLogValue, String operatingDate, String TextToVerifyInPDF, String supplierType,
			String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testCase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(hotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(hotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(hotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
			if (paymentMethod.equalsIgnoreCase("API Credit Card")) {
				pgWrapper.pageHotelSchedules.apicreditCard();
			} else if (paymentMethod.equalsIgnoreCase("Airline Credit Card")) {
				pgWrapper.pageHotelSchedules.airlineCreditCard(airlineCardNum);
			} else if (paymentMethod.equalsIgnoreCase("Synaptic Card")) {
				pgWrapper.pageHotelSchedules.synapticCard(synapticCardNum, synapticCSVNum, synapticCardExpiryDate,
						synapticMerchantLogValue);
			}

			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(notes);
			pgWrapper.pageFindTrip.verifyNotesForTrips(vendorNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotel, toGTProvider, requiredPDFLinks);
			pgWrapper.pageFindTrip.readPDF(fileName, TextToVerifyInPDF);
		}
	}

	@Test(description = "#ATA-396 Verify with all the payment modes while selecting same hotel supplier and different GT suppliers", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void diffPaymentMethodsDiffGTs(String runMode, String testCase, String tenantName, String city, String hotel,
			String startDate, String endDate, String tripIds, String toGTProvider, String fromGTProvider,
			String hotelconfirmationValue, String roomRate, String notes, String vendorNotes, String paymentMethod,
			String gtconfirmationValue, String gtRoomRate, String gtNotes, String gtvendoreNotes, String airlineCardNum,
			String synapticCardNum, String synapticCSVNum, String synapticCardExpiryDate,
			String synapticMerchantLogValue, String operatingDate, String TextToVerifyInPDF, String TextToVerifyInGTPDF,
			String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testCase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(hotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(hotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(hotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			if (paymentMethod.equalsIgnoreCase("API Credit Card")) {
				pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
				pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
				pgWrapper.pageHotelSchedules.apicreditCard();
				pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes,
						gtvendoreNotes);
				pgWrapper.pageHotelSchedules.selectPaymentMethod_GT(paymentMethod);
				pgWrapper.pageHotelSchedules.apicreditCard_GT();
			} else if (paymentMethod.equalsIgnoreCase("Airline Credit Card")) {
				pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
				pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
				pgWrapper.pageHotelSchedules.airlineCreditCard(airlineCardNum);
				pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes,
						gtvendoreNotes);
				pgWrapper.pageHotelSchedules.selectPaymentMethod_GT(paymentMethod);
				pgWrapper.pageHotelSchedules.airlineCreditCard_GT(airlineCardNum);
			} else if (paymentMethod.equalsIgnoreCase("Synaptic Card")) {
				pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
				pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
				pgWrapper.pageHotelSchedules.synapticCard(synapticCardNum, synapticCSVNum, synapticCardExpiryDate,
						synapticMerchantLogValue);
				pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes,
						gtvendoreNotes);
				pgWrapper.pageHotelSchedules.selectPaymentMethod_GT(paymentMethod);
				pgWrapper.pageHotelSchedules.synapticCard_GT(synapticCardNum, synapticCSVNum, synapticCardExpiryDate,
						synapticMerchantLogValue);
			}

			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(notes);
			pgWrapper.pageFindTrip.verifyNotesForTrips(vendorNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotel, toGTProvider, requiredPDFLinks);
			System.out.println(fileName);
			String pdfs[] = fileName.split(",");
			String hotelPDF = pdfs[0];
			String gtPDF = pdfs[1];
			pgWrapper.pageFindTrip.readPDF(hotelPDF, TextToVerifyInPDF);
			pgWrapper.pageFindTrip.readPDF(gtPDF, TextToVerifyInGTPDF);
		}
	}

	@Test(description = "Different Hotel and 3rd party GT", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void brDiffHotelAndToDiffGT(String runMode, String tenantName, String city, String hotel, String startDate,
			String endDate, String tripIds, String diffHotel, String toGTProvider, String fromGTProvider,
			String hotelconfirmationValue, String roomRate, String notes, String vendorNotes,
			String gtconfirmationValue, String gtRoomRate, String gtNotes, String gtvendorNotes, String paymentMethod,
			String operatingDate, String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Bulk Reassign Different Hotel and 3rd party GT");
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(diffHotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(diffHotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(diffHotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
			pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes, gtvendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod_GT(paymentMethod);
			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(diffHotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(notes);
			pgWrapper.pageFindTrip.verifyNotesForTrips(vendorNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotel, toGTProvider, requiredPDFLinks);
			System.out.println(fileName);
			String pdfs[] = fileName.split(",");
			String hotelPDF = pdfs[0];
			String gtPDF = pdfs[1];
			pgWrapper.pageFindTrip.readPDF(hotelPDF, vendorNotes);
			pgWrapper.pageFindTrip.readPDF(gtPDF, gtvendorNotes);
		}
	}

	@Test(description = "#ATA-392 Verify the Bulk Cancellation while selecting same hotel supplier along with different GT supplier, Different hotel and same/different GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelSameHotelDiffGT(String runMode, String testCase, String tenantName, String city, String hotel,
			String startDate, String endDate, String tripIds, String toGTProvider, String fromGTProvider,
			String hotelconfirmationValue, String roomRate, String notes, String vendorNotes, String paymentMethod,
			String gtconfirmationValue, String gtRoomRate, String gtNotes, String gtvendorNotes, String tabCount,
			String gtCancellationNumber, String gtCancellationNotes, String gtCancellationCommentsForVendor,
			String operatingDate, String textToVerifyInShowNotes, String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testCase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(hotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(hotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(hotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
			pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes, gtvendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod_GT(paymentMethod);
			pgWrapper.pageHotelSchedules.gtBulkCancellation(tabCount, gtCancellationNumber, gtCancellationNotes,
					gtCancellationCommentsForVendor);
			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(hotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(textToVerifyInShowNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(hotel, toGTProvider, requiredPDFLinks);
			System.out.println(fileName);
			String pdfs[] = fileName.split(",");
			String hotelPDF = pdfs[0];
			String gtPDF = pdfs[1];
			pgWrapper.pageFindTrip.readPDF(hotelPDF, vendorNotes);
			pgWrapper.pageFindTrip.readPDF(gtPDF, gtvendorNotes);
		}
	}

	@Test(description = "#ATA-392 Verify the Bulk Cancellation while selecting Different hotel and same GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelDiffHotelSameGT(String runMode, String testCase, String tenantName, String city, String hotel,
			String startDate, String endDate, String tripIds, String differentHotel, String toGTProvider,
			String fromGTProvider, String hotelconfirmationValue, String roomRate, String notes, String vendorNotes,
			String paymentMethod, String hotelCancellationNumber, String hotelCancellationNotes,
			String hotelCancellationCommentsForVendor, String tabCount, String gtCancellationNumber,
			String gtCancellationNotes, String gtCancellationCommentsForVendor, String operatingDate,
			String textToVerifyInShowNotes, String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testCase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(differentHotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(differentHotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(differentHotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod(paymentMethod);
			pgWrapper.pageHotelSchedules.hotelBulkCancellation(hotelCancellationNumber, hotelCancellationNotes,
					hotelCancellationCommentsForVendor);
			pgWrapper.pageHotelSchedules.clickOnGTTabCancel();
			pgWrapper.pageHotelSchedules.gtBulkCancellation(tabCount, gtCancellationNumber, gtCancellationNotes,
					gtCancellationCommentsForVendor);
			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(differentHotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(textToVerifyInShowNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(differentHotel, toGTProvider,
					requiredPDFLinks);
			System.out.println(fileName);
			String pdfs[] = fileName.split(",");
			String hotelPDF = pdfs[0];
			pgWrapper.pageFindTrip.readPDF(hotelPDF, vendorNotes);
		}
	}

	@Test(description = "#ATA-392 Verify the Bulk Cancellation while selecting Different hotel and Different GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelDiffHotelDiffGT(String runMode, String testCase, String tenantName, String city, String hotel,
			String startDate, String endDate, String tripIds, String differentHotel, String toGTProvider,
			String fromGTProvider, String hotelconfirmationValue, String roomRate, String notes, String vendorNotes,
			String confirmationValue, String gtRoomRate, String gtNotes, String gtvendorNotes, String paymentMethod,
			String hotelCancellationNumber, String hotelCancellationNotes, String hotelCancellationCommentsForVendor,
			String tabCount, String gtCancellationNumber, String gtCancellationNotes,
			String gtCancellationCommentsForVendor, String operatingDate, String textToVerifyInShowNotes,
			String requiredPDFLinks) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testCase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			pgWrapper.pageHotelSchedules.selectRadioBtn(differentHotel);
			pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(differentHotel, toGTProvider);
			pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(differentHotel, fromGTProvider);
			pgWrapper.pageHotelSchedules.clickOnNextBtn();
			pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			pgWrapper.pageHotelSchedules.enterDetailsForGT(confirmationValue, gtRoomRate, gtNotes, gtvendorNotes);
			pgWrapper.pageHotelSchedules.selectPaymentMethod_GT2(paymentMethod);
			pgWrapper.pageHotelSchedules.hotelBulkCancellation(hotelCancellationNumber, hotelCancellationNotes,
					hotelCancellationCommentsForVendor);
			pgWrapper.pageHotelSchedules.clickOnGTTabCancel();
			pgWrapper.pageHotelSchedules.gtBulkCancellation(tabCount, gtCancellationNumber, gtCancellationNotes,
					gtCancellationCommentsForVendor);
			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.opsDeskMenu.clickFindTripLink();
			List<String> reservationId = Arrays.asList(tripIds.split("/"));
			pgWrapper.pageFindTrip.findTrips(reservationId.get(0), operatingDate);
			pgWrapper.pageFindTrip.clickOnRequiredTrip(reservationId.get(0).trim());
			pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(differentHotel, "Booked");
			pgWrapper.pageFindTrip.verifyNotesForTrips(textToVerifyInShowNotes);
			String fileName = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(differentHotel, toGTProvider,
					requiredPDFLinks);
			System.out.println(fileName);
			String pdfs[] = fileName.split(",");
			String hotelPDF = pdfs[0];
			String gtPDF = pdfs[1];
			pgWrapper.pageFindTrip.readPDF(hotelPDF, vendorNotes);
			pgWrapper.pageFindTrip.readPDF(gtPDF, gtvendorNotes);
		}
	}

	@Test(description = "Verify the Proforma Invoice reprocessed with Same Hotel, Same GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void proformaInvoice_BulkReassign(String runMode, String testCase, String tenantName, String city,
			String hotel, String startDate, String endDate, String tripIds, String hotelconfirmationValue,
			String roomRate, String notes, String vendorNotes, String serviceType, String billingPeriod,
			String textToVerifyInProformaInvoice, String differentHotel, String toGTProvider, String fromGTProvider,
			String gtconfirmationValue, String gtRoomRate, String gtNotes, String gtvendorNotes) throws Exception {
		if (runMode.equals("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, testCase);
			pgWrapper = LocalDriverManager.getPageWrapper();
			pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickSchedulesLink();
			pgWrapper.pageHotelSchedules.clickHotelSchedulesLink();
			pgWrapper.pageHotelSchedules.findSchduledHotels(city, hotel, startDate, endDate);
			pgWrapper.pageHotelSchedules.selectTrips(tripIds);
			pgWrapper.pageHotelSchedules.clickOnBulkReassignBtn();
			if (serviceType.equalsIgnoreCase("Hotel")) {
				pgWrapper.pageHotelSchedules.selectRadioBtn(hotel);
				pgWrapper.pageHotelSchedules.clickOnNextBtn();
				pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
			} else if (serviceType.equalsIgnoreCase("GT")) {
				pgWrapper.pageHotelSchedules.selectRadioBtn(differentHotel);
				pgWrapper.pageHotelSchedules.selectGTProvider2ToHotel(differentHotel, toGTProvider);
				pgWrapper.pageHotelSchedules.selectGTProvider2FromHotel(differentHotel, fromGTProvider);
				pgWrapper.pageHotelSchedules.clickOnNextBtn();
				pgWrapper.pageHotelSchedules.enterDetailsForHotel(hotelconfirmationValue, roomRate, notes, vendorNotes);
				pgWrapper.pageHotelSchedules.enterDetailsForGT(gtconfirmationValue, gtRoomRate, gtNotes, gtvendorNotes);
			}
			pgWrapper.pageHotelSchedules.clickOnFinish();
			pgWrapper.pageDashBoard.checkForAlertBox();
			pgWrapper.pageHotelSchedules.verifyBulkReassignSummary();
			pgWrapper.pageHome.clickAccountingLink();
			pgWrapper.accountingMenu.clickCreateProformaInvoicesLink();
			if (serviceType.equalsIgnoreCase("Hotel"))
				pgWrapper.pageCreateProfarmaInvoices.createProformaInvoiceSingle(serviceType, city, hotel,
						billingPeriod);
			if (serviceType.equalsIgnoreCase("GT"))
				pgWrapper.pageCreateProfarmaInvoices.createProformaInvoiceSingle(serviceType, city, toGTProvider,
						billingPeriod);
			pgWrapper.pageCreateProfarmaInvoices.clickOnCreateInvoiceBtnSingleInvoice();
			pgWrapper.pageCreateProfarmaInvoices.waitForInProgressToComplete();
			String invoiceNumber = pgWrapper.pageCreateProfarmaInvoices.clickOnProformaInvoiceAndDownload();
			String proformaPDF = invoiceNumber.replace("P", "Proforma").concat(".pdf");
			System.out.println(proformaPDF);
			pgWrapper.pageFindTrip.readPDF(proformaPDF, textToVerifyInProformaInvoice);
		}
	}
	/*
	 * Bulk Reassign Ends
	 */

	/*
	 * Supplier Confirmation Portal
	 */

	@Test(description = "Supplier Confirmation Portal", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void confirmationInSupplierPortal(String runMode, String sftpIndicator, String tenantName,
			String timeFrameFilterValue, String timeFormatValue, String refreshIntervalValue, String alertType,
			String tripId, String gtProviderValue, String confirmationCode, String status, String toGTProviderValue,
			String fromGTProviderValue, String hotelRateValue, String toHotelGTRate, String fromHotelGTRate,
			String paymentMethod, String expectedStatus, String startDateValue, String endDateValue, String logFileName,
			String scenarioFolderName, String sftpFolderPath, String command, String keyWordInLogs) throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (sftpIndicator.equals("Yes")) {
				pgWrapper.genericClass.uploadFileToSFTP(sftpFolderPath, scenarioFolderName);
				pgWrapper.genericClass.verifylogsInPutty(sftpFolderPath, command, keyWordInLogs);
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, "Supplier Confirmation For " + alertType);
			String tripStartDate = "";
			String cityAndHotelName = "";
			String hotelName = "";
			String city = "";
			if (alertType.contains("Business")) {
				getDriver().get(airlinesUrl);
				pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName + "Username"),
						readPropValue(tenantName + "Password"));

				// logger.info("**** Create a reservation in request reservation
				// page ");
				pgWrapper.operationsTab.clickOnBusinessTravelLink();
				city = pgWrapper.requestReservationPage.requestReservationPage("ABI", "Local", "05:30", "23:40", "MQ",
						"577", "MQ", "478", "test@gmail.com");
				pgWrapper.requestReservationPage.hotel("Other");
				pgWrapper.requestReservationPage.accountType("2");
				pgWrapper.requestReservationPage.selectSingleRoomTypeAndEnterDetails("37291");
				hotelName = pgWrapper.requestReservationPage.hotelDescription("MCM Elegante Suites ABI");
				pgWrapper.requestReservationPage.clickOnSendToAPI();
				String reservId = pgWrapper.requestReservationPage.processingRequest();
				Thread.sleep(5000);
				tripId = reservId;
				getDriver().get(aces2Url);
			}
			pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.filterByAlertType(alertType);
			pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
			switch (alertType) {
			case "Limo":
				tripStartDate = pgWrapper.pageDashBoard.getStartDate();
				cityAndHotelName = pgWrapper.pagePendingConfirmations.selectHotelProvider();
				hotelName = cityAndHotelName.split(",")[0];
				city = cityAndHotelName.split(",")[1];
				pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue, fromGTProviderValue, "");
				pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
				pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
				break;

			case "Day Rooms":
				// pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue,
				// fromGTProviderValue,"");
				tripStartDate = pgWrapper.pageDashBoard.getStartDate();
				cityAndHotelName = pgWrapper.pagePendingConfirmations.selectHotelProvider();
				hotelName = cityAndHotelName.split(",")[0];
				city = cityAndHotelName.split(",")[1];
				pgWrapper.pagePendingConfirmations.selectGTLink();
				pgWrapper.pagePendingConfirmations.clickNextButton();
				pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
				pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
				break;

			case "Layover Hotel":
				tripStartDate = pgWrapper.pageDashBoard.getStartDate();
				cityAndHotelName = pgWrapper.pagePendingConfirmations.selectHotelProvider();
				hotelName = cityAndHotelName.split(",")[0];
				city = cityAndHotelName.split(",")[1];
				pgWrapper.pagePendingConfirmations.selectGTProvider(toGTProviderValue, fromGTProviderValue, "");
				pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
				pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
				break;

			case "Business":
				pgWrapper.pagePendingConfirmations.hotelProvider();
				pgWrapper.pageDashBoard.selectPaymentDetails_BT(paymentMethod);
				pgWrapper.pagePendingConfirmations.enterOverrideRate(hotelRateValue);
				break;

			default:
				Assert.fail("No alert Found to proceed");
				break;
			}
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			pgWrapper.pagePendingConfirmations.checkForAlertBox();
			pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
			pgWrapper.pagePendingConfirmations.findTrip(tripId);
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, "ABI", "MCM Elegante Suites ABI");
			pgWrapper.aces3SupplierHomePage.clickOnReservationsLink();
			pgWrapper.aces3SupplierReservationsPage.clickOnPendingConfirmationTab();
			pgWrapper.aces3SupplierReservationsPage.enterConfirmationNumberForReserv(tripId, confirmationCode);
			pgWrapper.aces3SupplierReservationsPage.clickOnFindReservationTab();
			pgWrapper.aces3SupplierReservationsPage.findReservationUsingConfirmation(confirmationCode, startDateValue,
					endDateValue);
			pgWrapper.aces3SupplierReservationsPage.verifyStatus(tripId, expectedStatus);
			pgWrapper.aces3SupplierReservationsPage.verifyNotesOfReservation(confirmationCode, alertType);
			verifylogsInPutty(logFileName, tripId);
		}
	}

	@Test(description = "Supplier Confirmation Portal - Cancellation", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void cancellationInSupplierPortal(String runMode, String tenantName, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String alertType, String tripId, String operatingDate,
			String city, String hotelName, String confirmationCode, String startDateValue, String endDateValue,
			String expectedStatus, String paymentMethod, String hotelRateValue, String logFileName) throws Exception {
		if (runMode.equals("Yes")) {
			pgWrapper = LocalDriverManager.getPageWrapper();
			if (alertType.contains("Business")) {
				getDriver().get(airlinesUrl);
				pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName + "Username"),
						readPropValue(tenantName + "Password"));

				// logger.info("**** Create a reservation in request reservation
				// page ");
				pgWrapper.operationsTab.clickOnBusinessTravelLink();
				city = pgWrapper.requestReservationPage.requestReservationPage("ABI", "Local", "05:30", "23:40", "MQ",
						"577", "MQ", "478", "test@gmail.com");
				pgWrapper.requestReservationPage.hotel("Other");
				pgWrapper.requestReservationPage.accountType("2");
				pgWrapper.requestReservationPage.selectSingleRoomTypeAndEnterDetails("37291");
				hotelName = pgWrapper.requestReservationPage.hotelDescription("MCM Elegante Suites ABI");
				pgWrapper.requestReservationPage.clickOnSendToAPI();
				String reservId = pgWrapper.requestReservationPage.processingRequest();
				Thread.sleep(5000);
				tripId = reservId;
				getDriver().get(aces2Url);
				pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
				pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
				pgWrapper.pageHome.clickOPSDeskLink();
				pgWrapper.opsDeskMenu.clickDashBoardLink();
				pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
						refreshIntervalValue);
				pgWrapper.pageDashBoard.filterByAlertType(alertType);
				pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
				pgWrapper.pagePendingConfirmations.hotelProvider();
				pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
				pgWrapper.pageDashBoard.selectPaymentDetails_BT(paymentMethod);
				pgWrapper.pagePendingConfirmations.enterOverrideRate(hotelRateValue);
				pgWrapper.pagePendingConfirmations.clickFinishButton();
				pgWrapper.pagePendingConfirmations.checkForAlertBox();
				pgWrapper.opsDeskMenu.clickReservationsLink();
				pgWrapper.pageFindReservation.clickFindReservationLink();
				pgWrapper.pageFindReservation.findReservation("Hotel", tripId);
				pgWrapper.pageFindReservation.clickReassignSupplier();
				pgWrapper.pagePendingConfirmations.hotelProvider();
				pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
				pgWrapper.pageDashBoard.selectPaymentDetails_BT(paymentMethod);
				pgWrapper.pagePendingConfirmations.enterOverrideRate(hotelRateValue);
				pgWrapper.pagePendingConfirmations.clickFinishButton();
				pgWrapper.pagePendingConfirmations.checkForAlertBox();
				pgWrapper.pageHome.clickLogoutLink();
				getDriver().get(aces2Url);
			}
			pgWrapper.pageLoginACESII.acesIILoginDetails(readPropValue("username"), readPropValue("password"));
			pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
			pgWrapper.pageHome.clickOPSDeskLink();
			if (!alertType.equalsIgnoreCase("Business")) {
				pgWrapper.opsDeskMenu.clickFindTripLink();
				pgWrapper.pageFindTrip.findTrips(tripId, operatingDate);
				pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
				pgWrapper.pageFindTrip.clickOnAssignOrReassignLink();
				/* Reassigning hotel Name and To, From GTs of last Trip only */
				pgWrapper.pagePendingConfirmations.hotelProvider();
				pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
				pgWrapper.pagePendingConfirmations.selectGTProvider("1", "1", "");
				pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
				pgWrapper.pageDashBoard.selectPaymentMethod(paymentMethod);
				pgWrapper.pagePendingConfirmations.clickFinishButton();
			}
			pgWrapper.opsDeskMenu.clickDashBoardLink();
			pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
					refreshIntervalValue);
			pgWrapper.pageDashBoard.searchPCWithTripNumber(tripId);
			pgWrapper.pagePendingConfirmations.clickFinishButton();
			pgWrapper.pagePendingConfirmations.checkForAlertBox();
			getDriver().get(aces3SupplierUrl);
			pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
					readPropValue("aces3SupplierPassword"));
			pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, hotelName);
			pgWrapper.aces3SupplierHomePage.clickOnReservationsLink();
			pgWrapper.aces3SupplierReservationsPage.clickOnPendingConfirmationTab();
			pgWrapper.aces3SupplierReservationsPage.enterCancelConfirmNumberForReserv(tripId, confirmationCode);
			pgWrapper.aces3SupplierReservationsPage.clickOnFindReservationTab();
			pgWrapper.aces3SupplierReservationsPage.findReservationUsingConfirmation(confirmationCode, startDateValue,
					endDateValue);
			pgWrapper.aces3SupplierReservationsPage.verifyStatus(tripId, expectedStatus);
			pgWrapper.aces3SupplierReservationsPage.verifyNotesOfReservation(confirmationCode, alertType);
			verifylogsInPutty(logFileName, tripId);

		}
	}

	/*
	 * Supplier Confirmation Portal Ends
	 */

	/*
	 * Synaptics Start here
	 */
	// UPDATE test data - change trip-ids in pairing files and test data files.
	@Test(description = "ATA-417 During reuse of Available reservation, verify that the Synaptic payment details info is inherited to reusing PA", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifySynaptic(String runMode, String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue, String supplierType, String status, String scenario,
			String processType, String textToVerifyInNotes) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				bookAddPairing1TripsSynaptic(sshIndicator, scenarioFolderName, tenantName, reservationId,
						checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, ccNumberValue,
						ccCSVNumberValue, ccExpiryDate, merchantLogValue, status, supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.verifyAddPairing2TripsSynaptic(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario, confirmationCode, ccNumberValue);
			} else if (processType.equalsIgnoreCase("revisePairing")) {
				pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario, processType, textToVerifyInNotes);
			} else
				Assert.fail("ProcessType Not found");
		}
	}

	private void bookAddPairing1TripsSynaptic(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue, String status, String supplierType, String scenario)
			throws Exception {
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
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
		ExtentTestManager.getTest().log(LogStatus.INFO, "Completed Booking PA " + reservationId);
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

	// UPDATE test data - change trip-ids and checInDateValue in pairing files
	// and test data files.
	@Test(description = "ATA-419  When PC is auto reused (hotel-provided GT), verify that Synaptic Payment details are inherited to reusing PA", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifySynapticAutoReuseHTGT(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue, String supplierType, String status, String scenario,
			String processType, String textToVerifyInNotes) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				pgWrapper.genericClass.bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName,
						tenantName, reservationId, checkInDateValue, hotelName, confirmationCode, hotelRateValue,
						paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status,
						supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.verifyAddPairing2TripsSynaptic(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario, confirmationCode, ccNumberValue);
			} else if (processType.equalsIgnoreCase("revisePairing")) {
				pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario, processType, textToVerifyInNotes);
			} else
				Assert.fail("ProcessType Not found");
		}
	}

	// UPDATE test data - change trip-ids and checInDateValue in pairing files
	// and test data files.
	@Test(description = "ATA-420  When PC is auto reused (3rd Party GT), verify that Synaptic Payment details are inherited to reusing PA", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifySynapticAutoReuseThirdPGT(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod,
			String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate, String merchantLogValue,
			String supplierType, String status, String scenario, String processType, String textToVerifyInNotes)
			throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				bookAddPairing1TripsSynapticAutoReuseHTGT(sshIndicator, scenarioFolderName, tenantName, reservationId,
						checkInDateValue, hotelName, confirmationCode, hotelRateValue, toHotelGTRate, fromHotelGTRate,
						paymentMethod, ccNumberValue, ccCSVNumberValue, ccExpiryDate, merchantLogValue, status,
						supplierType, scenario);
			} else if (processType.equalsIgnoreCase("deletePairing")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario);
			} else if (processType.equalsIgnoreCase("addPairing2")) {
				pgWrapper.genericClass.verifyAddPairing2TripsSynaptic(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario, confirmationCode, ccNumberValue);
			} else if (processType.equalsIgnoreCase("revisePairing")) {
				pgWrapper.genericClass.verifyRevisePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, status, scenario, processType, textToVerifyInNotes);
			} else
				Assert.fail("ProcessType Not found");
		}
	}

	private void bookAddPairing1TripsSynapticAutoReuseHTGT(String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod,
			String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate, String merchantLogValue, String status,
			String supplierType, String scenario) throws Exception {
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
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
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
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

	// UPDATE test data - change trip-ids and checInDateValue in pairing files
	// and test data files.
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
				verifyAddPairing3CrewTracSynaptic(sshIndicator, scenarioFolderName, tenantName, reservationId,
						checkInDateValue, hotelName, confirmationCode, hotelRateValue, paymentMethod, ccNumberValue,
						ccCSVNumberValue, ccExpiryDate, merchantLogValue, supplierType, status, scenario);
			}

			else
				Assert.fail("ProcessType Not found");
		}
	}

	private void verifyAddPairing3CrewTracSynaptic(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String paymentMethod, String ccNumberValue, String ccCSVNumberValue,
			String ccExpiryDate, String merchantLogValue, String supplierType, String status, String scenario)
			throws Exception {
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
		// if (tenantName.equalsIgnoreCase("LAN Airlines"))
		// getDriver().get(acesLatamUrl);
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

	@Test(description = "ATA-, Verify the Synaptic Payment details displayed on Fax generated (3rd Party GT)", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void SynapticPymt3rdPartyGT(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String tripId, String selectHotelProvider, String confirmationCode,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod,
			String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate, String merchantLogValue, String gtStaus,
			String verifyOnPDF) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		pgWrapper.pagePendingConfirmations.selectReqHotel(selectHotelProvider);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectGTLink();
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethodSynaptic(paymentMethod, ccNumberValue, ccCSVNumberValue,
				ccExpiryDate, merchantLogValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripId, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtStaus);
		String getGTName = pgWrapper.pageFindTrip.clickOnShowNotesOfReqHotelOrGT(gtStaus);
		pgWrapper.pageFindTrip.verifyNotesForRequiredText(confirmationCode);
		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", getGTName, "1");
		System.out.println(filename);
		String pdfs[] = filename.split(",");
		String gtPDF = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(gtPDF, verifyOnPDF);

	}

	@Test(description = "ATA-, Verify the Synaptic Payment details displayed on Fax generated (Hotel-provided GT)", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void SynapticPymtHotelProvidedGT(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String tripId, String selectHotelProvider, String confirmationCode,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethod,
			String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate, String merchantLogValue, String gtStaus,
			String verifyOnPDF) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		System.out.println("tripStartDate :" + tripStartDate);
		pgWrapper.pagePendingConfirmations.selectReqHotel(selectHotelProvider);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethodSynaptic(paymentMethod, ccNumberValue, ccCSVNumberValue,
				ccExpiryDate, merchantLogValue);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripId, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtStaus);
		pgWrapper.pageFindTrip.clickOnShowNotesOfReqHotelOrGT(gtStaus);
		pgWrapper.pageFindTrip.verifyNotesForRequiredText(confirmationCode);
		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload(selectHotelProvider, "", "1");
		String pdfs[] = filename.split(",");
		String hotelPDF = pdfs[0];
		pgWrapper.pageFindTrip.readPDF(hotelPDF, verifyOnPDF);

	}

	@Test(description = "ATA-, Verify using Synaptic Payment method only for one of the Suppliers(GT)", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void SynapticPymtOneSupplierGT(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String tripId, String selectHotelProvider, String confirmationCode,
			String reqToGTValue, String toGTProviderValue, String reqFromGTValue, String fromGTProviderValue,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String paymentMethodHotel,
			String gt1PaymentMethod, String ccNumberValue, String ccCSVNumberValue, String ccExpiryDate,
			String merchantLogValue, String gt2PaymentMethod, String airlineCCType, String gtStaus, String verifyOnPDF)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		String tripStartDate = pgWrapper.pageDashBoard.getStartDate();
		System.out.println("tripStartDate :" + tripStartDate);
		pgWrapper.pagePendingConfirmations.selectReqHotelName(selectHotelProvider);
		pgWrapper.pagePendingConfirmations.enterConfirmationCode(confirmationCode);
		pgWrapper.pagePendingConfirmations.selectReqToGTProvider(reqToGTValue, toGTProviderValue);
		pgWrapper.pagePendingConfirmations.selectReqFromGTProvider(reqFromGTValue, fromGTProviderValue);
		pgWrapper.pagePendingConfirmations.clickNextButton();
		pgWrapper.pagePendingConfirmations.addHotelRate(hotelRateValue);
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pageDashBoard.selectPaymentMethodSynapticSingleGT(paymentMethodHotel, gt1PaymentMethod, ccNumberValue,
				ccCSVNumberValue, ccExpiryDate, merchantLogValue, gt2PaymentMethod, airlineCCType);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripId, tripStartDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.verifyGTAndStatus(gtStaus);
		pgWrapper.pageFindTrip.clickOnShowNotesOfReqSupplier(toGTProviderValue, gtStaus);
		pgWrapper.pageFindTrip.verifyNotesForTrips(confirmationCode);
		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", toGTProviderValue, "1");
		String pdfs[] = filename.split(",");
		String gtPDF = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(gtPDF, verifyOnPDF);
	}

	/*
	 * Synaptics End here
	 */

	/*
	 * Bulk Cancellation
	 */

	@Test(description = "ATA-399,400 Verify Bulk Cancel of PCs available on Dashboard", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelMultipleTrips(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String hotelServiceType, String gtServiceType, String confirmationCode,
			String OperationDate, String gtStatus, String operationDate2) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		// pgWrapper.pageDashBoard.selectMultipleTenants("Cathay Pacific");
		String hotelTripID[] = pgWrapper.pageDashBoard.selectServiceType(gtServiceType);
		//// String gtTripID[] =
		//// pgWrapper.pageDashBoard.selectServiceType(gtServiceType);f
		System.out.println("Selected Trip Ids are " + hotelTripID[0] + " , " + hotelTripID[1]);
		pgWrapper.pageDashBoard.clickBulkCancelBtn();
		pgWrapper.pageDashBoard.enterHotelCancelConfirmCode(confirmationCode);
		pgWrapper.pageDashBoard.enterGTCancelConfirmCode(confirmationCode);
		pgWrapper.pageDashBoard.clickOnFinishCancelAllButton();
		pgWrapper.pageDashBoard.clickOnFinishButton();

		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(hotelTripID[0], OperationDate);
		pgWrapper.pageFindTrip.clickOnGivenTrip(hotelTripID[0]);
		pgWrapper.pageFindTrip.verifyGTAndStatus2(gtStatus);
		String getGTName = pgWrapper.pageFindTrip.clickOnShowNotesOfReqHotelOrGT2(gtStatus);
		pgWrapper.pageFindTrip.verifyNotesForRequiredText(confirmationCode);
		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", getGTName, "1");
		System.out.println(filename);
		String pdfs[] = filename.split(",");
		String gtPDF = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(gtPDF, confirmationCode);

		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(hotelTripID[1], operationDate2);
		pgWrapper.pageFindTrip.clickOnGivenTrip(hotelTripID[1]);
		pgWrapper.pageFindTrip.verifyGTAndStatus2(gtStatus);
		String getGTName1 = pgWrapper.pageFindTrip.clickOnShowNotesOfReqHotelOrGT2(gtStatus);
		pgWrapper.pageFindTrip.verifyNotesForRequiredText(confirmationCode);
		/* verify the text on downloaded PDF file */
		String filename1 = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", getGTName1, "1");
		System.out.println(filename1);
		String pdfs1[] = filename.split(",");
		String gtPDF1 = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(gtPDF, confirmationCode);

	}

	@Test(description = "ATA-401,402, Verify Partial Cancel of GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelHotelPartial(String tenantName, String timeFrameFilterValue, String timeFormatValue,
			String refreshIntervalValue, String hotelServiceType, String tripIds, String operationDate,
			String pccStatus, String confirmatonCode) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		/*
		 * select trip with Hotel Service Type and click on Bulk Cancel button
		 */
		pgWrapper.pageDashBoard.filterByAlertTypeCancelAlertList(hotelServiceType);
		pgWrapper.pageDashBoard.tripSelection(tripIds);
		pgWrapper.pageDashBoard.clickBulkCancelBtn();
		// String tripIds1 = "J5R65A";
		pgWrapper.pageDashBoard.unCheckGTOnBulkCancellationPage();

		/*
		 * without confirmation code proceed clicking Finish Cancel All Button
		 */
		pgWrapper.pageDashBoard.clickOnFinishCancelAllButton();
		pgWrapper.pageDashBoard.clickOnFinishButton();

		/* verify the status of the trip by searching the trip */
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripIds, operationDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.clickOnShowNotesOfTrip(pccStatus);

		/*
		 * go to Pending Confirmations Page and provide the confirmation code to the
		 * trip
		 */
		pgWrapper.opsDeskMenu.clickPendingConfirmationsLink();
		// String tripIds2 = "J2482/15";
		pgWrapper.pagePendingConfirmations.clickOnTripId(tripIds);
		pgWrapper.pagePendingConfirmations.confirmPendingCancellation(confirmatonCode);
		//
		/* verify the status of the trip by searching the trip */
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(tripIds, operationDate);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		String getGTName = pgWrapper.pageFindTrip.clickOnShowNotesOfReqHotelOrGT(pccStatus);
		pgWrapper.pageFindTrip.verifyNotesForRequiredText(confirmatonCode);

		/* verify the text on downloaded PDF file */
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", getGTName, "1");
		// String filename = "30370539-2020-07-31.pdf";
		pgWrapper.pageFindTrip.readPDF(filename, "CANCELLED");

	}

	@Test(description = "ATA-404, Verify bulk cancellation for BT Service type", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelBT(String tenantName, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String empID, String empName, String timeFrameFilterValue,
			String refreshIntervalValue, String confirmCode, String paymentDetails, String serviceType,
			String bookStatus, String hotelChange, String cancellationStatus) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();
		// Go to Airlines and create Reservation
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		String reservationId = createBTJB(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, empID, empName);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);

//		// Go to ACES2 main site and verify that created reservation is shown in Ops Dashboard
//		getDriver().get(aces2Url);
//		// String reservationId ="1102682";
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Started processing the following : " + reservationId + " reservation.");
//		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
//		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
//		pgWrapper.pageHome.clickOPSDeskLink();
//		pgWrapper.opsDeskMenu.clickDashBoardLink();
//		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
//		// Process the PA
//		pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
//		pgWrapper.pageDashBoard.clickOnReservationLink();
//		pgWrapper.pageDashBoard.enterBTConfirmationCode(confirmCode);
//		pgWrapper.pageDashBoard.selectPaymentDetails_BT(paymentDetails);
//		pgWrapper.pageDashBoard.clickOnFinishButton();
//		String fromDate = currentDate();
//
//		// Find the status of the trip it should be Booked and change assign different
//		// supplier and book it
//		pgWrapper.opsDeskMenu.clickReservationsLink();
//		pgWrapper.pageFindReservation.clickFindReservationLink();
//		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, reservationId, fromDate);
//		pgWrapper.pageFindReservation.getReservationBTStatus(bookStatus);
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Following " + reservationId + " reservation is booked.");
//		pgWrapper.pageFindReservation.clickReassignSupplier();
//		pgWrapper.pageDashBoard.selectReqHotel(hotelChange);
//		pgWrapper.pageDashBoard.selectPaymentDetails_BT(paymentDetails);
//		pgWrapper.pageDashBoard.enterBTConfirmationCode(confirmCode);
//		pgWrapper.pageDashBoard.clickOnFinishBtn();
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Supplier changed for the following reservation : " + reservationId );
//		ExtentTestManager.getTest().log(LogStatus.INFO, " "+ reservationId +" reservation status changed to PC");
//		// original trip should go to PC and do the bulk cancel for that PC
//		pgWrapper.opsDeskMenu.clickDashBoardLink();
//		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
//		pgWrapper.pageDashBoard.searchWithTripNumberPC(reservationId);
//		pgWrapper.pageDashBoard.selectCancellationAlert(reservationId);
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Bulk cancellation started for the following reservation : " + reservationId );
//		pgWrapper.pageDashBoard.clickBulkCancelBtn();
//		pgWrapper.pageDashBoard.enterHotelCancelConfirmCode(confirmCode);
//		pgWrapper.pageDashBoard.clickOnFinishCancelAllButton();
//		pgWrapper.pageDashBoard.clickOnFinishButton();
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Bulk cancellation ended for the following reservation : " + reservationId );
//
//		// verify the final status of the original trip
//		pgWrapper.opsDeskMenu.clickReservationsLink();
//		pgWrapper.pageFindReservation.clickFindReservationLink();
//		pgWrapper.pageFindReservation.findReservationWithTripId(serviceType, reservationId, fromDate);
//		pgWrapper.pageFindReservation.getReservationBTStatus(cancellationStatus);
////		pgWrapper.pageFindReservation.verifyViewNotes(confirmCode);
////		pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", "", "1");
	}

	// Bulk Cancellation of Stand alone GT's

	@Test(description = "ATA-403  Verify the Bulk Cancel of stand alone GT PCs", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCanceStandAloneGT(String tenantName, String locationValue, String pickUpTimeValue,
			String pickUpDetailValue, String dropOffLocValue, String dropOffDetailValue, String dropOffTimeValue,
			String additionalEmailAddressValue, String empIdValue, String empNameValue, String noOfPassengersValue,
			String notesValue, String costCenterValue, String reasonValue, String timeFrameFilterValue,
			String timeFormatValue, String refreshIntervalValue, String gtProviderName, String confirmCode,
			String btServiceType, String gtBookedStatus, String gtProvider2, String gtPCStatus, String confirmCode2,
			String gtCancelledStatus) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();

		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.loginToAirlines(readPropValue(tenantName + "Username"),
				readPropValue(tenantName + "Password"));
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.operationsTab.clickOnRequestLimoUnderBT();
		String getOpertionDate = pgWrapper.requestLimoPage.requestLimoPageUnderBTBC(locationValue, pickUpTimeValue,
				pickUpDetailValue, dropOffLocValue, dropOffDetailValue, dropOffTimeValue, additionalEmailAddressValue,
				empIdValue, empNameValue, noOfPassengersValue, notesValue, costCenterValue, reasonValue);
		String tripId = pgWrapper.requestLimoPage.processingRequestBT();
		System.out.println("Created Reservation Id is : " + tripId);

		getDriver().get(aces2Url);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
		// String tripId ="1100701";
		// String getOpertionDate = "16-Aug-2020";
		pgWrapper.pageHome.clickOPSDeskLink();
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.searchAndSelectTripUsingTripNumber(tripId);
		pgWrapper.pageDashBoard.selectGTProviderBTPA(gtProviderName);
		pgWrapper.pageDashBoard.bookBTGT(confirmCode);

		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(btServiceType, tripId, getOpertionDate);
		pgWrapper.pageFindReservation.getReservationBTStatus(gtBookedStatus);
		pgWrapper.pageFindReservation.clickOnBTGTResignButton();
		pgWrapper.pageDashBoard.selectGTProviderBTPA(gtProvider2);
		pgWrapper.pageDashBoard.bookBTGT(confirmCode);
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(btServiceType, tripId, getOpertionDate);
		pgWrapper.pageFindReservation.getReservationBTStatus(gtPCStatus);

		// /* select trip with Hotel Service Type and click on Bulk Cancel
		// button */
		pgWrapper.opsDeskMenu.clickDashBoardLink();
		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
		pgWrapper.pageDashBoard.selectCancellationAlert(tripId);
		pgWrapper.pageDashBoard.clickBulkCancelBtn();
		pgWrapper.pageDashBoard.enterGTCancelConfirmCode(confirmCode2);
		pgWrapper.pageDashBoard.clickOnFinishCancelAllButton();
		pgWrapper.pageDashBoard.clickOnFinishButton();
		pgWrapper.opsDeskMenu.clickReservationsLink();
		pgWrapper.pageFindReservation.clickFindReservationLink();
		pgWrapper.pageFindReservation.findReservationWithTripId(btServiceType, tripId, getOpertionDate);
		pgWrapper.pageFindReservation.getReservationBTStatus(gtCancelledStatus);
		pgWrapper.pageFindReservation.verifyBTPCViewNotes(confirmCode2);
		String filename = pgWrapper.pageFindTrip.clickOnPDFlinksAndDownload("", gtProviderName, "1");
		System.out.println(filename);
		String pdfs[] = filename.split(",");
		String gtPDF = pdfs[1];
		pgWrapper.pageFindTrip.readPDF(gtPDF, confirmCode2);

		//
	}

	@Test(description = "ATA- 402, Verify Partial Cancel of GT", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void bulkCancelPartial3rtyGT(String runMode, String sshIndicator, String scenarioFolderName,
			String tenantName, String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String supplierType, String status,
			String scenario, String pccStatus, String gtConfirmationCode, String processType) throws Exception {
		if (runMode.equals("Yes")) {
			if (processType.equalsIgnoreCase("addPairing1")) {
				bulkCancelPartial3rtyGTSSH(sshIndicator, scenarioFolderName, tenantName, reservationId,
						checkInDateValue, hotelName, confirmationCode, hotelRateValue, toHotelGTRate, fromHotelGTRate,
						supplierType, status, scenario, pccStatus, gtConfirmationCode);
			} else if (processType.equalsIgnoreCase("deletePairing1")) {
				pgWrapper.genericClass.verifyDeletePairingTrips(sshIndicator, scenarioFolderName, tenantName,
						reservationId, checkInDateValue, hotelName, pccStatus, scenario);
			}
		}
	}

	public void bulkCancelPartial3rtyGTSSH(String sshIndicator, String scenarioFolderName, String tenantName,
			String reservationId, String checkInDateValue, String hotelName, String confirmationCode,
			String hotelRateValue, String toHotelGTRate, String fromHotelGTRate, String supplierType, String status,
			String scenario, String pccStatus, String gtConfirmationCode) throws Exception {
		if (sshIndicator.equals("Yes")) {
			pgWrapper.genericClass.processPairingFilesInSSH(tenantName, scenarioFolderName);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Executing scenario " + scenario);
		pgWrapper = LocalDriverManager.getPageWrapper();
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
		pgWrapper.pagePendingConfirmations.addToHotelGTRates(toHotelGTRate, fromHotelGTRate);
		pgWrapper.pagePendingConfirmations.clickFinishButton();
		pgWrapper.opsDeskMenu.clickFindTripLink();
		pgWrapper.pageFindTrip.findTrips(reservationId, checkInDateValue);
		pgWrapper.pageFindTrip.clickOnTripIDInTheResultsTable();
		pgWrapper.pageFindTrip.clickOnShowNotesOfTrip(pccStatus);

	}

	/*
	 * Bulk Cancellation End
	 */

	/*
	 * This method connects to SSH and run the given command. Stores the terminal
	 * logs into string and verify whether expected string found or not
	 */
	private static void verifylogsInPutty(String fileName, String keyWordInLogs) throws Exception {
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		Session session;
		session = jsch.getSession("aces3", "10.10.103.131", 22);
		session.setPassword("acesadmin2013");
		session.setConfig(config);
		session.connect();
		System.out.println("Connected");
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("tail -1000f /home/aces3/logs/" + fileName);
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
				if (logs.contains(keyWordInLogs)) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"The Keyword " + keyWordInLogs + " is found in logs ");
					System.out.println("The Keyword you are searching is found in log " + keyWordInLogs);

					break;
				} else if (logs.contains("Exception") || logs.contains("Error")) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "The Errors found in logs ");
					Assert.fail("The Errors found in logs ");
				}
				Thread.sleep(10000);
			}
			if (channel.isClosed()) {
				// System.out.println("exit-status: " +
				// channel.getExitStatus());
				break;
			}
		}

		channel.disconnect();
		session.disconnect();
		System.out.println("DONE");
	}

	public String createBTJB(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotel, String empID, String EmpName) throws IOException {
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		pgWrapper.requestReservationPage.enterEmpDetailsForRoom(empID, EmpName);// 11790
		pgWrapper.requestReservationPage.selectReason("1");
		pgWrapper.requestReservationPage.reason("1");
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservId;
		return reservId = pgWrapper.requestReservationPage.processingRequest();

	}

//	public void createRequestReservation(String envoyUsername, String envoyPassword, String destinationValue,
//			String timeFormatValue, String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
//			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
//			String additionalEmailAddressValue, String hotelValue, String accountTypeValue, String singleRoomType,
//			String doubleRoomType, String hotelSupDescValue, String TestCaseScenario, String userName, String password,
//			String tenantName, String bidMonthIndex, String timeFrameFilterValue,String refreshIntervalValue) throws Exception {
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + TestCaseScenario);
//		String reservId;
//		pgWrapper = LocalDriverManager.getPageWrapper();
//		pgWrapper.airlinesLoginPage.loginToAirlines(envoyUsername, envoyPassword);
//
//		//logger.info("**** Create a reservation in request reservation page ");
//		pgWrapper.operationsTab.clickOnBusinessTravelLink();
//		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
//				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
//				departureFlightNumberValue, additionalEmailAddressValue);
//		pgWrapper.requestReservationPage.hotel(hotelValue);
//		pgWrapper.requestReservationPage.accountType(accountTypeValue);
//		pgWrapper.requestReservationPage.selectRoomTypeAndEnterDetails(singleRoomType, doubleRoomType);
//		pgWrapper.requestReservationPage.hotelDescription(hotelSupDescValue);
//		pgWrapper.requestReservationPage.clickOnSendToAPI();
//		reservId = pgWrapper.requestReservationPage.processingRequest();
//		pgWrapper.operationsTab.clickOnFindReservationLinkUnderBT();
//		pgWrapper.findReservationBTPage.enterSearchCriteria(reservId);
//		pgWrapper.findReservationBTPage.clickOnRetrieveButton();
//		pgWrapper.findReservationBTPage.verifyReservationExists(reservId,"Pending Assignment");
//		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
//		
//		//logger.info("****  Verifying the created reservation in ACES Application ");
//		getDriver().get(aces2Url);
//		pgWrapper = LocalDriverManager.getPageWrapper();
//		pgWrapper.pageLoginACESII.acesIILoginDetails(userName, password);
//		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
//		pgWrapper.pageHome.clickOPSDeskLink();
//		pgWrapper.opsDeskMenu.clickDashBoardLink();
//		pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue,
//				refreshIntervalValue);
//		pgWrapper.pageDashBoard.verifyAssignments(reservId);
//	}

	@Test(description = "Manual Booking", groups = { "Regression" }, dataProvider = "TestDataFile")
	public void manualBooking(String tenantName, String destinationValue, String timeFormatValue,
			String arrivaltimeValue, String departuretimeValue, String arrivalFlightCodeValue,
			String arrivalFlightNumberValue, String departureFlightCodeValue, String departureFlightNumberValue,
			String additionalEmailAddressValue, String hotel, String empID, String empName, String timeFrameFilterValue,
			String refreshIntervalValue, String confirmCode, String paymentDetails, String serviceType,
			String bookStatus, String hotelChange, String cancellationStatus) throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();

		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));

// Go to Airlines and create Reservation
		getDriver().get(airlinesUrl);
		pgWrapper.airlinesLoginPage.airlineLoginDetails(readPropValue(tenantName.replace(" ", "") + "Username"),
				readPropValue(tenantName.replace(" ", "") + "Password"));
		String reservationId = createBT(destinationValue, timeFormatValue, arrivaltimeValue, departuretimeValue,
				arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue, departureFlightNumberValue,
				additionalEmailAddressValue, hotel, empID, empName);
		pgWrapper.airlinesLoginPage.clickOnLogoutButton();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reservation Created with following id : " + reservationId);

//// Go to ACES2 main site and verify that created reservation is shown in Ops Dashboard
//getDriver().get(aces2Url);
//// String reservationId ="1102682";
//ExtentTestManager.getTest().log(LogStatus.INFO, "Started processing the following : " + reservationId + " reservation.");
//pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
//pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(readPropValue("bidMonthIndex")));
//pgWrapper.pageHome.clickOPSDeskLink();
//pgWrapper.opsDeskMenu.clickDashBoardLink();
//pgWrapper.pageDashBoard.refreshResults(tenantName, timeFrameFilterValue, timeFormatValue, refreshIntervalValue);
//// Process the PA
pgWrapper.pageDashBoard.searchWithTripNumber(reservationId);
//pgWrapper.pageDashBoard.clickOnReservationLink();

	}

	public String createBT(String destinationValue, String timeFormatValue, String arrivaltimeValue,
			String departuretimeValue, String arrivalFlightCodeValue, String arrivalFlightNumberValue,
			String departureFlightCodeValue, String departureFlightNumberValue, String additionalEmailAddressValue,
			String hotel, String empID, String EmpName) throws IOException {
		pgWrapper.operationsTab.clickOnBusinessTravelLink();
		pgWrapper.requestReservationPage.requestReservationPage(destinationValue, timeFormatValue, arrivaltimeValue,
				departuretimeValue, arrivalFlightCodeValue, arrivalFlightNumberValue, departureFlightCodeValue,
				departureFlightNumberValue, additionalEmailAddressValue);
		pgWrapper.requestReservationPage.hotel(hotel);
		// pgWrapper.requestReservationPage.enterEmpDetailsForRoom(empID, EmpName);//
		// 11790
		pgWrapper.requestReservationPage.selectReason("1");
//		pgWrapper.requestReservationPage.reason("1");
		pgWrapper.requestReservationPage.clickSaveBtn();
		String reservId;
		return reservId = pgWrapper.requestReservationPage.processingRequest();

	}
}
