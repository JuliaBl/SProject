package smava.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import smava.utils.Config;
import smava.utils.PropertiesLoader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverRunner {
    protected static final Config config = PropertiesLoader.getConfig();
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public synchronized static void setTLDriver () throws MalformedURLException {
        if (!Boolean.parseBoolean(config.isRemote())) {
            if(tlDriver.get() == null) {
                tlDriver = ThreadLocal.withInitial(() -> new ChromeDriver());
            }
        }
        else {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            if(tlDriver.get() == null) {
                URL url = new URL(config.getRemoteUrl());
                tlDriver = ThreadLocal.withInitial(() -> new RemoteWebDriver(url, capabilities));
            }
        }
    }

    public synchronized static WebDriver getTLDriver() {
        tlDriver.get().manage().window().maximize();
        return tlDriver.get();
    }

    public synchronized static void terminate() {
        if(getTLDriver() != null){
            getTLDriver().quit();
            tlDriver.remove();
        }
    }
}
