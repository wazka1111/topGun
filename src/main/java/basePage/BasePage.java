package basePage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected static PageFactory pageFactory;
    protected WebElement element;
    private Logger logger = LoggerFactory.getLogger(BasePage.class);
    private EventFiringMouse eventFiringMouse;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 15);
        this.pageFactory = new PageFactory(driver);
    }

   public BasePage() {
    }

    public void clickObject(WebElement element) {
        logger.debug("Click perform on the object: " + element.getText());
        highLighterMethod(element);
        element.click();
    }

    public void performSendKeys(WebElement element, String text) {
        logger.debug("SendKeys perform on the object: " + element.getText() + " following text has been sent " + text);
        highLighterMethod(element);
        element.clear();
        element.sendKeys(text);
    }
    private void highLighterMethod(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 5px solid red;');", element);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
