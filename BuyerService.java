package service;

import model.*;
import java.util.Scanner;
import exceptions.*;


public class BuyerService implements ProductOperations {

    @Override
    public void viewProducts(Product[] productList, int productCount) {
        if (productCount == 0) {
            System.out.println("No products to display!");
        } else {
            for (int i = 0; i < productCount; i++) {
                System.out.println("\n----* Available Products *----");
                System.out.println(productList[i]);
            }
        }
    }

    public void placeOrder(Product[] productList, int productCount, int productId, int quantity)
            throws ProductNotFoundException, OutOfStockException {
        Product product = null;

        for (int i = 0; i < productCount; i++) {
            if (productList[i].getId() == productId) {
                product = productList[i];
                break;
            }
        }

        if (product == null) {
            throw new ProductNotFoundException("Invalid product ID!");
        }

        if (quantity > product.getQuantity()) {
            throw new OutOfStockException("Not enough stock available!");
        }

        double total = product.getPrice() * quantity;
        product.setQuantity(product.getQuantity() - quantity);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String buyerName = sc.nextLine();
        System.out.println("Enter your email: ");
        String buyerEmail = sc.nextLine();
        System.out.println("Enter your phone: ");
        String buyerPhone = sc.nextLine();

        int orderId = FarmerService.orderCount + 1;
        Order order = new Order(orderId, productId, product.getName(), quantity, total, buyerName, buyerEmail, buyerPhone);

        if (FarmerService.orderCount < FarmerService.orderHistory.length) {
            FarmerService.orderHistory[FarmerService.orderCount++] = order;
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order storage full!");
        }
    }

    public void viewOrderHistory(String buyerEmail) {
        boolean found = false;

        for (int i = 0; i < FarmerService.orderCount; i++) {
            Order order = FarmerService.orderHistory[i];

                if (order!=null && order.getBuyerEmail().equalsIgnoreCase(buyerEmail)) {
                found = true;
                System.out.println("\n***** Your Order *****");
                System.out.println("---------------------------------");
                System.out.println("Product ID   : " + order.getProductId());
                System.out.println("Product Name : " + order.getProductName());
                System.out.println("Quantity     : " + order.getQuantity());
                System.out.println("Total Amount : " + order.getTotalAmount());
            }
        }

        if (!found) {
            System.out.println("No orders yet.");
        }
    }
}