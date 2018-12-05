package application;

import java.io.IOException;

import DB.BaseDBConnector;
import helpers.ImageLoader;
import helpers.StyleSheetLoader;
import helpers.ThemeLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) {
		AnchorPane root;

		try {

			@SuppressWarnings("unused")
			BaseDBConnector dc = BaseDBConnector.getInstance();
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("MainApp.fxml"));
			Scene scene = new Scene(root);

			StyleSheetLoader styleSheetLoader = new StyleSheetLoader();
			styleSheetLoader.setContext(scene);
			styleSheetLoader.loadStyleSheet("application");
			styleSheetLoader.loadStyleSheet(new ThemeLoader().get("current"));

			Image icon = ImageLoader.getInstance().loadImage("cooking.png");
			stage.getIcons().add(icon);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("UberChef - организатор");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.setOnCloseRequest(event -> {
			ButtonType yes = new ButtonType("Изход", ButtonData.OK_DONE);
			ButtonType no = new ButtonType("Отказ", ButtonData.CANCEL_CLOSE);

			Alert alert = new Alert(AlertType.CONFIRMATION, "Сигурни ли сте, че искате да излезете?", yes, no);
			alert.setTitle("Изход");
			alert.showAndWait();
			if (alert.getResult().equals(yes)) {
				stage.close();
			} else {
				event.consume();
			}
		});
	}
}
