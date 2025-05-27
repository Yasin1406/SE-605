import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BoardPage {
    private static final Logger logger = LoggerFactory.getLogger(BoardPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // Locators
    private final By addNewBoardLink = By.id("add_new_board");
    private final By boardNameInput = By.id("board_name");
    private final By boardNameDisplay = By.cssSelector("h3");
    private final By homePageLink = By.cssSelector("a[href='/'] .logo");

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void createBoard(String boardName) {
        logger.info("Creating board with name '{}'", boardName);
        clickAddNewBoard();
        enterBoardName(boardName);
        submitBoardForm();
        logger.info("Board creation completed");
    }

    public void navigateToHomePage() {
        logger.info("Navigating to homepage by clicking logo link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(homePageLink));
        link.click();
        logger.info("Navigated to homepage");
        waitForPageLoad();
    }

    public void clickAddNewBoard() {
        logger.info("Clicking add new board link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addNewBoardLink));
        link.click();
        logger.info("Clicked add new board link");
    }

    public void enterBoardName(String boardName) {
        logger.info("Entering board name '{}'", boardName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput));
        input.clear();
        input.sendKeys(boardName);
        logger.info("Entered board name '{}'", boardName);
    }

    public void submitBoardForm() {
        logger.info("Submitting board form with Enter key");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput));
        input.sendKeys(Keys.ENTER);
        logger.info("Submitted board form");
        waitForBoardPage();
    }

    public String getBoardNameDisplay() {
        logger.info("Retrieving displayed board name");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed board name: '{}'", text);
        return text;
    }

    private void waitForPageLoad() {
        logger.info("Waiting for page to load");
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        logger.info("Page loaded");
    }

    private void waitForBoardPage() {
        logger.info("Waiting for board page to load");
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameDisplay));
        logger.info("Board page loaded");
    }

    public String getPageSourceOnError() {
        logger.info("Retrieving page source for debugging");
        return driver.getPageSource();
    }
}