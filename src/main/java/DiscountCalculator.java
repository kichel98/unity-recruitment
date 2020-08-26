import java.math.BigDecimal;
import java.util.List;

public interface DiscountCalculator {
    List<BigDecimal> calculateDiscounts(List<Product> products, BigDecimal totalDiscount);
}
