package demo;    
import org.openqa.selenium.By;    //All the import statements as follows
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

import demo.wrappers.Wrappers;

import java.sql.Date;
import java.sql.Wrapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import org.openqa.selenium.support.ui.Select;

public class TestCases {

    WebDriver driver;   //Initializing WebDriver
    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id='mG61Hd']/div[2]/div/div[2]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/input")
    public WebElement Namefield;
    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id='mG61Hd']/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/textarea")
    public WebElement Automation_Field_Debrief;
    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id=\"i19\"]/div[3]/div")
    public WebElement Experience;
    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[1]/div[1]")
    public WebElement dropdown;
    // WebElements using FindBy annotation
    @FindBy(xpath = "//input[@type='date']")    
    public WebElement date1;
    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[3]/div/div[1]/div/div[1]/input")
    public WebElement timem;
    // WebElements using FindBy annotation
    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/input")
    public WebElement timeh;
    // Test Case 01
    @Test
    public void testCase01() throws InterruptedException {
        String pageurl = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform"; //URL for the GoogleForm page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Updated WebDriverWait for Selenium 4

        driver.get(pageurl); // Open pageURL using driver
        
        // Wait for Namefield to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(Namefield));
        
        // Send data to the Namefield
        Wrappers.WsendKeys(Namefield,"Crio Learner");   //Usage of Wrappers to sendkeys
        //Namefield.sendKeys("Crio Learner"); 
        wait.until(ExpectedConditions.elementToBeClickable(Automation_Field_Debrief));  //Usage of wait to check if elemnet is Clickable or not
        String data = "I want to be the best QA Engineer! " + System.currentTimeMillis(); 
        Wrappers.WsendKeys(Automation_Field_Debrief,data); //Updating Automation_Field_Debrief with the String provided in data using Wrappers
        //Automation_Field_Debrief.sendKeys(data);    
        String[] automated_Data = {"Java","Selenium","TestNG"};
        for (String i : automated_Data) {   // Selecting Options
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
        //Thread.sleep(3000);
        Wrappers.sync(3000);    //Usage of Wrappers
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[2]/div[3]/span")).click();
        //Thread.sleep(500);
        Wrappers.sync(500);     //Usage of Wrappers
        LocalDate date= LocalDate.now();   //LocalDate is used to present Date
        LocalDate Sevendate = date.minusDays(7);    // Usage of LocalDate to minus given days.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Usage of DateTimeFormatter to
        String formatDate = Sevendate.format(formatter);   //Here we are using to convert the Date to string format
        Wrappers.log(formatDate);   //Usage of Wrappers
        Wrappers.WsendKeys(date1,formatDate); //Sending or Typing the formatDate into date1 block 
        LocalTime currentTime = LocalTime.now();   //LocalDate is used to present Date
        DateTimeFormatter currentTimeInM = DateTimeFormatter.ofPattern("mm");
        Wrappers.WsendKeys(timem,currentTime.format(currentTimeInM).toString());    //Here we are using to convert the Date to string format
        DateTimeFormatter currentTimeInH = DateTimeFormatter.ofPattern("HH");
        Wrappers.WsendKeys(timeh,currentTime.format(currentTimeInH).toString());   //Here we are using to convert the Date to string format
        driver.findElement(By.xpath("//*[text()=\"Submit\"]")).click();  // usage of locater to find Submit Button
        //Thread.sleep(5000);
        Wrappers.sync(5000);    //Usage of Wrappers
        System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[3]")).getText());
        Wrappers.sync(5000);    //Usage of Wrappers
        //Thread.sleep(5000);  
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
