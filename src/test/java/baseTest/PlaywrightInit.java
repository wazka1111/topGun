package baseTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightInit {
    private Playwright playwright;
    private Page page;

    public PlaywrightInit() {
    }

    public Page initPage(){
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                setBrowserOptions(System.getProperty("channel"), Double.parseDouble(System.getProperty("slomo")), Boolean.parseBoolean(System.getProperty("headless")))
        );
        this.page =  browser.newPage();
        return this.page;
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
}
