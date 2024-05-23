package ru.harmony.cp24_client.controller.fuctional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.harmony.cp24_client.Entity.Access;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Worker;
import ru.harmony.cp24_client.service.entity.*;

import java.util.Optional;

public class AddWorkerController {
    private final WorkerService service = new WorkerService();
    private final AccessService accessService = new AccessService();
    private final UserService userService = new UserService();

    @FXML
    public ComboBox<Access> access;
    @FXML
    public TextField position;
    @FXML
    public TextField lastName;
    @FXML
    public TextField surName;
    @FXML
    public TextField name;
    public Button addNewWorker;
    public Label labelSurname;
    public Label labelname;
    public Label labelLastname;
    public Label labelPosition;
    public Label labelAccess;

    private Stage stage;

    private Button addNewFormButton;
    private Button updateFormButton;



    @FXML
    private void initialize() {
        try {
            accessService.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        access.setItems(accessService.getAccesses());
    }

    @Setter
    @Getter
    private Optional<Worker> workerGive;

    public void start() {
        if (workerGive == null)  workerGive = Optional.empty();
        if(workerGive.isPresent()) {
            surName.setText(workerGive.get().getSurName());
            lastName.setText(workerGive.get().getLastName());
            name.setText(workerGive.get().getName());
            position.setText(workerGive.get().getPosition());
            access.setValue(workerGive.get().getAccess());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setButtons(Button addNewFormButton, Button updateFormButton) {
        this.addNewFormButton = addNewFormButton;
        this.updateFormButton = updateFormButton;
    }

    public void setWorker(Optional<Worker> worker) {
        this.workerGive = worker;
    }

    public void handelAddButton(ActionEvent event) {
        Worker workerTemp = new Worker();
        if (!surName.getText().isEmpty() &&
                !lastName.getText().isEmpty() &&
                !name.getText().isEmpty() &&
                !position.getText().isEmpty() &&
                access.getSelectionModel().getSelectedItem() != null) {
            // Все поля заполнены
            workerTemp.setLastName(lastName.getText());
            workerTemp.setSurName(surName.getText());
            workerTemp.setName(name.getText());
            workerTemp.setPosition(position.getText());
            workerTemp.setAccess(access.getSelectionModel().getSelectedItem());
            start();
            try {
                if (workerGive.isEmpty()) {
                    service.add(workerTemp);
                    stage.close();
                } else {
                    workerTemp.setId(workerGive.get().getId());
                    service.update(workerTemp, workerGive.get());
                    stage.close();
                }
                if (addNewFormButton != null) {
                    addNewFormButton.setDisable(false);
                    updateFormButton.setDisable(false);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            // Не все поля заполнены
            System.out.println("error");
            labelname.setStyle("-fx-text-fill: red;");
            labelAccess.setVisible(true); labelAccess.setStyle("-fx-text-fill: red;");
            labelPosition.setStyle("-fx-text-fill: red;");
            labelLastname.setStyle("-fx-text-fill: red;");
            labelSurname.setStyle("-fx-text-fill: red;");
            labelname.setStyle("-fx-text-fill: red;");
        }
    }

    public void handlerCancelButton(ActionEvent event) {
        stage.close();
        if (addNewFormButton != null && updateFormButton != null) {
            addNewFormButton.setDisable(false);
            updateFormButton.setDisable(false);
        }
    }
}
