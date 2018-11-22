package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTreeTableView;

import entity.Client;
import entity.Mentor;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class MentorsController implements Initializable {

	@FXML TableView<Mentor> mentorsTable;
	
	@FXML TableColumn<Mentor, Integer> idColumn;
	@FXML TableColumn<Mentor, String> firstNameColumn;
	@FXML TableColumn<Mentor, String> lastNameColumn;
	@FXML TableColumn<Mentor, String> phoneColumn;
	@FXML TableColumn<Mentor, String> emailColumn;
	@FXML TableColumn<Mentor, Double> salaryColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
		
		Mentor exampleMentor = new Mentor("Огнян", "Христов", "oghristov@gmail.com", "0895493584", 1000);
		mentorsTable.getItems().add(exampleMentor);
	}

}
