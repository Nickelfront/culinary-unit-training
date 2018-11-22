package application;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import entity.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CoursesController implements Initializable {

	@FXML TableView<Course> coursesTable;

	@FXML TableColumn<Course, Integer> idColumn;
	@FXML TableColumn<Course, String> titleColumn;
	@FXML TableColumn<Course, String> descriptionColumn;
	@FXML TableColumn<Course, Date> startDateColumn;
	@FXML TableColumn<Course, Date> endDateColumn;
	@FXML TableColumn<Course, Integer> availableSpotsColumn;
	@FXML TableColumn<Course, Double> priceColumn;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));		
		startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		availableSpotsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSpots"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		Course exampleCourse = new Course();
		exampleCourse.setTitle("Морски дарове");
		exampleCourse.setDescription("Lorem ipsum");
		exampleCourse.setStartDate(new Date(1542902580000l));
		exampleCourse.setEndDate(new Date(1543680180000l));
		exampleCourse.setAvailableSpots(20);
		exampleCourse.setPrice(100);
		
		coursesTable.getItems().add(exampleCourse);
	}

}
