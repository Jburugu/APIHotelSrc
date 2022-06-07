package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ReportsMenu extends BasePage{
	
	
	@FindBy(css = "#reportPlanning > a")
	private WebElement planningReportsLink;
	
	@FindBy(css = "#hotelExport > a")
	private WebElement hotelExportLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Ops Reports')]")
	private WebElement opsReportsLink;
	
	public EventFiringWebDriver driver=null;

	public ReportsMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickPlanningReportsLink() throws Exception{
		clickOn(planningReportsLink, "Reports Menu -> PlanningReports Link");
	}
	
	public void clickHotelExportLink() throws Exception{
		clickOn(hotelExportLink, "Reports Menu -> HotelExport Link");
	}
	
	public void clickOpsReportsLink() throws Exception{
		clickOn(opsReportsLink, "Reports Menu -> OpsReport Link");
	}

}
