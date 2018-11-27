package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingsController { //TODO: Create settings for theme

	private final HashMap<String, List<String>> settings = new HashMap<>();
	
	private void fillWithColorOptions() {
	
		List<String> list = new ArrayList<>();
		list.add("");
		settings.put("Orange 50", list);
	}
	
	
	
}
