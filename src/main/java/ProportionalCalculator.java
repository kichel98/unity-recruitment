import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProportionalCalculator implements DiscountCalculator {
    @Override
    public List<BigDecimal> calculateDiscounts(List<Product> products, BigDecimal totalDiscount) {
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        BigDecimal sumOfPrices = products
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumOfDiscounts = new BigDecimal("0.00");
        List<BigDecimal> discounts = new ArrayList<>();
        for (Product product : products) {
            // scale needs to be consulted and well described
            BigDecimal productRatio = product.getPrice().divide(sumOfPrices, 20, RoundingMode.HALF_EVEN); // TODO division by zero!
            BigDecimal proportionalDiscount = productRatio
                    .multiply(totalDiscount)
                    .setScale(2, RoundingMode.FLOOR);
            BigDecimal finalDiscount = product.getPrice().min(proportionalDiscount);
            discounts.add(finalDiscount);
            sumOfDiscounts = sumOfDiscounts.add(finalDiscount);
        }
        BigDecimal discountRemainder = totalDiscount.subtract(sumOfDiscounts);
        BigDecimal lastProductDiscount = products.get(products.size() - 1).getPrice()
                .min(discounts.get(discounts.size() - 1).add(discountRemainder));
        discounts.set(discounts.size() - 1, lastProductDiscount);
        return discounts;
    }
}
