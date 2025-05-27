import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListPageTest {
    private static final Logger logger = LoggerFactory.getLogger(ListPageTest.class);
    private WebDriver driver;
    private BoardPage boardPage;
    private ListPage listPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox-esr");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(960, 1012));
        boardPage = new BoardPage(driver);
        listPage = new ListPage(driver);
        loginPage = new LoginPage(driver);
        logger.info("WebDriver, BoardPage, ListPage, and LoginPage initialized successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }

    @Test
    public void testCreateList() {
        try {
            loginPage.performLogin("john@phoenix-trello.com", "12345678");
            boardPage.createBoard("Mohammed");
            listPage.createList("Yasin");
            assertThat(listPage.getListNameDisplay(), is("Yasin"));
            logger.info("testCreateList completed successfully");
        } catch (Exception e) {
            logger.error("testCreateList failed due to: {}. Page source: {}", e.getMessage(), listPage.getPageSourceOnError(), e);
            throw e;
        }
    }
}