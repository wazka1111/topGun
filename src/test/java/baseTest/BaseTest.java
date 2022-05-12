package baseTest;

import TestContext.TestContext;
import basePage.BasePage;
import com.google.gson.Gson;
import com.microsoft.playwright.Page;
import configuration.yaml.YamlConfigControler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusLoginPage;
import pages.SartoriusMainPage;

public class BaseTest {
    private static Logger logger = LoggerFactory.getLogger("BaseTest.class");
    protected static TestContext context = TestContext.getInstance();
    protected static Gson parser = new Gson();
    protected static PlaywrightInit playwrightInit;
    protected  Page page;
    public static BasePage basePage;
    protected YamlConfigControler controler;
    protected SartoriusLoginPage sartoriusLoginPage;
    protected SartoriusMainPage sartoriusMainPage;


    public BaseTest() {
        System.out.println("Constructor BaseTest");
    }

}
