module com.endproject.javafundamentalsendproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.endproject.javafundamentalsendproject to javafx.fxml;
    exports com.endproject.javafundamentalsendproject;
    opens com.endproject.javafundamentalsendproject.Model to javafx.fxml;
    exports com.endproject.javafundamentalsendproject.Model;
    opens com.endproject.javafundamentalsendproject.Controller to javafx.fxml;
    exports com.endproject.javafundamentalsendproject.Controller;
    opens com.endproject.javafundamentalsendproject.Data to javafx.fxml;
    exports com.endproject.javafundamentalsendproject.Data;

}