package com.APIHotels.pages.ACESII;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;
import com.APIHotels.pages.navigationalLinks.ACESII.SupplierProfileMenu;

public class ErrorsAndWarningsHandler extends BasePage{
	
	
	public EventFiringWebDriver driver=null;

	public ErrorsAndWarningsHandler(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void roomLimitExceeded(String city) throws Exception{
		//logger.info("*** Handling roomLimitExceeded Error ***");
		Page_Home homePage = new Page_Home(driver);
		homePage.clickSupplierProfileLink();
		SupplierProfileMenu supplierProfileMenu = new SupplierProfileMenu(driver);
		supplierProfileMenu.clickAirlineSupplierLink();
		Page_FindExistingAirlineSupplier findExistingAirlineSupplierPage = new Page_FindExistingAirlineSupplier(driver);
		findExistingAirlineSupplierPage.clickFindSupplierLink();
		findExistingAirlineSupplierPage.searchSupplier(city, "Hotel", "CONTRACT"); //1 for GT, 3 for Hotel 
		findExistingAirlineSupplierPage.selectSupplier();
		Page_AirlineHotelSupplier airlineHotelSupplierPage = new Page_AirlineHotelSupplier(driver);
		airlineHotelSupplierPage.clickHotelContractDetailsBtn();
		Page_HotelSupplier_ContractDetails airlineSupplierContractDetailsPage = new Page_HotelSupplier_ContractDetails(driver);
		airlineSupplierContractDetailsPage.changeMaxRooms("500");
		airlineSupplierContractDetailsPage.cancelDetails();
		//logger.info("*** Completed handling roomLimitExceeded Error ***");
	}
	
	public void missingContractHotel(String city) throws Exception{
		//logger.info("*** Handling missingContractHotel Error ***");
		Page_Home homePage = new Page_Home(driver);
		homePage.clickSupplierProfileLink();
		SupplierProfileMenu supplierProfileMenu = new SupplierProfileMenu(driver);
		supplierProfileMenu.clickAirlineSupplierLink();
		Page_FindExistingAirlineSupplier findExistingAirlineSupplierPage = new Page_FindExistingAirlineSupplier(driver);
		findExistingAirlineSupplierPage.clickFindSupplierLink();
		findExistingAirlineSupplierPage.searchSupplier(city, "Hotel", "NON_CONTRACT");
		waitForPageLoad(driver);
		findExistingAirlineSupplierPage.selectSupplier();
		Page_AirlineHotelSupplier airlineHotelSupplierPage = new Page_AirlineHotelSupplier(driver);
		airlineHotelSupplierPage.setContractIndicator();
		airlineHotelSupplierPage.editContractDetails(getCurDate(), currentDatePlus(30));
		airlineHotelSupplierPage.clickHotelContractDetailsBtn();
		airlineHotelSupplierPage.saveDetails();
		//logger.info("*** Completed handling missingContractHotel Error ***");
	}
	
	public void changeSupplierDetails(String city, String errorText) throws Exception{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.focus();");
		Page_Home homePage = new Page_Home(driver);
		homePage.clickSupplierProfileLink();
		SupplierProfileMenu supplierProfileMenu = new SupplierProfileMenu(driver);
		supplierProfileMenu.clickAirlineSupplierLink();
		Page_FindExistingAirlineSupplier findExistingAirlineSupplierPage = new Page_FindExistingAirlineSupplier(driver);
		findExistingAirlineSupplierPage.clickFindSupplierLink();
		if(errorText.contains("GT")){
			findExistingAirlineSupplierPage.searchSupplier(city, 1, "NON_CONTRACT");
			waitForPageLoad(driver);
			findExistingAirlineSupplierPage.selectSupplier();
			Page_AirlineGTSupplier airlineGTSupplierPage = new Page_AirlineGTSupplier(driver);
			airlineGTSupplierPage.editContractDetails(currentDatePlus(-360), currentDatePlus(730), "1", "");
			airlineGTSupplierPage.editRoutesAndRates(1, currentDatePlus(-360), currentDatePlus(730), city, "", city, "", "", "", "USD");
			airlineGTSupplierPage.saveDetails();
		}else{
			findExistingAirlineSupplierPage.searchSupplier(city, 0, "NON_CONTRACT");
			waitForPageLoad(driver);
			findExistingAirlineSupplierPage.selectSupplier();
			Page_AirlineHotelSupplier airlineHotelSupplierPage = new Page_AirlineHotelSupplier(driver);
			airlineHotelSupplierPage.editAirportVenueHotelTransitTimesDetails(1, "S,M,T,W,TH,F,ST", city, currentDatePlus(-360), currentDatePlus(730), "", "", "20");
			airlineHotelSupplierPage.editHotelUseRules("S,M,T,W,TH,F,ST", city, currentDatePlus(-360), currentDatePlus(730), "", "");
			airlineHotelSupplierPage.editContractDetails(currentDatePlus(-360), currentDatePlus(730));
			airlineHotelSupplierPage.setContractIndicator();
			airlineHotelSupplierPage.clickHotelContractDetailsBtn();
			Page_HotelSupplier_ContractDetails airlineSupplierContractDetailsPage = new Page_HotelSupplier_ContractDetails(driver);
			airlineSupplierContractDetailsPage.editPrimaryHotelRulesAndRates();
			airlineSupplierContractDetailsPage.setRoomLimits(currentDatePlus(-360), currentDatePlus(730), "1", "500");
			airlineSupplierContractDetailsPage.editRoomRates("USD", "S,M,T,W,TH,F,ST", currentDatePlus(-360), currentDatePlus(730), "Any", "80.0", "1", "500");
			airlineSupplierContractDetailsPage.setShuttleOperatingTimes("S,M,T,W,TH,F,ST", currentDatePlus(-360), currentDatePlus(730), "1200", "1500");
			airlineSupplierContractDetailsPage.clickSaveDetails();
			airlineHotelSupplierPage.saveDetails();
		}
		
		
		
	}

}
