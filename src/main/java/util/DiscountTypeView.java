package util;

public enum DiscountTypeView implements ITextEnum {
    RATE(DiscountType.RATE, "RATE"),
    AMOUNT(DiscountType.AMOUNT, "AMOUNT"),
    FREE_DELIVERY(DiscountType.FREE_DELIVERY, "FREE_DELIVERY");

    private String text;
    private DiscountType actualEnum;

    DiscountTypeView(DiscountType status, String text) {
        this.text = text;
        this.actualEnum = status;
    }


    public DiscountType getActualEnum() {
        return actualEnum;
    }

    public String getText() {
        return text;
    }
}
