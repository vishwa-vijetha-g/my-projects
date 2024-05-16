package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverSingleton {
    
    // instance of singleton class
    private static DriverSingleton instanceOfSingletonBrowserClass = null;

    private RemoteWebDriver driver;

    // Constructor
    private DriverSingleton() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

        driver.manage().window().maximize();
    }

    // TO create instance of class
    public static DriverSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException {
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverSingleton();
        }
        return instanceOfSingletonBrowserClass;
    }

    // To get driver
    public RemoteWebDriver getDriver() {
        return driver;
    }
}
