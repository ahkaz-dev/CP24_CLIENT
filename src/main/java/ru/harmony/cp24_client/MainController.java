package ru.harmony.cp24_client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.harmony.cp24_client.controller.Category_ITController;

import java.io.IOException;

public class MainController {
    @FXML
    public Label userLoginLabel;
    @FXML
    private BorderPane mainBorderPane;

    private MenuController menuController;
    private Category_ITController categoryItController;

    private Stage primaryStage;
    private HelloController controller;
    private HelloApplication helloApplication;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setHelloApplication(HelloApplication helloApplication) { // Метод для установки главного класса приложения
        this.helloApplication = helloApplication;
    }

    public void setUserLoginLabel(Label userLoginLabel) {
        this.userLoginLabel.setText(userLoginLabel.getText());
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

    public void handeITcontinueButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("category-it.fxml"));
        Parent root = loader.load();

        Category_ITController categoryItController = loader.getController();
        categoryItController.setPrimaryStage(primaryStage);
        categoryItController.setHelloApplication(this.helloApplication);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
