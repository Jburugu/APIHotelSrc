package com.APIHotels.pages.ACES3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ChallengeInvoice_Aces3Supplier extends BasePage{
	
	@FindBy(id = "form1:Comments")
	private WebElement comments;
	
	@FindBy(id = "form1:First_Name")
	private WebElement firstName;
	
	@FindBy(id = "form1:Last_Name")
	private WebElement lastName;
	
	@FindBy(id = "form1:Phone_Number")
	private WebElement phoneNumber;
	
	@FindBy(id = "form1:Email")
	private WebElement email;
	
	@FindBy(id = "form1:submit")
	private WebElement challengeInvoiceBtn;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[contains(text(), 'OK')]")
	private WebElement challengeConfirmation_OkBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_ChallengeInvoice_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void challengeInvoice(String comments, String firstName, String lastName, String phoneNumber, String email) {
		typeInText(this.comments, comments, "ChallengeInvoicePage -> Comments");
		typeInText(this.firstName, firstName, "ChallengeInvoicePage -> First Name");
		typeInText(this.lastName, lastName, "ChallengeInvoicePage -> Last Name");
		typeInText(this.phoneNumber, phoneNumber, "ChallengeInvoicePage -> Phone Number");
		typeInText(this.email, email, "ChallengeInvoicePage -> Email Address");
		clickOn(challengeInvoiceBtn, "ChallengeInvoicePage -> Challenge Invoice btn");
		waitForElementToDisappear(spinner);
		clickOn(challengeConfirmation_OkBtn, "ChallengeInvoicePage -> Challenge Invoice Confirmation pop-upp -> OK btn");
	}

}
