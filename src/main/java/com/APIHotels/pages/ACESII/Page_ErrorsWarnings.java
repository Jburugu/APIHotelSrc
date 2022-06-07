package com.APIHotels.pages.ACESII;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.APIHotels.framework.BasePage;
import com.APIHotels.pages.navigationalLinks.ACESII.PlanningMenu;


public class Page_ErrorsWarnings extends BasePage{
	
	@FindBy(id = "bidPeriodNumber")
	WebElement bidPeriod;
	
	@FindBy(id = "errorLocCode")
	WebElement city;
	
	@FindBy(id = "errorType")
	List<WebElement> errorType;			
	
	@FindBy(name = "errorCd")
	WebElement errorCode;
	
	@FindBy(name = "warnCode")
	WebElement warningCode;
	
	@FindBy(name = "search")
	WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id='row']/tbody/tr")
	private List<WebElement> warningsErrorsRows;
	
	@FindBy(xpath = "//*[@id = 'ErrorResult']/div")
	private WebElement NoOfErrorsFoundMsg;
	
	@FindBy(xpath = "//*[@id = 'row']/tbody//tr/td[2]")
	private List<WebElement> cityCols; 
	
	@FindBy(xpath = "//*[@id = 'row']/tbody/tr[1]/td[2]")
	private WebElement firstRowCity;

	private String errorNotHandled;
	private String errorDescLink = "//*[@id='row']/tbody//a";
	
	private String errorTypeXpath1 = "//*[@id = 'errorType' and @value = '";
	private String errorTypeXpath2 = "']";
	private WebElement errorTypeRadioBtn;
	
	
	public EventFiringWebDriver driver=null;

	public Page_ErrorsWarnings(EventFiringWebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchAndSelectAssignmentErrors() throws Exception{
		clickOn(findElementByXpath(errorTypeXpath1+1+errorTypeXpath2));
		clickOn(searchBtn);
		waitForPageLoad(getDriver());
		if(Integer.parseInt(NoOfErrorsFoundMsg.getText().trim().split(" ")[0])>0){
			clickOn(findElementByXpath(errorDescLink));
			waitForPageLoad(getDriver());
		}
		
	}
	
	public void searchAndProcessErrorsAndWarnings(EventFiringWebDriver driver) throws Exception{
		for(int errorWarningRadioBtnValue = 0 ; errorWarningRadioBtnValue < 2; errorWarningRadioBtnValue++){
			errorTypeRadioBtn = findElementByXpath(errorTypeXpath1+errorWarningRadioBtnValue+errorTypeXpath2);
			WebElement dropdownType = null;
			if(errorTypeRadioBtn.getAttribute("value").equals("1"))
				dropdownType = errorCode;
			else if(errorTypeRadioBtn.getAttribute("value").equals("0") || errorTypeRadioBtn.getAttribute("value").equals("2"))
				continue;
			/*	dropdownType = warningCode;
			else if(errorTypeRadioBtn.getAttribute("value").equals("2"))*/
				
			List<WebElement> options = new Select(dropdownType).getOptions();
			List<String> errorWarningsText = new ArrayList<String>();
			options.forEach(option -> errorWarningsText.add(option.getText()));
			for(String errorText : errorWarningsText){
				errorNotHandled = "No";
				int resultRows = searchForErrorWarning(errorWarningRadioBtnValue, dropdownType, errorText);
				if(resultRows>0){
					startErrorWarningProcessing(driver, errorText);
					findElements(errorWarningRadioBtnValue, dropdownType);
				}
			}
		}
	}
	
	private int searchForErrorWarning(int errorWarningRadioBtnValue, WebElement dropdownType, String errorText) throws Exception{
		//logger.info("*** ErrorsWarningsPage -> searchForErrorWarning method started ***");
		clickOn(errorTypeRadioBtn);
		selectByVisibleText(dropdownType, errorText);
		clickOn(searchBtn);
		waitForPageToLoad("3");
		findElements(errorWarningRadioBtnValue, dropdownType);
		return Integer.parseInt(NoOfErrorsFoundMsg.getText().trim().split(" ")[0]);
	}
	
	private void startErrorWarningProcessing(EventFiringWebDriver ieDriver, String errorText) throws Exception{
		//logger.info("*** ErrorsWarningsPage -> startErrorWarningProcessing method started ***");
		EventFiringWebDriver chromeDriver = getDriver();
		String city = this.firstRowCity.getText();
		handleError(ieDriver, city, errorText);
		if(errorNotHandled.equals("No")){
			setWebDriver(chromeDriver);
			setPageWrapper(chromeDriver);
			driver = getDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.focus();");
			Page_Home pageHome = new Page_Home(driver);
			pageHome.clickPlanningLink();
		
			PlanningMenu planningMenu = new PlanningMenu(driver);
			planningMenu.clickBulkAssignLink();
		
			Page_BulkAssign bulkAssignPage = new Page_BulkAssign(driver);
			bulkAssignPage.bulkAssignPairings("Yes", "Yes", "Yes");
			planningMenu.clickErrorsWarningsLink();
		}
		//logger.info("*** startErrorWarningProcessing method completed ***");
	}
	
	private void handleError(EventFiringWebDriver ieDriver, String city, String errorText) throws Exception{
		if(errorText.equals("All"))
			errorNotHandled = "Yes";
		else{		
			setWebDriver(ieDriver);
			setPageWrapper(ieDriver);
			ErrorsAndWarningsHandler errorWarningHandler = new ErrorsAndWarningsHandler(ieDriver);
			errorWarningHandler.changeSupplierDetails(city, errorText);
		}
		
	}
	
	public void searchAndProcessErrorsAndWarnings(String city) throws Exception{
		//logger.info("*** ErrorsWarningsPage -> searchAndProcessErrorsAndWarnings method started ***");
		for(int errorWarningRadioBtnValue = 0 ; errorWarningRadioBtnValue < 2; errorWarningRadioBtnValue++){
			errorTypeRadioBtn = findElementByXpath(errorTypeXpath1+errorWarningRadioBtnValue+errorTypeXpath2);
			WebElement dropdownType = null;
			if(errorTypeRadioBtn.getAttribute("value").equals("1"))
				dropdownType = errorCode;
			else if(errorTypeRadioBtn.getAttribute("value").equals("0"))
				dropdownType = warningCode;
			else if(errorTypeRadioBtn.getAttribute("value").equals("2"))
				continue;
			List<WebElement> options = new Select(dropdownType).getOptions();
			List<String> errorWarningsText = new ArrayList<String>();
			options.forEach(option -> errorWarningsText.add(option.getText()));
			for(String errorText : errorWarningsText){
				errorNotHandled = "No";
				int resultRows = searchForErrorWarning(errorWarningRadioBtnValue, dropdownType, city, errorText);
				if(resultRows>0){
					startErrorWarningProcessing(errorText);
					findElements(errorWarningRadioBtnValue, dropdownType);
				}
			}
		}
	}
	
	private void startErrorWarningProcessing(String errorType) throws Exception{
		//logger.info("*** ErrorsWarningsPage -> startErrorWarningProcessing method started ***");
		
		String city = this.firstRowCity.getText();
		char c[] = errorType.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		errorType = new String(c);
		errorType = errorType.replace(" ", "");
		if(errorType.contains(":"))
			errorType = errorType.replace(":", "");
		handleError(errorType, city);
		
		if(errorNotHandled.equals("No")){
			Page_Home pageHome = new Page_Home(driver);
			pageHome.clickPlanningLink();
		
			PlanningMenu planningMenu = new PlanningMenu(driver);
			planningMenu.clickBulkAssignLink();
		
			Page_BulkAssign bulkAssignPage = new Page_BulkAssign(driver);
			bulkAssignPage.bulkAssignPairings("", "Yes", "Yes", "No");
			
			planningMenu.clickErrorsWarningsLink();
		}
		//logger.info("*** startErrorWarningProcessing method completed ***");
	}

	private void handleError(String errorType, String city){
		//logger.info("*** Planning_ErrorsWarningsPage -> handleErrorType method started ***");
		try{
		Class cls = Class.forName("com.APIHotels.ACESII.pageobjects.ErrorsAndWarningsHandler");
		Constructor con = cls.getDeclaredConstructor(WebDriver.class);
		Object obj = con.newInstance(driver);
		Method method = cls.getDeclaredMethod(errorType, String.class);
		method.invoke(obj, city);
		System.out.println("tested");
		}catch(NoSuchMethodException e){
			System.out.println("Error Warning Type: "+ errorType+" not handled!");
			errorNotHandled = "Yes";
		} catch (ClassNotFoundException e) {
			System.out.println("Error Warning Type: "+ errorType+" not handled!");
			errorNotHandled = "Yes";
		} catch (InstantiationException e) {
			System.out.println("Error Warning Type: "+ errorType+" not handled!");
			errorNotHandled = "Yes";
		} catch (IllegalAccessException e) {
			System.out.println("Error Warning Type: "+ errorType+" not handled!");
			errorNotHandled = "Yes";
		} catch (IllegalArgumentException e) {
			System.out.println("Error Warning Type: "+ errorType+" not handled!");
			errorNotHandled = "Yes";
		} catch (InvocationTargetException e) {
			System.out.println("Error Warning Type: "+ errorType+" not handled!");
			errorNotHandled = "Yes";
		}
	}

	private int searchForErrorWarning(int errorWarningRadioBtnValue, WebElement dropdownType, String city, String errorText) throws Exception{
		//logger.info("*** ErrorsWarningsPage -> searchForErrorWarning method started ***");
		typeInText(this.city, city);
		clickOn(errorTypeRadioBtn);
		selectByVisibleText(dropdownType, errorText);
		clickOn(searchBtn);
		waitForPageToLoad("3");
		findElements(errorWarningRadioBtnValue, dropdownType);
		return Integer.parseInt(NoOfErrorsFoundMsg.getText().trim().split(" ")[0]);
	}

	private void findElements(int errorWarningRadioBtnValue, WebElement dropdownType) {
		
		errorTypeRadioBtn = findElementByXpath(errorTypeXpath1+errorWarningRadioBtnValue+errorTypeXpath2);
		if(errorWarningRadioBtnValue == 1)
			dropdownType = errorCode;
		else if(errorWarningRadioBtnValue == 0)
			dropdownType = warningCode;
			
	}
	
	public int searchErrorsAndWarningsUsingBidPeriodAndCity(String bidMonth, String cityCode){
		selectByVisibleText(bidPeriod, bidMonth,"Errors and Warnings Page -> Bid Month");
		typeInText(city, cityCode, "Errors and Warnings Page -> City");
		//clickOn(findElementById(errorTypeXpath1+1+errorTypeXpath2));
		clickOn(searchBtn);
		waitForPageToLoad("3");
		return Integer.parseInt(NoOfErrorsFoundMsg.getText().trim().split(" ")[0]);
	}
}
