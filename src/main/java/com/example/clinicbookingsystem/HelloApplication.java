package com.example.clinicbookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Database database = new Database("database/clinic.db");
    @Override
    public void start(Stage stage) throws IOException {
        database.initializeDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Clinic Appointment Booking System - Login!");
        stage.setScene(scene);
        stage.show();
    }
}
