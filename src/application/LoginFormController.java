package application;

import helpers.Session;
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
		    Session session = new Session();
		    session.set("loggedUser", username);
		    session.set("userPicture", username + "-icon.png");
		    
		    try {
		    	session.save();
	        } catch (Exception e) {
				e.printStackTrace();
				// display a message to the end user
				Alert alert = new Alert(AlertType.ERROR, "Възникна грешка при влизане.");
				alert.setTitle("Грешка");
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Въведени са неправилни данни!");
			alert.setTitle("Неуспешно влизане");

			alert.showAndWait();
		}
	}
}
