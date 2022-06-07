package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.framework.BasePage;

public class Page_HotelExport extends BasePage{
	
	@FindBy(id = "hotelExportStartDate")
	private WebElement startDate;
	
	@FindBy(id = "hotelExportEndDate")
	private WebElement endDate;
	
	@FindBy(id = "exportFormat")
	private WebElement format;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Export']")
	private WebElement exportBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_HotelExport(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void exportHotel(String startDate, String endDate, String format) throws Exception{
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		selectByVisibleText(this.format, format);
		waitForElementVisibility(exportBtn);
	}
	
	public void exportHotel(DataTable exportHotelTable) throws Exception{
		typeInText(this.startDate, exportHotelTable.getFieldValue(1, "startDate"));
		typeInText(this.endDate, exportHotelTable.getFieldValue(1, "endDate"));
		selectByVisibleText(this.format, exportHotelTable.getFieldValue(1, "format"));
		waitForElementVisibility(exportBtn);
	}
}
