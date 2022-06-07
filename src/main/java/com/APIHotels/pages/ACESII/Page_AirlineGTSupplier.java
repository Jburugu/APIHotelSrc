package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;

public class Page_AirlineGTSupplier extends BasePage{

	@FindBy(name = "commonNotes")
	private WebElement commonNotes;
	
	@FindBy(id = "cNotes")
	private WebElement clientNotes;
	
	@FindBy(id = "tenantType")
	private WebElement clients;
	
	@FindBy(id = "dummyDelSupplier")
	private WebElement gt_ArchiveSupplierIndicator;
	
	@FindBy(id = "vendorId")
	private WebElement vendorId;
	
	@FindBy(id = "locationId")
	private WebElement locationId;
	
	@FindBy(name = "visibleToAirline")
	private WebElement showInAirlineWebSiteIndicator;
	
	@FindBy(id = "txtParing")
	private WebElement pairingFileName;
	
	@FindBy(id = "apiSuppliercode")
	private WebElement apiSupplierNo;
	
	@FindBy(name = "contactList[0].name")
	private WebElement name;
	
	@FindBy(name = "contactList[0].phone")
	private WebElement phone;
	
	@FindBy(name = "contactList[0].fax")
	private WebElement fax;
	
	@FindBy(name = "contactList[0].email")
	private WebElement email;
	
/*	@FindBy(name = "contactList[0].preferredMode")
	private List<WebElement> preferredMode;*/
	
	String preferredModeXpath1 = "//*[@name = 'contactList[0].preferredMode' and @value = '";
	String preferredModeXpath2 = "']";
	
	@FindBy(id = "contactList[0].exportableContact")
	private WebElement exportContactIndicator;
	
	@FindBy(name = "contactList[0].common")
	private List<WebElement> commonIndicator;
	
	@FindBy(name = "contactList[0].deleted")
	private WebElement deleteIndicator;
	
	@FindBy(name = "contactList[0].dummyPrimery")
	private WebElement gt_PrimaryContactIndicator;
	
	@FindBy(xpath = "//*[@onclick = 'addHotelContractInformationRow();ACES_Modified();' and @class = 'Aces_Btn_Add']")
	private WebElement contractInfoAddBtn;
	
	@FindBy(name = "contractList[0].startDate")
	private WebElement contractStartDate;
	
	@FindBy(name = "contractList[0].endDate")
	private WebElement contractEndDate;
	
	@FindBy(xpath = "//input[contains(@id,'contractCheck')]")
	private WebElement gtContractIndicator;
	
	@FindBy(name = "contractList[0].comments")
	private WebElement comments;
	
	@FindBy(name = "contractList[0].deleted")
	private WebElement contractDeleteIndicator;
	
	@FindBy(xpath = "//*[contains(@value, 'Details')]")
	private WebElement gtContractDetailsBtn;
	
	@FindBy(id = "earlyPaymentDiscount0")
	private WebElement earlyPaymentDiscount;
	
	@FindBy(xpath = "//*[contains(@name, 'plannedBookingNonCancellable')]")
	private WebElement plannedPickupsNotCancellableIndicator;
	
	@FindBy(name = "contractList[0].airlineContractDetailsForm.cancelRuleIdentifier")
	private List<WebElement> cancellationTypeRadioBtn;
	
	String cancellationTypeXpath1 = "//*[@name = 'contractList[0].airlineContractDetailsForm.cancelRuleIdentifier' and @value = '";
	String cancellationTypeXpath2 = "']";
	
	@FindBy(name = "cancelPolicy0")
	private List<WebElement> cancelPolicyRadioBtn;
	
	@FindBy(id = "timeOfDayDisplay0")
	private WebElement timeOfDay;
	
	@FindBy(id = "noOfDays0")
	private WebElement daysPrior;
	
	@FindBy(id = "hoursBefore0")
	private WebElement hoursBeforePickup;
	
	@FindBy(id = "alertOK")
	private List<WebElement> contractDetails_OkBtn;
	
	@FindBy(xpath = "//*[@id = 'alertBot0']/input[2]")
	private WebElement contractDetails_CancelBtn;
	
	@FindBy(id = "row1")
	private WebElement routesAndRatesSectionHeader;
	
	@FindBy(id = "row2")
	private WebElement taxesAndSurchargesSectionHeader;
	
	@FindBy(id = "row3")
	private WebElement supplierWebsiteInfoSectionHeader;
	
	@FindBy(css = "#inc1 > div > div.Aces_Top_btns > input:nth-child(1)")
	private WebElement routesAndRatesAddRowBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deleteGTRouteRate(this.form);' and @class = 'Aces_Btn_Add']")
	private WebElement routesAndRatesDeleteRowBtn;
	
	@FindBy(id = "routeTypeObjID0")
	private WebElement routeType;
	
	@FindBy(id = "gtRouteRate[0].startDate")
	private WebElement routesAndRatesStartDate;
	
	@FindBy(id = "gtRouteRate[0].endDate")
	private WebElement routesAndRatesEndDate;
	
	@FindBy(id = "frAirPortCode0")
	private WebElement fromAirportCode;
	
	@FindBy(id = "venueCodeAObjID0")
	private WebElement fromVenueCode;
	
	@FindBy(id = "toAirPortCode0")
	private WebElement toAirportCode;
	
	@FindBy(id = "venueCodeBObjID0")
	private WebElement toVenueCode;
	
	@FindBy(id = "gtRouteRate[0].supplierB")
	private WebElement hotel;
	
	@FindBy(name = "gtRouteRate[0].rate")
	private WebElement rate;
	
	@FindBy(name = "gtRouteRate[0].currencyCd")
	private WebElement units;
	
	@FindBy(id = "route0")
	private WebElement routesAndRatesDeleteRowCheckBox;
	
	@FindBy(id = "Expand")
	private WebElement expandAll;
	
	@FindBy(id = "Collapse")
	private WebElement collapseAll;
	
	@FindBy(xpath = "//*[@onclick = 'addFuelTableRow();ACES_Modified();' and @class = 'Aces_Btn_Add']")
	private WebElement fuelSurchargeInfoAddRowBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deleteGtFuel(this.form);']")
	private WebElement fuelSurchargeInfoDeleteRowBtn;
	
	@FindBy(xpath = "//*[@id = 'inc2']//table//td")
	private List<WebElement> taxesAndFuelSurchargeRules;
	
	@FindBy(name = "fuelTax[0].startdate")
	private WebElement fuelSurchargeStartDate;
	
	@FindBy(name = "fuelTax[0].enddate")
	private WebElement fuelSurchargeEndDate;
	
	@FindBy(id = "fuelpercent0")
	private WebElement percent;
	
	@FindBy(id = "flatfee0")
	private WebElement flatFee;
	
	@FindBy(id = "currencycd0")
	private WebElement fuelSurchargeUnits;
	
	@FindBy(id = "comments0")
	private WebElement fuelSurchargeComments;
	
	@FindBy(id = "delFuel0")
	private WebElement fuelSurchargeDeleteRowCheckBox;
	
	@FindBy(id = "scheduleId")
	private WebElement scheduleDisplayIndicator;
	
	@FindBy(id = "invoiceId")
	private WebElement invoiceDisplayIndicator;
	
	@FindBy(id = "createInvoiceId")
	private WebElement createInvoiceIndicator;
	
	@FindBy(id = "reservationId")
	private WebElement reservationDisplayIndicator;
	
	@FindBy(name = "contactList[0].deptName")
	private WebElement deptTitle;
	
	@FindBy(id = "routeRateIndex")
	private WebElement routeRateRow;
	
	String routeTypeId = "routeTypeObjID";
	String routeStartDateId1 = "gtRouteRate[";
	String routeStartDateId2 = "].startDate"; 
	String routeEndDateId1 = "gtRouteRate[";
	String routeEndDateId2 = "].endDate";
	String routeFromAPCodeId = "frAirPortCode";
	String routeToAPCodeId = "toAirPortCode";
	String routeRateId = "gtRate";
	String routeUnitsId1 = "gtRouteRate[";
	String routeUnitsId2 = "].currencyCd";
	
	@FindBy(name = "save")
	private WebElement saveBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_AirlineGTSupplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setGeneralGTSupplierDetails() throws Exception{
		waitForElementVisibility(commonNotes);
		waitForElementVisibility(clientNotes);
		waitForElementVisibility(clients);
		waitForElementVisibility(gt_ArchiveSupplierIndicator);
		waitForElementVisibility(showInAirlineWebSiteIndicator);
		waitForElementVisibility(vendorId);
		waitForElementVisibility(locationId);
		waitForElementVisibility(pairingFileName);
		waitForElementVisibility(this.apiSupplierNo);
	}
	
	public void verifyGeneralDetails(String client) {
		Select clientsDD = new Select(clients);
		WebElement thisClient = clientsDD.getFirstSelectedOption();
		assertEquals(thisClient.getAttribute("value"), client, "client mismatch");
	}
	
	public void verifyGeneralDetails(String client, String pairingFileName) {
		Select clientsDD = new Select(clients);
		WebElement thisClient = clientsDD.getFirstSelectedOption();
		assertEquals(thisClient.getAttribute("value"), client, "client mismatch");
		assertEquals(this.pairingFileName.getAttribute("value"), pairingFileName, "pairingFileName mismatch");
	}
	
	
	public void verifyContactDetails(String deptTitle, String name, String phone, String fax, String email){
		assertEquals(this.deptTitle.getAttribute("value"), deptTitle, "deptTitle mismatch");
		assertEquals(this.name.getAttribute("value"), name, "name mismatch");
		assertEquals(this.phone.getAttribute("value"), phone, "phone mismatch");
		assertEquals(this.fax.getAttribute("value"), fax, "fax mismatch");
	}
	
	public void verifyContactIndicatorsStatus(String preferredMode) throws Exception{
		WebElement preferredContactMode = findElementByXpath(preferredModeXpath1+preferredMode+preferredModeXpath2);
		assertTrue(preferredContactMode.isSelected(), "preferredContact indicator not selected");
		assertTrue(gt_PrimaryContactIndicator.isSelected(), "primary indicator not selected");
		assertTrue(exportContactIndicator.isSelected(), "export indicator not selected");
	}
	
	public void setContactDetails(String preferredMode)throws Exception{
		waitForElementVisibility(name);
		waitForElementVisibility(phone);
		waitForElementVisibility(fax);
		waitForElementVisibility(email);
		WebElement preferredContactMode = findElementByXpath(preferredModeXpath1+preferredMode+preferredModeXpath2);
		clickOn(preferredContactMode);
		assertTrue(preferredContactMode.isSelected(), preferredMode+" is not selected");
		waitForElementVisibility(gt_PrimaryContactIndicator);
		waitForElementVisibility(exportContactIndicator);
		waitForElementVisibility(commonIndicator.get(1));
		waitForElementVisibility(deleteIndicator);
	}
	
	public void setIndicators(String preferredMode){
		WebElement preferredContactMode = findElementByXpath(preferredModeXpath1+preferredMode+preferredModeXpath2);
		clickOn(preferredContactMode);
		clickOn(gt_PrimaryContactIndicator);
		clickOn(exportContactIndicator);
	}
	
	public void setContractDetails(String contractStartDate, String contractEndDate, String cancelRule, String cancelTimeValue) throws Exception{
		clickOn(contractInfoAddBtn);
		typeInText(this.contractStartDate, contractStartDate);
		typeInText(this.contractEndDate, contractEndDate);
		waitForElementVisibility(gtContractIndicator);
		waitForElementVisibility(comments);
		waitForElementVisibility(contractDeleteIndicator);
		clickOn(gtContractDetailsBtn);
		waitForElementVisibility(earlyPaymentDiscount);
		waitForElementVisibility(plannedPickupsNotCancellableIndicator);
		WebElement cancellationType = findElementByXpath(cancellationTypeXpath1+cancelRule+cancellationTypeXpath2);
		clickOn(cancellationType);
		cancelRule = cancellationType.getText();
		assertTrue(cancellationType.isSelected(), cancelRule+" is not selected");
		if(cancelRule.contains("Cancel Policy")){
			cancelPolicyRadioBtn.forEach(cancelPolicyRadioBtn -> {
				if(cancelPolicyRadioBtn.getAttribute("value").equals(cancelTimeValue)){
					try {
						clickOn(cancelPolicyRadioBtn);
					} catch (Exception e) {
						e.printStackTrace();
					}
					assertTrue(cancelPolicyRadioBtn.isSelected(), "radio button is not selected");
					waitForElementVisibility(timeOfDay);
					waitForElementVisibility(daysPrior);
					waitForElementVisibility(hoursBeforePickup);
				}});
		}
		
		waitForElementVisibility(contractDetails_OkBtn.get(1));
		clickOn(contractDetails_CancelBtn);
	}
	
	public void setContractDetailsAndClickSaveBtn(String contractStartDate, String contractEndDate, String cancelRule, String cancelTimeValue) throws Exception{
		clickOn(contractInfoAddBtn);
		typeInText(this.contractStartDate, contractStartDate);
		typeInText(this.contractEndDate, contractEndDate);
		clickOn(gtContractIndicator);
		waitForElementVisibility(comments);
		waitForElementVisibility(contractDeleteIndicator);
		setContractDetailsPopUp(cancelRule, cancelTimeValue);
	}
	
	public void editContractDetails(String contractStartDate, String contractEndDate, String cancelRule, String cancelTimeValue) throws Exception{
		typeInText(this.contractStartDate, contractStartDate);
		typeInText(this.contractEndDate, contractEndDate);
		clickOn(gtContractIndicator);
		setContractDetailsPopUp(cancelRule, cancelTimeValue);
	}
	
	private void setContractDetailsPopUp(String cancelRule, String cancelTimeValue){
		clickOn(gtContractDetailsBtn);
		waitForElementVisibility(earlyPaymentDiscount);
		waitForElementVisibility(plannedPickupsNotCancellableIndicator);
		WebElement cancellationType = findElementByXpath(cancellationTypeXpath1+cancelRule+cancellationTypeXpath2);
		clickOn(cancellationType);
		cancelRule = cancellationType.getText();
		assertTrue(cancellationType.isSelected(), cancelRule+" is not selected");
		if(cancelRule.contains("Cancel Policy")){
			cancelPolicyRadioBtn.forEach(cancelPolicyRadioBtn -> {
				if(cancelPolicyRadioBtn.getAttribute("value").equals(cancelTimeValue)){
					try {
						clickOn(cancelPolicyRadioBtn);
					} catch (Exception e) {
						e.printStackTrace();
					}
					assertTrue(cancelPolicyRadioBtn.isSelected(), "radio button is not selected");
					waitForElementVisibility(timeOfDay);
					waitForElementVisibility(daysPrior);
					waitForElementVisibility(hoursBeforePickup);
				}});
		}
		
		clickOn(contractDetails_OkBtn.get(1));
		//clickOn(contractDetails_CancelBtn);
	}
	
	public void verifyContractDetails(String contractStartDate, String contractEndDate, String cancelRule, String cancelTimeValue) throws Exception{
		assertEquals(this.contractStartDate.getAttribute("value"), contractStartDate, "ContractStartDate Mismatch!");
		assertEquals(this.contractEndDate.getAttribute("value"), contractEndDate, "ContractEndDate Mismatch!");
		assertTrue(gtContractIndicator.isSelected(), "contractIndicator not selected!");
		clickOn(gtContractDetailsBtn);
		WebElement cancellationType = findElementByXpath(cancellationTypeXpath1+cancelRule+cancellationTypeXpath2);
		assertTrue(cancellationType.isSelected(), cancellationType.getText()+"is not Selected!");
		cancelRule = cancellationType.getText();
		if(cancelRule.contains("Cancel Policy")){
			cancelPolicyRadioBtn.forEach(cancelPolicyRadioBtn -> {
				if(cancelPolicyRadioBtn.getAttribute("value").equals(cancelTimeValue)){
					assertTrue(cancelPolicyRadioBtn.isSelected(), "radio button is not selected");
				}});
		}
		clickOn(contractDetails_OkBtn.get(1));
	}
	
	public void setRoutesAndRates() throws Exception{
		waitForElementVisibility(expandAll);
		waitForElementVisibility(collapseAll);
		clickOn(routesAndRatesSectionHeader);
		clickOn(routesAndRatesAddRowBtn);
		waitForElementVisibility(routesAndRatesDeleteRowBtn);
		waitForElementVisibility(routeType);
		waitForElementVisibility(routesAndRatesStartDate);
		waitForElementVisibility(routesAndRatesEndDate);
		waitForElementVisibility(fromAirportCode);
		waitForElementVisibility(fromVenueCode);
		waitForElementVisibility(toAirportCode);
		waitForElementVisibility(toVenueCode);
		waitForElementVisibility(hotel);
		waitForElementVisibility(rate);
		waitForElementVisibility(units);
		waitForElementVisibility(routesAndRatesDeleteRowCheckBox);
	}
	
	public void setRoutesAndRates(int routeType, String routesAndRatesStartDate, String routesAndRatesEndDate, 
			String fromAirportCode, String fromVenueCode, String toAirportCode, String toVenueCode, String hotel, String rate, String units) throws Exception{
		clickOn(routesAndRatesSectionHeader);
		clickOn(routesAndRatesAddRowBtn);
		editRoutesAndRates(routeType, routesAndRatesStartDate, routesAndRatesEndDate, fromAirportCode, fromVenueCode, toAirportCode, toVenueCode, hotel, rate, units);
	}
	
	public void editRoutesAndRates(int routeType, String routesAndRatesStartDate, String routesAndRatesEndDate, 
			String fromAirportCode, String fromVenueCode, String toAirportCode, String toVenueCode, String hotel, String rate, String units) throws Exception{
		selectByIndex(this.routeType, routeType);
		typeInText(this.routesAndRatesStartDate, routesAndRatesStartDate);
		typeInText(this.routesAndRatesEndDate, routesAndRatesEndDate);
		typeInText(this.fromAirportCode, fromAirportCode);
		try{
			typeInText(this.toAirportCode, toAirportCode);
		}catch(Exception e){
			unExpectedAcceptAlert();
			typeInText(this.toAirportCode, toAirportCode);
		}
		typeInText(this.rate, rate);
		selectByVisibleText(this.units, units);
	}
	
	public void verifyRoutesAndRates(int routeType, String routesAndRatesStartDate, String routesAndRatesEndDate, 
			String fromAirportCode, String fromVenueCode, String toAirportCode, String toVenueCode, String hotel, String rate, String units) throws Exception{
		clickOn(routesAndRatesSectionHeader);
		Select routeTypeDD = new Select(this.routeType);
		assertEquals(routeTypeDD.getOptions().get(routeType).getText(), routeTypeDD.getFirstSelectedOption().getText(), "RouteType Mismatch!");
		assertEquals(this.routesAndRatesStartDate.getAttribute("value"), routesAndRatesStartDate, "RoutesAndRatesStartDate Mismatch!");
		assertEquals(this.routesAndRatesEndDate.getAttribute("value"), routesAndRatesEndDate, "RoutesAndRatesStartDate Mismatch!");
		assertEquals(this.fromAirportCode.getAttribute("value"), fromAirportCode, "fromAirportCode Mismatch!");
		assertEquals(this.toAirportCode.getAttribute("value"), toAirportCode, "fromAirportCode Mismatch!");
		assertEquals(this.rate.getAttribute("value"), rate, "Rate Mismatch!");
		Select unitsDD = new Select(this.units);
		assertEquals(unitsDD.getFirstSelectedOption().getText(), units, "Units Mismatch!");
	}
	
	public void setRoutesAndRates(String fromAirportCode, String toAirportCode, String rate, String units) throws Exception{
		clickOn(routesAndRatesSectionHeader);
		clickOn(routesAndRatesAddRowBtn);
		int rowId = Integer.parseInt(routeRateRow.getAttribute("value").toString());
		typeInText(findElementById(routeStartDateId1+rowId+routeStartDateId2), currentDate());
		typeInText(findElementById(routeEndDateId1+rowId+routeEndDateId2), currentDatePlus(30));
		typeInText(findElementById(routeFromAPCodeId+rowId), fromAirportCode);
		typeInText(findElementById(routeToAPCodeId+rowId), toAirportCode);
		typeInText(findElementById(routeRateId+rowId), rate);
		selectByVisibleText(findElementByName(routeUnitsId1+rowId+routeUnitsId2), units);
	}
	
	public void setTaxesAndFuelSurcharges() throws Exception{
		clickOn(taxesAndSurchargesSectionHeader);
		clickOn(fuelSurchargeInfoAddRowBtn);
		waitForElementVisibility(fuelSurchargeStartDate);
		waitForElementVisibility(fuelSurchargeEndDate);
		waitForElementVisibility(percent);
		waitForElementVisibility(flatFee);
		waitForElementVisibility(fuelSurchargeUnits);
		waitForElementVisibility(fuelSurchargeComments);
		waitForElementVisibility(fuelSurchargeDeleteRowCheckBox);
	}
	
	public void verifyTaxesAndFuelSurcharges(String fuelSurchargeName, String fuelSurchargeStartDate, String fuelSurchargeEndDate, String percent, String flatfee, String fuelSurchargeUnits) throws Exception{
		clickOn(taxesAndSurchargesSectionHeader);
		assertEquals(this.taxesAndFuelSurchargeRules.get(0).getText().trim(), fuelSurchargeName, "tax/SurchargeName Mismatch!");
		assertEquals(this.taxesAndFuelSurchargeRules.get(1).getText().trim(), fuelSurchargeStartDate, "startDate mismatch");
		assertEquals(this.taxesAndFuelSurchargeRules.get(2).getText().trim(), fuelSurchargeEndDate, "endDate mismatch");
		assertEquals(this.taxesAndFuelSurchargeRules.get(3).getText().trim(), percent, "percent mismatch");
		assertEquals(this.taxesAndFuelSurchargeRules.get(4).getText().trim(), flatfee, "flatfee mismatch");
		assertEquals(this.taxesAndFuelSurchargeRules.get(5).getText().trim(), fuelSurchargeUnits, "units mismatch");
	}
	
	public void setSupplierWebsiteInfo() throws Exception{
		clickOn(supplierWebsiteInfoSectionHeader);
		waitForElementVisibility(scheduleDisplayIndicator);
		waitForElementVisibility(invoiceDisplayIndicator);
		waitForElementVisibility(createInvoiceIndicator);
		waitForElementVisibility(reservationDisplayIndicator);
	}
	
	public void setSupplierWebsitenInfoIndicators() throws Exception{
		clickOn(supplierWebsiteInfoSectionHeader);
		clickOn(scheduleDisplayIndicator);
		clickOn(invoiceDisplayIndicator);
		clickOn(createInvoiceIndicator);
		clickOn(reservationDisplayIndicator);
	}
	
	public void saveDetails() throws Exception{
		clickOn(saveBtn);
		takeScreenshots();
		unExpectedAcceptAlert();
	}
}
