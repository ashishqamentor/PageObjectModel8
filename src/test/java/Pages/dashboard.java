package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class dashboard 
{
	WebDriver w; // null // instance
	@FindBy(xpath = "//input[@type='search']")WebElement search;
	@FindBy(xpath = "//button[text()='ADD TO CART']")WebElement addtopkart_btn;
	
	public dashboard(WebDriver driver) // w2 =w
	{
		this.w=driver;
		PageFactory.initElements(w, this);
	}

	public void addtokart() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/login.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(1);
		int rowxount = sh.getLastRowNum();
		for(int i = 0;i<rowxount;i++)
		{
			XSSFRow row = sh.getRow(i+1);
			String veg= row.getCell(0).getStringCellValue();
			search.sendKeys(veg);
			Thread.sleep(2000); 
			addtopkart_btn.click();
			search.clear();
		}
	}
	
}
