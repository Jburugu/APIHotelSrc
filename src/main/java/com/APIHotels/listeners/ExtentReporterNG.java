package com.APIHotels.listeners;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.APIHotels.framework.Driver;
import com.APIHotels.framework.ExtentManager;
import com.APIHotels.framework.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
 
public class ExtentReporterNG extends Driver implements IReporter{
	
	
      	
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        ExtentManager.getReporter(filePath).endTest(ExtentTestManager.getTest());        
        ExtentManager.getReporter(filePath).flush();
        /*try {
			ComposeGmail();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
    }
 
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                extReportTest.setStartedTime(getTime(result.getStartMillis()));
                extReportTest.setEndedTime(getTime(result.getEndMillis()));
            }
        }
    }
 
   private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
}
