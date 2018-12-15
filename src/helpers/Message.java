package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Message {

	public static void displayInfo(String message) {
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(message);
		al.show();
	}

	public static ButtonType prompt(String question, ButtonType[] buttons) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION, question, buttons);
		al.showAndWait();
		return al.getResult();
	}
}
