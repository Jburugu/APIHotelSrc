package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_ManageGroundTransportSchedule extends BasePage{

	@FindBy (how =How.CSS, using ="#enableSchedule1")
	private WebElement enableSchedule;
	
	@FindBy (how =How.CSS, using ="#contactDisabled")
	private WebElement contactInfoDisabled;
	
	@FindBy (how =How.CSS, using ="#includeCrewName")
	private WebElement includeCrewName;
	
	@FindBy(xpath = "//*[@id = 'activation1']/tbody/tr/td/fieldset/table/tbody/tr/td[2]/a[1]")
	private WebElement supplierOnLink;
	
	@FindBy(xpath = "//*[@id = 'activation1']/tbody/tr/td/fieldset/table/tbody/tr/td[2]/a[2]")
	private WebElement supplierOffLink;
	
	@FindBy (how =How.CSS, using="#dayInAdvance0")
	private WebElement dayInAdvance0;
	
	@FindBy(id = "timeOfDayHHMM00")
	private WebElement timeOfDay0;
	
	@FindBy (how =How.XPATH, using ="//div[@id='additionalDayInLoop0']//a[text()='Add Additional Time']")
	private WebElement addAdditionalTimeSection1;
	
	@FindBy (how=How.XPATH, using=".//*[@id='dayInAdvance0']/parent::td/following-sibling::td/a")
	private WebElement exceptions;
	
	@FindBy (how = How.XPATH, using =".//*[@id='addAdditionalDay']/following-sibling::table//a[text()='Add Additional Day']")
	private WebElement addAdditionalDaySection1;
	
	@FindBy(name = "Save")
	private WebElement saveBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_ManageGroundTransportSchedule(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void scheduleConfiguration() throws Exception{
		//logger.info("*** Admin_ManageGroundTransportSchedulePage -> scheduleConfiguration method started ***");
		waitForElementVisibility(enableSchedule);
		clickOn(contactInfoDisabled);
		clickOn(includeCrewName);
		waitForElementVisibility(supplierOnLink);
		waitForElementVisibility(supplierOffLink);
		waitForElementVisibility(dayInAdvance0);
		waitForElementVisibility(timeOfDay0);
		waitForElementVisibility(addAdditionalDaySection1);
		waitForElementVisibility(exceptions);
		waitForElementVisibility(addAdditionalTimeSection1);
		waitForElementVisibility(saveBtn);
		//logger.info("*** scheduleConfiguration method completed ***");
	}

}
