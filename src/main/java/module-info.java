module com.example.pt2022_30423_lungoci_toma_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pt2022_30423_lungoci_toma_assignment_1 to javafx.fxml;
    exports com.example.pt2022_30423_lungoci_toma_assignment_1;
    exports com.example.pt2022_30423_lungoci_toma_assignment_1.controller;
    opens com.example.pt2022_30423_lungoci_toma_assignment_1.controller to javafx.fxml;
}