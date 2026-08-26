package Testpackage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;

public class listen extends Baseclass implements ITestListener 
{

	ExtentReports extent = extentObj(); // global // instance
	ExtentTest test ;  // actual logger of report.
	ThreadLocal<ExtentTest> thread = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		String testname=result.getMethod().getMethodName();
		test = extent.createTest(testname);
		thread.set(test); //setting uniqueness to each test
		thread.get().info("i am running atomation framework.");	// thread.get()--> to get unique test.	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println("test is pass.");
		thread.get().pass("Test is passed");
		
		//code added to capture screenshot
		String testname=result.getMethod().getMethodName();
		try {
			
			w=(WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance()); 
			String imagepath=screenshot(w,testname);			
			
			//to attach image to report
			thread.get().addScreenCaptureFromPath(imagepath);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.flush(); //to print report
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		System.out.println("test is failed");
		thread.get().fail("Test failed");
		thread.get().fail( result.getThrowable() );
		
		//code added to capture screenshot
		String testname=result.getMethod().getMethodName();
		try {
			 
		    w=(WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
		  	String imagepath=screenshot(w,testname);
			
			//to attach image to report
			thread.get().addScreenCaptureFromPath(imagepath);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extent.flush();
	}

}
