package app;

import java.math.BigDecimal;

/**
 * Class representing single product.
 */
public class Product {
    /**
     * Name of product.
     */
    private String name;
    /**
     * Price of product, max scale of BigDecimal should be 2, e.g. 123.45
     */
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
