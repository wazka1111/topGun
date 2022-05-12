package basePage;

import baseTest.PlaywrightInit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Getter
public class BasePage {
    private Logger logger = LoggerFactory.getLogger("BasePage.class");
    private PlaywrightInit playwrightInit;
    private Page page;


    public BasePage() {

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
