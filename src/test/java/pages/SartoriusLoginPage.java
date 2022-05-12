/*
package pages;

import basePage.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SartoriusLoginPage {
    private Logger logger = LoggerFactory.getLogger(SartoriusLoginPage.class);
    private Page page;
    private BasePage basePage;

    public SartoriusLoginPage() {
        System.out.println("konstruktor Login Page");
        basePage= new BasePage();
        initLocators();
    }

    private Locator username;
    private Locator password;
    private Locator submitButton;

    private void initLocators() {
        username = page.locator("#username");
        password = page.locator("#password");
        submitButton = page.locator("#kc-login");
    }

    public SartoriusMainPage login() {
        username.fill(System.getProperty("login"));
        password.fill(System.getProperty("password"));
        submitButton.click();
        logger.info("User has been properly logged in to the application");
        return new SartoriusMainPage();
    }

}*/
