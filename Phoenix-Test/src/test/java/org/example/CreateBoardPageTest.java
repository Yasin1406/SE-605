package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.CreateBoardPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.JavascriptExecutor;
import utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CreateBoardPageTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private SignInPage signInPage;
    private CreateBoardPage createBoardPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
        signInPage = new SignInPage(driver);
        createBoardPage = new CreateBoardPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void navigateTo() {
        driver.get(signInPage.getUrl());
        driver.manage().window().setSize(new Dimension(550, 691));
    }

    @Test
    public void createBoardTest() {
        navigateTo();
        createBoardPage.clickSignInButton();
        createBoardPage.clickAddNewBoardButton();
        createBoardPage.enterBoardName(StringUtils.generateRandomString());
        createBoardPage.clickSubmitButton();
    }
}