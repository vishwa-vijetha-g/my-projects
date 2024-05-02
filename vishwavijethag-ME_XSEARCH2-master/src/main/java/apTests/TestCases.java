
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01(){
        System.out.println("Test Case Start: Test Case 01 - Verify Wikipedia Homepage URL");
        
        Boolean status;
        driver.get("https://www.wikipedia.org");
        status = driver.getTitle().toLowerCase().contains("wikipedia");

        if(!status){
            System.out.println("Test Case Failure: Test Case 01 - Verify Wikipedia Homepage URL: " + (status ? "PASS" : "FAIL" ));
        }

        System.out.println("Test Case End: Test Case 01 - Verify Wikipedia Homepage URL: " + (status ? "PASS" : "FAIL" ));
        
    }

    public void testCase02(){

        System.out.println("Test Case Start: Test Case 02 - Verify Wikipedia Header and Footer");
        
        Boolean status = false;
        Boolean termsOfUse = false;
        Boolean privacyPolicy = false;

        driver.get("https://www.wikipedia.org");
        status = driver.findElement(By.xpath("//h1/span")).getText().equals("Wikipedia");

        if(!status){
            System.out.println("Test Case Failure: Test Case 02 - Verify Wikipedia Header and Footer: " + (status ? "PASS" : "FAIL" ));
        }

        List<WebElement> footerElements = driver.findElements(By.xpath("//*[@id='www-wikipedia-org']/footer/p/small/a"));
        for(WebElement footerElement: footerElements){
            String footerText = footerElement.getText();
            if(footerText.equals("Terms of Use")){
                termsOfUse = true;
            }
            else if(footerText.equals("Privacy Policy")){
                privacyPolicy = true;
            }
            
        }

        status = termsOfUse && privacyPolicy;

        if(!status){
            System.out.println("Test Case Failure: Test Case 02 - Verify Wikipedia Header and Footer: " + (status ? "PASS" : "FAIL" ));
        }

        System.out.println("Test Case End: Test Case 02 - Verify Wikipedia Header and Footer: " + (status ? "PASS" : "FAIL" ));
        
    }


    public void testCase03(){
        System.out.println("Test Case Start: Test Case 03 - Verify the search functionality");
        Boolean status = false;

        driver.get("https://www.wikipedia.org");
        driver.findElement(By.id("searchInput")).sendKeys("apple");
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id='typeahead-suggestions']/div/a/div[1]/h3"));
        for(WebElement searchResult : searchResults){
            if(searchResult.getText().equals("Apple Inc.")){
                searchResult.click();
                status = true;
                break;
            }
        }

        if(!status){
            System.out.println("Test Case Failure: Test Case 03 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        }

        status = false;
        
        List<WebElement> foundersList = driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[9]/td/div/ul/li/a"));

        for(WebElement founder: foundersList){
            if(founder.getText().equals("Steve Jobs")){
                status = true;
                break;
            }
        }


        if(!status){
            System.out.println("Test Case Failure: Test Case 03 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        }

        System.out.println("Test Case End: Test Case 03 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        
    }

    public void testCase04(){
        System.out.println("Test Case Start: Test Case 04 - Verify the search functionality");
        Boolean status = false;

        driver.get("https://www.wikipedia.org");
        driver.findElement(By.id("searchInput")).sendKeys("microsoft");
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id='typeahead-suggestions']/div/a/div[1]/h3"));
        for(WebElement searchResult : searchResults){
            if(searchResult.getText().equals("Microsoft")){
                searchResult.click();
                status = true;
                break;
            }
        }

        if(!status){
            System.out.println("Test Case Failure: Test Case 04 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        }

        status = false;
        
        List<WebElement> foundersList = driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[8]/td/div/ul/li/a"));

        for(WebElement founder: foundersList){
            if(founder.getText().equals("Bill Gates")){
                status = true;
                founder.click();
                break;
            }
        }


        if(!status){
            System.out.println("Test Case Failure: Test Case 04 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        }

        status = driver.getCurrentUrl().contains("Bill_Gates");

        if(!status){
            System.out.println("Test Case Failure: Test Case 04 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        }

        System.out.println("Test Case End: Test Case 04 - Verify the search functionality: " + (status ? "PASS" : "FAIL" ));
        
    }

    public void testCase05(){

        System.out.println("Test Case Start: Test Case 05 - Verify 'About Wikipedia' Link and URL in Dropdown");
        Boolean status = false;

        driver.get("https://en.wikipedia.org/");
        driver.findElement(By.id("vector-main-menu-dropdown-checkbox")).click();
        driver.findElement(By.xpath("//*[@id='n-aboutsite']/a")).click();
        status = driver.getCurrentUrl().contains("About");


        if(!status){
            System.out.println("Test Case Failure: Test Case 05 - Verify 'About Wikipedia' Link and URL in Dropdown: " + (status ? "PASS" : "FAIL" ));
        }

        System.out.println("Test Case End: Test Case 05 - Verify 'About Wikipedia' Link and URL in Dropdown: " + (status ? "PASS" : "FAIL" ));
        

    }


}

