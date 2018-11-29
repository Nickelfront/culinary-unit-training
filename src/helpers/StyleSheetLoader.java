package helpers;

import javafx.scene.Scene;

public class StyleSheetLoader {

	private Scene scene;
	
	public void setContext(Scene scene) {
		this.scene = scene;
	}
	public void loadStyleSheet(String styleSheet) {
		this.scene.getStylesheets().add(getClass().getResource("../application/" + styleSheet + ".css").toExternalForm());
	}
}
