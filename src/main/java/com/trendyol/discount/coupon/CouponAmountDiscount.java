package com.trendyol.discount.coupon;

public class CouponAmountDiscount implements CouponDiscount {
    @Override
    public double apply(double totalPrice, double discountAmount) {
        return discountAmount;
    }
}
