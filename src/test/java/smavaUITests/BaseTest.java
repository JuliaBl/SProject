package smavaUITests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import smava.setup.WebDriverRunner;
import java.net.MalformedURLException;
import static smava.setup.WebDriverRunner.terminate;

public class BaseTest {

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        WebDriverRunner.setTLDriver();
    }

    @AfterAll
    public static void tearDown() {
        terminate();
    }
}
