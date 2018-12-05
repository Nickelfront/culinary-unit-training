package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entity.Base;
import entity.Client;
import entity.Course;
import helpers.TableFactory;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;

public class AssignedClientsToCourseController implements Initializable {

	@FXML
	TableView<Client> assignedTable;
	@FXML
	TableColumn<Client, Integer> idColumn;
	@FXML
	TableColumn<Client, String> firstNameColumn;
	@FXML
	TableColumn<Client, String> lastNameColumn;
	@FXML
	TableColumn<Client, String> birthDateColumn;
	@FXML
	TableColumn<Client, String> phoneColumn;
	@FXML
	TableColumn<Client, String> emailColumn;
	@FXML
	Label totalClients;

	private Course course;

	public AssignedClientsToCourseController(Course course) {
		this.course = course;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableFactory.configureRow(idColumn, "clientId");
		TableFactory.configureRow(firstNameColumn, "firstName");
		TableFactory.configureRow(lastNameColumn, "lastName");
		TableFactory.configureRow(birthDateColumn, "birthDate");
		TableFactory.configureRow(phoneColumn, "phone");
		TableFactory.configureRow(emailColumn, "email");
		
		List<Base> assignedClients = course.clients();
		
		TableFactory.fill(assignedTable, assignedClients);
		
		totalClients.setText(countAllClients(assignedClients) + "");
	
	}
	
	private int countAllClients(List assignedClients) {
		return assignedClients.size();
	}

}
