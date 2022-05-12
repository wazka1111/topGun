/*
package baseTest;


import basePage.BasePage;
import com.microsoft.playwright.Page;
import configuration.yaml.YamlConfigControler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusMainPage;


import java.io.IOException;

public class TestBasePlaywright {
    private static Logger logger = LoggerFactory.getLogger("TestBasePlaywright.class");
    private static YamlConfigControler controler;
    private static PlaywrightInit playwrightInit;
    protected static Page page;
    public static BasePage basePage;




    @BeforeAll
    static void setUp() {
        controler = new YamlConfigControler();
    }

    @BeforeEach
    void beforeEach() throws IOException {
        playwrightInit = new PlaywrightInit();
        page = playwrightInit.initPage();
        playwrightInit.openURL();
        basePage = new BasePage(page);
        logger.info("Playwright initialized");
    }

    @AfterEach
     void tearDown() {
        playwrightInit.stopTracing();
        //page.close();
        playwrightInit.closePlaywright();
        logger.info("Playwright closed properly");
    }

}
*/
