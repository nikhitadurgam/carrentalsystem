package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
     public static Connection getConnection() throws Exception{
         var properties = DBPropertyUtil.getConnectionString("DBConfig.properties").split(" ");
         final String URL = properties[0];
         final String USER = properties[1];
         final String PASSWORD = properties[2];
//         Class.forName("com.mysql.jdbc.Driver");
         return DriverManager.getConnection(URL, USER, PASSWORD);
     }
}
