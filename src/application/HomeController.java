package application;

import helpers.ImageLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import helpers.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeController implements Initializable {

	@FXML Hyperlink aboutHyperlink;
	@FXML Label username;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		username.setText(new Session().get("loggedUser"));
	}

	@FXML
    private void aboutOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent aboutRoot;
		try {
			aboutRoot = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Image icon = ImageLoader.getInstance().loadImage("cooking.png");
			stage.getIcons().add(icon);
			stage.setTitle("Относно");
			stage.setResizable(false);
			stage.setScene(new Scene(aboutRoot));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR, "Възникна грешка.");
			alert.showAndWait();
			if (alert.getResult() != null) {
				System.exit(1);
			}
		}
	}

}
