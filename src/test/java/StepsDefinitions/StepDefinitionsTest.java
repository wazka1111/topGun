package StepsDefinitions;

import TestContext.ContextOperations;
import TestContext.TestContext;
import baseTest.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.K_MainPage;
import pages.K_loginPage;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class StepDefinitionsTest {
    private Logger logger = LoggerFactory.getLogger(StepDefinitionsTest.class);
    protected static TestContext context;
    K_loginPage k_loginPage = new K_loginPage();
    K_MainPage k_mainPage = new K_MainPage();

    @Given("I save data {string}")
    public void i_save_data(String tags) {
        //context is a map created based on data from Examples - Param column
        context = TestContext.getInstance();
        ContextOperations.saveTestContext(tags, context);
    }
    @When("Authorized user navigate to main page")
    public void authorized_user_navigate_to_main_page() {

        //login to Studio
        BaseTest.localPage.navigate(System.getProperty("url"));
        k_loginPage.login();
        String accountUserName = k_mainPage.goToProjectsSection().
                getAccountName();
        assertThat("Wrong account name",accountUserName, equalToIgnoringCase("Dariusz Hryciuk"));
        logger.info("User is properly logged in");
    }
    @Then("Authorized user is able to see all existing projects")
    public void authorized_user_is_able_to_see_all_existing_projects() {
        List<String> projectsList = k_mainPage.getProjectsList();
    }

    @And("Authorized user has been logged out")
    public void authorized_user_has_been_logged_out() {
        k_mainPage.logout();
    }
}
