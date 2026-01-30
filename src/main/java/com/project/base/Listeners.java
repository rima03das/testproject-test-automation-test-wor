package com.project.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

public class Listeners extends ExtentReportListener implements ITestListener {


	public static ExtentReports extent ;
	
	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
			
	}

	public void onTestFailure(ITestResult result) {
//		//handle pop up
//		try {
//            By okButton = By.xpath("//button[(text()='OK') or (text()='Ok')]");
//            List<WebElement> popups = driver.findElements(okButton);
//            while (!popups.isEmpty()){
//                System.out.println("Handling error pop-up...");
//                driver.findElement(okButton).click();
//                popups = driver.findElements(okButton);
//            }
//        } catch (Exception e) {
//            System.out.println("No error pop-up detected.");
//        }
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		System.out.println("Execution Started....");
		extent=setUp();
	}

	public void onFinish(ITestContext context) {
		System.out.println("Execution Completed....");
		extent.flush();
		System.out.println("Report Generated....");
	}
	
}
