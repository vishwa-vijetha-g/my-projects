package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;

import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;

public class testCase_01 {
    static RemoteWebDriver driver;

    @BeforeTest(enabled = false)
    public static void createDriver()  throws MalformedURLException {
        // IMPORTANT!: Enter the Driver Location here
        DriverSingleton sbc1= DriverSingleton.getInstanceOfSingletonBrowserClass();
		driver = sbc1.getDriver();
    }

    @Test(description = "Verify user registration - login - logout", dataProvider = "data-provider" , dataProviderClass = DP.class, groups = {"Login Flow"}, enabled = false)
    public static void TestCase01(String UserName, String Password) throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.gotoHomePage();
        Thread.sleep(2000);
        home.clickRegister();
        RegisterPage register = new RegisterPage(driver);
        register.registerNewUser(UserName, Password, Password, true);
        Thread.sleep(3000);//TODO:UNWANTED ALERT REMOVE
        //driver.switchTo().alert().dismiss();//TODO:UNWANTED ALERT REMOVE
        String username = register.lastGeneratedUsername;
        LoginPage Login = new LoginPage(driver);
        Login.performLogin(username, Password);
        Thread.sleep(3000);//TODO:UNWANTED ALERT REMOVE
        //driver.switchTo().alert().dismiss();//TODO:UNWANTED ALERT REMOVE
        Assert.assertTrue(home.isUserLoggedIn());
        home.logOutUser();
        Thread.sleep(3000);
        Assert.assertFalse(home.isUserLoggedIn());
        home.gotoHomePage();
    }

    @AfterSuite(enabled = false)
    public static void quitDriver() throws InterruptedException
    {
        driver.quit();
    }

}
