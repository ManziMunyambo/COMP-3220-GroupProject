module com.example.clinicbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clinicbookingsystem to javafx.fxml;
    exports com.example.clinicbookingsystem;
    exports com.example.clinicbookingsystem.model;
}