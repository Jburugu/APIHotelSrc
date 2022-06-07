package com.APIHotels.pages.navigationalLinks.Airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class AccountingTab extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Accounting')]")
	public WebElement accountingLink;

	@FindBy(how = How.XPATH, using = "//td[text()='Hotel']")
	public WebElement hotelLink;

	@FindBy(how = How.ID, using = "iconfindInvoices")
	public WebElement findInvoicesLink;

	@FindBy(how = How.ID, using = "icongtUsage")
	public WebElement gtUsageLink;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Ground Transportation')]")
	public WebElement groundTransportationLink;

	@FindBy(how = How.ID, using = "iconfindGTInvoice")
	public WebElement findGTInvoiceLink;

	@FindBy(how = How.ID, using = "iconactionGTInvoice")
	public WebElement actionGTInvoiceLink;
	
	@FindBy(how = How.ID, using = "icongtEInvUsage")
	public WebElement gtUsageLinkUnderGT;

	@FindBy(how = How.ID, using = "icongtPayment")
	public WebElement gtPaymentFileLinkUnderGT;
	
	@FindBy(how = How.ID, using = "iconinvoices")
	public WebElement supplierInvoicesLink;
	
	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Room Usage')]")
	public WebElement roomUsageLink;
	
	@FindBy(how = How.ID, using = "iconviewRecInvoices")
	public WebElement reconciledInvoicesLink;
	
	public AccountingTab(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnHotel(){
		clickOn(hotelLink, "Accounting Menu -> Hotel Link");
	}
	
	public void clickOnRoomUsage(){
		clickOn(roomUsageLink, "Accounting Menu -> RoomUsage Link");
	}
	
	public void clickOnAccountingAndHotelLinks() {
		clickOn(accountingLink, "HomePage -> Accounting Menu");
		clickOn(hotelLink, "Accounting Menu -> Hotel Link");
	}

	public void clickOnFindInvoicesLink() {
		clickOnAccountingAndHotelLinks();
		clickOn(findInvoicesLink, "Hotel Link -> FindInvoices Link");
	}

	public void clickOnGTUsageLink() {
		clickOnAccountingAndHotelLinks();
		clickOn(gtUsageLink, "Hotel Link -> GTUsage Link");
	}

	public void clickOnAccountingLink() {
		clickOn(accountingLink, "HomePage -> Accounting Link");
	}

	public void clickOnGroundTransportationAndFindGTInvoice() {
		clickOn(groundTransportationLink, "Accounting Menu -> GroundTransportationLink");
		clickOn(findGTInvoiceLink, "GroundTransportationLink -> FindGTInvoiceLink" );
	}
	
	public void clickOnAccountingAndGroundTransportationLinks() {
		clickOnAccountingLink();
		clickOn(groundTransportationLink, "Accounting Menu -> GroundTransportationLink");
	}
	
	public void clickOnActionGTInvoiceLink(){
		clickOn(actionGTInvoiceLink,  "GroundTransportationLink -> ActionGTInvoiceLink" );
	}
	
	public void clickOnGTUsageLinkUnderGT(){
		clickOn(gtUsageLinkUnderGT, "GroundTransportationLink -> GTUsageLink" );
	}
	
	public void clickOnGTPaymentFileLinkUnderGT(){
		clickOn(gtPaymentFileLinkUnderGT, "GroundTransportationLink -> GTPaymentFileLink" );
	}
	
	public void clickOnSupplierInvoicesLink() {
		clickOnAccountingAndHotelLinks();
		clickOn(supplierInvoicesLink, "Hotel Link -> SupplierInvoices Link");
	}
	
	public void clickOnRoomUsageLink(){
		clickOnAccountingAndHotelLinks();
		clickOn(roomUsageLink, "Hotel Link -> RoomUsage Link");
	}
	
	public void isRoomUsageLinkPresent() {
		waitForElementVisibility(roomUsageLink);
	}
	
	public void clickOnReconciledInvoicesLink(){
		clickOnAccountingAndHotelLinks();
		clickOn(reconciledInvoicesLink, "Hotel Link -> ReconciledInvoices Link");
	}
}
