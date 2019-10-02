package entity;

import util.DiscountType;
import util.DiscountTypeView;


public class Campaign {
    private Category category;
    private DiscountTypeView discountType;
    private double discountRateOrAmount;
    private int quantity;

    public Campaign(Category category, DiscountTypeView discountType, Double discountRateOrAmount, int quantity ){
        this.category = category;
        this.discountType = discountType;
        this.discountRateOrAmount = discountRateOrAmount;
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public DiscountTypeView getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountTypeView discountType) {
        this.discountType = discountType;
    }

    public double getDiscountRateOrAmount() {
        return discountRateOrAmount;
    }

    public void setDiscountRateOrAmount(double discountRateOrAmount) {
        this.discountRateOrAmount = discountRateOrAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
