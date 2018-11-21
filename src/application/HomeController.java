package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class HomeController implements Initializable {

	@FXML Hyperlink aboutHyperlink;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
    private void aboutOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent aboutRoot;
		try {
			aboutRoot = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Относно");
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
