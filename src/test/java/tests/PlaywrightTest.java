package tests;

import baseTest.TestBasePlaywright;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusLoginPage;
import pages.SartoriusMainPage;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlaywrightTest extends TestBasePlaywright {
    private Logger logger = LoggerFactory.getLogger("PlaywrightTest.class");
    private SartoriusLoginPage sartoriusLoginPage;
    private SartoriusMainPage sartoriusMainPage;



    void loginToStudioPlaywright0(){
        sartoriusMainPage = new SartoriusMainPage(page);
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions() // or firefox, webkit
                            .setHeadless(false)
                            .setSlowMo(2000)
                            .setChannel("chrome")
            );
            BrowserContext context = browser.newContext();

            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setTitle("Title Darka"));

            Page page = context.newPage();
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

            //projects
            //List<String> projectsList = sartoriusMainPage.getProjectsList();

            //Logout
            page.locator("#single-spa-application\\:\\@umetrics\\/studio-ui-header >> studio-header >> div >> ui-root-sartorius-ui-header >> ui-root-sartorius-ui-button:nth-child(5)").click();
            logger.info("Test PASS");

            //stop tracing
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/trace.zip")));

        }
    }

    void loginToStudioPlaywright1(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions() // or firefox, webkit
                            .setHeadless(false)
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
           // page.locator("#single-spa-application\\:\\@umetrics\\/studio-ui-header >> studio-header >> div >> ui-root-sartorius-ui-header >> ui-root-sartorius-ui-button:nth-child(5)").click();
           page.locator("#button-logout").click();

           logger.info("Test PASS");

        }
    }


    void loginToStudioPlaywright2(){
        sartoriusLoginPage = new SartoriusLoginPage(page);
        sartoriusMainPage = new SartoriusMainPage(page);
        //login, skipOnboarding and get accountUserName
        String accountUserName = sartoriusLoginPage.login()
                .skipOnboarding()
                .getAccountName();

        assertThat("Wrong account name", accountUserName, containsStringIgnoringCase(System.getProperty("accountName")));

        //logout
        sartoriusMainPage.logout();
        logger.info("Test PASS");
    }

    @Test
    void checkProjectsList(){
        sartoriusLoginPage = new SartoriusLoginPage(page);
        sartoriusMainPage = new SartoriusMainPage(page);
        //login, skipOnboarding and get accountUserName
        String accountUserName = sartoriusLoginPage.login()
                .skipOnboarding()
                .goToProjectsSection()
                .getAccountName();

        assertThat("Wrong account name", accountUserName, containsStringIgnoringCase(System.getProperty("accountName")));

        //check existing projects list
        List<String> projectsList = sartoriusMainPage.getProjectsList();


        //logout
        sartoriusMainPage.logout();
        logger.info("Test PASS");
    }


}
