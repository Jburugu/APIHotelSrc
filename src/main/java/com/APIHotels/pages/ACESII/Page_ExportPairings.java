package com.APIHotels.pages.ACESII;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ExportPairings extends BasePage{

	@FindBy(id = "bidPeriodId")
	private WebElement bidPeriod;
	
	@FindBy(id = "lockScheduleId")
	private WebElement lockBidPeriodIndicator;
	
	@FindBy(id = "existingPrgFileId")
	private WebElement chooseFileBtn;
	
	@FindBy(xpath = "//*[@value = 'Export Pairings']")
	private WebElement exportPairingsBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_ExportPairings(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setDetailsForExportPairings(String fileName) throws Exception{
		//logger.info("*** ExportPairingsPage -> setDetailsForExportPairings method started ***");
		waitForElementVisibility(bidPeriod);
		Actions builder = new Actions(driver);
		//takeScreenshots("ExportPairingsScreen before upload");
		builder.doubleClick(chooseFileBtn).perform();
		
		/*control switches to AutoIt after chooseFileBtn is clicked
		 * fileName is passed as command line input to autoit exe file
		 * path to autoIT exe file along with fileName commandline input are passed to exec method of getRuntime
		 * */
		
		try{
			Thread.sleep(3000);
			Runtime.getRuntime().exec(".\\lib\\FileUploader.exe "+fileName);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		
		waitForElementVisibility(lockBidPeriodIndicator);
		waitForElementVisibility(exportPairingsBtn);
		//takeScreenshots("ExportPairingsScreen after upload");
		}catch(Exception e){
			Runtime.getRuntime().exec(".\\lib\\FileUploader.exe "+fileName);
			//takeScreenshots("ExportPairingsScreen after upload");
		}
		//logger.info("*** setDetailsForExportPairings method completed ***");
	}

}
