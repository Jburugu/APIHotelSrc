package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_Assignments extends BasePage{

	@FindBy(id = "supSelectBidPeriod")
	private WebElement bidPeriod;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType; //HOTEL, GT, BOTH
	
	@FindBy(id = "city")
	private WebElement city;
	
	@FindBy(id = "unAssigned")
	private WebElement status_Unassigned;
	
	@FindBy(id = "tba")
	private WebElement status_TBA;
	
	@FindBy(id = "autoAssigned")
	private WebElement status_AutoAssigned;
	
	@FindBy(id = "manualAssigned")
	private WebElement status_ManualAssigned;
	
	@FindBy(id = "allAssigned")
	private WebElement status_All;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id = 'hotelSupplier']/tbody/tr")
	private List<WebElement> hotelSupplierRows;
	
	@FindBy(name = "assignservice")
	private WebElement assignSelectedServiceBtn;
	
	private String hotelSupplierName = "htSupplierName";
	private String hotelSupplierSelectBtn = "htSupplierOptId";
	private String hotelReservationCheckBox = "htResrvSelect";
	private String hotelAssignmentOptionsText = "htHRefId";
	
	@FindBy(xpath = "//*[@id = 'htSuppheaderRowId']/tbody/tr")
	private List<WebElement> hotelReservationsTableRows;
	
	String serviceTypeXpath1 = "//*[@name = 'serviceType' and @value = '";
			
	String serviceTypeXpath2 = "']";
	
	@FindBy(name = "saveAssignment")
	private WebElement saveAssignmentsBtn;
	
	@FindBy(xpath = "//*[text()='GT Reservations']")
	private WebElement gtReservationsHeader;
	
	private String gtSupplierName = "gtSupplierName";
	private String gtSupplierSelectBtn = "gtSupplierOptId";
	private String gtReservationCheckBox = "gtResrvSelect";
	private String gtAssignmentOptionsText = "gtHRefId";
	
	
	@FindBy(xpath = "//*[@id = 'gtSubMasterTableID']/tbody/tr")
	private List<WebElement> gtReservationsTableRows;
	
	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_Assignments(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchAssignments(String serviceType, String city) throws Exception{
		//logger.info("*** AssignmentsPage -> searchAssignments method started ***");
		waitForElementVisibility(bidPeriod);
		clickOn(findElementByXpath(serviceTypeXpath1+serviceType+serviceTypeXpath2));
		if(!city.isEmpty())
			typeInText(this.city, city);
		//clickOn(status_Unassigned);
		waitForElementVisibility(status_AutoAssigned);
		waitForElementVisibility(status_ManualAssigned);
		waitForElementVisibility(status_TBA);
		clickOn(status_All);
		clickOn(searchBtn);
		//logger.info("*** searchAssignments method completed ***");
	}
	
	
	public void assignSelectedSupplier(String supplierName) throws Exception{
		//logger.info("*** AssignmenrsPage -> assignSelectedSupplier method started ***");
		for(int rowId = 0; rowId < hotelSupplierRows.size(); rowId++)
			if(findElementById(this.hotelSupplierName+rowId).getText().trim().equals(supplierName)){
				clickOn(findElementById(hotelSupplierSelectBtn+rowId));
				break;
			}
		waitForElementVisibility(assignSelectedServiceBtn);
		//logger.info("*** assignSelectedSupplier method completed ***");
	}
	
	public void assignHotelSupplierToReservation() throws Exception{
		int hotelReservationsBeforeAssignment = hotelReservationsTableRows.size();
		clickOn(findElementById(hotelSupplierSelectBtn+0));
		clickOn(findElementById(hotelReservationCheckBox+0));
		String supplierText = findElementById(this.hotelSupplierName+0).getText().trim();
		clickOn(assignSelectedServiceBtn);
		assertEquals(findElementById(hotelAssignmentOptionsText+0).getText().trim(), supplierText, "Supplier not assigned to reservation!");
		clickOn(saveAssignmentsBtn);
		waitForPageLoad(getDriver());
		//clickOn(alertOkBtn);
		unExpectedAcceptAlert();
		assertTrue(hotelReservationsTableRows.size()<hotelReservationsBeforeAssignment, "Manual Assignment Failed!");
	}
	
	public void assignHotelSupplierToReservationFromErrorsAndWarnings() throws Exception{
		//int hotelReservationsBeforeAssignment = hotelReservationsTableRows.size();
		clickOn(findElementById(hotelSupplierSelectBtn+0));
		clickOn(findElementById(hotelReservationCheckBox+0));
		String supplierText = findElementById(this.hotelSupplierName+0).getText().trim();
		clickOn(assignSelectedServiceBtn);
		assertEquals(findElementById(hotelAssignmentOptionsText+0).getText().trim(), supplierText, "Supplier not assigned to reservation!");
		clickOn(saveAssignmentsBtn);
		clickOn(alertOkBtn);
		unExpectedAcceptAlert();
	}
	
	public void assignGTSupplierToReservation() throws Exception{
		clickOn(gtReservationsHeader);
		int gtReservationsBeforeAssignment = gtReservationsTableRows.size(); 
		clickOn(findElementById(gtSupplierSelectBtn+0));
		clickOn(findElementById(gtReservationCheckBox+0));
		String supplierText = findElementById(this.gtSupplierName+0).getText().trim();
	}
}
