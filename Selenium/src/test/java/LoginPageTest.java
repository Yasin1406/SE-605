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

public class LoginPageTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageTest.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        // Force specific GeckoDriver version compatible with Firefox ESR
        WebDriverManager.firefoxdriver().driverVersion("0.36.0").setup();

        // Configure Firefox options to use ESR explicitly
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox-esr");

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        logger.info("WebDriver and LoginPage initialized successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        try {
            loginPage.navigateToLogin();
            loginPage.enterEmail("john@phoenix-trello.com");
            loginPage.enterPassword("12345678");
            loginPage.clickSubmitButton();
            assertThat(loginPage.getDisplayedName(), is("John Doe"));
            logger.info("testLoginWithValidCredentials completed successfully");
        } catch (Exception e) {
            logger.error("testLoginWithValidCredentials failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void testLoginWithInvalidPassword() {
        try {
            loginPage.navigateToLogin();
            loginPage.enterEmail("john@phoenix-trello.com");
            loginPage.enterPassword("123456");
            loginPage.clickSubmitButton();
            assertThat(loginPage.getErrorMessage(), is("Invalid email or password"));
            logger.info("testLoginWithInvalidPassword completed successfully");
        } catch (Exception e) {
            logger.error("testLoginWithInvalidPassword failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void testLoginWithInvalidEmail() {
        try {
            loginPage.navigateToLogin();
            loginPage.enterEmail("john@phoenix-trello.co");
            loginPage.enterPassword("12345678");
            loginPage.clickSubmitButton();
            assertThat(loginPage.getErrorMessage(), is("Invalid email or password"));
            logger.info("testLoginWithInvalidEmail completed successfully");
        } catch (Exception e) {
            logger.error("testLoginWithInvalidEmail failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    public void testLoginWithInvalidEmailAndPassword() {
        try {
            loginPage.navigateToLogin();
            loginPage.enterEmail("john@phoenix-trello.co");
            loginPage.enterPassword("123456");
            loginPage.clickSubmitButton();
            assertThat(loginPage.getErrorMessage(), is("Invalid email or password"));
            logger.info("testLoginWithInvalidEmailAndPassword completed successfully");
        } catch (Exception e) {
            logger.error("testLoginWithInvalidEmailAndPassword failed due to: {}", e.getMessage(), e);
            throw e;
        }
    }
}