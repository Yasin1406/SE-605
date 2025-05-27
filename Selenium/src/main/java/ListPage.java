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

    // Locators
    private final By listInnerContainer = By.cssSelector(".inner");
    private final By listNameInput = By.id("list_name");
    private final By listSubmitButton = By.cssSelector("button[type='submit']");
    private final By listNameDisplay = By.cssSelector("h4");

    public ListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void createList(String listName) {
        logger.info("Creating list with name '{}'", listName);
        clickListInnerContainer();
        enterListName(listName);
        clickSubmitButton();
        logger.info("List creation completed");
    }

    public void clickListInnerContainer() {
        logger.info("Clicking list inner container");
        WebElement container = wait.until(ExpectedConditions.elementToBeClickable(listInnerContainer));
        container.click();
        logger.info("Clicked list inner container");
    }

    public void enterListName(String listName) {
        logger.info("Entering list name '{}'", listName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        input.clear();
        input.sendKeys(listName);
        logger.info("Entered list name '{}'", listName);
    }

    public void clickSubmitButton() {
        logger.info("Clicking submit button");
        logger.info("Page source before submit: {}", driver.getPageSource());
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(listSubmitButton));
        button.click();
        logger.info("Clicked submit button");
    }

    public String getListNameDisplay() {
        logger.info("Retrieving displayed list name");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed list name: '{}'", text);
        return text;
    }

    public String getPageSourceOnError() {
        logger.info("Retrieving page source for debugging");
        return driver.getPageSource();
    }
}