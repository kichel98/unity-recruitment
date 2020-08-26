import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("100.00"))
        );
        App app = new App(products, new ProportionalCalculator());
        app.calculateAndPrintDiscounts();
    }
}
