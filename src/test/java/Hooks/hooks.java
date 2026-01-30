package Hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.project.base.BaseClass;
import com.project.base.ExtentReportListener;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hooks extends ExtentReportListener {
	private static String prevFeatureName ="";
	
	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("Starting scenario: " + scenario.getName());
		String featureName = scenario.getUri().toString();
		featureName = featureName.replaceAll(".*/(\\w+)\\.feature$", "$1");
		
		if(!prevFeatureName.equalsIgnoreCase(featureName)) {
			test = extent.createTest(Feature.class, featureName).assignAuthor("Rahul");//"Validate Login functionality for the Infin8 application"
		}
		prevFeatureName = featureName;
	}

	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			System.out.println("Scenario failed: " + scenario.getName());
			System.out.println("Failure details: " + scenario.getStatus());
		} else {
			System.out.println("Scenario passed: " + scenario.getName());
		}		
		if (driver != null) {
            driver.quit();  // Closes the browser after each scenario
        }
	}
}
