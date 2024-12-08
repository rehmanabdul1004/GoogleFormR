package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.logging.LogType;
import java.time.LocalTime;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import org.openqa.selenium.support.ui.Select;

public class TestCases {

    WebDriver driver;

    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id='mG61Hd']/div[2]/div/div[2]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/input")
    public WebElement Namefield;

    @FindBy(xpath = "//*[@id='mG61Hd']/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/textarea")
    public WebElement Automation_Field_Debrief;

    @FindBy(xpath = "//*[@id=\"i19\"]/div[3]/div")
    public WebElement Experience;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[1]/div[1]")
    public WebElement dropdown;

    @FindBy(xpath = "//input[@type='date']")    
    public WebElement date1;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[3]/div/div[1]/div/div[1]/input")
    public WebElement timem;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/input")
    public WebElement timeh;
    // Test Case 01
    @Test
    public void testCase01() throws InterruptedException {
        String pageurl = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Updated WebDriverWait for Selenium 4

        driver.get(pageurl);
        
        // Wait for Namefield to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(Namefield));
        
        // Send data to the Namefield
        Namefield.sendKeys("Crio Learner"); 
        wait.until(ExpectedConditions.elementToBeClickable(Automation_Field_Debrief));
        String data = "I want to be the best QA Engineer! " + System.currentTimeMillis();
        Automation_Field_Debrief.sendKeys(data);
        String[] automated_Data = {"Java","Selenium","TestNG"};
        for (String i : automated_Data) {
            WebElement radioAutomationTesting = driver.findElement(By.xpath("(//span[text()='" + i + "']/parent::div/parent::div/parent::div)/div[1]"));
            if(!radioAutomationTesting.isSelected()){
                radioAutomationTesting.click();
                System.out.println(i + " label clicked!");
            }  
            System.out.println("Hurray! " + i + " radio button was clicked.");
        }
        
        if(!Experience.isSelected()){
            Experience.click();
        }


        //driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[2]")).click();
        //Thread.sleep(2000);
        //driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[1]/div[3]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[2]/div[3]/span")).click();
        Thread.sleep(500);
        LocalDate date= LocalDate.now();
        LocalDate Sevendate = date.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDate = Sevendate.format(formatter);
        System.out.println(formatDate);
        date1.sendKeys(formatDate);

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter currentTimeInM = DateTimeFormatter.ofPattern("mm");
        timem.sendKeys(currentTime.format(currentTimeInM).toString());
        DateTimeFormatter currentTimeInH = DateTimeFormatter.ofPattern("HH");
        timeh.sendKeys(currentTime.format(currentTimeInH).toString());
        driver.findElement(By.xpath("//*[text()=\"Submit\"]")).click();
        //dropdown.click();
        //Thread.sleep(2000);
        
        //WebElement Mrword = driver.findElement(By.xpath("//*[text()='Mr']"));
        //Mrword.click();
        Thread.sleep(5000);
        System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[3]")).getText());
        
        Thread.sleep(5000);  
    }

    // Before Test - Set up the WebDriver and initialize PageFactory
    @BeforeTest
    public void startBrowser() {
        // Configure logging for Selenium logs
        System.setProperty("java.util.logging.config.file", "logging.properties");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        // Set the ChromeDriver log property
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        // Initialize the ChromeDriver
        driver = new ChromeDriver(options);

        // Maximize the window
        driver.manage().window().maximize();

        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // After Test - Close the browser and quit the driver
    @AfterTest
    public void endTest() {
        // Close and quit the driver after the test is complete
        driver.close();
        driver.quit();
    }
}
