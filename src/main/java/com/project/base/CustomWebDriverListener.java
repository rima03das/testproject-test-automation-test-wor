package com.project.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class CustomWebDriverListener implements WebDriverListener {
	 private WebDriver driver;

	    public CustomWebDriverListener(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void onError(WebDriver driver, WebElement element, RuntimeException exception) {
	        System.out.println("Exception detected: " + exception.getMessage());
	        handleUnexpectedPopup();
	    }

	    private void handleUnexpectedPopup() {
	        try {
	            By okButton = By.xpath("//button[text()='Ok']");

	            List<WebElement> popups = driver.findElements(okButton);
	            while (!popups.isEmpty()){
	                System.out.println("Handling error pop-up...");
	                driver.findElement(okButton).click();
	                popups = driver.findElements(okButton);
	            }
	        } catch (Exception e) {
	            System.out.println("No error pop-up detected.");
	        }
	    }
}
