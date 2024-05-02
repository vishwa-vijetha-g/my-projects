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

public class testCase_04 {
    public ExtentTest et = null;

    @Test(priority = 4, dataProvider ="Data Provider Method", dataProviderClass = DP.class, groups = "Reliability Flow")
    public void executeTestCase04(String emailDP, String passwordDP, String dataSet1DP, String dataSet2DP, String dataSet3DP) throws IOException{ 

        try{

            System.out.println("Test Case 04 - STARTED");
            et = testBeforeAfter.er.startTest("Test Case 04");
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

            String[][] dataSets = {dataSet1DP.split(";"), dataSet2DP.split(";"), dataSet3DP.split(";")};

            AdventurePage advPage = new AdventurePage(driver);
            AdventureDetailsPage advDetPage = new AdventureDetailsPage(driver);

            String cityDP = "";
            String adventureDP = "";
            String guestDP = "";
            String dateDP = "";
            String countDP = "";

            for (String[] dataSet : dataSets) {

                cityDP = dataSet[0];
                adventureDP = dataSet[1];
                guestDP = dataSet[2];
                dateDP = dataSet[3];
                countDP = dataSet[4];

                homPage.searchCity(cityDP);
                homPage.selectCity();
                advPage.selectAdventure(adventureDP);
                advDetPage.bookAdventure(guestDP, dateDP, countDP);
                Assert.assertTrue(advDetPage.isBookingSuccessful(), "Verify if Adventure Booking is successful - FAILED");
                advDetPage.navigateToHomePage();
            }

            advDetPage.navigateToReservations();
            HistoryPage hisPage = new HistoryPage(driver);
            List<String> allTransactionIds = hisPage.getTransactionIds();
            Assert.assertTrue(allTransactionIds.size()==3, "Verify if all bookings are displayed - FAILED");

            advDetPage.navigateToHomePage();
            homPage.logOutUser();
            et.log(LogStatus.PASS, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 04 - Reliability Flow - PASSED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 04 - PASSED");
        }catch(Exception e){
            
            RemoteWebDriver driver = testBeforeAfter.driver;
            et.log(LogStatus.FAIL, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 04 - Reliability Flow - FAILED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 04 - FAILED");
            e.printStackTrace();
        }
    }
}
