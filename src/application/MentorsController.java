package application;

import entity.Client;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import entity.Mentor;
import helpers.MessageDisplay;
import helpers.TableFactory;
import helpers.Validator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MentorsController implements Initializable {

    @FXML
    TableView<Mentor> mentorsTable;

    @FXML
    TableColumn<Mentor, Integer> idColumn;
    @FXML
    TableColumn<Mentor, String> firstNameColumn;
    @FXML
    TableColumn<Mentor, String> lastNameColumn;
    @FXML
    TableColumn<Mentor, String> phoneColumn;
    @FXML
    TableColumn<Mentor, String> emailColumn;
    @FXML
    TableColumn<Mentor, Double> salaryColumn;

    @FXML
    TextField lastName;

    @FXML
    TextField firstName;

    @FXML
    TextField phone;

    @FXML
    TextField email;

    @FXML
    TextField salary;
    
    @FXML
    MenuItem deleteRightClick;

    @FXML
    Button searchByPhoneBtn;

    @FXML
    Button searchByEmailBtn;

    @FXML
    Label currentResult;

    @FXML
    Label resultCount;

    @FXML
    Button nextBtn;

    @FXML
    Button prevBtn;

    @FXML
    TextField searchedMentorName;

    @FXML
    TextField searchedMentorPhone;

    @FXML
    TextField searchedMentorEmail;

    @FXML
    Button searchByNameBtn;

    @FXML
    Label foundMentorName;

    @FXML
    Label foundMentorPhone;

    @FXML
    Label foundMentorEmail;

    private List<Mentor> foundMentors;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableFactory.configureRow(idColumn, "mentorId");
        TableFactory.configureRow(firstNameColumn, "firstName");
        TableFactory.configureRow(lastNameColumn, "lastName");
        TableFactory.configureRow(phoneColumn, "phone");
        TableFactory.configureRow(emailColumn, "email");
        TableFactory.configureRow(salaryColumn, "salary");

        TableFactory.fill(mentorsTable, new Mentor().all());

        mentorsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                deleteRightClick.setDisable(tableIsEmpty());
            }
        });
    }

    @FXML
    private void handleAddMentor(ActionEvent event) {
        String mentorEmail = email.getText();
        String mentorPhoneNumber = phone.getText();

        if (!Validator.validateEmail(mentorEmail)) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Невалиден e-Mail!");
            Validator.setFieldInputAsInvalid(email);
            al.show();
            return;
        }
        if (!Validator.validatePhoneNumber(mentorPhoneNumber)) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Невалиден телефонен номер.");
            Validator.setFieldInputAsInvalid(phone);
            al.show();
            return;
        }

        Mentor mentor = new Mentor(incrementID(), firstName.getText(), lastName.getText(),
                mentorPhoneNumber, mentorEmail, Double.parseDouble(salary.getText()));
        mentor.save();
        mentorsTable.getItems().add(mentor);

        firstName.setText("");
        lastName.setText("");
        email.setText("");
        phone.setText("");
        salary.setText("");
    }

    @FXML
    public void handleClearForm() {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        phone.setText("");
        salary.setText("");
    }

    private int incrementID() {
        int lastItemID = 0;
        if (!mentorsTable.getItems().isEmpty()) {
            int lastItemIndex = mentorsTable.getItems().size() - 1;
            lastItemID = mentorsTable.getItems().get(lastItemIndex).getMentorId();
        }
        return ++lastItemID;
    }

    @FXML
    public void deleteMentor() {

        Mentor selectedMentor = mentorsTable.getSelectionModel().getSelectedItem();
        String mentorName = selectedMentor.getFirstName() + " " + selectedMentor.getLastName();

        ButtonType delete = new ButtonType("Изтрий", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Отказ", ButtonData.CANCEL_CLOSE);

        Alert al = new Alert(Alert.AlertType.CONFIRMATION,
                "Сигурни ли сте, че искате да изтриете следния ментор: \n\t" + mentorName, delete, cancel);
        al.showAndWait();
        if (al.getResult().equals(delete)) {
            selectedMentor.delete();
            mentorsTable.getItems().remove(selectedMentor);
        }
    }

    public boolean tableIsEmpty() {
        return mentorsTable.getItems().isEmpty();
    }

    @FXML
    public void searchByName() {
        clearResults();
        ObservableList<Mentor> mentorsList = mentorsTable.getItems();
        foundMentors = new ArrayList<Mentor>();

        if (searchedMentorName.getText().contains(" ")) {
            // String[] fullName = searchedMentorName.getText().split(" ");
            String name = searchedMentorName.getText();
            for (Mentor mentor : mentorsList) {
                // if (fullName[0].equals(Mentor.getFirstName())) {
                // if (fullName[1].equals(Mentor.getLastName())) {
                if (name.equals(mentor.getFirstName() + " " + mentor.getLastName())) {
                    foundMentors.add(mentor);
                    System.out.println(mentor);
                }
            }
            // }
        } else {
            String name = searchedMentorName.getText();
            for (Mentor mentor : mentorsList) {
                if (name.equals(mentor.getFirstName()) || name.equals(mentor.getLastName())) {
                    foundMentors.add(mentor);
                    System.out.println(mentor);
                }
            }
        }

        if (foundMentors.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundMentors.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundMentors.size() + "");
        displayResult(0);
    }

    @FXML
    public void searchByPhone() {
        clearResults();
        String phoneNumber = searchedMentorPhone.getText();
        ObservableList<Mentor> mentorsList = mentorsTable.getItems();

        foundMentors = new ArrayList<Mentor>();

        for (Mentor mentor : mentorsList) {
            if (phoneNumber.equals(mentor.getPhone())) {
                foundMentors.add(mentor);
            }
        }
        if (foundMentors.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundMentors.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundMentors.size() + "");
        displayResult(0);
    }

    @FXML
    public void searchByEmail() {
        clearResults();
        String email = searchedMentorEmail.getText();
        ObservableList<Mentor> mentorsList = mentorsTable.getItems();

        foundMentors = new ArrayList<Mentor>();

        for (Mentor mentor : mentorsList) {
            if (email.equals(mentor.getEmail())) {
                foundMentors.add(mentor);
            }
        }
        if (foundMentors.isEmpty()) {
            MessageDisplay.noSearchResults();
            return;
        }

        if (foundMentors.size() > 1) {
            enableNavigation();
        }
        currentResult.setText("1");
        resultCount.setText(foundMentors.size() + "");
        displayResult(0);
    }

    @FXML
    public void showPreviousResult() {
        clearResults();
        int foundMentorsCount = foundMentors.size();
        int currentIndex = Integer.parseInt(currentResult.getText()) - 1;
        int prevIndex;

        if (currentIndex > 0) {
            prevIndex = --currentIndex;
        } else {
            prevIndex = foundMentorsCount - 1;
        }

        displayResult(prevIndex);
        currentResult.setText(prevIndex + 1 + "");
    }

    @FXML
    public void showNextResult() {
        clearResults();
        int foundMentorsCount = foundMentors.size();
        int currentIndex = Integer.parseInt(currentResult.getText()) - 1;
        int nextIndex;

        if (currentIndex < foundMentorsCount - 1) {
            nextIndex = currentIndex + 1;
        } else {
            nextIndex = 0;
        }

        displayResult(nextIndex);
        currentResult.setText(nextIndex + 1 + "");
    }

    private void displayResult(int index) {
        Mentor mentorResult = foundMentors.get(index);
        foundMentorName.setText(
                foundMentorName.getText() + "\t" + mentorResult.getFirstName() + " " + mentorResult.getLastName());
        foundMentorEmail.setText(foundMentorEmail.getText() + "\t" + mentorResult.getEmail());
        foundMentorPhone.setText(foundMentorPhone.getText() + "\t" + mentorResult.getPhone());
    }

    private void enableNavigation() {
        prevBtn.setDisable(false);
        nextBtn.setDisable(false);
    }

    private void clearResults() {
        prevBtn.setDisable(true);
        nextBtn.setDisable(true);

        foundMentorName.setText("Име и фамилия:");
        foundMentorPhone.setText("Телефон:");
        foundMentorEmail.setText("Email:");
    }

    @FXML
    private void openCoursesInquery(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MentorCourses.fxml"));
            MentorCoursesController controller = new MentorCoursesController((Mentor) mentorsTable.getSelectionModel().getSelectedItem());
            loader.setController(controller);

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Справка за Ментор");
            stage.setResizable(false);
            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    @FXML
    private void openAssignView(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignMentorToCourse.fxml"));
            AssignMentorToCourseController controller = new AssignMentorToCourseController((Mentor) mentorsTable.getSelectionModel().getSelectedItem());
            loader.setController(controller);

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Справка за Ментор");
            stage.setResizable(false);
            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
