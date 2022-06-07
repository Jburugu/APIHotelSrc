package com.APIHotels.framework;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends Driver {
     
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }
        return extent;
    }
}
