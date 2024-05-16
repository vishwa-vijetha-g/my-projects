package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    //Elements
    RemoteWebDriver driver;
    WebDriverWait wait;

    /*
    @FindBy(name="email")
    WebElement email;
    @FindBy(name="password")
    WebElement password;
    @FindBy(name="confirmpassword")
    WebElement confirmpassword;
    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registerNowButton;
    */

    

    //Constructor
    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
        //PageFactory.initElements(driver, this);
    }




    public void registerNewUser(String emailVal, String passwordVal, String confirmPasswordVal){

        WebElement email = SeleniumWrapper.findElementWithRetry(this.driver, By.name("email"), 3);
        WebElement password = SeleniumWrapper.findElementWithRetry(this.driver, By.name("password"), 3);
        WebElement confirmpassword = SeleniumWrapper.findElementWithRetry(this.driver, By.name("confirmpassword"), 3);
        WebElement registerNowButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//button[text()='Register Now']"), 3);
        
        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOf(email),
            ExpectedConditions.visibilityOf(password),
            ExpectedConditions.visibilityOf(confirmpassword),
            ExpectedConditions.visibilityOf(registerNowButton)
        ));


        //replaced - email.sendKeys(emailVal);
        SeleniumWrapper.sendKeys(email,emailVal);
        //replaced - password.sendKeys(passwordVal);
        SeleniumWrapper.sendKeys(password,passwordVal);
        //replaced - confirmpassword.sendKeys(confirmPasswordVal);
        SeleniumWrapper.sendKeys(confirmpassword,confirmPasswordVal);
        //replaced - registerNowButton.click();
        SeleniumWrapper.click(registerNowButton,this.driver);
        
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));

    }

}
