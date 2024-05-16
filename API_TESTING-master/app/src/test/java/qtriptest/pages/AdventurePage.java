package qtriptest.pages;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import qtriptest.SeleniumWrapper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventurePage {
    
    RemoteWebDriver driver;
    
    @FindBy(id = "duration-select")
    WebElement durationFilter;

    @FindBy(id = "category-select")
    WebElement categoryFilter;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement clearDuration;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement clearCategory;


    public AdventurePage( RemoteWebDriver driver)
    {
     this.driver = driver;   
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }


    public void setFilterValue(String value)
    {
        try {
            SeleniumWrapper.click(durationFilter, this.driver);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Select FilterDropdown = new Select(durationFilter);
        FilterDropdown.selectByVisibleText(value);

    }

    public void setCategoryValue(String value)
    {
        categoryFilter.click();
        Select FilterDropdown = new Select(categoryFilter);
        FilterDropdown.selectByVisibleText(value);
    }

    public int getResultCount()
    {
        List<WebElement> resultGrid= this.driver.findElements(By.xpath("//div[@id='data']/div"));
        return resultGrid.size();
    }

    public void selectAdventure(String adventureName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement adventure =  this.driver.findElement(By.xpath(  String.format("//h5[text()='%s']",adventureName)));
        SeleniumWrapper.click(adventure, this.driver);
        
    }

    public void clearFilters()
    {
        
        try {
            SeleniumWrapper.click(clearCategory, this.driver);
            SeleniumWrapper.click(clearDuration, this.driver);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
