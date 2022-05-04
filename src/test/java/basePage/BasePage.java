package basePage;

import baseTest.PlaywrightInit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BasePage {
    private Logger logger = LoggerFactory.getLogger("BasePage.class");
    private PlaywrightInit playwrightInit;
    protected Page page;

    public BasePage() {
    }

    public BasePage(Page page) {
        this.page = page;
    }

    protected void waitForElement(Locator element) {
        if (element != null) {
            element.first().waitFor();
        } else {
            assertThat("Element is not visible on the page", true, is(false));
        }
    }
}
