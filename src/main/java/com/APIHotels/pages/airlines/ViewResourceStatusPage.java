package com.APIHotels.pages.airlines;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class ViewResourceStatusPage extends BasePage{

	public EventFiringWebDriver driver=null;
	// ADMIN -- VIEW RESOURCE STATUS(AIRCANADA/COMMUT AIR)

	@FindBy(how = How.ID, using = "iconadminResource")
	public WebElement viewResourceStatusLink;

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Monitored Resource Status ')]")
	public WebElement monitoredResourceStatusTitle;

	public ViewResourceStatusPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void viewResourceStatus() {

		clickOn(viewResourceStatusLink);
		waitForElementVisibility(monitoredResourceStatusTitle);
	}

}
