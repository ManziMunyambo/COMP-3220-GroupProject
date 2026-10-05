package com.example.clinicbookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class HelloController {
    private Database database = new Database("database/clinic.db");

    @FXML
    private RadioButton patientRadio;

    @FXML
    private RadioButton staffRadio;

    @FXML
    private TextField idField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    protected void handleLogin() {
        String enteredId = idField.getText().trim();
        String enteredPassword = passwordField.getText().trim();

        if (enteredId.isEmpty()) {
            messageLabel.setText("Please enter an ID to log in.");
            return;
        }
        if (patientRadio.isSelected()) {
            int id = Integer.parseInt(enteredId);
            if(database.selectPatient(id, enteredPassword) != null){
                messageLabel.setText("Navigating to Patient Dashboard for ID: " + id);

            }else{
                messageLabel.setText("Could not find the patient with specified ID and password");
            }
        } else if (staffRadio.isSelected()) {
            int id = Integer.parseInt(enteredId);
            if(database.selectReceptionist(id, enteredPassword) != null){
                messageLabel.setText("Navigating to Receptionist Dashboard for ID: " + id);
            }else{
                messageLabel.setText("Could not find the receptionist with specified ID and password");
            }        
        } else {
            messageLabel.setText("Please select a role.");
        }
    }
}
