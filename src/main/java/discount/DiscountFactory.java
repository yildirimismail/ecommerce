package discount;

public class DiscountFactory {
    public static Discount getDiscountType(String discountType) {
        if (discountType.equalsIgnoreCase("AMOUNT")) {
            return new DiscountAmount();
        } else if (discountType.equalsIgnoreCase("RATE")) {
            return new DiscountRate();
        }
        return null;
    }
}
