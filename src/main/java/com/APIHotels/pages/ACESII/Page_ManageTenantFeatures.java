package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_ManageTenantFeatures extends BasePage{

	@FindBy(how = How.XPATH, using = "//input[@name='billableNoShowsSupported' and @value = 'true']")
	private WebElement isBillableNoShowsSupportedIndicato_Yes;
	
	@FindBy(how = How.XPATH, using = "//input[@name='billableNoShowsSupported' and @value = 'false']")
	private WebElement isBillableNoShowsSupportedIndicato_No;
	
	@FindBy(name = "billableNoShowsActivated")
	private List<WebElement> activateThisFeatureIndicator;
	
	@FindBy(how = How.ID, using = "ccAuthorizeIncidental")
	private WebElement ccAuthorizeIncidental;

	@FindBy(how = How.ID, using = "editIncidentalVerbiage")
	private WebElement editIncidentalVerbiage;
	
	@FindBy(id = "incidentalVerbiageEN")
	private WebElement englishIncidentalVerbiage;

	@FindBy(id = "incidentalVerbiageENBold")
	private WebElement englishBold;
	
	@FindBy(id = "incidentalVerbiageENItalic")
	private WebElement englishItalic;
	
	@FindBy(id = "incidentalVerbiageES")
	private WebElement spanishIncidentalVerbiage;
	
	@FindBy(id = "incidentalVerbiageESBold")
	private WebElement spanishBold;
	
	@FindBy(id = "incidentalVerbiageESItalic")
	private WebElement spanishItalic;
		
	@FindBy(id = "incidentalVerbiagePT")
	private WebElement portugeseIncidentalVerbiage;
	
	@FindBy(id = "incidentalVerbiagePTBold")
	private WebElement portugeseBold;
	
	@FindBy(id = "incidentalVerbiagePTItalic")
	private WebElement portugeseItalic;

	public EventFiringWebDriver driver=null;

	public Page_ManageTenantFeatures(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setBillableNoShowsSettings(String featureIndicator, String activateFeatureIndicator) throws Exception{
		//logger.info("*** Admin_ManageTenantFeaturesPage -> setBillableNoShowsSettings method started ***");
		waitForElementVisibility(isBillableNoShowsSupportedIndicato_No);
		if(featureIndicator.equals("Yes")){
			clickOn(isBillableNoShowsSupportedIndicato_Yes);
			for(WebElement activateIndicator : this.activateThisFeatureIndicator)
				if(activateIndicator.getAttribute("value").equals(activateFeatureIndicator)){
					clickOn(activateIndicator);
					break;
				}
		}
		//logger.info("*** setBillableNoShowsSettings method completed ***");
	}
	
	public void setCreditCardAuthorizations(String authorizedIncidentals, String editIncidentalVerbiage) throws Exception{
		//logger.info("*** Admin_ManageTenantFeaturesPage -> setCreditCardAuthorizations method started ***");
		if(authorizedIncidentals.equals("true"))
			clickOn(ccAuthorizeIncidental);
		if(editIncidentalVerbiage.equals("true")){
			clickOn(this.editIncidentalVerbiage);
			waitForElementVisibility(englishIncidentalVerbiage);
			waitForElementVisibility(englishBold);
			waitForElementVisibility(englishItalic);
			
			waitForElementVisibility(spanishIncidentalVerbiage);
			waitForElementVisibility(spanishBold);
			waitForElementVisibility(spanishItalic);
			
			waitForElementVisibility(portugeseIncidentalVerbiage);
			waitForElementVisibility(portugeseBold);
			waitForElementVisibility(portugeseItalic);
		}
		//logger.info("*** setCreditCardAuthorizations method completed ***");
	}

}
