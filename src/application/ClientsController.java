package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import entity.Client;
import helpers.TableFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class ClientsController implements Initializable {

	@FXML TableView<Client> clientsTable;
	
	@FXML TableColumn<Client, Integer> idColumn;
	@FXML TableColumn<Client, String> firstNameColumn;
	@FXML TableColumn<Client, String> lastNameColumn;
	@FXML TableColumn<Client, Integer> ageColumn;
	@FXML TableColumn<Client, String> phoneColumn;
	@FXML TableColumn<Client, String> emailColumn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
                TableFactory.configureRow(idColumn, "clientId");
                TableFactory.configureRow(firstNameColumn, "firstName");
                TableFactory.configureRow(lastNameColumn, "lastName");
                TableFactory.configureRow(ageColumn, "age");
                TableFactory.configureRow(phoneColumn, "phone");
                TableFactory.configureRow(emailColumn, "email");

		Client exampleClient = new Client("Иван", "Петров", 26, "ipetrov@gmail.com", "08745863125");
		clientsTable.getItems().add(exampleClient);
	}

    @FXML
    private void handleAddClient(ActionEvent event) {
        try {
            int clientAge = Integer.parseInt(this.age.getText());
        
            if (!validateAge(clientAge)){
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Мноо си малък пич!");
                al.show();
                return;
            }
            
            Client customer = new Client(
                firstName.getText(),
                lastName.getText(),
                clientAge,
                email.getText(),
                phone.getText()
            );
        
            clientsTable.getItems().add(customer);
        
            firstName.setText("");
            lastName.setText("");
            age.setText("");
            email.setText("");
            phone.setText("");
        } catch (NumberFormatException e) {
        }
    }

    @FXML
    private void handleClearForm(ActionEvent event) {
    }
    
    private Boolean validateAge(int age){
        return age > 16;
    }
}
