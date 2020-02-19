package com.qa.linkedin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.linkedin.base.TestBase;

public class TestUtil extends TestBase{
	public String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fileOut = null;
	private static XSSFWorkbook wb = null;
	private static XSSFSheet sh1 = null;
	private static XSSFRow row = null;
	private static XSSFCell cell = null;
	public static String screenshotPath;
	public static String screenshotName;
		/**
	 * this Method is used to read the data from excelsheets
	 * @param fpath
	 * @param sheetName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static Object[][] getTestData(String fpath,String sheetName) throws InvalidFormatException, IOException {
		
	//Specify the path of file
	File srcFile=new File(fpath);

	//load file
	 fis=new FileInputStream(srcFile);
	//Load workbook
	 wb=new XSSFWorkbook(fis);

	//Load sheet- Here we are loading first sheetonly
	 sh1= wb.getSheet(sheetName);
		//two d array declaration
	int rowCount=sh1.getLastRowNum();
	log.debug("number of rows in the excel sheet is-->"+rowCount);
	int colCount=sh1.getRow(0).getLastCellNum();
	log.debug("number of columns in the excel sheet is -->"+colCount);
	Object[][] data = new Object[rowCount][colCount];
		for (int i = 0; i < rowCount; i++) {
			for (int k = 0; k < colCount; k++) {
				data[i][k] = sh1.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
		
	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}


	public static String timeStamp(){
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
}
