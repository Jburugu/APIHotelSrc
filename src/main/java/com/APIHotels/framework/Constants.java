package com.APIHotels.framework;

import java.io.File;

public class Constants {
	
		public static String path= System.getProperty("user.dir");
		public static String REPORT_PATH = path + File.separator + "reports";
		public static String IE_DRIVER_PATH = path + File.separator +"jars"+ File.separator +"IEDriverServer.exe";
		public static String IE_DRIVER_2_53_1_PATH = path + File.separator +"jars"+ File.separator +"IEDriverServer_Win32_2.53.1.exe";
		public static String CHROME_DRIVER_PATH = path + File.separator +"jars"+ File.separator +"chromedriver.exe";
		//public static String FIREFOX_DRIVER_PATH = path + File.separator +"jars"+ File.separator +"geckodriver.exe";
		public static String PROPERTIES_FILE_PATH = path + File.separator +"prop.properties";
		public static String ACESII_CONGIG_FILE_PATH = path + File.separator +"ACESII_Config.xlsx";
		public static String AIRLINES_CONGIG_FILE_PATH = path + File.separator +"Airlines_Config.xlsx";
		public static String acesPkg = "com.APIHotels.tests.ACESII.";
		public static String airlinesPkg = "com.APIHotels.tests.airlines.";
		public static String aces3Pkg = "com.APIHotels.tests.ACES3.";
		public static String CUSTOM_TEST_LISTENER_ADAPTER = "com.APIHotels.listeners.CustomTestListenerAdapter";
		public static String WEBDRIVER_THREAD_LISTENER = "com.APIHotels.listeners.WebDriverThreadListener";
		public static String EXTENT_REPORTERNG_LISTENER = "com.APIHotels.listeners.ExtentReporterNG";
		public static String Invoice_Number = path + File.separator +"testdata"+ File.separator +"testout.txt";
		public static String FIREFOX_DRIVER_PATH = path + File.separator +"jars"+File.separator +"geckodriver.tar";
		public static String HTMLUNIT_DRIVER_PATH = path + File.separator +"jars"+File.separator+"htmlunitdriver.exe";
		public static String downloadFilepath = path+ File.separator +"download_files";
		public static String libPath = path+ File.separator+"lib";
		public static String URL, nodeURL, dbIP, dbPort, dbUserId, dbPassword, sftpHost, sftpUser, sftpPassword;
		public static String aces2Url = "", airlinesUrl = "", aces3AcesUrl = "", aces3ClientUrl = "", aces3SupplierUrl = "", latamUrl = "", aces2SupplierUrl= "", acesLatamUrl= "";
		public static String testData_PATH = path + File.separator +"testdata"+ File.separator;
		public static String autoprocessingFilesPath= path+ File.separator +"AutoProcessing_Files";
		public static String gtOptimizationFilesPath= path+ File.separator +"GTOptimization_Files";
		public static String integrationAFFilePath =path+ File.separator +"AFIntegrationsFiles";
}
