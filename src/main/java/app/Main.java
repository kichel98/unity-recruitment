package app;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class, creates App with Proportional Calculator,
 * depute reading data and calculating discounts.
 */
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        App app = new App(new ProportionalCalculator());
        try {
            app.readProductsAndDiscountFromInput(scanner);
        } catch (NoSuchElementException e) {
            System.out.println("You entered number in wrong format!");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Max number of products is 5!");
            return;
        }
        app.calculateAndPrintDiscounts();
    }
}
