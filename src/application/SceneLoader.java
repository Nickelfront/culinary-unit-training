package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SceneLoader {

	public static SceneLoader getInstance() {
		return new SceneLoader();
	}
	
	private SceneLoader() {}
	
	public static void switchScene(String fxmlFile, AnchorPane appContainer) {
		fxmlFile += ".fxml";

		try {
			System.out.println(fxmlFile);
			AnchorPane loadedPane = FXMLLoader.load(ClassLoader.getSystemResource(fxmlFile));
			appContainer.getChildren().add(loadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
