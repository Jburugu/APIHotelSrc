package com.APIHotels.pages.airlines;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class FindOrEditRoomBlockRequestsPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// OPERATIONS -- FIND/EDIT ROOM BLOCK REQUESTS

	@FindBy(how = How.CSS, using = "input[id$='findAdhocLocCd']")
	public WebElement hotelLocation;

	@FindBy(how = How.CSS, using = "input[id$='editStartDateInputDate']")
	public WebElement startDate;

	@FindBy(how = How.CSS, using = "img[id$='editStartDatePopupButton']")
	public WebElement startDateCalendar;

	@FindBy(how = How.CSS, using = "input[id$='editEndDateInputDate']")
	public WebElement endDate;

	@FindBy(how = How.CSS, using = "img[id$='editEndDatePopupButton']")
	public WebElement endDateCalendar;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'findRBTenantList:tenantMenuId')]")
	public WebElement company;

	@FindBy(how = How.CSS, using = "input[id$='roomBlockRetrieveButton']")
	public WebElement retrievebutton;
	
	@FindBy(how=How.XPATH,using=".//*[@id='bizTravelForm:adhocResvTable']//tr")
	public List<WebElement> adhocResvTable;

	@FindBy(how = How.ID,using="updateForm:roomToCancelId:noOfRooms")
	public WebElement enterRoomsToCancel;
	
	@FindBy(how= How.ID,using="updateForm:notes")
	public WebElement cancelNotes;
	
	@FindBy(how=How.XPATH,using="//a[text()='OK']")
	public WebElement cancelRoomBlockOKBtn;
	
	@FindBy(how = How.XPATH,using=".//*[@id='bizTravelForm:findEditGlobalMessages']/dt/span")
	public WebElement noRecordsFoundMessage;
	
	@FindBy(how = How.XPATH,using="//p[@class='subHeading']")
	public WebElement supplierDetailHeader;
	
	@FindBy(how = How.XPATH,using="//table[@id='notesTable']//td[3]")
	public List<WebElement> notesList;
	

    @FindBy(how = How.XPATH,using="//table[@id='confUpdatePanelContentTable']//input[@value='OK']")
    public WebElement clickOnOK;
    
	String reservationTableRowXpath = ".//*[@id='bizTravelForm:adhocResvTable']//tr[";
	String reservationTableRowXpath1="]/td";
	String orginalRoomsCount="]/td[5]";
	String roomsAvailableCount="]/td[6]";
	String roomsUsedCount="]/td[7]";
	String roomsCancelCount="]/td[8]";
	String supplier="]/td[9]";
	String supplierNameLink="]/td[9]/a";
	String notesLink="]/td[10]/a";
	String actionLink="]/td[11]/a";

	public FindOrEditRoomBlockRequestsPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @param companyfield
	 *            - exists for latam
	 */
	public void company(String companyValue) {
		selectByValue(company, companyValue);
	}

	public void findOrEditRoomBlockRequest(String locationValue) {
		typeInText(hotelLocation, locationValue);
		waitForElementVisibility(startDateCalendar);
		typeInText(startDate, currentDate());
		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, currentDate());
		
	}
	
	public void findOrEditRoomBlockRequests(String locationValue, String startDateValue, String endDateValue){
		typeInText(hotelLocation, locationValue);
		waitForElementVisibility(startDateCalendar);
		typeInText(startDate, startDateValue);
		waitForElementVisibility(endDateCalendar);
		typeInText(endDate, endDateValue);
	}

	public void verifyRetrieveBtn() {
		waitForElementVisibility(retrievebutton);
	}
	
	public void clickRetrieveBtn(){
		clickOn(retrievebutton);
	}
	
	public int verifyAdhocBooking(String adhocType, String checkInDateValue, String checkInTimeValue,
			String checkOutDateValue, String checkOutTimeValue, String roomCountValue) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
try {
	takeScreenshots();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		int rowCount = 0;
		for (int i = 1; i < adhocResvTable.size(); i++) {
			List<WebElement> data = findElementsByXpath(reservationTableRowXpath + i + reservationTableRowXpath1);
			boolean checkInTime = false;
			boolean checkOutTime = false;
			boolean originalRooms = false;
			String adhoc = null;
			for (int j = 0; j < data.size(); j++) {

				if (j == 0) {
					adhoc = data.get(j).getText();
				}
				if (adhoc.contains("Adhoc-" + adhocType)) {
					if (!checkInTime)
						checkInTime = data.get(j).getText().equals(checkInDateValue + " " + checkInTimeValue);
					if (!checkOutTime)
						checkOutTime = data.get(j).getText().equals(checkOutDateValue + " " + checkOutTimeValue);
					if (!originalRooms)
						originalRooms = data.get(j).getText().equals(roomCountValue);
				}

			}
			rowCount++;
			if (checkInTime && checkOutTime && originalRooms == true)
				return rowCount;

		}
		return rowCount;
	}
	
	public String cancelRoomBlock(String roomCountValue,String cancelRoomValue, String cancelNotesValue) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int roomCount = Integer.parseInt(roomCountValue);
		int cancelRoom = Integer.parseInt(cancelRoomValue);
		if((cancelRoom == 0)||(cancelRoom>roomCount)){
			typeInText(enterRoomsToCancel, roomCountValue);
		}else
			typeInText(enterRoomsToCancel, cancelRoomValue);
		String roomsToCancel = enterRoomsToCancel.getAttribute("value");
		typeInText(cancelNotes, cancelNotesValue);
		clickOn(cancelRoomBlockOKBtn);
		return roomsToCancel;
	}
	
	public void clickOnCancelLink(int rowCount) {
		clickOn(findElementByXpath(reservationTableRowXpath + rowCount + actionLink));
	}

	public int verifyCancelledRowStatus(int rowCount, String roomCountValue, String roomsToCancel) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		String originalRooms = findElementByXpath(reservationTableRowXpath + rowCount + orginalRoomsCount).getText();
		String roomsAvail = findElementByXpath(reservationTableRowXpath + rowCount + roomsAvailableCount).getText();
		String roomsCancelled = findElementByXpath(reservationTableRowXpath + rowCount + roomsCancelCount).getText();
		int availRoomCountValue = Integer.parseInt(roomCountValue.toString()) - Integer.parseInt(roomsToCancel.toString());
		Assert.assertEquals(originalRooms, roomCountValue, "Room count value mismatch");
		Assert.assertEquals(Integer.parseInt(roomsAvail), availRoomCountValue, "Avail room count value is not calculated correctly");
		Assert.assertEquals(roomsCancelled, roomsToCancel, "validate the rooms cancelled");
		return availRoomCountValue;
	}
	
	public void verifyDataExists(String expectedValue) {
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='waitHeader']")));
		int count = adhocResvTable.size();
		if (count > 1) {
			Assert.assertTrue(adhocResvTable.get(1).isDisplayed(),
					"Room block Reservation is displayed for the search criteria");
		} else
			Assert.assertEquals(noRecordsFoundMessage.getText(), expectedValue, "Reservations are displayed");
	}
	
	public void clickOnSupplierNameAndValidate(int rowCount) {
		String currentWindowHandle = driver.getWindowHandle();
		String supplierName =findElementByXpath(reservationTableRowXpath + rowCount + supplierNameLink).getText();
		clickOn(findElementByXpath(reservationTableRowXpath + rowCount + supplierNameLink));
		switchToNewWindow(currentWindowHandle);
		Assert.assertEquals(supplierDetailHeader.getText(), "Supplier: " + supplierName,"Supplier Detail page is not displayed");
		close();
		switchToWindow(currentWindowHandle);
	}
	
	public void clickOnNotesLink(int rowCount,String notesValue,String roomCountValue){
		String currentWindowHandle = driver.getWindowHandle();
		clickOn(findElementByXpath(reservationTableRowXpath + rowCount + notesLink ));
		switchToNewWindow(currentWindowHandle);
		int notesCount = notesList.size();
		boolean notes = false;
		boolean rooms = false;
		for(int i=0;i<notesCount;i++){
			if(!notes){
				notes = notesList.get(i).getText().equals(notesValue);
			}if(!rooms){
				rooms = notesList.get(i).getText().equals(roomCountValue);
			}
		}
		if(notes && rooms == true){
			Assert.assertTrue(notesList.get(0).getText().equals(roomCountValue),"Room counts values are not displayed");
		}
		close();
		switchToWindow(currentWindowHandle);
	}
	
	public void verifyRoomBlocks(int rowCount, String roomCountValue, String roomsUsedValue){
		String originalRooms = findElementByXpath(reservationTableRowXpath + rowCount + orginalRoomsCount).getText();
		String roomsAvail = findElementByXpath(reservationTableRowXpath + rowCount + roomsAvailableCount).getText();
		String roomsUsed = findElementByXpath(reservationTableRowXpath + rowCount + roomsUsedCount).getText();
		int availRoomCountValue = Integer.parseInt(originalRooms.toString()) - Integer.parseInt(roomsUsedValue.toString());
		int originalRoomCountValue = Integer.parseInt(roomsAvail.toString()) + Integer.parseInt(roomsUsedValue.toString());
		Assert.assertEquals(originalRooms, roomCountValue ,"Room count value mismatch");
		Assert.assertEquals(Integer.parseInt(originalRooms), originalRoomCountValue, "Original room count is not matching with the addition of roomsavail + roomsused");
		Assert.assertEquals(Integer.parseInt(roomsAvail), availRoomCountValue, "Avail room count value is not calculated correctly");
		Assert.assertEquals(roomsUsed, roomsUsedValue, "Rooms used value mismatch");
	}
	
	public void verifyStatus(int rowCount,String expectedValue){
		String status = findElementByXpath(reservationTableRowXpath + rowCount + supplier).getText();
		Assert.assertEquals(status, expectedValue ,"Status is not changed to "+status);
	}
	
	public void verifyUsedRoomBlock(int rowCount,String roomCountValue,String roomsToCancel,String availableRooms ){
		String originalRooms = findElementByXpath(reservationTableRowXpath + rowCount + orginalRoomsCount).getText();//
		String roomsAvail = findElementByXpath(reservationTableRowXpath + rowCount + roomsAvailableCount).getText();
		String roomsUsed = findElementByXpath(reservationTableRowXpath + rowCount + roomsUsedCount).getText();
		String roomsCancelled = findElementByXpath(reservationTableRowXpath + rowCount + roomsCancelCount).getText();
		int usedRooms = Integer.parseInt(roomCountValue.toString()) - Integer.parseInt(roomsToCancel.toString());
		Assert.assertEquals(originalRooms, roomCountValue ,"Room count value mismatch");
		Assert.assertEquals(Integer.parseInt(roomsUsed), usedRooms ,"Used Rooms count mismatch");
		Assert.assertEquals(roomsCancelled, roomsToCancel, "Cancelled room mismatch");
		Assert.assertEquals(roomsAvail, availableRooms, "Available rooms mismatch");
	}
	
	public void clickCancelOkBtnAircanada() {
		 clickOn(clickOnOK);
	}
	
	public void clickOnOkBtn(){
		clickOn(cancelRoomBlockOKBtn);
	}

}
