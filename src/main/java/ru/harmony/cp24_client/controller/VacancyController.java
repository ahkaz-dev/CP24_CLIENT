package ru.harmony.cp24_client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import ru.harmony.cp24_client.Entity.Spec;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.service.entity.SpecService;
import ru.harmony.cp24_client.service.entity.VacancyService;

import java.util.Optional;

public class VacancyController {

    private final VacancyService service = new VacancyService();
    private final SpecService specService = new SpecService();
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
    public Button addNewVacancyButton;


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

    public void handleAddButton(ActionEvent event) {
        Dialog<Pair<String, String>> modalRegistration = new Dialog<>();
        //modalRegistration.setResizable(false);
        // setTitle | setHeaderText

        ButtonType login = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        modalRegistration.getDialogPane().getButtonTypes().addAll(login, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField headerVacancyField = new TextField();
        headerVacancyField.setPromptText("Заголовок вакансии");

        TextField wageField = new TextField();
        wageField.setPromptText("ЗП");

        TextField employeeField = new TextField();
        employeeField.setPromptText("Компания");

        TextField workExperienceField = new TextField();
        workExperienceField.setPromptText("Опыт работы");

        TextField headCountField = new TextField();
        headCountField.setPromptText("Кол-во сотрудников");

        comboBoxSpec.setPromptText("Специализация");

        grid.add(new Label("Заголовок"), 0, 0);
        grid.add(headerVacancyField, 1, 0);

        grid.add(new Label("ЗП"), 0, 1);
        grid.add(wageField, 1, 1);

        grid.add(new Label("Компания"), 0, 2);
        grid.add(employeeField, 1, 2);

        grid.add(new Label("Опыт работы"), 0, 3);
        grid.add(workExperienceField, 1, 3);

        grid.add(new Label("Кол-во сотрудников"), 0, 4);
        grid.add(headCountField, 1, 4);

        grid.add(new Label("Специализация"), 0, 5);
        grid.add(comboBoxSpec, 1, 5);

        modalRegistration.getDialogPane().setContent(grid);

//        Optional<Pair<String, String>> result = modalRegistration.showAndWait();

        Vacancy vacancy = new Vacancy();
        vacancy.setName(headerVacancyField.getText());
        vacancy.setWage(wageField.getText());
        vacancy.setSpec(comboBoxSpec.getSelectionModel().getSelectedItem());
        vacancy.setHeadcount(headCountField.getText());
        vacancy.setFromEmployer(employeeField.getText());
        vacancy.setWorkExperience(workExperienceField.getText());
        service.add(vacancy);

        tableVIewMain.getItems().clear();
        initialize();

        modalRegistration.show();
    }
}
