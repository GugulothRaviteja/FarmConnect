package main;

import model.*;
import service.*;
import exceptions.*;
import java.util.*;

public class FarmConnectApp {
    public static void main(String[] args) throws InputMismatchException, ProductNotFoundException {
        Scanner sc = new Scanner(System.in);

        FarmerService farmerService = new FarmerService();
        BuyerService buyerService = new BuyerService();

        while (true) {
            System.out.println("\n***** FarmConnect *****");
            System.out.println("1. Farmer");
            System.out.println("2. Buyer");
            System.out.println("3. Exit");
            System.out.print("Choose Role: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        Farmer farmer = new Farmer("Raviteja", "raviteja@gmail.com", "6305017463");
                        System.out.println("\n====================================");
                        System.out.println("Welcome to Farm " + farmer.getName());
                        System.out.println("====================================");
                        farmerMenu(sc, farmerService);
                        break;
                    case 2:
                        buyerMenu(sc, farmerService, buyerService);
                        break;
                    case 3:
                        System.out.println("Exiting... Thank you!");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid choice!");
            sc.nextLine();
            }catch (OutOfStockException e){
                System.out.println("Invalid input type!");
            }
        }
    }

    private static void farmerMenu(Scanner sc, FarmerService service){
        while (true) {
            System.out.println("\n---* Farmer Menu *---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Products");
            System.out.println("5. View sold out products");
            System.out.println("6. View Product by ID");
            System.out.println("7. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\n------------------------");
                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Product Name: ");
                        String name = sc.next();
                        System.out.print("Enter Product Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Product Quantity: ");
                        int qty = sc.nextInt();
                        service.addProduct(new Product(id, name, price, qty));
                        break;

                    case 2:
                        System.out.print("Enter Product ID to update: ");
                        id = sc.nextInt();
                        System.out.print("New Product Name: ");
                        name = sc.next();
                        System.out.print("New Product Price: ");
                        price = sc.nextDouble();
                        System.out.print("New Product Quantity: ");
                        qty = sc.nextInt();
                        service.updateProduct(id, name, price, qty);
                        break;

                    case 3:

                        System.out.print("Enter Product ID to delete: ");
                        id = sc.nextInt();
                        service.deleteProduct(id);
                        break;

                    case 4:
                        service.viewProducts(service.getProducts(), service.getProductCount());
                        break;
                    case 5:
                        service.viewSoldProducts();
                        break;
                    case 6:
                        System.out.print("Enter Product ID to search: ");
                        id = sc.nextInt();
                        try {
                            Product product = service.findProductById(id);
                            System.out.println(product);
                        } catch (ProductNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 7:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }catch (InputMismatchException e){
                    System.out.println("Invalid input type!");
                    sc.nextLine();
            }catch (ProductNotFoundException e){
                    System.out.println("Invalid input type!");
            }
        }
    }

    private static void buyerMenu(Scanner sc, FarmerService farmerService, BuyerService buyerService)
            throws OutOfStockException, ProductNotFoundException {
        while (true) {
            System.out.println("\n--- Buyer Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Place Order");
            System.out.println("3. View Order History");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    buyerService.viewProducts(farmerService.getProducts(), farmerService.getProductCount());
                    break;
                case 2 :
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    buyerService.placeOrder(farmerService.getProducts(), farmerService.getProductCount(), id, qty);
                    break;

                case 3:
                    System.out.println("Enter your registered email: ");
                    String email = sc.next();
                    buyerService.viewOrderHistory(email);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}