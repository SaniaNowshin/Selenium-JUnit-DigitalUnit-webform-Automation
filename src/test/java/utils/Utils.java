package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Utils {
    public static void scrollBy(WebDriver driver, int height){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+height+")");

    }
}
