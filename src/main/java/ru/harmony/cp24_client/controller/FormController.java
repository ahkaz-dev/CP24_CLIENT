package ru.harmony.cp24_client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.service.entity.FormService;

import javafx.beans.binding.Bindings;


public class FormController {
    @FXML
    public TableView<Form> tableViewForm;

    @FXML
    public TableColumn<Form, String> formHeader;
    @FXML
    public TableColumn<Form, String> formAspSpec;
    @FXML
    public TableColumn<Form, String> formFio;

    @FXML
    public TableColumn<Form, String> formName;

    @FXML
    public TableColumn<Form, String> formLastName;

    @FXML
    public TableColumn<Form, String> formSurName;
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


    public Vacancy vacancy;

    @FXML
    private void initialize() {

        try {
            service.getAll();
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
    }
}
