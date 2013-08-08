
package tests;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

import page_helpers.*;


public class PageTests_Home extends PageHelper_Home {
    private WebDriver browser;
    private String name = "";

    public void setUp() throws Exception {
        browser = new FirefoxDriver();
    }

    public void tearDown() throws Exception {
        takeScreenshot(browser, name);
        browser.quit();
    }

    /**
     * Tests the sign in link
     * 
     * @throws Exception
     */
    @Test(enabled = true)
    public void xSigninLink() throws Exception {
        browser.get(getUrl("home"));
        name = "signinLink";
        clickSignIn(browser);
    }
}
