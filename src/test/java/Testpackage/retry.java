package Testpackage;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retry implements IRetryAnalyzer 
{
	int count =1;
	int maxcount =2;

	@Override
	public boolean retry(ITestResult result) 
	{
		// TODO Auto-generated method stub'
		if(count<maxcount)
		{
			count++;
			return true;
		}
		return false;
	}

}
