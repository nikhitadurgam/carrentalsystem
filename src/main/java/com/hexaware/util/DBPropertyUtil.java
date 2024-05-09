package com.hexaware.util;

import com.hexaware.constant.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String fileName) {
        File file = new File(fileName);
        String connectionString = "";
        try(var fr = new FileReader(file)) {
            var properties = new Properties();
            properties.load(fr);
            connectionString =
                    properties.getProperty("url") + Constants.SPACE + properties.getProperty(
                    "user") + Constants.SPACE + properties.getProperty("password");
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return connectionString;
    }
}
