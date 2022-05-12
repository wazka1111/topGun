package StepsDefinitions;

import basePage.BasePage;
import baseTest.KBaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.K_MainPage;
import pages.K_loginPage;
import pages.SartoriusLoginPage;
import pages.SartoriusMainPage;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class StepDefinitionsTest {
    K_loginPage k_loginPage = new K_loginPage();
    K_MainPage k_mainPage = new K_MainPage();

    @Given("I save data {string}")
    public void i_save_data(String tags) {
        //save test data
        System.out.println("Save test context - TO DO");
        //ContextOperations.saveTestContext(tags, context);
    }
    @When("Authorized user navigate to main page")
    public void authorized_user_navigate_to_main_page() {

        //login to Studio
        KBaseTest.localPage.navigate(System.getProperty("url"));
        k_loginPage.login();
        String accountUserName = k_mainPage.goToProjectsSection().
                getAccountName();
        assertThat("Wrong account name",accountUserName, equalToIgnoringCase("Dariusz Hryciuk"));

        //login to Studio
       /* BasePage.sartoriusLoginPage = new SartoriusLoginPage();
        String accountUserName = BasePage.sartoriusLoginPage.login()
                .skipOnboarding()
                .goToProjectsSection()
                .getAccountName();
        assertThat("Wrong account name", accountUserName, containsStringIgnoringCase(System.getProperty("accountName")));
*/
    }
    @Then("Authorized user is able to see all existing projects")
    public void authorized_user_is_able_to_see_all_existing_projects() {
        List<String> projectsList = k_mainPage.getProjectsList();
       /* BasePage.sartoriusMainPage = new SartoriusMainPage();
        List<String> projectsList =  BasePage.sartoriusMainPage.getProjectsList();*/
    }

    @And("Authorized user has been logged out")
    public void authorized_user_has_been_logged_out() {
        k_mainPage.logout();
       // BasePage.sartoriusMainPage.logout();
    }


}
