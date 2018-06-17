package smava.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static smava.setup.element.Element.getElement;
import static smava.setup.element.Element.getSelect;

public class CreditComparisonPage extends Page<CreditComparisonPage>{

    private static final By SECTION_LOAN_BANK_PARTNER = By.xpath("//h1[.='Unverbindlicher, kostenloser Kreditvergleich']");
    private static final By DROPDOWN_USE = By.xpath(".//select[contains(@id, 'category')]");
    private static final By DROPDOWN_LOAN_AMOUNT = By.xpath(".//select[contains(@id, 'amount')]");
    private static final By DROPDOWN_LOAN_TERM = By.xpath(".//select[contains(@id, 'duration')]");
    private static final By SECTION_LOAN_MONTH = By.xpath("//label[contains(., 'Monatsrate')]/following-sibling::span[@class='loan-selection__value']");

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOfElementLocated(SECTION_LOAN_BANK_PARTNER);
    }

    public String getCreditUseFromDropDown() {
       return getSelect(DROPDOWN_USE).getFirstSelectedOption().getText();
    }

    public String getCreditLoanAmountFromDropDown() {
        return getSelect(DROPDOWN_LOAN_AMOUNT).getFirstSelectedOption().getText();
    }

    public String getCreditLoanTermFromDropDown() {
       return getSelect(DROPDOWN_LOAN_TERM).getFirstSelectedOption().getText();
    }

    public String getSectionLoanValueMonth() {
        return getElement(SECTION_LOAN_MONTH).getText();
    }
}
