package app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of calculating discounts which does it in proportional way.
 */
public class ProportionalCalculator implements DiscountCalculator {

    // scale needs to be consulted
    /**
     * Number of decimal places in product price ratio.
     */
    final int RATIO_SCALE = 32;
    /**
     * Number of decimal places in discount per product.
     */
    final int DISCOUNT_SCALE = 2;

    /**
     * Each product gets discount proportional to its price.
     * In case of impossibility of doing it precisely,
     * discount is rounded down to two decimal places and rounding error is added to discount of last product,
     * but discount per product can never be higher than price of product.
     * @param products      list of products that order comprises
     * @param totalDiscount total amount of money that order should be discounted by
     * @return list of discounts per product
     */
    @Override
    public List<BigDecimal> calculateDiscounts(List<Product> products, BigDecimal totalDiscount) {
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        BigDecimal sumOfPrices = products
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumOfDiscounts = BigDecimal.ZERO;
        List<BigDecimal> discounts = new ArrayList<>();
        for (Product product : products) {
            BigDecimal finalDiscount = calculateExactlyProportionalDiscountForProduct(product, totalDiscount, sumOfPrices);
            discounts.add(finalDiscount);
            sumOfDiscounts = sumOfDiscounts.add(finalDiscount);
        }
        addRemainderToLastDiscount(products, totalDiscount, sumOfDiscounts, discounts);
        return discounts;
    }

    private BigDecimal calculateExactlyProportionalDiscountForProduct(Product product, BigDecimal totalDiscount,
                                                                      BigDecimal sumOfPrices) {
        if (sumOfPrices.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // rounding mode should be consulted
        BigDecimal productRatio = product.getPrice().divide(sumOfPrices, RATIO_SCALE, RoundingMode.HALF_EVEN);
        BigDecimal proportionalDiscount = productRatio
                .multiply(totalDiscount)
                .setScale(DISCOUNT_SCALE, RoundingMode.FLOOR);
        return product.getPrice().min(proportionalDiscount);
    }

    private void addRemainderToLastDiscount(List<Product> products, BigDecimal totalDiscount,
                                            BigDecimal sumOfDiscounts, List<BigDecimal> discounts) {
        int lastProductIndex = products.size() - 1;
        int lastDiscountIndex = products.size() - 1;
        Product lastProduct = products.get(lastProductIndex);
        BigDecimal lastDiscount = discounts.get(lastDiscountIndex);

        BigDecimal discountRemainder = totalDiscount.subtract(sumOfDiscounts);
        BigDecimal newLastDiscount = lastProduct.getPrice()
                .min(lastDiscount.add(discountRemainder));
        discounts.set(lastDiscountIndex, newLastDiscount);
    }
}
