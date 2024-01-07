package com.endproject.javafundamentalsendproject.Controller;
import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class DashboardController {

    @FXML
    private Label showDateTime;
    @FXML
    private Label showRole;
    @FXML
    private Label showName;
    private User loggedInUser;


    public DashboardController(Database database) {

    }


    public void initUser(User user) {
        loggedInUser = user;
        showUserInfo();
        showDateTime();
    }

    private void showUserInfo() {
        String fullName = loggedInUser.getFirstname() + " " + loggedInUser.getLastname();
        showName.setText("Welcome " + fullName + "!");

        showRole.setText("Your role is: " + loggedInUser.getCompanyRoll().toString());

    }

    private void showDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(zoneId);

        showDateTime.setText("It is now: " + (formatter.format(localDateTime)));
    }


}
