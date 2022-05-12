package pages;


import baseTest.BaseTest;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class K_MainPage {
    private Logger logger = LoggerFactory.getLogger(K_MainPage.class);
    Page page = BaseTest.localPage;

    String logout = "#button-logout";
    String accountName = "#button-user-name";
    String projectsListRows = "ui-root-sartorius-ui-table-body ui-root-sartorius-ui-table-row";
    String nameLabel = "ui-root-sartorius-ui-table-cell:has-text(\"Name\")";
    String listOfProjectNames = "ui-root-sartorius-ui-table-cell:below(ui-root-sartorius-ui-table-cell:has-text(\"Name\"):nth-child(1))";  //relative locator.  All projects below Name label.
    String goToProjectsButton = "ui-root-sartorius-ui-button[data-testid='recent-projects-list-button']";  //relative locator.  All projects below Name label.


    public K_MainPage goToProjectsSection() {
        if (page.locator(goToProjectsButton).isVisible()) {
            page.click(goToProjectsButton);
        }
        return this;
    }

    public String getAccountName() {
        if (page.locator(accountName) != null) {
            page.locator(accountName).first().waitFor();
        } else {
            assertThat("Element is not visible on the page", true, is(false));
        }
        return page.locator(accountName).innerText();
    }

    public void logout() {
        if (page.locator(logout).isVisible()) {
            page.click(logout);
        }
        logger.info("User has been properly logged out");
    }

    private int countExistingProjects() {
        if (page.locator(projectsListRows) != null) {
            page.locator(projectsListRows).first().waitFor();
        } else {
            assertThat("Element is not visible on the page", true, is(false));
        }
        return page.locator(projectsListRows).count();
    }

    public List<String> getProjectsList() {
        if (page.locator(listOfProjectNames) != null) {
            page.locator(listOfProjectNames).first().waitFor();
        } else {
            assertThat("Element is not visible on the page", true, is(false));
        }
        List<String> existingProjects = new ArrayList<>();
        int projectsCount = countExistingProjects();
        logger.info("There are: " + projectsCount + " projects");
        for (int i = 0; i < projectsCount; i++) {
            existingProjects.add( page.locator(listOfProjectNames).allTextContents().get(i));
            logger.info("Existing project: " +  page.locator(listOfProjectNames).allTextContents().get(i));
        }
        return existingProjects;
    }
}
