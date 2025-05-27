package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.JavascriptExecutor;
import utils.StringUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SignUpPageTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
        signUpPage = new SignUpPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSignUp() {
        signUpPage.navigateTo();
        signUpPage.enterFirstName("Mohammed");
        signUpPage.enterLastName("Yasin");
        signUpPage.enterEmail(StringUtils.generateRandomString() +"@iit.du.ac.bd");
        signUpPage.enterPassword("12345");
        signUpPage.enterPasswordConfirmation("12345");
        signUpPage.clickSubmitButton();
    }

    private String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}