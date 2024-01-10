package com.endproject.javafundamentalsendproject.Controller;

import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.CompanyRole;
import com.endproject.javafundamentalsendproject.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Button createOrderButton;
    @FXML
    public Button productInventoryButton;
    @FXML
    private VBox pageVBox;
    @FXML
    private BorderPane borderPane;
    private User loggedInUser;
    Database database;
    FXMLLoader fxmlLoader;
    private DashboardController dashboardController;

    public void mainController(User user) {
        loggedInUser = user;
        loadScene("/com/endproject/javafundamentalsendproject/DashboardView.fxml", new DashboardController(database));

        dashboardController = fxmlLoader.getController();
        dashboardController.initUser(loggedInUser);

        createOrderButton.setVisible(loggedInUser.getCompanyRoll() == CompanyRole.sales);
        productInventoryButton.setVisible(loggedInUser.getCompanyRoll() == CompanyRole.manager);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = new Database();
    }



    private void loadScene(String scene, Object controller) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(scene));
            fxmlLoader.setController(controller);
            VBox view = fxmlLoader.load();
            borderPane.setCenter(view);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while loading the scene: " + e.getMessage(), e);
        }
    }

    public void onDashBoardButtonClick(ActionEvent actionEvent) {
        mainController(loggedInUser);
    }

    public void onCreateOrderButtonClick(ActionEvent actionEvent) {
        if(loggedInUser.getCompanyRoll() != CompanyRole.manager) {
            loadScene("/com/endproject/javafundamentalsendproject/CreateOrderView.fxml", new CreateOrderController(database));
        }
    }

    public void onProductInventoryButtonClick(ActionEvent actionEvent) {
        if (loggedInUser.getCompanyRoll() != CompanyRole.sales) {
            loadScene("/com/endproject/javafundamentalsendproject/ProductInventoryView.fxml", new ProductInventoryController(database));
        }
    }

    public void onOrderHistoryButtonClick(ActionEvent actionEvent) {
        loadScene("/com/endproject/javafundamentalsendproject/OrderHistoryView.fxml", new OrderHistoryController(database));
    }


}
