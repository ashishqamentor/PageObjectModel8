package loginstepdefination;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/featurefiles/login.feature",
		glue = "loginstepdefination",
		plugin = { "pretty" , "html:report/BDDreport/login.html",
							  "json:report/BDDreport/login.json" },
		monochrome = true		
		)

public class loginrunner extends AbstractTestNGCucumberTests
{

}
