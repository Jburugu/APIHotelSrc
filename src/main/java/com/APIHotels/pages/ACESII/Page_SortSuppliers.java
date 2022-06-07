package com.APIHotels.pages.ACESII;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.APIHotels.framework.BasePage;

public class Page_SortSuppliers extends BasePage{
	
	@FindBy(id = "locCd")
	private WebElement layoverLocation;
	
	@FindBy(xpath = "//*[@value = 'Retrieve']")
	private WebElement retrieveBtn;
	
	@FindBy(xpath = "//*[@value = 'Save']")
	private WebElement saveBtn;
	
	private WebElement supplierName;
	private WebElement sortOption;
	
	String supplierTableXpath="//*[@id = 'supplierTable']/tbody/tr";
	String supplierTableXpath1="]/td";
	
	public EventFiringWebDriver driver=null;

	public Page_SortSuppliers(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void retrieveSuppliers(String layoverLocation) throws Exception{
		typeInText(this.layoverLocation, layoverLocation);
		clickOn(retrieveBtn);
	}
	
	public void sortSuppliers(String supplierName, String sortOption) throws Exception{
		this.supplierName = findElementByXpath("//td[normalize-space() = '"+supplierName+"']");
		int supplierCurrentRowId = supplierCurrentRowId(supplierName);
		clickOn(this.supplierName);
		this.sortOption = findElementByXpath("//*[@value = '"+sortOption+"']");
		clickOn(this.sortOption);
		int supplierNewRowId = supplierCurrentRowId(supplierName);
		if(sortOption.equals("First")){
			System.out.println("OldPosition: "+supplierCurrentRowId+" New Position: "+supplierNewRowId);
			Assert.assertTrue(supplierNewRowId == 2, "Not sorted to First");
		}
		else if(sortOption.equals("Up")){
			System.out.println("OldPosition: "+supplierCurrentRowId+" New Position: "+supplierNewRowId);
			if(supplierCurrentRowId == 2)
				assertTrue(supplierNewRowId == 2, "Sort Not working as expected!");
			else
				assertTrue(supplierNewRowId == supplierCurrentRowId-1, "Not sorted to Up");
			}
		else if(sortOption.equals("Down")){
			System.out.println("OldPosition: "+supplierCurrentRowId+" New Position: "+supplierNewRowId);
			if(supplierCurrentRowId == 0)
				assertTrue(supplierNewRowId == 0, "Sort Not working as expected!");
			else
				assertTrue(supplierNewRowId == supplierCurrentRowId+1, "Not sorted to Down");
		}
		else if(sortOption.equals("Last")){
			System.out.println("OldPosition: "+supplierCurrentRowId+" New Position: "+supplierNewRowId);
			Assert.assertTrue(supplierNewRowId == 0, "Not sorted to Last");
		}
		waitForElementVisibility(saveBtn);
	}
	
	private int supplierCurrentRowId(String supplierName){
		List<WebElement> supplier_totalRows = findElementsByXpath(supplierTableXpath);
		int currentRow = -1;
		for(int i = 2; i<supplier_totalRows.size(); i++){
			WebElement td = findElementByXpath(supplierTableXpath+"["+i+supplierTableXpath1);
			                                 
			if(td.getText().contains(supplierName)){
				currentRow = i;
				break;
			}
			else
				currentRow++;
		}
		if(currentRow == supplier_totalRows.size())
			return 0;
		else
			return currentRow;
	}
	
	public void clickSaveBtn() throws Exception{
		clickOn(saveBtn);
		takeScreenshots();
		unExpectedAcceptAlert();
	}
}
