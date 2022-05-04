package pages;

import basePage.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SartoriusMainPage extends BasePage {
    private Logger logger = LoggerFactory.getLogger(SartoriusMainPage.class);


    public SartoriusMainPage(Page page) {
        super(page);
    }

    private Locator onboardingSkip = page.locator("#skip-onboarding");
    private Locator logout = page.locator("#single-spa-application\\:\\@umetrics\\/studio-ui-header >> studio-header >> div >> ui-root-sartorius-ui-header >> ui-root-sartorius-ui-button:nth-child(5)");
    private Locator accountName = page.locator("#button-user-name");
    private Locator projectsListRows = page.locator("ui-root-sartorius-ui-table-body ui-root-sartorius-ui-table-row");
    private Locator nameLabel = page.locator("ui-root-sartorius-ui-table-cell:has-text(\"Name\")").first();
    private Locator listOfProjectNames = page.locator("ui-root-sartorius-ui-table-cell:below(ui-root-sartorius-ui-table-cell:has-text(\"Name\"):nth-child(1))");  //relative locator.  All projects below Name label.


    public SartoriusMainPage skipOnboarding() {
        onboardingSkip.click();
        return this;
    }

    public String getAccountName() {
        return accountName.innerText();
    }
    public void logout() {
        logout.click();
    }

    private int countExistingProjects() {
        waitForElement(projectsListRows);
        return projectsListRows.count();
    }
    public List<String> getProjectsList() {
        waitForElement(listOfProjectNames);
        List<String> existingProjects = new ArrayList<>();
        for (int i = 0; i < countExistingProjects(); i++) {
            existingProjects.add(listOfProjectNames.allTextContents().get(i));
            logger.info("Existing project list: "+ listOfProjectNames.allTextContents().get(i));
        }
       return existingProjects;
    }
}