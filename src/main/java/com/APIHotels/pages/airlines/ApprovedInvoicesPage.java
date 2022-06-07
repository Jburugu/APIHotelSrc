package com.APIHotels.pages.airlines;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ApprovedInvoicesPage extends BasePage{
	
	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- APPROVED INVOICES

	public ApprovedInvoicesPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	public void approvedInvoices() {
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
