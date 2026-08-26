package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class loginpage 
{
	public WebDriver w;  //null
	@FindBy(css = "#user-name")WebElement user_ele;
	@FindBy(css = "#password")WebElement password_ele;
	@FindBy(css = "#login-button")WebElement login_button_ele;
		
	public loginpage(WebDriver w2) 
	{
		this.w= w2;
		PageFactory.initElements(w, this);
	}

	public void dologin(String user, String pass, String expURL) throws Exception
	{
		user_ele.sendKeys(user);
		password_ele.sendKeys(pass);
		login_button_ele.click();
		Thread.sleep(2000);
		
		String actURL = w.getCurrentUrl();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actURL, expURL);
		
		sa.assertAll();
	}

}
