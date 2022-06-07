package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;



public class Page_MultiNightGrouping extends BasePage{

	@FindBy(how = How.CSS, using ="#yes")
	private WebElement enableMultiNightGroupingYes;
	
	@FindBy(how = How.CSS, using ="#no")
	private WebElement enableMultiNightGroupingNo;
	
	@FindBy(id = "tenant")
	private WebElement tenant;
	
	@FindBy(name = "save")
	private WebElement saveBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_MultiNightGrouping(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void configMultiNightGrouping(String tenant, String enableMultiNightGroupingInd) throws Exception{
		//logger.info("*** Admin_MultiNightGroupingPage -> configureMultiNightGrouping method started ***");
		selectByVisibleText(this.tenant, tenant);
		/*this.tenant.sendKeys(Keys.TAB);
		waitForPageToLoad("5");*/
		Thread.sleep(10000);
		if(enableMultiNightGroupingInd.equals("Yes"))
			clickOn(enableMultiNightGroupingYes);
		waitForElementVisibility(enableMultiNightGroupingNo);
		waitForElementVisibility(saveBtn);
		//logger.info("*** configureMultiNightGrouping method completed ***");
	}
	
	
}
