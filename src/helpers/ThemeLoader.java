package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ThemeLoader {

	private Properties userSettings;

	public ThemeLoader() {
		userSettings = new Properties();
		InputStream is = null;
		
		File file = new File("user-settings.properties");
		try {
			is = new FileInputStream(file);
			userSettings.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String key) {
		return userSettings.getProperty(key);
	}
	
	public void set(String key, String value) {
		userSettings.setProperty(key, value);
	}
	
	public void save() throws IOException {
		userSettings.store(new FileWriter("user-settings.properties"), "");
	}
}
