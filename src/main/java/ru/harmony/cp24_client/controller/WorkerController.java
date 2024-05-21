package ru.harmony.cp24_client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.harmony.cp24_client.Entity.Worker;
import ru.harmony.cp24_client.service.entity.WorkerService;

public class WorkerController {
    public TableView<Worker> tableViewWorker;
    public TableColumn<Worker, String> name;
    public TableColumn<Worker, String> surName;
    public TableColumn<Worker, String> lastName;
    public TableColumn<Worker, String> position;
    public TableColumn<Worker, String> access;

    private final WorkerService service = new WorkerService();


    @FXML
    private void initialize() {
        try {
            service.getAll();
        } catch (Exception e ) {
            System.out.println(e);
        }
        name.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
        surName.setCellValueFactory(new PropertyValueFactory<Worker, String>("surName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Worker, String>("lastName"));
        position.setCellValueFactory(new PropertyValueFactory<Worker, String>("position"));
        access.setCellValueFactory(new PropertyValueFactory<Worker, String>("access"));

        tableViewWorker.setItems(service.getWorker());
    }
}
