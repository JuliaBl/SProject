package smavaUITests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
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

    @ParameterizedTest
    @CsvSource({"2.750 €, 24 Monate, Wohnen", "120.000 €, 12 Monate, Wohnen"})
    public void testCanAskAndVerifyLoan(String creditValue, String duration, String reason) {
        creditComparisonPage =
                landingPage
                        .selectCreditAmount(creditValue)
                        .selectCreditDuration(duration)
                        .selectCreditCategory(reason)
                        .clickOnLoanSelectForward();
        assertThat(creditComparisonPage.getCreditLoanAmountFromDropDown(), containsString(creditValue));
        assertThat(creditComparisonPage.getCreditLoanTermFromDropDown(), containsString(duration));
        assertThat(creditComparisonPage.getCreditUseFromDropDown(), containsString(reason));
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
