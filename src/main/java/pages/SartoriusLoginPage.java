package pages;


import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SartoriusLoginPage extends BasePage {

    @FindBy(id="username")
    private WebElement email;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="kc-login")
    private WebElement btn_submit;

    @FindBy(id="skip-onboarding")
    private WebElement btn_skipOnbording;

    public SartoriusLoginPage(WebDriver driver) {
        super(driver);
    }

    public SartoriusAccountPage login() {
        performSendKeys(email,System.getProperty("login"));
        performSendKeys(password,System.getProperty("password"));

        clickObject(btn_submit);
        clickObject(btn_skipOnbording);
        return pageFactory.create(SartoriusAccountPage.class);
    }



}
