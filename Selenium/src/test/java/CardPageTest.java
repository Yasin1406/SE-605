import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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

public class CardPageTest {
    private static final Logger logger = LoggerFactory.getLogger(CardPageTest.class);
    private static WebDriver driver;
    private static BoardPage boardPage;
    private static ListPage listPage;
    private static CardPage cardPage;
    private static LoginPage loginPage;
    private static SignUpPage signUpPage;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox-esr");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(960, 1012));
        boardPage = new BoardPage(driver);
        listPage = new ListPage(driver);
        cardPage = new CardPage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        logger.info("WebDriver, BoardPage, ListPage, CardPage, LoginPage, and SignUpPage initialized successfully");

        // User details
        String email = "bsse1407@iit.du.ac.bd";
        String password = "munna1407";
        String firstName = "Nowsad Hossen";
        String lastName = "Munna";

        try {
            // Sign up with the fixed email
            signUpPage.performSignUp(firstName, lastName, email, password, password);
            logger.info("Created new user with email: {}", email);

            // Log in with the new user
            loginPage.performLogin(email, password);
            logger.info("Logged in with user: {}", email);
        } catch (Exception e) {
            logger.error("Failed to create or log in with user '{}': {}. Page source: {}",
                    email, e.getMessage(), driver.getPageSource(), e);
            throw e;
        }
    }

    @After
    public void tearDown() {
        try {
            boardPage.navigateToHomePage();
            logger.info("Navigated to homepage after test, keeping browser session active");
        } catch (Exception e) {
            logger.error("Failed to navigate to homepage: {}. Page source: {}",
                    e.getMessage(), driver.getPageSource(), e);
            throw e;
        }
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed after all tests");
        }
    }

    @Test
    public void testCreateCard() {
        try {
            boardPage.createBoard("Mohammed");
            listPage.createList("Yasin");
            cardPage.createCard("1406");
            assertThat(cardPage.getCardNameDisplay(), is("1406"));
            logger.info("testCreateCard completed successfully");
        } catch (Exception e) {
            logger.error("testCreateCard failed due to: {}. Page source: {}",
                    e.getMessage(), cardPage.getPageSourceOnError(), e);
            throw e;
        }
    }

    @Test
    public void testMakeComment() {
        try {
            boardPage.createBoard("Board");
            listPage.createList("List");
            cardPage.createCard("Card");
            cardPage.addComment("Hello");
            assertThat(cardPage.getCommentTextDisplay(), is("Hello"));
            cardPage.closeCardModal();
            logger.info("testMakeComment completed successfully");
        } catch (Exception e) {
            logger.error("testMakeComment failed due to: {}. Page source: {}",
                    e.getMessage(), cardPage.getPageSourceOnError(), e);
            throw e;
        }
    }
}