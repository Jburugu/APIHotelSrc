package com.APIHotels.pages.ACESII;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_GenerateCrewSignInSheet extends BasePage{
	
	@FindBy(css = "#opsCsi > a")
	private WebElement generateSheetLink;
	
	@FindBy(id = "locCd")
	private WebElement city;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Get Suppliers']")
	private WebElement getSuppliersBtn;
	
	@FindBy(id = "supplierId")
	private WebElement supplier;
	
	@FindBy(id = "reservationDate")
	private WebElement reservationDate;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Generate']")
	private WebElement generateBtn;
	
	//private String crewSignInSheetPreviewFrameId = "crewSignSheetPreview";
	
	@FindBy(id = "crewSignSheetPreview")
	private WebElement crewSignInSheetPreviewFrameId;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Fax  *']")
	private WebElement faxBtn;
	
	@FindBy(xpath = "//*[@class = 'Aces_Btn' and @value = 'Email']")
	private WebElement emailBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_GenerateCrewSignInSheet(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickGenerateSheetLink() throws Exception{
		clickOn(generateSheetLink);
	}
	public void generateCrewSignInSheet(String city, String supplier, String reservationDate) throws Exception{
		typeInText(this.city, city);
		clickOn(getSuppliersBtn);
		selectByVisibleText(this.supplier, supplier);
		typeInText(this.reservationDate, reservationDate);
		clickOn(generateBtn);
		waitForElementVisibility(faxBtn);
		waitForElementVisibility(emailBtn);
	//	switchToFrame(crewSignInSheetPreviewFrameId);
		waitForElementVisibility(crewSignInSheetPreviewFrameId);
		
	}
}
