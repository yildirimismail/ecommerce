package discount;

public class DiscountRate extends Discount {
    @Override
    public Double makeDiscount(Double price, Double discount) {
        return price - (price * discount) / 100;
    }
}
