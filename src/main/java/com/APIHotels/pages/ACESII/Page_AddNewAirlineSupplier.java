package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;


public class Page_AddNewAirlineSupplier  extends BasePage{
	
	@FindBy(id = "newAirlineSupplier")
	private WebElement addNewSupplierLink;
	
	@FindBy(id = "hotel")
	private WebElement hotelRadioBtn;
	
	@FindBy(id = "gt")
	private WebElement gtRadioBtn;
	
	@FindBy(name ="serviceType")
	private List<WebElement> serviceType;
	
	@FindBy(id = "city")
	private WebElement city;
	
	@FindBy(xpath = "//*[@value = 'Get Supplier']")
	private WebElement getSupplierBtn;
	
	@FindBy(id = "supplierid")
	private WebElement suppliers;
	
	@FindBy(id = "supplier")
	private WebElement addSupplierBtn;
	
	@FindBy(how = How.CLASS_NAME, using = "Title")
	private WebElement supplierName;
	
	@FindBy(how = How.XPATH, using = "//*[@id='contractTable']/tbody/tr[2]/td[4]")
	private WebElement contractDetailsBtn;
	
	public EventFiringWebDriver driver=null;
	
	public Page_AddNewAirlineSupplier(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddNewSupplierLink() throws Exception{
		clickOn(addNewSupplierLink);
	}
	
	public void addNewSupplier(String city, String service, String supplier) throws Exception{
		if(service.equals("Hotel"))
			clickOn(hotelRadioBtn);
		else if(service.equals("GT"))
			clickOn(gtRadioBtn);
		typeInText(this.city, city);
		clickOn(getSupplierBtn);
		selectByVisibleText(this.suppliers, supplier);
		clickOn(addSupplierBtn);
		waitForElementVisibility(supplierName);
		assertTrue(supplierName.getText().contains(supplier), serviceType+" Supplier Name is not matching");
	}
	
	
	public void clickHotelContractDetailsBtn() throws Exception{
		String currentWindowHandle = driver.getWindowHandle();
		System.out.println(currentWindowHandle);
		clickOn(contractDetailsBtn);
		try{Thread.sleep(3000);}catch(Exception e){}
		switchToNewWindow(currentWindowHandle);
		waitForPageToLoad("5");
	}

}
