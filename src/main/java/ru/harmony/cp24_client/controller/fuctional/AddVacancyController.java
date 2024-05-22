package ru.harmony.cp24_client.controller.fuctional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.harmony.cp24_client.Entity.Spec;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.controller.VacancyController;
import ru.harmony.cp24_client.service.entity.SpecService;
import ru.harmony.cp24_client.service.entity.VacancyService;

import java.util.Optional;

public class AddVacancyController {
    private final VacancyService service = new VacancyService();
    private final SpecService specService = new SpecService();
    public ComboBox<Spec> comboBoxSpec;
    public TextField headCountField;
    public TextField workExperienceField;
    public TextField employeeField;
    public TextField wageField;
    public TextField headerVacancyField;
    @FXML
    public Button addNewVacancyModal;
    private Stage stage;

    VacancyController controller = new VacancyController();
    private Button addNewVacancyButton;
    private Button updateVacancyButton;
    private Vacancy vacancy;

    @Setter
    @Getter
    private Optional<Vacancy> vacancyGive;

    public void start() {
        if(vacancyGive.isPresent()) {
            headCountField.setText(vacancyGive.get().getHeadcount());
            workExperienceField.setText(vacancyGive.get().getWorkExperience());
            comboBoxSpec.setValue(vacancyGive.get().getSpec());
            employeeField.setText(vacancyGive.get().getFromEmployer());
            wageField.setText(vacancyGive.get().getWage());
            headerVacancyField.setText(vacancyGive.get().getName());

            addNewVacancyModal.setText("Изменить");
            System.out.println(vacancyGive);
            }
        }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        try {
            specService.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        comboBoxSpec.setItems(specService.getSpec());
    }

    public void setAddNewVacancyButton(Button addNewVacancyButton, Button updateVacancyButton) {
        this.addNewVacancyButton = addNewVacancyButton;
        this.updateVacancyButton = updateVacancyButton;
    }

    public void setVacancy(Optional<Vacancy> vacancyGive) {
        this.vacancyGive = vacancyGive;
    }

    public void handelAddButton(ActionEvent event) {
        Vacancy tempVacancy = new Vacancy();
        if (comboBoxSpec.getSelectionModel().getSelectedItem() != null &&
                !headCountField.getText().isEmpty() &&
                !workExperienceField.getText().isEmpty() &&
                !employeeField.getText().isEmpty() &&
                !wageField.getText().isEmpty() &&
                !headerVacancyField.getText().isEmpty()) {
            // Все поля заполнены
            tempVacancy.setSpec(comboBoxSpec.getSelectionModel().getSelectedItem());
            tempVacancy.setHeadcount(headCountField.getText());
            tempVacancy.setWorkExperience(workExperienceField.getText());
            tempVacancy.setFromEmployer(employeeField.getText());
            tempVacancy.setWage(wageField.getText());
            tempVacancy.setName(headerVacancyField.getText());
            try {
                if (vacancyGive.isEmpty()) {
                    service.add(tempVacancy);
                    stage.close();

                } else {
                    tempVacancy.setId(vacancyGive.get().getId());
                    service.update(tempVacancy, vacancyGive.get());
                    stage.close();
                }
/*                try {
                    service.add(tempVacancy);
                    stage.close();
                } catch (Exception e) {
                    service.update(tempVacancy, vacancyGive.get());
                }*/
                if (addNewVacancyButton != null) {
                    addNewVacancyButton.setDisable(false);
                    updateVacancyButton.setDisable(false);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            // Не все поля заполнены
            System.out.println("error");
        }
    }

    public void handlerCancelButton(ActionEvent event) {
        stage.close();
        if (addNewVacancyButton != null && updateVacancyButton != null) {
            addNewVacancyButton.setDisable(false);
            updateVacancyButton.setDisable(false);
        }
    }
}
