package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;

public class Page_CommonGTGeneralInfo extends BasePage{

	@FindBy(id = "locCD")
	private WebElement city;
	
	@FindBy(xpath = "//*[@onclick = 'addSupplierGTRow();ACES_Modified();' and @class = 'Aces_Btn_Add' and @value = 'Add']")
	private WebElement supplierName_AddBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deleteGt();' and @class = 'Aces_Btn_Add' and @value = 'Delete']")
	private WebElement supplierName_DeleteBtn;
	
	@FindBy(id = "startDate0")
	private WebElement startDate;
	
	@FindBy(id = "endDate0")
	private WebElement endDate;
	
	@FindBy(id = "scheduleName0")
	private WebElement provider;
	
	@FindBy(id = "delId0")
	private WebElement supplierName_DeleteRowCheckBox;
	
	@FindBy(name = "address1")
	private WebElement address1;
	
	@FindBy(name = "address2")
	private WebElement address2;
	
	@FindBy(name = "address3")
	private WebElement address3;
	
	@FindBy(name = "city")
	private WebElement supplierCity;
	
	@FindBy(name = "provstCD")
	private WebElement state;
	
	@FindBy(name = "postCD")
	private WebElement zip;
	
	@FindBy(id = "countryCdId")
	private WebElement country;
	
	@FindBy(id = "note1")
	private WebElement generalNotes;
	
	@FindBy(xpath = "//*[contains(@id,'tenantTypeId')]")
	private WebElement clients;
	
	@FindBy(name = "apiSupplierNo")
	private WebElement apiSupplierNo;
	
	@FindBy(xpath = "//*[@onclick = 'addGTContactInformationRow();ACES_Modified();' and @class = 'Aces_Btn_Add' and @value = 'Add']")
	private WebElement contactInfo_AddRowBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deletContactSupplierGTRow();' and @class = 'Aces_Btn_Add' and @value = 'Delete']")
	private WebElement contactInfo_DeleteRowBtn;
	
	@FindBy(name = "deptName")
	private WebElement dept_Title;
	
	@FindBy(name = "name")
	private WebElement name;
	
	@FindBy(name = "phone")
	private WebElement phone;
	
	@FindBy(name = "fax")
	private WebElement fax;
	
	@FindBy(name = "email")
	private WebElement email;
	
	@FindBy(name = "delContact")
	private WebElement contactInfo_DeleteRowCheckBox;
	
	@FindBy(name = "preferredLanguage")
	private WebElement preferredLanguage;
	
	@FindBy(id = "Expand")
	private WebElement expandAllLink;
	
	@FindBy(id = "Collapse")
	private WebElement collapseAllLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Tax / VAT Information')]")
	private WebElement taxVatInfo_Row;
	
	@FindBy(id = "suppliertaxid")
	private WebElement taxId;
	
	@FindBy(xpath = "//*[@id = 'inc1']/div/div[3]/button[1]")
	private WebElement taxVatInfo_AddRowBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deletSupplierGTTaxRow();' and @class = 'Aces_Btn_Add' and @value = 'Delete']")
	private WebElement taxVatInfo_DeleteRowBtn;
	
	@FindBy(id = "taxType0")
	private WebElement taxType;
	
	@FindBy(id = "vatname0")
	private WebElement taxName;
	
	@FindBy(id = "startDatetax0")
	private WebElement taxVat_StartDate;
	
	@FindBy(id = "endDatetax0")
	private WebElement taxVat_EndDate;
	
	@FindBy(id = "percent0")
	private WebElement percent;
	
	@FindBy(id = "flatFee0")
	private WebElement flatFree;
	
	@FindBy(id = "currencCd0")
	private WebElement units;
	
	@FindBy(id = "comments0")
	private WebElement comments;
	
	@FindBy(id = "delTax0")
	private WebElement taxVatInfo_DeleteRowCheckBox;
	
	@FindBy(xpath = "//*[@onclick = 'saveFunction(this.form)' and @name = 'buttonFlag' and @value = 'Save']")
	private WebElement saveBtn;
	public EventFiringWebDriver driver=null;

	public Page_CommonGTGeneralInfo(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setSupplierName(String city, String provider) throws Exception{
		typeInText(this.city, city);
		waitForElementVisibility(supplierName_AddBtn);
		waitForElementVisibility(supplierName_DeleteBtn);
		waitForElementVisibility(startDate);
		waitForElementVisibility(endDate);
		typeInText(this.provider, provider);
		waitForElementVisibility(supplierName_DeleteRowCheckBox);
	}
	
	public void verifySupplierName(String city, String provider) throws Exception{
		assertEquals(this.city.getAttribute("value"), city, "city mismatch");
		assertEquals(this.provider.getAttribute("value"), provider, "provider mismatch");
	}
	
	public void setGeneralDetails(String country, String client) throws Exception{
		waitForElementVisibility(address1);
		waitForElementVisibility(address2);
		waitForElementVisibility(address3);
		waitForElementVisibility(supplierCity);
		waitForElementVisibility(state);
		waitForElementVisibility(zip);
		selectByValue(this.country, country);
		waitForElementVisibility(generalNotes);
		selectByValue(clients, client);
		waitForElementVisibility(apiSupplierNo);
	}
	
	public void verifyGeneralDetails(String country, String client) throws Exception{
		Select thisCountry = new Select(this.country);
		assertEquals(thisCountry.getFirstSelectedOption().getAttribute("value"), country, country + " is not selected");
		Select thisClient = new Select(this.clients);
		assertEquals(thisClient.getFirstSelectedOption().getAttribute("value"), client, "client mismatch");
	}
	
	public void setContactInformation(String name, String preferredContactLanguage) throws Exception{
		waitForElementVisibility(contactInfo_DeleteRowBtn);
		clickOn(contactInfo_AddRowBtn);
		waitForElementVisibility(dept_Title);
		typeInText(this.name, name);
		waitForElementVisibility(phone);
		waitForElementVisibility(fax);
		waitForElementVisibility(email);
		waitForElementVisibility(contactInfo_DeleteRowCheckBox);
		selectByVisibleText(preferredLanguage, preferredContactLanguage);
	}
	
	public void setContactInformation(String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage) throws Exception{
		clickOn(contactInfo_AddRowBtn);
		typeInText(dept_Title, deptTitle);
		typeInText(this.name, name);
		typeInText(this.phone, phone);
		typeInText(this.fax, fax);
		typeInText(this.email, email);
		selectByVisibleText(preferredLanguage, preferredContactLanguage);
	}
	
	public void editContactInformation(String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage) throws Exception{
		typeInText(dept_Title, deptTitle);
		typeInText(this.name, name);
		typeInText(this.phone, phone);
		typeInText(this.fax, fax);
		typeInText(this.email, email);
		selectByVisibleText(preferredLanguage, preferredContactLanguage);
	}
	
	public void verifyContactInformation(String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage) throws Exception{
		assertEquals(dept_Title.getAttribute("value"), deptTitle, "deptTitle mismatch");
		assertEquals(this.name.getAttribute("value"), name, "name mismatch");
		assertEquals(this.phone.getAttribute("value"), phone, "phone mismatch");
		assertEquals(this.fax.getAttribute("value"), fax, "fax mismatch");
		assertEquals(this.email.getAttribute("value"), email, "email mismatch");
		Select prefLang = new Select(preferredLanguage);
		assertEquals(prefLang.getFirstSelectedOption().getText(), preferredContactLanguage, preferredContactLanguage + " is not selected");
	}
	
	public void setTaxVatInformation(int taxType, String taxName, String flatFree, String units) throws Exception{
		waitForElementVisibility(expandAllLink);
		waitForElementVisibility(collapseAllLink);
		clickOn(taxVatInfo_Row);
		waitForElementVisibility(taxId);
		waitForElementVisibility(taxVatInfo_DeleteRowBtn);
		clickOn(taxVatInfo_AddRowBtn);
		waitForElementVisibility(this.taxType);
		selectByIndex(this.taxType, taxType);
		typeInText(this.taxName, taxName);
		waitForElementVisibility(taxVat_StartDate);
		waitForElementVisibility(taxVat_EndDate);
		waitForElementVisibility(percent);
		typeInText(this.flatFree, flatFree);
		selectByValue(this.units, units);
		waitForElementVisibility(comments);
		waitForElementVisibility(taxVatInfo_DeleteRowCheckBox);
	}
	
	public void setTaxVatInformation(String taxId, int taxType, String taxName, String taxVat_StartDate, String taxVat_EndDate, String percent, String flatFree, String units) throws Exception{
		clickOn(taxVatInfo_Row);
		typeInText(this.taxId, taxId);
		clickOn(taxVatInfo_AddRowBtn);
		waitForElementVisibility(this.taxType);
		selectByIndex(this.taxType, taxType);
		typeInText(this.taxName, taxName);
		typeInText(this.taxVat_StartDate, taxVat_StartDate);
		typeInText(this.taxVat_EndDate, taxVat_EndDate);
		typeInText(this.percent, percent);
		typeInText(this.flatFree, flatFree);
		selectByValue(this.units, units);
	}
	
	public void editTaxVatInformation(String taxId, int taxType, String taxName, String taxVat_StartDate, String taxVat_EndDate, String percent, String flatFree, String units) throws Exception{
		clickOn(taxVatInfo_Row);
		typeInText(this.taxId, taxId);
		waitForElementVisibility(this.taxType);
		selectByIndex(this.taxType, taxType);
		typeInText(this.taxName, taxName);
		typeInText(this.taxVat_StartDate, taxVat_StartDate);
		typeInText(this.taxVat_EndDate, taxVat_EndDate);
		typeInText(this.percent, percent);
		typeInText(this.flatFree, flatFree);
		selectByValue(this.units, units);
	}
	
	public void verifyTaxVatInformation(String taxId, int taxType, String taxName, String taxVat_StartDate, String taxVat_EndDate, String percent, String flatFree, String units) throws Exception{
		clickOn(taxVatInfo_Row);
		assertEquals(this.taxId.getAttribute("value"), taxId, "taxId mismatch");
		waitForElementVisibility(this.taxType);
		Select thisTaxType = new Select(this.taxType);
		WebElement option = thisTaxType.getFirstSelectedOption();
		assertEquals(option.getText(), thisTaxType.getOptions().get(taxType).getAttribute("value"), "taxType mismatch");
		assertEquals(this.taxName.getAttribute("value"), taxName, "taxVatName mismatch");
		assertEquals(this.taxVat_StartDate.getAttribute("value"), taxVat_StartDate, "taxVat_StartDate mismatch");
		assertEquals(this.taxVat_EndDate.getAttribute("value"), taxVat_EndDate, "taxVat_EndDate mismatch");
		assertEquals(this.percent.getAttribute("value"), percent, "percent mismatch");
		assertEquals(this.flatFree.getAttribute("value"), flatFree, "FlatFree mismatch");
		Select thisUnits = new Select(this.units);
		WebElement unit = thisUnits.getFirstSelectedOption();
		assertEquals(unit.getText(), units, units + " is not selected");
	}
	
	public void saveDetails(){
		waitForElementVisibility(saveBtn);
	}
	
	public void clickSaveDetails() throws IOException{
		clickOn(saveBtn);
		takeScreenshots();
		unExpectedAcceptAlert();
	}
}
