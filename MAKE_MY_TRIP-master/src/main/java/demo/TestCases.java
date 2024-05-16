package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    String flightPrice;
    String trainPrice;
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
        // options.add_argument("start-maximized");
        // options.add_argument("--disable-blink-features=AutomationControlled");
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");


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
        System.out.println("Test Case 01 : START");
        driver.get("https://www.makemytrip.com/");
        boolean status = driver.getCurrentUrl().contains("makemytrip.");
        System.out.println(status ? "Test Case 01 : PASS" : "Test Case 01 : FAIL");
        System.out.println("Test Case 01 : END");
    }

    public  void testCase02() throws InterruptedException{
        boolean status = false;

        try{
            System.out.println("Test Case 02 : START");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            By fromCityLoc = By.id("fromCity");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityLoc));

            WebElement fromCity = driver.findElement(fromCityLoc);
            fromCity.click();

            driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("blr");
            
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/div/p/span[2]"), "BLR"));

            WebElement firstSuggestion = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]"));
            firstSuggestion.click();

            WebElement toCity = driver.findElement(By.id("toCity"));
            toCity.click();

            driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("del");
            
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/div/p/span[2]"), "DEL"));

            firstSuggestion = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]"));
            firstSuggestion.click();
            
            WebElement departure = driver.findElement(By.xpath("//label[@for='departure']/span"));
            departure.click();

            By dateLoc = By.xpath("//div[@aria-label='Wed May 29 2024']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dateLoc));

            WebElement dateVal = driver.findElement(dateLoc);
            dateVal.click();

            WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
            searchButton.click();

            By okayButtonLoc = By.xpath("//button[text()='OKAY, GOT IT!']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(okayButtonLoc));

            WebElement okayButton = driver.findElement(okayButtonLoc);
            okayButton.click();

            By priceLoc = By.xpath("(//div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice'])[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(priceLoc));

            WebElement priceVal = driver.findElement(priceLoc);
            flightPrice = priceVal.getText().substring(2);
            //System.out.println(flightPrice);

            status = true;

            System.out.println(status ? "Test Case 02 : PASS" : "Test Case 02 : FAIL");
            System.out.println("Test Case 02 : END");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(status ? "Test Case 02 : PASS" : "Test Case 02 : FAIL");
            System.out.println("Test Case 02 : END");
        }
    }

    public  void testCase03() throws InterruptedException{
        boolean status = false;

        try{
            driver.get("https://www.makemytrip.com/");

            System.out.println("Test Case 03 : START");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            By trainLoc = By.className("menu_Trains");
            wait.until(ExpectedConditions.visibilityOfElementLocated(trainLoc));

            WebElement train = driver.findElement(trainLoc);
            train.click();

            By fromCityLoc = By.id("fromCity");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityLoc));

            WebElement fromCity = driver.findElement(fromCityLoc);
            fromCity.click();

            By fromValLoc = By.xpath("//input[@placeholder='From']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromValLoc));
            driver.findElement(fromValLoc).sendKeys("ypr");
            
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/span"), "YPR"));

            WebElement firstSuggestion = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]"));
            firstSuggestion.click();
            
            // WebElement toCity = driver.findElement(By.id("toCity"));
            // toCity.click();

            By toValLoc = By.xpath("//input[@placeholder='To']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(toValLoc));
            driver.findElement(toValLoc).sendKeys("ndls");
            
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/span"), "NDLS"));

            firstSuggestion = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]"));
            firstSuggestion.click();
            
            // WebElement departure = driver.findElement(By.xpath("//label[@for='departure']/span"));
            // departure.click();

            By dateLoc = By.xpath("//div[@aria-label='Wed May 29 2024']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dateLoc));

            WebElement dateVal = driver.findElement(dateLoc);
            dateVal.click();

            // WebElement trainClass = driver.findElement(By.id("travelClass"));
            // trainClass.click();

            By thirdAcLoc = By.xpath("//*[@id='travelClass']/ancestor::div//li[text()='Third AC']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(thirdAcLoc));

            WebElement thirdAc = driver.findElement(thirdAcLoc);
            thirdAc.click();

            WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
            searchButton.click();

            // By okayButtonLoc = By.xpath("//button[text()='OKAY, GOT IT!']");
            // wait.until(ExpectedConditions.visibilityOfElementLocated(okayButtonLoc));

            // WebElement okayButton = driver.findElement(okayButtonLoc);
            // okayButton.click();

            By priceLoc = By.xpath("(//div[@class='ticket-price justify-flex-end'])[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(priceLoc));

            WebElement priceVal = driver.findElement(priceLoc);
            trainPrice = priceVal.getText().substring(2);
            //System.out.println(trainPrice);

            status = true;

            System.out.println(status ? "Test Case 03 : PASS" : "Test Case 03 : FAIL");
            System.out.println("Test Case 03 : END");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(status ? "Test Case 03 : PASS" : "Test Case 03 : FAIL");
            System.out.println("Test Case 03 : END");
        }
    }

    public  void testCase04() throws InterruptedException{

        boolean status = false;

        try{
            driver.get("https://www.makemytrip.com/");

            System.out.println("Test Case 04 : START");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            By trainLoc = By.className("menu_Buses");
            wait.until(ExpectedConditions.visibilityOfElementLocated(trainLoc));

            WebElement train = driver.findElement(trainLoc);
            train.click();

            By fromCityLoc = By.id("fromCity");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityLoc));

            WebElement fromCity = driver.findElement(fromCityLoc);
            fromCity.click();

            By fromValLoc = By.xpath("//input[@placeholder='From']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromValLoc));
            driver.findElement(fromValLoc).sendKeys("bangl");
            
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/p/span"), "Bangalore, Karnataka"));

            WebElement firstSuggestion = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]"));
            firstSuggestion.click();
            
            // WebElement toCity = driver.findElement(By.id("toCity"));
            // toCity.click();

            By toValLoc = By.xpath("//input[@placeholder='To']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(toValLoc));
            driver.findElement(toValLoc).sendKeys("kathma");
            
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/p/span"), "Kathmandu, Nepal"));

            firstSuggestion = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li[1]"));
            firstSuggestion.click();
            
            // WebElement departure = driver.findElement(By.xpath("//label[@for='departure']/span"));
            // departure.click();

            By dateLoc = By.xpath("//div[@aria-label='Wed May 29 2024']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dateLoc));

            WebElement dateVal = driver.findElement(dateLoc);
            dateVal.click();

            // WebElement trainClass = driver.findElement(By.id("travelClass"));
            // trainClass.click();

            // By thirdAcLoc = By.xpath("//*[@id='travelClass']/ancestor::div//li[text()='Third AC']");
            // wait.until(ExpectedConditions.visibilityOfElementLocated(thirdAcLoc));

            // WebElement thirdAc = driver.findElement(thirdAcLoc);
            // thirdAc.click();

            WebElement searchButton = driver.findElement(By.id("search_button"));
            searchButton.click();

            // By okayButtonLoc = By.xpath("//button[text()='OKAY, GOT IT!']");
            // wait.until(ExpectedConditions.visibilityOfElementLocated(okayButtonLoc));

            // WebElement okayButton = driver.findElement(okayButtonLoc);
            // okayButton.click();

            // By priceLoc = By.xpath("(//div[@class='ticket-price justify-flex-end'])[1]");
            // wait.until(ExpectedConditions.visibilityOfElementLocated(priceLoc));

            // WebElement priceVal = driver.findElement(priceLoc);
            // String trainPrice = priceVal.getText().substring(2);
            // System.out.println(trainPrice);

            By errorLoc = By.className("error-title");
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorLoc));

            WebElement errorMsg = driver.findElement(errorLoc);
            status = errorMsg.getText().contains("No buses found");

            System.out.println(status ? "Test Case 04 : PASS" : "Test Case 04 : FAIL");
            System.out.println("Test Case 04 : END");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(status ? "Test Case 04 : PASS" : "Test Case 04 : FAIL");
            System.out.println("Test Case 04 : END");
        }

    }
}
