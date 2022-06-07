package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;

public class Page_CommonHotelGeneralInfo extends BasePage{

	@FindBy(id = "locCD")
	private WebElement city;
	
	@FindBy(id = "startDate0")
	private WebElement startDate;
	
	@FindBy(id = "endDate0")
	private WebElement endDate;
	
	@FindBy(id = "scheduleName0")
	private WebElement hotelName;
	
	@FindBy(id = "gtScheduleName0")
	private WebElement gtScheduleName;
	
	@FindBy(id = "delId0")
	private WebElement supplierDetails_DeleteCheckBox;
	
	@FindBy(xpath = "//*[@onclick = 'addSupplierRow();ACES_Modified();' and @value = 'Add' and @class = 'Aces_Btn_Add']")
	private WebElement supplierDetails_AddBtn;
	
	@FindBy(xpath = "//*[@onclick = 'deletSuppliereRow();', and @value = 'Delete', and @class = 'Aces_Btn_Add']")
	private WebElement supplierDetails_DeleteBtn;
	
	@FindBy(name = "address1")
	private WebElement address1;
	
	@FindBy(name = "address2")
	private WebElement address2;
	
	@FindBy(name = "address3")
	private WebElement address3;
	
	@FindBy(id = "city")//name for GT
	private WebElement supplierCity;
	
	@FindBy(id = "provstCD")//name for GT
	private WebElement state;
	
	@FindBy(id = "postCD")//name is postCD for GT
	private WebElement zip;
	
	@FindBy(id = "countryCdId")
	private WebElement country; //SelectByValue: US->United States Of America
	
	@FindBy(id = "note1")
	private WebElement generalNotes;
	
	@FindBy(xpath = "//*[contains(@id,'tenantTypeId')]")
	private WebElement clients; //selectByValue: 1->Airline Supplier; 2->Cruiseline Supplier
	
	@FindBy(name = "locType")
	private List<WebElement> locationType; 	//getAttribute value: AIRPORT, DOWNTOWN, OTHER
	
	String locationTypeXpath1 = "//*[@name = 'locType' and @value = '";
	String locationTypeXpath2 = "']";
	
	@FindBy(name = "apiSupplierNo")
	private WebElement apiSupplierNo;
	
	@FindBy(xpath = "//*[@onclick = 'addContactInformationRow();ACES_Modified();' and @class = 'Aces_Btn_Add' and @value = 'Add']")
	private WebElement contactInfo_AddBtn;
	
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
	private WebElement contactDetails_DeleteCheckBox;
	
	@FindBy(xpath = "//*[@onclick = 'deletSupplierContactRow();' and @class = 'Aces_Btn_Add' and @value = 'Delete']")
	private WebElement contactDetails_DeleteBtn;
	
	@FindBy(name = "preferredLanguage")
	private WebElement preferredContactLanguage; // selectByVisibleText English for testing
	
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
	
	@FindBy(xpath = "//*[@id = 'inc1']/div/div[3]/button[2]")
	private WebElement taxVatInfo_DelRowBtn;
	
	@FindBy(xpath = "//*[@id='taxType0']")
	private WebElement taxType;
	
	@FindBy(id = "vatname0")
	private WebElement taxVatName;
	
	@FindBy(id = "startDatetax0")
	private WebElement taxVat_StartDate;
	
	@FindBy(id = "endDatetax0")
	private WebElement taxVat_EndDate;
	
	@FindBy(id = "Dummyexemption0")
	private WebElement exemptIndicator;
	
	@FindBy(id = "exemptionDays0")
	private WebElement exemptAfterDays;
	
	@FindBy(id = "percent0")
	private WebElement percent;
	
	@FindBy(id = "flatFee0")
	private WebElement flatFree;
	
	@FindBy(id = "currencCd0")
	private WebElement units; //SelectByValue;
	
	@FindBy(id = "Dummyrefund0")
	private WebElement refundIndicator;
	
	@FindBy(name = "comments")
	private WebElement comments;
	
	@FindBy(id = "delTax0")
	private WebElement deleteTaxRowCheckBox;


	@FindBy(xpath = "//*[@id = 'buttonFlag' and @value = 'Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//*[@name = 'buttonFlag' and @value = 'Save']")
	private WebElement saveBtn2;
	
	@FindBy(xpath = "//*[@onclick = 'deleteCommonSupplier()' and @name = 'delete']")
	private WebElement deleteBtn;
	
	@FindBy(id = "alertOK")
	private WebElement alertOkBtn;
	
	public EventFiringWebDriver driver=null;

	public Page_CommonHotelGeneralInfo(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setSupplierName(String city, String hotelName) throws Exception{
		typeInText(this.city, city);
		waitForElementVisibility(supplierDetails_AddBtn);
		waitForElementVisibility(startDate);
		waitForElementVisibility(endDate);
		typeInText(this.hotelName, hotelName);
		waitForElementVisibility(gtScheduleName);
		waitForElementVisibility(supplierDetails_DeleteCheckBox);
	}
	
	public void verifySupplierName(String city, String hotelName)throws Exception{
		assertEquals(this.city.getAttribute("value"), city, "city Mismatch");
		assertEquals(this.hotelName.getAttribute("value"), hotelName, "Hotel Name Mismatch");
	}
	
	public void setGeneralDetails(String countryValue, String locationType, String client) throws Exception{
		waitForElementVisibility(address1);
		waitForElementVisibility(address2);
		waitForElementVisibility(address3);
		waitForElementVisibility(supplierCity);
		waitForElementVisibility(state);
		waitForElementVisibility(zip);
		selectByValue(country, countryValue);
		for(WebElement locType : this.locationType)
			if(locType.getAttribute("value").equals(locationType))
			{
				clickOn(locType);
				break;
			}
		selectByValue(clients, client);
		waitForElementVisibility(apiSupplierNo);
	}
	
	public void verifyGeneralDetails(String countryValue, String locationType, String client) throws Exception{
		Select thisCountry = new Select(country);
		WebElement option = thisCountry.getFirstSelectedOption();
		assertEquals(option.getAttribute("value"), countryValue, "Country value mismatch");
		WebElement thisLocationType = findElementByXpath(locationTypeXpath1 + locationType + locationTypeXpath2);
		assertTrue(thisLocationType.isSelected(), locationType +" is not selected");
		Select thisClient = new Select(clients);
		WebElement clientSelected = thisClient.getFirstSelectedOption();
		assertEquals(clientSelected.getAttribute("value"), client, client+" is not selected");
	}
	
	public void setContactInformation(String name, String preferredContactLanguage) throws Exception{
		waitForElementVisibility(contactDetails_DeleteBtn);
		clickOn(contactInfo_AddBtn);
		waitForElementVisibility(dept_Title);
		typeInText(this.name, name);
		waitForElementVisibility(phone);
		waitForElementVisibility(fax);
		waitForElementVisibility(email);
		waitForElementVisibility(contactDetails_DeleteCheckBox);
		selectByVisibleText(this.preferredContactLanguage, preferredContactLanguage);
	}
	
	public void setContactInformation(String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage) throws Exception{
		waitForElementVisibility(contactDetails_DeleteBtn);
		clickOn(contactInfo_AddBtn);
		typeInText(this.dept_Title, deptTitle);
		typeInText(this.name, name);
		typeInText(this.phone, phone);
		typeInText(this.fax, fax);
		typeInText(this.email, email);
		selectByVisibleText(this.preferredContactLanguage, preferredContactLanguage);
	}
	
	public void editContactInformation(String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage) throws Exception{
		typeInText(this.dept_Title, deptTitle);
		typeInText(this.name, name);
		typeInText(this.phone, phone);
		typeInText(this.fax, fax);
		typeInText(this.email, email);
		selectByVisibleText(this.preferredContactLanguage, preferredContactLanguage);
	}
	
	public void verifyContactInformation(String deptTitle, String name, String phone, String fax, String email, String preferredContactLanguage) throws Exception{
		assertEquals(this.dept_Title.getAttribute("value"), deptTitle, "dept/title mismatch");
		assertEquals(this.name.getAttribute("value"), name, "name mismatch");
		assertEquals(this.phone.getAttribute("value"), phone, "phone mismatch");
		assertEquals(this.fax.getAttribute("value"), fax, "fax mismatch");
		assertEquals(this.email.getAttribute("value"), email, "email mismatch");
		Select preferredLanguage = new Select(this.preferredContactLanguage);
		WebElement prefLang = preferredLanguage.getFirstSelectedOption();
		assertEquals(prefLang.getText(), preferredContactLanguage, preferredContactLanguage + " not selected");
	}
	
	public void setTaxVatInformation(int taxType, String taxVatName, String exemptAfterDays, String flatFree, String units) throws Exception{
		waitForElementVisibility(expandAllLink);
		waitForElementVisibility(collapseAllLink);
		clickOn(taxVatInfo_Row);
		waitForElementVisibility(taxId);
		waitForElementVisibility(taxVatInfo_DelRowBtn);
		clickOn(taxVatInfo_AddRowBtn);
		waitForElementVisibility(this.taxType);
		selectByIndex(this.taxType, taxType);
		typeInText(this.taxVatName, taxVatName);
		waitForElementVisibility(taxVat_StartDate);
		waitForElementVisibility(taxVat_EndDate);
		clickOn(exemptIndicator);
		typeInText(this.exemptAfterDays, exemptAfterDays);
		waitForElementVisibility(percent);
		typeInText(this.flatFree, flatFree);
		selectByValue(this.units, units);
		waitForElementVisibility(refundIndicator);
		waitForElementVisibility(comments);
		waitForElementVisibility(deleteTaxRowCheckBox);
	}
	
	public void setTaxVatInformation(String taxId, int taxType, String taxVatName, String taxVat_StartDate, String taxVat_EndDate, String exemptAfterDays, String percent, String flatFree, String units) throws Exception{
		clickOn(taxVatInfo_Row);
		typeInText(this.taxId, taxId);
		clickOn(taxVatInfo_AddRowBtn);
		waitForElementVisibility(this.taxType);
		selectByIndex(this.taxType, taxType);
		typeInText(this.taxVatName, taxVatName);
		typeInText(this.taxVat_StartDate, taxVat_StartDate);
		typeInText(this.taxVat_EndDate, taxVat_EndDate);
		clickOn(exemptIndicator);
		typeInText(this.exemptAfterDays, exemptAfterDays);
		typeInText(this.percent, percent);
		typeInText(this.flatFree, flatFree);
		selectByValue(this.units, units);
		clickOn(refundIndicator);
	}
	
	public void editTaxVatInformation(String taxId, int taxType, String taxVatName, String taxVat_StartDate, String taxVat_EndDate, String exemptAfterDays, String percent, String flatFree, String units) throws Exception{
		clickOn(taxVatInfo_Row);
		typeInText(this.taxId, taxId);
		waitForElementVisibility(this.taxType);
		selectByIndex(this.taxType, taxType);
		typeInText(this.taxVatName, taxVatName);
		typeInText(this.taxVat_StartDate, taxVat_StartDate);
		typeInText(this.taxVat_EndDate, taxVat_EndDate);
		if(!exemptAfterDays.isEmpty()){
			if(!exemptIndicator.isSelected())
				clickOn(exemptIndicator);
		}else{
			if(exemptIndicator.isSelected())
				clickOn(exemptIndicator);
		}
			
		typeInText(this.exemptAfterDays, exemptAfterDays);
		typeInText(this.percent, percent);
		typeInText(this.flatFree, flatFree);
		selectByValue(this.units, units);
		clickOn(refundIndicator);
	}
	
	public void verifyTaxVatInformation(String taxId, int taxType, String taxVatName, String taxVat_StartDate, String taxVat_EndDate, String exemptAfterDays, String percent, String flatFree, String units) throws Exception{
		clickOn(taxVatInfo_Row);
		assertEquals(this.taxId.getAttribute("value"), taxId);
		waitForElementVisibility(this.taxType);
		Select thisTaxType = new Select(this.taxType);
		WebElement option = thisTaxType.getFirstSelectedOption();
		assertEquals(option.getText(), thisTaxType.getOptions().get(taxType).getAttribute("value"), "taxType mismatch");
		assertEquals(this.taxVatName.getAttribute("value"), taxVatName, "taxVatName mismatch");
		assertEquals(this.taxVat_StartDate.getAttribute("value"), taxVat_StartDate, "taxVat_StartDate mismatch");
		assertEquals(this.taxVat_EndDate.getAttribute("value"), taxVat_EndDate, "taxVat_EndDate mismatch");
		if(!exemptAfterDays.isEmpty())
			assertTrue(exemptIndicator.isSelected(), "Exempt indicator not selected");
		assertEquals(this.exemptAfterDays.getAttribute("value"), exemptAfterDays, "ExemptAfterDays mismatch");
		assertEquals(this.percent.getAttribute("value"), percent, "percent mismatch");
		assertEquals(this.flatFree.getAttribute("value"), flatFree, "FlatFree mismatch");
		Select thisUnits = new Select(this.units);
		WebElement unit = thisUnits.getFirstSelectedOption();
		assertEquals(unit.getText(), units, units + " is not selected");
		assertTrue(refundIndicator.isSelected(), "RefundIndicator not selected");
	}
	
	
	public void saveDetails(){
		waitForElementVisibility(saveBtn);
	}
	
	public void clickSaveDetails() throws IOException{
		clickOn(saveBtn);
		unExpectedAcceptAlert();
		takeScreenshots();
		clickOn(alertOkBtn);
	}
	
	public void clickSaveDetails_New() throws IOException{
		clickOn(saveBtn2);
		unExpectedAcceptAlert();
		takeScreenshots();
		clickOn(alertOkBtn);
	}
	
	public void deleteDetails(){
		waitForElementVisibility(deleteBtn);
	}
	
}

