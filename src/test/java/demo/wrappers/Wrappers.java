package demo.wrappers;
 
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
     public static void WsendKeys(WebElement element, String string ){
        element.clear();
        element.sendKeys(string);
        System.out.println("Success to write in element");
     }

     public static void log(String string){
        System.out.println(string);
     }

     public static void sync(int value) throws InterruptedException{
        Thread.sleep(value);
     }
}
