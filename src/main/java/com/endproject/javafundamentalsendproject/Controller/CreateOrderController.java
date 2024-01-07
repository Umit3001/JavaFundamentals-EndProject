package com.endproject.javafundamentalsendproject.Controller;
import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.Order;
import com.endproject.javafundamentalsendproject.Model.OrderItem;
import com.endproject.javafundamentalsendproject.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable {

    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField eMailAddressTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TableView<OrderItem> productsTableView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private Label exceptionLabel;
    private Database database;
    private ObservableList<OrderItem> orderItems;

    private List<Product> stockChanges = new ArrayList<>();

    private OrderDialogController orderDialogController;


    public CreateOrderController(Database database) {
        this.database = database;
        this.orderDialogController = new OrderDialogController(database, orderItems);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        orderItems = FXCollections.observableArrayList();
        productsTableView.setItems(orderItems);

    }

    public void onAddProductButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/endproject/javafundamentalsendproject/OrderDialogView.fxml"));
        fxmlLoader.setController(orderDialogController);

        Scene scene = new Scene(fxmlLoader.load());
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.setTitle("Select Product");
        dialog.showAndWait();

        ObservableList<OrderItem> selectedProducts = orderDialogController.getOrderItems();
        addProductToTableView(selectedProducts);

    }

    public void addProductToTableView(ObservableList<OrderItem> selectedProducts) {

        if (selectedProducts != null && !selectedProducts.isEmpty()) {
            for (OrderItem selectedProduct : selectedProducts) {
                boolean found = false;
                for (OrderItem item : productsTableView.getItems()) {
                    if (item.getProduct().getName().equals(selectedProduct.getProduct().getName())) {

                        item.setQuantity(item.getQuantity() + selectedProduct.getQuantity());

                        found = true;
                        break;
                    }
                }
                if (!found) {
                    productsTableView.getItems().add(selectedProduct);
                }
            }
        }
        productsTableView.refresh();

    }


    public void onDeleteProductButtonClick(ActionEvent actionEvent) {
        OrderItem selectedItem = productsTableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            productsTableView.getItems().remove(selectedItem);

            Product product = selectedItem.getProduct();
            int returnedQuantity = selectedItem.getQuantity();

            int newStock = product.getStock() + returnedQuantity;

            product.setStock(newStock);

        }
    }


    public void onCreateOrderButtonClick(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Confirm Order");
        confirmationAlert.setContentText("Are you sure you want to create this order?");
        confirmationAlert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            try {
                if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() ||
                        eMailAddressTextField.getText().isEmpty() || phoneNumberTextField.getText().isEmpty()) {
                    exceptionLabel.setText("Please fill in all fields");
                } else if (productsTableView.getItems().isEmpty()) {
                    exceptionLabel.setText("Please add products to the order");
                } else {
                    exceptionLabel.setText("");
                    Order order = new Order(setOrderID(), firstNameTextField.getText(), lastNameTextField.getText(), eMailAddressTextField.getText(), phoneNumberTextField.getText(), new ArrayList<>(productsTableView.getItems()), localDateTime(), calculateTotalPrice());

                    stockChanges.addAll(orderDialogController.getStockChanges());
                    database.updateProductStock(stockChanges);

                    database.getOrders().add(order);
                    clearFields();
                }
            } catch (Exception e) {
                exceptionLabel.setText("Please fill in all fields");
            }
        }
    }

    private void clearFields() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        eMailAddressTextField.clear();
        phoneNumberTextField.clear();
        productsTableView.getItems().clear();
    }



    private double calculateTotalPrice() {
        double total = 0.00;

        ObservableList<OrderItem> selectedProducts = productsTableView.getItems();
        for (OrderItem orderItem : selectedProducts) {
            Product product = orderItem.getProduct();
            int quantity = orderItem.getQuantity();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    private String localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(zoneId);
        return formatter.format(localDateTime);
    }

    private int setOrderID() {
        int orderID = 0;
        for (Order order : database.getOrders()) {
            if (order.getOrderId() > orderID) {
                orderID = order.getOrderId();
            }
        }
        orderID++;
        return orderID;
    }



}
