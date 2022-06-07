package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_FaxStatus extends BasePage{
	
	@FindBy(how = How.XPATH, using = "//*[@id = 'tenantId']")
	private List<WebElement> tenantName;
	
	@FindBy(how = How.CSS, using = "#faxId")
	private WebElement faxStatusFaxId;

	@FindBy(how = How.CSS, using = "#startDateId")
	private WebElement faxStatusDtRangeStartDate;

	@FindBy(how = How.CSS, using = "#endDateId")
	private WebElement faxStatusDtRangeEndDate;

	@FindBy(how = How.CSS, using = "#endDateId+img")
	private WebElement faxStatusDtRangeEndDatePicker;

	@FindBy(how = How.CSS, using = "#startTime")
	private WebElement faxStatusDtRangeStartTime;

	@FindBy(how = How.CSS, using = "#endTime")
	private WebElement faxStatusDtRangeEndTime;
	
	private String statusXPath1 = "//*[@name = 'faxStatus' and @value = '";
	private String statusXpath2 = "']";
	
	private String faxTypeXpath1 = "//*[@name = 'faxType' and @value = '";
	private String faxTypeXpath2 = "']";
	
	@FindBy(how = How.CSS, using = "#refreshInterval")
	private WebElement faxStatusRegreshInterval;

	@FindBy(how = How.CSS, using = "button[value='Refresh']")
	private WebElement faxStatusRefreshBtn;

	@FindBy(how =How.CSS, using =".Code_TextBox")
	private WebElement faxStatusFailedTime;
	
	@FindBy (how = How.CSS, using ="button[value='Re-Send Fax']")
	private WebElement reSendFaxBtn;
	
	@FindBy (how = How.CSS, using ="button[value='Manual Send']")
	private WebElement manualSendBtn;
	
	@FindBy (how = How.CSS, using ="button[value='Print Fax']")
	private WebElement printFaxBtn;
	
	@FindBy (how = How.LINK_TEXT, using ="Tenant")
	private WebElement tenant;
	
	@FindBy (how = How.LINK_TEXT, using ="Fax ID")
	private WebElement faxId;
	
	@FindBy (how = How.LINK_TEXT, using ="Date Created")
	private WebElement dateCreated;
	
	@FindBy (how = How.LINK_TEXT, using ="Vendor")
	private WebElement vendor;
	
	@FindBy (how = How.LINK_TEXT, using ="Fax #/Email Address")
	private WebElement faxEmailAddress;
	
	@FindBy (how = How.LINK_TEXT, using ="Voice #")
	private WebElement voice;
	
	@FindBy (how = How.LINK_TEXT, using ="Contact Name")
	private WebElement contactName;
	
	@FindBy (how = How.LINK_TEXT, using ="Fax Type")
	private WebElement faxType;
	
	@FindBy (how = How.LINK_TEXT, using ="Status")
	private WebElement status;
	
	@FindBy (how = How.LINK_TEXT, using ="Time in Queue")
	private WebElement timeInQueue;
	
	@FindBy (how = How.LINK_TEXT, using ="Explanation")
	private WebElement explanation;
	
	@FindBy (how = How.LINK_TEXT, using ="User")
	private WebElement user;
	
	@FindBy(how = How.XPATH, using = "//*[@name = 'checkbox' and @onclick = 'checkAll(this,this.form.check_list)']")
	private WebElement selectAll;
	
	public EventFiringWebDriver driver=null;

	public Page_FaxStatus(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setTenant(String tenantName){
		//logger.info("*** Admin_FaxStatusPage -> setTenant method started ***");
		selectByVisibleText(this.tenantName.get(1), tenantName);
		//logger.info("*** setTenant method completed ***");
	}
	
	public void setQueryDetails(String faxId, String startDate, String endDate, String startTime, String endTime, String status, String faxType) throws Exception{
		//logger.info("*** Admin_FaxStatusPage -> setQueryDetails method started ***");
		typeInText(this.faxStatusFaxId, faxId);
		typeInText(faxStatusDtRangeStartDate, startDate);
		typeInText(faxStatusDtRangeEndDate, endDate);
		typeInText(faxStatusDtRangeStartTime, startTime);
		typeInText(faxStatusDtRangeEndTime, endTime);
		for(String thisStatus : status.split(",")){
			if(!findElementByXpath(statusXPath1+thisStatus+statusXpath2).isSelected())
				clickOn(findElementByXpath(statusXPath1+thisStatus+statusXpath2));
		}
		for(String thisFaxType : faxType.split(",")){
			if(!findElementByXpath(faxTypeXpath1+thisFaxType+faxTypeXpath2).isSelected())
				clickOn(findElementByXpath(faxTypeXpath1+thisFaxType+faxTypeXpath2));
		}
		//logger.info("*** setQueryDetails method completed ***");
	}
	
	public void refresh(int refreshInterval) throws Exception{
		//logger.info("*** Admin_FaxStatusPage -> refresh method started ***");
		selectByIndex(faxStatusRegreshInterval, refreshInterval);
		waitForElementVisibility(faxStatusRefreshBtn);
		waitForElementVisibility(faxStatusFailedTime);
		waitForElementVisibility(reSendFaxBtn);
		waitForElementVisibility(manualSendBtn);
		waitForElementVisibility(printFaxBtn);
		//logger.info("*** refresh method completed ***");
	}
	
	public void recordsFound(){
		//logger.info("*** Admin_FaxStatusPage -> recordsFound method started ***");
		waitForElementVisibility(tenant);
		waitForElementVisibility(faxId);
		waitForElementVisibility(dateCreated);
		waitForElementVisibility(vendor);
		waitForElementVisibility(faxEmailAddress);
		waitForElementVisibility(voice);
		waitForElementVisibility(contactName);
		waitForElementVisibility(faxType);
		waitForElementVisibility(status);
		waitForElementVisibility(timeInQueue);
		waitForElementVisibility(explanation);
		waitForElementVisibility(user);
		waitForElementVisibility(selectAll);
		//logger.info("*** recordsFound method completed ***");
	}

}
