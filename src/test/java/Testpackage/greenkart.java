package Testpackage;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import Pages.checkoutpage;
import Pages.dashboard;

public class greenkart extends Baseclass
{
	@Parameters({"promo","country"})
	@Test( retryAnalyzer = retry.class)
	public void greenkartTest(String promocode, String country) throws Exception
	{
		//site launch
		w.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		//ADDTO KART		
		d.addtokart();		
		//checkout journey		
		ch.checkout(promocode,country);
	}
		
}
