package ru.harmony.cp24_client.service.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import ru.harmony.cp24_client.Response.DataResponse;
import ru.harmony.cp24_client.Response.ListResponse;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.Worker;
import ru.harmony.cp24_client.Response.ListResponse;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.JsonService;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WorkerService {
    @Getter
    private ObservableList<Worker> worker = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    private Type dataType = new TypeToken<DataResponse<Worker>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Worker>>() {}.getType();

    public void getAll() {
        ListResponse<Worker> workerList = new ListResponse<>();
        workerList = json.getObject(httpService.get(client_property.getAllWorker()), listType);
        if (workerList.isStatus()) {
            this.worker.addAll(workerList.getData());
        } else {
            throw new RuntimeException(workerList.getStatus_text());
        }
    }
}
