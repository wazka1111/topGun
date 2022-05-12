package basePage;

import baseTest.PlaywrightInit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import configuration.yaml.YamlConfigControler;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusLoginPage;
import pages.SartoriusMainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Getter
public class BasePage {
    private Logger logger = LoggerFactory.getLogger("BasePage.class");
    private PlaywrightInit playwrightInit;
    private Page page;

    //Pages
    public static SartoriusLoginPage sartoriusLoginPage;
    public static SartoriusMainPage sartoriusMainPage;

    public BasePage() {

        System.out.println("konstruktor bezparametrowy base page");
    }

    public BasePage(Page page) {
        this.page = page;
        logger.info("All pages were initialized properly");
    }


    public void waitForElement(Locator element) {
        if (element != null) {
            element.first().waitFor();
        } else {
            assertThat("Element is not visible on the page", true, is(false));
        }
    }
}
