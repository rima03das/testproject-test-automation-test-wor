package com.project.base;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener extends BaseClass {
	
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static ExtentTest scenario = null;

	public static ExtentReports setUp() {
//		String reportLocation = "./Reports/Extent_Report" + getTimeStamp() + ".html";
		String reportLocation = "./Reports/Extent_Report.html";
		ExtentHtmlReporter report = new ExtentHtmlReporter(reportLocation);
		report.config().setDocumentTitle("Automation Test Report");
		report.config().setReportName("BDD Cucumber Automation Report");
		report.config().setTheme(Theme.STANDARD);
		report.start();

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "8Twelve");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		return extent;

	}
	
	public static void testStep_Handle(String testStatus, ExtentTest extentTest, Throwable throwable) {
		switch (testStatus) {
        case "FAIL":
            extentTest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
            extentTest.error(throwable.fillInStackTrace());

            try {
                String screenshotRelativePath = captureScreenshot(); // already returns relative path
                extentTest.addScreenCaptureFromPath(screenshotRelativePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case "PASS":
            extentTest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
            break;

        case "SKIP":
            extentTest.pass(MarkupHelper.createLabel("Test Case is Skipped : ", ExtentColor.YELLOW));
            break;

        default:
            break;
    }
		
	}

	public static String getTimeStamp() {
		Date currentDate = new Date();
		String timestamp = currentDate.toString().replace(" ", "-").replace(":", "-");
		return timestamp;
	}

	public static String captureScreenshot() throws IOException {
		if (BaseClass.driver != null) {
	        File folder = new File("Reports/reportScreenshot");
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }

	        String fileName = "image-" + getTimeStamp() + ".png";
	        File destFile = new File(folder, fileName);

	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(srcFile, destFile);

	        // Return the relative path to be used in the report
	        return "reportScreenshot/" + fileName;
	    } else {
	        return "";
	    }
	}
}
