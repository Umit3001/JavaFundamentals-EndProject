package com.endproject.javafundamentalsendproject.Controller;

import com.endproject.javafundamentalsendproject.Data.Database;
import com.endproject.javafundamentalsendproject.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductInventoryController implements Initializable {
    @FXML
    private Label exceptionLabel;
    @FXML
    private TextField stockTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TableView<Product> productInventoryTableView;
    private Product selectedItem;
    private Database database;
    private ObservableList<Product> products;

    public ProductInventoryController(Database database) {
        this.database = database;
        products = FXCollections.observableArrayList(this.database.getProducts());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productInventoryTableView.setItems(products);
        productInventoryTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTextFields(newValue);
        });
    }


    public void onAddProductButtonClick(ActionEvent actionEvent) {
        try {
            if (nameTextField.getText().isEmpty() || categoryTextField.getText().isEmpty() || priceTextField.getText().isEmpty() || stockTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty()) {
                exceptionLabel.setText("Fill in all fields please");
            } else {
                int stock = Integer.parseInt(stockTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());

                if (stock < 0 || price < 0) {
                    exceptionLabel.setText("Stock and price must be non-negative numbers.");
                } else {
                    Product newProduct = new Product(setProductsId(), stock, nameTextField.getText(), categoryTextField.getText(), price, descriptionTextField.getText());
                    database.addProduct(newProduct);
                    products.add(newProduct);
                    productInventoryTableView.refresh();
                    exceptionLabel.setText("");
                }
            }
        } catch (NumberFormatException e) {
            exceptionLabel.setText("Invalid input. Please enter valid numbers for stock and price.");
        }
    }

    public void onEditProductButtonClick(ActionEvent actionEvent) {
        if (selectedItem != null) {
            try {
                if (nameTextField.getText().isEmpty() || categoryTextField.getText().isEmpty() || priceTextField.getText().isEmpty() || stockTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty()) {
                    exceptionLabel.setText("Fill in all fields please");
                } else {
                    int stock = Integer.parseInt(stockTextField.getText());
                    double price = Double.parseDouble(priceTextField.getText());

                    if (stock < 0 || price < 0) {
                        exceptionLabel.setText("Stock and price must be non-negative numbers.");
                    } else {
                        selectedItem.setStock(stock);
                        selectedItem.setName(nameTextField.getText());
                        selectedItem.setCategory(categoryTextField.getText());
                        selectedItem.setPrice(price);
                        selectedItem.setDescription(descriptionTextField.getText());
                        database.editProduct(selectedItem);
                        productInventoryTableView.refresh();

                        exceptionLabel.setText("");
                    }
                }
            } catch (NumberFormatException e) {
                exceptionLabel.setText("Invalid input. Please enter valid numbers for stock and price.");
            }
        } else {
            exceptionLabel.setText("Select a product please");
        }
    }

    public void populateTextFields(Product product) {
        if (product != null) {
            stockTextField.setText(Integer.toString(product.getStock()));
            nameTextField.setText(product.getName());
            categoryTextField.setText(product.getCategory());
            priceTextField.setText(Double.toString(product.getPrice()));
            descriptionTextField.setText(product.getDescription());

            selectedItem = product;
        }
    }

    public void onDeleteProductButtonClick(ActionEvent actionEvent) {
        selectedItem = (Product) productInventoryTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                products.remove(selectedItem);
                database.deleteProduct(selectedItem);
                productInventoryTableView.refresh();
                exceptionLabel.setText("");  // Clear any previous error messages
            } catch (Exception e) {
                exceptionLabel.setText("An error occurred while deleting the product.");
            }
        } else {
            exceptionLabel.setText("Select a product please");
        }
    }

    public int setProductsId() {
        int id = 1;
        for (Product product : products) {
            if(product.getProductId() > id) {
                id = product.getProductId();
            }
        }
        id++;
        return id;

    }
}
