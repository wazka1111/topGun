package pages;

import basePage.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SartoriusLoginPage extends BasePage{
    private Logger logger = LoggerFactory.getLogger(SartoriusLoginPage.class);


    public SartoriusLoginPage(Page page) {
        super(page);
    }

    private Locator username = page.locator("#username");
    private Locator password = page.locator("#password");
    private Locator submitButton = page.locator("#kc-login");

    public SartoriusMainPage login() {
        username.fill(System.getProperty("login"));
        password.fill(System.getProperty("password"));
        submitButton.click();
        return new SartoriusMainPage(page);
    }

}