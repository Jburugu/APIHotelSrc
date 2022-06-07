package com.APIHotels.pages.airlines;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class CrewIDIssueReportPage extends BasePage{
	
	public EventFiringWebDriver driver=null;
	// REPORTS -- ACCOUNTING REPORTS -- Tax Exemption Report

	public CrewIDIssueReportPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void crewIDIssueReport() {
		
		unExpectedDismissAlert();
		unExpectedDismissAlert();
		windowHandler();
	}


}
