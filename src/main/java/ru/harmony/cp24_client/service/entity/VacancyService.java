package ru.harmony.cp24_client.service.entity;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import ru.harmony.cp24_client.Entity.User;
import ru.harmony.cp24_client.Entity.Vacancy;
import ru.harmony.cp24_client.Response.DataResponse;
import ru.harmony.cp24_client.Response.ListResponse;
import ru.harmony.cp24_client.service.ClientProperties;
import ru.harmony.cp24_client.service.HttpService;
import ru.harmony.cp24_client.service.JsonService;

import java.lang.reflect.Type;
import java.util.List;

public class VacancyService {
    @Getter
    private ObservableList<Vacancy> vacancy = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    private Type dataType = new TypeToken<DataResponse<Vacancy>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Vacancy>>() {}.getType();

    public void getAll() {
        ListResponse<Vacancy> vacancyList = new ListResponse<>();
        vacancyList = json.getObject(httpService.get(client_property.getAllVacancy()), listType);
        if (vacancyList.isStatus()) {
            this.vacancy.addAll(vacancyList.getData());
        } else {
            throw new RuntimeException(vacancyList.getStatus_text());
        }
    }

    public void add(Vacancy vacancy) {
        String tempData = httpService.post(client_property.getSaveVacancy(), json.getJson(vacancy));
        DataResponse<Vacancy> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.vacancy.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }
}
