package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import helpers.ImageLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Properties session = new Properties();
		InputStream is = null;
		try {
			File file = new File("session.properties");
			is = new FileInputStream(file);
			session.load(is);
		} catch (Exception e) {
			is = null;
		}

		userName.setText(session.getProperty("loggedUser"));
		userName.setStyle("-fx-stroke: #000");
		userName.setStyle("-fx-stroke-width: 2px");
		
		String userPicturePath = session.getProperty("userPicture");
		Image userPicture = ImageLoader.getInstance().loadImage(userPicturePath);
		userImageHolder.setImage(userPicture);
		System.out.println(userPicturePath);
		
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
