package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAppController implements Initializable {

	@FXML
    private JFXDrawer sideMenu;

    @FXML
    private JFXHamburger showMenuHamburger;
    
    @FXML
    private AnchorPane appContainer;

	@FXML JFXDrawer draggableArea;
	
	private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	VBox box;
		try {
			box = FXMLLoader.load(getClass().getResource("SideMenu.fxml"));
			sideMenu.setMaxWidth(box.getWidth());
			sideMenu.setSidePane(box);
			System.out.println(box.getParent().getParent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		SceneLoader sceneLoader = SceneLoader.getInstance();
		sceneLoader.setContext(appContainer);
		sceneLoader.switchScene("Home");
		
//		draggableArea.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                xOffset = event.getSceneX() + 10;
//                yOffset = event.getSceneY() + 10;
//            }
//        });
//		
//		draggableArea.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
////                stage.setX(event.getScreenX() - xOffset);
////                stage.setY(event.getScreenY() - yOffset);
//            }
//        });
    }

    @FXML
    private void hamburgerHover(MouseEvent event) {    }
    
    @FXML
    private void hamburgerClick(MouseEvent event) {
    	sideMenu.toggle();
    }
    
    
}
