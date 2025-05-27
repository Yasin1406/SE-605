import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ListPage {
    private static final Logger logger = LoggerFactory.getLogger(ListPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By addCardLink = By.linkText("Add a new card...");
    private final By cardNameField = By.id("card_name");
    private final By submitButton = By.cssSelector("button");
    private final By cardContent = By.cssSelector(".card-content > span");
    private final By errorMessage = By.cssSelector(".error");


    public ListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddCardLink() {
        logger.info("Clicking add card link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addCardLink));
        link.click();
        logger.info("Clicked add card link");
    }

    public void enterCardName(String cardName) {
        logger.info("Entering card name '{}'", cardName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameField));
        input.clear();
        input.sendKeys(cardName);
        logger.info("Entered card name '{}'", cardName);
    }

    public void clickSubmitButton() {
        logger.info("Clicking submit button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
        logger.info("Clicked submit button");
    }

    public String getCardContent() {
        logger.info("Retrieving card content");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent));
        String text = element.getText();
        logger.info("Retrieved card content: '{}'", text);
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