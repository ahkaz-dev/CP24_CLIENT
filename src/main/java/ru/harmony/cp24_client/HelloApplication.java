package ru.harmony.cp24_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private boolean isLoginWindowOpen = true;
    private static Stage loginStage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Harmony");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("./components/image/icons/logo-yoga.png"))));

        HelloController helloController = loader.getController();
        helloController.setPrimaryStage(primaryStage);
        helloController.setHelloApplication(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

/*    public static void showLoginWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage loginStage = new Stage();
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root, 1069, 653));
            loginStage.setResizable(false);
            loginStage.show();
            HelloApplication.loginStage = loginStage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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