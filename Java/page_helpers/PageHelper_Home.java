
package page_helpers;


import helpers.Helper;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Helper for the Home page
 */
public class PageHelper_Home extends Helper {

    /**
     * Clicks the Sign In button
     * 
     * @param browser
     */
    public void clickSignIn(WebDriver browser) {
        browser.findElement(By.xpath(getUIMapValue("home", "signin"))).click();
        waitForPageToLoad(browser);
        Assert.assertTrue(browser.getCurrentUrl().contains(getUrl("home")));
    }
}