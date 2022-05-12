package pages;

import basePage.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SartoriusMainPage {
    private Logger logger = LoggerFactory.getLogger(SartoriusMainPage.class);
    private Page page;
    private BasePage basePage;


    public SartoriusMainPage() {
        System.out.println("konstruktor Login Page");
        basePage= new BasePage();
        initLocators();
    }


    private Locator onboardingSkip;
    private Locator logout;
    private Locator accountName;
    private Locator projectsListRows;
    private Locator nameLabel;
    private Locator listOfProjectNames;
    private Locator goToProjectsButton;


    private void initLocators() {
        onboardingSkip = page.locator("#skip-onboarding");
        logout = page.locator("#button-logout");
        accountName = page.locator("#button-user-name");
        projectsListRows = page.locator("ui-root-sartorius-ui-table-body ui-root-sartorius-ui-table-row");
        nameLabel = page.locator("ui-root-sartorius-ui-table-cell:has-text(\"Name\")").first();
        listOfProjectNames = page.locator("ui-root-sartorius-ui-table-cell:below(ui-root-sartorius-ui-table-cell:has-text(\"Name\"):nth-child(1))");  //relative locator.  All projects below Name label.
        goToProjectsButton = page.locator("ui-root-sartorius-ui-button[data-testid='recent-projects-list-button']");  //relative locator.  All projects below Name label.

    }

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
        basePage.waitForElement(projectsListRows);
        return projectsListRows.count();
    }

    public List<String> getProjectsList() {
        basePage.waitForElement((listOfProjectNames));
        List<String> existingProjects = new ArrayList<>();
        int projectsCount = countExistingProjects();
        logger.info("There are: " + projectsCount + " projects");
        for (int i = 0; i < projectsCount; i++) {
            existingProjects.add(listOfProjectNames.allTextContents().get(i));
            logger.info("Existing project: " + listOfProjectNames.allTextContents().get(i));
        }
        return existingProjects;
    }
}