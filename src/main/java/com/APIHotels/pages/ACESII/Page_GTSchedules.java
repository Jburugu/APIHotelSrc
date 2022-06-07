package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_GTSchedules extends BasePage{

	@FindBy(css = "#opsGTSchedules > a")
	private WebElement gtSchedulesLink;
	
	@FindBy(id = "cityCode")
	private WebElement city;
	
	@FindBy(id = "supplierId")
	private WebElement gt;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Find Supplier']")
	private WebElement findSupplier;
	
	@FindBy(id = "startDate")
	private WebElement startDate;
	
	@FindBy(id = "endDate")
	private WebElement endDate;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath ="//form[@name='opsGTSchedulesForm']//div[5]//input[@class = 'Aces_Btn' and @value = 'Fax  *']")
//"//*[@class = 'Aces_Btn' and @value = 'Fax  *']")
	private WebElement faxBtn;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Email']")
	private WebElement emailBtn;
	
	@FindBy(id = "row1")
	private WebElement scheduledSectionHeader;
	
	@FindBy(id = "row2")
	private WebElement cancelledSectionHeader;
	
	@FindBy(xpath = "//*[@id='inc1']/div/div")
	private WebElement scheduledReservationMsg;
	
	@FindBy(xpath = "//*[@id='inc2']/div/div")
	private WebElement cancelledReservationMsg;
	
	@FindBy(xpath = "//*[@id = 'cancelledTable']/tbody/tr")
	private List<WebElement> cancelledTableRow;
	
	@FindBy(xpath = "//*[@id = 'scheduledTable']/tbody/tr")
	private List<WebElement> scheduledTableRow;
	
	@FindBy (xpath = "//table[@id='scheduledTable']//tbody/tr/td[3]")
	private WebElement getPickupTime;

	@FindBy (xpath = "//table[@id='scheduledTable']//tbody/tr/td[5]")
	private WebElement getDropoffTime;
	
	String tripIdXpath1="//*[@id = 'scheduledTable']/tbody/tr[";
	String tripIdXpath2="]/td[1]";
	
	public EventFiringWebDriver driver=null;

	public Page_GTSchedules(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickGTSchedulesLink()throws Exception{
		clickOn(gtSchedulesLink);
	}
	
	public void verifyGTSchedules(String city, String gtSupplier, String startDate, String endDate) throws Exception{
		typeInText(this.city, city);
		clickOn(findSupplier);
		selectByVisibleText(gt, gtSupplier);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		clickOn(searchBtn);
		int results = Integer.parseInt(scheduledReservationMsg.getText().trim().split(" ")[0]);
		System.out.println("No of Scheduled Reservations found: "+results);
		clickOn(cancelledSectionHeader);
		try{Thread.sleep(3000);}catch(Exception e){}
		results = Integer.parseInt(cancelledReservationMsg.getText().trim().split(" ")[0]);
		System.out.println("No of Cancelled Reservations found: "+results);
	}
	
	
	public void verifyGTSchedules(String city, String gtSupplier) throws Exception{
		typeInText(this.city, city);
		clickOn(findSupplier);
		selectByVisibleText(gt, gtSupplier);
		typeInText(this.startDate, currentMonthStartDate());
		typeInText(this.endDate, currentMonthEndDate());
		clickOn(searchBtn);
		int results = Integer.parseInt(scheduledReservationMsg.getText().trim().split(" ")[0]);
		System.out.println("No of Scheduled Reservations found: "+results);
		clickOn(cancelledSectionHeader);
		try{Thread.sleep(3000);}catch(Exception e){}
		results = Integer.parseInt(cancelledReservationMsg.getText().trim().split(" ")[0]);
		System.out.println("No of Cancelled Reservations found: "+results);
	}
	
	public void clickOnScheuldesSection(){
		clickOn(scheduledSectionHeader);
	}
	
	public String getPickupTime(){
		String pickupTime = getTextOfElement(getPickupTime);
		System.out.println("Pick up Time on GT Schedules is "+ pickupTime);
		return pickupTime;
	}
	
	public String getDropoffTime(){
		String dropoffTime = getTextOfElement(getDropoffTime);
		System.out.println("Pick up Time on GT Schedules is "+ dropoffTime);
		return dropoffTime;
	}
	
	public static String removeCharAt(String time, int pos) {
		String s = time.substring(time.length() - 5);
		   StringBuffer buf = new StringBuffer( s.length() - 1 );
		   buf.append( s.substring(0,pos) ).append( s.substring(pos+1) );
		   return buf.toString();
		}
	
	@FindBy (xpath = "//table[@id='scheduledTable']//tbody/tr/td/a[1]")
	private WebElement tripnumbersInNotes;
	
	public void verifyOpsGTSchedulesAndVerifyPDF(String city, String gtSupplier, String startDate, String endDate,
			String verifyTrips, String textToVerifyInGTPDF)throws Exception{
		typeInText(this.city, city);
		clickOn(findSupplier);
		selectByVisibleText(gt, gtSupplier);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		clickOn(searchBtn);
		int scheduledReservationCount = Integer.parseInt(scheduledReservationMsg.getText().trim().split(" ")[0]);
		System.out.println("No of Scheduled Reservations found: "+scheduledReservationCount);
		if(scheduledReservationCount>0){
			String trips= tripnumbersInNotes.getText();
			System.out.println(trips);
			String str = trips.split("[\\(\\)]")[1];
			System.out.println(str);
			if(verifyTrips.equals(str.replace(", ",",")))
				ExtentTestManager.getTest().log(LogStatus.INFO, "Trip found in OpsDashboard");
		}
		verifyPDF(textToVerifyInGTPDF);
	}
	
	@FindBy(name="close")
	private WebElement closeBtn;
	
	@FindBy(xpath="//button[@id='open-button']")
	private WebElement openBtn;

	
	public void verifyPDF(String text) throws Exception{		
		String currentWindowHandle = getDriver().getWindowHandle();
		int countBeforeDownload = queryDirectorySize(downloadFilepath);
		clickOn(faxBtn);
		Thread.sleep(3000);
		switchToNewWindow(currentWindowHandle);
		switchToWindowIndex(1);
		getDriver().switchTo().frame(driver.findElement(By.tagName("iframe")));
		System.out.println("Switched To PDF Window");
//		closeBtn.sendKeys(Keys.TAB);		
		Thread.sleep(6000);
		JavascriptExecutor js = (JavascriptExecutor) getDriver(); 
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("open-button")));
		Thread.sleep(3000);
		driver.findElement(By.id("open-button")).click();
		Thread.sleep(3000);
//		clickOn(openBtn);
//		Thread.sleep(6000);
		int countAfterDownload = queryDirectorySize(downloadFilepath);
		if (countAfterDownload > countBeforeDownload) {
			System.out.println("File Downloaded Successfully");
			String pdfContent = fetchPDFContent(getTheLatestFile(downloadFilepath, "pdf"));
			assertTrue(pdfContent.contains(text), "Given Text does not exist!");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Supplier PDF download failed! Could not verify ScheduleNotes");
		}
		switchToWindowIndex(0);
		}

	public void verifyOpsGTSchedules(String city, String gtSupplier, String startDate, String endDate)throws Exception{
		typeInText(this.city, city, "OpsGTSchudule Page -> Entered City");
		clickOn(findSupplier,"OpsGTSchudule Page -> Clicked Find Supplier Button");
		selectByVisibleText(gt, gtSupplier,"OpsGTSchudule Page -> Selected GT Supplier");
		typeInText(this.startDate, startDate, "OpsGTSchudule Page -> Entered Start Date");
		typeInText(this.endDate, endDate, "OpsGTSchudule Page -> Entered End Date");
		clickOn(searchBtn, "OpsGTSchudule Page -> Clicked Search Button");
		int scheduledReservationCount = Integer.parseInt(scheduledReservationMsg.getText().trim().split(" ")[0]);
		System.out.println("No of Scheduled Reservations found: "+scheduledReservationCount);
		if(scheduledReservationCount==0)
			Assert.fail("No of Scheduled Reservations found");
		else
			for(int rowCount=1; rowCount<=scheduledTableRow.size() ; rowCount++){
				String tripIds=findElementByXpath(tripIdXpath1+rowCount+tripIdXpath2).getText();
				if(tripIds.contains(","))
					ExtentTestManager.getTest().log(LogStatus.PASS, "Trips in Ops GT Schedule got Mergerd: "+tripIds);
				else ExtentTestManager.getTest().log(LogStatus.PASS, "Trips in Ops GT Schedule remain Unmergerd: "+tripIds);
			}
			
			takeScreenshot();
		}
		

}
