package application;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;
import entity.Base;

import entity.Course;
import helpers.MessageDisplay;
import helpers.TableFactory;
import helpers.Validator;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CoursesController implements Initializable {

    @FXML
    TableView<Course> coursesTable;

    @FXML
    TableColumn<Course, Integer> idColumn;
    @FXML
    TableColumn<Course, String> titleColumn;
    @FXML
    TableColumn<Course, String> descriptionColumn;
    @FXML
    TableColumn<Course, Date> startDateColumn;
    @FXML
    TableColumn<Course, Date> endDateColumn;
    @FXML
    TableColumn<Course, Integer> availableSpotsColumn;
    @FXML
    TableColumn<Course, Double> priceColumn;

    @FXML
    TextField courseTitle;

    @FXML
    TextArea courseDescription;

    @FXML
    DatePicker courseEndDate;

    @FXML
    DatePicker courseStartDate;

    @FXML
    TextField courseMaxAvailableSpots;

    @FXML
    TextField coursePrice;

    @FXML
    MenuItem deleteRightClick;

    @FXML
    TextField searchedCourseTitle;

    @FXML
    Button searchByTitleBtn;

    @FXML
    Button searchByPeriodBtn;

    @FXML
    Button searchByPriceBtn;

    @FXML
    JFXSlider minSelectedPrice;

    @FXML
    JFXSlider maxSelectedPrice;

    @FXML
    DatePicker fromDate;

    @FXML
    DatePicker toDate;

    private List<Course> foundCourses;

    @FXML
    Button prevBtn;

    @FXML
    Button nextBtn;

    @FXML
    Label resultCount;

    @FXML
    Label currentResult;

    @FXML
    Label foundCoursePrice;

    @FXML
    Label foundCourseMaxSpots;

    @FXML
    Label foundCourseEndDate;

    @FXML
    Label foundCourseStartDate;

    @FXML
    Label foundCourseTitle;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableFactory.configureRow(idColumn, "courseId");
        TableFactory.configureRow(titleColumn, "title");
        TableFactory.configureRow(descriptionColumn, "description");
        TableFactory.configureRow(startDateColumn, "startDate");
        TableFactory.configureRow(endDateColumn, "endDate");
        TableFactory.configureRow(availableSpotsColumn, "availableSpots");
        TableFactory.configureRow(priceColumn, "price");

        TableFactory.fill(coursesTable, new Course().all());

        coursesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    deleteRightClick.setDisable(tableIsEmpty());
                }
            }
        });
    }

    @FXML
    public void handleAddCourses() {
        Course course = new Course();

        if (!Validator.validateDates(courseStartDate.getValue(), courseEndDate.getValue())) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Невалиден период от време.");
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

        coursesTable.getItems().add(course); //TODO: if possible, find a way to show a readable date in the table view as well
        course.save();
        handleClearForm();
    }

    @FXML
    public void handleClearForm() {
        courseTitle.setText("");
        courseDescription.setText("");
        courseStartDate.setValue(null);
        courseEndDate.setValue(null);
        courseMaxAvailableSpots.setText("");
        coursePrice.setText("");
    }

    private int incrementID() {
        int lastItemID = 0;
        
        if (!coursesTable.getItems().isEmpty()) {
            int lastItemIndex = coursesTable.getItems().size() - 1;
            lastItemID = coursesTable.getItems().get(lastItemIndex).getCourseId();
        }
        
        return ++lastItemID;
    }

    @FXML
    public void deleteCourse() {

        Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem();
        String courseName = selectedCourse.getTitle();

        ButtonType delete = new ButtonType("Изтрий", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Отказ", ButtonData.CANCEL_CLOSE);

        Alert al = new Alert(Alert.AlertType.CONFIRMATION,
                "Сигурни ли сте, че искате да изтриете следния курс: \n\t" + courseName, delete, cancel);
        al.showAndWait();
        if (al.getResult().equals(delete)) {
            coursesTable.getItems().remove(selectedCourse);
            selectedCourse.delete();
        }
    }

    public boolean tableIsEmpty() {
        return coursesTable.getItems().isEmpty();
    }

    @FXML
    public void searchByTitle() {
        clearResults();
        String title = searchedCourseTitle.getText();
        ObservableList<Course> coursesList = coursesTable.getItems();

        foundCourses = new ArrayList<Course>();

        for (Course course : coursesList) {
            if (!title.isEmpty() && course.getTitle().toLowerCase().contains(title)) {
                foundCourses.add(course);
            }
        }
        if (foundCourses.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }
        if (foundCourses.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundCourses.size() + "");
        displayResult(0);
    }

    @FXML
    public void searchByPeriod() {
        clearResults();
        Date from = Date.from(Instant.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault())));
        Date to = Date.from(Instant.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault())));

        ObservableList<Course> coursesList = coursesTable.getItems();
        foundCourses = new ArrayList<Course>();

        for (Course course : coursesList) {
            if (from.compareTo(course.getParsedStartDate()) > -1 && to.compareTo(course.getParsedEndDate()) < 1) {
                foundCourses.add(course);
            }
        }
        if (foundCourses.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }
        if (foundCourses.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundCourses.size() + "");
        displayResult(0);
    }

    @FXML
    public void searchByPrice() {
        clearResults();
        double minPrice = minSelectedPrice.getValue();
        double maxPrice = maxSelectedPrice.getValue();

        ObservableList<Course> coursesList = coursesTable.getItems();
        foundCourses = new ArrayList<Course>();

        for (Course course : coursesList) {
            if (course.getPrice() <= maxPrice && course.getPrice() >= minPrice) {
                foundCourses.add(course);
            }
        }

        if (foundCourses.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundCourses.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundCourses.size() + "");
        displayResult(0);

    }

    @FXML
    public void showPreviousResult() {
        clearResults();
        int foundCoursesCount = foundCourses.size();
        int currentIndex = Integer.parseInt(currentResult.getText()) - 1;
        int prevIndex;

        if (currentIndex > 0) {
            prevIndex = --currentIndex;
        } else {
            prevIndex = foundCoursesCount - 1;
        }

        displayResult(prevIndex);
        currentResult.setText(prevIndex + 1 + "");
    }

    @FXML
    public void showNextResult() {
        clearResults();
        int foundCoursesCount = foundCourses.size();
        int currentIndex = Integer.parseInt(currentResult.getText()) - 1;
        int nextIndex;

        if (currentIndex < foundCoursesCount - 1) {
            nextIndex = currentIndex + 1;
        } else {
            nextIndex = 0;
        }

        displayResult(nextIndex);
        currentResult.setText(nextIndex + 1 + "");
    }

    private void displayResult(int index) {
        Course courseResult = foundCourses.get(index);
        foundCourseTitle.setText(foundCourseTitle.getText() + "\t" + courseResult.getTitle());
        foundCourseStartDate.setText(foundCourseStartDate.getText() + "\t" + courseResult.getStartDate());
        foundCourseEndDate.setText(foundCourseEndDate.getText() + "\t" + courseResult.getEndDate());
        foundCourseMaxSpots.setText(foundCourseMaxSpots.getText() + "\t" + courseResult.getAvailableSpots());
        foundCoursePrice.setText(foundCoursePrice.getText() + "\t" + courseResult.getPrice());
    }

    private void enableNavigation() {
        prevBtn.setDisable(false);
        nextBtn.setDisable(false);
    }

    private void clearResults() {
        prevBtn.setDisable(true);
        nextBtn.setDisable(true);

        foundCourseTitle.setText("Име на курса: ");
        foundCourseStartDate.setText("Начална дата: ");
        foundCourseEndDate.setText("Крайна дата: ");
        foundCourseMaxSpots.setText("Максимален брой места: ");
        foundCoursePrice.setText("Цена: ");
    }

}
