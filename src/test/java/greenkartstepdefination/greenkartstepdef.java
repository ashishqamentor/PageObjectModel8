package greenkartstepdefination;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

import Testpackage.Baseclass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class greenkartstepdef extends Baseclass
{

	@Given("user is on greenkart site")
	public void user_is_on_greenkart_site() throws Exception
	{
		launch();
		w.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}	
	
	@When("user add items in basket")
	public void user_add_items_in_basket() throws Exception
	{
		d.addtokart();			
	}
		
	@And("do checkout journey")
	public void do_checkout_journey()
	{
		ch.checkout("ashish","India");
	}
	
	@Then("successful checkout screenshould be dispalyed and screenshot should be capture.")
	public void success() throws Exception
	{
		TakesScreenshot tc = (TakesScreenshot)w;
		File src = tc.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/geenkartBDD.png");
		Files.copy(src, dest);
		w.quit();
		
	}
	
}
