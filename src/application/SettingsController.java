package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import helpers.ThemeLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsController implements Initializable { // TODO: Create settings for theme

	@FXML
	AnchorPane settingsWindow;
	@FXML
	ComboBox<String> themesList;
	@FXML
	Label currentTheme;
	@FXML
	CheckBox oldSettingsChoice;
	@FXML
	Button saveBtn;
	@FXML
	Button cancelBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> themes = FXCollections.observableArrayList("Orange", "Brown", "Teal", "Green", "Pink", "Purple");
		themesList.getItems().addAll(themes);
		currentTheme.setText(currentTheme.getText() + " " + new ThemeLoader().get("current"));
	}

	@FXML
	public void cancelChoices() {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void saveChoices() {
		ThemeLoader theme = new ThemeLoader();

		String choice = themesList.getSelectionModel().getSelectedItem().toString().toLowerCase();
		
		if (oldSettingsChoice.isSelected()) {
			choice = theme.get("default");
		}
		theme.set("current", choice);
		
		try {
			theme.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(AlertType.INFORMATION, "Промените ще се приложат при следващото стартиране.");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
//			try {
//				new CulinaryStartup().start(new Stage());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			Stage stage = (Stage) saveBtn.getScene().getWindow();
			stage.close();
		}
	}

}
