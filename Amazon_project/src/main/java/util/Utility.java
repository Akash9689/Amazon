package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static void takeScreenshot(WebDriver driver,String testID) throws IOException {
		
//		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(" yy-MM-dd hh mm ss");
//		LocalDateTime localDateTime=LocalDateTime.now();
//		String dateTime=dateTimeFormatter.format(localDateTime);
		
//		String dateTime=DateTimeFormatter.ofPattern(" yy-MM-dd hh mm ss").format(LocalDateTime.now());
		
		String dateTime=new SimpleDateFormat(" yy-MM-dd hh mm ss").format(new Date());

		
		File temp=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file=new File("F:\\Java Classes\\Screenshots\\Test"+testID+dateTime+".jpg");
		FileHandler.copy(temp, file);
	}
	
	public static String extractDataFromExcel(String sheet,int row,int cell) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream file=new FileInputStream("F:\\Java Classes\\Book1.xlsx");
		String data=WorkbookFactory.create(file).getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		return data;
	}
}
