package tests;

import baseTest.TestBasePlaywright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlaywrightTest extends TestBasePlaywright {
    private Logger logger = LoggerFactory.getLogger("PlaywrightTest.class");

    @Test
    void loginToStudioPlaywright(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions() // or firefox, webkit
                            .setHeadless(true)
                            .setSlowMo(2000)
                            .setChannel("chrome")
            );
            Page page = browser.newPage();
            page.navigate(System.getProperty("url"));
            //Loging
            page.fill("#username", System.getProperty("login"));
            page.fill("#password", System.getProperty("password"));
            page.locator("#kc-login").click();

            //skip
            page.locator("#skip-onboarding").click();

            //get username
            String userName = page.locator("#button-user-name").innerText();
            assertThat("Error", userName, containsStringIgnoringCase(System.getProperty("accountName")));

            //Logout
            page.locator("#single-spa-application\\:\\@umetrics\\/studio-ui-header >> studio-header >> div >> ui-root-sartorius-ui-header >> ui-root-sartorius-ui-button:nth-child(5)").click();
           logger.info("Test PASS");

        }
    }


}
