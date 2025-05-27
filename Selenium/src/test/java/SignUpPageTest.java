import io.github.bonigarcia.wdm.WebDriverManager;
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

public class SignUpPageTest {
    private static final Logger logger = LoggerFactory.getLogger(SignUpPageTest.class);
    private static WebDriver driver;
    private static SignUpPage signUpPage;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox-esr");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(960, 1012));
        signUpPage = new SignUpPage(driver);
        logger.info("WebDriver and SignUpPage initialized successfully");
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed after all tests");
        }
    }

    @Test
    public void testSuccessfulSignUp() {
        try {
            String email = "bsse1407@iit.du.ac.bd";
            signUpPage.performSignUp("Nowsad Hossen", "Munna", email, "munna1407", "munna1407");
            // Assuming successful signup redirects or shows a success indicator
            logger.info("testSuccessfulSignUp completed successfully");
        } catch (Exception e) {
            logger.error("testSuccessfulSignUp failed due to: {}. Page source: {}",
                    e.getMessage(), signUpPage.getPageSource(), e);
            throw e;
        }
    }

    @Test
    public void testSignUpWithShortPassword() {
        try {
            signUpPage.performSignUp("Mohammed", "Yasin", "yasin6@gmail.com", "1234", "1234");
            assertThat(signUpPage.getErrorMessage(), is("should be at least 5 character(s)"));
            logger.info("testSignUpWithShortPassword completed successfully");
        } catch (Exception e) {
            logger.error("testSignUpWithShortPassword failed due to: {}. Page source: {}",
                    e.getMessage(), signUpPage.getPageSource(), e);
            throw e;
        }
    }

    @Test
    public void testSignUpWithExistingEmail() {
        try {
            signUpPage.performSignUp("John", "Doe", "john@phoenix-trello.com", "john1234", "john1234");
            assertThat(signUpPage.getErrorMessage(), is("Email already taken"));
            logger.info("testSignUpWithExistingEmail completed successfully");
        } catch (Exception e) {
            logger.error("testSignUpWithExistingEmail failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }
}