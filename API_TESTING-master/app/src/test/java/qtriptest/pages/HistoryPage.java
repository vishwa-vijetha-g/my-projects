package qtriptest.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import qtriptest.SeleniumWrapper;

public class HistoryPage {

    RemoteWebDriver driver;
    
    @FindBy(id = "reservation-table")
    WebElement reservationTable;



    public HistoryPage(ChromeDriver driver)
    {
     this.driver = driver;   
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void gotoHistoryPage()
    {
        SeleniumWrapper.navigate(this.driver, "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html");
    }

    public List<ReservationHistory> getReservations()
    {
        List<ReservationHistory> history =new ArrayList<>();
        List<WebElement> tableRows = reservationTable.findElements(By.xpath("//*[@id='reservation-table']/tr"));
        for(WebElement row : tableRows)
        {
            String tr_id , booking_name , adventure , person, date , price , bookingtime;
            tr_id = row.findElement(By.xpath("//th")).getText();
            booking_name = row.findElement(By.xpath("//td[1]")).getText();
            adventure = row.findElement(By.xpath("//td[2]")).getText();
            person = row.findElement(By.xpath("//td[3]")).getText();
            date = row.findElement(By.xpath("//td[4]")).getText();
            price = row.findElement(By.xpath("//td[5]")).getText();
            bookingtime = row.findElement(By.xpath("//td[6]")).getText();
            ReservationHistory rs = new ReservationHistory();
            var historyRecord = rs.CreateReservationHistory(tr_id, booking_name, adventure, person, date, price, bookingtime);
            history.add(historyRecord);
        }
        return history;

    }

    public void cancelReservation(String transactionId)
    {
      //TODO: Implement Cancellation
    }


    private class ReservationHistory
    {
        String transactionId , bookingName , adventure , persons , date , price , bookingtime; 

        public ReservationHistory CreateReservationHistory(String transactionId , String bookingName ,String adventure ,String persons ,String date ,String price ,String bookingtime ){
            this.transactionId=transactionId;
            this.bookingName = bookingName;
            this.adventure=adventure;
            this.persons=persons;
            this.date=date;
            this.price=price;
            this.bookingtime=bookingtime;
            return this;
        }
        

    }
}

