package com.APIHotels.pages.ACESII;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_GateTimes extends BasePage{

	@FindBy(id = "gateTimesRowCount")
	private WebElement rowCount;
	
	@FindBy(xpath = "//*[@name = 'gateTimesButton' and @value = 'Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//*[@onclick = 'addGateTimesRow()' and @value = 'Add']")
	private WebElement addBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deleteGateTimes()' and @value = 'Delete']")
	private WebElement deleteBtn;
	
	@FindBy(id="gateTime[0].gateToCurbTime")
	public WebElement gatetoCurbinMins;
	
	@FindBy(xpath="//*[starts-with(@id,'gateTime') and contains(@id, 'locCd')]")
	public List<WebElement> locationRows;
	
	private String commonId = "gateTime[";
	private String startDate = "].startDate";
	private String endDate = "].endDate";
	private String startTime = "].startTimeDummy";
	private String endTime = "].endTimeDummy";
	private String location = "].locCd";
	private String gateToCurbTime = "].gateToCurbTime";
	private String curbToGateTime = "].curbToGateTime";
	private String deleteCheckBox = "deleted";
	
	public EventFiringWebDriver driver=null;

	public Page_GateTimes(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addGateTimesRow(String location, String gateToCurbTime, String curbToGateTime) throws Exception{
		String rowId = rowCount.getAttribute("value").toString();
		waitForElementVisibility(deleteBtn);
		clickOn(addBtn);
		waitForElementVisibility(findElementById(commonId+rowId+startDate));
		waitForElementVisibility(findElementById(commonId+rowId+endDate));
		waitForElementVisibility(findElementById(commonId+rowId+startTime));
		waitForElementVisibility(findElementById(commonId+rowId+endTime));
		typeInText(findElementById(commonId+rowId+this.location), location);
		typeInText(findElementById(commonId+rowId+this.gateToCurbTime), gateToCurbTime);
		if(!curbToGateTime.isEmpty())
			typeInText(findElementById(commonId+rowId+this.curbToGateTime), curbToGateTime);
		waitForElementVisibility(findElementById(deleteCheckBox+rowId));
	}
	
	public void saveDetails() throws Exception{
		waitForElementVisibility(saveBtn);
	}
	
	public String getGateToCurbMins()
	{
		String gateToCurbMins=gatetoCurbinMins.getAttribute("value");
		System.out.println(gateToCurbMins);
		return gateToCurbMins;
	}
	
	public String getgateTimes(String CityValue) throws Exception
	{	
		String gateToCurbTimes="";
		String curbToGateTimes="";
		int locationRowCount = locationRows.size();
		for (int rowcount = 0; rowcount <= locationRowCount; rowcount++) {

			WebElement locationValue = findElementById(commonId + rowcount + location);
			String locValue = locationValue.getAttribute("value");
			if (locValue.equalsIgnoreCase(CityValue)) {
				WebElement gateToCabMins = findElementById(commonId + rowcount + gateToCurbTime);
				String gateToCurbMinsValue = gateToCabMins.getAttribute("value");
				WebElement CurbToGateMins= findElementById(commonId + rowcount + curbToGateTime);
				String curbToGateMinsValue= CurbToGateMins.getAttribute("value");
				gateToCurbTimes= formatDate(gateToCurbMinsValue);
				curbToGateTimes= formatDate(curbToGateMinsValue);
				break;
			}
		
		}
		
		return gateToCurbTimes + " " + curbToGateTimes;
	
		
	}
	
	public String formatDate(String reqTime) throws Exception{
		
		SimpleDateFormat format = new SimpleDateFormat("mm");
		Date gateTime = format.parse(reqTime);
		String formattedTime=gateTime.toString();
		String[] gateMins= formattedTime.split(" ");
		String gateTimes=gateMins[3];
		return gateTimes;
	}
	
	
}
