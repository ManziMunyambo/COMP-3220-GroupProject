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
    private Label messageLabel;

    @FXML
    protected void handleLogin() {
        String enteredId = idField.getText().trim();

        if (enteredId.isEmpty()) {
            messageLabel.setText("Please enter an ID to log in.");
            return;
        }
        if (patientRadio.isSelected()) {
            if(database.selectPatient(enteredId) != null){
                messageLabel.setText("Navigating to Patient Dashboard for ID: " + enteredId);

            }else{
                messageLabel.setText("Could not find the patient");
            }
        } else if (staffRadio.isSelected()) {
            if(database.selectReceptionist(Integer.parseInt(enteredId)) != null){
                messageLabel.setText("Navigating to Receptionist Dashboard for ID: " + enteredId);
            }else{
                messageLabel.setText("Could not find the receptionist");
            }        
        } else {
            messageLabel.setText("Please select a role.");
        }
    }
}
