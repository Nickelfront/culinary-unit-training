package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SceneLoader {
	private static SceneLoader instance = null;
	
	private AnchorPane appContainer;
	private Label contentLabel;
	
	private SceneLoader() {	}
	
	public static SceneLoader getInstance() {
		if (instance == null) {
			instance = new SceneLoader();
		}
		return instance;
	}
	
	public void setContext(AnchorPane appContainer, Label label) {
		this.appContainer = appContainer;
		this.contentLabel = label;
	}
	
	public void switchScene(String fxmlFile) {
		fxmlFile += ".fxml";
		
		try {
			AnchorPane loadedPane = FXMLLoader.load(getClass().getResource(fxmlFile));
			
			// clear container of its previous contents before loading the new ones
			appContainer.getChildren().clear();
			appContainer.getChildren().add(loadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setContentLabel(String labelText) {
		if (labelText.isEmpty()) {
			this.contentLabel.getParent().setVisible(false);
		} else {
			this.contentLabel.getParent().setVisible(true);
			this.contentLabel.setText(labelText);
		}
	}
}
