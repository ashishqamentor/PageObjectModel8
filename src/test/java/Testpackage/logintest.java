package Testpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.loginpage;

public class logintest extends Baseclass
{

	@Test(dataProvider = "mydata" )// ,retryAnalyzer =retry.class
	public void login(String user, String pass, String expURL) throws Exception
	{
		w.get("https://www.saucedemo.com/");
		l.dologin(user,pass,expURL);
	}	
}
