package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_SignInSheets_ACES3ACES extends BasePage {

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Online Sign-in Sheets')]")
	WebElement OnlineSignInSheetsLink;
	
	@FindBy(linkText = "Sign-in Sheets")
	WebElement signInSheetsLink;
	
	@FindBy(linkText = "Online Sign-in Sheets")
	WebElement onlineSignInSheetsLink;
	
	@FindBy(xpath = "//label[contains(text(),'Select Airline*:')]/parent::td/following-sibling::td/div[contains(@class,'selectonemenu')]")
	WebElement dropdown_TenantSIS;

	@FindBy(xpath = "//label[contains(text(),'City:')]/parent::span/span/input")
	WebElement textbox_CitySIS;

	public EventFiringWebDriver driver = null;

	public Page_SignInSheets_ACES3ACES(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnOnlineSignInSheetsLink() {
		clickOn(OnlineSignInSheetsLink,"Sign-In Sheets Menu -> Online Sign-In Sheets Link");
	}

	public void clickOnSighInSheetLink() {
		clickOn(signInSheetsLink,"ACES 3 ACES Menu -> Sign-In Sheets Link");
	}

	public void verifySISPageElements() {
		waitForElementVisibility(onlineSignInSheetsLink);
		waitForElementVisibility(textbox_CitySIS);
	}

}
