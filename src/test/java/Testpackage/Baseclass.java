package Testpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import Pages.checkoutpage;
import Pages.dashboard;
import Pages.loginpage;

public class Baseclass 
{
	public WebDriver w ; //instance / global  // B
	dashboard d;
	checkoutpage ch;
	loginpage l;
		
	@BeforeTest
	public void launch() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/config.properties");
		Properties p = new Properties();
		p.load(fis);
		String browsername =p.getProperty("browser");
		String env= p.getProperty("env");
		
		System.out.println("person b changes");  // person B - just to test 
		
		if(env.equalsIgnoreCase("remote"))
		{
			URL url = new URL("http://192.168.1.104:4444/wd/hub");
			
			if(browsername.equalsIgnoreCase("chrome"))
			{
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--incognito");
				op.setCapability("browserName", browsername);
				w= new RemoteWebDriver(url, op);
			}
			
			if(browsername.equalsIgnoreCase("MicrosoftEdge"))
			{
				EdgeOptions op = new EdgeOptions();
				op.addArguments("inprivate");
				op.setCapability("browserName", browsername);
				w= new RemoteWebDriver(url, op);
			}
			
			
		}
		else
		{
			
			if(browsername.equalsIgnoreCase("chrome"))
			{
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--incognito");
				w= new ChromeDriver(op);
			}
			if(browsername.equalsIgnoreCase("MicrosoftEdge"))
			{
				EdgeOptions op = new EdgeOptions();
				op.addArguments("inprivate");
				w= new EdgeDriver(op);
			
			}				
		}
			
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
		d = new dashboard(w);
		ch = new checkoutpage(w);	
		l = new loginpage(w);
	
	}
	
	@DataProvider(name = "mydata")
	public Object[][] data() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/config.properties");
		Properties p = new Properties();
		p.load(fis);
		String filepath = p.getProperty("excel");		
		
		fis = new FileInputStream(filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh =wb.getSheetAt(0);
		int rowcount = sh.getLastRowNum();
		int colcount = sh.getRow(0).getLastCellNum();
		
		Object obj[][]= new Object[rowcount][colcount];
		 	
		for(int i =0;i<rowcount;i++) //row
		{
			XSSFRow row = sh.getRow(i+1);//1
			for(int j =0;j<colcount;j++)
			{
				obj[i][j]= row.getCell(j).getStringCellValue();
			}			
		}
		return obj;		
	}
	
	public ExtentReports extentObj()
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter("./report/mytest.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
	
	public String screenshot(WebDriver w2, String testname) throws Exception
	{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("MM_dd_HH_mm_ss");
		String timestamp = now.format(formatter);
	     
		TakesScreenshot tc = (TakesScreenshot) w2;//
		File src = tc.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+testname+"_"+timestamp+".png");// 
		Files.copy(src, dest);
		
		return dest.getAbsolutePath();	
		
	}
	
	@AfterTest
 	public void terminate() throws IOException
	{				
		w.quit();
	}
	
	
}
