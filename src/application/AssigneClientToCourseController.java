/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entity.Client;
import entity.Course;
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
public class AssigneClientToCourseController implements Initializable {

    private Client client;
    @FXML
    private Label username;
    @FXML
    private ComboBox<Course> coursesComboBox;
    @FXML
    private Label price;

    public AssigneClientToCourseController(Client cl) {
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
            client.attach("course",
                    coursesComboBox.getSelectionModel().getSelectedItem().getCourseId());
            Stage currentStage = (Stage) coursesComboBox.getScene().getWindow();
            currentStage.close();
        } catch (Exception ex) {
            Logger.getLogger(AssigneClientToCourseController.class.getName()).log(Level.SEVERE, null, ex);
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
