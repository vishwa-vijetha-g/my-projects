package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {

    public static boolean click(WebElement elementToClick, WebDriver driver){

        try{
            Boolean elementExists = elementToClick.isDisplayed();
            if(elementExists){
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", elementToClick);
                elementToClick.click();
                return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend){

        try{
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean navigate(WebDriver driver, String url){

        try{
            String currentUrl = driver.getCurrentUrl();
            if(!currentUrl.equals(url)){
                driver.get(url);
            }
            return driver.getCurrentUrl().equals(url);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static WebElement findElementWithRetry(WebDriver driver, By by,int retryCount){
        
        for(int i=0; i<retryCount; i++){
            try{
                WebElement elem = driver.findElement(by);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.visibilityOf(elem));
                if(elem!=null){
                    return elem;
                }
            }catch(Exception e){
                //System.out.println("Retrying to Find Element : " + i + " : "+retryCount);
            }
        }
        //System.out.println("Element not found returning null: " + by);
        return null;
    }

}
