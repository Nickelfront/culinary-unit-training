package application;

import DB.BaseDBConnector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        AnchorPane root;

        try {
            BaseDBConnector dc = new BaseDBConnector();

            root = (AnchorPane) FXMLLoader.load(getClass().getResource("MainApp.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            try {
                Image icon = new Image(getClass().getClassLoader().getResourceAsStream("cooking.png"));
                stage.getIcons().add(icon);
            } catch (Exception e) {

            }
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("UberChef - организатор");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
