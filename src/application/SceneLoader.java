package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SceneLoader {
	private static SceneLoader inst = null;
	
	private AnchorPane appContainer;
	
	private SceneLoader() {
		
	}
	
	public static SceneLoader getInstance() {
		if (inst == null) {
			inst = new SceneLoader();
		}
		return inst;
	}
	
	public void setCtx(AnchorPane appContainer) {
		this.appContainer = appContainer;
	}
	
	public void switchScene(String fxmlFile) {
		fxmlFile += ".fxml";

		try {
			System.out.println(fxmlFile);
			AnchorPane loadedPane = FXMLLoader.load(getClass().getResource(fxmlFile));
			appContainer.getChildren().add(loadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
