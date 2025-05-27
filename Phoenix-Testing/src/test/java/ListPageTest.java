import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListPageTest extends TestCase{
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private LoginPage loginPage;
    private ListPage listPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        loginPage = new LoginPage(driver);
        listPage = new ListPage(driver);
        driver.manage().window().setSize(new Dimension(654, 751));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void validCardCreation() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.clickLoginButton();
        driver.get("http://localhost:4000/board"); // Navigate to board/list page
        listPage.clickAddCardLink();
        listPage.enterCardName("Study Task 1");
        listPage.clickSubmitButton();
        assertThat(listPage.getCardContent(), is("Study Task 1"));
    }

    @Test
    public void invalidCardCreation() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.clickLoginButton();
        driver.get("http://localhost:4000/board"); // Navigate to board/list page
        listPage.clickAddCardLink();
        listPage.enterCardName("");
        listPage.clickSubmitButton();
        assertThat(listPage.getErrorMessage(), is("Please fill out this field."));
    }

    @Test
    public void cancelCardCreation() {
        loginPage.navigateToLogin();
        driver.manage().window().setSize(new Dimension(602, 933));
        loginPage.clickLoginButton();
        driver.get("http://localhost:4000/board"); // Navigate to board/list page
        listPage.clickAddCardLink();
        // No submission, assuming form is cancelled by not clicking submit
        assertNull("No card should be created", driver.findElements(By.cssSelector(".card-content > span")).isEmpty() ? null : driver.findElement(By.cssSelector(".card-content > span")).getText());
    }
}