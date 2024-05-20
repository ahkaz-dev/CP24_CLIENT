package ru.harmony.cp24_client.service.entity;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.User;
import ru.harmony.cp24_client.Response.DataResponse;
import ru.harmony.cp24_client.Response.ListResponse;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.JsonService;

import java.lang.reflect.Type;

public class UserService {
    @Getter
    private ObservableList<User> user = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    private Type dataType = new TypeToken<DataResponse<User>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<User>>() {
    }.getType();

    public boolean findByData(String login, String password) {
        String tempData = httpService.get(client_property.getAllUserData() + login + "&password=" + password);
        DataResponse<User> response = json.getObject(tempData, dataType);
        System.out.println(response);

        return response.isStatus();
    }
}
