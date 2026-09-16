package loginstepdefination;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginstepdef 
{
	
	WebDriver w;
	
	@FindBy(css = "#user-name")WebElement user_ele;
	@FindBy(css = "#password")WebElement password_ele;
	@FindBy(css = "#login-button")WebElement login_button_ele;
	
	@Given("I am on the sause login page")
	public void page()
	{
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--incognito");
		w= new ChromeDriver(op);
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		
		PageFactory.initElements(w, this); // to initialize all elements
		
		w.get("https://www.saucedemo.com/");
	}
	
	@When("^I enter (.+) and (.+)$")
	public void data(String user, String pass) 
	{
		user_ele.sendKeys(user);
		password_ele.sendKeys(pass);
	}
	
	@And("click on login button")
	public void click() throws Exception
	{
		Thread.sleep(2000);
		login_button_ele.click();
	}
	
	@Then("I should see dashboard page with url {string}")
	public void dashboard(String expURL)
	{
		String actURL = w.getCurrentUrl();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actURL, expURL);		
		sa.assertAll();
		w.quit();
		
	}
}
