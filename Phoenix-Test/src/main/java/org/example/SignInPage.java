package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Page Object class for the Sign-In page
public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = "http://localhost:4000/sign_in";
    private final By passwordField = By.id("user_password");
    private final By signInButton = By.cssSelector("button");
    private final By errorMessage = By.cssSelector(".error");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public void clickSignInButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        button.click();
    }

    public String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorElement.getText();
    }

    public void clickErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.elementToBeClickable(errorMessage));
        errorElement.click();
    }

    public void doubleClickErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.elementToBeClickable(errorMessage));
        Actions builder = new Actions(driver);
        builder.doubleClick(errorElement).perform();
    }

    public String getUrl() {
        return url;
    }
}