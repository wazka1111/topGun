package baseTest;

import com.microsoft.playwright.*;

public class PlaywrightInit {

    public Browser getBrowser(String browserName, BrowserType.LaunchOptions launchOptions) {
        Playwright playwright = Playwright.create();

        BrowserType browserType = null;

        if (browserName.equalsIgnoreCase("chrome")) {
            browserType = playwright.chromium();
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            browserType = playwright.firefox();
        }
        if (browserName.equalsIgnoreCase("webkit")) {
            browserType = playwright.webkit();
        }
        return browserType.launch(launchOptions);
    }

    public BrowserContext getBrowserContext(Browser browser, Browser.NewContextOptions newContextOptions) {
        return browser.newContext(newContextOptions);
    }

    public Page getPage(BrowserContext browserContext) {
        return browserContext.newPage();
    }
}
