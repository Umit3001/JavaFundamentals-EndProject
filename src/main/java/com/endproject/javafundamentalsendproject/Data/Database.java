package com.endproject.javafundamentalsendproject.Data;

import com.endproject.javafundamentalsendproject.Model.CompanyRole;
import com.endproject.javafundamentalsendproject.Model.Order;
import com.endproject.javafundamentalsendproject.Model.Product;
import com.endproject.javafundamentalsendproject.Model.User;
import exceptions.UnknownObjectException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final File FILE_PATH = new File("src/main/resources/com/endproject/javafundamentalsendproject/Database.dat");

    private List<Order> orders = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Database() {
        if (FILE_PATH.exists()) {
           loadFromFile();
        } else {
            loadStandardData();
        }
    }

    private void loadStandardData() {

        try{
            Files.createFile(FILE_PATH.toPath());
        }catch (FileAlreadyExistsException ignored) {
            System.out.println("File could not be deleted, overwriting data");
        } catch (Exception e) {
            e.printStackTrace();
        }

        addProducts();
        addUsers();

        saveToFile();


    }

    private void addProducts() {

        products.add(new Product(1,2, "Fender Stratocaster Electric Guitar", "Guitar", 1199.99, "The guitar you want"));
        products.add(new Product(2,1, "Casio CTK-240", "Keyboard", 109.99, "The keyboard you want"));
    }

    private void addUsers() {

        users.add(new User(10,"Umit", "Can", "Umit", "1234", CompanyRole.sales));
        users.add(new User(11,"Hans", "Bakker", "Hans", "1234", CompanyRole.manager));
    }


    public void updateProductStock(List<Product> stockChanges) {
        for (Product change : stockChanges) {
            for (Product product : products) {
                if (product.equals(change)) {
                    product.setStock(change.getStock());
                    break;
                }
            }
        }
    }
    public void addProduct(Product newProduct) {
        products.add(newProduct);
    }
    public void editProduct(Product selectedItem) {
        for (Product product : products) {
            if (product.equals(selectedItem)) {
                product.setName(selectedItem.getName());
                product.setCategory(selectedItem.getCategory());
                product.setPrice(selectedItem.getPrice());
                product.setDescription(selectedItem.getDescription());
                break;
            }
        }
    }
    public void deleteProduct(Product selectedItem) {
        products.remove(selectedItem);
    }

    public void saveToFile() {
        System.out.println("Saving data to file");
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH, false);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Object object : orders) {
                oos.writeObject(object);
            }
            for(Object object : products) {
                oos.writeObject(object);
            }
            for(Object object : users) {
                oos.writeObject(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to save data to file");
        }
    }

    public void loadFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            loadObjects(ois);
            System.out.println("Loaded data from file");
        } catch (IOException e) {
            try {
                Files.delete(FILE_PATH.toPath());
            } catch (IOException e1) {
                e.printStackTrace();
                loadStandardData();
            }
        } catch (ClassNotFoundException | UnknownObjectException e) {
            e.printStackTrace();
            System.out.println("Unable to load data from file");
        }
    }

    private void loadObjects(ObjectInputStream ois) throws IOException, ClassNotFoundException,UnknownObjectException {
        while (true) {
            try {
                Object object = ois.readObject();
                if (object instanceof Order order) {
                    orders.add(order);
                } else if (object instanceof Product product) {
                    products.add(product);
                } else if (object instanceof User user) {
                    users.add(user);
                } else {
                    throw new UnknownObjectException();

                }
            } catch (EOFException e) {
                return;
            } catch (ClassNotFoundException e) {
                throw new UnknownObjectException(e);
            }

        }
    }


    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<User> getUsers() {
        return users;
    }
}
