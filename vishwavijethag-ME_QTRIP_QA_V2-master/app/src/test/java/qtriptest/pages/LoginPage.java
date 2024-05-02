package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    //Elements
    RemoteWebDriver driver;
    WebDriverWait wait;
    /* 
    @FindBy(name="email")
    WebElement email;
    @FindBy(name="password")
    WebElement password;
    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement loginButton;
    */
    

    //Constructor
    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
        //PageFactory.initElements(driver, this);
    }


    public void performLogin(String emailVal, String passwordVal){
        
        WebElement email = SeleniumWrapper.findElementWithRetry(this.driver, By.name("email"), 3);
        WebElement password = SeleniumWrapper.findElementWithRetry(this.driver, By.name("password"), 3);
        WebElement loginButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//button[text()='Login to QTrip']"), 3);

        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOf(email),
            ExpectedConditions.visibilityOf(password),
            ExpectedConditions.visibilityOf(loginButton)
        ));

        //replaced - email.sendKeys(emailVal);
        SeleniumWrapper.sendKeys(email,emailVal);
        //replaced - password.sendKeys(passwordVal);
        SeleniumWrapper.sendKeys(password,passwordVal);
        //replaced - loginButton.click();
        SeleniumWrapper.click(loginButton,this.driver);
       
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"));

    }
}
