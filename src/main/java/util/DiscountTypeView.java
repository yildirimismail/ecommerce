package util;

public enum DiscountTypeView implements ITextEnum {
    RATE(DiscountType.RATE, "RATE"),
    AMOUNT(DiscountType.AMOUNT, "AMOUNT");

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
