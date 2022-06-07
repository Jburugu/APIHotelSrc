package com.APIHotels.framework;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.IInvokedMethod;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.APIHotels.listeners.TestEventHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class FrameworkBase extends Constants {

	public final static String path = System.getProperty("user.dir");
	public static Properties prop = new Properties();
	public static Logger log = Logger.getLogger(Driver.class);
	public static TestEventHandler eventListener;
	public static ExtentReports extent;
	public static ExtentTest extReportTest;
	public static String filePath;
	private static String reportFileName;
	public static int timeout;
	public static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	private static Date date = new Date();

	@BeforeSuite
	public void suiteSetup() {
		log = Logger.getLogger(Driver.class);
		reportFileName = "Report-Build No-" + readProp().getProperty("BuildNumber") + "-" + getCurDate() + "-"
				+ getCurTime() + ".html";
		filePath = Constants.REPORT_PATH + File.separator + "Report-Build No-" + readProp().getProperty("BuildNumber")
				+ "-" + getCurDate() + "-" + getCurTime() + ".html";
		extent = ExtentManager.getReporter(filePath);
	}

	public static void setReports(IInvokedMethod method) {
		ExtentTestManager.startTest(method.getTestMethod().getMethodName(), method.getTestMethod().getDescription());
		String[] group = method.getTestMethod().getGroups();
		extReportTest.assignCategory(group);
	}

	@DataProvider(name = "TestDataFile")
	public static Object[][] readData(Method m) throws Exception {
		String[][] retObjArr = null;
		String filePath1 = path + File.separator + "testdata" + File.separator + m.getDeclaringClass().getSimpleName()
				+ ".xlsx";
		try {
			FileInputStream file = new FileInputStream(new File(filePath1));
			Workbook workbookb = WorkbookFactory.create(file);
			retObjArr = loadExcelFile(workbookb, m.getName());

		} catch (Exception e) {
			System.out.println(e);
			log.info(e.getMessage() + " Datasheet for required testcase not found");
			Reporter.log(e.getMessage() + " Datasheet for required testcase not found");
		}
		return (retObjArr);
	}

	public static String[][] loadExcelFile(Workbook workbook, String sheetName) throws IOException {
		int rowCount, columnCount;
		Sheet sheet = workbook.getSheet(sheetName);
		rowCount = sheet.getLastRowNum();
		columnCount = sheet.getRow(0).getLastCellNum();
		String[][] tempArray = new String[rowCount][columnCount]; // rowCount-1
																	// to leave
																	// reading
																	// 1st row
																	// i.e
																	// column
																	// heading
		try {
			for (int i = 1; i <= rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < columnCount; j++) {
					Cell cell = row.getCell(j);
					switch (cell.getCellType()) {
					case STRING:
						System.out.print(cell.getStringCellValue());
						String str = cell.getStringCellValue();
						tempArray[i - 1][j] = str;
						break;
					case BLANK:
						break;
					case BOOLEAN:
						break;
					case ERROR:
						break;
					case FORMULA:
						System.out.print(cell.getStringCellValue());
						str = cell.getStringCellValue();
						tempArray[i - 1][j] = str;
						break;
					case NUMERIC:
						break;
					case _NONE:
						break;
					default:
						break;

					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			log.info(e.getMessage());
		}
		return tempArray;
	}

	public static Properties readProp() {
		InputStream input = null;
		try {
			String filename = Constants.PROPERTIES_FILE_PATH;
			input = new FileInputStream(filename);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			log.warn(e.getMessage());// added for logging
		}
		return prop;
	}

	public static String readPropValue(String prop) {
		String propertyValue = null;
		try {
			propertyValue = readProp().getProperty(prop);
			if (propertyValue.isEmpty()) {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			log.info(e.getMessage() + " for property: " + prop);// added for
																// logging
		}
		return propertyValue;
	}

	public static String getCurTime() {
		DateFormat curTime;
		curTime = new SimpleDateFormat("h-mm-ss");
		return curTime.format(date);
	}

	public static String getCurDate() {
		DateFormat curDate;
		curDate = new SimpleDateFormat("yyyy-MM-dd");
		return curDate.format(date);
	}

	public String currentMonthStartDate() {
		LocalDate now = LocalDate.now();

		LocalDate firstDay = now.with(firstDayOfMonth());
		String start = firstDay.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		System.out.println("Month Start Date: " + start);
		return start;
	}

	public String currentMonthEndDate() {
		LocalDate now = LocalDate.now();

		LocalDate lastDay = now.with(lastDayOfMonth());
		String last = lastDay.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		System.out.println("Month End Date: " + last);
		return last;
	}

	public static void ComposeGmail() throws InterruptedException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "172.27.172.202");
		props.remove("mail.smtp.socketFactory.class");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("abhilashk@evolvingsols.com", "Cybage$#9");
			}
		});

		try {
			session.getProperties().put("mail.smtp.ssl.trust", "172.27.172.202");
			session.getProperties().put("mail.smtp.starttls.enable", "true");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(readPropValue("EmailFrom")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(readPropValue("EmailTo")));
			message.setSubject("Testing Reports");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText(
					"Hi Team, \n\nPlease find the attached automation test execution report.\nThis email is auto generated with autoamtion scirpt. \n\n\nThank you \nAutomation Team");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String file = filePath;
			String fileName = reportFileName;
			DataSource source = new FileDataSource(file);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(fileName);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart1);
			message.setContent(multipart);
			Thread.sleep(5000);
			Transport.send(message);
			log.info("Emailing execution report completed");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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

	public static void writeProp(String key, String value) {

		try {
			String filename = Constants.PROPERTIES_FILE_PATH;
			InputStream input = new FileInputStream(filename);
			prop.load(input);
			input.close();

			OutputStream output = new FileOutputStream(Constants.PROPERTIES_FILE_PATH);
			prop.setProperty(key, value);
			prop.store(output, null);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.warn(e.getMessage());// added for logging
		}
	}	
	
	public String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");
		Date date = new Date();
		String dateValue = dateFormat.format(date);
		return dateValue.toString();

	}
}
