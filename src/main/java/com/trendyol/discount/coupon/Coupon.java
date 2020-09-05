package com.trendyol.discount.coupon;

import com.trendyol.discount.DiscountType;

public class Coupon{
    private double minPurchaseAmount;
    private double discountAmount;
    private DiscountType discountType;

    public Coupon(double minPurchaseAmount, double discountAmount, DiscountType discountType) {
        this.minPurchaseAmount = minPurchaseAmount;
        this.discountAmount = discountAmount;
        this.discountType = discountType;
    }


    public double getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(double minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public boolean isApplicable(double totalAmount){
        return totalAmount >= minPurchaseAmount;
    }

    public double calculate(double totalPrice) {
        if (isApplicable(totalPrice)) {
            return CouponCalculator.calculate(this, totalPrice);
        }
        return 0.0;
    }
}
