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
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.HelloApplication;
import ru.harmony.cp24_client.controller.fuctional.AddFormController;
import ru.harmony.cp24_client.service.entity.FormService;

import javafx.beans.binding.Bindings;
import ru.harmony.cp24_client.service.entity.UserService;
import ru.harmony.cp24_client.service.entity.VacancyService;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class FormController {
    private final UserService userService = new UserService();

    @FXML
    public TableView<Form> tableViewForm;

    @FXML
    public TableColumn<Form, String> formHeader;
    @FXML
    public TableColumn<Form, String> formAspSpec;
    @FXML
    public TableColumn<Form, String> formFio;

    @FXML
    public TableColumn<Form, String> formAspSkills;
    @FXML
    public TableColumn<Form, String> formWorkExperience;
    @FXML
    public TableColumn<Form, String> formEducation;
    @FXML
    public TableColumn<Form, String> formWorkBefore;

    @FXML
    public TableColumn<Form, String> formAspBirthDate;

    private final FormService service = new FormService();
    private final VacancyService vacancyService = new VacancyService();

    static private String login;
    static private String password;

    public void setLogin(String login) {
        FormController.login = login;
    }

    public void setPassword(String password) {
        FormController.password = password;
    }

    public void checkAccess() {
        if (userService.findByDataAccess(login, password)) {
            addNewFormButton.setDisable(false);
            updateFormButton.setDisable(true);
            deleteFormButton.setDisable(false);
        } else {
            addNewFormButton.setDisable(true);
            updateFormButton.setDisable(true);
            deleteFormButton.setDisable(true);
        }
    }



    public Vacancy vacancy;
    public Button addNewFormButton;
    public Button deleteFormButton;
    public Button updateFormButton;

    private Optional<Form> formOptional = Optional.empty();
    public void setForm(Optional<Form> form) {
        this.formOptional = form;
        if (form.isPresent()) {
            if (form.get().getId() != null) {
                service.update(form.get(), tableViewForm.getSelectionModel().getSelectedItem());
            } else {
                service.add(form.get());
            }
        }
    }

    @FXML
    private void initialize() {
        checkAccess();
        try {
            service.getAll();
            vacancyService.getAll();
        } catch (Exception e ) {
            System.out.println(e);
        }
        formHeader.setCellValueFactory(new PropertyValueFactory<Form, String>("formHeader"));
        formAspSpec.setCellValueFactory(new PropertyValueFactory<Form, String>("aspSpec"));
        formFio.setCellValueFactory(cellData -> Bindings.createStringBinding( () -> cellData.getValue().getSurName() + " " + cellData.getValue().getName() + " " + cellData.getValue().getLastName()));
        formAspSkills.setCellValueFactory(new PropertyValueFactory<Form, String>("aspSkills"));
        formWorkExperience.setCellValueFactory(new PropertyValueFactory<Form, String>("workExperience"));
        formEducation.setCellValueFactory(new PropertyValueFactory<Form, String>("education"));
        formWorkBefore.setCellValueFactory(new PropertyValueFactory<Form, String>("workBefore"));
        formAspBirthDate.setCellValueFactory(new PropertyValueFactory<Form, String>("aspBirthDate"));

        tableViewForm.setItems(service.getForms());
        updateFormButton.setVisible(true);
        updateFormButton.setDisable(true);
    }

    public void handleAddFormButton(ActionEvent event) throws IOException {
        checkAccess();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("new-form.fxml"));
        Parent root = loader.load();
        AddFormController controller = loader.getController();
        controller.setButtons(addNewFormButton, updateFormButton);

        Stage stage = new Stage();
        stage.setTitle("Регистрация Анкеты | StaffHarmony");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("./components/image/icons/logo-yoga.png"))));
        controller.setStage(stage);
        controller.setStageOptions();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        addNewFormButton.setDisable(true);
        updateFormButton.setDisable(true);

        stage.showAndWait();;

        tableViewForm.getItems().clear();
        initialize();
    }

    @FXML
    void onMouseClickTableView(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                if (tableViewForm.getSelectionModel().getSelectedItem() != null) {
                    if (userService.findByDataAccess(login, password)) updateFormButton.setDisable(false);
                }
            }
        }
    }

    public void handleDeleteFormButton(ActionEvent event) {
        checkAccess();
        Form selectedForm = tableViewForm.getSelectionModel().getSelectedItem();
        if (selectedForm != null) {
            service.delete(selectedForm);
            tableViewForm.getItems().clear();
            initialize();
        }
    }

    public void handleUpdateFormButton(ActionEvent event) throws IOException {
        checkAccess();
        Form tempForm = tableViewForm.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("new-form.fxml"));
        Parent root = loader.load();
        AddFormController controller = loader.getController();
        controller.setFormGive(Optional.ofNullable(tempForm));
        controller.setButtons(addNewFormButton, updateFormButton);

        controller.start();

        Stage stage = new Stage();
        stage.setTitle("Обновление Анкеты | StaffHarmony");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("./components/image/icons/logo-yoga.png"))));

        controller.setStage(stage);
        controller.setStageOptions();

        addNewFormButton.setDisable(true);
        updateFormButton.setDisable(true);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();

        tableViewForm.getItems().clear();
        initialize();
    }
}
