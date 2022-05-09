package baseTest;


import basePage.BasePage;
import configuration.yaml.YamlConfigControler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusMainPage;


public class Hooks extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger("Hooks.class");

    @BeforeAll
    public static void beforeAll() {    // Runs before all scenarios
        controler = new YamlConfigControler();
    }

    @Before(order = 1)                //executed before each single scenario
    public void initTitle(Scenario scenario) {
        System.out.println("START. SCENARIO NAME = " + scenario.getName());
    }

    @Before(order = 2)                //executed before each single scenario
    public void initData(Scenario scenario) {
        playwrightInit = new PlaywrightInit();
        page = playwrightInit.initPage();
        playwrightInit.openURL();
        basePage = new BasePage(page);
        logger.info("Playwright initialized");

    }



    @After                              //executed after each single scenario
    public void closePlaywright(Scenario scenario) {
        playwrightInit.stopTracing();
        //page.close();
        playwrightInit.closePlaywright();
        logger.info("Playwright closed properly");
        logger.info("SCENARIO NAME =  " + scenario.getName() + ". STATUS = " + scenario.getStatus());

    }
}



