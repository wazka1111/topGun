package baseTest;


import basePage.BasePage;
import configuration.BrowserEnvironment;
import configuration.EnvironmentProperty;
import configuration.yaml.YamlConfigControler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusLoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBasePlaywright {
    protected static WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("TestBasePlaywright.class");
    private static BrowserEnvironment browserEnvironment;
    private static YamlConfigControler controler;




    @BeforeAll
    static void setUp() {
        browserEnvironment = new BrowserEnvironment();
        controler = new YamlConfigControler();

    }

    @BeforeEach
    void beforeEach() throws IOException {


        logger.debug("Driver initialized");

    }

    @AfterEach
     void tearDown() {

        logger.debug("Driver closed");
    }

}
