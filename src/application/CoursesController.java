package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import entity.Course;
import helpers.TableFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

public class CoursesController implements Initializable {

	@FXML TableView<Course> coursesTable;

	@FXML TableColumn<Course, Integer> idColumn;
	@FXML TableColumn<Course, String> titleColumn;
	@FXML TableColumn<Course, String> descriptionColumn;
	@FXML TableColumn<Course, Date> startDateColumn;
	@FXML TableColumn<Course, Date> endDateColumn;
	@FXML TableColumn<Course, Integer> availableSpotsColumn;
	@FXML TableColumn<Course, Double> priceColumn;

	@FXML TextField courseTitle;

	@FXML TextArea courseDescription;

	@FXML DatePicker courseEndDate;
	
	@FXML DatePicker courseStartDate;

	@FXML TextField courseMaxAvailableSpots;

	@FXML TextField coursePrice;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableFactory.configureRow(idColumn, "courseId");
		TableFactory.configureRow(titleColumn, "title");
		TableFactory.configureRow(descriptionColumn, "description");	
		TableFactory.configureRow(startDateColumn, "startDate");
		TableFactory.configureRow(endDateColumn, "endDate");
		TableFactory.configureRow(availableSpotsColumn, "availableSpots");
		TableFactory.configureRow(priceColumn, "price");
		
		Course exampleCourse = new Course();
		exampleCourse.setCourseId(0);
		exampleCourse.setTitle("Морски дарове");
		exampleCourse.setDescription("Lorem ipsum");
		exampleCourse.setStartDate(new Date(1542902580000l));
		exampleCourse.setEndDate(new Date(1543680180000l));
		exampleCourse.setAvailableSpots(20);
		exampleCourse.setPrice(100);
		
		coursesTable.getItems().add(exampleCourse);
	}


	@FXML 
	public void handleAddCourses() {
		Course course = new Course();

		if (!validateDates(courseStartDate.getValue(), courseEndDate.getValue())) {
			Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setContentText("Началната дата трябва да е поне ден преди крайната.");
			al.show();
			return;
		}
		
		Date startDate = Date.from(Instant.from(courseStartDate.getValue().atStartOfDay(ZoneId.systemDefault())));
		Date endDate = Date.from(Instant.from(courseEndDate.getValue().atStartOfDay(ZoneId.systemDefault())));
		
		course.setCourseId(incrementID());
		course.setTitle(courseTitle.getText());
		course.setDescription(courseDescription.getText());
		course.setStartDate(startDate);
		course.setEndDate(endDate);
		course.setAvailableSpots(Integer.parseInt(courseMaxAvailableSpots.getText()));
		course.setPrice(Double.parseDouble(coursePrice.getText()));
		
		coursesTable.getItems().add(course);
	}

	@FXML 
	public void handleClearForm() {
		courseTitle.setText("");
		courseDescription.setText("");
		courseMaxAvailableSpots.setText("");
		coursePrice.setText("");
	}
	
	private int incrementID() {
		int lastItemIndex = coursesTable.getItems().size() - 1;
		int lastItemID = coursesTable.getItems().get(lastItemIndex).getCourseId();

		return ++lastItemID;
	}
	
	private boolean validateDates(LocalDate startDate, LocalDate endDate) {
		return startDate.compareTo(endDate) == 1;
	}
}
