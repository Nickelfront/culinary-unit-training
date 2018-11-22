package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainAppController implements Initializable {

	@FXML
    private JFXDrawer sideMenu;

    @FXML
    private JFXHamburger showMenuHamburger;
    
    @FXML
    private AnchorPane appContainer;

	@FXML HBox draggableArea;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	VBox box;
		try {
			box = FXMLLoader.load(getClass().getResource("SideMenu.fxml"));
			sideMenu.setMaxWidth(box.getWidth());
			sideMenu.setSidePane(box);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SceneLoader sceneLoader = SceneLoader.getInstance();
		sceneLoader.setContext(appContainer);
		sceneLoader.switchScene("Home");
    }

    @FXML
    private void hamburgerHover(MouseEvent event) {    }
    
    @FXML
    private void hamburgerClick(MouseEvent event) {
    	sideMenu.toggle();
    }
    
    
}
