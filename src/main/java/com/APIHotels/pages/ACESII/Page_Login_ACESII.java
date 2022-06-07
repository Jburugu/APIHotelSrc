package com.APIHotels.pages.ACESII;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.framework.BasePage;

public class Page_Login_ACESII extends BasePage{
	
	@FindBy(id = "j_username")
	public WebElement acesIIUserName;

	@FindBy(id = "j_password")
	public WebElement acesIIPassword;

	@FindBy(name = "submit")
	public WebElement acesIILoginBtn;
	
	@FindBy(xpath="//span[contains(text(),'Login')]")
	public WebElement acesIISupplierLoginBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_Login_ACESII(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginToACESII(String userName, String password) throws Exception {
		typeInText(acesIIUserName, userName, "LoginPage -> UserID");
		typeInText(acesIIPassword, password, "LoginPage -> Password");
		clickOn(acesIILoginBtn, "LoginPage -> Login Button");
	}
	
	public void loginToACESII(DataTable loginDetailsTable) throws Exception {
		typeInText(acesIIUserName, loginDetailsTable.getFieldValue(1, "userName"),"LoginPage -> UserID");
		typeInText(acesIIPassword, loginDetailsTable.getFieldValue(1, "password"),"LoginPage -> Password");
		clickOn(acesIILoginBtn,"LoginPage -> Login Button");
	}
	
	protected boolean isElementPresent(WebElement element) {
		try {
			waitForElementVisibility(element);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
		
	public void loginToAces2Supplier(String userName, String password) throws Exception {
		typeInText(acesIIUserName, userName, "LoginPage -> UserID");
		typeInText(acesIIPassword, password, "LoginPage -> Password");
		clickOn(acesIISupplierLoginBtn,"LoginPage -> Login Button");
	}
}
	

