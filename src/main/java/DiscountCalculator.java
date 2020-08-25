import java.util.List;

public interface DiscountCalculator {
    List<Double> calculateDiscounts(List<Product> products, double totalDiscount);
}
