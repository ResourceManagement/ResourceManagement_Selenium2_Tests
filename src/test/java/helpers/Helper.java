
package helpers;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * General Helper
 */
public class Helper {

    private final SiteConfig config = new SiteConfig();
    public Random random = new Random();
    private final UIMap uimap = new UIMap();

    /**
     * Change a number of characters in the string.
     * 
     * @param string
     * @param numberOfRandomChars
     * @return modified String
     * @throws Exception
     */
    public String changeCharsInString(String string, int numberOfRandomChars) throws Exception {
        System.out.printf("Changed [%s] to ", string);
        String result = null;
        for (int i = 0; i < numberOfRandomChars; i++) {
            String randomCharacter = getRandomCharacters(1);
            result += random.nextInt(2) > 1 ? randomCharacter : string.charAt(i);
        }
        System.out.printf("[%s]\n", result);
        return result;
    }

    /**
     * @param account
     * @param item
     * @return
     */
    public String getAccountsData(String account, String item) {
        return config.find(account, item);
    }

    /**
     * Reads from siteConfig.properties (only one parameter required)
     * 
     * @param category
     * @param key
     * @return value of key
     */
    public String getConfigValue(String category, String key) {
        return config.find(category, key);
    }

    /**
     * @return default timeout
     */
    public int getDefaultTimeout() {
        return Integer.parseInt(config.find("timeout"));
    }

    /**
     * Gets the email for specified account number.
     * 
     * @param accountNumber
     *            int
     * @return email address
     */
    public String getEmail(int account) {
        return config.find("email", account + "");
    }

    /**
     * Get the number of tester's created config.
     * 
     * @return config.quantity
     */
    public int getNumberOfAccounts() {
        return Integer.parseInt(config.find("accountsNumber"));
    }

    /**
     * Gets the password for the specified account number.
     * 
     * @param account
     *            int
     * @return password
     */
    public String getPassword(int account) {
        return config.find("password", account + "");
    }

    /**
     * Creates a string with random characters.
     * 
     * @param size
     * @return
     */
    public String getRandomCharacters(int size) {
        String acceptedCharacters =
                "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-~!@#$%^&*()+=,.<>;':\"/?\\|{}[]`";
        return getRandomString(size, acceptedCharacters);
    }

    /**
     * Creates a string with random special characters.
     * 
     * @param size
     * @return
     * @throws Exception
     */
    public String getRandomSpecialCharacters(int size) {
        String acceptedCharacters = "~!@#$%^&*()+=,.<>;':\"/?\\|{}[]`";
        return getRandomString(size, acceptedCharacters);
    }

    /**
     * Creates a string with random characters.
     * 
     * @param size
     * @param acceptedCharacters
     * @return
     */
    private String getRandomString(int size, String acceptedCharacters) {
        StringBuilder sb = new StringBuilder(size);
        while (sb.length() < size)
            sb.append(acceptedCharacters.charAt(random.nextInt(acceptedCharacters.length())));
        return sb.toString();
    }

    /**
     * Creates a string with random characters: alpha-numeric, undescores, and
     * dashes.
     * 
     * @param size
     *            int
     * @return String
     * @throws Exception
     */
    public String getRandomValidCharacters(int size) {
        String acceptedCharacters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-";
        return getRandomString(size, acceptedCharacters);
    }

    /**
     * Get a random double from the interval [min, max].
     * 
     * @param min
     *            double
     * @param max
     *            double
     * @return double value from [min, max]
     * @throws Exception
     */
    public double getRandomValue(double min, double max) throws Exception {
        return min + (Math.random() * (max - min));
    }

    /**
     * Get a random int from the interval [min, max].
     * 
     * @param min
     *            int
     * @param max
     *            int
     * @return int value from [min, max]
     * @throws Exception
     */
    public int getRandomValue(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    /**
     * Reads from UIMap.properties
     * 
     * @param category
     * @param key
     *            (optional)
     * @return value of key
     */
    public String getUIMapValue(String category, String key) {
        return uimap.find(category, key);
    }

    /**
     * Gets an URL from uimap.properties.
     * 
     * @param page
     * @return url of a page
     */
    public String getUrl(String page) {
        return uimap.find("URL", page);
    }

    /**
     * Gets the user for the specified account number.
     * 
     * @param account
     *            int
     * @return username
     */
    public String getUser(int account) {
        return config.find("username", account + "");
    }

    /**
     * Checks if the user is logged in.
     * 
     * @param browser
     * @return login accepted status
     * @throws Exception
     */
    public boolean isUserLoggedIn(WebDriver browser) throws Exception {
        // TODO add check user login status
        return true;
    }

    /**
     * Logs in the user with a random (and valid) account number.
     * 
     * @param browser
     * @throws Exception
     */
    public void signIn(WebDriver browser) throws Exception {
        int accountNumber = random.nextInt(getNumberOfAccounts());
        String email = random.nextBoolean() == true ? getEmail(accountNumber) : getUser(accountNumber);
        String password = getPassword(accountNumber);
        signInNow(browser, email, password);
    }

    /**
     * Logs in the user with the specified account number.
     * 
     * @param accountNumber
     *            taken from siteConfig.properties
     */
    public void signIn(WebDriver browser, int accountNumber) throws Exception {
        String email = random.nextBoolean() == true ? getEmail(accountNumber) : getUser(accountNumber);
        String password = getPassword(accountNumber);
        signInNow(browser, email, password);
    }

    /**
     * Logs in the user with the specified credentials.
     * 
     * @param browser
     * @param emailOrUsername
     * @param password
     * @throws Exception
     */
    public void signIn(WebDriver browser, String emailOrUsername, String password) throws Exception {
        signInNow(browser, emailOrUsername, password);
    }

    /**
     * Logs in the user with the specified email and password.
     * 
     * @param browser
     * @param email
     * @param password
     * @throws Exception
     */
    private void signInNow(WebDriver browser, String email, String password) throws Exception {
        // TODO sign in
        isUserLoggedIn(browser);
    }

    /**
     * Logs out the user.
     * 
     * @param browser
     * @throws Exception
     */
    public void signOut(WebDriver browser) throws Exception {
        // TODO signout
    }

    public void takeScreenshot(WebDriver browser, String name) throws Exception {
        String screenshotsPath = "D:\\Screenshots\\";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String fileName = String.format("%s_%s.png", dateFormat.format(new Date()), name);

        File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotsPath + fileName));
        if (scrFile.exists())
            scrFile.delete();
    }

    /**
     * Waits [default timeout (siteConfig.properties)] seconds for a page to
     * load.
     * 
     * @param browser
     * @throws Exception
     */
    public void waitForPageToLoad(WebDriver browser) {
        waitingForPageToLoad(browser, getDefaultTimeout());
    }

    /**
     * Waits a specific number of seconds for a page to load.
     * 
     * @param browser
     * @param timeout
     *            int
     */
    public void waitForPageToLoad(WebDriver browser, int timeout) throws Exception {
        waitingForPageToLoad(browser, timeout);
    }

    /**
     * Actual wait-page-to-load script.
     * 
     * @param browser
     * @param timeout
     *            int
     */
    private void waitingForPageToLoad(WebDriver browser, int timeout) {
        System.out.print("Loading page... ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Failed.");
            e.printStackTrace();
        }
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver browser) {
                return ((JavascriptExecutor) browser).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(browser, timeout);
        wait.until(pageLoadCondition);
        System.out.println("Done.");
    }

}