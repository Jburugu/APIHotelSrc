package com.APIHotels.tests.ACES3;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3ACES_Invoice_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;
	String getInvocieNbr = "";
	// ATA 151 and ATA 152
	@Test(description = " Verify that the user is allowed to create manual invoice with all types of adjustments, Verify that the user is allowed to create manually created groups ", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyCreateManualInvocie(String tenantName, String city, String supplierName, String billingPeriod,
			String reason, String comments, String amount, String numberOfRooms, String reasonNoshow,
			String amountNoShow, String otherTax,String flight,String inFlight, String flightNoOfRooms, String InflighNoShowValue, String otherTaxValue, String approved)
			throws Exception {

		pgWrapper = LocalDriverManager.getPageWrapper();

		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		pgWrapper.aces3acesManualInvoicePage.createManualInvoice(tenantName, city, supplierName, billingPeriod);
		getInvocieNbr = pgWrapper.aces3acesManualInvoicePage.getHotelInvoiceNbr();
		System.out.println("Invoice Number :" + getInvocieNbr);
		pgWrapper.aces3acesManualInvoicePage.numberOfRoomsAdjustments(reason, comments, amount, numberOfRooms);
		pgWrapper.aces3acesManualInvoicePage.noShowAdjustments(reasonNoshow, comments, amountNoShow);
		pgWrapper.aces3acesManualInvoicePage.auditorTaxAdjustments(otherTax);
		pgWrapper.aces3acesManualInvoicePage.AddPaymentGroup(flight, inFlight);
		pgWrapper.aces3acesManualInvoicePage.adjustmentsInPaymentGroup(flightNoOfRooms, InflighNoShowValue,
				otherTaxValue);
		pgWrapper.aces3acesManualInvoicePage.currencyConversion();
		pgWrapper.aces3acesManualInvoicePage.clickOnApproveInvoice();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr, approved);

	}
	public String invoiceId() {
		return getInvocieNbr;
	}
	

	@Test(description = "ATA-153 : Verify that the all the actions like, approve, hold, void", groups = {
			"Regression" }, dataProvider = "TestDataFile")
	public void verifyApproveInvoice(String tenantName, String city, String supplierName, String billingPeriod,
			String approved, String hold, String voidValue) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();

		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAccountingLink();
		pgWrapper.aces3acesManualInvoicePage.createManualInvoice(tenantName, city, supplierName, billingPeriod);
		//Approve action
		String getInvocieNbr = pgWrapper.aces3acesManualInvoicePage.getHotelInvoiceNbr();
		String getInvocieNbr1 = getInvocieNbr.substring(2);
		System.out.println("Invoice Number :" + getInvocieNbr1);
		pgWrapper.aces3acesManualInvoicePage.clickOnApproveInvoice();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr1, approved);
		// Hold action
		pgWrapper.aces3acesManualInvoicePage.createManualInvoice(tenantName, city, supplierName, billingPeriod);
		getInvocieNbr = pgWrapper.aces3acesManualInvoicePage.getHotelInvoiceNbr();
		getInvocieNbr1 = getInvocieNbr.substring(2);
		System.out.println("Invoice Number :" + getInvocieNbr1);
		pgWrapper.aces3acesManualInvoicePage.clickOnHoldInvocie();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceSubStatus(getInvocieNbr1, hold);
		// Void action
		pgWrapper.aces3acesManualInvoicePage.createManualInvoice(tenantName, city, supplierName, billingPeriod);
		getInvocieNbr = pgWrapper.aces3acesManualInvoicePage.getHotelInvoiceNbr();
		getInvocieNbr1 = getInvocieNbr.substring(2);
		System.out.println("Invoice Number :" + getInvocieNbr1);
		pgWrapper.aces3acesManualInvoicePage.invoiceComments();
		pgWrapper.aces3acesManualInvoicePage.verifyInvoiceStatus(getInvocieNbr1, voidValue);

	}
}
