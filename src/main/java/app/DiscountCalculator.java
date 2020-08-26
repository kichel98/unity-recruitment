package app;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface defines method needed to be implemented
 * by concrete algorithm of calculating discounts.
 * Serves as abstract strategy in Strategy pattern.
 */
public interface DiscountCalculator {
    /**
     * Distributes total discount to products.
     * Returns list of discounts for list of products and total discount,
     * i.e i-th discount corresponds to i-th product.
     *
     * @param products      list of products that order comprises
     * @param totalDiscount total amount of money that order should be discounted by
     * @return list of discounts, its size is equal to {@code products} size
     */
    List<BigDecimal> calculateDiscounts(List<Product> products, BigDecimal totalDiscount);
}
