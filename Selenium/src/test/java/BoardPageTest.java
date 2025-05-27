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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardPageTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardPageTest.class);
    private WebDriver driver;
    private BoardPage boardPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox-esr");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(960, 1012));
        boardPage = new BoardPage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        logger.info("WebDriver, BoardPage, LoginPage, and SignUpPage initialized successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }

    @Test
    public void testCreateBoard() {
        try {
            loginPage.performLogin("john@phoenix-trello.com", "12345678");
            boardPage.createBoard("Mohammed");
            assertEquals("Mohammed", boardPage.getBoardNameDisplay());
            logger.info("testCreateBoard completed successfully");
        } catch (Exception e) {
            logger.error("testCreateBoard failed due to: {}. Page source: {}", e.getMessage(), boardPage.getPageSourceOnError(), e);
            throw e;
        }
    }

    @Test
    public void testAddInvalidMemberToBoard() {
        try {
            signUpPage.performSignUp("Mohammed", "Yasin", "yasinmohammed@gmail.com", "12345", "12345");
            loginPage.performLogin("yasinmohammed@gmail.com", "12345");
            boardPage.createBoard("Board");
            boardPage.addBoardMember("john@gmail.com");
            assertThat(boardPage.getMemberErrorMessage(), is("User does not exist"));
            logger.info("testAddInvalidMemberToBoard completed successfully");
        } catch (Exception e) {
            logger.error("testAddInvalidMemberToBoard failed due to: {}. Page source: {}", e.getMessage(), boardPage.getPageSourceOnError(), e);
            throw e;
        }
    }

    @Test
    public void testAddBoardMember() {
        try {
            signUpPage.performSignUp("Mohammed", "Yasin", "mohammedyasin@gmail.com", "12345", "12345");
            loginPage.performLogin("mohammedyasin@gmail.com", "12345");
            boardPage.createBoard("Board");
            boardPage.addBoardMember("john@phoenix-trello.com");
            assertTrue(boardPage.isMemberGravatarPresent());
            logger.info("testAddBoardMember completed successfully");
        } catch (Exception e) {
            logger.error("testAddBoardMember failed due to: {}. Page source: {}", e.getMessage(), boardPage.getPageSourceOnError(), e);
            throw e;
        }
    }
}