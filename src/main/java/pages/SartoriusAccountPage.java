package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SartoriusAccountPage extends BasePage {
    private Logger logger = LoggerFactory.getLogger(SartoriusAccountPage.class);


    public SartoriusAccountPage(WebDriver driver) {
        super(driver);
    }


    public  String getAccountName() {
        String text;
        WebElement element = getUsernameElement();
        try {
            text = element.getText();
            logger.debug("Account name :" + text);
            return text;
        }catch (Exception e) {
            logger.error("Account name failed:" +e.getMessage());
            return null;
        }
    }

    private WebElement getUsernameElement() {
        WebElement shadowHost = driver.findElement(By.cssSelector("#single-spa-application\\:\\@umetrics\\/studio-ui-header > studio-header"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#button-user-name"));

        return shadowContent;
    }
    public void logOut() {
        WebElement shadowHost = driver.findElement(By.cssSelector("#single-spa-application\\:\\@umetrics\\/studio-ui-header > studio-header"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement logout = shadowRoot.findElement(By.cssSelector("div > ui-root-sartorius-ui-header > ui-root-sartorius-ui-button:nth-child(5)"));
        clickObject(logout);

    }
}
