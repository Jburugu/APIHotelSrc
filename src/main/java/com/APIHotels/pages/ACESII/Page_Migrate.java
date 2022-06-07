package com.APIHotels.pages.ACESII;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;



public class Page_Migrate extends BasePage{


	
	@FindBy(xpath = "//button[contains(text(),'Migrate')]")
	private List<WebElement> migrateBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'none')]")
	private WebElement noneTxt;
	
	@FindBy(id="progressSpinner")
	private WebElement spinner;
	
	@FindBy(xpath = "//span[contains(text(),'Migration completed')]")
	private WebElement migrationCompletedText;
	
	public EventFiringWebDriver driver=null;

	public Page_Migrate(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	public void migrate() throws Exception{
//		try{
//			waitForElementVisibility(migrateBtn);
//		}catch(Exception e){
//			waitForElementVisibility(noneTxt);
//		}
//	}
	
	public void migrate() throws Exception{
		if(migrateBtn.size()>0){
			clickOn(migrateBtn.get(0), "Migrate Page -> Clicked On Migrate Button");
			waitForSpinnerToDisappear();
			Thread.sleep(15000);
			waitForElementVisibility(migrationCompletedText);
			Thread.sleep(10000);
			}
		else
			Assert.fail("Migrate Button not displayed in Application");
		
			}
	
	private void waitForSpinnerToDisappear() {
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(30));
		wait1.pollingEvery(Duration.ofSeconds(2));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}

}
