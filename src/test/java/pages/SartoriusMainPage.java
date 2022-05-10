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
    private Locator logout = page.locator("#button-logout");
    private Locator accountName = page.locator("#button-user-name");
    private Locator projectsListRows = page.locator("ui-root-sartorius-ui-table-body ui-root-sartorius-ui-table-row");
    private Locator nameLabel = page.locator("ui-root-sartorius-ui-table-cell:has-text(\"Name\")").first();
    private Locator listOfProjectNames = page.locator("ui-root-sartorius-ui-table-cell:below(ui-root-sartorius-ui-table-cell:has-text(\"Name\"):nth-child(1))");  //relative locator.  All projects below Name label.
    private Locator goToProjectsButton = page.locator("ui-root-sartorius-ui-button[data-testid='recent-projects-list-button']");  //relative locator.  All projects below Name label.


    public SartoriusMainPage skipOnboarding() {
        onboardingSkip.click();
        return this;
    }
    public SartoriusMainPage goToProjectsSection() {
        if (goToProjectsButton.isVisible()) {
            goToProjectsButton.click();
        }
        return this;
    }

    public String getAccountName() {
        return accountName.innerText();
    }
    public void logout() {
        logout.click();
        logger.info("User has been properly logged out");
    }

    private int countExistingProjects() {
        waitForElement(projectsListRows);
        return projectsListRows.count();
    }
    public List<String> getProjectsList() {
        waitForElement(listOfProjectNames);
        List<String> existingProjects = new ArrayList<>();
        int projectsCount = countExistingProjects();
        logger.info("There are: " + projectsCount + " projects");
        for (int i = 0; i < projectsCount; i++) {
            existingProjects.add(listOfProjectNames.allTextContents().get(i));
            logger.info("Existing project: "+ listOfProjectNames.allTextContents().get(i));
        }
       return existingProjects;
    }
}