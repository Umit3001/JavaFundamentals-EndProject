package com.endproject.javafundamentalsendproject.Controller;

import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.OrderItem;
import com.endproject.javafundamentalsendproject.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDialogController implements Initializable {

    @FXML
    private TextField quantityTextField;
    @FXML
    private TableView<Product> inventoryTableView;
    @FXML
    private Label exceptionLabel;
    Database database;
    private Product selectedProduct;
    private int selectedQuantity;
    private ObservableList<Product> productInventory;
    private List<Product> stockChanges = new ArrayList<>();
    private  ObservableList<OrderItem> orderItems;



    public OrderDialogController(Database database, ObservableList<OrderItem> orderItems) {
        this.database = database;
        this.orderItems = orderItems;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productInventory = FXCollections.observableArrayList(database.getProducts());

        orderItems = FXCollections.observableArrayList();
        inventoryTableView.setItems(productInventory);


    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onAddToOrderButtonClick(ActionEvent actionEvent) {
        selectedProduct = inventoryTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                selectedQuantity = Integer.parseInt(quantityTextField.getText());

                if (selectedQuantity > 0 && selectedQuantity <= selectedProduct.getStock()) {

                    int newStock = selectedProduct.getStock() - selectedQuantity;
                    selectedProduct.setStock(newStock);

                    stockChanges.add(new Product(selectedProduct.getProductId(), newStock, selectedProduct.getName(), selectedProduct.getCategory(), selectedProduct.getPrice(), selectedProduct.getDescription()));

                    orderItems.add(new OrderItem(selectedProduct, selectedQuantity));

                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    stage.close();
                } else {
                    exceptionLabel.setText("Invalid quantity");
                }

                exceptionLabel.setText("");

            } catch (NumberFormatException e) {

                exceptionLabel.setText("select a product and enter a valid quantity");
            }
        }
    }

    public ObservableList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public List<Product> getStockChanges() {
        return stockChanges;
    }

}
