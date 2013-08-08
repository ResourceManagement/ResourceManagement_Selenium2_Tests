
package helpers;


import java.io.IOException;
import java.util.Properties;


public class SiteConfig {

    private static Properties config = new Properties();
    private static boolean loaded = false;

    public SiteConfig() {
        if (loaded)
            return;
        System.out.print("Loading site config file... ");
        try {
            config.load(SiteConfig.class.getResourceAsStream("/siteConfig.properties"));
        } catch (IOException e) {
            System.out.println("Failed.");
            e.printStackTrace();
        }
        loaded = true;
        System.out.println("Done.");
    }

    /**
     * @param key
     * @return
     */
    public String find(String key) {
        return config.getProperty(key);
    }

    /**
     * @param category
     * @param key
     * @return value of a key (key = category.item)
     */
    public String find(String category, String key) {
        return config.getProperty(category + "." + key);
    }
}