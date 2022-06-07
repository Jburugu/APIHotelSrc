package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;

public class Page_AirlineHotelSupplier extends BasePage{
	
	@FindBy(name = "commonNotes")
	private WebElement commonNotes;
	
	@FindBy(id = "cNotes")
	private WebElement clientNotes;
	
	@FindBy(id = "tenantType")
	private WebElement clients;
	
	@FindBy(name = "deleteSupplier")
	private WebElement archiveSupplierIndicator;
	
	@FindBy(id = "paymentTerms")
	private WebElement paymentTerm;
	
	@FindBy(id = "vendorId")
	private WebElement vendorId;
	
	@FindBy(id = "locationId")
	private WebElement locationId;
	
	@FindBy(name = "visibleToAirline")
	private WebElement showInAirlineWebSiteIndicator;
	
	@FindBy(id = "txtParing")
	private WebElement pairingFileName;
	
	@FindBy(id = "crewTrac")
	private WebElement cmsName;
	
	@FindBy(id = "exportPriority")
	private WebElement exportPriority;
	
	@FindBy(id = "apiSuppliercode")
	private WebElement apiSupplierNo;
	
	@FindBy(name = "contactList[0].deptName")
	private WebElement deptTitle;
	
	@FindBy(name = "contactList[0].name")
	private WebElement name;
	
	@FindBy(name = "contactList[0].phone")
	private WebElement phone;
	
	@FindBy(name = "contactList[0].fax")
	private WebElement fax;
	
	@FindBy(name = "contactList[0].email")
	private WebElement email;
	
	@FindBy(id = "contactList[0].primary")
	private WebElement primaryContactIndicator;
	
	@FindBy(id = "contactList[0].exportableContact")
	private WebElement exportContactIndicator;
	
	/*@FindBy(name = "contactList[0].preferredMode")
	private List<WebElement> preferredMode;*/
	
	String preferredModeXpath1 = "//*[@name = 'contactList[0].preferredMode' and @value = '";
	String preferredModeXpath2 = "']";
	
	@FindBy(name = "contactList[0].common")
	private List<WebElement> commonIndicator;
	
	@FindBy(name = "contactList[0].deleted")
	private WebElement deleteIndicator;
	
	@FindBy(xpath = "//*[@onclick = 'addHotelContractInformationRow();ACES_Modified();' and @class = 'Aces_Btn_Add']")
	private WebElement contractInfoAddBtn;
	
	@FindBy(name = "contractList[0].startDate")
	private WebElement contractStartDate;
	
	@FindBy(name = "contractList[0].endDate")
	private WebElement contractEndDate;
	
	@FindBy(id = "contractList[0].contract")
	private WebElement contractIndicator;
	
	@FindBy(xpath = "//*[@id='contractTable']/tbody/tr[2]/td[4]")
	private WebElement contractDetailsBtn;
	
	@FindBy(xpath = "//*[@id = 'alertBot']/input[1]")
	private WebElement makeChangesAlert_OkBtn;
	
	@FindBy(xpath = "//*[@id = 'alertBot']/input[2]")
	private WebElement makeChangesAlert_CancelBtn;
	
	@FindBy(name = "contractList[0].comments")
	private WebElement comments;
	
	@FindBy(name = "contractList[0].deleted")
	private WebElement contractDeleteIndicator;
	
	@FindBy(name = "save")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//*[contains(text(),'Airport/Venue/Hotel Transit Times')]")
	private WebElement airportVenueHotelTransitTimesSectionHeader;
	
	@FindBy(xpath = "//*[@onclick = 'addTransitTimesRow();ACES_Modified();' and @class = 'Aces_Btn_Add']")
	private WebElement transitTimesAddRowBtn;
	
	@FindBy(xpath = "//*[contains(@id,'routeTypeObjID')]")
	private WebElement routeType;
	
	@FindBy(name = "apHotelTransitList[999].selectedItems")
	private List<WebElement> hotelTransitTimesDaysOfWeek;
	
	@FindBy(xpath = "//input[contains(@id,'frAirPortCode')]")
	private WebElement fromAirportCode;
	
	@FindBy(xpath = "//*[contains(@name, 'apHotelTransitLis') and contains(@name, 'startDate')]")
	private WebElement transitStartDate;
	
	@FindBy(xpath = "//*[contains(@name, 'apHotelTransitLis') and contains(@name, 'endDate')]")
	private WebElement transitEndDate;
	
	@FindBy(xpath = "//*[contains(@name, 'apHotelTransitLis') and contains(@name, 'dummyStartTime')]")
	private WebElement transitStartTime;
	
	@FindBy(xpath = "//*[contains(@name, 'apHotelTransitLis') and contains(@name, 'dummyEndTime')]")
	private WebElement transitEndTime;
	
	@FindBy(xpath = "//*[contains(@name, 'apHotelTransitLis') and contains(@name, 'transitTime')]")
	private WebElement transitTime;
	
	@FindBy(xpath = "//*[contains(@id,'transit')]")
	private WebElement transitTimesDeleteRowCheckBox;
	
	@FindBy(xpath = "//*[@onclick = 'deleteHotelTransit(this.form);' and @class = 'Aces_Btn_Add']")
	private WebElement transitTimesDeleteBtn;
	
	@FindBy(xpath = "//*[contains(text(),'Hotel Use Rules')]")
	private WebElement hotelUseRulesSectionHeader;
	
	@FindBy(xpath = "//*[@onclick = 'addHotelUseRulesRow();ACES_Modified();' and @class = 'Aces_Btn_Add']")
	private WebElement hotelUseRulesAddRowBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deleteHotelLayover(this.form);' and @class = 'Aces_Btn_Add']")
	private WebElement hotelUseRulesDeleteBtn;
	
	@FindBy(name = "hotelLayoverList[0].selectedItems")
	private List<WebElement> hotelLayoverDaysOfWeek;
	
	@FindBy(id = "hotelLayoverList[0].layoverLocCode")
	private WebElement hotelLayoverLocation;
	
	@FindBy(id = "hotelLayoverList[0].startDate")
	private WebElement hotelLayoverStartDate;
	
	@FindBy(id = "hotelLayoverList[0].endDate")
	private WebElement hotelLayoverEndDate;
	
	@FindBy(xpath = "//*[contains(@name, 'layoverFrom')]")
	private WebElement hotelLayoverFromTime;
	
	@FindBy(xpath = "//*[contains(@name, 'layoverTo')]")
	private WebElement hotelLayoverToTime;
	
	@FindBy(id = "layover0")
	private WebElement hotelUseRulesDeleteRowCheckBox;
	
	@FindBy(xpath = "//*[contains(text(),'Tax / VAT Information')]")
	private WebElement taxVatInfoSectionHeader;
	
	@FindBy(name = "taxExemption")
	private WebElement taxVatInfoTaxExemption;
	
	@FindBy(id = "row4")
	private WebElement supplierWebsiteInfoSectionHeader;
	
	@FindBy(id = "scheduleId")
	private WebElement supplierInfoScheduleIndicator;
	
	@FindBy(id = "invoiceId")
	private WebElement supplierInfoInvoiceIndicator;
	
	@FindBy(id = "createInvoiceId")
	private WebElement supplierInfoCreateInvoiceIndicator;
	
	@FindBy(id = "reservationId")
	private WebElement supplierInfoReservationIndicator;
	
	@FindBy(id = "Expand")
	private WebElement expandAllLink;
	
	@FindBy(id = "Collapse")
	private WebElement collapseAllLink;
	
	@FindBy(xpath = "//*[@id = 'inc3']/div/table/tbody/tr[2]/td")
	private List<WebElement> taxVatInfoCols;
	
	String daysOfWeekXpath1 = "//*[contains(@name, 'apHotelTransitList') and contains(@name, 'selectedItems') and @value = '";//*[@name = 'apHotelTransitList[999].selectedItems' and @value = '";
	String daysOfWeekXpath2 = "']";
	
	String layoverDaysOfWeekXpath1 = "//*[@name = 'hotelLayoverList[0].selectedItems' and @value = '";
	String layoverDaysOfWeekXpath2 = "']";
	
	@FindBy(xpath = "//*[@name = 'delete' and @onclick = 'deleteAirlineSupplier()']")
	private WebElement airlineSupplierDeleteBtn;
	
	@FindBy(xpath = "//*[@id = 'uiErrorMsgBodyDiv' and contains(text(), 'It cannot be deleted')]")
	private List<WebElement> errorMsgDiv;
	
	@FindBy(id = "alertOK")
	private WebElement alert_OkBtn;
	
	@FindBy(id = "alertBody")
	private WebElement successfulDelMsg;
	
	@FindBy(id = "contractCheck0")
	private WebElement contractCheck;
	
	
	public EventFiringWebDriver driver=null;

	public Page_AirlineHotelSupplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void setGeneralHotelSupplierDetails(String pairingFileName) throws Exception{
		waitForElementVisibility(commonNotes);
		waitForElementVisibility(clientNotes);
		waitForElementVisibility(clients);
		waitForElementVisibility(archiveSupplierIndicator);
		waitForElementVisibility(paymentTerm);
		waitForElementVisibility(showInAirlineWebSiteIndicator);
		waitForElementVisibility(vendorId);
		waitForElementVisibility(locationId);
		typeInText(this.pairingFileName, pairingFileName);
		waitForElementVisibility(cmsName);
		waitForElementVisibility(this.apiSupplierNo);
		waitForElementVisibility(exportPriority);
	}
	
	public void setContactDetails(String preferredMode) throws Exception{
		waitForElementVisibility(name);
		waitForElementVisibility(phone);
		waitForElementVisibility(fax);
		waitForElementVisibility(email);
		WebElement preferredContactMode = findElementByXpath(preferredModeXpath1+preferredMode+preferredModeXpath2);
		clickOn(preferredContactMode);
		assertTrue(preferredContactMode.isSelected(), preferredMode+" is not selected");
		waitForElementVisibility(primaryContactIndicator);
		waitForElementVisibility(exportContactIndicator);
		waitForElementVisibility(commonIndicator.get(1));
		waitForElementVisibility(deleteIndicator);
	}
	
	public void setIndicators(String preferredMode){
		WebElement preferredContactMode = findElementByXpath(preferredModeXpath1+preferredMode+preferredModeXpath2);
		clickOn(preferredContactMode);
		clickOn(primaryContactIndicator);
		clickOn(exportContactIndicator);
	}
	
	public void verifyContactDetails(String deptTitle, String name, String phone, String fax, String email){
		assertEquals(this.deptTitle.getAttribute("value"), deptTitle, "deptTitle mismatch");
		assertEquals(this.name.getAttribute("value"), name, "name mismatch");
		assertEquals(this.phone.getAttribute("value"), phone, "phone mismatch");
		assertEquals(this.fax.getAttribute("value"), fax, "fax mismatch");
	}
	
	public void editContactDetails(String deptTitle, String name, String phone, String fax, String email){
		typeInText(this.deptTitle, deptTitle);
		typeInText(this.name, name);
		typeInText(this.phone, phone);
		typeInText(this.fax, fax);
		typeInText(this.email, email);
	}
	
	public void setContractDetails(String contractStartDate, String contractEndDate) throws Exception{
		clickOn(contractInfoAddBtn);
		typeInText(this.contractStartDate, contractStartDate);
		typeInText(this.contractEndDate, contractEndDate);
		waitForElementVisibility(contractIndicator);
		waitForElementVisibility(comments);
		waitForElementVisibility(contractDeleteIndicator);
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(contractDetailsBtn);
		try{Thread.sleep(3000);}catch(Exception e){}
		switchToNewWindow(currentWindowHandle);
		waitForPageToLoad("5");
	}
	
	public void editContractDetails(String contractStartDate, String contractEndDate) throws Exception{
		typeInText(this.contractStartDate, contractStartDate);
		typeInText(this.contractEndDate, contractEndDate);
	}
	
	public void setContractIndicator() throws Exception{
		if(!contractIndicator.isSelected())
			clickOn(contractIndicator);
	}
	
	public void setAirportVenueHotelTransitTimesDetails() throws Exception{
		waitForElementVisibility(expandAllLink);
		waitForElementVisibility(collapseAllLink);
		clickOn(airportVenueHotelTransitTimesSectionHeader);
		clickOn(transitTimesAddRowBtn);
		waitForElementVisibility(transitTimesDeleteBtn);
		waitForElementVisibility(routeType);
		hotelTransitTimesDaysOfWeek.forEach(daysOfWeek -> waitForElementVisibility(daysOfWeek));
		waitForElementVisibility(fromAirportCode);
		waitForElementVisibility(transitStartDate);
		waitForElementVisibility(transitEndDate);
		waitForElementVisibility(transitStartTime);
		waitForElementVisibility(transitEndTime);
		waitForElementVisibility(transitTime);
		waitForElementVisibility(transitTimesDeleteRowCheckBox);
	}
	
	public void setAirportVenueHotelTransitTimesDetails(int routeType, String daysOfWeek, String fromAirportCode, String transitStartDate, String transitEndDate, 
			String transitStartTime, String transitEndTime, String transitTime) throws Exception{
		clickOn(airportVenueHotelTransitTimesSectionHeader);
		clickOn(transitTimesAddRowBtn);
		waitForElementVisibility(transitTimesDeleteBtn);
		selectByIndex(this.routeType, routeType);
		
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		typeInText(this.fromAirportCode, fromAirportCode);
		typeInText(this.transitStartDate, transitStartDate);
		typeInText(this.transitEndDate, transitEndDate);
		typeInText(this.transitStartTime, transitStartTime);
		typeInText(this.transitEndTime, transitEndTime);
		typeInText(this.transitTime, transitTime);
	}
	
	public void editAirportVenueHotelTransitTimesDetails(int routeType, String daysOfWeek, String fromAirportCode, String transitStartDate, String transitEndDate, 
			String transitStartTime, String transitEndTime, String transitTime) throws Exception{
		selectByIndex(this.routeType, routeType);
		
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		typeInText(this.fromAirportCode, fromAirportCode);
		typeInText(this.transitStartDate, transitStartDate);
		typeInText(this.transitEndDate, transitEndDate);
		typeInText(this.transitStartTime, transitStartTime);
		typeInText(this.transitEndTime, transitEndTime);
		typeInText(this.transitTime, transitTime);
	}
	
	public void verifyAirportVenueHotelTransitTimesDetails(String routeType, String daysOfWeek, String fromAirportCode, String transitStartDate, String transitEndDate, 
			String transitStartTime, String transitEndTime, String transitTime) throws Exception{
		clickOn(airportVenueHotelTransitTimesSectionHeader);
		waitForElementVisibility(transitTimesDeleteBtn);
		Select routeTypeDD = new Select(this.routeType);
		WebElement routeTypeOpt = routeTypeDD.getFirstSelectedOption();
		assertEquals(routeTypeOpt.getAttribute("value"), routeTypeDD.getOptions().get(Integer.parseInt(routeType)).getAttribute("value"), "routeType mismatch");
		Arrays.asList(daysOfWeek.split(",")).forEach(day -> {
			try {
				assertTrue(findElementByXpath(daysOfWeekXpath1+day+daysOfWeekXpath2).isSelected(), "day " + day+ " is not selected");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		assertEquals(this.fromAirportCode.getAttribute("value"), fromAirportCode, "FromAirportCode mismatch");
		assertEquals(this.transitStartDate.getAttribute("value"), transitStartDate, "transitStartDate mismatch");
		assertEquals(this.transitEndDate.getAttribute("value"), transitEndDate, "transitEndDate mismatch");
		assertEquals(this.transitStartTime.getAttribute("value"), transitStartTime, "transitStartTime mismatch");
		assertEquals(this.transitEndTime.getAttribute("value"), transitEndTime, "transitEndTime mismatch");
		assertEquals(this.transitTime.getAttribute("value"), transitTime, "transitTime mismatch");
	
	}
	
	public String getTransitTime(String routeType, String fromAirportCode) throws Exception
	{
		String APtoHotelTransitMins="";
		clickOn(airportVenueHotelTransitTimesSectionHeader);
		waitForElementVisibility(transitTimesDeleteBtn);
		Select routeTypeDD = new Select(this.routeType);
		WebElement routeTypeOpt = routeTypeDD.getFirstSelectedOption();
		assertEquals(routeTypeOpt.getAttribute("value"), routeTypeDD.getOptions().get(Integer.parseInt(routeType)).getAttribute("value"), "routeType mismatch");
		assertEquals(this.fromAirportCode.getAttribute("value"), fromAirportCode, "FromAirportCode mismatch");
		String APtoHotelTransitTimeValue=this.transitTime.getAttribute("value");
		SimpleDateFormat format = new SimpleDateFormat("mm");
		Date APtoHotelTransitTime = format.parse(APtoHotelTransitTimeValue);
		System.out.println(APtoHotelTransitTime);
		String formattedTime=APtoHotelTransitTime.toString();
		String[] atoHMins= formattedTime.split(" ");
		APtoHotelTransitMins=atoHMins[3];
		System.out.println(APtoHotelTransitMins);
		return APtoHotelTransitMins;
		
	}
	
	public void setHotelUseRules() throws Exception{
		clickOn(hotelUseRulesSectionHeader);
		clickOn(hotelUseRulesAddRowBtn);
		hotelLayoverDaysOfWeek.forEach(hotelLayoverDaysOfWeek -> waitForElementVisibility(hotelLayoverDaysOfWeek));
		waitForElementVisibility(hotelLayoverLocation);
		waitForElementVisibility(hotelLayoverStartDate);
		waitForElementVisibility(hotelLayoverEndDate);
		waitForElementVisibility(hotelLayoverFromTime);
		waitForElementVisibility(hotelLayoverToTime);
		waitForElementVisibility(hotelUseRulesDeleteRowCheckBox);
	}
	
	public void setHotelUseRules(String layoverDaysOfWeek, String hotelLayoverLocation, String layoverStartDate, String layoverEndDate, String layoverFromTime, String layoverToTime) throws Exception{
		clickOn(hotelUseRulesSectionHeader);
		clickOn(hotelUseRulesAddRowBtn);
		Arrays.asList(layoverDaysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(layoverDaysOfWeekXpath1+day+layoverDaysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		typeInText(this.hotelLayoverLocation, hotelLayoverLocation);
		typeInText(this.hotelLayoverStartDate, layoverStartDate);
		typeInText(this.hotelLayoverEndDate, layoverEndDate);
		typeInText(this.hotelLayoverFromTime, layoverFromTime);
		typeInText(this.hotelLayoverToTime, layoverToTime);
	}
	
	public void editHotelUseRules(String layoverDaysOfWeek, String hotelLayoverLocation, String layoverStartDate, String layoverEndDate, String layoverFromTime, String layoverToTime) throws Exception{
		Arrays.asList(layoverDaysOfWeek.split(",")).forEach(day -> {
			try {
				clickOn(findElementByXpath(layoverDaysOfWeekXpath1+day+layoverDaysOfWeekXpath2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		typeInText(this.hotelLayoverLocation, hotelLayoverLocation);
		typeInText(this.hotelLayoverStartDate, layoverStartDate);
		typeInText(this.hotelLayoverEndDate, layoverEndDate);
		typeInText(this.hotelLayoverFromTime, layoverFromTime);
		typeInText(this.hotelLayoverToTime, layoverToTime);
	}
	
	public void verifyHotelUseRules(String layoverDaysOfWeek, String hotelLayoverLocation, String layoverStartDate, String layoverEndDate, String layoverFromTime, String layoverToTime) throws Exception{
		clickOn(hotelUseRulesSectionHeader);
		Arrays.asList(layoverDaysOfWeek.split(",")).forEach(day -> {
			try {
				assertTrue(findElementByXpath(layoverDaysOfWeekXpath1+day+layoverDaysOfWeekXpath2).isSelected(), day+" is not selected");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		assertEquals(this.hotelLayoverLocation.getAttribute("value"), hotelLayoverLocation, "layoverlocation mismatch");
		assertEquals(this.hotelLayoverStartDate.getAttribute("value"), layoverStartDate, "layoverStartDate mismatch");
		assertEquals(this.hotelLayoverEndDate.getAttribute("value"), layoverEndDate, "layoverEndDate mismatch");
		assertEquals(this.hotelLayoverFromTime.getAttribute("value"), layoverFromTime, "layoverFromTime mismatch");
		assertEquals(this.hotelLayoverToTime.getAttribute("value"), layoverToTime, "layoverEndTime mismatch");
	}
	
	
	public void setTaxVatInformation() throws Exception{
		clickOn(taxVatInfoSectionHeader);
		waitForElementVisibility(taxVatInfoTaxExemption);
	}
	
	public void verifyTaxVatInformation() throws Exception{
		clickOn(taxVatInfoSectionHeader);
		
		
	}
	
	public void setSupplierWebsitenInfo() throws Exception{
		clickOn(supplierWebsiteInfoSectionHeader);
		waitForElementVisibility(supplierInfoScheduleIndicator);
		waitForElementVisibility(supplierInfoInvoiceIndicator);
		waitForElementVisibility(supplierInfoCreateInvoiceIndicator);
		waitForElementVisibility(supplierInfoReservationIndicator);
	}
	
	public void setSupplierWebsitenInfoIndicators() throws Exception{
		clickOn(supplierWebsiteInfoSectionHeader);
		clickOn(supplierInfoScheduleIndicator);
		clickOn(supplierInfoInvoiceIndicator);
		clickOn(supplierInfoCreateInvoiceIndicator);
		clickOn(supplierInfoReservationIndicator);
	}
	
	public void verifySupplierWebsitenInfoIndicators() throws Exception{
		clickOn(supplierWebsiteInfoSectionHeader);
		assertTrue(supplierInfoScheduleIndicator.isSelected(), "ScheduleIndicator is not selected");
		assertTrue(supplierInfoInvoiceIndicator.isSelected(), "InvoiceIndicator is not selected");
		assertTrue(supplierInfoCreateInvoiceIndicator.isSelected(), "CreateInvoiceIndicator is not selected");
		assertTrue(supplierInfoReservationIndicator.isSelected(), "ReservationIndicator is not selected");
	}
	
	public void clickHotelContractDetailsBtn() throws Exception{
		String currentWindowHandle = driver.getWindowHandle();
		System.out.println(currentWindowHandle);
		clickOn(contractDetailsBtn);
		try{Thread.sleep(3000);}catch(Exception e){}
		switchToNewWindow(currentWindowHandle);
		waitForPageToLoad("5");
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
	
	public void verifyContactIndicatorsStatus(String preferredMode) throws Exception{
		WebElement preferredContactMode = findElementByXpath(preferredModeXpath1+preferredMode+preferredModeXpath2);
		assertTrue(preferredContactMode.isSelected(), "preferredContact indicator not selected");
		assertTrue(primaryContactIndicator.isSelected(), "primary indicator not selected");
		assertTrue(exportContactIndicator.isSelected(), "export indicator not selected");
	}
	
	public void verifyContractDetails(String contractStartDate, String contractEndDate) throws Exception{
		assertEquals(this.contractStartDate.getAttribute("value"), contractStartDate, "ContractStartDate mismatch");
		assertEquals(this.contractEndDate.getAttribute("value"), contractEndDate, "ContractEndDate mismatch");
	}
	
	public void saveDetails() throws Exception{
		clickOn(saveBtn);
		takeScreenshots();
		unExpectedAcceptAlert();
		
		Thread.sleep(5000);
	}
	
	public void deleteDetails() throws Exception{
		clickOn(airlineSupplierDeleteBtn);
		unExpectedAcceptAlert();
		takeScreenshots();
		clickOn(alert_OkBtn);
	}
	
	public void verifyDeleteMsg(String deleteMsg) throws Exception{
		if(deleteMsg.equals("NotAllow"))
			assertTrue(errorMsgDiv.get(1).isDisplayed(), "Alert Msg Verification Failed!");
		else if(deleteMsg.equals("Allow"))
			assertTrue(successfulDelMsg.getText().trim().contains("Successfully Deleted"), "Alert Msg Verification Failed!");
		takeScreenshots();
		clickOn(alert_OkBtn);
	}
	
		public void setOnlyContractEndDate(String contractEndDate) throws Exception{
			String contractStartDate = getTextOfElement(this.contractStartDate);
			typeInText(this.contractEndDate, contractStartDate);
			typeInText(this.contractEndDate, contractEndDate);
	
	}
		public void setGTContractIndicator() throws Exception{
			if(!contractCheck.isSelected())
				clickOn(contractCheck);
		}
	
}	
