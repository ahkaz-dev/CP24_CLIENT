package ru.harmony.cp24_client.service.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Response.BaseResponse;
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

    public void add(Worker worker) {
        String tempData = httpService.post(client_property.getSaveWorker(), json.getJson(worker));
        DataResponse<Worker> response = json.getObject(tempData, dataType);
        if (response != null) {
            this.worker.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(Worker worker_new, Worker worker_main) {
        String tempData = httpService.put(client_property.getUpdateWorker(), json.getJson(worker_new));
        DataResponse<Worker> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.worker.remove(worker_main);
            this.worker.add(worker_new);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(Worker worker) {
        String tempData= httpService.delete(client_property.getDeleteWorker(), worker.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.worker.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }
}
