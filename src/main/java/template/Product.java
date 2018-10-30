package template;

import java.math.BigDecimal;

public class Product {
    String name;
    int quantity;
    BigDecimal price;
    BigDecimal totalPrice;

    public Product(String name, int quantity, BigDecimal price, BigDecimal totalPrice){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;

    }

    public Product(String name, int quantity, BigDecimal price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price.multiply(new BigDecimal(quantity));
    }

    public Product(String name, BigDecimal price){
        this(name, 1, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
