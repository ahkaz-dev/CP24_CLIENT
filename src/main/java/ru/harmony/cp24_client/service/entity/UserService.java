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

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

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
        return response.isStatus();
    }

    public boolean checkServerConnect() {
        try (Socket socket = new Socket("localhost", 28245)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
