package ru.harmony.cp24_client.service.entity;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.Spec;
import ru.harmony.cp24_client.Entity.User;
import ru.harmony.cp24_client.Response.DataResponse;
import ru.harmony.cp24_client.Response.ListResponse;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.JsonService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;

public class SpecService {
    @Getter
    private ObservableList<Spec> spec = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    private Type dataType = new TypeToken<DataResponse<User>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<User>>() {
    }.getType();

    public void getAll() {
        ListResponse<Spec> genreList = new ListResponse<>();
        genreList = json.getObject(httpService.get(client_property.getAllSpec()), listType);
        if (genreList.isStatus()) {
            this.spec.addAll(genreList.getData());
        } else {
            throw new RuntimeException(genreList.getStatus_text());
        }
    }
}
