/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Course;
import entity.Mentor;
import helpers.MessageDisplay;
import helpers.TableFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bozhidar
 */
public class AssignMentorToCourseController implements Initializable {
    
    private final Mentor mentor;
    @FXML
    private Label mentorName;
    @FXML
    private ComboBox<Course> coursesComboBox;
    
    public AssignMentorToCourseController(Mentor mr) {
        mentor = mr;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mentorName.setText(mentor.getFirstName() + " " + mentor.getLastName());
        TableFactory.fillComboBox(coursesComboBox, new Course().all());
    }    

    @FXML
    private void handleAttachToCourse(ActionEvent event) {
         try {
            Stage currentStage = (Stage) coursesComboBox.getScene().getWindow();

            Course selectedCourse = coursesComboBox.getSelectionModel()
                    .getSelectedItem();
            
            String response = mentor.attach("course", selectedCourse.getCourseId());
            
            if(response.equals("19")){
                MessageDisplay.info("Менторът вече е записан!");
                return;
            }
            
            currentStage.close();
        } catch (Exception ex) {
            Logger.getLogger(AssignClientToCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
