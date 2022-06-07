package com.APIHotels.pages.ACESII;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class Page_ViewConsolidatedInvoice extends BasePage{
	
	@FindBy(xpath = ".//*[@id='viewConsolidatedInvoice']/a")
	public WebElement viewConsolidatedInvoice;
	
	@FindBy(id = "hotel")
	public WebElement hotelServices;
	
	@FindBy(id = "gt")
	public WebElement gtServices;
	
	@FindBy(css = "input[value='Search/Refresh']")
	public WebElement searchRefresh;
	
	@FindBy(name = "serviceType")
	private List<WebElement> serviceType; //HOTEL, GT, BOTH
	
	String serviceTypeXpath1 = "//*[@name = 'serviceType' and @id = '";
	String serviceTypeXpath2 = "']";
	
	String invoiceCreatedDateXpath1 = "//*[contains(text(), '";
	String invoiceCreatedDateXpath2 = "')]";
	
	String totalValuesXpath1 = "//*[@id='vieInvoiceDetailsId']/table//td[";
	String totalValuesXpath2 = "]";
	
	String headerValueXpath = "//*[@id = 'vieInvoiceDetailsId']//tr/th";
	String headerValueXpath2 = "/a";
	
	String totalRooms = "", totalEPD = "", totalAmount = "";
	
	/*String totalRooms= "//*[@id='vieInvoiceDetailsId']/table//td[20]";
	public List<WebElement> totalRoomsAmount;
	
	@FindBy(xpath = "//*[@id='vieInvoiceDetailsId']/table//td[22]")
	public List<WebElement> totalEPD;
	
	@FindBy(xpath = "//*[@id='vieInvoiceDetailsId']/table//td[31]")
	public List<WebElement> totalAmount;*/
	
	@FindBy(xpath = "//*[@id = 'vieInvoiceDetailsId']//td/a")
	public List<WebElement> invoiceDownloadLinks;
	
	String parentWindowHandle = "";
	
	public EventFiringWebDriver driver=null;
	
	public Page_ViewConsolidatedInvoice(EventFiringWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnViewConsolidatedInvoices() throws Exception {
		waitForElementVisibility(viewConsolidatedInvoice);
		clickOn(viewConsolidatedInvoice);
	}

	public void viewConsolidatedInvoicesMainTab() throws Exception {
		clickOnViewConsolidatedInvoices();
		viewConsolidatedInvoices();
	}

	private void viewConsolidatedInvoices() throws Exception {
		waitForElementVisibility(hotelServices);
		waitForElementVisibility(gtServices);
		waitForElementVisibility(searchRefresh);
		clickOn(searchRefresh);
	}

	public void services(String serviceType){
		clickOn(findElementByXpath(serviceTypeXpath1+serviceType.toLowerCase()+serviceTypeXpath2));
	}
	
	public void viewConsolidatedInvoices(String serviceType) throws Exception {
		services(serviceType);
		waitForElementVisibility(searchRefresh);
		clickOn(searchRefresh);
	}
	
	public void viewConsolidatedHotelInvoice(String serviceType) throws Exception{
		viewConsolidatedInvoices(serviceType);
	}
	
	public void viewConsolidatedGTInvoice(String serviceType) throws Exception{
		viewConsolidatedInvoices(serviceType);
	}
	
	public void viewConsolidatedInvoiceByCurrentMonth(){
		waitForElementVisibility(findElementByXpath(invoiceCreatedDateXpath1+getDateInFormat("MMM-yyyy")+invoiceCreatedDateXpath2));
		clickOn(findElementByXpath(invoiceCreatedDateXpath1+getDateInFormat("MMM-yyyy")+invoiceCreatedDateXpath2));
	}
	
	public HashMap<String, String> fetchValuesFromInvoiceTables(String tenantName) throws Exception{
		String totalRooms = "", totalAmount = "", totalEPD = "";
		getTotalValueCellsBasedOnHeaderPosition();
		totalRooms = calculateTotalValues(totalRooms, this.totalRooms);
		totalEPD = calculateTotalValues(totalEPD, this.totalEPD);
		totalAmount = calculateTotalValues(totalAmount, this.totalAmount);
		HashMap<String, String> totalValuesFromViewConsolidatedInvoice = new HashMap<String, String>();
		totalValuesFromViewConsolidatedInvoice.put("TenantName", tenantName);
		totalValuesFromViewConsolidatedInvoice.put("TotalRooms", totalRooms);
		totalValuesFromViewConsolidatedInvoice.put("TotalEPD", totalEPD);
		totalValuesFromViewConsolidatedInvoice.put("TotalAmount", totalAmount);
		return totalValuesFromViewConsolidatedInvoice;
	}
	
	private void getTotalValueCellsBasedOnHeaderPosition() throws Exception{
		List<WebElement> consolidatedInvoiceHeaders = findElementsByXpath(headerValueXpath);
		while(consolidatedInvoiceHeaders.size() == 0){
			consolidatedInvoiceHeaders = findElementsByXpath(headerValueXpath);
			System.out.println("headers size: "+consolidatedInvoiceHeaders.size());
		}
		for(int i = 0; i < consolidatedInvoiceHeaders.size(); i++){
			WebElement headerElement = findElementByXpath(headerValueXpath+"["+(i+1)+"]"+headerValueXpath2);
			waitForElementVisibility(headerElement);
			String thisHeaderText = headerElement.getText();
			switch(thisHeaderText){
			case "#Rooms":
				this.totalRooms = totalValuesXpath1+(i+1)+totalValuesXpath2;
				break;
			case "EPD Amount":
				this.totalEPD = totalValuesXpath1+(i+1)+totalValuesXpath2;
				break;
			case "Amount":
				this.totalAmount = totalValuesXpath1+(i+1)+totalValuesXpath2;
				break;
			}
			if(totalEPD!="" && totalRooms!="" && totalAmount!="")
				break;
		}
	}
	
	private String calculateTotalValues(String result, String elementXpath){
		int i = 1;
		List<WebElement> element = null;
		while(element == null){
			element = findElementsByXpath(elementXpath);
		}
		while((result.equals("0")||result.equals("")) && i<5){
			result = "0";
			for(WebElement noOfRooms : element)
			{
				Double curTotal = Double.parseDouble(noOfRooms.getText().trim().replace(",", ""));
				result = String.valueOf(Math.round((Double.parseDouble(result)+curTotal)*100.0)/100.0);
			}
		}
		i++;
		return result;
	}
	
	public void downloadInvoices(String username, String password, String tenantName) throws Exception{
		for(WebElement thisInvoiceLink : invoiceDownloadLinks){
			parentWindowHandle = getDriver().getWindowHandle();
			String invoiceId = thisInvoiceLink.getText().trim();
			clickOn(thisInvoiceLink);
			try{Thread.sleep(3000);}catch(Exception e){}
			switchToNewWindow(parentWindowHandle);
			String url = driver.findElements(By.tagName("iframe")).get(0).getAttribute("src");
			driver.close();
			switchToWindow(parentWindowHandle);
			url = URL.substring(0,25)+url;
			System.out.println(url);
			createDriverAndLogin(username, password, url);
			downloadPDF("ConsolidatedInvoices_ACES_"+getDateInFormat("MMM-yyyy")+File.separator+tenantName.replace(" ", "_"), invoiceId);
		}
	}
	
	public void storeValuesForComparisonInProp(HashMap<String, String> invoiceValuesMap, String tenantName) throws Exception{
		writeProp("ACES_TotalRooms_"+tenantName,  invoiceValuesMap.get("TotalRooms"));
		writeProp("ACES_TotalEPD_"+tenantName,  invoiceValuesMap.get("TotalEPD"));
		writeProp("ACES_TotalAmount_"+tenantName,  invoiceValuesMap.get("TotalAmount"));
	}
	

}
