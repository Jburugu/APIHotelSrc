package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Page_Configuration extends BasePage{

	@FindBy(id="autoProcessEnabled")
	private WebElement hotelActivateAutoProcessing;
	
	@FindBy(xpath="//*[contains(text(),'Auto Processing')]/../table/tbody/tr[2]//input")
	private WebElement autoProcessSaveBtn;
	
	@FindBy(id="arrFlChngFaxEmailEnabled")
	private WebElement arrivalFlightChangeFaxEmail;
	
	@FindBy(id="arrFlChngPrintEnabled")
	private WebElement arrivalFlightChangePrint;
	
	@FindBy(xpath="//*[contains(@id,'FaxEmailEnabled')]")
	private List<WebElement> faxOrEmailCheckboxes;
	
	@FindBy(xpath="//*[contains(@id,'PrintEnabled')]")
	private List<WebElement> printCheckboxes;
	
	@FindBy(id="AlertBox")
	private WebElement alertBox;

	@FindBy(id="alertOK")
	private WebElement alertOKBtn;

	@FindBy(id="arrEarlyPrintToTime")
	private WebElement arrEarlyPrintToTime;
	
	@FindBy(id="arrLatePrintToTime")
	private WebElement arrLatePrintToTime;
	
	@FindBy(id="deptEarlyPrintToTime")
	private WebElement deptEarlyPrintToTime;
	
	@FindBy(id="deptLatePrintToTime")
	private WebElement deptLatePrintToTime;
	
	@FindBy(xpath="//input[@name='roomBlocksCheckinEarlyTolerance']")
	private WebElement textbox_roomBlockCheckin;
	
	@FindBy (xpath ="//input[@name='roomBlocksCheckoutLateTolerance']")
	private WebElement textbox_roomBlockCheckout;
	
	@FindBy (xpath="//input[@name='roomBlocksFaxEmailFromTime']")
	private WebElement textbox_roomBlockFaxEmailFromTime;
	
	@FindBy (xpath="//input[@name='roomBlocksFaxEmailToTime']")
	private WebElement textbox_roomBlocksFaxEmailToTime;
	
	@FindBy (xpath="//input[@name='roomBlocksPrintFromTime']")
	private WebElement textbox_roomBlockPrintFromTime;
	
	@FindBy (xpath="//input[@name='roomBlocksPrintToTime']")
	private WebElement textbox_roomBlocksPrintToTime;
	
	@FindBy (xpath ="//table[@class='gtAutoGtTD']/tbody/tr/td/div[@class='Title']/input[@value='true']")
	private WebElement radio_enableAutoProcessing3rdPartyGTYes;
	
	@FindBy (xpath ="//table[@class='gtAutoGtTD']/tbody/tr/td/div[@class='Title']/input[@value='false']")
	private WebElement radio_enableAutoProcessing3rdPartyGTNo;
	
	@FindBy (xpath="//input[@name='gtTimeFrame']")
	private WebElement textbox_gtTimeFrame3rdPartyGT;
	
	@FindBy(xpath ="//td[@class='Aces_Bold_Text']//div[@class='Title']//input[@value='true']")
	private WebElement radio_reuseRoomBlocksYes;

	@FindBy(xpath="//legend[text()='Auto Processing']/parent::fieldset//td//span[text()='Early Check-in Tolerance:']")
	private WebElement lable_EarlyCheckInTolerance;
	
	@FindBy (xpath ="//table[@class='gtAutoGtTD']//input[@value='true']")
	private List<WebElement> radio_setYesToAll;

	@FindBy (xpath ="//table[@class='gtAutoGtTD']//input[contains(@id,'FaxEmailEnabled')]")
	private List<WebElement> GTfaxOrEmailCheckboxes;
	
	@FindBy (xpath ="//table[@class='gtAutoGtTD']//input[contains(@id,'PrintEnabled')]")
	private List<WebElement> radioGTPrintEnableCheckboxes;
	

	
	public EventFiringWebDriver driver=null;

	public Page_Configuration(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void activateHotelAutoprocessing(){
		if(!hotelActivateAutoProcessing.getAttribute("value").equals(true)){
			hotelActivateAutoProcessing.click();
		}
	}
	
	public void clickOnSaveAutoprocessing(){
		clickOn(autoProcessSaveBtn);
		waitForElementVisibility(alertBox);
		clickOn(alertOKBtn);
	}
	
	public void selectNotificationTypeOfArrivalFlightChange(String notificationType){
		if(notificationType.equalsIgnoreCase("FaxEmail")){
			if(!arrivalFlightChangeFaxEmail.isSelected())
				clickOn(arrivalFlightChangeFaxEmail);
				clickOnSaveAutoprocessing();
		}
		else if(notificationType.equalsIgnoreCase("Print")){	
			if(!arrivalFlightChangePrint.isSelected())
				clickOn(arrivalFlightChangePrint);
		}
		
	}
	
	public void allConfigSetToFaxOrEmail(){
		if(faxOrEmailCheckboxes.size()>0){
			for(int i=0; i<faxOrEmailCheckboxes.size();i++)	{
				if(faxOrEmailCheckboxes.get(i).isDisplayed()&&!faxOrEmailCheckboxes.get(i).isSelected()){
					clickOn(faxOrEmailCheckboxes.get(i));
				}
				if(printCheckboxes.get(i).isDisplayed()&&printCheckboxes.get(i).isSelected()){
					clickOn(printCheckboxes.get(i));
				}
			}
			setPrintMinsTo60();
			clickOnSaveAutoprocessing();
			}
		else Assert.fail("No fax/email and Print checkboxes are displayed in Autoprocessing");
	}
	
	public void allConfigSetToPrint(){
		if(printCheckboxes.size()>0){
			for(int i=0; i<printCheckboxes.size();i++)	{
				if(printCheckboxes.get(i).isDisplayed()&&!printCheckboxes.get(i).isSelected()){
					clickOn(printCheckboxes.get(i));
				}
				if(faxOrEmailCheckboxes.get(i).isDisplayed()&&faxOrEmailCheckboxes.get(i).isSelected()){
					clickOn(faxOrEmailCheckboxes.get(i));
				}
			}
			setPrintMinsTo420();
			clickOnSaveAutoprocessing();
			}
		else Assert.fail("No fax/email and Print checkboxes are displayed in Autoprocessing");
	}
	
	private void setPrintMinsTo420(){
		if(!arrEarlyPrintToTime.getAttribute("value").equals("420")){
			typeInText(arrEarlyPrintToTime, "420");
		}
		if(!arrLatePrintToTime.getAttribute("value").equals("420")){
			typeInText(arrLatePrintToTime, "420");
		}
		if(!deptEarlyPrintToTime.getAttribute("value").equals("420")){
			typeInText(deptEarlyPrintToTime, "420");
		}
		if(!deptLatePrintToTime.getAttribute("value").equals("420")){
			typeInText(deptLatePrintToTime, "420");
		}
	}
	
	private void setPrintMinsTo60(){
		if(!arrEarlyPrintToTime.getAttribute("value").equals("60")){
			typeInText(arrEarlyPrintToTime, "60");
		}
		if(!arrLatePrintToTime.getAttribute("value").equals("60")){
			typeInText(arrLatePrintToTime, "60");
		}
		if(!deptEarlyPrintToTime.getAttribute("value").equals("60")){
			typeInText(deptEarlyPrintToTime, "60");
		}
		if(!deptLatePrintToTime.getAttribute("value").equals("60")){
			typeInText(deptLatePrintToTime, "60");
		}
	}
	
	
	public void allConfigSetToDefault(){
		
		
	}
	

	public void configResueRoomBlock3rdPartyGT(String checkInEarlyTolerance, String checkoutLateTolerance, String faxEmailFromTime,
			String faxEmailToTime, String printFromTime, String printToTime) {
			if(lable_EarlyCheckInTolerance.isDisplayed()) {
				System.out.println("Reuse Room Block is already set to Yes");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Reuse Room Block is already set to Yes");
				enterValuesForReuseRoomBlock(checkInEarlyTolerance, checkoutLateTolerance, faxEmailFromTime, faxEmailToTime, printFromTime, printToTime);
				clickOnSaveAutoprocessing();	
				} else
		{
			clickOn(radio_reuseRoomBlocksYes, "Configurations Page -> Reuse Room Block Yes Radiobutton");
			System.out.println("Reuse Room Block is now set to Yes");
			clickOn(autoProcessSaveBtn, "Auto Processing Configuration Page -> Save button");
			clickOn(alertOKBtn, "Auto Processing Configuration Page -> OK button");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Reuse Room Block is now set to Yes");
			enterValuesForReuseRoomBlock(checkInEarlyTolerance, checkoutLateTolerance, faxEmailFromTime, faxEmailToTime, printFromTime, printToTime);
			clickOnSaveAutoprocessing();
			}
		
		}
	
	private void enterValuesForReuseRoomBlock(String checkInEarlyTolerance, String checkoutLateTolerance, String faxEmailFromTime, String faxEmailToTime, String printFromTime, String printToTime) {
		typeInText(textbox_roomBlockCheckin, checkInEarlyTolerance);
		typeInText(textbox_roomBlockCheckout, checkoutLateTolerance);
		typeInText(textbox_roomBlockFaxEmailFromTime, faxEmailFromTime);
		typeInText(textbox_roomBlocksFaxEmailToTime, faxEmailToTime);
		typeInText(textbox_roomBlockPrintFromTime, printFromTime);
		typeInText(textbox_roomBlocksPrintToTime, printToTime);
	}
	
	public void configThirdPartyGT() throws Exception {
		if(!textbox_gtTimeFrame3rdPartyGT.isDisplayed())
			clickOn(radio_enableAutoProcessing3rdPartyGTYes);
		Thread.sleep(5000);
	}
	
	public void allConfigSetToYesFor3rdPartyGT() throws Exception{
		if(radio_setYesToAll.size()>0){
			for(int i=0; i<radio_setYesToAll.size();i++)	{
				if(radio_setYesToAll.get(i).isDisplayed()&&!radio_setYesToAll.get(i).isSelected()){
					clickOn(radio_setYesToAll.get(i));
				}
				
							}
			Thread.sleep(3000);
//			clickOnSaveAutoprocessing();
			}
		else Assert.fail("No fax/email and Print checkboxes are displayed in Autoprocessing");
	}
		
	
	public void allConfigSetToFaxOrEmailFor3rdPartyGT() throws Exception{
		if(GTfaxOrEmailCheckboxes.size()>0){
			for(int i=0; i<GTfaxOrEmailCheckboxes.size();i++)	{
				if(GTfaxOrEmailCheckboxes.get(i).isDisplayed()&&!GTfaxOrEmailCheckboxes.get(i).isSelected()){
					clickOn(GTfaxOrEmailCheckboxes.get(i));
				}
				if(radioGTPrintEnableCheckboxes.get(i).isDisplayed()&&radioGTPrintEnableCheckboxes.get(i).isSelected()){
					clickOn(radioGTPrintEnableCheckboxes.get(i));
				}
							}
			Thread.sleep(3000);
			clickOnSaveAutoprocessing();
			}
		else Assert.fail("No fax/email and Print checkboxes are displayed in Autoprocessing");
	}
	
	public void reuseRoomBlock() throws Exception {
		if(lable_EarlyCheckInTolerance.isDisplayed()) {
			System.out.println("Reuse Room Block is already set to Yes");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Reuse Room Block is already set to Yes");
		} else
		{
			clickOn(radio_reuseRoomBlocksYes, "Configurations Page -> Reuse Room Block Yes Radiobutton");
			System.out.println("Reuse Room Block is now set to Yes");
			clickOn(autoProcessSaveBtn, "Auto Processing Configuration Page -> Save button");
			clickOn(alertOKBtn, "Auto Processing Configuration Page -> OK button");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Reuse Room Block is now set to Yes");
		}
	}
	
	
}


