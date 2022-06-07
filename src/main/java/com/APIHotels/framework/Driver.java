package com.APIHotels.framework;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.APIHotels.listeners.TestEventHandler;
import com.APIHotels.log4j.Log4jPattern;
import com.relevantcodes.extentreports.LogStatus;

public class Driver extends FrameworkBase {

	protected Logger logger;
	protected WebDriver wbdriver;

	public EventFiringWebDriver createDriverInstance(String browserName, String URL) {
		wbdriver = null;
		EventFiringWebDriver driver = null;
		try {
			switch (browserName) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
				FirefoxOptions opt = new FirefoxOptions();
				opt.addPreference("download.default_directory",downloadFilepath);
				opt.addPreference("download.prompt_for_download", "false");
				opt.addPreference("browser.download.folderList",0);
				opt.addPreference("browser.helperApps.neverAsk.saveToDisk","application/xml");
				opt.addPreference("safebrowsing.enabled", "true");
		        wbdriver = new FirefoxDriver(opt);
				break;
			case "ie":
				DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
				cp.setJavascriptEnabled(true);
				cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cp.setCapability("requireWindowFocus", true);
				Properties pr = System.getProperties();
				String osType = pr.getProperty("os.arch");
				if (osType.equals("amd64")) {
					System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
				} else {
					System.setProperty("webdriver.ie.driver", IE_DRIVER_2_53_1_PATH);
				}
				wbdriver = new InternetExplorerDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
				wbdriver = new ChromeDriver();
				break;
			default:
				throw new RuntimeException("Invalid browser");
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println(e);
		}
		driver = new EventFiringWebDriver(wbdriver);
		eventListener = new TestEventHandler(wbdriver);
		driver.register(eventListener);
		driver.manage().window().maximize();
		logger = Logger.getLogger(Log4jPattern.class);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt((readProp().getProperty("Timeout"))),
				TimeUnit.SECONDS);
		driver.get(URL);
		return driver;

	}

	public EventFiringWebDriver createDriverInstance(String nodeUrl, String browserName, String URL) {
		wbdriver = null;
		EventFiringWebDriver driver = null;
		if (browserName.equals("headless")) {
			System.getProperty("webdriver.htmlunit.driver", Constants.HTMLUNIT_DRIVER_PATH);
			wbdriver = new HtmlUnitDriver(true);
		} else {
			try {
				wbdriver = new RemoteWebDriver(new URL(nodeUrl), getBrowserCapabilities(browserName));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		driver = new EventFiringWebDriver(wbdriver);
		eventListener = new TestEventHandler(wbdriver);
		driver.register(eventListener);
		driver.manage().window().maximize();
		logger = Logger.getLogger(Log4jPattern.class);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt((readProp().getProperty("Timeout"))),
				TimeUnit.SECONDS);
		driver.get(URL);
		return driver;
	}

	private DesiredCapabilities getBrowserCapabilities(String browserType) {
		switch (browserType) {
		case "firefox":
			System.out.println("Opening firefox driver");
			return DesiredCapabilities.firefox();
		case "chrome":
			System.out.println("Opening chrome driver");
			String downloadFilepath = path + File.separator + "download_files";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("safebrowsing.enabled", true);
			chromePrefs.put("plugins.always_open_pdf_externally", true);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cpChrome = DesiredCapabilities.chrome();
			cpChrome.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cpChrome.setCapability(ChromeOptions.CAPABILITY, options);
			return cpChrome;
		case "ie":
			System.out.println("Opening IE driver");
			DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
			cp.setJavascriptEnabled(true);
			cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cp.setCapability("requireWindowFocus", false);
			return cp;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			return DesiredCapabilities.firefox();
		}
	}

	public void takeScreenshots(String m) throws IOException {
		try {
			File scrFile = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			Calendar cal = Calendar.getInstance();
			String screenshotPath = path + File.separator + "reports" + File.separator + "screenshots" + File.separator
					+ "Build No-" + readProp().getProperty("BuildNumber") + "-" + getCurDate() + "_" + getCurTime()
					+ File.separator + m + "-" + cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.MINUTE) + "-"
					+ cal.get(Calendar.SECOND) + ".png";
			FileUtils.copyFile(scrFile, new File(screenshotPath));
			Reporter.log("<img src='" + screenshotPath + "'/><br>");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Log from threadId: " + Thread.currentThread().getId() + "-"
					+ "Error Snapshot below: " + FrameworkBase.extReportTest.addScreenCapture(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());// added for logging
		}
	}

	/*
	 * @AfterMethod public void afterTest(){ EventFiringWebDriver driverclose =
	 * LocalDriverManager.getDriver(); if (driverclose != null){
	 * //driverclose.close(); driverclose.quit(); }
	 * 
	 * }
	 */
}
