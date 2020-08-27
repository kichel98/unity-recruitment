package app;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {
    /**
     * One of implementation of DiscountCalculator, passed by constructor or changed by setter.
     */
    private DiscountCalculator discountCalculator;
    /**
     * List of products, on which we perform discount calculation.
     */
    private List<Product> products;
    /**
     * Total amount of money that order should be discounted by.
     */
    private BigDecimal totalDiscount;

    public App(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
        this.products = new ArrayList<>();
        this.totalDiscount = new BigDecimal("0.00");
    }

    public App(DiscountCalculator discountCalculator, List<Product> products, BigDecimal totalDiscount) {
        this.products = products;
        this.discountCalculator = discountCalculator;
        this.totalDiscount = totalDiscount;
    }

    /**
     * Reads all data needed to perform calculation and validates it using regex.
     * Regex specifies decimal number with max two decimal places (comma may be delimiter).
     * Here is whole validation of data - in case of not matching regex,
     * (e.g. wrong number format) throws NoSuchElementException,
     * in case of too many products throws IllegalArgumentException,
     * otherwise sets {@link #products} and {@link #totalDiscount} and returns nothing
     *
     * @param scanner
     */
    public void readProductsAndDiscountFromInput(Scanner scanner) {
        final int MAX_NUMBER_OF_PRODUCTS = 5;
        final String NUMBER_PATTERN = "\\d+([.,]\\d{1,2})?";
        System.out.println("Enter total amount of discount:");
        String totalDiscount = replaceCommaWithDot(scanner.next(NUMBER_PATTERN));
        System.out.println("Enter number of products:");
        int numberOfProducts = scanner.nextInt();
        if (numberOfProducts > MAX_NUMBER_OF_PRODUCTS) {
            throw new IllegalArgumentException();
        }
        scanner.nextLine();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println("Enter product name");
            String name = scanner.nextLine();
            System.out.println("Enter product price");
            String price = replaceCommaWithDot(scanner.next(NUMBER_PATTERN));
            scanner.nextLine();
            Product product = new Product(name, new BigDecimal(price));
            products.add(product);
        }
        this.products = products;
        this.totalDiscount = new BigDecimal(totalDiscount);
    }

    /**
     * Performs discount calculation using DiscountCalculator and prints it to the output.
     * Prices are printed according to local currency format (e.g. 2,53 zł in Poland).
     */
    public void calculateAndPrintDiscounts() {
        List<BigDecimal> discounts = discountCalculator.calculateDiscounts(products, totalDiscount);

        Iterator<Product> productsIterator = products.iterator();
        Iterator<BigDecimal> discountsIterator = discounts.iterator();
        while (productsIterator.hasNext() && discountsIterator.hasNext()) {
            Product product = productsIterator.next();
            BigDecimal discount = discountsIterator.next();
            String discountWithLocalCurrency = NumberFormat.getCurrencyInstance().format(discount);
            System.out.println("Discount for " + product.getName() + " = " + discountWithLocalCurrency);
        }
    }

    public DiscountCalculator getDiscountCalculator() {
        return discountCalculator;
    }

    public void setDiscountCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    private String replaceCommaWithDot(String decimalNumber) {
        return decimalNumber.replace(',', '.');
    }
}
