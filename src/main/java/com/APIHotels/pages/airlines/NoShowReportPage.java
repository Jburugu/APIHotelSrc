package com.APIHotels.pages.airlines;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class NoShowReportPage extends BasePage {
	
	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- Tax Exemption Report

	public NoShowReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void noShowReport() {
		
		unExpectedDismissAlert();
		unExpectedDismissAlert();
		windowHandler();
	}


}


