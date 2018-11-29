package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import entity.Client;
import helpers.DateManager;
import helpers.MessageDisplay;
import helpers.TableFactory;
import helpers.Validator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClientsController implements Initializable {

    @FXML
    TableView<Client> clientsTable;

    @FXML
    TableColumn<Client, Integer> idColumn;
    @FXML
    TableColumn<Client, String> firstNameColumn;
    @FXML
    TableColumn<Client, String> lastNameColumn;
    @FXML
    TableColumn<Client, String> birthDateColumn;
    @FXML
    TableColumn<Client, String> phoneColumn;
    @FXML
    TableColumn<Client, String> emailColumn;
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
    @FXML
    MenuItem deleteRightClick;

    @FXML
    TextField searchedClientName;

    @FXML
    TextField searchedClientPhone;

    @FXML
    TextField searchedClientEmail;

    @FXML
    Button searchBtn;

    @FXML
    Label foundClientName;

    @FXML
    Label foundClientPhone;

    @FXML
    Label foundClientEmail;

    @FXML
    Button searchByNameBtn;

    @FXML
    Button searchByPhoneBtn;

    @FXML
    Button searchByEmailBtn;

    @FXML
    Button prevBtn;

    @FXML
    Button nextBtn;

    @FXML
    Label resultCount;

    @FXML
    Label currentResult;

    private List<Client> foundClients;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableFactory.configureRow(idColumn, "clientId");
        TableFactory.configureRow(firstNameColumn, "firstName");
        TableFactory.configureRow(lastNameColumn, "lastName");
        TableFactory.configureRow(birthDateColumn, "birthDate");
        TableFactory.configureRow(phoneColumn, "phone");
        TableFactory.configureRow(emailColumn, "email");

        clientsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    deleteRightClick.setDisable(tableIsEmpty());
                }
            }
        });
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

        Client client = new Client(incrementID(), firstName.getText(), lastName.getText(), getClientAge(birthDateInput),
                email.getText(), phone.getText());

        clientsTable.getItems().add(client);

        handleClearForm();
    }

    @FXML
    private void handleClearForm() {
        firstName.setText("");
        lastName.setText("");
        birthDate.setValue(null);
        email.setText("");
        phone.setText(""); // TODO: if border has become red from previous validation attempt, reset it.
    }

    private Date getClientAge(LocalDate birthDateInput) {
         return Date.from(Instant.from(birthDateInput.atStartOfDay(ZoneId.systemDefault())));
    }

    private int incrementID() {
        int lastItemID = 0;
        if (!clientsTable.getItems().isEmpty()) {
            int lastItemIndex = clientsTable.getItems().size() - 1;
            lastItemID = clientsTable.getItems().get(lastItemIndex).getClientId();
        }
        return ++lastItemID;
    }

    @FXML
    public void deleteClient() {

        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        String clientName = selectedClient.getFirstName() + " " + selectedClient.getLastName();

        ButtonType delete = new ButtonType("Изтрий", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Отказ", ButtonData.CANCEL_CLOSE);

        Alert al = new Alert(Alert.AlertType.CONFIRMATION,
                "Сигурни ли сте, че искате да изтриете следния клиент: \n\t" + clientName, delete, cancel);
        al.showAndWait();
        if (al.getResult().equals(delete)) {
            clientsTable.getItems().remove(selectedClient);
        }
    }

    public boolean tableIsEmpty() {
        return clientsTable.getItems().isEmpty();
    }

    @FXML
    public void searchByName() {
        clearResults();
        ObservableList<Client> clientsList = clientsTable.getItems();
        foundClients = new ArrayList<Client>();

        if (searchedClientName.getText().contains(" ")) {
            // String[] fullName = searchedClientName.getText().split(" ");
            String name = searchedClientName.getText();
            for (Client client : clientsList) {
                // if (fullName[0].equals(client.getFirstName())) {
                // if (fullName[1].equals(client.getLastName())) {
                if (name.equals(client.getFirstName() + " " + client.getLastName())) {
                    foundClients.add(client);
                    System.out.println(client);
                }
            }
            // }
        } else {
            String name = searchedClientName.getText();
            for (Client client : clientsList) {
                if (name.equals(client.getFirstName()) || name.equals(client.getLastName())) {
                    foundClients.add(client);
                    System.out.println(client);
                }
            }
        }

        if (foundClients.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundClients.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundClients.size() + "");
        displayResult(0);
    }

    @FXML
    public void searchByPhone() {
        clearResults();
        String phoneNumber = searchedClientPhone.getText();
        ObservableList<Client> clientsList = clientsTable.getItems();

        foundClients = new ArrayList<Client>();

        for (Client client : clientsList) {
            if (phoneNumber.equals(client.getPhone())) {
                foundClients.add(client);
            }
        }
        if (foundClients.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundClients.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundClients.size() + "");
        displayResult(0);
    }

    @FXML
    public void searchByEmail() {
        clearResults();
        String email = searchedClientEmail.getText();
        ObservableList<Client> clientsList = clientsTable.getItems();

        foundClients = new ArrayList<Client>();

        for (Client client : clientsList) {
            if (email.equals(client.getEmail())) {
                foundClients.add(client);
            }
        }
        if (foundClients.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundClients.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundClients.size() + "");
        displayResult(0);
    }

    @FXML
    public void showPreviousResult() {
        clearResults();
        int foundClientsCount = foundClients.size();
        int currentIndex = Integer.parseInt(currentResult.getText()) - 1;
        int prevIndex;

        if (currentIndex > 0) {
            prevIndex = --currentIndex;
        } else {
            prevIndex = foundClientsCount - 1;
        }

        displayResult(prevIndex);
        currentResult.setText(prevIndex + 1 + "");

        //		Client prevResult = foundClients.get(prevIndex);
//		foundClientName.setText(foundClientName.getText() + "\t" + prevResult.getFirstName() + " " + prevResult.getLastName());
//		foundClientEmail.setText(foundClientEmail.getText() + "\t" + prevResult.getEmail());
//		foundClientPhone.setText(foundClientPhone.getText() + "\t" + prevResult.getPhone());
    }

    @FXML
    public void showNextResult() {
        clearResults();
        int foundClientsCount = foundClients.size();
        int currentIndex = Integer.parseInt(currentResult.getText()) - 1;
        int nextIndex;

        if (currentIndex < foundClientsCount - 1) {
            nextIndex = currentIndex + 1;
        } else {
            nextIndex = 0;
        }

        displayResult(nextIndex);
        currentResult.setText(nextIndex + 1 + "");
    }

    private void displayResult(int index) {
        Client clientResult = foundClients.get(index);
        foundClientName.setText(foundClientName.getText() + "\t" + clientResult.getFirstName() + " " + clientResult.getLastName());
        foundClientEmail.setText(foundClientEmail.getText() + "\t" + clientResult.getEmail());
        foundClientPhone.setText(foundClientPhone.getText() + "\t" + clientResult.getPhone());
    }

    private void enableNavigation() {
        prevBtn.setDisable(false);
        nextBtn.setDisable(false);
    }

    private void clearResults() {
        prevBtn.setDisable(true);
        nextBtn.setDisable(true);

        foundClientName.setText("Име и фамилия:");
        foundClientPhone.setText("Телефон:");
        foundClientEmail.setText("Email:");
    }
}
