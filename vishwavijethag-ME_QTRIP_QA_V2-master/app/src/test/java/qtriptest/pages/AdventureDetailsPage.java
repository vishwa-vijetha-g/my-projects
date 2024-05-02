package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureDetailsPage {

    //Elements

    RemoteWebDriver driver;
    WebDriverWait wait;
    
    /* Replacing for Module 3 Milestone 2 Activity  
    @FindBy(name = "name")
    WebElement enterName;
    @FindBy(name = "date")
    WebElement enterDate;
    @FindBy(name = "person")
    WebElement enterPersons;
    @FindBy(id="reservation-cost")
    WebElement reservationCost;
    @FindBy(className = "reserve-button")
    WebElement reserveButton;
    @FindBy(id="reserved-banner")
    WebElement reservedBanner;
    @FindBy(linkText = "Reservations")
    WebElement reservationsButton;
    @FindBy(linkText = "Home")
    WebElement homeButton;
    */

    //WebElement reservationCost = SeleniumWrapper.findElementWithRetry(this.driver, By.id("reservation-cost"), 3);
    
    
    
    




    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
        //changed - PageFactory.initElements(driver, this);
    }

    public void bookAdventure(String name, String date, String persons ) throws InterruptedException{
        WebElement enterName = SeleniumWrapper.findElementWithRetry(this.driver, By.name("name"), 3);
        WebElement enterDate = SeleniumWrapper.findElementWithRetry(this.driver, By.name("date"), 3);
        WebElement enterPersons = SeleniumWrapper.findElementWithRetry(this.driver, By.name("person"), 3);
        WebElement reserveButton = SeleniumWrapper.findElementWithRetry(this.driver, By.className("reserve-button"), 3);
        

        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOf(enterName),
            ExpectedConditions.visibilityOf(enterDate),
            ExpectedConditions.visibilityOf(enterPersons),
            ExpectedConditions.visibilityOf(reserveButton)
        ));

        //replaced - enterName.sendKeys(name);
        SeleniumWrapper.sendKeys(enterName,name);
        //replaced - enterDate.sendKeys(date);
        SeleniumWrapper.sendKeys(enterDate,date);
        //enterPersons.clear();
        //replaced - enterPersons.sendKeys(persons);
        SeleniumWrapper.sendKeys(enterPersons,persons);

        //wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(reservationCost, "0")));
        Thread.sleep(1000);

        //replaced - reserveButton.click();
        SeleniumWrapper.click(reserveButton, this.driver);
        WebElement reservedBanner = SeleniumWrapper.findElementWithRetry(this.driver, By.id("reserved-banner"), 3);
        wait.until(ExpectedConditions.visibilityOf(reservedBanner));


    }

    public Boolean isBookingSuccessful(){
        WebElement reservedBanner = SeleniumWrapper.findElementWithRetry(this.driver, By.id("reserved-banner"), 3);
        return reservedBanner.isDisplayed();
    }

    public void navigateToReservations(){

        //replaced - reservationsButton.click();
        WebElement reservationsButton = SeleniumWrapper.findElementWithRetry(this.driver, By.linkText("Reservations"), 3);
        SeleniumWrapper.click(reservationsButton, driver);
        
        wait.until(ExpectedConditions.urlContains("reservations"));
    }

    public void navigateToHomePage(){

        //replaced - homeButton.click();
        WebElement homeButton = SeleniumWrapper.findElementWithRetry(this.driver, By.linkText("Home"), 3);
        SeleniumWrapper.click(homeButton, this.driver);
        
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"));
    }

}