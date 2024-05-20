package ru.harmony.cp24_client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.harmony.cp24_client.Entity.User;
import ru.harmony.cp24_client.service.entity.UserService;

import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;

public class HelloController {
    private final UserService service = new UserService();
    public TextField usPassText;
    public TextField usLoginText;
    public Button exitButton;
    public ImageView loginByGoogleButton;
    public Label loginErrorValidLabel;
    public Label loginErrorConectionLabel;
    String login, password;

    Alert a = new Alert(Alert.AlertType.NONE);
    @FXML
    private Button loginButton;

    private Stage primaryStage;
    private HelloApplication helloApplication;

    public void setPrimaryStage(Stage primaryStage) { // Метод для установки первичного окна
        this.primaryStage = primaryStage;
        loginErrorValidLabel.setVisible(false);

        loginErrorConectionLabel.setVisible(!service.checkServerConnect());
    }

    public void setHelloApplication(HelloApplication helloApplication) { // Метод для установки главного класса приложения
        this.helloApplication = helloApplication;
    }

    @FXML
    private void handleLoginButton() throws IOException {
/*        if (dataValidation()) {
            try {
                service.findByData(login, password);
                FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("main-view.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            }  catch (ConnectException e){
                loginErrorConectionLabel.setVisible(true);
            } catch (NullPointerException s){
                loginErrorValidLabel.setVisible(true);
            }
        } else {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setHeaderText("Ошибка ввода");
            a.setContentText("Данные входа отсутствуют");
            a.show();
        }*/
        primaryStage.close();

        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("main-view.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();
        mainController.setPrimaryStage(primaryStage);
        mainController.setHelloApplication(this.helloApplication);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean dataValidation() {
        if (!usLoginText.getText().isEmpty() & !usPassText.getText().isEmpty()) {
            login = usLoginText.getText();
            password = usPassText.getText();
            return true;
        }
        return false;
    }

    public void handleGoogleLoginButton(MouseEvent mouseEvent) {
        a.setAlertType(Alert.AlertType.INFORMATION);

        a.setHeaderText("Инфорация | Harmony");
        a.setContentText("Просим прощения (╥﹏╥)! Данная функцие еще не добавлена в систему. . .");
        a.show();
    }

    public void handleExitButton(ActionEvent event) {
        if (helloApplication.isLoginWindowOpen()) { // If login window is open, just close it
            primaryStage.close();
        } else { // If login window is not open, close all windows and exit the application
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        }
    }
}