package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = {"src/test/java/Features/Deals.feature"},
	    glue = {"StepDefinitions","Hooks"},
	    tags = "@CRUD_Done"
	)
/*plugin={"pretty","html:target/htmlreport.html"}*/
public class TestNgTestRunner extends AbstractTestNGCucumberTests {
	
}
