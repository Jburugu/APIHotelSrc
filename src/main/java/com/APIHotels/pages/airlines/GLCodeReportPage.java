package com.APIHotels.pages.airlines;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class GLCodeReportPage extends BasePage {

	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- GL CODE REPORT

	@FindBy(xpath= "//input[@value='Hotel']")
	public WebElement hotelServicesRadioButton;

	@FindBy(xpath= "//input[@value='Transportation']")
	public WebElement transportationServicesRadioButton;

	@FindBy(xpath= ".//*[@value='Retrieve']")
	public WebElement retrieveButton;

	@FindBy(linkText= "CSV")
	public WebElement exportOptionCSV;

	@FindBy(linkText= "Excel")
	public WebElement exportOptionExcel;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceTypes; //HOTEL, GT, BOTH
	
	@FindBy(id="glCodeReportForm:closedDate:tb")
	private WebElement createdDateTable;
	
	String dateLinkXpath1="//td[contains(@id,'glCodeReportForm:closedDate')]//a[contains(text(),'";
	String dateLinkXpath2="')]";
	
	@FindBy(xpath="//*[@id='glCodeReportForm:glCodeTable:tb']//tr")
	private List<WebElement> tablerowXpath;
	
	@FindBy(xpath = "//*[@id='ProgressBox']")
	public WebElement inProgressBox;
	
	public GLCodeReportPage(EventFiringWebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	public void glCode() {
		clickOn(hotelServicesRadioButton);
		Assert.assertTrue(hotelServicesRadioButton.isSelected(), "Hotel service radio button is not selected");

		clickOn(transportationServicesRadioButton);
		Assert.assertTrue(transportationServicesRadioButton.isSelected(),
				"Transportation service radio button is not selected");

		waitForElementVisibility(retrieveButton);
		waitForElementVisibility(exportOptionCSV);
		waitForElementVisibility(exportOptionExcel);
	}
	
	
	public void services(String serviceType){
		for(WebElement thisServiceType : serviceTypes)
			if(thisServiceType.getAttribute("value").equalsIgnoreCase(serviceType)){
				clickOn(thisServiceType);
				break;
			}
	}
	
	public void verifyGeneratingGLCodeReport(String serviceType) throws IOException{
	if(serviceType.equalsIgnoreCase("Hotel"))
		clickOn(hotelServicesRadioButton);
	else clickOn(transportationServicesRadioButton);
	clickOn(retrieveButton);
	waitForElementVisibility(createdDateTable);	
	clickOn(findElementByXpath(dateLinkXpath1+readPropValue("ProformaCreatedDate")+dateLinkXpath2));
	if(tablerowXpath.size()>0){
		ExtentTestManager.getTest().log(LogStatus.PASS,	"GL Code report generated for ->  "+serviceType);
	}
	else ExtentTestManager.getTest().log(LogStatus.FAIL, "GL Code report not generated for ->  "+serviceType);
	}
	
	public void waitForInProgressToComplete(){
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.withTimeout(Duration.ofMinutes(60));
        wait.pollingEvery(Duration.ofSeconds((15)));
        wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.invisibilityOf(inProgressBox));
	}
	
	
	

}
