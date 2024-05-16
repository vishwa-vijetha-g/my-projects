package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class testBeforeAfter{

    public static RemoteWebDriver driver;
    public static ExtentReports er;

    public testBeforeAfter(){

    }

    // public static RemoteWebDriver createDriver() throws MalformedURLException{

    //     final DesiredCapabilities capabilities = new DesiredCapabilities();
    //     capabilities.setBrowserName(BrowserType.CHROME);
    //     RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

    //     return driver;
    // }

    // @BeforeSuite(alwaysRun = true, enabled = true)
    // public void beforeSuiteMethod() throws MalformedURLException{
    //     testBeforeAfter.driver = createDriver();
    //     driver.get("https://qtripdynamic-qa-frontend.vercel.app");
    //     driver.manage().window().maximize();
    // }

    @BeforeTest(alwaysRun = true, enabled = true)
    public void beforeTestMethod() throws MalformedURLException{
        testBeforeAfter.er = ReportSingleton.getExtentReports();
        
        DriverSingleton ds =  DriverSingleton.getInstanceOfSingletonBrowserClass();
        testBeforeAfter.driver = ds.getDriver();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app");
        driver.manage().window().maximize();

        
    }

    public static String capture(RemoteWebDriver driver) throws IOException{

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir")+"/QKARTImages/" + System.currentTimeMillis()+ ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;

    }


    @AfterSuite(alwaysRun = true, enabled = true)
    public void afterSuiteMethod(){
        driver.close();
        driver.quit();
        er.flush();
        
    }

    
}
