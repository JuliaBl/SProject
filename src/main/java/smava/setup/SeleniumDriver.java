package smava.setup;

import smava.utils.Config;
import smava.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.net.URL;

public class SeleniumDriver {
    protected static final Config config = PropertiesLoader.getConfig();
    private static WebDriver driver;

    public static synchronized WebDriver getDriver()  {
        if (!Boolean.parseBoolean(config.isRemote())) {
            if (driver == null) {
                driver = new ChromeDriver();
                System.out.println("Driver is initiated");
            }
        }
        else{
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            try {
                driver = new RemoteWebDriver(new URL(config.getRemoteUrl()), capabilities);
                System.out.println("Driver is initiated");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

}
