
package helpers;


import java.io.IOException;
import java.util.Properties;



public class UIMap {

	private static Properties uimap = new Properties();
	private static boolean loaded = false;


	public UIMap() {
		if (loaded) return;
		System.out.print("Loading uimap file... ");
		try {
			uimap.load(UIMap.class.getResourceAsStream("/uimap.properties"));
		} catch (IOException e) {
			System.out.print("Failed.");
			e.printStackTrace();
		}
		loaded = true;
		System.out.println("Done.");
	}


	/**
	 * Returns the value of a key from uimap.properties.
	 * 
	 * @param category
	 * @param key
	 * @return the value of the key
	 */
	public String find(String category, String key) {
		return uimap.getProperty(category + "." + key);
	}


	/**
	 * Returns the value of a key from uimap.properties.
	 * 
	 * @param item
	 * @return value of the key
	 */
	public String find(String key) {
		return uimap.getProperty(key);
	}
}