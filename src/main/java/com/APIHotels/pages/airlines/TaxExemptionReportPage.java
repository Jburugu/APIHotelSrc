package com.APIHotels.pages.airlines;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class TaxExemptionReportPage extends BasePage{
	
	public EventFiringWebDriver driver=null;
		// REPORTS -- ACCOUNTING REPORTS -- Tax Exemption Report

		public TaxExemptionReportPage(EventFiringWebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public void taxExemption() {
						
			unExpectedDismissAlert();
			unExpectedDismissAlert();
			windowHandler();
		}


	}

