package com.APIHotels.framework;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;

import com.APIHotels.pages.generic.PgWrapper;

public class LocalDriverManager extends FrameworkBase{
    private static ThreadLocal<EventFiringWebDriver> webDriver = new ThreadLocal<EventFiringWebDriver>();    
    private static ThreadLocal<PgWrapper> PgWrapper = new ThreadLocal<PgWrapper>();
 
    public static EventFiringWebDriver getDriver() {
        return webDriver.get();
    }
 
    public static void setWebDriver(EventFiringWebDriver driver) {
        webDriver.set(driver);
    }
    
    public static PgWrapper getPageWrapper() {
    	return PgWrapper.get();
    	
    }
    
    public static void setPageWrapper(EventFiringWebDriver driver) {
    	PgWrapper pg = new PgWrapper(driver);
    	PgWrapper.set(pg);
    }
    
    @AfterMethod
    public void afterTest(){
		EventFiringWebDriver driverclose = LocalDriverManager.getDriver();
        if (driverclose != null){
        	//driverclose.close();
        	driverclose.quit();
       }
		
}
   
}