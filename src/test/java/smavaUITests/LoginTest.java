package smavaUITests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smava.domain.User;
import smava.pageobjects.InvalidLoginPage;
import smava.pageobjects.LandingPage;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class LoginTest extends BaseTest{
    private LandingPage landingPage;
    private InvalidLoginPage invalidLoginPage;
    private User invalidUser;

    @BeforeEach
    public void setUpLoginTest() {
        landingPage = new LandingPage().open();
    }

    @Test
    public void testCannotLoginWithInvalidCredentials() {
        invalidUser = new User("sampleEmail@gmail.com", "12345");
        invalidLoginPage =
                landingPage
                        .clickOnSighInLink()
                        .inputUserEmailPassword(invalidUser)
                        .clickOnSignIn(InvalidLoginPage.class);
        assertThat(invalidLoginPage.getSignInError(), containsString("Ihre Angaben zum Einloggen sind ungültig. " +
                "Bitte versuchen Sie es erneut. Bitte beachten Sie, dass Ihr Zugang bei 3 Fehlversuchen von uns vorläufig gesperrt wird."));
    }

    @Test
    public void testCannotLoginWithEmptyCredentials() {
        invalidUser = new User("", "");
        landingPage
                .clickOnSighInLink()
                .inputUserEmailPassword(invalidUser)
                .clickOnSignIn(LandingPage.class);
        assertThat(landingPage.isPopUpSignInVisible(), equalTo(true));
    }
}
