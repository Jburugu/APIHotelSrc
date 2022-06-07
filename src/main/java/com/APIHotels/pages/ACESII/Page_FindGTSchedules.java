package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_FindGTSchedules extends BasePage{

	@FindBy(css = "#findGTDailySchedule > a")
	private WebElement findGTSchedulesLink;
	
	@FindBy(id = "locCd")
	private WebElement city;
	
	@FindBy(id = "supplierId")
	private WebElement suppliers;
	
	@FindBy(id = "startDate")
	private WebElement startDate;
	
	@FindBy(id = "endDate")
	private WebElement endDate;
	
	@FindBy(id = "gtScheduleId")
	private WebElement gtSheetId;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Retrieve']")
	private WebElement retrieveBtn;
	
	@FindBy(xpath = "//*[@id = 'gtDailySchedule']/tbody/tr")
	private List<WebElement> resultTableRows;
	
	@FindBy(xpath = "//*[@id = 'gtDailySchedule']/tbody/tr/td")
	private WebElement resultTableMessage;
	public EventFiringWebDriver driver=null;

	public Page_FindGTSchedules(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickFindGTSchedules()throws Exception{
		clickOn(findGTSchedulesLink);
	}
	
	public void findGTSchedules(String city, String supplier, String startDate, String endDate) throws Exception{
		typeInText(this.city, city);
		this.city.sendKeys(Keys.TAB);
		try{Thread.sleep(1000);}catch(Exception e){}
		waitForElementVisibility(this.suppliers);
		selectByIndex(this.suppliers, Integer.parseInt(supplier));
		
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		waitForElementVisibility(gtSheetId);
		clickOn(retrieveBtn);
		int rows = resultTableRows.size();
		if(rows == 1)
			System.out.println(resultTableMessage.getText());
	}
	
}
