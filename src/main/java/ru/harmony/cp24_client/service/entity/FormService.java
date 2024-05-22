package ru.harmony.cp24_client.service.entity;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.Form;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.Response.BaseResponse;
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

    public void add(Form form) {
        String tempData = httpService.post(client_property.getSaveForm(), json.getJson(form));
        DataResponse<Form> response = json.getObject(tempData, dataType);
        if (response != null) {
            this.forms.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(Form form_new, Form form_main) {
        String tempData = httpService.put(client_property.getUpdateForm(), json.getJson(form_new));
        DataResponse<Form> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.forms.remove(form_main);
            this.forms.add(form_new);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(Form form) {
        String tempData= httpService.delete(client_property.getDeleteForm(), form.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.forms.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }
}
