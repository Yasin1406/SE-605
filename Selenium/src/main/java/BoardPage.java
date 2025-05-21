import org.openqa.selenium.By;
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
    private final By loginContainer = By.cssSelector(".view-container");
    private final By loginField = By.cssSelector(".field:nth-child(2)");
    private final By loginButton = By.cssSelector("button");
    private final By addNewBoardButton = By.id("add_new_board");
    private final By boardNameInput = By.id("board_name");
    private final By boardSubmitButton = By.cssSelector("button");
    private final By boardNameDisplay = By.cssSelector("h3");
    private final By listContainer = By.cssSelector(".inner");
    private final By listNameInput = By.id("list_name");
    private final By listSubmitButton = By.cssSelector("button");
    private final By listNameDisplay = By.cssSelector("h4");
    private final By addNewCardLink = By.linkText("Add a new card...");
    private final By cardNameInput = By.id("card_name");
    private final By cardSubmitButton = By.cssSelector("button");
    private final By cardNameDisplay = By.cssSelector(".card-content > span");
    private final By signOutLink = By.cssSelector("#crawler-sign-out > span");
    private final By signInButton = By.cssSelector("button");

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLogin() {
        logger.info("Navigating to http://localhost:4000/sign_in");
        driver.get("http://localhost:4000/sign_in");
    }

    public void performLogin() {
        logger.info("Performing login actions");
        WebElement container = wait.until(ExpectedConditions.elementToBeClickable(loginContainer));
        container.click();
        logger.info("Clicked login container");
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(loginField));
        field.click();
        logger.info("Clicked login field");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
        logger.info("Clicked login button");
    }

    public void clickAddNewBoard() {
        logger.info("Clicking add new board button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton));
        button.click();
        logger.info("Clicked add new board button");
    }

    public void enterBoardName(String boardName) {
        logger.info("Entering board name '{}'", boardName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput));
        input.clear();
        input.sendKeys(boardName);
        logger.info("Entered board name '{}'", boardName);
    }

    public void submitBoard() {
        logger.info("Clicking board submit button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(boardSubmitButton));
        button.click();
        logger.info("Clicked board submit button");
    }

    public String getBoardNameDisplay() {
        logger.info("Retrieving board name display");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameDisplay));
        String text = element.getText();
        logger.info("Retrieved board name: '{}'", text);
        return text;
    }

    public void clickListContainer() {
        logger.info("Clicking list container");
        WebElement container = wait.until(ExpectedConditions.elementToBeClickable(listContainer));
        container.click();
        logger.info("Clicked list container");
    }

    public void enterListName(String listName) {
        logger.info("Entering list name '{}'", listName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        input.clear();
        input.sendKeys(listName);
        logger.info("Entered list name '{}'", listName);
    }

    public void submitList() {
        logger.info("Clicking list submit button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(listSubmitButton));
        button.click();
        logger.info("Clicked list submit button");
    }

    public String getListNameDisplay() {
        logger.info("Retrieving list name display");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameDisplay));
        String text = element.getText();
        logger.info("Retrieved list name: '{}'", text);
        return text;
    }

    public void clickAddNewCardLink() {
        logger.info("Clicking add new card link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(addNewCardLink));
        link.click();
        logger.info("Clicked add new card link");
    }

    public void enterCardName(String cardName) {
        logger.info("Entering card name '{}'", cardName);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput));
        input.clear();
        input.sendKeys(cardName);
        logger.info("Entered card name '{}'", cardName);
    }

    public void submitCard() {
        logger.info("Clicking card submit button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cardSubmitButton));
        button.click();
        logger.info("Clicked card submit button");
    }

    public String getCardNameDisplay() {
        logger.info("Retrieving card name display");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameDisplay));
        String text = element.getText();
        logger.info("Retrieved card name: '{}'", text);
        return text;
    }

    public void clickSignOut() {
        logger.info("Clicking sign out link");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
        link.click();
        logger.info("Clicked sign out link");
    }

    public String getSignInButtonText() {
        logger.info("Retrieving sign-in button text");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        String text = element.getText();
        logger.info("Retrieved sign-in button text: '{}'", text);
        return text;
    }
}