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
import ru.harmony.cp24_client.service.entity.VacancyService;

import java.io.IOException;
import java.util.Optional;

public class VacancyController {

    private final VacancyService service = new VacancyService();
    private final SpecService specService = new SpecService();
    @FXML
    public ComboBox<Spec> comboBoxSpec = new ComboBox<>();
    public Button deleteVacancyButton;

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
    public Button updateVacancyButton;
    @FXML
    private Button addNewVacancyButton;

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


    @FXML
    private void initialize() {
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
    }

    public void handleAddButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newVacancy-view.fxml"));
        Parent root = loader.load();
        AddVacancyController controller = loader.getController();
        controller.setAddNewVacancyButton(addNewVacancyButton);
        //controller.setTableVIewMain(tableVIewMain);
/*
        controller.setVacancyGive(vacancyOptional);
        controller.start();*/


        Stage stage = new Stage();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        addNewVacancyButton.setDisable(true);

        stage.showAndWait();
        tableVIewMain.getItems().clear();
        initialize();
    }
    public void callRefreshFunc() {
        tableVIewMain.getItems().clear();
        initialize();
    }

/*    @FXML
    void onMouseClickTableView(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                Vacancy tempVacancy = tableVIewMain.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newVacancy-view.fxml"));
                Parent root = loader.load();
                AddVacancyController controller = loader.getController();
                controller.setVacancyGive(Optional.ofNullable(tempVacancy));
                controller.start();

                Stage stage = new Stage();
                controller.setStage(stage);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.showAndWait();
*//*                textName.setText(tempVacancy.getName());
                textLastName.setText(tempVacancy.getLastname());
                textSurname.setText(tempVacancy.getSurname());*//*
            }
        }
    }*/

    public void handleDeleteButton(ActionEvent event) {
        Vacancy selectedVacancy = tableVIewMain.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            service.delete(selectedVacancy);
            tableVIewMain.getItems().clear();
            initialize();
        }
    }

    public void handleUpdateButton(ActionEvent event) throws IOException {
        Vacancy tempVacancy = tableVIewMain.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newVacancy-view.fxml"));
        Parent root = loader.load();
        AddVacancyController controller = loader.getController();
        controller.setVacancyGive(Optional.ofNullable(tempVacancy));
        controller.start();

        Stage stage = new Stage();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
