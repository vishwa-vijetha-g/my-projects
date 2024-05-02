package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import qtriptest.SeleniumWrapper;

public class AdventureDetailsPage {

    RemoteWebDriver driver;
    
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameTextBox;

    @FindBy(xpath = "//input[@name='date']")
    WebElement dateTextBox;

    @FindBy(xpath = "//input[@name='person']")
    WebElement personTextBox;

    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reserveButton;

    public AdventureDetailsPage(RemoteWebDriver driver)
    {
     this.driver = driver;   
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    
    public void bookAdventure(String name , String Date , int count) throws InterruptedException
    {
        SeleniumWrapper.sendKeys(nameTextBox, name);
        SeleniumWrapper.sendKeys(dateTextBox,Date);
        SeleniumWrapper.sendKeys(personTextBox, String.valueOf(count));
        Thread.sleep(2000);
        SeleniumWrapper.click(reserveButton, this.driver);
    }
    
    public boolean isBookingSuccessful()
    {
        //TODO: Return true if the booking was successful
        return true;
    }
}
