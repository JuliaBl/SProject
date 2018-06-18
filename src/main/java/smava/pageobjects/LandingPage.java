package smava.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import smava.domain.User;

import java.util.List;
import java.util.stream.Collectors;
import static smava.setup.element.Element.getElement;
import static smava.setup.element.Element.getElements;

public class LandingPage extends Page<LandingPage> {

    private static final By DROPDOWN_CREDIT_AMOUNT = By.xpath("//select[@id='lsAmount']/following-sibling::input");
    private static final By DROPDOWN_CREDIT_DURATION = By.xpath("//select[@id='lsDuration']/following-sibling::input");
    private static final By DROPDOWN_CREDIT_CATEGORY = By.id("myselect3");
    private static final By BUTTON_LOAN_SECTION_FORWARD = By.id("loanSelectionForward");
    private static final By BUTTON_SIGN_IN = By.xpath("//a[text()='Anmelden']");
    private static final By INPUT_USER_EMAIL = By.xpath("//input[@id='signonForm.email']");
    private static final By INPUT_USER_PASSWORD = By.xpath("//input[@id='signonForm.password']");
    private static final By BUTTON_TO_SIGN_IN = By.xpath("//div[text()='Anmelden']");
    private static final By POPUP_SIGN_IN = By.xpath("//div[contains(@class, 'popup-login')]");
    private static final By SEGMENT_BANK_MONTH = By.xpath("//div[@class='ui segment bank--mona']");
    private static final By LOGO = By.xpath("//img[@alt='Smava Logo']");


    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOfElementLocated(LOGO);
    }

    public LandingPage open() {
        return new LandingPage().openPage(LandingPage.class);
    }

    public LandingPage selectCreditAmount(String option) {
        getElement(DROPDOWN_CREDIT_AMOUNT).sendKeys(option);
        getElement(DROPDOWN_CREDIT_AMOUNT).sendKeys(Keys.ENTER);
        return this;
    }

    public LandingPage selectCreditDuration(String option) {
        getElement(DROPDOWN_CREDIT_DURATION).sendKeys(option);
        getElement(DROPDOWN_CREDIT_DURATION).sendKeys(Keys.ENTER);
        return this;
    }

    public LandingPage selectCreditCategory(String option) {
        getElement(DROPDOWN_CREDIT_CATEGORY).click();
        getElement(By.xpath("//div[contains(@class, 'item') and contains(text(),'"+option+"')]")).click();
        return this;
    }

    public CreditComparisonPage clickOnLoanSelectForward() {
        getElement(BUTTON_LOAN_SECTION_FORWARD).click();
        return new CreditComparisonPage().openPage(CreditComparisonPage.class);
    }

    public LandingPage clickOnSighInLink() {
        getElement(BUTTON_SIGN_IN).click();
        return this;
    }

    public LandingPage inputUserEmailPassword(User user) {
        getElement(INPUT_USER_EMAIL).sendKeys(user.getUserEmail());
        getElement(INPUT_USER_PASSWORD).sendKeys(user.getUserPassword());
        return this;
    }

    public <T extends Page<T>> T clickOnSignIn(Class<T> tClass) {
        getElement(BUTTON_TO_SIGN_IN).click();
        try {
            if (tClass == LandingPage.class) {
                return tClass.getConstructor().newInstance();
            } else {
                return tClass.getConstructor().newInstance().openPage(tClass);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public boolean isPopUpSignInVisible() {
        return getElement(POPUP_SIGN_IN).isElementVisible();
    }

    private List<String> getListOfMinPricesCredit() {
      return getElements(SEGMENT_BANK_MONTH)
                .stream()
                .map(WebElement::getText)
                .map(el -> el.replace(" €\nMonatlich", "").replace(",", "."))
                .collect(Collectors.toList());
    }

    public String getMinSegmentPerMonth() {
        return getListOfMinPricesCredit()
                .stream()
                .map(Float::valueOf)
                .reduce(Float::min)
                .get()
                .toString();
    }

    public CreditComparisonPage clickOnNextWithOption(String option) {
        int indexMinPriceCredit = getListOfMinPricesCredit().indexOf(option);
        getElement(By.xpath("//button[@class='ui right orange button']['" + indexMinPriceCredit + "']")).click();
        return new CreditComparisonPage().openPage(CreditComparisonPage.class);
    }

    public String getMinPriceLoanPerMonth() {
        return getMinSegmentPerMonth().replace(".", ",") + " €";
    }
}
