package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import entity.Client;
import helpers.TableFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class ClientsController implements Initializable {

	@FXML
	TableView<Client> clientsTable;

	@FXML
	TableColumn<Client, Integer> idColumn;
	@FXML
	TableColumn<Client, String> firstNameColumn;
	@FXML
	TableColumn<Client, String> lastNameColumn;
	@FXML
	TableColumn<Client, Integer> ageColumn;
	@FXML
	TableColumn<Client, String> phoneColumn;
	@FXML
	TableColumn<Client, String> emailColumn;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField age;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableFactory.configureRow(idColumn, "clientId");
		TableFactory.configureRow(firstNameColumn, "firstName");
		TableFactory.configureRow(lastNameColumn, "lastName");
		TableFactory.configureRow(ageColumn, "age");
		TableFactory.configureRow(phoneColumn, "phone");
		TableFactory.configureRow(emailColumn, "email");

		Client exampleClient = new Client(0, "Иван", "Петров", 26, "ipetrov@gmail.com", "0874586125");
		clientsTable.getItems().add(exampleClient);

	}

	@FXML
	private void handleAddClient(ActionEvent event) {
		try {
			int clientAge = Integer.parseInt(this.age.getText());
			String clientEmail = email.getText();
			String clientPhoneNumber = phone.getText();
		
			if (!validateEmail(clientEmail)) {
				Alert al = new Alert(Alert.AlertType.INFORMATION);
				al.setContentText("Невалиден e-Mail!");
				al.show();
				return;
			}
			if (!validateAge(clientAge)) {
				Alert al = new Alert(Alert.AlertType.INFORMATION);
				al.setContentText("Клиентът трябва да е лице на възраст над 16 години!");
				al.show();
				return;
			}
			if (!validatePhoneNumber(clientPhoneNumber)) {
				Alert al = new Alert(Alert.AlertType.INFORMATION);
				al.setContentText("Невалиден телефонен номер.");
				al.show();
				return;
			}
			
			Client client = new Client(incrementID(), firstName.getText(), lastName.getText(), clientAge,
					email.getText(), phone.getText());

			clientsTable.getItems().add(client);

			firstName.setText("");
			lastName.setText("");
			age.setText("");
			email.setText("");
			phone.setText("");
		} catch (NumberFormatException e) {
			Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setContentText("Възрастта трябва да е число!");
			age.setText("");
			al.show(); // TODO: Dont enter the age manually, but select date of birth
		}
	}

	@FXML
	private void handleClearForm(ActionEvent event) {
		firstName.setText("");
		lastName.setText("");
		age.setText("");
		email.setText("");
		phone.setText("");
	}


	private int incrementID() {
		int lastItemIndex = clientsTable.getItems().size() - 1;
		int lastItemID = clientsTable.getItems().get(lastItemIndex).getClientId();

		return ++lastItemID;
	}

	private boolean validatePhoneNumber(String phoneNumber) {
		String regex = "0[\\d]{9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		return matcher.matches();
	}
	
	private boolean validateAge(int age) {
		return age > 16;
	}
	
	private boolean validateEmail(String email) {
		String regex = "[\\w\\d\\-]+@[\\w\\d]+\\.[\\w]{2,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
}
