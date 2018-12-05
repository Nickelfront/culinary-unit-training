package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entity.Base;
import entity.Course;
import entity.Mentor;
import helpers.TableFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AssignedMentorsToCourseController implements Initializable {

	@FXML
	TableView<Mentor> assignedTable;
	@FXML
	TableColumn<Mentor, Integer> idColumn;
	@FXML
	TableColumn<Mentor, String> firstNameColumn;
	@FXML
	TableColumn<Mentor, String> lastNameColumn;
	@FXML
	TableColumn<Mentor, String> phoneColumn;
	@FXML
	TableColumn<Mentor, String> emailColumn;
	@FXML
	TableColumn<Mentor, Double> salaryColumn;
	@FXML
	Label totalMentors;

	private Course course;

	public AssignedMentorsToCourseController(Course course) {
		this.course = course;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableFactory.configureRow(idColumn, "mentorId");
		TableFactory.configureRow(firstNameColumn, "firstName");
		TableFactory.configureRow(lastNameColumn, "lastName");
		TableFactory.configureRow(phoneColumn, "phone");
		TableFactory.configureRow(emailColumn, "email");
		TableFactory.configureRow(salaryColumn, "salary");

		List<Base> assignedMentors = course.mentors();

		TableFactory.fill(assignedTable, assignedMentors);

		totalMentors.setText(countAllMentors(assignedMentors) + "");
	}

	private int countAllMentors(List assignedMentors) {
		return assignedMentors.size();
	}
	
}
