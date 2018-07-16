package smava.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import smava.utils.Config;
import smava.utils.PropertiesLoader;
import java.time.Duration;
import java.util.NoSuchElementException;
import static smava.setup.WebDriverRunner.getTLDriver;

public abstract class Page<T> {
    private static final Config config = PropertiesLoader.getConfig();
    private WebDriver driver = getTLDriver();
    private static final String BASE_URL = config.getUrl();
    private static final int LOAD_TIMEOUT = 60;
    private static final int REFRESH_RATE = 5;

    public T openPage(Class<T> clazz){
        T page = PageFactory.initElements(driver, clazz);
        if(clazz == LandingPage.class) {
            driver.get(BASE_URL);
        }
        ExpectedCondition pageLoadCondition = ((Page) page).getPageLoadCondition();
        waitForPageToLoad(pageLoadCondition);
        return page;
    }

    @SuppressWarnings("unchecked")
    private void waitForPageToLoad(ExpectedCondition pageLoadCondition){
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(LOAD_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(REFRESH_RATE))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
       wait.until(pageLoadCondition);
    }

    /**
     * Provides condition when page can be considered as fully loaded.
     *
     * @return
     */
    protected abstract ExpectedCondition getPageLoadCondition();

}
