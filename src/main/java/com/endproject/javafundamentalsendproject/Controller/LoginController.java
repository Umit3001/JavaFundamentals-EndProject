package com.endproject.javafundamentalsendproject.Controller;

import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label errorLabel;
    private Database databaseUsers;


    public LoginController() {
        databaseUsers = new Database();
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent e) throws IOException {
        validLoginInCheck();
    }


    public void validLoginInCheck() throws IOException {
        User loggedInUser = getUser();
        if(getUser() != null) {

            openMainView(loggedInUser);
        }
        else {
            errorLabel.setText("Invalid username/password");
        }
    }

    protected void openMainView(User loggedInUser) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/endproject/javafundamentalsendproject/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add((this.getClass().getResource("/css/main.css")).toExternalForm());

        MainController mainController = fxmlLoader.getController();
        mainController.mainController(loggedInUser);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Umit's Music Dungeon");
        stage.setOnCloseRequest(this::onCloseRequest);
        stage.show();

        //close current stage
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        currentStage.close();
    }

    private void onCloseRequest(WindowEvent event) {

        databaseUsers.saveToFile();

    }

    private User getUser() {

        for (User user : databaseUsers.getUsers()) {
            if (user.getUsername().equals(usernameTextField.getText()) && user.getPassword().equals(passwordTextField.getText())) {
                return user;
            }
        }
        return null;
    }



}


