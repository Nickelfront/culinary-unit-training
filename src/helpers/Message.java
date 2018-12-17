package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Message {

	public static void displayInfo(String message) {
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(message);
		al.setTitle("Информация");
		al.setHeaderText(null);
		Stage stage = (Stage) al.getDialogPane().getScene().getWindow();
		ImageLoader.getInstance().setIcon("cooking.png", stage);
		al.show();
	}
	
	public static void displayError(String message) {
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText(message);
		al.setTitle("Грешка");
		al.setHeaderText(null);
		Stage stage = (Stage) al.getDialogPane().getScene().getWindow();
		ImageLoader.getInstance().setIcon("cooking.png", stage);
		al.showAndWait();
	}
	
	public static ButtonType prompt(String question, ButtonType[] buttons) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION, question, buttons);
		al.setHeaderText(null);
		Stage stage = (Stage) al.getDialogPane().getScene().getWindow();
		ImageLoader.getInstance().setIcon("cooking.png", stage);
		al.showAndWait();
		return al.getResult();
	}
}
