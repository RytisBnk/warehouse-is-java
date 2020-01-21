package is.warehouse.UI.impl;

import is.warehouse.UI.ApplicationMenu;
import is.warehouse.model.Product;
import is.warehouse.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleMenu implements ApplicationMenu {
    private ProductService productService;

    @Autowired
    public ConsoleMenu(ProductService productService) {
        this.productService = productService;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            showMenuMain();
            String choice = in.nextLine();
            switch (choice) {
                case "1":
                    displayProducts(productService.getAllProducts());
                    in.nextLine();
                    break;
                case "2":
                    System.out.println("Enter date:\n");
                    try {
                        LocalDate selection = LocalDate.parse(in.nextLine());
                        displayProducts(productService.getProductsByExpiration(selection));
                        in.nextLine();
                    }
                    catch (DateTimeParseException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Enter maximum quantity:\n");
                    try {
                        int maxQuantity = Integer.parseInt(in.nextLine());
                        displayProducts(productService.getProductsByQuantity(maxQuantity));
                        in.nextLine();
                    }
                    catch (NumberFormatException exc) {
                        System.out.println("Input is not a number");
                    }
                    break;
                case "q":
                    return;

                default:
                    System.out.println("Invalid selection");
            }
        }
    }

    private void showMenuMain() {
        System.out.println(
                "Main menu: " +
                "\n 1. View all products" +
                "\n 2. View products based on expiration date " +
                "\n 3. View products based on quantity " +
                "\n q. Exit\n"
        );
    }

    private void displayProducts(List<Product> products) {
        System.out.println("Name | Code | Expiration | Quantity left");
        for (Product product : products) {
            System.out.println(
                    StringUtils.rightPad(product.getName(), 20, ' ') + " | "
                    + StringUtils.rightPad(Long.toString(product.getCode()), 20, ' ') + " | "
                    + product.getExpiration() + " | "
                    + product.getQuantity()
            );
        }
    }
}
