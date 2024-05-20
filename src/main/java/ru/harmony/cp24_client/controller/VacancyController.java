package ru.harmony.cp24_client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ru.harmony.cp24_client.service.entity.VacancyService;

public class VacancyController {
    @FXML
    public ListView dataList;
    private final VacancyService service = new VacancyService();


    @FXML
    private void initialize() {
        try {
            service.getAll();
            dataList.setItems(service.getVacancy());
        } catch (Exception e ) {
/*            alert.setTitle("Ошибка!");
            alert.setHeaderText("Отсутствует подключение к серверу ");
            alert.setContentText("Обратитесь в тех.поддержку.....");
            alert.showAndWait();
            dialogStage.close();*/
            System.out.println(e);
        }
    }
}
