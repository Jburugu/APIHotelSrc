package com.APIHotels.tests.ACESII;

import org.testng.annotations.Test;

import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;
import com.relevantcodes.extentreports.LogStatus;

public class ACESII_CommonSupplier_Regression_Suite extends LocalDriverManager{
	
	public PgWrapper pgWrapper;
	
	
	@Test(description = "TCs:1-4:Verify Add New Hotel/GT Airline/Cruiseline Common Supplier", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void addNewCommonSupplier(String tenantName, String bidMonthIndex,String supplierType, String city, String hotelName, String countryValue, String locationType, String deptTitle, String name, String phone, String fax, String email, 
			String preferredContactLanguage, String taxId, String taxType, String taxVatName, String taxVat_StartDate, String taxVat_EndDate, String exemptAfterDays, String percent,  
			String flatFree, String units, String provider, String client, String testCaseDetails) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testCaseDetails);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickCommonSupplierLink();
		pgWrapper.pageAddNewCommonSupplier.clickAddNewCommonSupplierLink();
		pgWrapper.pageAddNewCommonSupplier.selectSupplierType(supplierType);
		if(supplierType.equals("Hotel")){
			pgWrapper.pageCommonHotelGeneralInfo.setSupplierName(city, hotelName);
			pgWrapper.pageCommonHotelGeneralInfo.setGeneralDetails(countryValue, locationType, client);
			pgWrapper.pageCommonHotelGeneralInfo.setContactInformation(deptTitle, name, phone, fax, email, preferredContactLanguage);
			pgWrapper.pageCommonHotelGeneralInfo.setTaxVatInformation(taxId, Integer.parseInt(taxType), taxVatName, taxVat_StartDate, taxVat_EndDate, exemptAfterDays, percent, flatFree, units);
			pgWrapper.pageCommonHotelGeneralInfo.clickSaveDetails();
		}else if(supplierType.equals("GT")){
			pgWrapper.pageCommonGTGeneralInfo.setSupplierName(city, provider);
			pgWrapper.pageCommonGTGeneralInfo.setGeneralDetails(countryValue, client);
			pgWrapper.pageCommonGTGeneralInfo.setContactInformation(deptTitle, hotelName, phone, fax, email, preferredContactLanguage);
			pgWrapper.pageCommonGTGeneralInfo.setTaxVatInformation(taxId, Integer.parseInt(taxType), taxVatName, taxVat_StartDate, taxVat_EndDate, percent, flatFree, units);
			pgWrapper.pageCommonGTGeneralInfo.clickSaveDetails();
		}
	}
	
	@Test(description = "TCs:5-7:Verify that the user is able to find existing Common Supplier with HOTEL, GT and BOTH serviceType actions", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void findExistingCommonSupplier(String tenantName, String bidMonthIndex,String city, String serviceId, String supplierType, 
			String supplier, String hotelName, String countryValue, String locationType, String client, 
			String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage, String taxId, String taxType, String taxVatName, 
			String taxVat_StartDate, String taxVat_EndDate, String exemptAfterDays, String percent, String flatFree, String units, String provider, String testcaseDetails) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testcaseDetails);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickCommonSupplierLink();
		pgWrapper.pageFindCommonSupplier.clickFindCommonSupplierLink();
		pgWrapper.pageFindCommonSupplier.searchSupplier(city, serviceId, supplier);
		if(supplierType.equals("Hotel")){
			pgWrapper.pageCommonHotelGeneralInfo.verifySupplierName(city, hotelName);
			pgWrapper.pageCommonHotelGeneralInfo.verifyGeneralDetails(countryValue, locationType, client);
			pgWrapper.pageCommonHotelGeneralInfo.verifyContactInformation(deptTitle, name, phone, fax, email, preferredContactLanguage);
			pgWrapper.pageCommonHotelGeneralInfo.verifyTaxVatInformation(taxId, Integer.parseInt(taxType), taxVatName, taxVat_StartDate, taxVat_EndDate, exemptAfterDays, percent, flatFree, units);
		}else if(supplierType.equals("GT")){
			pgWrapper.pageCommonGTGeneralInfo.verifySupplierName(city, provider);
			pgWrapper.pageCommonGTGeneralInfo.verifyGeneralDetails(countryValue, client);
			pgWrapper.pageCommonGTGeneralInfo.verifyContactInformation(deptTitle, hotelName, phone, fax, email, preferredContactLanguage);
			pgWrapper.pageCommonGTGeneralInfo.verifyTaxVatInformation(taxId, Integer.parseInt(taxType), taxVatName, taxVat_StartDate, taxVat_EndDate, percent, flatFree, units);
		}
	}
	
	@Test(description = "TCs:8-9:Verify that the user is able to edit existing HOTEL, GT Common Supplier", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void editExistingCommonSupplier(String tenantName, String bidMonthIndex,String city, String serviceId, String supplierType, 
			String supplier, String hotelName, String countryValue, String locationType, String client, 
			String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage, String taxId, String taxType, String taxVatName, 
			String taxVat_StartDate, String taxVat_EndDate, String exemptAfterDays, String percent, String flatFree, String units, String provider, String testcaseDetails) throws Exception{
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Currently Running: " + testcaseDetails);
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickCommonSupplierLink();
		pgWrapper.pageFindCommonSupplier.clickFindCommonSupplierLink();
		pgWrapper.pageFindCommonSupplier.searchSupplier(city, serviceId, supplier);
		if(supplierType.equals("Hotel")){
			pgWrapper.pageCommonHotelGeneralInfo.setSupplierName(city, hotelName);
			pgWrapper.pageCommonHotelGeneralInfo.setGeneralDetails(countryValue, locationType, client);
			pgWrapper.pageCommonHotelGeneralInfo.editContactInformation(deptTitle, name, phone, fax, email, preferredContactLanguage);
			pgWrapper.pageCommonHotelGeneralInfo.editTaxVatInformation(taxId, Integer.parseInt(taxType), taxVatName, taxVat_StartDate, taxVat_EndDate, exemptAfterDays, percent, flatFree, units);
			pgWrapper.pageCommonHotelGeneralInfo.clickSaveDetails_New();
		}else if(supplierType.equals("GT")){
			pgWrapper.pageCommonGTGeneralInfo.setSupplierName(city, provider);
			pgWrapper.pageCommonGTGeneralInfo.setGeneralDetails(countryValue, client);
			pgWrapper.pageCommonGTGeneralInfo.editContactInformation(deptTitle, hotelName, phone, fax, email, preferredContactLanguage);
			pgWrapper.pageCommonGTGeneralInfo.editTaxVatInformation(taxId, Integer.parseInt(taxType), taxVatName, taxVat_StartDate, taxVat_EndDate, percent, flatFree, units);
			pgWrapper.pageCommonGTGeneralInfo.clickSaveDetails();
		}
		
	}
	
	@Test(description = "TC:10 - Verify Sort Suppliers", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void sortSuppliers(String tenantName, String bidMonthIndex,String layoverLocation, String supplierName, String sortOption) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickSortSupplierLink();
		pgWrapper.pageSortSuppliers.retrieveSuppliers(layoverLocation);
		pgWrapper.pageSortSuppliers.sortSuppliers(supplierName, sortOption);
		pgWrapper.pageSortSuppliers.clickSaveBtn();
	}
	
	@Test(description = "TC:11 - Verify user is able to add a new Hotel Supplier to particular airline by creating a contract", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void addNewHotelSupplierToAirline(String tenantName, String bidMonthIndex, String city, String service, String supplier, 
			String client, String pairingFileName, String deptTitle, String name, String phone, String fax, String email, 		
			String preferredMode, String contractStartDate, String contractEndDate, String roomRule, String cancelRuleValue, String cancelTimeValue, String currency, String daysOfWeek, String roomRateStartDate, String roomRateEndDate, String roomType, String roomRate, String minRooms, 
			String mealRateStartDate, String mealRateEndDate, String mealType, String mealRate, String routeType, String airportDaysOfWeek, String fromAirportCode, 
			String transitStartDate, String transitEndDate, String transitStartTime, String transitEndTime, String transitTime, String layoverDaysOfWeek, String hotelLayoverLocation,
			String layoverStartDate, String layoverEndDate, String layoverFromTime, String layoverToTime) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageAddNewAirlineSupplier.clickAddNewSupplierLink();
		pgWrapper.pageAddNewAirlineSupplier.addNewSupplier(city, service, supplier);
		pgWrapper.pageAirlineHotelSupplier.verifyGeneralDetails(client);
		pgWrapper.pageAirlineHotelSupplier.setGeneralHotelSupplierDetails(pairingFileName);
		pgWrapper.pageAirlineHotelSupplier.verifyContactDetails(deptTitle, name, phone, fax, email);
		pgWrapper.pageAirlineHotelSupplier.setIndicators(preferredMode);
		pgWrapper.pageAirlineHotelSupplier.setContractDetails(contractStartDate, contractEndDate);
		pgWrapper.pageHotelSupplierContractDetails.setPrimaryHotelRulesAndRates();
		pgWrapper.pageHotelSupplierContractDetails.setRoomLimits();
		pgWrapper.pageHotelSupplierContractDetails.setRoomUseRules(roomRule);
		pgWrapper.pageHotelSupplierContractDetails.setCancellationPolicy(cancelRuleValue, cancelTimeValue);
		pgWrapper.pageHotelSupplierContractDetails.setRoomRates(currency, daysOfWeek, roomRateStartDate, roomRateEndDate, roomType, roomRate, minRooms);
		pgWrapper.pageHotelSupplierContractDetails.setMealRates(mealRateStartDate, mealRateEndDate, mealType, mealRate);
		pgWrapper.pageHotelSupplierContractDetails.clickSaveDetails();
		//pgWrapper.pageHotelSupplierContractDetails.cancelDetails();
		pgWrapper.pageAirlineHotelSupplier.setContractIndicator();
		pgWrapper.pageAirlineHotelSupplier.setAirportVenueHotelTransitTimesDetails(Integer.parseInt(routeType), airportDaysOfWeek, fromAirportCode, transitStartDate, transitEndDate, transitStartTime, transitEndTime, transitTime);;
		pgWrapper.pageAirlineHotelSupplier.setHotelUseRules(layoverDaysOfWeek, hotelLayoverLocation, layoverStartDate, layoverEndDate, layoverFromTime, layoverToTime);
		pgWrapper.pageAirlineHotelSupplier.setTaxVatInformation();
		pgWrapper.pageAirlineHotelSupplier.setSupplierWebsitenInfoIndicators();
		pgWrapper.pageAirlineHotelSupplier.saveDetails();
		
	}
	
	@Test(description = "TC:12 - Verify user is able to add a new GT Supplier to particular airline by creating a contract", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void addNewGTSupplierToAirline(String tenantName, String bidMonthIndex, String city, String service, String supplier,
			String client, String deptTitle, String name, String phone, String fax, String email, 
			String preferredMode, String contractStartDate, String contractEndDate, String cancelRule, String cancelTimeValue,
			String routeType, String routesAndRatesStartDate, String routesAndRatesEndDate, 
			String fromAirportCode, String fromVenueCode, String toAirportCode, String toVenueCode, String hotel, String rate, String units, 
			String fuelSurchargeStartDate, String fuelSurchargeEndDate, String percent, String flatfee, String fuelSurchargeUnits) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageAddNewAirlineSupplier.clickAddNewSupplierLink();
		pgWrapper.pageAddNewAirlineSupplier.addNewSupplier(city, service, supplier);
		pgWrapper.pageAirlineGTSupplier.verifyGeneralDetails(client);
		pgWrapper.pageAirlineGTSupplier.setGeneralGTSupplierDetails();
		pgWrapper.pageAirlineGTSupplier.verifyContactDetails(deptTitle, name, phone, fax, email);
		pgWrapper.pageAirlineGTSupplier.setIndicators(preferredMode);
		pgWrapper.pageAirlineGTSupplier.setContractDetailsAndClickSaveBtn(contractStartDate, contractEndDate, cancelRule, cancelTimeValue);
		pgWrapper.pageAirlineGTSupplier.setRoutesAndRates(Integer.parseInt(routeType), routesAndRatesStartDate, routesAndRatesEndDate, fromAirportCode, fromVenueCode, toAirportCode, toVenueCode, hotel, rate, units);
		pgWrapper.pageAirlineGTSupplier.setSupplierWebsitenInfoIndicators();
		pgWrapper.pageAirlineGTSupplier.saveDetails();
		
	}
	
	@Test(description = "TC:13 - Verify that the user is able to find/edit the hotel supplier details for a particular airline", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void verifyEditExistingHotelSupplier(String tenantName, String bidMonthIndex, String city, String serviceId, String status, String supplierName, 
			String client, String pairingFileName, String NewpairingFileName, String deptTitle, String name, String phone, String fax, String email, String NewdeptTitle, 
			String Newname, String Newphone, String Newfax, String Newemail, String preferredMode, String NewpreferredMode, String contractStartDate, String contractEndDate, 
			String NewcontractStartDate, String NewcontractEndDate,
			String routeType, String daysOfWeek, String fromAirportCode, String transitStartDate, String transitEndDate, 
			String transitStartTime, String transitEndTime, String transitTime, String NewrouteType, String NewdaysOfWeek, String NewfromAirportCode, String NewtransitStartDate, String NewtransitEndDate, String NewtransitStartTime, String NewtransitEndTime, String NewtransitTime,
			String layoverDaysOfWeek, String hotelLayoverLocation, String layoverStartDate, String layoverEndDate, String layoverFromTime, String layoverToTime, 
			String NewlayoverDaysOfWeek, String NewhotelLayoverLocation, String NewlayoverStartDate, String NewlayoverEndDate, String NewlayoverFromTime, String NewlayoverToTime,
			String roomRule, String cancelRuleValue, String cancelTimeValue, 
			String NewcancelRuleValue, String NewcancelTimeValue, String currency, String roomRatesDaysOfWeek,
			String roomRateStartDate, String roomRateEndDate, String roomType, String roomRate, String minRooms, 
			String NewroomRatesDaysOfWeek, String NewroomRateStartDate, String NewroomRateEndDate, String NewroomType, String NewroomRate, String NewminRooms,
			String mealRateStartDate, String mealRateEndDate, String mealType, String mealRate, String NewmealRateStartDate, String NewmealRateEndDate, String NewmealType, String NewmealRate) throws Exception{
		
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(supplierName);
		pgWrapper.pageAirlineHotelSupplier.verifyGeneralDetails(client, pairingFileName);
		pgWrapper.pageAirlineHotelSupplier.setGeneralHotelSupplierDetails(NewpairingFileName);
		pgWrapper.pageAirlineHotelSupplier.verifyContactDetails(deptTitle, name, phone, fax, email);
		pgWrapper.pageAirlineHotelSupplier.verifyContactIndicatorsStatus(preferredMode);
		pgWrapper.pageAirlineHotelSupplier.setContactDetails(NewpreferredMode);
		pgWrapper.pageAirlineHotelSupplier.verifyContractDetails(contractStartDate, contractEndDate);
		pgWrapper.pageAirlineHotelSupplier.editContractDetails(NewcontractStartDate, NewcontractEndDate);
		pgWrapper.pageAirlineHotelSupplier.verifyAirportVenueHotelTransitTimesDetails(routeType, daysOfWeek, fromAirportCode, transitStartDate, transitEndDate, transitStartTime, transitEndTime, transitTime);
		pgWrapper.pageAirlineHotelSupplier.editAirportVenueHotelTransitTimesDetails(Integer.parseInt(NewrouteType), NewdaysOfWeek, NewfromAirportCode, NewtransitStartDate, NewtransitEndDate, NewtransitStartTime, NewtransitEndTime, NewtransitTime);
		pgWrapper.pageAirlineHotelSupplier.verifyHotelUseRules(layoverDaysOfWeek, hotelLayoverLocation, layoverStartDate, layoverEndDate, layoverFromTime, layoverToTime);
		pgWrapper.pageAirlineHotelSupplier.editHotelUseRules(NewlayoverDaysOfWeek, NewhotelLayoverLocation, NewlayoverStartDate, NewlayoverEndDate, NewlayoverFromTime, NewlayoverToTime);
		//Verify tax vat info pending
		pgWrapper.pageAirlineHotelSupplier.verifySupplierWebsitenInfoIndicators();
		pgWrapper.pageAirlineHotelSupplier.clickHotelContractDetailsBtn();
		pgWrapper.pageHotelSupplierContractDetails.verifyPrimaryHotelRulesAndRates();
		pgWrapper.pageHotelSupplierContractDetails.editPrimaryHotelRulesAndRates();
		pgWrapper.pageHotelSupplierContractDetails.verifyRoomUseRules(roomRule);
		pgWrapper.pageHotelSupplierContractDetails.verifyCancellationPolicy(cancelRuleValue, cancelTimeValue);
		pgWrapper.pageHotelSupplierContractDetails.setCancellationPolicy(NewcancelRuleValue, NewcancelTimeValue);
		pgWrapper.pageHotelSupplierContractDetails.verifyRoomRates(currency, roomRatesDaysOfWeek, roomRateStartDate, roomRateEndDate, roomType, roomRate, minRooms);
		pgWrapper.pageHotelSupplierContractDetails.setRoomRates(currency, NewroomRatesDaysOfWeek, NewroomRateStartDate, NewroomRateEndDate, NewroomType, NewroomRate, NewminRooms);
		pgWrapper.pageHotelSupplierContractDetails.verifyMealRates(mealRateStartDate, mealRateEndDate, mealType, mealRate);
		pgWrapper.pageHotelSupplierContractDetails.editMealRates(NewmealRateStartDate, NewmealRateEndDate, NewmealType, NewmealRate);
		pgWrapper.pageHotelSupplierContractDetails.clickSaveDetails();
		pgWrapper.pageAirlineHotelSupplier.saveDetails();
	}
	
	@Test(description = "TC:14 - Verify that the user is able to find/edit the GT supplier details for a particular airline", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void verifyEditExistingGTSupplier(String tenantName, String bidMonthIndex, String city, String serviceId, String status, String supplierName,
			String client, String pairingFileName, String deptTitle, String name, String phone, String fax, String email, String preferredMode, String NewpreferredMode,
			String contractStartDate, String contractEndDate, String cancelRule, String cancelTimeValue, String NewcontractStartDate, String NewcontractEndDate, String NewcancelRule, String NewcancelTimeValue, 
			String routeType, String routesAndRatesStartDate, String routesAndRatesEndDate, String fromAirportCode, String fromVenueCode, String toAirportCode, String toVenueCode, String hotel, String rate, String units,
			String NewroutesAndRatesStartDate, String NewroutesAndRatesEndDate, String NewfromAirportCode, String NewtoAirportCode, String Newrate,
			String fuelSurchargeName, String fuelSurchargeStartDate, String fuelSurchargeEndDate, String percent, String flatfee, String fuelSurchargeUnits) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(supplierName);
		pgWrapper.pageAirlineGTSupplier.verifyGeneralDetails(client, pairingFileName);
		pgWrapper.pageAirlineGTSupplier.verifyContactDetails(deptTitle, name, phone, fax, email);
		pgWrapper.pageAirlineGTSupplier.verifyContactIndicatorsStatus(preferredMode);
		pgWrapper.pageAirlineGTSupplier.setIndicators(NewpreferredMode);
		pgWrapper.pageAirlineGTSupplier.verifyContractDetails(contractStartDate, contractEndDate, cancelRule, cancelTimeValue);
		pgWrapper.pageAirlineGTSupplier.editContractDetails(NewcontractStartDate, NewcontractEndDate, NewcancelRule, NewcancelTimeValue);
		pgWrapper.pageAirlineGTSupplier.verifyRoutesAndRates(Integer.parseInt(routeType), routesAndRatesStartDate, routesAndRatesEndDate, fromAirportCode, fromVenueCode, toAirportCode, toVenueCode, hotel, rate, units);
		pgWrapper.pageAirlineGTSupplier.editRoutesAndRates(Integer.parseInt(routeType), NewroutesAndRatesStartDate, NewroutesAndRatesEndDate, NewfromAirportCode, fromVenueCode, NewtoAirportCode, toVenueCode, hotel, Newrate, units);
		pgWrapper.pageAirlineGTSupplier.verifyTaxesAndFuelSurcharges(fuelSurchargeName, fuelSurchargeStartDate, fuelSurchargeEndDate, percent, flatfee, fuelSurchargeUnits);
		pgWrapper.pageAirlineGTSupplier.saveDetails();
	}
	
	@Test(description = "Delete Existing Supplier From Airline", groups = { "ACESII_Regression" }, dataProvider = "TestDataFile")
	public void deleteExistingSupplier(String tenantName, String bidMonthIndex, String city, String serviceId, String status, String supplierName, String verificationMsg) throws Exception{
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.pageLoginACESII.loginToACESII(readPropValue("username"), readPropValue("password"));
		pgWrapper.pageHome.setTenantDetails(tenantName, Integer.parseInt(bidMonthIndex));
		pgWrapper.pageHome.clickSupplierProfileLink();
		pgWrapper.supplierProfileMenu.clickAirlineSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.clickFindSupplierLink();
		pgWrapper.pageFindExistingAirlineSupplier.searchSupplier(city, serviceId, status);
		pgWrapper.pageFindExistingAirlineSupplier.selectSupplier(supplierName);
		pgWrapper.pageAirlineHotelSupplier.deleteDetails();
		pgWrapper.pageAirlineHotelSupplier.verifyDeleteMsg(verificationMsg);
	}
	
	
	
}

	