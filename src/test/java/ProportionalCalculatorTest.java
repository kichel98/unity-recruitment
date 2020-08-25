import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProportionalCalculatorTest {
    ProportionalCalculator calculator = new ProportionalCalculator();

    @Test
    void shouldReturnFullDiscountForOneProduct() {
        // given
        List<Product> products = List.of(
                new Product("Product1", 100)
        );
        double totalDiscount = 50;

        // when
        List<Double> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<Double> expectedDiscounts = List.of(50.0);
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnExactProportionalDiscount() {
        // given
        List<Product> products = List.of(
                new Product("Product1", 500),
                new Product("Product2", 1500)
        );
        double totalDiscount = 100;

        // when
        List<Double> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<Double> expectedDiscounts = List.of(25.0, 75.0);
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnPricesForTotalDiscountHigherThanSumOfAllProducts() {
        // given
        List<Product> products = List.of(
                new Product("Product1", 500),
                new Product("Product2", 1500)
        );
        double totalDiscount = 2500;

        // when
        List<Double> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<Double> expectedDiscounts = List.of(500.0, 1500.0);
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnHigherDiscountForLastProductWhenCannotBeProportional() {
        // given
        List<Product> products = List.of(
                new Product("Product1", 1000),
                new Product("Product2", 1)
        );
        double totalDiscount = 0.01; // 1 grosz

        // when
        List<Double> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<Double> expectedDiscounts = List.of(0.0, 0.01);
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnHigherDiscountForLastProductWhenCannotBeProportional2() {
        // given
        List<Product> products = List.of(
                new Product("Product1", 400),
                new Product("Product1", 800),
                new Product("Product1", 1100),
                new Product("Product1", 1000),
                new Product("Product2", 500)
        );
        double totalDiscount = 1;

        // when
        List<Double> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<Double> expectedDiscounts = List.of(0.10, 0.21, 0.28, 0.26, 0.15);
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    // TODO przykład z README, gdzie nie da się dołożyć nadwyżki do ostatniego
    // TODO jeden produkt za 0 zł/pusta lista/uwaga na dzielenie przez zero

}

