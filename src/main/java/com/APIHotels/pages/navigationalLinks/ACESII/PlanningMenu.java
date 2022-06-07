package com.APIHotels.pages.navigationalLinks.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class PlanningMenu extends BasePage{
	
	@FindBy(xpath = "//*[contains(text(), 'Bulk Assign')]")
	private WebElement bulkAssignLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Errors Warnings')]")
	private WebElement errorsWarningsLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Assignment')]")
	private WebElement assignmentLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Export Pairings')]")
	private WebElement exportPairingsLink;
	
	@FindBy(xpath = "//*[@id = 'migrate']/a")
	private WebElement migrateLink;
	
	@FindBy(xpath = "//*[@id = 'pairingGroupConfig']/a")
	private WebElement configurationLink;

	public EventFiringWebDriver driver=null;

	public PlanningMenu(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickBulkAssignLink()throws Exception{
		clickOn(bulkAssignLink, "Planning Menu-> BulkAssign Link");
		//logger.info("*** clicked on Planning menu -> Bulk Assign Link ***");
	}
	
	public void clickErrorsWarningsLink()throws Exception{
		clickOn(errorsWarningsLink, "Planning Menu-> ErrorAndWarningsLink");
		//logger.info("*** clicked on Planning menu -> Errors Warnings Link ***");
	}
	
	public void clickAssignmentLink()throws Exception{
		clickOn(assignmentLink, "Planning Menu-> Assignments Link");
		//logger.info("*** clicked on Planning menu -> Assignment Link ***");
	}
	
	public void clickExportPairings()throws Exception{
		clickOn(exportPairingsLink, "Planning Menu-> ExportPairings Link");
		//logger.info("*** clicked on Planning menu -> Export Pairings Link ***");
	}
	
	public void clickMigrateLink()throws Exception{
		clickOn(migrateLink, "Planning Menu-> Migrate Link");
		//logger.info("*** clicked on Planning menu -> Migrate Link ***");
	}
	
	public void clickConfigurationLink()throws Exception{
		clickOn(configurationLink, "Planning Menu-> Configuration Link");
		//logger.info("*** clicked on Planning menu -> Configuration Link ***");
	}
	
	
}
