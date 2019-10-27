package com.example.sweater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.sqlite.core.DB;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static DBManager manager = new DBManager();

    public static void main(String[] args) {

        manager.connectToDataBase();
        try {
            manager.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TableModel model = new TableModel();
        model.array = GreetingController.getRandomArray();
        try {
            manager.removeData();
            manager.insert(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SpringApplication.run(Application.class, args);
    }
}
