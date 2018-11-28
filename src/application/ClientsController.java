package application;

import entity.Base;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import entity.Clients;
import helpers.DateManager;
import helpers.TableFactory;
import helpers.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientsController implements Initializable {

    @FXML
    TableView<Clients> clientsTable;

    @FXML
    TableColumn<Clients, Integer> idColumn;
    @FXML
    TableColumn<Clients, String> firstNameColumn;
    @FXML
    TableColumn<Clients, String> lastNameColumn;
    @FXML
    TableColumn<Clients, Date> birthDateColumn;
    @FXML
    TableColumn<Clients, String> phoneColumn;
    @FXML
    TableColumn<Clients, String> emailColumn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    DatePicker birthDate;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableFactory.configureRow(idColumn, "clientId");
        TableFactory.configureRow(firstNameColumn, "firstName");
        TableFactory.configureRow(lastNameColumn, "lastName");
        TableFactory.configureRow(birthDateColumn, "birthDate");
        TableFactory.configureRow(phoneColumn, "phone");
        TableFactory.configureRow(emailColumn, "email");

//		Clients exampleClient = new Clients(0, "Иван", "Петров", 26, "ipetrov@gmail.com", "0874586125");
//		clientsTable.getItems().add(exampleClient);
    }

    @FXML
    private void handleAddClient(ActionEvent event) {
        LocalDate birthDateInput = birthDate.getValue();
        String clientEmail = email.getText();
        String clientPhoneNumber = phone.getText();

        if (!Validator.validateEmail(clientEmail)) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Невалиден e-Mail!");
            Validator.setFieldInputAsInvalid(email);
            al.show();
            return;
        }
        if (!Validator.validateAge(birthDateInput)) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Клиентът трябва да е лице на възраст над 16 години!");
            al.show();
            return;
        }
        if (!Validator.validatePhoneNumber(clientPhoneNumber)) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Невалиден телефонен номер.");
            Validator.setFieldInputAsInvalid(phone);
            al.show();
            return;
        }

        Clients client = new Clients(incrementID(), firstName.getText(), lastName.getText(), getClientAge(birthDateInput),
                email.getText(), phone.getText());
        client.save();
        clientsTable.getItems().add(client);

        firstName.setText("");
        lastName.setText("");
        birthDate.setValue(null);
        email.setText("");
        phone.setText("");
    }

    @FXML
    private void handleClearForm(ActionEvent event) {
        firstName.setText("");
        lastName.setText("");
        birthDate.setValue(null);
        email.setText("");
        phone.setText(""); //TODO: if border has become red from previous validation attempt, reset it.
    }

    private Date getClientAge(LocalDate birthDateInput) {
        Date birthDate2 = Date.from(Instant.from(birthDateInput.atStartOfDay(ZoneId.systemDefault())));
//		int age = DateManager.getYearDifference(birthDate);
        return birthDate2;
    }

    private int incrementID() {
        int lastItemIndex = clientsTable.getItems().size() - 1;
        int lastItemID = 0;
        if (lastItemIndex >= 1) {
            lastItemID = clientsTable.getItems().get(lastItemIndex).getClientId();
        }

        return ++lastItemID;
    }
}
