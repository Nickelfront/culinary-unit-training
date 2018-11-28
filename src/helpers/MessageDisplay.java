package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageDisplay {

	public static void noSearchResults() {
		Alert alert = new Alert(AlertType.INFORMATION, "Няма резултати от търсенето.");
		alert.showAndWait();
	}
}
