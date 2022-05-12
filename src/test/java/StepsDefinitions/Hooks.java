package StepsDefinitions;

import baseTest.BaseTest;
import baseTest.FrameworkInitialize;
import configuration.yaml.YamlConfigControler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private Logger logger = LoggerFactory.getLogger("Hooks.class");
    YamlConfigControler controler;

    @Before(order = 1)                //executed before each single scenario
    public void initTitle(Scenario scenario) {
        controler = new YamlConfigControler();
        BaseTest.localPage = new FrameworkInitialize().initializePlaywright();
        logger.info("START. SCENARIO NAME = " + scenario.getName());
    }

    @After                              //executed after each single scenario
    public void closePlaywright(Scenario scenario) {
        BaseTest.localPage.close();
        logger.info("Playwright closed properly");
        logger.info("STOP. SCENARIO NAME = " + scenario.getName() + ". STATUS = " + scenario.getStatus());
    }
}
