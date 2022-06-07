package com.APIHotels.listeners;

import java.io.IOException;
import java.util.Scanner;

import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.Utils;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class CustomTestListenerAdapter extends TestListenerAdapter {

	@Override
	public void onTestStart(ITestResult tr) {
		log("Test Started...." + Thread.currentThread().getId());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		try{
		log("Test '" + tr.getName() + "' PASSED");
		log(tr.getTestClass());
		ExtentTestManager.getTest().log(LogStatus	.PASS, "Success");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//@Override
	/*public void onTestFailure(ITestResult tr) {
        try {
        	log("Test '" + tr.getName() + "' FAILED"); //tr.getName gives test method Name
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "FAILED");
        	Driver.takeScreenshots(tr.getName()); 
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
        System.out.println(".....");
        }
	}*/
	
	@Override
	public void onTestFailure(ITestResult tr) {
		String firstLine="";
		String str ="";
		Driver dr = new Driver();
		try {
			Throwable exception=tr.getThrowable();
			boolean hasThrowable = exception != null;
			if(hasThrowable){
				 str = Utils.shortStackTrace(exception, true); //.stackTrace(exception, true)[0];
				 Scanner scanner = new Scanner(str);
				firstLine = scanner.nextLine();
			}
			log("Test '" + tr.getName() + "' FAILED");
			ExtentTestManager.getTest().log(LogStatus.FAIL, firstLine);
			dr.takeScreenshots(tr.getName()); // for taking and showing screenshot on reports)
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log("Test '" + tr.getName() + "' SKIPPED");
		System.out.println(".....");
	}
	
	private void log(Throwable throwable) {
		System.out.println(throwable);
	}

	private void log(String methodName) {
		System.out.println(methodName);
	}

	private void log(IClass testClass) {
		System.out.println(testClass);
	}
}