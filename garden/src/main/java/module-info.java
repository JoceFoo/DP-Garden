module com.dp.group9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.dp.group9 to javafx.fxml;
    exports com.dp.group9;
}
