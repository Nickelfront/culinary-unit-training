package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import helpers.ImageLoader;
import helpers.Session;
import helpers.ThemeLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SideMenuController implements Initializable {

	@FXML
	ImageView userImageHolder;
	@FXML
	Label userName;
	@FXML
	JFXButton homeBtn;
	@FXML
	JFXButton clientsBtn;
	@FXML
	JFXButton coursesBtn;
	@FXML
	JFXButton mentorsBtn;
	@FXML
	JFXButton exitBtn;
	@FXML
	ImageView userBg;
	@FXML
	JFXButton settingsBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Session session = new Session();
		
		userName.setText(session.get("loggedUser"));
		userName.setStyle("-fx-stroke: #000");
		userName.setStyle("-fx-stroke-width: 2px");

		String userPicturePath = session.get("userPicture");
		Image userPicture = ImageLoader.getInstance().loadImage(userPicturePath);
		userImageHolder.setImage(userPicture);

		ThemeLoader themeLoader = new ThemeLoader();
		String userBgPath = "default-bg-" + themeLoader.get("current") + ".png";
		Image userBgPicture = ImageLoader.getInstance().loadImage(userBgPath);
		userBg.setImage(userBgPicture);
		
	}

	@FXML
	public void openHome() throws IOException {
		SceneLoader sceneLoader = SceneLoader.getInstance();

		sceneLoader.switchScene("Home");
		sceneLoader.setContentLabel("");
	}

	@FXML
	public void openClients() {
		SceneLoader sceneLoader = SceneLoader.getInstance();

		sceneLoader.switchScene("Clients");
		sceneLoader.setContentLabel("Клиенти");
	}

	@FXML
	public void openCourses() {
		SceneLoader sceneLoader = SceneLoader.getInstance();

		sceneLoader.switchScene("Courses");
		sceneLoader.setContentLabel("Курсове");
	}

	@FXML
	public void openMentors() {
		SceneLoader sceneLoader = SceneLoader.getInstance();

		sceneLoader.switchScene("Mentors");
		sceneLoader.setContentLabel("Ментори");
	}

	@FXML
	public void openSettings() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Settings.fxml"));
		Parent settingsRoot;
		try {
			settingsRoot = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Image icon = ImageLoader.getInstance().loadImage("cooking.png");
			stage.getIcons().add(icon);
			stage.setTitle("Настройки");
			stage.setResizable(false);
			stage.setScene(new Scene(settingsRoot));
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

	@FXML
	public void exitApplication() {
		ButtonType yes = new ButtonType("Изход", ButtonData.OK_DONE);
		ButtonType no = new ButtonType("Отказ", ButtonData.CANCEL_CLOSE);

		Alert alert = new Alert(AlertType.CONFIRMATION, "Сигурни ли сте, че искате да излезете?", yes, no);
		alert.setTitle("Изход");
		alert.showAndWait();
		if (alert.getResult().equals(yes)) {
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}

}
