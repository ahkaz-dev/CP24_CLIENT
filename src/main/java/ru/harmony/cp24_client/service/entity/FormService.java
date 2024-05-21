package ru.harmony.cp24_client.service.entity;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.Response.DataResponse;
import ru.harmony.cp24_client.Response.ListResponse;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.JsonService;

import java.lang.reflect.Type;

public class FormService {
    @Getter
    private ObservableList<Form> forms = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    private Type dataType = new TypeToken<DataResponse<Form>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Form>>() {}.getType();

    public void getAll() {
        ListResponse<Form> formList = new ListResponse<>();
        formList = json.getObject(httpService.get(client_property.getAllForm()), listType);
        if (formList.isStatus()) {
            this.forms.addAll(formList.getData());
        } else {
            throw new RuntimeException(formList.getStatus_text());
        }
    }
}
