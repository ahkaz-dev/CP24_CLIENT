package ru.harmony.cp24_client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.harmony.cp24_client.Entity.Spec;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.HelloApplication;
import ru.harmony.cp24_client.controller.fuctional.AddVacancyController;
import ru.harmony.cp24_client.service.entity.SpecService;
import ru.harmony.cp24_client.service.entity.UserService;
import ru.harmony.cp24_client.service.entity.VacancyService;

import java.io.IOException;
import java.util.Optional;

public class VacancyController {

    private final VacancyService service = new VacancyService();
    private final SpecService specService = new SpecService();
    private final UserService userService = new UserService();
    @FXML
    public ComboBox<Spec> comboBoxSpec = new ComboBox<>();

    @FXML
    public TableView<Vacancy> tableVIewMain;
    @FXML
    public TableColumn<Vacancy, String> headerVacancy;
    @FXML
    public TableColumn<Vacancy, String> wage;
    @FXML
    public TableColumn<Vacancy, String> employee;
    @FXML
    public TableColumn<Vacancy, String> workExperience;
    @FXML
    public TableColumn<Vacancy, String> headCount;
    @FXML
    public TableColumn<Vacancy, String> spec;
    @FXML
    private Button updateVacancyButton = new Button();
    @FXML
    private Button addNewVacancyButton = new Button();
    @FXML
    private Button deleteVacancyButton = new Button();


    private Optional<Vacancy> vacancyOptional;
    public void setVacancy(Optional<Vacancy> vacancy) {
        this.vacancyOptional = vacancy;
        if (vacancy.isPresent()) {
            if (vacancy.get().getId() != null) {
                service.update(vacancy.get(), tableVIewMain.getSelectionModel().getSelectedItem());
            } else {
                service.add(vacancy.get());
            }
        }
    }


    static private String login;
    static private String password;

    public void setLogin(String login) {
        VacancyController.login = login;
    }

    public void setPassword(String password) {
        VacancyController.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void checkAccess() {
        if (userService.findByDataAccess(login, password)) {
            addNewVacancyButton.setDisable(false);
            updateVacancyButton.setDisable(true);
            deleteVacancyButton.setDisable(false);
        } else {
            addNewVacancyButton.setDisable(true);
            updateVacancyButton.setDisable(true);
            deleteVacancyButton.setDisable(true);
        }
    }

    @FXML
    private void initialize() {
        checkAccess();
        try {
            service.getAll();
            specService.getAll();
        } catch (Exception e ) {
            System.out.println(e);
        }
        headerVacancy.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("name"));
        employee.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("fromEmployer"));
        wage.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("wage"));
        workExperience.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("workExperience"));
        headCount.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("headcount"));
        spec.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("spec"));

        comboBoxSpec.setItems(specService.getSpec());
        tableVIewMain.setItems(service.getVacancy());
/*        updateVacancyButton.setVisible(true);
        updateVacancyButton.setDisable(true);*/
        System.out.println(login + " " + password);
    }

    public void handleAddButton(ActionEvent event) throws IOException {
        checkAccess();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newVacancy-view.fxml"));
        Parent root = loader.load();
        AddVacancyController controller = loader.getController();
        controller.setAddNewVacancyButton(addNewVacancyButton, updateVacancyButton);

        Stage stage = new Stage();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.showAndWait();
        tableVIewMain.getItems().clear();
        initialize();

    }
    public void callRefreshFunc() {
        tableVIewMain.getItems().clear();
        initialize();
    }

    @FXML
    void onMouseClickTableView(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                if (tableVIewMain.getSelectionModel().getSelectedItem() != null) {
                    if (userService.findByDataAccess(login, password)) updateVacancyButton.setDisable(false);
                }
            }
        }
    }

    public void handleDeleteButton(ActionEvent event) {
        checkAccess();
        Vacancy selectedVacancy = tableVIewMain.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            service.delete(selectedVacancy);
            tableVIewMain.getItems().clear();
            initialize();
        }
    }

    public void handleUpdateButton(ActionEvent event) throws IOException {
        checkAccess();
        Vacancy tempVacancy = tableVIewMain.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newVacancy-view.fxml"));
        Parent root = loader.load();
        AddVacancyController controller = loader.getController();
        controller.setVacancyGive(Optional.ofNullable(tempVacancy));
        addNewVacancyButton.setDisable(true);
        controller.setAddNewVacancyButton(addNewVacancyButton, updateVacancyButton);
        controller.start();

        Stage stage = new Stage();
        controller.setStage(stage);

/*        addNewVacancyButton.setDisable(true);
        updateVacancyButton.setDisable(true);*/

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();

        tableVIewMain.getItems().clear();
        initialize();
    }
}
