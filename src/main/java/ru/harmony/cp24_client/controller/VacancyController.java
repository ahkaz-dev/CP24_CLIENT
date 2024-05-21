package ru.harmony.cp24_client.controller;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.entity.SpecService;
import ru.harmony.cp24_client.service.entity.VacancyService;

public class VacancyController {

    private final VacancyService service = new VacancyService();
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
    private void initialize() {
        try {
            service.getAll();
        } catch (Exception e ) {
            System.out.println(e);
        }
        headerVacancy.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("name"));
        employee.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("fromEmployer"));
        wage.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("wage"));
        workExperience.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("workExperience"));
        headCount.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("headcount"));
        spec.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("spec"));



        tableVIewMain.setItems(service.getVacancy());
    }
}
