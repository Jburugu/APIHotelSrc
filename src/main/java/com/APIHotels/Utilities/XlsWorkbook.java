package com.APIHotels.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class XlsWorkbook {

	private Workbook workbook;
	
	private String path;

	public XlsWorkbook(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		this.path = filePath;
		this.workbook = WorkbookFactory.create(fis);
		/*if(fis!=null){
			fis.close();
		*/}
	
	
	public XlsWorkbook(String folderPath, String fileName) throws Exception{
		this.path = folderPath+File.separator+fileName;
		FileInputStream fis = new FileInputStream(path);
		this.workbook = WorkbookFactory.create(fis);
		saveAndClose();
	}

	public DataTable getTestDataTable(String sheetName, String tableName)
	{
		Sheet sheet = getSheetByName(sheetName);
		return new DataTable(sheet, tableName);
	}
	
	public DataTable getTestDataTable(int sheetNo){
		Sheet sheet = getSheetByIndex(sheetNo);
		return new DataTable(sheet);
	}
	
	private Sheet getSheetByName(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		Sheet sheet = workbook.getSheetAt(index);
		return sheet;
	}
	
	private Sheet getSheetByIndex(int sheetNo) {
		Sheet sheet = workbook.getSheetAt(sheetNo);
		return sheet;
	}
	
	public void createSheetByName(String sheetName){
		if(workbook.getSheetIndex(sheetName)==-1)
			workbook.createSheet(sheetName);
	}
	
	public void saveAndClose() throws IOException
	{
		//lets write to file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}
	
	public DataTable getTestDataTable(String sheetName){
		Sheet sheet = getSheetByName(sheetName);
		return new DataTable(sheet);
	}
	
}
