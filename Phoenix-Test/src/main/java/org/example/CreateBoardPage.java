package org.example;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

public class CreateBoardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String signInUrl = "http://localhost:4000/sign_in";
    private final By signInButton = By.cssSelector("button");
    private final By addNewBoardButton = By.id("add_new_board");
    private final By boardNameField = By.id("board_name");
    private final By submitButton = By.cssSelector("button");

    public CreateBoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToSignIn() {
        driver.get(signInUrl);
        driver.manage().window().setSize(new Dimension(1300, 736));
    }

    public void clickSignInButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        button.click();
    }

    public void clickAddNewBoardButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton));
        button.click();
    }

    public void enterBoardName(String boardName) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(boardNameField));
        field.sendKeys(boardName);
    }

    public void clickSubmitButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
    }
}