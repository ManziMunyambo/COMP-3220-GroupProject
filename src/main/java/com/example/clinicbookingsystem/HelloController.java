package com.example.clinicbookingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("patient-login-view.fxml"));
                Scene scene = new Scene(loader.load());

                // Pass the entered ID into PatientLoginController
                PatientLoginController controller = loader.getController();
                controller.initData(enteredId);

                // Get current window from any control on this view (e.g. idField)
                Stage stage = (Stage) idField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Patient Portal Authentication");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Failed to load patient login view.");
            }

        } else if (staffRadio.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("staff-login-view.fxml"));
                Scene scene = new Scene(loader.load());

                StaffLoginController controller = loader.getController();
                controller.initData(enteredId);

                Stage stage = (Stage) idField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Staff Portal Authentication");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Failed to load staff login view.");
            }
        }else {
            messageLabel.setText("Please select a role.");
        }
    }
}