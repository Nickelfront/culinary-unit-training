package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginFormController{

	@FXML
	TextField usernameInput;
	@FXML
	PasswordField passwordInput;
	@FXML
	Button loginButton;


	@FXML
	public void onEnter(ActionEvent e) {
		loginAttempt();
	}
	
	@FXML
	public void loginAttempt() {
		String username = usernameInput.getText();
		String password = passwordInput.getText();

		//if the login entries are correct
		if (username.equals("admin") && password.equals("123")) {
		    Stage stage = (Stage) loginButton.getScene().getWindow();
		    stage.close();
		    
		    //start the application
		    MainApp app = new MainApp();
		    Stage mainAppStage = new Stage();
		    mainAppStage.setTitle("������������");
		    app.start(mainAppStage);
		    
		    //write a properties file with user information that will be shown in the app
		    Properties session = new Properties();
		    session.put("loggedUser", username);
		    session.put("userPicture", username + ".png");
		    File file = new File("session.properties");
		    try {
		    	OutputStream out = new FileOutputStream(file);
	            session.store(out, null);
	        } catch (FileNotFoundException e) {
				e.printStackTrace();	// show where it all went wrong 
				
				// display a message to the end user
				Alert alert = new Alert(AlertType.ERROR, "�������� ������� ��� ���������.");
				alert.setTitle("������");
			} catch (IOException e) {
				e.printStackTrace();

				Alert alert = new Alert(AlertType.ERROR, "�������� ������� ��� ���������.");
				alert.setTitle("������");
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "������ ��� ������ �����!");
			alert.setTitle("������");

			alert.showAndWait();
		}
	}
}
