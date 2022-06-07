package com.APIHotels.pages.ACESII;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ManageVenues extends BasePage {

	@FindBy(id = "strCityName")
	private WebElement city;

	@FindBy(name = "searchValue")
	private WebElement searchBtn;

	@FindBy(name = "addVenueGroupRowButton")
	private WebElement venueGroup_AddRowBtn;

	@FindBy(name = "deleteVenueGroupRowButton")
	private WebElement venueGroup_DeleteRowBtn;

	@FindBy(id = "venueGroup[0].venueGroupValue")
	private WebElement venueGroupNo;

	@FindBy(id = "venueGroup[0].venueGroupName")
	private WebElement groupName;

	@FindBy(id = "venueGroup[0].venueGroupDisplayName")
	private WebElement groupDisplayName;

	@FindBy(id = "venueGroupDeleted0")
	private WebElement venueGroup_DeleteRowCheckBox;

	@FindBy(xpath = "//*[@onclick = 'addVenueAddressRow()' and @value = 'Add']")
	private WebElement venues_AddRowBtn;

	@FindBy(xpath = "//*[@onclick = 'deleteVenueAddress()' and @value = 'Delete']")
	private WebElement venues_DeleteRowBtn;

	@FindBy(id = "venueAddress[0].venueCode")
	private WebElement venueCode;

	@FindBy(id = "venueAddress[0].venueName")
	private WebElement venueName;

	@FindBy(id = "venueAddress[0].venueGroupNumber")
	private WebElement venueGroupIndex;

	@FindBy(name = "venueAddress[0].street")
	private WebElement street;

	@FindBy(name = "venueAddress[0].city")
	private WebElement venueCity;

	@FindBy(name = "venueAddress[0].state")
	private WebElement state;

	@FindBy(name = "venueAddress[0].zipcode")
	private WebElement zip;

	@FindBy(name = "venueAddress[0].deleted")
	private WebElement venue_DeleteRowCheckBox;

	@FindBy(name = "addVenueGroupMergeRuleRowButton")
	private WebElement venueGroupMergeRule_AddRowBtn;

	@FindBy(xpath = "//*[@onclick = 'deleteVenueGroupMergeRule()' and @value = 'Delete']")
	private WebElement venueGroupMergeRule_DeleteRowBtn;

	@FindBy(id = "venueGroupMergeRule[0].direction")
	private WebElement direction;

	@FindBy(name = "venueGroupMergeRule[0].venueGroupNumberList")
	private List<WebElement> groupNumbers;

	@FindBy(id = "venueGroupMergeRuleDeleted0")
	private WebElement venueGroupMergeRule_DeleteRowCheckBox;

	@FindBy(id = "alertOK")
	private WebElement alert_OkBtn;

	@FindBy(id = "alertCancel")
	private WebElement alert_CancelBtn;

	@FindBy (xpath = "//*[@name= 'manageVenuesForm']/div[3]/input[@value = 'Save']")
	private WebElement saveBtn;

	public EventFiringWebDriver driver = null;

	public Page_ManageVenues(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchVenues(String city) throws Exception {
		typeInText(this.city, city);
		clickOn(searchBtn);
	}

	public void addVenueGroups(String groupName, String groupDisplayName) throws Exception {
		clickOn(venueGroup_AddRowBtn);
		waitForElementVisibility(venueGroupNo);
		typeInText(this.groupName, groupName);
		typeInText(this.groupDisplayName, groupDisplayName);
	}

	public void addVenues(String code, String venueName, int groupIndex) throws Exception {
		clickOn(venues_AddRowBtn);
		typeInText(venueCode, code);
		selectByIndex(venueGroupIndex, groupIndex);
		waitForElementVisibility(street);
		waitForElementVisibility(venueCity);
		waitForElementVisibility(state);
		waitForElementVisibility(zip);
	}

	public void addVenueGroupMergeRule(int direction, String groupNumbers) throws Exception {
		clickOn(venueGroupMergeRule_AddRowBtn);
		selectByIndex(this.direction, direction);
		for (String groupNumber : groupNumbers.split(","))
			for (WebElement groupNo : this.groupNumbers)
				if (groupNo.getAttribute("value").equals(groupNumber)) {
					clickOn(groupNo);
					break;
				}
	}

	public void deleteVenueGroups() throws Exception {
		clickOn(venueGroup_DeleteRowCheckBox);
		clickOn(venueGroup_DeleteRowBtn);
		handleAlertBox();
	}

	public void deleteVenues() throws Exception {
		clickOn(venue_DeleteRowCheckBox);
		clickOn(venues_DeleteRowBtn);
		handleAlertBox();
	}

	public void deleteVenueGroupMergeRule() throws Exception {
		clickOn(venueGroupMergeRule_DeleteRowCheckBox);
		clickOn(venueGroup_DeleteRowBtn);
		handleAlertBox();
	}

	private void handleAlertBox() throws Exception {
		waitForElementVisibility(alert_OkBtn);
		clickOn(alert_CancelBtn);
	}

	public void saveDetails() {
		waitForElementVisibility(saveBtn);
	}
	
	public void clickOnSaveButton() {
		clickOn(saveBtn);
	}

	String groupXpath = "//input[@id='venueGroup[";
	String groupNumberXpathStart = "//td[@id='venueGroup[";
	String groupNumberXpathEnd = "].venueGroupValue']";
	String groupNameXpathEnd = "].venueGroupName']";
	String groupDisplayNameEnd = "].venueGroupDisplayName']";
	String venueCodeXpathStart = "//input[@id='venueAddress[";
	String venueCodeXpathEnd = "].venueCode']";
	String venueNameXpathEnd = "].venueName']";
	String venueGroupXpathStart = "//select[@id='venueAddress[";
	String venueGroupXpathEnd = "].venueGroupNumber']";
	String venueStreetXpathEnd = "].street']";
	String venueCityXpathEnd = "].city']";
	String venueStateXpathEnd = "].state']";
	String venueZipCodeXpathEnd = "].zipcode']";

	@FindBy(xpath = "//input[@onclick='addVenueAddressRow()']/parent::div/following-sibling::table//tr")
	private List<WebElement> tablelist;

	@FindBy(xpath = "//input[@onclick='addVenueGroupMergeRuleRow()']/parent::div/following-sibling::table//tr[2]/td[2]/input")
	private List<WebElement> inputcount;
	@FindBy(xpath = "//input[@onclick='addVenueGroupMergeRuleRow()']/parent::div/following-sibling::table//tr[2]/td[1]/select")
	private WebElement directionsDropdown;

	String groupCheckboxXpath1 = "//input[@onclick='addVenueGroupMergeRuleRow()']/parent::div/following-sibling::table//tr[2]/td[2]/input[@value='";
	String groupCheckboxXpath2 = "']";

	public void editVenueGroups(int groupNumber, String groupNameVal, String groupDisplayNameVal) {

		int groupId = groupNumber - 1;

		String groupNumberValue = getTextOfElement(
				findElementByXpath(groupNumberXpathStart + groupId + groupNumberXpathEnd));
		System.out.println(groupNumberValue);

		String groupNameValue = findElementByXpath(groupXpath + groupId + groupNameXpathEnd).getAttribute("value");
		System.out.println(groupNameValue);
		if (!groupNameValue.equals(groupNameVal))
			typeInText(findElementByXpath(groupXpath + groupId + groupNameXpathEnd), groupNameVal);

		String groupDisplayNameValue = findElementByXpath(groupXpath + groupId + groupDisplayNameEnd)
				.getAttribute("value");
		System.out.println(groupDisplayNameValue);
		if (!groupDisplayNameValue.equals(groupDisplayNameVal))
			typeInText(findElementByXpath(groupXpath + groupId + groupDisplayNameEnd), groupDisplayNameVal);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editVenues(String venueCodeVal, String venuNameVal, String groupNameVal, String venuStreetVal,
			String venuCityVal) {
		int tablelistsize = tablelist.size() - 2;
		for (int i = 0; i < tablelistsize; i++) {
			String tst = driver.findElement(By.xpath("//input[@id='venueAddress[" + i + "].venueCode']"))
					.getAttribute("value");
			System.out.println("Row number " + i + " value is : " + tst);
			if (tst.equals(venueCodeVal)) {
				typeInText(findElementByXpath(venueCodeXpathStart + i + venueNameXpathEnd), venuNameVal);
				selectByVisibleText(findElementByXpath(venueGroupXpathStart + i + venueGroupXpathEnd), groupNameVal);
				typeInText(findElementByXpath(venueCodeXpathStart + i + venueStreetXpathEnd), venuStreetVal);
				typeInText(findElementByXpath(venueCodeXpathStart + i + venueCityXpathEnd), venuCityVal);
				break;

			}

		}
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editVenueGroupMergeDetails(String directionVal, String groupvalues) {
		selectByVisibleText(directionsDropdown, directionVal);
		System.out.println("Group size is : " + inputcount.size());
		for (int j = 1; j <= inputcount.size(); j++) {
			if (findElementByXpath(groupCheckboxXpath1 + j + groupCheckboxXpath2).isSelected())
				clickOn(findElementByXpath(groupCheckboxXpath1 + j + groupCheckboxXpath2));
		}
		int test = 0;
		List<String> inputsList = Arrays.asList(groupvalues.split(","));

		for (int i = 1; i <= inputsList.size(); i++) {
			clickOn(findElementByXpath(groupCheckboxXpath1 + inputsList.get(test) + groupCheckboxXpath2));
			test++;
		}
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editVenues(String venueCodeVal, String groupNameVal) {
		List<String> venueCodes= Arrays.asList(venueCodeVal.split(","));
		int tablelistsize = tablelist.size() - 2;
		int j=0;
		for (int i = 0; i < tablelistsize; i++) {
			String tst = driver.findElement(By.xpath("//input[@id='venueAddress[" + i + "].venueCode']"))
					.getAttribute("value");
			System.out.println("Row number " + i + " value is : " + tst);
			if(j<2){
			if (tst.equals(venueCodes.get(j))) {
				//typeInText(findElementByXpath(venueCodeXpathStart + i + venueNameXpathEnd), venuNameVal);
				selectByVisibleText(findElementByXpath(venueGroupXpathStart + i + venueGroupXpathEnd), "Group "+groupNameVal);
				//typeInText(findElementByXpath(venueCodeXpathStart + i + venueStreetXpathEnd), venuStreetVal);
				//typeInText(findElementByXpath(venueCodeXpathStart + i + venueCityXpathEnd), venuCityVal);
				//break;
				j++;
			}
			}

		}
		try {
			takeScreenshots();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editVenueGroups(String groupNumberInVenueGroup, String groupNameVal, String groupDisplayNameVal) {

		int groupId = Integer.parseInt(groupNumberInVenueGroup) - 1;

		String groupNumberValue = getTextOfElement(
				findElementByXpath(groupNumberXpathStart + groupId + groupNumberXpathEnd));
		System.out.println(groupNumberValue);

		String groupNameValue = findElementByXpath(groupXpath + groupId + groupNameXpathEnd).getAttribute("value");
		System.out.println(groupNameValue);
		if (!groupNameValue.equals(groupNameVal))
			typeInText(findElementByXpath(groupXpath + groupId + groupNameXpathEnd), groupNameVal);

		String groupDisplayNameValue = findElementByXpath(groupXpath + groupId + groupDisplayNameEnd)
				.getAttribute("value");
		System.out.println(groupDisplayNameValue);
		if (!groupDisplayNameValue.equals(groupDisplayNameVal))
			typeInText(findElementByXpath(groupXpath + groupId + groupDisplayNameEnd), groupDisplayNameVal);
		try {
			takeScreenshots();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
