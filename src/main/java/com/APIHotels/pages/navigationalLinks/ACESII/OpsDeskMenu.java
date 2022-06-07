package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class OpsDeskMenu extends BasePage{

	
	@FindBy(css = "#opsDashBoard > a")
	private WebElement dashBoardLink;
	
	@FindBy(css = "#opsFindTrip > a")
	private WebElement findTripLink;
	
	@FindBy(id = "opsRefreshTrip")
	private WebElement refreshTripLink;
	
	@FindBy(css = "#fetch > a")
	private WebElement pendingConfirmationsLink;
	
	@FindBy(xpath = "//*[@id='smenu5']/div[5]")
	private WebElement schedulesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Reservations')]")
	private WebElement reservationsLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Crew Sign-In Sheets')]")
	private WebElement crewSignInSheetsLink;
	
	@FindBy(xpath = "//*[@id='opsConfiguration']/a")
	private WebElement configurationLink;
	
	public EventFiringWebDriver driver=null;

	public OpsDeskMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickDashBoardLink() throws Exception{
		clickOn(dashBoardLink, "OpsDesk Menu-> Dashboard Link");
	}
	
	public void clickFindTripLink() throws Exception{
		clickOn(findTripLink, "OpsDesk Menu-> FindTrip Link");
	}
	
	public void clickRefreshTrip() throws Exception{
		clickOn(refreshTripLink, "OpsDesk Menu-> RefreshTrip Link");
	}
	
	public void clickPendingConfirmationsLink() throws Exception{
		clickOn(pendingConfirmationsLink, "OpsDesk Menu-> PendingConfirmation Link");
	}
	
	public void clickSchedulesLink() throws Exception{
		clickOn(schedulesLink, "OpsDesk Menu-> Schdules Link");
	}
	
	public void clickReservationsLink() throws Exception{
		clickOn(reservationsLink, "OpsDesk Menu-> Reservations Link");
	}
	
	public void clickCrewSignInSheetsLink() throws Exception{
		clickOn(crewSignInSheetsLink, "OpsDesk Menu-> CrewSignInSheets Link");
	}
	
	public void clickConfigurationLink() throws Exception{
		clickOn(configurationLink, "OpsDesk Menu-> Configuration Link");
	}
}
