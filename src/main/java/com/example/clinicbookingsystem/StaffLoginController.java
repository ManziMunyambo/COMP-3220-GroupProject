package com.example.clinicbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffLoginController {

    @FXML
    private Label staffIdLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private String currentStaffId;

    public void initData(String staffId) {
        this.currentStaffId = staffId;
        if (staffIdLabel != null) {
            staffIdLabel.setText("Staff ID: " + staffId);
        }
    }

    @FXML
    private void onSignInButtonClick(ActionEvent event) {
        String password = passwordField.getText();

        if (password == null || password.trim().isEmpty()) {
            statusLabel.setText("Please enter your staff password.");
            return;
        }

        statusLabel.setText("");
        System.out.println("Authenticating Staff ID: " + currentStaffId);
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Clinic Booking System - Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Failed to return to main login.");
        }
    }
}
