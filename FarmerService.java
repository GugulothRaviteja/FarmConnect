package service;

import model.Order;
import model.Product;
import exceptions.*;


public class FarmerService implements ProductOperations {
    private Product[] productList = new Product[100];
    public static Order[] orderHistory = new Order[100];

    public int productCount = 0;
    public static int orderCount = 0;

    public void addProduct(Product product) {
        if (productCount < productList.length) {
            productList[productCount++] = product;
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Product list is full!");
        }
    }

    public void updateProduct(int id, String newName, double newPrice, int newQuantity) throws ProductNotFoundException {
        Product product = findProductById(id);
        product.setName(newName);
        product.setPrice(newPrice);
        product.setQuantity(newQuantity);
        System.out.println("Product updated successfully!");
    }

    public void deleteProduct(int id) throws ProductNotFoundException {
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (productList[i].getId() == id) {
                found = true;
                for (int j = i; j < productCount - 1; j++) {
                    productList[j] = productList[j + 1];
                }
                productList[--productCount] = null;
                System.out.println("Product deleted successfully!");
                break;
            }
        }
        if (!found) throw new ProductNotFoundException("Product not found!");
    }

@Override
    public void viewProducts(Product[] products, int productCount) {
        if (productCount == 0) {
            System.out.println("No products available.");
        } else {
            for (int i = 0; i < productCount; i++) {
                System.out.println("\n----* Available Products *----");
                System.out.println(productList[i]);
            }
        }
    }

    public void viewSoldProducts() {
        if (orderCount == 0) {
            System.out.println("No products sold out.");
        } else {
            System.out.println("\n----* Sold Products *----");
            for (int i = 0; i < orderCount; i++) {
                Order order = orderHistory[i];
                System.out.println("-----------------------------------");
                System.out.println("Buyer Name   : " + order.getBuyerName());
                System.out.println("Buyer Phone  : " + order.getBuyerPhone());
                System.out.println("Buyer Email  : " + order.getBuyerEmail());
                System.out.println("Product ID   : " + order.getProductId());
                System.out.println("Product Name : " + order.getProductName());
                System.out.println("Quantity     : " + order.getQuantity());
                System.out.println("Total Amount : " + order.getTotalAmount());
            }
        }
    }

    public Product findProductById(int id) throws ProductNotFoundException {
        for (int i = 0; i < productCount; i++) {
            if (productList[i].getId() == id) {
                return productList[i];
            }
        }
        throw new ProductNotFoundException("Product not found!");
    }

    public Product[] getProducts() {
        return productList;
    }

    public int getProductCount() {
        return productCount;
    }
}