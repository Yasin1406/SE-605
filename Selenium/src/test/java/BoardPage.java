import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BoardPage {
    private static final Logger logger = LoggerFactory.getLogger(BoardPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By addNewBoardButton = By.id("add_new_board");
    private final By boardNameInput = By.id("board_name");
    private final By innerElement = By.cssSelector(".inner");
    private final By listNameInput = By.id("list_name");
    private final By h4Element = By.cssSelector("h4");
    private final By addNewListText = By.cssSelector(".add-new > .inner");
    private final By boardTitle = By.cssSelector("h3");

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        logger.info("Navigating to http://localhost:4000/");
        driver.get("http://localhost:4000/");
    }

    public void clickAddNewBoard() {
        logger.info("Locating and clicking 'Add new board' button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton));
        button.click();
        logger.info("Clicked 'Add new board' button");
    }

    public void enterBoardName(String boardName) {
        logger.info("Entering board name '{}'", boardName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput));
        input.sendKeys(boardName);
        input.sendKeys(Keys.ENTER);
        logger.info("Submitted board name '{}'", boardName);
    }

    public void clickInnerElement() {
        logger.info("Clicking inner element to add a list");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(innerElement));
        element.click();
        logger.info("Clicked inner element");
    }

    public void enterListName(String listName) {
        logger.info("Entering list name '{}'", listName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        input.sendKeys(listName);
        input.sendKeys(Keys.ENTER);
        logger.info("Submitted list name '{}'", listName);
    }

    public void clickH4Element() {
        logger.info("Clicking h4 element");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(h4Element));
        element.click();
        logger.info("Clicked h4 element");
    }

    public String getAddNewListText() {
        logger.info("Retrieving 'Add new list...' text");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addNewListText));
        String text = element.getText();
        logger.info("Retrieved text: '{}'", text);
        return text;
    }

    public String getBoardTitle() {
        logger.info("Retrieving board title");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle));
        String text = element.getText();
        logger.info("Retrieved board title: '{}'", text);
        return text;
    }
}