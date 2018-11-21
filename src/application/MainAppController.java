package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainAppController implements Initializable {

	@FXML
    private JFXDrawer sideMenu;

    @FXML
    private JFXHamburger showMenuHamburger;
    
    @FXML
    private AnchorPane appContainer;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	VBox box;
		try {
			box = FXMLLoader.load(getClass().getResource("SideMenu.fxml"));
			sideMenu.setMaxWidth(box.getWidth());
			sideMenu.setSidePane(box);
			
			AnchorPane sidePanel = (AnchorPane) box.getChildren().get(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		SceneLoader.switchScene("Home", appContainer);
    }

    @FXML
    private void hamburgerHover(MouseEvent event) {    }
    
    @FXML
    private void hamburgerClick(MouseEvent event) {
    	sideMenu.toggle();
    }
    
    
}
