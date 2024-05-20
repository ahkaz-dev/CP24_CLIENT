package ru.harmony.cp24_client.service;

import lombok.Getter;
import ru.harmony.cp24_client.HelloApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {
    private final Properties properties = new Properties();

    private String allUser;
    private String deleteUser;
    private String saveUser;
    private String updateUser;
    private String allUserData;

    public ClientProperties() {
        try (InputStream inputStream = HelloApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(inputStream);
            properties.load(inputStream);

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