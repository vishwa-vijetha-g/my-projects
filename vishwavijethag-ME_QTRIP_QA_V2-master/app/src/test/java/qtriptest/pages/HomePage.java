package qtriptest.pages;



import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    //Elements

    RemoteWebDriver driver;
    WebDriverWait wait;
    /* 
    @FindBy(xpath = "//a[text()='Login Here']")
    WebElement loginHereButton;
    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logOutButton;
    @FindBy(id = "autocomplete")
    WebElement searchBox;
    */
    // @FindBy(xpath = "//ul[@id='results']/h5")
    // WebElement noCityFound;
    // @FindBy(xpath = "//ul[@id='results']/a")
    // WebElement resultFound;
    @FindBy(xpath = "//a[contains(@href,'city')]")
    List<WebElement> cityHyperLinks;

    
    
    





    //Constructor
    public HomePage(RemoteWebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
        //AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void clickRegister(){
        WebElement registerButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//a[text()='Register']"), 3);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));

        //replaced - registerButton.click();

        SeleniumWrapper.click(registerButton,this.driver);
    }

    public Boolean isUserLoggedIn(){
        WebElement logOutButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//div[text()='Logout']"), 3);
        return logOutButton.isDisplayed();
    }

    public Boolean isUserLoggedOut(){
        WebElement loginHereButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//a[text()='Login Here']"), 3);
        return loginHereButton.isDisplayed();
    }

    public void logOutUser(){
        WebElement logOutButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//div[text()='Logout']"), 3);
        wait.until(ExpectedConditions.visibilityOf(logOutButton));
        
        //replaced - logOutButton.click();
        SeleniumWrapper.click(logOutButton,this.driver);
        WebElement loginHereButton = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("//a[text()='Login Here']"), 3);
        wait.until(ExpectedConditions.visibilityOf(loginHereButton));

    }

    public void searchCity(String city) throws InterruptedException{

        //wait.until(ExpectedConditions.visibilityOf(searchBox));
        Thread.sleep(1000);

        WebElement searchBox = SeleniumWrapper.findElementWithRetry(this.driver, By.id("autocomplete"), 3);
        searchBox.clear();
        //replaced - searchBox.sendKeys(city);
        SeleniumWrapper.sendKeys(searchBox,city);

        // wait.until(ExpectedConditions.or(
        //     ExpectedConditions.visibilityOf(noCityFound),
        //     ExpectedConditions.visibilityOf(resultFound)
        // ));

        Thread.sleep(1000);


    }

    public Boolean isResultFound() {
        if(cityHyperLinks.size()>8){
            return true;
        }
        return false;
    }

    public void selectCity() throws InterruptedException{ //String citySelect

        //replaced - cityHyperLinks.get(0).click();
        SeleniumWrapper.click(cityHyperLinks.get(0),this.driver);
        
        wait.until(ExpectedConditions.urlContains("adventures"));
        
    }

    public Boolean assertAutoCompleteText(String cityName){
        return false;
    }

    public void navigateToHomePage(){
        //replaced - driver.get("https://qtripdynamic-qa-frontend.vercel.app");
        SeleniumWrapper.navigate(this.driver, "https://qtripdynamic-qa-frontend.vercel.app");
    }
}
