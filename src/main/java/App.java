import java.util.List;
import java.util.Scanner;

public class App {
    private List<Product> products;
    private DiscountCalculator discountCalculator;

    public App(List<Product> products, DiscountCalculator discountCalculator) {
        this.products = products;
        this.discountCalculator = discountCalculator;
    }

    public void readProductsFromInput(Scanner scanner) {
        // TODO
    }

    public void calculateAndPrintDiscounts() {
        // TODO
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public DiscountCalculator getDiscountCalculator() {
        return discountCalculator;
    }

    public void setDiscountCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }
}
