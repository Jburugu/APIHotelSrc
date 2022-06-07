package com.APIHotels.pages.airlines;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class FindInvoicesPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// ACCOUNTING -- HOTEL -- FIND INVOICES

	public FindInvoicesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	public void findInvoices() {
		/*String winHandleBefore = driver.getWindowHandle();
		clickOn(findInvoicesLink);
		driver.switchTo().window(winHandleBefore);
		//driver.switchToWindow(winHandleBefore);
*/
		unExpectedDismissAlert();
		unExpectedDismissAlert();
		windowHandler();
	}

}
