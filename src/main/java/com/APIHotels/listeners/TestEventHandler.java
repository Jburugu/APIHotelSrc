package com.APIHotels.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class TestEventHandler extends Driver implements WebDriverEventListener {
	public int stepNo = 0;

	public TestEventHandler(WebDriver wbdriver) {
		this.wbdriver = wbdriver;
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		stepNo=stepNo+1;
		log.info(stepNo+ " " + Thread.currentThread().getId() + arg0.toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString().split("->")[1]);

	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		stepNo=stepNo+1;
		log.info(stepNo + " " + Thread.currentThread().getId() + arg0.toString());
		//ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on ->"+arg0.toString().split("->")[1]);
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0);
	//	ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - " +arg0.getCurrentUrl());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getCurrentUrl());
	}

	// private WebElement element;

	/*
	 * @Override public String toString() { return element.toString(); }
	 */

	public void afterNavigateForward(WebDriver arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + "-	Navigated Forward to " + arg0.getCurrentUrl());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getCurrentUrl());
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - Navigated to " + arg0);
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	public void afterScript(String arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0);
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.getAttribute("value"));
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		/*stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "arg0);
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());*/

	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		/*stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "arg0.toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());*/

	}

	public void beforeNavigateBack(WebDriver arg0) {
		/*stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "arg0.getCurrentUrl());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getCurrentUrl());*/
	}

	public void beforeNavigateForward(WebDriver arg0) {
		/*stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "arg0.getCurrentUrl());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getCurrentUrl());*/
	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
	/*	stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - ""-	Navigated to " + arg0);
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0);*/
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.toString());
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.getCurrentUrl());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getCurrentUrl());
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - " +arg0.getCurrentUrl());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getCurrentUrl());
	}

	public void onException(Throwable arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - Exception occured at " + arg0.getMessage());
		if(!arg0.toString().contains("StaleElementReference"))
			ExtentTestManager.getTest().log(LogStatus.FAIL, arg0.getMessage());
	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " -  " + Thread.currentThread().getId() +arg0.toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - " + Thread.currentThread().getId() +arg0.toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.getAttribute("value"));
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0);
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());

	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.getText());
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getText());
	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0);
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0);

	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		/*stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - " + Thread.currentThread().getId() +arg0.toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());*/

	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
	/*	stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - " + Thread.currentThread().getId() +arg0.toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());*/

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.getAttribute("value"));
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0);
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.toString());

	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "+arg0.getText());
		//ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getText());

	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		/*stepNo = stepNo + 1;
		log.info("Step No: " + stepNo + " - "arg0);
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0);*/
	}
}
