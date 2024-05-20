package ru.harmony.cp24_client.controller;

import javafx.stage.Stage;
import ru.harmony.cp24_client.HelloApplication;

public class Category_ITController {
    private Stage primaryStage;
    private HelloApplication helloApplication;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setHelloApplication(HelloApplication helloApplication) { // Метод для установки главного класса приложения
        this.helloApplication = helloApplication;
    }
}
