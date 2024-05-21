package ru.harmony.cp24_client.service.entity;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.Access;
import ru.harmony.cp24_client.Entity.Worker;
import ru.harmony.cp24_client.Response.DataResponse;
import ru.harmony.cp24_client.Response.ListResponse;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.JsonService;

import java.lang.reflect.Type;

public class AccessService {
    @Getter
    private ObservableList<Access> accesses = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    private Type dataType = new TypeToken<DataResponse<Access>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Access>>() {}.getType();

    public void getAll() {
        ListResponse<Access> accessList = new ListResponse<>();
        accessList = json.getObject(httpService.get(client_property.getAllAccess()), listType);
        if (accessList.isStatus()) {
            this.accesses.addAll(accessList.getData());
        } else {
            throw new RuntimeException(accessList.getStatus_text());
        }
    }
}
