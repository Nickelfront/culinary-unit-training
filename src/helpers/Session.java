package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Session {
	private Properties currentSession;

	public Session() {
		currentSession = new Properties();
		InputStream is = null;
		
		File file = new File("session.properties");
		try {
			is = new FileInputStream(file);
			currentSession.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String key) {
		return currentSession.getProperty(key);
	}
	
	public void set(String key, String value) {
		currentSession.setProperty(key, value);
	}
	
	public void save() throws IOException {
		currentSession.store(new FileWriter("session.properties"), "");
	}
}
