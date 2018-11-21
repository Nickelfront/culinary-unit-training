package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutController implements Initializable {

	@FXML
	AnchorPane aboutAnchorPane;
	@FXML
	JFXButton btnClose;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	@FXML
	public void closeAboutWindow() {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
}
