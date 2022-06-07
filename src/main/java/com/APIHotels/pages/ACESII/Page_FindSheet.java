package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_FindSheet extends BasePage{
	
	@FindBy(css = "#opsFindCsi > a")
	private WebElement findSheetLink;
	
	@FindBy(id = "locCd")
	private WebElement city;
	
	@FindBy(id = "supplierId")
	private WebElement supplier;
	
	@FindBy(id = "startDate")
	private WebElement startDate;
	
	@FindBy(id = "endDate")
	private WebElement endDate;
	
	@FindBy(id = "crewSignInId")
	private WebElement signInSheetId;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Retrieve']")
	private WebElement retrieveBtn;
	
	@FindBy(xpath = "//*[@id = 'crewSignIn']/tbody/tr")
	private List<WebElement> sheetTableRows;
	
	@FindBy(xpath = "//*[@id = 'crewSignIn']/tbody/tr/td")
	private WebElement sheetCol;
	
	public EventFiringWebDriver driver=null;

	public Page_FindSheet(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickFindSheetLink() throws Exception{
		clickOn(findSheetLink);
	}
	
	public void findCrewSignInSheet(String city, String supplier, String startDate, String endDate) throws Exception{
		typeInText(this.city, city);
		this.city.sendKeys(Keys.TAB);
		waitForPageLoad(driver);
		selectByVisibleText(this.supplier, supplier);
		typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);
		/*typeInText(this.startDate, startDate);
		typeInText(this.endDate, endDate);*/
		waitForElementVisibility(this.signInSheetId);
		clickOn(retrieveBtn);
		if(sheetTableRows.size() == 1)
			System.out.println("Row Detials: "+sheetCol.getText());
		else
			System.out.println("total rows: "+sheetTableRows.size());
	}
	
}
