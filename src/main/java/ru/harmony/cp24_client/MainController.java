package ru.harmony.cp24_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    public Label userLoginLabel;
    private Stage primaryStage;
    private HelloController controller;
    private HelloApplication helloApplication;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setHelloApplication(HelloApplication helloApplication) { // Метод для установки главного класса приложения
        this.helloApplication = helloApplication;
    }

    public void handleLogoutButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("hello-view.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        HelloController helloController = loader.getController();
        helloController.setPrimaryStage(primaryStage);
        helloController.setHelloApplication(this.helloApplication);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Close main window
        //primaryStage.close();
    }

}
