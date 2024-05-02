package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.io.IOException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class testCase_02 {
    public ExtentTest et = null;
    @Test(priority = 2,dataProvider ="Data Provider Method", dataProviderClass = DP.class, groups = "Search and Filter flow")
    public void executeTestCase02(String cityDP, String categoryDP, String durationDP, String filteredDP, String unfilteredDP) throws IOException{ 
        try{

            System.out.println("Test Case 02 - STARTED");
            et = testBeforeAfter.er.startTest("Test Case 02");
            RemoteWebDriver driver = testBeforeAfter.driver;
            HomePage homPage = new HomePage(driver);

            /* 
            homPage.searchCity("Hyderabad"); //This city is not present
            Assert.assertFalse(homPage.isResultFound(), "No City found message is not displayed");
            */

            homPage.searchCity(cityDP);
            Assert.assertTrue(homPage.isResultFound(), "City is not found in the search");

            homPage.selectCity();

            AdventurePage advPage = new AdventurePage(driver);

            advPage.setFilterValue(durationDP);
            //Assert.assertEquals(advPage.getResultCount(), 3, "Did not find the expected number of results");
            advPage.setCategoryValue(categoryDP);
            Assert.assertEquals(advPage.getResultCount(), Integer.valueOf(filteredDP), "Did not find the expected number of filtered results");

            advPage.clearFilters();
            Assert.assertEquals(advPage.getResultCount(), Integer.valueOf(unfilteredDP), "Did not find the expected number of unfiltered results");

            homPage.navigateToHomePage();
            et.log(LogStatus.PASS, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 02 - Search and Filter flow - PASSED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 02 - PASSED");

            

        }catch(Exception e){
            RemoteWebDriver driver = testBeforeAfter.driver;
            et.log(LogStatus.FAIL, et.addScreenCapture(testBeforeAfter.capture(driver)) +"Test Case 02 - Search and Filter flow - FAILED");
            testBeforeAfter.er.endTest(et);
            System.out.println("Test Case 02 - FAILED");
            e.printStackTrace();
        }
    }
}
