package ru.harmony.cp24_client.controller.fuctional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.HelloApplication;
import ru.harmony.cp24_client.controller.FormController;
import ru.harmony.cp24_client.service.entity.FormService;
import ru.harmony.cp24_client.service.entity.VacancyService;

import java.util.Objects;
import java.util.Optional;

public class AddFormController {
    private final FormService service = new FormService();
    private final VacancyService serviceVacancy = new VacancyService();

    public TextField formHeader;
    public TextField aspSpec;
    public TextField name;
    public TextField surname;
    public TextField lastname;
    public TextField aspSkills;
    public TextField workExperience;
    public TextField education;
    public TextField workBefore;
    public TextField aspBirthDate;
    public ComboBox<Vacancy> comboBoxVacancy;
    public Label labelBirthData;
    public Label labelVacancy;
    public Label labelHeader;
    public Label labelSpec;
    public Label labelSurname;
    public Label labelLastname;
    public Label labelName;
    public Label labelSkills;
    public Label labelWorkbefore;
    public Label labelEduc;
    public Label labelCompanyName;

    private Stage stage;
    FormController controller = new FormController();
    private Button addNewFormButton;
    private Button updateFormButton;
    private Form form;

    @Setter
    @Getter
    private Optional<Form> formGive;

    public void start() {
        if (formGive == null)  formGive = Optional.empty();
        if(formGive.isPresent()) {
            formHeader.setText(formGive.get().getFormHeader());
            aspSpec.setText(formGive.get().getAspSpec());
            name.setText(formGive.get().getName());
            surname.setText(formGive.get().getSurName());
            lastname.setText(formGive.get().getLastName());
            aspSkills.setText(formGive.get().getAspSkills());
            workExperience.setText(formGive.get().getWorkExperience());
            education.setText(formGive.get().getEducation());
            workBefore.setText(formGive.get().getWorkBefore());
            aspBirthDate.setText(formGive.get().getAspBirthDate());
            comboBoxVacancy.setValue(formGive.get().getVacancy());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStageOptions() {
    }

    @FXML
    private void initialize() {
        try {
            serviceVacancy.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        comboBoxVacancy.setItems(serviceVacancy.getVacancy());
    }

    public void setButtons(Button addNewFormButton, Button updateFormButton) {
        this.addNewFormButton = addNewFormButton;
        this.updateFormButton = updateFormButton;
    }

    public void setVacancy(Optional<Form> formGive) {
        this.formGive = formGive;
    }

    public void handelAddButton(ActionEvent event) {
        Form tempForm = new Form();
        if (!formHeader.getText().isEmpty() &&
                !aspSpec.getText().isEmpty() &&
                !name.getText().isEmpty() &&
                !surname.getText().isEmpty() &&
                !lastname.getText().isEmpty() &&
                !aspSkills.getText().isEmpty() &&
                !workExperience.getText().isEmpty() &&
                !education.getText().isEmpty() &&
                !workBefore.getText().isEmpty() &&
                !aspBirthDate.getText().isEmpty() && aspBirthDate.getText().matches("^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(\\d{4})$") &&
                comboBoxVacancy.getSelectionModel().getSelectedItem() != null) {
            // Все поля заполнены
            tempForm.setFormHeader(formHeader.getText());
            tempForm.setAspSpec(aspSpec.getText());
            tempForm.setName(name.getText());
            tempForm.setSurName(surname.getText());
            tempForm.setLastName(lastname.getText());
            tempForm.setAspSkills(aspSkills.getText());
            tempForm.setWorkExperience(workExperience.getText());
            tempForm.setEducation(education.getText());
            tempForm.setWorkBefore(workBefore.getText());
            tempForm.setAspBirthDate(aspBirthDate.getText());
            tempForm.setVacancy(comboBoxVacancy.getSelectionModel().getSelectedItem());
            start();
            try {
                if (formGive.isEmpty()) {
                    service.add(tempForm);
                    stage.close();
                } else {
                    tempForm.setId(formGive.get().getId());
                    service.update(tempForm, formGive.get());
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
            labelBirthData.setStyle("-fx-text-fill: red;");
            labelVacancy.setVisible(true); labelVacancy.setStyle("-fx-text-fill: red;");
            labelEduc.setStyle("-fx-text-fill: red;");
            labelHeader.setStyle("-fx-text-fill: red;");
            labelName.setStyle("-fx-text-fill: red;");
            labelSkills.setStyle("-fx-text-fill: red;");
            labelLastname.setStyle("-fx-text-fill: red;");
            labelSpec.setStyle("-fx-text-fill: red;");
            labelSurname.setStyle("-fx-text-fill: red;");
            labelWorkbefore.setStyle("-fx-text-fill: red;");
            labelCompanyName.setStyle("-fx-text-fill: red;");
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
