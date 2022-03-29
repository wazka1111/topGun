/*
package tests;

import baseTest.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SartoriusAccountPage;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

//@Execution(ExecutionMode.CONCURRENT)
public class SeleniumTest extends TestBase {
    private Logger logger = LoggerFactory.getLogger("SeleniumTest.class");


    @Test
    @DisplayName("SartoriusLogin")
    @Tag("Studio")
    public void studioLogin() {
        logger.info(">>>> Start test >>>>>");

        SartoriusAccountPage accountPage=  sartoriusLoginPage.login();
        String accountName = accountPage.getAccountName();
        assertThat("Error", accountName, containsStringIgnoringCase(System.getProperty("accountName")));
        accountPage.logOut();
        logger.info(">>>> Test pass >>>>>");



    }

}
*/
