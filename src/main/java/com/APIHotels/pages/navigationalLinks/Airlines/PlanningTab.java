package com.APIHotels.pages.navigationalLinks.Airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class PlanningTab extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = "//td[text()='Planning']")
	public WebElement planninglink;

	@FindBy(how = How.ID, using = "iconschedules") 
	public WebElement supplierScheduleslink;
	
	@FindBy(how = How.ID, using = "iconuploadPairings")
	public WebElement uploadPairingsLink;
	
	@FindBy(how = How.ID, using = "iconexportAssignedPairingId")
	public WebElement exportAssignedPairingsLink;

	@FindBy(how = How.ID, using = "iconexportHotelld")
	public WebElement exportHotelsLink;
	
	@FindBy(how = How.ID, using = "iconexportPairingId")
	public WebElement exportPairingsLink;
	
	@FindBy(how = How.ID, using = "iconuploadBTPairings")
	public WebElement uploadRoamingListLink;
	
	public PlanningTab(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnSupplierSchedulesLink() {
		clickOn(planninglink);
		clickOn(supplierScheduleslink);
	}
	
	public void clickOnUploadPairingsLink(){
		clickOn(planninglink);
		clickOn(uploadPairingsLink);
	}
	
	public void clickOnExportAssignedPairingsLink(){
		clickOn(planninglink);
		clickOn(exportAssignedPairingsLink);
	}
	
	public void clickOnExportHotelsLink(){
		clickOn(planninglink);
		clickOn(exportHotelsLink);
	}
	
	public void clickOnExportPairingsLink() {
		clickOn(planninglink);
		clickOn(exportPairingsLink);
	}
	
	public void clickOnUploadRoamingListLink() {
		clickOn(planninglink);
		clickOn(uploadRoamingListLink);
	}
}
