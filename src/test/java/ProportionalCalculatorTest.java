import app.Product;
import app.ProportionalCalculator;
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
    void shouldReturnPricesWhenTotalDiscountIsHigherThanSumOfAllProducts() {
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
                new Product("Product2", new BigDecimal("800.00")),
                new Product("Product3", new BigDecimal("1100.00")),
                new Product("Product4", new BigDecimal("1000.00")),
                new Product("Product5", new BigDecimal("500.00"))
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

    /**
     * It is the test, when sum of discounts is not equal to total discount
     * (due to low price of last product) - for more details check README.md.
     */
    @Test
    void shouldReturnHigherDiscountForLastProductWhenCannotBeProportional3() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("400.00")),
                new Product("Product2", new BigDecimal("800.00")),
                new Product("Product3", new BigDecimal("1100.00")),
                new Product("Product4", new BigDecimal("1000.00")),
                new Product("Product5", new BigDecimal("0.01"))
        );
        BigDecimal totalDiscount = new BigDecimal("1500.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(
                new BigDecimal("181.81"),
                new BigDecimal("363.63"),
                new BigDecimal("499.99"),
                new BigDecimal("454.54"),
                new BigDecimal("0.01"));
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnNoDiscountsForNoProducts() {
        // given
        List<Product> products = List.of();
        BigDecimal totalDiscount = new BigDecimal("0.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of();
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }

    @Test
    void shouldReturnZeroDiscountsForFreeProducts() {
        // given
        List<Product> products = List.of(
                new Product("Product1", new BigDecimal("0.00"))
        );
        BigDecimal totalDiscount = new BigDecimal("10.00");

        // when
        List<BigDecimal> discounts = calculator.calculateDiscounts(products, totalDiscount);

        // then
        List<BigDecimal> expectedDiscounts = List.of(
                new BigDecimal("0.00")
        );
        Assertions.assertIterableEquals(expectedDiscounts, discounts);
    }
}

