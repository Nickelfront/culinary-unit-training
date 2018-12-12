/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import entity.Base;
import entity.Client;
import entity.Course;
import helpers.ImageLoader;
import helpers.TableFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author bozhidar
 */
public class AssignedCoursesController implements Initializable {

    @FXML
    private TableColumn<Course, String> infoColumn;
    @FXML
    private TableColumn<Course, Integer> idColumn;
    @FXML
    private TableColumn<Course, String> nameColumn;
    @FXML
    private TableColumn<Course, Date> startColumn;
    @FXML
    private TableColumn<Course, Date> endColumn;
    @FXML
    private TableColumn<Course, Double> priceColumn;
    @FXML
    private Label totalPrice;
    @FXML
    private TableView<Course> assignedTable;

    private Client client;

    public AssignedCoursesController(Client cl) {
        this.client = cl;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableFactory.configureRow(idColumn, "courseId");
        TableFactory.configureRow(nameColumn, "title");
        TableFactory.configureRow(startColumn, "startDate");
        TableFactory.configureRow(endColumn, "endDate");
        TableFactory.configureRow(priceColumn, "price");
        TableFactory.configureRow(infoColumn, "description");

        List<Base> assignedCourses = client.courses();

        TableFactory.fill(assignedTable, assignedCourses);

        totalPrice.setText(calculateTotal(assignedCourses));
    }

    private String calculateTotal(List assignedCourses) {
        double result = 0;
        for (Object assignedCourse : assignedCourses) {
            result += ((Course) assignedCourse).getPrice();
        }
        return result + "";
    }
}
