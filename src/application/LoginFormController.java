package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
		//remove case sensitivity
		String username = usernameInput.getText().toLowerCase();
		String password = passwordInput.getText().toLowerCase();

		//if the login entries are correct
		if (username.equals("admin") && password.equals("123")) {
		    Stage stage = (Stage) loginButton.getScene().getWindow();
		    stage.close();
		    
		    //start the application
		    MainApp app = new MainApp();
		    Stage mainAppStage = new Stage();
		    app.start(mainAppStage);
		    
		    //write a properties file with user information that will be shown in the app
		    Properties session = new Properties();
		    session.put("loggedUser", username);
		    session.put("userPicture", username + "-icon.png");
//		    session.put("userBackground", username + "-bg.png");
		    
		    File file = new File("session.properties");
		    try {
		    	OutputStream out = new FileOutputStream(file);
	            session.store(out, null);
	        } catch (Exception e) {
				e.printStackTrace();	// show where it all went wrong 
				
				// display a message to the end user
				Alert alert = new Alert(AlertType.ERROR, "Възникна грешка при влизане.");
				alert.setTitle("Грешка");
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Въведени са неправилни данни!");
			alert.setTitle("Неправилни данни");

			alert.showAndWait();
		}
	}
}
