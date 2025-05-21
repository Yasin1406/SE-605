import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhoenixTest {
  private static final Logger logger = LoggerFactory.getLogger(PhoenixTest.class);
  private WebDriver driver;
  private BoardPage boardPage;

  @Before
  public void setUp() {
    // Force specific GeckoDriver version compatible with Firefox ESR
    WebDriverManager.firefoxdriver().driverVersion("0.35.0").setup();

    // Configure Firefox options to use ESR explicitly
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/usr/bin/firefox-esr");

    driver = new FirefoxDriver(options);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    driver.manage().window().maximize();
    boardPage = new BoardPage(driver);
    logger.info("WebDriver and BoardPage initialized successfully");
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      logger.info("WebDriver closed");
    }
  }

  @Test
  public void createBoardPageTest() {
    try {
      boardPage.navigateTo();
      boardPage.clickAddNewBoard();
      boardPage.enterBoardName("Mohammed");
      boardPage.clickInnerElement();
      boardPage.enterListName("Yasin");
      boardPage.clickH4Element();
      assertThat(boardPage.getAddNewListText(), is("Add new list..."));
      assertThat(boardPage.getBoardTitle(), is("Mohammed"));
      logger.info("Test completed successfully");
    } catch (Exception e) {
      logger.error("Test failed due to: {}", e.getMessage(), e);
      throw e;
    }
  }
}