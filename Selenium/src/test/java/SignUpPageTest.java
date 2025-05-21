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

public class SignUpPageTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserTest.class);
    private WebDriver driver;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        // Force specific GeckoDriver version compatible with Firefox ESR
        WebDriverManager.firefoxdriver().driverVersion("0.35.0").setup();

        // Configure Firefox options to use ESR explicitly
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox-esr");

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize(); // Maximize window instead of setting specific size
        signUpPage = new SignUpPage(driver);
        logger.info("WebDriver and SignUpPage initialized successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }

    @Test
    public void createUserTest() {
        try {
            signUpPage.navigateToSignIn();
            signUpPage.clickCreateNewAccountLink();
            signUpPage.enterFirstName("Mohammed");
            signUpPage.enterLastName("Yasin");
            signUpPage.enterEmail("bsse1406@iit.du.ac.bd");
            signUpPage.enterPassword("1406");
            signUpPage.enterPasswordConfirmation("1406");
            signUpPage.clickSubmitButton();
            // Assuming the first submission fails due to weak password
            signUpPage.enterPassword("Yasin1406");
            signUpPage.enterPasswordConfirmation("Yasin1406");
            signUpPage.clickSubmitButton();
            logger.info("createUserTest completed successfully");
        } catch (Exception e) {
            logger.error("createUserTest failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void signUpWithExistingEmail() {
        try {
            signUpPage.navigateToSignUp();
            signUpPage.enterFirstName("Mohammed");
            signUpPage.enterLastName("Yasin");
            signUpPage.enterEmail("john@phoenix-trello.com");
            signUpPage.enterPassword("yasin");
            signUpPage.enterPasswordConfirmation("yasin");
            signUpPage.clickSubmitButton();
            assertThat(signUpPage.getErrorMessage(), is("Email already taken"));
            logger.info("signUpWithExistingEmail completed successfully");
        } catch (Exception e) {
            logger.error("signUpWithExistingEmail failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }
}