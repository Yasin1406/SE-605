package org.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Test1Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    // Force specific GeckoDriver version compatible with Firefox ESR
    WebDriverManager.firefoxdriver().driverVersion("0.35.0").setup();

    // Configure Firefox options to use ESR explicitly
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/usr/bin/firefox-esr");

    driver = new FirefoxDriver(options);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void test1() {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      driver.get("http://localhost:4000/sign_in");
      driver.manage().window().setSize(new Dimension(1443, 1031));
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner"))).click();
      WebElement boardName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("board_name")));
      boardName.sendKeys("14");
      boardName.sendKeys(Keys.ENTER);
    } catch (Exception e) {
      fail("Test failed: " + e.getMessage());
    }
  }
}