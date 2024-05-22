package ru.harmony.cp24_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.controller.FormController;
import ru.harmony.cp24_client.controller.fuctional.AddFormController;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class HelloApplication extends Application {
    private boolean isLoginWindowOpen = true;
    private static Stage loginStage;


    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Harmony");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("./components/image/icons/logo-yoga.png"))));

        HelloController helloController = fxmlLoader.getController();
        helloController.setPrimaryStage(primaryStage);
        helloController.setHelloApplication(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void setLoginWindowOpen(boolean isLoginWindowOpen) {
        this.isLoginWindowOpen = isLoginWindowOpen;
    }

    public boolean isLoginWindowOpen() {
        return isLoginWindowOpen;
    }

    public static void main(String[] args) {
        launch();
    }
}