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

    private String allSpec;

    private String allForm;

    private String allWorker;

    private String allAccess;

    private String allUser;
    private String deleteUser;
    private String saveUser;
    private String updateUser;
    private String allUserData;

    public ClientProperties() {
        try (InputStream inputStream = HelloApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(inputStream);
            properties.load(inputStream);

            allVacancy = properties.getProperty("vacancy.getAll");
            saveVacancy = properties.getProperty("vacancy.save");

            allForm = properties.getProperty("form.getAll");

            allSpec = properties.getProperty("spec.getAll");

            allWorker = properties.getProperty("worker.getAll");

            allAccess = properties.getProperty("access.getAll");

            allUser = properties.getProperty("user.getAll");
            deleteUser = properties.getProperty("user.delete");
            saveUser = properties.getProperty("user.save");
            updateUser = properties.getProperty("user.update");
            allUserData = properties.getProperty("user.getData");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}