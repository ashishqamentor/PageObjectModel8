package greenkartstepdefination;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(  features = "src/test/java/featurefiles/greenkart.feature", 
				glue = "greenkartstepdefination", 
				plugin = { 	"pretty", "html:report/BDDreport/greenkart.html", 
									  "json:report/BDDreport/greenkart.json" },
				monochrome = true
)
public class geenkartrunner extends AbstractTestNGCucumberTests {

}
