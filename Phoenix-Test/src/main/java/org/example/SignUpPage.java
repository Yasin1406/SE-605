package org.example;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String signInUrl = "http://localhost:4000/sign_in";
    private final By createAccountLink = By.linkText("Create new account");
    private final By firstNameField = By.id("user_first_name");
    private final By lastNameField = By.id("user_last_name");
    private final By emailField = By.id("user_email");
    private final By passwordField = By.id("user_password");
    private final By passwordConfirmationField = By.id("user_password_confirmation");
    private final By submitButton = By.cssSelector("button");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get(signInUrl);
        driver.manage().window().setSize(new Dimension(854, 691));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(createAccountLink));
        link.click();
    }

    public void enterFirstName(String firstName) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        element.click();
        element.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
        element.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        element.sendKeys(password);
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(passwordConfirmationField));
        element.sendKeys(passwordConfirmation);
    }

    public void clickSubmitButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
    }
}