package com.APIHotels.pages.ACESII;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_FindTrip extends BasePage{

	@FindBy(id = "tripNumber")
	private WebElement tripNumber;
	
	@FindBy(id = "operatingDate")
	private WebElement operatingDate;
	
	@FindBy(id = "timeZone")
	private WebElement timeFormat;
	
	@FindBy(id = "flightNumber")
	private WebElement flightNumber;
	
	@FindBy(id = "flightFromDate")
	private WebElement fromDate;
	
	@FindBy(id = "flightToDate")
	private WebElement toDate;
	
	@FindBy(id = "crewId")
	private WebElement crewId;
	
	@FindBy(name = "change")
	private WebElement retrieveBtn;
	
	@FindBy(id = "row")
	private WebElement resultTable;
	
	@FindBy(xpath = "//*[@id = 'row']/tbody/tr")
	private List<WebElement> resultRows;
	
	@FindBy (xpath ="//table[@id='row']//td[1]")
	private WebElement tripNumberIntable;

	@FindBy (xpath ="//table[@id='row']//td[1]/a")
	private WebElement tripNumberlink;
	
	@FindBy (xpath="//*[@id='tripNumberDetails']/div[2]/table//tr[2]/td[8]")
	private WebElement getArrivalTime;
	
	@FindBy (xpath="//*[@id='tripNumberDetails']/div[2]/table//tr[2]/td[7]")
	private WebElement getDepartureTime;
	
	@FindBy(xpath="//*[@id='tripNumberDetails']/div[2]/table//tr[7]/td[6]")
	private WebElement getShowTime;
	
	@FindBy(xpath="//*[@id='tripNumberDetails']/div[2]/table//tr//td[contains(.,'Pickup')]")
	private WebElement pickupTimeText;
	
	@FindBy(xpath="//*[@id='tripNumberDetails']/div[2]/table//tr//td[contains(.,'Check-in')]")
	private WebElement checkInTimeText;

	String TranstoText= "//*[@id='tripNumberDetails']/div[2]/table//tr//td[contains(.,'Trans to')]";
	
	@FindBy(xpath="//*[@id='tripNumberDetails']/div[2]/table//tr//td[contains(.,'Trans to')]/a[2]")
	private WebElement ReassignLink;
	
	String tripTableRows= "//*[@id='tripNumberDetails']/div[2]/table//tr";
	String tripRowXpath1="[";
	String tripRowXpath2="]";
	
	String resultRowsXpath1="//*[@id = 'row']/tbody/tr[";
	String resultRowsXpath2="]/td/a";
	String layOvertext="//*[contains(text(),'LAX L/O')]";
	
	@FindBy(xpath="//div[contains(text(),'No Record(s) Found')]")
	private List<WebElement> noRecordsFoundMessage;
	
	@FindBy(xpath="//*[@id='row']//td[3]")
	private WebElement crewMembersDetails;

	String tripTableRowsAfterDeletePairing= "//*[@id='tripNumberDetails']/div[3]/table//tr";
	
	@FindBy(xpath="//*[@id='tripNumberDetails']/div[2]/table//tr//td[contains(.,'Trans to')]/a[1]")
	private WebElement showNotesLink;

	@FindBy(xpath="//*[@id='resvAction']//tr")
	private List<WebElement> reservationNotesRows;
	
	String reservationNotesXpath1="//*[@id='resvAction']//tr[";
	String reservationNotesXpath2="]/td[6]";
	
	String showNotesXpath1="//*[@id='tripNumberDetails']/div[2]/table//tr//td[contains(.,'";
	String showNotesXpath2="')]/a[1]";
	
	@FindBy(xpath="//table[@id='resvAction']//tr/td[6]/a")
	private List<WebElement> links_InNotes;

	@FindBy(xpath="//table[@id='resvAction']//tr/td[4]")
	private List<WebElement> supplierName;
	
	@FindBy (xpath ="//*[@id='tripNumberDetails']/div[3]/table//tr//td[2]")
	private List<WebElement> list_TripDetails;

	@FindBy(xpath ="//tr//td[2]//a[normalize-space()='Show Notes']")
	private List<WebElement> showNoteslinks;
	
	String tripDetailsTableXpath = "//body//div[@class='Aces_Table']//div//div//tr";
	
	@FindBy (xpath="//div[@class='Aces_Table']//tr/td[1]/a")
	private List<WebElement> resultsList;
	
	String showNotesXPathone = "//body//div[@class='Aces_Table']//div//div//tr//td[contains(.,'";
	String  selectTripXPath ="//div[@class='Aces_Table']//tr[";
	
	@FindBy(xpath = "//table[@class='TabBorder']//tr")
	private List<WebElement> list_ReviseTrip;
	
	@FindBy(xpath="//*[@id='tripNumberDetails']/div[2]/table//tr[2]/td[6]")
	private WebElement showTime;
	
	String DeletedTripTableRows= "//*[@id='tripNumberDetails']/div[3]/table//tr";

	public EventFiringWebDriver driver=null;

	public Page_FindTrip(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void findTrip(String tripIdValue,String timeFormatValue,
			String crewIdValue) throws Exception{
		typeInText(tripNumber, tripIdValue, "FindTripPage -> TripNumber");
		selectByVisibleText(timeFormat, timeFormatValue, "FindTripPage -> TimeFormat Dropdown");
		typeInText(this.crewId, crewIdValue, "FindTripPage -> CrewID");
	}
	
	public void findTrips(String tripIdValue, String operatingDateValue){
		typeInText(tripNumber, tripIdValue, "FindTripPage -> TripNumber");
		typeInText(operatingDate, operatingDateValue, "FindTripPage -> OperatingDate");
		clickOnRetriveBtn();
	}
	
	public void findTripUsingTripId(String tripIdValue){
		typeInText(tripNumber, tripIdValue, "FindTripPage -> TripNumber");
		clickOnRetriveBtn();
	}
	
	
	public void clickOnRetriveBtn(){
		clickOn(retrieveBtn, "FindTripPage -> Retrieve Button,");
		waitForElementVisibility(resultTable);
		System.out.println("No of result rows for given search criteria: "+resultRows.size());
	}
	
	public void verifyTripDetails(String tripIdValue){
		Assert.assertEquals(tripNumberIntable.getText(), tripIdValue);
		
	}
	
	public void clickOnTripIDInTheResultsTable() throws Exception{ 
		if(!tripNumberlink.isDisplayed())
			Assert.fail("Trips not displayed in PA");
		else
		clickOn(tripNumberlink, "FindTripPage -> TripNumber Link");
		Thread.sleep(2000);
	
	}
	
	public void clickOnRequiredTrip(String tripIdValue){
		int resultsRow= resultRows.size();
		for(int tripRows=1; tripRows<=resultsRow; tripRows++){
			WebElement tripNumber= findElementByXpath(resultRowsXpath1+tripRows+resultRowsXpath2);
			String tripValue=tripNumber.getText();
			if(tripValue.equalsIgnoreCase(tripIdValue)){
				clickOn(tripNumber, "FindTripPage -> TripNumber");
			break;
			}
		}
		
	}
	
	
	public void verifyPickupAndDropofftime(String tripIdValue) throws Exception{
		verifyTripDetails(tripIdValue);
		clickOnTripIDInTheResultsTable();
		
	}
	
	public String getPickUpTime(){
		String pickupTime = getTextOfElement(getArrivalTime);
		System.out.println("Pick up time :" + pickupTime);
		return pickupTime;
	}
	
	public String getDropOffTime(){
		String dropoffTime = getTextOfElement(getDepartureTime);
		System.out.println("Drop off time :" + dropoffTime);
		return dropoffTime;
	}
	
	public String getArrivalTime() throws Exception{
		String arrivalTimeValue = getTextOfElement(getArrivalTime);
		System.out.println("arrival Time :" + arrivalTimeValue);
		String arrivalTime= formatDate(arrivalTimeValue);
		return arrivalTime;
	}
	
	
	public String getShowTime() throws Exception
	{
		String showTimeValue= getTextOfElement(getShowTime);
		System.out.println("Show Time" + showTimeValue);
		String showTime=formatDate(showTimeValue);
		return showTime;

	}
	
	public String getpickUpTime() throws Exception
	{
		String pickUpText=getTextOfElement(pickupTimeText);
		String getPickUpTimeText[]=pickUpText.split("@");
		String getPickUp=getPickUpTimeText[1];
		String pickUpValue=getPickUp.substring(1, 5);
		String pickTime=formatDate(pickUpValue);
		System.out.println(pickTime);
		return pickTime;
		
	}
	
	public String getCheckInTime() throws Exception {
		String checkInText=getTextOfElement(checkInTimeText);
		String getcheckInText[]=checkInText.split("@");
		String getcheckIn=getcheckInText[1];
		String checkInValue=getcheckIn.substring(1, 5);
		String checkInTime=formatDate(checkInValue);
		System.out.println(checkInTime);
		return checkInTime;
	}
	
	public String getCheckOutTime() throws Exception {
		String checkOutText=getTextOfElement(checkInTimeText);
		String getcheckOutText[]=checkOutText.split("@");
		String getcheckOut=getcheckOutText[2];
		String checkOutValue=getcheckOut.substring(1, 5);
		String checkOutTime=formatDate(checkOutValue);
		System.out.println(checkOutTime);
		return checkOutTime;
	}
	
	public String getSupplierName(){
		String supplierText= getTextOfElement(pickupTimeText);
		String getSupplierText[]=supplierText.split(":");
		String supplier=getSupplierText[1];
		String supplierName = supplier.substring(supplier.indexOf(" ") + 1, supplier.indexOf(" ,"));
		System.out.println(supplierName);
		return supplierName;
	}
	
	public int noOfTrips(){
		List<WebElement> trips= findElementsByXpath(TranstoText);
		int countOfTrips=trips.size();
		return countOfTrips;
				
	}
	
	public void clickOnAssignOrReassignLink(){

		clickOn(ReassignLink, "FindTripPage -> Reassign Link");
		
	}
	
	private String formatDate(String reqValue) throws Exception
	{
		DateFormat format = new SimpleDateFormat("HHmm");
		Date timeStamp = format.parse(reqValue);
		System.out.println("********************"+timeStamp);
		String formatedTime=timeStamp.toString();
		String[] reqTime= formatedTime.split(" ");
		String timeRequired=reqTime[3];
		System.out.println(timeRequired);
		return timeRequired;
	}
	
	public void verifySupplierAndStatus(String hotelName, String status) throws Exception{
		boolean statusFlag= false;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRows);
				for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
				{
					String rowValue= findElementByXpath(tripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
					System.out.println(rowValue);
					if(rowValue.contains(hotelName.trim()) && rowValue.contains(status.trim()))
					{
						ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + hotelName+ " and " + status +" status is found " + rowValue);
						statusFlag= true;
						break;
					}
					
				}
				if(!statusFlag){
					throw new Exception ("Trip not found with required hotel " + hotelName +" and status " + status);
				}

	}
	
	public void verifyGTAndStatus(String gtName, String status) throws Exception{
		boolean statusFlag= false;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRows);
				for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
				{
					String rowValue= findElementByXpath(tripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
					System.out.println(rowValue);
					if(rowValue.contains(gtName.trim()) && rowValue.contains(status))
					{
						ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + gtName+ " and " + status +" status is found " + rowValue);
						System.out.println("Trip with" + gtName+ "with" + status +"status is found" + rowValue);
						statusFlag= true;
						break;
					}
					
				}
				if(!statusFlag){
					throw new Exception ("Trip not found with required gt" + gtName +"and status" + status);
				}

	}
	
	public void verifyGTAndStatus(String gtName) throws Exception{
		boolean statusFlag= false;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRows);
				for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
				{
					String rowValue= findElementByXpath(tripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
					System.out.println(rowValue);
					if(rowValue.contains(gtName.trim()))
					{
						System.out.println("Trip with" + gtName+ "status is found" + rowValue);
						statusFlag= true;
						break;
					}
					
				}
				if(!statusFlag){
					throw new Exception ("Trip not found with required hotel" + gtName );
				}
}
	
	
	public void verifyNoOfLOs(String layOverValue) throws Exception {
		boolean layoverCount = false;
		List<WebElement> layOverRows = findElementsByXpath(layOvertext);
		Integer layOversSize = layOverRows.size();
		Integer layover = Integer.parseInt(layOverValue);
		System.out.println(layover);
		if (layOversSize.equals(layover)){
			System.out.println("No. of layovers in Trip details " + layOversSize + " matched with required layoverValue " + layover);
			layoverCount = true;
		}
		if(layover==0){
			System.out.println("No need to verify Layovers count for this test case" );
			layoverCount = true;
		}
		if (!layoverCount) {
			throw new Exception("No. of layovers in Trip details" + layOversSize + "not matched with required layoverValue " + layover);
		}
		
	}
	
	/*
	 *   Get only crew Ids from the Crew Members text
	 *   Storing tripId in Key and crewId in values using Map
	 */
	public Map<String, List<String>> getCrewMembers(String tripId){
		List<String> crewIDs= new ArrayList<String>();
		Map<String, List<String>> crewMembers=new HashMap<String, List<String>>();
		for(String a: crewMembersDetails.getText().split("\\)")){
			crewIDs.add(a.substring(a.indexOf("(") + 1, a.length()));
		}
		crewMembers.put(tripId, crewIDs);
		
		return crewMembers;
	}
	
	public void verifySupplierAndStatusAfterDeletePairing(String hotelName, String status) throws Exception{
		boolean statusFlag= false;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRowsAfterDeletePairing);
				for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
				{
					String rowValue= findElementByXpath(tripTableRowsAfterDeletePairing+tripRowXpath1+tripRow+tripRowXpath2).getText();
					System.out.println(rowValue);
					if(rowValue.contains(hotelName.trim()) && rowValue.contains(status.trim()))
					{
						ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + hotelName+ " and " + status +" status is found " + rowValue);
						statusFlag= true;
						break;
					}
					
				}
				if(!statusFlag){
					throw new Exception ("Trip not found with required hotel " + hotelName +" and status " + status);
				}

	}
	
	public void clickOnShowNotesLink(){
		List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(parentHandle);
		clickOn(showNotesLink, "FindTripPage -> Show Notes Link");
		switchToNewWindow(parentHandle);
		waitForPageToLoad("5");
		
	}
	
	public void verifyFaxOrEmailVendorInReservationNotes(){
		String faxNote ="Faxed vendor, tracking number is";
		if(reservationNotesRows.size()>0){
		for(int rowCount=2; rowCount<reservationNotesRows.size(); rowCount++){
			String faxNoteInReservationNote= findElementByXpath(reservationNotesXpath1+rowCount+reservationNotesXpath2).getText();
			if(faxNote.contains(faxNoteInReservationNote))
				ExtentTestManager.getTest().log(LogStatus.INFO,"'Faxed vendor, tracking number is' found in Reservation Notes");
				break;
			}
		}
		else ExtentTestManager.getTest().log(LogStatus.FAIL,"'Faxed vendor, tracking number is' not found in Reservation Notes");
	}
	
	public void verifyNotesForTrips(String reqTexts){
		int textCount=0;
		List<String> reqText= Arrays.asList(reqTexts.split(","));
		if(reservationNotesRows.size()>0){
		for(int rowCount=2; rowCount<reservationNotesRows.size();rowCount++){
			if(textCount<reqText.size()){
			String textInReservationNotes= findElementByXpath(reservationNotesXpath1+rowCount+reservationNotesXpath2).getText();
			System.out.println(textInReservationNotes);
			if(textInReservationNotes.equalsIgnoreCase(reqText.get(textCount))){
				ExtentTestManager.getTest().log(LogStatus.INFO,"Required text: " +reqText.get(textCount) +" found in Reservation Notes");
			textCount++;
			}
		}
	} 
	}
		else
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Required text: " +reqText +" not found in Reservation Notes");
		}
	
	
	public void clickOnShowNotesOfReqSupplier(String supplierName, String status) throws Exception{
		boolean statusFlag=true;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRows);
		for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
		{
			String rowValue= findElementByXpath(tripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
			System.out.println(rowValue);
			if(rowValue.contains(supplierName.trim()) && rowValue.contains(status.trim()))
			{
				ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + supplierName+ " and " + status +" status is found " + rowValue);
				statusFlag= true;
				List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
				System.out.println(parentHandle);
				clickOn(findElementByXpath(showNotesXpath1+supplierName+showNotesXpath2), "FindTripPage -> Show Notes Link");
				switchToNewWindow(parentHandle);
				waitForPageToLoad("5");
				getDriver().manage().window().maximize();
				break;
			}
			
		}
		if(!statusFlag){
			throw new Exception ("Trip not found with required supplier " + supplierName +" and status " + status);
		}
	}
	
	public String clickOnPDFlinksAndDownload(String hotelName, String gtName, String requiredPDFLinks) throws InterruptedException{
		String renamedHotelFileName= "";
		String renamedGTFileName = "";
		int links_InNotesSize = links_InNotes.size();
		int supplierNameSize = supplierName.size();
		int totalLinks=Integer.parseInt(requiredPDFLinks);
		for(int i=links_InNotesSize-1; i>=0; i--){
			if(totalLinks>0){
			if(supplierName.get(supplierNameSize-1).getText().equalsIgnoreCase(gtName)){
				String faxNumberforGT = links_InNotes.get(i).getText();
				clickOn(links_InNotes.get(i));
				Thread.sleep(3000);
				switchToWindowIndex(2);
				getDriver().switchTo().frame(driver.findElement(By.tagName("iframe")));
				System.out.println("Switched To PDF Window");
				driver.findElement(By.id("open-button")).click();
				Thread.sleep(6000);
				renamedGTFileName = renameFileByApendingDateStamp(faxNumberforGT, ".pdf");
				switchToWindowIndex(1);
			}
			else if(supplierName.get(supplierNameSize-1).getText().equalsIgnoreCase(hotelName)){
				String faxNumberforHotel = links_InNotes.get(i).getText();
				clickOn(links_InNotes.get(i));
				Thread.sleep(3000);
				switchToWindowIndex(2);
				getDriver().switchTo().frame(driver.findElement(By.tagName("iframe")));
				System.out.println("Switched To PDF Window");
				driver.findElement(By.id("open-button")).click();
				Thread.sleep(6000);
				renamedHotelFileName = renameFileByApendingDateStamp(faxNumberforHotel, ".pdf");
				switchToWindowIndex(1);
			}
			supplierNameSize--;
			totalLinks--;
		}
		}
		return renamedHotelFileName+","+renamedGTFileName;
	}
	
	public void readPDF(String filename, String TextsToVerifyInPDF) throws Exception {
		List<String> TextsToVerifyInPDFList= Arrays.asList(TextsToVerifyInPDF.split(","));
		PDFTextStripper tStripper = new PDFTextStripper();
		tStripper.setStartPage(1);
		tStripper.setEndPage(3);
		PDDocument document = PDDocument.load(new File(System.getProperty("user.dir")+ File.separator +"download_files"+File.separator +filename));
		document.getClass();
		String content = null;
		if (!document.isEncrypted()) {
		String pdfFileInText = tStripper.getText(document);
		String[] lines = pdfFileInText.split("\\r\\n\\r\\n");
		for (String line : lines) {
		// System.out.println(line);
		content += line.trim();
		}
		for(int i=0; i<TextsToVerifyInPDFList.size(); i++){
		if(content.trim().contains(TextsToVerifyInPDFList.get(i))){
		System.out.println("PDF contains Required Text");
		ExtentTestManager.getTest().log(LogStatus.PASS,"Required text: " +TextsToVerifyInPDFList.get(i) +"  found in PDF");	
		}else
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Required text: " +TextsToVerifyInPDFList.get(i) +"  not found in PDF");
		}
		}
	}

	public void clickOnShowNotesforPCC() {
		System.out.println(list_TripDetails.size());
		for (int i = 2; i < list_TripDetails.size(); i++) {
			if(list_TripDetails.get(i).getText().contains("Pending Cancellation Confirmation"))
				driver.findElement(By.xpath("//*[@id='tripNumberDetails']/div[3]/table//tr["+i+"]//td[2]/a")).click();
				break;
		}	
	
	}
	
	public void verifyViewNotes(String[] getttt)
			throws Exception {
		String winHandleBefore = getDriver().getWindowHandle();
		showNoteslinks.get(0).click();
		getDriver().manage().window().maximize();
		takeScreenshots();
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
//		String[] getttt = getFlightNumbersFromXML(scenarioFolderName);
//		System.out.println(getttt.length);
		for (String string : getttt) {
			if (Arrays.asList(getDriver().getPageSource()).contains(string)) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Flight numbers got updated as per the add_pairing file");
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Flight numbers not matching with the add_pairing file");

			}
		}
		getDriver().close();
		getDriver().switchTo().window(winHandleBefore);
	}
	
	public String[] verifNotes() throws IOException{
		String[] tdValues = null;
		String celtext = null;
		String winHandleBefore = getDriver().getWindowHandle();
		showNoteslinks.get(0).click();
		takeScreenshots();
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
		getDriver().manage().window().maximize();
		takeScreenshots();
    	List < WebElement > rows_table = driver.findElements(By.xpath("//table[@class='TabBorder displayTable']//tr"));
    	int rows_count = rows_table.size();
    	tdValues = new String[2];
    	for (int row = 1; row < rows_count; row++) {
    	    List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
    	    int columns_count = Columns_row.size();
//    	    System.out.println("Number of cells In Row " + row + " are " + columns_count);
    	    for (int column = 0; column < columns_count; column++) {
    	         celtext = Columns_row.get(column).getText();
    	        if(celtext.contains("Arrival flight changed") ) {
    	        	tdValues[0] = celtext;
    	        }
    	        if(celtext.contains("Departure flight changed") ) {
    	        	tdValues[1] = celtext;
    	        }
    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
    	        }
    	    
    	    System.out.println("-------------------------------------------------- ");
    	}
		getDriver().close();
		getDriver().switchTo().window(winHandleBefore);
		return tdValues;
   	}
	
	public void clickOnGivenTrip(String tripIdValue){
		int resultsRow= resultsList.size();
		for(int tripRows=1; tripRows<=resultsRow; tripRows++){
			WebElement tripNumber= findElementByXpath(selectTripXPath+tripRows+resultRowsXpath2);
			String tripValue=tripNumber.getText();
			if(tripValue.equals(tripIdValue)) {
				clickOn(tripNumber, "FindTripPage -> TripNumber");
			break;
		}
		
	}
	}
	
	
	public void verifyGTAndStatus2(String gtName) throws Exception{
		boolean statusFlag= false;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripDetailsTableXpath);
				for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
				{
					String rowValue= findElementByXpath(tripDetailsTableXpath+tripRowXpath1+tripRow+tripRowXpath2).getText();
					System.out.println(rowValue);
					if(rowValue.contains(gtName.trim()))
					{
						System.out.println("Trip with" + gtName+ "status is found" + rowValue);
						statusFlag= true;
						takeScreenshots();
						break;
					}
					
				}
				if(!statusFlag){
					throw new Exception ("Trip not found with required hotel" + gtName );
				}
}
	
	public String clickOnShowNotesOfReqHotelOrGT2(String status) throws Exception{
		boolean statusFlag=true;
		String gtName =null;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripDetailsTableXpath);
		for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
		{
			String rowValue= findElementByXpath(tripDetailsTableXpath+tripRowXpath1+tripRow+tripRowXpath2).getText();
			System.out.println(rowValue);
			if(rowValue.contains(status.trim()))
			{
			 gtName = getGTNameOnFindTrip(rowValue);
				ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + status +" status is found " + rowValue);
				statusFlag= true;
				List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
				System.out.println(parentHandle);
				clickOn(findElementByXpath(showNotesXPathone+status+showNotesXpath2), "FindTripPage -> Show Notes Link");
				switchToNewWindow(parentHandle);
				waitForPageToLoad("5");
				getDriver().manage().window().maximize();
				break;
			}
			
		}
		if(!statusFlag){
			throw new Exception ("Trip not found with required status " + status);
		}
		return gtName;
	}
	
	public void verifyNotesForRequiredText(String reqTexts){////table[@class='TabBorder']//tr/td[4]
		int textCount=0;
		List<String> reqText= Arrays.asList(reqTexts.split(","));
		if(reservationNotesRows.size()>0){
		for(int rowCount=2; rowCount<=reservationNotesRows.size();rowCount++){
			if(textCount<reqText.size()){
			String textInReservationNotes= findElementByXpath(reservationNotesXpath1+rowCount+reservationNotesXpath2).getText();
			System.out.println(textInReservationNotes);
			//for(int textCount=0; textCount<reqText.size(); textCount++){
			if(textInReservationNotes.contains(reqText.get(textCount))){
				ExtentTestManager.getTest().log(LogStatus.INFO,"Required text: " +reqText.get(textCount) +" found in Reservation Notes");
			textCount++;//}
			}
		}
	}
	}
		else
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Required text: " +reqText +" not found in Reservation Notes");
		}
	

	public String  getGTNameOnFindTrip (String rowText)
	{
		String youString = null;
		if(rowText.contains("Trans to:")) {
			List<String> supplierTypeList= Arrays.asList(rowText.split(","));
			String str = supplierTypeList.get(0);
			youString = str.substring(str.indexOf(":")+1);
			   System.out.println(youString.trim());
			
			}else
			{ExtentTestManager.getTest().log(LogStatus.INFO,"GT is not showing up as PCC");
			}
		return youString.trim();
	}
	
	public void clickOnShowNotesOfTrip(String status) throws Exception{
		boolean statusFlag=true;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRows);
		for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
		{
			String rowValue= findElementByXpath(tripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
			System.out.println(rowValue);
			if(rowValue.contains(status.trim()))
			{
				ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + status +" status is found " + rowValue);
				statusFlag= true;
				List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
				System.out.println(parentHandle);
				break;
			}
			
		}
		if(!statusFlag){
			throw new Exception ("Trip not found with required status " + status);
		}
	}
	
	public String clickOnShowNotesOfReqHotelOrGT(String status) throws Exception{
		boolean statusFlag=true;
		String gtName =null;
		List<WebElement> tripTableRowscount= findElementsByXpath(tripTableRows);
		for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
		{
			String rowValue= findElementByXpath(tripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
			System.out.println(rowValue);
			if(rowValue.contains(status.trim()))
			{
			 gtName =	getGTNameOnFindTrip(rowValue);
				ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + status +" status is found " + rowValue);
				statusFlag= true;
				List<String> parentHandle = new ArrayList<String>(driver.getWindowHandles());
				System.out.println(parentHandle);
				clickOn(findElementByXpath(showNotesXpath1+status+showNotesXpath2), "FindTripPage -> Show Notes Link");
				switchToNewWindow(parentHandle);
				waitForPageToLoad("5");
				getDriver().manage().window().maximize();
				break;
			}
			
		}
		if(!statusFlag){
			throw new Exception ("Trip not found with required status " + status);
		}
		return gtName;
	}
	
	public void verifyTripsNotes(String reqTexts){
		String winHandleBefore = getDriver().getWindowHandle();
		showNoteslinks.get(0).click();
		
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
		int textCount=0;
		List<String> reqText= Arrays.asList(reqTexts.split(","));
		if(reservationNotesRows.size()>0){
		for(int rowCount=2; rowCount<reservationNotesRows.size();rowCount++){
			if(textCount<reqText.size()){
			String textInReservationNotes= findElementByXpath(reservationNotesXpath1+rowCount+reservationNotesXpath2).getText();
			System.out.println(textInReservationNotes);
			if(textInReservationNotes.equalsIgnoreCase(reqText.get(textCount))){
				ExtentTestManager.getTest().log(LogStatus.INFO,"Required text: " +reqText.get(textCount) +" found in Reservation Notes");
			textCount++;
			}
		}
	} 
	}
		else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Required text: " +reqText +" not found in Reservation Notes");
		}
		getDriver().close();
		getDriver().switchTo().window(winHandleBefore);
		}
	
	public void verifyNotesForTripTransaction(String reqTexts){
		int textCount=0;
		List<String> reqText= Arrays.asList(reqTexts.split(","));
		if(list_ReviseTrip.size()>0){
		for(int rowCount=2; rowCount<=list_ReviseTrip.size();rowCount++){
			if(textCount<reqText.size()){
			String textInReservationNotes= findElementByXpath("//table[@class='TabBorder']//tr["+rowCount+"]/td[4]").getText();
			System.out.println(textInReservationNotes);
			//for(int textCount=0; textCount<reqText.size(); textCount++){
			if(textInReservationNotes.contains(reqText.get(textCount))){
				ExtentTestManager.getTest().log(LogStatus.INFO,"Required text: " +reqText.get(textCount) +" found in Reservation Notes");
			textCount++;//}
			}
		}
	}
	}
		else
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Required text: " +reqText +" not found in Reservation Notes");
		}
	
	public String showTime() throws Exception{
		String showTimeValue= getTextOfElement(showTime);
		System.out.println("Show Time" + showTimeValue);
		String showTime=formatDate(showTimeValue);
		return showTime;

	}
	public void verifyDeletedTripStatus(String supplierName, String status) throws Exception{
		boolean statusFlag= false;
		List<WebElement> tripTableRowscount= findElementsByXpath(DeletedTripTableRows);
				for(int tripRow=2; tripRow<tripTableRowscount.size();tripRow++)
				{
					String rowValue= findElementByXpath(DeletedTripTableRows+tripRowXpath1+tripRow+tripRowXpath2).getText();
					System.out.println(rowValue);
					if(rowValue.contains(supplierName.trim()) && rowValue.contains(status.trim()))
					{
						ExtentTestManager.getTest().log(LogStatus.INFO,"Trip with " + supplierName+ " and " + status +" status is found " + rowValue);
						statusFlag= true;
						break;
					}
					
				}
				if(!statusFlag){
					throw new Exception ("Trip not found with required hotel " + supplierName +" and status " + status);
				}

	}
}

	