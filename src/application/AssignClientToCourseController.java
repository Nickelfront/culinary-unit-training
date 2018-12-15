/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entity.Client;
import entity.Course;
import helpers.MessageDisplay;
import helpers.TableFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AssignClientToCourseController implements Initializable {

    private Client client;
    @FXML
    private Label username;
    @FXML
    private ComboBox<Course> coursesComboBox;
    @FXML
    private Label price;

    public AssignClientToCourseController(Client cl) {
        client = cl;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(client.getFirstName() + " " + client.getLastName());
        TableFactory.fillComboBox(coursesComboBox, new Course().all());
        price.setText("");
    }

    @FXML
    private void registerClient(ActionEvent event) {
        try {
            Stage currentStage = (Stage) coursesComboBox.getScene().getWindow();

            Course selectedCourse = coursesComboBox.getSelectionModel()
                    .getSelectedItem();
            if (selectedCourse.getAvailableSpots() == 0) {
                MessageDisplay.info("Курсът е запълнен!");
                currentStage.close();
                return;
            }
            
            String response = client.attach("course", selectedCourse.getCourseId());
            
            if(response.equals("19")){
                MessageDisplay.info("Клиентът вече е записан!");
                return;
            }
            
            selectedCourse.setAvailableSpots(
                    selectedCourse.getAvailableSpots()-1
            );
            selectedCourse.update();
            currentStage.close();
        } catch (Exception ex) {
            Logger.getLogger(AssignClientToCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changedCourse(ActionEvent event) {
        Course selectedCourse = coursesComboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            price.setText(selectedCourse.getPrice() + "");
        }
    }

}
