/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entity.Base;
import entity.Course;
import entity.Mentor;
import helpers.TableFactory;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author bozhidar
 */
public class MentorCoursesController implements Initializable {
    private final Mentor mentor;
    
    @FXML
    private TableColumn<Course, Integer> idColumn;
    @FXML
    private TableColumn<Course, String> titleColumn;
    @FXML
    private TableColumn<Course, Date> startDateColumn;
    @FXML
    private TableColumn<Course, Date> endDateColumn;
    @FXML
    private TableView<Course> coursesTable;
    
    public MentorCoursesController(Mentor mr) {
        mentor = mr;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableFactory.configureRow(idColumn, "courseId");
        TableFactory.configureRow(titleColumn, "title");
        TableFactory.configureRow(startDateColumn, "startDate");
        TableFactory.configureRow(endDateColumn, "endDate");
        
        TableFactory.fill(coursesTable, mentor.courses());
    }    
    
}
