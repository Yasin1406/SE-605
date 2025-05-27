package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.openqa.selenium.Dimension;


public class SignInPageTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  private JavascriptExecutor js;
  private SignInPage signInPage;

  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
    FirefoxOptions options = new FirefoxOptions();
    driver = new FirefoxDriver(options);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<>();
    signInPage = new SignInPage(driver);
  }

  @After
  public void tearDown() {
    if(driver!=null)  driver.quit();
  }

  public void navigateTo() {
    driver.get(signInPage.getUrl());
    driver.manage().window().setSize(new Dimension(550, 691));
  }

  @Test
  public void testWhetherSignInWithInvalidPassword() {
    navigateTo();
    signInPage.enterPassword("123456");
    signInPage.clickSignInButton();
    // Simulate the redundant clicks and double-clicks from the original test
    signInPage.clickErrorMessage();
    signInPage.clickErrorMessage();
    signInPage.doubleClickErrorMessage();
    signInPage.clickErrorMessage();
    signInPage.clickErrorMessage();
    signInPage.doubleClickErrorMessage();
    assertEquals("Invalid email or password", signInPage.getErrorMessage());
  }

  @Test
  public void testSignInWithValidPassword() {
    navigateTo();
    signInPage.clickSignInButton();
  }
}
