import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SignUpPage {
    private static final Logger logger = LoggerFactory.getLogger(SignUpPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By firstNameInput = By.id("user_first_name");
    private final By lastNameInput = By.id("user_last_name");
    private final By emailInput = By.id("user_email");
    private final By passwordInput = By.id("user_password");
    private final By passwordConfirmationInput = By.id("user_password_confirmation");
    private final By submitButton = By.cssSelector("button");
    private final By nameDisplay = By.cssSelector("span:nth-child(3)");
    private final By errorMessage = By.cssSelector(".error");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToSignUp() {
        logger.info("Navigating to http://localhost:4000/sign_up");
        driver.get("http://localhost:4000/sign_up");
    }

    public void enterFirstName(String firstName) {
        logger.info("Entering first name '{}'", firstName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        input.clear();
        input.sendKeys(firstName);
        logger.info("Entered first name '{}'", firstName);
    }

    public void enterLastName(String lastName) {
        logger.info("Entering last name '{}'", lastName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        input.clear();
        input.sendKeys(lastName);
        logger.info("Entered last name '{}'", lastName);
    }

    public void enterEmail(String email) {
        logger.info("Entering email '{}'", email);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.clear();
        input.sendKeys(email);
        logger.info("Entered email '{}'", email);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        input.clear();
        input.sendKeys(password);
        logger.info("Entered password");
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        logger.info("Entering password confirmation");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationInput));
        input.clear();
        input.sendKeys(passwordConfirmation);
        logger.info("Entered password confirmation");
    }

    public void clickSubmitButton() {
        logger.info("Clicking submit button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
        logger.info("Clicked submit button");
    }

    public String getDisplayedName() {
        logger.info("Retrieving displayed name");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed name: '{}'", text);
        return text;
    }

    public String getErrorMessage() {
        logger.info("Retrieving error message");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String text = element.getText();
        logger.info("Retrieved error message: '{}'", text);
        return text;
    }
}