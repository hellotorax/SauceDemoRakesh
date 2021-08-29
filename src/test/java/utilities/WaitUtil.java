package utilities;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rakesh Kumar Padhiary
 * Purpose: This is utility class meant for variety syncronous opeartion
 */
public class WaitUtil {



    private WebDriver driver;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
    }

    public int getExplicitTimeout() {
        //Gets the predefined explicit wait from the config.reader & then returns the same in integer format
        return Integer.parseInt(ConfigReader.get("explicit.wait"));
    }

    public int getImplicitTimeout() {
        //Gets the predefined implicit wait from the config.reader & then returns the same in integer format
        return Integer.parseInt(ConfigReader.get("implicit.wait"));
    }

    public int getPollTime() {
        //Gets the predefined pollTime wait from the config.reader & then returns the same in integer format
        return Integer.parseInt(ConfigReader.get("poll.time"));
    }

    public Boolean isElementVisible(WebElement element, final long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (ElementNotVisibleException e) {
            LogUtil.error("\n" + element + "not found on the page and the exception details are as followed: ");
            LogUtil.error(String.valueOf(e));
        }
        return false;
    }

    public Boolean isElementsListVisible(List<WebElement> elements, final long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        } catch (ElementNotVisibleException e) {
            LogUtil.error("\n" + elements + "not found on the page and the exception details are as followed: ");
            LogUtil.error(String.valueOf(e));
        }
        return false;
    }

    public WebElement fluentWait(final By locator, final long timeout, final long polltime) {
        Wait<WebDriver> wait =
                new FluentWait<WebDriver>(driver)
                        .withTimeout(timeout, TimeUnit.SECONDS)
                        .pollingEvery(polltime, TimeUnit.MILLISECONDS)
                        .ignoring(NoSuchElementException.class);

        WebElement element =
                wait.until(
                        new Function<WebDriver, WebElement>() {
                            public WebElement apply(WebDriver driver) {
                                return driver.findElement(locator);
                            }
                        });

        return element;
    }


    //Is alert present
    public Alert isAlertPresent(final long timeout) {
        Alert element = null;

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(ExpectedConditions.alertIsPresent());
        } catch (ElementNotVisibleException e) {
            LogUtil.error("\n[Error] Alert is not present and the exceptio details are as follows ");
            LogUtil.error(String.valueOf(e));
        }
        return element;
    }

    public void setSleepTimeOut(final long sleepWait) {
        try {
            Thread.sleep(sleepWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleep(Integer timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException ignored) {
        }
    }

    public void setImplicitTimeOut(final long implicitWait) {
        try {
            driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}
