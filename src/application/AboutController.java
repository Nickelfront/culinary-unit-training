package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutController implements Initializable {

	@FXML
	AnchorPane aboutAnchorPane;
	@FXML
	JFXButton btnClose;
	@FXML
	Hyperlink linkIcon;
	@FXML
	Hyperlink freepikLink;
	@FXML
	Hyperlink flaticonLink;
	@FXML
	Hyperlink jfxLink;
	@FXML
	Hyperlink gitHubLink;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	public void closeAboutWindow() {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onClickFirstLink() {
		new MainApp().getHostServices().showDocument("https://www.freepik.com/");
	}

	@FXML
	public void onClickSecondLink() {
		new MainApp().getHostServices().showDocument("https://www.flaticon.com/");
	}

	@FXML
	public void onClickThirdLink() {
		new MainApp().getHostServices().showDocument("http://www.jfoenix.com");
	}
	
	@FXML
	public void onClickGitLink() {
		new MainApp().getHostServices().showDocument("https://github.com/Nickelfront/culinary-unit-training");
	}
}
