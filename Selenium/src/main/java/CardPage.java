import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CardPage {
    private static final Logger logger = LoggerFactory.getLogger(CardPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By addNewCardLink = By.linkText("Add a new card...");
    private final By cardNameInput = By.id("card_name");
    private final By newCardForm = By.id("new_card_form");
    private final By cardSubmitButton = By.cssSelector("button[type='submit']");
    private final By cardNameDisplay = By.cssSelector(".card-content > span");
    private final By modalCardNameDisplay = By.cssSelector(".info h3");
    private final By cardContent = By.cssSelector(".card-content");
    private final By commentTextArea = By.cssSelector("textarea");
    private final By commentSubmitButton = By.cssSelector("button");
    private final By commentTextDisplay = By.cssSelector(".comment .text");
    private final By closeModalButton = By.cssSelector("a.close");
    private final By editLink = By.linkText("Edit");
    private final By descriptionTextArea = By.cssSelector("textarea:nth-child(2)");
    private final By editNameInput = By.cssSelector("input");
    private final By editSubmitButton = By.cssSelector("button:nth-child(3)");
    private final By descriptionDisplay = By.cssSelector("p");

    public CardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void createCard(String cardName) {
        logger.info("Creating card with name '{}'", cardName);
        clickAddNewCard();
        enterCardName(cardName);
        clickNewCardForm();
        clickSubmitButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameDisplay));
        logger.info("Card creation completed");
    }

    public void addComment(String comment) {
        logger.info("Adding comment '{}'", comment);
        clickCardContent();
        enterComment(comment);
        clickCommentSubmitButton();
        logger.info("Comment addition completed");
    }

    public void editCardDescription(String description) {
        logger.info("Editing card description to '{}'", description);
        clickCardContent();
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(editLink));
        editButton.click();
        logger.info("Clicked edit link");
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextArea));
        textarea.clear();
        textarea.sendKeys(description);
        logger.info("Entered description '{}'", description);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(editSubmitButton));
        button.click();
        logger.info("Clicked submit button to save description");
    }

    public void editCardName(String newName) {
        logger.info("Editing card name to '{}'", newName);
        clickCardContent();
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(editLink));
        editButton.click();
        logger.info("Clicked edit link");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(editNameInput));
        input.clear();
        input.sendKeys(newName);
        logger.info("Entered new card name '{}'", newName);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(editSubmitButton));
        button.click();
        logger.info("Clicked submit button to save card name");
    }

    public void closeCardModal() {
        logger.info("Closing card modal");
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeModalButton));
        closeButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".md-overlay")));
        logger.info("Card modal closed");
    }

    public void clickAddNewCard() {
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

    public void clickNewCardForm() {
        logger.info("Clicking new card form");
        WebElement form = wait.until(ExpectedConditions.elementToBeClickable(newCardForm));
        form.click();
        logger.info("Clicked new card form");
    }

    public void clickSubmitButton() {
        logger.info("Clicking submit button");
        logger.info("Page source before submit: {}", driver.getPageSource());
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cardSubmitButton));
        button.click();
        logger.info("Clicked submit button");
    }

    public String getCardNameDisplay() {
        logger.info("Retrieving displayed card name from board page");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed card name: '{}'", text);
        return text;
    }

    public String getModalCardNameDisplay() {
        logger.info("Retrieving displayed card name from modal");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(modalCardNameDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed modal card name: '{}'", text);
        return text;
    }

    public String getCardDescriptionDisplay() {
        logger.info("Retrieving displayed card description");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed card description: '{}'", text);
        return text;
    }

    public void clickCardContent() {
        logger.info("Clicking card content");
        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(cardContent));
        card.click();
        logger.info("Clicked card content");
    }

    public void enterComment(String comment) {
        logger.info("Entering comment '{}'", comment);
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextArea));
        textarea.clear();
        textarea.sendKeys(comment);
        logger.info("Entered comment '{}'", comment);
    }

    public void clickCommentSubmitButton() {
        logger.info("Clicking comment submit button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(commentSubmitButton));
        button.click();
        logger.info("Clicked comment submit button");
    }

    public String getCommentTextDisplay() {
        logger.info("Retrieving displayed comment text");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextDisplay));
        String text = element.getText();
        logger.info("Retrieved displayed comment text: '{}'", text);
        return text;
    }

    public String getPageSourceOnError() {
        logger.info("Retrieving page source for debugging");
        return driver.getPageSource();
    }
}