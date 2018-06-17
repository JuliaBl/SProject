package smavaUITests;

import org.junit.AfterClass;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import static smava.setup.SeleniumDriver.getDriver;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        LoanTest.class,
        LoginTest.class
})

public class TestSuite {

    @AfterClass
    public static void tearDown() {
        getDriver().quit();
    }
}
