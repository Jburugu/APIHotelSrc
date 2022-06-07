package com.APIHotels.pages.ACESII;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_HotelAllowanceConfiguration extends BasePage{

	@FindBy(id = "tenant")
	private WebElement tenant;
	
	@FindBy(xpath = "//*[@id = 'enableSchedule1' and @value = 'true']")
	private WebElement enableAllowanceYes;
	
	@FindBy(name = "Save")
	private WebElement saveBtn;
	
	@FindBy(name = "Cancel")
	private WebElement cancelBtn;
	
	@FindBy(xpath = "//*[@id = 'activation1']/tbody/tr/td/fieldset/table/tbody/tr/td[2]/a[1]")
	private WebElement hotelSuppliers_OnLink;
	
	@FindBy(xpath = "//*[@id = 'activation1']/tbody/tr/td/fieldset/table/tbody/tr/td[2]/a[2]")
	private WebElement hotelSuppliers_OffLink;
	
	@FindBy(xpath = "//*[@id = 'example']/thead/tr/th[1]/input")
	private WebElement locationSort;
	
	@FindBy(xpath = "//*[@id = 'example']/thead/tr/th[2]/input")
	private WebElement nameSort;
	
	private String resultRow_EnabledBtnXpath1 = "//*[@id = 'example']/tbody/tr/td[contains(text(), '";
	private String resultRow_EnabledBtnXpath2 = "')]/../td/input[@name = 'enabledSupplier']";
	
	@FindBy(xpath = "//*[@class = 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")
	private List<WebElement> widgetBtns;
	
	@FindBy(xpath = "//*[@id = 'example_processing' and @style = 'display: none;']")
	private WebElement processingMsg;
	
	public EventFiringWebDriver driver=null;

	public Page_HotelAllowanceConfiguration(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setAllowanceConfiguration(String tenant, String enableAllowanceIndicator, String hotelSupplierLinks, String onSupplierDetails, String offSupplierDetails) throws Exception{
		//logger.info("*** Admin_HotelAllowanceConfigurationPage -> setAllowanceConfiguration method started ***");
		selectByVisibleText(this.tenant, tenant);
		//waitForPageLoad(driver);
		Thread.sleep(10000);
		if(enableAllowanceIndicator.equals("Yes")){
			clickOn(enableAllowanceYes);
			for(String hotelSupplierLink : hotelSupplierLinks.split(",")){
				if(hotelSupplierLink.equals("On")){
					clickOn(hotelSuppliers_OnLink);
					selectSuppliers(onSupplierDetails);
				}else if(hotelSupplierLink.equals("Off")){
					clickOn(hotelSuppliers_OffLink);
					selectSuppliers(offSupplierDetails);
				}
				
				waitForElementVisibility(widgetBtns.get(0));
				clickOn(widgetBtns.get(1));
			}
		}
		waitForElementVisibility(saveBtn);
		waitForElementVisibility(cancelBtn);
		//logger.info("*** setAllowanceConfiguration method completed ***");
	}
	
	private void selectSuppliers(String supplierDetails) throws Exception{
		//logger.info("*** Admin_HotelAllowanceConfigurationPage -> selectSuppliers method started ***");
		for(String supplier : supplierDetails.split(";")){
			String location = supplier.split(",")[0];
			String name = supplier.split(",")[1];
			typeInText(locationSort, location);
			typeInText(nameSort, name);
			clickOn(findElementByXpath(resultRow_EnabledBtnXpath1+name+resultRow_EnabledBtnXpath2));
		}
		//logger.info("*** selectSuppliers method completed ***");
	}
	
}
