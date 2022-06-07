package com.APIHotels.framework;

import static org.testng.Assert.fail;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.APIHotels.pages.ACESII.Page_Login_ACESII;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage extends LocalDriverManager {

	// private boolean acceptNextAlert = true;
	protected WebDriverWait wait;
	protected JavascriptExecutor javascriptExecutor;
	// public WebDriver wbdriver = null;

	@FindBy(how = How.XPATH, using = "//img[@class='tenant_logo']/parent::*/b")
	private WebElement tenantUserName;

	@FindBy(how = How.ID, using = "loginForm:usernameField:username")
	private WebElement airlineUserName;

	@FindBy(how = How.ID, using = "loginForm:passwordField:password")
	private WebElement airlinePassword;

	@FindBy(how = How.ID, using = "loginForm:login")
	private WebElement airineLogin;

	@FindBy(how = How.ID, using = "j_username")
	private WebElement acesIIUserName;

	@FindBy(how = How.ID, using = "j_password")
	private WebElement acesIIPassword;

	@FindBy(how = How.NAME, using = "submit")
	private WebElement acesIILoginBtn;

	EventFiringWebDriver driver2 = null;

	/**
	 * 
	 */
	public BasePage() {
		super();
		this.initialiseWaits();
	}

	/**
	 * @param driver
	 * @throws Exception
	 */
	public BasePage(EventFiringWebDriver driver) throws Exception {
		PageFactory.initElements(driver, this);
	}

	protected void initialiseWaits() {
		wait = new WebDriverWait(getDriver(), 5);
		wait.withTimeout(Duration.ofMinutes(1));
		wait.pollingEvery(Duration.ofMillis(60));
		wait.ignoring(StaleElementReferenceException.class);
	}

	/*
	 * protected void findElement(WebElement element){ FluentWait<WebDriver>
	 * wait3 = new FluentWait<WebDriver>(wbdriver)
	 * .withTimeout(Duration.ofSeconds(120)) .pollingEvery(Duration.ofMillis(5))
	 * .ignoring(NoSuchElementException.class).ignoring(
	 * StaleElementReferenceException.class);
	 * wait3.until(ExpectedConditions.visibilityOf(element)); }
	 */

	/**
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void airlineLoginDetails(String userName, String password) throws Exception {
		typeInText(airlineUserName, userName);
		typeInText(airlinePassword, password);
		clickOn(airineLogin);
	}

	/**
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void acesIILoginDetails(String userName, String password) throws Exception {
		typeInText(acesIIUserName, userName);
		typeInText(acesIIPassword, password);
		clickOn(acesIILoginBtn);
	}

	public void typeInText(WebElement element, String input) {
		waitForElementVisibility(element);
		element.clear();
		element.sendKeys(input);
		String elementText = element.getAttribute("name");
		ExtentTestManager.getTest().log(LogStatus.PASS, "typed in text: " + input + " in element: " + elementText);
	}

	public void typeInText(WebElement element, String input, String label) {
		waitForElementVisibility(element);
		element.clear();
		element.sendKeys(input);
		// String elementText = element.getAttribute("name");
		ExtentTestManager.getTest().log(LogStatus.PASS, "typed in text: " + input + " in element: " + label);
	}

	public void typeInTextIfInputNotEmpty(WebElement element, String input) {
		waitForElementVisibility(element);
		if (!input.isEmpty()) {
			element.clear();
			element.sendKeys(input);
			String elementText = element.getAttribute("name");
			ExtentTestManager.getTest().log(LogStatus.PASS, "typed in text: " + input + " in element: " + elementText);
		}
	}

	public void typeInTextIfInputNotEmpty(WebElement element, String input, String label) {
		waitForElementVisibility(element);
		if (!input.isEmpty()) {
			element.clear();
			element.sendKeys(input);
			// String elementText = element.getAttribute("name");
			ExtentTestManager.getTest().log(LogStatus.PASS, "typed in text: " + input + " in element: " + label);
		}
	}

	public void clickOn(WebElement element) {
		waitForElementVisibility(element);
		element.click();
		unExpectedAcceptAlert();
		// log.info("Clicked on: " +element.getText());
	}

	public void clickOn(WebElement element, String label) {
		waitForElementVisibility(element);
		element.click();
		unExpectedAcceptAlert();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on: " + label);
	}

	public void clickOnElement(WebElement element, WebElement element2) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public String getTitle() {
		return getDriver().getTitle();
	}

	public void close() {
		getDriver().close();
	}

	public void open(String url) {
		getDriver().get(url);
	}

	public void unExpectedAcceptAlert() {
		try {
			Alert alt = getDriver().switchTo().alert();
			alt.accept();
		} catch (Exception e) {
			// System.out.println("Message on Alert" + e);
		}
	}

	public void unExpectedDismissAlert() {
		try {
			Alert alt = getDriver().switchTo().alert();
			alt.dismiss();
		} catch (Exception e) {
			// System.out.println("Message on Alert" + e);
		}
	}

	/*
	 * public void waitForElementVisibility(Object element, String
	 * timeoutinseconds) { WebElement ele = (WebElement) element; //String
	 * messageStr = ""; try {
	 * 
	 * WebDriverWait wait = new
	 * WebDriverWait(wbdriver,Long.parseLong(timeoutinseconds));
	 * wait.until(ExpectedConditions.visibilityOf(ele)); messageStr =
	 * "Waited for visibility of an element" +
	 * CosmeticsEffects.formatString(element.toString());
	 * reporter.LogPass(messageStr); //logger.Log(LogType.INFO, messageStr); }
	 * catch (Exception e) { //
	 * reporter.LogFailwithScreenCapture(ExceptionType.Application,
	 * e.getMessage(), takeScreenshot());
	 * ExtentTestManager.getTest().log(LogStatus.FAIL,
	 * "Element no found: "+ele.toString().split("->")[1]); } }
	 */

	public void waitForElementVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public ExpectedCondition<WebElement> waitForElementVisibility(final By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				return element.isDisplayed() ? element : null;
			}
		};
	}

	public void waitForElementTextVisibility(WebElement element, String attribute) {
		wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
	}

	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void dynamicWaitForTextVisibility(String elementId, int maxTime) {
		maxTime = maxTime * 1000;
		boolean flag = true;
		while (flag) {
			try {
				WebElement element = getDriver().findElement(By.id(elementId));
				if (element.getAttribute("value") != null || maxTime == 0)
					flag = false;
			} catch (Exception e) {
			}

			maxTime--;
		}

	}

	/*
	 * private boolean isAlertPresent() { try { getDriver().switchTo().alert();
	 * return true; } catch (NoAlertPresentException e) { return false; } }
	 * 
	 * private String closeAlertAndGetItsText() { try { Alert alert =
	 * getDriver().switchTo().alert(); String alertText = alert.getText(); if
	 * (acceptNextAlert) { alert.accept(); } else { alert.dismiss(); } return
	 * alertText; } finally { acceptNextAlert = true; } }
	 */

	/**
	 * @param element
	 * @param valueToSelect
	 */
	public void selectByValue(WebElement element, String valueToSelect) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByValue(valueToSelect);
	}

	public void selectByValue(WebElement element, String valueToSelect, String label) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByValue(valueToSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Selected by Value: " + valueToSelect + " in element: " + label);
	}

	/**
	 * @param element
	 * @param valueToSelect
	 */
	public void selectByIndex(WebElement element, int valueToSelect) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByIndex(valueToSelect);
	}

	public void selectByIndex(WebElement element, int valueToSelect, String label) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByIndex(valueToSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Selected by Index: " + valueToSelect + " in element: " + label);
	}

	/**
	 * @param element
	 * @param valueToSelect
	 */
	public void selectByVisibleText(WebElement element, String valueToSelect) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByVisibleText(valueToSelect);

	}

	public void selectByVisibleText(WebElement element, String valueToSelect, String label) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByVisibleText(valueToSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Selected by Visible Text: " + valueToSelect + " in element: " + label);
	}

	/**
	 * @param element
	 * @param valueToSelect
	 */
	public void deselectByValue(WebElement element, String valueToSelect, String label) {
		waitForElementVisibility(element);
		Select sel = new Select(element);
		sel.deselectByValue(valueToSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Deselected by Visible Text: " + valueToSelect + " in element: " + label);
	}

	public void windowHandler() {
		String winHandleBefore = getDriver().getWindowHandle();
		// Perform the click operation that opens new window

		// Switch to new window opened
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}

		// Perform the actions on new window
		// Close the new window, if that window no more required
		getDriver().close();

		// Switch back to original browser (first window)
		getDriver().switchTo().window(winHandleBefore);
	}

	
	public void switchToWindow() {
		// logger.info("BasePage -> switchToWindow");
		for (String winHandle : getDriver().getWindowHandles())
			getDriver().switchTo().window((winHandle));
		// logger.info("*** switchToWindow method completed ***");
	}

	public void switchToWindow(String windowHandle) {
		// logger.info("BasePage -> switchToWindow method started");
		getDriver().switchTo().window(windowHandle);
		// logger.info("*** switchToWindow method completed ***");
	}

	public void switchToNewWindow(String windowHandle) {
		// logger.info("BasePage -> switchToNewWindow");
		for (String winHandle : getDriver().getWindowHandles()) {
			int i = 1;
			System.out.println("iteration " + i + ": " + winHandle);
			if (!winHandle.equals(windowHandle)) {
				getDriver().switchTo().window((winHandle));
				break;
			}
			i++;
		}
		// logger.info("*** switchToWindow method completed ***");
	}

	public void switchToNewWindow(String windowHandle1, String windowHandle2) {
		// logger.info("BasePage -> switchToNewWindow");
		for (String winHandle : getDriver().getWindowHandles()) {
			int i = 1;
			System.out.println("iteration " + i + ": " + winHandle);
			if (!winHandle.equals(windowHandle1) && !winHandle.equals(windowHandle2)) {
				getDriver().switchTo().window((winHandle));
				break;
			}
			i++;
		}
		// logger.info("*** switchToWindow method completed ***");
	}

	public void switchToNewWindow(String windowHandle, int timeOut) {
		// logger.info("BasePage -> switchToNewWindow");
		Set<String> windowHandles = null;
		for (int i = 0; i < timeOut; i++)
			windowHandles = getDriver().getWindowHandles();

		for (String winHandle : windowHandles) {
			int i = 1;
			System.out.println("iteration " + i + ": " + winHandle);
			if (!winHandle.equals(windowHandle)) {
				getDriver().switchTo().window((winHandle));
				break;
			}
			i++;
		}
		// logger.info("*** switchToWindow method completed ***");
	}

	public void waitForPageLoad(WebDriver wbdriver) {
		while (true) {
			String page_status = (String) ((JavascriptExecutor) wbdriver).executeScript("return document.readyState");
			if (page_status.equals("complete")) {
				break;
			}
		}
	}

	public String getTenantUserName() throws Exception {
		return tenantUserName.getText();
	}

	public String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");
		Date date = new Date();
		String dateValue = dateFormat.format(date);
		return dateValue.toString();

	}

	public void waitForPageToLoad(String timeOutInSeconds) {
		String windowTitle = getDriver().getTitle();
		int time = Integer.parseInt(timeOutInSeconds);
		int pageLength = 0;
		for (int second = 0;; second++) {
			if (second >= time) {
				fail("Timeout... Page load could not complete in " + timeOutInSeconds + " seconds");
			}
			if (pageLength == getDriver().getPageSource().length() && windowTitle != getDriver().getTitle()) {
				break;
			}
			// Sleeper.sleepTight(500);
			pageLength = getDriver().getPageSource().length();
		}
	}

	public void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils.copyFile(scrFile, new File("D:\\screenshots\\screenshot_" + System.currentTimeMillis() + ".png"));
	}

	// MMM yyyy- JAN 2019
	public String getCurrentMonthAndYear() {
		Date objDate = new Date(); // Current System Date and time is assigned
									// to objDate
		// System.out.println(objDate);
		String strDateFormat = "MMM yyyy"; // Date format is Specified
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // Date
																		// format
																		// string
																		// is
																		// passed
																		// as an
																		// argument
																		// to
																		// the
																		// Date
																		// format
																		// object
		System.out.println(objSDF.format(objDate).toString().toUpperCase());
		return objSDF.format(objDate).toString().toUpperCase();
	}

	public String getDateInFormat(String format) {
		Date objDate = new Date(); // Current System Date and time is assigned
									// to objDate
		SimpleDateFormat objSDF = new SimpleDateFormat(format); // Date format
																// string is
																// passed as an
																// argument to
																// the Date
																// format object
		System.out.println(objSDF.format(objDate).toString());
		return objSDF.format(objDate).toString();
	}

	public WebElement findElementById(String id) {
		// logger.info("*** BasePage -> findElementById ***");
		return getDriver().findElement(By.id(id));
	}

	public WebElement findElementByName(String name) {
		return getDriver().findElement(By.name(name));
	}

	public WebElement findElementByXpath(String xpath) {
		// logger.info("*** BasePage -> findElementByXpath ***");
		return getDriver().findElement(By.xpath(xpath));
	}

	public Object findObjectByXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath));
	}

	public List<WebElement> findElementsById(String id) {
		// logger.info("*** BasePage -> findElementsById ***");
		return getDriver().findElements(By.id(id));
	}

	public List<WebElement> findElementsByXpath(String xpath) {
		// logger.info("*** BasePage -> findElementsById ***");
		return getDriver().findElements(By.xpath(xpath));
	}

	public WebElement findElementByPartialLinkText(String text) {
		return getDriver().findElement(By.partialLinkText(text));
	}

	public void dynamicWaitForXpath(String xpath, int maxTime) {
		// logger.info("*** BasePage -> dynamicWaitForXpath method started
		// ***");
		int currentTime = 0;
		boolean flag = false;
		int count = 0;
		while (flag == true) {

			count = findElementsByXpath(xpath).size();
			if (count > 0)
				flag = false;
			if (currentTime > maxTime) {
				flag = false;
				fail("Element could not be found in: " + maxTime);
			}
			currentTime++;
		}
		// logger.info("*** dynamicWaitForXpath method completed ***");
	}

	public String getCurrentTimeInET() {
		DateTimeFormatter etFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm");
		ZoneId etZoneId = ZoneId.of("America/New_York");
		ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
		LocalDateTime currentDateTime = LocalDateTime.now();
		ZonedDateTime currentISTime = currentDateTime.atZone(istZoneId); // India
																			// Time
		ZonedDateTime currentETime = currentISTime.withZoneSameInstant(etZoneId).minusMinutes(1);
		return etFormat.format(currentETime).toString();
	}

	public String getTextOfElement(WebElement element) {
		String text = element.getText();
		return text;
	}

	public String getCurrentMonth() {
		Date objDate = new Date(); // Current System Date and time is assigned
									// to objDate
		String strDateFormat = "MMMM"; // Date format is Specified
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // Date
																		// format
																		// string
																		// is
																		// passed
																		// as an
																		// argument
																		// to
																		// the
																		// Date
																		// format
																		// object
		System.out.println(objSDF.format(objDate).toString().toUpperCase());
		return objSDF.format(objDate).toString();
	}

	public String getCurrentYear() {
		Date objDate = new Date(); // Current System Date and time is assigned
									// to objDate
		String strDateFormat = "YYYY"; // Date format is Specified
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // Date
																		// format
																		// string
																		// is
																		// passed
																		// as an
																		// argument
																		// to
																		// the
																		// Date
																		// format
																		// object
		System.out.println(objSDF.format(objDate).toString().toUpperCase());
		return objSDF.format(objDate).toString();
	}

	public String currentDatePlus(int addNoOfDays) {
		DateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, addNoOfDays); //
		Date newDate = cal.getTime();
		return dateFormat.format(newDate).toString();
	}

	public void writeDataToFile(String data) throws IOException {
		FileWriter fw = new FileWriter(Constants.Invoice_Number);
		fw.write(data);
		fw.close();
	}

	public String readTextFile() throws IOException {
		String invoiceNumber = readFile(Constants.Invoice_Number);
		return invoiceNumber;

	}

	public String readFile(String filename) throws IOException {
		String content = null;
		File file = new File(filename); // For example, foo.txt
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return content;
	}

	public ExpectedCondition<WebElement> visibilityofelementlocated(final By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				return element.getAttribute("value").isEmpty() ? element : null;
			}
		};
	}

	public ExpectedCondition<WebElement> visibilityOfElementLocated(WebElement element) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				return element.getAttribute("value").isEmpty() ? element : null;
			}
		};
	}

	public static void untilJqueryIsDone(WebDriver driver, int timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (!isJqueryCallDone)
				System.out.println("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, int timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void takeScreenshots() throws IOException {
		try {
			File scrFile = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			Calendar cal = Calendar.getInstance();
			String screenshotPath = path + File.separator + "reports" + File.separator + "screenshots" + File.separator
					+ "Build No-" + readProp().getProperty("BuildNumber") + "-" + getCurDate() + "_" + getCurTime()
					+ File.separator + cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.MINUTE) + "-"
					+ cal.get(Calendar.SECOND) + ".png";
			FileUtils.copyFile(scrFile, new File(screenshotPath));
			Reporter.log("<img src='" + screenshotPath + "'/><br>");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Log from threadId: " + Thread.currentThread().getId() + "-"
					+ "Snapshot below: " + FrameworkBase.extReportTest.addScreenCapture(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());// added for logging
		}
	}

	public void waitTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
		}
	}

	public int queryDirectorySize(String path) throws Exception {
		File sourceDirectory = new File(path);
		File[] files = sourceDirectory.listFiles();
		return files.length;
	}

	public String getTheLatestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			/** The newest file comes first **/
			 Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		System.out.println("The newest file absoulte path is === " + theNewestFile.getAbsolutePath());
		return theNewestFile.getAbsolutePath();
	}

	public static String fetchPDFContent(String pdfPath) throws IOException {
		try (PDDocument document = PDDocument.load(new File(pdfPath))) {
			String pdfFileInText = "";
			document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper tStripper = new PDFTextStripper();
				pdfFileInText = tStripper.getText(document);
			}
			return pdfFileInText;
		}
	}

	/**
	 * @param username
	 * @param password
	 * @param url
	 * @throws Exception
	 * 
	 *             This method is used to create a new instance of
	 *             EventFiringWebDriver and assign chrome driver to it Once the
	 *             driver is launched, the url passed in parameter is hit and
	 *             login details are entered as prompted Once the login is
	 *             successful, the url is again hit to open the PDF
	 */

	public void createDriverAndLogin(String username, String password, String url) throws Exception {
		Driver dr = new Driver();
		driver2 = dr.createDriverInstance(nodeURL, "chrome", url);
		driver2.get(url);
		Page_Login_ACESII loginPage = new Page_Login_ACESII(driver2);
		loginPage.loginToACESII(username, password);
		driver2.get(url);
	}

	/**
	 * @throws Exception
	 *             This method uses Actions class and AutoIt executable to
	 *             download the supplier PDF to specific folder location
	 * 
	 */
	public void downloadPDF(String folderPath, String fileName) throws Exception {
		folderPath = downloadFilepath + File.separator + folderPath;
		createFolderStructure(folderPath);
		Actions actions = new Actions(driver2);
		String filePath = folderPath + File.separator + fileName + ".pdf";
		System.out.println(filePath);
		try {
			actions.sendKeys(Keys.chord(Keys.CONTROL, "s")).perform();
			Thread.sleep(2000);
			Runtime.getRuntime().exec(libPath + File.separator + "SavePDF.exe " + filePath);
			Thread.sleep(3000);
		} catch (Exception e) {
			Runtime.getRuntime().exec(libPath + File.separator + "SavePDF.exe " + filePath);
			Thread.sleep(3000);
		}
		Thread.sleep(10000);
		driver2.quit();
	}
	
	public String renameFileByApendingDateStamp(String fileName, String ext) throws InterruptedException{
		File oldfile =new File(downloadFilepath+File.separator+fileName+ext);
		File newfile =new File(downloadFilepath+File.separator+(fileName+"-"+getCurDate())+ext);
		boolean destFile = oldfile.renameTo(newfile);
		Thread.sleep(2000);
		if(destFile){
		   System.out.println("File renamed successfully");
		}else{
		   System.out.println("Rename operation failed");
		 }
		String newFilename = newfile.getName();
		System.out.println(newFilename);
		return newFilename;
	}

	public void createFolderStructure(String folderPath) throws Exception {
		File directory = new File(folderPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	public void createFile(String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
	}

	public void fileMoveToDesiredFolder(String src, String directory, String fileName, String ext) {
		// File (or Directory) to be moved
		File file = new File(getTheLatestFile(src, ext));

		// Destination directory
		File dir = new File(directory);
		if (!dir.exists())
			dir.mkdirs();

		// Move file to a new directory
		boolean success = file.renameTo(new File(dir, fileName));

		if (success) {
			System.out.println("==File was successfully moved.==");
		} else {
			System.out.println("==File was not successfully moved.==");
		}
	}

	public String fileMoveToDesiredFolder_Updated(String src, String directory, String fileName, String ext)
			throws Exception {
		// File (or Directory) to be moved
		File file = new File(getTheLatestFile(src, ext));

		// Destination directory
		File dir = new File(directory);
		if (!dir.exists())
			dir.mkdirs();

		// Move file to a new directory
		// boolean success = file.renameTo(new File(dir, fileName));
		Path source = Paths.get(src + File.separator + file.getName());
		Path destination = Paths.get(directory + File.separator + file.getName());
		// Files.copy(source, destination);
		Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
		File destFile = new File(directory + File.separator + file.getName());
		System.out.println(destFile);
		String f = file.getName();
		System.out.println(f);
		boolean newFile = destFile.renameTo(new File(directory + File.separator + f));
		String newFileName = "";
		if (newFile == true) {
			newFileName = destFile.getName();
			System.out.println(newFileName);
			System.out.println("==File was successfully moved.==");
		} else {
			System.out.println("==File was not successfully moved.==");
		}
		return newFileName;
	}

	public boolean latestFilePresent(String filePath) throws Exception {
		boolean filePresent = false;
		File file = new File(filePath);
		if (file.exists())
			filePresent = true;
		return filePresent;
	}

	public List<WebElement> findElementsByName(String name) {
		// logger.info("*** BasePage -> findElementsByName ***");
		return getDriver().findElements(By.name(name));
	}

	public void datePicker_ACESIII(WebElement monthYearfromCalendar, String previousMonth, WebElement appcalendar,
			String toFromIndicator, String customDate)

	{
		DateFormat df = new SimpleDateFormat("d-MMMM YYYY");
		Calendar calendar = Calendar.getInstance();
		if (!toFromIndicator.equalsIgnoreCase("Custom")) {
			int subtractValue = Integer.parseInt(readPropValue("dateSubtractValue_ACESIII"));
			System.out.println("CalendarMonth: " + Calendar.MONTH + "SubtractValue: " + subtractValue);
			calendar.add(Calendar.MONTH, subtractValue);
		}

		int thisDate = 0;
		if (toFromIndicator.equals("From"))
			thisDate = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		else if (toFromIndicator.equals("To"))
			thisDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		/*
		 * Making changes to existing code to handle custom date inputs if
		 * toFromIndicator is custom, day and month are extracted from the
		 * parameter customDate and is fed to steps for further processing
		 * 
		 * if toFromIndicator is not customDate, the code fetches current date
		 * extracts day and month from the Date format, based on thisDate
		 * calculated above
		 */

		String day = "", month = "";
		if (toFromIndicator.equals("Custom")) {
			day = customDate.split("-")[0];
			if (day.charAt(0) == '0')
				day = day.substring(1, 2);
			month = customDate.split("-")[1];
		} else {
			calendar.set(Calendar.DATE, thisDate);
			Date newdt = calendar.getTime();
			String date = df.format(newdt);
			day = date.split("-")[0];
			month = date.split("-")[1].substring(0, 3);
			System.out.println(date);
		}

		while (!monthYearfromCalendar.getText().contains(month))
			findElementByXpath(previousMonth).click();
		List<WebElement> columns = appcalendar.findElements(By.tagName("td"));
		for (WebElement date : columns) {
			if (date.getText().equals(day)) {
				date.click();
				break;
			}
		}

	}

	public void hoverOnElement(WebElement element) {
		Actions action = new Actions(getDriver());
		action.moveToElement(element).clickAndHold().perform();
	}

	public void enterValueJavaScript(WebElement element, String attribute, String value) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String script2 = "arguments[0].setAttribute('" + attribute + "', '" + value + "')";
		js.executeScript(script2, element);
	}

	public void switchToNewWindow(List<String> availableWindowHandles) {
		Set<String> handles = getDriver().getWindowHandles();
		for (String thisHandle : handles) {
			if (!availableWindowHandles.contains(thisHandle)) {
				switchToWindow(thisHandle);
				availableWindowHandles.add(thisHandle);
				break;
			}
		}
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		if (dir_contents.length > 0) {
			for (int i = 0; i < dir_contents.length; i++) {
				if (dir_contents[i].getName().equals(fileName)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"File Downloaded Sucessfully and available in downloaded_files folder");
					return flag = true;
				}
			}
		} else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "File Not Downloaded into downloaded_files folder");

		return flag;

	}

	public void waitForFileToDownloadAndMove_Chrome(String expectedFileName, String fileExt, String folderPath)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.withTimeout(Duration.ofMinutes(3));
		wait.pollingEvery(Duration.ofSeconds(15));
		Path path = Paths.get(downloadFilepath);
		File fileToCheck = path.resolve(expectedFileName).toFile();
		String FileDownloaded = fileToCheck.getName();
		System.out.println(FileDownloaded);
		/*
		 * Checks for required file in downloaded_files. If not exists, wait
		 * till names of expected filename and file to be download are equal.
		 */
		if (!fileToCheck.exists()) {
			ExpectedCondition<Boolean> elementTextEqualsStringIgnoreCase = arg0 -> expectedFileName
					.equalsIgnoreCase(FileDownloaded);
			wait.until(elementTextEqualsStringIgnoreCase);
			ExtentTestManager.getTest().log(LogStatus.PASS, "File Downloaded Successfully");
		}
		fileMoveToDesiredFolder_Updated(downloadFilepath, downloadFilepath + File.separator + folderPath,
				expectedFileName, fileExt);

	}
	
	public void dragAndDrop(WebElement source, WebElement target, String label){
		Actions action = new Actions(getDriver());
		//action.clickAndHold(source).moveToElement(target).release(target).perform();
		action.dragAndDrop(source, target).perform();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Drag and Drop " + label);
	}	
	
	public void connectToDBAndExecuteQuery(String queryToExecute) throws Exception{
		String url = "jdbc:oracle:thin:@"+dbIP+":"+dbPort+":aces";
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, dbUserId, dbPassword);
	    System.out.println("connection established!");
		Statement st = con.createStatement();
		//String tenantIdQuery = "Select tenantid from Tenant where tenantname = '"+tenantName+"'";
		ResultSet rs = st.executeQuery(queryToExecute);
		rs.next();
	}
	
	public void switchToWindowIndex(int index) {
	    Set<String> windowHandles = getDriver().getWindowHandles();
	    List<String> windowStrings = new ArrayList<>(windowHandles);
	    String reqWindow = windowStrings.get(index);
	    getDriver().switchTo().window(reqWindow);
	    System.out.println("Switched to " + getDriver().getTitle());
	}
	
	public String currentDateFormatdMMyyyy() {
	DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
	Date date = new Date();
	String dateValue = dateFormat.format(date);
	return dateValue.toString();
	}
}


