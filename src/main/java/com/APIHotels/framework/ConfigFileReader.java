package com.APIHotels.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.APIHotels.Utilities.DataTable;
import com.APIHotels.Utilities.XlsWorkbook;

public class ConfigFileReader extends FrameworkBase{
	
	private String environmentValue;
	
	void readConfigFile() throws IOException{
	
		XlsWorkbook acesIIWorkbook = new XlsWorkbook(ACESII_CONGIG_FILE_PATH);
		XlsWorkbook airlinesWorkbook = new XlsWorkbook(AIRLINES_CONGIG_FILE_PATH);
		
		List<String> acesIIGroupsToExecute = getAllGroupsToExecute(acesIIWorkbook);
		List<String> airlinesGroupsToExecute = getAllGroupsToExecute(airlinesWorkbook);
		
		boolean contExecution = checkGroupsToExecute(acesIIGroupsToExecute, airlinesGroupsToExecute);
		if(contExecution){
			XmlSuite mySuite = new XmlSuite();
			ArrayList<String> listeners = new ArrayList<>();
			listeners.add(CUSTOM_TEST_LISTENER_ADAPTER);
			listeners.add(WEBDRIVER_THREAD_LISTENER);
			listeners.add(EXTENT_REPORTERNG_LISTENER);
			mySuite.setListeners(listeners);
			mySuite.setName("SmokeSuite");
			mySuite.setParallel(ParallelMode.TESTS);
			List<XmlTest> myTests = new ArrayList<XmlTest>();
			myTests = getXmlTestForExecutableGroups(myTests, mySuite, acesIIGroupsToExecute, acesIIWorkbook);
			myTests = getXmlTestForExecutableGroups(myTests, mySuite, airlinesGroupsToExecute, airlinesWorkbook);
			mySuite.setTests(myTests);	
			System.out.println("Printing TestNG Suite Xml");
			System.out.println(mySuite.toXml());
			setDBDetails(acesIIWorkbook);
			setSftpDetails(acesIIWorkbook);
			executeSuite(mySuite);
		}
		
	}

	private void setDBDetails(XlsWorkbook configFile) {
		DataTable dbDetailsTable = configFile.getTestDataTable("Configuration", "DatabaseDetails");
		for(int rowNo = 1; rowNo <= dbDetailsTable.getNoOfDataRows(); rowNo++){
			String env = dbDetailsTable.getFieldValue(rowNo, "Environment");
			if(env.equals(environmentValue)){
				dbIP = dbDetailsTable.getFieldValue(rowNo, "IP");
				dbPort = dbDetailsTable.getFieldValue(rowNo, "Port");
				dbUserId = dbDetailsTable.getFieldValue(rowNo, "username");
				dbPassword = dbDetailsTable.getFieldValue(rowNo, "password");
				break;
			}
				
		}
		
	}
	
	private void setSftpDetails(XlsWorkbook configFile) {
		DataTable dbDetailsTable = configFile.getTestDataTable("Configuration", "SftpDetails");
		for(int rowNo = 1; rowNo <= dbDetailsTable.getNoOfDataRows(); rowNo++){
			String env = dbDetailsTable.getFieldValue(rowNo, "Environment");
			if(env.equals(environmentValue)){
				sftpHost = dbDetailsTable.getFieldValue(rowNo, "Host");
				sftpUser = dbDetailsTable.getFieldValue(rowNo, "sftpusername");
				sftpPassword = dbDetailsTable.getFieldValue(rowNo, "sftppassword");
				break;
			}
				
		}
		
	}

	private void executeSuite(XmlSuite mySuite){
		List<XmlSuite> myXmlSuites = new ArrayList<XmlSuite>();
		myXmlSuites.add(mySuite);
		TestNG myTestNG = new TestNG();
		myTestNG.setXmlSuites(myXmlSuites);
		myTestNG.run();
	}
	
	
	/**
	 * getXmlTestForExecutableGroups method is used to generate List of Xml Tests for all the Selected Groups from ACESII and Airlines Config files
	 * It iterates on the list of selected groups
	 * Fetches details on List of Classes and methods selected under that group
	 * and adds them to Xml Test
	 * Finally adds all the genreate Xml Test objects to the List of XmlTests.  
	 * 
	 * 
	 * @param myTests
	 * @param mySuite
	 * @param groupsToExecute
	 * @param workbook
	 * @return List of Xml Tests
	 */
	private List<XmlTest> getXmlTestForExecutableGroups(List<XmlTest> myTests, XmlSuite mySuite, List<String> groupsToExecute, XlsWorkbook workbook) {
		Map<String, Set<String>> testClassesToExecute = new HashMap<>();
		Map<String, List<String>> testMethodsToExecute = new HashMap<>();
		DataTable gridNodeTable = workbook.getTestDataTable("Configuration", "GridNode");
		DataTable gridHubTable = workbook.getTestDataTable("Configuration", "GridHub");
		
		for(String groupToExecute : groupsToExecute){
			
			/*
			 * Fetch Environment Value given in the Property file
			 * In Config file Xl sheet, switch to EnvironmentDetails tab, and search for EnvironmentDetails table pertaining to the environment given in the property file
			 * Note down all the application URLs in global constants for that environment
			 * 
			 * */
			environmentValue = readPropValue("environment"); 
			DataTable environmentDetailsTable = workbook.getTestDataTable("EnvironmentDetails", environmentValue+"_EnvironmentDetails");
			int noOfenvRows = environmentDetailsTable.getNoOfDataRows();
			for(int rowId = 1; rowId <= noOfenvRows; rowId++){
				String app = environmentDetailsTable.getFieldValue(rowId, "Env");
				switch(app){
				case "ACESII":
					aces2Url = environmentDetailsTable.getFieldValue(rowId, "URL");
					break;
				case "Airlines":
					airlinesUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
					break;
				case "Latam":
					latamUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
					break;
				case "ACES3Supplier":
					aces3SupplierUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
					break;
				case "ACES3Client":
					aces3ClientUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
					break;
				case "ACES3ACES":
					aces3AcesUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
					break;
				case "ACES2Supplier":
					aces2SupplierUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
				case "ACESLatam":
					acesLatamUrl = environmentDetailsTable.getFieldValue(rowId, "URL");
				}
			}
			
			/*
			 * This is to determine the package name for selected group. 
			 * if the group is ACESII group, ACESII Testcase package name is assigned
			 * else if the group is Airlines/Latam, Airlines Testcase package name is assigned --> 
			 * This package name is used to give the executable class details in the generated Xml File  
			 * 
			 * */
			String packageName = "";
			if(groupToExecute.contains("ACESII")){
				packageName = acesPkg;
			}else if(groupToExecute.contains("Airlines") || groupToExecute.contains("Latam")){
				packageName = airlinesPkg;
			}else if(groupToExecute.contains("ACES3"))
				packageName = aces3Pkg;
			
			/*
			 * This is to notedown the row no of the Configuration File from which URL of ACESII, Airlines and Latam Airlines application is read.
			 * 
			 * */
			
			String url = "";
			int totalRows = environmentDetailsTable.getNoOfDataRows();
			for(int i = 1; i <= totalRows; i++){
				if(groupToExecute.contains("ACESII")){
					url = aces2Url;
					break;
				}
				else if(groupToExecute.contains("Airlines")){
					url = airlinesUrl;
					break;
				}
				else if(groupToExecute.contains("Latam")){
					url = latamUrl;
					break;
				}
				else if(groupToExecute.contains("ACES3Supplier")){
					url = aces3SupplierUrl;
					break;
				}
				else if(groupToExecute.contains("ACES3Client")){
					url = aces3ClientUrl;
					break;
				}
				else if(groupToExecute.contains("ACES3ACES")){
					url = aces3AcesUrl;
					break;
				}
			}
			
			
			/*Below code segment is used to extract ClassNames and Methods that are selected for execution from the current Group
			 * Goes to Functions sheet in the COnfiguration Xl File, Goes to Group table and iterates on all the rows under that table
			 * and, fetches all the Classes and Methods marked are "Yes" for execution and adds them to their respective collections
			 * 
			 * */
			
			DataTable groupTable = workbook.getTestDataTable("Functions", groupToExecute);
			int noOfRows = groupTable.getNoOfDataRows();
			for(int i = 1; i <= noOfRows; i++){
				String runIndicator = groupTable.getFieldValue(i, "Include In Execution");
				if(runIndicator.equals("Yes")){
					String className = packageName + groupTable.getFieldValue(i, "ClassName");
					String methodName = groupTable.getFieldValue(i, "MethodName");
					testClassesToExecute.computeIfAbsent(groupToExecute, k -> new HashSet<>()).add(className);
					testMethodsToExecute.computeIfAbsent(className, k -> new ArrayList<>()).add(methodName);
				}
			}
		
			/*
			 * If there are classes selected as Yes under the current Group, 
			 * Below code segment calls the createTestXmlClassesAndMethods method to generate XmlClasses
			 * It also checks for all the available nodes selected for execution from GridNode table of the COnfig file and 
			 * generates XmlTest with above XmlClasses added to it FOR EACH node 
			 * Each XmlTest generated like that, is inturn added to the list of XmlTests and is returned to the calling method.  
			 * 
			 * */
			
			if(testClassesToExecute.size() == 0)
				continue;
			else{
				List<XmlClass> myClasses = createTestXmlClassesAndMethods(testClassesToExecute, groupToExecute, testMethodsToExecute);
				
				String nodeURL = gridHubTable.getFieldValue(1, "Hub_URL");
				int totalNoOfNodes = gridNodeTable.getNoOfDataRows();
				for(int i = 1; i <= totalNoOfNodes; i++){
					XmlTest myTest = new XmlTest(mySuite);
					myTest.setName(groupToExecute+"_Node"+i);
					myTest.setClasses(myClasses);
					String runMode = gridNodeTable.getFieldValue(i, "Include In Execution");
					if(runMode.equals("Yes")){
						String browser = gridNodeTable.getFieldValue(i, "Browser");
						Map<String, String> testNGParams = new HashMap<String, String>();
						testNGParams.put("URL", url);
						testNGParams.put("browserName", browser);
						testNGParams.put("NodeUrl", nodeURL);
						myTest.setParameters(testNGParams);
						myTests.add(myTest);
					}
				}
				
			}
		}
		
		return myTests;
	}

	/**
	 * createTestXmlClassesAndMethods method is used to generate XmlClasses and all the methods that need to be included in those Classes
	 * It extracts all the classes pertaining to the given groupToExecute from the Map
	 * and from the list/array of Classes extracted, it will get the List of all the methods pertaining to each Class
	 * and adds them as IncludedMethods in the XmlClass
	 * and finally add each XmlClass to the List of XmlClasses and returns the list to the calling method
	 * 
	 * @param testClassesToExecute
	 * @param groupToExecute
	 * @param testMethodsToExecute
	 * @return List of XmlTest classes
	 */
	private List<XmlClass> createTestXmlClassesAndMethods(Map<String, Set<String>> testClassesToExecute, String groupToExecute,
			Map<String, List<String>> testMethodsToExecute) {
		
		List<XmlClass> myTestClasses = new ArrayList<XmlClass>();
		String[] classesToExecute = new String[0];
		classesToExecute = testClassesToExecute.get(groupToExecute).toArray(classesToExecute);
		for(String classToExecute : classesToExecute){
			XmlClass myTestClass = new XmlClass();
			List<XmlInclude> methodsToInclude = new ArrayList<XmlInclude>();
			myTestClass.setName(classToExecute);
			List<String> methodsToExecute = testMethodsToExecute.get(classToExecute);
			methodsToExecute.forEach(method -> methodsToInclude.add(new XmlInclude(method)));
			myTestClass.setIncludedMethods(methodsToInclude);
			myTestClasses.add(myTestClass);
			
		}
		return myTestClasses;
		
	}

	
	/**
	 * getAllGroupsToExecute method is used to read Configuration File and 
	 * check all the Groups Listed down in the Suite Execution Details Table
	 * and return List of all the Groups which are selected for current Execution.  
	 * 
	 * @param configFile
	 * @return All the groups selected to execute in the config file
	 */
	private List<String> getAllGroupsToExecute(XlsWorkbook configFile) {
		DataTable suiteExecutionDetails = configFile.getTestDataTable("Configuration", "SuiteExecutionDetails");
		int noOfRows = suiteExecutionDetails.getNoOfDataRows();
		List<String> groupsToExecute = new ArrayList<>();
		for(int i = 1; i<=noOfRows; i++){
			String runIndicator = suiteExecutionDetails.getFieldValue(i, "Include In Execution");
			if(runIndicator.equals("Yes")){
				String groupName = suiteExecutionDetails.getFieldValue(i, "GroupName");
				groupsToExecute.add(groupName); 
			}
		}
		
		return groupsToExecute;
	}
	
	/**
	 * checkGroupsToExecute method is used as a checkpoint to see if we have to continue execution or not.
	 * It takes Aces Groups to execute list and Airline Groups to execute list and 
	 * checks if the list size is greater than 0 or not
	 * if the sizes of both the groups are 0, then it returns false
	 * if the size of either of the groups is greater than 0, it returns true
	 * 
	 * @param acesIIGroupsToExecute
	 * @param airlinesGroupsToExecute
	 * @return
	 */
	private boolean checkGroupsToExecute(List<String> acesIIGroupsToExecute, List<String> airlinesGroupsToExecute){
		
		boolean contExecution = false;
		boolean acesExec = true;
		boolean airlinesExec = true;
		
		if(acesIIGroupsToExecute.size() == 0){
			System.out.println("No Groups Selected to Execute in ACESII");
			acesExec = false;
		}
		if(airlinesGroupsToExecute.size() == 0){
			System.out.println("No Groups Selected to Execute in Airlines");
			airlinesExec = false;
		}
		if(acesExec || airlinesExec)
			contExecution = true;
		return contExecution;
	}

}
