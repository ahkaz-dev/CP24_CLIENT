package ru.harmony.cp24_client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HelloController {
    @FXML
    private Button loginButton;

    private Stage primaryStage; // Сохраняем ссылку на первичное окно

    public void setPrimaryStage(Stage primaryStage) { // Метод для установки первичного окна
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleLoginButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("main-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene); // Устанавливаем новую сцену для первичного окна
        primaryStage.show(); // Отображаем новое окно

        // primaryStage.close(); // Альтернативно, вы можете закрыть первичное окно, но в этом случае вам нужно будет создать новое окно, а не переиспользовать первичное
    }
}