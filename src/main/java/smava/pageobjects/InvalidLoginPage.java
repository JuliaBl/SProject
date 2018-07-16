package smava.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static smava.setup.element.Element.getElement;

public class InvalidLoginPage extends Page<InvalidLoginPage> {

    private static final By INFO_PLEASE_LOGIN = By.xpath("//*[text()='Bitte loggen Sie sich ein']");
    private static final By BOX_INNER_ERROR = By.xpath("//div[@class='box-wrapper error']//li");


    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOfElementLocated(INFO_PLEASE_LOGIN);
    }

    public String getSignInError() {
        return getElement(BOX_INNER_ERROR).getText();
    }
}
