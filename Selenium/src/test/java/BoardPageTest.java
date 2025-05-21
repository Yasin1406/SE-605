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

public class BoardPageTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardPageTest.class);
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
    public void testCreateNewBoard() {
        try {
            boardPage.navigateToLogin();
            boardPage.performLogin();
            boardPage.clickAddNewBoard();
            boardPage.enterBoardName("Mohammed");
            boardPage.submitBoard();
            assertThat(boardPage.getBoardNameDisplay(), is("Mohammed"));
            logger.info("testCreateNewBoard completed successfully");
        } catch (Exception e) {
            logger.error("testCreateNewBoard failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void testCreateNewList() {
        try {
            boardPage.navigateToLogin();
            boardPage.performLogin();
            boardPage.clickAddNewBoard();
            boardPage.enterBoardName("Mohammed");
            boardPage.submitBoard();
            boardPage.clickListContainer();
            boardPage.enterListName("Yasin");
            boardPage.submitList();
            assertThat(boardPage.getListNameDisplay(), is("Yasin"));
            logger.info("testCreateNewList completed successfully");
        } catch (Exception e) {
            logger.error("testCreateNewList failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void testCreateNewCard() {
        try {
            boardPage.navigateToLogin();
            boardPage.performLogin();
            boardPage.clickAddNewBoard();
            boardPage.enterBoardName("Mohammed");
            boardPage.submitBoard();
            boardPage.clickListContainer();
            boardPage.enterListName("Yasin");
            boardPage.submitList();
            boardPage.clickAddNewCardLink();
            boardPage.enterCardName("1406");
            boardPage.submitCard();
            assertThat(boardPage.getCardNameDisplay(), is("1406"));
            logger.info("testCreateNewCard completed successfully");
        } catch (Exception e) {
            logger.error("testCreateNewCard failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void testSignOut() {
        try {
            boardPage.navigateToLogin();
            boardPage.performLogin();
            boardPage.clickSignOut();
            assertThat(boardPage.getSignInButtonText(), is("Sign in"));
            logger.info("testSignOut completed successfully");
        } catch (Exception e) {
            logger.error("testSignOut failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }
}