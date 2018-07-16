package smava.setup.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import static smava.setup.WebDriverRunner.getTLDriver;

public class Element{
    private static final int LOAD_TIMEOUT = 80;
    private static final int REFRESH_RATE = 2;
    private By locator;

    protected Element(By locator) {
        this.locator = locator;
    }

    @SuppressWarnings("unchecked")
    private FluentWait<WebDriver> baseWait(){
        return new FluentWait(getTLDriver())
                .withTimeout(Duration.ofSeconds(LOAD_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(REFRESH_RATE))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Element is not visible");
    }

    private WebElement waitFor(Function<WebElement, ExpectedCondition<WebElement>> condition){
        return baseWait().until(condition.apply(findElement()));
    }

    @SuppressWarnings("unchecked")
    public static List<WebElement> getElements(By locator) {
      Wait wait = new FluentWait(getTLDriver())
                .withTimeout(Duration.ofSeconds(LOAD_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(REFRESH_RATE))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
      return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    private WebElement findElement() {
        waitForDocStateReady();
        return getTLDriver().findElement(locator);
    }

    public static Element getElement(By locator) {
        return new Element(locator);
    }

    public void click() {
        waitFor(ExpectedConditions::elementToBeClickable).click();
    }

    public void sendKeys(CharSequence ... value) {
        waitFor(ExpectedConditions::visibilityOf).sendKeys(value);
    }

    public String getText() {
        return waitFor(ExpectedConditions::visibilityOf).getText();
    }

    public static Select getSelect(By locator) {
       return  new Select(getTLDriver().findElement(locator));
    }

    public boolean isElementVisible() {
        try {
            WebElement element = getTLDriver().findElement(locator);
            if (element != null) {
                return element.isDisplayed();
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }

    public WebElement getWrappedElement() {
        return  waitFor(ExpectedConditions::visibilityOf);
    }

    private static void waitForDocStateReady() {
        new WebDriverWait(getTLDriver(), 50).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
