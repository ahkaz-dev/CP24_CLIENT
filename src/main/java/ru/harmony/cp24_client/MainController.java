package ru.harmony.cp24_client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.harmony.cp24_client.controller.VacancyController;

import java.io.IOException;

public class MainController {
    @FXML
    public Label userLoginLabel;
    public Pane catalogMainPane;
    @FXML
    private AnchorPane anchorPaneMain;
    @FXML
    private BorderPane mainBorderPane;

    private VacancyController categoryItController;

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
    }

    @FXML
    public void handeITcontinueButton(ActionEvent actionEvent) throws IOException {
    }

    @FXML
    public void handleGoToMainView(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane-view.fxml"));
        AnchorPane vista2 = (AnchorPane) fxmlLoader.load();
        anchorPaneMain.getChildren().setAll(vista2);
    }

    @FXML
    public void handeIVacancyButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vacancy-view.fxml"));
        AnchorPane vista2 = (AnchorPane) fxmlLoader.load();
        anchorPaneMain.getChildren().setAll(vista2);
    }

    @FXML
    public void handelFormButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form-view.fxml"));
        AnchorPane vista2 = (AnchorPane) fxmlLoader.load();
        anchorPaneMain.getChildren().setAll(vista2);
    }

    @FXML
    public void handelWorkerButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("worker-view.fxml"));
        AnchorPane vista2 = (AnchorPane) fxmlLoader.load();
        anchorPaneMain.getChildren().setAll(vista2);
    }
}
