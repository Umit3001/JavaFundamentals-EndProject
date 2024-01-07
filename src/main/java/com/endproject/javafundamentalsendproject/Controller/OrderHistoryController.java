package com.endproject.javafundamentalsendproject.Controller;

import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.Order;
import com.endproject.javafundamentalsendproject.Model.OrderItem;
import com.endproject.javafundamentalsendproject.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {

    @FXML
    private TableView<OrderItem> orderedProductsTableView;
    @FXML
    private TableView<Order> orderHistoryTableView;

    private Database database;

    private ObservableList<Order> orders;
    private  ObservableList<OrderItem> orderItems;

    public OrderHistoryController(Database database) {
        this.database = database;
        orders = FXCollections.observableArrayList(database.getOrders());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        orderHistoryTableView.setItems(orders);

        orderHistoryTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldOrder, newOrder) -> {
            if (newOrder != null) {
                orderItems = FXCollections.observableArrayList(newOrder.getCustomerProduct());
                orderedProductsTableView.setItems(orderItems);
            }
        });
    }

}
