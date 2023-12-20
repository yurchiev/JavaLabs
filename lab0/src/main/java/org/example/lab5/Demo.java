package org.example.lab5;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/lab5";
        String username = "root";
        String password = "admin";
        DBService service = new DBService(url,username,password);

        //change for creating or droping
        service.createAllTables();
     //service.dropAllTables();
    }
}
