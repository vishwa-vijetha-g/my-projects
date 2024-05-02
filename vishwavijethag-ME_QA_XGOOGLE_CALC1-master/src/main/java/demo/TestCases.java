package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
// import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        boolean status = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{

            System.out.println("Test Case 01: START");

            driver.navigate().to("https://www.google.com");

            status = driver.getCurrentUrl().contains("google");
            System.out.println( status ? "Test Case 01 - 0: PASS" : "Test Case 01 - 0: FAIL");

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("calculator");
            searchBox.sendKeys(Keys.ENTER);

            By calcTextLoc = By.xpath("//*[@id='rso']/div[1]/div/div/div/div[1]/div/h2");
            wait.until(ExpectedConditions.visibilityOfElementLocated(calcTextLoc));

            WebElement calcHeading = driver.findElement(calcTextLoc);

            status = calcHeading.getText().equals("Calculator result");
            System.out.println( status ? "Test Case 01 - 1: PASS" : "Test Case 01 - 1: FAIL");

            WebElement result = driver.findElement(By.id("cwos"));

            status = result.getText().equals("0");
            System.out.println( status ? "Test Case 01 - 2: PASS" : "Test Case 01 - 2: FAIL");

            System.out.println("Test Case 01: END");

        }catch(Exception e){

            e.printStackTrace();
            System.out.println("Test Case 01: FAIL");
            System.out.println("Test Case 01: END");

        }
    }

    public  void testCase02(){
        boolean status = false;
        try{

            System.out.println("Test Case 02: START");

            driver.findElement(By.xpath("//div[@jsname='Ax5wH']")).click(); //5
            driver.findElement(By.xpath("//div[@jsname='XSr6wc']")).click(); //+
            driver.findElement(By.xpath("//div[@jsname='rk7bOd']")).click(); //7
            driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); //=

            WebElement result = driver.findElement(By.id("cwos"));

            status = result.getText().equals("12");
            System.out.println( status ? "Test Case 02 - 1: PASS" : "Test Case 02 - 1: FAIL");

            driver.findElement(By.xpath("//div[@jsname='SLn8gc']")).click(); //AC
            driver.findElement(By.xpath("//div[@jsname='N10B9']")).click(); //1
            driver.findElement(By.xpath("//div[@jsname='Ax5wH']")).click(); //5
            driver.findElement(By.xpath("//div[@jsname='pPHzQc']")).click(); //-
            driver.findElement(By.xpath("//div[@jsname='T7PMFe']")).click(); //8
            driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); //=

            status = result.getText().equals("7");
            System.out.println( status ? "Test Case 02 - 2: PASS" : "Test Case 02 - 2: FAIL");

            System.out.println("Test Case 02: END");

        }catch(Exception e){

            e.printStackTrace();
            System.out.println("Test Case 02: FAIL");
            System.out.println("Test Case 02: END");

        }
    }

    public  void testCase03(){
        boolean status = false;
        try{

            System.out.println("Test Case 03: START");

            driver.findElement(By.xpath("//div[@jsname='N10B9']")).click(); //1
            driver.findElement(By.xpath("//div[@jsname='bkEvMb']")).click(); //0
            driver.findElement(By.xpath("//div[@jsname='YovRWb']")).click(); //*
            driver.findElement(By.xpath("//div[@jsname='KN1kY']")).click(); //3
            driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); //=

            WebElement result = driver.findElement(By.id("cwos"));

            status = result.getText().equals("30");
            System.out.println( status ? "Test Case 03 - 1: PASS" : "Test Case 03 - 1: FAIL");

            driver.findElement(By.xpath("//div[@jsname='SLn8gc']")).click(); //AC
            
            status = result.getText().equals("0");
            System.out.println( status ? "Test Case 03 - 2: PASS" : "Test Case 03 - 2: FAIL");

            System.out.println("Test Case 03: END");

        }catch(Exception e){

            e.printStackTrace();
            System.out.println("Test Case 03: FAIL");
            System.out.println("Test Case 03: END");

        }
    }

    public  void testCase04(){
        boolean status = false;
        try{

            System.out.println("Test Case 04: START");

            driver.findElement(By.xpath("//div[@jsname='lVjWed']")).click(); //2
            driver.findElement(By.xpath("//div[@jsname='bkEvMb']")).click(); //0
            driver.findElement(By.xpath("//div[@jsname='WxTTNd']")).click(); ///
            driver.findElement(By.xpath("//div[@jsname='xAP7E']")).click(); //4
            driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); //=

            WebElement result = driver.findElement(By.id("cwos"));

            status = result.getText().equals("5");
            System.out.println( status ? "Test Case 04: PASS" : "Test Case 04: FAIL");

            System.out.println("Test Case 04: END");

        }catch(Exception e){

            e.printStackTrace();
            System.out.println("Test Case 04: FAIL");
            System.out.println("Test Case 04: END");

        }
    }

}
