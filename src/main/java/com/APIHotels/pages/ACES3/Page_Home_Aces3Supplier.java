package com.APIHotels.pages.ACES3;

import java.util.List;

import javax.annotation.Nullable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APIHotels.framework.BasePage;
import com.APIHotels.framework.ExtentTestManager;
import com.google.common.base.Predicate;
import com.relevantcodes.extentreports.LogStatus;

public class Page_Home_Aces3Supplier extends BasePage{
	
	@FindBy(xpath = "//*[contains(text(),'Select Supplier')]")
	private WebElement selectSupplierLink;
	
	@FindBy(xpath = "//div[contains(@id,'start')]")
	private WebElement spinner;
	
	@FindBy(xpath = "//label[contains(@id,'tenant_label')]")
	private WebElement tenantDropdown;
	
	@FindBy(xpath = "//ul[contains(@id,'tenant_items')]/li")
	private WebElement tenantList;
	
	private String tenantXpath1 = "//*[contains(@id, 'tenant_items')]/li[contains(text(), '";
	private String tenantXpath2 = "')]";
	
	@FindBy(xpath = "//*[contains(@id, 'airportcode')]")
	private WebElement airportCityCode;
	
	@FindBy(id = "selectSupplierForm:supplierName_input")
	private WebElement supplierNameInput;
	
	/*@FindBy(id = "selectSupplierForm:supplierName_panel")
	private WebElement selectSupplierOption;*/
	private String selectSupplierOptionXpath1 ="//*[@id='selectSupplierForm:supplierName_panel']//li[@data-item-label='";
	private String selectSupplierOptionXpath2 = "')]";
	
	@FindBy(id = "selectSupplierForm:selectSupplier")
	private WebElement selectBtn;
	
	@FindBy(id = "menuForm:schedules")
	private WebElement supplierSchedulesMenuLink;
	
	@FindBy(id = "menuForm:reservation")
	private WebElement supplierReservationsMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Admin')]")
	private WebElement adminMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Create User')]")
	private WebElement createUserLink;
	
	@FindBy(xpath="//a[contains(text(),'Accounting eDrive')]")
	private WebElement accountingEdrive;
	
	@FindBy(xpath="//a[contains(text(),'Invoice Dashboard')]")
	private WebElement invoiceDashboard;
	
	@FindBy(xpath="//a[contains(text(),'Upload Invoice')]")
	private WebElement uploadInvoice;
	
	@FindBy(xpath = "//*[contains(text(), 'Manage Users')]")
	private WebElement manageUsersLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Manage Roles')]")
	private WebElement manageRolesLink;
	
	@FindBy(id = "menuForm:logout")
	private WebElement logoutMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Home')]")
	private WebElement homeMenuLink;
	
	@FindBy(xpath = "//div[@class='disabledDiv']//a")
	private List<WebElement> selectedMenuLinks;
	
	@FindBy(xpath = "//*[contains(text(), 'Account Management')]")
	private WebElement accountManagementMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Admin')]/..//*[contains(@id, 'childrenGrid')]")
	private List<WebElement> adminChildrenGrid;
	
	@FindBy(xpath = "//*[contains(text(), 'Supplier Invoices')]/..//*[contains(@id, 'childrenGrid')]")
	private List<WebElement> supplierInvoicesChildrenGrid;
	
	@FindBy(xpath = "//*[contains(text(), 'Supplier Invoices')]")
	private WebElement supplierInvoicesLink;
		
	@FindBy(xpath = "//*[contains(@id, 'newInvoices')]")
	private WebElement newInvoicesLink;
	
	@FindBy(xpath = "//*[contains(@id, 'reviewedInvoices')]")
	private WebElement reviewedInvoicesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Sign-in Sheets')]/..//*[contains(@id, 'childrenGrid')]")
	private List<WebElement> signInSheetsChildrenGrid;
	
	@FindBy(xpath = "//*[contains(text(), 'Sign-in Sheets')]")
	private WebElement signInSheetsMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Online Sign-in Sheets')]")
	private WebElement onlineSignInSheetsLink;
	
	@FindBy(xpath = "//a[contains(text(), 'Accounting')]")
	private WebElement accountingMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Accounting')]/..//*[contains(@id, 'childrenGrid')]")
	private List<WebElement> accountingChildrenGrid;
	
	@FindBy(xpath = "//*[contains(text(), 'Create Invoice')]")
	private WebElement createInvoiceLink;
	
	@FindBy(id = "allowanceNavMenu")
	private WebElement allowancesNavMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'View Allowance Forecasts')]")
	private WebElement viewAllowanceForecastsLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Allowances')]/..//*[contains(@id, 'childrenGrid')]")
	private List<WebElement> allowancesChildrenGrid;
	
	@FindBy(xpath = "//a[contains(text(), 'Allowances')]")
	private WebElement allowancesMenuLink;
	
	@FindBy(xpath = "//*[contains(text(), 'View Forecast')]")
	private WebElement viewForecastLink;
	
	@FindBy(xpath = "//a[text()='Create Allowance Invoice']")
	private WebElement createAllowanceInvoiceLink;
	
	@FindBy(xpath = "//span[text()= 'Allowances']")
	private WebElement AllowancesLinkNewSupplier;
	
	@FindBy(xpath = "//a[text()='Find Allowance Invoice']")
	private WebElement findAllowanceInvoiceLink;
	
	@FindBy(id = "menuForm:pickupGTSchedules")
	private WebElement pickUpSchedulesLink;
	
	@FindBy(xpath = "//*[contains(text(), 'View/Print Sign-in Sheets')]")
	private WebElement viewPrintSignInSheetsLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Sign-in Report')]")
	private WebElement signInReportLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Find Invoice')]")
	private WebElement findInvoiceLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Pending Action Queue')]")
	private WebElement pendingActionQueueLink;

	@FindBy(xpath = "//*[contains(text(), 'View Adhoc Allowance')]")
	private WebElement viewAdhocAllowanceLink;
	
	@FindBy(xpath = "//*[contains(text(), 'View Forecast')]")
	private WebElement viewForcastLink;
	
	@FindBy(xpath = "//*[contains(text(), 'Create/View Allowance Sign In Sheet')]")
	private WebElement createViewAllowanceSISLink;
		
	@FindBy(xpath="//a[(text()='Frequently Asked Questions')]")
	private WebElement frequentlyAskedQuestionsLink;
	
	@FindBy(xpath="//a[(text()='(Clear)')]")
	private WebElement clearLink;
	
	@FindBy(xpath="//a[(text()='(Change)')]")
	private WebElement changeLink;
	
	@FindBy(xpath="//a[(text()='Contact Us')]")
	private WebElement contactUsLink;
		
	public EventFiringWebDriver driver=null;

	public Page_Home_Aces3Supplier(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectSupplier(String tenant, String airportCityCode, String supplier)   {
		//getDriver().unregister(eventListener);
		clickOn(selectSupplierLink, "HomePage -> Select Supplier link");
		waitForElementToDisappear(spinner);
		clickOn(tenantDropdown, "SelectSupplierPop-up -> Tenant Dropdown");
		waitForElementVisibility(tenantList);
		clickOn(findElementByXpath(tenantXpath1+tenant+tenantXpath2), "SelectSupplierPop-up -> "+tenant+" option");
		waitForElementToDisappear(spinner);
		typeInText(this.airportCityCode, airportCityCode, "SelectSupplierPop-up -> CityCode");
		driver.findElement(By.xpath("//tr//tr[2]//td[2]")).click();
		waitForElementToDisappear(spinner);       
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		System.out.println("Element found");
		wait.until(ExpectedConditions.elementToBeClickable(supplierNameInput));
		this.supplierNameInput.click();
		typeInText(supplierNameInput, supplier, "SelectSupplierPop-up -> Supplier dropdown prompt");
		waitForElementToDisappear(spinner);
		if(supplier.contains("'"))
			supplier = supplier.split("'")[0];
		clickOn(findElementByXpath(selectSupplierOptionXpath1+supplier+selectSupplierOptionXpath2), "SelectSupplierPop-up -> "+supplier+" option");
		waitForElementToDisappear(spinner);
		clickOn(selectBtn, "SelectSupplierPop-up -> Select button");
		waitForElementToDisappear(spinner);
		waitForPageLoad(getDriver());
	}	
	
	public boolean retryingFindClick(By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 3) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	
	public void clickOn(By locator, WebDriver driver, int timeout)
	{
	    final WebDriverWait wait = new WebDriverWait(driver, timeout);
	    wait.until(ExpectedConditions.refreshed(
	        ExpectedConditions.elementToBeClickable(locator)));
	    driver.findElement(locator).click();
	}
	
	public void clickOnSchedulesLink(){
		clickOn(supplierSchedulesMenuLink, "NavigationalLinksPanel -> Supplier Schedules link");
	}
	
	public void clickOnReservationsLink(){
		clickOn(supplierReservationsMenuLink, "NavigationalLinksPanel -> Reservations link");
	}
	
	public void clickOnCreateUserLink(){
		clickOnAdminMenu();
		waitForElementToDisappear(spinner);
		clickOn(createUserLink, "NavigationalLinksPanel -> AdminMenu -> Create User link");
	}
	
	public void clickOnManageUsersLink(){
		clickOnAdminMenu();
		waitForElementToDisappear(spinner);
		clickOn(manageUsersLink, "NavigationalLinksPanel -> AdminMenu -> Manage Users link");
	}
	
	public void clickOnManageRolesLink(){
		clickOnAdminMenu();
		waitForElementToDisappear(spinner);
		clickOn(manageRolesLink, "NavigationalLinksPanel -> AdminMenu -> Manage Roles link");
	}
	
	private void clickOnAdminMenu(){
		if(adminChildrenGrid.size()==0)
			clickOn(adminMenuLink, "NavigationalLinksPanel -> Admin Menu");
	}
	
	public void clickOnAccountingEdriveMenu(){
		clickOn(accountingEdrive, "NavigationalLinksPanel -> Accounting eDrive link");
	}
	
	public void clickOnUploadInvoice(){
		clickOnAccountingEdriveMenu();
		clickOn(uploadInvoice, "NavigationalLinksPanel -> Accounting eDrive Menu -> Upload Invoice link");
	}
	
	public void clickOninvoiceDashboard(){
		clickOn(invoiceDashboard, "NavigationalLinksPanel -> Accounting eDrive Menu -> Invoice Dashboard link");
	}
	
	public void clickOnLogoutLink(){
		clickOn(logoutMenuLink, "HomePage -> Logout link");
	}
	
	public void clickOnAccountManagementLink(){
		clickOn(accountManagementMenuLink, "NavigationalLinksPanel -> Account Management link");
	}
	
	public void verifyModulesAssignedPerRole(String roles){
		String role1 = roles.split(",")[0];
		String role2 = roles.split(",")[1];
		String module1 = "", module2 = "";
		if(role1.equals("newPvtRole"))
			module1 = "Schedule";
		if(role2.equals("newSharedRole"))
			module2 = "Sign-in Sheets";
		for(WebElement menu : selectedMenuLinks){
			if(!menu.getText().contains(module1) && !menu.getText().contains(module2) &&!menu.getText().contains("Home")){
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Modules for Other roles than assigned are displayed for user!");
				break;
			}
		}
	}
	
	public void clickOnHomeLink(){
		clickOn(homeMenuLink, "NavigationalLinksPanel -> Home link");
	}
	
	public void clickOnNewInvoicesLink(){
		clickOnSupplierInvoicesLink();
		waitForElementToDisappear(spinner);
		clickOn(newInvoicesLink, "NavigationalLinksPanel -> SupplierInvoices menu -> New Invoices link");
	}
	
	public void clickOnReviewedInvoicesLink(){
		clickOnSupplierInvoicesLink();
		waitForElementToDisappear(spinner);
		clickOn(reviewedInvoicesLink, "NavigationalLinksPanel -> SupplierInvoices menu -> Reviewed Invoices link");
	}
	
	private void clickOnSupplierInvoicesLink(){
		if(supplierInvoicesChildrenGrid.size()==0)
			clickOn(supplierInvoicesLink, "NavigationalLinksPanel -> SupplierInvoices link");
	}
	
	public void clickOnOnlineSignInSheetsLink(){
		clickOnSignInSheetsMenu();
		waitForElementToDisappear(spinner);
		clickOn(onlineSignInSheetsLink);
		clickOn(onlineSignInSheetsLink, "NavigationalLinksPanel -> Sign-in Sheets menu -> Online Sign-in Sheets link");
	}
	
	public void clickOnViewPrintSignInSheets(){
		clickOnSignInSheetsMenu();
		waitForElementToDisappear(spinner);
		clickOn(viewPrintSignInSheetsLink);
	}
	
	public void clickOnSignInReport(){
		clickOnSignInSheetsMenu();
		waitForElementToDisappear(spinner);
		clickOn(signInReportLink);
	}
	public void clickOnPickUpSchedulesLink(){
		clickOn(pickUpSchedulesLink);
		waitForElementToDisappear(spinner);
	}
	
	public void clickOnFindInvoiceLink(){
		clickOnAccountingMenu();
		waitForElementToDisappear(spinner);
		clickOn(findInvoiceLink);
	}
	
	public void clickOnPendingActionQueueLink(){
		clickOnAccountingMenu();
		waitForElementToDisappear(spinner);
		clickOn(pendingActionQueueLink);
	}
	
	public String clickOnViewForecast() throws InterruptedException{
		clickOnAllowancesMenu();
		waitForElementToDisappear(spinner);
		String parentWindow = driver.getWindowHandle();
//		clickOn(viewForcastLink);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewForcastLink);
		Thread.sleep(3000);
		return parentWindow;
	}
		
	public void clickOnViewAllowanceForecastLink(String parentWindow){
		switchToNewWindow(parentWindow);
		clickOnAllowancesNavigationalMenu();
		waitForPageLoad(getDriver());
		waitForElementToDisappear(spinner);
		clickOn(viewAllowanceForecastsLink, "Allowances Menu -> ViewAllowanceForecasts Link");
	}
	
	public void clickOnViewAdhocAllowanceLink(String parentWindow){
		switchToNewWindow(parentWindow);
		clickOnAllowancesNavigationalMenu();
		waitForPageLoad(getDriver());
		waitForElementToDisappear(spinner);
		clickOn(viewAdhocAllowanceLink, "Allowances Menu -> ViewAdhocAllowance Link");
	}
	
	public void clickOnViewAdhocAllowanceLink(){
		waitForElementToDisappear(spinner);
		clickOn(viewAdhocAllowanceLink, "Allowances Menu -> ViewAdhocAllowance Link");
	}
	
	public void clickOnCreateViewAllowanceSISLink(){
		waitForElementToDisappear(spinner);
		clickOn(createViewAllowanceSISLink, "Allowances Menu -> CreateViewAllowanceSIS Link");
	}
	
	public void clickOnFrequentlyAskedQuestionsLink() throws InterruptedException{
		Thread.sleep(2000);
		clickOn(frequentlyAskedQuestionsLink);
	}
	
	public void verifyClearAndChangeLinks() throws Exception{
		Thread.sleep(2000);
		waitForElementVisibility(clearLink);
		waitForElementVisibility(changeLink);
	}
	
	public void clickOnContactUs() throws InterruptedException{
		Thread.sleep(2000);
		clickOn(contactUsLink);
	}
	
	
	private void clickOnSignInSheetsMenu(){
		if(signInSheetsChildrenGrid.size()==0)
			clickOn(signInSheetsMenuLink, "NavigationalLinksPanel -> Sign-in Sheets link");
	}
	
	private void clickOnAccountingMenu(){
		if(accountingChildrenGrid.size()==0)
			clickOn(accountingMenuLink, "NavigationalLinksPanel -> Accounting link");
	}
	
	public void clickOnCreateInvoiceLink(){
		clickOnAccountingMenu();
		waitForElementToDisappear(spinner);
		clickOn(createInvoiceLink, "NavigationalLinksPanel -> Accounting menu -> Create Invoice link");
	}
	
	public void navigateToACESIISupplierSiteWindow(String parentWindow){
		switchToNewWindow(parentWindow);
		clickOnAllowancesNavigationalMenu();
		waitForPageLoad(getDriver());
		
	}
	
	public void clickOnViewAllowanceForecastsLink(){
		waitForElementToDisappear(spinner);
		clickOn(viewAllowanceForecastsLink, "NavigationalLinksPanel -> Allowances menu -> View Forecasts link");
	}
	
	public void clickOnAllowancesNavigationalMenu(){
		clickOn(allowancesNavMenuLink, "NavigationalLinksPanel -> Allowances link");
	}
	
	
	public void clickOnAllowancesMenu(){
		if(allowancesChildrenGrid.size()==0)
			clickOn(allowancesMenuLink, "NavigationalLinksPanel -> Allowances link");
	}
	
	public void clickOnCreateAllowanceInvoiceLink(){
		waitForElementToDisappear(spinner);
		clickOn(createAllowanceInvoiceLink, "ACESII Supplier site NavigationalLinks panel -> Create Allowance Invoice link");
	}
	
	public void clickOnFindAllowanceInvoiceLink(){
		waitForElementToDisappear(spinner);
		clickOn(findAllowanceInvoiceLink, "ACESII Supplier site NavigationalLinks panel -> Find Allowance Invoice link");
	}
}
