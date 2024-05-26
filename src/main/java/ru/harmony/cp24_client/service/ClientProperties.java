package ru.harmony.cp24_client.service;

import lombok.Getter;
import ru.harmony.cp24_client.HelloApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {
    private final Properties properties = new Properties();

    private String allVacancy;
    private String saveVacancy;
    private String deleteVacancy;
    private String getUpdateVacancy;

    private String allSpec;

    private String allForm;
    private String saveForm;
    private String deleteForm;
    private String updateForm;

    private String allWorker;
    private String saveWorker;
    private String deleteWorker;
    private String updateWorker;


    private String allAccess;

    private String allUser;
    private String deleteUser;
    private String saveUser;
    private String updateUser;
    private String allUserData;
    private String allUserDataByAccess;
    private String adminByData;

    public ClientProperties() {
        try (InputStream inputStream = HelloApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(inputStream);
            properties.load(inputStream);

            allVacancy = properties.getProperty("vacancy.getAll");
            saveVacancy = properties.getProperty("vacancy.save");
            deleteVacancy = properties.getProperty("vacancy.delete");
            getUpdateVacancy = properties.getProperty("vacancy.update");

            allForm = properties.getProperty("form.getAll");
            saveForm = properties.getProperty("form.save");
            updateForm = properties.getProperty("form.update");
            deleteForm = properties.getProperty("form.delete");

            allSpec = properties.getProperty("spec.getAll");

            allWorker = properties.getProperty("worker.getAll");
            saveWorker = properties.getProperty("worker.save");
            updateWorker = properties.getProperty("worker.update");
            deleteWorker = properties.getProperty("worker.delete");

            allAccess = properties.getProperty("access.getAll");

            allUser = properties.getProperty("user.getAll");
            deleteUser = properties.getProperty("user.delete");
            saveUser = properties.getProperty("user.save");
            updateUser = properties.getProperty("user.update");
            allUserData = properties.getProperty("user.getData");
            allUserDataByAccess = properties.getProperty("user.getDataByAccess");
            adminByData = properties.getProperty("user.getAdminByData");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}