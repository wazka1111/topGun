package baseTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;

public class KFrameworkInitialize {
    //init browser

    public Page initializePlaywright() {
        KPlaywrightInit kPlaywrightInit = new KPlaywrightInit();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = false;
        launchOptions.channel = "chrome";

        //get browser
        var browser = kPlaywrightInit.getBrowser("chrome", launchOptions);

        //get browserContext
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        //contextOptions.locale = "en/US";

        var context = kPlaywrightInit.getBrowserContext(browser, contextOptions);

        //get Page
        return kPlaywrightInit.getPage(context);
    }

}
