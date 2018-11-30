package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainAppController implements Initializable {

	@FXML
	private JFXDrawer sideMenu;

	@FXML
	private AnchorPane appContainer;

	@FXML
	private AnchorPane labelContainer;
	
	@FXML
	private Label contentLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		VBox box;
		try {
			box = FXMLLoader.load(getClass().getResource("SideMenu.fxml"));
			sideMenu.setMaxWidth(box.getWidth());
			sideMenu.setSidePane(box);
			sideMenu.open();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SceneLoader sceneLoader = SceneLoader.getInstance();
		sceneLoader.setContext(appContainer, contentLabel);
		sceneLoader.switchScene("Home");
		sceneLoader.setContentLabel("");
	}

}
