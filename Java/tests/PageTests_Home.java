
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
    }

    public void tearDown() throws Exception {
        takeScreenshot(browser, name);
    }

    @Test(enabled = true)
    public void xSigninLink() throws Exception {
        name = "signinLink";
        clickSignIn(browser);
    }
}
