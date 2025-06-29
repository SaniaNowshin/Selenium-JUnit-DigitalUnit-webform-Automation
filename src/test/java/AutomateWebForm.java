import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomateWebForm {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void formSubmission() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        //Close AD batton
        WebElement closeBatton = driver.findElement(By.className("banner-close-button"));
        closeBatton.click();

        List<WebElement> formElement = driver.findElements(By.className("form-control"));

        //Fill Name field
        formElement.get(0).sendKeys("Sania");
        Assertions.assertEquals("Sania", formElement.get(0).getAttribute("value"));

        //Fill Number field
        formElement.get(1).sendKeys("01312345678");
        Assertions.assertEquals("01312345678", formElement.get(1).getAttribute("value"));

        //Today's date
        WebElement dateElement = driver.findElement(By.id("edit-date"));
        //dateElement.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        dateElement.sendKeys(currentDate);
        Assertions.assertEquals("06/29/2025", currentDate);

        //Fill Email
        formElement.get(3).sendKeys("saniaTest@gmail.com");
        Assertions.assertEquals("saniaTest@gmail.com", formElement.get(3).getAttribute("value"));

        // About yourself
        Utils.scrollBy(driver, 1000);
        formElement.get(4).sendKeys("I am Sania Islam Nowshin, an postgraduate and currently learning Software Quality Assurance.");
        String expectedText = "I am Sania Islam Nowshin, an postgraduate and currently learning Software Quality Assurance.";
        Assertions.assertTrue(expectedText.contains(formElement.get(4).getAttribute("value")));

        //Upload File
        Utils.scrollBy(driver, 1000);
        String relativePath = "\\src\\test\\resources\\building.jpg";
        String absolutePath = System.getProperty("user.dir") + relativePath;
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(absolutePath);

        // Click on Check Box
        Utils.scrollBy(driver, 1000);
        Thread.sleep(1000);
        driver.findElement(By.id("edit-age")).click();

        // Submit
        Thread.sleep(1000);  // Use only minimal sleep for stabilization
        driver.findElement(By.id("edit-submit")).click();

        // Assert Success Message
        String actualMessage = driver.findElement(By.tagName("h1")).getText();
        String expectedMessage = "Thank you for your submission!";
        Assertions.assertEquals(actualMessage, expectedMessage);

    }

    @AfterAll
    @Test
    public void close() {
        driver.quit();
    }
}

