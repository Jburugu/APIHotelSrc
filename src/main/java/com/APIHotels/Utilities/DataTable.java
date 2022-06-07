package com.APIHotels.Utilities;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DataTable
{
	private static int START_COLUMN = 0;

	private static int MAX_ROWS_PER_DATASHEET = 3000000;
	
	private Sheet sheet;
	private String tableName;
	private int headerRow;
	
	public DataTable(Sheet sheet, String tableName)
	{
		this.sheet = sheet;
		this.tableName = tableName;
		this.headerRow = findHeaderRow(tableName) + 1;
	}
	
	public DataTable(Sheet sheet){
		this.sheet = sheet;
		this.headerRow = findHeaderRowForNonTable();
	}
	
	public String getFieldValue(int rownum, String fieldName) 
	{
		int col = getColumnNumberByName(this.headerRow, fieldName);
		Row row = sheet.getRow(this.headerRow + rownum);
		if(row!=null && row.getCell(col)!= null){
			switch(row.getCell(col).getCellType()){
			
			case FORMULA:
				try{
					return row.getCell(col).getStringCellValue();
				}catch(Exception e){
					return String.valueOf(Double.valueOf(row.getCell(col).getNumericCellValue()).intValue());
				}
			case STRING:
				return row.getCell(col).getStringCellValue();
			case NUMERIC:
				return String.valueOf(Double.valueOf(row.getCell(col).getNumericCellValue()));//.intValue());
			default:
				return null;
			}
		}else{
			return null;
		}
		
	}
	
	public String getFieldValueFromNonTable(int rownum, String fieldName){
		int col = getColumnNumberByName(this.headerRow, fieldName);
		Row row = sheet.getRow(this.headerRow + rownum);
		if(row!=null && row.getCell(col)!= null){
			switch(row.getCell(col).getCellType()){
			
			case FORMULA:
				try{
					return row.getCell(col).getStringCellValue();
				}catch(Exception e){
					return String.valueOf(Double.valueOf(row.getCell(col).getNumericCellValue()).intValue());
				}
			case STRING:
				return row.getCell(col).getStringCellValue();
			case NUMERIC:
				return String.valueOf(Double.valueOf(row.getCell(col).getNumericCellValue()).intValue());
			default:
				return null;
			}
		}else{
			return null;
		}
		
	}
	
	public void setFieldValue(int row, String fieldName, String data) throws IOException {
		int columnNum = getColumnNumberByName(this.headerRow, fieldName);
		setStringCellValue(headerRow + row, columnNum, data);
	}
	
	public void createHeaderRow(int col, String data) throws IOException{
		if(getColumnNumberByName(headerRow, data) == -1)
			setStringCellValue(headerRow, col, data);
	}
	
	private int findHeaderRow(String tableName) {
		int row = 0;
		String cellData = getStringCellValue(row, START_COLUMN);

		while ((cellData == null || !cellData.equals(tableName)) && row < MAX_ROWS_PER_DATASHEET) {
			row++;
			cellData = getStringCellValue(row, START_COLUMN);
		}

		if (row == MAX_ROWS_PER_DATASHEET) {
			//throw new RuntimeException("Table not found in sheet");
			row = -1;
		}

		return row;
	}
	
	private int findHeaderRowForNonTable(){
		int row = 0;
		String cellData = getStringCellValue(row, START_COLUMN);
		while((cellData == null) && row < MAX_ROWS_PER_DATASHEET){
			row++;
			cellData = getStringCellValue(row, START_COLUMN);
		}
		
		if(row == MAX_ROWS_PER_DATASHEET){
			throw new RuntimeException("No data found in sheet");
		}
				
		return row;
	}
	
	public void createTable(String tableName) throws IOException{
		if(headerRow == 0){
			setStringCellValue(0, START_COLUMN, tableName);
			headerRow = findHeaderRow(tableName) + 1;
		}
	}
	
	public int getNoOfDataRows(){
		int rowCount = 0;
		int currRowNo = findHeaderRow(tableName) + 2;
		String cellData = getStringCellValue(currRowNo, START_COLUMN);
		while((cellData != null && !cellData.equals("")) && currRowNo < MAX_ROWS_PER_DATASHEET){
			rowCount++;
			cellData = getStringCellValue(currRowNo+1, START_COLUMN);
			currRowNo++;
		}
		return rowCount;
	}
	
	public int getNoOfDataRowsForNonTable(){
		int rowCount = 0;
		int currRowNo = findHeaderRowForNonTable() + 1;
		String cellData = getStringCellValue(currRowNo, START_COLUMN);
		while((cellData != null && !cellData.equals("")) && currRowNo < MAX_ROWS_PER_DATASHEET){
			rowCount++;
			cellData = getStringCellValue(currRowNo+1, START_COLUMN);
			currRowNo++;
		}
		return rowCount;
	}
	
	// In a row find the column number where value exists
	// ASSUMPTION: There is no cell with blank value
	private int getColumnNumberByName(int row, String value) {
		int col = START_COLUMN;

		String cellData = getStringCellValue(row, col);
		while (cellData != null && !cellData.equals(value)) {
			col++;
			cellData = getStringCellValue(row, col);
		}

		// reached a cell with null value without finding the value
		if (cellData == null) {
			/*throw new RuntimeException(
					"No matching column found with name " + value);*/
			col = -1;
		}
		
		return col;
	}
	
	
	private String getStringCellValue(int rowNum, int col) {
		Row row = sheet.getRow(rowNum);
		return row !=null && row.getCell(col) != null ? row.getCell(col).getStringCellValue() : null;
	}
	
	
	
	private void setStringCellValue(int rownum, int col, String value) throws IOException {	
		Row row = sheet.getRow(rownum);
		row = createRowAndCellIfNotExists(rownum, col, sheet, row);
		row.getCell(col).setCellValue(value);
	}

	private Row createRowAndCellIfNotExists(int rownum, int col, Sheet sheet, Row row) {
		if (row == null)
		{
			row = sheet.createRow(rownum);
		}
		
		Cell cell = row.getCell(col);
		if (cell == null)
		{
			row.createCell(col);
		}
		return row;
	}
	
	
	public int getColumnNumberByName(String value) {
			int col = START_COLUMN;

			String cellData = getStringCellValue(this.headerRow, col);
			while (cellData != null && !cellData.equals(value)) {
				col++;
				cellData = getStringCellValue(this.headerRow, col);
			}

			// reached a cell with null value without finding the value
			if (cellData == null) {
				/*throw new RuntimeException(
						"No matching column found with name " + value);*/
				col = -1;
			}
			
			return col;
		}

}
