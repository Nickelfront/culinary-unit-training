package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTreeTableView;

import entity.Client;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class ClientsController implements Initializable {

	@FXML TableView<Client> clientsTable;
	
	@FXML TableColumn<Client, Integer> idColumn;
	@FXML TableColumn<Client, String> firstNameColumn;
	@FXML TableColumn<Client, String> lastNameColumn;
	@FXML TableColumn<Client, Integer> ageColumn;
	@FXML TableColumn<Client, String> phoneColumn;
	@FXML TableColumn<Client, String> emailColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		Client exampleClient = new Client("Иван", "Петров", 26, "ipetrov@gmail.com", "08745863125");
		clientsTable.getItems().add(exampleClient);
	}

}
