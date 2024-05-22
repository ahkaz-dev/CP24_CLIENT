package ru.harmony.cp24_client.controller.fuctional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.harmony.cp24_client.Entity.Access;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Worker;
import ru.harmony.cp24_client.service.entity.FormService;
import ru.harmony.cp24_client.service.entity.SpecService;
import ru.harmony.cp24_client.service.entity.VacancyService;
import ru.harmony.cp24_client.service.entity.WorkerService;

import java.util.Optional;

public class AddWorkerController {
    private final WorkerService service = new WorkerService();
    private final SpecService serviceSpec = new SpecService();

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

    private Stage stage;

    private Button addNewFormButton;
    private Button updateFormButton;

    @Setter
    @Getter
    private Optional<Worker> workerGive;

    public void start() {
        if (workerGive == null)  workerGive = Optional.empty();
        if(workerGive.isPresent()) {
//            formHeader.setText(formGive.get().getFormHeader());
//            aspSpec.setText(formGive.get().getAspSpec());
//            name.setText(formGive.get().getName());
//            surname.setText(formGive.get().getSurName());
//            lastname.setText(formGive.get().getLastName());
//            aspSkills.setText(formGive.get().getAspSkills());
//            workExperience.setText(formGive.get().getWorkExperience());
//            education.setText(formGive.get().getEducation());
//            workBefore.setText(formGive.get().getWorkBefore());
//            aspBirthDate.setText(formGive.get().getAspBirthDate());
//            comboBoxVacancy.setValue(formGive.get().getVacancy());
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
        if (!formHeader.getText().isEmpty() &&
                !aspSpec.getText().isEmpty() &&
                !name.getText().isEmpty() &&
                !surname.getText().isEmpty() &&
                !lastname.getText().isEmpty() &&
                !aspSkills.getText().isEmpty() &&
                !workExperience.getText().isEmpty() &&
                !education.getText().isEmpty() &&
                !workBefore.getText().isEmpty() &&
                !aspBirthDate.getText().isEmpty() &&
                comboBoxVacancy.getSelectionModel().getSelectedItem() != null) {
            // Все поля заполнены
            workerTemp.setFormHeader(formHeader.getText());
            workerTemp.setAspSpec(aspSpec.getText());
            workerTemp.setName(name.getText());
            workerTemp.setSurName(surname.getText());
            workerTemp.setLastName(lastname.getText());
            workerTemp.setAspSkills(aspSkills.getText());
            workerTemp.setWorkExperience(workExperience.getText());
            workerTemp.setEducation(education.getText());
            workerTemp.setWorkBefore(workBefore.getText());
            workerTemp.setAspBirthDate(aspBirthDate.getText());
            workerTemp.setVacancy(comboBoxVacancy.getSelectionModel().getSelectedItem());
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
