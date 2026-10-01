package com.example.clinicbookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private RadioButton patientRadio;

    @FXML
    private RadioButton staffRadio;

    @FXML
    private TextField idField;

    @FXML
    private Label messageLabel;

    @FXML
    protected void handleLogin() {
        String enteredId = idField.getText().trim();

        if (enteredId.isEmpty()) {
            messageLabel.setText("Please enter an ID to log in.");
            return;
        }

        if (patientRadio.isSelected()) {
            messageLabel.setText("Navigating to Patient Dashboard for ID: " + enteredId);
        } else if (staffRadio.isSelected()) {
            messageLabel.setText("Navigating to Receptionist Dashboard for ID: " + enteredId);
        } else {
            messageLabel.setText("Please select a role.");
        }
    }
}
