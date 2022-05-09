package StepsDefinitions;

import TestContext.ContextOperations;
import baseTest.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SartoriusLoginPage;
import pages.SartoriusMainPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;

public class StepDefinitionsTest extends BaseTest {

    @Given("I save data {string}")
    public void i_save_data(String tags) {
        ContextOperations.saveTestContext(tags, context);
    }
    @When("Authorized user navigate to main page")
    public void authorized_user_navigate_to_main_page() {
        sartoriusLoginPage = new SartoriusLoginPage(page);
        sartoriusMainPage = new SartoriusMainPage(page);
        //login to Studio
        String accountUserName = sartoriusLoginPage.login()
                .skipOnboarding()
                .goToProjectsSection()
                .getAccountName();
        assertThat("Wrong account name", accountUserName, containsStringIgnoringCase(System.getProperty("accountName")));

    }
    @Then("Authorized user is able to see all existing projects")
    public void authorized_user_is_able_to_see_all_existing_projects() {
        List<String> projectsList = sartoriusMainPage.getProjectsList();
    }

    @And("Authorized user has been logged out")
    public void authorized_user_has_been_logged_out() {
        sartoriusMainPage.logout();
    }


}
