package ru.harmony.cp24_client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Worker;
import ru.harmony.cp24_client.HelloApplication;
import ru.harmony.cp24_client.controller.fuctional.AddFormController;
import ru.harmony.cp24_client.controller.fuctional.AddWorkerController;
import ru.harmony.cp24_client.service.entity.UserService;
import ru.harmony.cp24_client.service.entity.WorkerService;

import java.io.IOException;
import java.util.Optional;

public class WorkerController {
    public TableView<Worker> tableViewWorker;
    public TableColumn<Worker, String> name;
    public TableColumn<Worker, String> surName;
    public TableColumn<Worker, String> lastName;
    public TableColumn<Worker, String> position;
    public TableColumn<Worker, String> access;

    private final WorkerService service = new WorkerService();
    private final UserService userService = new UserService();
    public Button addNewWorkerButton;
    public Button updateWorkerButton;
    public Button deleteWorkerButton;

    static private String login;
    static private String password;

    public void setLogin(String login) {
        WorkerController.login = login;
    }

    public void setPassword(String password) {
        WorkerController.password = password;
    }

    public void checkAccess() {
        if (userService.findByDataAccess(login, password)) {
            addNewWorkerButton.setDisable(false);
            updateWorkerButton.setDisable(true);
            deleteWorkerButton.setDisable(false);
        } else {
            addNewWorkerButton.setDisable(true);
            updateWorkerButton.setDisable(true);
            deleteWorkerButton.setDisable(true);
        }
    }

    @FXML
    private void initialize() {
        checkAccess();
        try {
            service.getAll();
        } catch (Exception e ) {
            System.out.println(e);
        }
        name.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
        surName.setCellValueFactory(new PropertyValueFactory<Worker, String>("surName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Worker, String>("lastName"));
        position.setCellValueFactory(new PropertyValueFactory<Worker, String>("position"));
        access.setCellValueFactory(new PropertyValueFactory<Worker, String>("access"));

        tableViewWorker.setItems(service.getWorker());
    }


    public void handleAddWorkerButton(ActionEvent event) throws IOException {
        checkAccess();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("new-worker.fxml"));
        Parent root = loader.load();
        AddWorkerController controller = loader.getController();
        controller.setButtons(addNewWorkerButton, updateWorkerButton);

        Stage stage = new Stage();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        addNewWorkerButton.setDisable(true);
        updateWorkerButton.setDisable(true);

        stage.showAndWait();;

        tableViewWorker.getItems().clear();
        initialize();
    }

    @FXML
    void onMouseClickTableView(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                if (tableViewWorker.getSelectionModel().getSelectedItem() != null) {
                    if (userService.findByDataAccess(login, password)) updateWorkerButton.setDisable(false);
                }
            }
        }
    }

    public void handleDeleteWorkerButton(ActionEvent event) {
        checkAccess();
        Worker selectedWorker = tableViewWorker.getSelectionModel().getSelectedItem();
        if (selectedWorker != null) {
            service.delete(selectedWorker);
            tableViewWorker.getItems().clear();
            initialize();
        }
    }

    public void handleUpdateWorkerButton(ActionEvent event) throws IOException {
        checkAccess();
        Worker tempWorker = tableViewWorker.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("new-worker.fxml"));
        Parent root = loader.load();
        AddWorkerController controller = loader.getController();
        controller.setWorker(Optional.ofNullable(tempWorker));
        controller.setButtons(addNewWorkerButton, updateWorkerButton);
        controller.start();

        Stage stage = new Stage();
        controller.setStage(stage);

        addNewWorkerButton.setDisable(true);
        updateWorkerButton.setDisable(true);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();

        tableViewWorker.getItems().clear();
        initialize();
    }
}
