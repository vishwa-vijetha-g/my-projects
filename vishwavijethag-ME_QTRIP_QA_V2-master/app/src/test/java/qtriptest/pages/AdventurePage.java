package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventurePage {

    //Elements

    RemoteWebDriver driver;
    WebDriverWait wait;
    /* 
    @FindBy(id="duration-select")
    WebElement durationSelect;
    @FindBy(id="category-select")
    WebElement categorySelect;
    @FindBy(xpath ="(//div[@class='ms-3'])[1]")
    WebElement clearDuration;
    @FindBy(xpath ="(//div[@class='ms-3'])[2]")
    WebElement clearCategory;
    */
    @FindBy(css =  ".col-6.col-lg-3.mb-4")
    List<WebElement> adventuresList;
    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']/a/div[2]/div/div[1]/h5")
    List<WebElement> adventureNames;
    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']/a")
    List<WebElement> adventureLinks;

    
    
    
    

    public AdventurePage(RemoteWebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
        //AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 5);
        PageFactory.initElements(driver, this);

    }

    public void setFilterValue(String duration) throws InterruptedException{
        Thread.sleep(1000);

        //replaced - durationSelect.click();
        WebElement durationSelect = SeleniumWrapper.findElementWithRetry(this.driver, By.id("duration-select"), 3);
        SeleniumWrapper.click(durationSelect,this.driver);
        Select durnSelect = new Select(durationSelect);
        durnSelect.selectByVisibleText(duration);
        
        wait.until(ExpectedConditions.textToBePresentInElement(durationSelect, duration));


    }

    public void setCategoryValue(String category) throws InterruptedException{
        Thread.sleep(1000);

        //replaced - categorySelect.click();
        WebElement categorySelect = SeleniumWrapper.findElementWithRetry(this.driver, By.id("category-select"), 3);
        SeleniumWrapper.click(categorySelect,this.driver);

        Select catSelect = new Select(categorySelect);
        catSelect.selectByVisibleText(category);

        wait.until(ExpectedConditions.textToBePresentInElement(categorySelect, category));

    }

    public int getResultCount(){
        return adventuresList.size();
    }

    public void clearFilters() throws InterruptedException{

        //replaced - clearDuration.click();
        WebElement clearDuration = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("(//div[@class='ms-3'])[1]"), 3);
        SeleniumWrapper.click(clearDuration,this.driver);
        //replaced - clearCategory.click();
        WebElement clearCategory = SeleniumWrapper.findElementWithRetry(this.driver, By.xpath("(//div[@class='ms-3'])[2]"), 3);
        SeleniumWrapper.click(clearCategory,this.driver);

        Thread.sleep(1000);

    }
    
    public void selectAdventure(String adventure) throws InterruptedException{

        wait.until(ExpectedConditions.visibilityOfAllElements(adventureNames));

        for(int i=0; i< adventureNames.size(); i++){
            if(adventureNames.get(i).getText().equals(adventure)){
                //replaced - adventureLinks.get(i).click();
                SeleniumWrapper.click(adventureLinks.get(i),driver);
            }
        }

        
        wait.until(ExpectedConditions.urlContains("adventures/detail/?adventure"));
    }
}