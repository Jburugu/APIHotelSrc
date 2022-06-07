package com.APIHotels.listeners;

import java.io.IOException;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.Utilities.XlsWorkbook;
import com.APIHotels.framework.Driver;
import com.APIHotels.framework.ExtentTestManager;
import com.APIHotels.framework.LocalDriverManager;
import com.relevantcodes.extentreports.LogStatus;

public class WebDriverThreadListener extends Driver implements IInvokedMethodListener {
	String oldMethodName = "";
	int rowNo = 0;
	    @Override
	    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	        	    	
	    	if (method.isTestMethod()) {
	            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");	        	
	            URL = method.getTestMethod().getXmlTest().getAllParameters().get("URL");
	            nodeURL = method.getTestMethod().getXmlTest().getAllParameters().get("NodeUrl"); 
	            setReports(method);
	            String xlFileName = method.getTestMethod().getTestClass().getName().split("\\.")[4]+".xlsx";
	            String xlSheetName = method.getTestMethod().getMethodName();
	            try {
	            	if(!oldMethodName.equals(xlSheetName)){
	            		oldMethodName = xlSheetName;
	            		rowNo = 1;
	            	}else
	            		rowNo++;
					String runMode = getRunMode(xlFileName, xlSheetName, rowNo);
					System.out.println();
					if(runMode.equals("Yes")){
						EventFiringWebDriver driver = null;
			            Driver dr = new Driver();
			            driver = dr.createDriverInstance(nodeURL, browserName.toLowerCase(), URL); 
			            LocalDriverManager.setWebDriver(driver);	                        
			            LocalDriverManager.setPageWrapper(driver);
					}else{
						ExtentTestManager.getTest().log(LogStatus.SKIP, "Skipped");
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
	            
	        }
	        else if (method.isConfigurationMethod()) {
	        	System.out.println("before invocation " + method.getTestMethod().getMethodName());
	    	}
	    }
	 
	    private String getRunMode(String xlFileName, String xlSheetName, int rowNo) throws IOException {
	    	String runMode = "";
	    	XlsWorkbook workbook = new XlsWorkbook(testData_PATH+xlFileName);
			DataTable table = workbook.getTestDataTable(xlSheetName);
			int colNo =  table.getColumnNumberByName("runMode");
			if(colNo >= 0)
				runMode = table.getFieldValue(rowNo, "runMode");
			else
				runMode = "Yes";
			return runMode;
		}
		@Override
	    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	        if (method.isTestMethod()) {
	        	EventFiringWebDriver driverclose = LocalDriverManager.getDriver();
	            if (driverclose != null) {
	            }
	        	
	        }
	    }
}
