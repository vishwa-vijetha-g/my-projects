package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_03 {
    public ExtentTest et = null;
    @Test(priority = 3, dataProvider ="Data Provider Method", dataProviderClass = DP.class, groups = "Booking and Cancellation Flow")
    public void executeTestCase03(String emailDP, String passwordDP, String cityDP, String adventureDP, String guestDP, String dateDP, String countDP) throws IOException{ 
        try{

            System.out.println("Test Case 03 - STARTED");
            et = testBeforeAfter.er.startTest("Test Case 03");
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

            homPage.searchCity(cityDP);
            homPage.selectCity();

            AdventurePage advPage = new AdventurePage(driver);
            advPage.selectAdventure(adventureDP);

            AdventureDetailsPage advDetPage = new AdventureDetailsPage(driver);
            advDetPage.bookAdventure(guestDP, dateDP, countDP);
            Assert.assertTrue(advDetPage.isBookingSuccessful(), "Verify if Adventure Booking is successful - FAILED");

            advDetPage.navigateToReservations();

            HistoryPage hisPage = new HistoryPage(driver);
            List<String> allTransactionIds = hisPage.getTransactionIds();
            hisPage.cancelReservation(allTransactionIds.get(0));

            driver.navigate().refresh();
            allTransactionIds = hisPage.getTransactionIds();
            Assert.assertTrue(allTransactionIds.size()==0, "Verify that the transaction is cancelled - FAILED");

            homPage.logOutUser();
            et.log(LogStatus.PASS, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 03 - Booking and Cancellation Flow - PASSED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 03 - PASSED");
        }catch(Exception e){
            RemoteWebDriver driver = testBeforeAfter.driver;
            et.log(LogStatus.FAIL, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 03 - Booking and Cancellation Flow - FAILED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 03 - FAILED");
            e.printStackTrace();
        }
    }
}
