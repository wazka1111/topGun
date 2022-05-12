package baseTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;

public class FrameworkInitialize {

    //init browser
    public Page initializePlaywright() {
        PlaywrightInit playwrightInit = new PlaywrightInit();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(System.getProperty("headless")))
                .setSlowMo(Double.parseDouble(System.getProperty("slomo")))
                .setChannel(System.getProperty("channel"));

        //get browser
        var browser = playwrightInit.getBrowser(System.getProperty("channel"), launchOptions);

        //get browserContext
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        //contextOptions.locale = "en/US";

        var context = playwrightInit.getBrowserContext(browser, contextOptions);

        //get Page
        return playwrightInit.getPage(context);
    }

}
