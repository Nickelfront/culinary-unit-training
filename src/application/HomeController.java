package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class HomeController implements Initializable {

	@FXML Hyperlink aboutHyperlink;
	@FXML Label username;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		Properties session = new Properties();
		InputStream is = null;
		try {
			File file = new File("session.properties");
			is = new FileInputStream(file);
			session.load(is);
		} catch (Exception e) {
			is = null;
		}
		username.setText(session.getProperty("loggedUser"));
	}

	@FXML
    private void aboutOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent aboutRoot;
		try {
			aboutRoot = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Image icon = new Image(getClass().getClassLoader().getResourceAsStream("cooking.png"));
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
