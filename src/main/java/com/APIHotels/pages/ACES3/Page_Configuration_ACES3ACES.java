package com.APIHotels.pages.ACES3;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;

public class Page_Configuration_ACES3ACES extends BasePage {

	@FindBy(linkText = "Configuration")
	private WebElement tab_Configuration;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Configure Tenant Features')]")
	WebElement ConfigureTenantFeaturesLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Configure Supplier Features')]")
	WebElement ConfigureSupplierFeaturesLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Configure Sign-In Sheets')]")
	WebElement ConfigureSignInSheetsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Configure Notifications')]")
	WebElement ConfigureNotificationsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Configure EDrive')]")
	WebElement ConfigureEDriveLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Configure Consolidated E-Invoice')]")
	WebElement ConfigureConsolidatedEInvoiceLink;

	@FindBy(xpath = "//a[text()='Configure Sign-In Sheets']")
	private WebElement tab_Configure_SignInSheets;

	@FindBy(xpath = "//label[@id='form1:tenantSelector_label']")
	private WebElement select_Tenant;

	@FindBy(xpath = "//ul[@id='form1:tenantSelector_items']/li")
	private WebElement select_TenantList;

	@FindBy(xpath = "//div[@class='ui-radiobutton-box ui-widget ui-corner-all ui-state-default ui-state-active']//span[@class='ui-radiobutton-icon ui-icon ui-icon-bullet ui-c']")
	private WebElement signInSheetConfigYes;

	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;

	String selectListItemXpath1 = "//ul[@id='form1:tenantSelector_items']/li[text()='";
	String selectListItemXpath2 = "']";

	@FindBy(xpath = "//label[@id='configTenantForm:tenantSelector_label']")
	private WebElement select_TenantConfigTenantPage;

	@FindBy(xpath = "//ul[@id='configTenantForm:tenantSelector_items']/li")
	private WebElement select_TenantConfigTenantPageList;

	String selectTenantXPath = "//ul[@id='configTenantForm:tenantSelector_items']/li[text()='";

	@FindBy(xpath = "//a[contains(text(),'Workflows')]")
	private WebElement tab_Workflows;

	@FindBy(xpath = "//li[contains(text(),'Nota Fiscal')]")
	private WebElement option_NotaFiscal;

	@FindBy(xpath = "//li[contains(text(),'Reference Number')]")
	private WebElement option_ReferenceNumber;

	@FindBy(xpath = "//li[contains(text(),'Invoice Export')]")
	private WebElement option_InvoiceExport;

	@FindBy(xpath = "//li[contains(text(),'Venc Date')]")
	private WebElement option_VencDate;

	@FindBy(xpath = "//li[contains(text(),'Auto Accept Payment Group')]")
	private WebElement option_AutoAcceptPaymentGroup;

	@FindBy(xpath = "//li[contains(text(),'Accept/Reject Payment Group')]")
	private WebElement option_AcceptRejectPaymentGroup;

	@FindBy(xpath = "//li[contains(text(),'Enter Paid Date')]")
	private WebElement option_EnterPaidDate;

	@FindBy(xpath = "//ul[contains(@id,'dragDropBoxContainer:available')]")
	private WebElement availableListContainer;

	@FindBy(xpath = "//ul[contains(@id,'dragDropBoxContainer:selected')]")
	private WebElement selectedListContainer;

	@FindBy(xpath = "//ul[contains(@id,'dragDropBoxContainer:selected')]/li")
	private List<WebElement> listofSelectedElements;

	@FindBy(xpath = "//ul[contains(@id,'dragDropBoxContainer:available')]/li")
	private List<WebElement> listofAvailableElements;

	@FindBy(xpath = "//ul[contains(@id,'dragDropBoxContainer:selected')]/li[1]")
	private WebElement selectedListContainerElements;

	String listXPath1 = "//ul[contains(@id,'dragDropBoxContainer:available')]/li[contains(text(),'";
	String listXPath2 = "')]";

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	private WebElement btn_SaveConfigs;

	@FindBy(how = How.XPATH, using = "//li[@id='configTenantForm:tenantSelector_1']")
	WebElement selectList_Tenant;

	@FindBy(how = How.XPATH, using = "//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
	WebElement dropdown_Tenant;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Activate Online Sign-in Sheets?')]/parent::div/div/span")
	WebElement checkbox_ActivateOnlineSignInSheets;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Activate e-Invoices for this tenant?')]/parent::div/div/span")
	WebElement checkbox_ActivateEInvoiceForTenant;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Settings')]")
	WebElement link_Settings;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Workflows')]")
	WebElement link_Workflows;

	@FindBy(how = How.XPATH, using = "//li[@class='ui-state-default ui-corner-left']//a[contains(text(),'Notifications')]")
	WebElement link_Notifications;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Save')]")
	WebElement btn_Save;

	@FindBy(how = How.XPATH, using = "//div[@id='form1:tenantSelector']/parent::td/following-sibling::td/span")
	WebElement textbox_ManageSuppFeaturesCity;

	@FindBy(how = How.XPATH, using = "//label[@id='form1:supplierSelector_label']")
	WebElement select_ManageSuppFeaturesSupplier;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Notification Configuration')]")
	WebElement tab_NotificationConfiguration;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'notifConfigForm:notificationSelection_label')]")
	WebElement select_notificatonType;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Email Contacts')]")
	WebElement tab_EmailContacts;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'tenant_label')]")
	WebElement select_TenantDropdown;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'City:')]/parent::span/span")
	WebElement textbox_CityNotificationPage;

	@FindBy(how = How.XPATH, using = "//label[contains(@id,'supplierSelector_label')]")
	WebElement select_SupplierDropdown;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'edriveSupplierConfigSheet')]")
	WebElement select_edriveSupplierConfigSheet;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'dragDropBoxContainer:available')]")
	WebElement available_ConsolidatedEInoviceSection;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'dragDropBoxContainer:selected')]")
	WebElement selected_ConsolidatedEInoviceSection;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Zero when missing')]/parent::td/div/div/span")
	WebElement radiobtn_ZeroWhenMissing;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'N/A when missing')]/parent::td/div/div/span")
	WebElement radiobtn_NAWhenMissing;

	@FindBy(how = How.XPATH, using = "//select[@id='configEDriveForm:tenantSelector']")
	WebElement select_TenantConfg;

	@FindBy(xpath = "//label[contains(@id,'tenant_label')]")
	WebElement selectAirline;

	@FindBy(xpath = "//ul[contains(@id,'tenant_items')]/li")
	WebElement airlinesList;

	String tenantXpath1 = "//*[contains(@id, 'tenant_items')]/li[contains(text(), '";
	String tenantXpath2 = "')]";

	@FindBy(xpath = "//*[contains(@id,'dragDropBoxContainer:selected')]/div")
	private List<WebElement> selectedColumnsCount;

	String selectedColumnNamesXpath1 = "//*[contains(@id,'dragDropBoxContainer:selected')]/div[";
	String selectedColumnNamesXpath2 = "]/span";

	public EventFiringWebDriver driver = null;

	public Page_Configuration_ACES3ACES(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnConfigureTenantFeaturesLink() {
		clickOn(ConfigureTenantFeaturesLink, "Configuration Menu -> Configure Tenant Features Link");
	}

	public void clickOnConfigureSupplierFeaturesLink() {
		clickOn(ConfigureSupplierFeaturesLink, "Configuration Menu -> Configure Supplier Features Link");
	}

	public void clickOnConfigureSignInSheetsLink() {
		clickOn(ConfigureSignInSheetsLink, "Configuration Menu -> Configure Sign-In Sheets Link");
	}

	public void clickOnConfigureNotificationsLink() {
		clickOn(ConfigureNotificationsLink, "Configuration Menu -> Configure Notifications Link");
	}

	public void clickOnConfigureEDriveLink() {
		clickOn(ConfigureEDriveLink, "Configuration Menu -> Configure E-Drive Link");
	}

	public void clickOnConfigureConsolidatedEInvoiceLink() {
		clickOn(ConfigureConsolidatedEInvoiceLink, "Configuration Menu -> Configure Consolidated E Invoice Link");
	}

	public void clickOnConfigureSignInSheetLink() {
		clickOn(tab_Configure_SignInSheets, "Configuration Menu -> Configure Sign-In Sheets Link");

	}

	public void selectTenant(String tenantName) {
		clickOn(select_Tenant, "Configure Sign-In Sheets Page -> Airline Select");
		waitForElementVisibility(select_TenantList);
		clickOn(findElementByXpath(selectListItemXpath1 + tenantName + selectListItemXpath2),
				"Configure Sign-In Sheets Page -> Airline Selected From Suggestion List");
		waitForElementToDisappear(spinner);
		verifyConfigurationIsEnabled();
	}

	public void verifyConfigurationIsEnabled() {
		if (signInSheetConfigYes.isEnabled() == true) {
			System.out.println("Sign-In Sheet configured is enable for the tenant");
		} else {
			System.out.println("Sign-In Sheet configured is not enable for tenant");
		}
	}

	public void clickOnConfiguration() {
		clickOn(tab_Configuration, "ACES 3 ACES Menu -> Configuration");
	}

	public void configWorkflow(String tenantName) throws Exception {
		clickOn(select_TenantConfigTenantPage, "Manage Tenant Features -> Tenant Select");
		waitForElementVisibility(select_TenantConfigTenantPageList);
		clickOn(findElementByXpath(selectTenantXPath + tenantName + selectListItemXpath2),
				"Manage Tenant Features -> Tenant Selected From Suggestion List");
		waitForElementToDisappear(spinner);
		clickOn(tab_Workflows, "Manage Tenant Features -> Workflow Tab");
		waitForElementToDisappear(spinner);
	}

	public void moveAvilableWorkflowConfiguration(WebElement element) throws Exception {
		Actions act = new Actions(driver);
		Action dragAndDrop = act.dragAndDrop(element, selectedListContainer).build();
		dragAndDrop.perform();
		waitForElementToDisappear(spinner);
		Thread.sleep(3000);
	}

	public void removeSelectedWorkflowConfiguration(WebElement element) throws Exception {
		Actions act = new Actions(driver);
		Action dragAndDrop = act.dragAndDrop(element, availableListContainer).build();
		dragAndDrop.perform();
		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
	}

	public void selectionOfConfigs(String workflowStep) throws Exception {
		List<WebElement> options = availableListContainer.findElements(By.tagName("li"));
		System.out.println("Count of the available options list :" + options.size());
		for (WebElement option : options) {
			if (option.getText().equals(workflowStep)) {
				switch (workflowStep) {
				case "Nota Fiscal":
					moveAvilableWorkflowConfiguration(option_NotaFiscal);
					break;
				case "Reference Number":
					moveAvilableWorkflowConfiguration(option_ReferenceNumber);
					break;
				case "Invoice Export":
					moveAvilableWorkflowConfiguration(option_InvoiceExport);
					break;
				case "Venc Date":
					moveAvilableWorkflowConfiguration(option_VencDate);
					break;
				case "Auto Accept Payment Group":
					moveAvilableWorkflowConfiguration(option_AutoAcceptPaymentGroup);
					break;
				case "Accept/Reject Payment Group":
					moveAvilableWorkflowConfiguration(option_AcceptRejectPaymentGroup);
					break;
				case "Enter Paid Date":
					moveAvilableWorkflowConfiguration(option_EnterPaidDate);
					break;
				default:
					System.out.println("This Option is already in Selected Contianer");
					break;
				}
			} else {
				System.out.println("Option is already in desired container list");
			}
		}
		Thread.sleep(5000);
	}

	public void deletionOfConfigs(String workflowStep) throws Exception {
		List<WebElement> options = selectedListContainer.findElements(By.tagName("li"));
		System.out.println("Count of the available options list :" + options.size());
		for (WebElement option : options) {
			if (option.getText().equals(workflowStep)) {
				switch (workflowStep) {
				case "Nota Fiscal":
					removeSelectedWorkflowConfiguration(option_NotaFiscal);
					break;
				case "Reference Number":
					removeSelectedWorkflowConfiguration(option_ReferenceNumber);
					break;
				case "Invoice Export":
					removeSelectedWorkflowConfiguration(option_InvoiceExport);
					break;
				case "Venc Date":
					removeSelectedWorkflowConfiguration(option_VencDate);
					break;
				case "Auto Accept Payment Group":
					removeSelectedWorkflowConfiguration(option_AutoAcceptPaymentGroup);
					break;
				case "Accept/Reject Payment Group":
					removeSelectedWorkflowConfiguration(option_AcceptRejectPaymentGroup);
					break;
				case "Enter Paid Date":
					removeSelectedWorkflowConfiguration(option_EnterPaidDate);
					break;
				default:
					System.out.println("This Option is already in Selected Contianer");
					break;
				}
			} else {
				System.out.println("Option is already in desired container list");
			}

		}
		Thread.sleep(3000);

	}

	public void removeAllSelected() throws Exception {
		int initSize = listofSelectedElements.size();
		for (int i = initSize; i > 0; i--) {
			removeSelectedWorkflowConfiguration(listofSelectedElements.get(i - 1));
		}
	}

	public void AddAvailableToSelected(String workflowStep) throws Exception {
		List<String> workflowSteps = new ArrayList<String>(Arrays.asList(workflowStep.split(",")));
		for (String step : workflowSteps) {
			moveAvilableWorkflowConfiguration(findElementByXpath(listXPath1 + step + listXPath2));
		}

		Thread.sleep(5000);
	}

	public void clickOnSaveConfigurationBtn() throws Exception {
		takeScreenshots();
		clickOn(btn_SaveConfigs, "Manage Tenant Features Page -> Save Button");
		Thread.sleep(5000);
		takeScreenshots();
	}

	public void clickOnLogout() {
		driver.findElement(By.xpath("//a[@id='menuForm:logout']")).click();
	}

	public void verifyElementsConfTenantFeaturesPage() {
		clickOn(dropdown_Tenant, "Manage Tenant Features -> Tenant Select");
		clickOn(selectList_Tenant, "Manage Tenant Features -> Tenant Selected");
		waitForElementToDisappear(spinner);
		waitForElementVisibility(checkbox_ActivateOnlineSignInSheets);
		waitForElementVisibility(checkbox_ActivateEInvoiceForTenant);
		waitForElementVisibility(link_Settings);
		waitForElementVisibility(link_Workflows);
		waitForElementVisibility(link_Notifications);
		waitForElementVisibility(btn_Save);

	}

	public void verifyElementsConfSupplierFeaturesPage() {
		waitForElementVisibility(select_Tenant);
		waitForElementVisibility(textbox_ManageSuppFeaturesCity);
		waitForElementVisibility(select_ManageSuppFeaturesSupplier);
	}

	public void verifyElementsConfSISPage() {
		waitForElementVisibility(select_Tenant);

	}

	public void verifyElementsConfNotificationsPage() {
		waitForElementVisibility(tab_NotificationConfiguration);
		waitForElementVisibility(select_notificatonType);
		waitForElementVisibility(tab_EmailContacts);
		clickOn(tab_EmailContacts, "Notification Configuration Page -> Email Contacts Tab");
		waitForElementVisibility(select_TenantDropdown);
		waitForElementVisibility(textbox_CityNotificationPage);
		waitForElementVisibility(select_SupplierDropdown);
		waitForElementVisibility(tab_NotificationConfiguration);
	}

	public void verifyElementsConfigEDrive() {
		waitForElementVisibility(select_TenantConfg);

	}

	public void verifyElementsConfigConsolidatedEInvoice() {
		waitForElementVisibility(select_edriveSupplierConfigSheet);
		waitForElementVisibility(available_ConsolidatedEInoviceSection);
		waitForElementVisibility(selected_ConsolidatedEInoviceSection);
		waitForElementVisibility(radiobtn_ZeroWhenMissing);
		waitForElementVisibility(radiobtn_NAWhenMissing);
		waitForElementVisibility(btn_SaveConfigs);
	}

	public List<String> getSelectedColumnsFromConfigureConsolidatedEInvoice(String tenantName) {
		waitForSpinnerToDisappear();
		clickOn(selectAirline);
		waitForElementVisibility(airlinesList);
		clickOn(findElementByXpath(tenantXpath1 + tenantName + tenantXpath2));
		waitForSpinnerToDisappear();
		List<String> columnNames = new ArrayList<String>();
		for (int i = 1; i <= selectedColumnsCount.size(); i++) {
			String column = findElementByXpath(selectedColumnNamesXpath1 + i + selectedColumnNamesXpath2).getText();
			System.out.println(column);
			columnNames.add(column);
		}
		return columnNames;
	}

	public void waitForSpinnerToDisappear() {
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 30);
		wait1.withTimeout(Duration.ofMinutes(30));
		wait1.pollingEvery(Duration.ofSeconds(2));
		wait1.ignoring(StaleElementReferenceException.class);
		wait1.until(ExpectedConditions.invisibilityOf(spinner));
	}

	public void arrangeTheOrder() throws IOException {

		takeScreenshots();
		Actions action = new Actions(driver);

		List<WebElement> list1 = driver.findElements(By.xpath("//td[2]//ul/li/span"));

		for (int i = 0; i < list1.size(); i++) {

			if (list1.get(i).getText().contains("Accept/Reject Payment Group")) {
				WebElement target = list1.get(i);
				WebElement dest = list1.get(0);
				if (!(list1.get(i) == list1.get(0)))
					action.click(target).clickAndHold().moveToElement(target, 1134, 570).release().build()
							.perform();
				waitForElementToDisappear(spinner);
			}

			if (list1.get(i).getText().contains("Enter Paid Date")) {
				WebElement target = list1.get(i);
				WebElement dest = list1.get(1);
				if (!(list1.get(i) == list1.get(1)))
					action.click(target).clickAndHold().moveToElement(target, 1134, 570).release().build()
							.perform();
				waitForElementToDisappear(spinner);
			}

			if (list1.get(i).getText().contains("Reference Number")) {
				WebElement target = list1.get(i);
				WebElement dest = list1.get(2);
				if (!(list1.get(i) == list1.get(2)))
					action.click(target).clickAndHold().moveToElement(target, 1134, 570).release().build()
							.perform();
				waitForElementToDisappear(spinner);
			}

			if (list1.get(i).getText().contains("Venc Date")) {
				WebElement target = list1.get(i);
				WebElement dest = list1.get(3);
				if (!(list1.get(i) == list1.get(3)))
					action.click(target).clickAndHold().moveToElement(target, 1134, 570).release().build()
							.perform();
				waitForElementToDisappear(spinner);
			}

			if (list1.get(i).getText().contains("Nota Fiscal")) {
				WebElement target = list1.get(i);
				WebElement dest = list1.get(4);
				if (!(list1.get(i) == list1.get(4)))
					action.click(target).clickAndHold().moveToElement(target, 1134, 570).release().build()
							.perform();
				waitForElementToDisappear(spinner);
			}
		}
	}
}
