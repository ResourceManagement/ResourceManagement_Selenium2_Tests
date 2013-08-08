
package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import page_helpers.PageHelper_Home;


public class PageTests_Home extends PageHelper_Home {
    private WebDriver browser;
    private String name = "";

    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.get("http://www.google.com");
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
