import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProportionalCalculatorTest {
    ProportionalCalculator calculator = new ProportionalCalculator();

    @Test
    void shouldReturnFullDiscountForOneProduct() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("100.00"))
        );
        BigDecimal totalDiscount = new BigDecimal("50.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(new BigDecimal("50.00"));
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnExactProportionalDiscount() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("500.00")),
                new Product("Product2", new BigDecimal("1500.00"))
        );
        BigDecimal totalDiscount = new BigDecimal("100.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(new BigDecimal("25.00"), new BigDecimal("75.00"));
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnPricesForTotalDiscountHigherThanSumOfAllProducts() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("500.00")),
                new Product("Product2", new BigDecimal("1500.00"))
        );
        BigDecimal totalDiscount = new BigDecimal("2500.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(new BigDecimal("500.00"), new BigDecimal("1500.00"));
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnHigherDiscountForLastProductWhenCannotBeProportional() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("1000.00")),
                new Product("Product2", new BigDecimal("1.00"))
        );
        BigDecimal totalDiscount = new BigDecimal("0.01"); // 1 grosz

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(new BigDecimal("0.00"), new BigDecimal("0.01"));
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnHigherDiscountForLastProductWhenCannotBeProportional2() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("400.00")),
                new Product("Product1", new BigDecimal("800.00")),
                new Product("Product1", new BigDecimal("1100.00")),
                new Product("Product1", new BigDecimal("1000.00")),
                new Product("Product2", new BigDecimal("500.00"))
        );
        BigDecimal totalDiscount = new BigDecimal("1.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(
                new BigDecimal("0.10"),
                new BigDecimal("0.21"),
                new BigDecimal("0.28"),
                new BigDecimal("0.26"),
                new BigDecimal("0.15"));
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    // TODO przykład z README, gdzie nie da się dołożyć nadwyżki do ostatniego
    // TODO jeden produkt za 0 zł/pusta lista/uwaga na dzielenie przez zero

}

