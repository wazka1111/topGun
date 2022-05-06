package baseTest;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class PlaywrightInit {
    private static Logger logger = LoggerFactory.getLogger("PlaywrightInit.class");
    private Playwright playwright;
    Browser browser;
    BrowserType browserType;
    BrowserContext context;
    private Page page;

    public PlaywrightInit() {
    }

    public Page initPage() {
        this.playwright = Playwright.create();
        if (Boolean.parseBoolean(System.getProperty("trace"))) {
            return startTracing();
        } else {
            this.browser = getBrowserType();
            this.page = browser.newPage();
            return this.page;
        }
    }

    private Browser getBrowserType() {

        switch (System.getProperty("channel")) {
            case "chrome":
                return playwright.chromium().launch(
                        setBrowserOptions(System.getProperty("channel"), Double.parseDouble(System.getProperty("slomo")), Boolean.parseBoolean(System.getProperty("headless"))));
            case "firefox":
                return playwright.firefox().launch(
                        setBrowserOptions(System.getProperty("channel"), Double.parseDouble(System.getProperty("slomo")), Boolean.parseBoolean(System.getProperty("headless"))));
            case "webkit":
                return playwright.webkit().launch(
                        setBrowserOptions(System.getProperty("channel"), Double.parseDouble(System.getProperty("slomo")), Boolean.parseBoolean(System.getProperty("headless"))));
            default:
                return playwright.chromium().launch(
                        setBrowserOptions(System.getProperty("channel"), Double.parseDouble(System.getProperty("slomo")), Boolean.parseBoolean(System.getProperty("headless"))));
        }
    }

    private BrowserType.LaunchOptions setBrowserOptions(String channel, double slomo, boolean headless) {
        return new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slomo)
                .setChannel(channel);
    }

    public void openURL() {
        page.navigate(System.getProperty("url"));

    }

    public Page getPage() {
        return this.page;
    }

    public void closePlaywright() {
        playwright.close();
    }

    public Page startTracing() {
        //this.playwright = Playwright.create();
        this.browser = getBrowserType();
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setTitle("Title Dariusz")
                .setSnapshots(true)
                .setScreenshots(true)
        );
        this.page = context.newPage();
        return this.page;

    }

    public void stopTracing() {
        if (Boolean.parseBoolean(System.getProperty("trace")))
        {
            this.context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/trace.zip")));
            logger.info("Trace.zip file created in target folder");
        }
    }

    public void listeners() {
        page.onLoad(p -> System.out.println("Listener onLoad: Page loaded!"));
        page.onClose(p -> System.out.println("Listener onClose: Page closed!"));
        page.onDOMContentLoaded(p -> System.out.println("Listener onDOMContentLoaded: Page onDOMContentLoaded!"));
        page.onRequest(p -> System.out.println("Listener onRequest: Page onRequest!"));
    }
}
