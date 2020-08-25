import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Product1", 100)
        );
        App app = new App(products, new ProportionalCalculator());
        app.calculateAndPrintDiscounts();
    }
}
