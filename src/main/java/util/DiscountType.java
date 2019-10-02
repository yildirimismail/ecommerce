package util;

public enum DiscountType implements IValueEnum{

    RATE(1),
    AMOUNT(2),
    FREE_DELIVERY(2);

    private final Integer value;

    DiscountType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
