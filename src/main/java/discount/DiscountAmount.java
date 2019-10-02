package discount;

public class DiscountAmount extends Discount {
    @Override
    public Double makeDiscount(Double price, Double discount) {
        return price - discount;
    }
}
