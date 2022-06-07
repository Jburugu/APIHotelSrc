package com.APIHotels.listeners;

import java.io.File;

public class Constants {
	
		public static String path= System.getProperty("user.dir");
		public static String REPORT_PATH = path + File.separator + "reports";
		public static String IE_DRIVER_PATH = path + File.separator +"jars\\IEDriverServer.exe";
		public static String IE_DRIVER_2_53_1_PATH = path + File.separator +"jars\\IEDriverServer_Win32_2.53.1.exe";
		public static String CHROME_DRIVER_PATH = path + File.separator +"jars\\chromedriver.exe";
		public static String FIREFOX_DRIVER_PATH = path + File.separator +"jars\\geckodriver.exe";
		public static String PROPERTIES_FILE_PATH = path + File.separator +"prop.properties";
		public static String ACESII_CONGIG_FILE_PATH = path + File.separator +"ACESII_Config.xlsx";
		public static String AIRLINES_CONGIG_FILE_PATH = path + File.separator +"Airlines_Config.xlsx";
		public static String acesPkg = "com.APIHotels.tests.ACESII.";
		public static String airlinesPkg = "com.APIHotels.tests.airlines.";
		public static String CUSTOM_TEST_LISTENER_ADAPTER = "com.APIHotels.listeners.CustomTestListenerAdapter";
		public static String WEBDRIVER_THREAD_LISTENER = "com.APIHotels.listeners.WebDriverThreadListener";
		public static String EXTENT_REPORTERNG_LISTENER = "com.APIHotels.listeners.ExtentReporterNG";
}
