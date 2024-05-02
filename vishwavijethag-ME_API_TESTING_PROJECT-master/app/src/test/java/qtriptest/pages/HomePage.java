package qtriptest.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import qtriptest.SeleniumWrapper;


public class HomePage {

    RemoteWebDriver driver;
    
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;

    @FindBy(id = "autocomplete")
    WebElement searchBox;

    @FindBy(xpath = "//h5[text()='No City found']")
    WebElement noCityfound;

    public HomePage(RemoteWebDriver driver)
    {
     this.driver = driver;   
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    

    public void gotoHomePage() throws InterruptedException
    {
        SeleniumWrapper.navigate(this.driver, "https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(2000);
    }

    public void clickRegister() throws InterruptedException
    {
        SeleniumWrapper.click(registerButton, this.driver);
    }

    public Boolean isUserLoggedIn()
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try{
        return logoutButton.isDisplayed();}
        catch(Exception e)
        {
            return false;
        }
    }

    public void logOutUser() throws InterruptedException
    {
        SeleniumWrapper.click(logoutButton, this.driver);
    }

    public void searchCity(String cityName)
    {
        SeleniumWrapper.sendKeys(searchBox, cityName);
    }

    public boolean assertAutoCompleteText(String cityName) throws Exception
    {
        By by  = new By.ByXPath(String.format("//li[@id='%s']",cityName));
        WebElement x = SeleniumWrapper.findElementWithRetry(this.driver, by, 3);

       return x.isDisplayed();
    }

    public void selectCity(String cityName) throws Exception
    {   Thread.sleep(2000);
        By by  = new By.ByXPath(String.format("//li[@id='%s']",cityName.toLowerCase()));
        WebElement x = SeleniumWrapper.findElementWithRetry(this.driver, by, 3);
        SeleniumWrapper.click(x, this.driver);
    }

    public boolean isNoCityFound()
    {
        try{
            return noCityfound.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
}
