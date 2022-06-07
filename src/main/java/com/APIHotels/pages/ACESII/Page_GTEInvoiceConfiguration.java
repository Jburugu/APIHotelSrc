package com.APIHotels.pages.ACESII;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;

public class Page_GTEInvoiceConfiguration extends BasePage {

	@FindBy(id = "tenant")
	private WebElement tenant;

	@FindBy(css = "#isEnableGTInvoice")
	private WebElement enableGTInvoice;

	@FindBy(css = "#isEnableManualAllocations")
	private WebElement enableManualAllocations;

	@FindBy(css = "#isEnableBillingPeriod")
	private WebElement enableBillingPeriod;

	@FindBy(css = "#xmlExportFrequency")
	private WebElement xmlExportFrequency;

	@FindBy(css = "#shippingCompanyName")
	private WebElement shippingCompanyName;

	@FindBy(css = "#shippingAttention")
	private WebElement shippingAttention;

	@FindBy(css = "#shippingAddressLine1")
	private WebElement shippingAddressLine1;

	@FindBy(css = "#shippingAddressLine2")
	private WebElement shippingAddressLine2;

	@FindBy(css = "#shippingAddressLine3")
	private WebElement shippingAddressLine3;

	@FindBy(css = "#billingCompanyName")
	private WebElement billingCompanyName;

	@FindBy(css = "#billingAttention")
	private WebElement billingAttention;

	@FindBy(css = "#billingAddressLine1")
	private WebElement billingAddressLine1;

	@FindBy(css = "#billingAddressLine2")
	private WebElement billingAddressLine2;

	@FindBy(css = "#billingAddressLine3")
	private WebElement billingAddressLine3;

	@FindBy(css = "a:contains(Enabled)")
	private WebElement enabledHeaderLink;

	@FindBy(css = "a:contains(Supplier Name)")
	private WebElement supplierNameHeaderLink;

	@FindBy(css = "a:contains(City)")
	private WebElement cityHeaderLink;

	@FindBy(css = "a:contains(Location)")
	private WebElement locationHeaderLink;

	@FindBy(css = "a:contains(Country)")
	private WebElement countryHeaderLink;

	@FindBy(css = "a:contains(API User Nota Fiscal Upload)")
	private WebElement aPIUserNotaFiscalUploadHeaderLink;

	@FindBy(css = "a:contains(Supplier User Nota Fiscal Upload)")
	private WebElement supplierNotaFiscalUploadHeaderLink;

	@FindBy(css = "a:contains(Supplier User View Invoice)")
	private WebElement supplierUserViewInvoiceHeaderLink;

	@FindBy(css = "a:contains(Supplier Billing Cycle)")
	private WebElement supplierBillingCycleHeaderLink;

	@FindBy(css = "a:contains(Invoice Export Trigger)")
	private WebElement invoiceExportTriggerHeaderLink;

	@FindBy(css = "input[value='Save']")
	private WebElement GTEInvoiceConfigSaveBtn;

	@FindBy(xpath = "//span[@class='pagebanner']")
	private WebElement paginationBanner;

	@FindBy(xpath = "//table[@id='results_generated_added']//tr/td[2]")
	private List<WebElement> suppliers;

	String pageLinksXPath1 = "//span[@class='pagelinks']/a[text()='";
	String pageLinksXPath2 = "']";
	String getSupplierNameXPath1 = "//table[@id='results_generated_added']//tr[";
	String getSupplierNameXPath2 = "]/td[2]";
	String notaFiscalCheckboxXpath1 = "//table[@id='results_generated_added']//tr/td[text()='";
	String notaFiscalCheckboxXpath2 = "']/following-sibling::td[4]/input";
	String notaFiscalCheckboxXpath3 = "']/following-sibling::td[6]/input";
	String select_invoiceExportTrigger ="']/following-sibling::td[8]/select";
	String select_SupplierBillingCycle ="']/following-sibling::td[7]/select";
	
	@FindBy(how =How.XPATH, using ="//input[@name='save']")
	private WebElement btn_SaveConfigurations;
	
	String test2 = "Metro Cabs";
	
	public EventFiringWebDriver driver = null;

	public Page_GTEInvoiceConfiguration(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setTenantDetailsAndOptionsReg(String tenant, int invoiceExportFrequency, String shippingToCompName,
			String billToCompName) throws Exception {
		selectByVisibleText(this.tenant, tenant, "GTEInvoiceConfigurationPage -> Tenant Dropdown");
		waitForPageToLoad("3");

		if (!enableGTInvoice.isEnabled())
			clickOn(enableGTInvoice, "GTEInvoiceConfigurationPage -> ActivateGTInvoicesForTheTenant Checkbox");
		typeInText(shippingCompanyName, shippingToCompName, "GTEInvoiceConfigurationPage -> InvoiceShiptoAddressCompanyName");
		typeInText(billingCompanyName, billToCompName, "GTEInvoiceConfigurationPage -> InvoiceBilltoAddressCompanyName");

	}

	public void clickOnGTConfSave() {
		clickOn(GTEInvoiceConfigSaveBtn, "GTEInvoiceConfigurationPage -> Save Button");
	}

	public void verifyInvoiceFrequencyOnOff(String tenant, String invoiceExportFrequency, String shippingToCompName,
			String billToCompName) throws Exception {
		selectByVisibleText(this.tenant, tenant, "GTEInvoiceConfigurationPage -> Tenant Dropdown");
		waitForPageToLoad("3");
		Select sel = new Select(xmlExportFrequency);
		if (sel.getFirstSelectedOption().equals("OFF")) {
			selectByVisibleText(xmlExportFrequency, invoiceExportFrequency, "GTEInvoiceConfigurationPage -> InvoiceExportFrequency Dropdown");
		} else {
			selectByVisibleText(xmlExportFrequency, invoiceExportFrequency, "GTEInvoiceConfigurationPage -> InvoiceExportFrequency Dropdown");
		}
	}

	public void supplierGTConfig(String tenant, String supplierNameValue, String invoiceExportFreq,
			String inoviceExportTrigger) throws Exception {
		selectByVisibleText(this.tenant, tenant, "GTEInvoiceConfigurationPage -> Tenant Dropdown");
		Thread.sleep(2000);
		String string = paginationBanner.getText();
		System.out.println("Pagination string :" + string);
		String youString = null;
		int spacePos = string.indexOf(" ");
		if (spacePos > 0) {
			youString = string.substring(0, spacePos - 0);
			System.out.println(youString);
		}
		double m = 10;
		double y = (Double.parseDouble(youString) / m);
		BigDecimal value = new BigDecimal(y);
		value = value.setScale(0, RoundingMode.UP);
		System.out.println("Rounding Mod UP :" + value);
		int size = value.intValue();
		System.out.println("Link size :" + size);
		boolean flag = false;
		if (size > 0) {
			System.out.println("pagination exists");
			// click on pagination link
			for (int i = 1; i <= size; i++) {
				try {
					if (i > 1)
						clickOn(findElementByXpath(pageLinksXPath1 + i + pageLinksXPath2), "GTEInvoiceConfigurationPage -> SupplierListPagination Link");
					Thread.sleep(5000);
					System.out.println("Loop " + i);
					for (int j = 1; j < suppliers.size() + 1; j++) {
						String getSupplierName = getTextOfElement(
								findElementByXpath(getSupplierNameXPath1 + j + getSupplierNameXPath2));
						System.out.println(getSupplierName);
						WebElement ele2 = driver.findElement(
								By.xpath("//table[@id='results_generated_added']//tr[" + j + "]/td[1]/input"));
						if (getSupplierName.equals(supplierNameValue)) {
							if (!ele2.isSelected())
								clickOn(ele2 ,"GTEInvoiceConfigurationPage -> Enabled Checkbox");
							if (!findElementByXpath(
									notaFiscalCheckboxXpath1 + supplierNameValue + notaFiscalCheckboxXpath2)
											.isSelected())
								clickOn(findElementByXpath(
										notaFiscalCheckboxXpath1 + supplierNameValue + notaFiscalCheckboxXpath2), "GTEInvoiceConfigurationPage -> SupplierUserNotaFiscalUpload Checkbox");
							if (!findElementByXpath(
									notaFiscalCheckboxXpath1 + supplierNameValue + notaFiscalCheckboxXpath3)
											.isSelected())
								clickOn(findElementByXpath(
										notaFiscalCheckboxXpath1 + supplierNameValue + notaFiscalCheckboxXpath3), "GTEInvoiceConfigurationPage -> SupplierUserNotaFiscalUpload Checkbox");
							selectByVisibleText(
									findElementByXpath(
											notaFiscalCheckboxXpath1 + supplierNameValue + select_SupplierBillingCycle),
									invoiceExportFreq);
							selectByVisibleText(
									findElementByXpath(
											notaFiscalCheckboxXpath1 + supplierNameValue + select_invoiceExportTrigger),
									inoviceExportTrigger);
							takeScreenshot();
							clickOn(btn_SaveConfigurations, "GTEInvoiceConfigurationPage -> Save Button");
							flag = true;
							break;
						} else {
							System.out.println("Link not found");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (flag == true)
					break;
			}
		} else {
			System.out.println("pagination not exists");
		}
	}
	
	
	public void setGTInvoicingConfigForTenant(String tenantName, String shippingToCompName,
			String billToCompName) throws Exception {
		selectByVisibleText(tenant, tenantName, "GTEInvoiceConfigurationPage -> Tenant Dropdown");
		waitForPageToLoad("3");

		if (!enableGTInvoice.isSelected()){
		clickOn(enableGTInvoice, "GTEInvoiceConfigurationPage -> ActivateGTInvoicesForTheTenant Checkbox");
		clickOn(enableBillingPeriod, "GTEInvoiceConfigurationPage -> UseGTSupplierBillingPeriodsForTheTenant Checkbox");
		typeInText(shippingCompanyName, shippingToCompName, "GTEInvoiceConfigurationPage -> InvoiceShiptoAddressCompanyName");
		typeInText(billingCompanyName, billToCompName, "GTEInvoiceConfigurationPage -> InvoiceBilltoAddressCompanyName");
		clickOnGTConfSave();
		}
	}


}
