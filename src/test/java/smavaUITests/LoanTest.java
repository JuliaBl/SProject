package smavaUITests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smava.pageobjects.CreditComparisonPage;
import smava.pageobjects.LandingPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;


public class LoanTest extends BaseTest {
    private LandingPage landingPage;
    private CreditComparisonPage creditComparisonPage;

    @BeforeEach
    public void setUpLoanTest() {
        landingPage = new LandingPage().open();
    }


    @Test
    public void testCanAskAndVerifyLoan() {
        creditComparisonPage =
                landingPage
                        .selectCreditAmount("2.750 €")
                        .selectCreditDuration("24 Monate")
                        .selectCreditCategory("Wohnen")
                        .clickOnLoanSelectForward();
        assertThat(creditComparisonPage.getCreditLoanAmountFromDropDown(), containsString("2.750 €"));
        assertThat(creditComparisonPage.getCreditLoanTermFromDropDown(), containsString("24 Monate"));
        assertThat(creditComparisonPage.getCreditUseFromDropDown(), containsString("Wohnen"));
    }

    @Test
    public void testCanAskLoanWithMinCreditPerMonth() {
        String minPriceLoanPerMonth =
                landingPage
                        .selectCreditAmount("2.750 €")
                        .selectCreditDuration("24 Monate")
                        .selectCreditCategory("Wohnen")
                        .getMinPriceLoanPerMonth();
        creditComparisonPage =
                landingPage
                        .clickOnNextWithOption(landingPage.getMinSegmentPerMonth());
        assertThat(creditComparisonPage.getSectionLoanValueMonth(), equalTo(minPriceLoanPerMonth));
    }
}
