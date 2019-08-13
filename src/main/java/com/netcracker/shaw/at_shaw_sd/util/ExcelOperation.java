package com.netcracker.shaw.at_shaw_sd.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ExcelOperation {
	
	public String filename = System.getProperty("user.dir")+"\\src\\testdata\\GoldenSuite.xls";
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private HSSFWorkbook workbook = null;
	private HSSFSheet sheet = null;
	private HSSFRow row   =null;
	private HSSFCell cell = null;
	private FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
	private DataFormatter objDefaultFormat = new DataFormatter();
	double value = 0.0;
	java.text.DecimalFormat formatter = null;
	java.text.FieldPosition fPosition = null;
	String formattingString = null;
	String resultString = null;
	StringBuffer buffer = null;
	public ExcelOperation(String path) {
		//System.out.println("Constructor called");
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			// System.out.println(cell.getCellType());
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = new BigDecimal(cell.getNumericCellValue()).toPlainString();

				/*
				 * if (HSSFDateUtil.isCellDateFormatted(cell)) { // format in
				 * form of M/D/YY double d = cell.getNumericCellValue();
				 * 
				 * Calendar cal =Calendar.getInstance();
				 * cal.setTime(HSSFDateUtil.getJavaDate(d)); cellText =
				 * (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				 * cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
				 * cal.get(Calendar.MONTH)+1 + "/" + cellText;
				 * 
				 * //System.out.println(cellText);
				 * 
				 * }
				 * 
				 */

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		}

		catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}
	
	//Meenakshi Added to findRow of particular string 
	
	public int findRow(String sheetName, String cellContent){
		        /*
		         *  This is the method to find the row number
		*/
		
		int index = workbook.getSheetIndex(sheetName);
		System.out.println("Index = " + index);
		int col_Num = -1;
		if (index == -1)
			return 0;

		sheet = workbook.getSheetAt(0);
		        int rowNum = 0; 

		        for (Row row : sheet) {
		            for (Cell cell : row) {
		                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
		                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
		                        return row.getRowNum();  
		                    }
		                }
		            }
		        }               
		        return 0;
	}


	public String getCellData(String sheetName, int colNum, int rowNum) {

		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			// objFormulaEvaluator.evaluate(cell);
			// String cellValueStr =
			// objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
			// SET CELL AS STRING TYPE
			// cell.setCellType(Cell.CELL_TYPE_STRING);
			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			}
		  
			else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
	public String getCellValue(String sheetName, int colNum, int rowNum) {

		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			// objFormulaEvaluator.evaluate(cell);
			String cellValueStr = objDefaultFormat.formatCellValue(cell);
			return cellValueStr;

		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
	
	public String formatData(Cell cell) {

		// Recover the numeric value from the cell
		value = cell.getNumericCellValue();

		// Format that number for display
		formatter.format(value, buffer, fPosition);

		// Not strictly necessary but I copy the result from the
		// StringBuffer into a String - leave this out for performance
		// reasons in production code
		resultString = buffer.toString();
		return resultString;
	}
	
	public static String getCellValueAsString(Cell cell) {
		String strCellValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCellValue = cell.toString();
				break;
			case Cell.CELL_TYPE_NUMERIC:

				strCellValue = NumberToTextConverter.toText(cell.getNumericCellValue());

				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
				break;
			case Cell.CELL_TYPE_BLANK:
				strCellValue = "";
				break;
			}
		}
		return strCellValue;
	}
	
	// returns true if data is set successfully else false
	
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			// cell style
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			cell.setCellStyle(cs);
			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// returns true if data is set successfully else false
		
	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		// System.out.println("**************addColumn*********************");

		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			HSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();
	}
	
	public int getCellRowNum(String sheetName,String colName,String cellValue){
		
		for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
		return -1;	
	}
	
	public String getCellDataFor(ExcelOperation xls, String rowName, String colName) {
		String colValue = null;
		int rows = xls.getRowCount(Constants.TESTCASES_SHEET);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcid = xls.getCellData(Constants.TESTCASES_SHEET, "TCID", rNum);
			if (tcid.equals(rowName)) {

				colValue = xls.getCellData(Constants.TESTCASES_SHEET, colName, rNum);
			}
		}
		return colValue;
	}
	
	public String getDataForTest(ExcelOperation xls, String rowName, String colName) {
		int rows = xls.getRowCount(Constants.TEST_SHEET);
		String colVal = null;
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcid = xls.getCellData(Constants.TEST_SHEET, "TCID", rNum);
			if (tcid.equals(rowName)) {
				colVal = (xls.getCellData("TestData", colName, rNum));
			}
		}
		return colVal;
	}
	
	public String getDataForTestName(ExcelOperation xls, String rowName, String colName) {
		int rows = xls.getRowCount(Constants.TEST_SHEET);
		String colVal = null;
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcName = xls.getCellData(Constants.TEST_SHEET, "TC_Name", rNum);

			if (tcName.equals(rowName)) {
				colVal = (xls.getCellData("TestData", colName, rNum));
				break;
			}
		}

		return colVal;
	}

	public String getValueForRowName(ExcelOperation xls, String rowName, String colName,String colName1) {
		int rows = xls.getRowCount(Constants.EXPECTED_SHEET);
		String colVal = null;
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcName = xls.getCellData(Constants.EXPECTED_SHEET, colName, rNum);

			if (tcName.equals(rowName)) {
				colVal = (xls.getCellData(Constants.EXPECTED_SHEET, colName1, rNum));
				break;
			}
		}

		return colVal;
	}
	public String getExpectedDataForColumn(ExcelOperation xls, String rowName, String colName) {
		int rows = xls.getRowCount(Constants.EXPECTED_SHEET);
		String colVal = null;
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcName = xls.getCellData(Constants.EXPECTED_SHEET, colName, rNum);

			if (tcName.equals(rowName)) {
				colVal = (xls.getCellData(Constants.EXPECTED_SHEET, colName, rNum));
				break;
			}
		}

		return colVal;
	}
	
	public String [] getDataForTheRow(ExcelOperation xls,String tcid){
		String sheetName=Constants.EXPECTED_SHEET;
		// reads data for only testCaseName
		
		int testStartRowNum=1;
		
		//getDataForTest(xls,TCID,"Run")
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(tcid)){
			testStartRowNum++;
		}
				
		int cols=0;
		while(!xls.getCellData(sheetName, cols, 1).equals("")){
			cols++;
		}
		
		
		ArrayList<String> colValueList=new ArrayList<>();
		
		//String[] colValues = new Object[1][1];
		int dataRow=0;
		
		for(int rNum=testStartRowNum;rNum<=testStartRowNum;rNum++){
			
			for(int cNum=6;cNum<cols;cNum++){
				//String key=xls.getCellData(sheetName,cNum,1);
				//cell = row.getCell(cNum);
				String value= xls.getCellData(sheetName, cNum, testStartRowNum);
				
				colValueList.add(value);
			}
			
		}
		String[] colValues= colValueList.toArray(new String[colValueList.size()]);
		return colValues;
	}
	
	public String [] getDataForTheRowNum(ExcelOperation xls,int rowNum,String sheetName){
		
		// reads data for only testCaseName
					
		int cols=0;
		while(!xls.getCellData(sheetName, cols, 1).equals("")){
			cols++;
		}
			
		ArrayList<String> colValueList=new ArrayList<>();
		
		//String[] colValues = new Object[1][1];
					
		for(int cNum=1;cNum<cols;cNum++){
				//String key=xls.getCellData(sheetName,cNum,1);
				//cell = row.getCell(cNum);
			String value= xls.getCellValue(sheetName, cNum, rowNum);
			if(!(value.equals("")))
			colValueList.add(value);
			}
			
		
		String[] colValues= colValueList.toArray(new String[colValueList.size()]);
		return colValues;
	}
	public String [] getDataForTheRowAndColNum(ExcelOperation xls,String rowName,int testStartRowNum,int colNum){
		String sheetName=Constants.EXPECTED_SHEET;
		// reads data for only testCaseName
		
		
		
		//getDataForTest(xls,TCID,"Run")
		while(!xls.getCellData(sheetName, colNum, testStartRowNum).equals(rowName)){
			testStartRowNum++;
		}
		System.out.println("Row start num is "+testStartRowNum);		
		int cols=1;
		while(!xls.getCellData(sheetName, cols, 1).equals("")){
			cols++;
		}
		System.out.println("Column num is "+cols);
		
		ArrayList<String> colValueList=new ArrayList<>();
		
		
		int dataRow=0;
		
		for(int rNum=testStartRowNum;rNum<=testStartRowNum;rNum++){
			
			for(int cNum=1;cNum<cols;cNum++){
				//String key=xls.getCellData(sheetName,cNum,1);
				//cell = row.getCell(cNum);
				String value= xls.getCellValue(sheetName, cNum, testStartRowNum);
				if(!(value.equals(""))){
				colValueList.add(value);
				}
			}
			
		}
		String[] colValues= colValueList.toArray(new String[colValueList.size()]);
		return colValues;
	}
// to run this on stand alone
	public String [] getDataForTheColumn(ExcelOperation xls,String ReportType,String colName){
		String sheetName=Constants.EXPECTED_SHEET;
		// reads data for only testCaseName
		
		int testStartRowNum=1;
				
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(ReportType)){
			testStartRowNum++;
			
		}
		System.out.println("Row start num is "+testStartRowNum); 
				
		int colNum=0;
		while(!xls.getCellData(sheetName, colNum, 1).equals(colName)){
			colNum++;
		}	
		System.out.println("Required column num is "+colNum); 
		int rowCount=testStartRowNum;
		while(!xls.getCellData(sheetName, colNum, rowCount).equals("")){
			rowCount++;
		}
		System.out.println("Row count num is "+rowCount); 		
		ArrayList<String> colValueList=new ArrayList<>();
		
		//String[] colValues = new Object[1][1];
		int dataRow=0;
		
		for(int rNum=testStartRowNum;rNum<=rowCount-1;rNum++){
			
			
				String value= xls.getCellData(sheetName, colNum, rNum);
				
				colValueList.add(value);
			
			
		}
		String[] colValues= colValueList.toArray(new String[colValueList.size()]);
		System.out.println("Total num of rows is : "+colValues.length); 
		return colValues;
	}
	
	
	
	
	public String [] getAllDataForTheReport(ExcelOperation xls,String reportType,String[] reportNames,String colName){
		String sheetName=Constants.EXPECTED_SHEET;
		// reads data for only testCaseName
		
		int testStartRowNum=1;
				
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(reportType)){
			testStartRowNum++;
			
		}
		System.out.println("Row start num is "+testStartRowNum); 
				
		int colNum=0;
		while(!xls.getCellData(sheetName, colNum, 1).equals(colName)){
			colNum++;
		}	
		System.out.println("Required column num is "+colNum); 
		int rowCount=testStartRowNum;
		while(!xls.getCellData(sheetName, colNum, rowCount).equals("")){
			rowCount++;
		}
		System.out.println("Row count num is "+rowCount); 		
		ArrayList<String> colValueList=new ArrayList<>();
		
		//String[] colValues = new Object[1][1];
		int dataRow=0;
		
		for(int rNum=testStartRowNum;rNum<=rowCount-1;rNum++){
			
			
				String value= xls.getCellData(sheetName, colNum, rNum);
				
				colValueList.add(value);
			
			
		}
		String[] colValues= colValueList.toArray(new String[colValueList.size()]);
		System.out.println("Total num of rows is : "+colValues.length); 
		return colValues;
	}
  public static void main(String arg[]) throws IOException{ 
 
  ExcelOperation xls=null; 
  System.out.println("Testing started.."); 
  xls = new  ExcelOperation("C:\\Project\\at-shaw-sd\\at-shaw-sd\\testdata\\GoldenSuite.xls");
  int rows = xls.getRowCount("ExpectedValues");
  int rowNum=xls.getCellRowNum("ExpectedValues","Report-Type","TC1-SOM");
  System.out.println("The row num for TC1-SOM is "+rowNum );
 for(int rNum=1;rNum<=rows;rNum++){
	 String tcid = xls.getCellData("ExpectedValues", "TCID", rNum);
	 if(tcid.equals("TC11")){
  
       System.out.println(xls.getCellData("ExpectedValues", "TCID", rNum));
  
       System.out.println(xls.getCellData("ExpectedValues", "Report_Name", rNum));
  
       System.out.println(xls.getCellData("ExpectedValues", "FROM", rNum));
       System.out.println(xls.getCellData("ExpectedValues", "Uniq_Req_String", rNum));
  
  
  } 
  
 }
 
 String[]  cols=xls.getDataForTheColumn(xls,"TC1-COM","Report_Name");
 System.out.println("Number of COM record  is "+cols.length);
 int rowStartNum=xls.getCellRowNum("ExpectedValues","Report-Type","TC1-COM");
 
 for(int i=0;i<cols.length;i++){
	 
	 String[] data=xls.getDataForTheRowAndColNum(xls, cols[i],rowStartNum,1);
	 System.out.println("Number of params for  TC1-COM  " +cols[i] +" is "+data.length);
	 for(int j=0;j<data.length;j++){
	 
	 if(Utility.checkStringasNumeric(data[j])){
	 System.out.println("Value in column "+j+ " is "+Math.round(Float.valueOf(data[j])));
	 }
	 else{
		 System.out.println("Value in column "+j+ " is "+data[j]);
	 }
	 }
	
 }
 String[]  cols1=xls.getDataForTheColumn(xls,"TC1-SOM","Report_Name");
 System.out.println("Number of SOM record  is "+cols1.length);
 //String[] data1=xls.getDataForTheRowAndColNum(xls, "NC_BILLING",1);
 
 for(int k=0;k<cols1.length;k++){
	 System.out.println("Row name is "+cols1[k]);
	 String[] data1=xls.getDataForTheRowAndColNum(xls, cols1[k],rowNum,1);
	 System.out.println("Number of params for  TC1-SOM " +cols1[k] +" is "+data1.length);
	 //System.out.println("Number of SOM params  is "+data1.length);
	 for(int m=0;m<data1.length;m++){
	 
	 /*if(Utility.checkStringasNumeric(data1[m])){
		 System.out.println(data1[m]+"has value type ") ;
	 System.out.println("Value in column "+m+ " is "+Math.round(Float.valueOf(data1[m])));
	 }
	 else{
		 System.out.println("Value in column "+m+ " is "+data1[m]);
	 }*/
		 System.out.println("Value in column "+m+ " is "+data1[m]);
	 }
	
 }
 }
}
 

