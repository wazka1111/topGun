package StepsDefinitions;

import baseTest.KBaseTest;
import baseTest.KFrameworkInitialize;
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
        System.out.println("START. SCENARIO NAME = " + scenario.getName());
        //before
        controler = new YamlConfigControler();
        KBaseTest.localPage = new KFrameworkInitialize().initializePlaywright();
        System.out.println(">>>>  Before 1");
    }

    @After                              //executed after each single scenario
    public void closePlaywright(Scenario scenario) {
        KBaseTest.localPage.close();
        logger.info("Playwright closed properly");
        logger.info("SCENARIO NAME =  " + scenario.getName() + ". STATUS = " + scenario.getStatus());
    }
}
