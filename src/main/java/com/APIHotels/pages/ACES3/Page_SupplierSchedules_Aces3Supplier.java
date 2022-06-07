package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_SupplierSchedules_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//*[contains(@id, 'scheduleTable_data')]/tr/td[6]")
	private WebElement releaseColumn;
	
	@FindBy(xpath = "//*[contains(@id, 'scheduleTable_data')]/tr/td[8]")
	private WebElement acknowledgedColumn;
	
	@FindBy(xpath = "//*[contains(@id, 'scheduleTable_data')]/tr/td[9]/a")
	private WebElement schedulePdfFile;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[@type = 'application/pdf']")
	private WebElement pdfElement;
	
	@FindBy(xpath = "//*[contains(@id, 'scheduleTable_data')]/tr/td[10]/a")
	private WebElement exportIcon;
	
	public EventFiringWebDriver driver=null;

	public Page_SupplierSchedules_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyReleaseDate(String expectedReleaseDate){
		Assert.assertEquals(releaseColumn.getText(), expectedReleaseDate, "ReleaseDate Mismatch!");
	}
	
	public void downloadPdf(){
		String currentWindowHandle = getDriver().getWindowHandle();
		clickOn(schedulePdfFile, "SupplierSchedulesPage -> pdfFileIcon");
		waitForElementToDisappear(spinner);
		switchToNewWindow(currentWindowHandle);
		getDriver().close();
		switchToWindow(currentWindowHandle);
	}
	
	public void verifyAcknowledged(String user){
		String acknowledgedText = getDateInFormat("ddMMMyyyy")+"-"+user;
		Assert.assertEquals(acknowledgedText, (acknowledgedColumn.getText().trim()).replace(" ", ""), "Acknowledged Info Mismatch!");
	}
	
	public void export() throws Exception{
		clickOn(exportIcon, "SupplierSchedulesPage -> exportFileIcon");
		waitForElementToDisappear(spinner);
		waitForPageLoad(getDriver());
		Thread.sleep(10000);
	}
}
