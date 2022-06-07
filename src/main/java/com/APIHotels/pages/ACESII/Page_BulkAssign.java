package com.APIHotels.pages.ACESII;


import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class Page_BulkAssign extends BasePage{

	@FindBy(id = "bidPeriod")
	private WebElement bidPeriod;
	
	@FindBy(name = "keepTba")
	private WebElement keepTbaIndicator;
	
	@FindBy(name = "keepManual")
	private WebElement keepManualIndicator;
	
	@FindBy(name = "keepMissingDbrtVendorAsWarn")
	private WebElement keepAsWarningIndicator;
	
	@FindBy(xpath = "//*[@value = 'Bulk Assign']")
	private WebElement bulkAssignBtn;
	
	@FindBy(xpath = "//*[@id='ProgressBox']")
	public WebElement inProgressBox;
	
	public EventFiringWebDriver driver=null;

	public Page_BulkAssign(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void bulkAssignPairings(String bidPeriodIndex, String keepTbaIndicator, String keepManualIndicator, String keepAsWarningIndicator) throws Exception{
		//logger.info("*** BulkAssignPage -> bulkAssignPairings method started ***");
		waitForElementVisibility(bidPeriod);//SelectByIndex if default needs to changed
		checkCheckBox(this.keepTbaIndicator, keepTbaIndicator);
		checkCheckBox(this.keepManualIndicator, keepManualIndicator);
		checkCheckBox(this.keepAsWarningIndicator, keepAsWarningIndicator);
		waitForElementVisibility(this.bulkAssignBtn);
		//logger.info("*** bulkAssignPairings method completed***");
	}
	
	
	public void bulkAssignPairings(String keepTbaIndicator, String keepManualIndicator, String keepAsWarningIndicator) throws Exception{
		//logger.info("*** BulkAssignPage -> bulkAssignPairings method started ***");
		checkCheckBox(this.keepTbaIndicator, keepTbaIndicator);
		checkCheckBox(this.keepManualIndicator, keepManualIndicator);
		checkCheckBox(this.keepAsWarningIndicator, keepAsWarningIndicator);
		clickOn(this.bulkAssignBtn);
		waitForInProgressToComplete();
		//logger.info("*** bulkAssignPairings method completed***");
	}
	
	//This method is used to select CheckBox if the corresponding elementIndicator value is set to Yes. 
	//If the value is set to any other value, the corresponding element/checkbox is unchecked
	private void checkCheckBox(WebElement checkBox, String indicatorName) throws Exception{
		//logger.info("*** BulkAssignPage -> checkCheckBox method started ***");
		if(indicatorName.equals("Yes")){
			if(!checkBox.isSelected())
				clickOn(checkBox);
		}else
			if(checkBox.isSelected())
				clickOn(checkBox);
	}
	
	public void waitForInProgressToComplete(){
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.withTimeout(Duration.ofMinutes(60));
        wait.pollingEvery(Duration.ofSeconds((15)));
        wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.invisibilityOf(inProgressBox));
	}
	public void bulkAssignPairingsFor(String bidMonth, String keepTbaIndicator, String keepManualIndicator, String keepAsWarningIndicator) throws Exception{
		//logger.info("** BulkAssignPage -> bulkAssignPairings method started **");
		selectByVisibleText(bidPeriod, bidMonth);
		checkCheckBox(this.keepTbaIndicator, keepTbaIndicator);
		checkCheckBox(this.keepManualIndicator, keepManualIndicator);
		checkCheckBox(this.keepAsWarningIndicator, keepAsWarningIndicator);
		clickOn(this.bulkAssignBtn);
		waitForInProgressToComplete();
		//logger.info("*** bulkAssignPairings method completed***");
	}

}

	

