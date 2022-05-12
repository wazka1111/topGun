package pages;


import baseTest.BaseTest;
import com.microsoft.playwright.Page;

public class K_loginPage {
    Page page = BaseTest.localPage;


    String username = "#username";
    String password = "#password";
    String submitButton = "#kc-login";
    String onboardingSkip = "#skip-onboarding";

    public K_MainPage login() {
        page.fill(username,System.getProperty("login"));
        page.fill(password,System.getProperty("password"));
        page.click(submitButton);
        skipOnboarding();
        return new K_MainPage();
    }

    public void skipOnboarding() {
            page.click(onboardingSkip);
    }
}
