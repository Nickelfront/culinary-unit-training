package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

	private double xOffset = 0;
    private double yOffset = 0;
	@Override
	public void start(Stage stage) {
		AnchorPane root;
		
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("MainApp.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			HBox draggableArea = (HBox) root.getChildren().get(0);
			draggableArea.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
			
			draggableArea.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                stage.setX(event.getScreenX() - xOffset);
	                stage.setY(event.getScreenY() - yOffset);
	            }
	        });
			stage.setScene(scene);
			stage.setTitle("Кулинарни курсове - организатор");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
