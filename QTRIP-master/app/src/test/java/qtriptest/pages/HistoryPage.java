package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HistoryPage {


    //Elements

    RemoteWebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id='reservation-table']/tr/th")
    List<WebElement> transactionsIds;
    @FindBy(xpath = "//*[@id='reservation-table']/tr/td[8]/div/button")
    List<WebElement> cancelButtons;

    public HistoryPage(RemoteWebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
        PageFactory.initElements(driver, this);

    }

    public void cancelReservation(String transaction) throws InterruptedException{

        for(int i =0; i< transactionsIds.size(); i++){
            if(transactionsIds.get(i).getText().equals(transaction)){
                //replaced - cancelButtons.get(i).click();
                SeleniumWrapper.click(cancelButtons.get(i),this.driver);
                break;
            }
        }

        Thread.sleep(1000);
    }

    public List<String> getTransactionIds() throws InterruptedException{

        //wait.until(ExpectedConditions.visibilityOfAllElements(transactionsIds));
        Thread.sleep(1000);

        List<String> tIds = new ArrayList<>();
        for(WebElement tId: transactionsIds){
            tIds.add(tId.getText());
        }

        return tIds;

    }
    
    // public ReservationHistory getReservations(){

    // }


    //ReservationHistory

}