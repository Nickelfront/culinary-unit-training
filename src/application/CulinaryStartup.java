package application;

import helpers.ImageLoader;
import helpers.StyleSheetLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CulinaryStartup extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		StyleSheetLoader styleSheetLoader = new StyleSheetLoader();
		styleSheetLoader.setContext(scene);
		styleSheetLoader.loadStyleSheet("application");
		
		Image icon = ImageLoader.getInstance().loadImage("cooking.png");
//
        primaryStage.getIcons().add(icon);
//
		primaryStage.setTitle("Вход");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
