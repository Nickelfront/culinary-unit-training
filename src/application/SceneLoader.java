package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SceneLoader {
	private static SceneLoader instance = null;
	
	private AnchorPane appContainer;
	
	private SceneLoader() {
		
	}
	
	public static SceneLoader getInstance() {
		if (instance == null) {
			instance = new SceneLoader();
		}
		return instance;
	}
	
	public void setContext(AnchorPane appContainer) {
		this.appContainer = appContainer;
	}
	
	public void switchScene(String fxmlFile) {
		fxmlFile += ".fxml";

		try {
			System.out.println(fxmlFile);
			AnchorPane loadedPane = FXMLLoader.load(getClass().getResource(fxmlFile));
			
			// clear container of its previous contents before loading the new ones
			appContainer.getChildren().clear();
			appContainer.getChildren().add(loadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
