package io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserProperty {
    private static Properties properties;


    static {
        properties = new Properties();
        try (InputStream inputStream = UserProperty.class.getClassLoader().getResourceAsStream("user.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Configuration file 'config.properties' not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file", e);
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

}
