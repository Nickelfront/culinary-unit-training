package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTreeTableView;

import entity.Client;
import entity.Mentor;
import helpers.TableFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class MentorsController implements Initializable {

	@FXML TableView<Mentor> mentorsTable;
	
	@FXML TableColumn<Mentor, Integer> idColumn;
	@FXML TableColumn<Mentor, String> firstNameColumn;
	@FXML TableColumn<Mentor, String> lastNameColumn;
	@FXML TableColumn<Mentor, String> phoneColumn;
	@FXML TableColumn<Mentor, String> emailColumn;
	@FXML TableColumn<Mentor, Double> salaryColumn;

	@FXML TextField lastName;

	@FXML TextField firstName;

	@FXML TextField phone;

	@FXML TextField email;

	@FXML TextField salary;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableFactory.configureRow(idColumn, "mentorId");
		TableFactory.configureRow(firstNameColumn, "firstName");
		TableFactory.configureRow(lastNameColumn, "lastName");
		TableFactory.configureRow(phoneColumn, "phone");
		TableFactory.configureRow(emailColumn, "email");
		TableFactory.configureRow(salaryColumn, "salary");
		
		Mentor exampleMentor = new Mentor(0, "Огнян", "Христов", "0895493584", "oghristov@gmail.com", 1000);
		mentorsTable.getItems().add(exampleMentor);
		
	}
	
	@FXML
	private void handleAddMentor(ActionEvent event) {
		try {
			String mentorEmail = emailColumn.getText();
			String mentorPhoneNumber = phoneColumn.getText();
		
			if (!validateEmail(mentorEmail)) {
				Alert al = new Alert(Alert.AlertType.INFORMATION);
				al.setContentText("Невалиден e-Mail!");
				al.show();
				return;
			}
			if (!validatePhoneNumber(mentorPhoneNumber)) {
				Alert al = new Alert(Alert.AlertType.INFORMATION);
				al.setContentText("Невалиден телефонен номер.");
				al.show();
				return;
			}
			
			Mentor mentor = new Mentor(incrementID(), firstName.getText(), lastName.getText(),
					email.getText(), phone.getText(), Double.parseDouble(salary.getText()));
			
			mentorsTable.getItems().add(mentor);

			firstName.setText("");
			lastName.setText("");
			email.setText("");
			phone.setText("");
			salary.setText("");
		}
		catch (Exception e) {
		}
	}
	
	@FXML 
	public void handleClearForm() {
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		phone.setText("");
		salary.setText("");
	}
	
	private int incrementID() {
		int lastItemIndex = mentorsTable.getItems().size() - 1;
		int lastItemID = mentorsTable.getItems().get(lastItemIndex).getMentorId();

		return ++lastItemID;
	}

	private boolean validatePhoneNumber(String phoneNumber) {
		String regex = "0[\\d]{9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		return matcher.matches();
	}
	
	private boolean validateEmail(String email) {
		String regex = "[\\w\\d\\-]+@[\\w\\d]+\\.[\\w]{2,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}

}