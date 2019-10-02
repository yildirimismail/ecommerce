package entity;

import util.DiscountType;

public class Coupon {
    private DiscountType discountType;
    private double minAmount;
    private double discountRate;

    public Coupon (DiscountType discountType, double minAmount, double discountRate){
        this.discountType = discountType;
        this.minAmount = minAmount;
        this.discountRate = discountRate;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
