package com.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.example.pages.LoginPage;
import com.example.pages.DashboardPage;

public class UserAuthenticationSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    @Given("I am on the login page")
    public void navigateToLoginPage() {
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
    }
    
    @When("I enter valid username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }
    
    @When("I enter valid password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }
    
    @When("I click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }
    
    @Then("I should see the dashboard page")
    public void verifyDashboardDisplayed() {
        dashboardPage = new DashboardPage();
        dashboardPage.verifyDashboardDisplayed();
    }
    
    @Then("I should see a welcome message")
    public void verifyWelcomeMessage() {
        dashboardPage.verifyWelcomeMessage();
    }
}