package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.util.UUID;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class testCase_01 {
    
    public ExtentTest et = null;
    

    @Test(priority = 1, dataProvider = "Data Provider Method", dataProviderClass = DP.class, groups = "Login Flow")
    public void executeTestCase01(String emailDP, String passwordDP) throws IOException{
        try{
            
            System.out.println("Test Case 01 - STARTED");
            et = testBeforeAfter.er.startTest("Test Case 01");
            RemoteWebDriver driver = testBeforeAfter.driver;
            HomePage homPage = new HomePage(driver);
            homPage.clickRegister();
            Assert.assertEquals(driver.getCurrentUrl(), "https://qtripdynamic-qa-frontend.vercel.app/pages/register/","Verify that registration page is displayed - FAILED");
            
            RegisterPage regPage = new RegisterPage(driver);
            String emailVal = UUID.randomUUID().toString().substring(0,9) + emailDP;
            String passwordVal = passwordDP;
            regPage.registerNewUser(emailVal, passwordVal, passwordVal);
            Assert.assertEquals(driver.getCurrentUrl(), "https://qtripdynamic-qa-frontend.vercel.app/pages/login","Verify that User is Navigated to Login Page - FAILED");

            LoginPage logPage = new LoginPage(driver);
            logPage.performLogin(emailVal,passwordVal);
            Assert.assertTrue(homPage.isUserLoggedIn(),"Verify that the user is Logged in - FAILED");

            homPage.logOutUser();
            Assert.assertTrue(homPage.isUserLoggedOut(),"Verify that the user is logged out - FAILED");

            et.log(LogStatus.PASS, et.addScreenCapture(testBeforeAfter.capture(driver)) + "Test Case 01 - Login Flow - PASSED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 01 - PASSED");

        }catch(Exception e){
            RemoteWebDriver driver = testBeforeAfter.driver;
            et.log(LogStatus.FAIL, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 01 - Login Flow - FAILED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 01 - FAILED");
            e.printStackTrace();
        }
    }

}
